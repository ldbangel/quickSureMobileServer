var orderCount1,orderCount2,orderCount3,orderCount4;
var initData;
var size;
var insuranceVOslist;
var showId=0;//用于判断是否显示非车或车险按钮
var showfcId=0;//用于跳转到非车的myAccount


$(document).ready(function(){
    var Index=30,Data;
    var content = $('#search_content').val();
    var index = $('#tabFlag').val();
    $('.Order_list a').eq(index).addClass('act');
    $('#chexianId').val(chexianId);
    if(chexianId==2||chexianId==1||(index!==null&& index!=="")){   	
    	init(content);
    }else{
    	 initfc(content);
    }
});

//查询非车和车险订单，判断是否显示弹框
function initfc(content){
    //初始化我的订单页面
    $.getJSON(getUrl()+'/myAccount/feichemyAccountInit.do?content='+content, function (data) {
    	/*initDatafc = data;
    	 insuranceVOslist = initDatafc.insuranceDetailsVOs;
    	 size=insuranceVOslist.length;*/
    	 size=data.countTotal
    	 $.getJSON(getUrl()+'/myAccount/myAccountInit.do?content='+content, function (data) {
    		    initData = data;
    	        orderCount1 = data.count1;
    	        orderCount2 = data.count2;
    			orderCount3 = data.count3;
    			orderCount4 = data.count4;
    			 if(size>0&&(orderCount1>0||orderCount2>0||orderCount3>0||orderCount4>0)){
    	    		 $('.chooseMyAccount').show();//如果非车订单和车险订单都不为空，显示弹框
    	    		 showId=1;
    	    		 $('#showId').val(showId);
    	    	 }else if(size<=0&&(orderCount1>0||orderCount2>0||orderCount3>0||orderCount4>0)){
    	    		 showId=0;//如果非车订单为空，车险订单不为空，直接进入车险订单页面
    	    		 init();
    	    	 }else if(size>0&&(orderCount1<=0&&orderCount2<=0&&orderCount3<=0&&orderCount4<=0)){
    	    		 showfcId=1;//如果车险订单为空，非车订单不为空，那么直接进入非车订单界面,并且在非车订单中不显示车险订单按钮
    	    		 window.location.href=getUrl()+"/views/quicksureFeiche/jiayixian/jsp/feiche_myAccount.jsp?showfcId="+showfcId;
    	    	 }else{
    	    		 showId=0;//如果非车和车险订单都为空，按么进入车险订单界面
    	    		 init();
    	    	 }
    	  });    	
    });
}
//点击车险进入车险订单界面
$('#chexian').click(function(){
    $('.chooseMyAccount').hide();
	init();
})
//点击非车进入非车订单,此情况代表车险同时也有订单
$('#feiche,#feicheID').click(function(){
	showfcId=2;
	window.location.href=getUrl()+"/views/quicksureFeiche/jiayixian/jsp/feiche_myAccount.jsp?showfcId="+showfcId;
})
//初始化
function init(content){
	$("#prompt").html("正在加载，请稍等...");
	load(10000);
	var content = $('#search_content').val();
	var chexianId = $('#chexianId').val();
	var showId = $('#showId').val();
    //初始化我的订单页面
    $.getJSON(getUrl()+'/myAccount/myAccountInit.do?content='+content, function (data) {
    	initData = data;
        orderCount1 = data.count1;
        orderCount2 = data.count2;
		orderCount3 = data.count3;
		orderCount4 = data.count4;
        $(".wait_pay").html(orderCount1);
        $(".already_pay").html(orderCount2);
        $(".Stop_keep").html(orderCount3);
        $(".On").html(orderCount4); 
        if(showId==1||chexianId==2){
        	$('#feicheID').show();
        }
        tabShowRule();
        $('#pop').hide();
    });
}


