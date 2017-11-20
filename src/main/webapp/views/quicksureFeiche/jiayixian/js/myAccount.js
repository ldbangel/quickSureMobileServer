var initData,orderCount1,orderCount2,orderCount3,orderCount4;
var size;
var chexianId=0;
var feicheshowId=0;//用于判断是否显示非车或车险按钮

$(document).ready(function(){
	var content = $('#search_box').val();
	var index = $('#tabFlag').val();
	$('.title_list li').eq(index).addClass('Active'); 
	if(!content || content==null){
		content = "";
	}
	$('#showfcId').val(showfcId);
	if(showfcId==2||showfcId==1||(index!==null&& index!=="")){//如果是从车险订单跳转过来，直接加载，不显示弹框,等于1代表车险订单为空，2代表不为空
		initMyAccount(content);
	}else{
		initfc();
	}
	
});

//查询非车和车险订单，判断是否显示弹框
function initfc(content){
    //初始化我的订单页面
	var content = $('#search_box').val();
    $.getJSON(path+'/myAccount/feichemyAccountInit.do?content='+content, function (data) {
    	/*initDatafc = data;
    	 insuranceVOslist = initDatafc.insuranceDetailsVOs;
    	 size=insuranceVOslist.length;*/
    	 size=data.countTotal
    	 $.getJSON(path+'/myAccount/myAccountInit.do?content='+content, function (data) {
    		    initData = data;
    	        orderCount1 = data.count1;
    	        orderCount2 = data.count2;
    			orderCount3 = data.count3;
    			orderCount4 = data.count4;
    			 if(size>0&&(orderCount1>0||orderCount2>0||orderCount3>0||orderCount4>0)){//如果非车订单和车险订单都不为空，显示弹框    				 
    				 $('.chooseMyAccount').show();
    	    		 feicheshowId=1;//代表两者订单都不为空，用于显示车险订单按钮
    	    		 $('#feicheshowId').val(feicheshowId);
    	    	 }else if(size>0&&(orderCount1<=0&&orderCount2<=0&&orderCount3<=0&&orderCount4<=0)){//如果车险订单为空，非车订单不为空
    	    		 feicheshowId=0;//，那么直接进入非车订单界面
    	    		 initMyAccount();
     	    	 }else if(size<=0&&(orderCount1>0||orderCount2>0||orderCount3>0||orderCount4>0)){//如果非车订单为空，车险订单不为空，直接进入车险订单页面
					 chexianId=1;
	   	    		 window.location.href=path+"/views/quicksure/jsp/myAccount.jsp?chexianId="+chexianId;//跳转到车险订单界面，此情况代表非车险没有订单					 
    	    	 }else{
    	    		 feicheshowId=0;//如果非车和车险订单都为空，按么进入非车险订单界面
    	    		 initMyAccount();
    	    	 }
    	  });    	
    });
}
//点击非车险进入非车险订单界面
$('#feiche').click(function(){
    $('.chooseMyAccount').hide();
    initMyAccount();
})
//点击车险进入车险订单页面,此情况代表非车险同时也有订单
$('#chexian,#chexianChoose').click(function(){
	chexianId=2;
	window.location.href=path+"/views/quicksure/jsp/myAccount.jsp?chexianId="+chexianId;
})

//状态菜单切换
$(".title_list li").click(function(){
    $(this).addClass('Active').siblings().removeClass('Active');
});

//初始化
function initMyAccount(content){
	$("#prompt").html("正在加载，请稍等...");
	load(10000);
    //初始化我的订单页面
	var content = $('#search_box').val();
	var feicheshowId = $('#feicheshowId').val();
	var showfcId = $('#showfcId').val();
    $.getJSON(path+'/feicheMyAccount/initMyAccount.do?content='+content, function (data) {
    	initData = data;
        orderCount1 = data.count1;
        orderCount2 = data.count2;
		orderCount3 = data.count3;
		orderCount4 = data.count4;
        $(".wait_pay").html(orderCount1);
        $(".already_pay").html(orderCount2);
        $(".stop_keep").html(orderCount3);
        $(".closed").html(orderCount4);
        if(feicheshowId==1||showfcId==2){
        	$('#chexianChoose').show();
        }
        tabShowRule();
        $('#pop').hide();
    });
}

