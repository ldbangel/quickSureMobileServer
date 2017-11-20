package com.quicksure.mobile.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quicksure.mobile.entity.InsuranceDetailsVO;

public interface PaymentCompleteService {
	public boolean updatePaymentInfor(HttpServletRequest request,HttpServletResponse response);
	public InsuranceDetailsVO processPaymentSuccessData(
			HttpServletRequest request, HttpServletResponse response);
}
