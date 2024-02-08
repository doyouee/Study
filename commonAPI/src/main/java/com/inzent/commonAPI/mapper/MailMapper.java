package com.inzent.commonAPI.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.inzent.commonAPI.vo.MailHistoryVO;
import com.inzent.commonAPI.vo.MailInfoVO;


@Mapper
public interface MailMapper {

	MailInfoVO getMailTemplate(Map<String, Object> apiKeyMap);
	
	void setMailHistory(MailHistoryVO mailHistoryVo);
	
}
