<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
  <title>用户绑定</title>
  <link rel="stylesheet" href="<%=path%>/views/quicksure/css/regist.css"/>
  <script src="<%=path%>/views/quicksure/scripts/jquery.1.7.2.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script src="<%=path%>/views/quicksure/scripts/registUser.js"></script>
 </head>
 <body>
  <div id='mobile_oder'>
      <div class='order_box'>
          <form action="" method="post">
             <input style="display:none" type="text" id="openId" name="openId"  value=”${openId} }“ > 
              <ul>
                  <li class="P_num"><input type="text" id="phoneno" placeholder="请输入手机号码" onblur="checkphoneforbind()" maxlength="11"></li>
                  <div id ="errorPhonenum" class='errorpop' style="display: none;"><lable style="color:red;">手机号输入有误！</lable></div>
                  <div id ="nullPhonenum" class='errorpop' style="display: none;"><lable style="color:red;">请输入手机号！</lable></div>               
                  <li class="mb_code"><input type="text" id="phoneCheckCode" name="mobile_code"placeholder="验证码" onblur="checkphoneCode()" maxlength="6"><span id="get_codenum" onclick="getCheckCodeforbind();">获取</span></li>
              	  <div id ="errorPhoneCode" class='errorpop' style="display: none;"><lable style="color:red;">验证码输入有误！<lable></div>
              </ul>
 				  <!-- <div id ="errorRegistUser" class='errorpop' style="display: none;"><lable style="color:red;">用户绑定失败！<lable></div> -->
              <div class="btn"><input type="button" onclick="bindUser()" value="用户绑定"></div>
          </form>
      </div>
  </div>
  
  <div class="errorhei" style="display:none">
	<div class="errortan">
		<p class="errortan1">信息提示</p>
		<p class="errortan2"><div id="chackMess"></div></p>
		<a class="errortan3" href="javascript:void(0);">确 定</a>
	</div>
    </div>
 </body>
<script type="text/javascript">
   $('.errortan3').click(function(){
    $('.errorhei').hide();
   });
</script>

</html>