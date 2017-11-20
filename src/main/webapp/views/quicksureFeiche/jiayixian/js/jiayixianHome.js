//提交表单
function submitForm(){
    var quota = $('.price_1').html();//
    var medical =$('.price_2').html();
    var Hospit=$('.price_3').html();
	var price = $('.price_left').html();//保费
	var injuryinsura;//伤害险保额
	var medicalinsura;//医疗保险保额
	var Hospitallow;//住院津贴
	var sumamount;//总保额
	var prodType;
	if(quota=="2万"){
		prodType="A";
		injuryinsura=20000;
		medicalinsura=2000;
		Hospitallow=0;
	}else if(quota=="5万"){
		prodType="B";
		injuryinsura=50000;
		medicalinsura=3000;
		Hospitallow=3600;
	}else if(quota=="10万"&&Hospit=="--"){
		prodType="C";
		injuryinsura=100000;
		medicalinsura=10000;
		Hospitallow=0;
	}else if(quota=="10万"&&Hospit==5400){
		prodType="D";
		injuryinsura=100000;
		medicalinsura=10000;
		Hospitallow=5400;
	}else if(quota=="15万"){
		prodType="E";
		injuryinsura=150000;
		medicalinsura=10000;
		Hospitallow=9000;
	}else if(quota=="25万"){
		prodType="F";
		injuryinsura=250000;
		medicalinsura=20000;
		Hospitallow=18000;
	}
	var sumamount=parseInt(injuryinsura)+parseInt(medicalinsura)+parseInt(Hospitallow); 
	/*var sumamounts=parseFloat(sumamount).toFixed(1);//添加一个小数点 */
	$("#prodType").val(prodType);
	$("#sumPremium").val(price);
	$("#sumamount").val(sumamount);
	
	document.getElementById("form").submit();
}
//从我的订单跳转到首页，获取订单号
$(document).ready(function(){
	var orderno=$('#orderno').val();
	if(orderno==null||orderno==""){
		$('#orderno').val(orderNo);
	}	
})

//非车二维码分享显示
$(document).ready(function(){
	if(agentfcFlag!=0 && agentfcFlag!==undefined){
		$("#qrcode").show();
	}
	if(isagentshare==1){
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
					$('.td_order').hide();
					$('#isagentshare').val(1);					
				}
			}
	   });
	}
});


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
    if(jyFlag==1 && jyFlag!==undefined){
    	strUrl="http://test.quicksure.cn:8000/ludiquickSureMobileServer/views/quicksureFeiche/jiayixian/jsp/jiayixianHome.jsp";
    	 window.location.href = getUrl()+"/loginUser/parseQRCode.do?userId="+userId+"&strUrl="+strUrl+"&isagentshare="+isagentshare;
	}
}

