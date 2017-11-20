package com.quicksure.mobile.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class RadarInfor {
   private Integer radarInforId; 
   private String  ownername;  //车主姓名
   private String  jqpremium;  //交强险保费
   private Date    createtime; //创建时间
   private Date    updatetime; //更新时间
   private String  lcnno;      //车牌号
   private String  brandName;  //车型名称
   private String isImported;  //是否进口车
   private String highriskcar; //是否高风险车
   private String BaseinforOrderNo;  //订单号
   private String IsChoiceCombustion;//是否选择自燃险
   private String IsChoiceCTPL;      //是否选择交强险
   private String IsChoiceTheft;     //是否选择盗抢险
   private String isChoiceMOD;//是否选择车损险
   private String cardamageSuminsured;//车损险保额
   private String isChoiceGlass;//是否选择玻璃破碎险
   private String IsChoiceDriver;//是否选择司机责任险
   private String IsChoicePassenger;//是否选择乘客责任险
   private String IsChoiceVTPL;//是否选择三者责任险
   private String IsChoiceDedWaiverCombust;//是否选择自燃险的不计免赔
   private String IsChoiceDedWaiverTheft;//是否选择盗抢险
   private String IsChoiceDedWaiverMOD;//是否选择车损险的不计免赔
   private String IsChoiceDedWaiverDriver;//是否选择司机责任的不计免赔
   private String IsChoiceDedWaiverPassenger;//是否选择乘客责任的不计免赔
   private String IsChoiceDedWaiverVTPL;//是否选择三者险的不计免赔
   private String JYDisplacement;//JY排量*1000
   private String OwnerAgeAtPolicyStart;//车主保单开始时的年龄
   private String OwnerDOB;//车主出生日期
   private String OwnerGender;//车主性别
   private String ProposerAgeAtPolicyStart;//投保人保单开始时年龄
   private String ProposerDOB;//投保人出生日期
   private String ProposerGender;//投保人性别
   private String PolicyChannel;//保单渠道   Agent or Direct
   private String PolicyFirstQuoteDate;//保单首次报价日期
   private String PolicyLastQuoteDate;//保单末次报价日期
   private String PolicyDurationwithQS;//保单续保慧英年数---
   private String PolicyDurationwithSS;//保单续保华安年数---
   private String PolicyFirstStartDate;//保单首次投保日期(当前时间)
   private String PolicyKnownSS;//上年保单数据是否在华安系统中可调取
   private String PolicyLastYearProvince;//保单在华安去年承保省份
   private String PolicyStartDateComm;//保单商业险起始日期
   private String PolicyStartDateCTPL;//保单交强险起始日期
   private String PTPolicyEndDateComm;//保单商业险终止日期
   private String PTPolicyEndDateCTPL;//保单交强险终止日期
   private String ScreenProcessCurrentStep;//投保步骤-目前投吧步骤 3
   private String ScreenProcessFurthestStep;//投保步骤-最远走到过哪一个投保步骤""
   private String VehicleAgeatPolicyStartMonths;//投保车龄-保单起始时
   private String VehicleAgeAtPolicyStartYrs;//投保车龄-保单终止时
   private String VehicleDateFirstRegistration; //投保车注册日期
   private String VehicleCurrentGlassType;//投保车玻璃类型
   private String VehicleLocationPostalCode;//投保车地址邮政编码
   private String VehicleLocationProvince;//投保车行驶省份
   private String VehicleSSHighRiskGroup;//投保车是否“高风险车”
   private String PTMODStandardRiskPremium;//车损险基准保费（折前保费）
   private String SSPremCombustion;//华安保费-“自燃损失险”
   private String zraSuminsured;// 自燃险保额
   private String SSPremDedWaiverCombust;//华安保费-“自燃损失险-不计免赔”
   private String SSPremDedWaiverDriverLiab;//华安保费-“司机座位责任险-不计免赔”
   private String SSPremDedWaiverMOD;//华安保费-“车辆损失险-不计免赔”
   private String SSPremDedWaiverPssgrLiab;//华安保费-“乘客座位责任险-不计免赔”
   private String SSPremDedWaiverTheft;//华安保费-“全车盗抢险-不计免赔”
   private String daqSuminsured;// 盗抢险保额
   private String SSPremDedWaiverVTPL;//华安保费-“第三者责任险-不计免赔”
   private String SSPremDriverLiab;//华安保费-“司机座位责任险”
   private String SSPremGlass;//华安保费-“玻璃单独破碎险”
   private String SSPremGovtVehicleTax;//华安保费-“车船税”
   private String SSPremMOD;//华安保费-“车辆损失险”
   private String SSPremPassengerLiab;//华安保费-“乘客座位责任险”
   private String SSPremVehicleTheft;//华安保费-“全车盗抢险”
   private String SSPremVTPL;//华安保费-“第三者责任险”
   private String SSPremTotal;//华安保费 - 总计
   private String PTAllowedSeats;//核定载客数
   private String VehicleRegulationZone;//投保车定价政策区域
   private String SSVehicleDepreciatedValue;//汽车折损后价值
   private String JYABSFlag; //精友-是否有ABS系统
   private String VehicleLocationCity; //投保行驶城市
   
   //返回的字段
   private String QSActuarialRiskCostCTPL;//自主定价-交强险
   private String QSActuarialRiskCostMOD;//自主定价-车辆损失险
   private String QSActuarialRiskCostVTPL;//自主定价-第三者责任险
   private String QSActuarialRiskCostTheft;//自主定价-全车盗抢险
   private String QSActuarialRiskCostCombust;//自主定价-自燃损失险
   private String QSActuarialRiskCostDriverLiab;//自主定价-司机座位责任险
   private String QSActuarialRiskCostPssgrLiab;//自主定价-乘客座位责任险
   private String QSActuarialRiskCostGlass;//自主定价-玻璃单独破碎险
   private String QSActuarialRiskCostOther;//自主定价-其他保险
   private String QSActuarialRiskCostTotal;//自主定价-总价格
   private String TariffPremAfterDiscountMOD;//实际折扣后保费-车辆损失险
   private String TariffPremAfterDiscountVTPL;//实际折扣后保费-第三者责任险
   private String TariffPremAfterDiscountTheft;//实际折扣后保费-全车盗抢险
   private String TariffPremAfterDiscountCombust;//实际折扣后保费-自燃损失险
   private String TariffPremAfterDiscountDriverLiab;//实际折扣后保费-司机座位责任险
   private String TariffPremAfterDiscountPssgrLiab;//实际折扣后保费-乘客座位责任险
   private String TariffPremAfterDiscountGlass;//实际折扣后保费-玻璃单独破碎险
   private String TariffPremAfterDiscountDedWaiverCombust;//实际折扣后保费-自燃损失险-不计免赔
   private String TariffPremAfterDiscountDedWaiverDriverLiab;//实际折扣后保费-司机座位责任险-不计免赔
   private String TariffPremAfterDiscountDedWaiverMOD;//实际折扣后保费-车辆损失险-不计免赔
   private String TariffPremAfterDiscountDedWaiverPssgrLiab;//实际折扣后保费-乘客座位责任险-不计免赔
   private String TariffPremAfterDiscountDedWaiverTheft;//实际折扣后保费-全车盗抢险-不计免赔
   private String TariffPremAfterDiscountDedWaiverVTPL;//实际折扣后保费-第三者责任险-不计免赔
   private String PremNoClaimsDiscount;//无理赔优待系数
   private String PremTrafficViolation;//交通违法系数
   private String PremUnderwritingFactor;//自主核保系数
   private String PremChannelFactor;//渠道系数
   private String PremTotal;//实算总保费
   private String BusinessRulesResults;//核保结果
   private String BusinessRulesErrorMessages;//核保报错信息
   private String BusinessRulesEndorsements;//核保标记
   private String RadarNotes;//雷达备注信息
   private String RadarModelName;//雷达模型版本
 
   public String getLcnno() {
	return lcnno;
}
public void setLcnno(String lcnno) {
	this.lcnno = lcnno;
}
public String getBrandName() {
	return brandName;
}
public void setBrandName(String brandName) {
	this.brandName = brandName;
}
public String getZraSuminsured() {
	return zraSuminsured;
}
public void setZraSuminsured(String zraSuminsured) {
	this.zraSuminsured = zraSuminsured;
}
public String getDaqSuminsured() {
	return daqSuminsured;
}
public void setDaqSuminsured(String daqSuminsured) {
	this.daqSuminsured = daqSuminsured;
}  
public String getHighriskcar() {
	return highriskcar;
}
public void setHighriskcar(String highriskcar) {
	this.highriskcar = highriskcar;
}
public String getIsImported() {
	return isImported;
}
public void setIsImported(String isImported) {
	this.isImported = isImported;
}
public String getCardamageSuminsured() {
	return cardamageSuminsured;
}
public void setCardamageSuminsured(String cardamageSuminsured) {
	this.cardamageSuminsured = cardamageSuminsured;
}
public Integer getRadarInforId() {
	return radarInforId;
}
public void setRadarInforId(Integer radarInforId) {
	this.radarInforId = radarInforId;
}
public String getOwnername() {
	return ownername;
}
public void setOwnername(String ownername) {
	this.ownername = ownername;
}
public String getJqpremium() {
	return jqpremium;
}
public void setJqpremium(String jqpremium) {
	this.jqpremium = jqpremium;
}
public Date getCreatetime() {
	return createtime;
}
public void setCreatetime(Date createtime) {
	this.createtime = createtime;
}
public Date getUpdatetime() {
	return updatetime;
}
public void setUpdatetime(Date updatetime) {
	this.updatetime = updatetime;
}
public String getVehicleDateFirstRegistration() {
	return VehicleDateFirstRegistration;
}
public void setVehicleDateFirstRegistration(String vehicleDateFirstRegistration) {
	VehicleDateFirstRegistration = vehicleDateFirstRegistration;
}
public String getPolicyFirstQuoteDate() {
	return PolicyFirstQuoteDate;
}
public void setPolicyFirstQuoteDate(String policyFirstQuoteDate) {
	PolicyFirstQuoteDate = policyFirstQuoteDate;
}
public String getPolicyLastQuoteDate() {
	return PolicyLastQuoteDate;
}
public void setPolicyLastQuoteDate(String policyLastQuoteDate) {
	PolicyLastQuoteDate = policyLastQuoteDate;
}

public String getVehicleLocationCity() {
	return VehicleLocationCity;
}
public void setVehicleLocationCity(String vehicleLocationCity) {
	VehicleLocationCity = vehicleLocationCity;
}
public String getPTMODStandardRiskPremium() {
		return PTMODStandardRiskPremium;
}

public String getJYABSFlag() {
	return JYABSFlag;
}
public void setJYABSFlag(String jYABSFlag) {
	JYABSFlag = jYABSFlag;
}
public void setPTMODStandardRiskPremium(String pTMODStandardRiskPremium) {
		PTMODStandardRiskPremium = pTMODStandardRiskPremium;
}  
public String getPTAllowedSeats() {
	return PTAllowedSeats;
}
public void setPTAllowedSeats(String pTAllowedSeats) {
	PTAllowedSeats = pTAllowedSeats;
}
public String getVehicleRegulationZone() {
	return VehicleRegulationZone;
}
public void setVehicleRegulationZone(String vehicleRegulationZone) {
	VehicleRegulationZone = vehicleRegulationZone;
}
public String getSSVehicleDepreciatedValue() {
	return SSVehicleDepreciatedValue;
}
public void setSSVehicleDepreciatedValue(String sSVehicleDepreciatedValue) {
	SSVehicleDepreciatedValue = sSVehicleDepreciatedValue;
}
public String getQSActuarialRiskCostCTPL() {
	return QSActuarialRiskCostCTPL;
}
public void setQSActuarialRiskCostCTPL(String qSActuarialRiskCostCTPL) {
	QSActuarialRiskCostCTPL = qSActuarialRiskCostCTPL;
}
public String getQSActuarialRiskCostMOD() {
	return QSActuarialRiskCostMOD;
}
public void setQSActuarialRiskCostMOD(String qSActuarialRiskCostMOD) {
	QSActuarialRiskCostMOD = qSActuarialRiskCostMOD;
}
public String getQSActuarialRiskCostVTPL() {
	return QSActuarialRiskCostVTPL;
}
public void setQSActuarialRiskCostVTPL(String qSActuarialRiskCostVTPL) {
	QSActuarialRiskCostVTPL = qSActuarialRiskCostVTPL;
}
public String getQSActuarialRiskCostTheft() {
	return QSActuarialRiskCostTheft;
}
public void setQSActuarialRiskCostTheft(String qSActuarialRiskCostTheft) {
	QSActuarialRiskCostTheft = qSActuarialRiskCostTheft;
}
public String getQSActuarialRiskCostCombust() {
	return QSActuarialRiskCostCombust;
}
public void setQSActuarialRiskCostCombust(String qSActuarialRiskCostCombust) {
	QSActuarialRiskCostCombust = qSActuarialRiskCostCombust;
}
public String getQSActuarialRiskCostDriverLiab() {
	return QSActuarialRiskCostDriverLiab;
}
public void setQSActuarialRiskCostDriverLiab(
		String qSActuarialRiskCostDriverLiab) {
	QSActuarialRiskCostDriverLiab = qSActuarialRiskCostDriverLiab;
}
public String getQSActuarialRiskCostPssgrLiab() {
	return QSActuarialRiskCostPssgrLiab;
}
public void setQSActuarialRiskCostPssgrLiab(String qSActuarialRiskCostPssgrLiab) {
	QSActuarialRiskCostPssgrLiab = qSActuarialRiskCostPssgrLiab;
}
public String getQSActuarialRiskCostGlass() {
	return QSActuarialRiskCostGlass;
}
public void setQSActuarialRiskCostGlass(String qSActuarialRiskCostGlass) {
	QSActuarialRiskCostGlass = qSActuarialRiskCostGlass;
}
public String getQSActuarialRiskCostOther() {
	return QSActuarialRiskCostOther;
}
public void setQSActuarialRiskCostOther(String qSActuarialRiskCostOther) {
	QSActuarialRiskCostOther = qSActuarialRiskCostOther;
}
public String getQSActuarialRiskCostTotal() {
	return QSActuarialRiskCostTotal;
}
public void setQSActuarialRiskCostTotal(String qSActuarialRiskCostTotal) {
	QSActuarialRiskCostTotal = qSActuarialRiskCostTotal;
}
public String getTariffPremAfterDiscountMOD() {
	return TariffPremAfterDiscountMOD;
}
public void setTariffPremAfterDiscountMOD(String tariffPremAfterDiscountMOD) {
	TariffPremAfterDiscountMOD = tariffPremAfterDiscountMOD;
}
public String getTariffPremAfterDiscountVTPL() {
	return TariffPremAfterDiscountVTPL;
}
public void setTariffPremAfterDiscountVTPL(String tariffPremAfterDiscountVTPL) {
	TariffPremAfterDiscountVTPL = tariffPremAfterDiscountVTPL;
}
public String getTariffPremAfterDiscountTheft() {
	return TariffPremAfterDiscountTheft;
}
public void setTariffPremAfterDiscountTheft(String tariffPremAfterDiscountTheft) {
	TariffPremAfterDiscountTheft = tariffPremAfterDiscountTheft;
}
public String getTariffPremAfterDiscountCombust() {
	return TariffPremAfterDiscountCombust;
}
public void setTariffPremAfterDiscountCombust(
		String tariffPremAfterDiscountCombust) {
	TariffPremAfterDiscountCombust = tariffPremAfterDiscountCombust;
}
public String getTariffPremAfterDiscountDriverLiab() {
	return TariffPremAfterDiscountDriverLiab;
}
public void setTariffPremAfterDiscountDriverLiab(
		String tariffPremAfterDiscountDriverLiab) {
	TariffPremAfterDiscountDriverLiab = tariffPremAfterDiscountDriverLiab;
}
public String getTariffPremAfterDiscountPssgrLiab() {
	return TariffPremAfterDiscountPssgrLiab;
}
public void setTariffPremAfterDiscountPssgrLiab(
		String tariffPremAfterDiscountPssgrLiab) {
	TariffPremAfterDiscountPssgrLiab = tariffPremAfterDiscountPssgrLiab;
}
public String getTariffPremAfterDiscountGlass() {
	return TariffPremAfterDiscountGlass;
}
public void setTariffPremAfterDiscountGlass(String tariffPremAfterDiscountGlass) {
	TariffPremAfterDiscountGlass = tariffPremAfterDiscountGlass;
}
public String getTariffPremAfterDiscountDedWaiverCombust() {
	return TariffPremAfterDiscountDedWaiverCombust;
}
public void setTariffPremAfterDiscountDedWaiverCombust(
		String tariffPremAfterDiscountDedWaiverCombust) {
	TariffPremAfterDiscountDedWaiverCombust = tariffPremAfterDiscountDedWaiverCombust;
}
public String getTariffPremAfterDiscountDedWaiverDriverLiab() {
	return TariffPremAfterDiscountDedWaiverDriverLiab;
}
public void setTariffPremAfterDiscountDedWaiverDriverLiab(
		String tariffPremAfterDiscountDedWaiverDriverLiab) {
	TariffPremAfterDiscountDedWaiverDriverLiab = tariffPremAfterDiscountDedWaiverDriverLiab;
}
public String getTariffPremAfterDiscountDedWaiverMOD() {
	return TariffPremAfterDiscountDedWaiverMOD;
}
public void setTariffPremAfterDiscountDedWaiverMOD(
		String tariffPremAfterDiscountDedWaiverMOD) {
	TariffPremAfterDiscountDedWaiverMOD = tariffPremAfterDiscountDedWaiverMOD;
}
public String getTariffPremAfterDiscountDedWaiverPssgrLiab() {
	return TariffPremAfterDiscountDedWaiverPssgrLiab;
}
public void setTariffPremAfterDiscountDedWaiverPssgrLiab(
		String tariffPremAfterDiscountDedWaiverPssgrLiab) {
	TariffPremAfterDiscountDedWaiverPssgrLiab = tariffPremAfterDiscountDedWaiverPssgrLiab;
}
public String getTariffPremAfterDiscountDedWaiverTheft() {
	return TariffPremAfterDiscountDedWaiverTheft;
}
public void setTariffPremAfterDiscountDedWaiverTheft(
		String tariffPremAfterDiscountDedWaiverTheft) {
	TariffPremAfterDiscountDedWaiverTheft = tariffPremAfterDiscountDedWaiverTheft;
}
public String getTariffPremAfterDiscountDedWaiverVTPL() {
	return TariffPremAfterDiscountDedWaiverVTPL;
}
public void setTariffPremAfterDiscountDedWaiverVTPL(
		String tariffPremAfterDiscountDedWaiverVTPL) {
	TariffPremAfterDiscountDedWaiverVTPL = tariffPremAfterDiscountDedWaiverVTPL;
}
public String getPremNoClaimsDiscount() {
	return PremNoClaimsDiscount;
}
public void setPremNoClaimsDiscount(String premNoClaimsDiscount) {
	PremNoClaimsDiscount = premNoClaimsDiscount;
}
public String getPremTrafficViolation() {
	return PremTrafficViolation;
}
public void setPremTrafficViolation(String premTrafficViolation) {
	PremTrafficViolation = premTrafficViolation;
}
public String getPremUnderwritingFactor() {
	return PremUnderwritingFactor;
}
public void setPremUnderwritingFactor(String premUnderwritingFactor) {
	PremUnderwritingFactor = premUnderwritingFactor;
}
public String getPremChannelFactor() {
	return PremChannelFactor;
}
public void setPremChannelFactor(String premChannelFactor) {
	PremChannelFactor = premChannelFactor;
}
public String getPremTotal() {
	return PremTotal;
}
public void setPremTotal(String premTotal) {
	PremTotal = premTotal;
}
public String getBusinessRulesResults() {
	return BusinessRulesResults;
}
public void setBusinessRulesResults(String businessRulesResults) {
	BusinessRulesResults = businessRulesResults;
}
public String getBusinessRulesErrorMessages() {
	return BusinessRulesErrorMessages;
}
public void setBusinessRulesErrorMessages(String businessRulesErrorMessages) {
	BusinessRulesErrorMessages = businessRulesErrorMessages;
}
public String getBusinessRulesEndorsements() {
	return BusinessRulesEndorsements;
}
public void setBusinessRulesEndorsements(String businessRulesEndorsements) {
	BusinessRulesEndorsements = businessRulesEndorsements;
}
public String getRadarNotes() {
	return RadarNotes;
}
public void setRadarNotes(String radarNotes) {
	RadarNotes = radarNotes;
}
public String getRadarModelName() {
	return RadarModelName;
}
public void setRadarModelName(String radarModelName) {
	RadarModelName = radarModelName;
}
public String getBaseinforOrderNo() {
	return BaseinforOrderNo;
}
public void setBaseinforOrderNo(String baseinforOrderNo) {
	BaseinforOrderNo = baseinforOrderNo;
}
public String getIsChoiceCombustion() {
	return IsChoiceCombustion;
}
public void setIsChoiceCombustion(String isChoiceCombustion) {
	IsChoiceCombustion = isChoiceCombustion;
}
public String getIsChoiceCTPL() {
	return IsChoiceCTPL;
}
public void setIsChoiceCTPL(String isChoiceCTPL) {
	IsChoiceCTPL = isChoiceCTPL;
}
public String getIsChoiceTheft() {
	return IsChoiceTheft;
}
public void setIsChoiceTheft(String isChoiceTheft) {
	IsChoiceTheft = isChoiceTheft;
}
public String getIsChoiceMOD() {
	return isChoiceMOD;
}
public void setIsChoiceMOD(String isChoiceMOD) {
	this.isChoiceMOD = isChoiceMOD;
}
public String getIsChoiceGlass() {
	return isChoiceGlass;
}
public void setIsChoiceGlass(String isChoiceGlass) {
	this.isChoiceGlass = isChoiceGlass;
}
public String getIsChoiceDriver() {
	return IsChoiceDriver;
}
public void setIsChoiceDriver(String isChoiceDriver) {
	IsChoiceDriver = isChoiceDriver;
}
public String getIsChoicePassenger() {
	return IsChoicePassenger;
}
public void setIsChoicePassenger(String isChoicePassenger) {
	IsChoicePassenger = isChoicePassenger;
}
public String getIsChoiceVTPL() {
	return IsChoiceVTPL;
}
public void setIsChoiceVTPL(String isChoiceVTPL) {
	IsChoiceVTPL = isChoiceVTPL;
}
public String getIsChoiceDedWaiverCombust() {
	return IsChoiceDedWaiverCombust;
}
public void setIsChoiceDedWaiverCombust(String isChoiceDedWaiverCombust) {
	IsChoiceDedWaiverCombust = isChoiceDedWaiverCombust;
}
public String getIsChoiceDedWaiverTheft() {
	return IsChoiceDedWaiverTheft;
}
public void setIsChoiceDedWaiverTheft(String isChoiceDedWaiverTheft) {
	IsChoiceDedWaiverTheft = isChoiceDedWaiverTheft;
}
public String getIsChoiceDedWaiverMOD() {
	return IsChoiceDedWaiverMOD;
}
public void setIsChoiceDedWaiverMOD(String isChoiceDedWaiverMOD) {
	IsChoiceDedWaiverMOD = isChoiceDedWaiverMOD;
}
public String getIsChoiceDedWaiverDriver() {
	return IsChoiceDedWaiverDriver;
}
public void setIsChoiceDedWaiverDriver(String isChoiceDedWaiverDriver) {
	IsChoiceDedWaiverDriver = isChoiceDedWaiverDriver;
}
public String getIsChoiceDedWaiverPassenger() {
	return IsChoiceDedWaiverPassenger;
}
public void setIsChoiceDedWaiverPassenger(String isChoiceDedWaiverPassenger) {
	IsChoiceDedWaiverPassenger = isChoiceDedWaiverPassenger;
}
public String getIsChoiceDedWaiverVTPL() {
	return IsChoiceDedWaiverVTPL;
}
public void setIsChoiceDedWaiverVTPL(String isChoiceDedWaiverVTPL) {
	IsChoiceDedWaiverVTPL = isChoiceDedWaiverVTPL;
}
public String getJYDisplacement() {
	return JYDisplacement;
}
public void setJYDisplacement(String jYDisplacement) {
	JYDisplacement = jYDisplacement;
}
public String getOwnerAgeAtPolicyStart() {
	return OwnerAgeAtPolicyStart;
}
public void setOwnerAgeAtPolicyStart(String ownerAgeAtPolicyStart) {
	OwnerAgeAtPolicyStart = ownerAgeAtPolicyStart;
}
public String getOwnerDOB() {
	return OwnerDOB;
}
public void setOwnerDOB(String ownerDOB) {
	OwnerDOB = ownerDOB;
}
public String getOwnerGender() {
	return OwnerGender;
}
public void setOwnerGender(String ownerGender) {
	OwnerGender = ownerGender;
}
public String getProposerAgeAtPolicyStart() {
	return ProposerAgeAtPolicyStart;
}
public void setProposerAgeAtPolicyStart(String proposerAgeAtPolicyStart) {
	ProposerAgeAtPolicyStart = proposerAgeAtPolicyStart;
}
public String getProposerDOB() {
	return ProposerDOB;
}
public void setProposerDOB(String proposerDOB) {
	ProposerDOB = proposerDOB;
}
public String getProposerGender() {
	return ProposerGender;
}
public void setProposerGender(String proposerGender) {
	ProposerGender = proposerGender;
}
public String getPolicyChannel() {
	return PolicyChannel;
}
public void setPolicyChannel(String policyChannel) {
	PolicyChannel = policyChannel;
}
public String getPolicyDurationwithQS() {
	return PolicyDurationwithQS;
}
public void setPolicyDurationwithQS(String policyDurationwithQS) {
	PolicyDurationwithQS = policyDurationwithQS;
}
public String getPolicyDurationwithSS() {
	return PolicyDurationwithSS;
}
public void setPolicyDurationwithSS(String policyDurationwithSS) {
	PolicyDurationwithSS = policyDurationwithSS;
}
public String getPolicyFirstStartDate() {
	return PolicyFirstStartDate;
}
public void setPolicyFirstStartDate(String policyFirstStartDate) {
	PolicyFirstStartDate = policyFirstStartDate;
}
public String getPolicyKnownSS() {
	return PolicyKnownSS;
}
public void setPolicyKnownSS(String policyKnownSS) {
	PolicyKnownSS = policyKnownSS;
}
public String getPolicyLastYearProvince() {
	return PolicyLastYearProvince;
}
public void setPolicyLastYearProvince(String policyLastYearProvince) {
	PolicyLastYearProvince = policyLastYearProvince;
}
public String getPolicyStartDateComm() {
	return PolicyStartDateComm;
}
public void setPolicyStartDateComm(String policyStartDateComm) {
	PolicyStartDateComm = policyStartDateComm;
}
public String getPolicyStartDateCTPL() {
	return PolicyStartDateCTPL;
}
public void setPolicyStartDateCTPL(String policyStartDateCTPL) {
	PolicyStartDateCTPL = policyStartDateCTPL;
}
public String getPTPolicyEndDateComm() {
	return PTPolicyEndDateComm;
}
public void setPTPolicyEndDateComm(String pTPolicyEndDateComm) {
	PTPolicyEndDateComm = pTPolicyEndDateComm;
}
public String getPTPolicyEndDateCTPL() {
	return PTPolicyEndDateCTPL;
}
public void setPTPolicyEndDateCTPL(String pTPolicyEndDateCTPL) {
	PTPolicyEndDateCTPL = pTPolicyEndDateCTPL;
}
public String getScreenProcessCurrentStep() {
	return ScreenProcessCurrentStep;
}
public void setScreenProcessCurrentStep(String screenProcessCurrentStep) {
	ScreenProcessCurrentStep = screenProcessCurrentStep;
}
public String getScreenProcessFurthestStep() {
	return ScreenProcessFurthestStep;
}
public void setScreenProcessFurthestStep(String screenProcessFurthestStep) {
	ScreenProcessFurthestStep = screenProcessFurthestStep;
}
public String getVehicleAgeatPolicyStartMonths() {
	return VehicleAgeatPolicyStartMonths;
}
public void setVehicleAgeatPolicyStartMonths(
		String vehicleAgeatPolicyStartMonths) {
	VehicleAgeatPolicyStartMonths = vehicleAgeatPolicyStartMonths;
}
public String getVehicleAgeAtPolicyStartYrs() {
	return VehicleAgeAtPolicyStartYrs;
}
public void setVehicleAgeAtPolicyStartYrs(String vehicleAgeAtPolicyStartYrs) {
	VehicleAgeAtPolicyStartYrs = vehicleAgeAtPolicyStartYrs;
}
public String getVehicleCurrentGlassType() {
	return VehicleCurrentGlassType;
}
public void setVehicleCurrentGlassType(String vehicleCurrentGlassType) {
	VehicleCurrentGlassType = vehicleCurrentGlassType;
}
public String getVehicleLocationPostalCode() {
	return VehicleLocationPostalCode;
}
public void setVehicleLocationPostalCode(String vehicleLocationPostalCode) {
	VehicleLocationPostalCode = vehicleLocationPostalCode;
}
public String getVehicleLocationProvince() {
	return VehicleLocationProvince;
}
public void setVehicleLocationProvince(String vehicleLocationProvince) {
	VehicleLocationProvince = vehicleLocationProvince;
}
public String getVehicleSSHighRiskGroup() {
	return VehicleSSHighRiskGroup;
}
public void setVehicleSSHighRiskGroup(String vehicleSSHighRiskGroup) {
	VehicleSSHighRiskGroup = vehicleSSHighRiskGroup;
}
public String getSSPremCombustion() {
	return SSPremCombustion;
}
public void setSSPremCombustion(String sSPremCombustion) {
	SSPremCombustion = sSPremCombustion;
}
public String getSSPremDedWaiverCombust() {
	return SSPremDedWaiverCombust;
}
public void setSSPremDedWaiverCombust(String sSPremDedWaiverCombust) {
	SSPremDedWaiverCombust = sSPremDedWaiverCombust;
}
public String getSSPremDedWaiverDriverLiab() {
	return SSPremDedWaiverDriverLiab;
}
public void setSSPremDedWaiverDriverLiab(String sSPremDedWaiverDriverLiab) {
	SSPremDedWaiverDriverLiab = sSPremDedWaiverDriverLiab;
}
public String getSSPremDedWaiverMOD() {
	return SSPremDedWaiverMOD;
}
public void setSSPremDedWaiverMOD(String sSPremDedWaiverMOD) {
	SSPremDedWaiverMOD = sSPremDedWaiverMOD;
}
public String getSSPremDedWaiverPssgrLiab() {
	return SSPremDedWaiverPssgrLiab;
}
public void setSSPremDedWaiverPssgrLiab(String sSPremDedWaiverPssgrLiab) {
	SSPremDedWaiverPssgrLiab = sSPremDedWaiverPssgrLiab;
}
public String getSSPremDedWaiverTheft() {
	return SSPremDedWaiverTheft;
}
public void setSSPremDedWaiverTheft(String sSPremDedWaiverTheft) {
	SSPremDedWaiverTheft = sSPremDedWaiverTheft;
}
public String getSSPremDedWaiverVTPL() {
	return SSPremDedWaiverVTPL;
}
public void setSSPremDedWaiverVTPL(String sSPremDedWaiverVTPL) {
	SSPremDedWaiverVTPL = sSPremDedWaiverVTPL;
}
public String getSSPremDriverLiab() {
	return SSPremDriverLiab;
}
public void setSSPremDriverLiab(String sSPremDriverLiab) {
	SSPremDriverLiab = sSPremDriverLiab;
}
public String getSSPremGlass() {
	return SSPremGlass;
}
public void setSSPremGlass(String sSPremGlass) {
	SSPremGlass = sSPremGlass;
}
public String getSSPremGovtVehicleTax() {
	return SSPremGovtVehicleTax;
}
public void setSSPremGovtVehicleTax(String sSPremGovtVehicleTax) {
	SSPremGovtVehicleTax = sSPremGovtVehicleTax;
}
public String getSSPremMOD() {
	return SSPremMOD;
}
public void setSSPremMOD(String sSPremMOD) {
	SSPremMOD = sSPremMOD;
}
public String getSSPremPassengerLiab() {
	return SSPremPassengerLiab;
}
public void setSSPremPassengerLiab(String sSPremPassengerLiab) {
	SSPremPassengerLiab = sSPremPassengerLiab;
}
public String getSSPremVehicleTheft() {
	return SSPremVehicleTheft;
}
public void setSSPremVehicleTheft(String sSPremVehicleTheft) {
	SSPremVehicleTheft = sSPremVehicleTheft;
}
public String getSSPremVTPL() {
	return SSPremVTPL;
}
public void setSSPremVTPL(String sSPremVTPL) {
	SSPremVTPL = sSPremVTPL;
}
public String getSSPremTotal() {
	return SSPremTotal;
}
public void setSSPremTotal(String sSPremTotal) {
	SSPremTotal = sSPremTotal;
}
   
   
   
   
}
