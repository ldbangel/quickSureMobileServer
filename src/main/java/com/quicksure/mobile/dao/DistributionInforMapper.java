package com.quicksure.mobile.dao;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

public interface DistributionInforMapper {
	
	InsuranceDetailsVO selectDistributionInfor(Baseinfor baseinfor);
}