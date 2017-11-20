<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<title>报价信息</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/views/quicksure/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/css.css">
<link href="<%=path%>/views/quicksure/css/mobiscroll_date.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/normalize.css" />
 <link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/htmleaf-demo.css">
 <link rel="stylesheet" href="<%=path%>/views/quicksure/css/optiscroll.css">
<%--  <link href="<%=path%>/views/quicksure/css/mobiscroll_003.css" rel="stylesheet" type="text/css">
 <link href="<%=path%>/views/quicksure/css/mobiscroll_002.css" rel="stylesheet" type="text/css"> --%>
 <script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
 <script type="text/javascript" src="<%=path%>/views/quicksure/scripts/quicksure/swiper-3.4.0.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/jquery.1.7.2.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/js.js"></script>
 <script type="text/javascript" src="<%=path%>/views/quicksure/scripts/optiscroll.js"></script> 
 <script src="<%=path%>/views/quicksure/scripts/quicksure/mobiscroll_date.js" charset="utf-8"></script>
 <script src="<%=path%>/views/quicksure/scripts/mobiscroll.js" type="text/javascript"></script>
<script src="<%=path%>/views/quicksure/scripts/date.js"></script>
<script src="<%=path%>/views/quicksure/scripts/coveinfor.js"></script> 
<%-- <script src="<%=path%>/views/quicksure/scripts/mobiscroll_002.js" type="text/javascript"></script>
<script src="<%=path%>/views/quicksure/scripts/mobiscroll_004.js" type="text/javascript"></script> --%>

<%-- <script src="<%=path%>/views/quicksure/scripts/mobiscroll_003.js" type="text/javascript"></script> --%>
</head>

<body>
<%-- <div style="position:fixed;top:0px;left:0px;width:100%;height:100%;z-index: 100;display:none;"id="pop">
<div  style='width: auto;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 10px;letter-spacing: 2px; padding: 20px;text-align: center; position: fixed;top: 35%; left: 34%;' >
 <img  src="<%=path%>/views/quicksure/images/mu_loading.gif" style='width: 36px; margin-bottom: 5px;'>
	   <p id="prompt"></p >
	</div>
</div>   --%>  
<div style="position:fixed;top:0px;left:0px;width:100%;height:100%;z-index: 100;display:none;"id="pop">
<div style="position: fixed;top:35%; width:100%;height:auto;text-align:center;">
     <div style='display:inline-block;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 10px;letter-spacing: 2px; padding: 20px;'>
        <img  src="<%=path%>/views/quicksure/images/mu_loading.gif" style='width: 36px; margin-bottom: 5px;'>
        <p id="prompt"></p>
	</div>
	 
