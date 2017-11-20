package com.quicksure.mobile.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 车辆信息
 * 
 * @author lenny.li001
 * 
 */
@Component
public class Vhlinfor {
	private int vhiinforid;

	private String lcnno; // 车牌号

	private String engno; // 发动机号

	private String vinno; // 车架号

	private String brandcode; // 厂牌车型代码
	
	private String highriskcar;//高风险车型

	private String vhlval; // 新车购置价

	private String vehiclename; // 车型名称

	private Integer carsort; // 车型分类

	private String carstyle; // 车型种类 进口/国产

	private String carmaker; // 制造厂商

	private Float tonnage; // 吨位

	private String setno; // 座位数

	private String displacement; // 排气量

	private Float quality; // 整备质量

	private String marketyear; // 制造年份

	private String carremark; // 车型备注

	private String riskname; // 风险类型

	private Integer fuelflag; // 车船税减免标志

	private Integer newenergyflag; // 新能源标志

	private String model; // 车型

	private String registerdate; // 登记日期

	private String chgownerflag; // 过户车标志

	private String transferdate; // 转移登记日期

	private String glasstype; // 玻璃种类

	private String certificatetype; // 车主证件类型

	private String certificateno; // 证件号码

	private Date certificatedate; // 车辆购置日期

	private String drvowner; // 车主姓名

	private String certitype; // 车主证件类型

	private String certicode; // 车主证件号码

	private String phoneno; // 车主手机号码

	private String createdatatime;

	private String updatetime;

	private String actualVal;// 车辆实际价格

	private String familyKind;// 车型级别

	private String baseinforOrdeo;
	
	private String brandName;//品牌名称
	
	private String demio;    //车系
	
	private String codeName;//品牌代码名称
	
	private String absflag;//abs标志
	
	private Integer alarmflag;//警告标志
	
	private Integer airbagfalg;//空气包标志
	
	private String gearboxtype;//档位类型
	
	private String fullweight;//全车重量
	
	private String setNumber;//核定载客人数
	
	
	
	public String getHighriskcar() {
		return highriskcar;
	}

	public void setHighriskcar(String highriskcar) {
		this.highriskcar = highriskcar;
	}

	public String getDemio() {
		return demio;
	}

	public void setDemio(String demio) {
		this.demio = demio;
	}

	public String getFullweight() {
		return fullweight;
	}

	public String getSetNumber() {
		return setNumber;
	}

	public void setSetNumber(String setNumber) {
		this.setNumber = setNumber;
	}

	public void setFullweight(String fullweight) {
		this.fullweight = fullweight;
	}

	public String getAbsflag() {
		return absflag;
	}

	public void setAbsflag(String absflag) {
		this.absflag = absflag;
	}

	public Integer getAlarmflag() {
		return alarmflag;
	}

	public void setAlarmflag(Integer alarmflag) {
		this.alarmflag = alarmflag;
	}

	public Integer getAirbagfalg() {
		return airbagfalg;
	}

	public void setAirbagfalg(Integer airbagfalg) {
		this.airbagfalg = airbagfalg;
	}

	public String getGearboxtype() {
		return gearboxtype;
	}

