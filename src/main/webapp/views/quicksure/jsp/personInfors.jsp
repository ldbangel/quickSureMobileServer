<%@page import="com.quicksure.mobile.entity.InsuranceDetailsVO"%>
<%@ page language="java" import="java.util.*,com.quicksure.mobile.utility.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String nowDate=DateFormatUtils.getSystemDateByYYYYMMDDHHMMSSSSS();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

/* InsuranceDetailsVO insuranceDetailsVO=(InsuranceDetailsVO)session.getAttribute("insuranceDetailsVO");
String owndress="";
String owndetaildress="";
String appdress="";
String appdetaildress="";
String insrdress="";
String insrdetaildress="";
String deldress="";
String deldetaildress="";
if(insuranceDetailsVO!=null){
	owndress=insuranceDetailsVO.getBaseinfor().getDeptAddress()==null?"":insuranceDetailsVO.getBaseinfor().getDeptAddress();
	insuranceDetailsVO.getBaseinfor().setDeptAddress(owndetaildress.split("-")[0]);
	if(owndress.contains("-")){
		owndetaildress=owndress.split("-")[1];
	}
	appdress=insuranceDetailsVO.getInsuranceperinfor().getApplicationaddress()==null?"":insuranceDetailsVO.getInsuranceperinfor().getApplicationaddress();
	insuranceDetailsVO.getInsuranceperinfor().setApplicationaddress(appdress.split("-")[0]);
	if(appdress.contains("-")){
		appdetaildress=appdress.split("-")[1];
	}
	insrdress=insuranceDetailsVO.getInsuranceperinfor().getInsureaddress()==null?"":insuranceDetailsVO.getInsuranceperinfor().getInsureaddress();
	insuranceDetailsVO.getInsuranceperinfor().setInsureaddress(insrdress.split("-")[0]);
	if(insrdress.contains("-")){
		insrdetaildress=insrdress.split("-")[1];
	}
	deldress=insuranceDetailsVO.getDeliveryinfor().getDeliveryaddress()==null?"":insuranceDetailsVO.getDeliveryinfor().getDeliveryaddress();
	insuranceDetailsVO.getDeliveryinfor().setDeliveryaddress(deldress.split("-")[0]);
	if(deldress.contains("-")){
		deldetaildress=deldress.split("-")[1];
	}
} */
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="format-detection" content="telephone=no"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<title>确认信息</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/views/quicksure/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/css.css">
<link rel="stylesheet" href="<%=path%>/views/quicksure/css/mobiscroll_date.css"/>
<link rel="stylesheet" href="<%=path%>/views/quicksure/css/LArea.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/normalize.css" />
 <link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/htmleaf-demo.css">
 <link rel="stylesheet" href="<%=path%>/views/quicksure/css/optiscroll.css">
 <script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/quicksure/swiper-3.4.0.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/js.js"></script>
 <script id='cityJson'></script>
 <script id='citySet'></script>
 <script id='Popt'></script>
<script type="text/javascript">
var firstscreenFlag=false;
javascript:window.history.forward(1); 
</script>
    
   
    <script type="text/javascript" src="<%=path%>/views/quicksure/scripts/optiscroll.js"></script>
<style>
    .con4_div>.con4_p1{width:100%;text-align:left;}
   .con4_p1>span{display:inline-block;width:60%;}
   .errorpop{display:none;font-size: 1rem;font-size: 0.6rem;
  padding-left: 4%;
  margin-top: -4%;
  }
  .con4_p1 .con4_span{max-width:90px;text-align:-webkit-center;}
  
