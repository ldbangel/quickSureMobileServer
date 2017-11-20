package com.quicksure.mobile.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.dms.LoginManageService;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.entity.WechatConfig;
import com.quicksure.mobile.service.LoginService;
import com.quicksure.mobile.serviceImpl.WechatLoginServiceImpl;
import com.quicksure.mobile.utility.StringUtils;
@Controller
@RequestMapping("/WechatLogin")
public class WechatLoginController {
	private static final Logger logger = Logger
			.getLogger(WechatLoginController.class);
	@Resource
	private WechatLoginServiceImpl wechatLoginServiceImpl;
	@Resource
	private LoginService loginService;
	@Resource
	private LoginManageService loginManageService;
	
	@RequestMapping(value="/goToFirstScreen")
	public String goToFirstScreen(HttpServletRequest httprequest,HttpServletResponse response,ModelMap modelMap){
		int userType = 3;
		Userinfor userinfor = new Userinfor();
		String openId = "";
		String action = httprequest.getParameter("action");
		try {
			openId = wechatLoginServiceImpl.getOpenId(httprequest, response);
			if (StringUtils.checkStringEmpty(openId)) {
				userinfor.setUsername(openId);
				userinfor.setUsertype(userType);
				loginService.registUser(openId, "", userType, httprequest);
				loginService.userExists(userinfor, httprequest);
			}
		} catch (Exception e) {
			 StringWriter sw = new StringWriter();  
			 e.printStackTrace(new PrintWriter(sw, true));  
			 String str = sw.toString();
			 logger.error("微信端跳转首页异常"+str);
		}
		modelMap.addAttribute("openId", openId);
		if(StringUtils.checkStringEmpty(action)&&"bindUser".equalsIgnoreCase(action)){
			return	LudiConstants.BINDUSER;
		}else if(StringUtils.checkStringEmpty(action)&&"jyx".equalsIgnoreCase(action)){
			return LudiConstants.JIAYIXIANHOME;
		}else if(StringUtils.checkStringEmpty(action)&&"jab".equalsIgnoreCase(action)){
			return LudiConstants.JUNANBAOHOME;
		}else if(StringUtils.checkStringEmpty(action)&&"ylpa".equalsIgnoreCase(action)){
			return LudiConstants.YILUPINGANHOME;
		}else{
			return LudiConstants.INDEX;
		}
	}
	
	@RequestMapping("/getUserinfo.do")
	@ResponseBody
	public Map<String,Object> selectUserinfo(HttpServletRequest httprequest,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		String openId = "oOLN5w3Ouu5f6g9tAjR0XXX_OR4Q";
		String token = "";
//		openId = wechatLoginServiceImpl.getOpenId(httprequest, response);
		token = wechatLoginServiceImpl.getaccessToken();
		if(StringUtils.checkStringEmpty(openId)&&StringUtils.checkStringEmpty(token)){
			map = wechatLoginServiceImpl.getUserinfo(openId, token);
		}
		return map;
	}
	
	@RequestMapping("/goToMyAccount.do")
	public String goToMyAccount(HttpServletRequest httprequest,HttpServletResponse response,ModelMap modelMap){
		int userType = 3;
		Userinfor userinfor = new Userinfor();
		String openId = "";
		try {
	    openId = wechatLoginServiceImpl.getOpenId(httprequest, response);
			if (StringUtils.checkStringEmpty(openId)) {
				userinfor.setUsername(openId);
				userinfor.setUsertype(userType);
				loginService.registUser(openId, "", userType, httprequest);
				loginService.userExists(userinfor, httprequest);
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.error("微信端跳转我的订单异常"+str);
		}
		return LudiConstants.MYACCOUNT;
	}
	
	@RequestMapping("/bindUser.do")
	public  void bindUserinfor(HttpServletRequest httprequest,HttpServletResponse response,ModelMap modelMap){		
		String result = wechatLoginServiceImpl.bindUser(httprequest,response);
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(result);
		} catch (IOException e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.error("微信端绑定用户信息异常"+str);
		}
	}
	
