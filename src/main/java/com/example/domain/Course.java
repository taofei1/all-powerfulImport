package com.example.domain;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable{
    private String cid;

    public Course(){}
    public Course(String cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", list=" + list +
                '}';
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    private String cname;
    private List<Student> list;
}
