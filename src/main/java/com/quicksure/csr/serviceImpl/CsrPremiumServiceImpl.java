package com.quicksure.csr.serviceImpl;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicksure.csr.service.CsrPremiumService;
import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dms.PaymentInforDataManageService;
import com.quicksure.mobile.dms.VHLDataManageService;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Vhlinfor;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;
import com.quicksure.mobile.utility.DateFormatUtils;

@Service("csrPremiumService")
public class CsrPremiumServiceImpl implements CsrPremiumService {
	private static final Logger logger = Logger
			.getLogger(CsrPremiumService.class);

	@Resource 
	private VHLDataManageService dataManageService;
	
	@Resource
	private Sinosafeinterface sinosafeinterface;
	@Autowired
	private PaymentInforDataManageService dbDataManageService;
	
	public InsuranceDetailsVO submitVehicleinfor(Vhlinfor vhlinfor,
			HttpServletRequest httprequest) {
		logger.info("CSR开始操作车辆信息");
		String vhorderno=httprequest.getParameter("vhorderno")==null?"":httprequest.getParameter("vhorderno");
		Vhlinfor existvhlinfor=dataManageService.csrgetVhlinforByorderno(vhorderno);
		InsuranceDetailsVO insuranceDetailsVO = new InsuranceDetailsVO();
		 try {
			 if(existvhlinfor!=null){//生成过订单号,车辆信息存在
				 logger.info("CSR开始修改车辆信息");
				 vhlinfor.setVhiinforid(existvhlinfor.getVhiinforid());
				 insuranceDetailsVO.setVhlinfor(vhlinfor);
				 insuranceDetailsVO = dataManageService.updateVehicleData(insuranceDetailsVO);
			 }else{
				 logger.info("CSR开始存储车辆信息并生成订单号");
				 insuranceDetailsVO.setVhlinfor(vhlinfor);
				 insuranceDetailsVO = dataManageService.saveVhlData(insuranceDetailsVO);
			 }
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString(); 
			logger.info("CSR操作车辆信息失败"+str);
		}
		 logger.info("CSR操作车辆信息结束");
		return insuranceDetailsVO;
	}
	

	
	/**
	 * 支付申请
	 */
	public InsuranceDetailsVO paymentApplication(Baseinfor baseinfor,HttpServletRequest request) {
		InsuranceDetailsVO insuranceDetails=null;
		if (insuranceDetails == null) {			
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetails=insuranceDetailsVo;
		}
		if(baseinfor!=null){
			insuranceDetails.setBaseinfor(baseinfor);
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
		
		if("10".equals(insuranceDetails.getInterfaceslogsWithBLOBs().getInterfacesstatu())){
			dbDataManageService.operatePaymentInfo(insuranceDetails);
		}
		return insuranceDetails;
	}

}
