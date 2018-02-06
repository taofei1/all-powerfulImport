package com.example.domain;

import java.util.ArrayList;
import java.util.Objects;

public class Difference {
    private Integer id;
    private String tableName;
    private String field;
    private String fieldName;
    private String differenceName;
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

    public String getDifferenceName() {
        return differenceName;
    }

    public void setDifferenceName(String differenceName) {
        this.differenceName = differenceName;
    }

    public Character getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Character isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public String toString() {
        return "Difference[" + "id=" + id + ", tableName='" + tableName + '\'' + ", field='" + field + '\'' + ", fieldName='" + fieldName + '\'' + ", differenceName='" + differenceName + '\'' + ", isEnable=" + isEnable + ']';
    }


}
