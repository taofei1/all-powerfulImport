package com.example.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Student implements Serializable{
    private String sid;
    private String sname;

    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }

    private List<Course> list;
    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                '}';
    }

    private Date birthday;
    private Integer age;
    public Student(){}
    public Student(String sid, String sname, Date birthday, Integer age) {
        this.sid = sid;
        this.sname = sname;
        this.birthday = birthday;
        this.age = age;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
