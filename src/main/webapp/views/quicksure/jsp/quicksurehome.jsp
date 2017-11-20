﻿<%@ page language="java" import="java.util.*,com.quicksure.mobile.utility.*,com.quicksure.mobile.entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String nowDate=DateFormatUtils.getSystemDateByYYYYMMDDHHMMSSSSS();
String path = request.getContextPath();
 Userinfor userinfor=null;
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String phoneNo = request.getParameter("phoneNo")==null?"":request.getParameter("phoneNo");
String deptAddress = request.getParameter("deptAddress")==null?"":request.getParameter("deptAddress");
String lcnNo = request.getParameter("lcno")==null?"":request.getParameter("lcno");
String dptcode=request.getParameter("dptcode")==null?"":request.getParameter("dptcode");
String codeId=request.getParameter("codeId")==null?"":request.getParameter("codeId");//二维码扫描后跳转到首页
String userIdQr=request.getParameter("userIdQr")==null?"":request.getParameter("userIdQr");//扫描二维码获取到userId
String shareId=request.getParameter("userId")==null?"":request.getParameter("userId"); //微信链接分享获取到userId
/* String passwordQr=request.getParameter("passwordQr")==null?"":request.getParameter("passwordQr"); */
/* dptcode = dptcode==""||dptcode==null ?0:dptcode; */
if(lcnNo!=null&&!"".equalsIgnoreCase(lcnNo)){
	lcnNo=new String(lcnNo.getBytes("iso8859-1"),"utf-8");
}
if(deptAddress!=null&&!"".equalsIgnoreCase(deptAddress)){
	deptAddress=new String(deptAddress.getBytes("iso8859-1"),"utf-8");
}
if(session.getAttribute("loginUser")!=null){
  userinfor=(Userinfor)session.getAttribute("loginUser");
}
String userName=userinfor!=null?userinfor.getUsername():"登录";
int agentFlag = userinfor!=null?userinfor.getAgentFlag():0;
int userType=userinfor!=null?userinfor.getUsertype():0;
int userId = userinfor!=null?userinfor.getUserid():0;
request.setAttribute("userType",userType); 
String backurl=request.getParameter("backurl");
String isagentshare=request.getParameter("isagentshare");
%>
<!doctype html>
<html class='home_page'>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="format-detection" content="telephone=no"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<title>慧英保险移动投保平台</title> 
<meta name="Keywords" content="慧英保险移动网站,慧英保险,车险报价,车险计算器、在线保险"/> 
<meta name="Description" content="慧英保险移动网站，车险计算器，在线车险、方便又省钱"/>
<link type="text/css" rel="stylesheet" href="<%=path%>/views/quicksure/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/css.css">
<link rel="stylesheet" href="<%=path%>/views/quicksure/css/LArea.css">
<script id='cityJson'></script>
<script id='citySet'></script>
<script id='Popt'></script>
<script type="text/javascript">
    var shareId="<%=shareId %>";
    var provincedata;
    var phoneNo = "<%=phoneNo %>";
    var deptAddress = "<%=deptAddress %>";
    var lcnNo ="<%=lcnNo %>" ;
    var dptcode="<%=dptcode%>";
    var userName="<%=userName%>";
    var agentFlag="<%=agentFlag%>";
    var userId ="<%=userId%>";
    var codeId ="<%=codeId%>";
    var userIdQr ="<%=userIdQr%>";
    var isagentshare ="<%=isagentshare%>";
    <%-- var passwordQr ="<%=passwordQr%>"; --%>
    var firstscreenFlag=true;
    function getUrl() {
		return "<%=path%>";
  	}
    if(userName=="null"){
    	userName="";
    }
    if(agentFlag==null){
       agentFlag=0;  
    }
    if(userId==null){
       userId=0;
    }
    if(shareId==null||shareId==""){
    	shareId=0;
    }
