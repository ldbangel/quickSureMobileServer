package com.quicksure.mobile.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Encoder;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.dms.VHLDataManageService;
import com.quicksure.mobile.dmsutility.DMSUtility;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.Dptinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.RadarInfor;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.entity.Vhlinfor;
import com.quicksure.mobile.service.VehicleInforService;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;
import com.quicksure.mobile.utility.ChineseTransforPingyin;
import com.quicksure.mobile.utility.DateFormatUtils;
import com.quicksure.mobile.utility.Httputils;
import com.quicksure.mobile.utility.StringUtils;
import com.quicksure.mobile.utility.SysConstantsConfig;

@Service("vehicleInforService")
public class VehicleInforServiceImpl implements VehicleInforService {
	private static final Logger logger = Logger
			.getLogger(VehicleInforServiceImpl.class);
	@Resource
	private Sinosafeinterface sinosafeinterface;
	@Resource 
	private VHLDataManageService dataManageService;
	@Resource
	private BaseinforMapper baseinforMapper;
	@Resource
	private DMSUtility dmsUtility;
	

	/*
	 * modleSerachByVin service
	 */
	public InsuranceDetailsVO modleSerachByVin(Vhlinfor vhlinfor,
			HttpServletRequest httprequest) {
		String orderNo=vhlinfor.getBaseinforOrdeo();
		logger.info("车架号车型查询开始，订单号："+orderNo);
		HttpSession session = httprequest.getSession();
		InsuranceDetailsVO insuranceDetails = null;
		if (StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null) {
			insuranceDetails = (InsuranceDetailsVO) session
					.getAttribute(orderNo+"insuranceDetailsVO");
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			insuranceDetails.setVhlinfoList(list);
		}
		if (insuranceDetails == null) {
			InsuranceDetailsVO insuranceDetailsVo = ProcessData
					.initInsuranceDetailsVO();
			insuranceDetails = insuranceDetailsVo;
		}
		insuranceDetails = ProcessData.setObjValuetoinsuranceDetails(vhlinfor,
				insuranceDetails);
		// insuranceDetails.setVhlinfor(vhlinfor);
		insuranceDetails.getUserinfor().setUserAction("ModelSerachByVin");// 设置action
		sinosafeinterface.performSinosafeOprations(insuranceDetails);																// name
		String lcnNo = insuranceDetails.getVhlinfor().getLcnno();
		try {
			if (lcnNo != null && !"".equalsIgnoreCase(lcnNo)) {
				insuranceDetails.getVhlinfor().setLcnno(
						URLDecoder.decode(lcnNo, "UTF-8"));
			}
			;
		} catch (UnsupportedEncodingException e) {
			StringWriter sw = new StringWriter();  
			 e.printStackTrace(new PrintWriter(sw, true));  
			 String str = sw.toString();
			logger.error("modleSerachByVin 字符编码异常： "+str);
		}
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		logger.info("车架号车型查询结束，订单号："+insuranceDetails.getBaseinfor().getOrderno());
		return insuranceDetails;
	}

