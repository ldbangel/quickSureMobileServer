//取绝对路径地址
function getUrl(){
	 var protocol = window.location.protocol;
	 var host = window.location.host;
	 var pathname = window.location.pathname.split('/');
	 var url = protocol+"//"+host+"/"+pathname[1];
	 return url;
}
var ischgownerflag = 0;//是否过户车
var checkLcnNoFlag=0;//车牌号码
var checkOwnerNameFlag=0;//车主名字
var checkApplicationNameFlag=0;//投保人姓名
var checkinNameFlag=0;//被保人姓名
var checkdeliveryNameFlag=0;//配送人
var ownersphoneNo=0;//车主手机号码
var appPhoneNo=0;//投保人手机
var inphoneNo=0;//被保人手机
var deliveryPhoneNo=0;//配送人手机
var owneridno=0;//车主身份证
var appidno=0;//投保人身份证
var inidno=0;//被保人身份证

var checkvincodeFlag=0;//车架
var baseinfors = "";//baseinfor对象
var ownerName = $("#carownerinfor").val();//车主姓名
var appName = $("#appName").val();//投保人姓名
var inName =$("#inName").val();//被保人姓名
var deliveryName = $("#deliveryName").val();//保费收件人姓名
var ownerphone =$("#carownerphone").val();//车主手机
var appPhone =$("#appphone").val();//投保人手机
var inphone =$("#inphone").val();//被保人手机
var deliveryPhone =$("#deliveryPhone").val();//配送人手机
var ownerCode = $("#carownercertcode").val();//车主身份证
var appCode = $("#appcertcode").val();//投保人身份证
var inCode = $("#incertcode").val();//被保人身份证

var orderNoEverExist=false;
var CsrUnderwritingFlag=false;
var province;

//交强险的选择()
$(document).ready(function(){
	$("#CTPLCode").click(function(){
		var syDate = $("#SY_DATE").val();//商业险
		if($("#CTPLCode").attr("checked")){
			if(syDate!==null ||syDate!==""){
				$("#JQ_DATE").val(syDate);
			}
		}else{
			$("#JQ_DATE").val("");
		}
		
		
	});
});

