package com.quicksure.feiche.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.quicksure.feiche.entity.LudifcBaseinfor;

/**
 * 非车myaccount service接口
 * @author Dongbo
 *
 */
public interface FeicheMyAccountService {
	public Map<String,Object> getMyOrdersInfor(HttpServletRequest request);
	
	//myaccount初始化
	public Map<String,Object> getMyAccountInitInfor(HttpServletRequest request);
	 
	//取消订单
	public String cancelOrder(String orderNo,HttpServletRequest request);
	 
	//继续支付
	public String continuePay(String orderNo,HttpServletRequest request);
	 
	//继续投保
	public String continueInsure(String orderNo,HttpServletRequest request);
	 
	//订单详情
	public LudifcBaseinfor showOrderDetail(String orderNo,HttpServletRequest request);
	
}
