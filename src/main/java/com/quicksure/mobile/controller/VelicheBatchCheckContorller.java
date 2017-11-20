package com.quicksure.mobile.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Interfaceslogs;
import com.quicksure.mobile.entity.InterfaceslogsWithBLOBs;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.service.VilicheBeatchCheckService;


/*
 * 车辆批量查询的contorller类
 */
@Controller
@RequestMapping("/VelicheBatchCheck")
public class VelicheBatchCheckContorller {
	private static final Logger logger = Logger.getLogger(VelicheBatchCheckContorller.class);
	
	@Autowired
	private VilicheBeatchCheckService vilicheBeatchCheckService;
	
	@SuppressWarnings({ "null", "unused" })
	@RequestMapping("/batchQuery.do")
	@ResponseBody
	public Map<String, Object> beatchCheck(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response){
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String lcnno = request.getParameter("lcnno");
		String orderstate = request.getParameter("orderstate");
		String orderno = request.getParameter("orderno");
		String drvowner = request.getParameter("drvowner");
		String deptcode = request.getParameter("deptcode");
		if(deptcode!=null && "".equalsIgnoreCase(deptcode.trim())){
			deptcode="1"; //当deptcode不等于空字符串时，将其设置为1
		}
		try {
			lcnno=new String(lcnno.getBytes("iso8859-1"),"utf-8");
			drvowner=new String(drvowner.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			  StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			  logger.error("beatchCheck 方法异常"+str);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orderNo", orderno);
		map.put("drvowner", drvowner);
		map.put("orderstate", orderstate);
		map.put("lcnNo", lcnno);
		map.put("deptcode", deptcode);
		String createStartTime = startTime==""?"":startTime+" 00:00:00";
		String createEndTime = endTime==""?"":endTime+" 23:59:59";
		map.put("createStartTime", createStartTime);
		map.put("createEndTime", createEndTime);	
		map.put("currentNum", 0); //分页从第几条数据开始
		map.put("pageSize", 15); //分页每页的最大数据量
		int policyCount = vilicheBeatchCheckService.searchPolicyCount(map); //查询订单总数
		List<InsuranceDetailsVO> InsuranceDetailsVOs = vilicheBeatchCheckService.vilicheBeatch(map);
		List<String> orderNoList = new ArrayList<String>();
		for(InsuranceDetailsVO insuranceDetailsVO : InsuranceDetailsVOs){
			insuranceDetailsVO.setInterfaceslog(new Interfaceslogs());
			orderNoList.add(insuranceDetailsVO.getOrderno());
		}
		if(orderNoList!=null){
			List<InsuranceDetailsVO> InsuranceDetailsVOs1 = vilicheBeatchCheckService.interfaceslogsQuery(orderNoList);
			for(InsuranceDetailsVO insuranceDetailsVO : InsuranceDetailsVOs){
				for(InsuranceDetailsVO insuranceDetailsVO1 : InsuranceDetailsVOs1){
					if(insuranceDetailsVO.getOrderno().equals(insuranceDetailsVO1.getOrderno())){
						if(insuranceDetailsVO1.getInterfaceslogList().size()>=1){
							insuranceDetailsVO.setInterfaceslog(insuranceDetailsVO1.getInterfaceslogList().get(insuranceDetailsVO1.getInterfaceslogList().size()-1));
						}
					}
				}
			}
		}
		Map<String, Object> map1=new HashMap<String, Object>();
		map1.put("InsuranceDetailsVOs", InsuranceDetailsVOs);
		map1.put("policyCount", policyCount);
        return map1;
	}
	
	@RequestMapping("/CSRPaging.do")
	@ResponseBody
	public List<InsuranceDetailsVO> pagingOrderDetails(HttpServletRequest request){
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String lcnno = request.getParameter("lcnno");
		String orderstate = request.getParameter("orderstate");
		String orderno = request.getParameter("orderno");
		String drvowner = request.getParameter("drvowner");
		String thatPage = request.getParameter("thatPage");
		String deptcode = request.getParameter("deptcode");
		try {
			lcnno=new String(lcnno.getBytes("iso8859-1"),"utf-8");
			drvowner=new String(drvowner.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			 StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			  logger.error("pagingOrderDetails 方法异常"+str);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orderNo", orderno);
		map.put("drvowner", drvowner);
		map.put("orderstate", orderstate);
		map.put("lcnNo", lcnno);
		map.put("deptcode", deptcode);
		String createStartTime = startTime==""?"":startTime+" 00:00:00";
		String createEndTime = endTime==""?"":endTime+" 23:59:59";
		map.put("createStartTime", createStartTime);
		map.put("createEndTime", createEndTime);	
		map.put("currentNum", (Integer.parseInt(thatPage)-1)*15); //分页从第几条数据开始
		map.put("pageSize", 15); //分页每页的最大数据量
		List<InsuranceDetailsVO> InsuranceDetailsVOs = vilicheBeatchCheckService.vilicheBeatch(map);
		List<String> orderNoList = new ArrayList<String>();
		for(InsuranceDetailsVO insuranceDetailsVO : InsuranceDetailsVOs){
			insuranceDetailsVO.setInterfaceslog(new Interfaceslogs());
			orderNoList.add(insuranceDetailsVO.getOrderno());
		}
		if(orderNoList!=null){
			List<InsuranceDetailsVO> InsuranceDetailsVOs1 = vilicheBeatchCheckService.interfaceslogsQuery(orderNoList);
			for(InsuranceDetailsVO insuranceDetailsVO : InsuranceDetailsVOs){
				for(InsuranceDetailsVO insuranceDetailsVO1 : InsuranceDetailsVOs1){
					if(insuranceDetailsVO.getOrderno().equals(insuranceDetailsVO1.getOrderno())){
						if(insuranceDetailsVO1.getInterfaceslogList().size()>=1){
							insuranceDetailsVO.setInterfaceslog(insuranceDetailsVO1.getInterfaceslogList().get(insuranceDetailsVO1.getInterfaceslogList().size()-1));
						}
					}
				}
			}
		}
		return InsuranceDetailsVOs;
	}
	
	@RequestMapping("/requestxml.do")
	@ResponseBody
	public InsuranceDetailsVO requestInterface(HttpServletRequest request, ModelMap modelMap,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String interfaceval=request.getParameter("interfacevalue");
		String requestxml=request.getParameter("requestxml");		
		Userinfor userinfor=new Userinfor();
		InterfaceslogsWithBLOBs interfaceslogsWithBLOBs=new InterfaceslogsWithBLOBs();		
		InsuranceDetailsVO invDetailsVO=new InsuranceDetailsVO();
		invDetailsVO.setUserinfor(userinfor);
		invDetailsVO.setInterfaceslogsWithBLOBs(interfaceslogsWithBLOBs);
		if(interfaceval!=null&&interfaceval!=""){
			if(interfaceval.equals("1")){
				invDetailsVO.getUserinfor().setUserAction("sinosafeConnect");
			}else if(interfaceval.equals("2")){
				invDetailsVO.getUserinfor().setUserAction("Radarinfor");
			}
		}
		if(requestxml!=null&&requestxml!=""){
			invDetailsVO.getInterfaceslogsWithBLOBs().setRequestxml(requestxml);
		}
		InsuranceDetailsVO ins=vilicheBeatchCheckService.getResponsexml(invDetailsVO);
		if(ins.getInterfaceslogsWithBLOBs().getResponsexml()==""){
			ins.getInterfaceslogsWithBLOBs().setResponsexml("请求格式不符,请检查");			
		}
		return ins;		
	}
	
}


