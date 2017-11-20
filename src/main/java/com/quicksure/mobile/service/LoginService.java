package com.quicksure.mobile.service;

import javax.servlet.http.HttpServletRequest;

import com.quicksure.mobile.entity.AgentCode;
import com.quicksure.mobile.entity.OTPGeneration;
import com.quicksure.mobile.entity.Userinfor;


public interface LoginService {
	
	public String userExists(Userinfor userinfor,HttpServletRequest request) throws Exception;
	
	public String resetPassword(String phoneno,String password) throws Exception;
	
	public String userFindPassword(Userinfor userinfor,HttpServletRequest request);
	
	public String registUser(String username,String password, int type,HttpServletRequest request);
	
	public OTPGeneration validateCode(String phoneNo,HttpServletRequest request);
	
	public String phoneCodeExist(String phoneNo,String checkCode,HttpServletRequest request);
	
	public String userEverRegist(String username);
	
	public AgentCode validateAgentCode(String agentCode);
	
	public Userinfor selectUserById(int id);
	
	/*public Userinfor quCodelogin(Userinfor userinfor,HttpServletRequest httprequest);*/
}
