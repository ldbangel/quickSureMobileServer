package com.quicksure.mobile.dms;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.dao.PaymentinforMapper;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Paymentinfor;
import com.quicksure.mobile.utility.DateFormatUtils;

@Repository
public class PaymentCompleteDataManageService {
	private static final Logger logger = Logger.getLogger(PaymentCompleteDataManageService.class);
	
	@Resource
	private BaseinforMapper baseinforMapper;
	
	@Resource
	private PaymentinforMapper paymentinforMapper;
	
	public int udpatePaymentCompleteData(Baseinfor baseinfor){
		logger.info("保单生成---根据投保单号查询订单号！--开始");
		Baseinfor base = baseinforMapper.selectByApplicationNo(baseinfor); //根据投保单号查询订单号
		logger.info("保单生成---订单号："+base.getOrderno());
		String updatetimes=DateFormatUtils.getSystemDate();
		baseinfor.setUpdatetime(updatetimes);//生成保单号之后，添加更新时间
		baseinfor.setOrderstate(60);
		baseinfor.setOrderno(base.getOrderno()); 
		logger.info("保单生成---订单状态："+baseinfor.getOrderstate());
		int result = baseinforMapper.updateByPrimaryKeySelective(baseinfor); //更新支付后baseinfor表的信息
		logger.info("保单生成---更新数据库结果："+result);
		return result;
	}
	
	/**
	 * 支付成功之后，更新payment和baseinfor表数据
	 * @param insuranceDetailsVO
	 * @return
	 */
	public InsuranceDetailsVO updatePaymentAndBaseinfor(InsuranceDetailsVO insuranceDetailsVO){
		logger.info("开始支付成功之后，更新payment和baseinfor表数据 ");
		if(insuranceDetailsVO!=null&&insuranceDetailsVO.getBaseinfor()!=null&&insuranceDetailsVO.getPaymentinfor()!=null){
			String orderNo=insuranceDetailsVO.getBaseinfor().getOrderno();
			logger.info("订单号： "+orderNo);
			String updatetimes=DateFormatUtils.getSystemDate();
			insuranceDetailsVO.getBaseinfor().setUpdatetime(updatetimes);
			logger.info("开始更新订单状态前,订单状态为： "+insuranceDetailsVO.getBaseinfor().getOrderstate());
			if(insuranceDetailsVO.getBaseinfor().getOrderstate()!=60){
				insuranceDetailsVO.getBaseinfor().setOrderstate(50);
			}			
			insuranceDetailsVO.getPaymentinfor().setUpdatetimes(updatetimes);
			logger.info("开始更新paymentinfor表");
			int updatePaymentInfor=paymentinforMapper.updateByPrimaryKeySelective(insuranceDetailsVO.getPaymentinfor());
			logger.info("结束更新paymentinfor表，结果为："+updatePaymentInfor);
			logger.info("开始更新Baseinfor表");
			int updateBaseinfor=baseinforMapper.updateByPrimaryKeySelective(insuranceDetailsVO.getBaseinfor());
			logger.info("结束更新Baseinfor表，结果为："+updateBaseinfor);
		}
		logger.info("结束支付成功之后，更新payment和baseinfor表数据 ");
		return insuranceDetailsVO;
	}

	public Baseinfor getBaseInfor(String orderNo) {

		return baseinforMapper.selectByPrimaryKey(orderNo);
	}

	public Paymentinfor getPaymentinfor(Baseinfor baseinfor) {
		int paymentinforId = baseinfor.getPaymentinforid();
		return paymentinforMapper.selectByPrimaryKey(paymentinforId);
	}
}
