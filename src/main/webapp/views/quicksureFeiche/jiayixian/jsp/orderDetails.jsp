<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
   	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="screen-orientation" content="portrait">
    <link type="text/css" rel="stylesheet" href="<%=path%>/views/quicksure/css/style.css">
    <link rel="stylesheet" href="<%=path%>/views/quicksure/css/policyDetails.css">
    <title>订单信息</title>
</head>
<body>
<div class="content">
    <div id="basic_mation">
        <div class="top_title"><i class='i1'><img style="width: 18px;" src="<%=path%>/views/quicksure/images/jbxx.png"></i>基本信息</div>
        <div>
            <table>
                <tr>
                    <td>订单号</td>
                    <td>${baseinfor.orderno}</td>
                </tr>
                <tr>
                    <td>被保人</td>
                    <td><c:if test="${!empty baseinfor.insurername}">${baseinfor.insurername}</c:if><c:if test="${empty baseinfor.insurername}">暂无数据</c:if></td>
                </tr>
                <tr>
                    <td>被保人性别</td>
                    <td>
                    	<c:if test="${!empty baseinfor.insSex && baseinfor.insSex=='106001'}">男</c:if>
                    	<c:if test="${!empty baseinfor.insSex && baseinfor.insSex=='106002'}">女</c:if>
                    	<c:if test="${empty baseinfor.insSex}">暂无数据</c:if>
                    </td>
                </tr>
                <tr>
                    <td>被保人手机</td>
                    <td><c:if test="${!empty baseinfor.insPhone}">${baseinfor.insPhone}</c:if><c:if test="${empty baseinfor.insPhone}">暂无数据</c:if></td>
                </tr>
                <tr>
                    <td>保额</td>
                    <td>
                    	<c:if test="${!empty baseinfor.sumamount}">${baseinfor.sumamount}</c:if>
                    	<c:if test="${empty baseinfor.sumamount}">暂无数据</c:if></td>
                </tr>
                <tr>
                    <td>保费</td>
                    <td><c:if test="${!empty baseinfor.sumpremium}">${baseinfor.sumpremium}</c:if><c:if test="${empty baseinfor.sumpremium}">暂无数据</c:if></td>
                </tr>
            </table>
        </div>
    </div>
    <div id="Insurance_date">
        <div class="top_title"><i class='i2'><img style="width: 22px;" src="<%=path%>/views/quicksure/images/bxqx.png"></i>保险期限</div>
        <div>
            <table>
                <tr>
                    <td>保险起期</td>
                    <td>
                    	<c:if test="${!empty baseinfor.insuranceStartTime && baseinfor.insuranceStartTime!=''}">${baseinfor.insuranceStartTime}（0时）起 </c:if>
                    	<c:if test="${empty baseinfor.insuranceStartTime || baseinfor.insuranceStartTime==''}">暂无数据 </c:if>
                    </td>
                </tr>
                <tr>
                    <td>保险止期</td>
                    <td>
                    	<c:if test="${!empty baseinfor.insruanceEndTime && baseinfor.insruanceEndTime!=''}">${baseinfor.insruanceEndTime}（24时）止 </c:if>
                    	<c:if test="${empty baseinfor.insruanceEndTime || baseinfor.insruanceEndTime==''}">暂无数据 </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="Insurance_list">
        <div class="top_title"><i class='i3'><img style="width: 20px;" src="<%=path%>/views/quicksure/images/xzxx.png"></i>险种信息</div>
        <table>
        	<tr>
        		<td>险种名称</td>
        		<td>
        			<c:if test="${!empty baseinfor.prodno && baseinfor.prodno=='060I'}">驾意险</c:if>
        			<c:if test="${!empty baseinfor.prodno && baseinfor.prodno=='060a'}">一路平安</c:if>
        			<c:if test="${!empty baseinfor.prodno && baseinfor.prodno=='0615'}">君安保</c:if>
        			<c:if test="${empty baseinfor.prodno}">暂无数据</c:if>
        		</td>
        	</tr>
        	<tr>
        		<td>产品类型</td>
        		<td>
        			<c:if test="${!empty baseinfor.prodno && baseinfor.prodno=='060I'}">
        				<c:if test="${!empty baseinfor.prodtype &&  baseinfor.prodtype=='A'}">方案一</c:if>
        				<c:if test="${!empty baseinfor.prodtype &&  baseinfor.prodtype=='B'}">方案二</c:if>
        				<c:if test="${!empty baseinfor.prodtype &&  baseinfor.prodtype=='C'}">方案三</c:if>
        				<c:if test="${!empty baseinfor.prodtype &&  baseinfor.prodtype=='D'}">方案四</c:if>
        				<c:if test="${!empty baseinfor.prodtype &&  baseinfor.prodtype=='E'}">方案五</c:if>
        				<c:if test="${!empty baseinfor.prodtype &&  baseinfor.prodtype=='F'}">方案六</c:if>
        			</c:if>
        			<c:if test="${!empty baseinfor.prodno && baseinfor.prodno=='060a'}">
        				<c:if test="${!empty baseinfor.prodtype &&  baseinfor.prodtype=='A'}">保障型</c:if>
        				<c:if test="${!empty baseinfor.prodtype &&  baseinfor.prodtype=='B'}">尊贵型</c:if>
        			</c:if>
        			<c:if test="${!empty baseinfor.prodno && baseinfor.prodno=='0615'}">
        				<c:if test="${!empty baseinfor.prodtype &&  baseinfor.prodtype=='A'}">基础型</c:if>
        				<c:if test="${!empty baseinfor.prodtype &&  baseinfor.prodtype=='B'}">优选型</c:if>
        				<c:if test="${!empty baseinfor.prodtype &&  baseinfor.prodtype=='C'}">尊贵型</c:if>
        			</c:if>
        		</td>
        	</tr>
        	<c:if test="${baseinfor.orderstate=='50' || baseinfor.orderstate=='60' || baseinfor.orderstate=='70'}">
        	<tr>
        		<td>投保单号</td>
        		<td>${baseinfor.applicationno }</td>
        	</tr>
        	<tr>
        		<td>保单号</td>
        		<td>${baseinfor.policyno }</td>
        	</tr>
        	</c:if>
        </table>
    </div>
    <%-- <div class="foot_btn">
	    <ul>
		    <c:choose><c:when test="${simpleinsurancevo.baseinfor.orderstate==10||simpleinsurancevo.baseinfor.orderstate==20}"><li class='Go_ON'><a href="#" onclick="continueInsure('${simpleinsurancevo.baseinfor.orderno}');">继续投保</a></li></c:when><c:when test="${simpleinsurancevo.baseinfor.orderstate==30||simpleinsurancevo.baseinfor.orderstate==40}"><li class='Go_ON'><a href="#" onclick="continuePay('${simpleinsurancevo.baseinfor.orderno}');">继续支付</a></li></c:when><c:otherwise></c:otherwise></c:choose>
		    <c:if test="${simpleinsurancevo.baseinfor.orderstate==10||simpleinsurancevo.baseinfor.orderstate==20||simpleinsurancevo.baseinfor.orderstate==30||simpleinsurancevo.baseinfor.orderstate==40}"><li><a href="#" onclick="cancelOrder('${simpleinsurancevo.baseinfor.orderno}');">取消订单</a></li></c:if>
	    </ul>	    
    </div> --%>
    
    <div class="errorhei" style="display:none;width: 648px;">
		<div class="errortan">
		<p class="errortan1">信息提示<a class="errortan4" href="javascript:void(0);"><img src="<%=path%>/views/quicksure/images/Clear.png"/></a></p>
		<p class="errortan2"><div id="Message" style="font-size: 17px;margin-top: 66px;"></div></p>
		</div>
	</div>
	
	<div style="position:fixed;top:0px;left:0px;width:100%;height:100%;z-index: 100;display:none;"id="pop">
		<div style="position: fixed;top:35%; width:100%;height:auto;text-align:center;">
	     	<span style='display:inline-block;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 10px;letter-spacing: 2px; padding: 20px;'>
	     	   <img  src="<%=path%>/views/quicksure/images/mu_loading.gif" style='width: 36px; margin-bottom: 5px;'>
	        	<p id="prompt"></p>
			</span>
		</div>
	</div>
