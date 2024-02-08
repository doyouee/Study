package com.inzent.commonAPI.service;


import com.inzent.commonAPI.common.Json;
import com.inzent.commonAPI.mapper.CommonCodeGroupMapper;
import com.inzent.commonAPI.vo.CommonCodeGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonCodeGroupService {
    @Autowired
    private CommonCodeGroupMapper commonCodeGroupMapper;

    private final Json json = new Json();
    public String inquiry(){

        List<Map<String, Object>> result = commonCodeGroupMapper.inquiry();
        return json.map2Json(result);
    }

    public int idDuplication(String code_group_id){
        return commonCodeGroupMapper.idDuplication(code_group_id);
    }
    public int nameDuplication(String code_group_name){
        return commonCodeGroupMapper.nameDuplication(code_group_name);
    }
    public void registration(CommonCodeGroupVO commonCodeGroupVO){
        commonCodeGroupMapper.registration(commonCodeGroupVO);

    }
    public void update(CommonCodeGroupVO commonCodeGroupVO){
        commonCodeGroupMapper.update(commonCodeGroupVO);
    }

    public int  delete (CommonCodeGroupVO commonCodeGroupVO){
        return commonCodeGroupMapper.delete(commonCodeGroupVO);
    }


}
