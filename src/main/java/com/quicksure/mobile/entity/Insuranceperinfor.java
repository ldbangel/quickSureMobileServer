package com.quicksure.mobile.entity;

import java.util.Date;

import org.springframework.stereotype.Component;
/**
 * 提核的基本信息
 * @author lenny.li001
 *
 */
@Component
public class Insuranceperinfor {
    private int insuranceperinforid; 
    
    private String orderno; //订单号
    
    private String ownersname; //车主姓名

    private String ownerscertitype; //车主证件类型

    private String ownerscerticode; //车主证件号码

    private String ownersphoneno; //车主手机号码
    
    private String  ownersaddress; //车主地址

    private String ownerszipcode; //车主邮编

    private String applicationname;//投保人姓名

    private String applicationcertitype; //投保人证件类型

    private String applicationcerticode; //投保人证件号码

    private String applicationphoneno; //投保人手机号码
    
    private String applicationaddress;  //投保人地址

    private String applicationzipcode; //投保人邮编号码

    private String insurename; //被保险人姓名

    private String insurecertitype; //被保险人证件类型

    private String insurecerticode; //被保险人证件号码

    private String insurephoneno; //被保险人手机号码
    
    private String  insureaddress; //被保险人地址
 
    private String insurezipcode; //被保险人邮编
    
    private String quotenumber;  //报价单号

    private Date creatdatatime; //创建时间

    private Date updatedatatime; //更新时间
    
    private String ownersdetailaddress;
    private String applicationdetailaddress;
    private String insuredetailaddress;
    private String deliverydetailaddress;
    private String appCoypCheckbox;
    private String insureCopyCheckbox;
    private String deliveryCopyCheckbox;
    
	public String getOwnersdetailaddress() {
		return ownersdetailaddress;
	}

	public void setOwnersdetailaddress(String ownersdetailaddress) {
		this.ownersdetailaddress = ownersdetailaddress;
	}

	public String getApplicationdetailaddress() {
		return applicationdetailaddress;
	}

	public void setApplicationdetailaddress(String applicationdetailaddress) {
		this.applicationdetailaddress = applicationdetailaddress;
	}

	public String getInsuredetailaddress() {
		return insuredetailaddress;
	}

	public void setInsuredetailaddress(String insuredetailaddress) {
		this.insuredetailaddress = insuredetailaddress;
	}

	public String getDeliverydetailaddress() {
		return deliverydetailaddress;
	}

	public void setDeliverydetailaddress(String deliverydetailaddress) {
		this.deliverydetailaddress = deliverydetailaddress;
	}
	
	public String getAppCoypCheckbox() {
		return appCoypCheckbox;
	}

	public void setAppCoypCheckbox(String appCoypCheckbox) {
		this.appCoypCheckbox = appCoypCheckbox;
	}

	public String getInsureCopyCheckbox() {
		return insureCopyCheckbox;
	}

	public void setInsureCopyCheckbox(String insureCopyCheckbox) {
		this.insureCopyCheckbox = insureCopyCheckbox;
	}

	public String getDeliveryCopyCheckbox() {
		return deliveryCopyCheckbox;
	}

	public void setDeliveryCopyCheckbox(String deliveryCopyCheckbox) {
		this.deliveryCopyCheckbox = deliveryCopyCheckbox;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Integer getInsuranceperinforid() {
		return insuranceperinforid;
	}

	public void setInsuranceperinforid(Integer insuranceperinforid) {
		this.insuranceperinforid = insuranceperinforid;
	}

	public String getOwnersname() {
		return ownersname;
	}

	public void setOwnersname(String ownersname) {
		this.ownersname = ownersname;
	}

	public String getOwnerscertitype() {
		return ownerscertitype;
	}

	public void setOwnerscertitype(String ownerscertitype) {
		this.ownerscertitype = ownerscertitype;
	}

	public String getOwnerscerticode() {
		return ownerscerticode;
	}

	public void setOwnerscerticode(String ownerscerticode) {
		this.ownerscerticode = ownerscerticode;
	}


	public String getOwnersaddress() {
		return ownersaddress;
	}

	public void setOwnersaddress(String ownersaddress) {
		this.ownersaddress = ownersaddress;
	}



	public String getApplicationname() {
		return applicationname;
	}

	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}

	public String getApplicationcertitype() {
		return applicationcertitype;
	}

	public void setApplicationcertitype(String applicationcertitype) {
		this.applicationcertitype = applicationcertitype;
	}

	public String getApplicationcerticode() {
		return applicationcerticode;
	}

	public void setApplicationcerticode(String applicationcerticode) {
		this.applicationcerticode = applicationcerticode;
	}

	public String getApplicationphoneno() {
		return applicationphoneno;
	}

	public String getOwnersphoneno() {
		return ownersphoneno;
	}

	public void setOwnersphoneno(String ownersphoneno) {
		this.ownersphoneno = ownersphoneno;
	}

	public String getOwnerszipcode() {
		return ownerszipcode;
	}

	public void setOwnerszipcode(String ownerszipcode) {
		this.ownerszipcode = ownerszipcode;
	}

	public void setInsuranceperinforid(int insuranceperinforid) {
		this.insuranceperinforid = insuranceperinforid;
	}

	public void setApplicationphoneno(String applicationphoneno) {
		this.applicationphoneno = applicationphoneno;
	}

	public String getApplicationaddress() {
		return applicationaddress;
	}

	public void setApplicationaddress(String applicationaddress) {
		this.applicationaddress = applicationaddress;
	}

	public String getApplicationzipcode() {
		return applicationzipcode;
	}

	public void setApplicationzipcode(String applicationzipcode) {
		this.applicationzipcode = applicationzipcode;
	}

	public String getInsurename() {
		return insurename;
	}

	public void setInsurename(String insurename) {
		this.insurename = insurename;
	}

	public String getInsurecertitype() {
		return insurecertitype;
	}

	public void setInsurecertitype(String insurecertitype) {
		this.insurecertitype = insurecertitype;
	}

	public String getInsurecerticode() {
		return insurecerticode;
	}

	public void setInsurecerticode(String insurecerticode) {
		this.insurecerticode = insurecerticode;
	}

	public String getInsurephoneno() {
		return insurephoneno;
	}

	public void setInsurephoneno(String insurephoneno) {
		this.insurephoneno = insurephoneno;
	}

	public String getInsureaddress() {
		return insureaddress;
	}

	public void setInsureaddress(String insureaddress) {
		this.insureaddress = insureaddress;
	}

	public String getInsurezipcode() {
		return insurezipcode;
	}

	public void setInsurezipcode(String insurezipcode) {
		this.insurezipcode = insurezipcode;
	}

	public String getQuotenumber() {
		return quotenumber;
	}

	public void setQuotenumber(String quotenumber) {
		this.quotenumber = quotenumber;
	}

	public Date getCreatdatatime() {
		return creatdatatime;
	}

	public void setCreatdatatime(Date creatdatatime) {
		this.creatdatatime = creatdatatime;
	}

	public Date getUpdatedatatime() {
		return updatedatatime;
	}

	public void setUpdatedatatime(Date updatedatatime) {
		this.updatedatatime = updatedatatime;
	}

	
}