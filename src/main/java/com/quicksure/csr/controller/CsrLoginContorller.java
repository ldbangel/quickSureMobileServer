package com.quicksure.csr.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.csr.service.CsrLoginService;
import com.quicksure.mobile.controller.LoginContorller;
import com.quicksure.mobile.entity.OTPGeneration;
import com.quicksure.mobile.entity.Userinfor;

@Controller
@RequestMapping("/csrLoginUser")
public class CsrLoginContorller {
	private static final Logger logger = Logger.getLogger(LoginContorller.class);
	@Autowired
	private CsrLoginService csrLoginService;
	
	@RequestMapping("/userLogin.do")
	@ResponseBody
	public String userLogin(String username,String password,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String result="";
		try {
			result = "";
			Userinfor userinfo = new Userinfor();
			userinfo.setUsername(username.toLowerCase());
			if (password != null && !password.equals("")) {
				userinfo.setPassword(password);
			}
			//userinfo.setUsertype(2);//这里用户类型默认2(用户执行sql添加)
			result = csrLoginService.userExists(userinfo, request);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.error("CSR登录异常 ："+str);
		}
		return result;
	}
	
	/**
	 * 发送验证码到用户手机
	 * 
	 * @param phoneno
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sendPhoneCode.do")
	@ResponseBody
	public OTPGeneration phoneCheck(String phoneno,HttpServletRequest request,HttpServletResponse response) throws Exception{
		OTPGeneration Generation=new OTPGeneration();
		try {
			Generation=csrLoginService.validateCode(phoneno,request);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.error("CSR登录发送手机验证码异常 ："+str);
		}
		return Generation;
	}
	
	/**
	 * 校验手机验证码
	 * 
	 * @param request
	 * @param phoneno
	 * @param checkCode
	 * @return
	 */
	@RequestMapping("checkPhoneCode.do")
	@ResponseBody
	public String checkPhoneCode(HttpServletRequest request,String phoneno,String checkCode){
		String codeExist="";
		try {
			codeExist=csrLoginService.phoneCodeExist(phoneno,checkCode,request);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.error("CSR登录校验手机验证码异常 ："+str);
		}
		return codeExist;
	}
	
	@RequestMapping("/checkPhoneExist.do")
	@ResponseBody
	public Userinfor checkPhoneExist(HttpServletRequest request,@RequestBody Userinfor userinfor){
		try {
			userinfor=csrLoginService.phoneExists(userinfor,request);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.error("CSR保费计算验证手机绑定用户ID异常 ："+str);
		}
		return userinfor;
	}
}
