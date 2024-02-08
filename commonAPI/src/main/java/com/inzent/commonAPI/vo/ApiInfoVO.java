package com.inzent.commonAPI.vo;

public class ApiInfoVO {
	private String api_id;
    private String api_key;
    private  String api_name;
    private  String api_url;

    public ApiInfoVO(String api_id, String api_key, String api_name, String api_url) {
        this.api_id = api_id;
        this.api_key = api_key;
        this.api_name = api_name;
        this.api_url = api_url;
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public ApiInfoVO(String api_id, String ab, String ab1, String ab2, String ab3) {
        this.api_id = api_id;


    }



}
