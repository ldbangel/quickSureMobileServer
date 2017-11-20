package com.quicksure.mobile.service;

import java.util.List;
import java.util.Map;

import com.quicksure.mobile.entity.InsuranceDetailsVO;


public interface VilicheBeatchCheckService {
	/*
	 * 车辆批量查询的服务接口类
	 */
	
	List<InsuranceDetailsVO> vilicheBeatch(Map<String, Object> map);
	
	int searchPolicyCount(Map<String, Object> map);
	
	List<InsuranceDetailsVO> interfaceslogsQuery(List<String> list);
	
	InsuranceDetailsVO getResponsexml(InsuranceDetailsVO ins);//获取响应报文
	
}
