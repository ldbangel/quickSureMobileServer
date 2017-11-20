package com.quicksure.mobile.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.quicksure.mobile.entity.InsuranceDetailsVO;

public interface MyAccountService {
	 public Map<String,Object> getMyOrdersInfor(HttpServletRequest request);
	 
	 //取消订单
	 public String cancelOrder(String orderNo,HttpServletRequest request);
	 
	 //继续支付
	 public String continuePay(String orderNo,HttpServletRequest request);
	 
	 //继续投保
	 public String continueInsure(String orderNo,HttpServletRequest request);
	 
	 //订单详情
	 public InsuranceDetailsVO showOrderDetail(String orderNo,HttpServletRequest request);
	 
	 //myAccount初始化
	 public Map<String, Object> getMyAccountInitInfor(HttpServletRequest request);
	 
	 //feichemyAccount初始化
	 public Map<String, Object> getfeicheMyAccountInitInfor(HttpServletRequest request);
}
