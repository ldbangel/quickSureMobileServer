package com.quicksure.mobile.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Insuranceperinfor;
import com.quicksure.mobile.entity.Vhlinfor;
import com.quicksure.mobile.service.PolicyCancelService;

@Controller
@RequestMapping("/userMation")
public class UserMationContorller {
	
	@Autowired
	private PolicyCancelService policyCancelService;
	
	private static final Logger logger = Logger.getLogger(UserMationContorller.class);
	
	/**
	 * 撤单接口需要交强险保单号、商业险保单号
	 * 
	 * @param baseinfor
	 * @param request
	 * @param httprequest
	 */
	@RequestMapping("/userPreCount.do")
	@ResponseBody
	public InsuranceDetailsVO premiumCount(@RequestBody List<Map<String,Object>> result,Vhlinfor vhlinfor,Baseinfor baseinfor,ModelMap modelMap,HttpServletRequest httprequest)throws ParseException{
		System.out.println(result.size());
		String a=result.get(0).get("vhlinfor").toString();
		System.out.println(a+result.get(1).get("coverageinfors")+result.get(2).get("insuranceperinfor"));
		
//		Vhlinfor vhlin=(Vhlinfor) result.get(0).get("vhlinfor");
//		List<Coverageinfor> coverages=(List<Coverageinfor>) result.get(0).get("coverageinfors");
//		Insuranceperinfor insuran=(Insuranceperinfor) result.get(0).get("insuranceperinfor");
		
		vhlinfor=(Vhlinfor) result.get(0);
		
		List<Coverageinfor> coverageinfors=(List<Coverageinfor>) result.get(1);
		
		Insuranceperinfor insuranceperinfor=(Insuranceperinfor) result.get(2);
		
		InsuranceDetailsVO insuranceVO=new InsuranceDetailsVO();
		insuranceVO.setVhlinfor(vhlinfor);
		return insuranceVO;
	} 
	
}
