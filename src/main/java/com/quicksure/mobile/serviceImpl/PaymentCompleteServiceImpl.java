package com.quicksure.mobile.serviceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dms.PaymentCompleteDataManageService;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Paymentinfor;
import com.quicksure.mobile.service.PaymentCompleteService;

/**
 * 支付完成服务service
 * @author dongbo
 *
 */
@Service("paymentCompleteService")
public class PaymentCompleteServiceImpl implements PaymentCompleteService {
	private static final Logger logger = Logger.getLogger(PaymentCompleteServiceImpl.class);
	@Autowired
	private PaymentCompleteDataManageService dbDataManageService;
	
	public boolean updatePaymentInfor(HttpServletRequest request,
			HttpServletResponse response) {
		boolean isSuccess = false;	
		
		String resultFromNewSnfForPayment = request.getParameter("result"); //0是成功，1是失败
		String jqapplicationno = request.getParameter("jq_app_ply_no"); //交强险投保单号
		String syapplicationno = request.getParameter("sy_app_ply_no"); //商业险投保单号
		String jqpolicyno = request.getParameter("jq_ply_no"); //交强险保单号
		String sypolicyno = request.getParameter("sy_ply_no"); //商业险保单号
		String orderNoforPayment = request.getParameter("pay_app_no"); //支付单号
		
		logger.info("生成保单---状态result为："+resultFromNewSnfForPayment+"   交强险投保单号："+jqapplicationno+"   商业险投保单号:"
				+syapplicationno+"   交强险保单号:"+jqpolicyno+"   商业险保单号"+sypolicyno+"  支付单号:"+orderNoforPayment);
		
		Baseinfor baseinfor = new Baseinfor();
		baseinfor.setSyapplicationno(syapplicationno);
		baseinfor.setJqapplicationno(jqapplicationno);
		baseinfor.setSypolicyno(sypolicyno);
		baseinfor.setJqpolicyno(jqpolicyno);
		//更新支付方式，10线下，20线上
		if("08".equals(orderNoforPayment.substring(0, 2))){
			baseinfor.setPaymentMethod(10);
		}else{
			baseinfor.setPaymentMethod(20);
		}
		
		logger.info("保单生成----更新保单号和投保单号---开始！");
		int result = dbDataManageService.udpatePaymentCompleteData(baseinfor);
		
		if(result > 0){
			isSuccess = true;
		}
		
		return isSuccess;
	}
	/**
	 * 支付成功之后，处理支付返回的数据
	 * 孙小东
	 * @param request
	 * @param response
	 * @return
	 */
	
	public InsuranceDetailsVO processPaymentSuccessData(
			HttpServletRequest request, HttpServletResponse response) {
		InsuranceDetailsVO insuranceDetailsVO = null;
		Paymentinfor paymentinfor = null;
		String orderNo = request.getParameter("LudiOrderNo");
		String orderAmount = request.getParameter("orderAmount");
		String orderTime = request.getParameter("orderTime");
		String orderId = request.getParameter("orderId");
	    Baseinfor baseinfor=dbDataManageService.getBaseInfor(orderNo);
	    paymentinfor=dbDataManageService.getPaymentinfor(baseinfor);
	    insuranceDetailsVO =ProcessData.initInsuranceDetailsVO();
	    insuranceDetailsVO.setBaseinfor(baseinfor);
	    insuranceDetailsVO.setPaymentinfor(paymentinfor);
		if (insuranceDetailsVO != null
				&& insuranceDetailsVO.getPaymentinfor() != null) {
			paymentinfor = insuranceDetailsVO.getPaymentinfor();
			if (insuranceDetailsVO.getPaymentinfor().getPaymentno() != null) {
				//手机页面支付完成 没有订单信息展示  
				/**	&& orderId.equalsIgnoreCase(insuranceDetailsVO
							.getPaymentinfor().getPaymentno())) {
				if (StringUtils.checkStringEmpty(orderAmount)) {
					int splitLenght = orderAmount.length() - 2;
					orderAmount = orderAmount.substring(0, splitLenght)
							+ "."
							+ orderAmount.substring(splitLenght,
									orderAmount.length());
					paymentinfor.setPaymentpremium(orderAmount);
				}
				if (StringUtils.checkStringEmpty(orderTime)) {
					orderTime = DateFormatUtils.tranDate(orderTime,
							"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
					paymentinfor.setPaymenttimes(orderTime);
				}**/
			
				paymentinfor.setPaymentstate(20);
				dbDataManageService
						.updatePaymentAndBaseinfor(insuranceDetailsVO);

			}
		}
		return insuranceDetailsVO;

	}
	
}
