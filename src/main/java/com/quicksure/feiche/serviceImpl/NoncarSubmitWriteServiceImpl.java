package com.quicksure.feiche.serviceImpl;


import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.quicksure.feiche.dms.NotCartDataManagerService;
import com.quicksure.feiche.entity.LudifcBaseinfor;
import com.quicksure.feiche.service.NoncarSubmitWriteService;
import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.sinosafefactory.Sinosafeinterface;
import com.quicksure.mobile.utility.ObjectUtil;
import com.quicksure.mobile.utility.StringUtils;

@Service("noncarSubmitWriteService")
public class NoncarSubmitWriteServiceImpl implements NoncarSubmitWriteService{
	private static final Logger logger = Logger
			.getLogger(NoncarSubmitWriteServiceImpl.class);
	
	@Resource
	private Sinosafeinterface sinosafeinterface;
	@Resource 
	private NotCartDataManagerService dataManageService;
	/**
	 * 驾意险提交--核保
	 */
	@Override
	public InsuranceDetailsVO noncarSubmitWrite(
			LudifcBaseinfor ludifcBaseinfor,HttpServletRequest httpRequest) {
		InsuranceDetailsVO insuranceDetails = null;
		String orderNo=ludifcBaseinfor.getOrderno();
		String AppId=ludifcBaseinfor.getAppId();//投保人身份证,通过获取身份证获取生日和性别
		String insureId=ludifcBaseinfor.getInsId();//被保人身份证,通过获取身份证获取生日和性别
		if(AppId!=null&&AppId!=""){
			String appbirthday=getBirthday(AppId);
			String appsex=getSex(AppId);
			ludifcBaseinfor.setAppBirthday(appbirthday);
			if(appsex.equals("男")){
		        ludifcBaseinfor.setAppSex("106001");
	        }else if(appsex.equals("女")){
	        	ludifcBaseinfor.setAppSex("106002");
	        }
			logger.info("投保人的性别是"+appsex); 
		}
		if(insureId!=null&&insureId!=""){
			String insbirthday=getBirthday(insureId);
			 String inssex=getSex(insureId);
			ludifcBaseinfor.setInsBirthday(insbirthday);
		    if(inssex.equals("男")){
	        	ludifcBaseinfor.setInsSex("106001");
	        }else if(inssex.equals("女")){
	        	ludifcBaseinfor.setInsSex("106002");
	        }
		    logger.info("被保人的性别是"+inssex);   
		}                  
		HttpSession session = httpRequest.getSession();
		//初始化大对象
		InsuranceDetailsVO insuranceDetailsVo = ProcessData.initInsuranceDetailsVO();
		//从订单中获取首页中的值并set进入大对象
		if (session.getAttribute(orderNo+"insuranceDetailsVO") != null) {
			insuranceDetails = (InsuranceDetailsVO) session
					.getAttribute(orderNo+"insuranceDetailsVO");	
			if(insuranceDetails.getLudifcBaseinfor()!=null){
				insuranceDetailsVo.setLudifcBaseinfor(insuranceDetails.getLudifcBaseinfor());
			}		
		}
		//将personInfor页面中的值赋值给大对象
		ludifcBaseinfor.setOrderstate(30);
		insuranceDetails = ProcessData.setObjValuetoinsuranceDetails(ludifcBaseinfor, insuranceDetailsVo);
		insuranceDetails=dataManageService.updateFeiBaseinfor(insuranceDetails);//调用华安前更新一次数据
		insuranceDetails.getUserinfor().setUserAction("jiayixiansubmit");
		insuranceDetails = sinosafeinterface.performSinosafeOprations(insuranceDetails);//提核
		String payUrl = insuranceDetails.getLudifcBaseinfor().getPayUrl();
        if(payUrl!=null){
        	insuranceDetails.getLudifcBaseinfor().setOrderstate(40);
        	logger.info("提核成功把支付链接更新进数据库");
        	 insuranceDetails = dataManageService
     				.updateFeiBaseinfor(insuranceDetails);//提核成功之后更新数据库
        }
		session.setAttribute(insuranceDetails.getLudifcBaseinfor().getOrderno()
				+"insuranceDetailsVO", insuranceDetails);
		
		return insuranceDetails;
	}

