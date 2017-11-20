package com.quicksure.csr.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.csr.service.CsrPremiumService;
import com.quicksure.mobile.entity.Agreementinfor;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.Deliveryinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Insuranceperinfor;
import com.quicksure.mobile.entity.Taxinfor;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.entity.Vhlinfor;
import com.quicksure.mobile.service.PremiumCountService;
import com.quicksure.mobile.service.SubmitInforService;
import com.quicksure.mobile.utility.JsonUtility;

@Controller
@RequestMapping("/CsrPremiumCount")
public class CsrPremiumCountContorller {

	@Autowired
	private PremiumCountService premiumCountService;
	
	@Autowired
	private CsrPremiumService csrPremiumService;
	
	@Autowired
	private SubmitInforService submitInforService;
	
	private static final Logger logger = Logger
			.getLogger(CsrPremiumCountContorller.class);
	
	/**
	 * 提交车辆信息并生成订单号
	 */
	
	@RequestMapping("/VhlSubmit.do")
	@ResponseBody
	public InsuranceDetailsVO vhlSubmit(@RequestBody Vhlinfor vhlinfor,HttpServletRequest httprequest){
		logger.info("CSR提交车辆信息开始-车架号:"+vhlinfor.getVinno());
		
		InsuranceDetailsVO insuranceDetailsVO = csrPremiumService.submitVehicleinfor(vhlinfor, httprequest);	 
		
		return insuranceDetailsVO;
	}
	
	
	/**
	 * 前端信息提交到此交互
	 * @param insuranceperinfor
	 * @param modelMap
	 * @param httprequest
	 * @throws ParseException 
	 */
	@RequestMapping("/CsrpremiumCount.do")
	@ResponseBody
	public InsuranceDetailsVO premiumCount(@RequestBody String insureData,HttpServletRequest httprequest) throws ParseException{
		logger.info("CSR保费试算开始--： "+1);
		/*
		 * 给予保费计算一些值,以后有前台，从前台获取
		 */
		Vhlinfor vhlinfor=new Vhlinfor();
		Baseinfor baseinfor=new Baseinfor();
		Userinfor userinfor=new Userinfor();
		List<Coverageinfor> coverageinfors=new ArrayList<Coverageinfor>();	
		if(insureData!=null){
			JsonUtility jsonUtility=new JsonUtility();
			String vhljsondata=jsonUtility.getVauleFromJson("insureData",insureData,"vhlinfor").toString();
			String basejsondata=jsonUtility.getVauleFromJson("insureData",insureData,"baseinfor").toString();
			String userjsondata=jsonUtility.getVauleFromJson("insureData", insureData,"userinfor").toString();
			List<Map<String, String>> coverageinforjsondata=(List<Map<String, String>>) jsonUtility.getVauleFromJson("insureData",insureData,"coverageinfors");
			InsuranceDetailsVO insuranceDetailsVO=new InsuranceDetailsVO();
	
			vhlinfor=(Vhlinfor) jsonUtility.getObjFromJson(vhlinfor, vhljsondata);
			baseinfor=(Baseinfor) jsonUtility.getObjFromJson(baseinfor, basejsondata);
			userinfor=(Userinfor) jsonUtility.getObjFromJson(userinfor, userjsondata);
			//获取用户ID
			if(userinfor!=null){
				baseinfor.setUserinforid(userinfor.getUserid());
			}
			if(coverageinforjsondata!=null&&coverageinforjsondata.size()>0){
				for(Map<String, String> map:coverageinforjsondata){
					Coverageinfor coverageinfor=new Coverageinfor();
					coverageinfor=(Coverageinfor) jsonUtility.getObjFromJson(coverageinfor, map.toString());
					coverageinfors.add(coverageinfor);
				}
			}
		}
		InsuranceDetailsVO premiumCount=null;
		try {
			 premiumCount=premiumCountService.PremiumCount(vhlinfor,baseinfor,coverageinfors, httprequest); 
		 if(premiumCount.getBaseinfor().getQuoteno()!=null){
			logger.info("CSR试算成功"+premiumCount.getVhlinfor().getDrvowner()); 
		 }else{
			 logger.info("CSR试算失败没有成功获取到报价单号");
		 }
		} catch (Exception e) {
			 StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();  
			logger.info("CSR试算失败:"+str);
		}
		return premiumCount;
	}
	

