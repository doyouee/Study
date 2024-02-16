package com.inzent.projectAPI.vo;

import org.springframework.lang.Nullable;

public class ProjectInspectionVO {
    public String project_id;
    public String inspection_status;
    public String inspection_date;
    private String inspection_user_id;
    private String inspection_user_name;
    private String inspection_rejection_reason;

    public ProjectInspectionVO(String project_id, String inspection_status, String inspection_date, String inspection_user_id, String inspection_user_name, String inspection_rejection_reason) {
        this.project_id = project_id;
        this.inspection_status = inspection_status;
        this.inspection_date = inspection_date;
        this.inspection_user_id = inspection_user_id;
        this.inspection_user_name = inspection_user_name;
        this.inspection_rejection_reason = inspection_rejection_reason;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getInspection_status() {
        return inspection_status;
    }

    public void setInspection_status(String inspection_status) {
        this.inspection_status = inspection_status;
    }

    public String getInspection_date() {
        return inspection_date;
    }

    public void setInspection_date(String inspection_date) {
        this.inspection_date = inspection_date;
    }

    public String getInspection_user_id() {
        return inspection_user_id;
    }

    public void setInspection_user_id(String inspection_user_id) {
        this.inspection_user_id = inspection_user_id;
    }

    public String getInspection_user_name() {
        return inspection_user_name;
    }

    public void setInspection_user_name(String inspection_user_name) {
        this.inspection_user_name = inspection_user_name;
    }

    public String getInspection_rejection_reason() {
        return inspection_rejection_reason;
    }

    public void setInspection_rejection_reason(String inspection_rejection_reason) {
        this.inspection_rejection_reason = inspection_rejection_reason;
    }
}
