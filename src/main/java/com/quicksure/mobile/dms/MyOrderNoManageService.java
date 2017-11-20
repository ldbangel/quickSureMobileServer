package com.quicksure.mobile.dms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quicksure.mobile.common.ProcessData;
import com.quicksure.mobile.dao.BaseinforAssociateMapper;
import com.quicksure.mobile.dao.BaseinforMapper;
import com.quicksure.mobile.dao.CoverageinforMapper;
import com.quicksure.mobile.dao.WechatBindMapper;
import com.quicksure.mobile.entity.Baseinfor;
import com.quicksure.mobile.entity.Coverageinfor;
import com.quicksure.mobile.entity.InsuranceDetailsVO;
import com.quicksure.mobile.entity.Userinfor;
import com.quicksure.mobile.entity.WechatBind;
import com.quicksure.mobile.service.PolicyCancelService;

@Repository
public class MyOrderNoManageService {
	@Resource
	private BaseinforMapper baseinforMapper;
	@Resource
	private CoverageinforMapper coverageinforMapper;
	@Resource
	private BaseinforAssociateMapper baseinforAssociateMapper;
	@Resource
	private PolicyCancelService policyCancelService;
	@Resource
	private  WechatBindMapper wechatBindMapper;
	
	private static final Logger logger = Logger.getLogger(MyOrderNoManageService.class);
	
