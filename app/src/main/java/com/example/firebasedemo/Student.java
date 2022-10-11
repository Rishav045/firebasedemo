package com.example.firebasedemo;

public class Student {
    private String name;
    private String departmentName;
    public Student() {
        this.setName("");
        this.setDepartmentName("");
    }
    public Student(String name, String departmentName)
    {
        this.setName(name);
        this.setDepartmentName(departmentName);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
