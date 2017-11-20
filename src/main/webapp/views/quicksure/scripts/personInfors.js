var ii;
var ming_to_flag=1;
function goback() {
	    window.location.href = getUrl()+"/vehicleInfor/backToCoverageScreen.do?orderNo="+orderNo;
	 }

$('html').ready(function(){
	$("#pop").hide();
    $("#ming_to").click(function(){
       if(ming_to_flag%2==0){
    	   $("#ming_to").html("点击查看险种明细");
       }else{
    	   $("#ming_to").html("点击关闭险种明细");
       }
       $('.ying_table').slideToggle();
       ming_to_flag=ming_to_flag+1;
    });
    
    var appCoypCheckbox=$("#appCoypCheckbox").val();
    var insureCopyCheckbox=$("#insureCopyCheckbox").val();
    var deliveryCopyCheckbox=$("#deliveryCopyCheckbox").val();
    var city1 = $('#city1').val();
    if(appCoypCheckbox=="1"){
    	var Span=document.getElementsByClassName("con_ul")[0].getElementsByTagName("span")[0];
    	Span.setAttribute("class","on");
    	$("#appCopy").attr("checked","checked");
    }
    if(insureCopyCheckbox=="1"){
    	var Span1=document.getElementsByClassName("con_ul")[0].getElementsByTagName("span")[1];
    	Span1.setAttribute("class","on");
    	$("#insureCopy").attr("checked","checked");
    }
    if(deliveryCopyCheckbox=="1"){
   	 	var Span2=document.getElementsByClassName("con_ul cona_ul")[0].getElementsByTagName("span")[0];
    	Span2.setAttribute("class","on");
    	$("#copyDelivery").attr("checked","checked");
    }
    
    $("#city1").blur(function(){
    	var city1 = $('#city1').val();
    	if ($('#appCopy').attr('checked')) {
    		$('#city2').val(city1);
    	}
    	if($('#insureCopy').attr('checked')){
    		$('#city3').val(city1);
    	}
    	if($('#copyDelivery').attr('checked')){
    		$('#city4').val(city1);
    	}
    });
    
    $(window).resize(function(){
	if($('body').width()>640)
	{
		var bodyWidth=$('body').width();
		var left=bodyWidth-640
		var lefta=left/2
		$('.gearArea').css('left',lefta+'px');
		}
	});

	if($('body').width()>640)
	{
		$('#ownersaddress').click(function(){
			var bodyWidth=$('body').width();
			var left=bodyWidth-640
			var lefta=left/2
			$('.gearArea').css('left',lefta+'px');
		});
		$('#applicationaddress').click(function(){
			var bodyWidth=$('body').width();
			var left=bodyWidth-640
			var lefta=left/2
			$('.gearArea').css('left',lefta+'px');
		});
		$('#insureaddress').click(function(){
			var bodyWidth=$('body').width();
			var left=bodyWidth-640
			var lefta=left/2
			$('.gearArea').css('left',lefta+'px');
		});
		$('#deliveryaddress').click(function(){
			var bodyWidth=$('body').width();
			var left=bodyWidth-640
			var lefta=left/2
			$('.gearArea').css('left',lefta+'px');
		});
 	}
  });
  var provincedata;
  
  

  
  
$('html').ready(function() {
	$.getJSON(getUrl()+'/vehicleInfor/getDptCode.do', function (data) {
	    provincedata=data;
	});
});

