var checkLcnNoFlag=false;
var checkphoneNoFlag=false;

$(document).ready(function(){
	if(isagentshare==1){
		$('#loginQr').hide();
		$("#qrcode").show();
		$('.td_right').hide();
		$('#phoneNo').val("");
		$('#isagentshare').val(1);
	}
	$("#pop").hide();
	var userName = $(".td_left").text();
	
	if(phoneNo!=null&&"undefined"!==typeof(phoneNo)&&""!==phoneNo){
		$("#phoneNo").val(phoneNo);
		}
	if(lcnNo!=null&&"undefined"!==typeof(lcnNo)&&lcnNo.length!==0&&""!==lcnNo){
		$("#lcno").val(lcnNo);
		}
	if(dptcode!=null&&"undefined"!==typeof(dptcode)&&""!==dptcode&&"null"!==dptcode){ 
		$("#dptcode").val(dptcode);
		}
	if(deptAddress!=null&&"undefined"!==typeof(deptAddress)&&deptAddress.length!==0&&""!==deptAddress){
		$("#demo1").val(deptAddress);
		$("#city").val(deptAddress);
	}
	if(userName!="登录" && agentFlag!=0){
		$("#qrcode").show();
	}	
	if(codeId=="1"){//二维码扫描到首页然后登录		
		$.ajax({
			type: "post",
			url : getUrl()+"/loginUser/userLogin.do",
			async: false,
			data: {userIdQr:userIdQr,codeId:codeId},
			success : function(result){
				if(result!==null&&result!==""){
					/*var name=result.username;
					alert(name);
					$('#phoneNo').val(name);
					$('#loginQr').hide();
					$('#qrcode').hide();*/
				}else{					
					$('#loginQr').hide();
					$("#qrcode").show();
					$('.td_right').hide();
					$('#isagentshare').val(1);
					$('#phoneNo').val("");
				}
			}
	   });
	}
});
    
$(function(){
	if($("#backToFirstFlag").val()!=null && $("#backToFirstFlag").val()!=""){
		if($("#NOflag").val()=='1'){
			$("#NO").attr('checked','true');
			$("#NO").prev().removeAttr('required');
			$("#lcno").attr('readonly','readonly');
		}else if($("#NOflag").val()=='2'){
		}
	}
});
    
$('#NO').change(function(){
    if($(this).is(':checked')==true){
        $('#lcno').removeAttr('required').val("*-*").attr( 'readonly','readonly');
    }else{
    	$('#lcno').removeAttr('readonly').val(" ").attr( 'required','required');
    }
});
function checkLcnNo() {
	var car_num = document.getElementById('lcno').value;
	if("*-*"!==car_num){
		var reg = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
		if (reg.test(car_num) != true) {
			checkLcnNoFlag=false;
			$('#Message').html("车牌号输入有误");
			$("#errorhei").show();	
			//hide();
			return false;
		} else {
			checkLcnNoFlag=true;	
			return true;
		}
	}else{
		return true;
	}
}
function checkPhone() {
	var phone = document.getElementById("phoneNo").value;

	if (!(/^1[34578]\d{9}$/.test(phone))) {
		checkphoneNoFlag=false;
		$('#Message').html("手机号输入有误");
		$("#errorhei").show();		
		//hide();
		return false;
	} else {
	    checkphoneNoFlag=true;
		return true;
	}

}
function Remove(){
    document.getElementById('lcno').nextElementSibling.setAttribute('class','Error');
}
function checkSubmit(){
	 checkLcnNo();
	 checkPhone() ;
	if(checkLcnNoFlag&&checkphoneNoFlag){
		return true;
	}else{
		return false;
	}
}
$(document).ready(function(){
	var lcno = $("#lcno").val();
	if(lcno=="*-*"){
		$('#con_li').addClass("action");
	}
	
});



 /**
	 * 通过选中的城市地区，赋行政区域,车牌赋值
	 * 
	 */
