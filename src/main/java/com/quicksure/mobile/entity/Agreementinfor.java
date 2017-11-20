package com.quicksure.mobile.entity;

import org.springframework.stereotype.Component;

@Component
public class Agreementinfor {
    private int agreementid;

    private String prodno;

    private String agreementcode;

    private String agreementname;

    private String agreementdetailed;

    private String ismodified;

    private String ismandatory;

    private String orderno;
    
    private String createdatatime;

    private String updatedatatime;
    
    

    public String getCreatedatatime() {
		return createdatatime;
	}

	public void setCreatedatatime(String createdatatime) {
		this.createdatatime = createdatatime;
	}

	public String getUpdatedatatime() {
		return updatedatatime;
	}

	public void setUpdatedatatime(String updatedatatime) {
		this.updatedatatime = updatedatatime;
	}

	public int getAgreementid() {
        return agreementid;
    }

    public void setAgreementid(int agreementid) {
        this.agreementid = agreementid;
    }

    public String getProdno() {
        return prodno;
    }

    public void setProdno(String prodno) {
        this.prodno = prodno == null ? null : prodno.trim();
    }

    public String getAgreementcode() {
        return agreementcode;
    }

    public void setAgreementcode(String agreementcode) {
        this.agreementcode = agreementcode == null ? null : agreementcode.trim();
    }

    public String getAgreementname() {
        return agreementname;
    }

    public void setAgreementname(String agreementname) {
        this.agreementname = agreementname == null ? null : agreementname.trim();
    }

    public String getAgreementdetailed() {
        return agreementdetailed;
    }

    public void setAgreementdetailed(String agreementdetailed) {
        this.agreementdetailed = agreementdetailed == null ? null : agreementdetailed.trim();
    }

    public String getIsmodified() {
        return ismodified;
    }

    public void setIsmodified(String ismodified) {
        this.ismodified = ismodified;
    }

    public String getIsmandatory() {
        return ismandatory;
    }

    public void setIsmandatory(String ismandatory) {
        this.ismandatory = ismandatory;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }
}