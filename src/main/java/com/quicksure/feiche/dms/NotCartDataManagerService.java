package com.quicksure.feiche.dms;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.quicksure.feiche.dao.LudifcBaseinforMapper;
import com.quicksure.feiche.entity.LudifcBaseinfor;
import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.utility.DateFormatUtils;
import com.quicksure.mobile.utility.StringUtils;
@Repository
public class NotCartDataManagerService {
	private static final Logger logger = Logger
			.getLogger(NotCartDataManagerService.class);
	@Resource
	private BaseinforMapper baseinforMapper;
	@Resource
	private LudifcBaseinforMapper ludifcBaseinforMapper;
	/**
	 * 存储首页的信息生成订单号
	 * 李长立
	 * @param insuranceDetailsVO
	 * @return
	 */
	public InsuranceDetailsVO savefirstScreenData(
			InsuranceDetailsVO insuranceDetailsVO) {
		logger.info("生成订单号开始");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		LudifcBaseinfor baseinfor = null;
		int baseinforresult = 0;
		String orderNo = "";
		String dataTime = DateFormatUtils.getSystemDate();
		if (insuranceDetailsVO != null) {
			baseinfor = insuranceDetailsVO.getLudifcBaseinfor();
		}
		resultMap.put("orderNamePre", "LF");
		resultMap.put("number", 8);
		resultMap.put("orderNo", "@orderNo");
		orderNo = baseinforMapper.getOrderNo(resultMap)+StringUtils.getValidateNo(true,4);// 生成订单号
		if(baseinfor.getOrderno()!=null && baseinfor.getOrderno()!=""){
			logger.info("从第二页回到第一页");
			baseinfor.setOrderstate(20);//设置订单状态
			baseinforresult = ludifcBaseinforMapper.updateByPrimaryKeySelective(baseinfor);
			logger.info("第二页回到第一页重新提交结束"+baseinforresult);
		}else{
			logger.info("生成订单号 " + orderNo);
			if (StringUtils.checkStringEmpty(orderNo)) {
				if (baseinfor != null) {
					baseinfor.setOrderno(orderNo);
				}
				baseinfor.setOrderstate(20);//设置订单状态
				baseinforresult = ludifcBaseinforMapper.insertSelective(baseinfor);
				logger.info("首页信息存进数据库  " + baseinforresult);
				logger.info(" 订单号存入数据库结束 ");
			}	
		}
			
		
		return insuranceDetailsVO;
	}
	
	/**
	 * 非车---录入信息更新进数据库
	 */
	public InsuranceDetailsVO updateFeiBaseinfor(
			InsuranceDetailsVO insuranceDetailsVO) {
		logger.info("开始把非车信息更新进数据库 订单号为"+insuranceDetailsVO.getLudifcBaseinfor().getOrderno());
		LudifcBaseinfor ludifcBaseinfor = insuranceDetailsVO.getLudifcBaseinfor();
		int BaseinforVO = ludifcBaseinforMapper.updateByPrimaryKeySelective(ludifcBaseinfor);
		logger.info("录入非车信息存入数据库结束"+BaseinforVO);
		return insuranceDetailsVO;
	}
}
