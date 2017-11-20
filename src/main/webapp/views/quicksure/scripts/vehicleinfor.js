var idno=0;
var checkLcnNoFlag=false;//车牌号码
var checkOwnerNameFlag=false;//车主名字
var checkvincodeFlag1=false;
var checkvincodeFlag2=false;
var checkvincodeFlag3=false;
var checkNewcarRegistdate=false;
var iswechatBrowser=false;

$('html').ready(function() {
	$("#pop").hide();
	//点击上一步的时候反向绑定图片
	$("#pop").hide();
	if($('#chgownerflag1').attr('checked') != null){
	    $('#chgownerflag1').parent().click();
	}
	if($('#chgownerflag2').attr('checked') != null){
		$('#chgownerflag2').parent().click();
	}
	var  modelDescription=$(".Inpput_I").val()+$(".Inpput_II").val()+$(".Inpput_III").val();
	if(modelDescription==null ||modelDescription==""){
		var marketyear=document.getElementById("marketyear").value;
	    var displacement=document.getElementById("displacement").value;
	    var vhlval=document.getElementById("vhlval").value;
	    var model=document.getElementById("model").value;
	    var setno=document.getElementById("setno").value;
	    if(model==""){
	    	
	    }else{
/*	    	$(".Inpput_I").val(model);
		    $(".Inpput_II").val(displacement+" "+setno+" "+vhlval)*/
	    	//续保单子不给于配置信息，需重新查询
	    }
	    
	}
	 var ua = window.navigator.userAgent.toLowerCase();
	    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
	    	iswechatBrowser=true;
	    }else{
	    	iswechatBrowser=false;
	    }
	if(iswechatBrowser){//如果是微信浏览器就去配置微信JS接口
		var currenturl=location.href.split('#')[0];
		 $.ajax({  
				type: "POST",  
				url: getUrl()+"/WechatLogin/getWechatJSAccess.do",  
				data:currenturl ,//将对象序列化成JSON字符串  
				dataType:"json",  
				contentType : 'application/json;charset=utf-8', //设置请求头信息  		
				success: function(data){ 
					wx.config({
					    debug: false, 
					    appId: data.appid, 
					    timestamp:data.timestamp ,
					    nonceStr: data.nonceStr, 
					    signature: data.signature.toLowerCase(),
					    jsApiList: ['chooseImage','uploadImage'] 
					});
				    },  
			    error: function(res){
			    	 $("#pop").hide();
			     	 $("#Message").html("微信登录失败");
			     	 $('.errorhei').show();
			     	 $('.beijing').hide();
			     	  //Hide();
			    }  
			   });	
	}	
}
)
//车牌号校验
function checkLcnNo() {
	var car_num = document.getElementById('lcno').value;
	if("*-*"!==car_num){
	var reg = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
	if (reg.test(car_num) != true) {		
    	checkLcnNoFlag=false;
	} else {
		checkLcnNoFlag=true;
	}
    }else if("*-*"==car_num){
    	checkLcnNoFlag=true;
    }
}

//新车注册日期校验
function checkRegistdate(){
	//北京上海相差7天,其他相差9个月
	var carnumber=document.getElementById('lcno').value;
	var registerdate=document.getElementById("registerdate").value;
	var citycode = $("#citycode").val();
	var test=citycode.substring(0,3);
	if("*-*"==carnumber){//新车
		if(test=='110' || test=='310'){//北京上海编码以110 310开头
			var dd = new Date();  
	        dd.setDate(dd.getDate()-7);//获取七天前的日期  
	        var y = dd.getFullYear();   
	        var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0  
	        var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate();//获取当前几号，不足10补0  
	        var dayBefore=y+"-"+m+"-"+d;
	        if(registerdate<dayBefore){
	        	checkNewcarRegistdate=false;
	        }else{
	        	checkNewcarRegistdate=true;
	        };
		}else{
			var dd1 = new Date();  
			dd1.setMonth(dd1.getMonth()-9);//获取9个月前的日期  
	        var y = dd1.getFullYear();   
	        var m = (dd1.getMonth()+1)<10?"0"+(dd1.getMonth()+1):(dd1.getMonth()+1);//获取当前月份的日期，不足10补0  
	        var d = dd1.getDate()<10?"0"+dd1.getDate():dd1.getDate();//获取当前几号，不足10补0  
	        var dayBefore=y+"-"+m+"-"+d;
	        if(registerdate<dayBefore){
	        	checkNewcarRegistdate=false;
	        }else{
	        	checkNewcarRegistdate=true;
	        };
		};
	}else{
		checkNewcarRegistdate=true;
	}
}

