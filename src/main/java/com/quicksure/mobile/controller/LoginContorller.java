package com.quicksure.mobile.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.entity.AgentCode;
import com.quicksure.mobile.entity.OTPGeneration;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.service.LoginService;
import com.quicksure.mobile.serviceImpl.WechatLoginServiceImpl;
import com.quicksure.mobile.utility.CreateQRCodeUtils;
import com.quicksure.mobile.utility.RandomValidateCode;
import com.quicksure.mobile.utility.StringUtils;

@Controller
@RequestMapping("/loginUser")
public class LoginContorller {
	private static final Logger logger = Logger
			.getLogger(LoginContorller.class);
	@Autowired
	private LoginService loginService;
	@Resource
	private WechatLoginServiceImpl wechatLoginServiceImpl;
	
	/**
	 * 用户登录校验,两种登录方式 方式一通过手机验证码登录;方式二用户名密码登录(通过usertype区分登录方式)
	 * 
	 * @param username
	 * @param password
	 * @param usertype
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userLogin.do")
	@ResponseBody
	public String userLogin(String username,String password,String usertype, HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("开始进行登录,用户名为 : " + username);
		Userinfor loUserinfor = null;
		String result="";
		String codeId=request.getParameter("codeId");
		String userIdQr=request.getParameter("userIdQr");
		//非车链接分享获取参数
		String shareId=request.getParameter("shareId");
		String feicShareID=request.getParameter("feicShareID");
		try {
			if("1".equals(feicShareID)){//非车链接分享获取uesId后登录
				Userinfor userinfor=loginService.selectUserById(Integer.parseInt(shareId));
				result=loginService.userExists(userinfor,request);	
				logger.info("loginUser2 = " + userinfor.getUserid());
			}else if("1".equals(codeId)){//二维码扫描到首页然后登录
				Userinfor userinfor=loginService.selectUserById(Integer.parseInt(userIdQr));
				result=loginService.userExists(userinfor,request);
			}else{
				Userinfor userinfo=new Userinfor();
				userinfo.setUsername(username.toLowerCase());
				if(password!=null&&!password.equals("")){
					userinfo.setPassword(password);
				}
				userinfo.setUsertype(Integer.parseInt(usertype));
				result=loginService.userExists(userinfo,request);
			}
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.error("用户登录服务，返回消息IO异常，异常信息为："+str);
		}
		return result;
	} 
	
	//用户微信登录（userId获取openId登录）
	@RequestMapping("/weixiLoginByOpenId.do")	
	@ResponseBody
	public String goToWeixiLogin(HttpServletRequest httprequest){
		int userType = 3;
	    String shareId=httprequest.getParameter("shareId");
		String feicShareID=httprequest.getParameter("feicShareID");
		Userinfor userinfor = new Userinfor();
		String result="";
		String openId = "";
		try {
			if("1".equals(feicShareID)){   //非车链接分享获取uesId后登录
			   userinfor=loginService.selectUserById(Integer.parseInt(shareId));
			   openId = userinfor.getUsername(); //获取用户openId
			   logger.info("用户openId : " + openId);
			}
			if (StringUtils.checkStringEmpty(openId)) {
				userinfor.setUsername(openId);
				userinfor.setUsertype(userType);
				loginService.registUser(openId, "", userType, httprequest);
				result = loginService.userExists(userinfor, httprequest);
			}
		} catch (Exception e) {
			 StringWriter sw = new StringWriter();  
			 e.printStackTrace(new PrintWriter(sw, true));  
			 String str = sw.toString();
			 logger.error("微信端跳转首页异常"+str);
		}
	    return result;
	}
	
	
	/**
	 * 用户密码重置 
	 * @param phoneno
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/resetPassword.do")
	@ResponseBody
	public String resetPassword(String phoneno,String password, HttpServletRequest request,HttpServletResponse response) throws Exception{
		//根据用户名修改(在登录成功的情况下重置密码,session保存当前登录用户信息,前端页面进行输入框提示,这里只需进行密码修改即可    这里是否需要发送短信通知用户?)
		logger.info("开始进行密码重置操作,手机号为 : " + phoneno);
		String result="";
		try {
			result = loginService.resetPassword(phoneno, password);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.info("密码重置异常"+str);
		}
		return result;
	}
	
	/**
	 * 用户注册
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/registUser.do")
	@ResponseBody
	public String registUser(String username,String password, HttpServletRequest request,HttpServletResponse response){
		logger.info("开始进行用户注册操作,手机号为 : " + username);
		String result="";
		try {
			result = loginService.registUser(username, password, 1, request);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.info("用户注册异常"+str);
		}
		return result;
	}
	
	/**
	 * 注册判断用户是否注册过
	 * @param username
	 * @return
	 */
	@RequestMapping("/userEverRegist.do")
	@ResponseBody
	public String userEverRegist(String username,HttpServletRequest request,HttpServletResponse response){
		//1表示用户已存在
		String result=loginService.userEverRegist(username);
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
	@RequestMapping("phoneCheck.do")
	@ResponseBody
	public OTPGeneration phoneCheck(String phoneno,HttpServletRequest request,HttpServletResponse response) throws Exception{
		OTPGeneration Generation=new OTPGeneration();
		try {
			Generation=loginService.validateCode(phoneno,request);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.info("用户注册发送验证码异常"+str);
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
			codeExist=loginService.phoneCodeExist(phoneno,checkCode,request);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.info("用户校验手机验证码异常"+str);
		}
		return codeExist;
	}
	
	
	/**
     * 登录页面生成验证码
     */
    @RequestMapping("getVerify.do")
    public void getVerify(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片  
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容  
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expire", 0); 
        RandomValidateCode randomValidateCode = new RandomValidateCode(); 
        try { 
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法  
        } catch (Exception e) { 
        	StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			  logger.error("登录页面生成验证码"+str);
        } 
    } 

    /**
     * 登录页面校验验证码
     */
    @RequestMapping("checkVerify.do")
    @ResponseBody
    public String checkVerify(String inputStr, HttpSession session){
        //从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        if(random.equals(inputStr)){
            return "TURE";//验证码正确
        }else{
            return "FALSE";//验证码错误
        }
    } 
    
