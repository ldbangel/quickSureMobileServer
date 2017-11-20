package com.quicksure.mobile.entity;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.quicksure.feiche.entity.LudifcBaseinfor;

@Component("insuranceDetailsVO")
public class InsuranceDetailsVO {
	private String orderno;
	@Resource
	private Baseinfor baseinfor;
//	@Resource
//	private Coverageinfor coverageinfor;
	@Resource
	private Coverageinfor coverageinfor;
	@Resource
	private Deliveryinfor deliveryinfor;
	@Resource
	private Dptinfor dptinfor;
	@Resource
	private DropdownListInfor dropdownListInfor;
	@Resource
	private Insuranceperinfor insuranceperinfor;
	@Resource
	private InterfaceDetails interfaceDetails;
	@Resource
	private Paymentinfor paymentinfor;
	@Resource
	private Taxinfor taxinfor;
	@Resource
	private Userinfor userinfor;
	@Resource
	private  Vhlinfor vhlinfor;
	@Resource
	private  InterfaceslogsWithBLOBs interfaceslogsWithBLOBs;
	@Resource
	private Interfaceslogs interfaceslog;
	@Resource
	private Agreementinfor agreementinfor;//特约
	@Resource
	private RadarInfor radarinfor;//雷达信息	
	@Resource
	private LudifcBaseinfor ludifcBaseinfor ;//非车
	
	private List<Map<String, Object>> vhlinfoList;//精友返回的车型信息集合
	
	private List<Map<String, String>> payInfoList; //支付申请返回信息集合
	
	private List<Map<String,String>> polCancelList; //撤销保单返回信息集合
	
	private List<Map<String,String>> policyStatusList;//保单状态查询信息集合
	
	private List<Map<String,String>> multiplePolicyStatusList;//批量保单状态查询信息集合
	
	private List<Baseinfor> baseinforList; //定时任务获取的baseinfor集合
	
	private List<Coverageinfor> coverageinfors;  //险种的集合
	
	private List<Agreementinfor> agreementinfors; //特约的集合
	
	private List<Interfaceslogs> interfaceslogList;//日志集合
	
	
	
	public LudifcBaseinfor getLudifcBaseinfor() {
		return ludifcBaseinfor;
	}
	public void setLudifcBaseinfor(LudifcBaseinfor ludifcBaseinfor) {
		this.ludifcBaseinfor = ludifcBaseinfor;
	}
	public RadarInfor getRadarinfor() {
		return radarinfor;
	}
	public void setRadarinfor(RadarInfor radarinfor) {
		this.radarinfor = radarinfor;
	}
	public Coverageinfor getCoverageinfor() {
		return coverageinfor;
	}
	public void setCoverageinfor(Coverageinfor coverageinfor) {
		this.coverageinfor = coverageinfor;
	}
	public Agreementinfor getAgreementinfor() {
		return agreementinfor;
	}
	public void setAgreementinfor(Agreementinfor agreementinfor) {
		this.agreementinfor = agreementinfor;
	}
	public List<Agreementinfor> getAgreementinfors() {
		return agreementinfors;
	}
	public void setAgreementinfors(List<Agreementinfor> agreementinfors) {
		this.agreementinfors = agreementinfors;
	}
	public List<Coverageinfor> getCoverageinfors() {
		return coverageinfors;
	}
	public void setCoverageinfors(List<Coverageinfor> coverageinfors) {
		this.coverageinfors = coverageinfors;
	}
	
	public List<Map<String, String>> getPolCancelList() {
		return polCancelList;
	}
	public void setPolCancelList(List<Map<String, String>> polCancelList) {
		this.polCancelList = polCancelList;
	}
	public List<Map<String, String>> getPayInfoList() {
		return payInfoList;
	}
	public void setPayInfoList(List<Map<String, String>> payInfoList) {
		this.payInfoList = payInfoList;
	}
	public InterfaceslogsWithBLOBs getInterfaceslogsWithBLOBs() {
		return interfaceslogsWithBLOBs;
	}
	public void setInterfaceslogsWithBLOBs(
			InterfaceslogsWithBLOBs interfaceslogsWithBLOBs) {
		this.interfaceslogsWithBLOBs = interfaceslogsWithBLOBs;
	}