//我的订单tab展示规则
function tabShowRule(){
	var This = $('.title_list>.Active'),A=$('.title_list li');
	var index = This.index();
	var num = This.children().text();
	if(num==0){
		if($('.wait_pay').text()!=0 && index!=0){
			A.eq(0).click();
		}else if($('.already_pay').text()!=0 && index!=1){
			A.eq(1).click();
		}else if($('.stop_keep').text()!=0 && index!=2){
			A.eq(2).click();
		}else if($('.closed').text()!=0 && index!=3){
			A.eq(3).click();
		}else if($('.wait_pay').text()==0&&$('.already_pay').text()==0
				&&$('.stop_keep').text()==0&&$('.closed').text()==0){
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

//点击tab事件
$('.title_list>li').click(function(e){
	$('#pager').html('');
	var This = $(this);
    e.preventDefault();
    This.addClass('Active').siblings().removeClass('Active');
    $('#Order_information').html("");
    var flag=This.index();
    $('#tabFlag').val(flag);
    $('#pager').html('');
    var baseinfors;
    if(flag=="0"){
    	baseinfors = initData.baseinfor1;
    }else if(flag=="1"){
    	baseinfors = initData.baseinfor2;
    }else if(flag=="2"){
    	baseinfors = initData.baseinfor3;
    }else if(flag=="3"){
    	baseinfors = initData.baseinfor4;
    }
    showTab(flag,baseinfors,1);
});

//展示tab
function showTab(flag,baseinfors,That_Page){
    var ordercount;
    if(flag==0){
    	orderCount = orderCount1;
    	for(var i=0;i<baseinfors.length;i++){
    		var status="";
       	    if(baseinfors[i].orderstate==40){
       	    	status="待支付";
       	    }
       	    addPolicyInfo(baseinfors[i],status);
    	}
    }else if(flag==1){
    	orderCount = orderCount2;
    	for(var i=0;i<baseinfors.length;i++){
    		var status="";
       	    if(baseinfors[i].orderstate==50){
       	    	status="已支付";
       	    }else if(baseinfors[i].orderstate==60){
       	    	status="已生效";
       	    }else{
       	    	status="已配送";
       	    }
       	    addPolicyInfo(baseinfors[i],status);
    	}
    }else if(flag==2){
    	orderCount = orderCount3;
    	for(var i=0;i<baseinfors.length;i++){
    		var status="";
    	    if(baseinfors[i].orderstate==10){
    	    	status="待定";
       	    }else if(baseinfors[i].orderstate==20||baseinfors[i].orderstate==30){
       	    	status="已报价";
       	    }
    	    addPolicyInfo(baseinfors[i],status);
    	}
    }else if(flag==3){
    	orderCount = orderCount4;
    	for(var i=0;i<baseinfors.length;i++){
    		var status = "已撤销";
    	    addPolicyInfo(baseinfors[i],status);
    	}
    }
    var Last_Page;
    if(typeof(orderCount)==undefined || orderCount==null || orderCount==""){
    	Last_Page = 1;
    }else{
    	Last_Page = parseInt(orderCount/10)+parseInt(orderCount%10>0?1:0);
    }
    Pager(That_Page,Last_Page);
}

//我的订单详情动态加载
function addPolicyInfo(baseinfor,orderstate){
	var orderno="'"+baseinfor.orderno+"'";
	var useId="'"+baseinfor.userinforid+"'";
	var order_info = $('#Order_information');
	var sp_div = $('<div class="single_product"/>');
	sp_div.append($('<ul class="Top_mation"/>').append('<li class="left">订单编号: <span class="indent_number" style="color: orange;">'+baseinfor.orderno+'</span></li class="right"><li class="right">状态: <span class="mode" style="color: orange;">'+orderstate+'</span></li>'));
	var mm_table = $('<table class="middle_mation" onclick="showOrderDetail('+orderno+','+useId+');"/>');
	var mm_tr = $('<tr/>');
	var mm_td1 = $('<td class="td_1"/>');
	var mm_td2 = $('<td class="td_2"/>');
	if(baseinfor.prodno && baseinfor.prodno=='060a'){
		mm_td1.append('<img src="'+path+'/views/quicksureFeiche/jiayixian/images/yilupingan.jpg" class="car_img"/>');
		mm_td2.append('<p>险种名称: <span>华安一路平安交通工具意外伤害保险</span></p>');
	}else if(baseinfor.prodno && baseinfor.prodno=='060I'){
		mm_td1.append('<img src="'+path+'/views/quicksureFeiche/jiayixian/images/jiayixian.jpg" class="car_img"/>');
		mm_td2.append('<p>险种名称: <span>华安驾校学员意外伤害保险</span></p>');
	}else if(baseinfor.prodno && baseinfor.prodno=='0615'){
		mm_td1.append('<img src="'+path+'/views/quicksureFeiche/jiayixian/images/junanbao.jpg" class="car_img"/>');
		mm_td2.append('<p>险种名称: <span>个人人身意外伤害险</span></p>');
	}
	if(!baseinfor.insurername || baseinfor.insurername==null || baseinfor.insurername==""){
		mm_td2.append('<p>被保人名字: <span>暂无数据</span></p>');
	}else{
		mm_td2.append('<p>被保人名字: <span>'+baseinfor.insurername+'</span></p>');
	}
	if(!baseinfor.updatetime || baseinfor.updatetime==null || baseinfor.updatetime==""){
		mm_td2.append('<p>更新时间: <span>暂无更新</span></p>');
	}else{
		mm_td2.append('<p>更新时间: <span>'+formatDate(baseinfor.updatetime)+'</span></p>');
	}
	if(!baseinfor.insuranceStartTime || baseinfor.insuranceStartTime==null || baseinfor.insuranceStartTime==""){
		mm_td2.append('<p>保险起期: <span>暂无数据</span></p>');
	}else{
		mm_td2.append('<p>保险起期: <span>'+baseinfor.insuranceStartTime+'</span></p>');
	}
	mm_tr.append(mm_td1).append(mm_td2);
	mm_table.append(mm_tr);
	var end_div = $('<div class="bottom_mation"/>');
	var end_ul = $('<ul/>');
	var end_li1 = $('<li class="left_select"/>');
	if(baseinfor.orderstate=="40"){
		end_li1.append('<span class="Active" onclick="continuePay('+orderno+');">继续支付</span><span onclick="">取消订单</span>');
	}else if(baseinfor.orderstate=="10" || baseinfor.orderstate=="20" || baseinfor.orderstate=="30" ){
		end_li1.append('<span class="Active" onclick="continueInsure('+orderno+');">继续投保</span><span onclick="">取消订单</span>');
	}else if(baseinfor.orderstate=="50" || baseinfor.orderstate=="60" || baseinfor.orderstate=="70"){
		
	}else if(baseinfor.orderstate=="80"){
		
	}
	end_ul.append(end_li1);
	if(baseinfor.orderstate=="20" ||baseinfor.orderstate=="30" || baseinfor.orderstate=="40" || baseinfor.orderstate=="50" || baseinfor.orderstate=="60" || baseinfor.orderstate=="70"){
		end_ul.append('<li>总保费: <span style="color: orange;">'+baseinfor.sumpremium+'</span></li>');
	}else{
		end_ul.append('<li>总保费: <span style="color: orange;">0:00</span></li>');
	}
	end_div.append(end_ul);
	order_info.append(sp_div).append(mm_table).append(end_div);
}

//分页栏
function Pager(That_Page,Last_Page){
	var pager=$('#pager');
	pager.html("");
	if((That_Page-1)>0){
		pager.append('<li><span>首页</span></li>');
		pager.append('<li class="prev" ><span>上一页</span></li>');
		pager.append('<li>'+parseInt(That_Page-1)+'</li>');
	}else{
		pager.append('<li class="none"><span>首页</span></li>');
		pager.append('<li class="none"><span>上一页</span></li>');
	}
	pager.append('<li class="act">'+That_Page+'</li>');
	if((That_Page+1)<=Last_Page){
		pager.append('<li>'+ parseFloat(That_Page+1) +'</li>');
		pager.append('<li class="next go"><span>下一页</span></li>');
		pager.append('<li class="go" data-last="'+Last_Page+'"><span>尾页</span></li>');
	}else{
		pager.append('<li class="none"><span>下一页</span></li>');
		pager.append('<li class="none"><span>尾页</span></li>');
	}
	pager.append('<li class="All_page" disabled="disabled"><span>共'+Last_Page+'页</span></li>');
}

//点击分页栏
$('#pager').on('click','#pager>li',function(event){
	var This=$(this),fewPage,flag=$('.title_list>.Active').index();
	var content = $('#search_box').val();
	if(This.hasClass('act')||This.hasClass('All_page')||This.hasClass('none')){
		return true;
	}else{
		if(This.children().length!=0){
			if(This.hasClass('go')){//判断 此页 的  右边还是左边
				if(This.hasClass('next')){
					fewPage=parseFloat(parseInt(This.siblings('.act').text())+1);
				}else{
					fewPage=parseInt(This.attr('data-last'))
				}
			}else {
				if (This.hasClass('prev')) {
					fewPage = parseFloat(parseInt(This.siblings('.act').text())-1);
				} else {
					fewPage = 1
				}
			}
			getdata(fewPage,flag,content);
			$(document).scrollTop(0);
		}else{
			fewPage = parseFloat(This.text());
			getdata(fewPage,flag,content);
			$(document).scrollTop(0);
		}
	}
});

//点击分页页码，请求后台返回对应页码的数据
function getdata(fewPage,flag,content){
   $.getJSON(path+'/feicheMyAccount/getMyOrders.do?curPage='+fewPage+'&flag='+flag+'&content'+content, function (data) {
	   orderCount1 = data.count1;
       orderCount2 = data.count2;
       orderCount3 = data.count3;
       orderCount4 = data.count4;
       var Data = data.baseinfors;
		
       $(".wait_pay").html(orderCount1);
       $(".already_pay").html(orderCount2);
       $(".stop_keep").html(orderCount3);
       $(".closed").html(orderCount4);

       $('#Order_information').html("");
	   var That_Page = fewPage;
	   showTab(flag,Data,That_Page);
   });
}

//继续支付
function continuePay(orderno){//继续支付
	$("#prompt").html("正在支付，请稍等...");
	load(60000);
	$.ajax({
	   type : "POST",
	   url : path+"/feicheMyAccount/continuePay.do?orderno="+orderno,
       async: false ,
	   success : function(result){
		   $("#pop").hide();
		   if(result==null||result==""){
			   $('#Message').html("支付异常！");
			   $('.errorhei').show();
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
function cancelOrder(orderno){//取消订单
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

//继续投保
function continueInsure(orderno){
   $.ajax({
	   type : "POST",
	   url : path+"/feicheMyAccount/continueInsure.do?orderno="+orderno,
       async: false ,
	   success : function(result){
		   if(result != ""){
			   window.location.href = path + result;
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

//展示订单详情
function showOrderDetail(orderno){
	window.location.href= path+"/feicheMyAccount/orderDetails.do?orderno="+orderno;
}

//定时关闭弹出框
function load(outtimes){
	$("#pop").show();
	setTimeout(function(){$('#pop').hide();
	},outtimes);
}

//时间戳格式转换成时间格式
function formatDate(timestamp) { 
	var now = new Date(timestamp); 
	var year = now.getFullYear(); 
	var month = now.getMonth()+1; 
	var day = now.getDate(); 
	if(month<10){ 
        month = "0"+month; 
    } 
    if(day<10){ 
    	day = "0"+day; 
    } 
	var hour = now.getHours(); 
	var minute = now.getMinutes(); 
	var second = now.getSeconds(); 
	return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second; 
} 

