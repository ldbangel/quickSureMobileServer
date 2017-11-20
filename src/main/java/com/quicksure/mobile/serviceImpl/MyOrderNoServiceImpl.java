package com.quicksure.mobile.serviceImpl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.dms.MyOrderNoManageService;
import com.quicksure.mobile.dms.PaymentInforDataManageService;
import com.quicksure.mobile.dms.PolicyCancelManageService;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.service.MyOrderNoService;
import com.quicksure.mobile.service.PolicyCancelService;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;
import com.quicksure.mobile.utility.DateFormatUtils;
import com.quicksure.mobile.utility.StringUtils;

@Service("myOrderNoService")
public class MyOrderNoServiceImpl implements MyOrderNoService{
	@Resource
	private MyOrderNoManageService myOrderNoManageService;
	@Resource
	private Sinosafeinterface sinosafeinterface;
	@Resource 
	private PolicyCancelManageService policyCancelManageService;
	@Autowired
	private PaymentInforDataManageService dbDataManageService;
	@Resource
	private PolicyCancelService policyCancelService;
	@Resource
	private BaseinforMapper baseinfroMapper;
	
	private static final Logger logger = Logger.getLogger(MyOrderNoServiceImpl.class);
	
	public List<InsuranceDetailsVO> getOrderinformation(Map<String,Object> params,HttpServletRequest request) {
		HttpSession session=request.getSession();
		Userinfor user = (Userinfor) session.getAttribute("loginUser");
		params.put("userid", user.getUserid());
		params.put("phoneno", user.getUsername());
//		params.put("userid", 5);
		List<InsuranceDetailsVO> listInsurance=myOrderNoManageService.getOrderListinformation(params,user);
		session.setAttribute("listInsurance", listInsurance);
		return listInsurance;
	}
	
	public List<Coverageinfor> getListCoverage(String orderNo){
		List<Coverageinfor> listCoverage=new ArrayList<Coverageinfor>();
		listCoverage = myOrderNoManageService.getListCoverage(orderNo);
		return listCoverage;
	}
	
	public String cancelOrderNo(Baseinfor baseinfor, HttpServletRequest request) {
		HttpSession session=request.getSession();
		List<InsuranceDetailsVO> listInsurance=(List<InsuranceDetailsVO>) session.getAttribute("listInsurance");
		Baseinfor binfo=new Baseinfor();
		for(InsuranceDetailsVO insurance:listInsurance){
			if(insurance.getBaseinfor().getOrderno().equals(baseinfor.getOrderno())&&insurance.getBaseinfor().getUserinforid()==baseinfor.getUserinforid()){
				binfo=insurance.getBaseinfor();
			}
		}
		String msg="";
		int orderState=binfo.getOrderstate();
		if(orderState==10||orderState==20){//待定状态 修改状态为80
			msg=myOrderNoManageService.updateBaseOrderStatu(binfo);
			if(StringUtils.checkStringEmpty(msg)&&"1".equalsIgnoreCase(msg)){//当状态为10或者20的时候，撤销操作只需要更新订单状态，如果返回状态为1，即更新成功
				msg="";
			}
		}else if(orderState==30||orderState==40){//已报价或已下单状态,调撤单接口
			InsuranceDetailsVO insuranceDetails=null;
			if (insuranceDetails == null) {			
				InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
				insuranceDetails=insuranceDetailsVo;
			}
			insuranceDetails.setBaseinfor(binfo);//撤单接口只需baseinfor中一些数据
			//小对象赋值大对象
			insuranceDetails=ProcessData.setObjValuetoinsuranceDetails(binfo,insuranceDetails);
			//给Action后面取模板需要
			insuranceDetails.getUserinfor().setUserAction("PolicyCancel");
	        insuranceDetails = sinosafeinterface.performSinosafeOprations(insuranceDetails);
	        if("10".equalsIgnoreCase(insuranceDetails.getInterfaceslogsWithBLOBs().getInterfacesstatu())){
	        	policyCancelManageService.updateBaseinforOrderState(insuranceDetails);//baseinfor须orderNo有值
	        }else{
//	        	msg="1";//调用撤单接口失败.
	        	logger.info("订单号 :"+binfo.getOrderno()+"取消订单调用撤单接口失败");
	        	msg=insuranceDetails.getUserinfor().getErrorMessage();
	        }
		}else{
			logger.info("订单状态错误.");
		}
		if(msg.equals("")){
			session.setAttribute("checkOrderState", orderState);
		}
		return msg;
	}

