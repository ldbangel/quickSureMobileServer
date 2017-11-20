package com.quicksure.mobile.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dms.PolicyStatusDataManageService;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.service.PolicyStatusQueryService;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;
import com.quicksure.mobile.utility.DateFormatUtils;
import com.quicksure.mobile.utility.StringUtils;

@Service("policyStatusQuery")
public class PolicyStatusQueryServiceImpl implements PolicyStatusQueryService {
	@Autowired
	private Sinosafeinterface sinosafeInterface; 
	@Autowired
	private PolicyStatusDataManageService dbDataManageService;
	
	/**
	 * 单个保单状态查询
	 */
	public InsuranceDetailsVO policyStatusQuery(Baseinfor baseinfor,
			HttpServletRequest request) {
		String orderNo=request.getParameter("orderNo");
		HttpSession session = request.getSession();
		InsuranceDetailsVO insuranceDetails=null;
		if (StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null) {
			 insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo+"insuranceDetailsVO");
			}
		if (insuranceDetails == null) {			
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetails=insuranceDetailsVo;
		}
		List<Baseinfor> baseinforList = new ArrayList<Baseinfor>();
		baseinforList.add(baseinfor);
		insuranceDetails.setBaseinforList(baseinforList);
		insuranceDetails.getUserinfor().setUserAction("PolicyStatusQueryList"); //设置action name
		sinosafeInterface.performSinosafeOprations(insuranceDetails);
		//返回成功才update
		if("10".equals(insuranceDetails.getInterfaceslogsWithBLOBs().getInterfacesstatu())){
			updatePolicyStatus(insuranceDetails);
		}
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		return insuranceDetails;
	}
	
	/**
	 * currentTime格式为"yyyy-MM-dd"
	 * 批量保单状态查询，根据时间段查询
	 * @throws ParseException 
	 */
	public List<Baseinfor> multiplePolicyStatusQuery() throws ParseException{
		String currentDate = DateFormatUtils.getSystemDateByYmd();
		String startTime = currentDate + " 00:00:00";
		String endTime = currentDate + " 23:59:59";
		
		InsuranceDetailsVO insuranceDetails = ProcessData.initInsuranceDetailsVO();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<Baseinfor> baseinforList = dbDataManageService.selectByTimes(map); //根据时间段获取相应的baseinfor
		insuranceDetails.setBaseinforList(baseinforList);
		insuranceDetails.getUserinfor().setUserAction("PolicyStatusQueryList"); //设置action name
		sinosafeInterface.performSinosafeOprations(insuranceDetails);
		//返回成功才update
		if("10".equals(insuranceDetails.getInterfaceslogsWithBLOBs().getInterfacesstatu())){
			updateMultiplePolicyStatus(insuranceDetails);
		}
		
		return baseinforList;
	}
	
	/**
	 * 单个更改商业/交强险保单状态
	 */
	private void updatePolicyStatus(InsuranceDetailsVO insuranceDetails){
//		Baseinfor baseinfor = baseinforMappper.selectByApplicationNo(insuranceDetails.getBaseinfor());
//		List<Map<String, String>> responseList = insuranceDetails.getPolicyStatusList();
		Baseinfor baseinfor = insuranceDetails.getBaseinforList().get(0);
		Baseinfor baseinfor1 = dbDataManageService.selectByApplicationNo(baseinfor);
		List<Map<String, String>> responseList = insuranceDetails.getMultiplePolicyStatusList();
		for(int i =0 ; i<responseList.size(); i++){
			if(responseList.get(i).get("PLY_APP_NO").equals(baseinfor.getJqapplicationno())){
				baseinfor.setJqpolicystatus(responseList.get(i).get("UNDR_OPN"));
				baseinfor.setJqpolicyno(responseList.get(i).get("PLY_NO"));
			}
			else if(responseList.get(i).get("PLY_APP_NO").equals(baseinfor.getSyapplicationno())){
				baseinfor.setSypolicystatus(responseList.get(i).get("UNDR_OPN"));
				baseinfor.setSypolicyno(responseList.get(i).get("PLY_NO"));
			}
			//baseinfor里面如果sypolicyno/jqpolicyno任何一个不为空，说明已生成保单，则需要改变订单状态
			if((baseinfor.getJqpolicyno() != null && !("".equals(baseinfor.getJqpolicyno())))
					|| (baseinfor.getSypolicyno() != null && !("".equals(baseinfor.getSypolicyno())))){
				baseinfor.setOrderstate(50);
			}
		}
		baseinfor.setOrderno(baseinfor1.getOrderno());
		//更改baseinfor表的信息
		dbDataManageService.updateMultiplePolicyStatus(insuranceDetails.getBaseinforList());
	}
	
	/**
	 * 批量更改保单状态和保单号
	 * @param insuranceDetails
	 * @return
	 */
	private int updateMultiplePolicyStatus(InsuranceDetailsVO insuranceDetails){
		List<Map<String, String>> responseList = insuranceDetails.getMultiplePolicyStatusList();
		List<Baseinfor> baseinfor = insuranceDetails.getBaseinforList();
		for(int i = 0; i<baseinfor.size(); i++){
			for(int j =0; j<responseList.size(); j++){
				if(responseList.get(j).get("PLY_APP_NO").equals(baseinfor.get(i).getJqapplicationno())){
					baseinfor.get(i).setJqpolicystatus(responseList.get(j).get("UNDR_OPN"));
					baseinfor.get(i).setJqpolicyno(responseList.get(j).get("PLY_NO"));
				}else if(responseList.get(j).get("PLY_APP_NO").equals(baseinfor.get(i).getSyapplicationno())){
					baseinfor.get(i).setSypolicystatus(responseList.get(j).get("UNDR_OPN"));
					baseinfor.get(i).setSypolicyno(responseList.get(j).get("PLY_NO"));
				}
			}
			//baseinfor里面如果sypolicyno/jqpolicyno任何一个不为空，说明已生成保单，则需要改变订单状态
			if((baseinfor.get(i).getJqpolicyno() != null && !("".equals(baseinfor.get(i).getJqpolicyno())))
					|| (baseinfor.get(i).getSypolicyno() != null && !("".equals(baseinfor.get(i).getSypolicyno())))){
				baseinfor.get(i).setOrderstate(50);
			}
		}
		insuranceDetails.setBaseinforList(baseinfor);
		//更改baseinfor表的信息
		return dbDataManageService.updateMultiplePolicyStatus(insuranceDetails.getBaseinforList());
	}
}
