<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="format-detection" content="telephone=no"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<title>支付详情界面</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/views/quicksure/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/css.css">
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/quicksure/swiper-3.4.0.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/js.js"></script>
</head>

<body>
<div class="div640">
    <div class="div600 index7">
     <div class="ul_bei">
   <%--  <img src="<%=path%>/views/quicksure/images/index8_1.png"> --%>
    <img src="<%=path%>/views/quicksure/images/zhifu.png">
    <!--  <ul class="ul_a">
      <li><a href="javascript:void(0);">基本信息</a></li>
      <li><a href="javascript:void(0);">报价信息</a></li>
      <li><a href="javascript:void(0);">确认信息</a></li>
      <li class="on"><a href="javascript:void(0);">支付</a></li>      
     </ul> -->
     <div class="clear"></div>
     </div>
     
     <div class="content9 boder1">
     <div class="content8">
        <div class="logo"><img src="<%=path%>/views/quicksure/images/index8_2.png"></div>
          <P class="con8_p1">您的爱车已成功投保，电话客服将会在一个工作日内与您联系，感谢您选择慧英保险</P>
        
        <div class="con8_div">
          <p class="con8_p2">订单编号:<span>${insuranceDetailsVO.baseinfor.orderno}</span></p>
          <p class="con8_p2" style='float:right;padding:0px;'>已支付：<span>${insuranceDetailsVO.paymentinfor.paymentpremium}</span></p>
          <div class="clear"></div>
        </div>
        <table class="con8_table">
       <tr class="tr1">
            <td class="td1">产品名称</td>
				<td class="td2"><c:if test="${!empty insuranceDetailsVO.baseinfor.jqpolicyno||!empty insuranceDetailsVO.baseinfor.sypolicyno}">保单号</c:if>
				<c:if test="${empty insuranceDetailsVO.baseinfor.jqpolicyno && empty insuranceDetailsVO.baseinfor.sypolicyno}">            	                  		 
	           		 <c:if test="${!empty insuranceDetailsVO.baseinfor.jqapplicationno || !empty insuranceDetailsVO.baseinfor.syapplicationno}">
	           		 投保单号
	           		 </c:if>
           		 </c:if>
				</td>           
            <%-- 
	            <c:if test="${(!empty insuranceDetailsVO.baseinfor.jqpolicyno && insuranceDetailsVO.baseinfor.jqpolicyno!='') || (!empty insuranceDetailsVO.baseinfor.sypolicyno&&insuranceDetailsVO.baseinfor.sypolicyno!='')}">
	            </c:if>
	            <c:if test="${empty insuranceDetailsVO.baseinfor.jqpolicyno && insuranceDetailsVO.baseinfor.jqpolicyno=='' && empty insuranceDetailsVO.baseinfor.sypolicyno && insuranceDetailsVO.baseinfor.sypolicyno==''} ">            	                  		 
	           		 <c:if test="${(!empty insuranceDetailsVO.baseinfor.jqapplicationno || insuranceDetailsVO.baseinfor.jqapplicationno!='') || (!empty insuranceDetailsVO.baseinfor.syapplicationno || insuranceDetailsVO.baseinfor.syapplicationno!='')}">
	           		 <td class="td2">投保单号</td>
	           		 </c:if>
           		 </c:if>
           		 <c:if test="${!empty insuranceDetailsVO.baseinfor.syapplicationno || insuranceDetailsVO.baseinfor.syapplicationno==''}">111111</c:if>	
            	 --%>
            <td class="td3">金额</td>
       </tr>
       <c:if test="${!empty insuranceDetailsVO.baseinfor.jqpremium}">
       <tr>
            <td class="td1">交强险</td>
           
                <c:if test="${!empty insuranceDetailsVO.baseinfor.jqpolicyno}">
                      <td class="td2"><span>${insuranceDetailsVO.baseinfor.jqpolicyno}</span></td>
                </c:if>
              
           	    <c:if test="${!empty insuranceDetailsVO.baseinfor.jqapplicationno&&empty insuranceDetailsVO.baseinfor.jqpolicyno}">
                <td class="td2"><span>${insuranceDetailsVO.baseinfor.jqapplicationno}</span></td>
                </c:if>
               
            
            <td class="td3"><span>${insuranceDetailsVO.baseinfor.jqpremium}</span></td>
       </tr>
       </c:if>
       <c:if test="${!empty insuranceDetailsVO.baseinfor.sypremium}">
       <tr>
            <td class="td1">商业险</td>
        
	            <c:if test="${!empty insuranceDetailsVO.baseinfor.sypolicyno}">
	                    <td class="td2"><span>${insuranceDetailsVO.baseinfor.sypolicyno}</span></td>
	            </c:if>
	            
           	    <c:if test="${!empty insuranceDetailsVO.baseinfor.syapplicationno&&empty insuranceDetailsVO.baseinfor.sypolicyno}">
                <td class="td2"><span>${insuranceDetailsVO.baseinfor.syapplicationno}</span></td>
                </c:if>
              
           
            <td class="td3"><span>${insuranceDetailsVO.baseinfor.sypremium}</span></td>
       </tr>
       </c:if>
       <c:if test="${!empty insuranceDetailsVO.baseinfor.taxpremium}">
       <tr>
            <td class="td1">车船税</td>
            <td class="td2"><span style="display: none">${insuranceDetailsVO.baseinfor.taxinforid}</span></td>
            <td class="td3"><span>${insuranceDetailsVO.baseinfor.taxpremium}</span></td>
       </tr>
       </c:if>
      </table>
          
          <p class="con3_p7 "><a href="<%=path%>/views/quicksure/jsp/quicksurehome.jsp?orderNo=${insuranceDetailsVO.baseinfor.orderno}">返回首页</a></p>
      
     </div>
     </div>
     
    </div>
    
</div>
</body>

</html>