function setDeptNoandLcnNo(areaValue,updateLcnNo) {
	$('#con_li').removeClass('action');
	$("#errorMessageforLcnNo").hide();
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
			if (provincedata[i].name === $.trim(province)) {
				for ( var j = 0; j < provincedata.length; j++) {
					if (provincedata[i].deptinforid === provincedata[j].parentid
							&& provincedata[j].name === $.trim(city)) {						
						var deptCode = provincedata[j].deptinforid;
						var lcnNo=provincedata[j].licensePlate;												
						for(var k=0;k<provincedata.length; k++){
							if(provincedata[j].deptinforid === provincedata[k].parentid&& provincedata[k].name === $.trim(county)){
								deptCode=provincedata[k].deptinforid;
							}
						}
						if (deptCode != null
								&& "undefined" !== typeof (deptCode)&&"null"!==dptcode) {							
								$("#dptcode").val(deptCode);														
						}
						if (lcnNo != null
								&& "undefined" !== typeof (lcnNo)) {
							if(!$(".con_p2 span").hasClass('on')&&updateLcnNo){								
									$("#lcno").val(lcnNo);
									$("#lcno").focus();															
							}else if(updateLcnNo){
								$("#phoneNo").focus();
							}
							
						}
						
					}
				}
			}

		}
	}

	
 	 
  }
/*$('.con_p2>span').click(function(){
	if($(this).hasClass("on")){
		$('#lcno').val(' ').removeAttr('readonly');		
	}else{
		$('#lcno').val('*-*').attr('readonly',true);
    	$('.errorpop').css("display","none")
	}
});*/

$(document).ready(function() {
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
	$('#demo1').click(function(){
		var bodyWidth=$('body').width();
			var left=bodyWidth-640
			var lefta=left/2
			$('.gearArea').css('left',lefta+'px');
		});
      }
});

function submitFrom() {
	var deptAddress=$("#demo1").val();
	var city=$("#city").val();
	var dptcode = $('#dptcode').val();
	if(deptAddress==="undefined"||""===deptAddress||deptAddress==null){
		deptAddress=city;
	}
	if(deptAddress==null||"undefined"===deptAddress||""===deptAddress){
		$('#Message').html("行驶区域不能为空");
		$("#errorhei").show();		
		//hide();
	}else if(!checkLcnNo()){
		return false;
	}else if(!checkPhone()){
		return false;
	}else{		
		setDeptNoandLcnNo(deptAddress,false);
		$("#form").submit();
		return true;
	}
	
}
/*function hide(){
	setTimeout(function(){$('.errorhei').hide()
		},2000);
	}*/

//登录
/*function login(){
	var address = $('#demo1').val();
	var chepai = $('#lcno').val();
	var dptcode = $('#dptcode').val();
	if(userName=="登录"){
		window.location.href=getUrl()+"/views/quicksure/jsp/LoginUser.jsp?address="+address+"&chepai="+chepai+"&dptcode="+dptcode;
	}else{
		if(window.confirm("确定退出当前账户吗？")){
			
		}
	}
}*/

function login(){
	var address = $('#demo1').val();
	var chepai = $('#lcno').val();
	var dptcode = $('#dptcode').val();
	var phoneNo=$("#phoneNo").val();
	if(address==null||"undefined"===address||""===address){
		address=$("#city").val();
	}
	if(userName!="登录"){
		/*e.preventDefault();*/
		if(window.confirm("确定退出当前账户吗？")){
			window.location.href=getUrl()+"/loginUser/logout.do";
		}
	}else{
		window.location.href=getUrl()+"/views/quicksure/jsp/LoginUser.jsp?address="+address+"&chepai="+chepai+"&dptcode="+dptcode+"&phoneNo="+phoneNo;
	}
	
}

//生成二维码
/*function createQrcode(){
	var strUrl="http://localhost:8088/ludiquickSureMobileServer/";
	window.location.href = getUrl()+"/loginUser/parseQRCode.do?userId="+userId+"&strUrl="+strUrl;
}*/

//车险生成二维码
function createQrcode(){
	/*var strUrl="http://m.quicksure.com/ludiquickSureMobileServer/";//生产环境地址*/
	var strUrl="http://test.quicksure.cn:8000/ludiquickSureMobileServer/";
	var isagentshare=$('#isagentshare').val();
	if(isagentshare==1){
		
	}else{
		isagentshare=0;
	}	
	window.location.href = getUrl()+"/loginUser/parseQRCode.do?userId="+userId+"&strUrl="+strUrl+"&isagentshare="+isagentshare;
}

//生成二维码(驾意险)
$(document).ready(function() {
	$("#qrcode").click(function(){
		var This_ul=$(".share-list")
		if(This_ul.hasClass('anima')){This_ul.removeClass('anima');}else{This_ul.addClass('anima');}		
	})
	$('.share-link').click(function(){
			$(this).parents('.share').removeClass('anima');
			$(".sharetips").show();
		})
	$(".sharetips").click(function(){
		$(this).hide();
	})
})


