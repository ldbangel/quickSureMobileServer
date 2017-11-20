/*$(document).ready(function(){
	$("#page_box").panel({iWheelStep:32});
});*/
var wr = new Optiscroll(document.getElementById('page_box'), { forceScrollbars: true });
$('.readNotice span').click(function(){
    var text=$(this).text(),Class=".page"+$(this).attr("data-a");
    $("#page_content").html($(Class).html());
    $('.page_title').html(text);
        $('.shade_box').show();
        $('#m-wrapper').removeClass('anima');
});
$('.close_icon').click(function(){
    $('.shade_box').hide();
    $('#m-wrapper').addClass('anima');
});
//保险类型改变
$('.title_list li').click(function(){
    var Index=$(this).index();
    $(this).addClass('Active').siblings().removeClass('Active');
    var val=$('#insrncPeriod').val();
    if(Index==0){
    	$('#sumamount').val('950000');
    	$('#prodtype').val('A');
        $('.price_0').html("50万");
        $('.price_0').siblings().html("5万");
        if(val==7){
            $('#price').html("11");
        }else if(val==30){
        	$('#price').html("14");
        }else if(val==90){
            $('#price').html("20");
        }else if(val==180){
        	$('#price').html("26");
        }else if(val==365){
        	$('#price').html("37");
        }
    }else {
    	$('#sumamount').val('2600000');
    	$('#prodtype').val('B');
    	console.log($('#sumamount').val());
    	console.log($('#prodtype').val());
    	$('.price_0').html("100万");
    	$('.alike').html("20万");
    	$('.alike_1').html("10万");
    	$('#price').html("27");
    	if(val==7){
    		$('#price').html("27");
    	}else if(val==30){
    		$('#price').html("35");
    	}else if(val==90){
    		$('#price').html("52");
    	}else if(val==180){ 
    		$('#price').html("66");
    	}else if(val==365){
    		$('#price').html("94");
    	}
    }
});

//保险天数改变
$("#insrncPeriod").change(function(){
	var val=$(this).val();
    var Index=$('.title_list .Active').index();
    if(Index==0){
    	if(val==7){
    		$('#price').html("11");
    	}else if(val==30){
    		$('#price').html("14");
    	}else if(val==90){
    		$('#price').html("20");
    	}else if(val==180){ 
    		$('#price').html("26");
    	}else if(val==365){
    		$('#price').html("37");
    	}
    }else{
    	if(val==7){
    		$('#price').html("27");
    	}else if(val==30){
    		$('#price').html("35");
    	}else if(val==90){
    		$('#price').html("52");
    	}else if(val==180){ 
    		$('#price').html("66");
    	}else if(val==365){
    		$('#price').html("94");
    	}
    }
});

$('.tab-wrap_select>li').click(function(){
    var Index=$(this).index();
    $(this).addClass('l_over').siblings().removeClass('l_over');
    if(Index==0){
        $('.particulars').show().siblings('div').hide();
    }else{
        $('.FAQ').show().siblings('div').hide();
    }
});


function submit(){
	$('#sumpremium').val($('#price').html());
	$('#submit').submit();
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

//生成二维码(一路平安)
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
    if(ylFlag==1 && ylFlag!==undefined){
		strUrl="http://test.quicksure.cn:8000/ludiquickSureMobileServer/views/quicksureFeiche/yilupingan/jsp/yiluHomePage.jsp";
		 window.location.href = getUrl()+"/loginUser/parseQRCode.do?userId="+userId+"&strUrl="+strUrl+"&isagentshare="+isagentshare;
	}
   
}
