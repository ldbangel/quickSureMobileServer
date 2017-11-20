package com.quicksure.feiche.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.quicksure.feiche.entity.LudifcBaseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;


public interface NoncarSubmitWriteService {
	//驾意险提核
	public InsuranceDetailsVO  noncarSubmitWrite(LudifcBaseinfor ludifcBaseinfor,HttpServletRequest httpRequest);
	//驾意险首页进入人员信息
	public InsuranceDetailsVO getjiyiaPersonalHomeinfor(LudifcBaseinfor ludifcBaseinfor,HttpServletRequest httprequest);
	//君安保首页跳转
	public InsuranceDetailsVO getPersonalHomeinfor(HttpServletRequest httprequest);	
	//一路平安--首页信息处理
	public InsuranceDetailsVO getVehicleAccHomeInfo(HttpServletRequest httpRequest,LudifcBaseinfor baseinfor);
	//一路平安--提交核保
	public InsuranceDetailsVO VehicleAccSubmitUnder(HttpServletRequest httpRequest,LudifcBaseinfor baseinfor);
	//君安保提交信息
	public InsuranceDetailsVO submitinfor(HttpServletRequest httprequest,LudifcBaseinfor ludifcBaseinfor);
	
	//保单接收更新进数据库
	public InsuranceDetailsVO getpolicyinfor(HttpServletRequest httprequest) throws IOException;

}
