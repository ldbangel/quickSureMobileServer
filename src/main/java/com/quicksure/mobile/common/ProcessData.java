package com.quicksure.mobile.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.quicksure.feiche.entity.LudifcBaseinfor;
import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.entity.Agreementinfor;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.Deliveryinfor;
import com.quicksure.mobile.entity.Dptinfor;
import com.quicksure.mobile.entity.DropdownListInfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Insuranceperinfor;
import com.quicksure.mobile.entity.InterfaceDetails;
import com.quicksure.mobile.entity.InterfaceslogsWithBLOBs;
import com.quicksure.mobile.entity.Paymentinfor;
import com.quicksure.mobile.entity.RadarInfor;
import com.quicksure.mobile.entity.Taxinfor;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.entity.Vhlinfor;
import com.quicksure.mobile.utility.DateFormatUtils;
import com.quicksure.mobile.utility.StringUtils;
import com.quicksure.mobile.utility.SysConstantsConfig;
import com.quicksure.mobile.utility.XmlUtilsByDom4j;
import com.quicksure.mobile.velocity.VelocityTemplate;

public class ProcessData {
	private static final Logger logger = Logger
			.getLogger(ProcessData.class);
	/**
	 * 生成請求信息根據action
	 * 
	 * @param insuranceDetailsVO
	 * @return
	 */
	public static String handleRequest(InsuranceDetailsVO insuranceDetailsVO) {
		String action = insuranceDetailsVO.getUserinfor().getUserAction();
		String templateName = getTemplateName(action);
		insuranceDetailsVO = setInterfacecode(insuranceDetailsVO); 
		return generaceRequest(templateName, insuranceDetailsVO);
	}

	/**
	 * 調用velocity，生成requets
	 * 
	 * @param templateName
	 * @param obj
	 * @return
	 */
	public static String generaceRequest(String templateName, Object obj) {
		String requestXml = "";
		Map<String, Object> map = new HashedMap();
		try {
			InsuranceDetailsVO insuranceDetailsVO = (InsuranceDetailsVO) obj;
			String userAction = insuranceDetailsVO.getUserinfor()
					.getUserAction();
			if (StringUtils.checkStringEmpty(userAction)
					&& (userAction.equalsIgnoreCase("ModelSerachByVin") || userAction
							.equalsIgnoreCase("ModelSerachByName"))) {
				String Vehiclename=insuranceDetailsVO.getVhlinfor().getVehiclename();
				if(Vehiclename!=null&&!"".equalsIgnoreCase(Vehiclename)){
					Vehiclename=URLEncoder.encode(Vehiclename, "UTF-8");
					insuranceDetailsVO.getVhlinfor().setVehiclename(Vehiclename);
				}
/*				insuranceDetailsVO.getVhlinfor().setLcnno(encodePlanNo);*/
			}
			String dateTime = DateFormatUtils.getSystemDate();
			String[] dateTimes = StringUtils.split(dateTime, " ");
			String date = dateTimes[0] != null ? dateTimes[0] : "";
			String Times = dateTimes[1] != null ? dateTimes[1] : "";
			map.put("dateTime", dateTime);
			map.put("date", date);
			map.put("times", Times);
			map.put("JYUserName", SysConstantsConfig.JY_CONNECT_USERNAME);
			map.put("JYPassword", SysConstantsConfig.JY_CONNECT_PASSWORD);
			map.put("SinosafeUserName",SysConstantsConfig.SINOSAFE_CONNECT_USERNAME);
			map.put("SinosafePassword",SysConstantsConfig.SINOSAFE_CONNECT_PASSWOED);
			map.put("SinosafeExtenterCode",SysConstantsConfig.SINOSAFE_EXTENTERPCODE);
			map.put("SinosafeFeicheUserName", SysConstantsConfig.SINOSAFE_NOTCART_USERNAME);
			map.put("SinosafeFeichePassword", SysConstantsConfig.SINOSAFE_NOTCART_PASSWOED);
			map.put("sinosafeSynchronizationUrl", SysConstantsConfig.SINOSAFE_SYNCHRONIZATION_URL);
			map.put("sinosafeAsynchronousUrl", SysConstantsConfig.SINOSAFE_ASYNCHRONOUS_URL);
			map.put("sinosafeFeicheCode", SysConstantsConfig.SINOSAFE_FEICHE_CODE);
			map.put("returnURL",SysConstantsConfig.SINOSAFE_RETURN_URL+"?LudiOrderNo="+insuranceDetailsVO.getBaseinfor().getOrderno());
			map.put("dataObj", obj);
			requestXml = VelocityTemplate
					.getVelocityTemplate(templateName, map);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString(); 
			  logger.error("调用模板引擎异常"+str);
		}
		return requestXml;

	}

	/**
	 * 
	 * 根据useraction 获取对应action的template 请求模板
	 * 
	 * @param useraction
	 * @return
	 */
	public static String getTemplateName(String useraction) {
		String templateName = "";
		if (StringUtils.checkStringEmpty(useraction)) {
			if (useraction.equals("ModelSerachByVin")) {
				templateName = "template_ModelSerachbyvinfromJY.xml";
			} else if (useraction.equals("ModelSerachByName")) {
				templateName = "template_ModelSerachbyModelfromJY.xml";	
			} else if (useraction.equals("SubmitUnderwriting")) {
				templateName = "template_SubmitUnderwriting.xml";//提交核保
			} else if (useraction.equals("DeliveryOrderInfo")){
				templateName = "template_ModelDeliveryInforToJY.xml";
			} else if (useraction.equals("PaymentApplication")){
				templateName = "template_PaymentApplication.xml";
			} else if(useraction.equals("PremiumCount")){
				templateName = "template_PremiumCount.xml"; //保费试算报文
			} /*else if (useraction.equals("PolicyStatusQuery")){
				templateName = "template_PolicyStatusQuery.xml";
			} */else if(useraction.equals("PolicyCancel")){
				templateName = "template_ModelPolicyCancelFromSinosafe.xml";
			} else if(useraction.equals("PolicyStatusQueryList")){
				templateName = "template_PolicyStatusQueryList.xml";
			}else if(useraction.equals("Renewalinfor")){//续保
				templateName = "template_RenewalinforQuery.xml";
			}else if(useraction.equals("ModelSearchBySinosafe")){ //车辆查询通过华安
				templateName = "template_ModelSearchFromSinosafe.xml";
			}else if(useraction.equals("Radarinfor")){    //雷达试算
				templateName = "template_Radarinfor.xml";
			}else if(useraction.equals("jiayixiansubmit")){//驾意险提交核保信息提核
				templateName = "template_NoncarjiayixianUnderwriting.xml";
			}else if(useraction.equals("junanbaosubmit")){
				templateName = "template_NoncarJunanbaoUnderwriting.xml";//君安保提交信息
			}else if(useraction.equals("VehicleAccidentSubmit")){
				templateName = "template_NoncarYilupinganUnderwriting.xml";//一路平安提交信息核保
			}else if(useraction.equals("PremiumCourtVehicleSubmit")){
				templateName = "template_PremiumCourtVehicleSubmit.xml";//车辆信息提交调一次保费试算
			}
		}
		return templateName;

	}

