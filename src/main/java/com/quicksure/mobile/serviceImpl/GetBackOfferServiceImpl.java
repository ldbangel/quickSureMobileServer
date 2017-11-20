package com.quicksure.mobile.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dms.GetBackOfferDataManageService;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Vhlinfor;
import com.quicksure.mobile.service.GetBackOfferService;
import com.quicksure.mobile.utility.StringUtils;

/**
 * 找回报价
 * @author dongbo
 *
 */
@Service("getBackOfferService")
public class GetBackOfferServiceImpl implements GetBackOfferService {
//	private static final Logger logger = Logger.getLogger(GetBackOfferServiceImpl.class);
	
	@Autowired
	private GetBackOfferDataManageService dbDataManagerService;
	
	public List<Baseinfor> getBackOfferByVhlinfor(Vhlinfor vhlinfor, 
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
		insuranceDetails.setVhlinfor(vhlinfor);
		List<Baseinfor> baseinforList = dbDataManagerService.searchAndUpdatePolicyStatus(insuranceDetails);
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		return baseinforList;
	}

}
