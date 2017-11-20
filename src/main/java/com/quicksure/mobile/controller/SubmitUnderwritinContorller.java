package com.quicksure.mobile.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.entity.Deliveryinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Insuranceperinfor;
import com.quicksure.mobile.service.SubmitInforService;
@Controller
@RequestMapping("/submitInfor")
public class SubmitUnderwritinContorller {
	
		@Autowired
		private SubmitInforService submitInforService;
		private static final Logger logger = Logger
				.getLogger(SubmitUnderwritinContorller.class);

		/**
		 * 提核的Controller
		 */
		@RequestMapping("/submitUnderwriting.do")
		public  String submitUnderwriting(Deliveryinfor deliveryinfor,Insuranceperinfor insuranceperinfor,ModelMap modelMap,
				HttpServletRequest httprequest,HttpServletResponse response){
			logger.info("提核获取传输过来的订单号--提核开始--： "+httprequest.getParameter("orderNo"));
			InsuranceDetailsVO insuranceDetailsVO=new InsuranceDetailsVO();
			int lastImplementPage=5;//代表用户操作的当前页,跳转到支付页面
			String Success = ""; 
			synchronized(this){
			try {	
			insuranceDetailsVO = submitInforService.submitUnderwriting(modelMap,deliveryinfor,insuranceperinfor, httprequest);
			//获取错误信息传给前台jsp(EL)
			String errorMessage = insuranceDetailsVO.getInterfaceslogsWithBLOBs().getResponsemessage();
			String errorCode = insuranceDetailsVO.getInterfaceslogsWithBLOBs().getResponsecode();
			int agentFlag=0;
			if(insuranceDetailsVO.getUserinfor()!=null){
				agentFlag=insuranceDetailsVO.getUserinfor().getAgentFlag();
			}
					modelMap.addAttribute("agentFlag", agentFlag);
					modelMap.addAttribute("errorMessage", errorMessage);
					modelMap.addAttribute("errorCode", errorCode);
				Success = insuranceDetailsVO.getInterfaceslogsWithBLOBs().getInterfacesstatu();
			if(Success.equals("10")){
				insuranceDetailsVO.getBaseinfor().setLastImplementPage(lastImplementPage);
				if(insuranceDetailsVO.getBaseinfor().getOrderstate()==20){
					//总价格四舍五入保留2个小数点
					String totalPremium = insuranceDetailsVO.getBaseinfor().getTotalPremium();
					insuranceDetailsVO.getBaseinfor().setTotalPremium(String.format("%.2f", Double.parseDouble(totalPremium)));
					submitInforService.saveSubmitInfor(insuranceDetailsVO.getBaseinfor());
				}
				InsuranceDetailsVO insuranceDetails=submitInforService.AddSubmitInfor(deliveryinfor,insuranceperinfor,httprequest);
				modelMap.addAttribute("insuranceDetailsVO",insuranceDetails);
				logger.info("人员信息增加进人员信息表: "+insuranceDetails.getBaseinfor().getOwnersname());
				
			}else{
				logger.info("提核失败"+insuranceDetailsVO.getInterfaceslogsWithBLOBs().getResponsemessage());
			}		
			} catch (Exception e) {
				StringWriter sw = new StringWriter();  
				  e.printStackTrace(new PrintWriter(sw, true));  
				  String str = sw.toString();
				logger.error("提核失败:"+str);
			}
			}
			if(Success.equals("10")){
			 return LudiConstants.PAYMENT;		
			}else{
			 return LudiConstants.PERSONINFORS;
			}
		}

}
