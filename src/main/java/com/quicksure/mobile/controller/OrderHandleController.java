package com.quicksure.mobile.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.OrderHandleService;

//订单操作
@Controller
@RequestMapping("/orderHandle")
public class OrderHandleController {
	private static final Logger logger = Logger.getLogger(OrderHandleController.class);
	@Resource
	private OrderHandleService orderHandleService;
	/**
	 * 撤销订单
	 * @return
	 */
	@RequestMapping("/cancelOrder.do")
	@ResponseBody
	public String cancelOrder(String orderno, HttpServletRequest request){
 		String msg = orderHandleService.cancelOrder(orderno,request);
		return msg;
	}
	
	/**
	 * 撤销商业投保单，较强投保单
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/cancelInsurancePolicy.do")
	
	public @ResponseBody void cancelInsurancePolicy( @RequestBody Baseinfor baseinfor,HttpServletRequest request,HttpServletResponse response) {
		String msg;
		try {
			msg = orderHandleService.cancelInsurancePolicy(baseinfor,request,response);
		
		//设置浏览器不进行Ajax处理页面的缓存
		 response.setContentType("text/html");  
         response.setHeader("Cache-Control", "no-store");  
         response.setHeader("Pragma", "no-cache");  
         response.setDateHeader("Expires", 0);  
		 String flag ="";
		 String failMsg="";
		 String resoponsecode="";
		 String errormessage="";
		 Map<String, Object> map= new HashMap<String, Object>();
		 List<String> listInfo = new ArrayList<String>();  //报文返回错误消息集合
		 Document doc=DocumentHelper.parseText(msg);	//创建dom对象
	 	 Element rootElt=doc.getRootElement();                   //获取根节点
		 Iterator iter=rootElt.elementIterator("BODY");
   while(iter.hasNext()){
		 Element  recordBody = (Element) iter.next();
		 Iterator iters = recordBody.elementIterator("BASE"); 
      while(iters.hasNext()){
		  Element recordBase = (Element) iters.next();
	      for(Iterator iters2 = recordBase.elementIterator("APP_INFO");iters2.hasNext();) {
		     Element recordAppinfo = (Element) iters2.next();
             flag =  recordAppinfo.elementTextTrim("FLAG");           //获取交易状态
             failMsg =  recordAppinfo.elementTextTrim("FAIL_MESSAGE");//获取交易错误信息
        	 logger.info("获取交易状态 :"+flag);
        	 listInfo.add(flag);
        	 listInfo.add(failMsg);
	      }
	     map.put("listInfo", listInfo);
      }
   }
   		//当录入的保单号不存在时候
          Iterator iterHEAD=rootElt.elementIterator("HEAD");
     while(iterHEAD.hasNext()){
    	 Element recordHeadinfor =(Element) iterHEAD.next();
    	 resoponsecode=recordHeadinfor.elementTextTrim("RESPONSECODE");
    	 errormessage=recordHeadinfor.elementTextTrim("ERRORMESSAGE");
    	 
    	 if(resoponsecode.equals("C99999999")){
    		 listInfo.add(resoponsecode);
    	 }
    	 if(errormessage.equals("成功")){
    		 
    	 }else{
    		 listInfo.add(errormessage);
    	 }
    	 
    	 map.put("listInfo", listInfo);
     }
   
        JSONObject jsonObject = JSONObject.fromObject(map);
        response.setContentType("application/json;charset=utf-8");
	     PrintWriter pw=response.getWriter();
	     pw.print(jsonObject);
  
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
	}
	
	/**
	 * CSR订单详情
	 */
	@RequestMapping("/orderDetails.do")
	public String CSROrderDetails(String orderno, HttpServletRequest request,ModelMap model){
		InsuranceDetailsVO insuranceDetailsVO = orderHandleService.getOrderDetailInfor(orderno, request);
		model.addAttribute("insuranceDetailsVO", insuranceDetailsVO);
		return LudiConstants.CSRORDERDETAILS;
	}
	
	/**
	 * CSR继续投保
	 */
	@RequestMapping("/CSRContinueInsure.do")
	public String CSRContinueInsure(String orderno, HttpServletRequest request,ModelMap model){
		InsuranceDetailsVO insuranceDetailsVO = orderHandleService.getOrderDetailInfor(orderno, request);
		model.addAttribute("insuranceDetailsVO", insuranceDetailsVO);
		return LudiConstants.USERMATION;
	}
	
	/**
	 * CSR订单导出excel
	 */
	@RequestMapping("exportExcel.do")
	public void CSRExportExcel(HttpServletRequest request,HttpServletResponse response){
		orderHandleService.exportExcel(request,response);
	}
}
