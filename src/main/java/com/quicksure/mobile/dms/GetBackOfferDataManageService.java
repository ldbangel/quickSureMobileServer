package com.quicksure.mobile.dms;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

@Repository
public class GetBackOfferDataManageService {
	
	@Resource
	private BaseinforMapper baseinforMapper;
	
	public List<Baseinfor> searchAndUpdatePolicyStatus(InsuranceDetailsVO insuranceDetailsVO){
		List<Baseinfor> baseinforList = baseinforMapper.getBaseinforByVhlinfor(insuranceDetailsVO.getVhlinfor());
//		int userinforId = insuranceDetails.getUserinfor().getUserid(); //从userinfor获取userid
		//对每个baseinfor绑定userid
		for(int i = 0; i<baseinforList.size(); i++){
			baseinforList.get(i).setUserinforid(7654321);
		}
		baseinforMapper.updateMultiplePolicyStatus(baseinforList);
		
		return baseinforList;
	}
}