</div>
</div>
<form action="<%=path%>/PremiumCount/goToPersonInfor.do" method="post" id="form" name="form" style='position:relative;'> 
<div class="div640">
    <div class="div600 index7">
     <div class="ul_bei">
     <img src="<%=path%>/views/quicksure/images/cove.png">
		    <ul class="ul_a">
		      <li><a href="javascript:void(0);">基本信息</a></li>
		      <li class="on"><a href="javascript:void(0);">报价信息</a></li>
		      <li><a href="javascript:void(0);">确认信息</a></li>
		      <li><a href="javascript:void(0);">支付</a></li>
		    </ul>
      <div class="clear"></div>
     </div>
    
     <div class="content3 boder1 con3_paddding" style="margin-top: 7px;">
       <p class="con3_p1"><img src="<%=path%>/views/quicksure/images/index3_2.png">险种报价</p>
       
       <div class="content3  con3_paddding">
	        <p class="con3_p3"><span class="con3_span"><input type="checkbox" id="CTPLCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='0357'}">checked="checked"</c:if></c:forEach>></span>依据国家交通法规,您应该<a style="color:red;">投保交强险</a>,缴纳车船税</p>
	        <p class="con3_p2"><font class="font1"></font>交强险起保日期：&nbsp;&nbsp;<input readonly class="input text2 mnbv" id="JQ_DATE" type="text" maxlength="11" <c:if test="${insuranceDetailsVO.baseinfor.jqpolicystartdate!=null&&insuranceDetailsVO.baseinfor.jqpolicystartdate!=''}">value='${insuranceDetailsVO.baseinfor.jqpolicystartdate}'</c:if> placeholder="请选择交強险起期"></p>
	        <p class="jqcon con3_p4" style="margin-left: 8px;">
	           交强险保费:<span><input style="width: 60px;text-align: center;color: rgba(208, 36, 36, 0.94);"  type="text" readonly="readonly" id="CTPLPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='0357'}">value="${s.premium}"</c:if></c:forEach>></span>
	          车船税:<span><input style="width: 60px;text-align: center;color: rgba(208, 36, 36, 0.94);" id ="currenttax"  readonly="readonly" type="text" value="${insuranceDetailsVO.taxinfor.sumuptax}"></span>
	        </p>
       </div>
       
       <p class="con3_p21 boder1"><font class="font1">*</font>商业险起保日期：<input readonly class="input text2 mnbv" name="" id="SY_DATE" type="text" maxlength="11" <c:if test="${insuranceDetailsVO.baseinfor.sypolicystartdate!=null&&insuranceDetailsVO.baseinfor.sypolicystartdate!=''}">value='${insuranceDetailsVO.baseinfor.sypolicystartdate}'</c:if> placeholder="请选择商业险起期" ><b style="color:red;"></b></p>
       <div class="clear"></div>
       <b style="display:none">订单号:<input  type="text" id="orderNo" name="orderNo" value="${insuranceDetailsVO.baseinfor.orderno}" readonly="readonly"/></b>
       <table class="con3_table">
         <tbody class="tbodyinfor">
	         <tr class="tr1 tri">
	           <td class="td01" style="font-size: 14px;"><span id="selectAll"></span>&nbsp;&nbsp;投保险种</td>
	           <td class="td2" style="width: 65px;">不计免赔</td>
	           <td class="td3">保额</td>
	           <!--  <td class="td4" style='color: rgba(208, 36, 36, 0.94);'>保费<b class="td4B">+不计</b></td>-->
	         </tr>
	         
         </tbody>
         <tbody class="tbodyinfor">
	         <tr class='tri'>
	           <td class="td1"><span><input type="checkbox"  id="modCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="list"><c:if test="${list.insrnccode=='030101'}">checked="checked"</c:if></c:forEach>/></span><font>车辆损失险</font></td>
	           <td class="td2"><span><input type="checkbox" id="modAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.deductibleflag!=null}">checked="checked"</c:if></c:forEach> /></span></td>
	           <td class="td3">
	             <i class="con3_i">
	               <input readonly="readonly" type="text" id="modQuota" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'}">value="${s.suminsured}"</c:if></c:forEach> />
	             </i>
	           </td>
	           <%-- <td class="td4"><input  readonly="readonly" type="text" id="modQuotaPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>><input readonly="readonly" type="text" id="modductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach> /></td> --%>
	         </tr>
	        <tr class="trll" id="trl1" style="display:none">
	        	<td>&yen;<input class="inputtr" readonly="readonly" type="text" id="modQuotaPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>></td>
	        	<td>&yen;<input readonly="readonly" type="text" class="inputtr" id="modductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach> /></td>
	        	<td>合计:&yen;<input class="inputtr" id="modTotalPremium" value='' readonly="readonly"/></td>
	        </tr>
         </tbody>
         <tbody class="tbodyinfor">
	         <tr class='tri'>
	           <td class="td1"><span><input type="checkbox"  id="vtplCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'}">checked="checked"</c:if></c:forEach> /></span><font>第三者责任险</font></td>
	           <td class="td2"><span><input type="checkbox" id="vtplAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.deductibleflag!=null}">checked="checked"</c:if></c:forEach> /></span></td>
	           <td class="td3">
	             <i>
	                <select class="select" id="vtplQuota">
	                   	<option value="306006004" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='306006004'}">selected="selected"</c:if></c:forEach>>5W</option>
					    <option value="306006005" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='306006005'}">selected="selected"</c:if></c:forEach>>10W</option>
					    <option value="306006006" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='306006006'}">selected="selected"</c:if></c:forEach>>20W</option>
					    <option value="306006007" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='306006007'}">selected="selected"</c:if></c:forEach>>30W</option>
					    <option value="306006009" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='306006009'}">selected="selected"</c:if></c:forEach>>50W</option>
					    <option value="306006014" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='3060060014'}">selected="selected"</c:if></c:forEach>>100W</option>
	                </select>
	              
	             </i>
	           </td>
	           <%-- <td class="td4"><input readonly="readonly" type="text" id="vtplPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>><input readonly="readonly"  type="text" id="vtplductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach>></td> --%>
	         </tr>
	        <tr class="trll" id="trl2" style="display:none"><td>&yen;<input class="inputtr" readonly="readonly" type="text" id="vtplPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>></td><td>&yen;<input readonly="readonly"  type="text" class="inputtr" id="vtplductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach>></td><td>合计:&yen;<input id="vtplTotalPremium" class="inputtr" value='' readonly="readonly"/></td></tr>
	      </tbody>
	       <tbody class="tbodyinfor">
		         <tr class='tri'>
		           <td class="td1"><span><input type="checkbox"  id="theftCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'}">checked="checked"</c:if></c:forEach> /></span><font>全车盗抢险</font></td>
		           <td class="td2"><span><input type="checkbox" id="theftAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.deductibleflag!=null}">checked="checked"</c:if></c:forEach>/></span></td>
		           <td class="td3">
		             <i class="con3_i">
		                <input readonly="readonly" type="text" id="theftQuota" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'}">value="${s.suminsured}"</c:if></c:forEach> />
		             </i>
		           </td>
		           <%-- <td class="td4"><input readonly="readonly" type="text" id="theftPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>><input readonly="readonly" type="text"  id="theftductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach>></td> --%>
		         </tr>
		         <tr class="trll" id="trl3" style="display:none"><td>&yen;<input class="inputtr" readonly="readonly" type="text" id="theftPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>></td><td>&yen;<input class="inputtr" readonly="readonly" type="text"  id="theftductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach>></td><td>合计&yen;<input class="inputtr" id="theftTotalPremium" value='' readonly="readonly"/></td></tr>
		    </tbody>
		    <tbody class="tbodyinfor">
				  <tr class='tri'>
					           <td class="td1"><span><input type="checkbox"  id="DriversCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'}">checked="checked"</c:if></c:forEach> /></span><font>司机座位责任险</font></td>
					           <td class="td2"><span><input type="checkbox" id="DriversAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.deductibleflag!=null}">checked="checked"</c:if></c:forEach> /></span></td>
					           <td class="td3">
					             <i>
					                <select class="select" id="DriversQuota">
					                  	<option value="10000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='10000'}">selected="selected"</c:if></c:forEach>>10000</option>
						               <option value="20000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='20000'}">selected="selected"</c:if></c:forEach>>20000</option>
						               <option value="30000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='30000'}">selected="selected"</c:if></c:forEach>>30000</option>
						               <option value="50000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='50000'}">selected="selected"</c:if></c:forEach>>50000</option>
						               <option value="60000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='60000'}">selected="selected"</c:if></c:forEach>>60000</option>
						               <option value="70000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='70000'}">selected="selected"</c:if></c:forEach>>70000</option>
						               <option value="80000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='80000'}">selected="selected"</c:if></c:forEach>>80000</option>
						               <option value="90000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='90000'}">selected="selected"</c:if></c:forEach>>90000</option>
						               <option value="100000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='100000'}">selected="selected"</c:if></c:forEach>>100000</option>
					                </select>
					                
					             </i>
					           </td>
		           <%-- <td class="td4"><input readonly="readonly" type="text" id="DriversPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>><input  readonly="readonly"  type="text" id="DriversductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach>></td> --%>
		         </tr>
		        <tr class="trll" id="trl4" style="display:none"><td>&yen;<input class="inputtr" readonly="readonly" type="text" id="DriversPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>></td><td>&yen;<input  class="inputtr" readonly="readonly"  type="text" id="DriversductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach>></td><td>合计:&yen;<input class="inputtr" id="DriversTotalPremium" value='' readonly="readonly"/></td></tr>
         </tbody>
         <tbody class="tbodyinfor">
		         <tr class='tri'>
		           <td class="td1"><span><input type="checkbox"  id="PassengerCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'}">checked="checked"</c:if></c:forEach>></span><font>乘客座位责任险</font></td>
		           <td class="td2"><span><input type="checkbox" id="PassengerAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.deductibleflag!=null}">checked="checked"</c:if></c:forEach> /></span></td>
		           <td class="td3">
		             <i>
		                <select class="select" id="PassengerQuota">
		                   <option value="10000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='10000'}">selected="selected"</c:if></c:forEach>>10000</option>
			               <option value="20000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='20000'}">selected="selected"</c:if></c:forEach>>20000</option>
			               <option value="30000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='30000'}">selected="selected"</c:if></c:forEach>>30000</option>
			               <option value="50000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='50000'}">selected="selected"</c:if></c:forEach>>50000</option>
			               <option value="60000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='60000'}">selected="selected"</c:if></c:forEach>>60000</option>
			               <option value="70000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='70000'}">selected="selected"</c:if></c:forEach>>70000</option>
			               <option value="80000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='80000'}">selected="selected"</c:if></c:forEach>>80000</option>
			               <option value="90000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='90000'}">selected="selected"</c:if></c:forEach>>90000</option>
			               <option value="100000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='100000'}">selected="selected"</c:if></c:forEach>>100000</option>
		                </select>
		             </i>
		           </td>
		           <%-- <td class="td4"><input readonly="readonly" type="text" id="PassengerPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>><input  readonly="readonly"  type="text" id="PassengerductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach>></td> --%>
		         </tr>
		      <tr class="trll" id="trl5" style="display:none"><td>&yen;<input class="inputtr" readonly="readonly" type="text" id="PassengerPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>></td><td>&yen;<input  readonly="readonly" class="inputtr" type="text" id="PassengerductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach>></td><td>合计:&yen;<input class="inputtr" id="PassengerTotalPremium" value=''readonly="readonly"/></td></tr>
         </tbody>
         <tbody class="tbodyinfor">
		         <tr class='tri'>
		           <td class="td1"><span><input type="checkbox"  id="CombustionCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'}">checked="checked"</c:if></c:forEach>></span><font>自燃损失险</font></td>
		           <td class="td2"><span><input type="checkbox" id="CombustionAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'&&s.deductibleflag!=null}">checked="checked"</c:if></c:forEach>></span></td>
		           <td class="td3">
		             <i class="con3_i">
		               <input readonly="readonly" type="text" id="CombustionQuota" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'}">value="${s.suminsured}"</c:if></c:forEach>> 
		             </i>
		           </td>
		           <%-- <td class="td4"><input readonly="readonly" type="text" id="CombustionPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>><input  readonly="readonly"  type="text" id="CombustionductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach>></td> --%>
		         </tr>
		       <tr class="trll" id="trl6" style="display:none"><td>&yen;<input class="inputtr" readonly="readonly" type="text" id="CombustionPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>></td><td>&yen;<input class="inputtr" readonly="readonly"  type="text" id="CombustionductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'&&s.deductibleflag!=null}">value="${s.nyl12}"</c:if></c:forEach>></td><td>合计:&yen;<input class="inputtr" id="CombustionTotalPremium" value='' readonly="readonly"/></td></tr>
		  </tbody>
		  <tbody class="tbodyinfor">
	         <tr class='tri'>
	           <td class="td1"><span><input type="checkbox" id="GlassCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'}">checked="checked"</c:if></c:forEach>></span><font>玻璃单独破碎险</font></td>
	           <td></td>
	           <td class="td3">
	             <i>
	                <select id="GlassQuota" class="select">
	                  <option value="303011001" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'&&s.suminsured=='303011001'}">selected="selected"</c:if></c:forEach>>国产玻璃</option>
	                  <option value="303011002" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'&&s.suminsured=='303011002'}">selected="selected"</c:if></c:forEach>>进口玻璃</option>
	              </select>
	               
	             </i>
	           </td>
	          <%--  <td class="td4"><input readonly="readonly" type="text" id="GlassQuotaPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>></td> --%>
	         </tr>  
	         <tr class="trll" id="trl7" style="display:none"><td>&yen;<input readonly="readonly" class="inputtr" type="text" id="GlassQuotaPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'&&s.deductibleflag!=null}">value="${s.premium}"</c:if></c:forEach>></td><td></td><td>合计:&yen;<input id="GlassTotalPremium" class="inputtr" value='' readonly="readonly"/></td></tr>
         </tbody>     
         <tr style="display:none;">
           <td class="td1"><span></span><font>机动车损失无法找到</br>第三方损失险</font></td>
           <td class="td2"><span></span></td>
           <td class="td3"></td>
           <td class="td4">3000|60</td>
         </tr>
         <tr style="display:none;">
           <td class="td1"><span></span><font>指定专修厂特约险</font></td>
           <td class="td2"><span></span></td>
           <td class="td3"></td>
           <td class="td4">3000|60</td>
         </tr>
       </table>
       
     </div>
     
       
       <div class="content3 boder1">
      
        <p class="con3_p5 con3_p6"><span>总保费 </span><b>=</b><span>商业险</span><b>+</b><span>交强险</span><b>+</b><span>车船税</span></p>
        <p class="con3_p5"><span><input id ="totalPremium" type="text" readonly="readonly" value="${insuranceDetailsVO.baseinfor.totalPremium}"></span>
        <b id="b1" style="display:none">=</b><span><input id ="sypremium" type="text"  readonly="readonly"  value="${insuranceDetailsVO.baseinfor.sypremium}"></span>
        <b id="b2" style="display:none">+</b><span><input id ="jqpremium" type="text" readonly="readonly"  value="${insuranceDetailsVO.baseinfor.jqpremium}"></span>
        <b id="b3" style="display:none">+</b><span><input id ="currenttax1" type="text"  readonly="readonly" value="${insuranceDetailsVO.taxinfor.sumuptax}"></span></p>
        <p class="con3_p7"><input type="button" id="quote" onclick="getQuote()" value="计算保费"/></p>
       
       </div>
       
       <div class="con7_divb con3_divb" >
         <div style="display:none;" id="next">
          <!-- <a class="con7_a1" onclick="goback()">上一步</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
          <a class="con7_a2 red" href="javascript:submitFrom()" style="margin-bottom: 5%;margin-top: -3%;" >确认报价</a>
          <p class="con3_p3">若对保费信息有任何疑问,请拔打服务热线<b style="color:red;">10100111-6</b></p>
         </div> 
       </div>

