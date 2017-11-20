<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String action = request.getParameter("action"); //定义一个action监视登录前的动作，登录之后返回到之前的动作去
String phoneNo=request.getParameter("phoneNo")==null?"":request.getParameter("phoneNo");
String deptAddress=request.getParameter("deptAddress")==null?"":request.getParameter("deptAddress"); //这个是首页填完信息再去登录的时候带过来的
String address = request.getParameter("address")==null?"":request.getParameter("address"); //这个地址是首页定位之后带过来的
/* 非车首页需要提交的字段 */
String prodno = request.getParameter("prodno")==null?"":request.getParameter("prodno"); //非车产品代码
String prodtype = request.getParameter("prodtype")==null?"":request.getParameter("prodtype"); //非车产品类型
String sumamount = request.getParameter("sumamount")==null?"":request.getParameter("sumamount"); //非车总保额
String sumpremium = request.getParameter("sumpremium")==null?"":request.getParameter("sumpremium"); //非车总保费
String insrncPeriod = request.getParameter("insrncPeriod")==null?"":request.getParameter("insrncPeriod"); //非车投保期限

if(address!=null){
	address=new String(address.getBytes("iso8859-1"),"utf-8");
}
deptAddress = deptAddress=="" || deptAddress==null?address:deptAddress; //上面两个地址哪个不为空就用哪个，都为空的话就为空
String lcnNo=request.getParameter("lcno");
String chepai = request.getParameter("chepai");
if(chepai!=null){
	chepai=new String(chepai.getBytes("iso8859-1"),"utf-8");
}
lcnNo = lcnNo==""||lcnNo==null?chepai:lcnNo;

String dptcode=request.getParameter("dptcode");

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
    <title>会员登录</title>
    <link rel="stylesheet" href="<%=path%>/views/quicksure/css/menber.css">
    <script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
    <script type="text/javascript">
    var action = "<%=action%>";
	var phoneNo = "<%=phoneNo %>";
    var address = "<%=address %>";
    var deptAddress = "<%=deptAddress %>";
    var chepai = "<%=chepai %>";
    var lcnNo = "<%=lcnNo %>";
    var dptcode= "<%=dptcode %>";
    /* 非车字段 */
    var prodno = "<%=prodno %>";
    var prodtype = "<%=prodtype %>";
    var sumamount = "<%=sumamount %>";
    var sumpremium = "<%=sumpremium %>";
    var insrncPeriod = "<%=insrncPeriod %>";
    if(action=="null"){
		action="";
	}
	if(lcnNo=="null"){
		lcnNo="";
	}
	if(deptAddress=="null"){
		deptAddress="";
	}
	if(chepai=="null"){
		chepai="";
	}
	if(address=="null"){
		address="";
	}
	
	
	
</script>
</head>

<body>
<div style='padding:20px;max-width:640px;margin:0 auto;'>

<div id="member_page">
    
     <div  class="selet_psw">
        <ul>
            <li class="hover">动态密码</li>
            <li>会员密码</li>
        </ul>
    </div>
    <div class="member_box">
        <div class="dongtai_box act">
            <form action="" method="post" onsubmit='return Check()'>
            	<input type="hidden" id="checkCode">
                <ul>
                    <li><input type="text" id="phoneno" placeholder="请输入手机号" class='phone'  maxlength="11" onKeyUp="if(value!=value.replace(/[^\d]/g,''))value=value.replace(/[^\d]/g,'')"></li>
                    <div class="login_phoneno_warn" style="display: none"><span class="warnPhoneno_text" style="color: red"></span></div>
                    <li><input type="text" id="phoneCheckCode" value=""placeholder="请输入验证码" maxlength="6"><span onclick="getCheckCode();" >获取</span></li>
                	<div class="login_code_warn" style="display: none"><span class="warnCode_text" style="color: red"></span></div>
                </ul>
				<div class="storage_Tel">
					<ul>
						<li><input type="checkbox" class="remenber" />记住手机号码</li>
						<li><a href="<%=path%>/views/quicksure/jsp/resetPassword.jsp" style="text-decoration:none;">忘记密码？</a></li>
					</ul>	
		       </div>
                <input type="button" value="登录" onclick="userLogin(1);">
            </form>
        </div>
        <div class="user_box">
            <form action="" method="post" onsubmit='return Check()'>
                <ul>
                    <li><input type="text" id="phonenum" placeholder="请输入手机号" class='phone' maxlength="11"></li>
                    <div class="login_phonenum_warn" style="display: none"><span class="warnPhonenum_text" style="color: red"></span></div>
                    <li><input type="password" id="psw" placeholder="请输入密码" ></li>
                    <div class="login_codenum_warn" style="display: none"><span class="warnCodenum_text" style="color: red"></span></div>
                </ul>
				<div class="storage_Tel">
					<ul>
						<li><input type="checkbox" class="remenber" />记住手机号码</li>
						<li><a href="<%=path%>/views/quicksure/jsp/resetPassword.jsp" style="text-decoration:none;">忘记密码？</a></li>
					</ul>	
		       </div>
                <input type="button" value="登录" onclick="userLogin(2);">
            </form>
        </div>
		
    </div>

    </div>
    <div class="head_top" style='margin:0 5px'>
		 <ul style='float:left;width:100%;margin-top:20px;'>
		      <%--  <li><a href="<%=path%>/views/quicksure/jsp/resetPassword.jsp" style="text-decoration:none;">忘记密码？</a></li> --%>
		       <li class='Right'><a href="javascript:regist();" style="text-decoration:none;">立即注册</a></li>
		   	</ul> 
    </div>
</div>

	<div class="errorhei" style="display:none">
		
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
	     <span style='display:inline-block;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 10px;letter-spacing: 2px; padding: 20px;'>
	        <img  src="<%=path%>/views/quicksure/images/mu_loading.gif" style='width: 36px; margin-bottom: 5px;'>
	        <p id="prompt"></p>
		</span>
	</div>
</div>

</body>
<script src="<%=path%>/views/quicksure/scripts/jquery.1.7.2.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script src="<%=path%>/views/quicksure/scripts/loginUser.js"></script>
<script type="text/javascript">
	$('#ensure').click(function(){
    $('.errorhei').hide();
    }) ;
</script>
</html>