package com.quicksure.mobile.entity;

import org.springframework.stereotype.Component;

@Component
public class InterfaceslogsWithBLOBs extends Interfaceslogs {
    private String requestxml;

    private String responsexml;

    public String getRequestxml() {
        return requestxml;
    }

    public void setRequestxml(String requestxml) {
        this.requestxml = requestxml == null ? null : requestxml.trim();
    }

    public String getResponsexml() {
        return responsexml;
    }

    public void setResponsexml(String responsexml) {
        this.responsexml = responsexml == null ? null : responsexml.trim();
    }
}