</div>
  
</div>
</form>

     <div class="errorhei2" id="errorService" style="display:none">
	<div class="errortan">
		<p class="errortan1">信息提示<a class="errortan3" href="javascript:void(0);"><img src="<%=path%>/views/quicksure/images/Clear.png"/></a></p>
		<div class="m-wrapper" id="m-wrapper">
		  <p class="errortan2"><div id="chackMessage"></div></p>
		</div>
		<a class="errortan3" href="javascript:void(0);"></a>
	</div>
    </div> 
    <div id="errorhei" class="errorhei" style="display:none;z-index: 100;">
		<div style="   position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
			<div style='max-width:300px;background: rgba(250,250,250,1);color: #333;margin: 0 auto;padding: 10px 0px;border-radius: 5px;'>
			     <h3>信息提示</h3>
			     <span id="chackMess" style='display:inline-block;z-index: 100;width: auto;height: auto;color:#333;font-size: 13px;letter-spacing: 2px;padding:20px;text-align: center; '></span>
			<div style='border-top:1px solid #ddd;font-size:17px;color:#333;padding: 8px 0px;'><span style='display:block;width:100%;font-size:15px' id="ensure">确定</span></div>
			</div>
			
			<!-- <span id="chackMess" style='display:inline-block;font-style: italic;z-index: 100;width: auto;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 13px;letter-spacing: 2px;    padding: 20px;text-align: center; '></span> -->
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
</body>
<script src="<%=path%>/views/quicksure/scripts/layer.js"></script>
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script type="text/javascript">
var wr = new Optiscroll(document.getElementById('m-wrapper'), { forceScrollbars: true });
	function goback(){
		window.location.href = "<%=path%>/vehicleInfor/backToVehicleScreen.do?orderNo=${insuranceDetailsVO.baseinfor.orderno}";
	}
	//关闭错误信息
	$('.btn').click(function(){
	    $('.error_Box').hide();
	
	})
	//关闭错误信息
	$('.errortan3').click(function(){
	    $('#errorService').hide();
	
	})
	$('#ensure').click(function(){
    $('.errorhei').hide();
    }) ;
</script>

</html>
