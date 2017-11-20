//取绝对路径地址
function getPath(){
	 var protocol = window.location.protocol;
	 var host = window.location.host;
	 var pathname = window.location.pathname.split('/');
	 var url = protocol+"//"+host+"/"+pathname[1];
	 return url;
}

$(document).ready(function(){
	var ua = window.navigator.userAgent.toLowerCase(); //识别微信浏览器
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
    	iswechatBrowser=true;
    }else{
    	iswechatBrowser=false;
    }
    
    if(iswechatBrowser){ //如果是微信浏览器就去配置微信JS接口
    	var currenturl = location.href.split('#')[0];
    	if(currenturl=="http://m.quicksure.com/ludiquickSureMobileServer/" || 
    		currenturl=="http://m.quicksure.com/ludiquickSureMobileServer/views/quicksure/jsp/quicksurehome.jsp"){
    		return ;
    	}
    	var sharedTitle = "慧英保险";
    	var imageUrl;
    	if(currenturl.indexOf("jyx")>=0 || currenturl.indexOf("diverInsure/")>=0){
    		sharedTitle="【车主福利】超级优惠的高保额驾乘险来啦，完美替代座位险，耶~";
    		imageUrl = getPath()+"/views/quicksure/images/jiayixian.jpg";
    	}else if(currenturl.indexOf("jab")>=0 || currenturl.indexOf("PersonalAccidentInsurance/")>=0){
    		sharedTitle="【君安宝】，一款全家都适合的全方位综合意外保障，你值得拥有！";
    		imageUrl = getPath()+"/views/quicksure/images/junanbao.jpg";
    	}else if(currenturl.indexOf("ylpa")>=0 || currenturl.indexOf("VehicleAccidentInsurance/")>=0){
    		sharedTitle="【一路平安】超10种交通工具意外保障，100万高额保障，11元起，速速来抢！";
    		imageUrl = getPath()+"/views/quicksure/images/yilupingan.jpg";
    	}else{
    		sharedTitle="车险投保就戳我";
    		imageUrl = getPath()+"/views/quicksure/images/index-bg.png";
    	}
		$.ajax({  
			type: "POST",  
			url: getPath()+"/WechatLogin/getWechatJSAccess.do",  
			data:currenturl ,//将对象序列化成JSON字符串  
			dataType:"json",  
			contentType:'application/json;charset=utf-8', //设置请求头信息  		
			success: function(data){ 
				var sharedLink = data.sharedLink;
				wx.config({
				    debug: false, 
				    appId: data.appid, // 必填，公众号的唯一标识
				    timestamp:data.timestamp , // 必填，生成签名的时间戳
				    nonceStr: data.nonceStr,   // 必填，生成签名的随机串
				    signature: data.signature.toLowerCase(), // 必填，签名，见附录1
				    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表
				});
				wx.ready(function(){ //通过ready接口处理成功验证,需要把相关接口放在ready函数中调用来确保正确执行
					//实现JS分享功能
					wx.onMenuShareTimeline({//1.获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
		                title: sharedTitle, // 分享标题
		                link: sharedLink, // 分享链接
		                imgUrl: imageUrl, // 分享图标
		                success: function () { 
		                    // 用户确认分享后执行的回调函数
		                },
		                cancel: function () { 
		                    // 用户取消分享后执行的回调函数
		                }
		            });
		            wx.onMenuShareAppMessage({//2.获取“分享给朋友”按钮点击状态及自定义分享内容接口
		                title: sharedTitle, // 分享标题
		                desc: "http://m.quicksure.com/ludiquickSureMobileServer", // 分享描述
		                link: sharedLink, // 分享链接
		                imgUrl: imageUrl, // 分享图标
		                type: '', // 分享类型,music、video或link，不填默认为link
		                dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		                success: function () { 
		                    // 用户确认分享后执行的回调函数
		                },
		                cancel: function () { 
		                    // 用户取消分享后执行的回调函数
		                }
		            });
				});
		    },  
		    error: function(res){
		    	 $("#pop").hide();
		     	 $("#Message").html("微信登录失败");
		     	 $('.errorhei').show();
		     	 $('.beijing').hide();
		    }  
		});	
	}
});
/**
 * 分享链接后登录
 */
$(document).ready(function(){
	if(shareId!==0 && shareId!==null && shareId!=="" ){//非车链接分享出单,获取到userId
		var feicShareID=1;
		$.ajax({
			type:"POST",
			url:getPath()+"/loginUser/weixiLoginByOpenId.do",      //通过userId获取用户信息进行登录
			async:false,
			data:{shareId:shareId,feicShareID:feicShareID},
			success:function(result){
				if(result!==null&&result!==""){
					
				}else{				
					/*alert("success");*/
					$('.top_right').hide();	
					$('.td_left').hide();
					$('.td_right').hide();
					$('#qrcode').show();
					$('#isagentshare').val(1);
				}
			}
	   });
	}
})


