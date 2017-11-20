package com.quicksure.mobile.serviceImpl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.constants.LudiConstants;
import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.dao.CarBrandsMapper;
import com.quicksure.mobile.dao.RadarInforMapper;
import com.quicksure.mobile.dao.VhlinforMapper;
import com.quicksure.mobile.dms.PremiumDataManageService;
import com.quicksure.mobile.dms.VHLDataManageService;
import com.quicksure.mobile.dmsutility.DMSUtility;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.CarBrands;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.Dptinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.RadarInfor;
import com.quicksure.mobile.entity.Vhlinfor;
import com.quicksure.mobile.service.PremiumCountService;
import com.quicksure.mobile.sinosafefactory.RadarinforInterface;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;
import com.quicksure.mobile.utility.ChineseTransforPingyin;
import com.quicksure.mobile.utility.RadarinforNeedDateUtil;
import com.quicksure.mobile.utility.StringUtils;

@Service("premiumCountService")
public class PremiumCountServiceImpl implements PremiumCountService {

	private static final Logger logger = Logger.getLogger(PremiumCountServiceImpl.class);
	@Resource
	private Sinosafeinterface sinosafeinterface;
	@Resource
	private RadarinforInterface radarinforInterface;
	@Resource 
	private VHLDataManageService dataManageService;
	@Resource
	private PremiumDataManageService premiumDataManageService;
	@Resource
	private DMSUtility dmsUtility;
	@Resource
	private BaseinforMapper baseinforMapper;
	@Resource
	private RadarInforMapper radarInforMapper;
	@Resource 
	private CarBrandsMapper carBrandsMapper;
	@Resource
	private VhlinforMapper vhlinforMapper;
	/**
	 * 试算的业务
	 */
	
	@SuppressWarnings("unused")
	public InsuranceDetailsVO PremiumCount(Vhlinfor vhlinfor,Baseinfor baseinfor,List<Coverageinfor> coverageinfors,HttpServletRequest httprequest) {
		String orderNo=httprequest.getParameter("orderNo");
		HttpSession session=httprequest.getSession();
		String sypolicystartdate =null;//商业起保时间
		String sypolicyendDate="";//商业到期时间
		String jqpolicystartdate =null;//交强起保时间
		String jqpolicyendDate="";//交强到期时间
		String drvowner="";//车主姓名
		InsuranceDetailsVO insuranceDetails=null;
		//玻璃类型
		 String glassType = "";
		 //给予各个险种配置险种常量代码
		 if(coverageinfors.size()>0){
			 for(int i=0;coverageinfors.size()>i;i++){
				 if(coverageinfors.get(i).getInsrnccode()!=null){
				 if(coverageinfors.get(i).getInsrnccode().equals("102")){
					 coverageinfors.get(i).setInsrnccode(LudiConstants.THREEPARTYINSUEANCE);
				 }else if (coverageinfors.get(i).getInsrnccode().equals("101")){
					 coverageinfors.get(i).setInsrnccode(LudiConstants.LOSSINSUEANCE);
				 }else if(coverageinfors.get(i).getInsrnccode().equals("357")){
					 coverageinfors.get(i).setInsrnccode(LudiConstants.COMPULSORYINSURANCE);
				 }else if(coverageinfors.get(i).getInsrnccode().equals("104")){
					 coverageinfors.get(i).setInsrnccode(LudiConstants.DRIVERINSUEANCE);
				 }else if(coverageinfors.get(i).getInsrnccode().equals("105")){
					 coverageinfors.get(i).setInsrnccode(LudiConstants.PASSENGERINSUEANCE);
				 }else if(coverageinfors.get(i).getInsrnccode().equals("103")){
					 coverageinfors.get(i).setInsrnccode(LudiConstants.ROBBERYINSUEANCE);
				 }else if(coverageinfors.get(i).getInsrnccode().equals("108")){
					 coverageinfors.get(i).setInsrnccode(LudiConstants.SPONTANEOUSCOMBUSTIONINSUEANCE);
				 }else if(coverageinfors.get(i).getInsrnccode().equals("107")){
					 glassType=coverageinfors.get(i).getSuminsured();
					 coverageinfors.get(i).setSuminsured("1");//设置保额为1
					 coverageinfors.get(i).setBulletGlass("0");//设置默认值不是防爆玻璃
					 coverageinfors.get(i).setInsrnccode(LudiConstants.GLASSINSUEANCE);
				 }else if(coverageinfors.get(i).getInsrnccode().equals("115")){
					 coverageinfors.get(i).setInsrnccode(LudiConstants.NOTFOUNDINSUEANCE);
				 }else if(coverageinfors.get(i).getInsrnccode().equals("116")){
					 coverageinfors.get(i).setInsrnccode(LudiConstants.APPOINTINSUEANCE); 
				 }
			  }else{
				sypolicystartdate=coverageinfors.get(i).getSypolicystartdate();
				jqpolicystartdate=coverageinfors.get(i).getJqpolicystartdate();
				orderNo = 	coverageinfors.get(i).getBaseinfororderno();	 
			  }
			  
			 }		 
		 }
		if(StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null){
			 insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo+"insuranceDetailsVO");
			 if(insuranceDetails.getBaseinfor()!=null){
			 insuranceDetails.getBaseinfor().setJqpremium(null);
			 insuranceDetails.getBaseinfor().setSypolicyenddate(null);
			 insuranceDetails.getBaseinfor().setJqpolicyenddate(null);
			 insuranceDetails.getBaseinfor().setSypremium(null);
			 insuranceDetails.getBaseinfor().setJqpolicystartdate(null);
			 }			 
			 if(insuranceDetails.getTaxinfor()!=null){
				 insuranceDetails.getTaxinfor().setSumuptax(null);
				 insuranceDetails.getTaxinfor().setCurrenttax(null);
			 }
			 if(insuranceDetails.getVhlinfor()!=null){
				 if(insuranceDetails.getVhlinfor().getChgownerflag()!=null){
					 if(insuranceDetails.getVhlinfor().getChgownerflag().equals("0")){
						 insuranceDetails.getVhlinfor().setTransferdate(null);
					 }
				 }
				 
			 }
			}
		if (insuranceDetails == null) {			
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetails=insuranceDetailsVo;
		}

		 
		 //给予起保时间加入时分秒,后续前端控件有就删除     给予请求报文的时间都为T+1默认时间
		    Date date = new Date();
		    Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
	        date = calendar.getTime();
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        String dateString = formatter.format(date);
		 if(!"".equals(sypolicystartdate) && sypolicystartdate!=null && sypolicystartdate.length()<12 ){
			 String lcn = insuranceDetails.getVhlinfor().getLcnno();
			    if("*-*".equals(lcn)){
			    	sypolicystartdate=sypolicystartdate+" 00:00:00";
					 insuranceDetails.getBaseinfor().setSypolicystartdate(sypolicystartdate);
			    }else{
			    	sypolicystartdate=sypolicystartdate+" 00:00:00";
					 insuranceDetails.getBaseinfor().setSypolicystartdate(sypolicystartdate);
			    	//sypolicystartdate=dateString+" 00:00:00";
					//insuranceDetails.getBaseinfor().setSypolicystartdate(sypolicystartdate);   //默认去当前时间+1天的话，提前续保的单出不了。 孙小东
			    }
		 }
		 if(!"".equals(jqpolicystartdate) && jqpolicystartdate!=null && jqpolicystartdate.length()<12){
			 String lcn = insuranceDetails.getVhlinfor().getLcnno();
			    if("*-*".equals(lcn)){
			    	jqpolicystartdate=jqpolicystartdate+" 00:00:00";
					 insuranceDetails.getBaseinfor().setJqpolicystartdate(jqpolicystartdate);
			    }else{
			    	jqpolicystartdate=jqpolicystartdate+" 00:00:00";
					 insuranceDetails.getBaseinfor().setJqpolicystartdate(jqpolicystartdate);
			    	//jqpolicystartdate=dateString+" 00:00:00";
			    	// insuranceDetails.getBaseinfor().setJqpolicystartdate(jqpolicystartdate);//默认去当前时间+1天的话，核心有些单子会报已经投保记录  李长立  --核心问题，年后处理
			   }
			 
		 }
		 if(insuranceDetails.getBaseinfor()!=null&&insuranceDetails.getBaseinfor().getSypolicystartdate()!=null&&insuranceDetails.getBaseinfor().getSypolicystartdate().length()>19){
        	 insuranceDetails.getBaseinfor().setSypolicystartdate(insuranceDetails.getBaseinfor().getSypolicystartdate().substring(0, insuranceDetails.getBaseinfor().getSypolicystartdate().length()-2)); 
         }
			 if(insuranceDetails.getVhlinfor()!=null){
				 if(insuranceDetails.getVhlinfor().getBrandcode()!=null){
					 insuranceDetails.getVhlinfor().setGlasstype(glassType);
				 }else{
					 insuranceDetails.setVhlinfor(vhlinfor);
					 insuranceDetails.getVhlinfor().setGlasstype(glassType);
				 }
			 }
		 
