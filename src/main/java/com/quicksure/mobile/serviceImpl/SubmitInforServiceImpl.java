package com.quicksure.mobile.serviceImpl;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dms.SubmitDataManageService;
import com.quicksure.mobile.entity.Agreementinfor;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.Deliveryinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Insuranceperinfor;
import com.quicksure.mobile.entity.Taxinfor;
import com.quicksure.mobile.service.SubmitInforService;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;
import com.quicksure.mobile.utility.StringUtils;

@Service("submitInforService")
public class SubmitInforServiceImpl implements SubmitInforService {
	
	private static final Logger logger = Logger.getLogger(SubmitInforService.class);
	@Resource
	private InsuranceDetailsVO insuranceDetailsVO;
	@Resource
	private Sinosafeinterface sinosafeinterface;
	
	@Resource
	private SubmitDataManageService submitDataManageService;
	
	public InsuranceDetailsVO csrSubmitUnderwriting(
			Deliveryinfor deliveryinfor, Insuranceperinfor insuranceperinfor,
			Baseinfor baseinfor,HttpServletRequest httprequest) {
		InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
		String quotano =httprequest.getParameter("quotano");	
		baseinfor.setQuoteno(quotano);
		insuranceDetailsVo.setBaseinfor(baseinfor);
		insuranceDetailsVo.setInsuranceperinfor(insuranceperinfor);
		insuranceDetailsVo.setDeliveryinfor(deliveryinfor);
		
		insuranceDetailsVo.getUserinfor().setUserAction("SubmitUnderwriting");
		
		insuranceDetailsVo=sinosafeinterface.performSinosafeOprations(insuranceDetailsVo);
		
		return insuranceDetailsVo;
	}
	
	/**
	 * 提核的业务
	 */
	
	public InsuranceDetailsVO submitUnderwriting(ModelMap modelMap,Deliveryinfor deliveryinfor,Insuranceperinfor insuranceperinfor,
			HttpServletRequest httprequest) {
		String orderNo=httprequest.getParameter("orderNo");
		HttpSession session=httprequest.getSession();
		InsuranceDetailsVO insuranceDetails=null;
		if (StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null) {
		 insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo+"insuranceDetailsVO");
		}
		if (insuranceDetails == null) {
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetails=insuranceDetailsVo;
		}
		int insuranceperinforid=0;
		if(insuranceDetails!=null&&insuranceDetails.getInsuranceperinfor()!=null&&insuranceDetails.getInsuranceperinfor().getInsuranceperinforid()!=null){
			insuranceperinforid = insuranceDetails.getInsuranceperinfor().getInsuranceperinforid();
		}
		int deliveryinforid=0;
		if(insuranceDetails!=null&&insuranceDetails.getDeliveryinfor()!=null&&insuranceDetails.getDeliveryinfor().getDeliveryid()!=null){
			deliveryinforid=insuranceDetails.getDeliveryinfor().getDeliveryid();
		}
		if(insuranceperinforid!=0){
			insuranceperinfor.setInsuranceperinforid(insuranceperinforid);
		}
		if(deliveryinforid!=0){
			deliveryinfor.setDeliveryid(deliveryinforid);
		}
		if(insuranceDetails.getBaseinfor()!=null){
			if(insuranceDetails.getBaseinfor().getQuoteno()!=null){
				
			}else{
				String quotano =httprequest.getParameter("quotano");
				insuranceDetails.getBaseinfor().setQuoteno(quotano);
			}
		}
		
		insuranceDetails.setDeliveryinfor(deliveryinfor);
		insuranceDetails.setInsuranceperinfor(insuranceperinfor);
		