	/**
	 *驾意险首页进入人员信息
	 */
	@Override
	public InsuranceDetailsVO getjiyiaPersonalHomeinfor(
			LudifcBaseinfor ludifcBaseinfor,HttpServletRequest httprequest) {
		HttpSession session=httprequest.getSession();
	    String ordernomyaccount= httprequest.getParameter("orderNo");//从myaccount跳转过来获取的订单
	    String agentshare= httprequest.getParameter("agentshare");//判断是否是连接分享过来出单
	   /* String orderNo = (String)session.getAttribute("jiayixianorderno");//从第二页回调到第一页时候，从session中获取订单*/
		InsuranceDetailsVO insuranceDetailsVO = new InsuranceDetailsVO();
		Userinfor userinfor = (Userinfor) session.getAttribute("loginUser");
		//绑定userid
		if(userinfor!=null && userinfor.getUserid()!=null && !"".equals(userinfor.getUserid())){
			ludifcBaseinfor.setUserinforid(userinfor.getUserid());
		}
		ludifcBaseinfor.setProdno("060I");
		insuranceDetailsVO.setUserinfor(userinfor);
		/*if((orderNo!=null&&!"".equals(orderNo))&&(ordernomyaccount==null||"".equals(ordernomyaccount))){
			ludifcBaseinfor.setOrderno(orderNo);
		}else if((orderNo==null||"".equals(orderNo))&&(ordernomyaccount!=null&&!"".equals(ordernomyaccount))){
			ludifcBaseinfor.setOrderno(ordernomyaccount);
		}else if((orderNo!=null&&!"".equals(orderNo))&&(ordernomyaccount!=null&&!"".equals(ordernomyaccount))){
			ludifcBaseinfor.setOrderno(ordernomyaccount);
		}*/
		if(ordernomyaccount!=null&&!"".equals(ordernomyaccount)){
			ludifcBaseinfor.setOrderno(ordernomyaccount);
		}
		//设置非车连接分享标识
		if("1".equals(agentshare)){
			ludifcBaseinfor.setIsagentshare(Integer.valueOf(agentshare));
		}else{
			ludifcBaseinfor.setIsagentshare(Integer.valueOf("0"));
		}
		insuranceDetailsVO.setLudifcBaseinfor(ludifcBaseinfor);				
		insuranceDetailsVO = dataManageService
				.savefirstScreenData(insuranceDetailsVO);//生成订单号并存储信息
		logger.info("跳转第二页开始，订单号："+insuranceDetailsVO.getLudifcBaseinfor().getOrderno());
		String orderno = insuranceDetailsVO.getLudifcBaseinfor().getOrderno();
		/*session.setAttribute("jiayixianorderno", orderno);*/
		session.setAttribute(insuranceDetailsVO.getLudifcBaseinfor().getOrderno()
				+"insuranceDetailsVO", insuranceDetailsVO);
		return insuranceDetailsVO;
	}