		 //第一个元素的起保日期是否为空，不为空就移除
		 if(coverageinfors.get(0).getSypolicystartdate()!=null || "".equals(coverageinfors.get(0).getSypolicystartdate())){
			 coverageinfors.remove(0);	 
		 }
		 if(baseinfor.getJqpolicystartdate()!=null || baseinfor.getJqpolicystartdate()!=""){
			 baseinfor.setJqpolicystartdate("");
			 baseinfor.setJqpolicyenddate("");
		 }
		 if(baseinfor.getSypolicystartdate()!=null || baseinfor.getSypolicystartdate()!=""){
			 baseinfor.setSypolicystartdate("");
			 baseinfor.setSypolicyenddate("");
		 }
		 insuranceDetails.setCoverageinfors(coverageinfors);
		 insuranceDetails=ProcessData.setObjValuetoinsuranceDetails(baseinfor,insuranceDetails);
        
		 insuranceDetails.getUserinfor().setUserAction("PremiumCount"); //封装模板
         if(insuranceDetails.getVhlinfor().getDrvowner()!=null){
        	 logger.info("试算获取前台的数据车主名称为："+insuranceDetails.getVhlinfor().getDrvowner());
        	 drvowner = insuranceDetails.getVhlinfor().getDrvowner();
        	 insuranceDetails.getBaseinfor().setOwnersname(drvowner);
        	 //把车主姓名更新进base表
        	 premiumDataManageService.updatePremium(insuranceDetails);
         }
         
        
        //封装数据调用华安接口
        sinosafeinterface.performSinosafeOprations(insuranceDetails);
        //费用保留两位小数,不足不0
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        for(Coverageinfor cover:insuranceDetails.getCoverageinfors()){
        	if(cover.getSuminsured()!=null&&!cover.getSuminsured().equals("")&&!cover.getSuminsured().equals("0")
        	   &&!cover.getSuminsured().equals("0.00")&&!cover.getSuminsured().equals(LudiConstants.THREE_COVERAGE_5W)
        	   &&!cover.getSuminsured().equals(LudiConstants.THREE_COVERAGE_10W)&&!cover.getSuminsured().equals(LudiConstants.THREE_COVERAGE_20W)
        	   &&!cover.getSuminsured().equals(LudiConstants.THREE_COVERAGE_30W)&&!cover.getSuminsured().equals(LudiConstants.THREE_COVERAGE_50W)
        	   &&!cover.getSuminsured().equals(LudiConstants.THREE_COVERAGE_100W)){
        		cover.setSuminsured(decimalFormat.format(Float.parseFloat(cover.getSuminsured())));
        	}
        	if(cover.getPremium()!=null&&!cover.getPremium().equals("")&&!cover.getPremium().equals("0")&&!cover.getPremium().equals("0.00")){
        		cover.setPremium(decimalFormat.format(Float.parseFloat(cover.getPremium())));
        	}
        	if(cover.getBasepremium()!=null&&!cover.getBasepremium().equals("")&&!cover.getBasepremium().equals("0")&&!cover.getBasepremium().equals("0.00")){
        		cover.setBasepremium(decimalFormat.format(Float.parseFloat(cover.getBasepremium())));
        	}
        	if(cover.getBeforepremium()!=null&&!cover.getBeforepremium().equals("")&&!cover.getBeforepremium().equals("0")&&!cover.getBeforepremium().equals("0.00")){
        		cover.setBeforepremium(decimalFormat.format(Float.parseFloat(cover.getBeforepremium())));
        	}
        	if(cover.getCyl15()!=null&&!cover.getCyl15().equals("")&&!cover.getCyl15().equals("0")&&!cover.getCyl15().equals("0.00")){
        		cover.setCyl15(decimalFormat.format(Float.parseFloat(cover.getCyl15())));
        	}
        	if(cover.getNyl12()!=null&&!cover.getNyl12().equals("")&&!cover.getNyl12().equals("0")&&!cover.getNyl12().equals("0.00")){
        		cover.setNyl12(decimalFormat.format(Float.parseFloat(cover.getNyl12())));
        	}else{
        		cover.setNyl12("0.00");
        	}
        	if(cover.getRiskpremium()!=null&&!cover.getRiskpremium().equals("")&&!cover.getRiskpremium().equals("0")&&!cover.getRiskpremium().equals("0.00")){
        		cover.setRiskpremium(decimalFormat.format(Float.parseFloat(cover.getRiskpremium())));
        	}
        }
        if(insuranceDetails.getBaseinfor()!=null){
        	Baseinfor bb=insuranceDetails.getBaseinfor();
        	if(bb.getTotalPremium()!=null&&!bb.getTotalPremium().equals("")&&!bb.getTotalPremium().equals("0")&&!bb.getTotalPremium().equals("0.00")){
        		bb.setTotalPremium(decimalFormat.format(Float.parseFloat(bb.getTotalPremium())));
        	}
        	if(bb.getJqpremium()!=null&&!bb.getJqpremium().equals("")&&!bb.getJqpremium().equals("0")&&!bb.getJqpremium().equals("0.00")){
        		bb.setJqpremium(decimalFormat.format(Float.parseFloat(bb.getJqpremium())));
        	}
        	insuranceDetails.setBaseinfor(bb);
        }
        if(insuranceDetails.getTaxinfor()!=null){
        	if(insuranceDetails.getTaxinfor().getSumuptax()!=null&&!insuranceDetails.getTaxinfor().getSumuptax().equals("")){
        		insuranceDetails.getTaxinfor().setSumuptax(decimalFormat.format(Float.parseFloat(insuranceDetails.getTaxinfor().getSumuptax())));
        	}
        }
        