//身份证校验
function identityCodeValid(val) {
  var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
  var reg=/^\d{6}(19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
  ownerCode = $("#carownercertcode").val();//车主身份证
  var ownerCode2;
  appCode = $("#appcertcode").val();//投保人身份证
  var appCode2;
  inCode = $("#incertcode").val();//被保人身份证
  var inCode2;
   if(ownerCode==''||ownerCode==null||ownerCode==undefined){
	   owneridno=1;//不能为空		
	}else if(!reg.test(ownerCode) || !city[ownerCode.substr(0,2)]){
		owneridno=2;
		$('#chackMess').html("车主身份证格式错误");
		$('.errorhei').show();
	}else if(ownerCode.length == 18){
		ownerCode2 = ownerCode.split('');
          //∑(ai×Wi)(mod 11)
          //加权因子
          var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
          //校验位
          var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
          var sum = 0;
          var ai = 0;
          var wi = 0;
          for (var i = 0; i < 17; i++)
          {
              ai = ownerCode2[i];
              wi = factor[i];
              sum += ai * wi;
          }
          var last = parity[sum % 11];
          if(parity[sum % 11] != ownerCode2[17]){
              tip = "The last character is wrong!";
              owneridno=2;
              $('#chackMess').html("车主身份证输入有误！请检查");
				$('.errorhei').show();
          }else{
        	  owneridno=0; 
          }
      }else{
    	  owneridno=0; 
      }
	if(appCode==''||appCode==null||appCode==undefined){
		appidno=1;//不能为空
	}else if(!reg.test(appCode) || !city[appCode.substr(0,2)]){
		appidno=2;
		$('#chackMess').html("投保人身份证格式错误");
		$('.errorhei').show();
	}else if(appCode.length == 18){
		appCode2 = appCode.split('');
          //∑(ai×Wi)(mod 11)
          //加权因子
          var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
          //校验位
          var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
          var sum = 0;
          var ai = 0;
          var wi = 0;
          for (var i = 0; i < 17; i++)
          {
              ai = appCode2[i];
              wi = factor[i];
              sum += ai * wi;
          }
          var last = parity[sum % 11];
          if(parity[sum % 11] != appCode2[17]){
              tip = "The last character is wrong!";
              appidno=2;
              $('#chackMess').html("投保人身份证输入有误！请检查");
				$('.errorhei').show();
          }else{
        	  appidno=0;
          }
      }else {
    	  appidno=0;
      }
	if(inCode==''||inCode==null||inCode==undefined){
		inidno=1;//不能为空
	}else if(!reg.test(inCode) || !city[inCode.substr(0,2)]){
		inidno=2;
		$('#chackMess').html("被保人身份证格式错误");
		$('.errorhei').show();
	}else if(inCode.length == 18){
		inCode2 = inCode.split('');
          //∑(ai×Wi)(mod 11)
          //加权因子
          var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
          //校验位
          var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
          var sum = 0;
          var ai = 0;
          var wi = 0;
          for (var i = 0; i < 17; i++)
          {
              ai = inCode2[i];
              wi = factor[i];
              sum += ai * wi;
          }
          var last = parity[sum % 11];
          if(parity[sum % 11] != inCode2[17]){
              tip = "The last character is wrong!";
              inidno=2;
              $('#chackMess').html("被保人身份证输入有误！请检查");
				$('.errorhei').show();
          }else {
        	  inidno=0;
          }
      }else{
    	  inidno=0;
      }
	if(val=='Owner'){
		if(ownerCode==''||ownerCode==null||ownerCode==undefined){
			   owneridno=1;//不能为空
			   $('#chackMess').html("车主身份证不能为空");
				$('.errorhei').show();
		}else if(!reg.test(ownerCode) || !city[ownerCode.substr(0,2)]){
			owneridno=2;
			$('#chackMess').html("车主身份证格式错误");
			$('.errorhei').show();
		}else if(ownerCode.length == 18){
			ownerCode = ownerCode.split('');
	          //∑(ai×Wi)(mod 11)
	          //加权因子
	          var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
	          //校验位
	          var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
	          var sum = 0;
	          var ai = 0;
	          var wi = 0;
	          for (var i = 0; i < 17; i++)
	          {
	              ai = ownerCode[i];
	              wi = factor[i];
	              sum += ai * wi;
	          }
	          var last = parity[sum % 11];
	          if(parity[sum % 11] != ownerCode[17]){
	              tip = "The last character is wrong!";
	              owneridno=2;
	              $('#chackMess').html("车主身份证输入有误！请检查");
					$('.errorhei').show();
	          }else{
	        	  owneridno=0; 
	          }
	      }else{
	    	  owneridno=0; 
	      }
	}else if(val=='Application'){
			if(appCode==''||appCode==null||appCode==undefined){
				appidno=1;//不能为空
				   $('#chackMess').html("投保人身份证不能为空");
					$('.errorhei').show();
			}else if(!reg.test(appCode) || !city[appCode.substr(0,2)]){
				appidno=2;
				$('#chackMess').html("投保人身份证格式错误");
				$('.errorhei').show();
			}else if(appCode.length == 18){
				appCode = appCode.split('');
		          //∑(ai×Wi)(mod 11)
		          //加权因子
		          var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
		          //校验位
		          var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
		          var sum = 0;
		          var ai = 0;
		          var wi = 0;
		          for (var i = 0; i < 17; i++)
		          {
		              ai = appCode[i];
		              wi = factor[i];
		              sum += ai * wi;
		          }
		          var last = parity[sum % 11];
		          if(parity[sum % 11] != appCode[17]){
		              tip = "The last character is wrong!";
		              appidno=2;
		              $('#chackMess').html("投保人身份证输入有误！请检查");
						$('.errorhei').show();
		          }else{
		        	  appidno=0;
		          }
		      }else {
		    	  appidno=0;
		      }
		}else if(val=='Insured'){
			if(inCode==''||inCode==null||inCode==undefined){
				inidno=1;//不能为空
				   $('#chackMess').html("被保人身份证不能为空");
					$('.errorhei').show();
			}else if(!reg.test(inCode) || !city[inCode.substr(0,2)]){
				inidno=2;
				$('#chackMess').html("被保人身份证格式错误");
				$('.errorhei').show();
			}else if(inCode.length == 18){
				inCode = inCode.split('');
		          //∑(ai×Wi)(mod 11)
		          //加权因子
		          var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
		          //校验位
		          var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
		          var sum = 0;
		          var ai = 0;
		          var wi = 0;
		          for (var i = 0; i < 17; i++)
		          {
		              ai = inCode[i];
		              wi = factor[i];
		              sum += ai * wi;
		          }
		          var last = parity[sum % 11];
		          if(parity[sum % 11] != inCode[17]){
		              tip = "The last character is wrong!";
		              inidno=2;
		              $('#chackMess').html("被保人身份证输入有误！请检查");
						$('.errorhei').show();
		          }else {
		        	  inidno=0;
		          }
		      }else{
		    	  inidno=0;
		      }
		}
				
		
	
  
}


//车牌号校验
function checkLcnNo() {
	var car_num = document.getElementById('lcnno').value;
	if("*-*"!==car_num){
		var reg = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
		if(car_num=='' ||car_num==null || car_num==undefined){
			checkLcnNoFlag=1;//不能为空
		}else if (!reg.test(car_num)) {		
	    	checkLcnNoFlag=2;//格式错误
		} else {
			checkLcnNoFlag=0;
		}
    }else if("*-*"==car_num){
    	checkLcnNoFlag=0;
    }
}
//如果为过户车，过户日期必填
$(".chgownerflag").change(function(){
	if($("#chgownerflag1").attr("checked")){
		$(".transferdate").show();
		ischgownerflag=1;
	}else{
		ischgownerflag=0;
		$(".transferdate").hide();
	}
})

//去除字符串中的空格
function Trim(obj){
	var str = obj.value;
    var a = str.replace(/<\/?[^>]*>/gim,"");//去掉所有的html标记
    var b = a.replace(/(^\s+)|(\s+$)/g,"");//去掉前后空格
    var c = b.replace(/\s/g,"");//去除文章中间空格
    obj.value=c;
}
//姓名校验
function validateName(val){
	ownerName = $("#carownerinfor").val();//车主姓名
	appName = $("#appName").val();//投保人姓名
	inName =$("#inName").val();//被保人姓名
	deliveryName = $("#deliveryName").val();//保费收件人姓名
	var reg = /^[\u4e00-\u9fa5]{2,4}$/i; 
	if(ownerName==''||ownerName==null||ownerName==undefined){
		checkOwnerNameFlag=1;//不能为空	
	}else if(!reg.test(ownerName)){
		checkOwnerNameFlag=2;
	}else{
		checkOwnerNameFlag=0;
	}
	if(appName==''||appName==null||appName==undefined){
		checkApplicationNameFlag=1;//不能为空
	}else if(!reg.test(appName)){
		checkApplicationNameFlag=2;
	}else{
		checkApplicationNameFlag=0;
	}
	if(inName==''||inName==null||inName==undefined){
		checkinNameFlag=1;//不能为空
	}else if(!reg.test(inName)){
		checkinNameFlag=2;
	}else{
		checkinNameFlag=0;
	}
	if(deliveryName==''||deliveryName==null||deliveryName==undefined){
		checkdeliveryNameFlag=1;
	}else if(!reg.test(deliveryName)){
		checkdeliveryNameFlag=2;
	}else{
		checkdeliveryNameFlag=0;
	}
	if(val=='Owner'){
		if(ownerName==''||ownerName==null||ownerName==undefined){
			checkOwnerNameFlag=1;//不能为空
			$('#chackMess').html("车主姓名不能为空");
			$('.errorhei').show();
		}else if(!reg.test(ownerName)){
			checkOwnerNameFlag=2;//格式错误
			$('#chackMess').html("车主姓名格式有误！请检查！");
			$('.errorhei').show();
		}else{
			checkOwnerNameFlag=0;
		}
	}else if(val=='Application'){
		if(appName==''||appName==null||appName==undefined){
			$('#chackMess').html("投保人姓名不能为空");
			$('.errorhei').show();
			checkApplicationNameFlag=1;//不能为空
		}else if(!reg.test(appName)){
			$('#chackMess').html("投保人姓名格式有误！请检查！");
			$('.errorhei').show();
			checkApplicationNameFlag=2;//格式错误
		}else{
			checkApplicationNameFlag=0;
		}
	}else if(val=='Insured'){
		if(inName==''||inName==null||inName==undefined){
			$('#chackMess').html("被保人姓名不能为空");
			$('.errorhei').show();
			checkinNameFlag=1;//不能为空
		}else if(!reg.test(inName)){
			$('#chackMess').html("被保人姓名格式有误！请检查！");
			$('.errorhei').show();
			checkinNameFlag=2;//格式错误
		}else{
			checkinNameFlag=0;
		}
	}else if(val=='Delivery'){
		if(deliveryName==''||deliveryName==null||deliveryName==undefined){
			$('#chackMess').html("配送人姓名不能为空");
			$('.errorhei').show();
			checkdeliveryNameFlag=1;//不能为空
		}else if(!reg.test(deliveryName)){
			$('#chackMess').html("配送人姓名格式有误！请检查！");
			$('.errorhei').show();
			checkdeliveryNameFlag=2;//格式错误
		}else{
			checkdeliveryNameFlag=0;
		}
	}
	
	
}
//手机号码校验
function mobileNumber(val){
	ownerphone =$("#carownerphone").val();//车主手机
	appPhone =$("#appphone").val();//投保人手机
    inphone =$("#inphone").val();//被保人手机
	deliveryPhone =$("#deliveryPhone").val();//配送人手机
	mobilePattern = /^(1[(3-5)|(7-8)]{1})\d{9}$/;
	if(ownerphone==''||ownerphone==null||ownerphone==undefined){
		ownersphoneNo=1;//不能为空	
	}else if(!mobilePattern.test(ownerphone)){
		ownersphoneNo=2;
	}else{
		ownersphoneNo=0;
	}
	if(appPhone==''||appPhone==null||appPhone==undefined){
		appPhoneNo=1;//不能为空
	}else if(!mobilePattern.test(appPhone)){
		appPhoneNo=2;
	}else{
		appPhoneNo=0;
	}
	
	if(inphone==''||inphone==null||inphone==undefined){
		inphoneNo=1;//不能为空
	}else if(!mobilePattern.test(inphone)){
		inphoneNo=2;
	}else{
		inphoneNo=0;
	}
	
	if(deliveryPhone==''||deliveryPhone==null||deliveryPhone==undefined){
		deliveryPhoneNo=1;
	}else if(!mobilePattern.test(deliveryPhone)){
		deliveryPhoneNo=2;
	}else{
		deliveryPhoneNo=0;
	}
	
	if(val=='Owner'){
		if(ownerphone==''||ownerphone==null||ownerphone==undefined){
			ownersphoneNo=1;//不能为空
			$('#chackMess').html("车主手机号不能为空");
			$('.errorhei').show();
		}else if(!mobilePattern.test(ownerphone)){
			ownersphoneNo=2;//格式错误
			$('#chackMess').html("车主手机格式有误！请检查！");
			$('.errorhei').show();
		}else{
			ownersphoneNo=0;
		}
	}else if(val=='Application'){
		if(appPhone==''||appPhone==null||appPhone==undefined){
			$('#chackMess').html("投保人手机号不能为空");
			$('.errorhei').show();
			appPhoneNo=1;//不能为空
		}else if(!mobilePattern.test(appPhone)){
			$('#chackMess').html("投保人手机号格式有误！请检查！");
			$('.errorhei').show();
			appPhoneNo=2;//格式错误
		}else{
			appPhoneNo=0;
		}
	}else if(val=='Insured'){
		if(inphone==''||inphone==null||inphone==undefined){
			$('#chackMess').html("被保人手机号不能为空");
			$('.errorhei').show();
			inphoneNo=1;//不能为空
		}else if(!mobilePattern.test(inphone)){
			$('#chackMess').html("被保人手机号格式有误！请检查！");
			$('.errorhei').show();
			inphoneNo=2;//格式错误
		}else{
			inphoneNo=0;
		}
	}else if(val=='Delivery'){
		if(deliveryPhone==''||deliveryPhone==null||deliveryPhone==undefined){
			$('#chackMess').html("配送人姓名不能为空");
			$('.errorhei').show();
			deliveryPhoneNo=1;//不能为空
		}else if(!mobilePattern.test(deliveryPhone)){
			$('#chackMess').html("配送人姓名格式有误！请检查！");
			$('.errorhei').show();
			deliveryPhoneNo=2;//格式错误
		}else{
			deliveryPhoneNo=0;
		}
	}
	     
}
//车架号校验
function validateVIN() {
	var vinlimit = /^\w*[a-zA-Z]+\w*$/;
	var vinCode=document.getElementById('vinno').value;
	if(vinCode==""||vinCode==null||vinCode==undefined){
		checkvincodeFlag==1;//不能为空
	}else if(vinCode.length>17 || vinCode.length<17){
		checkvincodeFlag=2;
	}else if((vinCode.indexOf('Q') >= 0) || (vinCode.indexOf('I') >= 0) || (vinCode.indexOf('O') >= 0) || (vinCode.indexOf('*') >= 0)){
		checkvincodeFlag=3;
    }else if(!vinlimit.test(vinCode)){
    	checkvincodeFlag=4;
    }else{
    	checkvincodeFlag=0;
    }
}

//车型查询校验
function validateModel(){
	var str = document.getElementById('model').value;
	if(str!="" && str!=null && typeof(str)!=undefined){
//		$('#errorMessageforModel').hide();	
		searchByModel();
	}else{
//		$('#errorMessageforModel').show();
		
	}
}

/*    //定位
     $('.pf_left span').click(function(){
         var Position="."+$(this).attr('class')+"_p";
         var moved= 0,move= 0,step= 0, interval=10,elem_selTop= 0,timer=null;
         var scroll=$(document).scrollTop(), screenHeight=$(window).innerHeight();
         function scrollTo(){
             elem_selTop=$(Position).offset().top;
             move=(scroll+screenHeight/2)-elem_selTop;
             step=move/100;
             timer=setInterval(function timer(){scrollStep()},10);
             function scrollStep(){
                 scroll=scroll-(step);
              $(document).scrollTop(scroll);
                 moved++;
                 if(moved==100){
                     clearInterval(timer);
                    timer=null;
                    moved=0;
                 }
             }
         }
         scrollTo();
     });*/
	 var agreementinfors=new Array();
	 var coverageinforsave=new Array();
	 
     function getCoverage(){
    	  var coverageList = new Array();
    	  
    	  //车损
    	  if($("#modCode").prop("checked")){
    		  var modsuminsured=1;//车损保额
    		  var moddeductibleflag;
    	  if($("#modAbatement").prop("checked")){
    		  moddeductibleflag=1;
    	  }else{
    		  moddeductibleflag=0;
    	  }
    		  coverageList.push({insrnccode:"101",suminsured:modsuminsured,deductibleflag:moddeductibleflag});
    	  }else{
    	    $("#modQuota").val("");
    		$("#modQuotaPremium").val("");
    		$("#modductiblePremium").val("");
    	  }
    	  //三者
    	  if($("#vtplCode").prop("checked")){
    		 var vtpldeductibleflag;//不计免赔
    		 var vtplsuminsured=$("#vtplQuota").val();;//保额
    	  
    	  if($("#vtplAbatement").prop("checked")){
    		 vtpldeductibleflag=1;
    	  }else{
    		 vtpldeductibleflag=0;
    	  }
    		  coverageList.push({insrnccode:"102",suminsured:vtplsuminsured,deductibleflag:vtpldeductibleflag});
    	  }else{
    	    $("#vtplPremium").val("");
    	    $("#vtplductiblePremium").val("");
    	  }
    	  //盗抢
    	  if($("#theftCode").prop("checked")){
    		 var TheftDeductibleflag;
    		 var TheftSuminsured=1;//保额
    	 if($("#theftAbatement").prop("checked")){
    		 TheftDeductibleflag=1;
    	  }else{
    		  TheftDeductibleflag=0;
    	  }
    	 	coverageList.push({insrnccode:"103",suminsured:TheftSuminsured,deductibleflag:TheftDeductibleflag});
    	  }else{
    	    $("#theftQuota").val("");
    		$("#theftPremium").val("");
    		$("#theftductiblePremium").val("");
    	  }
    	  //司机责任
    	 if($("#DriversCode").prop("checked")){
    		  var Driversdeductibleflag;//不计免赔
    		  var Driverssuminsured=$("#DriversQuota").val();;//保额
    	  if($("#DriversAbatement").prop("checked")){
    		  Driversdeductibleflag=1;
    	  }else{
    		  Driversdeductibleflag=0;
    	  }
    	     coverageList.push({insrnccode:"104",suminsured:Driverssuminsured,deductibleflag:Driversdeductibleflag});
    	  }else{
    	    $("#DriversPremium").val("");
    	    $("#DriversductiblePremium").val("");
    	  } 
    	  //乘客责任
    	 if($("#PassengerCode").prop("checked")){
    		 var Passengerdeductibleflag;//不计免赔
    		 var Passengersuminsured=$("#PassengerQuota").val();;//保额
    	  if($("#PassengerAbatement").prop("checked")){
    		 Passengerdeductibleflag=1;
    	  }else{
    		 Passengerdeductibleflag=0;
    	  }
    	  	 coverageList.push({insrnccode:"105",suminsured:Passengersuminsured,deductibleflag:Passengerdeductibleflag});
    	  }else{
    	     $("#PassengerPremium").val("");
    	     $("#PassengerductiblePremium").val("");
    	  }
    	  //自燃
    	  if($("#CombustionCode").prop("checked")){
    		 var CombustionDeductibleflag;
    		 var CombustionSuminsured=1;//保额
    	 if($("#CombustionAbatement").prop("checked")){
    		 CombustionDeductibleflag=1;
    	  }else{
    		 CombustionDeductibleflag=0;
    	  }
    	 	coverageList.push({insrnccode:"108",suminsured:CombustionSuminsured,deductibleflag:CombustionDeductibleflag});
    	  }else{
    	    $("#CombustionQuota").val("");
    		$("#CombustionPremium").val("");
    		$("#CombustionductiblePremium").val("");
    	  }
    	  //玻璃
    	  if($("#GlassCode").prop("checked")){
    		 var GlassDeductibleflag;
    		 var Glasssuminsured=$("#GlassQuota").val();;//保额
    		 coverageList.push({insrnccode:"107",suminsured:Glasssuminsured,deductibleflag:GlassDeductibleflag});
    	  }else{
    	     $("#GlassQuotaPremium").val("");
    	  }
    	  //第三方
    	  if($("#ThirdPartyCode").prop("checked")){
    		 var ThirdPartyDeductibleflag=null;
    		 var ThirdPartySuminsured=1;//保额
    		 coverageList.push({insrnccode:"115",suminsured:ThirdPartySuminsured,deductibleflag:ThirdPartyDeductibleflag});
    	  }else{
    		 $("#ThirdPartyQuota").val("");
    		 $("#ThirdPartyPremium").val("");
    	  }
    	  //专车
    	  if($("#CarCode").prop("checked")){
    		 var CarDeductibleflag=null;
    		 var CarSuminsured=1;//保额
    		 coverageList.push({insrnccode:"116",suminsured:CarSuminsured,deductibleflag:CarDeductibleflag});
    	  }else{
    	    $("#CarQuota").val("");
    	    $("#CarPartyPremium").val("");
    	  }
    	  //交强
    	  if($("#CTPLCode").prop("checked")){
    		 var TCPLdeductibleflag=null;
    		 var TCPLsuminsured=1;//保额
    		 coverageList.push({insrnccode:"357",suminsured:TCPLsuminsured,deductibleflag:TCPLdeductibleflag});
    	  }else{
    	    $("#CTPLQuota").val("");
    		$("#CTPLPremium").val("");
    		$("#currenttax").val("");
    		$("#appDate_1").val("");
    	  }
    	   //起保时间
    	   var syStartDate=document.getElementById("SY_DATE").value;
    	   var jqStartDate=document.getElementById("JQ_DATE").value;
    	   //订单号
/*    	   var orderNo = document.getElementById("orderNo").value;*/
    	   var orderNo = $("#csrPremiumOrderNo").val();
    	   if(syStartDate!=null || jqStartDate!=null || orderNo!=null){
    	     coverageList.push({sypolicystartdate:syStartDate,jqpolicystartdate:jqStartDate,baseinfororderno:orderNo});
    	   } 
    	  
    	  return coverageList;
    	  }
     
  function getVhlinfor(){
	  var vinno=$("#vinno").val();
	  var brandName=$("#brandName").val();
	  var brandcode=$("#brandcode").val();
	  var modelName=$("#modelName").val();
	  var marketyear=$("#marketyear").val();
	  var lcnno=$("#lcnno").val();
	  var engno=$("#engno").val();
/*	  var certificatedate=$("#certificatedate").val();*/
	  var vhlval=$("#vhlval").val();
	  var displacement=$("#displacement").val();
	  var setno=$("#setno").val();
	  var registerdate=$("#registerdate").val();
	  var ownersname=$("#carownerinfor").val();
	  var ownersphoneno=$("#carownerphone").val();
	  var ownerscerticode=$("#carownercertcode").val();
	  var chgownerflag= $('input:radio[name="chgownerflag"]:checked').val();
	  if(chgownerflag=="0"){
		  $("#transferdate").attr("readOnly",false);//不可编辑，可以传值
		  $("#transferdate").attr("disabled",false);//不可编辑，不可以传值
	  }
	  var vhlinfor=
	  {
		  "vinno":vinno,
	  	  "brandName":brandName,
	  	  "brandcode":brandcode,
	  	  "vehiclename":modelName,
	  	  "marketyear":marketyear,
	  	  "model":modelName,
	  	  "lcnno":lcnno,
	  	  "engno":engno,
	  	  "vhlval":vhlval,
/*	  	  "certificatedate":certificatedate,*/
	  	  "displacement":displacement,
	  	  "brandcode":brandcode,
	  	  "setno":setno,
	  	  "drvowner":ownersname,
		  "phoneno":ownersphoneno,
		  "certificateno":ownerscerticode,
	  	  "registerdate":registerdate,
	  	  "chgownerflag":chgownerflag
	  };
	  return vhlinfor;
  }
  
  function getUserinfor(){
	  var phoneCheckCode=$("#carownerphone").val();
	  var userinfor={
			  "username":phoneCheckCode
	  }
	  return userinfor;
  }
  
  function getDeliveryinfor(){
	  var deliveryname=$("#deliveryName").val();
	  var deliveryphone=$("#deliveryPhone").val();
//	  var Delivery_Province=$("#Delivery_Province").val();
//	  var Delivery_City=$("#Delivery_City").val();
//	  var Delivery_County=$("#Delivery_County").val();
	  var Delivery_Province=$("#Delivery_Province").find("option:selected").attr("data-name");
	  var Delivery_City=$("#Delivery_City").find("option:selected").attr("data-name");
	  var Delivery_County=$("#Delivery_County").find("option:selected").attr("data-name");
	  var deliverydetailaddress=$("#deliverydetailaddress").val();
	  var deliveryaddress=Delivery_Province+","+Delivery_City+","+Delivery_County+"-"+deliverydetailaddress;
	  var deliveryinfor=
	  {
			  "deliveryname":deliveryname,  
			  "deliveryphone":deliveryphone,
			  "deliveryaddress":deliveryaddress
	  };
	  return deliveryinfor;
  }
  
  function getBaseinfor(){
	  var deptNo=$("#code").val();
	  var orderno=$("#csrPremiumOrderNo").val();
	  var jqStartDate=$("#jqStartDate").val();
	  var jqendDate=$("#jqEndDate").val();
	  var syStartDate=$("#syStartDate").val();
	  var syendDate=$("#syEndDate").val();
	  var jqpremium=$("#jqpremium").val();
	  if(jqpremium==""){
		  jqpremium=null;
	  }
	  var sypremium=$("#sypremium").val();
	  if(sypremium==""){
		  sypremium=null;
	  }
	  var totalPremium=$("#totalPremium").val();
	  if(totalPremium==""){
		  totalPremium=null;
	  }
	  var claimAdjustRsn=$("#claimAdjustRsn").val();
	  var applyTotalAdj=$("#applyTotalAdj").val();
	  
	  if(claimAdjustRsn=='连续3年没有发生赔款'){
	    	 claimAdjustRsn="641001";
	     }else if(claimAdjustRsn=='连续2年没有发生赔款'){
	    	 claimAdjustRsn="641002";
	     }else if(claimAdjustRsn=='上年没有发生赔款'){
	    	 claimAdjustRsn="641003";
	     }else if(claimAdjustRsn=='新保或上年发生1次赔款'){
	    	 claimAdjustRsn="641004";
	     }else if(claimAdjustRsn=='上年发生2次赔款'){
	    	 claimAdjustRsn="641005";
	     }else if(claimAdjustRsn=='上年发生3次赔款'){
	    	 claimAdjustRsn="641006";
	     }else if(claimAdjustRsn=='上年发生4次赔款'){
	    	 claimAdjustRsn="641007";
	     }else if(claimAdjustRsn=='上年发生5次及以上赔款'){
	    	 claimAdjustRsn="641008";
	     }
	  
	  var taxpremium=$("#currenttax").val();
	  if(taxpremium==""){
		  taxpremium=0.00;
	  }
	  var baseinfor=
	  {	  
		  "orderno":orderno,	  
		  "deptno":deptNo,	  
		  "jqpolicystartdate":jqStartDate,	  
		  "jqpolicyenddate":jqendDate,	  
		  "sypolicystartdate":syStartDate,	  
		  "sypolicyenddate":syendDate,	  
		  "jqpremium":jqpremium,	  
		  "sypremium":sypremium,	  
		  "totalPremium":totalPremium,
		  "applyTotalAdj":applyTotalAdj,
		  "claimAdjustRsn":claimAdjustRsn,
		  "taxpremium":taxpremium
	  };
	  return baseinfor;
  }
  
  function getTaxinfor(){
	  var latefee=$("#latefee").val();//滞纳金
	  if(latefee==""){
		  latefee=0;
	  }
	  var currenttax=$("#currentTax2").val();//当年应缴
	  if(currenttax==""){
		  currenttax=0;
	  }
	  var taxinfor=
	  {
		"latefee":latefee,	  
		"currenttax":currenttax,	  
	  };
	  return taxinfor;
  }
  
  function getInsuranceperinfor(){
	  var ownersname=$("#carownerinfor").val();
	  var ownersphoneno=$("#carownerphone").val();
	  var ownerscerticode=$("#carownercertcode").val();
	  var ownersaddress=$("#ownersdetailaddress").val();
	  
	  var applicationname=$("#appName").val();
	  var applicationphoneno=$("#appphone").val();
	  var applicationcerticode=$("#appcertcode").val();
//	  var Application_Province=$("#Application_Province").val();
//	  var Application_City=$("#Application_City").val();
//	  var Application_County=$("#Application_County").val();
	  var Application_Province=$("#Application_Province").find("option:selected").attr("data-name");
	  var Application_City=$("#Application_City").find("option:selected").attr("data-name");
	  var Application_County=$("#Application_County").find("option:selected").attr("data-name");
	  var applicationdetailaddress=$("#applicationdetailaddress").val();
	  var applicationaddress=Application_Province+","+Application_City+","+Application_County+"-"+applicationdetailaddress;
	  var applicationzipcode=$("#Application_ZipCode").val();
	  
	  var insurename=$("#inName").val();
	  var insurephoneno=$("#inphone").val();
	  var insurecerticode=$("#incertcode").val();
//	  var Insured_Province=$("#Insured_Province").val();
//	  var Insured_City=$("#Insured_City").val();
//	  var Insured_County=$("#Insured_County").val();
	  var Insured_Province=$("#Insured_Province").find("option:selected").attr("data-name");
	  var Insured_City=$("#Insured_City").find("option:selected").attr("data-name");
	  var Insured_County=$("#Insured_County").find("option:selected").attr("data-name");
	  var insuredetailaddress=$("#insuredetailaddress").val();
	  var insureaddress=Insured_Province+","+Insured_City+","+Insured_County+"-"+insuredetailaddress;
	  var insurezipcode=$("#Insured_ZipCode").val();
	  
	  var deliveryname=$("#deliveryName").val();
	  var deliveryphone=$("#deliveryPhone").val();
	  var Delivery_Province=$("#Delivery_Province").val();
	  var Delivery_City=$("#Delivery_City").val();
	  var Delivery_County=$("#Delivery_County").val();
	  var deliverydetailaddress=$("#deliverydetailaddress").val();
	  var deliveryaddress=Delivery_Province+","+Delivery_City+","+Delivery_County+"-"+deliverydetailaddress;
	  
	  var insuranceperinfor=
	  {
			  "ownersname":ownersname,
		  	  "ownersphoneno":ownersphoneno,
		  	  "ownerscerticode":ownerscerticode,
		  	  "ownersaddress":ownersaddress,
		  	  "applicationname":applicationname,
		  	  "applicationphoneno":applicationphoneno,
		  	  "applicationcerticode":applicationcerticode,
		  	  "applicationaddress":applicationaddress,
		  	  "applicationzipcode":applicationzipcode,
		  	  "insurename":insurename,
		  	  "insurephoneno":insurephoneno,
		  	  "insurecerticode":insurecerticode,
		  	  "insureaddress":insureaddress,
		  	  "insurezipcode":insurezipcode,
		  	  "deliveryname":deliveryname,
		  	  "deliveryphone":deliveryphone,
		  	  "deliveryaddress":deliveryaddress
	  };
	  return insuranceperinfor;
  }
  
  //保费试算按钮触发
  function getQuote(){
	    checkLcnNo();//校验车牌号码
	    validateName();//校验车主姓名
	    validateVIN();//校验车架号
	    mobileNumber();//手机号码
	    identityCodeValid();//身份证
	    var ownersdetailaddress = $("#ownersdetailaddress").val();//车主详细地址
	    var modelName = $("#modelName").val();//品牌型号
	    var engno = $("#engno").val();//发动机
	    var registerdate = $("#registerdate").val();
	    var transferdate = $("#transferdate").val();
	    var syDate=document.getElementById("SY_DATE").value;
		var jqDate=document.getElementById("JQ_DATE").value;
		if(syDate=="" || syDate==null || syDate==undefined){
			$('#chackMess').html("商业险起保时间不能为空");
			$('.errorhei').show();
			$('#next').hide();
			return false;
		}
		if($("#CTPLCode").prop("checked")){
			if(jqDate=="" || jqDate==null || jqDate==undefined){
				$('#chackMess').html("交强险起保时间不能为空");
				$('.errorhei').show();
				$('#next').hide();
				return false;
			}
			
		}
	    if(checkOwnerNameFlag==1){
	    	$('#chackMess').html("车主姓名不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(checkOwnerNameFlag==2){
	    	$('#chackMess').html("车主姓名格式输入有误！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(ownersphoneNo==1){
	    	$('#chackMess').html("车主手机号码不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(ownersphoneNo==2){
	    	$('#chackMess').html("车主手机号码格式输入有误！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(owneridno==1){
	    	$('#chackMess').html("车主身份证号码不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(owneridno==2){
	    	$('#chackMess').html("车主身份证号码格式错误！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(ownersdetailaddress=="" || ownersdetailaddress==null ||ownersdetailaddress==undefined){
	    	$('#chackMess').html("车主详细地址不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(checkvincodeFlag==1){
	    	$('#chackMess').html("车架号不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(checkvincodeFlag==2){
	    	$('#chackMess').html("车架号必须为17位");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(checkvincodeFlag==3){
	    	$('#chackMess').html("车架号不能有O,Q,I,*字符");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(checkvincodeFlag==4){
	    	$('#chackMess').html("车架号只能是英文和数字组合");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if (modelName=="" || modelName==null || modelName==undefined){
	    	$('#chackMess').html("品牌名称不能为空");
	    	$('.beijing').hide();
	    	$('.errorhei').show();
	    	return false;
	    }else if(checkLcnNoFlag==1){
	    	$('#chackMess').html("车牌号码不能为空");
	    	$('.beijing').hide();
	    	$('.errorhei').show();
	    	return false;
	    }else if(checkLcnNoFlag==2){
	    	$('#chackMess').html("车牌号码格式有误！");
	    	$('.beijing').hide();
	    	$('.errorhei').show();
	    	return false;
	    }else if(engno=="" || engno==null || engno==undefined){
	    	$('#chackMess').html("发动机号不能为空！");
	    	$('.beijing').hide();
	    	$('.errorhei').show();
	    	return false;
	    }else if(registerdate=="" || registerdate==null || registerdate==undefined){
	    	$('#chackMess').html("车辆注册日期不能为空");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if($('#chgownerflag1').prop("checked") && (transferdate ==  null || transferdate == "" || transferdate==undefined)){
	    	$('#chackMess').html("过户日期不能为空");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }
	    
	  
	  
	  
	  
		//险种规则搭配
		if($("#modCode").prop("checked")||$("#vtplCode").prop("checked")||$("#theftCode").prop("checked")||$("#DriversCode").prop("checked")||$("#PassengerCode").prop("checked")){
				
		}else{
			$('#chackMess').html("必须选择一个主险投保");
			$('.errorhei').show();
			$('#next').hide();
			return false;
		}
		//不能单车损和选择了附加险不选择车损
		if($("#CombustionCode").prop("checked")||$("#GlassCode").prop("checked")){
			if($("#modCode").prop("checked")){
				if($("#vtplCode").prop("checked")||$("#theftCode").prop("checked")||$("#DriversCode").prop("checked")||$("#PassengerCode").prop("checked")){
						
				}else{
					$('#chackMess').html("不能单承保车损和附加险");
					$('.errorhei').show();
					$('#next').hide();
					return false;
				}
			}else if($("#PassengerCode").prop("checked")){
				if($("#DriversCode").prop("checked")){
					$('#chackMess').html("承保车损了才能承保附加险");
					$('.errorhei').show();
					$('#next').hide();
					return false;
				}else{
					$('#chackMess').html("承保车损了才能承保附加险+承保司机责任险才能承保乘客责任险");
					$('.errorhei').show();
					$('#next').hide();
					return false;
				}
				
			}else{
				$('#chackMess').html("承保车损了才能承保附加险");
				$('.errorhei').show();
				$('#next').hide();
				return false;
			}		
		}
		//不能单车损
		if($("#modCode").prop("checked")){
			if($("#vtplCode").prop("checked")||$("#theftCode").prop("checked")||$("#DriversCode").prop("checked")||$("#PassengerCode").prop("checked")){
					
			}else{
				$('#chackMess').html("不能单独投保车损险");
				$('.errorhei').show();
				$('#next').hide();
				return false;
			}
		}
		//必须选择了司机责任才能选择乘客
		if($("#PassengerCode").prop("checked")){
			if($("#DriversCode").prop("checked")){
				
			}else{
				$('#chackMess').html("承保司机责任险才能承保乘客责任险");
				$('.errorhei').show();
				$('#next').hide();
				return false;
			}
		}
		//目前不能承保无法找到第三方损失险和专车修理厂险
		if($("#ThirdPartyCode").prop("checked")||$("#CarCode").prop("checked")){
			$('#chackMess').html("很抱歉！目前不能投保'无法找到第三方损失险'和'专车修理厂险'险别");
			$('.errorhei').show();
			$('#next').hide();
			return false;
		}
     $("#prompt").html("保费计算中，请稍等...");     
	 load(20000);	
	 var userinfor=getUserinfor();
	 //验证手机用户	
	 $.ajax({
		 type:"POST",
		 url:getUrl()+"/csrLoginUser/checkPhoneExist.do",
		 data: JSON.stringify(userinfor),
		 dataType:"json",
		 contentType : 'application/json;charset=utf-8',
		 success: function(data){
			 if(data != null){
				 var userinfor=data;
				 var vhlinfor=getVhlinfor(); 	
				  var vhorderno=$("#csrPremiumOrderNo").val();
				   /* var	layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});*/
				  $.ajax({ 
					  type:"POST",
					  url:getUrl()+"/CsrPremiumCount/VhlSubmit.do?vhorderno="+vhorderno,
					  data: JSON.stringify(vhlinfor),
					  dataType:"json",
					  contentType : 'application/json;charset=utf-8',
					    success: function(data){
					    	if(data.baseinfor!=null){
					    		$("#csrPremiumOrderNo").val(data.baseinfor.orderno);
					    	}
						  	var coveragelist=getCoverage();
							var baseinfor=getBaseinfor();
							var result = new Array();
							result.push({userinfor:userinfor});
							result.push({vhlinfor:vhlinfor});
							result.push({coverageinfors:coveragelist});
							result.push({baseinfor:baseinfor});
							var insureData={"insureData":result};
					    	$.ajax({  
							    type: "POST",  
							    url: getUrl()+"/CsrPremiumCount/CsrpremiumCount.do",  
							    data: JSON.stringify(insureData),//将对象序列化成JSON字符串  
							    dataType:"json",  
							    contentType : 'application/json;charset=utf-8', //设置请求头信息  
							    success: function(data){
							    agreementinfors=data.agreementinfors;
							    var sypremium;
							    var jqpremium;
							    var currenttax;
							    var currenttax1;
							    var totalPremium;
							    var coverageinfors=data.coverageinfors;
							    coverageinforsave=data.coverageinfors;
							    /*  layer.close(layerIndex);*/
							   // 如果返回错误给予友好提示框
							   //var errorcode ="E00000030";
							      $("#pop").hide();
							   var errorcode ="E00000030";
							   var errorcode2 = "C99999999";
							   if(data.userinfor.errorCode==null && data.baseinfor.sypremium==null){								  
								   $("#chackMess").html("调用华安超时或没有返回,请稍后重试");
								   $('.errorhei').show();
							        $('#next').hide();
							   }else if(data.userinfor.errorCode==errorcode || data.userinfor.errorCode==errorcode2){
							/*        alert(data.userinfor.errorMessage); */
							         var errorMessage =data.userinfor.errorMessage;							         
							       $("#chackMess").html(errorMessage);
							        $('.errorhei').show();
							        $('#next').hide();
							   }else{								
								   $("#chackMess").html("保费计算成功");
								   $('.errorhei').show();
								   $("#submit").show();
							      $('#next').show();
							   }
							   
							   
							   if(data.baseinfor!=null){
							     if(data.baseinfor.sypremium!=null){
							      sypremium=data.baseinfor.sypremium;
							      $("#sypremium").text(sypremium);
							      $("#quotation").val(data.baseinfor.quoteno);
							      $("#applyTotalAdj").val(data.baseinfor.applyTotalAdj);
							     var claimAdjustRsn  = data.baseinfor.claimAdjustRsn;
								     if(claimAdjustRsn=='641001'){
								    	 $("#claimAdjustRsn").val("连续3年没有发生赔款");
								     }else if(claimAdjustRsn=='641002'){
								    	 $("#claimAdjustRsn").val("连续2年没有发生赔款");
								     }else if(claimAdjustRsn=='641003'){
								    	 $("#claimAdjustRsn").val("上年没有发生赔款");
								     }else if(claimAdjustRsn=='641004'){
								    	 $("#claimAdjustRsn").val("新保或上年发生1次赔款");
								     }else if(claimAdjustRsn=='641005'){
								    	 $("#claimAdjustRsn").val("上年发生2次赔款");
								     }else if(claimAdjustRsn=='641006'){
								    	 $("#claimAdjustRsn").val("上年发生3次赔款");
								     }else if(claimAdjustRsn=='641007'){
								    	 $("#claimAdjustRsn").val("上年发生4次赔款");
								     }else if(claimAdjustRsn=='641008'){
								    	 $("#claimAdjustRsn").val("上年发生5次及以上赔款");
								     }
								     
							      $("#b1").show();
							     }
							     
							      if(data.baseinfor.jqpremium!=null){
							      jqpremium=data.baseinfor.jqpremium;
							      $("#jqpremium").text(jqpremium);
							      $("#b2").show();
							     }else{
							    	 $("#jqpremium").text(""); 
							    	 $("#b2").hide();
							     }
							     if(data.baseinfor.totalPremium!=null){
							      totalPremium = data.baseinfor.totalPremium;
							      $("#totalPremium").text(totalPremium);
							     }else{
							    	 $("#totalPremium").text("");
							     }
							     
							     if(data.baseinfor.jqpolicystartdate!=null){
							       $("#appDate_1").val(data.baseinfor.jqpolicystartdate);
							       $("#syStartDate").val(data.baseinfor.jqpolicystartdate);
							       $("#syEndDate").val(data.baseinfor.jqpolicyenddate);
							     }
							     if(data.baseinfor.sypolicystartdate!=null){
							       $("#appDate").val(data.baseinfor.sypolicystartdate);
							       $("#jqStartDate").val(data.baseinfor.sypolicystartdate);
							       $("#jqEndDate").val(data.baseinfor.sypolicyenddate);
							     }
							   }
							   if(data.taxinfor!=null){
								    if(data.taxinfor.sumuptax!=null){
								     currenttax=data.taxinfor.sumuptax;
								     $("#currenttax").val(data.taxinfor.sumuptax);  
								     $("#currenttax1").text(currenttax);
								     $("#latefee").val(data.taxinfor.latefee);
								     $("#currentTax2").val(data.taxinfor.currenttax);
								     $("#b3").show();
								   }
							   }else{
								   $("#currenttax1").text("");
								   $("#b3").hide();
							   }
							   if(coverageinfors!=null){
							   for(var i=0;i<coverageinfors.length;i++){
							    var coveragr=coverageinfors[i];
							    if(coveragr.insrnccode==="0357"){
							     if(coveragr.premium!=null){
							      $("#CTPLQuota").val(coveragr.suminsured);
							      $("#CTPLPremium").val(coveragr.premium);
							      }
							    }
							     if(coveragr.insrnccode==="030101"){
							      $("#modQuota").val(coveragr.suminsured);
							      $("#modQuotaPremium").val(coveragr.premium);
							      $("#modductiblePremium").val(coveragr.nyl12);
							    }
							     if(coveragr.insrnccode==="030102"){
							      $("#vtplPremium").val(coveragr.premium);
							      $("#vtplductiblePremium").val(coveragr.nyl12);
							    }
							     if(coveragr.insrnccode==="030103"){
							       $("#theftQuota").val(coveragr.suminsured);
							       $("#theftPremium").val(coveragr.premium);
							       $("#theftductiblePremium").val(coveragr.nyl12);
							    }
							     if(coveragr.insrnccode==="030104"){
							       $("#DriversPremium").val(coveragr.premium);
							       $("#DriversductiblePremium").val(coveragr.nyl12);
							    }
							     if(coveragr.insrnccode==="030105"){
							       $("#PassengerPremium").val(coveragr.premium);
							      $("#PassengerductiblePremium").val(coveragr.nyl12);
							    }
							     if(coveragr.insrnccode==="030108"){
							      $("#CombustionQuota").val(coveragr.suminsured);
							       $("#CombustionPremium").val(coveragr.premium);
							       $("#CombustionductiblePremium").val(coveragr.nyl12);
							    }
							     if(coveragr.insrnccode==="030107"){
							       $("#GlassQuotaPremium").val(coveragr.premium);   
							    }       
							   }
							   }
							     if(sypremium==null || sypremium==undefined){
							       sypremium=0.00;
							    }
							     if(jqpremium==null || jqpremium==undefined){
							      jqpremium=0.00;
							    }
							     if(currenttax==null || currenttax==undefined){
							      currenttax=0.00;
							      currenttax1=0.00;
							    }
							    totalPremium=parseFloat(sypremium)+parseFloat(jqpremium)+parseFloat(currenttax);
							    if(totalPremium==0 || totalPremium==null || totalPremium==undefined){
							    	
							    }else{
							    	 $("#totalPremium").text(totalPremium.toFixed(2)); 
							    }
							   
							    
							    },
							    error: function(res){  
							 
							    }  
							}); 
					    }
				  });
			 }else{
				 $('#chackMess').html("车主手机号输入有误.");
			     $('.errorhei').show();
			     $('.beijing').hide();
			 }
		 }
		 
	 });
	 
	}
//提核按钮触发
  function submitform(){
	    validateName();//校验姓名
	    mobileNumber();//手机号码
	    identityCodeValid();//身份证
	    var appaddress = $("#applicationdetailaddress").val();//投保人地址
	    var inaddress = $("#insuredetailaddress").val();//被保人地址
	    var deliveryaddress = $("#deliverydetailaddress").val();//配送地址
	    if(checkApplicationNameFlag==1){
	    	$('#chackMess').html("投保人姓名不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(checkApplicationNameFlag==2){
	    	$('#chackMess').html("投保人姓名格式输入有误！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(appPhoneNo==1){
	    	$('#chackMess').html("投保人手机号码不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(appPhoneNo==2){
	    	$('#chackMess').html("投保人手机号码格式输入有误！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(appidno==1){
	    	$('#chackMess').html("投保人身份证号码不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(appidno==2){
	    	$('#chackMess').html("投保人身份证号码错误！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(appaddress==null || appaddress=="" || appaddress==undefined){
	    	$('#chackMess').html("投保人详细地址不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(checkinNameFlag==1){
	    	$('#chackMess').html("被保人姓名不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(checkinNameFlag==2){
	    	$('#chackMess').html("被保人姓名格式输入有误！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(inphoneNo==1){
	    	$('#chackMess').html("被保人手机号码不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(inphoneNo==2){
	    	$('#chackMess').html("被保人手机号码格式输入有误！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(inidno==1){
	    	$('#chackMess').html("被保人身份证号码不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(inidno==2){
	    	$('#chackMess').html("被保人身份证号码错误！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(inaddress==null || inaddress=="" || inaddress==undefined){
	    	$('#chackMess').html("被保人详细地址不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(checkdeliveryNameFlag==1){
	    	$('#chackMess').html("配送人姓名不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(checkdeliveryNameFlag==2){
	    	$('#chackMess').html("配送人姓名格式输入有误！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(deliveryPhoneNo==1){
	    	$('#chackMess').html("配送人手机号码不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(deliveryPhoneNo==2){
	    	$('#chackMess').html("配送人手机号码格式输入有误！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }else if(deliveryaddress==null || deliveryaddress=="" || deliveryaddress==undefined){
	    	$('#chackMess').html("配送人详细地址不能为空！");
	    	$('.errorhei').show();
	    	$('.beijing').hide();
	    	return false;
	    }		
	  
	  
	  
//	  var coveragelist=getCoverage();
	  var taxinfor=getTaxinfor();
	  var baseinfor=getBaseinfor();
	  var result = new Array();
	  result.push({taxinfor:taxinfor});
	  result.push({coverageinfors:coverageinforsave});
	  result.push({baseinfor:baseinfor});
	  result.push({agreementinfors:agreementinfors});
	  var insureData={"insureData":result};
	  var insuranceperinfor=getInsuranceperinfor();
	  var quotation =  $("#quotation").val();
	  $("#prompt").html("正在提交，请稍等...");	  
		 load(10000);	
	  if(quotation!==null && quotation!==""){
		  $.ajax({//保存险种信息并修改订单状态起保时间以及新增特约表数据
			  	type: "POST",  
			    url: getUrl()+"/CsrPremiumCount/CsrCoverageinforsDateSave.do?quotano="+quotation,  
			    data: JSON.stringify(insureData),//将对象序列化成JSON字符串  
			    dataType:"json",  
			    contentType : 'application/json;charset=utf-8', //设置请求头信息  
			  /*  beforeSend: function () {
			    	this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
			    },*/
			    success: function(data){
			    	var deliveryinfor=getDeliveryinfor();
			    	var backbaseinfor=data.baseinfor;
			    	var requestresult=new Array();
			    	requestresult.push({deliveryinfor:deliveryinfor});
			    	requestresult.push({baseinfor:backbaseinfor});
			    	requestresult.push({insuranceperinfor:insuranceperinfor});
			    	var requestdata={"requestdata":requestresult};
			    		$.ajax({//保存人员信息并提核  
						    type: "POST",  
						    url: getUrl()+"/CsrPremiumCount/CsrUnderwriting.do?quotano="+quotation,  
						    data: JSON.stringify(requestdata),//将对象序列化成JSON字符串  
						    dataType:"json",  
						    contentType : 'application/json;charset=utf-8', //设置请求头信息  
/*						    beforeSend: function () {
						    	this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
						    },*/
						    success: function(data){
						      /*layer.close(this.layerIndex);*/
						 
						   // 如果返回错误给予友好提示框
						      $("#pop").hide();
						   var errorcode ="E00000030";
						   var errorcode2 = "C99999999";
						   if(data.userinfor.errorCode==errorcode || data.userinfor.errorCode==errorcode2){
						/*        alert(data.userinfor.errorMessage); */
						         var errorMessage =data.userinfor.errorMessage;						        
						       $("#chackMess").html(errorMessage);
						        $('.errorhei').show();
						   }else if(data.baseinfor.syapplicationno!==null && data.baseinfor.jqapplicationno==null){							   
					    	    $("#chackMess").html("提核成功！商业险投保单:"+data.baseinfor.syapplicationno);
						        $('.errorhei').show();
						        baseinfors = data.baseinfor;
						        $("#quote").hide();
						        $("#submit").hide();
						        $("#payment").show();
					      }else if(data.baseinfor.syapplicationno!==null && data.baseinfor.jqapplicationno!==null){					    
					    	  $("#chackMess").html("提核成功！商业险投保单:"+data.baseinfor.syapplicationno+"交强险投保单:"+data.baseinfor.jqapplicationno);
						        $('.errorhei').show();						       
						        baseinfors = data.baseinfor;
						        $("#quote").hide();
						        $("#submit").hide();
						        $("#payment").show();
					      }else{
						      $('.errorhei').hide();
						   }
						    },
						    error: function(res){  
						 
						    }  
						});
			    },
			    error: function(res){  
			 
			    }
		  });
	}  
	  
  }
  
 //支付申请
  function paymentinfor(){
	  $("#prompt").html("正在加载，请稍等...");	  
		 load(20000);	
	  $.ajax({//保存险种信息并修改订单状态起保时间以及新增特约表数据
		  	type: "POST",  
		    url: getUrl()+"/CsrPremiumCount/Csrpaymentinfor.do",  
		    data: JSON.stringify(baseinfors),//将对象序列化成JSON字符串  
		    dataType:"json",  
		    contentType : 'application/json;charset=utf-8', //设置请求头信息  
/*		    beforeSend: function () {
		    	this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 10*1000});
		    },*/
		    success: function(data){
		    	 /*layer.close(this.layerIndex);*/
		    	 var url;
		    	 if(data.paymentinfor!==null){
		    		 url=data.paymentinfor.paymenturl;
			    	if(url!==null){
			    		$("#pop").hide();
			    		$("#chackMess").html("支付申请成功！链接地址为 : "+url);
				        $('.errorhei').show();
//			    		window.location.href=url;
			    	}
		    	 }
		    },
		    error: function(res){  
		 
		    }
	  });
  }
  
  
//关闭错误信息
	$('.errortan3').click(function(){
	    $('.errorhei').hide();
	
	})

//车型查询（车架号查询）
function modelSelect(){
	   var modleName=$("#modelName").val();
	   var vinNo=$("#vinno").val();
	   var orderNo='LD20161205002245';
	   var vhldata={vehiclename:modleName,baseinforOrdeo:orderNo};
	   var vhldata1={vinno:vinNo,baseinforOrdeo:orderNo};
	   $("#prompt").html("正在搜索，请稍等...");	  
		 load(20000);	
	   if(modleName!=='' && modleName!==null && vinNo=='' || vinNo==null){
		   $.ajax({  
			type: "POST",  
			url: getUrl()+"/vehicleInfor/modelSerachByName.do",  
			data: JSON.stringify(vhldata),//将对象序列化成JSON字符串  
			dataType:"json", 
			async:false,
			contentType : 'application/json;charset=utf-8', //设置请求头信息  
			/*beforeSend: function () {
		    	this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
		    },*/
			success: function(data){ 
				
				/*layer.close(this.layerIndex);*/
			    if(data.length==0){
			    	$("#pop").hide();
			    	    $('.conModel4').html("");
			            $('.conModel4').append('<p  style="color:red;font-size: 16px;margin-left: 230px;">没搜索到！</p>');
			        }else{
			        	$("#pop").hide();
			            $('.conModel4').html("");
			            for(var i= 0;i<data.length;i++){
			            	$('.conModel4').append('<li><i></i><div class="conModel5"><span class="right">'+data[i].VehicleName+'</span><span class="right">'+data[i].Remark+'</span><span class="right">'+data[i].MarketDate+'</span><span class="right">'+data[i].Seat+'</span>座<span class="right">'+data[i].Displacement+'</span>&nbsp;&nbsp;&nbsp;参考价格:<span class="right">'+data[i].PurchasePrice+'</span><span class="right" style="display:none">'+data[i].VehicleCode+'</span><span class="right" style="display:none">'+data[i].BrandName+'</span></div></li>');
			            };
			        }
			    $('.conModel1').show();
			    },  
		    error: function(res){  
		    }  
		   });
	   }else if(vinNo!=='' && vinNo!==null && modleName=='' || modleName==null){
		   $.ajax({  
			    type: "POST",  
			    url: getUrl()+"/vehicleInfor/modelSerachByVin.do",  
			    data: JSON.stringify(vhldata1),//将对象序列化成JSON字符串  
			    dataType:"json",  
			    contentType : 'application/json;charset=utf-8', //设置请求头信息  
			  /*  beforeSend: function () {
			    	this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
			    },*/
			    success: function(data){  
			    	/*layer.close(this.layerIndex);*/
			    	if(data.length==0){
			    		$("#pop").hide();
			    		$('.conModel4').html("");
			            $('.conModel4').append('<p  style="color:red;font-size: 16px;margin-left: 230px;">没搜索到！请使用车型来车型</p>');
			        }else{
			        	   $("#pop").hide();
				        	$('.conModel4').html("");
				            for(var i= 0;i<data.length;i++){
				            	$('.conModel4').append('<li><i></i><div class="conModel5"><span class="right">'+data[i].VehicleName+'</span><span class="right">'+data[i].Remark+'</span><span class="right">'+data[i].MarketDate+'</span><span class="right">'+data[i].Seat+'</span>座<span class="right">'+data[i].Displacement+'</span>&nbsp;&nbsp;&nbsp;参考价格:<span class="right">'+data[i].PurchasePrice+'</span><span class="right" style="display:none">'+data[i].VehicleCode+'</span><span class="right" style="display:none">'+data[i].BrandName+'</span></div></li>');
				            };
			            }
			    	$('.conModel1').show();
			    },  
			    	error: function(res){  
			    }  
			   });	
	   }else if(vinNo!=='' && vinNo!==null && modleName!=='' || modleName!==null){
		   var layerIndex;
		   $.ajax({  
			    type: "POST",  
			    url: getUrl()+"/vehicleInfor/modelSerachByVin.do",  
			    data: JSON.stringify(vhldata1),//将对象序列化成JSON字符串  
			    dataType:"json",  
			    contentType : 'application/json;charset=utf-8', //设置请求头信息  
/*			    beforeSend: function () {
			    	layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
			    },*/
			    success: function(data){ 
			    	/*layer.close(layerIndex);*/
			    	if(data.length==0){
			 		   $.ajax({  
			 				type: "POST",  
			 				url: getUrl()+"/vehicleInfor/modelSerachByName.do",  
			 				data: JSON.stringify(vhldata),//将对象序列化成JSON字符串  
			 				dataType:"json",  
			 				contentType : 'application/json;charset=utf-8', //设置请求头信息  
			 				success: function(data){ 
			 					/*layer.close(layerIndex);*/
			 				    if(data.length==0){
			 				    	$("#pop").hide();
			 				    	    $('.conModel4').html("");
			 				            $('.conModel4').append('<p  style="color:red;font-size: 16px;margin-left: 230px;">没搜索到！</p>');
			 				            $('.conModel1').show();
			 				        }else{
			 				        	$("#pop").hide();
			 				            $('.conModel4').html("");
			 				            for(var i= 0;i<data.length;i++){
			 				            	$('.conModel4').append('<li><i></i><div class="conModel5"><span class="right">'+data[i].VehicleName+'</span><span class="right">'+data[i].Remark+'</span><span class="right">'+data[i].MarketDate+'</span><span class="right">'+data[i].Seat+'</span>座<span class="right">'+data[i].Displacement+'</span>&nbsp;&nbsp;&nbsp;参考价格:<span class="right">'+data[i].PurchasePrice+'</span><span class="right" style="display:none">'+data[i].VehicleCode+'</span><span class="right" style="display:none">'+data[i].BrandName+'</span></div></li>');
			 				            };
			 				           $('.conModel1').show();
			 				        }
			 				    },  
			 			    error: function(res){  
			 			    }  
			 			   });
			        }else{
			        	    $("#pop").hide();
				        	$('.conModel4').html("");
				            for(var i= 0;i<data.length;i++){
				            	$('.conModel4').append('<li><i></i><div class="conModel5"><span class="right">'+data[i].VehicleName+'</span><span class="right">'+data[i].Remark+'</span><span class="right">'+data[i].MarketDate+'</span><span class="right">'+data[i].Seat+'</span>座<span class="right">'+data[i].Displacement+'</span>&nbsp;&nbsp;&nbsp;参考价格:<span class="right">'+data[i].PurchasePrice+'</span><span class="right" style="display:none">'+data[i].VehicleCode+'</span><span class="right" style="display:none">'+data[i].BrandName+'</span></div></li>');
				            };
				            $('.conModel1').show();
			            }
			    },  
			    	error: function(res){  
			    }  
			   });	
	   }  
}

//选中选择的车型
$('.conModel4').on('click',"li",function(){
	var text1=$(this).children('.conModel5').children().eq(0).html();
	var text2=$(this).children('.conModel5').children().eq(1).html();
	var text3=$(this).children('.conModel5').children().eq(2).html();
	var text4=$(this).children('.conModel5').children().eq(3).html();
	var text5=$(this).children('.conModel5').children().eq(4).html();
	var text6=$(this).children('.conModel5').children().eq(5).html();
	var text7=$(this).children('.conModel5').children().eq(6).html();
	var text8=$(this).children('.conModel5').children().eq(7).html();
    $("#marketyear").val(text3);
    $("#setno").val(text4);
    $("#displacement").val(text5);
    $("#vhlval").val(text6);
    $("#brandcode").val(text7);
    $("#brandName").val(text8);
    $('#modelName').val(text1);
    /*$('#model1').html(text2+"&nbsp;"+text3+"&nbsp;"+text4+"&nbsp;"+text5+"&nbsp;"+text6);*/
    $('#model1').append('<span class="right">'+text2+'&nbsp;</span><span class="right">'+text3+'&nbsp;</span><span class="right">'+text4+'座&nbsp;</span><span class="right">'+text5+'&nbsp;</span><span class="right">参考价格:'+text6+'</span>');
    if( $('#model').val()!==""){
    	$('#vehicleModel').hide();
    }
});

//初始化时区域显示
$('html').ready(function() {
	var deptcode = $('#deptcode1').val();
	$.ajaxSettings.async = false;
    $.getJSON(getUrl()+'/views/quicksure/scripts/json/department.json',
    function(data) {
        province = data;
    	var idList = new Array("Travel","Owner","Application","Insured","Delivery"); //地址初始化的影响范围：车主信息、投保人信息、被保人信息、配送信息
    	var provinceList = new Array(5); //初始化信息Province的影响范围：车主省份、投保人省份、被保人省份、配送省份
    	var cityList = new Array(5); //初始化信息City的影响范围：车主城市、投保人城市、被保人城市、配送城市
    	var countyList = new Array(5); //初始化信息County的影响范围：车主County、投保人County、被保人County、配送County
        for(var y = 0; y < idList.length; y++){
        	var id = idList[y];
        	for(var o=0; o < provinceList.length; o++){
	        	if(provinceList[o]=="" || provinceList[o] == null || "undefined"==typeof(provinceList[o])){
	        		provinceList[o] = 440000;
	        	}
	        	if(cityList[o]=="" || cityList[o]==null || "undefined"==typeof(cityList[o])){
	        		cityList[o] = 440300;
	        	}
	        	if(countyList[o]=="" || countyList[o]==null || "undefined"==typeof(countyList[o])){
	        		countyList[o] = 440303;
	        		$("#code").val(countyList[o]);
	        	}
	        }
	        for (var i = 0; i < data.length; i++) {
	        	var a,b,c;
	            if (data[i].parentid == '100000') { //省级                
	                a = {
	                    n: data[i].pinyin,
	                    Value: data[i].deptinforid,
	                    name: data[i].name
	                };
	                if (provinceList[y] == data[i].deptinforid) {
	                    $("#" + id + "_Province").append("<option value=" + a.Value + ' name=' + a.n + " data-name=" + data[i].name + " selected>" + data[i].name + '</option>');
	                } else {
	                    $("#" + id + "_Province").append("<option value=" + a.Value + ' name=' + a.n + " data-name=" + data[i].name + ">" + data[i].name + '</option>');
	                }
	            } else if (data[i].parentid == provinceList[y]) { //市级
	                b = {
	                    n: data[i].pinyin,
	                    Value: data[i].deptinforid,
	                    name: data[i].name
	                };
	                if (cityList[y] == data[i].deptinforid ) {
	                    $("#" + id + "_City").append("<option value=" + b.Value + ' name=' + b.n + " data-name=" + data[i].name + " selected>" + data[i].name + '</option>');
	                }else{
		                $("#" + id + "_City").append("<option value=" + b.Value + ' name=' + b.n + " data-name=" + data[i].name + ">" + data[i].name + '</option>');
	                }
	            } else if (data[i].parentid == cityList[y]) { //镇级
	                c = {
	                    n: data[i].pinyin,
	                    Value: data[i].deptinforid,
	                    name: data[i].name,
	                    zip: data[i].zipcode
	                };
	                if(countyList[y] == data[i].detinforid){
		                $("#" + id + "_County").append("<option value=" + c.Value + ' name=' + c.n + " data-name=" + data[i].name + " zipCode=" + data[i].zipcode + "selected>" + data[i].name + '</option>');
	                }else{
	                	$("#" + id + "_County").append("<option value=" + c.Value + ' name=' + c.n + " data-name=" + data[i].name + " zipCode=" + data[i].zipcode + ">" + data[i].name + '</option>');
	                }
	            }
	        }
	        var data_name_1 = $("#" + id + "_Province").children(':selected').attr('data-name');
	        var data_name_2 = $("#" + id + "_City").children(':selected').attr('data-name');
	        var data_name_3 = $("#" + id + "_County").children(':selected').attr('data-name');
	        var text_4 = $("#" + id + "_County").children(':first').attr('zipCode');
	        $("#" + id + "_Address").val(data_name_1 + ' ' + data_name_2 + ' ' + data_name_3);
	       	$("#" + id + "_ZipCode").val(text_4);
        }   	
    });
    
    //地址回填
    if(deptcode!=''&&deptcode!=null){
    	var zipcode1 ;
    	for ( var i = 0; i < province.length; i++) {
			if(province[i].deptinforid==deptcode){
				countyNo = province[i].deptinforid;
				cityNo = province[i].parentid;
				provinceNo = deptcode.slice(0,2)+"0000";
				zipcode1 = province[i].zipcode;
			}
		}
    	copyArea("Travel",provinceNo,cityNo,countyNo);
    	copyArea("Owner",provinceNo,cityNo,countyNo);
    	$('#code').val(countyNo);
    	$('#Owner_ZipCode').val(zipcode1);
    }
});

var provinceNo=440000;
var cityNo =440300;
var countyNo=440303;
var arr = []; //点击复制同车主信息
var area = []; //存放地址的省code、市code、镇code
//投保人信息和车主信息同步
$('.Policy_holder_p>input').click(function() {
    if ($(this).attr('checked') != null) {
    	$('.Area select').each(function(i) {
            area[i] = $(this).val();
        });
    	$('.car_user_content td input:text').each(function(i) {
            arr[i] = $(this).val();
          });
          var Class_input = "." + $(this).parent().parent().attr("class") + " input:text";
          $(Class_input).each(function(i) {
            $(this).val(arr[i]);
          })
        copyArea("Application",provinceNo,cityNo,countyNo);
    }
});
//被保人信息和车主信息同步
$('.Insured_content_p>input').click(function() {
    if ($(this).attr('checked') != null) {
    	$('.Area select').each(function(i) {
            area[i] = $(this).val();
        });
    	$('.car_user_content td input:text').each(function(i) {
            arr[i] = $(this).val();
          });
          var Class_input = "." + $(this).parent().parent().attr("class") + " input:text";
          $(Class_input).each(function(i) {
            $(this).val(arr[i]);
          })
        copyArea("Insured",provinceNo,cityNo,countyNo);
    }
});
//配送信息和车主信息同步
$('.Delivery_content_p>input').click(function() {
    if ($(this).attr('checked') != null) {
    	$('.Area select').each(function(i) {
            area[i] = $(this).val();
        });
    	$('.car_user_content td input:text').each(function(i) {
            arr[i] = $(this).val();
          });
          var Class_input = "." + $(this).parent().parent().attr("class") + " input:text";
          $(Class_input).each(function(i) {
            $(this).val(arr[i]);
          })
        copyArea("Delivery",provinceNo,cityNo,countyNo);
    }
});


//地址复制
function copyArea(id,provincecode,citycode,countycode){
	$("#" + id + "_Province").html("");
	$("#" + id + "_City").html("");
	$("#" + id + "_County").html("");
	var k;
    for (var i = 0; i < province.length; i++) { //市级
    	if (province[i].parentid == '100000') { //省级                
	        a = {
	            n: province[i].pinyin,
	            Value: province[i].deptinforid,
	            name: province[i].name
	        };
	        if (provincecode == province[i].deptinforid) {
	            $("#" + id + "_Province").append("<option value=" + a.Value + ' name=' + a.n + " data-name=" + province[i].name + " selected>" + province[i].name + '</option>');
	        } else {
	            $("#" + id + "_Province").append("<option value=" + a.Value + ' name=' + a.n + " data-name=" + province[i].name + ">" + province[i].name + '</option>');
	        }
    	}else if(province[i].parentid == provincecode) {
            b = {
                n: province[i].pinyin,
                Value: province[i].deptinforid,
                name: province[i].name
            };
            if(province[i].deptinforid == citycode){
            	$("#" + id + "_City").append("<option value=" + b.Value + ' name=' + b.n + " data-name=" + province[i].name +" selected>" + province[i].name + '</option>');
            }else{
	            $("#" + id + "_City").append("<option value=" + b.Value + ' name=' + b.n + " data-name=" + province[i].name +">" + province[i].name + '</option>');
            }
        }else if(province[i].parentid == citycode){ 
            m = {
                n: province[i].pinyin,
                Value: province[i].zipcode,
                name: province[i].name
            };
            if(province[i].deptinforid == countycode){
            	$("#" + id + "_County").append("<option value=" + m.Value + ' name=' + m.n + " data-name=" + province[i].name + " selected>" + province[i].name + '</option>');
            	break;
            }else{
	            $("#" + id + "_County").append("<option value=" + m.Value + ' name=' + m.n + " data-name=" + province[i].name + ">" + province[i].name + '</option>');
            }
        
        }
    }
}

//省级改变   
function provinceChange(id, value) {
	provinceNo = value;
    var idArray = id.split("_");
    id = idArray[0];
    $("#" + id + "_City").html("");
    $("#" + id + "_Address").val("");
    for (var i = 0; i < province.length; i++) { //市级
        if (province[i].parentid == value) {
            m = {
                n: province[i].pinyin,
                Value: province[i].deptinforid,
                name: province[i].name
            };
            $("#" + id + "_City").append('<option value=' + m.Value + ' name=' + m.n + '>' + province[i].name + '</option>');
        }
    }
    var cityFirstValue = $("#" + id + "_City").children('option:first').val();
    cityChange(id + "_City", cityFirstValue);

}

//市级改变
function cityChange(id, value) {
	cityNo = value;
    var idArray = id.split("_");
    id = idArray[0];
    $("#" + id + "_County").html("");
    $("#" + id + "_Address").val("");
    for (var i = 0; i < province.length; i++) { //市级
        if (province[i].parentid == value) {

            m = {
                n: province[i].pinyin,
                Value: province[i].zipcode,
                name: province[i].name
            };
            $("#" + id + "_County").append("<option value=" + m.Value + ' name=' + m.n + " data-name=" + m.name + ">" + m.name + '</option>');

        }
    }
    var address = $("#" + id + "_Province").children('option:selected').text() + $("#" + id + "_City").children('option:selected').text() + $("#" + id + "_County").children('option:selected').text();
    var zipcode = $("#" + id + "_County").val();
    $("#" + id + "_Address").val(address);
    $("#" + id + "_ZipCode").val(zipcode);
    if(id=="Travel"){
    	for(var i=0;i<province.length; i++){
        	if (province[i].zipcode == zipcode) {
        		m = {
                        n: province[i].deptinforid,
                    };
        		$("#code").val(m.n);
        	}
        }
    }
    

}

//镇级改变
function townChange(id, value) {
	countyNo=value;
    var idArray = id.split("_");
    var zipcode = null;
    id = idArray[0];
    $("#" + id + "_Address").val("");
    for (var i = 0; i < province.length; i++) { //市级
        if (province[i].deptinforid == value) {
           zipcode = province[i].zipcode;
           break;
        }
    }
    var address = $("#" + id + "_Province").children('option:selected').text() + $("#" + id + "_City").children('option:selected').text() + $("#" + id + "_County").children('option:selected').text();
    var zipcode;
    if($("#" + id + "_County").children('option:selected').attr("zipcode")!==undefined){
      zipcode = $("#" + id + "_County").children('option:selected').attr("zipcode");
    }else{
      zipcode = $("#" + id + "_County").val();
    }
     
    $("#" + id + "_Address").val(address);
    $("#" + id + "_ZipCode").val(zipcode);
    if(id=="Travel"){
    	for(var i=0;i<province.length; i++){
        	if (province[i].zipcode == zipcode) {
        		m = {
                        n: province[i].deptinforid,
                    };
        		$("#code").val(m.n);
        	}
        }
    }
    
}
//加载框
function load(outtimes) {
	 $("#pop").show();
    setTimeout(function() {
        $('#pop').hide()
    },
    outtimes);
}