._citys { min-width: 450px; display: inline-block; border: 2px solid #eee; padding: 5px; position: relative; background:#fff;}
._citys span { color: #56b4f8; height: 15px; width: 15px; line-height: 15px; text-align: center; border-radius: 3px; position: absolute; right: 10px; top: 10px; border: 1px solid #56b4f8; cursor: pointer; }
._citys0 { width: 100%; height: 34px; display: inline-block; border-bottom: 2px solid #56b4f8; padding: 0; margin: 0; }
._citys0 li { display: inline-block; line-height: 34px; font-size: 15px; color: #888; width: 80px; text-align: center; cursor: pointer; }
.citySel { background-color: #56b4f8; color: #fff !important; }
._citys1 { width: 100%; display: inline-block; padding: 10px 0; }
._citys1 a { max-width: 83px; height: 35px; display: inline-block; background-color: #f5f5f5; color: #666; margin-left: 6px; margin-top: 3px; line-height: 35px; text-align: center; cursor: pointer; font-size: 13px; overflow: hidden;padding:0px 5px; }
._citys1 a:hover { color: #fff; background-color: #56b4f8; }
.AreaS { background-color: #56b4f8 !important; color: #fff !important; }
</style>
</head>

<body>
<div class="div640">
    <div class="div600 index7">
     <div class="ul_bei">
  <img src="<%=path%>/views/quicksure/images/sure.png">
		    <ul class="ul_a">
		    
		      <li><a href="javascript:void(0);">基本信息</a></li>
		      <li><a href="javascript:void(0);">报价信息</a></li>
		      <li class="on"><a href="javascript:void(0);">确认信息</a></li>
		      <li><a href="javascript:void(0);">支付</a></li>
		     
		    </ul>
     <div class="clear"></div>
     </div>
      <p class="index7_p1 index4_p1" style='margin:0 5px 0px 10px;'>请核实确认以下信息。交强险合同生效后，您将不能因为信息错误等原因申请退保。</p>

     <div class="content7 boder1 content4">
       <div class="con4_div">
        <p class="con4_p1"><span class='con4_span'>车牌号码：</span><span>${insuranceDetailsVO.vhlinfor.lcnno}</span></p>
        <p class="con4_p1"><span class='con4_span'>车架号码：</span><span>${insuranceDetailsVO.vhlinfor.vinno}</span></p>
        <p class="con4_p1"><span class='con4_span'>发动机号：</span><span>${insuranceDetailsVO.vhlinfor.engno}</span></p>
        <p class="con4_p1"><span class='con4_span'>品牌型号：</span><span>${insuranceDetailsVO.vhlinfor.model}</span></p>
       </div>
      <table class="con9_table">
       <tr class="tr1"><td class="td1">投保内容</td><td class="td2">保险起期</td><td class="td3" style="color: #333333;">金额</td></tr>
       <c:if test="${!empty insuranceDetailsVO.baseinfor.jqpremium}"><tr><td class="td1">交强险</td><td class="td2">&yen;${insuranceDetailsVO.baseinfor.jqpolicystartdate}起</td><td class="td3">&yen;${insuranceDetailsVO.baseinfor.jqpremium}</td></tr></c:if>
       <c:if test="${!empty insuranceDetailsVO.baseinfor.sypremium}"><tr><td class="td1">商业险</td><td class="td2">&yen;${insuranceDetailsVO.baseinfor.sypolicystartdate}起</td><td class="td3">&yen;${insuranceDetailsVO.baseinfor.sypremium}</td></tr></c:if>
       <c:if test="${!empty insuranceDetailsVO.baseinfor.taxpremium}"><tr><td class="td1">车船税</td><td class="td2"></td><td class="td3">&yen;${insuranceDetailsVO.baseinfor.taxpremium}</td></tr></c:if>
      </table>

      <table class="con9_table con9_table2">
      <tr><td class="td1"><span id="ming_to" style='color: #e82418;'>点击查看投保明细</span></td><td>合计：<span>&yen;<fmt:formatNumber value="${insuranceDetailsVO.baseinfor.totalPremium + 0.0001}" pattern="#,##0.00"/></span></td></tr>
      </table>

      
      <div class="ying_table">
      <table class="con9_table con9_table3">
      		<tr>
                <th style="font-size: 13px;">险种</th>
                <th style="font-size: 13px;">保额</th>
                <th style="font-size: 13px;">保费</th>
                <th style="font-size: 13px;">不计免赔</th>
            </tr>
            <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s">
            <tr>
                <td><c:if test="${s.insrnccode=='030101'}">车辆损失险</c:if>
                	<c:if test="${s.insrnccode=='030102'}">第三者责任险</c:if>
                	<c:if test="${s.insrnccode=='030103'}">全车盗强险</c:if>
                	<c:if test="${s.insrnccode=='030104'}">司机座位责任险</c:if>
                	<c:if test="${s.insrnccode=='030105'}">乘客座位责任险</c:if>
                	<c:if test="${s.insrnccode=='030107'}">玻璃破碎险</c:if>
                	<c:if test="${s.insrnccode=='030108'}">自燃险</c:if>
                	<c:if test="${s.insrnccode=='030115'}">机动车损失无法找到第三方损失险</c:if>
                	<c:if test="${s.insrnccode=='030116'}">制定专车修理厂</c:if>
                	<%-- <c:if test="${s.insrnccode=='030119'}">不计免赔总保费</c:if> --%>
                	<c:if test="${s.insrnccode=='0357'}">交强险</c:if>
                </td>
                <td>${s.suminsured}</td>
                <td><c:if test="${s.insrnccode!='030119'}">${s.premium}</c:if></td>
                <td>${s.nyl12}</td>
            </tr>
     		</c:forEach>
      </table>

      </div>

      <style type="text/css">
       .con9_table3{ margin-top: 0; border-top: none;}
       .ying_table{ display: none;}
      </style>
     </div>
     
     <form action="<%=path%>/submitInfor/submitUnderwriting.do" method="post" id="submit_form">
     <input type="text" style="display: none;" id="orderNo" name="orderNo" value="${insuranceDetailsVO.baseinfor.orderno}" readonly="readonly"/>
     <input type="text" style="display: none;" id="appCoypCheckbox" name="appCoypCheckbox" value="${insuranceDetailsVO.insuranceperinfor.appCoypCheckbox}" readonly="readonly"/>
     <input type="text" style="display: none;" id="insureCopyCheckbox" name="insureCopyCheckbox" value="${insuranceDetailsVO.insuranceperinfor.insureCopyCheckbox}" readonly="readonly"/>
     <input type="text" style="display: none;" id="deliveryCopyCheckbox" name="deliveryCopyCheckbox" value="${insuranceDetailsVO.insuranceperinfor.deliveryCopyCheckbox}" readonly="readonly"/>
     <div class="con_div3 boder1">
     <ul class="con_ul" id="con_ultab">
      <li class="on">车主信息</li>
      <li>被保人<span><input type="checkbox" id="appCopy" value="appCopy" <c:if test="${insuranceDetailsVO.insuranceperinfor.appCoypCheckbox=='1'}">checked="checked"</c:if> ></span>同车主</li>
      <li class="last">投保人<span><input type="checkbox" id="insureCopy" value="insureCopy" <c:if test="${insuranceDetailsVO.insuranceperinfor.insureCopyCheckbox=='1'}">checked="checked"</c:if>></span>同车主</li>
      <div class="clear"></div>
     </ul>
	
    <div class="con_ul_tab">
     <div style=" display: block;">
     
     <div class="con_div_a con_div_b">
       <p class="p1">车主姓名：</p>
       <input class="text6" type="text" required ='required'value="${insuranceDetailsVO.vhlinfor.drvowner}" name="ownersname" id="ownersname" maxlength="4" onchange="changevalue('ownersname');"/>
       <div class="clear"></div>
     </div>
     <div class="con_div_a">
       <p class="p1">身份证号：</p>
       <input class="text6" type="text" required ='required'value="${insuranceDetailsVO.vhlinfor.certificateno}" name="ownerscerticode" id="ownerscerticode" maxlength="18" onchange="changevalue('ownerscerticode');" onblur="setUpperCase(this);identityCodeValid();" 
       onkeyup="if(value!=value.replace(/[\W]/g,''))value=value.replace(/[\W]/g,'')"/>
      <%--  <img src="<%=path%>/views/quicksure/images/xx.png"  class='Clear_Val' style='display:none;position:relative;top:4px;right:25px;'> --%>
  <!--      </div> -->
       <div class="clear"></div>
     </div>
     <div class="con_div_a">
       <p class="p1">手机号：</p>
       <input class="text6" type="text" required ='required'value="${insuranceDetailsVO.vhlinfor.phoneno}" name="ownersphoneno" id="ownersphoneno" maxlength="11" onchange="changevalue('ownersphoneno');" 
      onKeyUp="if(value!=value.replace(/[^\d]/g,''))value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
       <div class="clear"></div>
     </div>
     <div class="con_div_a">
       <p class="p1">车主地址：</p>
       <input id="ownersaddress" name="ownersaddress"  type="text" class="text6"  readonly placeholder="请选择对应的城市" onblur="setDeptNo(this.value,1)" value="${insuranceDetailsVO.baseinfor.deptAddress}" onchange="changevalue('ownersaddress');"/>
       <input id="city1" name="ownersaddress"  type="text" class="text6 city"  readonly placeholder="请选择对应的城市" onblur="setDeptNo(this.value,1)" value="${insuranceDetailsVO.baseinfor.deptAddress}" onchange="changevalue('ownersaddress');" style="display:none"/>
       <input class="text6 text7" type="text" placeholder="*必填——详细地址" id="ownersdetailaddress" required ='required' name="ownersdetailaddress" onchange="changevalue('ownersdetailaddress');" value="${insuranceDetailsVO.insuranceperinfor.ownersdetailaddress}" />
       <div class="clear"></div>
       <input type="hidden" id="ownerszipcode" name="ownerszipcode">
     </div>

     </div>

      <div>
        <p class="con_ppp"></p>
          <div class="con_div_a con_div_b">
          <p class="p1">被保人姓名：</p>
          <input class="text6" type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationname}" placeholder="请输入被保人姓名" required ='required' maxlength="4" name="applicationname" id="applicationname" />
          <div class="clear"></div>
         </div>
      <div class="con_div_a">
       <p class="p1">身份证号：</p>
       <input class="text6" type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationcerticode}" placeholder="请输入被保人身份证号码" 
       	required ='required' name="applicationcerticode" id="applicationcerticode" maxlength="18"  onblur="setUpperCase(this);identityCodeValid();" 
        onkeyup="if(value!=value.replace(/[\W]/g,''))value=value.replace(/[\W]/g,'')"/>
       <div class="clear"></div>
       </div>
         <div class="con_div_a">
          <p class="p1">手机号：</p>
          <input class="text6" type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationphoneno}" placeholder="请输入被保人手机号码" required ='required'name="applicationphoneno" id="applicationphoneno" maxlength="11" 
         onKeyUp="if(value!=value.replace(/[^\d]/g,''))value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
          <div class="clear"></div>            
         </div>
     <div class="con_div_a">
       <p class="p1">被保人地址：</p>
       <input id="applicationaddress" name="applicationaddress"  type="text" class="text6"  readonly placeholder="请选择对应的城市" value="${insuranceDetailsVO.insuranceperinfor.applicationaddress}"/>
       <input id="city2" name="applicationaddress"  type="text" class="text6 city"  readonly placeholder="请选择对应的城市" value="${insuranceDetailsVO.insuranceperinfor.applicationaddress}" style="display:none" />
       <input class="text6 text7" type="text" placeholder="*必填——详细地址" id="applicationdetailaddress"  required ='required' name="applicationdetailaddress" value="${insuranceDetailsVO.insuranceperinfor.applicationdetailaddress}"/>
       <div class="clear"></div>
       <input type="hidden" id="applicationzipcode" name="applicationzipcode">
     </div>
     </div>

      <div>
          <div class="con_div_a con_div_b">
          <p class="p1">投保人姓名：</p>
          <input class="text6" required ='required'type="text" value="${insuranceDetailsVO.insuranceperinfor.insurename}" placeholder="请输入投保人姓名" name="insurename" id="insurename" maxlength="4"/>
          <div class="clear"></div>
         </div>
      <div class="con_div_a">
       <p class="p1">身份证号：</p>
       <input class="text6" required ='required'type="text" value="${insuranceDetailsVO.insuranceperinfor.insurecerticode}" placeholder="请输入投保人身份证号码" 
       	name="insurecerticode" id="insurecerticode"  maxlength="18" onblur="setUpperCase(this);identityCodeValid();" 
       	onkeyup="if(value!=value.replace(/[\W]/g,''))value=value.replace(/[\W]/g,'')"/>
       <div class="clear"></div>
       </div>
         <div class="con_div_a">
          <p class="p1">手机号：</p>
          <input class="text6" required ='required'type="text" value="${insuranceDetailsVO.insuranceperinfor.insurephoneno}" placeholder="请输入投保人手机号码" name="insurephoneno" id="insurephoneno" maxlength="11" 
        onKeyUp="if(value!=value.replace(/[^\d]/g,''))value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
          <div class="clear"></div>
         </div>
     <div class="con_div_a">
       <p class="p1">投保人地址：</p>
       <input id="insureaddress" name="insureaddress"  type="text" class="text6"  readonly placeholder="请选择对应的城市"  value="${insuranceDetailsVO.insuranceperinfor.insureaddress}"/>
       <input id="city3" name="insureaddress"  type="text" class="text6 city"  readonly placeholder="请选择对应的城市"  value="${insuranceDetailsVO.insuranceperinfor.insureaddress}" style="display:none"/>
       <input class="text6 text7" type="text" placeholder="*必填——详细地址" required ='required' id="insuredetailaddress"  name="insuredetailaddress" value="${insuranceDetailsVO.insuranceperinfor.insuredetailaddress}"/>
       <div class="clear"></div>
       <input type="hidden" id="insurezipcode" name="insurezipcode">
     </div>
			
      </div>
     </div>
     </div>
   
     
      <div class="con_div3 boder1">
     <ul class="con_ul cona_ul" id="con_uldelivery">
      <li class="last li">保单收件人<span><input type="checkbox" id="copyDelivery" value="deliveryCopy" <c:if test="${insuranceDetailsVO.insuranceperinfor.deliveryCopyCheckbox=='1'}">checked="checked"</c:if> ></span>同车主</li>
      <div class="clear"></div>
     </ul>
     
     <div class="con_div_a con_div_b">
       <p class="p1">收件人姓名：</p>
       <input class="text6" type="text" required ='required' id="deliveryname" name="deliveryname" placeholder="请输入收件人姓名" maxlength="4" value="${insuranceDetailsVO.deliveryinfor.deliveryname}" />
       <div class="clear"></div>
     </div>
     <div class="con_div_a">
       <p class="p1">手机号：</p>

       <input class="text6" type="text" required ='required' id="deliveryphone" name="deliveryphone" placeholder="请输入收件人手机号"  maxlength="11" value="${insuranceDetailsVO.deliveryinfor.deliveryphone}" onKeyUp="if(value!=value.replace(/[^\d]/g,''))value=value.replace(/[^\d]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>

       <div class="clear"></div>
     </div>
     <div class="con_div_a">
       <p class="p1">收件人地址：</p>
       <input id="deliveryaddress" name="deliveryaddress"  type="text" class="text6"  readonly placeholder="请选择对应的城市" value="${insuranceDetailsVO.deliveryinfor.deliveryaddress}" />
        <input id="city4" name="deliveryaddress"  type="text" class="text6 city"  readonly placeholder="请选择对应的城市" value="${insuranceDetailsVO.deliveryinfor.deliveryaddress}" style="display:none" />
       <input class="text6 text7" type="text" placeholder="*必填——详细地址" required ='required' id="deliverydetailaddress" name="deliverydetailaddress" value="${insuranceDetailsVO.insuranceperinfor.deliverydetailaddress}" />
       <div class="clear"></div>
       <input type="hidden" id="Delivery_ZipCode" name="Delivery_ZipCode">
     </div>
      <script>
      var ispc=false;
							                $(function (){
							                    var system={
							                    win:false,
							                    mac:false,
							                    x11:false
							                    };
													var p = navigator.platform;var urlpath = "<%=path%>";
													system.win = p.indexOf("Win") == 0;
													system.mac = p.indexOf("Mac") == 0;
													system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
													//跳转语句
													if(system.win||system.mac||system.xll){
													         ispc=true;
													         $("#city1").css('display','block');
													         $("#city2").css('display','block');
													         $("#city3").css('display','block');
													         $("#city4").css('display','block');
															$('#ownersaddress').remove();
															$('#applicationaddress').remove();
															$('#insureaddress').remove();
															$('#deliveryaddress').remove();
															
															$("#city1").click(function (e) {
																SelCity(this,e);
															});
															$("#city2").click(function (e) {
																SelCity(this,e);
															});
															$("#city3").click(function (e) {
																SelCity(this,e);
															});
															$("#city4").click(function (e) {
																SelCity(this,e);
															});
															$('#cityJson').attr('src',urlpath+"/views/quicksure/scripts/quicksure/cityJson.js");
															$('#citySet').attr('src',urlpath+"/views/quicksure/scripts/quicksure/citySet.js");
															$('#Popt').attr('src',urlpath+"/views/quicksure/scripts/quicksure/Popt.js");
													
													}else{
													     ispc=false;
														$('#city1').remove();
														$('#city2').remove();
														$('#city3').remove();
														$('#city4').remove();
											           var area1 = new LArea();
													  area1.init({
													  	'trigger': '#ownersaddress', 
													  	'valueTo': '#value1', 
													  	'keys': {
													  		id: 'id',
													  		name: 'name'
													  	}, 
													  	'type': 1, 
													  	'data': LAreaData 
													  });
													  area1.value=[1,13,3];
													
													  var area2 = new LArea();
													  area2.init({
													  	'trigger': '#applicationaddress', 
													  	'valueTo': '#value2', 
													  	'keys': {
													  		id: 'id',
													  		name: 'name'
													  	}, 
													  	'type': 1, 
													  	'data': LAreaData 
													  });
													  area2.value=[1,13,3];
													
													  var area3 = new LArea();
													  area3.init({
													  	'trigger': '#insureaddress', 
													  	'valueTo': '#value3', 
													  	'keys': {
													  		id: 'id',
													  		name: 'name'
													  	}, 
													  	'type': 1, 
													  	'data': LAreaData 
													  });
													  area3.value=[1,13,3];
													
													  var area4 = new LArea();
													  area4.init({
													  	'trigger': '#deliveryaddress', 
													  	'valueTo': '#value4', 
													  	'keys': {
													  		id: 'id',
													  		name: 'name'
													  	}, 
													  	'type': 1, 
													  	'data': LAreaData 
													  });
													  area4.value=[1,13,3];
												
													}
							                     });
							                     
	  </script>
     
     </div>
     <p class="con3_p3 con_p8"><span class="con3_span"></span>我已阅读并同意
     <a href="#" class="LudiDCDN_content">《免责说明》</a><a href="#" class="LudiDCInClause_content">《保险条款》</a><a href="#" class="LudiDCCTPLClause">《强保条款》</a>
     <a href="#" class="LudiDCInDetion_content">《投保声明》</a>
     <!-- <font id="shuomingshu">《投保说明》</font></p> -->
     <p class="con_p9">本次保险由慧英保险的合作伙伴华安保险为您提供</p>
     <div class="con7_divb con3_divb">
          <!-- <a class="con7_a1" href="javascript:goback();">上一步  <a class="con7_a2 red" ></a></a> -->
        
          <input type="button" class="con7_a2 red " onclick="submitform();" id="next" value="下一步" >
     </div>
     </form> 
    </div>
  
  
<div style="position:fixed;top:0px;left:0px;width:100%;height:100%;z-index: 100;display:none;"id="pop">
<div style="position: fixed;top:35%; width:100%;height:auto;text-align:center;">
     <span style='display:inline-block;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 10px;letter-spacing: 2px; padding: 20px;'>
        <img  src="<%=path%>/views/quicksure/images/mu_loading.gif" style='width: 36px; margin-bottom: 5px;'>
        <p id="prompt"></p>
	</span>
	 
</div>
</div>


<div class="beijing">

<div class="con4_tang9">
<p class='nation_title'>负责说明</p>
<span class="close"></span>
 <div class="m-wrapper" id="wrapper">
           <div class="add_content">

           </div>
           
        </div>
</div>
<script type="text/javascript">
    var wr = new Optiscroll(document.getElementById('wrapper'), { forceScrollbars: true });
</script>
</div>
</div>
<div class="error_Box" style="display:none">
	    <div class="middle1">
	     <p  style="color:red;font-size: 1rem;color: red;text-align: center;margin-bottom: 5%;margin-top:5px;">温馨提示</p >
	       <div class="m-wrapper " id="m-wrapper">
	       
			  <div class="message" id="Message" > </div>
	        </div>
	        <button  class='close_btn btn' >关闭</button>
		    
	    </div>
	    
	</div>
	<div class="errorhei2" id="errorService" style="display:none">
	<div class="errortan">
		<p class="errortan1">信息提示<a class="errortan3" href="javascript:void(0);"><img src="<%=path%>/views/quicksure/images/Clear.png"/></a></p>
		<p class="errortan2"><div id="chackMessage"></div></p>
		
	</div>
    </div>  
	<div class="errorhei" style="display:none">
	
	
    <div style="   position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
		<div style="width:85%;margin:0 auto;max-width:300px;background:#fff;color: #333; border-radius:5px;padding: 20px 0px 3px 0px;box-shadow: 0 0 20px 2px #333;">
					<h2 style='font-size: 17px;color: #222;'>提示信息</h2>
					<p style='margin-bottom: 10px;font-size: 12px;padding: 15px 20px;text-align: center; color:#444;'id="CheckMessage"></p>
					<div style='border-top:1px solid #ddd;font-size:17px;color:#333;padding: 8px 0px;'><span style='display:block;width:100%;font-size:15px' id="ensure">确定</span></div>
	    </div>
	</div>
</div>
<div id="LudiDCInClause_content" style="display:none;">
    <div style="line-height: 1.5; ">
    <div style="height:auto; font-size:0.7rem; line-height:18px; color:#333; background-color: #fff; text-align: center; padding-right: 10px;">机动车综合商业保险示范条款（2014版-广西、山东、重庆、陕西、青岛、黑龙江、广东、湖南、四川、天津、安徽、湖北、河南、吉林、内蒙古、青海、宁夏、新疆、福建、浙江、大连、云南、辽宁、江西、山西、宁波、河北、贵州）</div>


    <div style="height:auto; font-size: 0.8rem; line-height: 50px; color:#333; background-color: #fff; text-align: center; padding-right: 10px;"><a href="http://10.1.109.95:8380/shop/product/jdc/20151127/53966.html" target="_blank" style="font-size: 14px;color: #e82418;"> 机动车综合商业保险免责事项说明书</a></div>

    <div class="content-title">

    </div>

    <p style="text-align: center;"><strong>总则</strong></p>
    <p>第一条 本保险条款分为主险、附加险。<br>
        主险包括机动车损失保险、机动车第三者责任保险、机动车车上人员责任保险、机动车全车盗抢保险共四个独立的险种，投保人可以选择投保全部险种，也可以选择投保其中部分险种。保险人依照本保险合同的约定，按照承保险种分别承担保险责任。<br>
        附加险不能独立投保。附加险条款与主险条款相抵触之处，以附加险条款为准，附加险条款未尽之处，以主险条款为准。</p>
    <p>&nbsp;</p>
    <p>第二条 本保险合同中的被保险机动车是指在中华人民共和国境内（不含港、澳、台地区）行驶，以动力装置驱动或者牵引，上道路行驶的供人员乘用或者用于运送物品以及进行专项作业的轮式车辆（含挂车）、履带式车辆和其他运载工具，但不包括摩托车、拖拉机、特种车。</p>
    <p>&nbsp;</p>
    <p>第三条 本保险合同中的第三者是指因被保险机动车发生意外事故遭受人身伤亡或者财产损失的人，但不包括被保险机动车本车车上人员、被保险人。</p>
    <p>&nbsp;</p>
    <p>第四条 本保险合同中的车上人员是指发生意外事故的瞬间，在被保险机动车车体内或车体上的人员，包括正在上下车的人员。</p>
    <p>&nbsp;</p>
    <p>第五条 本保险合同中的各方权利和义务，由保险人、投保人遵循公平原则协商确定。保险人、投保人自愿订立本保险合同。<br>
        除本保险合同另有约定外，投保人应在保险合同成立时一次交清保险费。保险费未交清前，本保险合同不生效。</p>
    <p>&nbsp;</p>
    <p id="030101" style="text-align: center;"><strong>第一章 机动车损失保险</strong></p>
    <p style="text-align: center;"><strong>保险责任</strong></p>
    <p>第六条 保险期间内，被保险人或其允许的驾驶人在使用被保险机动车过程中，因下列原因造成被保险机动车的直接损失，且不属于免除保险人责任的范围，保险人依照本保险合同的约定负责赔偿：<br>
        （一） 碰撞、倾覆、坠落；<br>
        （二） 火灾、爆炸；<br>
        （三） 外界物体坠落、倒塌；<br>
        （四） 雷击、暴风、暴雨、洪水、龙卷风、冰雹、台风、热带风暴；<br>
        （五） 地陷、崖崩、滑坡、泥石流、雪崩、冰陷、暴雪、冰凌、沙尘暴；<br>
        （六） 受到被保险机动车所载货物、车上人员意外撞击；<br>
        （七） 载运被保险机动车的渡船遭受自然灾害（只限于驾驶人随船的情形）。</p>
    <p>&nbsp;</p>
    <p>第七条 发生保险事故时，被保险人或其允许的驾驶人为防止或者减少被保险机动车的损失所支付的必要的、合理的施救费用，由保险人承担；施救费用数额在被保险机动车损失赔偿金额以外另行计算，最高不超过保险金额的数额。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><span style="color:#e82418;"><strong>责任免除</strong></span></p>
    <p><span style="color:#e82418;">第八条 在上述保险责任范围内，下列情况下，不论任何原因造成被保险机动车的任何损失和费用，保险人均不负责赔偿：<br>
（一）事故发生后，被保险人或其允许的驾驶人故意破坏、伪造现场、毁灭证据；<br>
（二）驾驶人有下列情形之一者：<br>
1、事故发生后，在未依法采取措施的情况下驾驶被保险机动车或者遗弃被保险机动车离开事故现场；<br>
2、饮酒、吸食或注射毒品、服用国家管制的精神药品或者麻醉药品；<br>
3、无驾驶证，驾驶证被依法扣留、暂扣、吊销、注销期间；<br>
4、驾驶与驾驶证载明的准驾车型不相符合的机动车；<br>
5、实习期内驾驶公共汽车、营运客车或者执行任务的警车、载有危险物品的机动车或牵引挂车的机动车；<br>
6、驾驶出租机动车或营业性机动车无交通运输管理部门核发的许可证书或其他必备证书；<br>
7、学习驾驶时无合法教练员随车指导；<br>
8、非被保险人允许的驾驶人；<br>
（三）被保险机动车有下列情形之一者：<br>
１、发生保险事故时被保险机动车行驶证、号牌被注销的，或未按规定检验或检验不合格；<br>
2、被扣押、收缴、没收、政府征用期间；<br>
3、在竞赛、测试期间，在营业性场所维修、保养、改装期间；<br>
4、被保险人或其允许的驾驶人故意或重大过失，导致被保险机动车被利用从事犯罪行为。</span></p>
    <p><span style="color:#e82418;">&nbsp;</span></p>
    <p><span style="color:#e82418;">第九条 下列原因导致的被保险机动车的损失和费用，保险人不负责赔偿：<br>
（一）地震及其次生灾害；<br>
（二）战争、军事冲突、恐怖活动、暴乱、污染（含放射性污染）、核反应、核辐射；<br>
（三）人工直接供油、高温烘烤、自燃、不明原因火灾；<br>
（四）违反安全装载规定；<br>
（五）被保险机动车被转让、改装、加装或改变使用性质等，被保险人、受让人未及时通知保险人，且因转让、改装、加装或改变使用性质等导致被保险机动车危险程度显著增加；<br>
（六）被保险人或其允许的驾驶人的故意行为。</span></p>
    <p><span style="color: #e82418;">&nbsp;</span></p>
    <p><span style="color: #e82418;">第十条 下列损失和费用，保险人不负责赔偿：<br>
（一）因市场价格变动造成的贬值、修理后因价值降低引起的减值损失；<br>
（二）自然磨损、朽蚀、腐蚀、故障、本身质量缺陷；<br>
（三）遭受保险责任范围内的损失后，未经必要修理并检验合格继续使用，致使损失扩大的部分；<br>
（四）投保人、被保险人或其允许的驾驶人知道保险事故发生后，故意或者因重大过失未及时通知，致使保险事故的性质、原因、损失程度等难以确定的，保险人对无法确定的部分，不承担赔偿责任，但保险人通过其他途径已经及时知道或者应当及时知道保险事故发生的除外；<br>
（五）因被保险人违反本条款第十六条约定，导致无法确定的损失；<br>
（六）被保险机动车全车被盗窃、被抢劫、被抢夺、下落不明，以及在此期间受到的损坏，或被盗窃、被抢劫、被抢夺未遂受到的损坏，或车上零部件、附属设备丢失；<br>
（七）车轮单独损坏，玻璃单独破碎，无明显碰撞痕迹的车身划痕，以及新增设备的损失；<br>
（八）发动机进水后导致的发动机损坏。</span></p>
    <p><span style="color:#e82418;">&nbsp;</span></p>
    <p style="text-align: center;">&nbsp;</p>
    <p><span style="color: rgb(0, 0, 0);"> </span></p>
    <p style="text-align: center;"><strong>免赔率与免赔额</strong></p>
    <p>&nbsp;</p>
    <p><span>第十一条 保险人在依据本保险合同约定计算赔款的基础上，按照下列方式免赔：<br>
（一）被保险机动车一方负次要事故责任的，实行5%的事故责任免赔率；负同等事故责任的，实行10%的事故责任免赔率；负主要事故责任的，实行15%的事故责任免赔率；负全部事故责任或单方肇事事故的，实行20%的事故责任免赔率；<br>
（二）被保险机动车的损失应当由第三方负责赔偿，无法找到第三方的，实行30%的绝对免赔率；<br>
（三）违反安全装载规定、但不是事故发生的直接原因的，增加10%的绝对免赔率；<br>
（四）对于投保人与保险人在投保时协商确定绝对免赔额的，本保险在实行免赔率的基础上增加每次事故绝对免赔额。</span></p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><strong>保险金额</strong></p>
    <p>第十二条  保险金额按投保时被保险机动车的实际价值确定。<br>
        投保时被保险机动车的实际价值由投保人与保险人根据投保时的新车购置价减去折旧金额后的价格协商确定或其他市场公允价值协商确定。<br>
        折旧金额可根据本保险合同列明的参考折旧系数表确定。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><strong>赔偿处理</strong></p>
    <p>第十三条 发生保险事故时，被保险人或其允许的驾驶人应当及时采取合理的、必要的施救和保护措施，防止或者减少损失，并在保险事故发生后48小时内通知保险人。被保险人或其允许的驾驶人根据有关法律法规规定选择自行协商方式处理交通事故的，应当立即通知保险人。</p>
    <p>&nbsp;</p>
    <p>第十四条 被保险人或其允许的驾驶人根据有关法律法规规定选择自行协商方式处理交通事故的，应当协助保险人勘验事故各方车辆、核实事故责任，并依照《道路交通事故处理程序规定》签订记录交通事故情况的协议书。</p>
    <p>&nbsp;</p>
    <p>第十五条 被保险人索赔时，应当向保险人提供与确认保险事故的性质、原因、损失程度等有关的证明和资料。<br>
        被保险人应当提供保险单、损失清单、有关费用单据、被保险机动车行驶证和发生事故时驾驶人的驾驶证。<br>
        属于道路交通事故的，被保险人应当提供公安机关交通管理部门或法院等机构出具的事故证明、有关的法律文书（判决书、调解书、裁定书、裁决书等）及其他证明。被保险人或其允许的驾驶人根据有关法律法规规定选择自行协商方式处理交通事故的，被保险人应当提供依照《道路交通事故处理程序规定》签订记录交通事故情况的协议书。</p>
    <p>&nbsp;</p>
    <p>第十六条 因保险事故损坏的被保险机动车，应当尽量修复。修理前被保险人应当会同保险人检验，协商确定修理项目、方式和费用。对未协商确定的，保险人可以重新核定。</p>
    <p>&nbsp;</p>
    <p>第十七条 被保险机动车遭受损失后的残余部分由保险人、被保险人协商处理。如折归被保险人的，由双方协商确定其价值并在赔款中扣除。</p>
    <p>&nbsp;</p>
    <p>第十八条 因第三方对被保险机动车的损害而造成保险事故，被保险人向第三方索赔的，保险人应积极协助；被保险人也可以直接向本保险人索赔，保险人在保险金额内先行赔付被保险人，并在赔偿金额内代位行使被保险人对第三方请求赔偿的权利。<br>
        被保险人已经从第三方取得损害赔偿的，保险人进行赔偿时，相应扣减被保险人从第三方已取得的赔偿金额。<br>
        保险人未赔偿之前，被保险人放弃对第三方请求赔偿的权利的，保险人不承担赔偿责任。<br>
        被保险人故意或者因重大过失致使保险人不能行使代位请求赔偿的权利的，保险人可以扣减或者要求返还相应的赔款。<br>
        保险人向被保险人先行赔付的，保险人向第三方行使代位请求赔偿的权利时，被保险人应当向保险人提供必要的文件和所知道的有关情况。</p>
    <p>&nbsp;</p>
    <p>第十九条 机动车损失赔款按以下方法计算：<br>
        （一）全部损失<br>
        赔款＝（保险金额－被保险人已从第三方获得的赔偿金额 ）×（1－事故责任免赔率）×（1－绝对免赔率之和）－绝对免赔额<br>
        （二）部分损失<br>
        被保险机动车发生部分损失，保险人按实际修复费用在保险金额内计算赔偿：<br>
        赔款＝（实际修复费用－被保险人已从第三方获得的赔偿金额）×（1－事故责任免赔率）×（1－绝对免赔率之和）－绝对免赔额<br>
        （三）施救费<br>
        施救的财产中，含有本保险合同未保险的财产，应按本保险合同保险财产的实际价值占总施救财产的实际价值比例分摊施救费用。</p>
    <p>&nbsp;</p>
    <p>第二十条 保险人受理报案、现场查勘、核定损失、参与诉讼、进行抗辩、要求被保险人提供证明和资料、向被保险人提供专业建议等行为，均不构成保险人对赔偿责任的承诺。</p>
    <p>&nbsp;</p>
    <p>第二十一条 被保险机动车发生本保险事故，导致全部损失，或一次赔款金额与免赔金额之和（不含施救费）达到保险金额，保险人按本保险合同约定支付赔款后，本保险责任终止，保险人不退还机动车损失保险及其附加险的保险费。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;" id="030102"><strong>第二章 机动车第三者责任保险</strong></p>
    <p style="text-align: center;"><strong>保险责任</strong></p>
    <p>第二十二条 保险期间内，被保险人或其允许的驾驶人在使用被保险机动车过程中发生意外事故，致使第三者遭受人身伤亡或财产直接损毁，依法应当对第三者承担的损害赔偿责任，且不属于免除保险人责任的范围，保险人依照本保险合同的约定，对于超过机动车交通事故责任强制保险各分项赔偿限额的部分负责赔偿。</p>
    <p>&nbsp;</p>
    <p>第二十三条 保险人依据被保险机动车一方在事故中所负的事故责任比例，承担相应的赔偿责任。<br>
        被保险人或被保险机动车一方根据有关法律法规规定选择自行协商或由公安机关交通管理部门处理事故未确定事故责任比例的，按照下列规定确定事故责任比例：<br>
        被保险机动车一方负主要事故责任的，事故责任比例为70%；<br>
        被保险机动车一方负同等事故责任的，事故责任比例为50%；<br>
        被保险机动车一方负次要事故责任的，事故责任比例为30%。<br>
        涉及司法或仲裁程序的，以法院或仲裁机构最终生效的法律文书为准。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><span style="color: #e82418;"><strong>责任免除</strong></span></p>
    <p><span style="color: #e82418;">第二十四条 在上述保险责任范围内，下列情况下，不论任何原因造成的人身伤亡、财产损失和费用，保险人均不负责赔偿：<br>
（一）事故发生后，被保险人或其允许的驾驶人故意破坏、伪造现场、毁灭证据；<br>
（二）驾驶人有下列情形之一者：<br>
1、事故发生后，在未依法采取措施的情况下驾驶被保险机动车或者遗弃被保险机动车离开事故现场；<br>
2、饮酒、吸食或注射毒品、服用国家管制的精神药品或者麻醉药品；<br>
3、无驾驶证，驾驶证被依法扣留、暂扣、吊销、注销期间；<br>
4、驾驶与驾驶证载明的准驾车型不相符合的机动车；<br>
5、实习期内驾驶公共汽车、营运客车或者执行任务的警车、载有危险物品的机动车或牵引挂车的机动车；<br>
6、驾驶出租机动车或营业性机动车无交通运输管理部门核发的许可证书或其他必备证书；<br>
7、学习驾驶时无合法教练员随车指导；<br>
8、非被保险人允许的驾驶人；<br>
（三）被保险机动车有下列情形之一者：<br>
１、发生保险事故时被保险机动车行驶证、号牌被注销的，或未按规定检验或检验不合格；<br>
2、被扣押、收缴、没收、政府征用期间；<br>
3、在竞赛、测试期间，在营业性场所维修、保养、改装期间；<br>
4、全车被盗窃、被抢劫、被抢夺、下落不明期间。</span></p>
    <p><span style="color:#e82418;">&nbsp;</span></p>
    <p><span style="color:#e82418;">第二十五条 下列原因导致的人身伤亡、财产损失和费用，保险人不负责赔偿：<br>
（一）地震及其次生灾害、战争、军事冲突、恐怖活动、暴乱、污染（含放射性污染）、核反应、核辐射；<br>
（二）第三者、被保险人或其允许的驾驶人的故意行为、犯罪行为，第三者与被保险人或其他致害人恶意串通的行为；<br>
（三）被保险机动车被转让、改装、加装或改变使用性质等，被保险人、受让人未及时通知保险人，且因转让、改装、加装或改变使用性质等导致被保险机动车危险程度显著增加。</span></p>
    <p><span style="color:#e82418;">&nbsp;</span></p>
    <p><span style="color:#e82418;">第二十六条 下列人身伤亡、财产损失和费用，保险人不负责赔偿：<br>
（一）被保险机动车发生意外事故，致使任何单位或个人停业、停驶、停电、停水、停气、停产、通讯或网络中断、电压变化、数据丢失造成的损失以及其他各种间接损失；<br>
（二）第三者财产因市场价格变动造成的贬值，修理后因价值降低引起的减值损失；<br>
（三）被保险人及其家庭成员、被保险人允许的驾驶人及其家庭成员所有、承租、使用、管理、运输或代管的财产的损失，以及本车上财产的损失；<br>
（四）被保险人、被保险人允许的驾驶人、本车车上人员的人身伤亡；<br>
（五）停车费、保管费、扣车费、罚款、罚金或惩罚性赔款；<br>
（六）超出《道路交通事故受伤人员临床诊疗指南》和国家基本医疗保险同类医疗费用标准的费用部分；<br>
（七）律师费，未经保险人事先书面同意的诉讼费、仲裁费；<br>
（八）投保人、被保险人或其允许的驾驶人知道保险事故发生后，故意或者因重大过失未及时通知，致使保险事故的性质、原因、损失程度等难以确定的，保险人对无法确定的部分，不承担赔偿责任，但保险人通过其他途径已经及时知道或者应当及时知道保险事故发生的除外；<br>
（九）因被保险人违反本条款第三十四条约定，导致无法确定的损失；<br>
（十）精神损害抚慰金；<br>
（十一）应当由机动车交通事故责任强制保险赔偿的损失和费用；<br>
保险事故发生时，被保险机动车未投保机动车交通事故责任强制保险或机动车交通事故责任强制保险合同已经失效的，对于机动车交通事故责任强制保险责任限额以内的损失和费用，保险人不负责赔偿。</span></p>
    <p><span style="color: #e82418;">&nbsp;</span></p>
    <p style="text-align: center;"><strong>免赔率</strong></p>
    <p>第二十七条 保险人在依据本保险合同约定计算赔款的基础上，在保险单载明的责任限额内，按照下列方式免赔：<br>
        （一）被保险机动车一方负次要事故责任的，实行5%的事故责任免赔率；负同等事故责任的，实行10%的事故责任免赔率；负主要事故责任的，实行15%的事故责任免赔率；负全部事故责任的，实行20%的事故责任免赔率；<br>
        （二） 违反安全装载规定的，实行10%的绝对免赔率。</p>
    <p style="text-align: center;"><strong>&nbsp;</strong></p>
    <p style="text-align: center;"><strong>责任限额</strong></p>
    <p>第二十八条 每次事故的责任限额，由投保人和保险人在签订本保险合同时协商确定。</p>
    <p>&nbsp;</p>
    <p>第二十九条 主车和挂车连接使用时视为一体，发生保险事故时，由主车保险人和挂车保险人按照保险单上载明的机动车第三者责任保险责任限额的比例，在各自的责任限额内承担赔偿责任，但赔偿金额总和以主车的责任限额为限。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><strong>赔偿处理</strong></p>
    <p>第三十条 发生保险事故时，被保险人或其允许的驾驶人应当及时采取合理的、必要的施救和保护措施，防止或者减少损失，并在保险事故发生后48小时内通知保险人。被保险人或其允许的驾驶人根据有关法律法规规定选择自行协商方式处理交通事故的，应当立即通知保险人。</p>
    <p>&nbsp;</p>
    <p>第三十一条 被保险人或其允许的驾驶人根据有关法律法规规定选择自行协商方式处理交通事故的，应当协助保险人勘验事故各方车辆、核实事故责任，并依照《道路交通事故处理程序规定》签订记录交通事故情况的协议书。</p>
    <p>&nbsp;</p>
    <p>第三十二条 被保险人索赔时，应当向保险人提供与确认保险事故的性质、原因、损失程度等有关的证明和资料。<br>
        被保险人应当提供保险单、损失清单、有关费用单据、被保险机动车行驶证和发生事故时驾驶人的驾驶证。<br>
        属于道路交通事故的，被保险人应当提供公安机关交通管理部门或法院等机构出具的事故证明、有关的法律文书（判决书、调解书、裁定书、裁决书等）及其他证明。被保险人或其允许的驾驶人根据有关法律法规规定选择自行协商方式处理交通事故的，被保险人应当提供依照《道路交通事故处理程序规定》签订记录交通事故情况的协议书。</p>
    <p>&nbsp;</p>
    <p>第三十三条  保险人对被保险人给第三者造成的损害，可以直接向该第三者赔偿。<br>
        被保险人给第三者造成损害，被保险人对第三者应负的赔偿责任确定的，根据被保险人的请求，保险人应当直接向该第三者赔偿。被保险人怠于请求的，第三者有权就其应获赔偿部分直接向保险人请求赔偿。<br>
        被保险人给第三者造成损害，被保险人未向该第三者赔偿的，保险人不得向被保险人赔偿。</p>
    <p>&nbsp;</p>
    <p>第三十四条 因保险事故损坏的第三者财产，应当尽量修复。修理前被保险人应当会同保险人检验，协商确定修理项目、方式和费用。对未协商确定的，保险人可以重新核定。</p>
    <p>&nbsp;</p>
    <p>第三十五条 赔款计算<br>
        1、当（依合同约定核定的第三者损失金额－机动车交通事故责任强制保险的分项赔偿限额）×事故责任比例 等于或高于每次事故赔偿限额时：<br>
        赔款=每次事故赔偿限额×（1－事故责任免赔率）×（1－绝对免赔率之和）<br>
        2、当（依合同约定核定的第三者损失金额－机动车交通事故责任强制保险的分项赔偿限额）×事故责任比例低于每次事故赔偿限额时：<br>
        赔款＝（依合同约定核定的第三者损失金额－机动车交通事故责任强制保险的分项赔偿限额）×事故责任比例×（1－事故责任免赔率）×（1－绝对免赔率之和）</p>
    <p>&nbsp;</p>
    <p>第三十六条 保险人按照《道路交通事故受伤人员临床诊疗指南》和国家基本医疗保险的同类医疗费用标准核定医疗费用的赔偿金额。<br>
        未经保险人书面同意，被保险人自行承诺或支付的赔偿金额，保险人有权重新核定。不属于保险人赔偿范围或超出保险人应赔偿金额的，保险人不承担赔偿责任。</p>
    <p>&nbsp;</p>
    <p>第三十七条 保险人受理报案、现场查勘、核定损失、参与诉讼、进行抗辩、要求被保险人提供证明和资料、向被保险人提供专业建议等行为，均不构成保险人对赔偿责任的承诺。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;" id="030104"><strong>第三章 机动车车上人员责任保险</strong></p>
    <p style="text-align: center;"><strong>保险责任</strong></p>
    <p>第三十八条 保险期间内，被保险人或其允许的驾驶人在使用被保险机动车过程中发生意外事故，致使车上人员遭受人身伤亡，且不属于免除保险人责任的范围，依法应当对车上人员承担的损害赔偿责任，保险人依照本保险合同的约定负责赔偿。</p>
    <p>&nbsp;</p>
    <p>第三十九条 保险人依据被保险机动车一方在事故中所负的事故责任比例，承担相应的赔偿责任。<br>
        被保险人或被保险机动车一方根据有关法律法规规定选择自行协商或由公安机关交通管理部门处理事故未确定事故责任比例的，按照下列规定确定事故责任比例：<br>
        被保险机动车一方负主要事故责任的，事故责任比例为70%；<br>
        被保险机动车一方负同等事故责任的，事故责任比例为50%；<br>
        被保险机动车一方负次要事故责任的，事故责任比例为30%。<br>
        涉及司法或仲裁程序的，以法院或仲裁机构最终生效的法律文书为准。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><span style="color:#e82418;">责任免除</span></p>
    <p><span style="color:#e82418;">第四十条 在上述保险责任范围内，下列情况下，不论任何原因造成的人身伤亡，保险人均不负责赔偿：<br>
（一）事故发生后，被保险人或其允许的驾驶人故意破坏、伪造现场、毁灭证据；<br>
（二）驾驶人有下列情形之一者：<br>
1、事故发生后，在未依法采取措施的情况下驾驶被保险机动车或者遗弃被保险机动车离开事故现场；<br>
2、饮酒、吸食或注射毒品、服用国家管制的精神药品或者麻醉药品；<br>
3、无驾驶证，驾驶证被依法扣留、暂扣、吊销、注销期间；<br>
4、驾驶与驾驶证载明的准驾车型不相符合的机动车；<br>
5、实习期内驾驶公共汽车、营运客车或者执行任务的警车、载有危险物品的机动车或牵引挂车的机动车；<br>
6、驾驶出租机动车或营业性机动车无交通运输管理部门核发的许可证书或其他必备证书；<br>
7、学习驾驶时无合法教练员随车指导；<br>
8、非被保险人允许的驾驶人；<br>
（三）被保险机动车有下列情形之一者：<br>
１、发生保险事故时被保险机动车行驶证、号牌被注销的，或未按规定检验或检验不合格；<br>
2、被扣押、收缴、没收、政府征用期间；<br>
3、在竞赛、测试期间，在营业性场所维修、保养、改装期间；<br>
4、全车被盗窃、被抢劫、被抢夺、下落不明期间。</span></p>
    <p><span style="color:#e82418;">&nbsp;</span></p>
    <p><span style="color:#e82418;">第四十一条 下列原因导致的人身伤亡，保险人不负责赔偿：<br>
（一）地震及其次生灾害、战争、军事冲突、恐怖活动、暴乱、污染（含放射性污染）、核反应、核辐射；<br>
（二）被保险机动车被转让、改装、加装或改变使用性质等，被保险人、受让人未及时通知保险人，且因转让、改装、加装或改变使用性质等导致被保险机动车危险程度显著增加；<br>
（三）被保险人或驾驶人的故意行为。</span></p>
    <p><span style="color:#e82418;">&nbsp;</span></p>
    <p><span style="color:#e82418;">第四十二条 下列人身伤亡、损失和费用，保险人不负责赔偿：<br>
（一）被保险人及驾驶人以外的其他车上人员的故意行为造成的自身伤亡；<br>
（二）车上人员因疾病、分娩、自残、斗殴、自杀、犯罪行为造成的自身伤亡；<br>
（三）违法、违章搭乘人员的人身伤亡；<br>
（四）罚款、罚金或惩罚性赔款；<br>
（五）超出《道路交通事故受伤人员临床诊疗指南》和国家基本医疗保险同类医疗费用标准的费用部分；<br>
（六）律师费，未经保险人事先书面同意的诉讼费、仲裁费；<br>
（七）投保人、被保险人或其允许的驾驶人知道保险事故发生后，故意或者因重大过失未及时通知，致使保险事故的性质、原因、损失程度等难以确定的，保险人对无法确定的部分，不承担赔偿责任，但保险人通过其他途径已经及时知道或者应当及时知道保险事故发生的除外；<br>
（八）精神损害抚慰金；<br>
（九）应当由机动车交通事故责任强制保险赔付的损失和费用。</span></p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><strong>免赔率</strong></p>
    <p>第四十三条 保险人在依据本保险合同约定计算赔款的基础上，在保险单载明的责任限额内，按照下列方式免赔：<br>
        被保险机动车一方负次要事故责任的，实行5%的事故责任免赔率；负同等事故责任的，实行10%的事故责任免赔率；负主要事故责任的，实行15%的事故责任免赔率；负全部事故责任或单方肇事事故的，实行20%的事故责任免赔率。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><strong>责任限额</strong></p>
    <p>第四十四条 驾驶人每次事故责任限额和乘客每次事故每人责任限额由投保人和保险人在投保时协商确定。投保乘客座位数按照被保险机动车的核定载客数（驾驶人座位除外）确定。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><strong>赔偿处理<br>
    </strong></p>
    <p>第四十五条 发生保险事故时，被保险人或其允许的驾驶人应当及时采取合理的、必要的施救和保护措施，防止或者减少损失，并在保险事故发生后48小时内通知保险人。被保险人或其允许的驾驶人根据有关法律法规规定选择自行协商方式处理交通事故的，应当立即通知保险人。</p>
    <p>&nbsp;</p>
    <p>第四十六条 被保险人或其允许的驾驶人根据有关法律法规规定选择自行协商方式处理交通事故的，应当协助保险人勘验事故各方车辆、核实事故责任，并依照《道路交通事故处理程序规定》签订记录交通事故情况的协议书。</p>
    <p>&nbsp;</p>
    <p>第四十七条 被保险人索赔时，应当向保险人提供与确认保险事故的性质、原因、损失程度等有关的证明和资料。<br>
        被保险人应当提供保险单、损失清单、有关费用单据、被保险机动车行驶证和发生事故时驾驶人的驾驶证。<br>
        属于道路交通事故的，被保险人应当提供公安机关交通管理部门或法院等机构出具的事故证明、有关的法律文书（判决书、调解书、裁定书、裁决书等）和通过机动车交通事故责任强制保险获得赔偿金额的证明材料。被保险人或其允许的驾驶人根据有关法律法规规定选择自行协商方式处理交通事故的，被保险人应当提供依照《道路交通事故处理程序规定》签订记录交通事故情况的协议书和通过机动车交通事故责任强制保险获得赔偿金额的证明材料。</p>
    <p>&nbsp;</p>
    <p>第四十八条  赔款计算<br>
        （一）对每座的受害人，当（依合同约定核定的每座车上人员人身伤亡损失金额－应由机动车交通事故责任强制保险赔偿的金额）×事故责任比例高于或等于每次事故每座赔偿限额时：<br>
        赔款=每次事故每座赔偿限额×（1－事故责任免赔率）<br>
        （二）对每座的受害人，当（依合同约定核定的每座车上人员人身伤亡损失金额－应由机动车交通事故责任强制保险赔偿的金额）×事故责任比例低于每次事故每座赔偿限额时：<br>
        赔款=（依合同约定核定的每座车上人员人身伤亡损失金额－应由机动车交通事故责任强制保险赔偿的金额）×事故责任比例×（1－事故责任免赔率）</p>
    <p>&nbsp;</p>
    <p>第四十九条 保险人按照《道路交通事故受伤人员临床诊疗指南》和国家基本医疗保险的同类医疗费用标准核定医疗费用的赔偿金额。<br>
        未经保险人书面同意，被保险人自行承诺或支付的赔偿金额，保险人有权重新核定。因被保险人原因导致损失金额无法确定的，保险人有权拒绝赔偿。</p>
    <p>&nbsp;</p>
    <p>第五十条 保险人受理报案、现场查勘、核定损失、参与诉讼、进行抗辩、要求被保险人提供证明和资料、向被保险人提供专业建议等行为，均不构成保险人对赔偿责任的承诺。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;" id="030103"><strong>第四章 机动车全车盗抢保险</strong></p>
    <p style="text-align: center;"><strong>保险责任</strong></p>
    <p>第五十一条 保险期间内，被保险机动车的下列损失和费用，且不属于免除保险人责任的范围，保险人依照本保险合同的约定负责赔偿：<br>
        （一） 被保险机动车被盗窃、抢劫、抢夺，经出险当地县级以上公安刑侦部门立案证明，满60天未查明下落的全车损失；<br>
        （二） 被保险机动车全车被盗窃、抢劫、抢夺后，受到损坏或车上零部件、附属设备丢失需要修复的合理费用；<br>
        （三） 被保险机动车在被抢劫、抢夺过程中，受到损坏需要修复的合理费用。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><span style="color:#e82418;"><strong>责任免除</strong></span></p>
    <p><span style="color:#e82418;">第五十二条  在上述保险责任范围内，下列情况下，不论任何原因造成被保险机动车的任何损失和费用，保险人均不负责赔偿：<br>
（一）被保险人索赔时未能提供出险当地县级以上公安刑侦部门出具的盗抢立案证明；<br>
（二）驾驶人、被保险人、投保人故意破坏现场、伪造现场、毁灭证据；<br>
（三）被保险机动车被扣押、罚没、查封、政府征用期间；<br>
（四）被保险机动车在竞赛、测试期间，在营业性场所维修、保养、改装期间，被运输期间。</span></p>
    <p><span style="color: #e82418;">&nbsp;</span></p>
    <p><span style="color:#e82418;">第五十三条 下列损失和费用，保险人不负责赔偿：<br>
（一）地震及其次生灾害导致的损失和费用；<br>
（二）战争、军事冲突、恐怖活动、暴乱导致的损失和费用；<br>
（三）因诈骗引起的任何损失；因投保人、被保险人与他人的民事、经济纠纷导致的任何损失；<br>
（四）被保险人或其允许的驾驶人的故意行为、犯罪行为导致的损失和费用；<br>
（五）非全车遭盗窃，仅车上零部件或附属设备被盗窃或损坏；<br>
（六）新增设备的损失；<br>
（七）遭受保险责任范围内的损失后，未经必要修理并检验合格继续使用，致使损失扩大的部分；<br>
（八）被保险机动车被转让、改装、加装或改变使用性质等，被保险人、受让人未及时通知保险人，且因转让、改装、加装或改变使用性质等导致被保险机动车危险程度显著增加而发生保险事故；<br>
（九）投保人、被保险人或其允许的驾驶人知道保险事故发生后，故意或者因重大过失未及时通知，致使保险事故的性质、原因、损失程度等难以确定的，保险人对无法确定的部分，不承担赔偿责任，但保险人通过其他途径已经及时知道或者应当及时知道保险事故发生的除外；<br>
（十）因被保险人违反本条款第五十八条约定，导致无法确定的损失。</span></p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><strong>免赔率</strong></p>
    <p>第五十四条 保险人在依据本保险合同约定计算赔款的基础上，按照下列方式免赔：<br>
        （一） 发生全车损失的，绝对免赔率为20%；<br>
        （二） 发生全车损失，被保险人未能提供《机动车登记证书》、机动车来历凭证的，每缺少一项，增加1%的绝对免赔率。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><strong>保险金额</strong></p>
    <p>第五十五条  保险金额在投保时被保险机动车的实际价值内协商确定。<br>
        投保时被保险机动车的实际价值由投保人与保险人根据投保时的新车购置价减去折旧金额后的价格协商确定或其他市场公允价值协商确定。<br>
        折旧金额可根据本保险合同列明的参考折旧系数表确定。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><strong>赔偿处理</strong></p>
    <p>第五十六条 被保险机动车全车被盗抢的，被保险人知道保险事故发生后，应在24小时内向出险当地公安刑侦部门报案，并通知保险人。</p>
    <p>&nbsp;</p>
    <p>第五十七条 被保险人索赔时，须提供保险单、损失清单、有关费用单据、《机动车登记证书》、机动车来历凭证以及出险当地县级以上公安刑侦部门出具的盗抢立案证明。</p>
    <p>&nbsp;</p>
    <p>第五十八条 因保险事故损坏的被保险机动车，应当尽量修复。修理前被保险人应当会同保险人检验，协商确定修理项目、方式和费用。对未协商确定的，保险人可以重新核定。</p>
    <p>&nbsp;</p>
    <p>第五十九条 保险人按下列方式赔偿：<br>
        （一）被保险机动车全车被盗抢的，按以下方法计算赔款：<br>
        赔款＝保险金额×（1－绝对免赔率之和）<br>
        （二）被保险机动车发生本条款第五十一条第（二）款、第（三）款列明的损失，保险人按实际修复费用在保险金额内计算赔偿。</p>
    <p>&nbsp;</p>
    <p>第六十条 保险人确认索赔单证齐全、有效后，被保险人签具权益转让书，保险人赔付结案。</p>
    <p>&nbsp;</p>
    <p>第六十一条 被保险机动车发生本保险事故，导致全部损失，或一次赔款金额与免赔金额之和达到保险金额，保险人按本保险合同约定支付赔款后，本保险责任终止，保险人不退还机动车全车盗抢保险及其附加险的保险费。</p>
    <p style="text-align: center;"><strong>&nbsp;</strong></p>
    <p style="text-align: center;"><strong>第五章 通用条款<br>
        保险期间</strong></p>
    <p>第六十二条  除另有约定外，保险期间为一年，以保险单载明的起讫时间为准。</p>
    <p>&nbsp;</p>
    <p style="text-align: center;"><strong>其它事项</strong></p>
    <p>第六十三条 保险人按照本保险合同的约定，认为被保险人索赔提供的有关证明和资料不完整的，应当及时一次性通知被保险人补充提供。</p>
    <p>&nbsp;</p>
    <p>第六十四条 保险人收到被保险人的赔偿请求后，应当及时作出核定；情形复杂的，应当在三十日内作出核定。保险人应当将核定结果通知被保险人；对属于保险责任的，在与被保险人达成赔偿协议后十日内，履行赔偿义务。保险合同对赔偿期限另有约定的，保险人应当按照约定履行赔偿义务。<br>
        保险人未及时履行前款约定义务的，除支付赔款外，应当赔偿被保险人因此受到的损失。</p>
    <p>&nbsp;</p>
    <p>第六十五条 保险人依照本条款第六十四条的约定作出核定后，对不属于保险责任的，应当自作出核定之日起三日内向被保险人发出拒绝赔偿通知书，并说明理由。</p>
    <p>&nbsp;</p>
    <p>第六十六条  保险人自收到赔偿请求和有关证明、资料之日起六十日内，对其赔偿数额不能确定的，应当根据已有证明和资料可以确定的数额先予支付；保险人最终确定赔偿数额后，应当支付相应的差额。</p>
    <p>&nbsp;</p>
    <p>第六十七条  在保险期间内，被保险机动车转让他人的，受让人承继被保险人的权利和义务。被保险人或者受让人应当及时通知保险人，并及时办理保险合同变更手续。<br>
        因被保险机动车转让导致被保险机动车危险程度发生显著变化的，保险人自收到前款约定的通知之日起三十日内，可以相应调整保险费或者解除本保险合同。</p>
    <p>&nbsp;</p>
    <p>第六十八条 保险责任开始前，投保人要求解除本保险合同的，应当向保险人支付应交保险费金额3%的退保手续费，保险人应当退还保险费。<br>
        保险责任开始后，投保人要求解除本保险合同的，自通知保险人之日起，本保险合同解除。保险人按日收取自保险责任开始之日起至合同解除之日止期间的保险费，并退还剩余部分保险费。</p>
    <p>&nbsp;</p>
    <p>第六十九条 因履行本保险合同发生的争议，由当事人协商解决，协商不成的，由当事人从下列两种合同争议解决方式中选择一种，并在本保险合同中载明： <br>
        （一）提交保险单载明的仲裁委员会仲裁； <br>
        （二）依法向人民法院起诉。<br>
        本保险合同适用中华人民共和国（不含港、澳、台地区）法律。</p>
    <p>&nbsp;</p>
    <p><strong>&nbsp;&nbsp; 附加险</strong></p>
    <p>附加险条款的法律效力优于主险条款。附加险条款未尽事宜，以主险条款为准。除附加险条款另有约定外，主险中的责任免除、免赔规则、双方义务同样适用于附加险。<br>
        1、玻璃单独破碎险<br>
        2、自燃损失险<br>
        3、新增加设备损失险<br>
        4、车身划痕损失险<br>
        5、发动机涉水损失险<br>
        6、修理期间费用补偿险<br>
        7、车上货物责任险<br>
        8、精神损害抚慰金责任险<br>
        9、不计免赔率险<br>
        10、机动车损失保险无法找到第三方特约险<br>
        11、指定修理厂险</p>
    <p>&nbsp;</p>
    <p id="030107"><strong>玻璃单独破碎险</strong></p>
    <p>投保了机动车损失保险的机动车，可投保本附加险。</p>
    <p>&nbsp;</p>
    <p>第一条 保险责任<br>
        保险期间内，被保险机动车风挡玻璃或车窗玻璃的单独破碎，保险人按实际损失金额赔偿。</p>
    <p>&nbsp;</p>
    <p>第二条 投保方式<br>
        投保人与保险人可协商选择按进口或国产玻璃投保。保险人根据协商选择的投保方式承担相应的赔偿责任。</p>
    <p>&nbsp;</p>
    <p>第三条 责任免除<br>
        安装、维修机动车过程中造成的玻璃单独破碎。</p>
    <p>&nbsp;</p>
    <p>第四条 本附加险不适用主险中的各项免赔率、免赔额约定</p>
    <p>&nbsp;</p>
    <p id="030108"><strong>自燃损失险</strong></p>
    <p>投保了机动车损失保险的机动车，可投保本附加险。</p>
    <p>第一条 保险责任<br>
        （一）保险期间内，指在没有外界火源的情况下，由于本车电器、线路、供油系统、供气系统等被保险机动车自身原因或所载货物自身原因起火燃烧造成本车的损失；<br>
        （二）发生保险事故时，被保险人为防止或者减少被保险机动车的损失所支付的必要的、合理的施救费用，由保险人承担；施救费用数额在被保险机动车损失赔偿金额以外另行计算，最高不超过本附加险保险金额的数额。</p>
    <p>&nbsp;</p>
    <p>第二条 责任免除<br>
        （一）自燃仅造成电器、线路、油路、供油系统、供气系统的损失；<br>
        （二）由于擅自改装、加装电器及设备导致被保险机动车起火造成的损失；<br>
        （三）被保险人在使用被保险机动车过程中，因人工直接供油、高温烘烤等违反车辆安全操作规则造成的损失；<br>
        （四）本附加险每次赔偿实行20%的绝对免赔率，不适用主险中的各项免赔率、免赔额约定。</p>
    <p>&nbsp;</p>
    <p>第三条 保险金额<br>
        保险金额由投保人和保险人在投保时被保险机动车的实际价值内协商确定。</p>
    <p>&nbsp;</p>
    <p>第四条 赔偿处理<br>
        全部损失，在保险金额内计算赔偿；部分损失，在保险金额内按实际修理费用计算赔偿。</p>
    <p>&nbsp;</p>
    <p id="030109"><strong>新增加设备损失险</strong></p>
    <p>投保了机动车损失保险的机动车，可投保本附加险。</p>
    <p>第一条 保险责任 保险期间内，投保了本附加险的被保险机动车因发生机动车损失保险责任范围内的事故，造成车上新增加设备的直接损毁，保险人在保险单载明的本附加险的保险金额内，按照实际损失计算赔偿。</p>
    <p>&nbsp;</p>
    <p>第二条 责任免除<br>
        本附加险每次赔偿的免赔约定以机动车损失保险条款约定为准。</p>
    <p>&nbsp;</p>
    <p>第三条 保险金额<br>
        保险金额根据新增加设备投保时的实际价值确定。新增加设备的实际价值是指新增加设备的购置价减去折旧金额后的金额。</p>
    <p>&nbsp;</p>
    <p id="030110"><strong>车身划痕损失险</strong></p>
    <p>投保了机动车损失保险的机动车，可投保本附加险。</p>
    <p>第一条 保险责任<br>
        保险期间内，投保了本附加险的机动车在被保险人或其允许的驾驶人使用过程中，发生无明显碰撞痕迹的车身划痕损失，保险人按照保险合同约定负责赔偿。</p>
    <p>&nbsp;</p>
    <p>第二条 责任免除<br>
        （一）被保险人及其家庭成员、驾驶人及其家庭成员的故意行为造成的损失；<br>
        （二）因投保人、被保险人与他人的民事、经济纠纷导致的任何损失；<br>
        （三）车身表面自然老化、损坏，腐蚀造成的任何损失；<br>
        （四）本附加险每次赔偿实行15%的绝对免赔率，不适用主险中的各项免赔率、免赔额约定。</p>
    <p>&nbsp;</p>
    <p>第三条 保险金额<br>
        保险金额为2000元、5000元、10000元或20000元，由投保人和保险人在投保时协商确定。</p>
    <p>&nbsp;</p>
    <p>第四条 赔偿处理<br>
        （一）在保险金额内按实际修理费用计算赔偿。<br>
        （二）在保险期间内，累计赔款金额达到保险金额，本附加险保险责任终止。</p>
    <p>&nbsp;</p>
    <p id="030111"><strong>发动机涉水损失险</strong></p>
    <p>本附加险仅适用于家庭自用汽车、党政机关、事业团体用车、企业非营业用车，且只有在投保了机动车损失保险后，方可投保本附加险。</p>
    <p>第一条 保险责任<br>
        保险期间内，投保了本附加险的被保险机动车在使用过程中，因发动机进水后导致的发动机的直接损毁，保险人负责赔偿；<br>
        发生保险事故时，被保险人为防止或者减少被保险机动车的损失所支付的必要的、合理的施救费用，由保险人承担；施救费用数额在被保险机动车损失赔偿金额以外另行计算，最高不超过保险金额的数额。</p>
    <p>&nbsp;</p>
    <p>第二条 责任免除<br>
        本附加险每次赔偿均实行15%的绝对免赔率，不适用主险中的各项免赔率、免赔额约定。</p>
    <p>&nbsp;</p>
    <p>第三条 赔偿处理<br>
        发生保险事故时，保险人在保险金额内计算赔偿。</p>
    <p>&nbsp;</p>
    <p id="030112"><strong>修理期间费用补偿险</strong></p>
    <p>只有在投保了机动车损失保险的基础上方可投保本附加险，机动车损失保险责任终止时，本保险责任同时终止。</p>
    <p>第一条 保险责任<br>
        保险期间内，投保了本条款的机动车在使用过程中，发生机动车损失保险责任范围内的事故，造成车身损毁，致使被保险机动车停驶，保险人按保险合同约定，在保险金额内向被保险人补偿修理期间费用，作为代步车费用或弥补停驶损失。</p>
    <p>&nbsp;</p>
    <p>第二条 责任免除<br>
        下列情况下，保险人不承担修理期间费用补偿：<br>
        （一）因机动车损失保险责任范围以外的事故而致被保险机动车的损毁或修理；<br>
        （二）非在保险人认可的修理厂修理时，因车辆修理质量不合要求造成返修；<br>
        （三）被保险人或驾驶人拖延车辆送修期间；<br>
        （四）本附加险每次事故的绝对免赔额为1天的赔偿金额，不适用主险中的各项免赔率、免赔额约定。</p>
    <p>&nbsp;</p>
    <p>第三条 保险金额<br>
        本附加险保险金额=补偿天数×日补偿金额。补偿天数及日补偿金额由投保人与保险人协商确定并在保险合同中载明，保险期间内约定的补偿天数最高不超过90天。</p>
    <p>&nbsp;</p>
    <p>第四条 赔偿处理<br>
        全车损失，按保险单载明的保险金额计算赔偿；部分损失，在保险金额内按约定的日赔偿金额乘以从送修之日起至修复之日止的实际天数计算赔偿，实际天数超过双方约定修理天数的，以双方约定的修理天数为准。<br>
        保险期间内，累计赔款金额达到保险单载明的保险金额，本附加险保险责任终止。</p>
    <p>&nbsp;</p>
    <p id="030113"><strong>车上货物责任险</strong></p>
    <p>投保了机动车第三者责任保险的机动车，可投保本附加险。</p>
    <p>第一条 保险责任<br>
        保险期间内，发生意外事故致使被保险机动车所载货物遭受直接损毁，依法应由被保险人承担的损害赔偿责任，保险人负责赔偿。</p>
    <p>&nbsp;</p>
    <p>第二条 责任免除<br>
        （一）偷盗、哄抢、自然损耗、本身缺陷、短少、死亡、腐烂、变质、串味、生锈，动物走失、飞失、货物自身起火燃烧或爆炸造成的货物损失；<br>
        （二）违法、违章载运造成的损失；<br>
        （三）因包装、紧固不善，装载、遮盖不当导致的任何损失；<br>
        （四）车上人员携带的私人物品的损失；<br>
        （五）保险事故导致的货物减值、运输延迟、营业损失及其他各种间接损失；<br>
        （六）法律、行政法规禁止运输的货物的损失；<br>
        （七）本附加险每次赔偿实行20%的绝对免赔率，不适用主险中的各项免赔率、免赔额约定。</p>
    <p>&nbsp;</p>
    <p>第三条 责任限额<br>
        责任限额由投保人和保险人在投保时协商确定。</p>
    <p>&nbsp;</p>
    <p>第四条 赔偿处理<br>
        被保险人索赔时，应提供运单、起运地货物价格证明等相关单据。保险人在责任限额内按起运地价格计算赔偿。</p>
    <p>&nbsp;</p>
    <p id="030114"><strong>精神损害抚慰金责任险</strong></p>
    <p>只有在投保了机动车第三者责任保险或机动车车上人员责任保险的基础上方可投保本附加险。<br>
        在投保人仅投保机动车第三者责任保险的基础上附加本附加险时，保险人只负责赔偿第三者的精神损害抚慰金；在投保人仅投保机动车车上人员责任保险的基础上附加本附加险时，保险人只负责赔偿车上人员的精神损害抚慰金。</p>
    <p>&nbsp;</p>
    <p>第一条 保险责任<br>
        保险期间内，被保险人或其允许的驾驶人在使用被保险机动车的过程中，发生投保的主险约定的保险责任内的事故，造成第三者或车上人员的人身伤亡，受害人据此提出精神损害赔偿请求，保险人依据法院判决及保险合同约定，对应由被保险人或被保险机动车驾驶人支付的精神损害抚慰金，在扣除机动车交通事故责任强制保险应当支付的赔款后，在本保险赔偿限额内负责赔偿。</p>
    <p>&nbsp;</p>
    <p>第二条 责任免除<br>
        （一）根据被保险人与他人的合同协议，应由他人承担的精神损害抚慰金；<br>
        （二）未发生交通事故，仅因第三者或本车人员的惊恐而引起的损害；<br>
        （三）怀孕妇女的流产发生在交通事故发生之日起30天以外的；<br>
        （四）本附加险每次赔偿实行20%的绝对免赔率，不适用主险中的各项免赔率、免赔额约定。</p>
    <p>&nbsp;</p>
    <p>第三条 赔偿限额 本保险每次事故赔偿限额由保险人和投保人在投保时协商确定。</p>
    <p>&nbsp;</p>
    <p>第四条 赔偿处理<br>
        本附加险赔偿金额依据人民法院的判决在保险单所载明的赔偿限额内计算赔偿。</p>
    <p>&nbsp;</p>
    <p id="030119"><strong>不计免赔率险</strong></p>
    <p>投保了任一主险及其他设置了免赔率的附加险后，均可投保本附加险。</p>
    <p>&nbsp;</p>
    <p>第一条 保险责任<br>
        保险事故发生后，按照对应投保的险种约定的免赔率计算的、应当由被保险人自行承担的免赔金额部分，保险人负责赔偿。</p>
    <p>&nbsp;</p>
    <p>第二条 责任免除<br>
        下列情况下，应当由被保险人自行承担的免赔金额，保险人不负责赔偿：<br>
        （一）机动车损失保险中应当由第三方负责赔偿而无法找到第三方的；<br>
        （二）因违反安全装载规定而增加的；<br>
        （三）发生机动车全车盗抢保险约定的全车损失保险事故时，被保险人未能提供《机动车登记证书》、机动车来历凭证的，每缺少一项而增加的；<br>
        （四）机动车损失保险中约定的每次事故绝对免赔额；<br>
        （五）可附加本条款但未选择附加本条款的险种约定的；<br>
        （六）不可附加本条款的险种约定的。</p>
    <p>&nbsp;</p>
    <p id="030115&quot;"><strong>机动车损失保险无法找到第三方特约险</strong></p>
    <p>投保了机动车损失保险后，可投保本附加险。<br>
        投保了本附加险后，对于机动车损失保险第十一条第（二）款列明的，被保险机动车损失应当由第三方负责赔偿，但因无法找到第三方而增加的由被保险人自行承担的免赔金额，保险人负责赔偿。</p>
    <p>&nbsp;</p>
    <p id="030116"><strong>指定修理厂险</strong></p>
    <p>投保了机动车损失保险的机动车，可投保本附加险。<br>
        投保了本附加险后，机动车损失保险事故发生后，被保险人可指定修理厂进行修理。</p>
    <p>&nbsp;</p>
    <p><strong>&nbsp;&nbsp;&nbsp;&nbsp; 释义</strong></p>
    <p>【碰撞】指被保险机动车或其符合装载规定的货物与外界固态物体之间发生的、产生撞击痕迹的意外撞击。<br>
        【倾覆】指被保险机动车由于自然灾害或意外事故，造成本被保险机动车翻倒，车体触地，失去正常状态和行驶能力，不经施救不能恢复行驶。<br>
        【坠落】 指被保险机动车在行驶中发生意外事故，整车腾空后下落，造成本车损失的情况。非整车腾空，仅由于颠簸造成被保险机动车损失的，不属于坠落。<br>
        【外界物体倒塌】指被保险机动车自身以外的物体倒下或陷下。<br>
        【自燃】指在没有外界火源的情况下，由于本车电器、线路、供油系统、供气系统等被保险机动车自身原因或所载货物自身原因起火燃烧。<br>
        【火灾】指被保险机动车本身以外的火源引起的、在时间或空间上失去控制的燃烧（即有热、有光、有火焰的剧烈的氧化反应）所造成的灾害。<br>
        【次生灾害】指地震造成工程结构、设施和自然环境破坏而引发的火灾、爆炸、瘟疫、有毒有害物质污染、海啸、水灾、泥石流、滑坡等灾害。<br>
        【暴风】指风速在28.5米/秒（相当于11级大风）以上的大风。风速以气象部门公布的数据为准。<br>
        【暴雨】指每小时降雨量达16毫米以上，或连续12小时降雨量达30毫米以上，或连续24小时降雨量达50毫米以上。<br>
        【洪水】指山洪暴发、江河泛滥、潮水上岸及倒灌。但规律性的涨潮、自动灭火设施漏水以及在常年水位以下或地下渗水、水管爆裂不属于洪水责任。<br>
        【玻璃单独破碎】指未发生被保险机动车其他部位的损坏，仅发生被保险机动车前后风挡玻璃和左右车窗玻璃的损坏。<br>
        【车轮单独损坏】指未发生被保险机动车其他部位的损坏，仅发生轮胎、轮辋、轮毂罩的分别单独损坏，或上述三者之中任意二者的共同损坏，或三者的共同损坏。<br>
        【车身划痕损失】仅发生被保险机动车车身表面油漆的损坏，且无明显碰撞痕迹。<br>
        【新增设备】指被保险机动车出厂时原有设备以外的，另外加装的设备和设施。<br>
        【新车购置价】指本保险合同签订地购置与被保险机动车同类型新车的价格，无同类型新车市场销售价格的，由投保人与保险人协商确定。<br>
        【单方肇事事故】指不涉及与第三者有关的损害赔偿的事故，但不包括自然灾害引起的事故。<br>
        【家庭成员】指配偶、子女、父母。<br>
        【市场公允价值】 指熟悉市场情况的买卖双方在公平交易的条件下和自愿的情况下所确定的价格，或无关联的双方在公平交易的条件下一项资产可以被买卖或者一项负债可以被清偿的成交价格。<br>
        【参考折旧系数表】
    </p><table border="1" style="margin-bottom: 5px;" algin="center">
    <tbody algin="center">
    <tr>
        <td rowspan="3">车辆种类</td>
        <td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 月折旧系数</td>
    </tr>
    <tr>
        <td rowspan="2">家庭自用</td>
        <td rowspan="2">非营业</td>
        <td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 营业</td>
    </tr>
    <tr>
        <td>出租</td>
        <td>其他</td>
    </tr>
    <tr>
        <td>9座以下客车</td>
        <td>0.60%</td>
        <td>0.60%</td>
        <td>1.10%</td>
        <td>0.90%</td>
    </tr>
    <tr>
        <td>10座以上客车</td>
        <td>0.90%</td>
        <td>0.90%</td>
        <td>1.10%</td>
        <td>0.90%</td>
    </tr>
    <tr>
        <td>微型载货汽车</td>
        <td>/</td>
        <td>0.90%</td>
        <td>1.10%</td>
        <td>1.10%</td>
    </tr>
    <tr>
        <td>带拖挂的载货汽车</td>
        <td>/</td>
        <td>0.90%</td>
        <td>1.10%</td>
        <td>1.10%</td>
    </tr>
    <tr>
        <td>低速货车和三轮汽车</td>
        <td>/</td>
        <td>1.10%</td>
        <td>1.40%</td>
        <td>1.40%</td>
    </tr>
    <tr>
        <td>其他车辆</td>
        <td>/</td>
        <td>0.90%</td>
        <td>1.10%</td>
        <td>0.90%</td>
    </tr>
    </tbody>
</table>
    折旧按月计算，不足一个月的部分，不计折旧。最高折旧金额不超过投保时被保险机动车新车购置价的80%。<br>
    折旧金额=新车购置价×被保险机动车已使用月数×月折旧系数<br>
    【饮酒】指驾驶人饮用含有酒精的饮料，驾驶机动车时血液中的酒精含量大于等于20 mg/100 mL的。<br>
    【全部损失】 指被保险机动车发生事故后灭失，或者受到严重损坏完全失去原有形体、效用，或者不能再归被保险人所拥有的，为实际全损；或被保险机动车发生事故后，认为实际全损已经不可避免，或者为避免发生实际全损所需支付的费用超过实际价值的，为推定全损。<p></p>
</div>
</div>
<div id="LudiDCInDetion_content" style="display:none;">
    <div style="">
    <div style="height:auto; font-size: 0.8rem; font-size: 15px; line-height: 1.5; color:v#333; background-color: #fff; text-align: center; padding-right: 10px;">机动车保险投保提示</div>


    <p style="color: #666; font-size: 12px; line-height: 1.5; margin-top: 10px;">欢迎您到华安财险投保！根据保险法的要求，我公司就保险标的或者被保险人的有关情况填写的，您应当如实告知。为了保障您的合法权益，在您填写投保信息前，请先详细阅读《机动车交通事故责任强制保险条款》及我公司《机动车综合商业保险条款[2014版]》，阅读条款时请您特别注意各个条款中的保险责任、责任免除、投保人被保险人义务、赔偿处理、附则等内容。您在充分理解条款后，再填写投保各项内容。为合理确定投保机动车的保险费，确认您已按保险法的相关要求履行如实告知义务，保证您获得充足的保障，请您认真填写每个项目，确保内容的真实可靠。您所填写的内容我公司将为您保密。本投保单所填内容如有变动，请您及时到我公司办理变更手续。
        最后，再次确认保险人已经告知本人仔细阅读条款，提示本人特别阅读黑体字标注部分的条款内容。保险人对保险合同内容、尤其是免除保险人责任的条款、交通违法系数已经向本人作出了明确说明，本人已经完全理解，没有异议，本人保证上述填写内容真实。
    </p>
        </div>
</div>
<div id="LudiDCDN_content" style="display:none;">
    <div style=" line-height: 1.5; color: #666;">
    <h3 style="font-size: 15px;margin-bottom: 5px; color: #333;">机动车综合商业保险免责事项说明书</h3>
    <p style="font-size: 12px;">
        尊敬的客户：<br/>
        欢迎您选择华安财产保险公司投保机动车综合商业保险。当您投保本保险后，我公司将根据您选择投保的主险和附加险险种，按照保险合同的约定，承担相应的保险赔偿责任。
        风险是无处不在的。应对风险带来的损失，您可以采取控制的方式消除或减少，可以采取自留的方式靠自身力量解决，还可以通过购买保险的方式将风险损失转移给保险公司。但是，作为风险管理的技术之一，并不是所有的风险都适合或可以采用保险的方法来处理，只有可保风险才是保险公司所能接受承保的风险。保险公司一般通过保险条款中的保险责任条款和免除保险人责任条款对可保风险予以明确。免除保险人责任条款通过把保险人不承保的情形和事由予以排除，使保险费率保持在合理的水平，减轻消费者的投保压力和保费负担；同时有利于实现保险公司稳健经营。
        本保险合同在保险责任的基础上，从风险控制角度出发，设置了免除保险人责任条款，明确约定了保险人不承担保险赔偿责任的范围，或减轻保险人保险赔偿责任的情形、范围和事由。为维护您的合法权益，在您填写投保单前，我公司就保险合同中的免除保险人责任条款做出如下书面说明，请您注意阅读。同时，我公司工作人员会针对本免责事项说明书的内容以及投保单所附机动车综合商业保险条款向您进行详细说明。您也可以随时向我公司工作人员提出询问，或者致电95556客服热线，我们将悉心为您解答。<br/>
        尊敬的客户，当您已全面了解本免责事项说明书以及机动车综合商业保险条款的内容后，请在本免责事项说明书的投保人签章处签字或盖章确认。祝您投保愉快！

    </p>
    <div>
        第一部分 责任免除条款<br>
        概念：责任免除是指保险合同约定的，而在保险责任内予以剔除的损失和事由。保险人承担的保险赔偿责任，是指属于保险条款列明的保险责任，且不属于免除保险人责任的范围。<br>
        原因：<br>
        <ol>
            <li>国家道路安全法律法规已有禁止性规定。如：“饮酒、吸食或注射毒品、服用国家管制的精神药品或者麻醉药品”、“无驾驶证”、“驾驶与驾驶证载明的准驾车型不相符合的机动车”等。</li>
            <li>缺乏历史统计数据积累，无法得出风险发生概率，缺少费率计算基础。如“战争”、“军事冲突”、“核反应”、“核辐射”等。</li>
            <li>缺乏历史统计数据积累，无法得出风险发生概率，缺少费率计算基础。如“战争”、“军事冲突”、“核反应”、“核辐射”等。</li>
            <li>可以通过增加保险费，加保相应附加险获得保障。如“发动机进水后导致的发动机损坏”、“玻璃单独破碎”、“无明显碰撞痕迹的车身划痕”、“以及新增设备的损失”等。</li>
        </ol>
        内容：根据我公司机动车综合商业保险条款的约定，不同险种的责任免除包括：<br>
        机动车损失保险<br>
        第八条 在上述保险责任范围内，下列情况下，不论任何原因造成被保险机动车的任何损失和费用，保险人均不负责赔偿：<br>
        （一）事故发生后，被保险人或其允许的驾驶人故意破坏、伪造现场、毁灭证据；<br>
        （二）驾驶人有下列情形之一者：<br>
        <ol>
            <li>事故发生后，在未依法采取措施的情况下驾驶被保险机动车或者遗弃被保险机动车离开事故现场；</li>
            <li>饮酒、吸食或注射毒品、服用国家管制的精神药品或者麻醉药品；</li>
            <li>无驾驶证，驾驶证被依法扣留、暂扣、吊销、注销期间；</li>
            <li>驾驶与驾驶证载明的准驾车型不相符合的机动车；</li>
            <li>实习期内驾驶公共汽车、营运客车或者执行任务的警车、载有危险物品的机动车或牵引挂车的机动车；</li>
            <li>驾驶出租机动车或营业性机动车无交通运输管理部门核发的许可证书或其他必备证书；</li>
            <li>学习驾驶时无合法教练员随车指导；</li>
            <li>非被保险人允许的驾驶人；</li>
        </ol>
        （三）被保险机动车有下列情形之一者：<br>
        <ol>
            <li>发生保险事故时被保险机动车行驶证、号牌被注销的，或未按规定检验或检验不合格；</li>
            <li>被扣押、收缴、没收、政府征用期间；</li>
            <li>在竞赛、测试期间，在营业性场所维修、保养、改装期间；</li>
            <li>被保险人或其允许的驾驶人故意或重大过失，导致被保险机动车被利用从事犯罪行为。</li>
        </ol>
        第九条 下列原因导致的被保险机动车的损失和费用，保险人不负责赔偿：<br>
        （一）地震及其次生灾害；<br>
        （二）战争、军事冲突、恐怖活动、暴乱、污染（含放射性污染）、核反应、核辐射；<br>
        （三）人工直接供油、高温烘烤、自燃、不明原因火灾；<br>
        （四）违反安全装载规定；<br>
        （五）被保险机动车被转让、改装、加装或改变使用性质等，被保险人、受让人未及时通知保险人，且因转让、改装、加装或改变使用性质等导致被保险机动车危险程度显著增加；<br>
        （六）被保险人或其允许的驾驶人的故意行为。<br>
        第十条 下列损失和费用，保险人不负责赔偿：<br>
        （一）因市场价格变动造成的贬值、修理后因价值降低引起的减值损失；<br>
        （二）自然磨损、朽蚀、腐蚀、故障、本身质量缺陷；<br>
        （三）遭受保险责任范围内的损失后，未经必要修理并检验合格继续使用，致使损失扩大的部分；<br>
        （四）投保人、被保险人或其允许的驾驶人知道保险事故发生后，故意或者因重大过失未及时通知，致使保险事故的性质、原因、损失程度等难以确定的，保险人对无法确定的部分，不承担赔偿责任，但保险人通过其他途径已经及时知道或者应当及时知道保险事故发生的除外；<br>
        （五）因被保险人违反本条款第十六条约定，导致无法确定的损失；<br>
        （六）被保险机动车全车被盗窃、被抢劫、被抢夺、下落不明，以及在此期间受到的损坏，或被盗窃、被抢劫、被抢夺未遂受到的损坏，或车上零部件、附属设备丢失；<br>
        （七）车轮单独损坏，玻璃单独破碎，无明显碰撞痕迹的车身划痕，以及新增设备的损失；<br>
        （八）发动机进水后导致的发动机损坏。<br>
        说明：上述第八条是情形除外，即只要具有该条所列举的情形，不论任何原因造成被保险机动车的任何损失和费用，保险人均不负责赔偿。<br>
        上述第九条是原因除外，即由于该条所列举的原因导致的被保险机动车的损失和费用，保险人不负责赔偿。<br>
        上述第十条是损失和费用除外，即无论造成损失和费用的原因是什么，该条所列举的损失和费用，保险人均不负责赔偿。其中，除第（七）项“车轮单独损坏”外，第（六）、（七）、（八）项损失和费用，投保人可以增加保险费，加保附加险。<br>
        上述第八条第（一）项，第（二）项第1、2、3、4、5、6、7目，第（三）项第1目，第九条第（四）项属于《道路交通安全法》和《道路交通安全法实施条例》禁止性规定或强制性规定。<br>
        <br>
        机动车第三者责任保险<br>
        第二十四条&nbsp; 在上述保险责任范围内，下列情况下，不论任何原因造成的人身伤亡、财产损失和费用，保险人均不负责赔偿：<br>
        （一）事故发生后，被保险人或其允许的驾驶人故意破坏、伪造现场、毁灭证据；<br>
        （二）驾驶人有下列情形之一者：<br>
        <ol>
            <li>事故发生后，在未依法采取措施的情况下驾驶被保险机动车或者遗弃被保险机动车离开事故现场；</li>
            <li>饮酒、吸食或注射毒品、服用国家管制的精神药品或者麻醉药品；</li>
            <li>无驾驶证，驾驶证被依法扣留、暂扣、吊销、注销期间；</li>
            <li>驾驶与驾驶证载明的准驾车型不相符合的机动车；</li>
            <li>实习期内驾驶公共汽车、营运客车或者执行任务的警车、载有危险物品的机动车或牵引挂车的机动车；</li>
            <li>驾驶出租机动车或营业性机动车无交通运输管理部门核发的许可证书或其他必备证书；</li>
            <li>学习驾驶时无合法教练员随车指导；</li>
            <li>非被保险人允许的驾驶人；</li>
        </ol>

        （三）被保险机动车有下列情形之一者：<br>
        <ol>
            <li>发生保险事故时被保险机动车行驶证、号牌被注销的，或未按规定检验或检验不合格；</li>
            <li>被扣押、收缴、没收、政府征用期间；</li>
            <li>在竞赛、测试期间，在营业性场所维修、保养、改装期间；</li>
            <li>全车被盗窃、被抢劫、被抢夺、下落不明期间。</li>
        </ol>
        第二十五条 下列原因导致的人身伤亡、财产损失和费用，保险人不负责赔偿：<br>
        （一）地震及其次生灾害、战争、军事冲突、恐怖活动、暴乱、污染（含放射性污染）、核反应、核辐射；<br>
        （二）第三者、被保险人或其允许的驾驶人的故意行为、犯罪行为，第三者与被保险人或其它致害人恶意串通的行为；<br>
        （三）被保险机动车被转让、改装、加装或改变使用性质等，被保险人、受让人未及时通知保险人，且因转让、改装、加装或改变使用性质等导致被保险机动车危险程度显著增加。<br>
        第二十六条 下列人身伤亡、财产损失和费用，保险人不负责赔偿：<br>
        （一）被保险机动车发生意外事故，致使任何单位或个人停业、停驶、停电、停水、停气、停产、通讯或网络中断、电压变化、数据丢失造成的损失以及其它各种间接损失；<br>
        （二）第三者财产因市场价格变动造成的贬值，修理后因价值降低引起的减值损失；<br>
        （三）被保险人及其家庭成员、被保险人允许的驾驶人及其家庭成员所有、承租、使用、管理、运输或代管的财产的损失，以及本车上财产的损失；<br>
        （四）被保险人、被保险人允许的驾驶人、本车车上人员的人身伤亡；<br>
        （五）停车费、保管费、扣车费、罚款、罚金或惩罚性赔款；<br>
        （六）超出《道路交通事故受伤人员临床诊疗指南》和国家基本医疗保险同类医疗费用标准的费用部分；<br>
        （七）律师费，未经保险人事先书面同意的诉讼费、仲裁费；<br>
        （八）投保人、被保险人或其允许的驾驶人知道保险事故发生后，故意或者因重大过失未及时通知，致使保险事故的性质、原因、损失程度等难以确定的，保险人对无法确定的部分，不承担赔偿责任，但保险人通过其他途径已经及时知道或者应当及时知道保险事故发生的除外；<br>
        （九）因被保险人违反本条款第三十四条约定，导致无法确定的损失；<br>
        （十）精神损害抚慰金；<br>
        （十一）应当由机动车交通事故责任强制保险赔偿的损失和费用；<br>
        保险事故发生时，被保险机动车未投保机动车交通事故责任强制保险或机动车交通事故责任强制保险合同已经失效的，对于机动车交通事故责任强制保险责任限额以内的损失和费用，保险人不负责赔偿。<br>
        说明：上述第二十四条是情形除外，即只要具有该条所列举的情形，不论任何原因造成的人身伤亡、财产损失和费用，保险人均不负责赔偿。<br>
        上述第二十五条是原因除外，即由于该条所列举的原因导致的人身伤亡、财产损失和费用，保险人不负责赔偿。<br>
        上述第二十六条是损失和费用除外，即无论造成人身伤亡、财产损失和费用的原因是什么，该条所列举的损失和费用，保险人均不负责赔偿。其中第（十）项损失和费用，投保人可以增加保险费，加保附加险。<br>
        上述第二十四条第（一）项，第（二）项第1、2、3、4、5、6、7目，第（三）项第1目属于《道路交通安全法》和《道路交通安全法实施条例》禁止性规定或强制性规定。<br>
        机动车车上人员责任保险<br>
        第四十条 在上述保险责任范围内，下列情况下，不论任何原因造成的人身伤亡，保险人均不负责赔偿：<br>
        （一）事故发生后，被保险人或其允许的驾驶人故意破坏、伪造现场、毁灭证据；<br>
        （二）驾驶人有下列情形之一者：<br>
        <ol>
            <li>事故发生后，在未依法采取措施的情况下驾驶被保险机动车或者遗弃被保险机动车离开事故现场；</li>
            <li>饮酒、吸食或注射毒品、服用国家管制的精神药品或者麻醉药品；</li>
            <li>无驾驶证，驾驶证被依法扣留、暂扣、吊销、注销期间；</li>
            <li>驾驶与驾驶证载明的准驾车型不相符合的机动车；</li>
            <li>实习期内驾驶公共汽车、营运客车或者执行任务的警车、载有危险物品的机动车或牵引挂车的机动车；</li>
            <li>驾驶出租机动车或营业性机动车无交通运输管理部门核发的许可证书或其他必备证书；</li>
            <li>学习驾驶时无合法教练员随车指导；</li>
            <li>非被保险人允许的驾驶人；</li>
        </ol>

        （三）被保险机动车有下列情形之一者：<br>
        <ol>
            <li>发生保险事故时被保险机动车行驶证、号牌被注销的，或未按规定检验或检验不合格；</li>
            <li>被扣押、收缴、没收、政府征用期间；</li>
            <li>在竞赛、测试期间，在营业性场所维修、保养、改装期间；</li>
            <li>全车被盗窃、被抢劫、被抢夺、下落不明期间。</li>
        </ol>
        第四十一条 下列原因导致的人身伤亡，保险人不负责赔偿：<br>
        （一）地震及其次生灾害、战争、军事冲突、恐怖活动、暴乱、污染（含放射性污染）、核反应、核辐射；<br>
        （二）被保险机动车被转让、改装、加装或改变使用性质等，被保险人、受让人未及时通知保险人，且因转让、改装、加装或改变使用性质等导致被保险机动车危险程度显著增加；<br>
        （三）被保险人或驾驶人的故意行为。<br>
        第四十二条 下列人身伤亡、损失和费用，保险人不负责赔偿：<br>
        （一）被保险人及驾驶人以外的其他车上人员的故意行为造成的自身伤亡；<br>
        （二）车上人员因疾病、分娩、自残、斗殴、自杀、犯罪行为造成的自身伤亡；<br>
        （三）违法、违章搭乘人员的人身伤亡；<br>
        （四）罚款、罚金或惩罚性赔款；<br>
        （五）超出《道路交通事故受伤人员临床诊疗指南》和国家基本医疗保险同类医疗费用标准的费用部分；<br>
        （六）律师费，未经保险人事先书面同意的诉讼费、仲裁费；<br>
        （七）投保人、被保险人或其允许的驾驶人知道保险事故发生后，故意或者因重大过失未及时通知，致使保险事故的性质、原因、损失程度等难以确定的，保险人对无法确定的部分，不承担赔偿责任，但保险人通过其他途径已经及时知道或者应当及时知道保险事故发生的除外；<br>
        （八）精神损害抚慰金；<br>
        （九）应当由机动车交通事故责任强制保险赔付的损失和费用。<br>
        说明：上述第四十条是情形除外，即只要具有该条所列举的情形，不论任何原因造成的车上人员人身伤亡，保险人均不负责赔偿。<br>
        上述第四十一条是原因除外，即由于该条所列举的原因导致的车上人员人身伤亡，保险人不负责赔偿。<br>
        上述第四十二条是损失和费用除外，即无论造成车上人员人身伤亡的原因是什么，该条所列举的损失和费用，保险人均不负责赔偿。其中第（八）项损失和费用，投保人可以增加保险费、加保附加险。<br>
        上述第四十条第（一）项，第（二）项第1、2、3、4、5、6、7目，第（三）项第1目属于《道路交通安全法》和《道路交通安全法实施条例》禁止性规定或强制性规定。<br>
        机动车全车盗抢保险<br>
        第五十二条 在上述保险责任范围内，下列情况下，不论任何原因造成被保险机动车的任何损失和费用，保险人均不负责赔偿：<br>
        （一）被保险人索赔时未能提供出险当地县级以上公安刑侦部门出具的盗抢立案证明；<br>
        （二）驾驶人、被保险人、投保人故意破坏现场、伪造现场、毁灭证据；<br>
        （三）被保险机动车被扣押、罚没、查封、政府征用期间；<br>
        （四）被保险机动车在竞赛、测试期间，在营业性场所维修、保养、改装期间，被运输期间。<br>
        第五十三条 下列损失和费用，保险人不负责赔偿：<br>
        （一）地震及其次生灾害导致的损失和费用；<br>
        （二）战争、军事冲突、恐怖活动、暴乱导致的损失和费用；<br>
        （三）因诈骗引起的任何损失；因投保人、被保险人与他人的民事、经济纠纷导致的任何损失；<br>
        （四）被保险人或其允许的驾驶人的故意行为、犯罪行为导致的损失和费用；<br>
        （五）非全车遭盗窃，仅车上零部件或附属设备被盗窃或损坏；<br>
        （六）新增设备的损失；<br>
        （七）遭受保险责任范围内的损失后，未经必要修理并检验合格继续使用，致使损失扩大的部分；<br>
        （八）被保险机动车被转让、改装、加装或改变使用性质等，被保险人、受让人未及时通知保险人，且因转让、改装、加装或改变使用性质等导致被保险机动车危险程度显著增加；<br>
        （九）投保人、被保险人或其允许的驾驶人知道保险事故发生后，故意或者因重大过失未及时通知，致使保险事故的性质、原因、损失程度等难以确定的，保险人对无法确定的部分，不承担赔偿责任，但保险人通过其他途径已经及时知道或者应当及时知道保险事故发生的除外；<br>
        （十）因被保险人违反本条款第五十八条约定，导致无法确定的损失。<br>
        说明：上述第五十二条是情形除外，即只要具有该条所列举的情形，不论任何原因造成被保险机动车的任何损失和费用，保险人均不负责赔偿。<br>
        上述第五十三条是损失和费用除外，即无论造成损失和费用的原因是什么，该条所列举的损失和费用，保险人均不负责赔偿。<br>
        上述第五十二条第（二）项属于《道路交通安全法》和《道路交通安全法实施条例》强制性规定。<br>
        附加险<br>
        险种&nbsp;&nbsp;&nbsp; 责任免除内容<br>
        玻璃单独破碎险&nbsp;&nbsp;&nbsp; 第三条 责任免除<br>
        安装、维修机动车过程中造成的玻璃单独破碎。<br>
        自燃损失险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        （一）自燃仅造成电器、线路、油路、供油系统、供气系统的损失；<br>
        （二）由于擅自改装、加装电器及设备导致被保险机动车起火造成的损失；<br>
        （三）被保险人在使用被保险机动车过程中，因人工直接供油、高温烘烤等违反车辆安全操作规则造成的损失；<br>
        车身划痕损失险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        （一）被保险人及其家庭成员、驾驶人及其家庭成员的故意行为造成的损失；<br>
        （二）因投保人、被保险人与他人的民事、经济纠纷导致的任何损失；<br>
        （三）车身表面自然老化、损坏，腐蚀造成的任何损失；<br>
        修理期间费用补偿险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        下列情况下，保险人不承担修理期间费用补偿：<br>
        （一）因机动车损失保险责任范围以外的事故而致被保险机动车的损毁或修理；<br>
        （二）非在保险人认可的修理厂修理时，因车辆修理质量不合要求造成返修；<br>
        （三）被保险人或驾驶人拖延车辆送修期间；<br>
        车上货物责任险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        （一）偷盗、哄抢、自然损耗、本身缺陷、短少、死亡、腐烂、变质、串味、生锈，动物走失、飞失、货物自身起火燃烧或爆炸造成的货物损失；<br>
        （二）违法、违章载运造成的损失；<br>
        （三）因包装、紧固不善，装载、遮盖不当导致的任何损失；<br>
        （四）车上人员携带的私人物品的损失；<br>
        （五）保险事故导致的货物减值、运输延迟、营业损失及其它各种间接损失；<br>
        （六）法律、行政法规禁止运输的货物的损失；<br>
        精神损害抚慰金责任险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        （一）根据被保险人与他人的合同协议，应由他人承担的精神损害抚慰金；<br>
        （二）未发生交通事故，仅因第三者或本车人员的惊恐而引起的损害；<br>
        （三）怀孕妇女的流产发生在交通事故发生之日起30天以外的；<br>
        不计免赔率险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        下列情况下，应当由被保险人自行承担的免赔金额，保险人不负责赔偿：<br>
        （一）机动车损失保险中应当由第三方负责赔偿而无法找到第三方的；<br>
        （二）因违反安全装载规定而增加的；<br>
        （三）发生机动车全车盗抢保险约定的全车损失保险事故时，被保险人未能提供《机动车登记证书》、机动车来历凭证的，每缺少一项而增加的；<br>
        （四）机动车损失保险中约定的每次事故绝对免赔额；<br>
        （五）可附加本条款但未选择附加本条款的险种约定的；<br>
        （六）不可附加本条款的险种约定的。<br>
        <br>
        第二部分 免赔额和免赔率<br>
        概念：免赔额是指保险合同中约定的，保险公司不承担赔偿责任、由被保险人自行承担的损失额度，对于此额度内的损失，保险公司在理赔时将予以扣除。<br>
        免赔率是指保险合同中约定的，保险人不负赔偿责任的损失占总体损失的比例，保险公司在理赔时，将按照此比例计算不负赔偿责任的损失额度，然后在赔款中进行扣减。<br>
        内容：根据我公司机动车综合商业保险条款的约定，不同险种所使用的免赔率包括：<br>
        机动车损失保险<br>
        第十一条 保险人在依据本保险合同约定计算赔款的基础上，按照下列方式免赔：<br>
        （一）被保险机动车一方负次要事故责任的，实行5%的事故责任免赔率；负同等事故责任的，实行10%的事故责任免赔率；负主要事故责任的，实行15%的事故责任免赔率；负全部事故责任或单方肇事事故的，实行20%的事故责任免赔率；<br>
        （二）被保险机动车的损失应当由第三方负责赔偿，无法找到第三方的，实行30%的绝对免赔率；<br>
        （三）违反安全装载规定、但不是事故发生的直接原因的，增加10%的绝对免赔率；<br>
        （四）对于投保人与保险人在投保时协商确定绝对免赔额的，本保险在实行免赔率的基础上增加每次事故绝对免赔额。<br>
        说明：上述第（一）项中的事故责任免赔率，可以增加保险费，加保“不计免赔率险”，上述第（二）项中的绝对免赔率，可以增加保险费，加保“机动车损失保险无法找到第三方特约险”。而对于第（三）、（四）项中的绝对免赔率（额），不能通过增加保险费扩展承保。<br>
        机动车第三者责任保险<br>
        第二十七条 保险人在依据本保险合同约定计算赔款的基础上，在保险单载明的责任限额内，按照下列方式免赔：<br>
        （一）被保险机动车一方负次要事故责任的，实行5%的事故责任免赔率；负同等事故责任的，实行10%的事故责任免赔率；负主要事故责任的，实行15%的事故责任免赔率；负全部事故责任的，实行20%的事故责任免赔率；<br>
        （二） 违反安全装载规定的，实行10%的绝对免赔率。<br>
        说明：上述第（一）项中的事故责任免赔率，可以增加保险费，加保“不计免赔率险”扩展承保。而对于第（二）项中的绝对免赔率，不能通过增加保险费，加保“不计免赔率险”扩展承保。<br>
        机动车车上人员责任保险<br>
        第四十三条 保险人在依据本保险合同约定计算赔款的基础上，在保险单载明的责任限额内，按照下列方式免赔：<br>
        被保险机动车一方负次要事故责任的，实行5%的事故责任免赔率；负同等事故责任的，实行10%的事故责任免赔率；负主要事故责任的，实行15%的事故责任免赔率；负全部事故责任或单方肇事事故的，实行20%的事故责任免赔率。<br>
        说明：上述约定的事故责任免赔率，可以增加保险费，加保“不计免赔率险”扩展承保。<br>
        机动车全车盗抢保险<br>
        第五十四条 保险人在依据本保险合同约定计算赔款的基础上，按照下列方式免赔：<br>
        （一） 发生全车损失的，绝对免赔率为20%；<br>
        （二） 发生全车损失，被保险人未能提供《机动车登记证书》、机动车来历凭证的，每缺少一项，增加1%的绝对免赔率。<br>
        说明：对于第（一）项中的绝对免赔率，可以增加保险费，加保“不计免赔率险”扩展承保；对于第（二）项中的绝对免赔率，不能通过增加保险费，加保“不计免赔率险”扩展承保。<br>
        附加险<br>
        险种&nbsp;&nbsp;&nbsp; 免赔额、免赔率内容<br>
        自燃损失险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        （四）本附加险每次赔偿实行20%的绝对免赔率，不适用主险中的各项免赔率、免赔额约定。<br>
        新增加设备损失险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        本附加险每次赔偿的免赔约定以机动车损失保险条款约定为准。<br>
        车身划痕损失险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        （四）本附加险每次赔偿实行15%的绝对免赔率，不适用主险中的各项免赔率、免赔额约定。<br>
        发动机涉水损失险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        本附加险每次赔偿均实行15%的绝对免赔率，不适用主险中的各项免赔率、免赔额约定。<br>
        修理期间费用补偿险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        （四）本附加险每次事故的绝对免赔额为1天的赔偿金额，不适用主险中的各项免赔率、免赔额约定。<br>
        车上货物责任险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        （七）本附加险每次赔偿实行20%的绝对免赔率，不适用主险中的各项免赔率、免赔额约定。<br>
        精神损害抚慰金责任险&nbsp;&nbsp;&nbsp; 第二条 责任免除<br>
        （四）本附加险每次赔偿实行20%的绝对免赔率，不适用主险中的各项免赔率、免赔额约定。<br>
        <br>
        第三部分 保险合同终止或解除<br>
        概念：保险合同终止，是指出现法律规定或合同约定的情形时，保险合同的权利义务终止。保险合同解除是指投保人或保险公司依法或依保险合同约定，终止保险合同的行为，是合同终止的情形之一。对于保险合同终止后发生的事故，保险公司不予赔偿。<br>
        内容：<br>
        险种&nbsp;&nbsp;&nbsp; 内容<br>
        机动车损失保险&nbsp;&nbsp;&nbsp; 第二十一条 被保险机动车发生本保险事故，导致全部损失，或一次赔款金额与免赔金额之和（不含施救费）达到保险金额，保险人按本保险合同约定支付赔款后，本保险责任终止，保险人不退还机动车损失保险及其附加险的保险费。<br>
        机动车全车盗抢保险&nbsp;&nbsp;&nbsp; 第六十一条 被保险机动车发生本保险事故，导致全部损失，或一次赔款金额与免赔金额之和达到保险金额，保险人按本保险合同约定支付赔款后，本保险责任终止，保险人不退还机动车全车盗抢保险及其附加险的保险费。<br>
        通用&nbsp;&nbsp;&nbsp; 第六十七条 在保险期间内，被保险机动车转让他人的，受让人承继被保险人的权利和义务。被保险人或者受让人应当及时书面通知保险人。<br>
        因被保险机动车转让导致被保险机动车危险程度发生显著变化的，保险人自收到前款约定的通知之日起三十日内，可以相应调整保险费或者解除本保险合同。<br>
        <br>
        第四部分 其他<br>
        概念：保险人根据本保险合同的约定，按照《道路交通事故受伤人员临床诊疗指南》和国家基本医疗保险的同类医疗费用标准核定医疗费用。<br>
        本保险合同约定的医疗费用赔偿标准和保险费厘定相匹配，对于超出本赔偿标准的医疗费用，保险人不承担赔偿责任。另外，本保险合同为机动车综合商业保险，为被保险人提供基本的风险保障，不能转移被保险人所有的赔偿责任风险。投保人可以通过增加保险费，选择其他更高保障标准的保险产品或相应附加险获得更全面的保障。<br>
        内容：<br>
        险种&nbsp;&nbsp;&nbsp; 内容<br>
        机动车第三者责任保险&nbsp;&nbsp;&nbsp; 第三十六条 保险人按照《道路交通事故受伤人员临床诊疗指南》和国家基本医疗保险的同类医疗费用标准核定医疗费用的赔偿金额。<br>
        未经保险人书面同意，被保险人自行承诺或支付的赔偿金额，保险人有权重新核定。不属于保险人赔偿范围或超出保险人应赔偿金额的，保险人不承担赔偿责任。<br>
        机动车车上人员责任保险&nbsp;&nbsp;&nbsp; 第四十九条 保险人按照《道路交通事故受伤人员临床诊疗指南》和国家基本医疗保险的同类医疗费用标准核定医疗费用的赔偿金额。<br>
        未经保险人书面同意，被保险人自行承诺或支付的赔偿金额，保险人有权重新核定。因被保险人原因导致损失金额无法确定的，保险人有权拒绝赔偿。<br>
        <br>
        第五部分&nbsp; 免除保险人责任条款有关名词释义<br>
        内容&nbsp;&nbsp;&nbsp; 释义<br>
        合法教练员&nbsp;&nbsp;&nbsp; 符合《机动车驾驶员培训管理规定》，持有与培训车型相符的《机动车驾驶培训教练员证》的教练人员。<br>
        竞赛&nbsp;&nbsp;&nbsp; 专业组织在专用场地、赛道组织的专业赛事，以及在各种路面、公路进行的专业赛事，包括非法组织的各种竞速、改装比赛。<br>
        地震次生灾害&nbsp;&nbsp;&nbsp; 指地震造成工程结构、设施和自然环境破坏而引发的火灾、爆炸、瘟疫、有毒有害物质污染、海啸、水灾、泥石流、滑坡等灾害。<br>
        人工直接供油&nbsp;&nbsp;&nbsp; 机动车行驶中供油系统发生故障时，不经燃油供油装置而依靠燃油重力直接向化油器喉管注入燃油的供油方法。<br>
        自燃&nbsp;&nbsp;&nbsp; 在没有外界火源的情况下，由于本车电器、线路、供油系统、供气系统等被保险机动车自身原因或所载货物自身原因起火燃烧。<br>
        自然磨损&nbsp;&nbsp;&nbsp; 被保险机动车在正常使用过程中，没有受到意外事故和自然灾害的影响，机动车整体及零部件的磨擦损耗。<br>
        本身质量缺陷&nbsp;&nbsp;&nbsp; 被保险机动车的产品设计、原材料和零部件、制造装配或说明指示等方面的，未能满足消费或使用产品所必须合理安全要求的情形。<br>
        车轮单独损坏&nbsp;&nbsp;&nbsp; 未发生被保险机动车其他部位的损坏，仅发生轮胎、轮辋、轮毂罩的分别单独损坏，或上述三者之中任意二者的共同损坏，或三者的共同损坏。<br>
        玻璃单独破碎&nbsp;&nbsp;&nbsp; 未发生被保险机动车其他部位的损坏，仅发生被保险机动车前后风挡玻璃和左右车窗玻璃的损坏。<br>
        <br>
        无明显碰撞痕迹的车身划痕&nbsp;&nbsp;&nbsp; 仅发生被保险机动车车身表面油漆的损坏，且无明显碰撞痕迹。<br>
        第三者&nbsp;&nbsp;&nbsp; 因被保险机动车发生意外事故遭受人身伤亡或者财产损失的人，但不包括被保险机动车本车车上人员、被保险人。<br>
        家庭成员&nbsp;&nbsp;&nbsp; 配偶、子女、父母。<br>
        本车车上人员&nbsp;&nbsp;&nbsp; 发生意外事故的瞬间，在被保险机动车车体内或车体上的人员，包括正在上下车的人员。<br>
        新增设备&nbsp;&nbsp;&nbsp; 被保险机动车出厂时原有设备以外的，另外加装的设备和设施。<br>
        饮酒&nbsp;&nbsp;&nbsp; 驾驶人饮用含有酒精的饮料，驾驶机动车时血液中的酒精含量大于等于20 mg/100 mL的。<br>
        交通事故&nbsp;&nbsp;&nbsp; 车辆在道路上因过错或者意外造成的人身伤亡或者财产损失的事件，其中道路是指公路、城市道路和虽在单位管辖范围但允许社会机动车通行的地方，包括广场、公共停车场等用于公众通行的场所。<br>
        单方肇事事故&nbsp;&nbsp;&nbsp; 不涉及与第三者有关的损害赔偿的事故，但不包括自然灾害引起的事故。<br>
        全部损失&nbsp;&nbsp;&nbsp; 被保险机动车发生事故后灭失，或者受到严重损坏完全失去原有形体、效用，或者不能再归被保险人所拥有的，为实际全损；或被保险机动车发生事故后，认为实际全损已经不可避免，或者为避免发生实际全损所需支付的费用超过实际价值的，为推定全损。<br>
        <br>
        <br>
        <strong>投保人声明：<br>
            保险人已通过上述书面形式向本人详细介绍并提供了投保险种所适用的条款，并对其中免除保险人责任的条款（包括责任免除条款、免赔额、免赔率等免除或者减轻保险人责任的条款），以及本保险合同中付费约定和特别约定的内容向本人作了书面明确说明，本人已充分理解并接受上述内容，同意以此作为订立保险合同的依据。本人自愿投保上述险种。<br>
            <br>
        </strong>尊敬的客户，为了充分保障您的权益，请您将以下黑体字内容，在方格内进行手书，以表明您已了解投保内容，并自愿投保：<strong><br>
        本人确认收到条款及《机动车综合商业保险免责事项说明书》。保险人已明确说明免除保险人责任条款的内容及法律后果。<br>
        本人确认收到条款及《机动车综合商业保险免责事项说明书》。</strong>□□□□□□□□□□□□□□□□□□□□□□□□□。<br>
        <br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 投保人签章处：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>
        日期：&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日</p>
    </div>
    </div>
</div>
<div id="LudiDCCTPLClause" style="display:none;">
    <div style="line-height: 1.5 font-size: normal;">
        <div style="height:auto; font-size: 0.8rem; line-height: 1.5; color:#333; font-size: 15px; margin-bottom: 10px; background-color: #fff; text-align: center; padding-right: 10px;">机动车交通事故责任强制保险条款（2008版）</div>

        <div style="height:auto; font-size: 0.8rem; line-height: 1.5; color:#666; background-color: #fff; text-align: center; padding-right: 10px;"><a href="" target="_blank" style="font-size: 14px;color: #0000ff"></a></div>

        <div class="content-title">
        </div>

        <p>特别提示：为充分保障您的权益，请您仔细阅读本条款。机动车交通事故责任强制保险向您提供的是因交通事故造成的对受害人损害赔偿责任风险的基本保障。每辆机动车只需投保一份机动车交通事故责任强制保险，请不要重复投保。<br>
            在投保本保险后，您可以投保其他机动车保险。</p>
        <p>&nbsp;</p>
        <p style="text-align: center;"><strong>总则</strong> </p>
        <p>第一条  根据《中华人民共和国道路交通安全法》、《中华人民共和国保险法》、《机动车交通事故责任强制保险条例》等法律、行政法规，制定本条款。</p>
        <p>&nbsp;</p>
        <p>第二条  机动车交通事故责任强制保险（以下简称交强险）合同由本条款与投保单、保险单、批单和特别约定共同组成。凡与交强险合同有关的约定，都应当采用书面形式。</p>
        <p>&nbsp;</p>
        <p>第三条  交强险费率实行与被保险机动车道路交通安全违法行为、交通事故记录相联系的浮动机制。<br>
            签订交强险合同时，投保人应当一次支付全部保险费。保险费按照中国保险监督管理委员会（以下简称保监会）批准的交强险费率计算。</p>
        <p>&nbsp;</p>
        <p>第四条  交强险合同中的被保险人是指投保人及其允许的合法驾驶人。<br>
            投保人是指与保险人订立交强险合同，并按照合同负有支付保险费义务的机动车的所有人、管理人。</p>
        <p>&nbsp;</p>
        <p>第五条  交强险合同中的受害人是指因被保险机动车发生交通事故遭受人身伤亡或者财产损失的人，但不包括被保险机动车本车车上人员、被保险人。</p>
        <p>&nbsp;</p>
        <p>第六条  交强险合同中的责任限额是指被保险机动车发生交通事故，保险人对每次保险事故所有受害人的人身伤亡和财产损失所承担的最高赔偿金额。责任限额分为死亡伤残赔偿限额、医疗费用赔偿限额、财产损失赔偿限额以及被保险人在道路交通事故中无责任的赔偿限额。其中无责任的赔偿限额分为无责任死亡伤残赔偿限额、无责任医疗费用赔偿限额以及无责任财产损失赔偿限额。</p>
        <p>&nbsp;</p>
        <p>第七条  交强险合同中的抢救费用是指被保险机动车发生交通事故导致受害人受伤时，医疗机构对生命体征不平稳和虽然生命体征平稳但如果不采取处理措施会产生生命危险，或者导致残疾、器官功能障碍，或者导致病程明显延长的受害人，参照国务院卫生主管部门组织制定的交通事故人员创伤临床诊疗指南和国家基本医疗保险标准，采取必要的处理措施所发生的医疗费用。</p>
        <p>&nbsp;</p>
        <p style="text-align: center;"><strong>保险责任</strong></p>
        <p>第八条  在中华人民共和国境内（不含港、澳、台地区），被保险人在使用被保险机动车过程中发生交通事故，致使受害人遭受人身伤亡或者财产损失，依法应当由被保险人承担的损害赔偿责任，保险人按照交强险合同的约定对每次事故在下列赔偿限额内负责赔偿：<br>
            （一）死亡伤残赔偿限额为110000元；<br>
            （二）医疗费用赔偿限额为10000元；<br>
            （三）财产损失赔偿限额为2000元；<br>
            （四）被保险人无责任时，无责任死亡伤残赔偿限额为11000元；无责任医疗费用赔偿限额为1000元；无责任财产损失赔偿限额为100元。<br>
            医疗费用赔偿限额和无责任医疗费用赔偿限额项下负责赔偿医药费、诊疗费、住院费、住院伙食补助费，必要的、合理的后续治疗费、整容费、营养费。<br>
            死亡伤残赔偿限额和无责任死亡伤残赔偿限额项下负责赔偿丧葬费、死亡补偿费、受害人亲属办理丧葬事宜支出的交通费用、残疾赔偿金、残疾辅助器具费、护理费、康复费、交通费、被扶养人生活费、住宿费、误工费，被保险人依照法院判决或者调解承担的精神损害抚慰金。</p>
        <p>&nbsp;</p>
        <p style="text-align: center;"><strong>垫付与追偿</strong></p>
        <p>第九条  被保险机动车在本条（一）至（四）之一的情形下发生交通事故，造成受害人受伤需要抢救的，保险人在接到公安机关交通管理部门的书面通知和医疗机构出具的抢救费用清单后，按照国务院卫生主管部门组织制定的交通事故人员创伤临床诊疗指南和国家基本医疗保险标准进行核实。对于符合规定的抢救费用，保险人在医疗费用赔偿限额内垫付。被保险人在交通事故中无责任的，保险人在无责任医疗费用赔偿限额内垫付。对于其他损失和费用，保险人不负责垫付和赔偿。<br>
            （一）驾驶人未取得驾驶资格的；<br>
            （二）驾驶人醉酒的；<br>
            （三）被保险机动车被盗抢期间肇事的；<br>
            （四）被保险人故意制造交通事故的。<br>
            对于垫付的抢救费用，保险人有权向致害人追偿。</p>
        <p style="text-align: center;"><strong>&nbsp;</strong></p>
        <p style="text-align: center;"><strong>责任免除</strong></p>
        <p>第十条  下列损失和费用，交强险不负责赔偿和垫付：<br>
            （一）因受害人故意造成的交通事故的损失； <br>
            （二）被保险人所有的财产及被保险机动车上的财产遭受的损失；<br>
            （三）被保险机动车发生交通事故，致使受害人停业、停驶、停电、停水、停气、停产、通讯或者网络中断、数据丢失、电压变化等造成的损失以及受害人财产因市场价格变动造成的贬值、修理后因价值降低造成的损失等其他各种间接损失；<br>
            （四）因交通事故产生的仲裁或者诉讼费用以及其他相关费用。</p>
        <p style="text-align: center;"><strong>&nbsp;</strong></p>
        <p style="text-align: center;"><strong>保险期间</strong></p>
        <p>第十一条  除国家法律、行政法规另有规定外，交强险合同的保险期间为一年，以保险单载明的起止时间为准。</p>
        <p>&nbsp;</p>
        <p style="text-align: center;"><strong>投保人、被保险人义务</strong></p>
        <p>第十二条  投保人投保时，应当如实填写投保单，向保险人如实告知重要事项，并提供被保险机动车的行驶证和驾驶证复印件。重要事项包括机动车的种类、厂牌型号、识别代码、号牌号码、使用性质和机动车所有人或者管理人的姓名（名称）、性别、年龄、住所、身份证或者驾驶证号码（组织机构代码）、续保前该机动车发生事故的情况以及保监会规定的其他事项。<br>
            投保人未如实告知重要事项，对保险费计算有影响的，保险人按照保单年度重新核定保险费计收。</p>
        <p>&nbsp;</p>
        <p>第十三条  签订交强险合同时，投保人不得在保险条款和保险费率之外，向保险人提出附加其他条件的要求。</p>
        <p>&nbsp;</p>
        <p>第十四条  投保人续保的，应当提供被保险机动车上一年度交强险的保险单。</p>
        <p>&nbsp;</p>
        <p>第十五条  在保险合同有效期内，被保险机动车因改装、加装、使用性质改变等导致危险程度增加的，被保险人应当及时通知保险人，并办理批改手续。否则，保险人按照保单年度重新核定保险费计收。</p>
        <p>&nbsp;</p>
        <p>第十六条  被保险机动车发生交通事故，被保险人应当及时采取合理、必要的施救和保护措施，并在事故发生后及时通知保险人。</p>
        <p>&nbsp;</p>
        <p>第十七条  发生保险事故后，被保险人应当积极协助保险人进行现场查勘和事故调查。<br>
            发生与保险赔偿有关的仲裁或者诉讼时，被保险人应当及时书面通知保险人。</p>
        <p>&nbsp;</p>
        <p style="text-align: center;"><strong>赔偿处理</strong></p>
        <p>第十八条  被保险机动车发生交通事故的，由被保险人向保险人申请赔偿保险金。被保险人索赔时，应当向保险人提供以下材料：<br>
            （一）交强险的保险单；<br>
            （二）被保险人出具的索赔申请书；<br>
            （三）被保险人和受害人的有效身份证明、被保险机动车行驶证和驾驶人的驾驶证；<br>
            （四）公安机关交通管理部门出具的事故证明，或者人民法院等机构出具的有关法律文书及其他证明；<br>
            （五）被保险人根据有关法律法规规定选择自行协商方式处理交通事故的，应当提供依照《交通事故处理程序规定》规定的记录交通事故情况的协议书；<br>
            （六）受害人财产损失程度证明、人身伤残程度证明、相关医疗证明以及有关损失清单和费用单据;<br>
            （七）其他与确认保险事故的性质、原因、损失程度等有关的证明和资料。<br>
            因保险事故损坏的受害人财产需要修理的，被保险人应当在修理前会同保险人检验，协商确定修理或者更换项目、方式和费用。否则，保险人在交强险责任限额内有权重新核定。</p>
        <p>&nbsp;</p>
        <p>第十九条  保险事故发生后，保险人按照国家有关法律法规规定的赔偿范围、项目和标准以及交强险合同的约定，并根据国务院卫生主管部门组织制定的交通事故人员创伤临床诊疗指南和国家基本医疗保险标准，在交强险的责任限额内核定人身伤亡的赔偿金额。</p>
        <p>&nbsp;</p>
        <p>第二十条  因保险事故造成受害人人身伤亡的，未经保险人书面同意，被保险人自行承诺或支付的赔偿金额，保险人在交强险责任限额内有权重新核定。</p>
        <p>&nbsp;</p>
        <p>第二十一条  被保险机动车发生涉及受害人受伤的交通事故，因抢救受害人需要保险人支付抢救费用的，保险人在接到公安机关交通管理部门的书面通知和医疗机构出具的抢救费用清单后，按照国务院卫生主管部门组织制定的交通事故人员创伤临床诊疗指南和国家基本医疗保险标准进行核实。对于符合规定的抢救费用，保险人在医疗费用赔偿限额内支付。被保险人在交通事故中无责任的，保险人在无责任医疗费用赔偿限额内支付。</p>
        <p>&nbsp;</p>
        <p style="text-align: center;"><strong>合同变更与终止</strong></p>
        <p>第二十二条  在交强险合同有效期内，被保险机动车所有权发生转移的，投保人应当及时通知保险人，并办理交强险合同变更手续。</p>
        <p>&nbsp;</p>
        <p>第二十三条  在下列三种情况下，投保人可以要求解除交强险合同：<br>
            （一）被保险机动车被依法注销登记的；<br>
            （二）被保险机动车办理停驶的；<br>
            （三）被保险机动车经公安机关证实丢失的。<br>
            交强险合同解除后，投保人应当及时将保险单、保险标志交还保险人；无法交回保险标志的，应当向保险人说明情况，征得保险人同意。</p>
        <p>&nbsp;</p>
        <p>第二十四条  发生《机动车交通事故责任强制保险条例》所列明的投保人、保险人解除交强险合同的情况时，保险人按照日费率收取自保险责任开始之日起至合同解除之日止期间的保险费。</p>
        <p>&nbsp;</p>
        <p style="text-align: center;"><strong>附则</strong></p>
        <p>第二十五条  因履行交强险合同发生争议的，由合同当事人协商解决。<br>
            协商不成的，提交保险单载明的仲裁委员会仲裁。保险单未载明仲裁机构或者争议发生后未达成仲裁协议的，可以向人民法院起诉。</p>
        <p>&nbsp;</p>
        <p>第二十六条  交强险合同争议处理适用中华人民共和国法律。</p>
        <p>&nbsp;</p>
        <p>第二十七条  本条款未尽事宜，按照《机动车交通事故责任强制保险条例》执行。</p>
    </div>
</div>
<script type="text/javascript">
	var orderNo = "${insuranceDetailsVO.baseinfor.orderno}";
	//获取错误信息	
var errorMessage = "${errorMessage}";
var errorCode = "${errorCode}";	
var agentFlag="${agentFlag}";
var error = "C99999999";
var error2 = "E00000030";
//判断，如果错误了，显示错误消息，反之
if (error == errorCode || error2 == errorCode) {
	if(agentFlag == "1"){
		$("#chackMessage").html(errorMessage);
	}else{
		$("#chackMessage").html("提交失败，请拨打客服热线10100111-6咨询");	
	}
    $("#errorService").show();
}

/* $('html').ready(function(){
	var appCoypCheckbox="${appCoypCheckbox}";
    var insureCopyCheckbox="${insureCopyCheckbox}";
    var deliveryCopyCheckbox="${deliveryCopyCheckbox}";
    if(appCoypCheckbox=="1"){
    	var Span=document.getElementsByClassName("con_ul")[0].getElementsByTagName("span")[0];
    	Span.setAttribute("class","on");
    	$("#appCopy").attr("checked","checked");
    }
    if(insureCopyCheckbox=="1"){
    	var Span1=document.getElementsByClassName("con_ul")[0].getElementsByTagName("span")[1];
    	Span1.setAttribute("class","on");
    	$("#insureCopy").attr("checked","checked");
    }
    if(deliveryCopyCheckbox=="1"){
   	 	var Span2=document.getElementsByClassName("con_ul cona_ul")[0].getElementsByTagName("span")[0];
    	Span2.setAttribute("class","on");
    	$("#copyDelivery").attr("checked","checked");
    }
}); */

//关闭错误信息
	$('.errortan3').click(function(){
	    $("#errorService").hide();
	
	});
</script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/LAreaData1.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/LAreaData2.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/LArea.js"></script>
<%-- <script src="<%=path%>/views/quicksure/scripts/quicksure/jquery.min.js"></script>  --%>
<script src="<%=path%>/views/quicksure/scripts/quicksure/mobiscroll_date.js" charset="gb2312"></script> 
<script src="<%=path%>/views/quicksure/scripts/quicksure/mobiscroll.js"></script> 
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script src="<%=path%>/views/quicksure/scripts/personInfors.js?version=<%=nowDate %>"></script>
<script src="<%=path%>/views/quicksure/scripts/layer.js"></script>
<script src="<%=path%>/views/quicksure/scripts/date.js"></script>
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/optiscroll.js"></script>
</body>
<script type="text/javascript">
	$('#ensure').click(function(){
         $('.errorhei').hide();
        }) ;
</script>
</html>
