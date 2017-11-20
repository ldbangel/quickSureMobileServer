package com.quicksure.csr.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicksure.csr.service.CsrLoginService;
import com.quicksure.mobile.controller.LoginContorller;
import com.quicksure.mobile.dao.OTPGenerationMapper;
import com.quicksure.mobile.dao.UserinforMapper;
import com.quicksure.mobile.entity.OTPGeneration;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.service.OTPValidationService;
import com.quicksure.mobile.utility.DesUtil;

@Service("csrLoginService")
public class CsrLoginServiceImpl implements CsrLoginService {
	/*@Resource
	private  CsrUserinforMapper csrUserinforMapper;*/
	@Resource
	private UserinforMapper userinforMapper;
	@Autowired
	private OTPValidationService ValidationService;
	@Resource 
	private OTPGenerationMapper otpGenerationMapper;
	
	private static final Logger logger = Logger.getLogger(LoginContorller.class);

	public String userExists(Userinfor userinfor, HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		String msg="";
		if(userinfor.getPassword()!=null&&!userinfor.getPassword().equals("")){
			userinfor.setPassword(DesUtil.encrypt(userinfor.getPassword()));
		}
		Userinfor user = userinforMapper.selectByName(userinfor);
		if(user.getUsertype()==1){
			user=null;
		}
		if(user==null){
			msg="2";//"用户存在或密码输入错误";
		}else{
			logger.info("CSR帐号密码登录用户名为：" + user.getUsername());
			msg="1";//用户登录成功
			session.setAttribute("loginUser", user);
		}
		return msg;
	}
	
	public OTPGeneration validateCode(String phoneNo, HttpServletRequest request) {
		OTPGeneration otpGeneration=new OTPGeneration();
		ValidationService.templateSMS(phoneNo, otpGeneration);
		return otpGeneration;
	}
	
	public String phoneCodeExist(String phoneNo,String checkCode, HttpServletRequest request) {
		Boolean exist=false;
		Map params=new HashMap();
		params.put("phoneno", phoneNo);
		params.put("dateTime", new Date());
		//查询该用户所有未过期的并且有效的验证码
		List<OTPGeneration> listOTP=otpGenerationMapper.listOTPGeneration(params);
		for(OTPGeneration otp:listOTP){
			if(checkCode.equals(otp.getValidationno())){
				exist=true;
				otp.setStatus("20");//验证成功,设置验证码状态为已用
				otpGenerationMapper.updateByPrimaryKey(otp);
			}
		}
		return exist.toString();
	}

	@Override
	public Userinfor phoneExists(Userinfor userinfor, HttpServletRequest request) {
		Userinfor user = userinforMapper.selectByName(userinfor);
		return user;
	}
}