function changevalue(type){
	var name = $('#ownersname').val();
	var phone = $('#ownersphoneno').val();
	var certcode = $('#ownerscerticode').val(); 
	var ownersaddress; 
	var ownersdetailaddress = $('#ownersdetailaddress').val();
	if(ispc){
		ownersaddress=$("#city1").val();
	}else{
		ownersaddress=$('#ownersaddress').val();
	}
	if ($('#appCopy').attr('checked')) {
			if(type=="ownersname"){
				$('#applicationname').val(name);
			}else if(type=="ownerscerticode"){
				$('#applicationcerticode').val(certcode.toLocaleUpperCase());
			}else if(type=="ownersphoneno"){
				$('#applicationphoneno').val(phone);
			}else if(type=="ownersaddress"){
				if(ispc){
					$('#city2').val(ownersaddress);
				}else{
					$('#applicationaddress').val(ownersaddress);
				}
				
			}else if(type=="ownersdetailaddress"){
				$('#applicationdetailaddress').val(ownersdetailaddress);
			}
	}if($('#insureCopy').attr('checked')){
		if(type=="ownersname"){
			$('#insurename').val(name);
		}else if(type=="ownerscerticode"){
			$('#insurecerticode').val(certcode.toLocaleUpperCase());
		}else if(type=="ownersphoneno"){
			$('#insurephoneno').val(phone);
		}else if(type=="ownersaddress"){
			if(ispc){
				$('#city3').val(ownersaddress);
			}else{
				$('#insureaddress').val(ownersaddress);
			}					
		}else if(type=="ownersdetailaddress"){			
				$('#insuredetailaddress').val(ownersdetailaddress);					
		}
	}if($('#copyDelivery').attr('checked')){
		if(type=="ownersname"){
			$('#deliveryname').val(name);
		}else if(type=="ownersphoneno"){
			$('#deliveryphone').val(phone);
		}else if(type=="ownersaddress"){
			if(ispc){
				$('#city4').val(ownersaddress);
			}else{
				$('#deliveryaddress').val(ownersaddress);
			}
		}else if(type=="ownersdetailaddress"){			
				$('#deliverydetailaddress').val(ownersdetailaddress);		
		}
	}	
}

function setDeptNo(areaValue,type){
	var province, city, county;
	if (areaValue != null && "undefined" !== typeof (areaValue)) {
		var areaArray = areaValue.split(",");
		if (areaArray !== null) {
			if (areaArray[0] !== null) {
				province = areaArray[0];
			}
			if (areaArray[1] !== null) {
				city = areaArray[1];
			}
			if (areaArray[2] !== null) {
				county = areaArray[2];
			}
		}
	}
	if (provincedata != null && "undefined" !== typeof (provincedata)) {
		for ( var i = 0; i < provincedata.length; i++) {
			if (provincedata[i].name === province) {
				for ( var j = 0; j < provincedata.length; j++) {
					if (provincedata[i].deptinforid === provincedata[j].parentid
							&& provincedata[j].name === city) {
						var deptCode = provincedata[j].deptinforid;
						if (deptCode != null&& "undefined" !== typeof (deptCode)) {
							if(type==1){
								$("#ownerszipcode").val(deptCode);
							}else if(type==2){
								$("#applicationzipcode").val(deptCode);
							}else if(type==3){
								$("#insurezipcode").val(deptCode);
							}else if(type==4){
								$("#Delivery_ZipCode").val(deptCode);
							}
						}
					}
				}
			}
		}
	}
}

$(".con_ul span").click(function(){
	var check=$(this).children('input').attr("checked");
	var value=$(this).children('input').val();
	if("undefined"==typeof check){
		if(value=="appCopy"){
			copy(1);
		}else if(value=="insureCopy"){
			copy(2);
		}
	}else if("checked"==check){
		if(value=="appCopy"){
			removeCopy(1);
		}else if(value=="insureCopy"){
			removeCopy(2);
		}
	}
});

$("#con_uldelivery span").click(function(){
	var check=$(this).children('input').attr("checked");
	if("undefined"==typeof check){
		copy(3);
	}else if("checked"==check){
		removeCopy(3);
	}
});

function copy(type){
	var ownersaddress ;
	var name = $('#ownersname').val();
	var phone = $('#ownersphoneno').val();
	var certcode = $('#ownerscerticode').val(); 
	if(ispc){
		ownersaddress=$("#city1").val();
	}else{
		ownersaddress=$('#ownersaddress').val();
	}	
	var ownersdetailaddress = $('#ownersdetailaddress').val();
	if(type==1){
		$('#applicationname').val(name);
		$('#applicationphoneno').val(phone);
		$('#applicationcerticode').val(certcode);
		if(ispc){
			$('#city2').val(ownersaddress);
		}else{
			$('#applicationaddress').val(ownersaddress);
		}	
		
		$('#applicationdetailaddress').val(ownersdetailaddress); 
	}
	if(type==2){
		$('#insurename').val(name);
		$('#insurephoneno').val(phone);
		$('#insurecerticode').val(certcode);
		if(ispc){
			$('#city3').val(ownersaddress);
		}else{
			$('#insureaddress').val(ownersaddress);
		}			
		$('#insuredetailaddress').val(ownersdetailaddress);
	}
	if(type==3){
		$('#deliveryname').val(name);
		$('#deliveryphone').val(phone);
		if(ispc){
			$('#city4').val(ownersaddress);
		}else{
			$('#deliveryaddress').val(ownersaddress);
		}					
		$('#deliverydetailaddress').val(ownersdetailaddress);
	}
}

