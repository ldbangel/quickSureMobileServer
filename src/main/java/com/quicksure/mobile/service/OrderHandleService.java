package com.quicksure.mobile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

public interface OrderHandleService {
	public String cancelOrder(String orderNo,HttpServletRequest request);
	
	public String cancelInsurancePolicy( Baseinfor baseinfor,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//CSR订单详情
	public InsuranceDetailsVO getOrderDetailInfor(String orderNo,HttpServletRequest request);
	
	//CSR继续投保
	public String continueInsure(String orderNo,HttpServletRequest request);
	
	//CSR导出excel表格
	public String exportExcel(HttpServletRequest request,HttpServletResponse response);
}

