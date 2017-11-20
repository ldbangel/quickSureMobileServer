<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>支付</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/views/quicksure/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/css.css">
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/js.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/swiper-3.4.0.min.js"></script>

</head>

<body>

<div class="div640">
    <div class="div600 index7">
     <div class="ul_bei">
  <img src="<%=path%>/views/quicksure/images/zhifu.png">
		    <ul class="ul_a">
		    
		      <li><a href="javascript:void(0);">基本信息</a></li>
		      <li><a href="javascript:void(0);">报价信息</a></li>
		      <li><a href="javascript:void(0);">确认信息</a></li>
		      <li class="on"><a href="javascript:void(0);">支付</a></li>
		     
		    </ul>
     <div class="clear"></div>
     </div>
     
     <div class="content9 boder1">
     <form action="<%=path%>/paymentInfor/paymentApplication.do" method="post" name="form"  id="form" enctype="application/x-www-form-urlencoded">
      <p class="con9_p1"><font>您的订单已成功提交！</font></p><p style="font-size: 15px;padding: 0 2%;">为保证您的保单能按时起效，请尽快完成支付</p>
      <input style="display:none" id="orderNo" name="orderNo" value="${insuranceDetailsVO.baseinfor.orderno}" readonly="readonly"/>
      <table class="con10_table">
       <tr class="tr1"><td class="td1">投保内容</td><td class="td2">保险起期</td><td class="td3" style="color: #333333;">金额</td></tr>
       <c:if test="${!empty insuranceDetailsVO.baseinfor.jqpremium}"><tr><td class="td1">交强险</td><td class="td2" style="font-size: 13px;">${insuranceDetailsVO.baseinfor.jqpolicystartdate}</td><td class="td3">&yen;${insuranceDetailsVO.baseinfor.jqpremium}</td></tr></c:if>
       <c:if test="${!empty insuranceDetailsVO.baseinfor.sypremium}"><tr><td class="td1">商业险</td><td class="td2" style="font-size: 13px;">${insuranceDetailsVO.baseinfor.sypolicystartdate}</td><td class="td3">&yen;${insuranceDetailsVO.baseinfor.sypremium}</td></tr></c:if>
       <c:if test="${!empty insuranceDetailsVO.baseinfor.taxpremium}"><tr><td class="td1">车船税</td><td class="td2"></td><td class="td3">&yen;${insuranceDetailsVO.baseinfor.taxpremium}</td></tr></c:if>
      </table>
      <table class="con9_table con9_table2">
      <tr><td>合计：&yen;${insuranceDetailsVO.baseinfor.totalPremium}</td></tr>
      </table>
      
      <div class="con9_div" style="display:none">
        <p class="p1">输入推荐优惠劵码</p>
        <input class="text3" type="text" disabled="disabled"/>
        <input class="button1" type="button" value="提交" disabled="disabled"/>
        <div class="clear"></div>
      </div>
      <p class="con10_p3" style="display:none">实际应付：&yen;${insuranceDetailsVO.baseinfor.totalPremium}</p>
      <p class="con3_p7 "><a href="javascript:submitFrom()">立即支付</a></p>
    </form>
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
<div style="position:fixed;top:0px;left:0px;width:100%;height:100%;z-index: 100;display:none;"id="pop">
<div style="position: fixed;top:35%; width:100%;height:auto;text-align:center;">
     <span style='display:inline-block;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 10px;letter-spacing: 2px; padding: 20px;'>
        <img  src="<%=path%>/views/quicksure/images/mu_loading.gif" style='width: 36px; margin-bottom: 5px;'>
        <p id="prompt"></p>
	</span>
	 
</div>
</div>
<script src="<%=path%>/views/quicksure/scripts/layer.js"></script>
<script type="text/javascript">
$('html').ready(function(){
	$("#pop").hide();
});
var errorMessage = "${errorMessage}";
var errorCode = "${errorCode}";	
  var ii;
	function submitFrom() {	
		 // ii = layer.load(0, {shade: [0.5, '#393D49'],time: 7*1000});
		  $("#prompt").html("正在支付,请稍后...");
  		  load(20000);
		  $("#form").submit();
	
	}
//延迟时间
function load(outtimes){
	$("#pop").show();
	setTimeout(function(){$('#pop').hide()
	},outtimes);
}

 var error = "C99999999";
var error2 = "E00000030";
//判断，如果错误了，显示错误消息，反之
if (error == errorCode || error2 == errorCode) {
    $("#Message").html(errorMessage);
    $('.error_Box').show();
} else {
    layer.close(ii);
    $('.error_Box').hide();
}	
</script>
</body>

</html>
