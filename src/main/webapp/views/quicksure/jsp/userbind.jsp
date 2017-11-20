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
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
<script src="<%=path%>/views/quicksure/scripts/jquery.1.7.2.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script src="<%=path%>/views/quicksure/scripts/userbind.js"></script>
 </head>
 <body>
  <div id='mobile_oder'>
      <div class='order_box'>
          <form action="" method="post">
           <input style="display:none" type="text" id="openId" name="openId"  value="${openId}"> 
              <ul>
                  <li class="P_num"><input type="text" id="phoneno" placeholder="请输入手机号码" maxlength="11" onKeyUp="if(value!=value.replace(/[^\d]/g,''))value=value.replace(/[^\d]/g,'')"></li>
                  <div class="regist_phonenum_warn" style="display: none"><span class="registPhonenum_text" style="color: red"></span></div>
                  <li class="mb_code"><input type="text" id="phoneCheckCode" name="mobile_code"placeholder="验证码" maxlength="6"><span onclick="getCheckCodeforbind();">获取</span></li>
              	  <li><div class="regist_phonecode_warn" style="display: none"><span class="registphonecode_text" style="color: red"></span></div></li>
              </ul>
              <div class="btn"><input type="button" onclick="userbind();" value="用户绑定"></div>
          </form>
      </div>
  </div>
  <!-- 普通弹框 -->
  <div class="errorhei1" style="display:none">
	<div style="position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
			<div style="width:85%;margin:0 auto;background:#fff;color: #333; border-radius:5px;padding: 20px 0px 3px 0px;box-shadow: 0 0 20px 2px #333;">
				<h2 style='font-size: 17px;color: #222;'>提示信息</h2>
				<p style='margin-bottom: 10px;font-size: 12px;padding: 15px 20px;text-align: center; color:#444;'id="Message"></p>
                <div style='border-top:1px solid #ddd;font-size:17px;color:#333;padding: 8px 0px;'><span style='display:block;cursor: pointer;width:100%;font-size:15px' id="ensure">确定</span></div>  
			</div>
    </div>
  </div>
  <!-- 选择是否注册弹框 -->
  <div class="errorhei2" style="display:none">
	<div style="position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
			<div style="width:85%;margin:0 auto;background:#fff;color: #333; border-radius:5px;padding: 20px 0px 3px 0px;box-shadow: 0 0 20px 2px #333;">
				<h2 style='font-size: 17px;color: #222;'>提示信息</h2>
				<p style='margin-bottom: 10px;font-size: 12px;padding: 15px 20px;text-align: center; color:#444;'id="Message1"></p>
				<div style='font-size:17px;color:#333;padding: 12px 0px;text-align:center;'>
				   <button class="ensure" id="ensure2" style="cursor: pointer;width: 63px; background: #f63c30 ;border-radius: 3px; display: inline-block;
                           height: 24px;color: #fff ; border: none;" onclick="userRegist();">是</button>&nbsp;&nbsp;
                   <button class="ensure" id="ensure1" style="cursor: pointer;width: 63px; background: #f63c30 ;border-radius: 3px; display: inline-block;
                           height: 24px;color: #fff ;border: none;">否</button>
                </div>  
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
    $('.errorhei1').hide();
   });
   $('#ensure').click(function(){
    $('.errorhei1').hide();
    }) ;
   $('#ensure1').click(function(){
    $('.errorhei2').hide();
   })
</script>

</html>