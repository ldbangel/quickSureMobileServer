package com.quicksure.csr.service;

import javax.servlet.http.HttpServletRequest;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Vhlinfor;

public interface CsrPremiumService {

	 public InsuranceDetailsVO submitVehicleinfor(Vhlinfor vhlinfor,HttpServletRequest httprequest);
	 
	 public InsuranceDetailsVO paymentApplication(Baseinfor baseinfor,HttpServletRequest httpRequest);
}
