package com.inzent.deliverablesAPI.vo;

import java.sql.Clob;

public class DeliverablesListVO {
    private String id;
    private String name;
    private String pId;
    private char grade;
    private char type;
    private String description;
    private char delete_yn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getDelete_yn() {
        return delete_yn;
    }

    public void setDelete_yn(char delete_yn) {
        this.delete_yn = delete_yn;
    }

    @Override
    public String toString() {
        return "DeliverablesListVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pId='" + pId + '\'' +
                ", grade=" + grade +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", delete_yn=" + delete_yn +
                '}';
    }
}
