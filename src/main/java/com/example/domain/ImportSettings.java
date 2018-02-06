package com.example.domain;

public class ImportSettings {
    private Integer id;
    private String import_name;
    private String name;
    private String primarykey;
    private Character enable_;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImport_name() {
        return import_name;
    }

    public void setImport_name(String import_name) {
        this.import_name = import_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public Character getEnable_() {
        return enable_;
    }

    public void setEnable_(Character enable_) {
        this.enable_ = enable_;
    }

    @Override
    public String toString() {
        return "ImportSettings{" + "id=" + id + ", import_name='" + import_name + '\'' + ", name='" + name + '\'' + ", primarykey='" + primarykey + '\'' + ", enable_=" + enable_ + '}';
    }
}
