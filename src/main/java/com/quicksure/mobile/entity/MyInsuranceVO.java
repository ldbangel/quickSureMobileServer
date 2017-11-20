package com.quicksure.mobile.entity;

public class MyInsuranceVO {
	private int Id;
	private Baseinfor baseinfor;
	private Vhlinfor vhlinfor;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Baseinfor getBaseinfor() {
		return baseinfor;
	}
	public void setBaseinfor(Baseinfor baseinfor) {
		this.baseinfor = baseinfor;
	}
	public Vhlinfor getVhlinfor() {
		return vhlinfor;
	}
	public void setVhlinfor(Vhlinfor vhlinfor) {
		this.vhlinfor = vhlinfor;
	}
	
}
