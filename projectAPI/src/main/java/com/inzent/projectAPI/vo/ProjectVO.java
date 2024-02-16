package com.inzent.projectAPI.vo;

public class ProjectVO {
    private String project_id;
    private String project_code;
    private String project_name;
    private Integer hub_contract_count;
    private Integer agent_contract_count;
    private String delete_yn;
    private String customer_name;
    private String team_id;
    private String project_status;
    private String inspection_status;
    private String inspection_date;
    private String inspection_user_id;
    private String inspection_user_name;
    private String inspection_rejection_reason;

    public ProjectVO(String project_id, String project_code, String project_name, Integer hub_contract_count, Integer agent_contract_count, String delete_yn, String customer_name, String team_id, String project_status, String inspection_status, String inspection_date, String inspection_user_id, String inspection_user_name, String inspection_rejection_reason) {
        this.project_id = project_id;
        this.project_code = project_code;
        this.project_name = project_name;
        this.hub_contract_count = hub_contract_count;
        this.agent_contract_count = agent_contract_count;
        this.delete_yn = delete_yn;
        this.customer_name = customer_name;
        this.team_id = team_id;
        this.project_status = project_status;
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

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Integer getHub_contract_count() {
        return hub_contract_count;
    }

    public void setHub_contract_count(Integer hub_contract_count) {
        this.hub_contract_count = hub_contract_count;
    }

    public Integer getAgent_contract_count() {
        return agent_contract_count;
    }

    public void setAgent_contract_count(Integer agent_contract_count) {
        this.agent_contract_count = agent_contract_count;
    }

    public String getDelete_yn() {
        return delete_yn;
    }

    public void setDelete_yn(String delete_yn) {
        this.delete_yn = delete_yn;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
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