	/**
	 * 根據action，生成需要解析的對應節點 如
	 * DataPacket/ProductResponses/VehicleResponse/Vehicle 即解析的xml結構為
	 * <DataPacket
	 * ><ProductResponses><VehicleResponse><Vehicle></VehicleResponse>
	 * </ProductResponses><DataPacket>
	 * 
	 * @param action
	 * @return
	 */
	public static String getAnalysisPathformAction(String action) {
		String path = "";
		if (StringUtils.checkStringEmpty(action)) {
			if (action.equals("ModelSerachByVin")
					|| action.equalsIgnoreCase("ModelSerachByName")) {
				path = "DataPacket/ProductResponses/VehicleResponse/Vehicle";
			}else if(action.equals("DeliveryOrderInfo")) {
				path = "PACKET/HEAD"; 
			}else if(action.equals("PaymentApplication")){
				path = "PACKET/BODY/BASE";
			}else if(action.equals("PolicyStatusQueryList")){
				path = "PACKET/BODY/APP_INFO";
			}else if(action.equals("PolicyCancel")){
				path = "PACKET/BODY/BASE/APP_INFO";
			}else if(action.equals("PremiumCount")||action.equals("PremiumCourtVehicleSubmit")){ //保费试算
				path = "PACKET/BODY";
			}else if(action.equals("SubmitUnderwriting")){ //提交核保
				path = "PACKET/BODY";
			}else if(action.equals("Renewalinfor")){ //续保返回解析
				path = "PACKET/BODY";
			}else if(action.equals("ModelSearchBySinosafe")){
				path = "PACKET/BODY/VHL_LIST/VHL_DATA";
			}else if(action.equals("Radarinfor")){
				path = "";
			}else if(action.equals("VehicleAccidentSubmit")
					|| action.equals("junanbaosubmit")
					|| action.equals("jiayixiansubmit")){ //非车险
				path = "PACKET";
			}	
		}
		return path;
	}

