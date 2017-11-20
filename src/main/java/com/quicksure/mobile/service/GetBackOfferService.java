package com.quicksure.mobile.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Vhlinfor;

public interface GetBackOfferService {
	public List<Baseinfor> getBackOfferByVhlinfor(Vhlinfor vhlinfor, HttpServletRequest request);
}