	/**
	 * 君安保---首页跳转到第二页
	 */
	@Override
	public InsuranceDetailsVO getPersonalHomeinfor(
			HttpServletRequest httprequest) {
		Userinfor user=null;
		InsuranceDetailsVO insuranceDetailsVO = new InsuranceDetailsVO();
		LudifcBaseinfor ludifcBaseinfor = new LudifcBaseinfor();
		String ordernomyaccount=httprequest.getParameter("orderNo");//从myaccount跳转过来获取的订单
		String agentshare= httprequest.getParameter("agentshare");//判断是否是连接分享过来出单
        /*logger.info("跳转第二页开始，订单号："+orderNo);*/
		HttpSession session=httprequest.getSession();
		/*String orderNo =  (String) session.getAttribute("orderno");//从第二页回调到第一页时候，从session中获取订单*/
		Userinfor userinfor = (Userinfor) session.getAttribute("loginUser");
	    int lastImplementPage=2;//代表用户操作的当前页,将其存放入数据库
	    String prodType = httprequest.getParameter("prodtype");//产品类型
		String sumPremium = httprequest.getParameter("sumpremium");//总保费
		String sumamount = httprequest.getParameter("sumamount");//总保额
		ludifcBaseinfor.setProdno("0615"); 
		/*if((orderNo!=null&&!"".equals(orderNo))&&(ordernomyaccount==null||"".equals(ordernomyaccount))){
			ludifcBaseinfor.setOrderno(orderNo);
		}else if((orderNo==null||"".equals(orderNo))&&(ordernomyaccount!=null&&!"".equals(ordernomyaccount))){
			ludifcBaseinfor.setOrderno(ordernomyaccount);
		}else if((orderNo!=null&&!"".equals(orderNo))&&(ordernomyaccount!=null&&!"".equals(ordernomyaccount))){
			ludifcBaseinfor.setOrderno(ordernomyaccount);
		}*/
		if(ordernomyaccount!=null&&!"".equals(ordernomyaccount)){
			ludifcBaseinfor.setOrderno(ordernomyaccount);
		}
		if(prodType!=null){
			ludifcBaseinfor.setProdtype(prodType);
		}
		if(sumPremium!=null){
			ludifcBaseinfor.setSumpremium(sumPremium);
		}
		if(sumamount!=null){
			ludifcBaseinfor.setSumamount(sumamount);
		}
		//绑定userid
		if(userinfor!=null && userinfor.getUserid()!=null && !"".equals(userinfor.getUserid())){
			ludifcBaseinfor.setUserinforid(userinfor.getUserid());
		}
		insuranceDetailsVO.setUserinfor(userinfor);
		//设置非车连接分享标识
		if("1".equals(agentshare)){
			ludifcBaseinfor.setIsagentshare(Integer.valueOf(agentshare));
		}else{
			ludifcBaseinfor.setIsagentshare(Integer.valueOf("0"));
		}
		insuranceDetailsVO.setLudifcBaseinfor(ludifcBaseinfor);
		insuranceDetailsVO = dataManageService
				.savefirstScreenData(insuranceDetailsVO);//生成订单号并存储信息
		String orderno = insuranceDetailsVO.getLudifcBaseinfor().getOrderno();
		/*session.setAttribute("orderno", orderno);*/
		session.setAttribute(insuranceDetailsVO.getLudifcBaseinfor().getOrderno()
				+"insuranceDetailsVO", insuranceDetailsVO);
		return insuranceDetailsVO;
	}
	
	/**
	 * 君安保---提交核保
	 */
	@Override
	public InsuranceDetailsVO submitinfor(HttpServletRequest httprequest,
			LudifcBaseinfor ludifcBaseinfor) {
		String orderNo1 = httprequest.getParameter("orderno");
		String orderNo = ludifcBaseinfor.getOrderno();
		HttpSession session=httprequest.getSession();
		InsuranceDetailsVO insuranceDetail=null;
		InsuranceDetailsVO insuranceDetails=null;
		String appBirthday="";//投保人出生年月
		String insBirthday="";//被保人出生年月
		String appSex="";//投保人性别
		String insSex="";//被保人性别
		if (insuranceDetails == null) {			
			InsuranceDetailsVO insuranceDetailsVo=ProcessData.initInsuranceDetailsVO();
			insuranceDetail=insuranceDetailsVo;
		}
		if(StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null){
			insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo+"insuranceDetailsVO");		 
			}
	    if(insuranceDetails!=null){
	    	if(insuranceDetails.getLudifcBaseinfor()!=null){
				String prodType = insuranceDetails.getLudifcBaseinfor().getProdtype(); //产品类型
				String sumPremium = insuranceDetails.getLudifcBaseinfor().getSumpremium(); //总保费
				String sumamount = insuranceDetails.getLudifcBaseinfor().getSumamount(); //总保费
				String appID = ludifcBaseinfor.getAppId();//投保人身份证
				String insID = ludifcBaseinfor.getInsId();//被保人身份证
				if(appID!=null){
					appBirthday =getBirthday(appID);
					appSex = getSex(appID);
					if(appSex.equals("男")){
						appSex="106001";
					}else{
						appSex="106002";
					}
				}
				if(insID!=null){
					insBirthday=getBirthday(insID);
					insSex = getSex(insID);
					if(insSex.equals("男")){
						insSex="106001";
					}else{
						insSex="106002";
					}
				}
				ludifcBaseinfor.setProdtype(prodType);
				ludifcBaseinfor.setSumpremium(sumPremium);
				ludifcBaseinfor.setSumamount(sumamount);
				ludifcBaseinfor.setAppBirthday(appBirthday);
				ludifcBaseinfor.setInsBirthday(insBirthday);
				ludifcBaseinfor.setAppSex(appSex);
				ludifcBaseinfor.setInsSex(insSex);
				ludifcBaseinfor.setOrderstate(30);
				insuranceDetail.setLudifcBaseinfor(ludifcBaseinfor);
			}
	    }
		
