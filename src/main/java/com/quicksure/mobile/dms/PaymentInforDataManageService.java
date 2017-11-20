package com.quicksure.mobile.dms;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.dao.PaymentinforMapper;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

@Repository
public class PaymentInforDataManageService {
	private static final Logger logger = Logger.getLogger(PaymentInforDataManageService.class);
	
	@Resource
	private BaseinforMapper baseinforMapper;
	@Resource
	private PaymentinforMapper paymentinforMapper;
	
	public void operatePaymentInfo(InsuranceDetailsVO insuranceDetails){
		int paymentInforId = -1;
		
		int isSuccess = paymentinforMapper.insertSelective(insuranceDetails.getPaymentinfor());//插入支付信息
		if(isSuccess>0){
			paymentInforId = insuranceDetails.getPaymentinfor().getPaymentinforid();
			logger.info("支付ID---Payment ID is :"+paymentInforId);
		}
		
		//保单已生成
		if(paymentInforId>-1){
			insuranceDetails.getBaseinfor().setPaymentinforid(paymentInforId);
			insuranceDetails.getBaseinfor().setOrderstate(40); //华安返回支付单号，说明华安已生成保单了，所以保单状态改为--40：调用支付接口成功
			baseinforMapper.updateByPrimaryKeySelective(insuranceDetails.getBaseinfor()); //将paymentinforId更新到baseinfor表
		}
	}
}
