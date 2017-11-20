<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="format-detection" content="telephone=no"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
    <title>重置密码页面</title>
    <link rel="stylesheet" href="<%=path%>/views/quicksure/css/resetPassword.css">
    <script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
</head>
<body>
<div id="reset_content">
   
    <form action="" method="post">
    <div id="reset_box">
        <ul>
            <li><input type="text" id="phoneno" placeholder="请输入手机号码"  maxlength="11"></li>
            <li class="pas_word"><input type="password" id="psw" placeholder='请输入新密码'  maxlength="16"></li>
            <li class="Confir"><input type="password" id="confirmPsw" placeholder="请重复录入一遍密码" ></li>
            <li class="form_text"><input type="text" id="phoneCheckCode" placeholder="请输入验证码" name="mobileCode" maxlength="6" style='width:60%;'><span onclick="getCheckCode();">获取</span></li>
            <li> <input type="button" value="确认重置" onclick="resetPassword(); "style='width:100%; background: #e82418;height: 2.6rem;margin-top: 10px;font-size:16px;color:#fff;'></li>
        </ul>
        <!-- <input type="button" value="确认重置" onclick="resetPassword();"> -->
    </div>
    </form>
     <div class="re_top">
        <ul>
        	<!-- <li style="width:35%;padding-left:4%;"><h4></h4></li> -->
            <li class="Right"><a href="<%=path%>/views/quicksure/jsp/LoginUser.jsp" class="rg" style="text-decoration:none;">登录</a></li>
        </ul>
    </div>
</div>
<!-- <div class="errorhei" style="display:none">
	
	<div style="   position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
		<div style="width:85%;margin:0 auto;background:#fff;color: #333; border-radius:5px;padding: 20px 0px 3px 0px;box-shadow: 0 0 20px 2px #333;">
				<h2 style='font-size: 17px;color: #222;'>提示信息</h2>
				<p style='margin-bottom: 10px;font-size: 12px;padding: 15px 20px;text-align: center; color:#444;'id="Message"></p>
				<div style='border-top:1px solid #ddd;font-size:17px;color:#333;padding: 8px 0px;'><span style='display:block;width:100%;font-size:15px' id="ensure">确定</span></div>
		</div>
   </div>
</div> -->
	<div class="errorhei" style="display:none">
		<div style="   position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
			<div style="width:85%;max-width:300px;margin:0 auto;background:#fff;max-width:300px;color: #333; border-radius:5px;padding: 20px 0px 3px 0px;box-shadow: 0 0 20px 2px #333;">
					<h2 style='font-size: 17px;color: #222;font-weight: normal;'>提示信息</h2>
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
<script src="<%=path%>/views/quicksure/scripts/jquery.1.7.2.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script src="<%=path%>/views/quicksure/scripts/resetPassword.js"></script>
<script type="text/javascript">
	$('#ensure').click(function(){
    $('.errorhei').hide();
    }) ;

</script>
</html>