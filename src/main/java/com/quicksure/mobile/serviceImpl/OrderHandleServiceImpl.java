package com.quicksure.mobile.serviceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.dao.CoverageinforMapper;
import com.quicksure.mobile.dao.MyAccountMapper;
import com.quicksure.mobile.dao.VelicheBatchCheckMapper;
import com.quicksure.mobile.dms.PolicyCancelManageService;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.OrderHandleService;
import com.quicksure.mobile.sinosafefactory.SinosafeConnect;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;
import com.quicksure.mobile.utility.ExcelUtil;

@Service("orderHandleService")
public class OrderHandleServiceImpl implements OrderHandleService {
	private static final Logger logger = Logger.getLogger(OrderHandleServiceImpl.class); 
	@Resource
	private BaseinforMapper baseinforMapper;
	@Resource
	private MyAccountMapper myAccountMapper;
	@Resource
	private Sinosafeinterface sinosafeinterface;
	@Resource
	private SinosafeConnect sinosafeConnect;
	@Resource
	private CoverageinforMapper coverageinforMapper;
	@Resource 
	private PolicyCancelManageService policyCancelManageService;
	@Resource
	private VelicheBatchCheckMapper velicheBatchCheckMapper;
	
	/**
	 * 撤销保单
	 */
	public String cancelOrder(String orderNo,HttpServletRequest request) {
		String msg = "";
		Baseinfor baseinfor = baseinforMapper.selectByPrimaryKey(orderNo);//根据orderNo查询出订单
		int orderstate = baseinfor.getOrderstate();
		if(orderstate==10 || orderstate==20){ //暂存状态的订单撤销只需要更新我们数据库就OK
			baseinfor.setOrderstate(80);//状态为关闭
			int result=baseinforMapper.updateOrderState(baseinfor);
			if(result==1){
				msg="success";
				logger.info("取消订单修改状态成功 订单号为 :" +orderNo);
			}
		}else if(orderstate == 30 || orderstate == 40){ //待支付状态的订单需要经过华安的接口才能撤销
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetailsVo.setBaseinfor(baseinfor);
			insuranceDetailsVo.getUserinfor().setUserAction("PolicyCancel");
			insuranceDetailsVo = sinosafeinterface.performSinosafeOprations(insuranceDetailsVo);
	        if("10".equalsIgnoreCase(insuranceDetailsVo.getInterfaceslogsWithBLOBs().getInterfacesstatu())){
	        	msg = policyCancelManageService.updateBaseinforOrderState(insuranceDetailsVo);//baseinfor须orderNo有值
	        }else{
	        	logger.info("订单号 :"+baseinfor.getOrderno()+"取消订单调用撤单接口失败");
	        	msg=insuranceDetailsVo.getUserinfor().getErrorMessage();
	        }
		}
		return msg;
	}
	
	/**
	 * 撤销商业投保，较强投保
	 */
	public String cancelInsurancePolicy(Baseinfor baseinfor,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String requestXml = "";		
		String responseXml ="";
	
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetailsVo.setBaseinfor(baseinfor);
			insuranceDetailsVo.getUserinfor().setUserAction("PolicyCancel");
			//insuranceDetailsVo = sinosafeinterface.performSinosafeOprations(insuranceDetailsVo) ;
			requestXml = ProcessData.handleRequest(insuranceDetailsVo);
			/*requestXml=insuranceDetailsVo.getInterfaceslogsWithBLOBs().getRequestxml();*/
			/*responseXml=insuranceDetailsVo.getInterfaceslogsWithBLOBs().getResponsexml();*/
			responseXml=sinosafeConnect.sinosafeConnection(requestXml,"PolicyCancel");
						
			return responseXml;
		}

	/**
	 * CSR获取订单详情信息
	 */
	public InsuranceDetailsVO getOrderDetailInfor(String orderNo,
			HttpServletRequest request) {
		InsuranceDetailsVO insuranceDetailsVO = myAccountMapper.getInsuranceByOrderNo(orderNo);
		List<Coverageinfor> coverageinfors = coverageinforMapper.getListCoverage(orderNo);
		insuranceDetailsVO.setCoverageinfors(coverageinfors);
		String jqstarttime = null;
		String systarttime = null;
		if(insuranceDetailsVO.getBaseinfor()!=null && insuranceDetailsVO.getBaseinfor().getJqpolicystartdate()!=null){
			jqstarttime = insuranceDetailsVO.getBaseinfor().getJqpolicystartdate().substring(0, 10);
		}
		if(insuranceDetailsVO.getBaseinfor()!=null && insuranceDetailsVO.getBaseinfor().getSypolicystartdate()!=null){
			systarttime = insuranceDetailsVO.getBaseinfor().getSypolicystartdate().substring(0, 10);
		}
		insuranceDetailsVO.getBaseinfor().setJqpolicystartdate(jqstarttime);
		insuranceDetailsVO.getBaseinfor().setSypolicystartdate(systarttime);
		return insuranceDetailsVO;
	}

	/**
	 * CSR继续投保
	 */
	public String continueInsure(String orderNo, HttpServletRequest request) {
		return null;
	}
	
	/**
	 * CSR订单导出Excel
	 */
	public String exportExcel(HttpServletRequest request,HttpServletResponse response){
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String lcnno = request.getParameter("lcnno");
		String orderstate = request.getParameter("orderstate");
		String orderno = request.getParameter("orderno");
		String drvowner = request.getParameter("drvowner");
		String deptcode = request.getParameter("deptcode");
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
		List<InsuranceDetailsVO> InsuranceDetailsVOs = velicheBatchCheckMapper.CSRExportExcel(map);
		//导出excel表格--定义表头字段
		String[] headers = {"创建时间","订单号","商业险投保单号","交强险投保单号","商业险保单号","交强险保单号","分公司",
							"中支机构","支公司","车主姓名","车牌号码","起保日期","销售状态","商业险保费总额","交强险保费总额",
							"车船税","总保费","车辆损失险保费","第三者责任险保费","全车盗抢险保费","司机座位责任险保费",
							"乘客座位责任险保费","玻璃单独破碎险保费","自燃损失险保费","不计免赔险保费","支付方式","最后更新时间","业务员代码"};
		try {
			 ExcelUtil.excelUtil(response,headers,InsuranceDetailsVOs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
