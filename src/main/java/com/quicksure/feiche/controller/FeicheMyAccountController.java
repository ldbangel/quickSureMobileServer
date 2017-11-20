package com.quicksure.feiche.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.feiche.entity.LudifcBaseinfor;
import com.quicksure.feiche.service.FeicheMyAccountService;
import com.quicksure.mobile.constants.LudiConstants;

@Controller
@RequestMapping("feicheMyAccount")
public class FeicheMyAccountController {
	private final static Logger logger = Logger.getLogger(FeicheMyAccountController.class);
	
	@Resource
	private FeicheMyAccountService feicheMyAccountService;
	
	/**
	 * 初始化非车myAccount
	 */
	@RequestMapping("/initMyAccount.do")
	@ResponseBody
	public Map<String,Object> initMyAccount(HttpServletRequest request){
		logger.info("---------非车险MyAccount初始化开始！---------");
		Map<String,Object> map = feicheMyAccountService.getMyAccountInitInfor(request);
		return map;
	}
	
	/**
	 * 继续支付
	 */
	@RequestMapping("/continuePay.do")
	@ResponseBody
	public String continuePayment(String orderno,HttpServletRequest request){
		String url = feicheMyAccountService.continuePay(orderno, request);
		return url;
	}
	
	/**
	 * 继续投保
	 */
	@RequestMapping("/continueInsure.do")
	@ResponseBody
	public String continueInsure(String orderno,HttpServletRequest request){
		String prodno = feicheMyAccountService.continueInsure(orderno, request);
		String path = null;
		if("060I".equals(prodno)){
			path = "/views/quicksureFeiche/jiayixian/jsp/jiayixianHome.jsp?orderno="+orderno;
		}else if("060a".equals(prodno)){
			path = "/views/quicksureFeiche/yilupingan/jsp/yiluHomePage.jsp?orderno="+orderno;
		}else if("0615".equals(prodno)){
			path = "/views/quicksureFeiche/junanbao/jsp/junanbaoHome.jsp?orderno="+orderno;
		}
		return path;
	}
	
	/**
	 * 订单详情
	 */
	@RequestMapping("/orderDetails.do")
	public String orderDetails(ModelMap modelMap,String orderno,
			HttpServletRequest request,HttpServletResponse response){
		LudifcBaseinfor baseinfor = feicheMyAccountService.showOrderDetail(orderno, request);
		modelMap.addAttribute("baseinfor", baseinfor);
		return LudiConstants.FCORDERDETAILS;
	}
	
	/**
	 * 获取每个展示页面的订单数据
	 */
	@RequestMapping("/getMyOrders.do")
	@ResponseBody
	public Map<String,Object> myOrdersPaging(HttpServletRequest request,HttpServletResponse response){
		long starttime=System.currentTimeMillis();
		System.out.println("起始时间为："+starttime);
		Map<String,Object> map = feicheMyAccountService.getMyOrdersInfor(request);
		long endtime=System.currentTimeMillis();
		System.out.println("结束时间为："+endtime);
		System.out.println("耗时:"+(endtime-starttime)/1000);
		return map;
	}
}
