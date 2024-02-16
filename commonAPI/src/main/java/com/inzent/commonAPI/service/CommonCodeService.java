package com.inzent.commonAPI.service;


import com.inzent.commonAPI.mapper.CommonCodeMapper;
import com.inzent.commonAPI.mapper.CommonMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonCodeService {
    @Autowired
    private CommonCodeMapper commonCodeMapper;


    public String inquiry(){


        List<Map<String, Object>> result = (List<Map<String, Object>>) commonCodeMapper.inquiry();
        String jsonString = "";

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (Map<String, Object> o : result){
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





}
