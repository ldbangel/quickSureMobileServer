package com.quicksure.mobile.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Dptinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Vhlinfor;
import com.quicksure.mobile.service.VehicleInforService;
import com.quicksure.mobile.utility.StringUtils;


@Controller
@RequestMapping("/vehicleInfor")
public class VehicleInforContorller {
	@Autowired
	private VehicleInforService vehicleInforService;
	@Autowired
	private BaseinforMapper baseinforMapper;
	private static final Logger logger = Logger
			.getLogger(VehicleInforContorller.class);

	/**
	 * 车型查询查询条件为车架号
	 * 
	 * @param vhlinfor
	 * @param modelMap
	 * @param httprequest
	 * @return
	 */
	@RequestMapping("/modelSerachByVin.do")
	public @ResponseBody
	List<Map<String, Object>> modleSerachByVin(@RequestBody Vhlinfor vhlinfor,
			HttpServletRequest httprequest, HttpServletResponse response) {
		logger.info("Modle SerachBy Vin Start the Vin No is： "
				+ vhlinfor.getVinno());

		InsuranceDetailsVO insuranceDetailsVO = vehicleInforService
				.modleSerachByVin(vhlinfor, httprequest);

		logger.info("Modle SerachBy Vin Ends the result data is： "
				+ insuranceDetailsVO.getVhlinfoList());
		return insuranceDetailsVO.getVhlinfoList();

	}
	/**
	 * 车型查询查询条件为车型名称
	 * @param vhlinfor
	 * @param modelMap
	 * @param httprequest
	 * @return
	 */
	@RequestMapping("/modelSerachByName.do")
	public @ResponseBody List<Map<String, Object>> modleSerachByName(@RequestBody Vhlinfor vhlinfor,HttpServletRequest httprequest,HttpServletResponse response){	
		logger.info("Modle SerachBy Name Start the VhlName  is"+vhlinfor.getVehiclename());		
		InsuranceDetailsVO insuranceDetailsVO=vehicleInforService.modleSerachByName(vhlinfor, httprequest);	
		logger.info("Modle SerachBy Name Ends the result data is： "+insuranceDetailsVO.getVhlinfoList());
		return insuranceDetailsVO.getVhlinfoList();
	}
	
	/**
	 * 华安车型查询
	 * @param vhlinfor
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/modelSearchFromSinosafe.do")
	@ResponseBody
	public List<Map<String, Object>> modelSearchFromSinosafe(@RequestBody Vhlinfor vhlinfor,
			HttpServletRequest request,HttpServletResponse response){
		InsuranceDetailsVO insuranceDetailsVO = vehicleInforService.modleSerachFromSinosafe(vhlinfor, request);
		logger.info("Modle Serach From Sinosafe Ends the result data is： "+insuranceDetailsVO.getVhlinfoList());
		return insuranceDetailsVO.getVhlinfoList();
	}
	
	/**
	 * 提交车辆信息
	 * @param vhlinfor
	 * @param httprequest
	 * @param response
	 * @return
	 */
	@RequestMapping("/vehicleSumbit.do")
	public String onSumbitAction(ModelMap modelMap,Vhlinfor vhlinfor,Baseinfor baseinfor,
			HttpServletRequest httprequest,HttpServletResponse response){
		logger.info("vehicle information sumbit Start the Vin No  is"+vhlinfor.getVinno());
		InsuranceDetailsVO insuranceDetailsVO = vehicleInforService.saveVehicleDate(vhlinfor,baseinfor, httprequest);
		//获取错误信息传给前台jsp(EL)
		/*String errorMessage = insuranceDetailsVO.getInterfaceslogsWithBLOBs().getResponsemessage();
		String errorCode = insuranceDetailsVO.getInterfaceslogsWithBLOBs().getResponsecode();
		modelMap.addAttribute("errorMessage", errorMessage);
		modelMap.addAttribute("errorCode", errorCode);
		insuranceDetailsVO.setCoverageinfors(null);*/
		modelMap.addAttribute("insuranceDetailsVO",insuranceDetailsVO);
		logger.info("vehicle information sumbit Ends the Vin No  is"+vhlinfor.getVinno());	
		return LudiConstants.COVEIFNOR;
	}
	
	/**
	 * 返回coverage页面
	 * @return
	 */
	@RequestMapping("/backToCoverageScreen.do")
	public String backToCoverageScreen(ModelMap modelMap,HttpServletRequest request){
		String orderNo = request.getParameter("orderNo");
		InsuranceDetailsVO insuranceDetailsVO = (InsuranceDetailsVO) request.getSession()
				.getAttribute(orderNo + "insuranceDetailsVO");
		if(insuranceDetailsVO.getUserinfor().getPageFlag()==0){
			//
			Baseinfor baseinfor = baseinforMapper.selectByPrimaryKey(orderNo);
			if(baseinfor.getInsuranceperinforid() != null){
				insuranceDetailsVO.getUserinfor().setPageFlag(5); 
			}else if(baseinfor.getTotalPremium() != null){
				insuranceDetailsVO.getUserinfor().setPageFlag(4);
			}else{
				insuranceDetailsVO.getUserinfor().setPageFlag(3); //我的订单中继续投保到这个页面时，pageFlag为0，手动给其赋值到相应的页面标记
			}
		}
		modelMap.addAttribute("insuranceDetailsVO", insuranceDetailsVO);
		return LudiConstants.COVEIFNOR;
	}
	
