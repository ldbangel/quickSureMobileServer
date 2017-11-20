package com.quicksure.mobile.service;

import javax.servlet.http.HttpServletRequest;

import com.quicksure.mobile.entity.InsuranceDetailsVO;

public interface PaymentInforService {
	public InsuranceDetailsVO paymentApplication(HttpServletRequest httpRequest);
}