	    insuranceDetails = dataManageService
				.updateFeiBaseinfor(insuranceDetail);
		insuranceDetails.setLudifcBaseinfor(ludifcBaseinfor);
		
		
		insuranceDetail.getUserinfor().setUserAction("junanbaosubmit");
		//封装数据调用华安接口
        sinosafeinterface.performSinosafeOprations(insuranceDetail);
        String payUrl = insuranceDetail.getLudifcBaseinfor().getPayUrl();
        if(payUrl!=null){
        	insuranceDetail.getLudifcBaseinfor().setOrderstate(40);
        	logger.info("提核成功把支付链接更新进数据库");
        	 insuranceDetails = dataManageService
     				.updateFeiBaseinfor(insuranceDetail);
        }
        
		return insuranceDetail;
	}
	
	/**
	 * 一路平安---首页信息处理
	 * 
	 */
	@Override
	public InsuranceDetailsVO getVehicleAccHomeInfo(HttpServletRequest request
			,LudifcBaseinfor ludifcBaseinfor) {
		InsuranceDetailsVO insuranceDetailsVO = ProcessData.initInsuranceDetailsVO();
		HttpSession session = request.getSession();
		String ordernomyaccount=request.getParameter("orderNo");//从myaccount跳转过来获取的订单
		String agentshare= request.getParameter("agentshare");//判断是否是连接分享过来出单
		/*String orderNo =  (String) session.getAttribute("yilupinganorderno");//从第二页回调到第一页时候，从session中获取订单
*/		Userinfor userinfor = (Userinfor) session.getAttribute("loginUser");
		//给订单绑定userId
		if(userinfor!=null && userinfor.getUserid()!=null && !"".equals(userinfor.getUserid())){
			ludifcBaseinfor.setUserinforid(userinfor.getUserid());
		}
		insuranceDetailsVO.setUserinfor(userinfor);
		/*if((orderNo!=null&&!"".equals(orderNo))&&(ordernomyaccount==null||"".equals(ordernomyaccount))){
			ludifcBaseinfor.setOrderno(orderNo);
		}else if((orderNo==null||"".equals(orderNo))&&(ordernomyaccount!=null&&!"".equals(ordernomyaccount))){
			ludifcBaseinfor.setOrderno(ordernomyaccount);
		}else if((orderNo!=null&&!"".equals(orderNo))&&(ordernomyaccount!=null&&!"".equals(ordernomyaccount))){
			ludifcBaseinfor.setOrderno(ordernomyaccount);
		}*/
		if(ordernomyaccount!=null&&!"".equals(ordernomyaccount)){
			ludifcBaseinfor.setOrderno(ordernomyaccount);
		}
		//设置非车连接分享标识
		if("1".equals(agentshare)){
			ludifcBaseinfor.setIsagentshare(Integer.valueOf(agentshare));
		}else{
			ludifcBaseinfor.setIsagentshare(Integer.valueOf("0"));
		}
		insuranceDetailsVO.setLudifcBaseinfor(ludifcBaseinfor);
		insuranceDetailsVO = dataManageService
				.savefirstScreenData(insuranceDetailsVO);//生成订单号并存储信息
		String orderno = insuranceDetailsVO.getLudifcBaseinfor().getOrderno();
		/*session.setAttribute("yilupinganorderno", orderno);*/
		session.setAttribute(insuranceDetailsVO.getLudifcBaseinfor().getOrderno()
				+"insuranceDetailsVO", insuranceDetailsVO);
		return insuranceDetailsVO;
	}

	
	/**
	 * 一路平安--提交核保
	 * 
	 */
	@Override
	public InsuranceDetailsVO VehicleAccSubmitUnder(HttpServletRequest httpRequest,
			LudifcBaseinfor baseinfor) {
		String orderNo = httpRequest.getParameter("orderNo");
		HttpSession session = httpRequest.getSession();
		InsuranceDetailsVO insuranceDetails=null;
		if(StringUtils.checkStringEmpty(orderNo)
				&& session.getAttribute(orderNo + "insuranceDetailsVO") != null){
			insuranceDetails = (InsuranceDetailsVO)session.getAttribute(orderNo+"insuranceDetailsVO");
		}else if (insuranceDetails == null) {
			InsuranceDetailsVO insuranceDetailsVo = ProcessData.initInsuranceDetailsVO();
			insuranceDetails = insuranceDetailsVo;
		}
		//同类对象一个对象的非空字段复制给另外一个对象
		ObjectUtil.mergeObject(baseinfor, insuranceDetails.getLudifcBaseinfor());
		//给定证件类型
		insuranceDetails.getLudifcBaseinfor().setAppCertType("120001");
		insuranceDetails.getLudifcBaseinfor().setInsCertType("120001");
		//获取投保人被保人的出生日期和性别
		insuranceDetails.getLudifcBaseinfor().setAppBirthday(
				getBirthday(insuranceDetails.getLudifcBaseinfor().getAppId()));
		insuranceDetails.getLudifcBaseinfor().setInsBirthday(
				getBirthday(insuranceDetails.getLudifcBaseinfor().getInsId()));
		insuranceDetails.getLudifcBaseinfor().setAppSex(
				"男".equals(getSex(insuranceDetails.getLudifcBaseinfor().getAppId()))?"106001":"106002");
		insuranceDetails.getLudifcBaseinfor().setInsSex(
				"男".equals(getSex(insuranceDetails.getLudifcBaseinfor().getInsId()))?"106001":"106002");
		
		insuranceDetails.getUserinfor().setUserAction("VehicleAccidentSubmit");
		insuranceDetails.getLudifcBaseinfor().setOrderstate(30);
		sinosafeinterface.performSinosafeOprations(insuranceDetails);
		if(insuranceDetails.getLudifcBaseinfor().getPayUrl()!=null
				&& !"".equals(insuranceDetails.getLudifcBaseinfor().getPayUrl())){
			insuranceDetails.getLudifcBaseinfor().setOrderstate(40);
		}
		//一路平安信息更新进数据库表
		insuranceDetails = dataManageService.updateFeiBaseinfor(insuranceDetails);
		session.setAttribute(insuranceDetails.getLudifcBaseinfor().getOrderno()
				+"insuranceDetailsVO", insuranceDetails);
		return insuranceDetails;
	}
	
	/**
	 * 根据身份证获取生日
	 */
	public String getBirthday(String ID){
		String str=ID.substring(6,10);
        String str1=ID.substring(10,12);
        String str2=ID.substring(12,14);
        String birthday=str+"-"+str1+"-"+str2;
		return birthday;
		
	}	
	/**
	 * 根据身份证判断性别
	 */
	public String getSex(String ID){	
	    ID = ID.trim();
		if (ID == null || (ID.length() != 15 && ID.length() != 18)){		
		    return null;
		}
		if (ID.length() == 15 || ID.length() == 18){		
		    String lastValue = ID.substring(ID.length() - 2, ID.length()-1);
		    int sex;
			sex = Integer.parseInt(lastValue) % 2;
		    return sex == 0 ? "女" : "男";
		}else{		
			return null;
		}
	}
	/**
	 * 获取华安发送过来的保单号投保单号
	 * @return 
	 * @throws IOException 
	 */
	@Override
	public InsuranceDetailsVO getpolicyinfor(HttpServletRequest httprequest) throws IOException {
		
		String svcseqNo = httprequest.getParameter("svcseqNo");
		String plyNo = httprequest.getParameter("plyNo");
		String cPlyAppNo = httprequest.getParameter("cPlyAppNo");
		
		/*BufferedReader br = new BufferedReader(new InputStreamReader(httprequest.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        } 
        br.close(); 
        String Strjson=sb.toString();
        logger.info("接收到的JSON为:"+Strjson);*/
        InsuranceDetailsVO insuranceDetail = new InsuranceDetailsVO();
		LudifcBaseinfor ludifcBaseinfor = new LudifcBaseinfor();
      /*  if("".equals(Strjson)){*/
		logger.info("接收到的信息为:订单号:"+svcseqNo+"保单号:"+plyNo+"投保单号:"+cPlyAppNo);
		if(svcseqNo!=null && svcseqNo!=""){
			ludifcBaseinfor.setOrderno(svcseqNo);
			ludifcBaseinfor.setPolicyno(plyNo);
			ludifcBaseinfor.setApplicationno(cPlyAppNo);
			ludifcBaseinfor.setOrderstate(50);
			insuranceDetail.setLudifcBaseinfor(ludifcBaseinfor);
	    	logger.info("接收华安发送过来的保单并更新进数据库,订单号为:"+insuranceDetail.getLudifcBaseinfor().getOrderno());
	    	insuranceDetail = dataManageService.updateFeiBaseinfor(insuranceDetail);
        	
        }else{
        	logger.info("没有数据传过来!保单接收失败");
        }
        
		
		return insuranceDetail;
	}
}
