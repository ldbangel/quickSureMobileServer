package com.quicksure.mobile.serviceImpl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.dms.PolicyCancelManageService;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.PolicyCancelService;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;

@Service("policyCancelService")
public class PolicyCancelServiceImpl implements PolicyCancelService {
	private static final Logger logger = Logger.getLogger(PolicyCancelServiceImpl.class);
	@Resource
	private InsuranceDetailsVO insuranceDetailsVO;
	
	@Resource 
	private PolicyCancelManageService policyCancelManageService;
	
	@Resource
	private BaseinforMapper baseinfroMapper;
	
	@Resource
	private Sinosafeinterface sinosafeinterface;
	
	@Transactional
	public InsuranceDetailsVO policyCancelOrder(Baseinfor baseinfor) {
		InsuranceDetailsVO insuranceDetails=null;
		if (insuranceDetails == null) {			
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetails=insuranceDetailsVo;
		}
		//小对象赋值大对象
		insuranceDetails=ProcessData.setObjValuetoinsuranceDetails(baseinfor,insuranceDetails);
		//给Action后面取模板需要
		insuranceDetails.getUserinfor().setUserAction("PolicyCancel");
		
        insuranceDetails = sinosafeinterface.performSinosafeOprations(insuranceDetails);
        if("10".equalsIgnoreCase(insuranceDetails.getInterfaceslogsWithBLOBs().getInterfacesstatu())){
        	policyCancelManageService.updateBaseinforOrderState(insuranceDetails);//baseinfor须orderNo有值
        }
		return insuranceDetails;
	}

}
