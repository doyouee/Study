package com.inzent.commonAPI.vo;

public class CommonCodeVO {
	private String code_id;
    private String code_name;
    private  String code_group_id;
    private  String code_top_id;

    private  String code_group_name;

    private  String sub_system;


    public CommonCodeVO(String code_id, String code_name, String code_group_id, String code_top_id, String code_group_name, String sub_system) {
        this.code_id = code_id;
        this.code_name = code_name;
        this.code_group_id = code_group_id;
        this.code_top_id = code_top_id;
        this.code_group_name = code_group_name;
        this.sub_system = sub_system;
    }
    public String getCode_group_name() {
        return code_group_name;
    }
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




    public void setCode_group_name(String code_group_name) {
        this.code_group_name = code_group_name;
    }

    public String getSub_system() {
        return sub_system;
    }

    public void setSub_system(String sub_system) {
        this.sub_system = sub_system;
    }



}
