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
    <script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
    <title>订单信息</title>
</head>
<body>
<div class="content">
    <div id="basic_mation">
        <div class="top_title"><i class='i1'><img style="width: 18px;" src="<%=path%>/views/quicksure/images/jbxx.png"></i>基本信息</div>
        <div>
            <table>
                <tr>
                    <td>被保人</td>
                    <td><c:if test="${!empty simpleinsurancevo.insuranceperinfor.insurename}">${simpleinsurancevo.insuranceperinfor.insurename}</c:if><c:if test="${empty simpleinsurancevo.insuranceperinfor.insurename}">暂无数据</c:if></td>
                </tr>
                <tr>
                    <td>车牌号</td>
                    <td><c:if test="${!empty simpleinsurancevo.vhlinfor.lcnno}">${simpleinsurancevo.vhlinfor.lcnno}</c:if><c:if test="${empty simpleinsurancevo.vhlinfor.lcnno}">暂无数据</c:if></td>
                </tr>
                <tr>
                    <td>车辆所属人</td>
                    <td><c:if test="${!empty simpleinsurancevo.vhlinfor.drvowner}">${simpleinsurancevo.vhlinfor.drvowner}</c:if><c:if test="${empty simpleinsurancevo.vhlinfor.drvowner}">暂无数据</c:if></td>
                </tr>
                <tr>
                    <td>发动机号</td>
                    <td><c:if test="${!empty simpleinsurancevo.vhlinfor.engno}">${simpleinsurancevo.vhlinfor.engno}</c:if><c:if test="${empty simpleinsurancevo.vhlinfor.engno}">暂无数据</c:if></td>
                </tr>
                <tr>
                    <td>车架号</td>
                    <td><c:if test="${!empty simpleinsurancevo.vhlinfor.vinno}">${simpleinsurancevo.vhlinfor.vinno}</c:if><c:if test="${empty simpleinsurancevo.vhlinfor.vinno}">暂无数据</c:if></td>
                </tr>
                <tr>
                    <td>核定载客数</td>
                    <td><c:if test="${!empty simpleinsurancevo.vhlinfor.vinno}">${simpleinsurancevo.vhlinfor.setno}人</c:if><c:if test="${empty simpleinsurancevo.vhlinfor.vinno}">暂无数据</c:if></td>
                </tr>
                <tr>
                    <td>初登日期</td>
                    <td><c:if test="${!empty simpleinsurancevo.vhlinfor.registerdate}">${simpleinsurancevo.vhlinfor.registerdate}</c:if><c:if test="${empty simpleinsurancevo.vhlinfor.registerdate}">暂无数据</c:if></td>
                </tr>
                <tr>
                    <td>新车购置价</td>
                    <td><c:if test="${!empty simpleinsurancevo.vhlinfor.vhlval}">${simpleinsurancevo.vhlinfor.vhlval}元</c:if><c:if test="${empty simpleinsurancevo.vhlinfor.vhlval}">暂无数据</c:if></td>
                </tr>
                <tr>
                    <td>保费</td>
                    <td><c:if test="${!empty simpleinsurancevo.baseinfor.totalPremium}">${simpleinsurancevo.baseinfor.totalPremium}元</c:if><c:if test="${empty simpleinsurancevo.baseinfor.totalPremium}">暂无数据</c:if></td>
                </tr>
            </table>
        </div>
    </div>
    <div id="Insurance_date">
        <div class="top_title"><i class='i2'><img style="width: 22px;" src="<%=path%>/views/quicksure/images/bxqx.png"></i>保险期限</div>
        <div>
            <table>
                <tr>
                    <td>商业险</td>
                    <td>${simpleinsurancevo.baseinfor.sypolicystartdate}（0时）起 至 ${simpleinsurancevo.baseinfor.sypolicyenddate}（24时）止</td>
                </tr>
                <tr>
                    <td>交强险</td>
                    <td>${simpleinsurancevo.baseinfor.jqpolicystartdate}（0时）起 至 ${simpleinsurancevo.baseinfor.jqpolicyenddate}（24时）止</td>
                </tr>
            </table>
        </div>
    </div>
    <div id="Insurance_list">
        <div class="top_title"><i class='i3'><img style="width: 20px;" src="<%=path%>/views/quicksure/images/xzxx.png"></i>险种信息</div>
        <table>
            <tr>
                <th>承保险别</th>
                <th>保额（元）</th>
                <th>保费（元）</th>
                <th>不计免赔保费（元）</th>
            </tr>
            
             <c:forEach items="${simpleinsurancevo.coverageinfors}" var="s">
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
                	<c:if test="${s.insrnccode=='030119'}">不计免赔总保费</c:if>
                	<c:if test="${s.insrnccode=='0357'}">交强险</c:if>
                	<%-- <c:if test="${s.insrnccode=='0357'&&s.premium!=''}">车船税</c:if> --%>
                	
                </td>               
                <td><c:if test="${empty s.suminsured || s.suminsured==''|| s.suminsured=='0' || s.suminsured=='0.00'}">--</c:if>
                    <c:if test="${!empty s.suminsured && s.suminsured!=''&& s.suminsured!='0' && s.suminsured!='0.00'}">${s.suminsured}</c:if></td>
                <%-- <td>${s.premium}</td> --%>
                <td><c:if test="${empty s.premium || s.premium==''|| s.premium=='0' || s.premium=='0.00'}">--</c:if>
                    <c:if test="${!empty s.premium && s.premium!=''&& s.premium!='0' && s.premium!='0.00'}">${s.premium}</c:if></td>
                <%-- <td>${s.nyl12}</td> --%>
                <td><c:if test="${empty s.nyl12 || s.nyl12==''|| s.nyl12=='0' || s.nyl12=='0.00'}">--</c:if>
                    <c:if test="${!empty s.nyl12 && s.nyl12!=''&& s.nyl12!='0' && s.nyl12!='0.00'}">${s.nyl12}</c:if></td>
            </tr>        
     		</c:forEach>     		
     			<c:if test="${!empty simpleinsurancevo.baseinfor.taxpremium && simpleinsurancevo.baseinfor.taxpremium!=''&& simpleinsurancevo.baseinfor.taxpremium!='0'&& simpleinsurancevo.baseinfor.taxpremium!='0.00'}">
     			<tr>
     				<td>
     				车船税   				
     				</td>
     				<td>--</td>
     				<td>${simpleinsurancevo.baseinfor.taxpremium}</td>
     				<td>--</td>     				
     			</tr>
     			</c:if>       		
        </table>
    </div>
    <div class="foot_btn">
	    <ul>
		    <c:choose><c:when test="${simpleinsurancevo.baseinfor.orderstate==10||simpleinsurancevo.baseinfor.orderstate==20}"><li class='Go_ON'><a href="#" onclick="continueInsure('${simpleinsurancevo.baseinfor.orderno}');">继续投保</a></li></c:when><c:when test="${simpleinsurancevo.baseinfor.orderstate==30||simpleinsurancevo.baseinfor.orderstate==40}"><li class='Go_ON'><a href="#" onclick="continuePay('${simpleinsurancevo.baseinfor.orderno}');">继续支付</a></li></c:when><c:otherwise></c:otherwise></c:choose>
		    <c:if test="${simpleinsurancevo.baseinfor.orderstate==10||simpleinsurancevo.baseinfor.orderstate==20||simpleinsurancevo.baseinfor.orderstate==30||simpleinsurancevo.baseinfor.orderstate==40}"><li><a href="#" onclick="cancelOrder('${simpleinsurancevo.baseinfor.orderno}');">取消订单</a></li></c:if>
	    </ul>	    
    </div>
    
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
//继续支付
function continuePay(orderno){//继续支付
	$("#prompt").html("正在支付，请稍等...");
	load(60000);
	$.ajax({
	   type : "POST",
	   url : getUrl()+"/myAccount/continuePay.do?orderno="+orderno,
       async: false ,
	   success : function(result){
		   $("#pop").hide();
		   if(result==null||result==""){
			   $('#Message').html("支付异常！");
			   $('.errorhei').show();
			   $('.errortan4').click(function(){
				 $('.errorhei').hide();
			  });
			  /* $("#prompt").html("支付异常！");
			   load(1000);*/
		   }else{
			   window.location.href=result;
		   }
	   },
	    error: function(res){
	    	 $("#pop").hide();
	     	 $("#Message").html("支付异常");
	     	 $('.errorhei').show();
	     	 $('.errortan4').click(function(){
				 $('.errorhei').hide();
			  });
	    }  
	});
}