		 session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		 session.setAttribute("errorCode", insuranceDetails.getUserinfor().getErrorCode());
		 session.setAttribute("errorMessage", insuranceDetails.getUserinfor().getErrorMessage());
		return insuranceDetails;
	}


   
//	public String saveBaseDate(Baseinfor baseinfor,
//			HttpServletRequest httprequest) {
//		HttpSession session=httprequest.getSession();
//		InsuranceDetailsVO insuranceDetails=null;
//		if(session.getAttribute("insuranceDetailsVO")!=null){
//		 insuranceDetails = (InsuranceDetailsVO)session.getAttribute("insuranceDetailsVO");
//		}
//		if (insuranceDetails == null) {
//			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
//			insuranceDetails=insuranceDetailsVo;
//		}
//		insuranceDetails=ProcessData.setObjValuetoinsuranceDetails(baseinfor,insuranceDetails);
//		insuranceDetails=dataManageService.updateBaseData(insuranceDetails);
//		session.setAttribute("insuranceDetailsVO", insuranceDetails);
//		return "";
//	}



	public InsuranceDetailsVO savePremiumInfor(ModelMap modelMap,HttpServletRequest httprequest) {
		String orderNo = httprequest.getParameter("orderNo");
		HttpSession session = httprequest.getSession();
		int lastImplementPage=4;//代表用户操作的当前页,跳转到人员信息页面
		InsuranceDetailsVO insuranceDetails=null;
		if(StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null){
		 insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo+"insuranceDetailsVO");
		}
		if (insuranceDetails == null) {
			InsuranceDetailsVO insuranceDetailsVo = ProcessData.initInsuranceDetailsVO();
			insuranceDetails = insuranceDetailsVo;
		}
		insuranceDetails.getBaseinfor().setLastImplementPage(lastImplementPage);
		if(insuranceDetails.getUserinfor().getPageFlag()<4){//返回保费计算重新提交的数据
			insuranceDetails = premiumDataManageService.savePremiuminfor(insuranceDetails);
			insuranceDetails.getUserinfor().setPageFlag(4); //首次进入人员信息(personinfor)页面,将pageFlag状态改为4
		}else{
			insuranceDetails = premiumDataManageService.updatePremiuminfor(insuranceDetails);
		}
		
		for(Coverageinfor coverage:insuranceDetails.getCoverageinfors()){
			if("030107".equals(coverage.getInsrnccode())){
				if("0".equals(coverage.getSuminsured())){
					coverage.setSuminsured("");
				}
			}
			if("0.00".equals(coverage.getNyl12())){
				coverage.setNyl12("");
			}
		}
		if("0".equals(insuranceDetails.getBaseinfor().getJqpremium())){
			insuranceDetails.getBaseinfor().setJqpremium(null);
		}
		if("0".equals(insuranceDetails.getBaseinfor().getTaxpremium())){
			insuranceDetails.getBaseinfor().setTaxpremium(null);
		}		
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		session.removeAttribute("appCoypCheckbox");
		session.removeAttribute("insureCopyCheckbox");
		session.removeAttribute("deliveryCopyCheckbox");
		return insuranceDetails;
	}

    /**
	 * 雷达的试算
	 */

	@SuppressWarnings("static-access")
	@Override
	public InsuranceDetailsVO RadarPremiumCount(InsuranceDetailsVO premiumCount,RadarInfor radarinfor,HttpServletRequest httprequest) {
		
		HttpSession session = httprequest.getSession();
		InsuranceDetailsVO insuranceDetails = null;
		Baseinfor baseinfor = null;
		ChineseTransforPingyin ctop = new ChineseTransforPingyin();//汉字转拼音
		RadarinforNeedDateUtil rnd = new RadarinforNeedDateUtil(); //将时间字符串转换成yyyy/MM/dd 'T' HH:mm:ss +HH:mm
		String orderNo = premiumCount.getBaseinfor().getOrderno();//获取华安试算订单号
		int record;  //高风险车型记录
		radarinfor.setBaseinforOrderNo(orderNo);  //订单号
        //华安保费-总计
	    if(premiumCount.getBaseinfor().getSypremium() !=null && premiumCount.getBaseinfor().getSypremium()!=""
	    	&& premiumCount.getBaseinfor().getJqpremium()!=null && premiumCount.getBaseinfor().getJqpremium()!=""
	    	  && premiumCount.getTaxinfor().getCurrenttax()!=null && premiumCount.getTaxinfor().getCurrenttax()!="" ){
	    	     float sypremium = Float.parseFloat(premiumCount.getBaseinfor().getSypremium());
	 	         float jqpremium = Float.parseFloat(premiumCount.getBaseinfor().getJqpremium());
	 	         float currenttax= Float.parseFloat(premiumCount.getTaxinfor().getCurrenttax());
	 	         float totalPrmium = sypremium + jqpremium + currenttax;
	 	         radarinfor.setSSPremTotal(String.valueOf(totalPrmium));
	    }else{
	    	     radarinfor.setSSPremTotal(premiumCount.getBaseinfor().getSypremium()); //只投保商业险的总保费
	    }
	     baseinfor = baseinforMapper.selectByPrimaryKey(orderNo);//根据订单号查出订单详情
		 //保单首次报价日期
		 String policyFirstQuoteDate = baseinfor.getCreatetime().substring(0, 19);
		 radarinfor.setPolicyFirstQuoteDate(rnd.toStandard(policyFirstQuoteDate));
		 //保单末次报价日期
		 String policyLastQuoteDate = baseinfor.getUpdatetime().substring(0, 19);
		 radarinfor.setPolicyLastQuoteDate(rnd.toStandard(policyLastQuoteDate));
		 //获取华安险种报价集合
		 List<Coverageinfor> coverageinfor =premiumCount.getCoverageinfors(); 
		 if(coverageinfor.size()>0){
		 for(int i=0;coverageinfor.size()>i;i++){
			 if(coverageinfor.get(i).getInsrnccode()!=null){
				if(coverageinfor.get(i).getInsrnccode().equals("0357")){//交强
					 //交强险保费
					 radarinfor.setJqpremium(String.valueOf(Float.parseFloat(premiumCount.getBaseinfor().getJqpremium())));
					 //华安保费-“车船税”
					 radarinfor.setSSPremGovtVehicleTax(premiumCount.getTaxinfor().getCurrenttax());
				 }else if(coverageinfor.get(i).getInsrnccode().equals("030101")){//车损
					     //车损险应缴保费
					     radarinfor.setSSPremMOD(coverageinfor.get(i).getPremium());
					     //车损险保额
					     String cardamageSuminsured = coverageinfor.get(i).getSuminsured();
					     radarinfor.setCardamageSuminsured(cardamageSuminsured);
					     //车损基准保费（车损折前保费）
					     radarinfor.setPTMODStandardRiskPremium(coverageinfor.get(i).getBeforepremium());
					 if(coverageinfor.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
						 //车损不计免赔保费
						 radarinfor.setSSPremDedWaiverMOD(coverageinfor.get(i).getNyl12());
					 }
				 }else if(coverageinfor.get(i).getInsrnccode().equals("030102")){//三者
					     //三者保费
					     radarinfor.setSSPremVTPL(coverageinfor.get(i).getPremium());
					 if(coverageinfor.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
						 //三者不计免赔保费
						 radarinfor.setSSPremDedWaiverVTPL(coverageinfor.get(i).getNyl12());
					 }
				 }else if(coverageinfor.get(i).getInsrnccode().equals("030103")){//盗抢
					     //盗抢险应缴保费
					     radarinfor.setSSPremVehicleTheft(coverageinfor.get(i).getPremium());
					     //盗抢险保额
					     String daqSuminsured = coverageinfor.get(i).getSuminsured();
					     radarinfor.setDaqSuminsured(daqSuminsured);
					 if(coverageinfor.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
						 //盗抢险不计免赔保费
						 radarinfor.setSSPremDedWaiverTheft(coverageinfor.get(i).getNyl12());
					 } 
				 }else if(coverageinfor.get(i).getInsrnccode().equals("030104")){//司机
					     //司机险应缴保费
					     radarinfor.setSSPremDriverLiab(coverageinfor.get(i).getPremium());
					 if(coverageinfor.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
						 //司机不计免赔保费
						 radarinfor.setSSPremDedWaiverDriverLiab(coverageinfor.get(i).getNyl12());
					 } 
				 }else if(coverageinfor.get(i).getInsrnccode().equals("030105")){//乘客
					     //乘客险应缴保费
					     radarinfor.setSSPremPassengerLiab(coverageinfor.get(i).getPremium());
					 if(coverageinfor.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
						 //乘客险不计免赔保费
						 radarinfor.setSSPremDedWaiverPssgrLiab(coverageinfor.get(i).getNyl12());
					 } 
				 }else if(coverageinfor.get(i).getInsrnccode().equals("030107")){//玻璃
					     //玻璃险应缴保费
					     radarinfor.setSSPremGlass(coverageinfor.get(i).getPremium());
				 }else if(coverageinfor.get(i).getInsrnccode().equals("030108")){//自燃
					     //自燃险应缴保费
					     radarinfor.setSSPremCombustion(coverageinfor.get(i).getPremium());
					     //自燃险保额
					     String zraSuminsured = coverageinfor.get(i).getSuminsured();
					     radarinfor.setZraSuminsured(zraSuminsured);
					 if(coverageinfor.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
						 //自燃险不计免赔保费
						 radarinfor.setSSPremDedWaiverCombust(coverageinfor.get(i).getNyl12());
					 } 
				 }
			 }

		   }	
	    }
		if(StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null){
		 insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo+"insuranceDetailsVO");
		}
		if (insuranceDetails == null) {
			InsuranceDetailsVO insuranceDetailsVo = ProcessData.initInsuranceDetailsVO();
			insuranceDetails = insuranceDetailsVo;
		}
		if(insuranceDetails.getBaseinfor()!=null){
			if(insuranceDetails.getBaseinfor().getQuoteno()!=null){
				if(insuranceDetails.getCoverageinfors().size()>0){
					List<Coverageinfor> coverageinfors = insuranceDetails.getCoverageinfors();
					 if(coverageinfors.size()>0){
						 radarinfor.setIsChoiceCombustion(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceCTPL(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceDedWaiverCombust(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceDedWaiverDriver(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceDedWaiverMOD(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceDedWaiverPassenger(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceDedWaiverTheft(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceDedWaiverVTPL(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceDriver(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceGlass(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceMOD(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoicePassenger(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceVTPL(LudiConstants.NOT_INSURED);
						 radarinfor.setIsChoiceTheft(LudiConstants.NOT_INSURED);
						 for(int i=0;coverageinfors.size()>i;i++){
							 if(coverageinfors.get(i).getInsrnccode()!=null){
								 if(coverageinfors.get(i).getInsrnccode().equals("0357")){//交强
									 radarinfor.setIsChoiceCTPL(LudiConstants.INSURED);
								 }else if(coverageinfors.get(i).getInsrnccode().equals("030101")){//车损
									 radarinfor.setIsChoiceMOD(LudiConstants.INSURED);
									 if(coverageinfors.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
										 radarinfor.setIsChoiceDedWaiverMOD(LudiConstants.INSURED);
									 }
								 }else if(coverageinfors.get(i).getInsrnccode().equals("030102")){//三者
									 if(coverageinfors.get(i).getSuminsured().equals(LudiConstants.THREE_COVERAGE_5W)){
								    	 radarinfor.setIsChoiceVTPL(LudiConstants.THREE_COVERAGE_MOMEY_5W.substring(0,LudiConstants.THREE_COVERAGE_MOMEY_5W.indexOf(".")));//三者保额
								     }else if(coverageinfors.get(i).getSuminsured().equals(LudiConstants.THREE_COVERAGE_10W)){
								    	 radarinfor.setIsChoiceVTPL(LudiConstants.THREE_COVERAGE_MOMEY_10W);
								     }else if(coverageinfors.get(i).getSuminsured().equals(LudiConstants.THREE_COVERAGE_20W)){
								    	 radarinfor.setIsChoiceVTPL(LudiConstants.THREE_COVERAGE_MOMEY_20W);
								     }else if(coverageinfors.get(i).getSuminsured().equals(LudiConstants.THREE_COVERAGE_30W)){
								    	 radarinfor.setIsChoiceVTPL(LudiConstants.THREE_COVERAGE_MOMEY_30W);
								     }else if(coverageinfors.get(i).getSuminsured().equals(LudiConstants.THREE_COVERAGE_50W)){
								    	 radarinfor.setIsChoiceVTPL(LudiConstants.THREE_COVERAGE_MOMEY_50W);
								     } else if(coverageinfors.get(i).getSuminsured().equals(LudiConstants.THREE_COVERAGE_100W)){
									    	 radarinfor.setIsChoiceVTPL(LudiConstants.THREE_COVERAGE_MOMEY_100W);
								     }
									 if(coverageinfors.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
										 radarinfor.setIsChoiceDedWaiverVTPL(LudiConstants.INSURED);
									 }
								 }else if(coverageinfors.get(i).getInsrnccode().equals("030103")){//盗抢
									     radarinfor.setIsChoiceTheft(LudiConstants.INSURED);
									 if(coverageinfors.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
										 radarinfor.setIsChoiceDedWaiverTheft(LudiConstants.INSURED);
									 } 
								 }else if(coverageinfors.get(i).getInsrnccode().equals("030104")){//司机
									     radarinfor.setIsChoiceDriver(coverageinfors.get(i).getSuminsured().substring(0,coverageinfors.get(i).getSuminsured().indexOf(".")));//“司机座位责任险”保额
									 if(coverageinfors.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
										 radarinfor.setIsChoiceDedWaiverDriver(LudiConstants.INSURED);
									 } 
								 }else if(coverageinfors.get(i).getInsrnccode().equals("030105")){//乘客
									     radarinfor.setIsChoicePassenger(coverageinfors.get(i).getSuminsured().substring(0,coverageinfors.get(i).getSuminsured().indexOf(".")));//“乘客座位责任险”保额
									 if(coverageinfors.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
										 radarinfor.setIsChoiceDedWaiverPassenger(LudiConstants.INSURED);
									 } 
								 }else if(coverageinfors.get(i).getInsrnccode().equals("030107")){//玻璃
									 //判断是否进口车(635001:国产      635002：进口)
									 if("635001".equals(premiumCount.getVhlinfor().getCarstyle())){
										 radarinfor.setIsChoiceGlass(LudiConstants.INSURED);
									 }else if("635002".equals(premiumCount.getVhlinfor().getCarstyle())){
										 radarinfor.setIsChoiceGlass("2");
									 }
								 }else if(coverageinfors.get(i).getInsrnccode().equals("030108")){//自燃
									     radarinfor.setIsChoiceCombustion(LudiConstants.INSURED);
									 if(coverageinfors.get(i).getDeductibleflag().equals(LudiConstants.INSURED)){
										 radarinfor.setIsChoiceDedWaiverCombust(LudiConstants.INSURED);
									 } 
								 }
							 }
						 }		 
					 }
				}
				//保单首次投保日期
				 SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd 'T' HH:mm:ss +HH:mm");
				 String policyFirstStartDate = df.format(new Date());
				 radarinfor.setPolicyFirstStartDate(policyFirstStartDate);
				//保单数据是否在华安系统中可调取(根据车牌号查续保信息)
				if(insuranceDetails.getVhlinfor().getLcnno()!=null && 
						!("*-*").equals(insuranceDetails.getVhlinfor().getLcnno())){
					//封装数据调用华安接口
					insuranceDetails.getUserinfor().setUserAction("Renewalinfor");
					logger.info("----------开始调取华安接口-----------");
					insuranceDetails = sinosafeinterface.performSinosafeOprations(insuranceDetails);
					radarinfor.setPolicyKnownSS(LudiConstants.INSURED);
					//保单在华安去年承保省份
				    String [] dept = insuranceDetails.getBaseinfor().getDeptAddress().split(",");
			        String deptAdress = dept[0];
				    if(deptAdress!=null && deptAdress!=""){
					      radarinfor.setPolicyLastYearProvince(deptAdress);
			        }
				}else{
					radarinfor.setPolicyKnownSS(LudiConstants.NOT_INSURED);
				}
				/*//订单号
				radarinfor.setBaseinforOrderNo(insuranceDetails.getBaseinfor().getOrderno());*/
				double displacement =  Double.valueOf(insuranceDetails.getVhlinfor().getDisplacement().trim());
				int displacement1 = (int) (displacement*1000);
				//排量
				radarinfor.setJYDisplacement(String.valueOf(displacement1));
				    String idCard = insuranceDetails.getVhlinfor().getCertificateno();
			        Calendar cal = Calendar.getInstance();
			        String year = idCard.substring(6, 10);
			        int iCurrYear = cal.get(Calendar.YEAR);
			        int iAge = iCurrYear - Integer.valueOf(year);
			    //车主保单开始时年龄 
			    radarinfor.setOwnerAgeAtPolicyStart(String.valueOf(iAge)); 
			    //投保人保单开始时年龄（当前同车主）
			    radarinfor.setProposerAgeAtPolicyStart(String.valueOf(iAge));
			        String month = idCard.substring(10,12);
			        String day = idCard.substring(12,14);
			        String DateOfBirth = year+"-"+month+"-"+day;
			        //车主出生日期
			        radarinfor.setOwnerDOB(DateOfBirth); 
			        //投保人出生日期（当前同车主）
			        radarinfor.setProposerDOB(DateOfBirth);
			        String sCardNum = idCard.substring(16, 17);
			        String sGender="";
			        if (Integer.parseInt(sCardNum) % 2 != 0) {
			            sGender = "Male";//男
			        } else {
			            sGender = "Female";//女
			        }
			       //车主性别
			       radarinfor.setOwnerGender(sGender);
			       //投保人性别（当前同车主）
			       radarinfor.setProposerGender(sGender);
			       //精友-是否有ABS系统
			       String JYABSFlag = insuranceDetails.getVhlinfor().getAbsflag();
			       if(JYABSFlag != null && JYABSFlag.equals("1")){
			    	   radarinfor.setJYABSFlag(LudiConstants.INSURED);
			       }else{
			    	   radarinfor.setJYABSFlag(LudiConstants.NOT_INSURED);
			       }
			       String channel = insuranceDetails.getBaseinfor().getAgentCode();
			       //出单渠道
			       if(channel!=null && channel!=""){
			    	   radarinfor.setPolicyChannel(LudiConstants.AGENT_CHANNEL);
			       }else{
			    	   radarinfor.setPolicyChannel(LudiConstants.GENERAL_CHANNEL);
			       }
			       String sypolicystartdate = insuranceDetails.getBaseinfor().getSypolicystartdate();//商业险起期
			       String sypolicyEnddate = insuranceDetails.getBaseinfor().getSypolicyenddate(); //商业险止期
			       String registerdate = insuranceDetails.getVhlinfor().getRegisterdate();
			       //保单商业险起始日期
			       if(sypolicystartdate!=null && sypolicystartdate!=""){
			    	    radarinfor.setPolicyStartDateComm(rnd.toStandard(sypolicystartdate));//将时间字符串转换成yyyy/MM/dd 'T' HH:mm:ss +HH:mm
			       }
                   //投保车龄-保单起始时（保单起期-车辆登记日期）
			       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			       try {
					    Date firstDateTimeDate = format.parse(sypolicystartdate);
					    Date secondDateTimeDate = format.parse(registerdate);
					    long firstDateMilliSeconds = firstDateTimeDate.getTime();
			            long secondDateMilliSeconds = secondDateTimeDate.getTime();
			            long subDateMilliSeconds = firstDateMilliSeconds - secondDateMilliSeconds;
			            //毫秒转化为秒
			            int totalSeconds = (int) (subDateMilliSeconds/1000);
			            // 毫秒数转化为总天数
			            float days_remains = totalSeconds/(3600*24);
			            float month_remains = days_remains / 30;
			            int startCarYear = (int)Math.rint(month_remains);
			            radarinfor.setVehicleAgeatPolicyStartMonths(String.valueOf(startCarYear));
			    	} catch (ParseException e) {
					    e.printStackTrace();
			    	}
		           
			       //保单商业险终止日期
			       if(sypolicyEnddate !=null && sypolicyEnddate!=""){
			    	   radarinfor.setPTPolicyEndDateComm(rnd.toStandard(sypolicyEnddate));
			       }
			       //投保车龄-保单终止时（保单止期-车辆登记日期）
			       try {
					    Date firstDateTimeDate = format.parse(sypolicyEnddate);
					    Date secondDateTimeDate = format.parse(registerdate);
					    long firstDateMilliSeconds = firstDateTimeDate.getTime();
			            long secondDateMilliSeconds = secondDateTimeDate.getTime();
			            long subDateMilliSeconds = firstDateMilliSeconds - secondDateMilliSeconds;
			            //毫秒转化为秒
			            int totalSeconds = (int) (subDateMilliSeconds/1000);
			            // 毫秒数转化为总天数
			            float days_remains = totalSeconds/(3600*24);
			            float month_remains = days_remains / 365;
			            int endCarYear = (int)Math.rint(month_remains);
			            radarinfor.setVehicleAgeAtPolicyStartYrs(String.valueOf(endCarYear));
			    	} catch (ParseException e) {
					    e.printStackTrace();
			    	}
			       //投保车辆注册日期
			       String vehicleDateFirstRegistration = insuranceDetails.getVhlinfor().getRegisterdate();
			       if(vehicleDateFirstRegistration !=null && vehicleDateFirstRegistration!=""){
			    	   radarinfor.setVehicleDateFirstRegistration(rnd.toStandard(vehicleDateFirstRegistration));
			       }
			       String jqpolicystartdate = insuranceDetails.getBaseinfor().getJqpolicystartdate();//交强险起期
			       String jqpolicyEnddate = insuranceDetails.getBaseinfor().getJqpolicyenddate();//交强险止期
			       //保单交强险起始日期
			       if(jqpolicystartdate !=null && jqpolicystartdate!=""){
			    	   radarinfor.setPolicyStartDateCTPL(rnd.toStandard(jqpolicystartdate));
			       }
			       //保单交强险终止日期
			       if(jqpolicyEnddate!=null &&jqpolicyEnddate !=""){
			    	   radarinfor.setPTPolicyEndDateCTPL(rnd.toStandard(jqpolicyEnddate));
			       }
			       //投保步骤-目前投吧步骤
			       radarinfor.setScreenProcessCurrentStep("3");
			       //投保步骤-最远走到过哪一个投保步骤----""
			       radarinfor.setScreenProcessFurthestStep("");
			       String policyNumber = insuranceDetails.getVhlinfor().getSetNumber();//
			       //核定载客数
			       if(policyNumber!=null && policyNumber!=""){
			    	   radarinfor.setPTAllowedSeats(policyNumber);
			       }
			       //投保车辆玻璃类型
			       String vehicleCurrentGlassType = insuranceDetails.getVhlinfor().getGlasstype();
			       if(vehicleCurrentGlassType!=null && vehicleCurrentGlassType!=""){
			    	  if(vehicleCurrentGlassType.equals(LudiConstants.MADING_IN_CHINA)){
			    		  radarinfor.setVehicleCurrentGlassType(LudiConstants.INSURED_CHINA);
			    	  }else if(vehicleCurrentGlassType.equals(LudiConstants.IMPORT)){
			    		  radarinfor.setVehicleCurrentGlassType(LudiConstants.INSURED_IMPORT);
			    	  }
			       }
			       String brandName = premiumCount.getVhlinfor().getBrandName();
			       //获取车系
			       Vhlinfor vhlin = vhlinforMapper.selectByorderno(orderNo);
			       String familyName = vhlin.getFamilyKind();
			       //投保车是否“高风险车”
			       //radarinfor.setVehicleSSHighRiskGroup(LudiConstants.NOT_INSURED);
			       List<Map<String, Object>> vhlinforList = premiumCount.getVhlinfoList();
			       int recored = 0;  //高风险车型记录数
				   if(familyName!=null && familyName !="" && brandName !=null && brandName!=""){
			       if(familyName.contains("系")){
			    		familyName = familyName.replace("系", "");
			    		logger.info("品牌车系是： "+familyName);
			       }
			       CarBrands carBrands = new CarBrands();
			       carBrands.setBrand(brandName);
			       //特定高风险车型品牌
			       if(brandName.contains("欧宝")||"迈巴赫".equals(brandName)||"林肯".equals(brandName)||"阿尔法?罗米欧".contains(brandName)||
			    			"兰博基尼".equals(brandName)||"玛莎拉蒂".equals(brandName)||"法拉利".equals(brandName)||"雷诺".equals(brandName)||
			    			"宾利".equals(brandName)||"劳斯莱斯".equals(brandName)||"爱雷德".equals(brandName)||"帕格尼".equals(brandName)||
			    			"西雅特".equals(brandName)||"伊日".equals(brandName)){
			    		carBrands.setDemio("全系");
			       }else{
			    		carBrands.setDemio(familyName);
			       }
			       recored = carBrandsMapper.selectBybrandNameAndDemio(carBrands);
			       //投保车是否“高风险车”
			       if(recored>0){
			    	   radarinfor.setVehicleSSHighRiskGroup(LudiConstants.INSURED);
			       }else{
			    	   radarinfor.setVehicleSSHighRiskGroup(LudiConstants.NOT_INSURED);
			       }
				   }
				   //是否进口车
				   String isImported = premiumCount.getVhlinfor().getCarstyle();
				   if("635001".equals(isImported)){
					        radarinfor.setIsImported("Domestic");
				     }else if("635002".equals(isImported)){
				            radarinfor.setIsImported("Imported");
				     }
			       //投保车地址邮政编码
			       String deptNo = insuranceDetails.getBaseinfor().getDeptno();//获取投保行政区域代码
			       List<Dptinfor> dptinfors = dmsUtility.getAlldeptinfor();//获取行政机构集合
			       for(Dptinfor dpt : dptinfors){
			    	   String deptinforNo = Integer.toString(dpt.getDeptinforid());//获取行政机构代码
			    	   String vehicleLocationPostalCode = dpt.getZipcode();//获取行政机构邮编
			    	   if(deptinforNo.equals(deptNo) && vehicleLocationPostalCode!=null &&vehicleLocationPostalCode!=""){
			    		   radarinfor.setVehicleLocationPostalCode(vehicleLocationPostalCode);//投保车地址邮编
			    	   }
			       }
			       
			       //投保车行驶省份
			       String [] dept = insuranceDetails.getBaseinfor().getDeptAddress().split(",");
			       String deptAdress = dept[0];
			       deptAdress=deptAdress.substring(0, deptAdress.length()-1);
			       String cityAdress = dept[1];
			       cityAdress =cityAdress.substring(0, cityAdress.length()-1);
			       if(deptAdress!=null && deptAdress!=""){
			           radarinfor.setVehicleLocationProvince(ctop.chinsesToPinyin(deptAdress));
			       }
			       if(cityAdress!=null&& cityAdress != ""){
			    	   //投保车行驶城市
			    	   radarinfor.setVehicleLocationCity(ctop.chinsesToPinyin(cityAdress));
			    	   //投保车定价政策区域
			    	   if(cityAdress.equals("深圳")){
			    	     radarinfor.setVehicleRegulationZone(ctop.chinsesToPinyin(cityAdress));
			    	   }else{
			    		 radarinfor.setVehicleRegulationZone(ctop.chinsesToPinyin(deptAdress)); 
			    	   }
			       }
			       //汽车折损后价值(通过华安试算后返回的数据，车损险，盗抢险，自燃险保额的任意一个值，如果都没有的话返回n.a.)
			       /*if((radarinfor.getSSPremMOD()!=null && radarinfor.getSSPremMOD()!="")||
			    		(radarinfor.getSSPremVehicleTheft()!=null&& radarinfor.getSSPremVehicleTheft()!="")||
			    		      (radarinfor.getSSPremCombustion()!=null&&radarinfor.getSSPremCombustion()!="")){
			    	   radarinfor.setSSVehicleDepreciatedValue(radarinfor.getCardamageSuminsured());//车损险
			       }else {
			    	   radarinfor.setSSVehicleDepreciatedValue("n.a");
			       }*/
			       if(radarinfor.getSSPremMOD()!=null && radarinfor.getSSPremMOD()!=""){
			    	   radarinfor.setSSVehicleDepreciatedValue(radarinfor.getCardamageSuminsured());//车损险
			       }else if(radarinfor.getSSPremVehicleTheft()!=null&& radarinfor.getSSPremVehicleTheft()!=""){
			    	   radarinfor.setSSVehicleDepreciatedValue(radarinfor.getDaqSuminsured()); //盗抢险
			       }else if(radarinfor.getSSPremCombustion()!=null&&radarinfor.getSSPremCombustion()!=""){
			    	   radarinfor.setSSVehicleDepreciatedValue(radarinfor.getZraSuminsured()); //自燃险
			       }else {
			    	   radarinfor.setSSVehicleDepreciatedValue("n.a");
			       }
			}
		}
		 insuranceDetails.setRadarinfor(radarinfor);
		 insuranceDetails.getUserinfor().setUserAction("Radarinfor"); //封装模板
		 //封装数据调雷达接口
		 radarinforInterface.performRadarinforOprations(insuranceDetails);
		 
		 //获取车牌号
		 String lcnno = insuranceDetails.getVhlinfor().getLcnno();
		 if(lcnno!=null && lcnno !=""){
			 radarinfor.setLcnno(lcnno);
		 }
		 //获取车型名称
		 String brandName = insuranceDetails.getVhlinfor().getBrandName();
		 if(brandName!=null && brandName !=""){
			 radarinfor.setBrandName(brandName);
		 }
		 //是否高风险车
		 String highriskcar = radarinfor.getVehicleSSHighRiskGroup();
		 if(highriskcar!=null && highriskcar!=""){
			 radarinfor.setHighriskcar(highriskcar);
		 }
		 //创建radarinfor对象(调雷达返回信息)
		 RadarInfor radarinfors = insuranceDetails.getRadarinfor();
		 //是否选择三者险
		 if(radarinfors.getIsChoiceVTPL() != null && radarinfors.getIsChoiceVTPL() !="" ){
			 radarinfors.setIsChoiceVTPL(LudiConstants.INSURED);   
		 }else{
			 radarinfors.setIsChoiceVTPL(LudiConstants.NOT_INSURED);
		 }
		 //是否选择司机责任险
		 if(radarinfors.getIsChoiceDriver()!=null && radarinfors.getIsChoiceDriver()!=""){
			 radarinfors.setIsChoiceDriver(LudiConstants.INSURED);
		 }else{
			 radarinfors.setIsChoiceDriver(LudiConstants.NOT_INSURED);
		 }
		 //是否选择乘客险
		 if(radarinfors.getIsChoicePassenger()!=null && radarinfors.getIsChoicePassenger()!=""){
			 radarinfors.setIsChoicePassenger(LudiConstants.INSURED);
		 }else{
			 radarinfors.setIsChoicePassenger(LudiConstants.NOT_INSURED);
		 }
		 String ownerName = insuranceDetails.getBaseinfor().getOwnersname();
		    if(ownerName !=null && ownerName != ""){
		    	radarinfors.setOwnername(ownerName);
		    }
		 //交强险保费
		 String jqpreuim = radarinfors.getJqpremium();
		 if(jqpreuim !=null && jqpreuim != ""){
			    radarinfors.setJqpremium(jqpreuim);
		 }
		 RadarInfor radarInfor = null;
		 radarInfor = radarInforMapper.selectByPrimaryKey(orderNo);//根据订单号查出雷达信息判断插入/更新
         if(null ==radarInfor || "".equals(radarInfor)){
        	//保存雷达数据
 		    radarInforMapper.insertSelective(radarinfors);
         }else{
        	radarInforMapper.updateByPrimaryKeySelective(radarinfors);
         }
		session.setAttribute(insuranceDetails.getBaseinfor().getOrderno()+"insuranceDetailsVO", insuranceDetails);
		return insuranceDetails;
	}
	
}