	public List<Map<String, Object>> getVhlinfoList() {
		return vhlinfoList;
	}
	
	public void setVhlinfoList(List<Map<String, Object>> vhlinfoList) {
		this.vhlinfoList = vhlinfoList;
	}
	
	public Baseinfor getBaseinfor() {
		return baseinfor;
	}
	public void setBaseinfor(Baseinfor baseinfor) {
		this.baseinfor = baseinfor;
	}
//	public Coverageinfor getCoverageinfor() {
//		return coverageinfor;
//	}
//	public void setCoverageinfor(Coverageinfor coverageinfor) {
//		this.coverageinfor = coverageinfor;
//	}
	public Deliveryinfor getDeliveryinfor() {
		return deliveryinfor;
	}
	public void setDeliveryinfor(Deliveryinfor deliveryinfor) {
		this.deliveryinfor = deliveryinfor;
	}
	public Dptinfor getDptinfor() {
		return dptinfor;
	}
	public void setDptinfor(Dptinfor dptinfor) {
		this.dptinfor = dptinfor;
	}
	public DropdownListInfor getDropdownListInfor() {
		return dropdownListInfor;
	}
	public void setDropdownListInfor(DropdownListInfor dropdownListInfor) {
		this.dropdownListInfor = dropdownListInfor;
	}
	public Insuranceperinfor getInsuranceperinfor() {
		return insuranceperinfor;
	}
	public void setInsuranceperinfor(Insuranceperinfor insuranceperinfor) {
		this.insuranceperinfor = insuranceperinfor;
	}
	public InterfaceDetails getInterfaceDetails() {
		return interfaceDetails;
	}
	public void setInterfaceDetails(InterfaceDetails interfaceDetails) {
		this.interfaceDetails = interfaceDetails;
	}
	public Paymentinfor getPaymentinfor() {
		return paymentinfor;
	}
	public void setPaymentinfor(Paymentinfor paymentinfor) {
		this.paymentinfor = paymentinfor;
	}
	public Taxinfor getTaxinfor() {
		return taxinfor;
	}
	public void setTaxinfor(Taxinfor taxinfor) {
		this.taxinfor = taxinfor;
	}
	public Userinfor getUserinfor() {
		return userinfor;
	}
	public void setUserinfor(Userinfor userinfor) {
		this.userinfor = userinfor;
	}
	public Vhlinfor getVhlinfor() {
		return vhlinfor;
	}
	public void setVhlinfor(Vhlinfor vhlinfor) {
		this.vhlinfor = vhlinfor;
	}
	public List<Map<String, String>> getPolicyStatusList() {
		return policyStatusList;
	}
	public void setPolicyStatusList(List<Map<String, String>> policyStatusList) {
		this.policyStatusList = policyStatusList;
	}
	public List<Baseinfor> getBaseinforList() {
		return baseinforList;
	}
	public void setBaseinforList(List<Baseinfor> baseinforList) {
		this.baseinforList = baseinforList;
	}
	public List<Map<String, String>> getMultiplePolicyStatusList() {
		return multiplePolicyStatusList;
	}
	public void setMultiplePolicyStatusList(
			List<Map<String, String>> multiplePolicyStatusList) {
		this.multiplePolicyStatusList = multiplePolicyStatusList;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public Interfaceslogs getInterfaceslog() {
		return interfaceslog;
	}
	public void setInterfaceslog(Interfaceslogs interfaceslog) {
		this.interfaceslog = interfaceslog;
	}
	public List<Interfaceslogs> getInterfaceslogList() {
		return interfaceslogList;
	}
	public void setInterfaceslogList(List<Interfaceslogs> interfaceslogList) {
		this.interfaceslogList = interfaceslogList;
	}
	
}
