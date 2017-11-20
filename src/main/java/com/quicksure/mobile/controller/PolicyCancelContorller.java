package com.quicksure.mobile.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.PolicyCancelService;

@Controller
@RequestMapping("/policyCancel")
public class PolicyCancelContorller {
	
	@Autowired
	private PolicyCancelService policyCancelService;
	
	private static final Logger logger = Logger.getLogger(PolicyCancelContorller.class);
	
	/**
	 * 撤单接口需要交强险保单号、商业险保单号
	 * 
	 * @param baseinfor
	 * @param request
	 * @param httprequest
	 */
	@RequestMapping("/policyCancelInfo.do")
	public void policyCancelInfo(Baseinfor baseinfor, HttpServletRequest request,HttpServletRequest httprequest){
		logger.info("Policy Cancel By ApplicationNo Start, the Syapplicationno is:"+baseinfor.getSyapplicationno());
		logger.info("Policy Cancel By ApplicationNo Start the Syapplicationno is:"+baseinfor.getJqapplicationno());
//		String jq=httprequest.getParameter("jqCode");
//		String sy=httprequest.getParameter("syCode");
//		Baseinfor baseinfor=new Baseinfor();
//		baseinfor.setJqapplicationno(jq);
//		baseinfor.setSyapplicationno(sy);
//		insuranceDetailsVOr.setBaseinfor(baseinfor);
		
		try {
			InsuranceDetailsVO insuranceDetails=policyCancelService.policyCancelOrder(baseinfor);
			logger.info("撤单成功"+insuranceDetails);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			logger.info("撤单异常"+str);
		}
	} 
	
}
