package com.inzent.commonAPI.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class Json {

    //List<Map<String, Object>> 변수를 json 형식의 String 으로 변환
    public String map2Json (List<Map<String, Object>> map){
        JSONArray jsonArray = new JSONArray();

        for (Map<String, Object> o : map){
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

    public static Map<String, Object> jsonToMap (String jsonString) throws Exception{
    	ObjectMapper objectMapper = new ObjectMapper();
	    TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {};
	    return objectMapper.readValue(jsonString, typeReference);
    }
}
