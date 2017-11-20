package com.quicksure.feiche.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quicksure.feiche.entity.LudifcBaseinfor;
import com.quicksure.feiche.service.NoncarSubmitWriteService;
import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

/**
 * 交通工具意外险
 * @author lenny.li001
 *
 */
@Controller
@RequestMapping("/VehicleAccidentInsurance")
public class VehicleAccidentInsuranceContorller {
	private static final Logger logger = 
			Logger.getLogger(VehicleAccidentInsuranceContorller.class);
	@Resource
	private NoncarSubmitWriteService noncarSucmitWriteService;
	
	/**
	 * 一路平安--首页跳转次页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("immeInsuranceSubmit.do")
	public String immediateInsurance(HttpServletRequest request,
			LudifcBaseinfor baseinfor, ModelMap model){
		logger.info("一路平安首页信息进入到后台！");
		InsuranceDetailsVO insuranceDetailsVO = noncarSucmitWriteService
				.getVehicleAccHomeInfo(request,baseinfor);
		model.addAttribute("insuranceDetailsVO", insuranceDetailsVO);
		return LudiConstants.YILUPINGAN;
	}
	
	/**
	 * 一路平安--提核跳转支付页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("submitUnderwriting.do")
	public String submitUnderwriting(HttpServletRequest request,ModelMap model,
			LudifcBaseinfor baseinfor,HttpServletResponse response){
		logger.info("一路平安--提交核保！");
		InsuranceDetailsVO insuranceDetailsVO = noncarSucmitWriteService
				.VehicleAccSubmitUnder(request,baseinfor);
		model.addAttribute("insuranceDetailsVO", insuranceDetailsVO);
		String url=insuranceDetailsVO.getLudifcBaseinfor().getPayUrl();
		if(url==null||url==""){
			return LudiConstants.YILUPINGAN;
		}
		try {
			response.setCharacterEncoding("utf-8");	
			response.setContentType( "application/json");
			response.sendRedirect(url);
			response.getWriter().flush();
		} catch (IOException e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.error("paymentApplication exception the  error message is: "+str);
		}
		return null;
	}
}
