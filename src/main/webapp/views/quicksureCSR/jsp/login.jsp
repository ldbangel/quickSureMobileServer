<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>登录</title>
  <link rel="stylesheet" href="<%=path%>/views/quicksureCSR/css/style.css">
  <script src="<%=path%>/views/quicksure/scripts/jquery.1.7.2.min.js"></script>
  <script src="<%=path%>/views/quicksure/scripts/common.js"></script>
  
  <body>
    <div class="login-container">
      <form action="<%=path%>/csrLoginUser/submitUnderwriting.do" method="post" id="loginForm">
        <h1>惠英车险登录平台</h1>
        <div>
          <input type="text" name="username" id="username" class="username" placeholder="用户名" maxlength="11">
        </div>
          <div class="login_phone_warn" style="display: none"><span class="warnPhone_text"></span></div>	
        <div>
          <input type="password" name="password" id="password" class="password" placeholder="密码" maxlength="16">
        </div>
          <div class="login_password_warn" style="display: none"><span class="warnPassword_text"></span></div>
        <div class="cell vcode" style='width: 302px;margin: 0 auto;'>
          <input type="text" name="code" id="phoneCheckCode" class="text" placeholder="验证码" maxlength="4">
          <img id="imgVerify" src="" alt="点击更换验证码" width="112" height="36" onclick="getVerify(this);" style='margin-top: 26px;float: right;'>
        </div>
        <div class="login_form_warn" style="display: none"><span class="warn_text"></span></div>
        <button id="submit" type="button" onclick="userLogin();">登 录</button>
        <a href="register.html">
      </form>
    </div>
    
    <div style="position:fixed; bottom:20px;width:100%;text-align:center;margin:0 auto;font:normal 12px/18px 'MicroSoft YaHei';color: #fff;">
      <!-- <p style="margin-bottom:5px;">
        <a href="index.html" style="color: #fff;">首&nbsp;&nbsp;页</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="prdouct1.html" style="color: #fff;">产品介绍1</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="prdouct2.html" style="color: #fff;">产品介绍2</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="prdouct3.html" style="color: #fff;">产品介绍3</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="lipei.html" style="color: #fff;">理&nbsp;&nbsp;赔</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="aboutus.html" style="color: #fff;">关于我们</a>
      </p> -->
      <p>技术支持：玉芦笛（深圳）咨询有限公司</p>
    </div>
	
  <!-- <div class="errorhei" style="display:none">
	<div class="errortan">
		<p class="errortan1">信息提示</p>
		<p class="errortan2"><div id="Message">很强势</div></p>
		<a class="errortan3" href="javascript:void(0);">确 定</a>
	</div>
  </div> -->
  <div class="errorhei" style="display:none">
	<div style="   position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
		<span id="Message" style='display:inline-block;font-style: italic;z-index: 100;width: auto;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 13px;letter-spacing: 2px;    padding: 20px;text-align: center; '></span>
	</div>
  </div>
	

    <script src="<%=path%>/views/quicksureCSR/js/jquery.min.js"></script>
    <script src="<%=path%>/views/quicksureCSR/js/common.js"></script>
    <script src="<%=path%>/views/quicksureCSR/js/supersized.3.2.7.min.js"></script>
    <script src="<%=path%>/views/quicksureCSR/js/supersized-init.js"></script>
    <script src="<%=path%>/views/quicksureCSR/js/jquery.validate.min.js?var1.14.0"></script>
    <script type="text/javascript">
    	$(document.body).ready(function () {
		    //首次获取验证码
		    $("#imgVerify").attr("src",getUrl()+"/loginUser/getVerify.do?"+Math.random());
		});
    	
    	var checkphoneNoFlag=false;
		var checkpswFlag=false;
		    	
    	//获取验证码
		function getVerify(obj){
		    obj.src = getUrl()+"/loginUser/getVerify.do?"+Math.random();
		}
		
    	function userLogin(){
			var username=$("#username").val();
			var password=$("#password").val();
			checkphone();
			if(checkphoneNoFlag){
				checkPassword();//直接验证密码(包含手机验证)
				if(checkpswFlag){
					verifyCodeCheck();
			    	if(checkVerifyCodeFlag){
			    		var inputStr = $("#phoneCheckCode").val();
						inputStr = inputStr.toUpperCase();//将输入的字母全部转换成大写
						$.ajax({
				            url : getUrl()+"/loginUser/checkVerify.do",
				            data: {inputStr:inputStr},
				            success : function(datas) {
				                if(datas == "TURE"){
				                    $.ajax({
						    			url : getUrl()+"/csrLoginUser/userLogin.do",
										async: false ,
										type:"POST",
										data: {
											username:username,
											password:password,
											},
						    			success : function(result){
						    				if(result=="2"){
						    					$('#Message').html("用户名或密码输入错误！");
									    		$('.errorhei').show();
						    					Hide();
						    					$("#username").focus();
											}else{
												window.location.href = getUrl()+"/views/quicksureCSR/jsp/batch_search.jsp";
											}
						    			}
						    		});
				                    
				                }else{
				                    $('#Message').html("校验码输入错误！");
									$('.errorhei').show();
									Hide();
									$("#phoneCheckCode").focus();
				                }
				            }
				        });
			    	}
				}
			}
    	}
		    	
    	function checkphone(){
    		$(".login_phone_warn").css("display","none");
    		var phone=$("#username").val();
			if(phone==null||"undefined"==phone||""==phone){
		        $('#Message').html("请输入手机号！");
				$('.errorhei').show();
				Hide();
				$("#username").focus();
			}else{
				if(!(/^1[34578]\d{9}$/.test(phone))){
					checkphoneNoFlag=false;
		            $('#Message').html("手机号输入有误！");
					$('.errorhei').show();
					Hide();
					$("#username").focus();
				}else{
					checkphoneNoFlag=true;
				}
			}
    	}
    	
    	function checkPassword(){
    		checkphone();
    		if(checkphoneNoFlag){
    			var password=$("#password").val();
				if(password==null||"undefined"==typeof password||""==password){
					checkpswFlag=false;
					$('#Message').html("请输入密码！");
					$('.errorhei').show();
					Hide();
					$("#password").focus();
				}else{
					if (!(/^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,20}$/.test(password))) {
						checkpswFlag=false;
						$("#Message").html("密码输入有误！");
						$(".errorhei").show();
						Hide();
						$("#psw").focus();
					}else{
					    checkpswFlag=true;
					}
				}
    		}
    	}
    	
    	function verifyCodeCheck(){
    		checkPassword();
    		if(checkpswFlag){
    			var phoneCheckCode=$("#phoneCheckCode").val();
				if(phoneCheckCode==null||"undefined"==typeof phoneCheckCode||""==phoneCheckCode){
					checkVerifyCodeFlag=false;
					$('#Message').html("请输入校验码！");
					$('.errorhei').show();
					Hide();
					$("#phoneCheckCode").focus();
				}else{
					/* if(!reg.test(phoneCheckCode)){
						checkVerifyCodeFlag=false;
						$('#Message').html("校验码输入有误！");
						$('.errorhei').show();
						Hide();
						$("#phoneCheckCode").focus();
					}else{ */
						checkVerifyCodeFlag=true;
					/* } */
				}
    		}
    	}
    	
    	function getCheckCode(){
			checkphone(1);
			if(checkphoneNoFlag){
				var num=60;
			    var Span=document.getElementsByClassName('cell vcode ')[0].getElementsByTagName('a')[0];
		
			    Span.setAttribute('class','Color ');
			    console.log(num);
			    var time=setInterval( function time(){
			        Span.innerHTML=' ';
			        num--;
			        Span.innerHTML=num+'s';
			        if(num<=0){
			            Span.removeAttribute("class");
			            Span.innerHTML='获取验证码';
			           clear();
			        }
			    },1000);
				 function clear(){
				     clearInterval(time);
				 }
		
				var phoneno=$("#phoneno").val();
				$.ajax({
					url : getUrl()+"/loginUser/phoneCheck.do",
					async: false ,
					data: {phoneno:phoneno},
					success : function(generation){
					}
				});
			}
		}
		
		$('.errortan3').click(function(){
    		$('.errorhei').hide();
		});
		
		function Hide(){
			setTimeout(function(){$('.errorhei').hide();
			},1000);
		}
    	
    </script>
  </body>

</html>