	/**
	 * 进入车辆信息(vehicleinfor)页面
	 * @param modelMap
	 * @param httprequest
	 * @param response
	 * @return
	 */
	@RequestMapping("/goToVehicleScreen.do")
	public String goToVehicleinfor(ModelMap modelMap,HttpServletRequest httprequest,HttpServletResponse response){
		logger.info("Starts go to the vehicle screen the phone No is: "+httprequest.getParameter("lcno"));
		InsuranceDetailsVO insuranceDetailsVO = vehicleInforService.goToVehicleinfor(httprequest);
		modelMap.addAttribute("insuranceDetailsVO",insuranceDetailsVO);
		logger.info("Ends go to the vehicle screen the phone No is: "+httprequest.getParameter("lcno"));
		return LudiConstants.VEHICLEINFOR;
	}
	
	/**
	 * 返回车辆信息页面
	 * @return
	 */
	@RequestMapping("/backToVehicleScreen.do")
	public String backToVehicleinfor(HttpServletRequest request,ModelMap modelMap){
		String orderNo = request.getParameter("orderNo");
		InsuranceDetailsVO insuranceDetailsVO = (InsuranceDetailsVO) request.getSession()
				.getAttribute(orderNo + "insuranceDetailsVO");
		/*if(insuranceDetailsVO.getUserinfor().getPageFlag()==0){
			insuranceDetailsVO.getUserinfor().setPageFlag(2); //我的订单中继续投保到这个页面时，pageFlag为0，手动给其赋值2
		}*/
		modelMap.addAttribute("insuranceDetailsVO", insuranceDetailsVO);
		return LudiConstants.VEHICLEINFOR;
	}

	@RequestMapping("/getDptCode.do")
	public @ResponseBody
	List<Dptinfor> getAlldeptinfor(HttpServletResponse response) {

		List<Dptinfor> dptinfors = vehicleInforService.getAlldeptinfor();

		return dptinfors;
	}
	
	/**
	 * 车辆信息页面返回上一步到首页
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goBackToFirstPage.do")
	public String goBackToFirstPage(ModelMap modelMap, HttpServletRequest request, 
			HttpServletResponse response){
		String orderNo = request.getParameter("orderNo");
		InsuranceDetailsVO insuranceDetailsVO = (InsuranceDetailsVO) request.getSession()
				.getAttribute(orderNo+"insuranceDetailsVO");
		String lcnno = insuranceDetailsVO.getVhlinfor().getLcnno();
		/*String city_code = insuranceDetailsVO.getBaseinfor().getDeptno();
		String pro_code = (String) city_code.substring(0, 2)+"0000";
		String province = insuranceDetailsVO.getBaseinfor().getProvinceName()==null?pro_code:insuranceDetailsVO.getBaseinfor().getProvinceName();
		String city = insuranceDetailsVO.getBaseinfor().getCityName()==null?city_code:insuranceDetailsVO.getBaseinfor().getCityName();*/
		modelMap.addAttribute("orderNo", orderNo);
		/*modelMap.addAttribute("province", province);
		modelMap.addAttribute("city", city);*/
		modelMap.addAttribute("insuranceDetailsVO",insuranceDetailsVO);
		if("*_*".equals(lcnno)){
			modelMap.addAttribute("NOflag", "1");
		}else if(lcnno!=null && !"".equals(lcnno)){
			modelMap.addAttribute("NOflag", "2");
		}
		return LudiConstants.INDEX;
	}

	/**
	 * 开始识别行驶证
	 * 
	 * @param vhlinfor
	 * @param modelMap
	 * @param httprequest
	 * @return
	 */
	@RequestMapping("/getResultbyOCR.do")
	public void uploadFile(@RequestParam("picFile") MultipartFile myfile,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("开始识别行驶证");
		String result = vehicleInforService.getVehicleinforByOCR(myfile,null);
		try {
			response.setContentType("text/html;chartset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		} catch (IOException e) {
			 StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			  logger.error("行驶证扫描异常"+str);
		}
		logger.info("结束识别行驶证");

	}
	
	/**
	 * 开始识别行驶证
	 * 
	 * @param vhlinfor
	 * @param modelMap
	 * @param httprequest
	 * @return
	 */
	@RequestMapping("/getResultbyOCRbyWechat.do")
	public void uploadFileBywechat(@RequestBody String imgdata,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("开始识别行驶证");
		if(StringUtils.checkStringEmpty(imgdata)&&imgdata.indexOf("base64,")>=0){
			imgdata=imgdata.split("base64,")[1];
		}
		String result = vehicleInforService.getVehicleinforByOCR(null,imgdata);
		try {
			response.setContentType("text/html;chartset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		} catch (IOException e) {
			  StringWriter sw = new StringWriter();  
			  e.printStackTrace(new PrintWriter(sw, true));  
			  String str = sw.toString();
			  logger.error("微信端行驶证扫描异常"+str);
		}
		logger.info("结束识别行驶证");

	}
	
}
