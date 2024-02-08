package com.inzent.commonAPI.service;


import com.inzent.commonAPI.mapper.CommonCodeMapper;
import com.inzent.commonAPI.vo.CommonCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inzent.commonAPI.common.Json;

import java.util.List;
import java.util.Map;

@Service
public class CommonCodeService {
    @Autowired
    private CommonCodeMapper commonCodeMapper;

    private final Json json = new Json();
    public String inquiry(){

        List<Map<String, Object>> result = commonCodeMapper.inquiry();

        return json.map2Json(result);
    }

    public String groupInquiry(){
        List<Map<String,Object>> result =  commonCodeMapper.groupInquiry();
        return json.map2Json(result);
    }

    public String codeInquiry(String code_id){
        List<Map<String,Object>> result =  commonCodeMapper.codeInquiry(code_id);
        return json.map2Json(result);
    }

    public String topInquiry(){
        List<Map<String,Object>> result =  commonCodeMapper.topInquiry();
        return json.map2Json(result);
    }
    public void registration(CommonCodeVO commonCodeVO){
       commonCodeMapper.registration(commonCodeVO);

    }
    public void update(CommonCodeVO commonCodeVO){
        commonCodeMapper.update(commonCodeVO);
    }

    public int  delete (CommonCodeVO commonCodeVO){
        return commonCodeMapper.delete(commonCodeVO);
    }


}
