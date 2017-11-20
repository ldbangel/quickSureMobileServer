package com.quicksure.mobile.entity;
import org.springframework.stereotype.Component;
@Component
public class Taxinfor {
    private int taxinforid; //

    private String taxtype; //减免税标志

    private String taxvaltype; //车船税车辆分类

    private String latefee; //滞纳金

    private String currenttax; //当年应缴

    private String formertax; //上年应缴

    private String sumuptax; //合计应缴

    private String minustaxamt; //减免金额

    private String fueltype; //新能源车标志

	public int getTaxinforid() {
		return taxinforid;
	}

	public void setTaxinforid(int taxinforid) {
		this.taxinforid = taxinforid;
	}

	public String getTaxtype() {
		return taxtype;
	}

	public void setTaxtype(String taxtype) {
		this.taxtype = taxtype;
	}

	public String getTaxvaltype() {
		return taxvaltype;
	}

	public void setTaxvaltype(String taxvaltype) {
		this.taxvaltype = taxvaltype;
	}

	public String getLatefee() {
		return latefee;
	}

	public void setLatefee(String latefee) {
		this.latefee = latefee;
	}

	public String getCurrenttax() {
		return currenttax;
	}

	public void setCurrenttax(String currenttax) {
		this.currenttax = currenttax;
	}

	public String getFormertax() {
		return formertax;
	}

	public void setFormertax(String formertax) {
		this.formertax = formertax;
	}

	public String getSumuptax() {
		return sumuptax;
	}

	public void setSumuptax(String sumuptax) {
		this.sumuptax = sumuptax;
	}

	public String getMinustaxamt() {
		return minustaxamt;
	}

	public void setMinustaxamt(String minustaxamt) {
		this.minustaxamt = minustaxamt;
	}

	public String getFueltype() {
		return fueltype;
	}

	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}
 
    
}