	/**
	 * 處理interfaces response
	 * 
	 * @param response
	 * @param insuranceDetailsVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static InsuranceDetailsVO handleResponse(String response,
			InsuranceDetailsVO insuranceDetailsVO) {
		String action = insuranceDetailsVO.getUserinfor().getUserAction();
		Map<String, Object> responseList = getListfromResponse(response,action);
		if (StringUtils.checkStringEmpty(action)) {
			if (action.equalsIgnoreCase("ModelSerachByVin")
					|| action.equalsIgnoreCase("ModelSerachByName")) {
				if(responseList.get("Vehicle")==null){
					List<Map<String, Object>> vhlinforList=new ArrayList<Map<String,Object>>();
					vhlinforList.add(responseList);
					insuranceDetailsVO.setVhlinfoList(vhlinforList);
				}else{
					insuranceDetailsVO.setVhlinfoList((List<Map<String, Object>>) responseList.get("Vehicle"));				
				}								
			}else if(action.equalsIgnoreCase("ModelSearchBySinosafe")){
				String brandName = "";//品牌
				String demio = "";//车系
				if(responseList.get("VHL_DATA")==null){
					List<Map<String, Object>> vhlinforList=new ArrayList<Map<String,Object>>();
					vhlinforList.add(responseList);
					insuranceDetailsVO.setVhlinfoList(vhlinforList);
				}else{
					insuranceDetailsVO.setVhlinfoList((List<Map<String, Object>>) responseList.get("VHL_DATA"));				
				}	
			}else if(action.equalsIgnoreCase("DeliveryOrderInfo")){
				insuranceDetailsVO.setPolCancelList((List<Map<String, String>>) responseList.get(""));
			}else if(action.equalsIgnoreCase("PaymentApplication")){
				insuranceDetailsVO.getPaymentinfor().setPaymentno(responseList.get("PAY_APP_NO").toString());//支付链接：paymentUrl
				insuranceDetailsVO.getPaymentinfor().setPaymenturl(responseList.get("PAYADDRESS").toString());//订单号：paymentNo
			}else if(action.equalsIgnoreCase("PolicyCancel")){
				insuranceDetailsVO.setPolCancelList((List<Map<String, String>>) responseList.get("APP_INFO"));
			}else if(action.equalsIgnoreCase("PolicyStatusQueryList")){
				insuranceDetailsVO.setMultiplePolicyStatusList((List<Map<String, String>>) responseList.get("APP_INFO"));
			}else if(action.equalsIgnoreCase("SubmitUnderwriting")){
				Map<String,String> jqmap =(HashMap<String, String>) responseList.get("JQ_BASE");//获取到JQ节点
				Map<String,String> symap =(HashMap<String, String>) responseList.get("SY_BASE") ;//获取到SY节点
				
				String syapplicationno = "";//商业险投保单
				String sypremium = "";  //商业险保费
				String sypolicyenddate = ""; //商业险保险止期
				String jqapplicationno = ""; //交强险投保单
				String jqpremium = ""; //交强险保费
				String jqpolicyenddate = ""; //交强险止期
				String createtime = ""; //下单时间
				String applicationname = ""; //投保人
				String insrntname = ""; //被保人
			   
				 if(!symap.get("PLY_APP_NO").equals("")){
					 syapplicationno=symap.get("PLY_APP_NO");
					 sypremium=symap.get("PREMIUM");
					 sypolicyenddate=symap.get("INSRNC_END_TM");
					 createtime=symap.get("OPER_DATE");
					 applicationname = symap.get("APP_NME");
					 insrntname = symap.get("INSRNT_CNM");
					 insuranceDetailsVO.getBaseinfor().setSyapplicationno(syapplicationno);
				     insuranceDetailsVO.getBaseinfor().setSypremium(sypremium);
				     insuranceDetailsVO.getBaseinfor().setSypolicyenddate(sypolicyenddate);
				     insuranceDetailsVO.getBaseinfor().setCreatetime(createtime);
					 insuranceDetailsVO.getBaseinfor().setBase_applicationname(applicationname);
					 insuranceDetailsVO.getBaseinfor().setInsrntname(insrntname);
				 }
				 if(!jqmap.get("PLY_APP_NO").equals("")){
					 jqapplicationno=jqmap.get("PLY_APP_NO");
					 jqpremium=jqmap.get("PREMIUM");
					 jqpolicyenddate=jqmap.get("INSRNC_END_TM");
					 insuranceDetailsVO.getBaseinfor().setJqapplicationno(jqapplicationno);
					 insuranceDetailsVO.getBaseinfor().setJqpremium(jqpremium);
					 insuranceDetailsVO.getBaseinfor().setJqpolicyenddate(jqpolicyenddate);
				 }
				
			}else if(action.equalsIgnoreCase("PremiumCount")||action.equals("PremiumCourtVehicleSubmit")){				
				 Map<String,Object> changedMap=changeKeyForPremium(responseList);//将保费试算返回key转换为对应对象的属性名
				 insuranceDetailsVO=setPremiumResponsetoVo(changedMap,insuranceDetailsVO);//将保费计算返回封装为对象
				 Map<String,String> adjust =(HashMap<String, String>) responseList.get("ADJUST") ;//获取到Ad节点
				 Map<String,String> vhl =(HashMap<String, String>) responseList.get("VHL") ;//获取到vhl节点
				 Map<String,String> sybase =(HashMap<String, String>) responseList.get("SY_BASE") ;//获取到sy节点
				 Map<String,String> jqbase =(HashMap<String, String>) responseList.get("JQ_BASE") ;//获取到jq节点
				 String channelAdjustVal ="";//自主渠道系数
				 String autonomyAdjustVal ="";//自主核保系数 
				 String clamAdjustVal  ="";//无赔款优待记录系数
				 String peccancyAdjustVal ="";//交通违法记录系数
				 String setNumber=""; //核定载客人数
				 String syquerySequenceNo ="";//商业投保查询码
				 String jqquerySequenceNo ="";//交强投保查询码
				 if(!sybase.get("QUERY_SEQUENCE_NO").equals("")&&sybase.get("QUERY_SEQUENCE_NO")!=null){
					 syquerySequenceNo=sybase.get("QUERY_SEQUENCE_NO");
					 insuranceDetailsVO.getBaseinfor().setSyquerySequenceNo(syquerySequenceNo);
				 }
				 if(jqbase!=null){
					 if(!jqbase.get("QUERY_SEQUENCE_NO").equals("")&&jqbase.get("QUERY_SEQUENCE_NO")!=null){
						 jqquerySequenceNo=jqbase.get("QUERY_SEQUENCE_NO");
						 insuranceDetailsVO.getBaseinfor().setJqquerySequenceNo(jqquerySequenceNo);
					 }
				 }
				 String isImported=adjust.get("IS_IMPORTED");//是否进口车
				 if(isImported!=null && isImported !=""){
				 if("635001".equals(isImported)){
					 insuranceDetailsVO.getVhlinfor().setCarstyle(isImported);
				 }else if("635002".equals(isImported)){
					 insuranceDetailsVO.getVhlinfor().setCarstyle(isImported);
				 }
				 }
				 if(!adjust.get("CLAIM_ADJUST_VAL").equals("")){
					 clamAdjustVal=adjust.get("CLAIM_ADJUST_VAL");
					 insuranceDetailsVO.getBaseinfor().setClamAdjustVal(clamAdjustVal);
				 }
				 if(!adjust.get("PECCANCY_ADJUST_VAL").equals("")){
					 peccancyAdjustVal=adjust.get("PECCANCY_ADJUST_VAL");
					 insuranceDetailsVO.getBaseinfor().setPeccancyAdjustVal(peccancyAdjustVal);
				 }
				 if(!adjust.get("AUTONOMY_ADJUST_VAL").equals("")){
					 autonomyAdjustVal=adjust.get("AUTONOMY_ADJUST_VAL");
					 insuranceDetailsVO.getBaseinfor().setAutonomyAdjustVal(autonomyAdjustVal);
				 }
				 if(!adjust.get("CHANNEL_ADJUST_VAL").equals("")){
					 channelAdjustVal=adjust.get("CHANNEL_ADJUST_VAL");
					 insuranceDetailsVO.getBaseinfor().setChannelAdjustVal(channelAdjustVal);
				 }
				 //大对象中添加核定载客数
				 if(!vhl.get("SET_NUM").equals("")){
					 setNumber=vhl.get("SET_NUM");
					 insuranceDetailsVO.getVhlinfor().setSetNumber(setNumber); //核定载客数
				 }
			}else if(action.equalsIgnoreCase("Renewalinfor")){	//解析续保KEY封装到对象里			
				Map<String,Object> returnforRenewalinforMap = (Map<String, Object>) responseList.get("VHL");
				if(returnforRenewalinforMap!=null&&returnforRenewalinforMap.size()>0){
					Map<String, Object> newresponseList=new HashMap<String, Object>();
					newresponseList.put("VHL_RENEWAL", returnforRenewalinforMap);
					Map<String,String> changedMap=(Map<String, String>) changeKeyForPremium(newresponseList).get("VHL_RENEWAL");//将保费试算返回key转换为对应对象的属性名
					for(String key: changedMap.keySet()){
						setValuetoObj(key,changedMap.get(key),insuranceDetailsVO.getVhlinfor());
					}
				}				
			}else if(action.equalsIgnoreCase("Radarinfor")){
			    List<Element> returnforRadarinfor =  (List<Element>) responseList.get("radarinfor");
			    RadarInfor radarinfor = insuranceDetailsVO.getRadarinfor();
			    if(null !=returnforRadarinfor && returnforRadarinfor.size()>0){
				    String strBusinessRulesEndorsements = returnforRadarinfor.get(0).attributeValue("value");
				    if(strBusinessRulesEndorsements !=null && strBusinessRulesEndorsements !=""){
				    	 radarinfor.setBusinessRulesEndorsements(strBusinessRulesEndorsements);
				    }
				    String strBusinessRulesErrorMessages =returnforRadarinfor.get(1).attributeValue("value");
				    if(strBusinessRulesErrorMessages!=null && strBusinessRulesErrorMessages!=""){
				    	 radarinfor.setBusinessRulesErrorMessages(strBusinessRulesErrorMessages);
				    }
				   
				    String strBusinessRulesResults =returnforRadarinfor.get(2).attributeValue("value");
				    if(strBusinessRulesResults!=null && strBusinessRulesResults!=""){
				    	 radarinfor.setBusinessRulesResults(strBusinessRulesResults);
				    }
				    
				    String strPremChannelFactor =returnforRadarinfor.get(3).attributeValue("value");
				    if(strPremChannelFactor!=null && strPremChannelFactor!=""){
				    	radarinfor.setPremChannelFactor(strPremChannelFactor);
				    }
				    
				    String strPremNoClaimsDiscount =returnforRadarinfor.get(4).attributeValue("value");
				    if(strPremNoClaimsDiscount!=null && strPremNoClaimsDiscount!=""){
				    	radarinfor.setPremNoClaimsDiscount(strPremNoClaimsDiscount);
				    }
				    
				    String strPremTotal =returnforRadarinfor.get(5).attributeValue("value");
				    if(strPremTotal!=null && strPremTotal!=""){
				    	 radarinfor.setPremTotal(strPremTotal);
				    }
				    
				    String strPremTrafficViolation =returnforRadarinfor.get(6).attributeValue("value");
				    if(strPremTrafficViolation!=null && strPremTrafficViolation!=""){
				    	radarinfor.setPremTrafficViolation(strPremTrafficViolation);
				    }
				    
				    String strPremUnderwritingFactor =returnforRadarinfor.get(7).attributeValue("value");
				    if(strPremUnderwritingFactor!=null && strPremUnderwritingFactor!=""){
				    	radarinfor.setPremUnderwritingFactor(strPremUnderwritingFactor);
				    }
				    
				    String strQSActuarialRiskCostCTPL =returnforRadarinfor.get(8).attributeValue("value");
				    if(strQSActuarialRiskCostCTPL!=null && strQSActuarialRiskCostCTPL!=""){
				    	  radarinfor.setQSActuarialRiskCostCTPL(strQSActuarialRiskCostCTPL);
				    }
				    
				    String strQSActuarialRiskCostCombust =returnforRadarinfor.get(9).attributeValue("value");
				    if(strQSActuarialRiskCostCombust!=null && strQSActuarialRiskCostCombust!=""){
				    	 radarinfor.setQSActuarialRiskCostCombust(strQSActuarialRiskCostCombust);
				    }
				    
				    String strQSActuarialRiskCostDriverLiab =returnforRadarinfor.get(10).attributeValue("value");
				    if(strQSActuarialRiskCostDriverLiab!=null && strQSActuarialRiskCostDriverLiab!=""){
				    	 radarinfor.setQSActuarialRiskCostDriverLiab(strQSActuarialRiskCostDriverLiab);
				    }
				    
				    String strQSActuarialRiskCostGlass =returnforRadarinfor.get(11).attributeValue("value");
				    if(strQSActuarialRiskCostGlass!=null && strQSActuarialRiskCostGlass!=""){
				    	 radarinfor.setQSActuarialRiskCostGlass(strQSActuarialRiskCostGlass);
				    }
				    
				    String strQSActuarialRiskCostMOD =returnforRadarinfor.get(12).attributeValue("value");
				    if(strQSActuarialRiskCostMOD!=null && strQSActuarialRiskCostMOD!=""){
				    	  radarinfor.setQSActuarialRiskCostMOD(strQSActuarialRiskCostMOD);
				    }
				    
				    String strQSActuarialRiskCostOther =returnforRadarinfor.get(13).attributeValue("value");
				    if(strQSActuarialRiskCostOther!=null && strQSActuarialRiskCostOther!=""){
				    	  radarinfor.setQSActuarialRiskCostOther(strQSActuarialRiskCostOther);
				    }
				    
				    String strQSActuarialRiskCostPssgrLiab =returnforRadarinfor.get(14).attributeValue("value");
				    if(strQSActuarialRiskCostPssgrLiab!=null && strQSActuarialRiskCostPssgrLiab!=""){
				    	  radarinfor.setQSActuarialRiskCostPssgrLiab(strQSActuarialRiskCostPssgrLiab);
				    }
				    
				    String strQSActuarialRiskCostTheft =returnforRadarinfor.get(15).attributeValue("value");
				    if(strQSActuarialRiskCostTheft!=null && strQSActuarialRiskCostTheft!=""){
				    	 radarinfor.setQSActuarialRiskCostTheft(strQSActuarialRiskCostTheft);
				    }
				    
				    String strQSActuarialRiskCostTotal =returnforRadarinfor.get(16).attributeValue("value");
				    if(strQSActuarialRiskCostTotal!=null && strQSActuarialRiskCostTotal!=""){
				    	 radarinfor.setQSActuarialRiskCostTotal(strQSActuarialRiskCostTotal);
				    }
				    
				    String strQSActuarialRiskCostVTPL =returnforRadarinfor.get(17).attributeValue("value");
				    if(strQSActuarialRiskCostVTPL!=null && strQSActuarialRiskCostVTPL!=""){
				    	 radarinfor.setQSActuarialRiskCostVTPL(strQSActuarialRiskCostVTPL);
				    }
				    
				    String strRadarModelName =returnforRadarinfor.get(18).attributeValue("value");
				    if(strRadarModelName!=null && strRadarModelName!=""){
				    	  radarinfor.setRadarModelName(strRadarModelName);
				    }
				    
				    String strRadarNotes =returnforRadarinfor.get(19).attributeValue("value");
				    if(strRadarNotes!=null && strRadarNotes!=""){
				    	radarinfor.setRadarNotes(strRadarNotes);
				    }
				    
				    String strTariffPremAfterDiscountCombust =returnforRadarinfor.get(20).attributeValue("value");
				    if(strTariffPremAfterDiscountCombust!=null && strTariffPremAfterDiscountCombust!=""){
				    	 radarinfor.setTariffPremAfterDiscountCombust(strTariffPremAfterDiscountCombust);
				    }
				    
				    String strTariffPremAfterDiscountDedWaiverCombust =returnforRadarinfor.get(21).attributeValue("value");
				    if(strTariffPremAfterDiscountDedWaiverCombust!=null && strTariffPremAfterDiscountDedWaiverCombust!=""){
				    	  radarinfor.setTariffPremAfterDiscountDedWaiverCombust(strTariffPremAfterDiscountDedWaiverCombust);
				    }
				    
				    String strTariffPremAfterDiscountDedWaiverDriverLiab =returnforRadarinfor.get(22).attributeValue("value");
				    if(strTariffPremAfterDiscountDedWaiverDriverLiab!=null && strTariffPremAfterDiscountDedWaiverDriverLiab!=""){
				    	 radarinfor.setTariffPremAfterDiscountDedWaiverDriverLiab(strTariffPremAfterDiscountDedWaiverDriverLiab);
				    }
				    
				    String strTariffPremAfterDiscountDedWaiverMOD =returnforRadarinfor.get(23).attributeValue("value");
				    if(strTariffPremAfterDiscountDedWaiverMOD!=null && strTariffPremAfterDiscountDedWaiverMOD!=""){
				    	radarinfor.setTariffPremAfterDiscountDedWaiverMOD(strTariffPremAfterDiscountDedWaiverMOD);
				    }
				    
				    String strTariffPremAfterDiscountDedWaiverPssgrLiab =returnforRadarinfor.get(24).attributeValue("value");
				    if(strTariffPremAfterDiscountDedWaiverPssgrLiab!=null && strTariffPremAfterDiscountDedWaiverPssgrLiab!=""){
				    	 radarinfor.setTariffPremAfterDiscountDedWaiverPssgrLiab(strTariffPremAfterDiscountDedWaiverPssgrLiab);
				    }
				    
				    String strTariffPremAfterDiscountDedWaiverTheft =returnforRadarinfor.get(25).attributeValue("value");
				    if(strTariffPremAfterDiscountDedWaiverTheft!=null && strTariffPremAfterDiscountDedWaiverTheft!=""){
				    	 radarinfor.setTariffPremAfterDiscountDedWaiverTheft(strTariffPremAfterDiscountDedWaiverTheft);
				    }
				    
				    String strTariffPremAfterDiscountDedWaiverVTPL =returnforRadarinfor.get(26).attributeValue("value");
				    if(strTariffPremAfterDiscountDedWaiverVTPL!=null && strTariffPremAfterDiscountDedWaiverVTPL!=""){
				    	 radarinfor.setTariffPremAfterDiscountDedWaiverVTPL(strTariffPremAfterDiscountDedWaiverVTPL);
				    }
				    
				    String strTariffPremAfterDiscountDriverLiab =returnforRadarinfor.get(27).attributeValue("value");
				    if(strTariffPremAfterDiscountDriverLiab!=null && strTariffPremAfterDiscountDriverLiab!=""){
				    	radarinfor.setTariffPremAfterDiscountDriverLiab(strTariffPremAfterDiscountDriverLiab);
				    }
				    
				    String strTariffPremAfterDiscountGlass =returnforRadarinfor.get(28).attributeValue("value");
				    if(strTariffPremAfterDiscountGlass!=null && strTariffPremAfterDiscountGlass!=""){
				    	 radarinfor.setTariffPremAfterDiscountGlass(strTariffPremAfterDiscountGlass);
				    }
				    
				    String strTariffPremAfterDiscountMOD =returnforRadarinfor.get(29).attributeValue("value");
				    if(strTariffPremAfterDiscountMOD!=null && strTariffPremAfterDiscountMOD!=""){
				    	  radarinfor.setTariffPremAfterDiscountMOD(strTariffPremAfterDiscountMOD);
				    }
				    
				    String strTariffPremAfterDiscountPssgrLiab = returnforRadarinfor.get(30).attributeValue("value");
				    if(strTariffPremAfterDiscountPssgrLiab !=null && strTariffPremAfterDiscountPssgrLiab !=""){
				    	  radarinfor.setTariffPremAfterDiscountPssgrLiab(strTariffPremAfterDiscountPssgrLiab);
				    }
				    
				    String strTariffPremAfterDiscountTheft =returnforRadarinfor.get(31).attributeValue("value");
				    if(strTariffPremAfterDiscountTheft!=null && strTariffPremAfterDiscountTheft!=""){
				    	  radarinfor.setTariffPremAfterDiscountTheft(strTariffPremAfterDiscountTheft);
				    }
				    
				    String strTariffPremAfterDiscountVTPL =returnforRadarinfor.get(32).attributeValue("value");
				    if(strTariffPremAfterDiscountVTPL!=null && strTariffPremAfterDiscountVTPL!=""){
				    	  radarinfor.setTariffPremAfterDiscountVTPL(strTariffPremAfterDiscountVTPL);
				    }
				    insuranceDetailsVO.setRadarinfor(radarinfor);
			   } 
			}else if(action.equalsIgnoreCase("VehicleAccidentSubmit")){
				Map<String,String> thirdMap = (HashMap<String, String>) responseList.get("THIRD"); 
				Map<String,String> bodyMap = (HashMap<String, String>) responseList.get("BODY"); 
				/*String prodno = thirdMap.get("PRODNO");
				String planno = thirdMap.get("PLANNO");*/
				String paymentUrl = bodyMap.get("PAYNUMBER"); //支付链接
				