//展示tab
function showTab(flag){
	var insuranceVOs1 = initData.insuranceDetailsVOs1;
    var insuranceVOs2 = initData.insuranceDetailsVOs2;
    var insuranceVOs3 = initData.insuranceDetailsVOs3;
    var insuranceVOs4 = initData.insuranceDetailsVOs4;
    $('#pager').html('');
    var ordercount;
    if(flag==0){
    	orderCount=orderCount1;
    	for(var i=0;i<insuranceVOs1.length;i++){
    		var status="";
       	    if(insuranceVOs1[i].baseinfor.orderstate==30){
       	    	status="已下单";
       	    }else{
       	    	status="待支付";
       	    }
       	    addPolicyInfo(insuranceVOs1[i],status);
    	}
    }else if(flag==1){
    	orderCount=orderCount2;
    	for(var i=0;i<insuranceVOs2.length;i++){
    		var status="";
       	    if(insuranceVOs2[i].baseinfor.orderstate==50){
       	    	status="已支付";
       	    }else if(insuranceVOs2[i].baseinfor.orderstate==60){
       	    	status="已生效";
       	    }else if(insuranceVOs2[i].baseinfor.orderstate==70){
       	    	status="待配送";
       	    }else if(insuranceVOs2[i].baseinfor.orderstate==90){
       	    	status="已配送";
       	    }
       	    addPolicyInfo(insuranceVOs2[i],status);
    	}
    }else if(flag==2){
    	orderCount=orderCount3;
    	for(var i=0;i<insuranceVOs3.length;i++){
    		var status="";
    	    if(insuranceVOs3[i].baseinfor.orderstate==10){
    	    	status="待定";
       	    }else{
       	    	status="已报价";
       	    }
    	    addPolicyInfo(insuranceVOs3[i],status);
    	}
    }else if(flag==3){
    	orderCount=orderCount4;
    	for(var i=0;i<insuranceVOs4.length;i++){
    		var status = "已撤销";
    	    addPolicyInfo(insuranceVOs4[i],status);
    	}
    }
    var Last_Page;
    var That_Page = 1;
    if(typeof(orderCount)==undefined || orderCount==null || orderCount==""){
    	Last_Page = 1;
    }else{
    	Last_Page = parseInt(orderCount/10)+parseInt(orderCount%10>0?1:0);
    }
    Pager(That_Page,Last_Page);
}


//点击tab事件
$('.Order_list>a').click(function(e){
	$('#pager').html('');
	var This = $(this);
    e.preventDefault();
    This.addClass('act').siblings().removeClass('act');
    Index=This.attr('data-mode');
    $('#Order_information').html("");
    var flag=This.index();
    $('#tabFlag').val(flag);
    console.log($('#tabFlag').val());
    var content = $('#search_content').val();
    /*getdata(1,flag,content);*/
    showTab(flag);
});

//触发检索框
$('.search').click(function(){
	Content=$('#search_content').val();
	if(Content=="" || Content==null || Content==" "){
        $('#search_content').val('');
		$('#Message').html("搜索内容不能为空！");
		$('.errorhei').show();
	}else{
		$(".single_product").remove();
		$.ajaxSettings.async = false;
		init(Content);
		if(orderCount1==0&&orderCount2==0&&orderCount3==0&&orderCount4==0){
			$('#Message').html("您搜索的车主或车牌不存在!");
			$('.errorhei').show();
		}
	}
	
});


//点击我的订单按钮
$("#MyOrder_box").click(function(){
	$('#search_content').val('');
	var content = "";
	init(content);
});


//定时关闭弹出框
function load(outtimes){
	$("#pop").show();
	setTimeout(function(){$('#pop').hide();
	},outtimes);
}
	
//继续支付
function continuePay(orderno,userinforid){//继续支付
	$("#prompt").html("正在支付，请稍等...");
	load(60000);
	$.ajax({
	   type : "POST",
	   url : getUrl()+"/myAccount/continuePay.do?orderno="+orderno,
       async: false ,
	   success : function(result){
		   $("#pop").hide();
		   if(result==null||result==""){
			   $('#Message').html("支付异常！");
			   $('.errorhei').show();
			  /* $("#prompt").html("支付异常！");
			   load(1000);*/
		   }else{
			   window.location.href=result;
		   }
	   },
	    error: function(res){
	    	 $("#pop").hide();
	     	 $("#Message").html("支付异常");
	     	 $('.errorhei').show();
	     		     
	    }  
	});
}

