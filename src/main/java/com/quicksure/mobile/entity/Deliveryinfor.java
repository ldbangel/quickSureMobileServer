package com.quicksure.mobile.entity;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class Deliveryinfor {
    private Integer deliveryid;

    private String deliveryname; //配送人姓名

    private String deliveryphone; //配送人电话
 
    private String deliveryaddress; //配送地址

    private Date deliverydate; 

    private Date createdatetime;

    private Date updatetime;

    private Integer deliverystate; //配送状态
    
    private String jqsequenceno;//交强投保查询码
    
    private String sysequenceno;//商业投保查询码
    
    private String deliveryCounty;//收件人镇
    
    private String deliveryCity;//收件人城
    
    private String deliveryProvince;// 收件人省

    public String getDeliveryCounty() {
		return deliveryCounty;
	}

	public void setDeliveryCounty(String deliveryCounty) {
		this.deliveryCounty = deliveryCounty;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryProvince() {
		return deliveryProvince;
	}

	public void setDeliveryProvince(String deliveryProvince) {
		this.deliveryProvince = deliveryProvince;
	}

    public String getJqsequenceno() {
		return jqsequenceno;
	}

	public void setJqsequenceno(String jqsequenceno) {
		this.jqsequenceno = jqsequenceno;
	}

	public String getSysequenceno() {
		return sysequenceno;
	}

	public void setSysequenceno(String sysequenceno) {
		this.sysequenceno = sysequenceno;
	}

	public Integer getDeliveryid() {
        return deliveryid;
    }

    public void setDeliveryid(Integer deliveryid) {
        this.deliveryid = deliveryid;
    }

    public String getDeliveryname() {
        return deliveryname;
    }

    public void setDeliveryname(String deliveryname) {
        this.deliveryname = deliveryname == null ? null : deliveryname.trim();
    }

    public String getDeliveryphone() {
        return deliveryphone;
    }

    public void setDeliveryphone(String deliveryphone) {
        this.deliveryphone = deliveryphone == null ? null : deliveryphone.trim();
    }

    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress == null ? null : deliveryaddress.trim();
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getDeliverystate() {
        return deliverystate;
    }

    public void setDeliverystate(Integer deliverystate) {
        this.deliverystate = deliverystate;
    }
}