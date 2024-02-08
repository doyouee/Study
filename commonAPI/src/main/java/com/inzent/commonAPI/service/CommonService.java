package com.inzent.commonAPI.service;


import com.inzent.commonAPI.mapper.CommonMapper;
import com.inzent.commonAPI.vo.MenuVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzent.commonAPI.common.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {
    @Autowired
    private CommonMapper commonMapper;
    private final Json json = new Json();
    public String apiInfo() {
        List<Map<String, Object>> result = commonMapper.apiInfo();
       return json.map2Json(result);
    }


    public String searchApiId(String api_key){
        return commonMapper.searchApiId(api_key);
    }

    public String apiExistence(String api_key){

        return  commonMapper.apiExistence(api_key);
    }

    public Map<String,List<String>> getMenu() {
        List<MenuVO> menuList = commonMapper.getMenu();
        Map<String,List<String>> resultList = new HashMap<>();
        for(MenuVO mainList : menuList){
            if(mainList.getTop_menu_id() == null){
                for(MenuVO subMenuList : menuList){
                    if(mainList.getMenu_id().equals(subMenuList.getTop_menu_id())){
                        List<String> subList = new ArrayList<>();
                        if(resultList.get(mainList.getMenu_name()) != null) {
                            subList = resultList.get(mainList.getMenu_name());
                        }
                        subList.add(subMenuList.getMenu_name());
                        resultList.put(mainList.getMenu_name(),subList);
                    }

                }
            }
        }
        return resultList;
    }
    
    public boolean tokenAuthorityConfirm(String userId, String mappingUrl, String method) {
    	try {
	        // 호출한 메소드 명, 매핑할 URL, user id를 이용하여 권한 확인
	        Map<String, Object> confirmInfo = new HashMap<String, Object>();
	        confirmInfo.put("method", method);
	        confirmInfo.put("mappingUrl", mappingUrl);
	        confirmInfo.put("userId", userId);
	        
	        String confirm = commonMapper.authorityConfirm(confirmInfo);

	        if(confirm.equals("1")) {
	        	return true;
	        }else {
	        	return false;
	        }
		} catch (Exception e) {
			e.printStackTrace();
        	return false;
		}
    }
}
