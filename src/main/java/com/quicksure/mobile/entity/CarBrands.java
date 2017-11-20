package com.quicksure.mobile.entity;

public class CarBrands {
    private Integer carid;

    private String brand;   //品牌

    private String demio;   //车系

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getDemio() {
        return demio;
    }

    public void setDemio(String demio) {
        this.demio = demio == null ? null : demio.trim();
    }
}