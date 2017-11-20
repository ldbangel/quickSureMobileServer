package com.quicksure.mobile.sinosafefactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.dms.VHLDataManageService;
import com.quicksure.mobile.dmsutility.DMSUtility;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.utility.DateFormatUtils;
import com.quicksure.mobile.utility.StringUtils;
@Component
public class Sinosafeinterface {
	private static final Logger logger = Logger
			.getLogger(SinosafeConnect.class);
	@Resource
	private VHLDataManageService dataManageService;
	@Resource
	private DMSUtility dmsUtility;
	@Resource
	private SinosafeConnect sinosafeConnect;
	@Resource
	private JYConnect jyConnect;
	/**
	 * 处理Sinosafe 接口请求
	 * @param insuranceDetailsVO
	 * @return
	 */
	 public InsuranceDetailsVO performSinosafeOprations(InsuranceDetailsVO insuranceDetailsVO){
		 String action = insuranceDetailsVO.getUserinfor().getUserAction();
		 if("jiayixiansubmit".equals(action)
				 ||"junanbaosubmit".equals(action)
				 ||"VehicleAccidentSubmit".equals(action)){
			 logger.info("开始请求华安，请求action为: "+insuranceDetailsVO.getUserinfor().getUserAction()
					 +"and order No is: "+insuranceDetailsVO.getLudifcBaseinfor().getOrderno());
		 }else{
			 logger.info("开始请求华安，请求action为: "+insuranceDetailsVO.getUserinfor().getUserAction()
					 +"and order No is: "+insuranceDetailsVO.getBaseinfor().getOrderno());
		 }
		 synchronized(this){
		 try{
				 String responseXml="";
				 String errorCode="";
				 String errorMessage="";
				 Map<String, String> map = new HashMap<String, String>();
				 String userAction = insuranceDetailsVO.getUserinfor().getUserAction();
				 String requestXml = ProcessData.handleRequest(insuranceDetailsVO);
				 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setRequestxml(requestXml);
				 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setInterfaceslogsid(0);
				
				 dmsUtility.Saveinterfaceslogs(insuranceDetailsVO);
				 if(StringUtils.checkStringEmpty(userAction)
						 &&("ModelSerachByVin".equals(userAction)
								 ||("ModelSerachByName").equalsIgnoreCase(userAction))){
					 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setRequesttime(DateFormatUtils.getSystemDate());
					 responseXml = jyConnect.SinosafeconnectionJingyou(requestXml);				 
				 }else if("SubmitUnderwriting".equalsIgnoreCase(userAction)&&insuranceDetailsVO.getBaseinfor().getOrderstate()==30){
					 logger.info("第二次申请核保开始");
					 return insuranceDetailsVO;
				 }else{
					 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setRequesttime(DateFormatUtils.getSystemDate());
					 responseXml = sinosafeConnect.sinosafeConnection(requestXml,userAction);
				 }
				 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setResponsexml(responseXml);						 
				 if(StringUtils.checkStringEmpty(responseXml)){
					 map = ProcessData.getResponseCodeandMessage(userAction,responseXml);
				 }else{
					 logger.info(userAction+"连接超时，请检查服务的连接");
				 }				
				 if(map!=null&&map.size()>0){
					 errorCode=map.get("errorCode");
					 errorMessage=map.get("errorMessage");
					 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setResponsecode(errorCode);
					 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setResponsemessage(errorMessage);
				 }else{
					 errorCode="TO999999";
					 errorMessage="连接华安服务异常";
					 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setResponsecode(errorCode);
					 insuranceDetailsVO.getInterfaceslogsWithBLOBs().setResponsemessage(errorMessage);
				 }
				 
				 insuranceDetailsVO.getUserinfor().setErrorCode(null);
				 insuranceDetailsVO.getUserinfor().setErrorMessage(null);
				if (StringUtils.checkStringEmpty(errorCode)) {
					if (LudiConstants.SINOSAFEINTERFACESUCCESS.equalsIgnoreCase(errorCode)
							|| LudiConstants.JYSUCCESS.equalsIgnoreCase(errorCode)
							|| LudiConstants.SINOSAFECANCELINTERFACESUCCESS.equals(errorCode)
							|| LudiConstants.SINOSAFEFEICHESUCCESS.equals(errorCode)) {
						insuranceDetailsVO.getInterfaceslogsWithBLOBs()
								.setInterfacesstatu("10");//成功
						insuranceDetailsVO = ProcessData.handleResponse(//成功就调用返回处理
								responseXml, insuranceDetailsVO);
					} else if(LudiConstants.SINOSAFEINTERFACEFAIL.equalsIgnoreCase(errorCode)||LudiConstants.SINOSAFECANCELINTERFACEFAIL.equals(errorCode)
							|| LudiConstants.SINOSAFEFEICHEFAIL.equals(errorCode)){
						insuranceDetailsVO.getInterfaceslogsWithBLOBs()
								.setInterfacesstatu("20");//失败
						insuranceDetailsVO.getUserinfor().setErrorCode(errorCode);
						insuranceDetailsVO.getUserinfor().setErrorMessage(errorMessage);
					}else {
						insuranceDetailsVO.getInterfaceslogsWithBLOBs()
								.setInterfacesstatu("30");//异常
						insuranceDetailsVO.getUserinfor().setErrorCode(errorCode);
						insuranceDetailsVO.getUserinfor().setErrorMessage(errorMessage);

					}
				}
				dmsUtility.updateInterfaceLogs(insuranceDetailsVO);		 
		 }
		 catch (Exception e) {
			  StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();  
			logger.error("华安工厂类异常，异常信息为 "+str);
		}
		 }
		 logger.info("结束华安请求,请求action为 "+insuranceDetailsVO.getUserinfor().getUserAction()+"and order No is: "+insuranceDetailsVO.getBaseinfor().getOrderno());		 
		 return insuranceDetailsVO;
		 
	 }
	 
	 
	/**
	 * 
	* @Title: getrespoonsexml 
	* @Description: TODO(batch_search.jsp 页面接口请求) 
	* @param @param ins
	* @param @return
	* @param @throws Exception    设定文件 
	* @return InsuranceDetailsVO    返回类型 
	* @throws
	 */
	 public InsuranceDetailsVO getrespoonsexml(InsuranceDetailsVO ins) throws Exception{
		 String useraction=ins.getUserinfor().getUserAction();
		 String requestxml=ins.getInterfaceslogsWithBLOBs().getRequestxml();
		 String responseXml="";
		 //判断是华安还是精友
		 if(useraction.equals("sinosafeConnect")){
			 responseXml = sinosafeConnect.sinosafeConnection(requestxml,useraction);
			 logger.info("调用华安接口响应报文"+responseXml);
		 }		 
		 ins.getInterfaceslogsWithBLOBs().setResponsexml(responseXml);
		return ins;
		 
	 }

}
