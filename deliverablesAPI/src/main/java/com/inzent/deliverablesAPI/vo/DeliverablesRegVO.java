package com.inzent.deliverablesAPI.vo;

import java.sql.Clob;

public class DeliverablesRegVO {
    private String registerId;
    private String projectId;
    private String deliverId;
    private char contentType;
    private String deliverTitle;
    private String fileName;
    private String filePath;
    private String textContents;
    private String notRegisReason;
    private String regisUserId;
    private String regisUserName;
    private String regisDate;
    private String deleteUserId;
    private String deleteUserName;
    private String deleteDate;

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(String deliverId) {
        this.deliverId = deliverId;
    }

    public char getContentType() {
        return contentType;
    }

    public void setContentType(char contentType) {
        this.contentType = contentType;
    }

    public String getDeliverTitle() {
        return deliverTitle;
    }

    public void setDeliverTitle(String deliverTitle) {
        this.deliverTitle = deliverTitle;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTextContents() {
        return textContents;
    }

    public void setTextContents(String textContents) {
        this.textContents = textContents;
    }

    public String getNotRegisReason() {
        return notRegisReason;
    }

    public void setNotRegisReason(String notRegisReason) {
        this.notRegisReason = notRegisReason;
    }

    public String getRegisUserId() {
        return regisUserId;
    }

    public void setRegisUserId(String regisUserId) {
        this.regisUserId = regisUserId;
    }

    public String getRegisUserName() {
        return regisUserName;
    }

    public void setRegisUserName(String regisUserName) {
        this.regisUserName = regisUserName;
    }

    public String getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(String regisDate) {
        this.regisDate = regisDate;
    }

    public String getDeleteUserId() {
        return deleteUserId;
    }

    public void setDeleteUserId(String deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    public String getDeleteUserName() {
        return deleteUserName;
    }

    public void setDeleteUserName(String deleteUserName) {
        this.deleteUserName = deleteUserName;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    @Override
    public String toString() {
        return "DeliverablesRegVO{" +
                "registerId='" + registerId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", deliverId='" + deliverId + '\'' +
                ", contentType=" + contentType +
                ", deliverTitle='" + deliverTitle + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", textContents=" + textContents +
                ", notRegisReason='" + notRegisReason + '\'' +
                ", regisUserId='" + regisUserId + '\'' +
                ", regisUserName='" + regisUserName + '\'' +
                ", regisDate='" + regisDate + '\'' +
                ", deleteUserId='" + deleteUserId + '\'' +
                ", deleteUserName='" + deleteUserName + '\'' +
                ", deleteDate='" + deleteDate + '\'' +
                '}';
    }
}
