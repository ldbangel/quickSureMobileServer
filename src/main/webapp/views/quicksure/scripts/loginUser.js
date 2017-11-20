$(document).ready(function() {
//	$("#phoneno").focus();
	$("#pop").hide();
});
var _interval;
var checkphoneNoFlag=false;
var checkcodeflag=false;
if(typeof(phoneNo)!=="undefined"&&phoneNo!==null){
  $("#phoneno").val(phoneNo);
  $("#phonenum").val(phoneNo);
}

$('.selet_psw li').click(function(){
    	var Index=$(this).index();
        $(this).addClass('hover').siblings().removeClass('hover');
        $('.member_box>div').eq(Index).addClass('act').siblings().removeClass('act');
    });
    var _interval;
function getCheckCode(){
	checkphone(1);
	if(checkphoneNoFlag){
		var num=60;
	    var Span=document.getElementsByClassName('member_box ')[0].getElementsByTagName('span')[1];

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
    
function userLogin(usertype){
	if(usertype=="1"){
		var phoneCheckCode=$("#phoneCheckCode").val();
		var username=$("#phoneno").val();
		checkphone(usertype);
		if(checkphoneNoFlag){
			checkphonetest(usertype);
			if(checkcodeflag){
				$.ajax({
					url : getUrl()+"/loginUser/checkPhoneCode.do",
					async: false ,
					data: {checkCode:phoneCheckCode,phoneno:username},
					success : function(result){
						if(result=="true"){
							$("#prompt").html("正在登录，请稍等...");
							load(15000);
				    		$.ajax({
				    			url : getUrl()+"/loginUser/userLogin.do",
								async: false ,
								data: {
									username:username,
									usertype:usertype,
									},
				    			success : function(result){
				    				$("#pop").hide();
				    				if(result=="1"){
				    					$('#Message').html("登录失败！");
								    	$('.errorhei').show();
								    	//Hide();
									}else{
										clearInterval(_interval);
										$('.member_box span').eq(1).html('获取验证码').removeClass('Color');
										if(action && typeof(action)!="undefined" && action!=null && action=="myaccount"){
											window.location.href = getUrl()+"/views/quicksure/jsp/myAccount.jsp";
										}else if(typeof(action)!="undefined" && action!=null && action=="fcmyaccount"){
											window.location.href = getUrl()+"/views/quicksureFeiche/jiayixian/jsp/feiche_myAccount.jsp";
										}else if(action && typeof(action)!="undefined" && action!=null && action=="jiayixian"){
											window.location.href = getUrl()+"/diverInsure/goTopersonInfo.do?prodtype="+prodtype+"&sumamount="+sumamount+"&sumpremium="+sumpremium;
										}else if(action && typeof(action)!="undefined" && action!=null && action=="junanbao"){
											window.location.href = getUrl()+"/PersonalAccidentInsurance/getSetMealSumbit.do?prodtype="+prodtype+"&sumamount="+sumamount+"&sumpremium="+sumpremium;
										}else if(action && typeof(action)!="undefined" && action!=null && action=="yilupingan"){
											window.location.href = getUrl()+"/VehicleAccidentInsurance/immeInsuranceSubmit.do?prodno="+prodno+"&prodtype="+prodtype+"&sumamount="+sumamount+"&sumpremium="+sumpremium+"&insrncPeriod="+insrncPeriod;
										}else if(!phoneNo || typeof(phoneNo)=="undefined" || phoneNo==null || phoneNo==""){
											window.location.href = getUrl()+"/views/quicksure/jsp/quicksurehome.jsp?phoneNo="+username+"&deptAddress="+deptAddress+"&lcno="+lcnNo+"&dptcode="+dptcode;
										}else{
											window.location.href = getUrl()+"/vehicleInfor/goToVehicleScreen.do?phoneNo="+phoneNo+"&deptAddress="+deptAddress+"&lcno="+lcnNo+"&dptcode="+dptcode;
										}
									}
				    			},  
				    		    error: function(res){   
				    		    	 $("#pop").hide();
				    		     	 $("#Message").html("登录失败");
				    		     	 $('.errorhei').show();				    		     	
				    		     	//Hide();
				    		    }  
				    		});
			    		}else{
			    			$('#Message').html("验证码输入有误！");
					    	$('.errorhei').show();
					    	//Hide();
					    	$("#phoneCheckCode").focus();
					    	
			    		}
			    	},
	    		    error: function(res){   
	    		    	 $("#pop").hide();
	    		     	 $("#Message").html("登录失败");
	    		     	 $('.errorhei').show();				    		     	
	    		     	//Hide();
	    		    } 
			    });
			}
		}
	}else{
		var password=$("#psw").val();
		var username=$("#phonenum").val();
		checkphone(usertype);
		if(checkphoneNoFlag){
			checkphonetest(usertype);
			if(checkcodeflag){
				$("#prompt").html("正在登录，请稍等...");
				load(15000);
				$.ajax({
					type: "post",
					url : getUrl()+"/loginUser/userLogin.do",
					async: false ,
					data: {username:username,password:password,usertype:usertype},
					success : function(result){
						$("#pop").hide();
						if(result=="3"){
							$('#Message').html("用户名不存在或密码输入错误！");
					    	$('.errorhei').show();
						}else{
							if(typeof(action)!="undefined" && action!=null && action=="myaccount"){
								window.location.href = getUrl()+"/views/quicksure/jsp/myAccount.jsp";
							}else if(typeof(action)!="undefined" && action!=null && action=="fcmyaccount"){
								window.location.href = getUrl()+"/views/quicksureFeiche/jiayixian/jsp/feiche_myAccount.jsp";
							}else if(action && typeof(action)!="undefined" && action!=null && action=="jiayixian"){
								window.location.href = getUrl()+"/diverInsure/goTopersonInfo.do?prodtype="+prodtype+"&sumamount="+sumamount+"&sumpremium="+sumpremium;
							}else if(action && typeof(action)!="undefined" && action!=null && action=="junanbao"){
								window.location.href = getUrl()+"/PersonalAccidentInsurance/getSetMealSumbit.do?prodtype="+prodtype+"&sumamount="+sumamount+"&sumpremium="+sumpremium;
							}else if(action && typeof(action)!="undefined" && action!=null && action=="yilupingan"){
								window.location.href = getUrl()+"/VehicleAccidentInsurance/immeInsuranceSubmit.do?prodno="+prodno+"&prodtype="+prodtype+"&sumamount="+sumamount+"&sumpremium="+sumpremium+"&insrncPeriod="+insrncPeriod;
							}else if(!phoneNo || typeof(phoneNo)=="undefined" || phoneNo==null || phoneNo==""){
								window.location.href = getUrl()+"/views/quicksure/jsp/quicksurehome.jsp?phoneNo="+username+"&deptAddress="+deptAddress+"&lcno="+lcnNo+"&dptcode="+dptcode;
							}else{
								window.location.href = getUrl()+"/vehicleInfor/goToVehicleScreen.do?phoneNo="+phoneNo+"&deptAddress="+deptAddress+"&lcno="+lcnNo+"&dptcode="+dptcode;
							}
						}
					},					 
	    		    error: function(res){   
	    		    	 $("#pop").hide();
	    		     	 $("#Message").html("登录失败");
	    		     	 $('.errorhei').show();				    		     	
	    		     	//Hide();
	    		    } 
				});
			}
		}
	}
}
    
$(document).ready(function(){
  if(localStorage.getItem('key')!=null){
	  console.log(localStorage.getItem('key'));
     $('.phone').val(localStorage.getItem('key'));
	 $('.remenber').attr('checked','checked');
  }else{console.log(1)}
});


function checkphonetest(type) {
	var codee='';
	var reg="";
	if(type=='1'){
		checkphone(type);
		if(checkphoneNoFlag){
			$("#errorPhoneno").css("display", "none");
			$("#nullPhoneno").css("display", "none");
			reg=/^\d{6}$/;
			codee=$('#phoneCheckCode').val();
			if(codee==null||"undefined"==typeof codee||""==codee){
				checkcodeflag=false;
				$('#Message').html("请输入验证码！");
		    	$('.errorhei').show();
		    	//Hide();
		    	$("#phoneCheckCode").focus();
				
			}else{
				if(!reg.test(codee)){
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
	}if(type=='2'){
		checkphone(type);
		if(checkphoneNoFlag){
			codee=$('#psw').val();
			reg=/^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,20}$/;
			if(codee==null||"undefined"==typeof codee||""==codee){
				checkcodeflag=false;
				$('#Message').html("请输入密码！");
		    	$('.errorhei').show();
		    	//Hide();
		    	$("#psw").focus();
			}else{
				if(!reg.test(codee)){
					$('#Message').html("密码输入有误！");
			    	$('.errorhei').show();
			    	//Hide();
			    	$("#psw").focus();
					checkcodeflag=false;
				}else{
					checkcodeflag=true;
				}
			}
		}
	}
}

function checkphone(type) {
	var phone='';
	if(type=='1'){
		phone=$("#phoneno").val();
		if(phone==null||"undefined"==typeof phone||""==phone){
			checkphoneNoFlag=false;
			$('#Message').html("请输入手机号！");
	    	$('.errorhei').show();
	    	//Hide();
			$("#phoneno").focus();
		}else{
			if(!(/^1[34578]\d{9}$/.test(phone))){
				checkphoneNoFlag=false;
				$('#Message').html("手机号输入有误！");
		    	$('.errorhei').show();
		    	//Hide();
				
				$("#phoneno").focus();
			}else{
				checkphoneNoFlag=true;
			}
		}
	}
	if(type=='2'){
		phone=$("#phonenum").val();
		if(phone==null||"undefined"==phone||""==phone){
			checkphoneNoFlag=false;
			$('#Message').html("请输入手机号！");
	    	$('.errorhei').show();
	    	//Hide();
			$("#phonenum").focus();
		}else{
			if(!(/^1[34578]\d{9}$/.test(phone))){
				checkphoneNoFlag=false;
				$('#Message').html("手机号输入有误！");
		    	$('.errorhei').show();
		    	//Hide();
				$("#phonenum").focus();
			}else{
				checkphoneNoFlag=true;
			}
		}
	}
}

$('.remenber').click(function(){
      if($(this).attr('checked')!=null){
		   $('.remenber').attr('checked','checked');
	  }else{
		   $('.remenber').removeAttr('checked');
	  }
});

$('.errortan3').click(function(){
    $('.errorhei').hide();
});
/*function Hide(){
	setTimeout(function(){$('.errorhei').hide()
		},1000);
}*/

function load(outtimes){
	$("#pop").show();
	setTimeout(function(){$('#pop').hide()
	},outtimes);
}

//注册按钮
function regist(){
	window.location.href=getUrl()+"/views/quicksure/jsp/registUser.jsp?address="+address+"&chepai="+chepai+"&dptcode="+dptcode;
}