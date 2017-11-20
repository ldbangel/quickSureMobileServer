package com.quicksure.csr.service;

import javax.servlet.http.HttpServletRequest;

import com.quicksure.mobile.entity.OTPGeneration;
import com.quicksure.mobile.entity.Userinfor;


public interface CsrLoginService {
	
	public String userExists(Userinfor userinfor,HttpServletRequest request) throws Exception;
	
	public OTPGeneration validateCode(String phoneNo,HttpServletRequest request);
	
	public String phoneCodeExist(String phoneNo,String checkCode,HttpServletRequest request);
	
	public Userinfor phoneExists(Userinfor userinfor,HttpServletRequest request);
}