    /**
     * 注销账户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("logout.do")
    public String logout(HttpServletRequest request,HttpServletResponse response){
    	HttpSession session = request.getSession();
    	session.removeAttribute("loginUser");
    	response.setHeader("Cache-Control","no-cache");
    	response.setHeader("Pragma","no-cache");
    	response.setDateHeader ("Expires", 0);
    	return LudiConstants.INDEX;
    }
    
    /**
     * 校验代理人识别码
     * @return
     */
    @RequestMapping("checkAgentCode.do")
    @ResponseBody
    public String checkAgentCode(HttpServletRequest request, String agentCode){
    	String agentCode1 = request.getParameter("agentCode");
    	String agentCode2 = (String) request.getAttribute("agentCode");
    	AgentCode agent = loginService.validateAgentCode(agentCode);
    	if(agent!=null){
    		return "true";
    	}
    	return "false";
    }
    
    /**
     * 生成二维码   
     * @throws Exception 
     */
    @RequestMapping("parseQRCode.do")
   /* @ResponseBody*/
    public String parseQRCode(HttpServletRequest httprequest,ModelMap modelMap) throws Exception{
    	 String result="";
    	 CreateQRCodeUtils codeUtil = new CreateQRCodeUtils();//二维码生成工具类
    	 String userIdQr=httprequest.getParameter("userId");
    	 String strUrl =httprequest.getParameter("strUrl");
    	 String isagentshare =httprequest.getParameter("isagentshare");
    	 //获取web项目全路径
    	 String basePath = httprequest.getScheme()+"://"
    			 +httprequest.getServerName()+":"+httprequest.getServerPort()+httprequest.getContextPath()  +"/"; 
    	 String ccbPath = basePath+"views/quicksure/images/logo5.png";   //获取图片路径(URL)
    	 logger.info("userid = "+userIdQr);
    	  /** 再此调用扫描二维码的方法获取Id   */ 	 
    	/* Userinfor userinfor=loginService.selectUserById(Integer.parseInt(userId));*/
    	
    	 String codeId="1";//二维码登录ID,用于判断是否是生成二维码时候再次登录   
    	 String data="";
    	 //生成二维码的链接（车险首页）
    	 data=strUrl+"?userIdQr="+userIdQr+"&codeId="+codeId;
    	 //读取文件转换为字节数组
    	 ByteArrayOutputStream output = new ByteArrayOutputStream(); 
    	 //生成二维码（data:二维码内容，output:字节输出流，png:图片类型 ，8:图片尺寸大小, ccbPath:logo图片所在路径URL）
    	 result = codeUtil.encoderQRCode(data, output, "png", 10, ccbPath);  
    	 modelMap.addAttribute("result", result);
    	 modelMap.addAttribute("strUrl", strUrl);
    	 modelMap.addAttribute("isagentshare", isagentshare);
    	 return LudiConstants.QRCODE;
    }
  
}
