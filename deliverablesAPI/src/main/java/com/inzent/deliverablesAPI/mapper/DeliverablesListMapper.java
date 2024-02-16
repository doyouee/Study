package com.inzent.deliverablesAPI.mapper;

import java.util.*;

import com.inzent.deliverablesAPI.vo.DeliverablesListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeliverablesListMapper {

    // GET
    List<DeliverablesListVO> getList();

    // POST
    void postList(@Param("deliver") DeliverablesListVO deliverablesListVO);

    // PUT
    void putList(@Param("deliver") DeliverablesListVO deliverablesListVO);

    // DELETE
    void deleteList(String id);
}