	@RequestMapping("/getWechatJSAccess.do")
	public @ResponseBody WechatConfig getWechatJSAccess(@RequestBody String url,
			HttpServletRequest httprequest,HttpServletResponse response){
		logger.info("get URL is:"+url);
		HttpSession session = httprequest.getSession();
		WechatConfig wechatConfig = null;
		Userinfor userinfor = new Userinfor();
		Userinfor user = new Userinfor();
		String ticket="";
		String token="";
		if(session!=null&&session.getAttribute("token")!=null){
			token=(String) session.getAttribute("token");
			session.setAttribute("token", token);
		}else{
			token=wechatLoginServiceImpl.getaccessToken();
		}
		if(session!=null&&session.getAttribute("ticket")!=null){
			ticket=(String) session.getAttribute("ticket");
		}else{
			if(StringUtils.checkStringEmpty(token)){
				ticket=wechatLoginServiceImpl.getjsapi_ticket(token);
				session.setAttribute("ticket", ticket);		
			}
		}
		if(StringUtils.checkStringEmpty(ticket)){
			 wechatConfig = wechatLoginServiceImpl.getSingnature(ticket,url);
			 if(session!=null && session.getAttribute("loginUser")!=null){
				 userinfor = (Userinfor) session.getAttribute("loginUser");
			 }
			 try {
				 user = loginManageService.selectByName(userinfor);
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
			 if(user!=null && user.getUserid()!=null){
//					wechatConfig.setSharedLink("http://m.quicksure.com/ludiquickSureMobileServer/?userId="+user.getUserid());
				 if(url.indexOf("jyx")>=0 || url.indexOf("diverInsure/")>=0){ //非车“驾意险”链接分享
					 wechatConfig.setSharedLink("http://m.quicksure.com/ludiquickSureMobileServer/views/quicksureFeiche/jiayixian/jsp/jiayixianHome.jsp?userId="+user.getUserid());
				 }else if(url.indexOf("jab")>=0 || url.indexOf("PersonalAccidentInsurance/")>=0){ //非车“君安保”链接分享
					 wechatConfig.setSharedLink("http://m.quicksure.com/ludiquickSureMobileServer/views/quicksureFeiche/junanbao/jsp/junanbaoHome.jsp?userId="+user.getUserid());
				 }else if(url.indexOf("ylpa")>=0 || url.indexOf("VehicleAccidentInsurance/")>=0){ //非车“一路平安”链接分享
					 wechatConfig.setSharedLink("http://m.quicksure.com/ludiquickSureMobileServer/views/quicksureFeiche/yilupingan/jsp/yiluHomePage.jsp?userId="+user.getUserid());
				 }else{ //车险链接分享
					 wechatConfig.setSharedLink("http://m.quicksure.com/ludiquickSureMobileServer/views/quicksure/jsp/quicksurehome.jsp?userId="+user.getUserid());
				 }
			}
		}
		return wechatConfig;
	
	}
	
	@RequestMapping("/getVehicleinforByOCR.do")
	public void  saveImageToDisk(@RequestBody String mediaId,HttpServletRequest httprequest,HttpServletResponse response,ModelMap modelMap){
		HttpSession session=httprequest.getSession();
		JSONObject jsonObject=null;
		String token="";
		if(session!=null&&session.getAttribute("token")!=null){
			token=(String) session.getAttribute("token");
			session.setAttribute("token", token);
		}else{
			token=wechatLoginServiceImpl.getaccessToken();
		}
		try {
			InputStream is=wechatLoginServiceImpl.getInputStream(token,mediaId);
			String result= wechatLoginServiceImpl.getVehicleinforByOCR(is);	
			jsonObject = JSONObject.fromObject(result);
			response.setContentType("text/html;chartset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(jsonObject);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.error("微信端下载图片异常"+str);
		}
	}

}