		/*if(insuranceDetails.getUserinfor().getPageFlag()<5){
			insuranceDetails.getUserinfor().setPageFlag(5); //首次进入baseinfor页面,将pageFlag状态改为5
		}*/
		insuranceDetails.getUserinfor().setUserAction("SubmitUnderwriting");
		//提核错误方便页面数据填充
		modelMap.addAttribute("insuranceDetailsVO",insuranceDetails);
		//去除提核xml地址属性值中的特殊符号
		String insuraddress="";
		String applicaddress="";
		if(insuranceDetails.getInsuranceperinfor()!=null){
			insuraddress=insuranceDetails.getInsuranceperinfor().getInsureaddress();
			applicaddress=insuranceDetails.getInsuranceperinfor().getApplicationaddress();
			insuranceDetails.getInsuranceperinfor().setApplicationaddress((applicaddress+insuranceDetails.getInsuranceperinfor().getApplicationdetailaddress()).replace(",", "").replace("-", ""));
			insuranceDetails.getInsuranceperinfor().setInsureaddress((insuraddress+insuranceDetails.getInsuranceperinfor().getInsuredetailaddress()).replace(",", "").replace("-", ""));
		}
		//请求过来的数据插入	insuranceperinfor表，
		sinosafeinterface.performSinosafeOprations(insuranceDetails);
		if(insuranceDetails.getInsuranceperinfor()!=null){
			insuranceDetails.getInsuranceperinfor().setApplicationaddress(applicaddress);
			insuranceDetails.getInsuranceperinfor().setInsureaddress(insuraddress);
		}
		
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		session.setAttribute("errorCode", insuranceDetails.getUserinfor().getErrorCode());
		session.setAttribute("errorMessage", insuranceDetails.getUserinfor().getErrorMessage());
		session.setAttribute("appCoypCheckbox", insuranceperinfor.getAppCoypCheckbox());
		session.setAttribute("insureCopyCheckbox", insuranceperinfor.getInsureCopyCheckbox());
		session.setAttribute("deliveryCopyCheckbox", insuranceperinfor.getDeliveryCopyCheckbox());
		return insuranceDetails;
	}
	@Override
	public void saveSubmitInfor(Baseinfor baseinfor) {
//		String orderNo=httprequest.getParameter("orderno");
//		HttpSession session=httprequest.getSession();
//		InsuranceDetailsVO insuranceDetails=null;
//		if (StringUtils.checkStringEmpty(orderNo)
//				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null) {
//		 insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo+"insuranceDetailsVO");
//		}
//		if (insuranceDetails == null) {
//			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
//			insuranceDetails=insuranceDetailsVo;
//		}
//		InsuranceDetailsVO insuranceDetails=submitDataManageService.saveSubmitinfor(baseinfor);
		submitDataManageService.saveSubmitinfor(baseinfor);
//		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
//		return insuranceDetails;
	}

   /**
    * 人员信息增加进人员信息表
    */
	@Override
	public InsuranceDetailsVO AddSubmitInfor(Deliveryinfor deliveryinfor,Insuranceperinfor insuranceperinfor,
			HttpServletRequest httprequest){
		String orderNo=httprequest.getParameter("orderNo");
		String applicationProvinceName = httprequest.getParameter("applicationProvinceName");
		String applicationCityName = httprequest.getParameter("applicationCityName");
		String applicationCountyName = httprequest.getParameter("applicationCountyName");
		String insuredProvinceName = httprequest.getParameter("insuredProvinceName");
		String insuredCityName = httprequest.getParameter("insuredCityName");
		String insuredCountyName = httprequest.getParameter("insuredCountyName");
		String deliveryProvinceName = httprequest.getParameter("deliveryProvinceName");
		String deliveryCityName = httprequest.getParameter("deliveryCityName");
		String deliveryCountyName = httprequest.getParameter("deliveryCountyName");
		HttpSession session=httprequest.getSession();
		InsuranceDetailsVO insuranceDetails=null;
		if (StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null) {
		 insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo+"insuranceDetailsVO");
		}
		if (insuranceDetails == null) {
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetails=insuranceDetailsVo;
		}
		insuranceDetails.getBaseinfor().setApplicationProvinceName(applicationProvinceName);
		insuranceDetails.getBaseinfor().setApplicationCityName(applicationCityName);
		insuranceDetails.getBaseinfor().setApplicationCountyName(applicationCountyName);
		insuranceDetails.getBaseinfor().setInsuredProvinceName(insuredProvinceName);
		insuranceDetails.getBaseinfor().setInsuredCityName(insuredCityName);
		insuranceDetails.getBaseinfor().setInsuredCountyName(insuredCountyName);
		insuranceDetails.getBaseinfor().setDeliveryProvinceName(deliveryProvinceName);
		insuranceDetails.getBaseinfor().setDeliveryCityName(deliveryCityName);
		insuranceDetails.getBaseinfor().setDeliveryCountyName(deliveryCountyName);
		insuranceDetails.getBaseinfor().setLastImplementPage(5);
		int insuranceperinforid = insuranceDetails.getInsuranceperinfor().getInsuranceperinforid();
		int deliveryinforid = insuranceDetails.getDeliveryinfor().getDeliveryid()==null?0:insuranceDetails.getDeliveryinfor().getDeliveryid();
		if(insuranceperinforid!=0){
			insuranceperinfor.setInsuranceperinforid(insuranceperinforid);
		}
		if(deliveryinforid!=0){
			deliveryinfor.setDeliveryid(deliveryinforid);
		}
		insuranceDetails.setDeliveryinfor(deliveryinfor);
		insuranceDetails.setInsuranceperinfor(insuranceperinfor);
		insuranceDetails =submitDataManageService.AddSubmitinfor(httprequest,insuranceDetails);
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		if(insuranceDetails.getBaseinfor()!=null){
        	Baseinfor bb=insuranceDetails.getBaseinfor();
        	if(bb.getTotalPremium()!=null&&!bb.getTotalPremium().equals("")&&!bb.getTotalPremium().equals("0")&&!bb.getTotalPremium().equals("0.00")){
        		bb.setTotalPremium(decimalFormat.format(Float.parseFloat(bb.getTotalPremium())));
        	}
        	if(bb.getJqpremium()!=null&&!bb.getJqpremium().equals("")&&!bb.getJqpremium().equals("0")&&!bb.getJqpremium().equals("0.00")){
        		bb.setJqpremium(decimalFormat.format(Float.parseFloat(bb.getJqpremium())));
        	}
        	insuranceDetails.setBaseinfor(bb);
        }
		
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		session.setAttribute("insuranceDetailsVO", insuranceDetails);
		return insuranceDetails;
	}
	
	public void insertCoverageBatch(List<Coverageinfor> coverageinfors,Baseinfor baseinfor,Taxinfor taxinfor,List<Agreementinfor> agreementinfors,HttpServletRequest request){
		logger.info("开始处理CSR批量险种操作 单号为 : " + baseinfor.getOrderno());
		submitDataManageService.insertCoverageBatch(request,coverageinfors,baseinfor,taxinfor,agreementinfors);
		
	}

	@Override
	public InsuranceDetailsVO csrPersonInforAdd(Deliveryinfor deliveryinfor,
			Insuranceperinfor insuranceperinfor,Baseinfor baseinfor,HttpServletRequest request) {
		logger.info("CSR提核成功 开始新增insuranceperinfor表数据 , 车主姓名为 : " + insuranceperinfor.getOwnersname());
		InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
		Insuranceperinfor existInsurance=submitDataManageService.csrgetInsuranceByorderNo(baseinfor.getOrderno());
		Deliveryinfor existDelivery=submitDataManageService.csrgetDeliveryByorderNo(baseinfor.getOrderno());
		if(existInsurance!=null&&existDelivery!=null){//根据订单号查询人员信息表以及配送表是否存在
			//修改需要主键,这里把查出来的主键赋值给页面传过来的对象
			deliveryinfor.setDeliveryid(existInsurance.getInsuranceperinforid());
			insuranceperinfor.setInsuranceperinforid(existInsurance.getInsuranceperinforid());
			//修改标记
			insuranceDetailsVo.getUserinfor().setPageFlag(5);
		}
		insuranceDetailsVo.setDeliveryinfor(deliveryinfor);
		insuranceDetailsVo.setInsuranceperinfor(insuranceperinfor);
		insuranceDetailsVo.setBaseinfor(baseinfor);
		insuranceDetailsVo=submitDataManageService.AddSubmitinfor(request, insuranceDetailsVo);
		return insuranceDetailsVo;
	}
	
}
