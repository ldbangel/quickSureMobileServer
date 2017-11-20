package com.quicksure.mobile.controller;


import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.PolicyStatusQueryService;

/**
 * 保单查询(单个&批量)
 * @author dongbo
 *
 */
@Controller
@RequestMapping("/policyStatusQuery")
public class PolicyStatusQueryController {
	private static final Logger logger = Logger.getLogger(PolicyStatusQueryController.class);
	@Autowired
	private PolicyStatusQueryService policyStutusQueryService;
	
	@RequestMapping("/policyStatusQuery.do")
	public void policyStatusQuery(Baseinfor baseinfor, HttpServletRequest request, HttpServletResponse response) throws ParseException{
		logger.info("Policy Status Query Start the Syapplicationno is:"+baseinfor.getSyapplicationno());
		logger.info("Policy Status Query Start the Jqapplicationno is:"+baseinfor.getJqapplicationno());
		//单个保单状态查询
		InsuranceDetailsVO insuranceDetailsVO = policyStutusQueryService.policyStatusQuery(baseinfor, request);
		//批量保单状态查询
		policyStutusQueryService.multiplePolicyStatusQuery();
	}
}
