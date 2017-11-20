package com.quicksure.mobile.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.quicksure.mobile.dms.VilicheBeatchCheckManagerService;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.VilicheBeatchCheckService;
import com.quicksure.mobile.sinosafefactory.RadarinforInterface;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;

@Service("vilicheBeatchCheckService")
public class VilicheBeatchCheckServiceImpl implements VilicheBeatchCheckService{

	@Resource
	private VilicheBeatchCheckManagerService vilicheBeatchCheckManagerService;
	
	@Resource
	private Sinosafeinterface sinosafeinterface;
	
	@Resource
	private RadarinforInterface radarinforInterface;
	
	@Override
	public List<InsuranceDetailsVO> vilicheBeatch(Map<String, Object> map) {
		List<InsuranceDetailsVO> InsuranceDetailsVOs =  vilicheBeatchCheckManagerService.velicheBatchCheck(map);
		return InsuranceDetailsVOs;
	}

	@Override
	public int searchPolicyCount(Map<String, Object> map) {
		return vilicheBeatchCheckManagerService.searchPolicyCount(map);
	}

	@Override
	public List<InsuranceDetailsVO> interfaceslogsQuery(List<String> list) {
		return vilicheBeatchCheckManagerService.searchInterfacelogList(list);
	}

	@Override
	public InsuranceDetailsVO getResponsexml(InsuranceDetailsVO ins) {
		InsuranceDetailsVO insuranceDetailsVO = null;
		try {
			if(ins.getUserinfor().getUserAction().equals("sinosafeConnect")){
				insuranceDetailsVO = sinosafeinterface.getrespoonsexml(ins);
			}else if(ins.getUserinfor().getUserAction().equals("Radarinfor")){
				insuranceDetailsVO=radarinforInterface.getRadaresponseXml(ins);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return insuranceDetailsVO;
	}	
	
	

}