function removeCopy(type){
	if(type==1){
		$('#applicationname').val("");
		$('#applicationphoneno').val("");
		$('#applicationcerticode').val("");
		if(ispc){
			$('#city2').val("");
		}else{
			$('#applicationaddress').val("");
		}				
		$('#applicationdetailaddress').val(""); 
	}
	if(type==2){
		$('#insurename').val("");
		$('#insurephoneno').val("");
		$('#insurecerticode').val("");
		if(ispc){
			$('#city3').val("");
		}else{
			$('#insureaddress').val("");
		}		
		$('#insuredetailaddress').val("");
	}
	if(type==3){
		$('#deliveryname').val("");
		$('#deliveryphone').val("");
		if(ispc){
			$('#city4').val("");
		}else{
			$('#deliveryaddress').val("");
		}		
		$('#deliverydetailaddress').val("");
	}
}
	

$('.btn').click(function(){
    $('.error_Box').hide();

});
$('.errortan3').click(function(){
    $('.errorhei').hide();

});
  
  


var value = 1;
$('i').on('click',function() {
    if (value == 1) {
        $(this).parent().next().show();
        $(this).css('background', 'blue');
        value = 0;
    } else {
        $(this).parent().next().hide();
        $(this).css('background', 'green');
        value = 1;
    }
});
/**
 * 校验姓名
 */
