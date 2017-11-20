package com.quicksure.mobile.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Userinfor {
	private Integer userid;
	private String username;
	private String password;
	private String userAction;

	private String phoneno;

	private String address;

	private String emailsaddress;

	private Integer usertype;

	private String zipaddress;

	private Date createtimes;
	private String updatetimes;
	private String errorCode; //错误代码
	private String errorMessage; //错误信息
	private boolean actionStatu;
	
	private int pageFlag; //页面标识，用于上一步下一步时，判断页面
	
	private int agentFlag; //是否为代理人
	
	private String couponCode; //优惠码
	
	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public boolean isActionStatu() {
		return actionStatu;
	}

	public void setActionStatu(boolean actionStatu) {
		this.actionStatu = actionStatu;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getEmailsaddress() {
		return emailsaddress;
	}

	public void setEmailsaddress(String emailsaddress) {
		this.emailsaddress = emailsaddress == null ? null : emailsaddress
				.trim();
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public String getZipaddress() {
		return zipaddress;
	}

	public void setZipaddress(String zipaddress) {
		this.zipaddress = zipaddress == null ? null : zipaddress.trim();
	}

	public Date getCreatetimes() {
		return createtimes;
	}

	public void setCreatetimes(Date createtimes) {
		this.createtimes = createtimes;
	}

	public String getUpdatetimes() {
		return updatetimes;
	}

	public void setUpdatetimes(String updatetimes) {
		this.updatetimes = updatetimes;
	}

	public String getUserAction() {
		return userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

	public int getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(int pageFlag) {
		this.pageFlag = pageFlag;
	}

	public int getAgentFlag() {
		return agentFlag;
	}

	public void setAgentFlag(int agentFlag) {
		this.agentFlag = agentFlag;
	}
	

}