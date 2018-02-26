package com.example.service.impl;

import com.example.demo.HeaderField;
import com.example.domain.*;
import com.example.mapper.*;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommonImport {
    @Autowired
    private ImportMapper importMapper;
    @Autowired
    private SynonymMapper synonymMapper;
    @Autowired
    private DifferenceMapper differenceMapper;
    @Autowired
    private FieldMappingMapper fieldMappingMapper;
    @Autowired
    private CommonInsertMapper commonInsertMapper;
    private int firstRowIndex = -1;

    @Transactional
    public void importExcel(String importName, String primarykey, MultipartFile file) throws Exception {
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Workbook wb = null;
        if (file.getName().endsWith("xls")) {
            try {
                wb = new HSSFWorkbook(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (file.getName().endsWith("xlsx")) {
            try {
                wb = new XSSFWorkbook(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new Exception("文件格式不支持，仅支持excel!");
        }
        List<PrimaryKey> list;
        if (StringUtils.isEmpty(primarykey)) {
            list = importMapper.findByNameWithNoPrimaryKey(importName);
        } else {
            list = importMapper.findByNameWithPrimaryKey(importName, primarykey);
        }
        List<String> tableList = new ArrayList<>();
        for (PrimaryKey primaryKey : list) {
            tableList.add(primaryKey.getTable_name());
        }
        if (CollectionUtils.isEmpty(list)) {
            throw new Exception("查询不到主键信息！");
        }
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            HashMap<String, List> fieldMap;
            Sheet s = wb.getSheetAt(i);
            if (s == null) continue;
            fieldMap = getHeaderField(list, s);
            if (fieldMap != null) {
            } else {
                //该页没数据
                continue;
            }
            if (firstRowIndex == -1) {
                //该页找不到标题行翻下页
                continue;
            }
            for (int j = firstRowIndex + 1; j < s.getLastRowNum(); j++) {
                Row r = s.getRow(j);
                if (r == null) {
                    continue;
                }
                for (String tableName : tableList) {
                    List<HeaderField> table;
                    List<String> values = new ArrayList<>();
                    if (fieldMap.containsKey(tableName + "_field")) {
                        table = fieldMap.get(tableName + "_field");
                        int flag = 0;
                        for (HeaderField headerField : table) {
                            values.add(getStringVal(r.getCell(headerField.getCellNum())));
                            if (flag == 0 && headerField.getIsPrimaryKey() == 1) {
                                flag = 1;
                            }
                        }
                        if (flag == 0) {
                            throw new Exception(tableName + "表主键没有找到！");
                        } else {
                            List<Object> objectList = processFieldType(table, values);
                            commonInsertMapper.insertByCritear(tableName, table, objectList);
                        }

                    }
                }

            }

        }
    }

    /**
     * 处理非String(varchar) 类型的字段
     *
     * @param fieldList 字段列表
     * @param values    等待插入的值
     * @return 新的字段列表
     */
    private List<Object> processFieldType(List<HeaderField> fieldList, List<String> values) throws Exception {
        List<Object> list = new ArrayList<>();
        list.addAll(values);
        for (int i = 0; i < fieldList.size(); i++) {
            if (!"".equalsIgnoreCase(values.get(i))) {
                String fieldType = fieldList.get(i).getFieldType();
                if ("".equals(fieldType) || "String".equalsIgnoreCase(fieldType)) {
                    continue;
                } else if ("int".equalsIgnoreCase(fieldType) || "Integer".equalsIgnoreCase(fieldType)) {
                    list.set(i, Integer.parseInt(values.get(i)));
                } else if ("double".equalsIgnoreCase(fieldType)) {
                    list.set(i, Double.parseDouble(values.get(i)));
                } else if ("float".equalsIgnoreCase(fieldType)) {
                    list.set(i, Float.parseFloat(values.get(i)));
                } else if (fieldType.substring(0, fieldType.indexOf("(")).equalsIgnoreCase("date")) {
                    SimpleDateFormat sdf = new SimpleDateFormat(fieldType.substring(fieldType.indexOf("(") + 1, fieldType.length() - 1));
                    try {
                        list.set(i, sdf.parse(values.get(i)));
                    } catch (ParseException e) {
                        System.out.println(fieldType + "日期类型转换异常！");
                        e.printStackTrace();
                    }
                } else {
                    throw new Exception("未知类型:" + fieldType);
                }
            }

        }
        return list;
    }

    /**
     * 读取标题行列位置及类型信息
     *
     * @param list
     * @param s
     * @return
     */
    private HashMap getHeaderField(List<PrimaryKey> list, Sheet s) throws Exception {
        HashMap<String, List> fieldMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            PrimaryKey primaryKey = list.get(i);
            String tableName = primaryKey.getTable_name();
            String primarykeyField = primaryKey.getPrimarykey();
            String primarykeyFieldName = primaryKey.getPrimarykey_name();

            if (StringUtils.isEmpty(primarykeyField)) {
                throw new Exception("请设置[" + tableName + "]的主键字段！");
            }
            List<Synonym> synonymList = synonymMapper.findByTableName(tableName);
            List<Difference> differenceList = differenceMapper.findByTableName(tableName);
            String primaryKeySynonym = "";
            for (Synonym synonym : synonymList) {
                if (synonym.getTable().equals(tableName) && synonym.getColumn().equals(primarykeyField)) {
                    primaryKeySynonym = synonym.getSynonymName();
                }
            }

            List fieldList = new ArrayList();
            int startLine = 0;
            int endLine = s.getLastRowNum();
            if (firstRowIndex != -1) {
                startLine = firstRowIndex;
                endLine = firstRowIndex + 1;
            }
            for (int j = startLine; j < endLine; j++) {
                Row row =  s.getRow(j);
                if (row == null) {
                    continue; //排除空行
                }
                int flag=0;
                for (int k = 0; k < row.getLastCellNum(); k++) {
                    flag = 0;
                    Cell cell = row.getCell(k);
                    if (cell == null) {
                        continue;//排除空列
                    }
                    String val = getStringVal(cell);
                    if (StringUtils.isEmpty(val)) {
                        continue; //跳过空格
                    }
                    HeaderField headerField = new HeaderField();
                    if (val.equals(primarykeyFieldName)) {
                        headerField.setCellNum(k);
                        headerField.setField(primarykeyField);
                        headerField.setIsPrimaryKey(1);
                        headerField.setFieldType(headerField.getFieldType());

                        flag = 1;
                    } else if (!primaryKeySynonym.equals("")) {
                        String[] primarykeySy = primaryKeySynonym.split(",");
                        for (String a : primarykeySy) {
                            if (val.equals(a)) {
                                headerField.setCellNum(k);
                                headerField.setField(primarykeyField);
                                headerField.setIsPrimaryKey(1);
                                headerField.setFieldType(headerField.getFieldType());

                                flag = 1;
                                break;
                            }
                        }
                    }
                    if (flag == 0) {
                        List<FieldMapping> mappingList = fieldMappingMapper.findByTableName(tableName);
                        for (FieldMapping fieldMapping : mappingList) {
                            if (fieldMapping.getField().equals(val)) {
                                headerField.setCellNum(k);
                                headerField.setField(fieldMapping.getField());
                                headerField.setFieldType(fieldMapping.getFieldType());
                                flag = 1;
                                break;
                            } else {
                                String fieldSynonym = "";
                                for (Synonym synonym : synonymList) {
                                    if (synonym.getTable().equals(tableName) && synonym.getColumn().equals(fieldMapping.getField())) {
                                        fieldSynonym = synonym.getSynonymName();
                                        break;
                                    }
                                }
                                if (!fieldSynonym.equals("")) {
                                    String[] fieldSynonymList = fieldSynonym.split(",");
                                    for (String synonym : fieldSynonymList) {
                                        if (synonym.equals(val)) {
                                            headerField.setCellNum(k);
                                            headerField.setField(fieldMapping.getField());
                                            headerField.setFieldType(fieldMapping.getFieldType());

                                            flag = 1;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (flag == 1) {

                                break;
                            }
                        }

                    } else {
                        //找到主键
                        if (firstRowIndex == -1) {
                            firstRowIndex = j;
                        }
                    }
                    if (headerField != null) {
                        fieldList.add(headerField);
                    }

                }
                if (flag == 1) {
                    break;
                }

            }
            fieldMap.put(tableName + "_field",fieldList);
        }
        return fieldMap;
    }

    /**
     * 将所有类型的单元格值转换为String类型读出
     * @param cell 列
     * @return 值
     */
    public String getStringVal(Cell cell) {
        String v = "";
        switch (cell.getCellTypeEnum()) {
            case _NONE:
                v = "";
                break;
            case BLANK:
                v = "";
                break;
            case STRING:
                v = cell.getStringCellValue();
                break;
            case BOOLEAN:
                v = cell.getBooleanCellValue() ? "true" : "false";
                break;
            case FORMULA:
                v = cell.getCellFormula();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;

                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }

                    Date date = cell.getDateCellValue();

                    v = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    double value = cell.getNumericCellValue();

                    Date date = DateUtil.getJavaDate(value);

                    v = sdf.format(date);
                } else {
                    DecimalFormat df = new DecimalFormat("##0.00");

                    v = df.format(cell.getNumericCellValue());

                    if (v.endsWith(".00")) {
                        v = v.replace(".00", "");
                    }
                }

        }
        return v;

    }

}
