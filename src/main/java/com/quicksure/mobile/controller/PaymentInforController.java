package com.quicksure.mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.PaymentInforService;
import com.quicksure.mobile.service.SubmitInforService;

@Controller
@RequestMapping("/paymentInfor")
public class PaymentInforController {
	private static final Logger logger = Logger.getLogger(PaymentInforController.class);
	@Autowired
	private PaymentInforService paymentInforService;
	@Autowired
	private SubmitInforService submitInforService;
	
//	@RequestMapping("/paymentApplication.do")
//	public String submitUnderwriting(Insuranceperinfor insuranceperinfor,Baseinfor baseinfor,ModelMap modelMap,
//			HttpServletRequest httprequest,HttpServletResponse response){
//		logger.info("提核获取传输过来的订单号--提核开始--： "+insuranceperinfor.getOrderno());
//		JSONArray jsonArray=null;
//		String Success = "";
//		String url="";
//		InsuranceDetailsVO insuranceDetailsVO=null;
//		try {	
//			insuranceDetailsVO=submitInforService.submitUnderwriting(insuranceperinfor, httprequest);
//		//获取错误信息传给前台jsp(EL)
//		String errorMessage = insuranceDetailsVO.getInterfaceslogsWithBLOBs().getResponsemessage();
//		String errorCode = insuranceDetailsVO.getInterfaceslogsWithBLOBs().getResponsecode();
//				modelMap.addAttribute("errorMessage", errorMessage);
//				modelMap.addAttribute("errorCode", errorCode);
//		//提核返回成功插入base表&自动触发支付申请
//			Success = insuranceDetailsVO.getInterfaceslogsWithBLOBs().getInterfacesstatu();
//		if(Success.equals("10")){
//			if(insuranceDetailsVO.getBaseinfor().getOrderstate()==20){
//				InsuranceDetailsVO submitInfor = submitInforService.saveSubmitInfor(httprequest);
//				modelMap.addAttribute("insuranceDetailsVO",submitInfor);
//				logger.info("提核返回成功数据加进Base表里面: "+submitInfor.getBaseinfor().getOrderno());
//			}
//			InsuranceDetailsVO insuranceDetails=paymentInforService.paymentApplication(baseinfor, httprequest);
//			jsonArray=JSONArray.fromObject(insuranceDetails.getPayInfoList()); 
//			response.setCharacterEncoding("utf-8");	
//			response.setContentType( "application/json");
//			 url=insuranceDetails.getPaymentinfor().getPaymenturl();
//			response.sendRedirect(insuranceDetails.getPaymentinfor().getPaymenturl());//支付链接跳转
//		    //response.getWriter().print(jsonArray);
//		    response.getWriter().flush();
//		}else{
//			logger.info("提核失败"+insuranceDetailsVO.getInterfaceslogsWithBLOBs().getResponsemessage());
//		}
//		
//		} catch (Exception e) {
//			logger.info("提核失败:"+e.getMessage());
//		}
//		if(Success.equals("10")){
//		 return url;	
//		}else{
//			modelMap.addAttribute("insuranceDetailsVO", insuranceDetailsVO);
//		 return "/baseinfor";
//		}
//	}
//}	
	
	@RequestMapping("/paymentApplication.do")
	public void paymentApplication(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		JSONArray jsonArray=null;
		try{
			InsuranceDetailsVO insuranceDetailsVO=paymentInforService.paymentApplication(request);
			jsonArray=JSONArray.fromObject(insuranceDetailsVO.getPayInfoList()); 
			response.setCharacterEncoding("utf-8");	
			response.setContentType( "application/json");
			response.sendRedirect(insuranceDetailsVO.getPaymentinfor().getPaymenturl());//支付链接跳转
		    //response.getWriter().print(jsonArray);
		    response.getWriter().flush();
		}catch (IOException e) {	
			StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			logger.error("paymentApplication exception the  error message is: "+str);
				
		}		
		logger.info("payment application Ends the result data is： "+jsonArray);
	}
}