//取消订单
function cancelOrder(orderno,userid){//取消订单
   if(window.confirm('你确定要取消订单?')){
	    $("#prompt").html("正在撤销，请稍等...");
		load(60000);
		$.ajax({
		   type : "POST",
		   url : getUrl()+"/orderHandle/cancelOrder.do?orderno="+orderno,
		   success : function(result){
			    $("#pop").hide();
				if(result=="success"){
					$('#Message').html("订单取消成功！");
					$('.errorhei').show();
					$('.errortan4').click(function(){
						$('.errorhei').hide();
						window.location.href=getUrl()+"/views/quicksure/jsp/myAccount.jsp";
					});
				}else{
					$('#Message').html("取消失败！");
					$('.errorhei').show();
					$('.errortan4').click(function(){
						$('.errorhei').hide();
					});
				}
		   },
		   error: function(res){
		    	 $("#pop").hide();
		     	 $("#Message").html("取消失败");
		     	 $('.errorhei').show();
		     	 $('.errortan4').click(function(){
						$('.errorhei').hide();
					});     
		    }  		   
		});
        return true;
    }
}

//继续投保
function continueInsure(orderno){
   $.ajax({
	   type : "POST",
	   url : getUrl()+"/myAccount/continueInsure.do?orderno="+orderno,
       async: false ,
	   success : function(path){
		   if(path != ""){
			   window.location.href=getUrl()+path;
		   }else{
			   return false;
		   }
	   },
	   error: function(res){
	    	 $("#pop").hide();
	     	 $("#Message").html("投保失败");
	     	 $('.errorhei').show();
	     	 $('.errortan4').click(function(){
				 $('.errorhei').hide();
			  });
	    }  
   });
}

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