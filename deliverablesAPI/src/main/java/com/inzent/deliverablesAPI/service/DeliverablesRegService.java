package com.inzent.deliverablesAPI.service;

import com.inzent.deliverablesAPI.mapper.DeliverablesRegMapper;
import com.inzent.deliverablesAPI.vo.DeliverablesRegVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DeliverablesRegService {
    private DeliverablesRegMapper deliverablesRegMapper;

    public DeliverablesRegService(DeliverablesRegMapper deliverablesRegMapper) {
        this.deliverablesRegMapper = deliverablesRegMapper;
    }

    // GET
    public List<DeliverablesRegVO> getReg() {
        return deliverablesRegMapper.getReg();
    }

    public List<DeliverablesRegVO> getRegOne(String id) {
        return deliverablesRegMapper.getRegOne(id);
    }

    // POST
    public void postReg(DeliverablesRegVO deliverablesRegVO, String regiId, String filename, String filePath) {
        deliverablesRegMapper.postReg(deliverablesRegVO, regiId, filename, filePath);
    }

    // PUT
    public void putReg(DeliverablesRegVO deliverablesRegVO) {
        deliverablesRegMapper.putReg(deliverablesRegVO);
    }

    // DELETE
    public void deleteReg(List<String> deleteList) {
        deliverablesRegMapper.deleteReg(deleteList);
    }

    public List<HashMap<String, Object>> deleteFileInfo(List<String> deleteList) { return deliverablesRegMapper.deleteFileInfo(deleteList); }
}
