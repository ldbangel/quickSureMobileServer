//提交表单
function submitForm(){
    var quota = $('.price_1').html();
    var medical =$('.price_2').html();
	var hospitalization=$('.price_3').html();
	var sumPremium = $('.price_left').html();
	var prodType;
	var sumamount;//保额
	if(quota=="10万"){
		prodType="A";
		sumamount='119000';
	}else if(quota=="20万"){
		prodType="B";
		sumamount='238000';
	}else if(quota=="30万"){
		prodType="C";
		sumamount='357000';
	}
	$("#prodType").val(prodType);
	$("#sumPremium").val(sumPremium);
	$("#sumamount").val(sumamount);
	document.getElementById("form").submit();
}

//从我的订单跳转到首页，获取订单号
$(document).ready(function(){
	var orderno=$('#orderNo').val();
	if(orderno==null||orderno==""){
		$('#orderNo').val(orderNo);
	}	
})

//非车二维码分享显示
$(document).ready(function(){
	if(agentfcFlag!=0 && agentfcFlag!==undefined){
		$("#qrcode").show();
	}
	if(isagentshare==1){
		$("#qrcode").show();
		$('.td_order').hide();
		$('#isagentshare').val(1);
	}
});

//扫描二维码然后登陆
$(document).ready(function(){	
	if(codeId=="1"){//二维码扫描到首页然后登录		
		$.ajax({
			type: "post",
			url : getUrl()+"/loginUser/userLogin.do",
			async: false,
			data: {userIdQr:userIdQr,codeId:codeId},
			success : function(result){
				if(result!==null&&result!==""){
					
				}else{	
					$("#qrcode").show();
					$('.td_order').hide();
					$('#isagentshare').val(1);					
				}
			}
	   });
	}
});

//生成二维码(君安保)
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

/**
 * 非车险点击生成二维码
 */
function createFcQrcode(){
	var strUrl;
	var isagentshare=$('#isagentshare').val();
	if(isagentshare==1){
		
	}else{
		isagentshare=0;
	}	
     if(jaFlag==1 && jaFlag!==undefined){
		strUrl="http://test.quicksure.cn:8000/ludiquickSureMobileServer/views/quicksureFeiche/junanbao/jsp/junanbaoHome.jsp";
		 window.location.href = getUrl()+"/loginUser/parseQRCode.do?userId="+userId+"&strUrl="+strUrl+"&isagentshare="+isagentshare;
	}
}
