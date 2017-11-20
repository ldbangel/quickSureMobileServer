package com.quicksure.mobile.serviceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dms.PaymentInforDataManageService;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.PaymentInforService;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;
import com.quicksure.mobile.utility.DateFormatUtils;
import com.quicksure.mobile.utility.StringUtils;

@Service("paymentInforService")
public class PaymentInforSerivceImpl implements PaymentInforService {
	private static final Logger logger = Logger.getLogger(PaymentInforSerivceImpl.class);
	@Resource
	private Sinosafeinterface sinosafeinterface;
	@Autowired
	private PaymentInforDataManageService dbDataManageService;
	
	/**
	 * 支付申请
	 */
	public InsuranceDetailsVO paymentApplication(HttpServletRequest request) {
		HttpSession  session = request.getSession();
		 System.out.println(session.getId());
		String orderNo=request.getParameter("orderNo");
		int lastImplementPage=6;//代表用户操作的当前页,跳转到支付页面
		InsuranceDetailsVO insuranceDetails=null;
		if (StringUtils.checkStringEmpty(orderNo)&& session.getAttribute(orderNo + "insuranceDetailsVO") != null){
			 insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo +"insuranceDetailsVO");
			}
		if (insuranceDetails == null) {			
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetails=insuranceDetailsVo;
		}
		
		insuranceDetails.getUserinfor().setUserAction("PaymentApplication");//设置action name
		sinosafeinterface.performSinosafeOprations(insuranceDetails);
		
		Float taxpremium =Float.parseFloat((insuranceDetails.getBaseinfor().getTaxpremium()==null?0:insuranceDetails.getBaseinfor().getTaxpremium()).toString()); 
		Float jqpremium = Float.parseFloat((insuranceDetails.getBaseinfor().getJqpremium()==null?0:insuranceDetails.getBaseinfor().getJqpremium()).toString());
		Float sypremium = Float.parseFloat((insuranceDetails.getBaseinfor().getSypremium()==null?0:insuranceDetails.getBaseinfor().getSypremium()).toString());
		Float paymentpremium = taxpremium + jqpremium + sypremium; //得到支付的总金额
		insuranceDetails.getPaymentinfor().setPaymentpremium(paymentpremium.toString());
		insuranceDetails.getPaymentinfor().setPaymenttimes(DateFormatUtils.getSystemDate());
		insuranceDetails.getPaymentinfor().setPaymentstate(10);
		insuranceDetails.getBaseinfor().setLastImplementPage(lastImplementPage);
		if("10".equals(insuranceDetails.getInterfaceslogsWithBLOBs().getInterfacesstatu())){
			dbDataManageService.operatePaymentInfo(insuranceDetails);
		}
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		return insuranceDetails;
	}
	
}
