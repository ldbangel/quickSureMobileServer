package com.quicksure.mobile.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

public interface MyOrderNoService {
	public List<Coverageinfor> getListCoverage(String orderNo);
	
	public List<InsuranceDetailsVO> getOrderinformation(Map<String,Object> map,HttpServletRequest request);
	
	public String cancelOrderNo(Baseinfor baseinfor,HttpServletRequest request);
	
	public InsuranceDetailsVO continuePayment(InsuranceDetailsVO insuranceDetails, HttpServletRequest request,HttpServletResponse response);
	
	public List<Baseinfor> getAllBaseinfor();

}