	public void setGearboxtype(String gearboxtype) {
		this.gearboxtype = gearboxtype;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBaseinforOrdeo() {
		return baseinforOrdeo;
	}

	public void setBaseinforOrdeo(String baseinforOrdeo) {
		this.baseinforOrdeo = baseinforOrdeo;
	}

	public String getActualVal() {
		return actualVal;
	}

	public void setActualVal(String actualVal) {
		this.actualVal = actualVal;
	}

	public String getFamilyKind() {
		return familyKind;
	}

	public void setFamilyKind(String familyKind) {
		this.familyKind = familyKind;
	}

	public int getVhiinforid() {
		return vhiinforid;
	}

	public void setVhiinforid(int vhiinforid) {
		this.vhiinforid = vhiinforid;
	}

	public String getLcnno() {
		return lcnno;
	}

	public void setLcnno(String lcnno) {
		this.lcnno = lcnno == null ? null : lcnno.trim();
	}

	public String getEngno() {
		return engno;
	}

	public void setEngno(String engno) {
		this.engno = engno == null ? null : engno.trim();
	}

	public String getVinno() {
		return vinno;
	}

	public void setVinno(String vinno) {
		this.vinno = vinno == null ? null : vinno.trim();
	}

	public String getBrandcode() {
		return brandcode;
	}

	public void setBrandcode(String brandcode) {
		this.brandcode = brandcode == null ? null : brandcode.trim();
	}

	public String getVehiclename() {
		return vehiclename;
	}

	public void setVehiclename(String vehiclename) {
		this.vehiclename = vehiclename == null ? null : vehiclename.trim();
	}

	public Integer getCarsort() {
		return carsort;
	}

	public void setCarsort(Integer carsort) {
		this.carsort = carsort;
	}

	public String getCarstyle() {
		return carstyle;
	}

	public void setCarstyle(String carstyle) {
		this.carstyle = carstyle == null ? null : carstyle.trim();
	}

	public String getCarmaker() {
		return carmaker;
	}

	public void setCarmaker(String carmaker) {
		this.carmaker = carmaker == null ? null : carmaker.trim();
	}

	public Float getTonnage() {
		return tonnage;
	}

	public void setTonnage(Float tonnage) {
		this.tonnage = tonnage;
	}

	public Float getQuality() {
		return quality;
	}

	public void setQuality(Float quality) {
		this.quality = quality;
	}

	public String getCarremark() {
		return carremark;
	}

	public void setCarremark(String carremark) {
		this.carremark = carremark == null ? null : carremark.trim();
	}

	public String getRiskname() {
		return riskname;
	}

	public void setRiskname(String riskname) {
		this.riskname = riskname == null ? null : riskname.trim();
	}

	public Integer getFuelflag() {
		return fuelflag;
	}

	public void setFuelflag(Integer fuelflag) {
		this.fuelflag = fuelflag;
	}

	public Integer getNewenergyflag() {
		return newenergyflag;
	}

	public void setNewenergyflag(Integer newenergyflag) {
		this.newenergyflag = newenergyflag;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model == null ? null : model.trim();
	}

	public String getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}

	public String getVhlval() {
		return vhlval;
	}

	public void setVhlval(String vhlval) {
		this.vhlval = vhlval;
	}

	public String getSetno() {
		return setno;
	}

	public void setSetno(String setno) {
		this.setno = setno;
	}

	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	public String getMarketyear() {
		return marketyear;
	}

	public void setMarketyear(String marketyear) {
		this.marketyear = marketyear;
	}

	public String getChgownerflag() {
		return chgownerflag;
	}

	public void setChgownerflag(String chgownerflag) {
		this.chgownerflag = chgownerflag;
	}

	public String getTransferdate() {
		return transferdate;
	}

	public void setTransferdate(String transferdate) {
		this.transferdate = transferdate;
	}

	public String getGlasstype() {
		return glasstype;
	}

	public void setGlasstype(String glasstype) {
		this.glasstype = glasstype == null ? null : glasstype.trim();
	}

	public String getCertificatetype() {
		return certificatetype;
	}

	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype == null ? null : certificatetype
				.trim();
	}

	public String getCertificateno() {
		return certificateno;
	}

	public void setCertificateno(String certificateno) {
		this.certificateno = certificateno == null ? null : certificateno
				.trim();
	}

	public Date getCertificatedate() {
		return certificatedate;
	}

	public void setCertificatedate(Date certificatedate) {
		this.certificatedate = certificatedate;
	}

	public String getDrvowner() {
		return drvowner;
	}

	public void setDrvowner(String drvowner) {
		this.drvowner = drvowner == null ? null : drvowner.trim();
	}

	public String getCertitype() {
		return certitype;
	}

	public void setCertitype(String certitype) {
		this.certitype = certitype == null ? null : certitype.trim();
	}

	public String getCerticode() {
		return certicode;
	}

	public void setCerticode(String certicode) {
		this.certicode = certicode == null ? null : certicode.trim();
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getCreatedatatime() {
		return createdatatime;
	}

	public void setCreatedatatime(String createdatatime) {
		this.createdatatime = createdatatime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
}