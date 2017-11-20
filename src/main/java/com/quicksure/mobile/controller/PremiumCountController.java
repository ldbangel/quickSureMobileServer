package com.quicksure.mobile.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.RadarInfor;
import com.quicksure.mobile.entity.Vhlinfor;
import com.quicksure.mobile.service.PremiumCountService;
/**
 * 保费计算的后台入口
 * @author lenny.li001
 *
 */
@Controller
@RequestMapping("/PremiumCount")
public class PremiumCountController {
	
	@Autowired
	private PremiumCountService premiumCountService;
	private static final Logger logger = Logger
			.getLogger(PremiumCountController.class);
	/**
	 * 前端信息提交到此交互
	 * @param insuranceperinfor
	 * @param modelMap
	 * @param httprequest
	 * @throws ParseException 
	 */
	@RequestMapping("/premiumCount.do")
	public @ResponseBody InsuranceDetailsVO premiumCount(@RequestBody List<Coverageinfor> coverageinfors,Vhlinfor vhlinfor,Baseinfor baseinfor,ModelMap modelMap,HttpServletRequest httprequest) throws ParseException{
		logger.info("保费试算开始--： 客户端IP："+httprequest.getRemoteAddr());
		/*
		 * 给予保费计算一些值,以后有前台，从前台获取
		 */
		InsuranceDetailsVO premiumCount=null;
		InsuranceDetailsVO radarPremiumCount=null;
		//RadarinforNeedDateUtil rnd = new RadarinforNeedDateUtil(); //将时间字符串转换成yyyy/MM/dd 'T' HH:mm:ss +HH:mm
		try {
			 //华安报价返回信息
		     premiumCount=premiumCountService.PremiumCount(vhlinfor,baseinfor,coverageinfors, httprequest);
		/*if(premiumCount.getBaseinfor().getQuoteno()!=null){
			 //调用华安后再根据返回的信息调用一次雷达进行算价
			 RadarInfor radarinfor = new RadarInfor();
			 radarPremiumCount=premiumCountService.RadarPremiumCount(premiumCount,radarinfor,httprequest);
			logger.info("试算成功"+radarPremiumCount.getVhlinfor().getDrvowner()); 
		 }else{
			 logger.info("试算失败没有成功获取到报价单号");
		 }*/
		} catch (Exception e) {
			 StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			logger.error("试算失败:"+str);
		}
		return premiumCount;
	}

	/**
	 * 根据订单号更新起保时间等信息信息
	 * @param vhlinfor
	 * @param httprequest
	 * @param response
	 * @return
	 */
//	@RequestMapping("/BaseUpdate.do")
//	public String onSumbitAction(Baseinfor baseinfor,HttpServletRequest httprequest,HttpServletResponse response){
//		logger.info("更新Base表,车主名字为:"+baseinfor.getOwnersname());
//		String returnAction="";
//		String orderno = "LD20161025"; //订单号
//		baseinfor.setOrderno(orderno);
//		returnAction=premiumCountService.saveBaseDate(baseinfor, httprequest);				
//		logger.info("更新成功,车主名字为："+baseinfor.getOwnersname());
//		return returnAction;
//	}
	/**
	 * 跳转到人员信息页面(提核)
	 * 孙小东
	 * @return
	 */
	@RequestMapping("/goToPersonInfor.do")
	public String goToPersonInfor(HttpServletRequest httprequest,ModelMap modelMap){
		InsuranceDetailsVO insuranceDetailsVO=premiumCountService.savePremiumInfor(modelMap,httprequest);	
		modelMap.addAttribute("insuranceDetailsVO",insuranceDetailsVO);
		return LudiConstants.PERSONINFORS;
	}
	
	/**
	 * 返回到人员信息(personinfor)页面
	 * @return
	 */
	@RequestMapping("/backToPersonInfor.do")
	public String backToPersonInfor(ModelMap modelMap, HttpServletRequest request){
		String orderNo = request.getParameter("orderNo");
		InsuranceDetailsVO insuranceDetailsVO = (InsuranceDetailsVO) request.getSession()
				.getAttribute(orderNo + "insuranceDetailsVO");
		modelMap.addAttribute("insuranceDetailsVO", insuranceDetailsVO);
		return LudiConstants.PERSONINFORS;
	}
}
