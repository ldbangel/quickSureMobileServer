package com.quicksure.feiche.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.feiche.entity.LudifcBaseinfor;
import com.quicksure.feiche.service.NoncarSubmitWriteService;
import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

/**
 * 
* @ClassName: DriverInsureContorller 
* @Description: TODO(驾意险) 
* @author tangwenwu
* @date 2017-4-21 下午2:08:39
 */
@Controller
@RequestMapping("/diverInsure")
public class DriverInsureContorller {
	
	@Autowired
	private NoncarSubmitWriteService noncarSubmitWriteService;
	
	private static final Logger logger = Logger.getLogger(DriverInsureContorller.class);
	/**
	 * 跳转到产品页面 
	 */
	@RequestMapping("/goTopersonInfo.do")
	public String goTopersonInfo(LudifcBaseinfor ludifcBaseinfor,ModelMap modelMap,HttpServletRequest httprequest){
	    InsuranceDetailsVO insuranceDetailsVO= noncarSubmitWriteService
	    		.getjiyiaPersonalHomeinfor(ludifcBaseinfor,httprequest);
		modelMap.addAttribute("insuranceDetailsVO", insuranceDetailsVO);		
		return LudiConstants.JIAYIXIANPERSON;
		
	} 
	/**
	 * 驾意险提核
	 */
	@RequestMapping("/noncarSubmitUnderInfor.do")
	public @ResponseBody InsuranceDetailsVO noncarSubmitUnderInfor(@RequestBody LudifcBaseinfor ludifcBaseinfor,ModelMap modelMap,HttpServletRequest httprequest){
		
		logger.info("非车驾意险提核开始");
		InsuranceDetailsVO insuranceDetails=noncarSubmitWriteService.noncarSubmitWrite(ludifcBaseinfor,httprequest);
		logger.info("驾意险controller提核结束"+insuranceDetails);
		return insuranceDetails;
		
	} 
}
