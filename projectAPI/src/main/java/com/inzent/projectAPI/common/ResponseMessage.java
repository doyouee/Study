package com.inzent.projectAPI.common;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseMessage {
    public String setMessage(String code, String message){
        JSONObject json = new JSONObject();
        try {
            json.put("responseCode" , code);
            json.put("responseMessage" , message);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return json.toString();
    }
}
