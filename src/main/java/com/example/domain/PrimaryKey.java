package com.example.domain;

public class PrimaryKey {
    private Integer id;
    private String primarykey;
    private String primarykey_name;
    private String table_name;
    private String primarykey_column;
    private String history_column;
    private String recent_column;
    private String force_recent_column;
    private Character enable_;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public String getPrimarykey_name() {
        return primarykey_name;
    }

    public void setPrimarykey_name(String primarykey_name) {
        this.primarykey_name = primarykey_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getPrimarykey_column() {
        return primarykey_column;
    }

    public void setPrimarykey_column(String primarykey_column) {
        this.primarykey_column = primarykey_column;
    }

    public String getHistory_column() {
        return history_column;
    }

    public void setHistory_column(String history_column) {
        this.history_column = history_column;
    }

    public String getRecent_column() {
        return recent_column;
    }

    public void setRecent_column(String recent_column) {
        this.recent_column = recent_column;
    }

    public String getForce_recent_column() {
        return force_recent_column;
    }

    public void setForce_recent_column(String force_recent_column) {
        this.force_recent_column = force_recent_column;
    }

    public Character getEnable_() {
        return enable_;
    }

    public void setEnable_(Character enable_) {
        this.enable_ = enable_;
    }

    @Override
    public String toString() {
        return "PrimaryKey{" + "id=" + id + ", primarykey='" + primarykey + '\'' + ", primarykey_name='" + primarykey_name + '\'' + ", table_name='" + table_name + '\'' + ", primarykey_column='" + primarykey_column + '\'' + ", history_column='" + history_column + '\'' + ", recent_column='" + recent_column + '\'' + ", force_recent_column='" + force_recent_column + '\'' + ", enable_=" + enable_ + '}';
    }
}
