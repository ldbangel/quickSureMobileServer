package com.quicksure.feiche.entity;

import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class LudifcBaseinfor {
    private Integer orderid;

    private String orderno;//订单号

    private Integer userinforid;

    private Integer orderstate;//订单状态

    private String applicationname;//投保人姓名

    private String insurername;//被保人姓名

    private String appSex;//投保人性别

    private String insSex;//被保人性别

    private String appEmail;//投保邮箱

    private String insEmail;//被保人邮箱

    private String appCertType;//投保人证件类型

    private String insCertType;//被保人证件类型

    private String appId;//投保人身份证号

    private String insId;//被保人身份证号

    private String appBirthday;//投保人出生年月

    private String insBirthday;//被保人出生年月

    private String appAddr;//投保人常住城市

    private String insAddr;//被保人常住城市

    private String appPhone;//投保人手机号

    private String insPhone;//被保人手机号

    private String ralationship;//投保人和被保人关系

    private String insuranceStartTime;//保险始期

    private String insruanceEndTime;//保险结束期

    private Integer insrncPeriod;//保险期间

    private Date createtime;//创建时间

    private Date updatetime;//更新时间

    private String prodno;//产品编码

    private String prodtype;//产品类型

    private String sumamount;//总保额

    private String sumpremium;//总保费

    private String applicationno;//投保单号

    private String policyno;//保单号

    private String payUrl;//支付连接

    private String carno;//车牌号

    private String insuredriveno;//车架号

    private Integer tgtFld11;//核定载客人数

    private String tgtFld12;//车辆类型

    private String tgtFld16;//车辆使用性质
    
    private Integer isagentshare;//是否为分享订单

    public Integer getIsagentshare() {
		return isagentshare;
	}

	public void setIsagentshare(Integer isagentshare) {
		this.isagentshare = isagentshare;
	}

	public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Integer getUserinforid() {
        return userinforid;
    }

    public void setUserinforid(Integer userinforid) {
        this.userinforid = userinforid;
    }

    public Integer getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(Integer orderstate) {
        this.orderstate = orderstate;
    }

    public String getApplicationname() {
        return applicationname;
    }

    public void setApplicationname(String applicationname) {
        this.applicationname = applicationname == null ? null : applicationname.trim();
    }

    public String getInsurername() {
        return insurername;
    }

    public void setInsurername(String insurername) {
        this.insurername = insurername == null ? null : insurername.trim();
    }

    public String getAppSex() {
        return appSex;
    }

    public void setAppSex(String appSex) {
        this.appSex = appSex == null ? null : appSex.trim();
    }

    public String getInsSex() {
        return insSex;
    }

    public void setInsSex(String insSex) {
        this.insSex = insSex == null ? null : insSex.trim();
    }

    public String getAppEmail() {
        return appEmail;
    }

    public void setAppEmail(String appEmail) {
        this.appEmail = appEmail == null ? null : appEmail.trim();
    }

    public String getInsEmail() {
        return insEmail;
    }

    public void setInsEmail(String insEmail) {
        this.insEmail = insEmail == null ? null : insEmail.trim();
    }

    public String getAppCertType() {
        return appCertType;
    }

    public void setAppCertType(String appCertType) {
        this.appCertType = appCertType == null ? null : appCertType.trim();
    }

    public String getInsCertType() {
        return insCertType;
    }

    public void setInsCertType(String insCertType) {
        this.insCertType = insCertType == null ? null : insCertType.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getInsId() {
        return insId;
    }

    public void setInsId(String insId) {
        this.insId = insId == null ? null : insId.trim();
    }

    public String getAppBirthday() {
        return appBirthday;
    }

    public void setAppBirthday(String appBirthday) {
        this.appBirthday = appBirthday == null ? null : appBirthday.trim();
    }

    public String getInsBirthday() {
        return insBirthday;
    }

    public void setInsBirthday(String insBirthday) {
        this.insBirthday = insBirthday == null ? null : insBirthday.trim();
    }

    public String getAppAddr() {
        return appAddr;
    }

    public void setAppAddr(String appAddr) {
        this.appAddr = appAddr == null ? null : appAddr.trim();
    }

    public String getInsAddr() {
        return insAddr;
    }

    public void setInsAddr(String insAddr) {
        this.insAddr = insAddr == null ? null : insAddr.trim();
    }

    public String getAppPhone() {
        return appPhone;
    }

    public void setAppPhone(String appPhone) {
        this.appPhone = appPhone == null ? null : appPhone.trim();
    }

    public String getInsPhone() {
        return insPhone;
    }

    public void setInsPhone(String insPhone) {
        this.insPhone = insPhone == null ? null : insPhone.trim();
    }

    public String getRalationship() {
        return ralationship;
    }

    public void setRalationship(String ralationship) {
        this.ralationship = ralationship == null ? null : ralationship.trim();
    }

    public String getInsuranceStartTime() {
        return insuranceStartTime;
    }

    public void setInsuranceStartTime(String insuranceStartTime) {
        this.insuranceStartTime = insuranceStartTime == null ? null : insuranceStartTime.trim();
    }

    public String getInsruanceEndTime() {
        return insruanceEndTime;
    }

    public void setInsruanceEndTime(String insruanceEndTime) {
        this.insruanceEndTime = insruanceEndTime == null ? null : insruanceEndTime.trim();
    }

    public Integer getInsrncPeriod() {
        return insrncPeriod;
    }

    public void setInsrncPeriod(Integer insrncPeriod) {
        this.insrncPeriod = insrncPeriod;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno == null ? null : prodno.trim();
    }

    public String getProdtype() {
        return prodtype;
    }

    public void setProdtype(String prodtype) {
        this.prodtype = prodtype == null ? null : prodtype.trim();
    }

    public String getSumamount() {
        return sumamount;
    }

    public void setSumamount(String sumamount) {
        this.sumamount = sumamount == null ? null : sumamount.trim();
    }

    public String getSumpremium() {
        return sumpremium;
    }

    public void setSumpremium(String sumpremium) {
        this.sumpremium = sumpremium == null ? null : sumpremium.trim();
    }

    public String getApplicationno() {
        return applicationno;
    }

    public void setApplicationno(String applicationno) {
        this.applicationno = applicationno == null ? null : applicationno.trim();
    }

    public String getPolicyno() {
        return policyno;
    }

    public void setPolicyno(String policyno) {
        this.policyno = policyno == null ? null : policyno.trim();
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl == null ? null : payUrl.trim();
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno == null ? null : carno.trim();
    }

    public String getInsuredriveno() {
        return insuredriveno;
    }

    public void setInsuredriveno(String insuredriveno) {
        this.insuredriveno = insuredriveno == null ? null : insuredriveno.trim();
    }

    public Integer getTgtFld11() {
        return tgtFld11;
    }

    public void setTgtFld11(Integer tgtFld11) {
        this.tgtFld11 = tgtFld11;
    }

    public String getTgtFld12() {
        return tgtFld12;
    }

    public void setTgtFld12(String tgtFld12) {
        this.tgtFld12 = tgtFld12 == null ? null : tgtFld12.trim();
    }

    public String getTgtFld16() {
        return tgtFld16;
    }

    public void setTgtFld16(String tgtFld16) {
        this.tgtFld16 = tgtFld16 == null ? null : tgtFld16.trim();
    }
}