//去除字符串中的空格
function Trim(obj){
	var str = obj.value;
    var a = str.replace(/<\/?[^>]*>/gim,"");//去掉所有的html标记
    var b = a.replace(/(^\s+)|(\s+$)/g,"");//去掉前后空格
    var c = b.replace(/\s/g,"");//去除文章中间空格
    obj.value=c;
}
//车主姓名校验
function validateName(){
	var val=document.getElementById('drvowner').value;
	var reg = /^[\u4e00-\u9fa5]{2,4}$/i; 
	if(val==''||val==null||val==undefined){
		checkOwnerNameFlag=false;		
	}else if(!reg.test(val)){
		checkOwnerNameFlag=false;		
	}else{

		checkOwnerNameFlag=true;
	}
}
//车架号校验
function validateVIN() {
	var vinlimit = /^\w*[a-zA-Z]+\w*$/;
	var vinCode=document.getElementById('vinno').value;
	if(vinCode.length>17 || vinCode.length<17){
		checkvincodeFlag1=false;
		return false;
	}else if((vinCode.indexOf('Q') >= 0) || (vinCode.indexOf('I') >= 0) || (vinCode.indexOf('O') >= 0) || (vinCode.indexOf('*') >= 0)){
		checkvincodeFlag1=true;
		checkvincodeFlag2=false;
	    return false;
    }else if(!vinlimit.test(vinCode)){
    	checkvincodeFlag1=true;
		checkvincodeFlag2=true;
    	checkvincodeFlag3=false;
    }else{
    	checkvincodeFlag1=true;
    	checkvincodeFlag2=true;
    	checkvincodeFlag3=true;
    	//searchByVIN();
    }
}


//车型查询校验
function validateModel(){
	var str = $("#model").val();
	if(str!="" && str!=null && typeof(str)!=undefined){
		searchByModel();
	}
}

