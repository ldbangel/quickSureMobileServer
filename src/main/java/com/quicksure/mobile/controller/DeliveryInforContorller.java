package com.quicksure.mobile.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quicksure.mobile.entity.Deliveryinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.DeliveryInforService;

@Controller
@RequestMapping("/deliveryInfor")
public class DeliveryInforContorller {
	
	@Autowired
	private DeliveryInforService deliveryInforService;
	
	private static final Logger logger = Logger.getLogger(DeliveryInforContorller.class);
	
	@RequestMapping("/deliveryOrderInfo.do")
	public void deliveryOrderInfo(Deliveryinfor deliveryinfor,ModelMap modelMap,HttpServletRequest httprequest){
		logger.info("开始进行配送接口调用:Delivery Order start.");
		try {
			InsuranceDetailsVO insuranceDetails=deliveryInforService.deliveryToCustomer(deliveryinfor, httprequest);
			logger.info("配送成功"+insuranceDetails);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			logger.error("配送失败"+str);
		}
	} 
	
}
