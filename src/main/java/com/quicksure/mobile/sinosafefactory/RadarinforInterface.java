package com.quicksure.mobile.sinosafefactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dms.VHLDataManageService;
import com.quicksure.mobile.dmsutility.DMSUtility;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.utility.DateFormatUtils;
import com.quicksure.mobile.utility.StringUtils;
import com.towerswatson.rto.dpo.services._2010._01.DpoService;
import com.towerswatson.rto.dpo.services._2010._01.DpoServiceGetPofConfigurationFaultContractFaultFaultMessage;
import com.towerswatson.rto.dpo.services._2010._01.DpoServiceGetPofSevereFaultContractFaultFaultMessage;
import com.towerswatson.rto.dpo.services._2010._01.DpoService_Service;
import com.towerswatson.rto.dpo.services._2010._01.ObjectFactory;
import com.towerswatson.rto.dpo.services._2010._01.PofRequest;
import com.towerswatson.rto.dpo.services._2010._01.PofResponse;
import com.towerswatson.rto.smf.types.PofrInformationCollectionDataContract;
import com.towerswatson.rto.smf.types.PofrInformationDataContract;

@Component
public class RadarinforInterface {
	private static final Logger logger = Logger
			.getLogger(RadarinforInterface.class);
	@Resource
	private VHLDataManageService dataManageService;
	@Resource
	private DMSUtility dmsUtility;
	@Resource
	private SinosafeConnect sinosafeConnect;
	@Resource
	private JYConnect jyConnect;
	@Resource
	private DpoService_Service  dpoService_Service;
	/**
	 * 处理Radarinfor 接口请求
	 * @param insuranceDetailsVO
	 * @return
	 */
	 public InsuranceDetailsVO performRadarinforOprations(InsuranceDetailsVO insuranceDetailsVO){
		 logger.info("开始请求雷达，请求action为: "+insuranceDetailsVO.getUserinfor().getUserAction()+"and order No is: "+insuranceDetailsVO.getBaseinfor().getOrderno());
		 synchronized(this){
			 try{
					 String responseXml="";
					 String errorCode="";
					 String errorMessage="";
					 PofRequest pofRequest=new PofRequest();      //PofRequest
				     PofResponse pofResponse=new PofResponse();   //PofResponse
				     ObjectFactory objfac=new ObjectFactory();    //ObjectFactory
					 PofrInformationCollectionDataContract pofrCollectionsCollectionDataContract= new PofrInformationCollectionDataContract();	//PofrInformationCollectionDataContract
					 PofrInformationDataContract pofrInformationDataContract=new PofrInformationDataContract(); //PofrInformationDataContract
					 Map<String, String> map=new HashMap<String, String>();
					 String userAction=insuranceDetailsVO.getUserinfor().getUserAction();
					 String requestXml = ProcessData.handleRequest(insuranceDetailsVO);   //请求的报文
					 logger.info("开始请求雷达，请求时间为 "+DateFormatUtils.getSystemDate()+"请求数据为 \r\n"+requestXml);
					 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setRequestxml(requestXml); //保存报文
					 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setInterfaceslogsid(0);
					
					 dmsUtility.Saveinterfaceslogs(insuranceDetailsVO);
					 if(StringUtils.checkStringEmpty(userAction)&&("ModelSerachByVin".equals(userAction)||("ModelSerachByName").equalsIgnoreCase(userAction))){
						 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setRequesttime(DateFormatUtils.getSystemDate());
						 responseXml=jyConnect.SinosafeconnectionJingyou(requestXml);				 
					 }else if("SubmitUnderwriting".equalsIgnoreCase(userAction)&&insuranceDetailsVO.getBaseinfor().getOrderstate()==30){
						 logger.info("第二次申请核保开始");
						 return insuranceDetailsVO;
					 }
					 else{
						 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setRequesttime(DateFormatUtils.getSystemDate());
						 pofrInformationDataContract.setPofr(requestXml);
					     pofrCollectionsCollectionDataContract.getPofrInformationDataContract().add(pofrInformationDataContract);
						 JAXBElement<PofrInformationCollectionDataContract> pofrCollection=objfac.createPofRequestPofrCollection(pofrCollectionsCollectionDataContract);	
						 pofrCollection.setValue(pofrCollectionsCollectionDataContract);		
						 pofRequest.setPofrCollection(pofrCollection);
						 
						 try {
								DpoService dpos= dpoService_Service.getDefaultDpoServiceEndpoint(); 
								pofResponse = dpos.getPof(pofRequest);
							    responseXml = pofResponse.getPofCollection().getValue().getPofInformationDataContract().get(0).getPof().getValue();//雷达请求返回报文
							    logger.info("雷达返回报文："+responseXml);
							} catch (DpoServiceGetPofConfigurationFaultContractFaultFaultMessage e) {
								  StringWriter sw = new StringWriter();  
								  e.printStackTrace(new PrintWriter(sw, true));  
								  String str = sw.toString();
								  logger.error("调雷达失败:"+str);
							} catch (DpoServiceGetPofSevereFaultContractFaultFaultMessage e) {
								  StringWriter sw = new StringWriter();  
								  e.printStackTrace(new PrintWriter(sw, true));  
								  String str = sw.toString();
								  logger.error("调雷达失败:"+str);
							}
					  }
					     insuranceDetailsVO.getInterfaceslogsWithBLOBs().setResponsexml(responseXml);	
					     
//					 if(StringUtils.checkStringEmpty(responseXml)){
//						 map=ProcessData.getResponseCodeandMessage(userAction,responseXml);
//					 }else{
//						 logger.info(userAction+"连接超时，请检查服务的连接");
//					 }				
//					 if(map!=null&&map.size()>0){
//						 errorCode=map.get("errorCode");
//						 errorMessage=map.get("errorMessage");
//						 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setResponsecode(errorCode);
//						 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setResponsemessage(errorMessage);
//					 }else{
//						 errorCode="TO999999";
//						 errorMessage="连接雷达服务异常";
//						 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setResponsecode(errorCode);
//						 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setResponsemessage(errorMessage);
//					 }
//					 
//					 insuranceDetailsVO.getUserinfor().setErrorCode(null);
//					 insuranceDetailsVO.getUserinfor().setErrorMessage(null);
//					if (StringUtils.checkStringEmpty(errorCode)) {
//						if (LudiConstants.SINOSAFEINTERFACESUCCESS
//								.equalsIgnoreCase(errorCode)
//								|| LudiConstants.JYSUCCESS
//										.equalsIgnoreCase(errorCode)||LudiConstants.SINOSAFECANCELINTERFACESUCCESS.equals(errorCode)) {
//							insuranceDetailsVO.getInterfaceslogsWithBLOBs()
//									.setInterfacesstatu("10");//成功
							insuranceDetailsVO = ProcessData.handleResponse(//成功就调用返回处理
									responseXml, insuranceDetailsVO);
//						} else if(LudiConstants.SINOSAFEINTERFACEFAIL.equalsIgnoreCase(errorCode)||LudiConstants.SINOSAFECANCELINTERFACEFAIL.equals(errorCode)){
//							insuranceDetailsVO.getInterfaceslogsWithBLOBs()
//							.setInterfacesstatu("20");//失败
//							insuranceDetailsVO.getUserinfor().setErrorCode(errorCode);
//							insuranceDetailsVO.getUserinfor().setErrorMessage(errorMessage);
//						}else {
//							insuranceDetailsVO.getInterfaceslogsWithBLOBs()
//							.setInterfacesstatu("30");//异常
//							insuranceDetailsVO.getUserinfor().setErrorCode(errorCode);
//							insuranceDetailsVO.getUserinfor().setErrorMessage(errorMessage);
//
//						}
//					}
							
					dmsUtility.updateInterfaceLogs(insuranceDetailsVO);
					 
			 }
			 catch (Exception e) {
				  StringWriter sw = new StringWriter();  
				  e.printStackTrace(new PrintWriter(sw, true));  
				  String str = sw.toString();  
				logger.error("雷达工厂类异常，异常信息为 "+str);
			}
			 }
		 logger.info("结束雷达请求,请求action为 "+insuranceDetailsVO.getUserinfor().getUserAction()+"and order No is: "+insuranceDetailsVO.getBaseinfor().getOrderno());		 
		 return insuranceDetailsVO;
	 }
	 /**
	  * 
	 * @Title: getRadaresponseXml 
	 * @Description: TODO(csr调用接口获取获取雷达的报文) 
	 * @param @param ins
	 * @param @return    设定文件 
	 * @return InsuranceDetailsVO    返回类型 
	 * @throws
	  */
	 public InsuranceDetailsVO getRadaresponseXml(InsuranceDetailsVO ins){
		 
		 String requestxml=ins.getInterfaceslogsWithBLOBs().getRequestxml();
		 PofrInformationDataContract pofrInformationDataContract=new PofrInformationDataContract();
		 PofrInformationCollectionDataContract pofrCollectionsCollectionDataContract= new PofrInformationCollectionDataContract();
		 ObjectFactory objfac=new ObjectFactory();
		 PofRequest pofRequest=new PofRequest(); 
		 PofResponse pofResponse=new PofResponse(); 
		 String responseXml="";
		 pofrInformationDataContract.setPofr(requestxml);
	     pofrCollectionsCollectionDataContract.getPofrInformationDataContract().add(pofrInformationDataContract);
		 JAXBElement<PofrInformationCollectionDataContract> pofrCollection=objfac.createPofRequestPofrCollection(pofrCollectionsCollectionDataContract);	
		 pofrCollection.setValue(pofrCollectionsCollectionDataContract);		
		 pofRequest.setPofrCollection(pofrCollection);
	  
		try {
			DpoService dpos= dpoService_Service.getDefaultDpoServiceEndpoint(); 
			pofResponse = dpos.getPof(pofRequest);
			if(pofResponse.getPofCollection().getValue()!=null){
				responseXml = pofResponse.getPofCollection().getValue().getPofInformationDataContract().get(0).getPof().getValue();//雷达请求返回报文
				logger.info("调用雷达接口响应报文"+responseXml);
				ins.getInterfaceslogsWithBLOBs().setResponsexml(responseXml);	
			}else{
				ins.getInterfaceslogsWithBLOBs().setResponsexml("");
				logger.info("调用雷达接口响应报文"+null);
			}					
		} catch (DpoServiceGetPofConfigurationFaultContractFaultFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DpoServiceGetPofSevereFaultContractFaultFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		    return ins;
		 
	 }
}