//取消订单
function cancelOrder(orderno,userid){//取消订单
   var content = $('#search_content').val();
   var flag=$('.Order_list>.act').index();
   var A=$('.Order_list a');
   if(window.confirm('你确定要取消订单?')){
	    $("#prompt").html("正在撤销，请稍等...");
		load(60000);
		$.ajax({
		   type : "POST",
		   url : getUrl()+"/orderHandle/cancelOrder.do?orderno="+orderno,
		   success : function(result){
			    $("#pop").hide();
				if(result=="success"){
					$('#Message').html("取消成功！");
					$('.errorhei').show();
					$('.errortan3').click(function(){
						$('.errorhei').hide();
						/*$.ajaxSettings.async = false;*/
						init(content);
						if(flag==0){
							A.eq(0).click();
						}else if(flag==1){
							A.eq(1).click();
						}else if(flag==2){
							A.eq(2).click();
						}else if(flag==3){
							A.eq(3).click();
						}
					});
				}else{
					$('#Message').html("取消失败！");
					$('.errorhei').show();
					/*$("#prompt").html("取消失败！");
					load(1000);*/
				}
		   },
		   error: function(res){
		    	 $("#pop").hide();
		     	 $("#Message").html("取消失败");
		     	 $('.errorhei').show();
		     		     
		    }  		   
		});
        return true;
    }
}
   
//展示订单详情
function showOrderDetail(orderno,userinforid){
	window.location.href= getUrl()+"/myAccount/orderDetails.do?orderno="+orderno;
}

//继续投保
function continueInsure(orderno,userinforid){
   $.ajax({
	   type : "POST",
	   url : getUrl()+"/myAccount/continueInsure.do?orderno="+orderno,
       async: false ,
	   success : function(path){
		   if(path != ""){
			   window.location.href=getUrl()+path;
		   }else{
			   return false;
		   }
	   },
	   error: function(res){
	    	 $("#pop").hide();
	     	 $("#Message").html("投保失败");
	     	 $('.errorhei').show();
	     		     
	    }  
   });
}

//点击分页页码，请求后台返回对应页码的数据
function getdata(fewPage,flag,content){
   $.getJSON(getUrl()+'/myAccount/getMyOrders.do?curPage='+fewPage+'&flag='+flag+'&content='+content, function (data) {
	   orderCount1 = data.count1;
       orderCount2 = data.count2;
       orderCount3 = data.count3;
       orderCount4 = data.count4;
       var Data = data.insuranceDetailsVOs;
		
       $(".wait_pay").html(orderCount1);
       $(".already_pay").html(orderCount2);
       $(".Stop_keep").html(orderCount3);
       $(".On").html(orderCount4);
       
	   var That_Page = fewPage;
	   var orderCount;
	   $('#Order_information').html("");
	   for(var i=0;i<Data.length;i++){
	       if(flag == 0){
	    	   var orderstate="";
	       	   if(Data[i].baseinfor.orderstate==30){
	       		   orderstate="已下单";
	       	   }else{
	       		   orderstate="待支付";
	       	   }
	       	   orderCount=orderCount1;
	       	   addPolicyInfo(Data[i],orderstate);
	       }else if(flag == 1){
	    	   var orderstate="";
	       	   if(Data[i].baseinfor.orderstate==50){
	       		   orderstate="已支付";
	       	   }else if(Data[i].baseinfor.orderstate==60){
	       		   orderstate="已生效";
	       	   }else if(Data[i].baseinfor.orderstate==70){
	       		   orderstate="待配送";
	       	   }else if(Data[i].baseinfor.orderstate==90){
	       		   orderstate="已配送";
	       	   }
	       	   orderCount=orderCount2;
	       	   addPolicyInfo(Data[i],orderstate);
	       }else if(flag == 2){
	    	   var orderstate="";
	    	   if(Data[i].baseinfor.orderstate==10){
	       		   orderstate="待定";
	       	   }else{
	       		   orderstate="已报价";
	       	   }
	    	   orderCount=orderCount3;
	    	   addPolicyInfo(Data[i],orderstate);
	       }else if(flag == 3){
	    	   orderCount=orderCount4;
	    	   var orderstate = "已撤销";
	    	   addPolicyInfo(Data[i],orderstate);
	       }
	   }
	   var Last_Page;
	   if(typeof(orderCount)==undefined || orderCount==null || orderCount==""){
	   	Last_Page = 1;
	   }else{
	   	Last_Page = parseInt(orderCount/10)+parseInt(orderCount%10>0?1:0);
	   }
	   Pager(That_Page,Last_Page);
   });
}
//点击分页页码
$('#pager').on('click','#pager>li',function(event){
   var This=$(this),fewPage,flag=$('.Order_list>.act').index();
   var content = $('#search_content').val();
   if(This.hasClass("active")){
       return true;
   }else if(This.children().length!=0){
	   if(This.hasClass("None_1")||This.hasClass("None")){//判断上一页或下一页
		   if(This.hasClass("prev")){//判断首页或尾页
			   fewPage=parseFloat(parseInt(This.siblings('.active').text())-1);
		   }else if(This.hasClass("next")){
			   fewPage=parseFloat(parseInt(This.siblings('.active').text())+1);
		   }else if(This.hasClass("first")){
			   fewPage=parseFloat(parseInt(1));
		   }else{
			   fewPage=parseFloat(parseInt(This.attr('data-lastpage')));
		   }
	   }else{
		   return true;
	   }
      
       getdata(fewPage,flag,content);$(document).scrollTop(0);
   }else{
       This.addClass("active").siblings('.active').removeClass('active');
       fewPage=parseFloat(This.text());
       //flag=$('.Order_list li').children('.act').index();
       getdata(fewPage,flag,content);$(document).scrollTop(0);
   }
});
  
