package com.quicksure.feiche.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.feiche.entity.LudifcBaseinfor;
import com.quicksure.feiche.service.NoncarSubmitWriteService;
import com.quicksure.feiche.serviceImpl.NoncarSubmitWriteServiceImpl;
import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

/**
 * 君安保
 * @author lenny.li001
 *
 */
@Controller
@RequestMapping("/PersonalAccidentInsurance")
public class PersonalAccidentInsuranceContorller {
	private static final Logger logger = Logger
			.getLogger(PersonalAccidentInsuranceContorller.class);
	@Autowired
	private NoncarSubmitWriteService SubmitWriteService;
	/**
	 * 跳转到产品页面
	 */
	@RequestMapping("/getSetMealSumbit.do")
	public String getSetMeal(ModelMap modelMap,LudifcBaseinfor ludifcBaseinfor,
			HttpServletRequest httprequest,HttpServletResponse response){
		InsuranceDetailsVO insuranceDetailsVO = SubmitWriteService.getPersonalHomeinfor(httprequest);
		String orderno = insuranceDetailsVO.getLudifcBaseinfor().getOrderno();
		httprequest.getSession().setAttribute(orderno+"insuranceDetailsVO", insuranceDetailsVO);
		modelMap.addAttribute("insuranceDetailsVO",insuranceDetailsVO);
		return LudiConstants.INFORMATION;
	}
	
	@RequestMapping("/submitinfor.do")
	public @ResponseBody InsuranceDetailsVO submitinfor(@RequestBody LudifcBaseinfor ludifcBaseinfor,ModelMap modelMap,HttpServletRequest httprequest){
		InsuranceDetailsVO submit = SubmitWriteService.submitinfor(httprequest, ludifcBaseinfor);
		return submit;
	}
	
	//接收保单信息
	@RequestMapping("/PolicyCallback.do")
	public void getpolicyinfor(HttpServletRequest httprequest,HttpServletResponse response) throws IOException{
		logger.info("接收保单信息");
		try {
			InsuranceDetailsVO insuranceDetailsVO = SubmitWriteService.getpolicyinfor(httprequest);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			logger.error("接收失败:"+str);
		}
	}
	
	//支付成功之后跳转接口
	@RequestMapping("/PaymentCallback.do")
	public String paymentCallback(HttpServletRequest request,ModelMap model){
		logger.info("回调支付成功页面");
		return LudiConstants.INSURANCESUCCESS;
	}
	
}
