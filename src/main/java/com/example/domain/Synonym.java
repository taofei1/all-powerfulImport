package com.example.domain;


/**
 * 同义词
 */
public class Synonym {


    private Integer id;
    private String table;
    private String column;
    private String columnName;
    private String synonymName;
    private Character enable;

    public Synonym() {
    }

    public Synonym(String table, String column, String columnName, String synonym_name, Character enable) {
        this.table = table;
        this.column = column;
        this.columnName = columnName;
        this.synonymName = synonym_name;
        this.enable = enable;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSynonymName() {
        return synonymName;
    }

    public void setSynonymName(String synonym_name) {
        this.synonymName = synonym_name;
    }

    public Character getEnable() {
        return enable;
    }

    public void setEnable(Character enable) {
        this.enable = enable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Synonym[");
        sb.append("id=").append(id);
        sb.append(", table='").append(table).append('\'');
        sb.append(", column='").append(column).append('\'');
        sb.append(", columnName='").append(columnName).append('\'');
        sb.append(", synonymName='").append(synonymName).append('\'');
        sb.append(", enable=").append(enable);
        sb.append(']');
        return sb.toString();
    }
}
