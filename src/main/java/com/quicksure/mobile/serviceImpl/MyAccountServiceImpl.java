package com.quicksure.mobile.serviceImpl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.dao.CoverageinforMapper;
import com.quicksure.mobile.dao.MyAccountMapper;
import com.quicksure.mobile.dao.WechatBindMapper;
import com.quicksure.mobile.dms.PaymentInforDataManageService;
import com.quicksure.mobile.dms.PolicyCancelManageService;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.InterfaceslogsWithBLOBs;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.entity.WechatBind;
import com.quicksure.mobile.service.MyAccountService;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;
import com.quicksure.mobile.utility.DateFormatUtils;

@Service("myAccountService")
public class MyAccountServiceImpl implements MyAccountService {
	private static final Logger logger = Logger.getLogger(MyAccountServiceImpl.class); 
	@Autowired
	private MyAccountMapper myAccountMapper;
	@Resource
	private BaseinforMapper baseinforMapper;
	@Resource
	private CoverageinforMapper coverageinforMapper;
	@Autowired
	private PaymentInforDataManageService dbDataManageService;
	@Resource
	private Sinosafeinterface sinosafeinterface;
	@Resource 
	private PolicyCancelManageService policyCancelManageService;
	@Resource
	private  WechatBindMapper wechatBindMapper;
	