function checkName(value,type){	
	if(value==null||"undefined"==value||""==value){
		if(type==="own"){
			$("#CheckMessage").html("车主姓名不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type==="app"){
			$("#CheckMessage").html("被保人姓名不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type=="insure"){
			$("#CheckMessage").html("投保人姓名不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type=="delivery"){
			$("#CheckMessage").html("配送人姓名不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}
	}else if (!(/^[\u4e00-\u9fa5 ]{2,20}$/.test(value))) {
		if(type==="own"){
			$("#CheckMessage").html("车主姓名输入有误，请检查");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type==="app"){
			$("#CheckMessage").html("被保人姓名输入有误，请检查");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type=="insure"){
			$("#CheckMessage").html("投保人姓名输入有误，请检查");
			$(".errorhei").show();
			//Hide();
			  return false;
		}	else if(type=="delivery"){
			$("#CheckMessage").html("配送人姓名姓名输入有误，请检查");
			$(".errorhei").show();
			//Hide();
			  return false;
		}	
	} else{
		return true;
	}	
}
/**
 * 校验地址
 */
function checkAddress(value,type){
	if(value==null||"undefined"==value||""==value){
		if(type==="own"){
			$("#CheckMessage").html("车主地址不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type==="app"){
			$("#CheckMessage").html("被保人地址不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type=="insure"){
			$("#CheckMessage").html("投保人地址不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type=="delivery"){
			$("#CheckMessage").html("配送人地址不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}
	}else{
		return true;
	}	
}
/**
 * 校验详细地址
 */
function checkAddressDetails(value,type){
	if(value==null||"undefined"==value||""==value){
		if(type==="own"){
			$("#CheckMessage").html("车主详细地址不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type==="app"){
			$("#CheckMessage").html("被保人详细地址地址不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type=="insure"){
			$("#CheckMessage").html("投保人详细地址地址不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type=="delivery"){
			$("#CheckMessage").html("配送人详细地址不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}
	}else{
		return true;
	}	
}
/**
 * 校验手机号
 */
function checkPhone(value,type){
	if(value==null||"undefined"==value||""==value){
		if(type==="own"){
			$("#CheckMessage").html("车主手机号不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type==="app"){
			$("#CheckMessage").html("被保人手机号不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type=="insure"){
			$("#CheckMessage").html("投保人手机号不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type=="delivery"){
			$("#CheckMessage").html("配送人手机号不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}
	} else if (!(/^1[34578]\d{9}$/.test(value))) {
	if(type==="own"){
		$("#CheckMessage").html("车主手机号输入有误");
		$(".errorhei").show();
		//Hide();
		  return false;
	}else if(type==="app"){
		$("#CheckMessage").html("被保人手机号输入有误");
		$(".errorhei").show();
		//Hide();
		  return false;
	}else if(type=="insure"){
		$("#CheckMessage").html("投保人手机号输入有误");
		$(".errorhei").show();
		//Hide();
		  return false;
	}else if(type=="delivery"){
		$("#CheckMessage").html("配送人手机号输入有误");
		$(".errorhei").show();
		//Hide();
		  return false;
	}
}else{
	return true;
}	
}
/**
 * 校验身份证
 */
function checkId(value,type){
	if(value==null||"undefined"==value||""==value){
		if(type==="own"){
			$("#CheckMessage").html("车主身份证不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type==="app"){
			$("#CheckMessage").html("被保人身份证不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type=="insure"){
			$("#CheckMessage").html("投保人身份证不能为空");
			$(".errorhei").show();
			//Hide();
			  return false;
		}
	}else if(value.length!==15&&value.length!==18){
		if(type==="own"){
			$("#CheckMessage").html("车主身份证长度不符");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type==="app"){
			$("#CheckMessage").html("被保人身份证长度不符");
			$(".errorhei").show();
			//Hide();
			  return false;
		}else if(type=="insure"){
			$("#CheckMessage").html("投保人身份证长度不符");
			$(".errorhei").show();
			//Hide();
			  return false;
		}	
	}else{
	var code=value;
	 var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
	    var tip = "";
	    var pass= true;
	    if(!code || !/^\d{6}(19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
	        tip = "The format of Id no is wrong!";
	        pass = false;
	    }

	    else if(!city[code.substr(0,2)]){
	        tip = "Area code is wrong ";
	        pass = false;
	    }
	    else{
	        // 18位身份证需要验证最后一位校验位
	        if(code.length == 18){
	            code = code.split('');
	            // ∑(ai×Wi)(mod 11)
	            // 加权因子
	            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
	            // 校验位
	            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
	            var sum = 0;
	            var ai = 0;
	            var wi = 0;
	            for (var i = 0; i < 17; i++)
	            {
	                ai = code[i];
	                wi = factor[i];
	                sum += ai * wi;
	            }
	            var last = parity[sum % 11];
	            if(parity[sum % 11] != code[17]){
	                tip = "The last character is wrong!";
	                pass =false;
	            }
	        }
	    }
	    if(!pass){
	    	if(type==="own"){
				$("#CheckMessage").html("车主身份证输入有误，请检查");
				$(".errorhei").show();
				//Hide();
				return false;
			}else if(type==="app"){
				$("#CheckMessage").html("被保人身份证输入有误，请检查");
				$(".errorhei").show();
				//Hide();
				return false;
			}else if(type=="insure"){
				$("#CheckMessage").html("投保人身份证输入有误，请检查");
				$(".errorhei").show();
				//Hide();
				return false;
			}		
	    }else{
	    	return true;
	    }
	}
}

function submitCheck(){
	$("#CheckMessage").html("");
	var ownAddress;
	var appAddress;
	var insureAddress;
	var deliveryAddress;
	var ownName=$("#ownersname").val();
	var ownId=$("#ownerscerticode").val();
	var ownPhone=$("#ownersphoneno").val();
	if(ispc){
		ownAddress=$("#city1").val();
	}else{
		ownAddress=$('#ownersaddress').val();
	}
	var ownAddressDetails=$("#ownersdetailaddress").val();
	var applicationname=$("#applicationname").val();
	var applicationcerticode=$("#applicationcerticode").val();
	var applicationphoneno=$("#applicationphoneno").val();
	if(ispc){
		appAddress=$("#city2").val();
	}else{
		appAddress=$("#applicationaddress").val();
	}
	var appAddressDetails=$("#applicationdetailaddress").val();
	var insurename=$("#insurename").val();
	var insurecerticode=$("#insurecerticode").val();
	var insurephoneno=$("#insurephoneno").val();
	if(ispc){
		insureAddress=$("#city3").val();
	}else{
		insureAddress=$("#insureaddress").val();
	}
	var insureAddressDeatis=$("#insuredetailaddress").val();
	var deliveryname=$("#deliveryname").val();
	var deliveryphone=$("#deliveryphone").val();
	if(ispc){
		deliveryAddress=$("#city4").val();
	}else{
		deliveryAddress=$("#deliveryaddress").val();
	}
	var deliveryAddressDetails=$("#deliverydetailaddress").val();
	var checkboxflag=$('.con3_span').hasClass("on");
	if(!checkName(ownName,"own")){
		 return false;
	}
	if(!checkId(ownId,"own")){
		 return false;
	}
	if(!checkPhone(ownPhone,"own")){
		 return false;
	}
	if(!checkAddress(ownAddress,"own")){
		 return false;
	}
	if(!checkAddressDetails(ownAddressDetails,"own")){
		 return false;
	}
	if(!checkName(applicationname,"app")){
		 return false;
	}
	if(!checkId(applicationcerticode,"app")){
		 return false;
	}
	if(!checkPhone(applicationphoneno,"app")){
		 return false;
	}
	
	if(!checkAddress(appAddress,"app")){
		 return false;
	}
	if(!checkAddressDetails(appAddressDetails,"app")){
		 return false;
	}
	if(!checkName(insurename,"insure")){
		 return false;
	}
	if(!checkId(insurecerticode,"insure")){
		 return false;
	}
	if(!checkPhone(insurephoneno,"insure")){
		 return false;
	}
	if(!checkAddress(insureAddress,"insure")){
		 return false;
	}
	if(!checkAddressDetails(insureAddressDeatis,"insure")){
		 return false;
	}
	if(!checkName(deliveryname,"delivery")){
		 return false;
	}
	if(!checkPhone(deliveryphone,"delivery")){
		 return false;
	}
	if(!checkAddress(deliveryAddress,"delivery")){
		 return false;
	}
	if(!checkAddressDetails(deliveryAddressDetails,"delivery")){
		 return false;
	}
	if(!checkboxflag){
		$("#CheckMessage").html("请阅读并同意《免责说明》《保险条款》《强保条款》《投保申明》");
		$(".errorhei").show();
		Hide();
		return false;
	}else{
		return true;
	}
}

function getInsuranceperInfor(){
	var ownersname=$("#ownersname").val();
	var ownerscerticode=$("#ownerscerticode").val();
	var ownersphoneno=$("#ownersphoneno").val();
	var ownersaddress=$("#ownersaddress").val();
	var ownerszipcode=$("#ownerszipcode").val();
	var applicationname=$("#applicationname").val();
	var applicationcerticode=$("#applicationcerticode").val();
	var applicationphoneno=$("#applicationphoneno").val();
	var applicationaddress=$("#applicationaddress").val();
	var applicationdetailaddress=$("#applicationdetailaddress").val();
	var applicationaddress=applicationaddress+"-"+applicationdetailaddress;
	var applicationzipcode=$("#applicationzipcode").val();
	var insurename=$("#insurename").val();
	var insurecerticode=$("#insurecerticode").val();
	var insurephoneno=$("#insurephoneno").val();
	var insureaddress=$("#insureaddress").val();
	var insuredetailaddress=$("#insuredetailaddress").val();
	var insureaddress=insureaddress+"-"+insuredetailaddress;
	var insurezipcode=$("#insurezipcode").val();
	var insuranceperinfor={
			ownersname:ownersname,
			ownerscerticode:ownerscerticode,
			ownersphoneno:ownersphoneno,
			ownersaddress:ownersaddress,
			ownerszipcode:ownerszipcode,
			applicationname:applicationname,
			applicationcerticode:applicationcerticode,
			applicationphoneno:applicationphoneno,
			applicationaddress:applicationaddress,
			applicationzipcode:applicationzipcode,
			insurename:insurename,
			insurecerticode:insurecerticode,
			insurephoneno:insurephoneno,
			insureaddress:insureaddress,
			insurezipcode:insurezipcode
	}
	return insuranceperinfor;
	
}

function getDeliveryInfor(){
	var deliveryname=$("#deliveryname").val();
	var deliveryphone=$("#deliveryphone").val();
	var deliveryaddress=$("#deliveryaddress").val();
	var deliverydetailaddress=$("#deliverydetailaddress").val();
	var deliveryaddress=deliveryaddress+"-"+deliverydetailaddress;
	var deliveryinfor={
			"deliveryname":deliveryname,
			"deliveryphone":deliveryphone,
			"deliveryaddress":deliveryaddress
	};
	return deliveryinfor;
}

//提交form表单
$("#next").click(function(){
	/*if($(this).hasClass('Change')){
		$(this).removeClass('Change');
	}else{$(this).addClass('Change');}*/
	   if(submitCheck()){
			//点击保存的时候给对应编码赋值
			   	if ($('#copyDelivery').attr('checked')) {
				    $('#deliveryCopyCheckbox').val("1");
				}
			   	if ($('#appCopy').attr('checked')) {
				    $('#appCoypCheckbox').val("1");
				}
			   	if ($('#insureCopy').attr('checked')) {
				    $('#insureCopyCheckbox').val("1");
				}
			    var ownerdemo=$('#ownersaddress').val();
			    setDeptNo(ownerdemo, 1);
				var appdemo=$('#applicationaddress').val();
				setDeptNo(appdemo,2);
				var insuredemo=$('#insureaddress').val();
				setDeptNo(insuredemo,3);
				var deliverydemo=$('#deliveryaddress').val();
				setDeptNo(deliverydemo,4);
				var insuranceperinfor=getInsuranceperInfor();
				var deliveryinfor=getDeliveryInfor();
				var result=new Array();
				result.push({insuranceperinfor:insuranceperinfor});
				result.push({deliveryinfor:deliveryinfor});
				var insureData={"insureData":result};
				//给予加载
//				ii = layer.load(0, {shade: [0.5, '#393D49'],time: 15*1000});
				$("#prompt").html("订单确认中，请稍等...");
		  		load(40000);
		  		
		  		var orderNo=$("#orderNo").val();
		  		
//		  		$.ajax({
//		  			type: "POST",
//				    url: getUrl()+"/submitInfor/submitUnderwriting.do?orderNo="+orderNo,
//				    data: JSON.stringify(insureData),//将对象序列化成JSON字符串  
//				    dataType:"json", 
//				    async: true,
//				    contentType : 'application/json;charset=utf-8', //设置请求头信息  
////				    beforeSend: function () {
////				    	this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 10*1000});
////				    },
//				    success: function(data){
//				    	$('#pop').hide();
//				    	var errorcode ="E00000030";
//						var errorcode2 = "C99999999";
//				    	
//						if(data.userinfor.errorCode==errorcode || data.userinfor.errorCode==errorcode2){
//							    var errorMessage =data.userinfor.errorMessage;
//							    $("#chackMessage").html(errorMessage);
//							    $('.errorhei2').show();
////							    $('#next').hide();
//						}else{
//							    $('.errorhei').hide();
//							    window.location.href=getUrl()+"/views/quicksure/jsp/paymentInfor.jsp";
////							    $('#next').show();
//						}
//				    }
//		  		});
				$("#submit_form").submit();	
		   }
})
/*function submitform(){
	

}*/
/*function Hide(){
	setTimeout(function(){$('.errorhei').hide()
		},2000);
	}*/
//延迟时间
function load(outtimes){
	$("#pop").show();
	setTimeout(function(){$('#pop').hide()
	},outtimes);
}

//$('.Clear_Val').click(function(){
//	 $(this).siblings('input').val('');$(this).hide();
//	 })
//
////车主身份证叉叉功能实现
//
//$("#ownerscerticode").blur(function(){	
//	 setTimeout(function(){ $("#ownerscerticode").siblings('.Clear_Val').hide();
//		},500);
//			
//})
//			
//
//  $("#ownerscerticode").focus(function(){
//		 if($(this).val().length!==0){
//		    	$(this).siblings('img').show();
//		    }
//	 })
//
//$("#ownerscerticode").keyup(function(){
//	    if($(this).val().length!==0){
//	    	$(this).siblings('img').show();
//	    }else{
//	    	$(this).siblings('img').hide();
//	    	};
//});











