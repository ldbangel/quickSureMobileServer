package com.quicksure.mobile.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.MyAccountService;

@Controller
@RequestMapping("/myAccount")
public class MyAccountController {
	@Autowired
	private MyAccountService myAccountService;
	
	/**
	 * 初始化myAccount
	 */
	@RequestMapping("/myAccountInit.do")
	@ResponseBody
	public Map<String,Object> myAccountInit(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = myAccountService.getMyAccountInitInfor(request);
		return map;
	}
	
	/**
	 * 初始化fechemyAccount
	 */
	@RequestMapping("/feichemyAccountInit.do")
	@ResponseBody
	public Map<String,Object> feicheMyAccountInit(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = myAccountService.getfeicheMyAccountInitInfor(request);
		return map;
	}
	
	/**
	 * 获取每个展示页面的订单数据
	 */
	@RequestMapping("/getMyOrders.do")
	@ResponseBody
	public Map<String,Object> myOrdersPaging(HttpServletRequest request,HttpServletResponse response){
		long starttime=System.currentTimeMillis();
		System.out.println("起始时间为："+starttime);
		Map<String,Object> map = myAccountService.getMyOrdersInfor(request);
		long endtime=System.currentTimeMillis();
		System.out.println("结束时间为："+endtime);
		System.out.println("耗时:"+(endtime-starttime)/1000);
		return map;
	}
	
	/**
	 * 撤销订单
	 */
	@RequestMapping("/cancelOrder.do")
	@ResponseBody
	public String cancelOrder(String orderno, HttpServletRequest request){
 		String msg = myAccountService.cancelOrder(orderno,request);
		return msg;
	}
	
	/**
	 * 继续支付
	 */
	@RequestMapping("/continuePay.do")
	@ResponseBody
	public String continuePayment(String orderno,HttpServletRequest request){
		String url = myAccountService.continuePay(orderno, request);
		return url;
	}
	
	/**
	 * copy支付链接
	 */
	/*@RequestMapping("/copyLink.do")
	@ResponseBody
	public String CopyLink(HttpServletRequest request){
		String url = request.getParameter("url");
		Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable text = new StringSelection(url);
		sysClip.setContents(text, null);
		return "success";
	}*/
	
	/**
	 * 继续投保
	 * 暂存状态的继续投保操作(10跳转到车辆信息页面,20跳转到险种页面) 
	 */
	@RequestMapping("/continueInsure.do")
	@ResponseBody
	public String continueInsure(String orderno,HttpServletRequest request){
		String path = myAccountService.continueInsure(orderno, request);
		return path;
	}

	/**
	 * 订单详情
	 */
	@RequestMapping("/orderDetails.do")
	public String orderDetails(ModelMap modelMap,String orderno,HttpServletRequest request,HttpServletResponse response){
		InsuranceDetailsVO insuranceDetailsVO = myAccountService.showOrderDetail(orderno, request);
		modelMap.addAttribute("simpleinsurancevo", insuranceDetailsVO);
		return LudiConstants.ORDERDETAILS;
	}
}
