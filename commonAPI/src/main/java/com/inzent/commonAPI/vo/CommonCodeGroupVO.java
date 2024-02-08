package com.inzent.commonAPI.vo;

public class CommonCodeGroupVO {

    private  String code_group_id;

    private  String code_group_name;

    private  String sub_system;


    public CommonCodeGroupVO(String code_group_id,  String code_group_name, String sub_system) {

        this.code_group_id = code_group_id;
        this.code_group_name = code_group_name;
        this.sub_system = sub_system;
    }
    public String getCode_group_name() {
        return code_group_name;
    }


    public String getCode_group_id() {
        return code_group_id;
    }

    public void setCode_group_id(String code_group_id) {
        this.code_group_id = code_group_id;
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