	/**
	 * 提核
	 * @param insuranceperinfor
	 * @param modelMap
	 * @param httprequest
	 * @throws ParseException 
	 */
	@RequestMapping("/CsrUnderwriting.do")
	@ResponseBody
	public InsuranceDetailsVO submitUnderwriting(@RequestBody String requestdata,HttpServletRequest httprequest,ModelMap modelMap) throws ParseException{
		InsuranceDetailsVO insuranceDetailsVO=new InsuranceDetailsVO();
		Insuranceperinfor insuranceperinfor=new Insuranceperinfor();
		Baseinfor baseinfor=new Baseinfor();
		Deliveryinfor deliveryinfor=new Deliveryinfor();
		try {
			String Success = "";
			JsonUtility jsonUtility=new JsonUtility();
			String insuperjsondata=jsonUtility.getVauleFromJson("requestdata",requestdata,"insuranceperinfor").toString();
			insuranceperinfor=(Insuranceperinfor) jsonUtility.getObjFromJson(insuranceperinfor, insuperjsondata);
			String basejsondata=jsonUtility.getVauleFromJson("requestdata",requestdata,"baseinfor").toString();
			baseinfor=(Baseinfor) jsonUtility.getObjFromJson(baseinfor, basejsondata);
			String deliveryjsondata=jsonUtility.getVauleFromJson("requestdata",requestdata,"deliveryinfor").toString();
			deliveryinfor=(Deliveryinfor) jsonUtility.getObjFromJson(deliveryinfor, deliveryjsondata);
			
			insuranceDetailsVO=submitInforService.csrSubmitUnderwriting(deliveryinfor, insuranceperinfor, baseinfor, httprequest);
					
			String errorMessage = insuranceDetailsVO.getInterfaceslogsWithBLOBs().getResponsemessage();
			String errorCode = insuranceDetailsVO.getInterfaceslogsWithBLOBs().getResponsecode();		
			modelMap.addAttribute("errorMessage", errorMessage);
			modelMap.addAttribute("errorCode", errorCode);
			
			Success = insuranceDetailsVO.getInterfaceslogsWithBLOBs().getInterfacesstatu();
			if(Success.equals("10")){//提核成功,修改订单状态为30,然后添加人员信息
				baseinfor = insuranceDetailsVO.getBaseinfor();
				submitInforService.saveSubmitInfor(baseinfor);//修改订单状态为30
				logger.info("CSR提核成功,人员信息添加进人员信息表, 车主为 : "+insuranceperinfor.getOwnersname());
				
				submitInforService.csrPersonInforAdd(deliveryinfor, insuranceperinfor,baseinfor, httprequest);//新增数据到人员信息表
				
			}else{
				logger.info("提核失败"+insuranceDetailsVO.getInterfaceslogsWithBLOBs().getResponsemessage());
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();  
			logger.info("提核异常 : "+str);
			
		}
		
		return insuranceDetailsVO;
	}
	
	/**
	 * 新增险种数据并修改订单状态和特约表信息
	 * @param insureData
	 * @param httprequest
	 * @throws ParseException
	 */
	@RequestMapping("/CsrCoverageinforsDateSave.do")
	@ResponseBody
	public InsuranceDetailsVO savePremiumCountData(@RequestBody String insureData,HttpServletRequest httprequest) throws ParseException{
		logger.info("CSR新增险种数据操作开始--： "+1);
		InsuranceDetailsVO insuranceDetailsVO=new InsuranceDetailsVO();
		try {
			Baseinfor baseinfor=new Baseinfor();
			Taxinfor taxinfor=new Taxinfor();
			List<Coverageinfor> coverageinfors=new ArrayList<Coverageinfor>();
			List<Agreementinfor> agreementinfors=new ArrayList<Agreementinfor>();
			if(insureData!=null){
				JsonUtility jsonUtility=new JsonUtility();
				String basejsondata=jsonUtility.getVauleFromJson("insureData",insureData,"baseinfor").toString();
				List<Map<String, String>> coverageinforjsondata=(List<Map<String, String>>) jsonUtility.getVauleFromJson("insureData",insureData,"coverageinfors");
				String taxjsondata=jsonUtility.getVauleFromJson("insureData", insureData, "taxinfor").toString();
				
				baseinfor=(Baseinfor) jsonUtility.getObjFromJson(baseinfor, basejsondata);
				insuranceDetailsVO.setBaseinfor(baseinfor);
				taxinfor=(Taxinfor) jsonUtility.getObjFromJson(taxinfor, taxjsondata);
				insuranceDetailsVO.setTaxinfor(taxinfor);
				if(coverageinforjsondata!=null&&coverageinforjsondata.size()>0){
					for(Map<String, String> map:coverageinforjsondata){
						Coverageinfor coverageinfor=new Coverageinfor();
						coverageinfor=(Coverageinfor) jsonUtility.getObjFromJson(coverageinfor, map.toString());
						coverageinfor.setBaseinfororderno(baseinfor.getOrderno());
						coverageinfors.add(coverageinfor);
					}
					insuranceDetailsVO.setCoverageinfors(coverageinfors);
				}
				List<Map<String,String>> agreementinforjsondata=(List<Map<String, String>>) jsonUtility.getVauleFromJson("insureData",insureData,"agreementinfors");
				if(agreementinforjsondata!=null&&agreementinforjsondata.size()>0){
					for(Map<String, String> map:agreementinforjsondata){
						Agreementinfor agreementinfor=new Agreementinfor();
						agreementinfor=(Agreementinfor) jsonUtility.getObjFromJson(agreementinfor, map.toString());
						agreementinfor.setOrderno(baseinfor.getOrderno());
						agreementinfors.add(agreementinfor);
					}
					insuranceDetailsVO.setAgreementinfors(agreementinfors);
				}
			}
			//保存险种信息
			submitInforService.insertCoverageBatch(coverageinfors,baseinfor,taxinfor,agreementinfors,httprequest);
			
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();  
			logger.info("新增险种数据失败 : " + str);
		}
		return insuranceDetailsVO;
	}
	/**
	 * 支付申请CSR
	 */
	@RequestMapping("/Csrpaymentinfor.do")
	@ResponseBody
	public InsuranceDetailsVO paymentinfors(@RequestBody Baseinfor baseinfor,HttpServletRequest httprequest,HttpServletResponse response){
		InsuranceDetailsVO insuranceDetailsVO = null;
		String URL="";
		try {
			insuranceDetailsVO=csrPremiumService.paymentApplication(baseinfor,httprequest); 
			response.setCharacterEncoding("utf-8");	
			response.setContentType( "application/json");
			 URL = insuranceDetailsVO.getPaymentinfor().getPaymenturl();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();  
			logger.error("支付申请失败"+str);
		}
		
		/*response.sendRedirect(URL);*///支付链接跳转
		//response.getWriter().print(jsonArray);
  /*  response.getWriter().flush();*/		
		logger.info("payment application Ends the result data is： "+insuranceDetailsVO.getPaymentinfor().getPaymentno());
	  return insuranceDetailsVO;
	}
}
