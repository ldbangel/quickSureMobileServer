$(document).ready(function() {
	$("#phoneno").focus();
	$("#pop").hide();
});

var checkphoneNoFlag=false;
var checkpswFlag=false;
var checkconfirmFlag=false;
var checkcodeflag=false;
var userExist=false;
var validatecodeFlag=false;

var _interval;

function getCheckCode(){
	var username=$('#phoneno').val();
	checkphone();
	if(checkphoneNoFlag){
		$.ajax({
			type: "POST",  
			url: getUrl()+"/loginUser/userEverRegist.do", 
//			data: JSON.stringify(phone),//将对象序列化成JSON字符串  
			data:{username:username},
//			dataType:"json",  
//			contentType : 'application/json;charset=utf-8', //设置请求头信息  			
	        success : function(data){
	        	if(data=="1"){//用户已存在
	        		$('#Message').html("用户已存在！");
	    	    	$('.errorhei').show();
	    	    	//Hide();
	        		checkphoneNoFlag=false;
	        		$('#phoneno').focus();
	        	}else{
//	        		checkphoneNoFlag=true;
	        		if(checkphoneNoFlag){
	        			checkpswtest();
	        			checkconfirm();
	        			if(checkpswFlag&&checkconfirmFlag){
	        				var num=60;
	        				var Span=document.getElementsByClassName('order_box')[0].getElementsByTagName('span')[4];
	        				
	        				Span.setAttribute('class','Color ');
	        				console.log(num);
	        					_interval=setInterval( function time(){
	        					Span.innerHTML=' ';
	        				    num--;
	        				    Span.innerHTML=num+'s';
	        				    if(num<=0){
	        				        Span.removeAttribute("class");
	        				        Span.innerHTML='获取';
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
	        	}
	        }
	   	});
	}
}
//绑定手机号，获取验证码
function getCheckCodeforbind(){
	checkphone();
	if(checkphoneNoFlag){
		var num=60;
		var Span=document.getElementsByClassName('order_box')[0].getElementsByTagName('span')[4];
		
		Span.setAttribute('class','Color ');
			_interval=setInterval( function time(){
			Span.innerHTML=' ';
		    num--;
		    Span.innerHTML=num+'s';
		    if(num<=0){
		        Span.removeAttribute("class");
		        Span.innerHTML='获取';
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
		
//是否为代理人:1是 0否
var isAgent=3;
//是否代理人注册，是显示代理人注册码
function getagent(){
//   $('#errorAgentCode').hide();
//	checkphone();
	$("input[type=radio]").val("1");
	var valCheck=$('input:radio[name="isAgent"]:checked').val();
	//if(checkphoneNoFlag&&valCheck=='1'){
//		$("#").attr("checked","checked");
//		$("input[@type=radio][name=sex][@value=0]").attr("checked",true);
		//$("input[type=radio]").attr("checked",'0');
		$('#referral').css("display", "block");
	    isAgent=1;
		$('#errorAgentCode').css("display", "none");
	//}
}

function getagent1(){
	$('#referral').hide();
   $('#errorAgentCode').hide();
   $("input[type=radio]").val("0");
//   $('#referralCode').val("");
   isAgent=0;
   
}
function userRegist(){
	var phoneCheckCode=$("#phoneCheckCode").val();
	var username=$("#phoneno").val();
	var password=$("#psw").val();
	var password2=$("#Confirm_psw").val();
//	var referralCode = $("#referralCode").val();
	var referralCode = $('input[name="isAgent"]:checked').val();
	
	checkphone();
	
	if(checkphoneNoFlag){
		$.ajax({
			type: "POST",  
			url: getUrl()+"/loginUser/userEverRegist.do", 
//			data: JSON.stringify(phone),//将对象序列化成JSON字符串  
			data:{username:username},
//			dataType:"json",  
//			contentType : 'application/json;charset=utf-8', //设置请求头信息  			
	        success : function(data){
	        	if(data=="1"){//用户已存在
	        		$('#Message').html("用户已存在！");
	    	    	$('.errorhei').show();
	    	    	//Hide();
	        		checkphoneNoFlag=false;
//	        		$('#phoneno').focus();
	        	}else{
//	        		checkphoneNoFlag=true;
	        		checkpswtest();
	    			if(checkpswFlag){
	    				checkconfirm();
	    				if(checkconfirmFlag){
//	    					var valCheck = $('input:radio[name="isAgent"]:checked').val();
//	    					if(valCheck=='1'){
//	    						validateRecode();
//	    					}
//	    					if((valCheck=='1'&&validatecodeFlag)||valCheck=='0'){
	    						checkphoneCode();
	    						if(checkcodeflag){
	    							$.ajax({
	    								   url : getUrl()+"/loginUser/checkPhoneCode.do",
	    						           async: false ,
	    								   data: {checkCode:phoneCheckCode,phoneno:username},
	    								   success : function(result){
	    									   if(result=="true"){//手机验证码校验成功
	    										    $("#prompt").html("正在注册，请稍等...");
	    			    							load(15000);
	    									   		$.ajax({
	    								            url : getUrl()+"/loginUser/registUser.do",
	    								            async: false ,
	    								            data: {username:username,password:password,agentCode:referralCode},
	    								            success : function(result){
	    								            	$("#pop").hide();
	    								            	if(result=="1"){
	    								            		$('#Message').html("用户注册失败！");
	    								        	    	$('.errorhei').show();
	    								        	    	//Hide();
	    								            	}else if(result=="2"){
	    								            		$('#Message').html("用户已存在！");
	    								        	    	$('.errorhei').show();
	    								        	    	//Hide();
	    								        	    	$("#phoneno").focus();
	    								            	}else{
	    								            		var  openId=$("#openId").val();
	    								            		clearInterval(_interval);
	    													$('.order_box span').eq(4).html('获取').removeClass('Color');							            		
	    								            		if(openId!=null&&""!==openId&&"undefined"!==openId){
	    								            			bindUser();
	    								            		}else{
	    								            			$("#Message").html("用户注册成功.");
	    									            		$(".errorhei").show();
	    								            		   setTimeout(function(){
	    								            			$('.errorhei').hide();
	    								            			window.location.href = getUrl()+"/views/quicksure/jsp/LoginUser.jsp?address="+address+"&chepai="+chepai;
	    								            			},1000);
	    								            		}
	    													    
	    								            	};
	    								            },
	    								            error:function(res){
	    								            	$("#pop").hide();
	    								            	$('#Message').html("注册失败！");
    								        	    	$('.errorhei').show();
    								        	    	//Hide();
	    								            }
	    						               		});	
	    									   }else{
	    										   	$("#Message").html("验证码输入有误！");
	    						            		$(".errorhei").show();
	    						            		//Hide();
	    						            		$("#phoneCheckCode").focus();
	    									   };
	    								   }
	    						     });
	    						}
//	    					}
	    			}
	    		}
	        }
	        }
	   	});	
	}
}


function checkphone() {
	var phone=$('#phoneno').val();
	if(phone==null||"undefined"==typeof phone||""==phone){
		checkphoneNoFlag=false;
		$('#Message').html("请输入手机号！");
    	$('.errorhei').show();
    	//Hide();
		$('#phoneno').focus();
	}else{
		if (!(/^1[34578]\d{9}$/.test(phone))) {
			checkphoneNoFlag=false;
			$('#Message').html("手机号输入有误！");
	    	$('.errorhei').show();
	    	//Hide();
			$('#phoneno').focus();
		}else{
			checkphoneNoFlag=true;
		}
	}
}

function checkphoneforbind() {
	var phone=$('#phoneno').val();
	if(phone==null||"undefined"==typeof phone||""==phone){
		$('#Message').html("请输入手机号！");
    	$('.errorhei').show();
		$("#phoneno").focus();
	}else{
		if (!(/^1[34578]\d{9}$/.test(phone))) {
			checkphoneNoFlag=false;
			$('#Message').html("手机号输入有误！");
	    	$('.errorhei').show();
			$("#phoneno").focus();
		}else{
			$(".regist_phonenum_warn").css("display","none");
			checkphoneNoFlag=true;
		 }
	}
	
}

function checkpswtest(){
//	checkphone();
//	if(checkphoneNoFlag){
		var pass=$('#psw').val();
		if(pass==null||"undefined"==typeof pass||""==pass){
			checkpswFlag=false;
			$('#Message').html("请输入密码！");
	    	$('.errorhei').show();
	    	//Hide();
	    	$("#psw").focus();
		}else{
			if (!/^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,20}$/.test(pass)) {
				checkpswFlag=false;
				$('#Message').html("密码格式有误！");
		    	$('.errorhei').show();
		    	//Hide();
				$("#psw").focus();
			}else{
				checkpswFlag=true;
			}
		}
//	}
}

function checkconfirm(){
//	checkpswtest();
//	if(checkpswFlag){
		var pass=$('#psw').val();
		var confirmPsw=$('#Confirm_psw').val();
		if(confirmPsw==null||"undefined"==typeof confirmPsw||""==confirmPsw){//先验证再次输入密码是否为空
			checkconfirmFlag=false;
			$('#Message').html("请再次输入密码!");
	    	$('.errorhei').show();
	    //	Hide();
	    	$("#Confirm_psw").focus();
		}else{
			if(pass!=confirmPsw){
				checkconfirmFlag=false;
				$('#Message').html("两次输入密码不一致！");
		    	$('.errorhei').show();
		    	//Hide();
				$("#Confirm_psw").focus();
			}else{
				checkconfirmFlag=true;
			}
		}
//	}
}

function checkphoneCode(){
	var code=$('#phoneCheckCode').val();
	var reg=/^\d{6}$/;
//	validateRecode();
		if(code==null||"undefined"==typeof code||""==code){
			checkcodeflag=false;
			$('#Message').html("请输入验证码！");
	    	$('.errorhei').show();
	    	//Hide();
	    	$("#phoneCheckCode").focus();
		}else{
			if(!reg.test(code)){
				checkcodeflag=false;
				$('#Message').html("验证码输入有误！");
		    	$('.errorhei').show();
		    	//Hide();
		    	$("#phoneCheckCode").focus();
			}else{
				checkcodeflag=true;
			}
		}
}

function remove_red(){
    $('Error').removeClass('red');
}
function bindUser(){
	var username=$("#phoneno").val();
	var  openId=$("#openId").val();
	$.ajax({
        url : getUrl()+"/WechatLogin/bindUser.do",
        async: false ,
        data: {openId:openId,phoneNo:username},
        success : function(result){
        	//$("#Message").html(result);
    		//$(".errorhei").show();
    		//Hide();
    		if("绑定成功"===result){
    			$("#Message").html("用户注册成功.");
        		$(".errorhei").show();
        		//Hide();
    			window.location.href = getUrl()+"/views/quicksure/jsp/quicksurehome.jsp";
    		}
    		
        }
   		});	
	
}
//代理人识别码校验
function validateRecode() {
	var valCheck = $('input:radio[name="isAgent"]:checked').val();
	if (valCheck == '1') {
		$('#errorAgentCode').css("display", "none");
		var agentCode = $("#referralCode").val();
		if(agentCode==null||"undefined"==typeof agentCode||""==agentCode){
			validatecodeFlag = false;
			$("#Message").html("请输入识别码！");
			$(".errorhei").show();
			//Hide();
		}else{
			$.ajax({
				type : "POST",
				url : getUrl() + "/loginUser/checkAgentCode.do",
				data : {
					agentCode : agentCode
				},
				success : function(result) {
					var valCheckflag = $('input:radio[name="isAgent"]:checked')
							.val();
					if (valCheckflag == '1') {
						if (result == "true") {
							$('#errorAgentCode').css("display", "none");
							validatecodeFlag = true;
						} else {
							validatecodeFlag = false;
							/*$('#errorAgentCode').css("display", "block");*/
							$("#Message").html("识别码错误！");
							$(".errorhei").show();
							//Hide();
							$('#referralCode').val("");
							// $('#referralCode').focus();
						}
					} else {
						$('#errorAgentCode').css("display", "none");
						validatecodeFlag = true;
					}

				},
			   error: function(res){   
			    	 $("#pop").hide();
			     	 $("#Message").html("识别失败！");
			     	 $('.errorhei').show();				     	
			     //	Hide();
			    } 
			});
		}
	} else {
		$('#errorAgentCode').css("display", "none");
		validatecodeFlag = true;
	}
}

$('.errortan3').click(function(){
    $('.errorhei').hide();
});
/*function Hide(){
	setTimeout(function(){$('.errorhei').hide();},1000);
}
*/
function load(outtimes){
	$("#pop").show();
	setTimeout(function(){$('#pop').hide()
	},outtimes);
}

//登录
function login(){
	window.location.href=getUrl()+"/views/quicksure/jsp/LoginUser.jsp?address="+address+"&chepai="+chepai+"&dptcode="+dptcode;
}

//从绑定用户页面跳转到登录页，获取手机号码
$(document).ready(function(){
	var phoneno=$('#phoneno').val();
	if(phoneno==null||phoneno==""){
		$('#phoneno').val(phoneNo);
	}	
})