//分页栏
function Pager(That_Page,Last_Page){
	 var pager=$('#pager');
	 pager.html("");
	 if((That_Page-1)>0){
		 pager.append("<li class='prevPage None first att' ><span>首页</span></li>");
		 pager.append("<li class='prevPage None prev att' ><span>上一页</span></li>");
	 }else{
		 pager.append("<li class='prevPage' ><span>首页</span></li>");
		 pager.append(" <li class='prevPage '><span>上一页</span></li>");
     }
	
     if((That_Page-1)>0){pager.append("<li class='att'>"+parseInt(That_Page-1)+"</li>");}
     pager.append("<li class='active'>"+That_Page+"</li>");
     if((That_Page+1)<=Last_Page){pager.append("<li class='att'>"+ parseFloat(That_Page+1) +"</li>");}
   
     console.log(That_Page+1);console.log(typeof(That_Page+1));
     if((That_Page+1)<=Last_Page){
    	 pager.append(" <li class='nextPage None_1 next att'><span>下一页</span></li>");
    	 pager.append(" <li class='nextPage None_1 last att' data-lastpage="+Last_Page+"><span>尾页</span></li>");
     }else{
    	 pager.append(" <li class='nextPage'><span>下一页</span></li>");
    	 pager.append(" <li class='nextPage'><span>尾页</span></li>");
     }
     pager.append(" <li class='AllPage'><span>共"+Last_Page+"页</span></li>");
}	