				insuranceDetailsVO.getLudifcBaseinfor().setPayUrl(paymentUrl);
			}else if(action.equalsIgnoreCase("junanbaosubmit")){
				Map<String,String> thirdMap = (HashMap<String, String>) responseList.get("THIRD"); 
				Map<String,String> bodyMap = (HashMap<String, String>) responseList.get("BODY"); 
				String paymentUrl = bodyMap.get("PAYNUMBER"); //支付链接
				insuranceDetailsVO.getLudifcBaseinfor().setPayUrl(paymentUrl);
			}else if(action.equalsIgnoreCase("jiayixiansubmit")){
				Map<String,String> thirdMap = (HashMap<String, String>) responseList.get("THIRD"); 
				Map<String,String> bodyMap = (HashMap<String, String>) responseList.get("BODY"); 
				String paymentUrl = bodyMap.get("PAYNUMBER"); //支付链接
				insuranceDetailsVO.getLudifcBaseinfor().setPayUrl(paymentUrl);
			}
		}
		return insuranceDetailsVO;
	}

	
	/**
	 * 處理interfaces response
	 * 
	 * @param response
	 * @param action
	 * @return
	 */
	public static Map<String, Object> getListfromResponse(String response,
			String action) {
		Map<String, Object> responseMap = null;
		String encodeing = "UTF-8";
		if (StringUtils.checkStringEmpty(action)
				&& (action.equals("ModelSerachByVin") || action
						.equalsIgnoreCase("ModelSerachByName"))) {
			encodeing = "gbk";
		}
		if (StringUtils.checkStringEmpty(action)
				&& (action.equals("Radarinfor") || action
						.equalsIgnoreCase("Radarinfor"))) {
			encodeing = "UTF-16";
		}
		String analysisPath = getAnalysisPathformAction(action);//获取解析节点

		try {
			responseMap = XmlUtilsByDom4j.XmlToMap(response, analysisPath,encodeing);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString(); 
			logger.error("解析返回信息异常"+str);
		}
		return responseMap;
	}

	/**
	 * 获取interface code通过action
	 * @param insuranceDetailsVO
	 * @return
	 */
   public static InsuranceDetailsVO setInterfacecode(InsuranceDetailsVO insuranceDetailsVO){
	   String action=insuranceDetailsVO.getUserinfor().getUserAction();
	   String interfaceCode="";
	   if(StringUtils.checkStringEmpty(action)){
		   if(action.equals("ModelSerachByVin") 
				   || action.equalsIgnoreCase("ModelSerachByName")
				   || action.equalsIgnoreCase("ModelSearchBySinosafe")){
			   interfaceCode="100001";//车型查询代码
		   }else if(action.equals("SubmitUnderwriting")){
			   interfaceCode="100014";//提核代码
		   }else if(action.equals("DeliveryOrderInfo")){
			   interfaceCode="100016";//配送代码
		   }else if(action.equals("PaymentApplication")){
			   interfaceCode="100010";//支付申请
		   }else if(action.equals("PremiumCount")||action.equals("PremiumCourtVehicleSubmit")){
			   interfaceCode="100004";//保费试算
		   }/*else if(action.equals("PolicyStatusQuery")){
			   interfaceCode="100007";//保单状态查询
		   }*/else if(action.equals("PolicyCancel")){
			   interfaceCode="100015";//撤单
		   }else if(action.equals("PolicyStatusQueryList")){
			   interfaceCode="100007";//保单状态查询
		   }else if(action.equals("Renewalinfor")){
			   interfaceCode="100019";//续保
		   }else if(action.equals("Radarinfor")){
			   interfaceCode="100020";//雷达
		   }else if(action.equals("jiayixiansubmit")){
			   interfaceCode="100011";// 驾意险
		   }else if(action.equals("junanbaosubmit")
				   || action.equals("VehicleAccidentSubmit")){
			   interfaceCode="100011";//非车--君安保||一路平安
		   }
	   }
	   insuranceDetailsVO.getInterfaceslogsWithBLOBs().setInterfacescode(interfaceCode);
	   return insuranceDetailsVO;
   }
   /**
    * 获取responseCode
    * &&errorMessage
    * @param action
    * @param response
    * @return
    */
   public static  Map<String, String> getResponseCodeandMessage(String action,String response){
	   Map<String, String> map=new HashMap<String,String>();
	   String path="";
	   String encoding="utf-8";
	   if(StringUtils.checkStringEmpty(action)){
		   if(action.equals("ModelSerachByVin") || action
					.equalsIgnoreCase("ModelSerachByName")){
			   path = "DataPacket/Responsetor";
			   encoding="gbk";
		   }else{
			   path="PACKET/HEAD";
		   }	   
	   }
	   List<Map<String, String>> resultList=XmlUtilsByDom4j
				.specifiedXml2list(response, path, encoding);
	   if(resultList!=null&&resultList.size()>0){
		   for(String s:resultList.get(0).keySet()){
			   if(LudiConstants.SINOSAFEERRORCODE.equalsIgnoreCase(s)||LudiConstants.JYERRORCODE.equalsIgnoreCase(s)||LudiConstants.SINOSAFECANCELERRORCODE.equalsIgnoreCase(s)){			   
				   map.put("errorCode",resultList.get(0).get(s) );
			   }
			   if(LudiConstants.SINOSAFEERRORMESSAGE.equalsIgnoreCase(s)||LudiConstants.JYERRORMESSAGE.equalsIgnoreCase(s)||LudiConstants.SINOSAFECANCELERRORMESSAGE.equalsIgnoreCase(s)){
				   map.put("errorMessage", resultList.get(0).get(s));
			   }
			   
		   }
	   }	   
	   return map;
	   
   }
   /**
    * 赋值给大对象
    * 孙小东
    * @param object
    * @param insuranceDetails
    * @return
    */
   public static InsuranceDetailsVO setObjValuetoinsuranceDetails(Object object,InsuranceDetailsVO insuranceDetails){
		Class objclass = object.getClass();
		Class insuranceDetaisClass = insuranceDetails.getClass();
		String methodNames = "get" + objclass.getSimpleName();
		Object object1 = null;
		try {
			Method method = insuranceDetaisClass.getMethod(methodNames, null);
			object1 = method.invoke(insuranceDetails, null);
			Field[] field = objclass.getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				String fieldName = field[i].getName();
				String methodName = "get" + fieldName;
				Object fieldvalue;
				Method[] methods = objclass.getDeclaredMethods();
				if(StringUtils.checkStringEmpty(fieldName)&&"vhiinforid".equalsIgnoreCase(fieldName)){
					continue;
				 }
				for (int j = 0; j < methods.length; j++) {
					if (methodName.equalsIgnoreCase(methods[j].getName())) {

						fieldvalue = methods[j].invoke(object, null);
						if (fieldvalue != null
								&& !"".equalsIgnoreCase(fieldvalue.toString())) {
							setValuetoObj(fieldName, fieldvalue, object1);
						}

					}
				}
			}
		} catch (Exception e) {
			  StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			  logger.error("setObjValuetoinsuranceDetails 方法异常"+str);
		}

		return insuranceDetails;
	}
   /**
    * 赋value到对象
    * 孙小东
    * @param fieldName
    * @param value
    * @param obj
    * @return
    */
   public static Object setValuetoObj(String fieldName,Object value, Object obj){
	   Class classObject=obj.getClass();
	   Method[] methods=classObject.getDeclaredMethods();
	   for(int i=0;i<methods.length;i++){
		   Method method=methods[i];
		   String methodName="set"+fieldName;
		   String currentMethodName=methods[i].getName();
		   Class paramType=null;		   		  	   		    
		   if(methodName.equalsIgnoreCase(currentMethodName)){
			   try {					
				obj= method.invoke(obj, value);
			} catch (IllegalAccessException e) {
				 StringWriter sw = new StringWriter();  
				  e.printStackTrace(new PrintWriter(sw, true));  
				  String str = sw.toString();
				  logger.error("setValuetoObj 方法异常"+str);
			} catch (IllegalArgumentException e) {
				StringWriter sw = new StringWriter();  
				  e.printStackTrace(new PrintWriter(sw, true));  
				  String str = sw.toString();
				  logger.error("setValuetoObj 方法异常"+str);
			} catch (InvocationTargetException e) {
				 StringWriter sw = new StringWriter();  
				  e.printStackTrace(new PrintWriter(sw, true));  
				  String str = sw.toString();
				  logger.error("setValuetoObj 方法异常"+str);
			}			   
		   }
	   }
	   
	   return obj;
   }
   /**
    * 大对象初始化
    * 孙小东
    * @return
    */
   public static InsuranceDetailsVO initInsuranceDetailsVO(){
	   InsuranceDetailsVO insuranceDetailsVO= new InsuranceDetailsVO();
	   
	   Baseinfor baseinfor=new Baseinfor();
	   Deliveryinfor deliveryinfor=new Deliveryinfor();
	   Dptinfor dptinfor=new Dptinfor();
	   DropdownListInfor dropdownListInfor=new DropdownListInfor();
	   Insuranceperinfor insuranceperinfor=new Insuranceperinfor();
	   InterfaceDetails interfaceDetails=new InterfaceDetails();
	   Paymentinfor paymentinfor=new Paymentinfor();
	   Taxinfor taxinfor=new Taxinfor();
	   Userinfor userinfor=new Userinfor();
	   Vhlinfor vhlinfor=new Vhlinfor();
	   InterfaceslogsWithBLOBs interfaceslogsWithBLOBs=new InterfaceslogsWithBLOBs();
	   List<Map<String, Object>> vhlinfoLis=new ArrayList<Map<String,Object>>();
	   List<Coverageinfor> coverageinfors=new ArrayList<Coverageinfor>();
	   LudifcBaseinfor ludifcBaseinfor=new LudifcBaseinfor();
	   insuranceDetailsVO.setLudifcBaseinfor(ludifcBaseinfor);
	   insuranceDetailsVO.setBaseinfor(baseinfor);
	   insuranceDetailsVO.setCoverageinfors(coverageinfors);
	   insuranceDetailsVO.setDeliveryinfor(deliveryinfor);
	   insuranceDetailsVO.setDptinfor(dptinfor);
	   insuranceDetailsVO.setDropdownListInfor(dropdownListInfor);
	   insuranceDetailsVO.setInsuranceperinfor(insuranceperinfor);
	   insuranceDetailsVO.setInterfaceDetails(interfaceDetails);
	   insuranceDetailsVO.setInterfaceslogsWithBLOBs(interfaceslogsWithBLOBs);
	   insuranceDetailsVO.setTaxinfor(taxinfor);
	   insuranceDetailsVO.setUserinfor(userinfor);
	   insuranceDetailsVO.setVhlinfoList(vhlinfoLis);
	   insuranceDetailsVO.setPaymentinfor(paymentinfor);
	   insuranceDetailsVO.setVhlinfor(vhlinfor);
	   
	   return insuranceDetailsVO;
   }
