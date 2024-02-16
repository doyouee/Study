package com.inzent.deliverablesAPI.service;

import com.inzent.deliverablesAPI.mapper.DeliverablesListMapper;
import com.inzent.deliverablesAPI.vo.DeliverablesListVO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliverablesListService {
    private DeliverablesListMapper deliverablesListMapper;

    public DeliverablesListService(DeliverablesListMapper deliverablesListMapper) {
        this.deliverablesListMapper = deliverablesListMapper;
    }

    // GET
    public List<DeliverablesListVO> getList() {
        return deliverablesListMapper.getList();
    }

    // POST
    public void postList(DeliverablesListVO deliverablesListVO) {
        deliverablesListMapper.postList(deliverablesListVO);
    }

    // PUT
    public void putList(DeliverablesListVO deliverablesListVO) {
        deliverablesListMapper.putList(deliverablesListVO);
    }

    // DELETE
    public void deleteList(String id) {
        deliverablesListMapper.deleteList(id);
    }

}
