$(document).ready(function() {
	$("#phoneno").focus();
	$("#pop").hide();
});

var checkphoneNoFlag=false;//第一次加载默认值是false,所以设为true
var checkpswFlag=false;
var checkconfirmFlag=false;
var checkcodeflag=false;

var _interval;

function getCheckCode(){
	var username=$('#phoneno').val();
	checkphone();
	if(checkphoneNoFlag){//一步一个验证
		$.ajax({
			type: "POST",  
			url: getUrl()+"/loginUser/userEverRegist.do", 
			data:{username:username},
	        success : function(data){
	        	if(data=="1"){
	        		checkpsw();
	        		if(checkpswFlag){
	        			checkconfirm();
	        			if(checkconfirmFlag){
	        				var num=60;
	        				var Span=document.getElementById('reset_box').getElementsByTagName('span')[0];
	        				
	        				Span.setAttribute('class','Color ');
	        				console.log(num);
	        				    _interval=setInterval( function time(){
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
	        				   clearInterval(_interval);
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
	        	}else{//用户不存在
//	        		checkphoneNoFlag=false; 
	        		$("#Message").html("用户不存在！");
	    			$(".errorhei").show();
	    			//Hide();
	        		$('#phoneno').focus();
	        	}
	        }
		});
	}
}

function resetPassword(){
	var username=$('#phoneno').val();
	checkphone();
	if(checkphoneNoFlag){
		$.ajax({
			type: "POST",  
			url: getUrl()+"/loginUser/userEverRegist.do", 
			data:{username:username},
	        success : function(data){
	        	if(data=="1"){
//	        		checkphoneNoFlag=true;
	        		checkpsw();
	        		if(checkpswFlag){
	        			checkconfirm();
	        			if(checkconfirmFlag){
	        				checkphoneCode();
	        				if(checkcodeflag){
	        					var phoneno=$("#phoneno").val();
	        					var password=$("#psw").val();
	        					var phoneCheckCode=$("#phoneCheckCode").val();
	        					$.ajax({
	        						   url : getUrl()+"/loginUser/checkPhoneCode.do",
	        				           async: false ,
	        						   data: {checkCode:phoneCheckCode,phoneno:phoneno},
	        						   success : function(result){
	        							   if(result=="true"){//手机验证码校验成功
	        								    $("#prompt").html("正在重置，请稍等...");
	        		        					load(15000);
	        							   		$.ajax({
	        										url : getUrl()+"/loginUser/resetPassword.do",
	        										async: false ,
	        										data: {phoneno:phoneno,password:password},
	        										success : function(result){
	        											$("#pop").hide();
	        											if(result==""){
	        												clearInterval(_interval);
	        												$('#reset_box span').eq(0).html('获取验证码').removeClass('Color');
	        												$("#Message").html("密码重置成功.");
	        							            		$(".errorhei").show();
	        							            		//Hide();
	        							            		window.location.href = getUrl()+"/views/quicksure/jsp/LoginUser.jsp";
	        											}else{
	        										        $("#Message").html("密码重置失败！");
	        							            		$(".errorhei").show();
	        							            		//Hide();
	        											}
	        										}
	        									});	
	        							   }
	        							   else{
	        								   $("#Message").html("验证码输入有误！");
	        								   $(".errorhei").show();
	        				            	   //Hide();
	        							       $("#phoneCheckCode").focus();
	        							   }
	        						   }
	        				    });
	        				}	
	        			}
	        		}
	        	}else{//用户不存在
	        		checkphoneNoFlag=false; 
	        		$("#Message").html("用户不存在！");
	    			$(".errorhei").show();
	    			//Hide();
	        		$('#phoneno').focus();
	        	}
	        }
	   	});
	}
}

function checkphone() {
	var phone=$('#phoneno').val();
	if(phone==null||"undefined"==typeof phone||""==phone){
		checkphoneNoFlag=false;
		$("#Message").html("请输入手机号！");
		$(".errorhei").show();
		//Hide();
		$('#phoneno').focus();
	}else{
		if (!(/^1[34578]\d{9}$/.test(phone))) {
			checkphoneNoFlag=false;
			$("#Message").html("手机号输入有误！");
			$(".errorhei").show();
			//Hide();
			$('#phoneno').focus();
		}else{
			checkphoneNoFlag=true;
		}
	}
}

function checkpsw(){
//	checkphone();
//	if(checkphoneNoFlag){//先验证手机
		var pass=$('#psw').val();
		if(pass==null||"undefined"==typeof pass||""==pass){//先验证密码是否为空
			checkpswFlag=false;
			$("#Message").html("请输入密码！");
			$(".errorhei").show();
			//Hide();
			$("#psw").focus();
		}else{
			if (!(/^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,20}$/.test(pass))) {
				checkpswFlag=false;
				$("#Message").html("密码格式有误！");
				$(".errorhei").show();
				//Hide();
				$("#psw").focus();
			}else{
				checkpswFlag=true;
			}
		}
//	}
}

function checkconfirm(){
//	checkpsw();
//	if(checkpswFlag){
		var pass=$('#psw').val();
		var confirmPsw=$('#confirmPsw').val();
		if(confirmPsw==null||"undefined"==typeof confirmPsw||""==confirmPsw){//先验证再次输入密码是否为空
			checkconfirmFlag=false;
			$("#Message").html("请再次输入密码!");
			$(".errorhei").show();
			//Hide();
			$("#confirmPsw").focus();
		}else{
			if(pass!=confirmPsw){
				checkconfirmFlag=false;
				$("#Message").html("两次输入密码不一致!");
				$(".errorhei").show();
				//Hide();
				$("#confirmPsw").focus();
			}else{
				checkconfirmFlag=true;
			}
		}
//	}
}

function checkphoneCode(){
	var code=$('#phoneCheckCode').val();
	var reg=/^\d{6}$/;
	if(code==null||"undefined"==typeof code||""==code){
		checkcodeflag=false;
		$("#Message").html("请输入验证码！");
		$(".errorhei").show();
	//	Hide();
		$("#phoneCheckCode").focus();
	}else{
		if(!reg.test(code)){
			checkcodeflag=false;
			$("#Message").html("验证码输入有误！");
			$(".errorhei").show();
			//Hide();
			$("#phoneCheckCode").focus();
		}else{
			checkcodeflag=true;
		}
	}
}

$('.errortan3').click(function(){
    $('.errorhei').hide();
});

/*function Hide(){
	setTimeout(function(){$('.errorhei').hide()
		},1000);
}
*/
function load(outtimes){
	$("#pop").show();
	setTimeout(function(){$('#pop').hide()
	},outtimes);
}