	/**
	 * myAccount初始化
	 */
	public Map<String, Object> getMyAccountInitInfor(HttpServletRequest request){
		String content = request.getParameter("content");
		if(content!=null && !"".equals(content)){
			try {
				content=new String(content.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				StringWriter sw = new StringWriter();  
				 e.printStackTrace(new PrintWriter(sw, true));  
				 String str = sw.toString();
				logger.error("getMyAccountInitInfor 方法编码异常"+str);
			}
		}
		HttpSession session=request.getSession();
		Userinfor user = (Userinfor) session.getAttribute("loginUser");
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> result = new HashMap<String, Object>();
		int bindUserId = 0 ;
		//开始获取用户绑定用户的数据
		if(user!=null){
			WechatBind wechatBind=null;
			Integer userType=user.getUsertype();
			if(userType!=null&&userType==3){//微信端获取我的订单数据
				int wechatUserId=user.getUserid();
			     wechatBind	= wechatBindMapper.selectByWechatUserId(wechatUserId);
			 if(wechatBind!=null&&wechatBind.getPhoneuserid()!=null){
				 bindUserId = wechatBind.getPhoneuserid();
			 }
			}else if(userType!=null){//移动端获取我的订单数据
				int phoneUserId=user.getUserid();
				 wechatBind = wechatBindMapper.selectByphoneUserId(phoneUserId);
				 if(wechatBind!=null&&wechatBind.getWechatuserid()!=null){
					 bindUserId = wechatBind.getWechatuserid();
				 }
			}
		}
		map.put("userid", user.getUserid());
		map.put("bindUserId", bindUserId);
		map.put("content", content);
		int count1 = myAccountMapper.getMyOrdersCount1(map);
		int count2 = myAccountMapper.getMyOrdersCount2(map);
		int count3 = myAccountMapper.getMyOrdersCount3(map);
		int count4 = myAccountMapper.getMyOrdersCount4(map);
		result.put("count1", count1);
		result.put("count2", count2);
		result.put("count3", count3);
		result.put("count4", count4);
		List<InsuranceDetailsVO> insuranceDetailsVOs1 = myAccountMapper.getMyOrdersTopTen1(map);
		List<InsuranceDetailsVO> insuranceDetailsVOs2 = myAccountMapper.getMyOrdersTopTen2(map);
		List<InsuranceDetailsVO> insuranceDetailsVOs3 = myAccountMapper.getMyOrdersTopTen3(map);
		List<InsuranceDetailsVO> insuranceDetailsVOs4 = myAccountMapper.getMyOrdersTopTen4(map);
		List<List<InsuranceDetailsVO>> insuranceDetailsVOsList = new ArrayList<List<InsuranceDetailsVO>>();
		insuranceDetailsVOsList.add(insuranceDetailsVOs1);
		insuranceDetailsVOsList.add(insuranceDetailsVOs2);
		insuranceDetailsVOsList.add(insuranceDetailsVOs3);
		insuranceDetailsVOsList.add(insuranceDetailsVOs4);
		for (List<InsuranceDetailsVO> insuranceDetailsVOs:insuranceDetailsVOsList) {
			for(InsuranceDetailsVO insuranceDetailsVO:insuranceDetailsVOs){
				if(insuranceDetailsVO!=null){
					insuranceDetailsVO.getVhlinfor().setCodeName(ProcessData.brandNameToCode(insuranceDetailsVO.getVhlinfor().getBrandName()==null?"":insuranceDetailsVO.getVhlinfor().getBrandName()));
					insuranceDetailsVO.getVhlinfor().setDrvowner(insuranceDetailsVO.getVhlinfor().getDrvowner()==null?"":insuranceDetailsVO.getVhlinfor().getDrvowner());
					String systartdate=insuranceDetailsVO.getBaseinfor().getSypolicystartdate();
					String syenddate=insuranceDetailsVO.getBaseinfor().getSypolicyenddate();
					String jqstartdate=insuranceDetailsVO.getBaseinfor().getJqpolicystartdate();
					String jqenddate=insuranceDetailsVO.getBaseinfor().getJqpolicyenddate();
					
					insuranceDetailsVO.getBaseinfor().setSypolicystartdate(systartdate==null?"":systartdate.substring(0, 10));
					insuranceDetailsVO.getBaseinfor().setSypolicyenddate(syenddate==null?"":syenddate.substring(0, 10));
					insuranceDetailsVO.getBaseinfor().setJqpolicystartdate(jqstartdate==null?"":jqstartdate.substring(0, 10));
					insuranceDetailsVO.getBaseinfor().setJqpolicyenddate(jqenddate==null?"":jqenddate.substring(0, 10));
				}
				if(insuranceDetailsVO.getBaseinfor().getTotalPremium() == null){
					insuranceDetailsVO.getBaseinfor().setTotalPremium("0");
				}
			}
		}
		
		result.put("insuranceDetailsVOs1", insuranceDetailsVOs1);
		result.put("insuranceDetailsVOs2", insuranceDetailsVOs2);
		result.put("insuranceDetailsVOs3", insuranceDetailsVOs3);
		result.put("insuranceDetailsVOs4", insuranceDetailsVOs4);
		return result;
	}
	/**
	 *非车订单查询
	 */
	@Override
	public Map<String, Object> getfeicheMyAccountInitInfor(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		int bindUserId = 0 ;
		if(content!=null && !"".equals(content)){
			try {
				content=new String(content.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				StringWriter sw = new StringWriter();  
				 e.printStackTrace(new PrintWriter(sw, true));  
				 String str = sw.toString();
				logger.error("getMyAccountInitInfor 方法编码异常"+str);
			}
		}
		HttpSession session=request.getSession();
		Userinfor user = (Userinfor) session.getAttribute("loginUser");		
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> result = new HashMap<String, Object>();	
		//开始获取用户绑定用户的数据
				if(user!=null){
					WechatBind wechatBind=null;
					Integer userType=user.getUsertype();
					if(userType!=null&&userType==3){//微信端获取我的订单数据
						int wechatUserId=user.getUserid();
					     wechatBind	= wechatBindMapper.selectByWechatUserId(wechatUserId);
					 if(wechatBind!=null&&wechatBind.getPhoneuserid()!=null){
						 bindUserId = wechatBind.getPhoneuserid();
					 }
					}else if(userType!=null){//移动端获取我的订单数据
						int phoneUserId=user.getUserid();
						 wechatBind = wechatBindMapper.selectByphoneUserId(phoneUserId);
						 if(wechatBind!=null&&wechatBind.getWechatuserid()!=null){
							 bindUserId = wechatBind.getWechatuserid();
						 }
					}
				}
		map.put("userid", user.getUserid());
		map.put("bindUserId", bindUserId);
		map.put("content", content);
		/*List<InsuranceDetailsVO> insuranceDetailsVOs  = myAccountMapper.getfeicheMyOrdersCount1(map);
		result.put("insuranceDetailsVOs", insuranceDetailsVOs);*/
		int countTotal = myAccountMapper.getfeicheMyOrdersCount1(map);
		result.put("countTotal", countTotal);
		
		return result;
	}

	
	/**
	 * 获取每页展示的订单数据
	 */
	public Map<String, Object> getMyOrdersInfor(HttpServletRequest request) {
		String thatPage = request.getParameter("curPage");
		String index = request.getParameter("flag");
		String content = request.getParameter("content");
		if(content!=null && !"".equals(content)){
			try {
				content=new String(content.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				StringWriter sw = new StringWriter();  
				 e.printStackTrace(new PrintWriter(sw, true));  
				 String str = sw.toString();
				logger.error("获取每页展示的订单数据 方法编码异常"+str);
			}
		}
		int thatpage = thatPage==null?1:Integer.parseInt(thatPage);
		int tabIndex = index==null?0:Integer.parseInt(index);
		HttpSession session=request.getSession();
		Userinfor user = (Userinfor) session.getAttribute("loginUser");
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> result = new HashMap<String, Object>();
		int bindUserId = 0 ;
		//开始获取用户绑定用户的数据
		if(user!=null){
			WechatBind wechatBind=null;
			Integer userType=user.getUsertype();
			if(userType!=null&&userType==3){//微信端获取我的订单数据
				int wechatUserId=user.getUserid();
			     wechatBind	= wechatBindMapper.selectByWechatUserId(wechatUserId);
			 if(wechatBind!=null&&wechatBind.getPhoneuserid()!=null){
				 bindUserId = wechatBind.getPhoneuserid();
			 }
			}else if(userType!=null){//移动端获取我的订单数据
				int phoneUserId=user.getUserid();
				 wechatBind = wechatBindMapper.selectByphoneUserId(phoneUserId);
				 if(wechatBind!=null&&wechatBind.getWechatuserid()!=null){
					 bindUserId = wechatBind.getWechatuserid();
				 }
			}
		}
		map.put("bindUserId", bindUserId);
		map.put("userid", user.getUserid());
		map.put("currentNum", (thatpage-1)*10); //要查询的起始数据条数
		map.put("pageSize", 10); //每页展示的最多数据条数
		map.put("content", content);
		int count1 = myAccountMapper.getMyOrdersCount1(map);
		int count2 = myAccountMapper.getMyOrdersCount2(map);
		int count3 = myAccountMapper.getMyOrdersCount3(map);
		int count4 = myAccountMapper.getMyOrdersCount4(map);
		result.put("count1", count1);
		result.put("count2", count2);
		result.put("count3", count3);
		result.put("count4", count4);
		map.put("index", tabIndex); //选择的tab:0表示待支付、1表示已支付、2表示暂存、3表示已撤销
		List<InsuranceDetailsVO> insuranceDetailsVOs = myAccountMapper.getMyOrders(map);
		for(InsuranceDetailsVO insuranceDetailsVO:insuranceDetailsVOs){
			if(insuranceDetailsVO!=null){
				insuranceDetailsVO.getVhlinfor().setCodeName(ProcessData.brandNameToCode(insuranceDetailsVO.getVhlinfor().getBrandName()==null?"":insuranceDetailsVO.getVhlinfor().getBrandName()));
				insuranceDetailsVO.getVhlinfor().setDrvowner(insuranceDetailsVO.getVhlinfor().getDrvowner()==null?"":insuranceDetailsVO.getVhlinfor().getDrvowner());
				String systartdate=insuranceDetailsVO.getBaseinfor().getSypolicystartdate();
				String syenddate=insuranceDetailsVO.getBaseinfor().getSypolicyenddate();
				String jqstartdate=insuranceDetailsVO.getBaseinfor().getJqpolicystartdate();
				String jqenddate=insuranceDetailsVO.getBaseinfor().getJqpolicyenddate();
				
				insuranceDetailsVO.getBaseinfor().setSypolicystartdate(systartdate==null?"":systartdate.substring(0, 10));
				insuranceDetailsVO.getBaseinfor().setSypolicyenddate(syenddate==null?"":syenddate.substring(0, 10));
				insuranceDetailsVO.getBaseinfor().setJqpolicystartdate(jqstartdate==null?"":jqstartdate.substring(0, 10));
				insuranceDetailsVO.getBaseinfor().setJqpolicyenddate(jqenddate==null?"":jqenddate.substring(0, 10));
			}
			if(insuranceDetailsVO.getBaseinfor().getTotalPremium() == null){
				insuranceDetailsVO.getBaseinfor().setTotalPremium("0");
			}
		}
		result.put("insuranceDetailsVOs", insuranceDetailsVOs);
		return result;
	}
	
	/**
	 * 取消订单
	 */
	public String cancelOrder(String orderNo, HttpServletRequest request) {
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
	 * 继续支付
	 */
	public String continuePay(String orderNo, HttpServletRequest request) {
		InsuranceDetailsVO insuranceDetailsVO = myAccountMapper.getInsuranceByOrderNo(orderNo);
		InterfaceslogsWithBLOBs interfaceslogsWithBLOBs = new InterfaceslogsWithBLOBs();    //存报文信息
		insuranceDetailsVO.setInterfaceslogsWithBLOBs(interfaceslogsWithBLOBs);
		String url = insuranceDetailsVO.getPaymentinfor().getPaymenturl();
		Baseinfor baseinfor = insuranceDetailsVO.getBaseinfor();
		if(baseinfor.getOrderstate()==30&&(url==null||url.equals(""))){//状态等于30,未生成url,调用支付接口生成URL
			Userinfor userinfor = new Userinfor();
			insuranceDetailsVO.setUserinfor(userinfor);
			insuranceDetailsVO.getUserinfor().setUserAction("PaymentApplication");//设置action name
			sinosafeinterface.performSinosafeOprations(insuranceDetailsVO);
			
			Float taxpremium =Float.parseFloat((insuranceDetailsVO.getBaseinfor().getTaxpremium()==null?0:insuranceDetailsVO.getBaseinfor().getTaxpremium()).toString()); 
			Float jqpremium = Float.parseFloat((insuranceDetailsVO.getBaseinfor().getJqpremium()==null?0:insuranceDetailsVO.getBaseinfor().getJqpremium()).toString());
			Float sypremium = Float.parseFloat((insuranceDetailsVO.getBaseinfor().getSypremium()==null?0:insuranceDetailsVO.getBaseinfor().getSypremium()).toString());
			Float paymentpremium = taxpremium + jqpremium + sypremium; //得到支付的总金额
			insuranceDetailsVO.getPaymentinfor().setPaymentpremium(paymentpremium.toString());
			insuranceDetailsVO.getPaymentinfor().setPaymenttimes(DateFormatUtils.getSystemDate());
			
			if("10".equals(insuranceDetailsVO.getInterfaceslogsWithBLOBs().getInterfacesstatu())){
				dbDataManageService.operatePaymentInfo(insuranceDetailsVO);
			}
			url=insuranceDetailsVO.getPaymentinfor().getPaymenturl();
		}
		logger.info("URL:"+url);
		return url;
	}

	/**
	 * 继续投保
	 * 暂存状态的继续投保操作(10跳转到车辆信息页面,20跳转到险种页面) 
	 */
	public String continueInsure(String orderNo, HttpServletRequest request) {
		String path="";
		HttpSession session=request.getSession();
		InsuranceDetailsVO insuranceDetailsVO = myAccountMapper.getInsuranceByOrderNo(orderNo);
		List<Coverageinfor> listCoverage=coverageinforMapper.getListCoverage(orderNo);
		insuranceDetailsVO.setCoverageinfors(listCoverage);
		InterfaceslogsWithBLOBs interfaceslogsWithBLOBs = new InterfaceslogsWithBLOBs();
		insuranceDetailsVO.setInterfaceslogsWithBLOBs(interfaceslogsWithBLOBs);
     	Baseinfor baseinfor = insuranceDetailsVO.getBaseinfor();
		session.setAttribute(orderNo+"insuranceDetailsVO", insuranceDetailsVO);
		if(baseinfor.getOrderstate()==10 //状态为10，并且没有身份证号，判断此单最后操作为首页提交表单成功了
				&&(insuranceDetailsVO.getVhlinfor().getCertificateno()==null
				||insuranceDetailsVO.getVhlinfor().getCertificateno()=="")){
			insuranceDetailsVO.getUserinfor().setPageFlag(2);
		}else if(baseinfor.getOrderstate()==10  //状态为10，并且有身份证号码，就判断为此单最后操作为车辆信息页面提交表单成功了
				&&insuranceDetailsVO.getVhlinfor().getCertificateno()!=null
				&&insuranceDetailsVO.getVhlinfor().getCertificateno()!=""){
			 insuranceDetailsVO.getUserinfor().setPageFlag(3);
		}else if(baseinfor.getOrderstate()==20){ //判断此单最后操作为保险页面确认报价成功了
			insuranceDetailsVO.getUserinfor().setPageFlag(4);
		}
		/**else if(baseinfor.getOrderstate()==30){ //判断此单最后操作为人员信息页面提核成功了
			insuranceDetailsVO.getUserinfor().setPageFlag(4);
		}
		**/
		path = "/vehicleInfor/backToVehicleScreen.do?orderNo="+orderNo;
		return path;
	}

	/**
	 * 获取订单详情
	 */
	public InsuranceDetailsVO showOrderDetail(String orderNo, HttpServletRequest request) {
		InsuranceDetailsVO insuranceDetailsVO = myAccountMapper.getInsuranceByOrderNo(orderNo);
		List<Coverageinfor> listCoverage=coverageinforMapper.getListCoverage(orderNo);
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		for(Coverageinfor cover:listCoverage){
			if(cover.getSuminsured()!=null&&!cover.getSuminsured().equals("")&&!cover.getSuminsured().equals("0")
		        	   &&!cover.getSuminsured().equals("0.00")){
		        		cover.setSuminsured(decimalFormat.format(Float.parseFloat(cover.getSuminsured())));
		        	}
		        	if(cover.getPremium()!=null&&!cover.getPremium().equals("")&&!cover.getPremium().equals("0")&&!cover.getPremium().equals("0.00")){
		        		cover.setPremium(decimalFormat.format(Float.parseFloat(cover.getPremium())));
		        	}
		        	if(cover.getNyl12()!=null&&!cover.getNyl12().equals("")&&!cover.getNyl12().equals("0")&&!cover.getNyl12().equals("0.00")){
		        		cover.setNyl12(decimalFormat.format(Float.parseFloat(cover.getNyl12())));
		        	}
		}
		insuranceDetailsVO.setCoverageinfors(listCoverage);
		return insuranceDetailsVO;
	}

	
}
