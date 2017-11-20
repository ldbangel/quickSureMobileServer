package com.quicksure.mobile.entity;

import java.util.Date;

public class OTPGeneration {
    private Integer otpid;

    private String phoneno;

    private String validationno;

    private Date createtimes;

    private String exprietimes;

    private String status;

    public Integer getOtpid() {
        return otpid;
    }

    public void setOtpid(Integer otpid) {
        this.otpid = otpid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getValidationno() {
        return validationno;
    }

    public void setValidationno(String validationno) {
        this.validationno = validationno == null ? null : validationno.trim();
    }

    public Date getCreatetimes() {
        return createtimes;
    }

    public void setCreatetimes(Date createtimes) {
        this.createtimes = createtimes;
    }

    public String getExprietimes() {
        return exprietimes;
    }

    public void setExprietimes(String exprietimes) {
        this.exprietimes = exprietimes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}