/**
 * 转换保费试算返回的key转换为对像的属性名
 * 孙小东
 * @param map
 * @return
 */
	public static Map<String, Object> changeKeyForPremium(
			Map<String, Object> returnLists) {
		Map<String, Object> changedData = new HashMap<String, Object>();
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"premiumReturnMapping.xml");
		for (String key : returnLists.keySet()) {
			boolean breakflag = false;
			if ( ("CLAIM_LIST").equalsIgnoreCase(key)
					|| ("REPEAT_DATA").equalsIgnoreCase(key)
					|| "REINSURE_LIST".equalsIgnoreCase(key)
					|| "REINSURE_MSG".equalsIgnoreCase(key)
					|| "ACCIDENT_LIST".equalsIgnoreCase(key)) {
				continue;
			}
			Map<String, String> map1 = (Map<String, String>) context
					.getBean(key);
			Map<String, Object> map2 = (Map<String, Object>) returnLists
					.get(key);
			Map<String, Object> map3 = new HashMap<String, Object>();
			for (String key1 : map1.keySet()) {
				for (String key2 : map2.keySet()) {
					if ("CVRG_DATA".equalsIgnoreCase(key2)) {
						breakflag = true;
						if(map2.get(key2) instanceof List){
							List<Map<String, String>> list = (List<Map<String, String>>) map2
									.get(key2);
							List<Map<String, String>> coverageList = new ArrayList<Map<String, String>>();
							for (Map<String, String> map : list) {
								Map<String, String> map4 = new HashMap<String, String>();
								for (String key4 : map1.keySet()) {
									for (String key3 : map.keySet()) {
										if (key4.equalsIgnoreCase(key3)) {
											map4.put(map1.get(key4),
													(String) map.get(key3));
											break;
										}
									}
								}
								
								coverageList.add(map4);
							}
							map3.put(key2, coverageList);
						}else{   //只选三者或司机并且不勾选不计免赔的时候报类型转换异常
							Map<String, String> map = (Map<String, String>) map2.get(key2);
							List<Map<String, String>> coverageList = new ArrayList<Map<String, String>>();
							Map<String, String> map4 = new HashMap<String, String>();
							for (String key4 : map1.keySet()) {
								for (String key3 : map.keySet()) {
									if (key4.equalsIgnoreCase(key3)) {
										map4.put(map1.get(key4),
												(String) map.get(key3));
										break;
									}
								}
							}
							
							coverageList.add(map4);
							map3.put(key2, coverageList);
						}

					} 
					else if ("APPNT_DATA".equalsIgnoreCase(key2)) {
						breakflag = true;
						List<Map<String, String>> list = (List<Map<String, String>>) map2
								.get(key2);
						List<Map<String, String>> agreementList = new ArrayList<Map<String, String>>();
						for (Map<String, String> map : list) {
							Map<String, String> map4 = new HashMap<String, String>();
							for (String key4 : map1.keySet()) {
								for (String key3 : map.keySet()) {
									if (key4.equalsIgnoreCase(key3)) {
										map4.put(map1.get(key4),
												(String) map.get(key3));
										break;
									}
								}
							}

							agreementList.add(map4);
						}
						map3.put(key2, agreementList);

					} 
					else if (key1.equalsIgnoreCase(key2)) {
						map3.put(map1.get(key1), (String) map2.get(key2));
						break;
					}

				}
				if (breakflag) {
					break;
				}
			}
			changedData.put(key, map3);
		}

		return changedData;

	}
	
	/**
	 * 转换续保返回的key转换为对像的属性名
	 * 
	 * @param map
	 * @return
	 */
