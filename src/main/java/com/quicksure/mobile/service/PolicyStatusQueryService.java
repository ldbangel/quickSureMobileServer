package com.quicksure.mobile.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

public interface PolicyStatusQueryService {
	public InsuranceDetailsVO policyStatusQuery(Baseinfor baseinfor, HttpServletRequest request);
	
	public List<Baseinfor> multiplePolicyStatusQuery() throws ParseException;
}