</div>

<script src="<%=path%>/views/quicksure/scripts/jquery.1.7.2.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script>
//定时关闭弹出框
function load(outtimes){
	$("#pop").show();
	setTimeout(function(){$('#pop').hide();
	},outtimes);
}


//定义转换函数
function dateConvert(dateParms){ 
    // 对传入的时间参数进行判断
    if(dateParms instanceof Date){
        var datetime=dateParms;
    }
    //判断是否为字符串
    if((typeof dateParms=="string")&&dateParms.constructor==String){
         
        //将字符串日期转换为日期格式
        var datetime= new Date(Date.parse(dateParms.replace(/-/g,   "/")));
     
    }
     
    //获取年月日时分秒
     var year = datetime.getFullYear();
     var month = datetime.getMonth()+1; 
     var date = datetime.getDate(); 
     var hour = datetime.getHours(); 
     var minutes = datetime.getMinutes(); 
     var second = datetime.getSeconds();
     
     //月，日，时，分，秒 小于10时，补0
     if(month<10){
      month = "0" + month;
     }
     if(date<10){
      date = "0" + date;
     }
     if(hour <10){
      hour = "0" + hour;
     }
     if(minutes <10){
      minutes = "0" + minutes;
     }
     if(second <10){
      second = "0" + second ;
     }
      
     //拼接日期格式【例如：yyyymmdd】
     var time = year+'-'+month+'-'+date; 
      
     //或者：其他格式等
     //var time = year+"年"+month+"月"+date+"日"+hour+":"+minutes+":"+second; 
      
     //返回处理结果
     return time;
    }


</script>
</body>
</html>