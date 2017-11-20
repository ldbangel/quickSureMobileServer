package com.quicksure.mobile.entity;

import org.springframework.stereotype.Component;

/**
 * 险别的信息
 * @author lenny.li001
 *
 */
@Component
public class Coverageinfor {
    private int coverageid;

    private String baseinfororderno; //订单号

    private String insrnccode; //险别代码

    private String suminsured; //保额

    private String premium; //应缴保费

    private String deductibleflag; //不计免赔标志

    private String deductiblepremium; //不计免赔保费

    private String seqno; //序列号

    private String numberday;  //保障天数

    private String numberperson;  //保障人数

    private String bulletGlass;  //防弹玻璃

	private String rate;  //费率

    private String basepremium;  //基本保费

    private String beforepremium;  //折前保费

    private String cyl15;  // 车险绝对免赔额

    private String cyl12;

    private String cyl11; //辅助计算

    private String nyl12;  //不计免赔保费

    private String riskpremium; //风险保费

    private String varrescfunds; //救助基金变化值

    private String ptgrescfunds; //救助基金比例

    private String rescfunds; //救助基金
    
    private String sypolicystartdate;//商业险起期
    
    private String jqpolicystartdate;//交强起期
    
    

    public String getSypolicystartdate() {
		return sypolicystartdate;
	}

	public void setSypolicystartdate(String sypolicystartdate) {
		this.sypolicystartdate = sypolicystartdate;
	}

	public String getJqpolicystartdate() {
		return jqpolicystartdate;
	}

	public void setJqpolicystartdate(String jqpolicystartdate) {
		this.jqpolicystartdate = jqpolicystartdate;
	}

	public int getCoverageid() {
		return coverageid;
	}

	public void setCoverageid(int coverageid) {
		this.coverageid = coverageid;
	}

	public String getBaseinfororderno() {
		return baseinfororderno;
	}

	public void setBaseinfororderno(String baseinfororderno) {
		this.baseinfororderno = baseinfororderno;
	}

	public String getInsrnccode() {
		return insrnccode;
	}

	public void setInsrnccode(String insrnccode) {
		this.insrnccode = insrnccode;
	}

	public String getSuminsured() {
		return suminsured;
	}

	public void setSuminsured(String suminsured) {
		this.suminsured = suminsured;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getDeductibleflag() {
		return deductibleflag;
	}

	public void setDeductibleflag(String deductibleflag) {
		this.deductibleflag = deductibleflag;
	}

	public String getDeductiblepremium() {
		return deductiblepremium;
	}

	public void setDeductiblepremium(String deductiblepremium) {
		this.deductiblepremium = deductiblepremium;
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getNumberday() {
		return numberday;
	}

	public void setNumberday(String numberday) {
		this.numberday = numberday;
	}

	public String getNumberperson() {
		return numberperson;
	}

	public void setNumberperson(String numberperson) {
		this.numberperson = numberperson;
	}

	public String getBulletGlass() {
		return bulletGlass;
	}

	public void setBulletGlass(String bulletGlass) {
		this.bulletGlass = bulletGlass;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getBasepremium() {
		return basepremium;
	}

	public void setBasepremium(String basepremium) {
		this.basepremium = basepremium;
	}

	public String getBeforepremium() {
		return beforepremium;
	}

	public void setBeforepremium(String beforepremium) {
		this.beforepremium = beforepremium;
	}

	public String getCyl15() {
		return cyl15;
	}

	public void setCyl15(String cyl15) {
		this.cyl15 = cyl15;
	}

	public String getCyl12() {
		return cyl12;
	}

	public void setCyl12(String cyl12) {
		this.cyl12 = cyl12;
	}

	public String getCyl11() {
		return cyl11;
	}

	public void setCyl11(String cyl11) {
		this.cyl11 = cyl11;
	}

	public String getNyl12() {
		return nyl12;
	}

	public void setNyl12(String nyl12) {
		this.nyl12 = nyl12;
	}

	public String getRiskpremium() {
		return riskpremium;
	}

	public void setRiskpremium(String riskpremium) {
		this.riskpremium = riskpremium;
	}

	public String getVarrescfunds() {
		return varrescfunds;
	}

	public void setVarrescfunds(String varrescfunds) {
		this.varrescfunds = varrescfunds;
	}

	public String getPtgrescfunds() {
		return ptgrescfunds;
	}

	public void setPtgrescfunds(String ptgrescfunds) {
		this.ptgrescfunds = ptgrescfunds;
	}

	public String getRescfunds() {
		return rescfunds;
	}

	public void setRescfunds(String rescfunds) {
		this.rescfunds = rescfunds;
	}

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

	private String createdatatime;

    private String updatedatatime;
    
   
}