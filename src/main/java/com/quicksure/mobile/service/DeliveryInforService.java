package com.quicksure.mobile.service;

import javax.servlet.http.HttpServletRequest;

import com.quicksure.mobile.entity.Deliveryinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

public interface DeliveryInforService {
	public InsuranceDetailsVO deliveryToCustomer(Deliveryinfor deliveryinfor,HttpServletRequest httprequest);
	
	//查询配送接口报文所需信息
	public InsuranceDetailsVO selectDistributionInfors(HttpServletRequest request);
	
	//配送接口
	public InsuranceDetailsVO deliveryToCustomer(InsuranceDetailsVO ins);
}

