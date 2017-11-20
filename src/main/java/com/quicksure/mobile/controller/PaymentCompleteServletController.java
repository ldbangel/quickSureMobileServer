package com.quicksure.mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.DeliveryInforService;
import com.quicksure.mobile.service.PaymentCompleteService;

/**
 * 支付完成服务
 * @author dongbo
 *
 */
@Controller
@RequestMapping("/paymentCompleteServlet")
public class PaymentCompleteServletController {
	private static final Logger logger = Logger.getLogger(PaymentCompleteServletController.class);
	@Autowired
	private PaymentCompleteService paymentCompleteService;
	
	@Autowired
	private DeliveryInforService deliveryService;
	/**
	 * 支付完成之后，然后将支付结果存库
	 * @param request
	 * @param response
	 */
	@RequestMapping("/paymentComplete.do")
	public void PaymentComplete(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("payment completed and udpate paymentinfor start!");
		String result = request.getParameter("result"); // 0是成功，1是失败
		boolean isSuccess = false;
		if ("0".equals(result)) {
			isSuccess = paymentCompleteService.updatePaymentInfor(request,
					response);
		}
		logger.info("payment completed and udpate paymentinfor end!");
		try {
			if (isSuccess == true) {
				response.getWriter().print("success");
			} else {
				response.getWriter().print("fail");// return "fail";
			}
		} catch (IOException e) {
			 StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			  logger.error("接收生成保单服务，返回消息IO异常，异常信息为："+str);
		}
	}
	
	@RequestMapping(value="/goPaymentSucessPage.do", method=RequestMethod.GET)
	public String goPaymentSucessPage(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		InsuranceDetailsVO insuranceDetailsVO=null;	
		String orderNo=request.getParameter("LudiOrderNo");
		logger.info("开始获取支付返回的信息，订单号为： "+orderNo);		
		insuranceDetailsVO=paymentCompleteService.processPaymentSuccessData(request, response);
		if(insuranceDetailsVO.getBaseinfor()!=null){
			if("".equals(insuranceDetailsVO.getBaseinfor().getSyapplicationno())){
				insuranceDetailsVO.getBaseinfor().setSyapplicationno(null);
			}
			if("".equals(insuranceDetailsVO.getBaseinfor().getSypolicyno())){
				insuranceDetailsVO.getBaseinfor().setSypolicyno(null);
			}
			if("".equals(insuranceDetailsVO.getBaseinfor().getJqapplicationno())){
				insuranceDetailsVO.getBaseinfor().setJqapplicationno(null);
			}else if("0".equals(insuranceDetailsVO.getBaseinfor().getJqpremium())){
				insuranceDetailsVO.getBaseinfor().setJqpremium(null);
			}else if("0".equals(insuranceDetailsVO.getBaseinfor().getTaxpremium())){
				insuranceDetailsVO.getBaseinfor().setTaxpremium(null);
			}
			if("".equals(insuranceDetailsVO.getBaseinfor().getJqpolicyno())){
				insuranceDetailsVO.getBaseinfor().setJqpolicyno(null);
			}
		}
		//调用配送,获取配送接口所需要的字段
		InsuranceDetailsVO ins=deliveryService.selectDistributionInfors(request);
		String str=ins.getDeliveryinfor().getDeliveryaddress();		
		String[]  strs=str.split(",");
		ins.getDeliveryinfor().setDeliveryCity(strs[1]);
		ins.getDeliveryinfor().setDeliveryCounty(strs[2]);
		ins.getDeliveryinfor().setDeliveryProvince(strs[0]);
	    if(ins!=null){
	    	deliveryService.deliveryToCustomer(ins);//调用配送接口,并修改订单状态	
	    }
		modelMap.addAttribute("insuranceDetailsVO", insuranceDetailsVO);
		logger.info("结束获取支付返回的信息，订单号为： "+orderNo);
		return LudiConstants.INSURESUCCESS;
		
	}
	
}
