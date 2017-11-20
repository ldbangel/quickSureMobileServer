package com.quicksure.mobile.entity;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class DropdownListInfor {
    private Integer dropdownlistid;

    private Integer dropdownlistcode;

    private String dropdownlistname;

    private String dropdownlistdescript;

    private Date createdatatime;

    private Date updatetime;

    private Integer referenceid;

    public Integer getDropdownlistid() {
        return dropdownlistid;
    }

    public void setDropdownlistid(Integer dropdownlistid) {
        this.dropdownlistid = dropdownlistid;
    }

    public Integer getDropdownlistcode() {
        return dropdownlistcode;
    }

    public void setDropdownlistcode(Integer dropdownlistcode) {
        this.dropdownlistcode = dropdownlistcode;
    }

    public String getDropdownlistname() {
        return dropdownlistname;
    }

    public void setDropdownlistname(String dropdownlistname) {
        this.dropdownlistname = dropdownlistname == null ? null : dropdownlistname.trim();
    }

    public String getDropdownlistdescript() {
        return dropdownlistdescript;
    }

    public void setDropdownlistdescript(String dropdownlistdescript) {
        this.dropdownlistdescript = dropdownlistdescript == null ? null : dropdownlistdescript.trim();
    }

    public Date getCreatedatatime() {
        return createdatatime;
    }

    public void setCreatedatatime(Date createdatatime) {
        this.createdatatime = createdatatime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getReferenceid() {
        return referenceid;
    }

    public void setReferenceid(Integer referenceid) {
        this.referenceid = referenceid;
    }
}