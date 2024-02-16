package com.inzent.commonAPI.service;


import com.inzent.commonAPI.mapper.CommonMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonService {
    @Autowired
    private CommonMapper commonMapper;

    public String apiInfo() {

        List<Map<String, Object>> result = (List<Map<String, Object>>) commonMapper.apiInfo();
        String jsonString = "";

        JSONArray jsonArray = new JSONArray();

        for (Map<String, Object> o : result){
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<String, Object> entry : o.entrySet()) {
                String key = entry.getKey();
                String value = (String) entry.getValue();
                try {
                    jsonObject.put(key,value);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
            jsonArray.put(jsonObject);


        }
        return jsonArray.toString();
    }



    public String apiExistence(String api_key){

        return  commonMapper.apiExistence(api_key);
    }




}
