package com.example.service.impl;

import com.example.domain.Difference;
import com.example.domain.PrimaryKey;
import com.example.domain.Synonym;
import com.example.mapper.DifferenceMapper;
import com.example.mapper.ImportMapper;
import com.example.mapper.SynonymMapper;
import com.github.pagehelper.StringUtil;
import org.apache.commons.collections4.ListUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.tool.Diff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CommonImport {
    @Autowired
    private ImportMapper importMapper;
    @Autowired
    private SynonymMapper synonymMapper;
    @Autowired
    private DifferenceMapper differenceMapper;
    private int firstRowIndex = -1;

    public void importExcel(String importName, String primarykey, File file) {
        int sheetIndex = -1;
        if (!file.exists()) {
            throw new RuntimeException("文件不存在！");
        }
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
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
            throw new RuntimeException("文件格式不支持，仅支持excel!");
        }
        List<PrimaryKey> list = null;
        if (StringUtils.isEmpty(primarykey)) {
            list = importMapper.findByNameWithNoPrimaryKey(importName);
        } else {
            list = importMapper.findByNameWithPrimaryKey(importName, primarykey);
        }
        if (CollectionUtils.isEmpty(list)) {
            throw new RuntimeException("查询不到主键信息！");
        }
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            HashMap fieldMap = null;
            Sheet s = wb.getSheetAt(i);
            if (s == null) continue;
            fieldMap = getHeaderField(list, s);
            if (fieldMap != null) {
                sheetIndex = i;
            } else {
                continue;
            }

        }
    }

    //寻找标题列
    private HashMap getHeaderField(List<PrimaryKey> list, Sheet s) {
        HashMap fieldMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            PrimaryKey primaryKey = list.get(i);
            String tableName = primaryKey.getTable_name();
            String primarykeyField = primaryKey.getPrimarykey();
            String primarykeyFieldName = primaryKey.getPrimarykey_name();
            String historyField = primaryKey.getHistory_column();
            String recentField = primaryKey.getRecent_column();
            String forcerencentField = primaryKey.getForce_recent_column();
            if (StringUtils.isEmpty(primarykeyField)) {
                throw new RuntimeException("请设置[" + tableName + "]的主键字段");
            }
            List<Synonym> synonymList = synonymMapper.findByTableName(tableName);
            List<Difference> differenceList = differenceMapper.findByTableName(tableName);
            HashMap primaryKeyMap = new HashMap();
            for (int j = 0; j < 10; j++) {
                Row row = (Row) s.getRow(j);
                if (row == null) {
                    continue; //排除空行
                }
                for (int k = 0; k < row.getLastCellNum(); k++) {
                    Cell cell = (Cell) row.getCell(k);
                    if (cell == null) {
                        continue;//排除空列
                    }
                    String val = getStringVal(cell);
                    if (StringUtils.isEmpty(val)) {
                        continue; //跳过空格
                    }
                    String tmpVal = val;
                    Difference difference = differenceMapper.findByDifferenceName(val);
                    if (difference != null) {
                        val = difference.getFieldName();
                    }
                    if (primarykeyFieldName.indexOf(val) == -1 && historyField.indexOf(val) == -1 && recentField.indexOf(val) == -1 && forcerencentField.indexOf(val) == -1) {

                    }


                }
            }
        }
        return null;
    }

    public String getStringVal(Cell cell) {
        String v = "";
        switch (cell.getCellTypeEnum()) {
            case _NONE:
                v = "";
            case BLANK:
                v = "";
            case STRING:
                v = cell.getStringCellValue();
            case BOOLEAN:
                v = cell.getBooleanCellValue() ? "true" : "false";
            case FORMULA:
                v = cell.getCellFormula();
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
