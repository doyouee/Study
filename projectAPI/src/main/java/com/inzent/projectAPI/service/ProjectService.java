package com.inzent.projectAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.inzent.projectAPI.common.Json;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestParam;
import org.zeroturnaround.zip.ZipUtil;

import com.inzent.projectAPI.common.HttpSender;
import com.inzent.projectAPI.mapper.ProjectMapper;
import com.inzent.projectAPI.vo.ProjectInspectionVO;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ProjectService {
    private final Json json = new Json();
	private final ProjectMapper projectMapper;
    private final HttpSender httpSender = new HttpSender();
    public ProjectService(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    /* 프로젝트 검색 조회 */
    public String searchProject(String project_id, String project_name, String team_id, Integer inspection_status) {
        List<Map<String, Object>> result = projectMapper.searchProject(project_id, project_name, team_id, inspection_status);
        System.out.println("service 결과 :: " + result);
        return json.map2Json(result);
    }

    /* 프로젝트 검수 상태 수정 */
    public String editProjectInspection(HttpServletRequest request, String userId, ProjectInspectionVO projectInspectionVO) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<HashMap<String, String>> list;
        String connect = httpSender.sndHTTP("http://localhost:8081/user", "GET", "user_email&user_name&team_id", setHeader(request));
        System.out.println("connect :: " + connect);
        try {
            list = mapper.readValue(connect, new TypeReference<ArrayList<HashMap<String, String>>>(){});
        } catch (JsonProcessingException e) { throw new RuntimeException(e); }
        for(Map<String, String> a : list) {
            if(a.get("USER_ID").equals(userId)) {
                projectInspectionVO.setInspection_user_id(userId);
                projectInspectionVO.setInspection_user_name(a.get("USER_NAME"));
            }
        }
        projectMapper.editInspection(projectInspectionVO);
        return "검수 상태 수정이 완료되었습니다.";
    }

    /* 산출물 zip 다운로드 */
    public String downloadDeliverables(HttpServletRequest request, String projectId) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<HashMap<String, String>> list;
        String rootPathList = "";
        String connect = httpSender.sndHTTP("http://localhost:8083/deliverablesReg", "GET", "", setHeader(request));
        try {
            list = mapper.readValue(connect, new TypeReference<ArrayList<HashMap<String, String>>>(){});
        } catch (JsonProcessingException e) { throw new RuntimeException(e); }
        for(Map<String, String> pSet : list) {
            if(pSet.get("PROJECT_ID").equals(projectId)) {
                if(pSet.get("CONTENT_TYPE").equals("1")) { // txt 타입일 때 .txt 다운로드
                    if(pSet.get("NOT_REGISTRATION_REASON") != null) { // 미등록 사유
                        getTxtFile(pSet.get("FILE_PATH"), pSet.get("NOT_REGISTRATION_REASON"), pSet.get("DELIVERABLES_TITLE")+"_"+"미등록"); // 경로에 [산출물제목_미등록.txt]로 저장
                    } else if(pSet.get("TEXT_CONTENTS") != null){ // 텍스트 내용
                        getTxtFile(pSet.get("FILE_PATH"), pSet.get("TEXT_CONTENTS"), pSet.get("DELIVERABLES_TITLE"));
                    }
                }
                rootPathList = pSet.get("FILE_PATH");
            }
        }
        String fileTitle = projectMapper.getProjectName(projectId);
        String fileRootPath = getRootPaht(rootPathList, projectId);
        getZip(fileRootPath, fileTitle);
        return "ZIP 다운로드가 완료되었습니다.";
    }

    /* 호출 Header 생성 */
    public Map<String, Object> setHeader(HttpServletRequest request) {
        Map<String, Object> headerValue = new HashMap<>();
        headerValue.put("USER_ID", request.getHeader("USER_ID"));
        headerValue.put("TOKEN_VALUE", request.getHeader("TOKEN_VALUE"));
        headerValue.put("API_KEY", request.getHeader("API_KEY"));
        return headerValue;
    }

    /* txt 파일 생성 method */
    public void getTxtFile(String path, String contents, String title) {
        String filePath = null;
        createPath(path);
        filePath = path +  File.separator + title + ".txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(contents);
            fileWriter.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    /* path 유효성 확인 및 생성 */
    public void createPath(String path) {
        File file = new File(path);
        Path directoryPath = Paths.get(path);
        if (!file.isDirectory()) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) { throw new RuntimeException(e); }
        }
    }

    /* zip파일 생성 후 다운로드 method : ZipUtil dependency 사용 */
    public void getZip(String path, String title) {
        String zipFileStr = System.getProperty("user.home") + File.separator + "Downloads"  + File.separator;
        ZipUtil.pack(new File(path), new File(zipFileStr + title + ".zip"));
    }

    /* zip파일 생성을 위한 rootPath 가져오기 */
    public String getRootPaht(String path, String projectId) {
        String[] array = path.toString().split("\\\\");
        String directoryStr = "";
        for(String b : array) {
            if(!b.equals(projectId)) { directoryStr += b + File.separator; }
            else { directoryStr += b + File.separator; break; }
        }
        return  directoryStr;
    }

}