/*		public static Map<String, Object> changeKeyForRenewal(
				Map<String, Object> returnLists) {
			Map<String, Object> changedData = new HashMap<String, Object>();
			ApplicationContext context = new ClassPathXmlApplicationContext("premiumReturnMapping.xml");
			for (String key : returnLists.keySet()) {
				boolean breakflag = false;
				if ( ("CLAIM_LIST").equalsIgnoreCase(key)
						|| ("REPEAT_DATA").equalsIgnoreCase(key)
						|| "REINSURE_LIST".equalsIgnoreCase(key)) {
					continue;
				}
				
			}
			
			
			return null;
		}*/

	/**
	 * 将保费计算返回的数据封装到承保大对象里面 
	 * 孙小东
	 */
	public static InsuranceDetailsVO setPremiumResponsetoVo(Map<String, Object> map,InsuranceDetailsVO insuranceDetailsVO){
		String orderNo=insuranceDetailsVO.getBaseinfor().getOrderno();
		Map<String, String> jQBaseinforMap=(Map<String, String>) map.get("JQ_BASE");
		Map<String, String> SYBaseinforMap=(Map<String, String>) map.get("SY_BASE");
		Map<String,String>  VHLMap=(Map<String, String>) map.get("VHL");
		Map<String, String> adjustMap=(Map<String, String>) map.get("ADJUST");
		Map<String, String> VhTaxMap=(Map<String, String>) map.get("VHLTAX");
		Map<String, List<Map<String, String>>> coveragemap=(Map<String, List<Map<String, String>>>) map.get("CVRG_LIST");
		List<Map<String,String>> coverageDatas=coveragemap.get("CVRG_DATA");
		Map<String, List<Map<String, String>>> agreementmap=(Map<String, List<Map<String, String>>>) map.get("APPNT_LIST");
		List<Map<String,String>> agreementData=agreementmap.get("APPNT_DATA");
		List<Coverageinfor> coverageinfors=new ArrayList<Coverageinfor>(); 
		List<Agreementinfor> agreementinfors=new ArrayList<Agreementinfor>(); 
		if(jQBaseinforMap!=null){
			for(String key:jQBaseinforMap.keySet()){
				setValuetoObj(key,jQBaseinforMap.get(key),insuranceDetailsVO.getBaseinfor());
			}
		}
		for(String key:SYBaseinforMap.keySet()){
			setValuetoObj(key,SYBaseinforMap.get(key),insuranceDetailsVO.getBaseinfor());
		}
		for(String key:VHLMap.keySet()){
			setValuetoObj(key,VHLMap.get(key),insuranceDetailsVO.getVhlinfor());
		}
		for(String key:adjustMap.keySet()){
			setValuetoObj(key,adjustMap.get(key),insuranceDetailsVO.getBaseinfor());
		}
		if(VhTaxMap!=null){
			if(insuranceDetailsVO.getTaxinfor()==null){
				Taxinfor taxinfor = new Taxinfor();
				insuranceDetailsVO.setTaxinfor(taxinfor);
			}
			for(String key:VhTaxMap.keySet()){
				setValuetoObj(key,VhTaxMap.get(key),insuranceDetailsVO.getTaxinfor());
			}
		}else{
			insuranceDetailsVO.setTaxinfor(null);//如果没有投保交强险，设置车船税对象为空
		}
		
		for(Map<String, String> coverageMap:coverageDatas ){
			Coverageinfor coverageinfor=new Coverageinfor();
			coverageinfor.setBaseinfororderno(orderNo);
			for(String key:coverageMap.keySet()){
				setValuetoObj(key,coverageMap.get(key),coverageinfor);				
			}
			coverageinfors.add(coverageinfor);
			
		}
		
		for(Map<String, String> agreementMap:agreementData ){
			Agreementinfor agreementinfor=new Agreementinfor();
			agreementinfor.setOrderno(orderNo);
			for(String key:agreementMap.keySet()){
				setValuetoObj(key,agreementMap.get(key),agreementinfor);				
			}
			agreementinfors.add(agreementinfor);
			
		}

		insuranceDetailsVO.setCoverageinfors(coverageinfors);
		insuranceDetailsVO.setAgreementinfors(agreementinfors);
		return insuranceDetailsVO;
	}
	
	public static Baseinfor setValuetoBaseInfor(Map<String, String> map,Baseinfor baseinfor){
		for(String key:map.keySet()){
			setValuetoObj(key,map.get(key),baseinfor);
		}				
		return baseinfor;
		
	}
	/**
	 * 获取发送短信验证码，返回状态
	 * 孙小东
	 * @param xml
	 * @return
	 */
	public static String getTemplateSMSReturnCode(String xml) {
		String resultCode = "";
		List<Map<String, String>> resultList = XmlUtilsByDom4j
				.specifiedXml2list(xml, "resp", "UTF-8");
		if (resultList != null && resultList.size() > 0) {
			for (String s : resultList.get(0).keySet()) {
				if (LudiConstants.TEMPLATE_SMS_RESPONSE_CODE
						.equalsIgnoreCase(s)) {
					resultCode = resultList.get(0).get(s);
				}

			}
		}
		return resultCode;

	}
	
	public static String brandNameToCode(String brandName){
		String code="";
		ApplicationContext context = new ClassPathXmlApplicationContext("premiumReturnMapping.xml");
		Map<String, String> map1 = (Map<String, String>) context.getBean("BRANDNAME_LIST");
		for (String key1 : map1.keySet()) {
//			if(key1.equals(brandName)){
//				code=map1.get(key1);
//			}
			if(brandName.contains(key1)){
				code=map1.get(key1);
			}
		}
		if(code.equals("")){
			code="carLogo";
		}
		return code;
	}
}
