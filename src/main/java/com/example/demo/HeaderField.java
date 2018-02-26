package com.example.demo;

/**
 *
 */
public class HeaderField {
    //列数
    private int cellNum;
    //对应数据库表的字段
    private String field;
    //是否是主键
    private int isPrimaryKey;
    //类型
    private String fieldType;

    public HeaderField() {
        //默认不是主键
        this.isPrimaryKey = 0;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(int isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public int getCellNum() {
        return cellNum;
    }

    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
