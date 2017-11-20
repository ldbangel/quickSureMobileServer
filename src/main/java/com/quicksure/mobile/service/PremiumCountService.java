package com.quicksure.mobile.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.RadarInfor;
import com.quicksure.mobile.entity.Vhlinfor;

public interface PremiumCountService {
    /**
     * 保费试算业务接口
     * @param vhlinfor
     * @param baseinfor
     * @param coverageinfors
     * @param httprequest
     * @return
     */
	 public InsuranceDetailsVO PremiumCount(Vhlinfor vhlinfor,Baseinfor baseinfor,List<Coverageinfor> coverageinfors,HttpServletRequest httprequest);
	 /**
	  * 根据订单号更新基本信息
	  * @param baseinfor
	  * @param httprequest
	  * @return
	  */
//	 public String saveBaseDate(Baseinfor baseinfor,HttpServletRequest httprequest);
	 
	 public InsuranceDetailsVO savePremiumInfor(ModelMap modelMap,HttpServletRequest httprequest);
	 
	 /**
	  * 雷达试算接口
	  */
	 public InsuranceDetailsVO RadarPremiumCount(InsuranceDetailsVO insuranceDetailsVO,RadarInfor radarinfor,HttpServletRequest httprequest);
}
