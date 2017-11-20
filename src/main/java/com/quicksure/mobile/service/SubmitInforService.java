package com.quicksure.mobile.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.quicksure.mobile.entity.Agreementinfor;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.Deliveryinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Insuranceperinfor;
import com.quicksure.mobile.entity.Taxinfor;

public interface SubmitInforService {

	 /**
	  * 根据录入的提核信息进行组装报文发送
	  * @param insuranceperinfor
	  * @param httprequest
	  * @return
	  */
	 public InsuranceDetailsVO submitUnderwriting(ModelMap modelMap,Deliveryinfor deliveryinfor,Insuranceperinfor insuranceperinfor,HttpServletRequest httprequest);

	 /**
	  * 根据提核返回的信息插入base表
	  * @param insuranceDetailsVO
	  * @return
	  */
//	public String updateByPrimarySelective(InsuranceDetailsVO insuranceDetailsVO);
	 public void saveSubmitInfor(Baseinfor baseinfor); 
	 /**
	  * 提核数据加入
	  * @param insuranceperinfor
	  * @return
	  */
	 public InsuranceDetailsVO AddSubmitInfor(Deliveryinfor deliveryinfor,Insuranceperinfor insuranceperinfor,HttpServletRequest httprequest);
	 
	 
	 public void insertCoverageBatch(List<Coverageinfor> coverageinfors,Baseinfor baseinfor,Taxinfor taxinfor,List<Agreementinfor> agreementinfors,HttpServletRequest request);
	 
	 public InsuranceDetailsVO csrSubmitUnderwriting(Deliveryinfor deliveryinfor,Insuranceperinfor insuranceperinfor,Baseinfor baseinfor,HttpServletRequest httprequest);
	 
	 public InsuranceDetailsVO csrPersonInforAdd(Deliveryinfor deliveryinfor,Insuranceperinfor insuranceperinfor,Baseinfor baseinfor,HttpServletRequest httprequest);
	 
}
