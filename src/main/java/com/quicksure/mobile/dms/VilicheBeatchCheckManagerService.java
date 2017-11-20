package com.quicksure.mobile.dms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quicksure.mobile.dao.VelicheBatchCheckMapper;
import com.quicksure.mobile.entity.InsuranceDetailsVO;

@Repository
public class VilicheBeatchCheckManagerService {
	@Autowired
	private VelicheBatchCheckMapper velicheBatchCheckMapper;
	
	public List<InsuranceDetailsVO> velicheBatchCheck(Map<String, Object> map){
		List<InsuranceDetailsVO> insuranceDetailsVOs=velicheBatchCheckMapper.velicheBatchQuery(map);
			return insuranceDetailsVOs;
		}
	
	public int searchPolicyCount(Map<String, Object> map){
		return velicheBatchCheckMapper.searchPolicyCount(map);
	}
	
	public List<InsuranceDetailsVO> searchInterfacelogList(List<String> list){
		return velicheBatchCheckMapper.interfaceslogsQuery(list);
	}
	 
}
