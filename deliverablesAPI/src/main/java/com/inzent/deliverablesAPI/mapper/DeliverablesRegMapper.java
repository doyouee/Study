package com.inzent.deliverablesAPI.mapper;

import java.util.*;

import com.inzent.deliverablesAPI.vo.DeliverablesRegVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeliverablesRegMapper {
    // GET
    List<DeliverablesRegVO> getReg();

    // GET
    List<DeliverablesRegVO> getRegOne(String id);

    // POST
    void postReg(@Param("regis") DeliverablesRegVO deliverablesRegVO, String regiId, String filename, String filePath);

    // PUT
    void putReg(@Param("regis") DeliverablesRegVO deliverablesRegVO);

    // DELETE
    void deleteReg(List<String> deleteList);
    List<HashMap<String, Object>> deleteFileInfo(List<String> deleteList);
}
