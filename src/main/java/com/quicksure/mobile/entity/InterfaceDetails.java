package com.quicksure.mobile.entity;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class InterfaceDetails {
    private Integer interfaceid;

    private Integer interfacestate;

    private String interfacename;

    private String interfaceurl;

    private Integer uderwritingcode;

    private String clientip;

    private Date createdatetimes;

    private Date updatetimes;

    public Integer getInterfaceid() {
        return interfaceid;
    }

    public void setInterfaceid(Integer interfaceid) {
        this.interfaceid = interfaceid;
    }

    public Integer getInterfacestate() {
        return interfacestate;
    }

    public void setInterfacestate(Integer interfacestate) {
        this.interfacestate = interfacestate;
    }

    public String getInterfacename() {
        return interfacename;
    }

    public void setInterfacename(String interfacename) {
        this.interfacename = interfacename == null ? null : interfacename.trim();
    }

    public String getInterfaceurl() {
        return interfaceurl;
    }

    public void setInterfaceurl(String interfaceurl) {
        this.interfaceurl = interfaceurl == null ? null : interfaceurl.trim();
    }

    public Integer getUderwritingcode() {
        return uderwritingcode;
    }

    public void setUderwritingcode(Integer uderwritingcode) {
        this.uderwritingcode = uderwritingcode;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip == null ? null : clientip.trim();
    }

    public Date getCreatedatetimes() {
        return createdatetimes;
    }

    public void setCreatedatetimes(Date createdatetimes) {
        this.createdatetimes = createdatetimes;
    }

    public Date getUpdatetimes() {
        return updatetimes;
    }

    public void setUpdatetimes(Date updatetimes) {
        this.updatetimes = updatetimes;
    }
}