<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String address = request.getParameter("address");
if(address!=null){
	address=new String(address.getBytes("iso8859-1"),"utf-8");
}
String chepai = request.getParameter("chepai");
if(chepai!=null){
	chepai=new String(chepai.getBytes("iso8859-1"),"utf-8");
}
String dptcode=request.getParameter("dptcode");
String phoneNo = request.getParameter("phoneNo");
%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="format-detection" content="telephone=no"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
  <title>会员注册</title>
  <link rel="stylesheet" href="<%=path%>/views/quicksure/css/regist.css"/>
<script type="text/javascript">
	var address= "<%=address %>";
	var chepai= "<%=chepai %>";
	var dptcode= "<%=dptcode %>";
	var phoneNo = "<%=phoneNo %>";
	if(chepai=="null"){
		chepai="";
	}
	if(address=="null"){
		address="";
	}
	if(phoneNo=="null"){
		phoneNo="";
	}
</script>
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
<script src="<%=path%>/views/quicksure/scripts/jquery.1.7.2.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script src="<%=path%>/views/quicksure/scripts/registUser.js"></script>
 </head>
 <body>
  <div id='mobile_oder'>
	  
      <div class='order_box'>
          <form action="" method="post">
           <input style="display:none" type="text" id="openId" name="openId"  value="${openId}"> 
              <ul>
                  <li class="P_num"><input type="text" id="phoneno" name="phoneNo" placeholder="请输入手机号码" maxlength="11" onKeyUp="if(value!=value.replace(/[^\d]/g,''))value=value.replace(/[^\d]/g,'')"></li>
                  <div class="regist_phonenum_warn" style="display: none"><span class="registPhonenum_text" style="color: red"></span></div>
                  <li><input type="password" id="psw" placeholder="密码长度大于6位的字符+数字等不同组合"  maxlength="16"/></li>
                  <div class="regist_password_warn" style="display: none"><span class="registPassword_text" style="color: red"></span></div>
                  <li><input type="password" id="Confirm_psw" placeholder="请重复录入一遍密码"></li>
                  <div class="regist_confirmpsw_warn" style="display: none"><span class="registconfirmpsw_text" style="color: red"></span></div>
                  <p style="margin-top: 10px;margin-bottom: 10px;">
                  <span style="font-size: 16px;">是否为代理人：<input type="radio" id="isAgent" name="isAgent" value="1">是&nbsp;&nbsp;&nbsp;<input type="radio" id="isAgent" name="isAgent" value="0"  checked="checked">不是</span>
                  <li id="referral" style="display: none;margin-top: 0px;"><input type="text" id="referralCode" placeholder="输入代理人识别码"/></li>
                  <div id ="errorAgentCode" class='errorpop' style="display: none;"><lable style="color:red;">识别码错误！<lable></div>
                  </p>
                  <li class="mb_code"><input type="text" id="phoneCheckCode" name="mobile_code"placeholder="验证码" maxlength="6"><span onclick="getCheckCode();">获取</span></li>
              	  <li><div class="regist_phonecode_warn" style="display: none"><span class="registphonecode_text" style="color: red"></span></div></li>
              </ul>
              <div class="btn"><input type="button" onclick="userRegist();" value="注册"></div>
          </form>
      </div>
       <div class="head_top">
	       <ul style="float:left;width:100%;margin-top:60px;">
	       	<li><h4></h4></li>
	       	<li class='Right' style="margin-left: 47%">已有账户？直接<a href="javascript:login();" style="text-decoration:none;">登录</a></li>
	       </ul>
	   </div>
  </div>
  
  <div class="errorhei" style="display:none">
	
	<div style="   position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
			<div style="width:85%;margin:0 auto;background:#fff;color: #333; border-radius:5px;padding: 20px 0px 3px 0px;box-shadow: 0 0 20px 2px #333;">
				<h2 style='font-size: 17px;color: #222;'>提示信息</h2>
				<p style='margin-bottom: 10px;font-size: 12px;padding: 15px 20px;text-align: center; color:#444;'id="Message"></p>
				<div style='border-top:1px solid #ddd;font-size:17px;color:#333;padding: 8px 0px;'><span style='display:block;width:100%;font-size:15px' id="ensure">确定</span></div>
			</div>
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
</body>
<script type="text/javascript">
   $('.errortan3').click(function(){
    $('.errorhei').hide();
   });
   $('#ensure').click(function(){
    $('.errorhei').hide();
    }) ;
</script>

</html>