	/**
	 * modleSerachByName service
	 * 
	 */
	public InsuranceDetailsVO modleSerachByName(Vhlinfor vhlinfor,
			HttpServletRequest httprequest) {
		String orderNo=vhlinfor.getBaseinforOrdeo();
		logger.info("车型名称车型查询开始，订单号："+orderNo);
		HttpSession session=httprequest.getSession();
		InsuranceDetailsVO insuranceDetails=null;
		if(StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null){
			 insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo+"insuranceDetailsVO");
			 List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
			 insuranceDetails.setVhlinfoList(list);
			}
		else if (insuranceDetails == null) {
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetails=insuranceDetailsVo;
		}
		insuranceDetails=ProcessData.setObjValuetoinsuranceDetails(vhlinfor,insuranceDetails);
		insuranceDetails.getUserinfor().setUserAction("ModelSerachByName");
		sinosafeinterface.performSinosafeOprations(insuranceDetails);
		String lcnNo=insuranceDetails.getVhlinfor().getLcnno();
		String vehicle=insuranceDetails.getVhlinfor().getVehiclename();
		try {
			if(lcnNo!=null&&!"".equalsIgnoreCase(lcnNo)){
				insuranceDetails.getVhlinfor().setLcnno(URLDecoder.decode(lcnNo, "UTF-8"));
			}
			if(vehicle!=null&&!"".equalsIgnoreCase(vehicle)){
				insuranceDetails.getVhlinfor().setVehiclename(URLDecoder.decode(vehicle, "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			StringWriter sw = new StringWriter();  
			e.printStackTrace(new PrintWriter(sw, true));  
			String str = sw.toString();
			logger.error("modleSerachByName 字符编码异常： "+str);
		}
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		logger.info("车型名称车型查询结束，订单号："+insuranceDetails.getBaseinfor().getOrderno());
		return insuranceDetails;
	}
	
	/**
	 * modleSerachFromSinosafe service
	 * 
	 */
	public InsuranceDetailsVO modleSerachFromSinosafe(Vhlinfor vhlinfor,
			HttpServletRequest httprequest) {
		String orderNo = vhlinfor.getBaseinforOrdeo();
		logger.info("车型名称车型查询开始，订单号："+orderNo);
		HttpSession session = httprequest.getSession();
		InsuranceDetailsVO insuranceDetails=null;
		if(StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null){
			insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo+"insuranceDetailsVO");
			List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
			insuranceDetails.setVhlinfoList(list);
		}else if (insuranceDetails == null) {
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetails=insuranceDetailsVo;
		}
		insuranceDetails=ProcessData.setObjValuetoinsuranceDetails(vhlinfor,insuranceDetails);
		insuranceDetails.getUserinfor().setUserAction("ModelSearchBySinosafe");
		sinosafeinterface.performSinosafeOprations(insuranceDetails);
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		return insuranceDetails;
	}

	/**
	 * 提交车辆信息
	 */
	public InsuranceDetailsVO saveVehicleDate(Vhlinfor vhlinfor,Baseinfor baseinfor,
			HttpServletRequest httprequest) {
		String orderNo=httprequest.getParameter("orderNo");	
		int lastImplementPage=3;//代表用户操作的当前页,跳转到保费计算页面
		logger.info("提交车辆信息开始，订单号："+orderNo);
		HttpSession session=httprequest.getSession();
		InsuranceDetailsVO insuranceDetails=null;
		String Chgownerflag = "";
		if(vhlinfor!=null){
			if(vhlinfor.getChgownerflag()!=null){
				
			}else{
				vhlinfor.setChgownerflag("0");
				Chgownerflag = vhlinfor.getChgownerflag();
			}
			
		}
		
		if (StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null) {
			insuranceDetails = (InsuranceDetailsVO) session
					.getAttribute(orderNo + "insuranceDetailsVO");
			if(Chgownerflag.endsWith("0")){
				insuranceDetails.getVhlinfor().setChgownerflag(Chgownerflag);
				insuranceDetails.getVhlinfor().setTransferdate(null);
			}
		} else if (insuranceDetails == null) {
			InsuranceDetailsVO insuranceDetailsVo = ProcessData
					.initInsuranceDetailsVO();
			insuranceDetails = insuranceDetailsVo;
		}
		if(insuranceDetails.getUserinfor().getPageFlag()<3){
			insuranceDetails.getUserinfor().setPageFlag(3); //如果首次进入到车辆信息(vehicleinfor)页面,将pageFlag状态改变为3
		}			
		insuranceDetails = ProcessData.setObjValuetoinsuranceDetails(baseinfor,insuranceDetails);
		insuranceDetails = ProcessData.setObjValuetoinsuranceDetails(vhlinfor,insuranceDetails);
		insuranceDetails.getBaseinfor().setLastImplementPage(lastImplementPage);
		//封装数据调用华安接口(车辆信息提交调用华安保费试算)
		/*Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date = calendar.getTime();
		String policystartdate=sdf.format(date)+" "+"00:00:00";
		insuranceDetails.getBaseinfor().setSypolicystartdate(policystartdate);
		insuranceDetails.getBaseinfor().setJqpolicystartdate(policystartdate);
		insuranceDetails.getUserinfor().setUserAction("PremiumCourtVehicleSubmit");
		List<Coverageinfor> list=new ArrayList<Coverageinfor>();
		Coverageinfor coverageinfor=new Coverageinfor();
		list.add(coverageinfor);
		insuranceDetails.setCoverageinfors(list);
		insuranceDetails.getCoverageinfors().get(0).setInsrnccode("030101");//默认选择车损险，三者责任险，交强
		insuranceDetails.getCoverageinfors().get(0).setSuminsured("1");
        sinosafeinterface.performSinosafeOprations(insuranceDetails);
        if(!insuranceDetails.getInterfaceslogsWithBLOBs().getResponsecode().equals("C00000000")){
        	insuranceDetails.getCoverageinfors().get(0).setInsrnccode(null);
        	sinosafeinterface.performSinosafeOprations(insuranceDetails);//如果以上报价出错，那么默认选择三者险和交强险
        }*/
		insuranceDetails=dataManageService.updateVehicleData(insuranceDetails);
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		logger.info("提交车辆信息结束，订单号："+insuranceDetails.getBaseinfor().getOrderno());
		return insuranceDetails;
	}

	/**
	 * 跳转到车辆信息页面 孙小东
	 */
	public InsuranceDetailsVO goToVehicleinfor(HttpServletRequest httprequest) {
		Userinfor user=null;
		String orderNo=httprequest.getParameter("orderNo");
		logger.info("提交车辆信息开始，订单号："+orderNo);
		InsuranceDetailsVO insuranceDetails=null;
		HttpSession session=httprequest.getSession();
	    int lastImplementPage=2;//代表用户操作的当前页,将其存放入数据库
		String lcNo = httprequest.getParameter("lcno");
		String dptCode = httprequest.getParameter("dptcode");
		String phoneNo = httprequest.getParameter("phoneNo");
		String deptAddress=httprequest.getParameter("deptAddress");
		String isagentshare = httprequest.getParameter("isagentshare");
		//如果从从首页到登录再跳转过来需要转码，先登录后调到首页再过来则不需要转码
		if("get".equalsIgnoreCase(httprequest.getMethod())){
			try {
				lcNo = new String(lcNo.getBytes("iso8859-1"), "utf-8");
				deptAddress = new String(deptAddress.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				StringWriter sw = new StringWriter();  
				 e.printStackTrace(new PrintWriter(sw, true));  
				 String str = sw.toString();
				logger.error("goToVehicleinfor 字符编码异常： "+str);
			}
		}
		
		if (StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null) {
			insuranceDetails = (InsuranceDetailsVO) session
					.getAttribute(orderNo + "insuranceDetailsVO");
		} else if (insuranceDetails == null) {
			InsuranceDetailsVO insuranceDetailsVo = ProcessData
					.initInsuranceDetailsVO();
			insuranceDetails = insuranceDetailsVo;
		}
		//Start 获取登录用户的信息，并给大对象赋值
		if(session.getAttribute("loginUser")!=null){
			user=(Userinfor)session.getAttribute("loginUser");
			int pageFlag = insuranceDetails.getUserinfor().getPageFlag();
			user.setPageFlag(pageFlag);
			insuranceDetails.setUserinfor(user);			
			insuranceDetails.getBaseinfor().setUserinforid(user.getUserid());			
			
		}
		//End 获取登录用户的信息，并给大对象赋值
		if(StringUtils.checkStringEmpty(dptCode)){
		insuranceDetails.getBaseinfor().setDeptno(dptCode);
		}
		//insuranceDetails.getBaseinfor().setProvinceName(provinceName);
		//insuranceDetails.getBaseinfor().setCityName(cityName);
		insuranceDetails.getBaseinfor().setDeptAddress(deptAddress);
		if(isagentshare !=null && isagentshare!=""){
			insuranceDetails.getBaseinfor().setIsagentshare(Integer.parseInt(isagentshare));
		}else{
			insuranceDetails.getBaseinfor().setIsagentshare(Integer.parseInt("0"));
		}
		insuranceDetails.getVhlinfor().setPhoneno(phoneNo);
		insuranceDetails.getVhlinfor().setLcnno(lcNo);
		insuranceDetails.getBaseinfor().setLastImplementPage(lastImplementPage);
		int pageFlag = insuranceDetails.getUserinfor().getPageFlag();
		/*页面刷新url的时候回调到index页面，还是登陆状态pageFlag还是存在的，
			不过这时orderNo没有了，所以条件判断判断一下orderNo*/
		if("".equals(orderNo) || pageFlag<2){ 
			insuranceDetails = dataManageService
					.savefirstScreenData(insuranceDetails);
			insuranceDetails.getUserinfor().setPageFlag(2); //首次进入车辆信息(vehicleinfor)页面,将pageFlag更改为2
			//根据手机号码查出是否为代理人，是的话带出代理人优惠码
			String  phoneno = insuranceDetails.getVhlinfor().getPhoneno();
			Userinfor userinfor = dataManageService.selectUserinfor(phoneno);
			if(userinfor!=null){
				String couponCode = userinfor.getCouponCode();
				if(couponCode!=null && couponCode!=""){
					insuranceDetails.getUserinfor().setCouponCode(couponCode);
					logger.info("根据手机号查到是代理人手机号,优惠码为:"+couponCode);
				}
			}else{
				logger.info("根据手机号查询结束,不是代理人手机号！");
			}
			
			//第一次跳转根据车牌号查出续保的单子
			if(lcNo!=null&&!lcNo.equals("*-*")){
				//封装数据调用华安接口
				insuranceDetails.getUserinfor().setUserAction("Renewalinfor");
				insuranceDetails = sinosafeinterface.performSinosafeOprations(insuranceDetails);
				if("10".equals(insuranceDetails.getInterfaceslogsWithBLOBs().getInterfacesstatu())){
					insuranceDetails.getBaseinfor().setOrigFlg("1");
				}else{
					insuranceDetails.getBaseinfor().setOrigFlg("0");
				}
				RadarInfor radarInfor = new RadarInfor();
				ChineseTransforPingyin ctop = new ChineseTransforPingyin();//汉字转拼音
				if(insuranceDetails.getVhlinfor().getDrvowner()!=null && insuranceDetails.getVhlinfor().getDrvowner()!=""){
					radarInfor.setPolicyKnownSS(LudiConstants.INSURED);//是否为续保的单子
					//保单在华安去年承保省份
				    String [] dept = insuranceDetails.getBaseinfor().getDeptAddress().split(",");
			        String deptAdress = dept[0];
				    if(deptAdress!=null && deptAdress!=""){
				    	radarInfor.setPolicyLastYearProvince(ctop.chinsesToPinyin(deptAdress.substring(0, deptAdress.length()-1)));
			        }
				}else{
					radarInfor.setPolicyKnownSS(LudiConstants.NOT_INSURED);
				}
				insuranceDetails.setRadarinfor(radarInfor);
			}else{
				insuranceDetails.getBaseinfor().setOrigFlg("0");//将续保标志设为0
			}
			
		}else{
			insuranceDetails = dataManageService
					.updatefirstScreenData(insuranceDetails);
		}		
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		logger.info("跳转到车辆信息页面结束，订单号："+insuranceDetails.getBaseinfor().getOrderno());
		return insuranceDetails;
	}

	/**
	 * 获取行驶区域代码 孙小东
	 */
	public List<Dptinfor> getAlldeptinfor() {
		List<Dptinfor> dptinfors=dmsUtility.getAlldeptinfor();
		return dptinfors;
	}

	/**
	 * 通过OCR自动识别行驶证信息 孙小东
	 */
	public String getVehicleinforByOCR(MultipartFile myfile,String imgdata) {
		String result = "";
		if(!StringUtils.checkStringEmpty(imgdata)){
		byte[] bs = null;
		BASE64Encoder encode = new BASE64Encoder();
		if (!myfile.isEmpty()) {
			try {
				bs = myfile.getBytes();
			} catch (IOException e) {
				StringWriter sw = new StringWriter();  
				 e.printStackTrace(new PrintWriter(sw, true));  
				 String str = sw.toString();
				logger.error("上传图片转化为字节数组报错，错误消息为 ： "+str);
			}
		}
		if (bs != null && bs.length > 204800) {
			logger.info("文件大小大于200k，需要进行压缩");
			bs = compress(bs);
		}
		imgdata = encode.encode(bs);
		}
		logger.info("图片字节，转换为Base64编码,编码结果为： "+imgdata);
		Map<String, String> headers = new HashMap<String, String>();
		Map<String, String> querys = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE "
				+ SysConstantsConfig.OCR_APPCODE);
		String bodys = "{\"inputs\":[{\"image\":{\"dataType\":50,\"dataValue\":\""
				+ imgdata + " \"}}]}";
		try {
			HttpResponse response = Httputils
					.doPost(SysConstantsConfig.OCR_URL_ADDRESS,
							SysConstantsConfig.OCR_PATH, "POST", headers,
							querys, bodys);
			if (response != null) {
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();  
			 e.printStackTrace(new PrintWriter(sw, true));  
			 String str = sw.toString();
			logger.error("发送图片数据到服务器识别异常 ： "+str);
		}
		logger.info("行驶证识别完毕，识别结果为： "+result);
		return result;

	}

	/**
	 * 压塑图片 孙小东 
	 * @param bs
	 * @return
	 */
	public byte[] compress(byte[] bs) {
		if (bs != null) {
			InputStream input = new ByteArrayInputStream(bs);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				Thumbnails.of(input).size(1024, 768).toOutputStream(out);
				bs = out.toByteArray();
			} catch (IOException e) {				
				StringWriter sw = new StringWriter();  
				 e.printStackTrace(new PrintWriter(sw, true));  
				 String str = sw.toString();
				 logger.error("压缩图片异常"+str);
			}
		}
		return bs;
	}
}
