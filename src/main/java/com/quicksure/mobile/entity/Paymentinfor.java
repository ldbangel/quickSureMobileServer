package com.quicksure.mobile.entity;

import org.springframework.stereotype.Component;
@Component
public class Paymentinfor {
    private Integer paymentinforid;

    private String paymenturl;

    private String paymentno;

    private Integer paymentstate;

    private String paymentpremium;

    private String paymenttimes;

    private String createdatetimes;

    private String updatetimes;

    public Integer getPaymentinforid() {
        return paymentinforid;
    }

    public void setPaymentinforid(Integer paymentinforid) {
        this.paymentinforid = paymentinforid;
    }

    public String getPaymenturl() {
        return paymenturl;
    }

    public void setPaymenturl(String paymenturl) {
        this.paymenturl = paymenturl == null ? null : paymenturl.trim();
    }

    public String getPaymentno() {
		return paymentno;
	}

	public void setPaymentno(String paymentno) {
		this.paymentno = paymentno;
	}

	public Integer getPaymentstate() {
        return paymentstate;
    }

    public void setPaymentstate(Integer paymentstate) {
        this.paymentstate = paymentstate;
    }

    public String getPaymentpremium() {
        return paymentpremium;
    }

    public void setPaymentpremium(String paymentpremium) {
        this.paymentpremium = paymentpremium == null ? null : paymentpremium.trim();
    }

	public String getPaymenttimes() {
		return paymenttimes;
	}

	public void setPaymenttimes(String paymenttimes) {
		this.paymenttimes = paymenttimes;
	}

	public String getCreatedatetimes() {
		return createdatetimes;
	}

	public void setCreatedatetimes(String createdatetimes) {
		this.createdatetimes = createdatetimes;
	}

	public String getUpdatetimes() {
		return updatetimes;
	}

	public void setUpdatetimes(String updatetimes) {
		this.updatetimes = updatetimes;
	}
    
}