//我的订单详情动态加载
function addPolicyInfo(insuranceDetailsVO,orderstate){
	var orderno="'"+insuranceDetailsVO.baseinfor.orderno+"'";
	var useId="'"+insuranceDetailsVO.baseinfor.userinforid+"'";
	var order_info = $('#Order_information');
	var sp_div = $('<div class="single_product"/>');
	sp_div.append($('<ul class="Top_mation"/>').append('<li class="left">订单编号: <span class="indent_number">'+insuranceDetailsVO.baseinfor.orderno+'</span></li class="right"><li class="right">状态: <span class="mode">'+orderstate+'</span></li>'));
	var mm_div = $('<div class="Middle_mation" onclick="showOrderDetail('+orderno+','+useId+');"/>');
	mm_div.append('<img src="'+urlpath+'/views/quicksure/images/'+ insuranceDetailsVO.vhlinfor.codeName+'.jpg" class="car_img"/>');
	var sp_ul = $('<ul class="Car_mation"/>').append('<li class="main_search">车牌号: <span class="Car_number">'+insuranceDetailsVO.vhlinfor.lcnno+'</span>车主:<span class="user_Name">'+insuranceDetailsVO.vhlinfor.drvowner+'</span></li>');
	if(insuranceDetailsVO.baseinfor.updatetime != null && insuranceDetailsVO.baseinfor.updatetime != ""){
		sp_ul.append($('<li style="color:#333;">更新时间:<span class="begin_date">'+insuranceDetailsVO.baseinfor.updatetime.slice(0,19)+'</span></li>'));
	}else{
		sp_ul.append($('<li style="color:#333;">创建时间:<span class="begin_date">'+insuranceDetailsVO.baseinfor.createtime.slice(0,19)+'</span></li>'));
	}
	if(insuranceDetailsVO.baseinfor.sypolicystartdate != null && insuranceDetailsVO.baseinfor.sypolicystartdate != ""){
		sp_ul.append($('<li style="color:#333;">商业险起期:<span class="begin_date">'+insuranceDetailsVO.baseinfor.sypolicystartdate.slice(0,11)+'</span></li>'));
	}
	if(insuranceDetailsVO.baseinfor.jqpolicystartdate != null && insuranceDetailsVO.baseinfor.jqpolicystartdate !=""){
		sp_ul.append($('<li style="color:#333;">交强险起期:<span class="begin_date">'+insuranceDetailsVO.baseinfor.jqpolicystartdate.slice(0,11)+'</span></li>'));
	}
	if(insuranceDetailsVO.baseinfor.orderstate==30||insuranceDetailsVO.baseinfor.orderstate==40){
		if(insuranceDetailsVO.baseinfor.syapplicationno != null){
			sp_ul.append($('<li style="color:#333;"><pre>商业投保单号:</pre><span class="end_date">'+insuranceDetailsVO.baseinfor.syapplicationno+'</span></li>'));
		}
		if(insuranceDetailsVO.baseinfor.jqapplicationno != null){
			sp_ul.append($('<li style="color:#333;"><pre>交强投保单号:</pre><span class="end_date">'+insuranceDetailsVO.baseinfor.jqapplicationno+'</span></li>'));
		}
	}
	if(insuranceDetailsVO.baseinfor.orderstate==50||insuranceDetailsVO.baseinfor.orderstate==60||insuranceDetailsVO.baseinfor.orderstate==70||insuranceDetailsVO.baseinfor.orderstate==90){
		if(insuranceDetailsVO.baseinfor.sypolicyno!=null){
			sp_ul.append($('<li style="color:#333;"><pre>商业保单号:</pre><span class="end_date">'+insuranceDetailsVO.baseinfor.sypolicyno+'</span></li>'));
		}
		if(insuranceDetailsVO.baseinfor.jqpolicyno!=null){
			sp_ul.append($('<li style="color:#333;"><pre>交强保单号:</pre><span class="end_date">'+insuranceDetailsVO.baseinfor.jqpolicyno+'</span></li>'));
		}
	}
	mm_div.append(sp_ul).append('<img src="'+urlpath+'/views/quicksure/images/BM.png" style="vertical-align:middle;"/>');
	sp_div.append(mm_div);
	var p = $('<p/>');
	if(insuranceDetailsVO.baseinfor.orderstate==30||insuranceDetailsVO.baseinfor.orderstate==40){
		p.append('<a href="#" onclick="continuePay('+orderno+','+useId+');">继续支付</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelOrder('+orderno+','+useId+');">取消订单</a>&nbsp;&nbsp;&nbsp;&nbsp;');
	}
	if(insuranceDetailsVO.baseinfor.orderstate==10||insuranceDetailsVO.baseinfor.orderstate==20){
		p.append('<a href="#" onclick="continueInsure('+orderno+','+useId+');">继续投保</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelOrder('+orderno+','+useId+');">取消订单</a>');
	}
	p.append('<span style="color:#333;font-size:11px;">金额</span>: <span class="money">'+insuranceDetailsVO.baseinfor.totalPremium+'</span>');
	sp_div.append(p);
	order_info.append(sp_div);
}

//隐藏消息弹出框
$('.errortan3').click(function(){
    $('.errorhei').hide();

});

//我的订单tab展示规则
function tabShowRule(){
	var This = $('.Order_list>.act'),A=$('.Order_list a');
	var index = This.index();
	console.log("这个tab的index为："+index);
	var num = This.children().text();
	console.log("num为："+num);
	if(num==0){
		if($('.wait_pay').text()!=0 && index!=0){
			A.eq(0).click();
		}else if($('.already_pay').text()!=0 && index!=1){
			A.eq(1).click();
		}else if($('.Stop_keep').text()!=0 && index!=2){
			A.eq(2).click();
		}else if($('.On').text()!=0 && index!=3){
			A.eq(3).click();
		}else if($('.wait_pay').text()==0&&$('.already_pay').text()==0
				&&$('.Stop_keep').text()==0&&$('.On').text()==0){
			A.eq(0).click();
		}
	}else{
		if(index==0){
			A.eq(0).click();
		}else if(index==1){
			A.eq(1).click();
		}else if(index==2){
			A.eq(2).click();
		}else if(index==3){
			A.eq(3).click();
		}
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