	public InsuranceDetailsVO continuePayment(InsuranceDetailsVO insuranceDetails, HttpServletRequest request,HttpServletResponse response) {
		insuranceDetails.getUserinfor().setUserAction("PaymentApplication");//设置action name
		sinosafeinterface.performSinosafeOprations(insuranceDetails);
		
		/* Baseinfor base = dbOperateManageService.selectByApplicationNo(baseinfor); //通过投保单号获取订单号
		insuranceDetails.getBaseinfor().setOrderno(base.getOrderno());*/ //不用获取订单号了    直接可以从session中拿到
		Float taxpremium =Float.parseFloat((insuranceDetails.getBaseinfor().getTaxpremium()==null?0:insuranceDetails.getBaseinfor().getTaxpremium()).toString()); 
		Float jqpremium = Float.parseFloat((insuranceDetails.getBaseinfor().getJqpremium()==null?0:insuranceDetails.getBaseinfor().getJqpremium()).toString());
		Float sypremium = Float.parseFloat((insuranceDetails.getBaseinfor().getSypremium()==null?0:insuranceDetails.getBaseinfor().getSypremium()).toString());
		Float paymentpremium = taxpremium + jqpremium + sypremium; //得到支付的总金额
		insuranceDetails.getPaymentinfor().setPaymentpremium(paymentpremium.toString());
		insuranceDetails.getPaymentinfor().setPaymenttimes(DateFormatUtils.getSystemDate());
		
		if("10".equals(insuranceDetails.getInterfaceslogsWithBLOBs().getInterfacesstatu())){
			dbDataManageService.operatePaymentInfo(insuranceDetails);
		}
		return insuranceDetails;
	}

	/**
	 * 删除支付失效及当前时间大于保单生效时间的30，40的单子
	 */
	@Override
	public List<Baseinfor> getAllBaseinfor() {
		//查询30,40状态的订单
		logger.info("开始查询状态为30和40的订单");
		List<Baseinfor> BaseAll =myOrderNoManageService.getAllBaseinforStart();//获取所有状态为30和40的订单
		if(BaseAll!=null && BaseAll.size()>0){
			//循环出所有30,40状态的订单,然后判断是否过了有效期,过了就删单改状态
			for(int i=0;i<=BaseAll.size();i++){
				Baseinfor baseinfor =new Baseinfor();

				baseinfor = BaseAll.get(i);
				if(baseinfor!=null){
					try {
						SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
						//最后操作的时间  +10天
						 Calendar now = Calendar.getInstance();  
						String updateTime = baseinfor.getUpdatetime();
						Date updateDate=null;
						  if(updateTime!=null){
							   updateDate =formatter.parse(updateTime);
								 now.setTime(updateDate);
								 now.set(Calendar.DATE, now.get(Calendar.DATE) + 10); 
								 updateDate =now.getTime(); 
						  }
							
						//保单生效时间
						String systartDate = baseinfor.getSypolicystartdate();
						Date sysstart = null;
						if(systartDate!=null){
							 sysstart = formatter.parse(systartDate);
						}
							
						//当前时间
						Date dateTime = new Date();
						formatter.format(dateTime);
		                //如果保单生效时间大于当前时间  || 当前系统时间大于最后操作时间10天 则保单状态改为已失效(80);
						if(updateDate!=null && sysstart!=null){
							if(sysstart.getTime()<dateTime.getTime()|| dateTime.getTime()>updateDate.getTime()){
								//删除订单并更改状态
								String jqPolicy = baseinfor.getJqapplicationno();
								String syPolicy = baseinfor.getSyapplicationno();
								if(jqPolicy!=null || syPolicy!=null){
									InsuranceDetailsVO insur = policyCancelService.policyCancelOrder(baseinfor);
								}else{
									baseinfor.setOrderstate(80);// 已撤销状态
									int result = baseinfroMapper.updateOrderState(baseinfor);//如果没有投保单号把状态改为已撤销
								}
								
							}
						}else if(updateDate==null && sysstart!=null){
							if(sysstart.getTime()<dateTime.getTime()){//如果保单生效时间大于当前时间 
								//删除订单并更改状态
								String jqPolicy = baseinfor.getJqapplicationno();
								String syPolicy = baseinfor.getSyapplicationno();
								if(jqPolicy!=null || syPolicy!=null){
									InsuranceDetailsVO insur = policyCancelService.policyCancelOrder(baseinfor);
								}else{
									baseinfor.setOrderstate(80);// 已撤销状态
									int result = baseinfroMapper.updateOrderState(baseinfor);//如果没有投保单号把状态改为已撤销
								}
								
							}
						}
						
						
					} catch (Exception e) {
						StringWriter sw = new StringWriter();  
						 e.printStackTrace(new PrintWriter(sw, true));  
						 String str = sw.toString();
						logger.error("撤单异常"+str+"订单号为:"+baseinfor.getOrderno());
					}
				}
			}
		}
		logger.info("过期订单删单结束！");
		return BaseAll;
	}
	
}
