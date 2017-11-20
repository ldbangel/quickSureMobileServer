package com.quicksure.mobile.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dao.DistributionInforMapper;
import com.quicksure.mobile.dms.DeliveryInforManageService;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Deliveryinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.InterfaceslogsWithBLOBs;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.service.DeliveryInforService;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;
import com.quicksure.mobile.utility.StringUtils;

@Service("deliveryInforService")
public class DeliveryInforServiceImpl implements DeliveryInforService {
	private static final Logger logger = Logger.getLogger(DeliveryInforServiceImpl.class);
	@Resource
	private InsuranceDetailsVO insuranceDetailsVO;
	@Resource
	private DeliveryInforManageService deliveryInforManageService;
	@Resource
	private Sinosafeinterface sinosafeinterface;	
	
	@Transactional
	public InsuranceDetailsVO deliveryToCustomer(Deliveryinfor deliveryinfor,HttpServletRequest httprequest) {
		HttpSession session = httprequest.getSession();
		InsuranceDetailsVO insuranceDetails = null;
		String orderNo=httprequest.getParameter("orderNo");
		if (StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null) {
			insuranceDetails = (InsuranceDetailsVO) session
					.getAttribute(orderNo+"insuranceDetailsVO");
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			insuranceDetails.setVhlinfoList(list);
		}
		if (session.getAttribute("insuranceDetailsVO") != null) {
			insuranceDetails = (InsuranceDetailsVO) session.getAttribute("insuranceDetailsVO");
		}
		if (insuranceDetails == null) {
			InsuranceDetailsVO insuranceDetailsVo = ProcessData.initInsuranceDetailsVO();
			insuranceDetails = insuranceDetailsVo;
		}
		//数据转换,小对象赋值大对象
		insuranceDetails = ProcessData.setObjValuetoinsuranceDetails(deliveryinfor, insuranceDetails);
		// Vhlinfor vhlinfor=new Vhlinfor();
		// vhlinfor.setLcnno("WX5019803602016000387");
		// insuranceDetails.setVhlinfor(vhlinfor);
		// Dptinfor dptinfor=new Dptinfor();
		// dptinfor.setDeptinforid(440300);
		// dptinfor.setName("人民共和国");
		// insuranceDetails.setDptinfor(dptinfor);
		//
		// Baseinfor baseinfor=new Baseinfor();
		// baseinfor.setSyapplicationno("WX5019803802016011429");
		// baseinfor.setSypolicyno("WX1019803802016011193");
		// baseinfor.setOrderno("0001-0000013704");
		// baseinfor.setInsrntname("准报");
		// insuranceDetails.setBaseinfor(baseinfor);
		//
		// Insuranceperinfor insuranceperinfor=new Insuranceperinfor();
		// SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//
		// try {
		// insuranceperinfor.setCreatdatatime(simple.parse("2016-10-18 16:35:06"));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// insuranceDetails.setInsuranceperinfor(insuranceperinfor);
		//
		// Deliveryinfor deliveryinfor=new Deliveryinfor();
		// deliveryinfor.setDeliveryname("准报");
		// deliveryinfor.setDeliveryphone("45415152563");
		// deliveryinfor.setDeliveryaddress("54643");
		// Date date=new Date();
		// deliveryinfor.setDeliverydate(date);
		// deliveryinfor.setCreatedatetime(date);
		// // deliveryinfor.setUpdatetime(new Date());
		// deliveryinfor.setDeliverystate(2);
		// deliveryinfor.setJqsequenceno("");
		// deliveryinfor.setSysequenceno("V0101HAIC440316001476684222898");
		// insuranceDetails.setDeliveryinfor(deliveryinfor);
		insuranceDetails.getUserinfor().setUserAction("DeliveryOrderInfo");
		insuranceDetails = sinosafeinterface.performSinosafeOprations(insuranceDetails);
		if ("10".equalsIgnoreCase(insuranceDetails.getInterfaceslogsWithBLOBs().getInterfacesstatu())) {
			logger.info("调用配送接口返回成功.");
			deliveryInforManageService.insertDeliveryData(insuranceDetails);
		}else{
			logger.info("调用配送接口返回失败.");
		}
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		return insuranceDetails;
	}
	/**
	 *根据保单号或者是订单号查询配送===请求报文所需要的字段
	 */
	@Override
	public InsuranceDetailsVO selectDistributionInfors(
			HttpServletRequest request) {
		String jqapplicationno = request.getParameter("jq_app_ply_no"); //交强险投保单号
		String syapplicationno = request.getParameter("sy_app_ply_no"); //商业险投保单号
		String jqpolicyno = request.getParameter("jq_ply_no"); //交强险保单号
		String sypolicyno = request.getParameter("sy_ply_no"); //商业险保单号
		String orderno=request.getParameter("LudiOrderNo");
		Baseinfor baseinfor = new Baseinfor();
		baseinfor.setSyapplicationno(syapplicationno);
		baseinfor.setJqapplicationno(jqapplicationno);
		baseinfor.setSypolicyno(sypolicyno);
		baseinfor.setJqpolicyno(jqpolicyno);
		baseinfor.setOrderno(orderno);
		InsuranceDetailsVO ins=deliveryInforManageService.selectDeliveryInfor(baseinfor);
		
		return ins;
	}
	/**
	 *调用华安接口执行配送
	 */
	@Override
	public InsuranceDetailsVO deliveryToCustomer(InsuranceDetailsVO insuranceDetails) {
		int result=deliveryInforManageService.updateOrdersdeliver(insuranceDetails);//调用配送接口前修改订单状态的方法
		if(result==1){		
			Userinfor userinfor =new Userinfor();
			InterfaceslogsWithBLOBs interfaceslogsWithBLOBs = new InterfaceslogsWithBLOBs();
			userinfor.setUserAction("DeliveryOrderInfo");
			insuranceDetails.setUserinfor(userinfor);
			insuranceDetails.setInterfaceslogsWithBLOBs(interfaceslogsWithBLOBs);
			insuranceDetails = sinosafeinterface.performSinosafeOprations(insuranceDetails);
			if ("10".equalsIgnoreCase(insuranceDetails.getInterfaceslogsWithBLOBs().getInterfacesstatu())) {
				logger.info("调用配送接口返回成功.");
				deliveryInforManageService.insertDeliveryData(insuranceDetails);
			}else{
				logger.info("调用配送接口返回失败.");
			}
		}
		return null;
	}

}
