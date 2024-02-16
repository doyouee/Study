package com.inzent.commonAPI.vo;

public class CommonCodeVO {
	private String code_id;
    private String code_name;
    private  String code_group_id;

    public CommonCodeVO(String code_id, String code_name, String code_group_id, String code_top_id) {
        this.code_id = code_id;
        this.code_name = code_name;
        this.code_group_id = code_group_id;
        this.code_top_id = code_top_id;
    }

    private  String code_top_id;
    public String getCode_id() {
        return code_id;
    }

    public void setCode_id(String code_id) {
        this.code_id = code_id;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public String getCode_group_id() {
        return code_group_id;
    }

    public void setCode_group_id(String code_group_id) {
        this.code_group_id = code_group_id;
    }

    public String getCode_top_id() {
        return code_top_id;
    }

    public void setCode_top_id(String code_top_id) {
        this.code_top_id = code_top_id;
    }







}
