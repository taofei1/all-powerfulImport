package com.example.domain;

public class FieldMapping {
    private Integer id;
    private String tableName;
    private String field;
    private String fieldName;
    private String fieldType;
    private String defaultValue;
    private Character isEnable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Character getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Character isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public String toString() {
        return "FieldMapping{" + "id=" + id + ", tableName='" + tableName + '\'' + ", field='" + field + '\'' + ", fieldName='" + fieldName + '\'' + ", fieldType='" + fieldType + '\'' + ", defaultValue='" + defaultValue + '\'' + ", isEnable=" + isEnable + '}';
    }
}
