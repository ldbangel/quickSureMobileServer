package com.quicksure.mobile.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Dptinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Vhlinfor;
public interface VehicleInforService {
 public InsuranceDetailsVO modleSerachByVin(Vhlinfor vhlinfor,HttpServletRequest httprequest);
 public InsuranceDetailsVO modleSerachByName(Vhlinfor vhlinfor,HttpServletRequest httprequest);
 public InsuranceDetailsVO modleSerachFromSinosafe(Vhlinfor vhlinfor,HttpServletRequest httprequest);
 public InsuranceDetailsVO saveVehicleDate(Vhlinfor vhlinfor,Baseinfor baseinfor,
			HttpServletRequest httprequest); 
 public InsuranceDetailsVO goToVehicleinfor(HttpServletRequest httprequest);
 public List<Dptinfor> getAlldeptinfor();
 public  String  getVehicleinforByOCR(MultipartFile myfile,String imgdata);
}
