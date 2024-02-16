package com.inzent.deliverablesAPI.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.inzent.deliverablesAPI.service.DeliverablesRegService;
import com.inzent.deliverablesAPI.vo.DeliverablesRegVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

import java.util.List;

@RestController
public class DeliverablesRegController {

    @Autowired
    private DeliverablesRegService deliverablesRegService;

    // 등록된 산출물 조회 (GET)
    @RequestMapping(value = "/deliverablesReg", method= RequestMethod.GET)
    public List<DeliverablesRegVO> getReg(Model model) throws Exception{
        List<DeliverablesRegVO> list = deliverablesRegService.getReg();
        model.addAttribute("registerlist", list);
        return list;
    }

    // 산출물 파일 정보 디비 저장 후 파일을 서버에 저장 (POST)
    // 아직 프론트엔드와 연계되지 않아 REGISTRATION_ID를 임의로 넣어서 구현 (추후 작업 진행)
    @RequestMapping(value = "/deliverablesReg", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String postReg(@RequestPart(required = false, value = "filelist") List<MultipartFile> filelist, @RequestPart(required = false, value = "vo") DeliverablesRegVO deliverablesRegVO) throws Exception{

        String uploadFolderName = deliverablesRegVO.getDeliverTitle();
        String uploadSubFolderName = deliverablesRegVO.getDeliverId();
        String path = System.getProperty("user.dir") + File.separator + uploadFolderName  + File.separator + uploadSubFolderName;
        File folder = new File(path);
        String[] registerList = {"test1", "test2", "test3"};

        // 폴더가 없다면
        if (!folder.exists()) {
            // 폴더 생성
            if (folder.mkdir()) {
                System.out.println("폴더 생성 성공");

                int i=0;
                for(MultipartFile file : filelist) {

                    // 파일 전송
                    try {
                        file.transferTo(new File(path + "\\" + file.getOriginalFilename()));
                        deliverablesRegService.postReg(deliverablesRegVO, registerList[i], file.getOriginalFilename(), path);
                        i++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //deliverablesRegService.postReg(deliverablesRegVO);
            } else
                System.out.println("폴더 생성 실패");
        } else {
            int i=0;
            for(MultipartFile file : filelist) {

                // 파일 전송
                try {
                    file.transferTo(new File(path + "\\" + file.getOriginalFilename()));
                    deliverablesRegService.postReg(deliverablesRegVO, registerList[i], file.getOriginalFilename(), path);
                    i++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //deliverablesRegService.postReg(deliverablesRegVO);
        }

        return "Success";
    }

    // 산출물 파일 정보 디비 수정 후 기존 파일 삭제 후 파일을 서버에 저장 (PUT)
    @RequestMapping(value = "/deliverablesReg", method = RequestMethod.PUT)
    public String putReg(@RequestBody DeliverablesRegVO deliverablesRegVO){
        deliverablesRegService.putReg(deliverablesRegVO);
        return "Success";
    }

    // 산출물 파일 정보 디비 삭제 후 파일을 서버에서 삭제 (DELETE)
    @RequestMapping(value = "/deliverablesReg", method = RequestMethod.DELETE)
    public String deleteReg(@RequestBody List<String> deleteList){

        // 서버의 파일 삭제
        List<HashMap<String, Object>> list = deliverablesRegService.deleteFileInfo(deleteList);

        for(int i=0; i<list.size(); i++) {
            String deleteFilePath = list.get(i).get("filepath") + "\\" + list.get(i).get("filename");
            File file = new File(deleteFilePath);

            if( file.exists() ){
                if(file.delete()){
                    System.out.println("파일삭제 성공");
                    // DB 내 데이터 삭제
                    deliverablesRegService.deleteReg(deleteList);
                }else{
                    System.out.println("파일삭제 실패");
                }
            }else{
                System.out.println("파일이 존재하지 않습니다.");
            }
        }

        return "Success";
    }
}