</script>
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/quicksure/swiper-3.4.0.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/layer.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=faeb78fb58db7f3acb8314910e5e3bd7&version=<%=nowDate %>"></script>
<script src="<%=path%>/views/quicksure/scripts/getAddress.js?version=<%=nowDate %>"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/js.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/LAreaData1.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/LAreaData2.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/LArea.js"></script>
<script src="<%=path%>/views/quicksure/scripts/common.js?version=<%=nowDate %>"></script>
<script src="<%=path%>/views/quicksure/scripts/index.js?version=<%=nowDate %>"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="<%=path%>/views/quicksure/scripts/wx_linkshare.js"></script>

<style type="text/css">
.errorpop{display:none;font-size: 1rem;
  padding-left: 4%;
  margin-top: -4%;
  }
input {
	width: 90%;
	height: 40px;
	font-size: 18px;
	border: 1px solid #b72f20;
	border-radius: 5px;
	margin: 20px 5% 0 5%;
	padding: 5px;
}
._citys { max-width: 450px; display: inline-block; border: 2px solid #eee; padding: 5px; position: relative; background:#fff;}
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
<div class="div640a">
	   <div class="top">
	      <div class="div600">
		      <table style='width:100%;background: rgba(255,255,255,0.5);'>
		         <tr>
		           <td class='top_tr1'><img src="<%=path%>/views/quicksure/images/index_logo.png" style='max-width:100%;'></td>
		           <td style=''class='top_tr'>
		            <!-- <a href="javascript:createQrcode();" class='td_qrcode' id='qrcode' style='text-align:right; position: relative;display: none'>分享二维码</a> -->
	          	   	<c:if test="${requestScope.userType!=3}" var="condition" scope="request">
	          	   	<a class="removea td_left" href="javascript:login();" ><div id="loginQr"><img src="<%=path%>/views/quicksure/images/indexa_2.png"><%=userName %></div></a></c:if>
		           	<span class='td_qrcode' id='qrcode' style='text-align:right;right:-1px; position: relative;display:none;'>分享客户
		                <ul class="share-list">
		                   <li class="share-link">分享链接</li>
		                   <li><a href="javascript:createQrcode();" style="text-decoration:none;">分享二维码</a></li>
		                </ul> 
			        </span>
		           	<a href="<%=path%>/views/quicksure/jsp/myAccount.jsp"class='td_right' style='text-align:right;'><img  src="<%=path%>/views/quicksure/images/indexa_4.png">我的订单</a>
		           </td>
		          
		         </tr>
		         <div class="clear"></div>
		      </table>
		      <div class="clear"></div>
			  <div class="sharetips">
	           <div class="shares">点击这里分享给好友</div>
	          </div>
	     </div>
	   </div>
			 <div class="content">
			           <img class="bei1" src="<%=path%>/views/quicksure/images/index-bg2.png">
			          <div class="div600">
							   <form action="<%=path%>/vehicleInfor/goToVehicleScreen.do" method="POST" name="form"  id="form" class="form_home" enctype="application/x-www-form-urlencoded" >
										   <input  style="display:none" type="text" id="dptcode" name="dptcode"/>
										   <%-- <input style="display:none" type="text" id="orderNo" name="orderNo" value="${orderNo}"/> --%>
										   <input style="display:none" type="text" id="orderNo" name="orderNo" value=""/>										  
										    <input type="hidden" id="NOflag" name="NOflag" value="${NOflag}"/>
										    <input type="hidden" id="provinceid" name="provinceid" value="${province}"/>
										    <input type="hidden" id="cityid" name="cityid" value="${city}"/>
										    <input type="hidden" id="isagentshare" name="isagentshare" value=""/>
											   <div class="con_div con_div2">
												    <%--  <p class="p1"><img src="<%=path%>/views/quicksure/images/xiu_2.png">投保城市</p>  --%>
												    <p class="p1" style="font-size: 15px; color: #333">投保城市</p>
												    <input id="demo1" name="deptAddress"   type="text" class="text4 textk " value="${insuranceDetailsVO.baseinfor.deptAddress}" placeholder="请选择对应的城市" readonly="readonly" style='position:relative;z-index:300;'/>
												    <input id="city" name="deptAddress"   type="text" class="text4 textk city" value="${insuranceDetailsVO.baseinfor.deptAddress}" placeholder="请选择对应的城市" readonly="readonly" style='display:none;position:relative;z-index:300;'/>
											        <div class="clear"></div>
							                   </div>
							                    <script id='LArea'></script>
							                    <script id='cityJson'></script>
							                    <script id='citySet'></script>
							                    <script id='Popt'></script>		            			                   
											   <div class="con_div" id="con_divl">
													    <p class="p1" style="font-size: 15px; color: #333"><%-- <img src="<%=path%>/views/quicksure/images/xiu_4.png"> --%>车牌号码</p>
													
													    <input class="text4" type="text" style="text-transform: uppercase;" placeholder="请输入车牌号码" id="lcno" value="${insuranceDetailsVO.vhlinfor.lcnno}" 
													     name="lcno" maxlength="7" onblur="setUpperCase(this);" oninput="if(value.length>7)value=value.slice(0,7)" required ='required' />
														 <div id='con_d2'class='con_d2'>未上牌<ul><li id="con_li" class='con_li'></li></ul><input type="hidden" value=''></div>
											     <div class="clear"></div>
											   </div>
			  
			                       			   <div class="con_div">	
														    <p class="p1" style="font-size: 15px; color: #333"><%-- <img src="<%=path%>/views/quicksure/images/xiu_5.png"> --%>手机号码</p>
														    <input class="text4" type="number" placeholder="请输入手机号码" name="phoneNo" id="phoneNo" <c:if test="${loginUser.usertype!=3}"> value="${loginUser.username}" </c:if> required ='required' max="11" 
															oninput="if(value.length>10)value=value.slice(0,11)"/>
														    <div class="clear"></div>
												   </div>
			   
			                                     <div id="errorMessageforPhoneNo" class='errorpop'> <lable style="color:red;">手机号输入有误！<lable></div>
				                                 <p class="con_aa"><a href="javascript:submitFrom()" style='display:block;margin:0 auto;line-height:43px;font-size:19px;'>现在报价</a></p>
				                                 <p style='text-align:center;width: 100%;max-width: 640px; color: #888; font-size: 12px;margin-top: 15%;'>欢迎访问慧英保险官网！请填写一些您的信息，以便为您报价。</p>
			                        </form>
			                 </div>
			      </div>

</div>
<div id="errorhei" class="errorhei" style="display:none;z-index: 100;">
	
		<div style="   position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
			<div style="width:85%;margin:0 auto;max-width:300px;background:#fff;color: #333; border-radius:5px;padding: 20px 0px 3px 0px;box-shadow: 0 0 20px 2px #333;">
						<h2 style='font-size: 17px;color: #222;'>提示信息</h2>
						<p style='margin-bottom: 10px;font-size: 12px;padding: 15px 20px;text-align: center; color:#444;'id="Message"></p>
						<div style='border-top:1px solid #ddd;font-size:17px;color:#333;padding: 8px 0px;'><span style='display:block;width:100%;font-size:15px' id="ensure">确定</span></div>
		    </div>
	 </div>
</div>
<div style="position:fixed;top:0px;left:0px;width:100%;height:100%;z-index: 100;display:none;"id="pop">
    <div style="position: fixed;top:35%; width:100%;height:auto;text-align:center;">
     <span style='display:inline-block;height: auto;border-radius: 5px;background: rgba(250,250,250,1);color: #888;font-size: 10px;letter-spacing: 2px; padding: 20px;'>
        <img  src="<%=path%>/views/quicksure/images/mu_loading.gif" style='width: 36px; margin-bottom: 5px;'>
        <p>正在定位，请稍等...</p>
	</span>
	 
</div>
</div>

<script type="text/javascript">   
   	
	$('.removea').click(function(e){
		if(userName!="登录"){
			e.preventDefault();
			var result = confirm("确定退出当前账户吗？");
			if(result==true){
				window.location.href=getUrl()+"/loginUser/logout.do";
				/* window.location.href=getUrl()+"/views/quicksure/jsp/quicksurehome.jsp"; */
			}else{
				return true;
			}
		}
	});
	$('#ensure').click(function(){
    $('#errorhei').hide();
    }) ;
</script>
<script>

</script>

</body>

</html>