$("#modelSerach").click(function(){
	var vinno=document.getElementById("vinno").value;
	var model=document.getElementById("model").value;
	validateVIN();
	if (vinno=="" || vinno==null || vinno==undefined){
    	$('#Message').html("车架号不能为空");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();    	
    	return false;
    }else if(!checkvincodeFlag1){
    	$('#Message').html("车架号必须为17位");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if(!checkvincodeFlag2){
    	$('#Message').html("车架号不能有O,Q,I,*字符");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if(!checkvincodeFlag3){
    	$('#Message').html("车架号只能是英文和数字组合");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if (model=="" || model==null || model==undefined){
    	$('#Message').html("品牌名称不能为空");
    	$('.beijing').hide();
    	$('.errorhei').show();
    	//Hide();
    	return false;
    }else{
//    	searchByVIN();	
    	modelSearch();
    }
})
//提交表单
function submitForm(){
    var lcnno=document.getElementById("lcno").value;
    var registerdate=document.getElementById("registerdate").value;
    var vinno=document.getElementById("vinno").value;
    var model=document.getElementById("model").value;
    var engno=document.getElementById("engno").value;
    var transferdate = document.getElementById("transferdate").value;
    var drvowner = document.getElementById("drvowner").value;
    var certificateno = document.getElementById("certificateno").value;
    var  modelDescription=$(".Inpput_I").val()+$(".Inpput_II").val()+$(".Inpput_III").val();
    //document.getElementById("model1").value;
    checkRegistdate();//校验新车注册日期
    checkLcnNo();//校验车牌号码
    validateName();//校验车主姓名
    validateVIN();//校验车架号
    if(lcnno=="" || lcnno==null || lcnno==undefined){
    	$('#Message').html("车牌号不能为空");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if(!checkLcnNoFlag){
    	$('#Message').html("车牌号码输入有误");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if(registerdate=="" || registerdate==null || registerdate==undefined){
    	$('#Message').html("车辆注册日期不能为空");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if(!checkNewcarRegistdate){
    	$('#Message').html("新车注册日期不得与当前日期相差9个月(北京、上海不得相差7天)");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if (vinno=="" || vinno==null || vinno==undefined){
    	$('#Message').html("车架号不能为空");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if(!checkvincodeFlag1){
    	$('#Message').html("车架号必须为17位");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if(!checkvincodeFlag2){
    	$('#Message').html("车架号不能有O,Q,I,*字符");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if(!checkvincodeFlag3){
    	$('#Message').html("车架号只能是英文和数字组合");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if (model=="" || model==null || model==undefined){
    	$('#Message').html("品牌名称不能为空");
    	$('.beijing').hide();
    	$('.errorhei').show();
    	//Hide();
    	return false;
    }else if (engno=="" || engno==null || engno==undefined){
    	$('#Message').html("发动机号不能为空");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
		return false;// 
    }else if($('#chgownerflag1').attr("checked")=='checked' && (transferdate==  null || transferdate == "" || transferdate==undefined)){
    	$('#Message').html("过户日期不能为空");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;  
    }else if (drvowner=="" || drvowner==null || drvowner==undefined){
    	$('#Message').html("车主名称不能为空");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if(!checkOwnerNameFlag){
    	$('#Message').html("名字输入有误！");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;
    }else if (certificateno=="" || certificateno==null || certificateno==undefined){
    	$('#Message').html("身份证不能为空");
    	$('.errorhei').show();
    	$('.beijing').hide();
    	//Hide();
    	return false;	
    }else if(certificateno.length!==15&&certificateno.length!==18){
    	$('#Message').html("身份证长度不符,请检查！");
		$('.errorhei').show();
		$('.beijing').hide();
		//Hide();
		return false;    
    }
    else if (idno==1){
    	$('#Message').html("身份证信息有误,请检查！");
		$('.errorhei').show();
		$('.beijing').hide();
		//Hide();
		return false;
  	} else  if(modelDescription=="" || modelDescription==null || modelDescription==undefined)
  	{
  		$('#Message').html("配置型号不允许为空，请选择配置型号");
		$('.errorhei').show();
		$('.beijing').hide();
		//Hide();
  	}else if(document.getElementById("agentCode")!=null){
  		var agentCode=document.getElementById("agentCode").value;
  		if(agentCode==null||agentCode=="undefined"||""==agentCode){
  			$('#Message').html("优惠券码不允许为空");
  			$('.errorhei').show();
  			$('.beijing').hide();
  			//Hide();
  		}else if(agentCode.length!=9 && agentCode.length!=8){
  			$('#Message').html("优惠券码必须是8/9位");
  			$('.errorhei').show();
  			$('.beijing').hide();
  			//Hide();
  		} else{
  			$("#prompt").html("正在加载，请稍等...");
  	  		load(15000);
  	  		document.getElementById("form1").submit();
  		}		 	
  	}
  	else {
  		$("#prompt").html("正在加载，请稍等...");
  		load(15000);
  		document.getElementById("form1").submit();
  	}	
}


var orderNo=$("#orderNo").val();

function searchByModel(){
   var model = $('#model');
   var modleName = model.val();
   var vhldata={vehiclename:modleName,baseinforOrdeo:orderNo};
   
   $.ajax({  
	type: "POST",  
	url: getUrl()+"/vehicleInfor/modelSerachByName.do",  
	data: JSON.stringify(vhldata),//将对象序列化成JSON字符串  
	dataType:"json",  
	contentType : 'application/json;charset=utf-8', //设置请求头信息  
	/*beforeSend: function () {
		model.layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
    },*/
	success: function(data){ 
		//layer.close(model.layerIndex);
	    if(data.length==0){
	    	$("#pop").hide();
	    	    $('.tang5_ul').html("");
	    	    /*$('.textarea1').html("");*/
	    	    $('.Inpput_I').val('');
        	    $('.Inpput_II').val('');
        	    $('.Inpput_III').val('');
	            $('.tang5_ul').append('<p  style="color:red;font-size: 16px;margin-top: 30%;">没找到您的车型，请核实填写的车架号和品牌型号、如有需要请拨打服务热线10100111-6</p>');
	            /*$('.tang5_ul').append('<p  style="color: #FFF;background-color: #e42219; width: 76px;display: inline-block;border-radius: 5px;line-height: 1.8em;margin-top: 53%;" href="javascript:void(0);">关闭</p>');*/
	            
	        }else{
	        	$("#pop").hide();
	            /*$('.textarea1').html("");*/
	        	$('.Inpput_I').val('');
        	    $('.Inpput_II').val('');
        	    $('.Inpput_III').val('');
	            $('.tang5_ul').html("");
	            for(var i= 0;i<data.length;i++){
	            	$('.tang5_ul').append('<li><i></i><div class="tang5_ul_content"><span class="right">'+data[i].VehicleName+'</span><span class="right">'+data[i].Remark+'</span><span class="right">'+data[i].MarketDate+'</span><span class="right">'+data[i].Seat+'</span>座<span class="right">'+data[i].Displacement+'</span>&nbsp;&nbsp;&nbsp;参考价格:<span class="right">'+data[i].PurchasePrice+'</span><span class="right" style="display:none">'+data[i].VehicleCode+'</span><span class="right" style="display:none">'+data[i].BrandName+'</span><span class="right" style="display:none">'+data[i].ABSFlag+'</span><span class="right" style="display:none">'+data[i].AlarmFlag+'</span><span class="right" style="display:none">'+data[i].AirbagFlag+'</span><span class="right" style="display:none">'+data[i].NewEnergyFlag+'</span><span class="right" style="display:none">'+data[i].FamilyName+'</span><span class="right" style="display:none">'+data[i].GearboxType+'</span><span class="right" style="display:none">'+data[i].FullWeight+'</span></div></li>');
	            };
	        }
	    $("#pop").hide();
	    $('.beijing').show();
	    $('.con4_tang5').show();
	    },  
    error: function(res){   
    	 $("#pop").hide();
     	 $("#Message").html("车型查询失败");
     	 $('.errorhei').show();
     	 $('.beijing').hide();
     	//Hide();
    }  
   });
}

function searchByVIN(){
	$("#prompt").html("车型查询中，请稍等...");
	load(20000);
   var vinno = $("#vinno");
   var vinNo=$("#vinno").val();
   var lcnNo=$("#lcnno").val();
   var registerdate=$("#registerdate").val();
   var vhldata={lcnno:lcnNo,vinno:vinNo,registerdate:registerdate,baseinforOrdeo:orderNo};
   $.ajax({  
    type: "POST",  
    url: getUrl()+"/vehicleInfor/modelSerachByVin.do",  
    data: JSON.stringify(vhldata),//将对象序列化成JSON字符串  
    dataType:"json",  
    contentType : 'application/json;charset=utf-8', //设置请求头信息  
    success: function(data){  
    	if(data.length==0){
    		searchByModel();
        }else{
    	    $("#pop").hide();
    	    $('.Inpput_I').val('');
    	    $('.Inpput_II').val('');
    	    $('.Inpput_III').val('');
            $('.tang5_ul').html("");
            $('.tang5_ul').html("");
            for(var i= 0;i<data.length;i++){
            	$('.tang5_ul').append('<li><i></i><div class="tang5_ul_content"><span class="right">'+data[i].VehicleName+'</span><span class="right">'+data[i].Remark+'</span><span class="right">'+data[i].MarketDate+'</span><span class="right">'+data[i].Seat+'</span>座<span class="right">'+data[i].Displacement+'</span>&nbsp;&nbsp;&nbsp;参考价格:<span class="right">'+data[i].PurchasePrice+'</span><span class="right" style="display:none">'+data[i].VehicleCode+'</span><span class="right" style="display:none">'+data[i].BrandName+'</span><span class="right" style="display:none">'+data[i].ABSFlag+'</span><span class="right" style="display:none">'+data[i].AlarmFlag+'</span><span class="right" style="display:none">'+data[i].AirbagFlag+'</span><span class="right" style="display:none">'+data[i].NewEnergyFlag+'</span><span class="right" style="display:none">'+data[i].FamilyName+'</span><span class="right" style="display:none">'+data[i].GearboxType+'</span><span class="right" style="display:none">'+data[i].FullWeight+'</span></div></li>');
            };
            $('.beijing').show();
            $('.con4_tang5').show();
        }
    	
        return true;
    },  
    	error: function(res){ 
    		searchByModel();
    		//return false;
    }  
   });
}

//车辆查询调华安接口
function modelSearch(){
	$("#prompt").html("车型查询中，请稍等...");
	load(60000);
	var vinNo=$("#vinno").val();
	var modleName = $('#model').val();
	var lcnNo=$("#lcnno").val();
	var registerdate=$("#registerdate").val();
//	var vhiinforid=document.getElementById("vhiinforid").value;
	var vhldata={
		lcnno:lcnNo,
		vinno:vinNo,
		registerdate:registerdate,
		baseinforOrdeo:orderNo,
//		vhiinforid:vhiinforid,
		vehiclename:modleName};
	$.ajax({  
	   type: "POST",  
	   url: getUrl()+"/vehicleInfor/modelSearchFromSinosafe.do",  
	   data: JSON.stringify(vhldata),//将对象序列化成JSON字符串  
	   dataType:"json",  
	   contentType : 'application/json;charset=utf-8', //设置请求头信息  
	   success: function(data){   	
		   console.log(data);
		   debugger;
		   if(data.length==0){    		
			   $("#Message").html("");
			   $("#pop").hide();
			   $("#Message").html("车型查询失败");
			   $('.errorhei').show();
			   $('.beijing').hide();
		   }else{
			   $("#pop").hide();
	    	   $('.Inpput_I').val('');
	    	   $('.Inpput_II').val('');
	    	   $('.Inpput_III').val('');
	           $('.tang5_ul').html("");
	           $('.tang5_ul').html("");	
	           for (var i = 0; i < data.length; i++) {
		    		$('.tang5_ul').append( '<li><i></i><div class="tang5_ul_content"><span class="right">'+data[i].MODEL_NAME+'</span><span class="right">'+data[i].CAR_REMARK+'</span><span class="right">'+data[i].MARKET_YEAR+'</span><span class="right">'+data[i].SET_NUM+'</span>座<span class="right">'+data[i].DISPLACEMENT+'</span>&nbsp;&nbsp;&nbsp;参考价格:<span class="right">'+data[i].CAR_PRICE+'</span><span class="right" style="display:none">'+data[i].MODEL_CODE+'</span><span class="right" style="display:none">'+data[i].BRAND_NAME+'</span><span class="right" style="display:none">'+data[i].F_ABS+'</span><span class="right" style="display:none">'+data[i].F_ALARM+'</span><span class="right" style="display:none">'+data[i].N_AIRBAG+'</span><span class="right" style="display:none">'+data[i].NEW_ENERGY_FLAG+'</span><span class="right" style="display:none">'+data[i].FAMILY_NAME+'</span><span class="right" style="display:none">'+data[i].TRANSMISSIONT_TYPE+'</span><span class="right" style="display:none">'+data[i].QUALITY+'</span></div></li>');
	           };
	           $('.beijing').show();
	           $('.con4_tang5').show();
	           
           }
		   return true;
	   },  
	   error: function(res){
		   $("#Message").html("");
		   $("#pop").hide();
		   $("#Message").html("车型查询失败");
		   $('.errorhei').show();
		   $('.beijing').hide();
	   }  
	});
}

$('.tang5_ul').on('click',"li",function(){
	var text1=$(this).children('.tang5_ul_content').children().eq(0).html();
	var text2=$(this).children('.tang5_ul_content').children().eq(1).html();
	var text3=$(this).children('.tang5_ul_content').children().eq(2).html();
	var text4=$(this).children('.tang5_ul_content').children().eq(3).html();
	var text5=$(this).children('.tang5_ul_content').children().eq(4).html();
	var text6=$(this).children('.tang5_ul_content').children().eq(5).html();
	var text7=$(this).children('.tang5_ul_content').children().eq(6).html();
	var text8=$(this).children('.tang5_ul_content').children().eq(7).html();
	var text9=$(this).children('.tang5_ul_content').children().eq(8).html();
	var text10=$(this).children('.tang5_ul_content').children().eq(9).html();
	var text11=$(this).children('.tang5_ul_content').children().eq(10).html();
	var text12=$(this).children('.tang5_ul_content').children().eq(11).html();
	var text13=$(this).children('.tang5_ul_content').children().eq(12).html();
	var text14=$(this).children('.tang5_ul_content').children().eq(13).html();
	var text15=$(this).children('.tang5_ul_content').children().eq(14).html();
    $("#marketyear").val(text3);
    $("#setno").val(text4);
    $("#displacement").val(text5);
    $("#vhlval").val(text6);
    $("#brandcode").val(text7);
    $("#brandName").val(text8);
    $('#model').val(text1);
    /*$('#alarmflag').val(text10);
    $('#airbagfalg').val(text11);
    $('#newenergyflag').val(text12);
    $('#gearboxtype').val(text14);
    $('#absflag').val(text9);*/
    if(text10 == "undefined"){
    	$('#alarmflag').val("");
    }else{
    	$('#alarmflag').val(text10);
    }
    if(text11 == "undefined"){
    	$('#airbagfalg').val("");
    }else{
    	$('#airbagfalg').val(text11);
    }
    if(text12 == "undefined"){
    	$('#newenergyflag').val("");
    }else{
    	$('#newenergyflag').val(text12);
    }
    if(text14 == "undefined"){
        $('#gearboxtype').val("");
    }else{
    	$('#gearboxtype').val(text14);
    }
    if(text9 == "undefined"){
    	$('#absflag').val("");
    }else{
        $('#absflag').val(text9);
    }
    $('#fullweight').val(text15);
    $('#familyKind').val(text13);
    /*$('#model1').html(text2+"&nbsp;"+text3+"&nbsp;"+text4+"&nbsp;"+text5+"&nbsp;"+text6);*/
    /*$('#model1').append('<span class="right">'+text2+'&nbsp;</span><span class="right">'+text3+'&nbsp;</span><span class="right">'+text4+'座&nbsp;</span><span class="right">'+text5+'&nbsp;</span><span class="right">参考价格:'+text6+'</span>');*/
    var string = text2+" "+text3+" "+text4+"座  "+text5+" 报价:"+text6;
    var str1 = string.slice(0,16);
    var str2 = string.slice(16,32);
    var str3 = string.slice(32,60);
    /*$('#model1').val(text2+text3+text4+text5+text6);*/
    $('.Inpput_I').val(str1);
    $('.Inpput_II').val(str2);
    $('.Inpput_III').val(str3);
    if( $('#model').val()!==""){
    	$('#vehicleModel').hide();
    	$('.beijing').hide();
    }
});
//输入车架号没有查询到数据，点击"没搜索到"字段,隐藏弹出框
$('.tang5_ul').on('click',"p",function(){
	$('#vehicleModel').hide();
	$('.beijing').hide();
});
//是否过户：点击是的时候显示日期控件
$(".con7_ul>li").eq(0).click(function(){
	$('#gouhudate').show();
});
//是否过户：点击否的时候隐藏日期控件
$(".con7_ul>li").eq(1).click(function(){
	$('#transferdate').val("");
	$('#gouhudate').hide();
});

$('.select').on('click',"p",function(){
	$('.select').html(' ');
	$('#vehicleModel').css('display','none');
});
var layerIndex;
function readFile(obj){
	//layerIndex = layer.load(1, { shade: [0.5, '#393D49'],time: 60*1000});
	$("#prompt").html("正在识别中，请稍等...");
	load(15000);
    var file = obj.files[0];    
    //判断类型是不是图片  
    if(!/image\/\w+/.test(file.type)){ 
    	    $("#pop").hide();
            $("#Message").html("请确保文件为图像类型");
            $('.errorhei').show();
        	$('.beijing').hide();
        	//Hide();
            return false;   
    }   
    var reader = new FileReader();   
    reader.readAsDataURL(file); 
    reader.onload = function(e){
    	doUpload();
    }   
}  
/**
 * 上传行驶证，扫描行驶证信息
 * 孙小东
 */
function doUpload() {  
	$("#prompt").html("正在识别中，请稍等...");
	load(60000);
	//layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
    var formData = new FormData($( "#uploadForm" )[0]);  
    $.ajax({  
         url: getUrl() + "/vehicleInfor/getResultbyOCR.do",
         type: 'POST',  
         data: formData, 
     	 dataType:"json",		 
    	 contentType : false, 
    	 processData : false,
         async: true,
        	 success: function(data) {
        		 //data=eval("(" + data + ")");
                 if (data.length == 0) {
                	 $("#pop").hide();
                 	//layer.close(layerIndex);
                     $("#Message").html("识别失败");
                     $('.errorhei').show();
                 	 $('.beijing').hide();
                 	//Hide();
                 } else {
                     var outputValue = data.outputs[0].outputValue;
                     if (outputValue != null && "undefined" !== typeof(outputValue)) {
                         var datavalue = outputValue.dataValue;
                         if (datavalue != null && "undefined" !== typeof(datavalue)) {
                             datavalue = eval("(" + datavalue + ")");
                             var successflag = datavalue.success;
                             if (successflag) {
                            	// layer.close(layerIndex);
                            	 $("#pop").hide();
                                 var engine_num = datavalue.engine_num;
                                 var modelName = datavalue.model;
                                 var owner = datavalue.owner;
                                 var plate_num = datavalue.plate_num;
                                 var register_date = datavalue.register_date;
                                 var vin = datavalue.vin;                                
                                 if(register_date!=null&&"undefined"!==typeof(register_date)){
                                	  var year=register_date.substring(0,4);
                                	  var month=register_date.substring(4,6);
                                	  var day=register_date.substring(6,8);
                                	  register_date=year+"-"+month+"-"+day;
                                 }
                                 $("#drvowner").val(owner);
                                 $("#lcnno").val(plate_num);
                                 $("#vinno").val(vin);
                                 $("#model").val(modelName);
                                 $("#engno").val(engine_num);
                                 $("#registerdate").val(register_date);
                                 //$('#model').trigger("blur");
                                 $("#Message").html("识别成功，请仔细核对数据");
                             	 $('.errorhei').show();
                             	 $('.beijing').hide();
                             	//Hide();
                                
                             } else {
                             	 //layer.close(layerIndex);
                            	 $("#pop").hide();
                             	 $("#Message").html("识别失败");
                             	 $('.errorhei').show();
                             	 $('.beijing').hide();
                             	//Hide();
                                 
                             }

                         } else {
                         	 //layer.close(layerIndex);
                        	 $("#pop").hide();
                         	 $("#Message").html("识别失败");
                         	 $('.errorhei').show();
                         	 $('.beijing').hide();
                         	//Hide();
                         }
                     } else {
                     	 //layer.close(layerIndex);
                    	 $("#pop").hide();
                     	 $("#Message").html("识别失败");
                     	 $('.errorhei').show();
                     	 $('.beijing').hide();
                     	//Hide();
                     }

                 }
             },
             error: function(res) {
             	// layer.close(layerIndex);
            	 $("#pop").hide();
             	 $("#Message").html("识别失败");
             	 $('.errorhei').show();
             	 $('.beijing').hide();
             	//Hide();
             }
    });  
}  


/**
 * 上传行驶证，扫描行驶证信息weixin
 * 孙小东
 */
function doUploadbyweixin(imgdata) {  
    $.ajax({  
         url: getUrl() + "/WechatLogin/getVehicleinforByOCR.do",
         type: 'POST',  
         data: imgdata, 
     	 dataType:"json",		 
    	 contentType : 'application/json;chartset=UTF-8', //设置请求头信息 
         async: true,    
        	 success: function(data) {
        		 //data=eval("(" + data + ")");
                 if (data.length == 0) {
                	 $("#pop").hide();
                     $("#Message").html("识别失败");
                     $('.errorhei').show();
                 	 $('.beijing').hide();
                 	//Hide();
                 } else {
                     var outputValue = data.outputs[0].outputValue;
                     if (outputValue != null && "undefined" !== typeof(outputValue)) {
                         var datavalue = outputValue.dataValue;
                         if (datavalue != null && "undefined" !== typeof(datavalue)) {
                             datavalue = eval("(" + datavalue + ")");
                             var successflag = datavalue.success;
                             if (successflag) {
                            	 $("#pop").hide();
                                 var engine_num = datavalue.engine_num;
                                 var modelName = datavalue.model;
                                 var owner = datavalue.owner;
                                 var plate_num = datavalue.plate_num;
                                 var register_date = datavalue.register_date;
                                 var vin = datavalue.vin;                                
                                 if(register_date!=null&&"undefined"!==typeof(register_date)){
                                	  var year=register_date.substring(0,4);
                                	  var month=register_date.substring(4,6);
                                	  var day=register_date.substring(6,8);
                                	  register_date=year+"-"+month+"-"+day;
                                 }
                                 $("#drvowner").val(owner);
                                 $("#lcnno").val(plate_num);
                                 $("#vinno").val(vin);
                                 $("#model").val(modelName);
                                 $("#engno").val(engine_num);
                                 $("#registerdate").val(register_date);
                                 $("#Message").html("识别成功，请仔细核对数据");
                             	 $('.errorhei').show();
                             	 $('.beijing').hide();
                             	//Hide();
                                
                             } else {
                            	 $("#pop").hide();
                             	 $("#Message").html("识别失败");
                             	 $('.errorhei').show();
                             	 $('.beijing').hide();
                             	//Hide();
                                 
                             }

                         } else {;
                        	 $("#pop").hide();
                         	 $("#Message").html("识别失败");
                         	 $('.errorhei').show();
                         	 $('.beijing').hide();
                         	//Hide();
                         }
                     } else {
                    	 $("#pop").hide();
                     	 $("#Message").html("识别失败");
                     	 $('.errorhei').show();
                     	 $('.beijing').hide();
                     	//Hide();
                     }

                 }
             },
             error: function(res) {
            	 $("#pop").hide();
             	 $("#Message").html("识别失败");
             	 $('.errorhei').show();
             	 $('.beijing').hide();
             	//Hide();
             }
    });  
}  

$('.errortan3').click(function(){
    $('.beijing').hide();
    $('.errorhei').hide();
});


$('.errortan4').click(function(){
    $('.con4_tang5').hide();
});


function Prompt(){
	if(window.confirm("调整拍摄角度，避免反光影响，确保图像清晰,可提高识别率")){
		if(isWeiXin()){
			wx.chooseImage({
			    count: 1, // 默认9
			    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
			    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
			    success: function (res) {
			    	$("#prompt").html("正在识别中，请稍等...");
			    	load(60000);
			        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
				     var img = document.createElement('img');
                     img.src = localIds[0];
					  img.onload =function() {
						  wx.uploadImage({
							    localId: localIds[0], // 需要上传的图片的本地ID，由chooseImage接口获得
							    isShowProgressTips: 0, // 默认为1，显示进度提示
							    success: function (res) {
							        var serverId = res.serverId; // 返回图片的服务器端ID
							        doUploadbyweixin(serverId);
							    }
							});
						
					  }                 
			    }
			});
		}else{		
		$("#picFile").trigger("click");
		}
		}
}

/*function Hide(){
	setTimeout(function(){$('.errorhei').hide()
		},2000);
	}*/

function load(outtimes){
	$("#pop").show();
	setTimeout(function(){$('#pop').hide()
	},outtimes);
}

function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}

function getBase64Image(img) {
    var canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;

    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0, img.width, img.height);

    var dataURL = canvas.toDataURL("image/png");
    return dataURL
  }

 $('.Clear_Val').click(function(){
	 $(this).siblings('input').val('');$(this).hide();
	 })
	 $("#certificateno").focus(function(){
		 if($(this).val().length!==0){
		    	$(this).siblings('img').show();
		    }
	 })
 $("#certificateno").keyup(function(){
    if($(this).val().length!==0){
    	$(this).siblings('img').show();
    }else{
    	$(this).siblings('img').hide();
    };
  });
 //焦点离开身份证叉叉隐藏延迟时间
 $("#certificateno").blur(function(){	
	 setTimeout(function(){ $("#certificateno").siblings('.Clear_Val').hide();
		},500);
			
 })
  //车架号
 $("#vinno").blur(function(){	
	 setTimeout(function(){ $("#vinno").siblings('.Clear_Val').hide();
		},500);
			
 })

  $("#vinno").focus(function(){
		 if($(this).val().length!==0){
		    	$(this).siblings('img').show();
		    }
	 })
 
 $("#vinno").keyup(function(){
	    if($(this).val().length!==0){
	    	$(this).siblings('img').show();
	    }else{
	    	$(this).siblings('img').hide();
	    	};
 });

 //品牌型号
 $("#model").blur(function(){	
	 setTimeout(function(){ $("#model").siblings('.Clear_Val').hide();
		},500);
			
 })
   $("#model").focus(function(){
		 if($(this).val().length!==0){
		    	$(this).siblings('img').show();
		    }
	 })
 
 $("#model").keyup(function(){
	    if($(this).val().length!==0){
	    	$(this).siblings('img').show();
	    }else{
	    	$(this).siblings('img').hide();
	    	};
 });

