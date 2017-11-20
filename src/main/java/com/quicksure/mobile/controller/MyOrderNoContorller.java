package com.quicksure.mobile.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Insuranceperinfor;
import com.quicksure.mobile.entity.InterfaceslogsWithBLOBs;
import com.quicksure.mobile.service.MyOrderNoService;

@Controller
@RequestMapping("/orderNo")
public class MyOrderNoContorller {

	@Autowired
	private MyOrderNoService myOrderNoService;
	
	private static final Logger logger = Logger.getLogger(MyOrderNoContorller.class);
	
	/**
	 * 获取个人手下所有单号
	 * 
	 * @param map
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/myOrderNoInfor.do")
	@ResponseBody
	public List<InsuranceDetailsVO> orderInformationTest(Map<String, Object> map,HttpServletRequest request, HttpServletResponse response) {
		logger.info("开始执行我的订单功能");
		long starttime=System.currentTimeMillis();
		System.out.println("开始时间:" +starttime);
		List<InsuranceDetailsVO> listInsuranceDetailsVo = new ArrayList<InsuranceDetailsVO>();
		try {
			listInsuranceDetailsVo = myOrderNoService.getOrderinformation(map,request);
			logger.info("我的订单查询成功");
		} catch (Exception e) {
			 StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			logger.info("我的订单功能异常" + str);
		}
		long endtime=System.currentTimeMillis();
		System.out.println("结束时间：" +endtime);
		System.out.println("耗时:"+(endtime-starttime)/1000);
		return listInsuranceDetailsVo;
	}
	
	/**
	 * 取消订单操作,状态为10,20 update;其他状态调撤单接口
	 * 
	 * @param orderno
	 * @param userinforid
	 * @param insuranceperinfor
	 * @param baseinfor
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/cancelOrderNo.do")
	@ResponseBody
	public String cancelOrder(Insuranceperinfor insuranceperinfor,@RequestBody Baseinfor baseinfor,HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
		logger.info("开始执行取消订单操作 :" +baseinfor.getOrderno());
		String msg="";
		try {
			msg=myOrderNoService.cancelOrderNo(baseinfor, request);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			logger.error("撤单异常 :"+str);
		}
		return msg;
	}
	
	/**
	 * 继续支付操作,跳转到返回的URL支付页面
	 * 
	 * @param orderno
	 * @param userinforid
	 * @param insuranceperinfor
	 * @param baseinfor
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/continuePayment.do")
	@ResponseBody
	public String continuePayment(String orderno,String userinforid,Insuranceperinfor insuranceperinfor,Baseinfor baseinfor,HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
			logger.info("开始处理继续支付操作.");
			HttpSession session=request.getSession();
			List<InsuranceDetailsVO> listInsurance=(List<InsuranceDetailsVO>) session.getAttribute("listInsurance");
			Baseinfor binfor=new Baseinfor();
			InsuranceDetailsVO insurancevo=new InsuranceDetailsVO();
			String url="";
			for(InsuranceDetailsVO insurance:listInsurance){
				if(orderno.equals(insurance.getBaseinfor().getOrderno())&&Integer.parseInt(userinforid)==insurance.getBaseinfor().getUserinforid()){	
					insurancevo = insurance;
					InterfaceslogsWithBLOBs interfaceslogsWithBLOBs = new InterfaceslogsWithBLOBs();
					insurancevo.setInterfaceslogsWithBLOBs(interfaceslogsWithBLOBs);
					url=insurance.getPaymentinfor().getPaymenturl();
					binfor=insurance.getBaseinfor();
				}
			}
			if(binfor.getOrderstate()==30&&(url==null||url.equals(""))){//状态等于30,未生成url,调用支付接口生成URL
				InsuranceDetailsVO	insuranceDetailsVO=myOrderNoService.continuePayment(insurancevo, request, response);
				url=insuranceDetailsVO.getPaymentinfor().getPaymenturl();
			}
			logger.info("URL:"+url);
//			response.setCharacterEncoding("utf-8");	
//			response.setContentType( "application/json");
//			response.sendRedirect(url);//支付链接跳转
			return url;
	}
	
	/**
	 * 暂存状态的继续投保操作(10跳转到车辆信息页面,20跳转到险种页面) 
	 * 
	 * @param orderno
	 * @param userinforid
	 * @param insuranceperinfor
	 * @param baseinfor
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/continueInsure.do")
	@ResponseBody
	public String continueInsure(String orderno,String userinforid,Insuranceperinfor insuranceperinfor,Baseinfor baseinfor,HttpServletRequest request, HttpServletResponse response){
		logger.info("开始处理继续投保操作.");
		String path="";
		HttpSession session=request.getSession();
		List<InsuranceDetailsVO> listInsurance=(List<InsuranceDetailsVO>) session.getAttribute("listInsurance");
		Baseinfor binfor=new Baseinfor();
		InsuranceDetailsVO insurVO=new InsuranceDetailsVO();
		InterfaceslogsWithBLOBs inter=new InterfaceslogsWithBLOBs();
		for(InsuranceDetailsVO insurance:listInsurance){
			if(orderno.equals(insurance.getBaseinfor().getOrderno())&&Integer.parseInt(userinforid)==insurance.getBaseinfor().getUserinforid()){	
				insurVO=insurance;
				insurVO.setInterfaceslogsWithBLOBs(inter);
				binfor=insurance.getBaseinfor();
			}
		}
		session.setAttribute(orderno+"insuranceDetailsVO", insurVO);
		if(binfor.getOrderstate()==10){
			path = "/vehicleInfor/backToVehicleScreen.do?orderNo="+orderno+"";
		}else if(binfor.getOrderstate()==20){
			path = "/vehicleInfor/backToCoverageScreen.do?orderNo="+orderno+"";
		}
		return path;
	}
	
	/**
	 * 依据选择的单号展示订单详情 
	 * 
	 * @param orderno
	 * @param userinforid
	 * @param insuranceperinfor
	 * @param baseinfor
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping("getOrderDetail.do")
	public void getOrderDetail(String orderno,String userinforid,Insuranceperinfor insuranceperinfor,Baseinfor baseinfor,HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
		logger.info("开始处理展示订单详情.");
		HttpSession session=request.getSession();
		List<InsuranceDetailsVO> listInsurance=(List<InsuranceDetailsVO>) session.getAttribute("listInsurance");
		InsuranceDetailsVO insurancevo=new InsuranceDetailsVO();
		List<Coverageinfor> listCoverage=new ArrayList<Coverageinfor>();
		for(InsuranceDetailsVO insurance:listInsurance){
			if(orderno.equals(insurance.getBaseinfor().getOrderno())&&Integer.parseInt(userinforid)==insurance.getBaseinfor().getUserinforid()){	
				listCoverage=myOrderNoService.getListCoverage(orderno);
				insurance.setCoverageinfors(listCoverage);
				insurancevo=insurance;
			}
		}
		session.removeAttribute("simpleinsurancevo");
		session.setAttribute("simpleinsurancevo", insurancevo);
		
		
	}
	
	
	/**
	 * 删除支付失效及当前时间大于保单生效时间的30，40的单子
	 */
/*	public void AllDelete(){
		logger.info("开始查询状态为30和40的订单");
		List<Baseinfor> mess;
		try {
			mess= myOrderNoService.getAllBaseinfor();
			
		} catch (Exception e) {	
			logger.info("查询删单异常！"+e.getMessage());
		}
		logger.info("过期订单删单结束！");
	}*/
	
}
