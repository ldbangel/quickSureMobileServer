package com.quicksure.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Vhlinfor;
import com.quicksure.mobile.service.GetBackOfferService;

/**
 * 找回报价
 * @author dongbo
 *
 */
@Controller
@RequestMapping("/getBackOffer")
public class GetBackOfferController {
	private static final Logger logger = Logger.getLogger(GetBackOfferController.class);
	@Autowired
	private GetBackOfferService getBackOfferService;
	
	/**
	 * 根据车主姓名和手机号找回没有完成的订单的报价
	 * @param vhlinfor
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getOrderNoByVhlinfor.do")
	public void getOrderNoByVhlinfor(Vhlinfor vhlinfor, HttpServletRequest request,
			HttpServletResponse response){
		logger.error("找回报价 start!");
		List<Baseinfor> baseinforList = getBackOfferService.getBackOfferByVhlinfor(vhlinfor, request);
		logger.error("找回报价 end!");
	}
}
