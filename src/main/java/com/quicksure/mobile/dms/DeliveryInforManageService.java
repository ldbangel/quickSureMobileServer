package com.quicksure.mobile.dms;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.dao.DeliveryinforMapper;
import com.quicksure.mobile.dao.DistributionInforMapper;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

@Repository
public class DeliveryInforManageService {
	private static final Logger logger = Logger.getLogger(DeliveryInforManageService.class);
	@Resource
	private  DeliveryinforMapper deliveryinforMapper;
	@Resource
	private BaseinforMapper baseinfroMapper;	
	@Resource
	private DistributionInforMapper distributionInforMapper;
	
	@Transactional
	public void insertDeliveryData(InsuranceDetailsVO insurancedetail){
		//数据插入配送表
		/*int result=deliveryinforMapper.insert(insurancedetail.getDeliveryinfor());
		if(result==1){
			logger.info("ludimb_deliveryinfor : 配送数据插入成功");*/
			Baseinfor baseinfor=new Baseinfor();
			baseinfor.setOrderno(insurancedetail.getBaseinfor().getOrderno());
			/*baseinfor.setDeliveryinforid(deliveryinforMapper.getDevlieryIdByCreateTime());*/
			baseinfor.setOrderstate(90);
			int result1=baseinfroMapper.updateOrderState(baseinfor);
			if(result1==1){
				logger.info("修改baseinfor表状态为配送成功.将订单状态修改为90");
			}else{
				logger.info("修改baseinfor表状态为配送异常.");
			}
		/*}else{
			logger.info("ludimb_deliveryinfor : 配送数据插入失败");
		}*/
	}
	
	//查询配送报文所需信息
	public InsuranceDetailsVO selectDeliveryInfor(Baseinfor baseinfor){
		InsuranceDetailsVO ins =distributionInforMapper.selectDistributionInfor(baseinfor);		
		return ins;
		
	}
	
	
	//调用配送接口前修改订单状态为70
	public int updateOrdersdeliver(InsuranceDetailsVO ins){
		Baseinfor baseinfor=new Baseinfor();
		baseinfor.setOrderno(ins.getBaseinfor().getOrderno());
		baseinfor.setOrderstate(70);
		int result1=baseinfroMapper.updateOrderState(baseinfor);
		if(result1==1){
			logger.info("调用配送接口前，将订单状态修改为70成功.");
		}else{
			logger.info("调用配送接口前，将订单状态修改为70失败.");
		}
		
		return result1;		
	}
	
}
