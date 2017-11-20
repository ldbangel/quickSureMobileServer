package com.quicksure.mobile.serviceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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

import com.quicksure.mobile.controller.LoginContorller;
import com.quicksure.mobile.dao.OTPGenerationMapper;
import com.quicksure.mobile.dao.UserinforMapper;
import com.quicksure.mobile.dms.LoginManageService;
import com.quicksure.mobile.entity.AgentCode;
import com.quicksure.mobile.entity.OTPGeneration;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.service.LoginService;
import com.quicksure.mobile.service.OTPValidationService;
import com.quicksure.mobile.utility.DesUtil;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource
	private  UserinforMapper userinforMapper;
	@Autowired
	private OTPValidationService ValidationService;
	@Resource
	private LoginManageService loginManageService;
	@Resource 
	private OTPGenerationMapper otpGenerationMapper;
	
	private static final Logger logger = Logger.getLogger(LoginContorller.class);

	public String userExists(Userinfor userinfor, HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		String msg="";
		Integer type=userinfor.getUsertype();//1：动态密码登录；2：会员密码登录
//		userinfor.setUsertype(null);//去掉用户类型
		if(type!=3){
			userinfor.setUsertype(null);//用户登录统一设置type为1
		}
		if(userinfor.getPassword()!=null&&!userinfor.getPassword().equals("")){
			userinfor.setPassword(DesUtil.encrypt(userinfor.getPassword()));
		}
		/*if(userinfor.getPassword()==null||userinfor.getPassword().equals("")){
		userinfor.setPassword("");
	    }*/
		Userinfor user = userinforMapper.selectByName(userinfor);
		AgentCode agent = new AgentCode();
		if(type==1){//动态密码登录先验证用户是否存在，不存在的话添加用户 
			if(user==null){
				logger.info("首次验证码登录,用户为空  添加用户");
				userinfor.setPassword(DesUtil.encrypt("ludi123"));//首次登录默认密码
				userinfor.setPhoneno(userinfor.getUsername());
				userinfor.setUsertype(1);
				msg=loginManageService.registUser(userinfor,agent);//添加用户
			}else{
				logger.info("用户登录查询结果用户名为:" + user.getUsername());
				userinfor=user;
			}
			session.setAttribute("loginUser", userinfor);
		}
		if(type==2||type==3){
			if(user==null){
				msg="3";//"用户名不存在或密码输入错误";
			}else{
				logger.info("帐号密码登录或者微信登录查询用户名为：" + user.getUsername()+"------usertype为："+user.getUsertype());
				session.setAttribute("loginUser", user);
			}
		}		
		return msg;
	}

	public String resetPassword(String phoneno,String password) throws Exception {
		String msg="";
		Userinfor user=new Userinfor();
		user.setUsername(phoneno);
		Userinfor uu=loginManageService.selectByName(user);
		if(uu==null){
			msg="2";//手机号不存在
		}else{
			Userinfor userinfor=new Userinfor();
			userinfor.setPassword(DesUtil.encrypt(password));
			userinfor.setUsername(phoneno);
			msg=loginManageService.resetPassword(userinfor);
		}
		
		return msg;
	}

	public String userFindPassword(Userinfor userinfor,HttpServletRequest request) {
		String msg=loginManageService.userFindPassword(userinfor, request);
		return msg;
	}
	
	public String registUser(String username,String password,int type ,HttpServletRequest request) {
		String agentCode = request.getParameter("agentCode")==null?"0":request.getParameter("agentCode");
		int isAgent = Integer.parseInt(agentCode);
		//用户名和密码唯一,注册须判断
		String msg = "";
			try {
//				userinfor.setUsertype(2);// 用户类型(会员)
				// userinfor.setPassword(Md5Utils.string2MD5(userinfor.getPassword()));
				Userinfor user=new Userinfor();
				user.setUsername(username);
				Userinfor uu=loginManageService.selectByName(user);
				if(uu!=null){
					msg="2";//手机号已被使用（手机已注册）
				}else{
					Userinfor userinfor=new Userinfor();
					AgentCode agent = new AgentCode();
					userinfor.setUsername(username);
					userinfor.setAgentFlag(isAgent);
					//前台页面已经做了判断，如果勾选了代理人则必须要输入正确的识别码才能提交表单，所以这里直接判断agentCode如果为空则是没勾选代理人
//					if("1".equals(agentCode)){
//						userinfor.setAgentFlag(1);
////						agent.setAgentcode(agentCode);
//					}
//					if("0".equals(agentCode)){
//						userinfor.setAgentFlag(1);
////						agent.setAgentcode(agentCode);
//					}
					if(type!=3){
						userinfor.setPassword(DesUtil.encrypt(password));
						userinfor.setPhoneno(username);//手机就是用户名						
					}
					userinfor.setUsertype(type);
					msg=loginManageService.registUser(userinfor,agent);
				}
				
				
			} catch (Exception e) {
				StringWriter sw = new StringWriter();  
				 e.printStackTrace(new PrintWriter(sw, true));  
				 String str = sw.toString();
				logger.info("用户注册异常: "+str);
			}
		return msg;
	}
	
	public String userEverRegist(String username) {
		String msg="";
		try {
			Userinfor user=new Userinfor();
			user.setUsername(username);
			Userinfor uu=loginManageService.selectByName(user);
			if(uu!=null){
				msg="1";
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			 e.printStackTrace(new PrintWriter(sw, true));  
			 String str = sw.toString();
			logger.info("用户查询异常: "+str);
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
	
	/**
	 * 代理人识别码校验
	 */
	public AgentCode validateAgentCode(String agentCode){
		return loginManageService.selectAgent(agentCode);
	}

	/**
	 * 二维码扫描,通过ID获取User
	 */
	@Override
	public Userinfor selectUserById(int userid) {
		Userinfor userinfor=null;
		 userinfor=userinforMapper.selectByPrimaryKey(userid);
		 String password="";
		/* String username="";
		  username=userinfor.getUsername();*/
		  password=userinfor.getPassword();
		 try {
			password=DesUtil.decrypt(password);
			userinfor.setPassword(password);
		 } catch (Exception e) {
			e.printStackTrace();
		 }		
		return userinfor;
	}	
}