	@Transactional
	public List<InsuranceDetailsVO> getOrderListinformation(
			Map<String, Object> params,Userinfor user){
		List<InsuranceDetailsVO> listInsurance =new ArrayList<InsuranceDetailsVO>();
		List<Integer> userIds=new ArrayList<Integer>();
		userIds.add((Integer) params.get("userid"));
		//开始获取用户绑定用户的数据
		if(user!=null){
			WechatBind wechatBind=null;
			Integer userType=user.getUsertype();
			if(userType!=null&&userType==3){//微信端获取我的订单数据
				int wechatUserId=user.getUserid();
			     wechatBind=	wechatBindMapper.selectByWechatUserId(wechatUserId);
			 if(wechatBind!=null&&wechatBind.getPhoneuserid()!=null){
				 userIds.add(wechatBind.getPhoneuserid());
			 }
			}else if(userType!=null){//移动端获取我的订单数据
				int phoneUserId=user.getUserid();
				 wechatBind=	wechatBindMapper.selectByphoneUserId(phoneUserId);
				 if(wechatBind!=null&&wechatBind.getWechatuserid()!=null){
					 userIds.add(wechatBind.getWechatuserid());
				 }
			}
		}
		//结束获取用户绑定用户的数据
		List<String> allOrdeoNo = baseinforAssociateMapper
				.getALLOrderNo(userIds);
		if (allOrdeoNo != null && allOrdeoNo.size() > 0) {
			for (String orderNo : allOrdeoNo) {
				InsuranceDetailsVO insuranceDetailsVO = ProcessData
						.initInsuranceDetailsVO();
				params.put("orderNo", orderNo);
				insuranceDetailsVO = baseinforAssociateMapper
						.getOrderListinformation3(params);
				
				//根据订单时间来进行删单处理的规则
//				if(insuranceDetailsVO!=null){
//					int start = insuranceDetailsVO.getBaseinfor().getOrderstate();
//					if(start==30||start==40){
//						try {
//							SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
//							//最后操作的时间  +10天
//							 Calendar now = Calendar.getInstance();  
//							String updateTime = insuranceDetailsVO.getBaseinfor().getUpdatetime();
//								Date updateDate = formatter.parse(updateTime);
//								 now.setTime(updateDate);
//								 now.set(Calendar.DATE, now.get(Calendar.DATE) + 10); 
//								 updateDate =now.getTime();
//							//保单生效时间
//							String systartDate = insuranceDetailsVO.getBaseinfor().getSypolicystartdate();
//							Date sysstart = formatter.parse(systartDate);	
//							//当前时间
//							Date dateTime = new Date();
//							formatter.format(dateTime);
//			                //如果保单生效时间大于当前时间  || 当前系统时间大于最后操作时间10天 则保单状态改为已失效(80);
//							if(sysstart.getTime()<dateTime.getTime()|| dateTime.getTime()>updateDate.getTime()){
//								//删除订单并更改状态
////								insuranceDetailsVO.getUserinfor().setUserAction("PolicyCancel");
////								insuranceDetailsVO = sinosafeinterface.performSinosafeOprations(insuranceDetailsVO);
//								Baseinfor baseinfor = insuranceDetailsVO.getBaseinfor();
//								InsuranceDetailsVO insur = policyCancelService.policyCancelOrder(baseinfor);
//								//这里重新赋值baseinfor表数据,
//								insuranceDetailsVO.setBaseinfor(insur.getBaseinfor());
//							}
//							
//						} catch (ParseException e) {
//							logger.info("撤单异常"+e.getMessage());
//							e.printStackTrace();
//						}
//						
//					}
//	
//				}
				
				if(insuranceDetailsVO!=null){
					insuranceDetailsVO.getVhlinfor().setCodeName(ProcessData.brandNameToCode(insuranceDetailsVO.getVhlinfor().getBrandName()==null?"":insuranceDetailsVO.getVhlinfor().getBrandName()));
					insuranceDetailsVO.getVhlinfor().setDrvowner(insuranceDetailsVO.getVhlinfor().getDrvowner()==null?"":insuranceDetailsVO.getVhlinfor().getDrvowner());
					String systartdate=insuranceDetailsVO.getBaseinfor().getSypolicystartdate();
					String syenddate=insuranceDetailsVO.getBaseinfor().getSypolicyenddate();
					String jqstartdate=insuranceDetailsVO.getBaseinfor().getJqpolicystartdate();
					String jqenddate=insuranceDetailsVO.getBaseinfor().getJqpolicyenddate();
					
					insuranceDetailsVO.getBaseinfor().setSypolicystartdate(systartdate==null?"":systartdate.substring(0, 10));
					insuranceDetailsVO.getBaseinfor().setSypolicyenddate(syenddate==null?"":syenddate.substring(0, 10));
					insuranceDetailsVO.getBaseinfor().setJqpolicystartdate(jqstartdate==null?"":jqstartdate.substring(0, 10));
					insuranceDetailsVO.getBaseinfor().setJqpolicyenddate(jqenddate==null?"":jqenddate.substring(0, 10));
				}
				
//				coverageinfors = coverageinforMapper.getListCoverage(orderNo);
//				if(coverageinfors!=null&&coverageinfors.size()>0){
//					insuranceDetailsVO.setCoverageinfors(coverageinfors);
//				}
				if(insuranceDetailsVO.getBaseinfor().getTotalPremium() == null){
					insuranceDetailsVO.getBaseinfor().setTotalPremium("0");
				}
				listInsurance.add(insuranceDetailsVO);
			}
			
		}
		return listInsurance;
	}
	
	public List<Coverageinfor> getListCoverage(String orderNo){
		List<Coverageinfor> listCoverage=coverageinforMapper.getListCoverage(orderNo);
		return listCoverage;
	}
	
	public String deleteBaseinforRecord(Baseinfor baseinfor){
		String msg="";
		int result=baseinforMapper.deleteByPrimaryKey(baseinfor.getOrderno());
		if(result!=1){
			msg="1";//删除baseinfor表异常.
		}
		return msg;
	}
	
	public String updateBaseOrderStatu(Baseinfor baseinfor){
		String msg="";
		Baseinfor binfo=new Baseinfor();
		binfo.setOrderno(baseinfor.getOrderno());
		binfo.setOrderstate(80);//状态为关闭
		int result=baseinforMapper.updateOrderState(binfo);
		if(result==1){
			msg="1";//修改baseinfor表状态.
			logger.info("取消订单修改状态成功 订单号为 :" +binfo.getOrderno());
		}
		return msg;
	}
	public List<Baseinfor> getAllBaseinforStart(){
		return baseinforMapper.selectAllOrder();
	}
	
}
