var orderCount1,orderCount2,orderCount3,orderCount4;
var Content=$('#search_content').val();
$(document).ready(function(){
    var Index=30,Data,Index_stop,Index_type;
    $('.Order_list li>a').click(function(e){
    	var orderSize;
    	var Content=$('#search_content').val();
        e.preventDefault();
        $(this).addClass('act').siblings().removeClass('act');
        Index=$(this).attr('data-mode');
        Index_stop=$(this).attr('data-stop');
        Index_type=$(this).attr('data-type');
        $('#Order_information').html(" ");
        var count1 = 0,count2 = 0,count3 = 0,count4 = 0;
        for(var i=0;i<Data.length;i++){
        	var orderno="'"+Data[i].baseinfor.orderno+"'";
       		var useId="'"+Data[i].baseinfor.userinforid+"'";
       		//判断检索框是否有值,有值就判断值是否和Data里面的值相同，然后展示出来
       		if(Content!==null && Content!==undefined && Content!==""){
       			if(Content==Data[i].vhlinfor.drvowner){
           			if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==Index_stop)&&30==Index&&count1<10){//待支付
                		var orderstate="";
                		if(Data[i].baseinfor.orderstate==30){
                			orderstate="已下单";
                		}else{
                			orderstate="待支付";
                		}
                		orderSize = parseInt($(".wait_pay").html());
                		count1++;
                		addPolicyInfo1(Data[i],orderstate);
                    }
                    if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==Index_stop||Data[i].baseinfor.orderstate==Index_type)&&50==Index&&count2<10){//已支付
                        var orderstate="";
                		if(Data[i].baseinfor.orderstate==50){
                			orderstate="已支付";
                		}else if(Data[i].baseinfor.orderstate==60){
                			orderstate="已生效";
                		}else{
                			orderstate="已配送";
                		}
                		orderSize = parseInt($(".already_pay").html());
                		count2++;
                		addPolicyInfo1(Data[i],orderstate);
                    }
                    if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==Index_stop)&&10==Index&&count3<10){//暂存
                    	if(Data[i].baseinfor.orderstate==10){
                			orderstate="待定";
                		}else{
                			orderstate="已报价";
                		}
                    	orderSize = parseInt($(".Stop_keep").html());
                    	count3++;
                    	addPolicyInfo1(Data[i],orderstate);
                    }
                    if(Data[i].baseinfor.orderstate==Index&&80==Index&&count4<10){//已关闭
                    	orderSize = parseInt($(".On").html());
                    	var orderstate = "已撤销";
                    	count4++;
                    	addPolicyInfo1(Data[i],orderstate);
                    }
           		}
       		}else{
       			if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==Index_stop)&&30==Index&&count1<10){//待支付
	        		var orderstate="";
	        		if(Data[i].baseinfor.orderstate==30){
	        			orderstate="已下单";
	        		}else{
	        			orderstate="待支付";
	        		}
	        		orderSize = parseInt($(".wait_pay").html());
	        		count1++;
	        		addPolicyInfo(Data[i],orderstate);
	            }
	            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==Index_stop||Data[i].baseinfor.orderstate==Index_type)&&50==Index&&count2<10){//已支付
	                var orderstate="";
	        		if(Data[i].baseinfor.orderstate==50){
	        			orderstate="已支付";
	        		}else if(Data[i].baseinfor.orderstate==60){
	        			orderstate="已生效";
	        		}else{
	        			orderstate="已配送";
	        		}
	        		orderSize = parseInt($(".already_pay").html());
	        		count2++;
	        		addPolicyInfo(Data[i],orderstate);
	            }
	            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==Index_stop)&&10==Index&&count3<10){//暂存
	            	if(Data[i].baseinfor.orderstate==10){
	        			orderstate="待定";
	        		}else{
	        			orderstate="已报价";
	        		}
	            	orderSize = parseInt($(".Stop_keep").html());
	            	count3++;
	            	addPolicyInfo(Data[i],orderstate);
	            }
	            if(Data[i].baseinfor.orderstate==Index&&80==Index&&count4<10){//已关闭
	            	orderSize = parseInt($(".On").html());
	            	var orderstate = "已撤销";
	            	count4++;
	            	addPolicyInfo(Data[i],orderstate);
	            }
       		}
       	  
        }
        var That_Page = 1;
        var Last_Page;
        if(typeof(orderSize)==undefined || orderSize==null || orderSize==""){
        	Last_Page = 1;
        }else{
        	Last_Page = parseInt(orderSize/10)+parseInt(orderSize%10>0?1:0);
        }
        Pager(That_Page,Last_Page);
    });
    var lay = layer.load(0, { shade: [0.5, '#393D49'],time: 20*1000});
    //初始化我的订单页面
    $.getJSON(getUrl()+'/orderNo/myOrderNoInfor.do', function (data) {
        Data=data;
        var countdzf=0;
        var countyzf=0;
		var countzt=0;
		var countygb=0;
		var That_Page = 1;
        
        for(var i=0;i<data.length;i++){
        	if(data[i].baseinfor.orderstate==30||data[i].baseinfor.orderstate==40){//待支付
        		countdzf++;
        	}
        	if(data[i].baseinfor.orderstate==50||data[i].baseinfor.orderstate==60||data[i].baseinfor.orderstate==70){//已支付
        		countyzf++;
        	}
        	if(data[i].baseinfor.orderstate==10||data[i].baseinfor.orderstate==20){//暂存
        		countzt++;
        	}
        	if(data[i].baseinfor.orderstate==80){//已关闭
        		countygb++;
        	}
        }
        orderCount1=countdzf;
        orderCount2=countyzf;
        orderCount3=countzt;
        orderCount4=countygb;
        $(".wait_pay").html(countdzf);
        $(".already_pay").html(countyzf);
        $(".Stop_keep").html(countzt);
        $(".On").html(countygb);
        tabShowRule();
        /*var count1 = 0,count2 = 0,count3 = 0,count4 = 0;
        for(var i=0;i<data.length;i++){
        	var orderno="'"+data[i].baseinfor.orderno+"'";
       	 	var useId="'"+data[i].baseinfor.userinforid+"'";
            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==40)&&30==Index && count1<10){//待支付
            	var orderstate="";
        		if(Data[i].baseinfor.orderstate==30){
        			orderstate="已下单";
        		}else{
        			orderstate="待支付";
        		}
        		count1++;
        		addPolicyInfo(Data[i],orderstate);
            }
        }
        var Last_Page;
        if(typeof(countdzf)==undefined || countdzf==null || countdzf==""){
        	Last_Page = 1;
        }else{
        	Last_Page = parseInt(countdzf/10)+parseInt(countdzf%10>0?1:0);
        }
        Pager(That_Page,Last_Page);*/
        layer.close(lay);
    });
    
    //触发我的订单
    $("#MyOrder_box").click(function(){
    	$('#search_content').val("");
        $(".single_product").remove();
    	
    	var data = Data;
        var countdzf=0;
        var countyzf=0;
		var countzt=0;
		var countygb=0;
		var That_Page = 1;
		var orderCount;
        
        for(var i=0;i<data.length;i++){
        	if(data[i].baseinfor.orderstate==30||data[i].baseinfor.orderstate==40){//待支付
        		countdzf++;
        	}
        	if(data[i].baseinfor.orderstate==50||data[i].baseinfor.orderstate==60||data[i].baseinfor.orderstate==70){//已支付
        		countyzf++;
        	}
        	if(data[i].baseinfor.orderstate==10||data[i].baseinfor.orderstate==20){//暂存
        		countzt++;
        	}
        	if(data[i].baseinfor.orderstate==80){//已关闭
        		countygb++;
        	}
        }
        orderCount1=countdzf;
        orderCount2=countyzf;
        orderCount3=countzt;
        orderCount4=countygb;
        $(".wait_pay").html(countdzf);
        $(".already_pay").html(countyzf);
        $(".Stop_keep").html(countzt);
        $(".On").html(countygb);
        tabShowRule();
        /*var count1 = 0,count2 = 0,count3 = 0,count4 = 0;
        for(var i=0;i<data.length;i++){
        	var orderno="'"+data[i].baseinfor.orderno+"'";
       	 	var useId="'"+data[i].baseinfor.userinforid+"'";
            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==40)&&30==Index && count1<10){//待支付
            	orderCount = orderCount1;
            	var orderstate="";
        		if(Data[i].baseinfor.orderstate==30){
        			orderstate="已下单";
        		}else{
        			orderstate="待支付";
        		}
        		count1++;
        		addPolicyInfo(Data[i],orderstate);
            }
            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==60||Data[i].baseinfor.orderstate==70)&&50==Index&&count2<10){//已支付
            	orderCount = orderCount2;
            	var orderstate="";
	    		if(Data[i].baseinfor.orderstate==50){
	    			orderstate="已支付";
	    		}else if(Data[i].baseinfor.orderstate==60){
	    			orderstate="已生效";
	    		}else{
	    			orderstate="已配送";
	    		}
	    		count2++;
	    		addPolicyInfo(Data[i],orderstate);
            }
	        if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==20)&&10==Index&&count3<10){//暂存
	        	orderCount = orderCount3;
	        	if(Data[i].baseinfor.orderstate==10){
	    			orderstate="待定";
	    		}else{
	    			orderstate="已报价";
	    		}
	        	count3++;
	        	addPolicyInfo(Data[i],orderstate);
	        }
	        if(data[i].baseinfor.orderstate==Index&&80==Index&&count4<10){//已关闭
	        	orderCount = orderCount4;
	        	count4++;
	        	orderstate="已撤销";
	        	addPolicyInfo(Data[i],orderstate);
	        }
        }
        var Last_Page;
        if(typeof(orderCount)==undefined || orderCount==null || orderCount==""){
        	Last_Page = 1;
        }else{
        	Last_Page = parseInt(orderCount/10)+parseInt(orderCount%10>0?1:0);
        }
        Pager(That_Page,Last_Page);*/
    });	
    
    //输入框的值改变时
    $("#search_content").blur(function(){
    	$(".single_product").remove();
    	var Content=$('#search_content').val();
    	var data = Data;
        var countdzf=0;
        var countyzf=0;
		var countzt=0;
		var countygb=0;
		var That_Page = 1;
		var Last_Page = 1;
		var orderCount;
        if(Content!=='' && Content!==null){
        	for(var i=0;i<data.length;i++){
  	          if(Content==data[i].vhlinfor.drvowner){	
  	        	if(data[i].baseinfor.orderstate==30||data[i].baseinfor.orderstate==40){//待支付
  	        		countdzf++;
  	        	}
  	        	if(data[i].baseinfor.orderstate==50||data[i].baseinfor.orderstate==60||data[i].baseinfor.orderstate==70){//已支付
  	        		countyzf++;
  	        	}
  	        	if(data[i].baseinfor.orderstate==10||data[i].baseinfor.orderstate==20){//暂存
  	        		countzt++;
  	        	}
  	        	if(data[i].baseinfor.orderstate==80){//已关闭
  	        		countygb++;
  	        	}
  	          }	
  	        }
  	        orderCount1=countdzf;
  	        orderCount2=countyzf;
  	        orderCount3=countzt;
  	        orderCount4=countygb;
  	        if(orderCount1==0&&orderCount2==0&&orderCount3==0&&orderCount4==0){ 
  	        	$('#Message').html("没有搜到<span style='color:red;font-weight: 600;'> "+Content+"</span> 请确认是否输入正确");
  				$('.errorhei').show();
  	        }
  	        $(".wait_pay").html(countdzf);
  	        $(".already_pay").html(countyzf);
  	        $(".Stop_keep").html(countzt);
  	        $(".On").html(countygb);
  	        tabShowRule();
  	        /*var count1 = 0,count2 = 0,count3 = 0,count4 = 0;
  	        for(var i=0;i<data.length;i++){
  	        	if(Content==data[i].vhlinfor.drvowner){
  		        	var orderno="'"+data[i].baseinfor.orderno+"'";
  		       	 	var useId="'"+data[i].baseinfor.userinforid+"'";
  		            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==40)&&30==Index && count1<10){//待支付
  		            	orderCount = orderCount1;
  		            	var orderstate="";
  		        		if(Data[i].baseinfor.orderstate==30){
  		        			orderstate="已下单";
  		        		}else{
  		        			orderstate="待支付";
  		        		}
  		        		count1++;
  		        		addPolicyInfo1(Data[i],orderstate);
  		            }
  		            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==60||Data[i].baseinfor.orderstate==70)&&50==Index&&count2<10){//已支付
  		            	orderCount = orderCount2;
  		            	var orderstate="";
	  	        		if(Data[i].baseinfor.orderstate==50){
	  	        			orderstate="已支付";
	  	        		}else if(Data[i].baseinfor.orderstate==60){
	  	        			orderstate="已生效";
	  	        		}else{
	  	        			orderstate="已配送";
	  	        		}
	  	        		count2++;
	  	        		addPolicyInfo1(Data[i],orderstate);
	  	            }
	  	            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==20)&&10==Index&&count3<10){//暂存
	  	            	orderCount = orderCount3;
	  	            	if(Data[i].baseinfor.orderstate==10){
	  	        			orderstate="待定";
	  	        		}else{
	  	        			orderstate="已报价";
	  	        		}
	  	            	count3++;
	  	            	addPolicyInfo1(Data[i],orderstate);
	  	            }
	  	            if(data[i].baseinfor.orderstate==Index&&80==Index&&count4<10){//已关闭
	  	            	orderCount = orderCount4;
	  	            	count4++;
	  	            	orderstate="已撤销";
	  	            	addPolicyInfo1(Data[i],orderstate);
	  	            }
  	        	}   
  	        }*/
        }else{
        	$('#MyOrder_box').click();
        	 /*for(var i=0;i<data.length;i++){
 	        	if(data[i].baseinfor.orderstate==30||data[i].baseinfor.orderstate==40){//待支付
 	        		countdzf++;
 	        	}
 	        	if(data[i].baseinfor.orderstate==50||data[i].baseinfor.orderstate==60||data[i].baseinfor.orderstate==70){//已支付
 	        		countyzf++;
 	        	}
 	        	if(data[i].baseinfor.orderstate==10||data[i].baseinfor.orderstate==20){//暂存
 	        		countzt++;
 	        	}
 	        	if(data[i].baseinfor.orderstate==80){//已关闭
 	        		countygb++;
 	        	}
 	        }
            
 	        orderCount1=countdzf;
 	        orderCount2=countyzf;
 	        orderCount3=countzt;
 	        orderCount4=countygb;
 	        
 	        $(".wait_pay").html(countdzf);
 	        $(".already_pay").html(countyzf);
 	        $(".Stop_keep").html(countzt);
 	        $(".On").html(countygb);
 	        tabShowRule();
 	        var count1 = 0,count2 = 0,count3 = 0,count4 = 0;
 	        for(var i=0;i<data.length;i++){
 	        	var orderno="'"+data[i].baseinfor.orderno+"'";
 	       	 	var useId="'"+data[i].baseinfor.userinforid+"'";
 	            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==40)&&30==Index && count1<10){//待支付
 	            	orderCount = orderCount1;
 	            	var orderstate="";
 	        		if(Data[i].baseinfor.orderstate==30){
 	        			orderstate="已下单";
 	        		}else{
 	        			orderstate="待支付";
 	        		}
 	        		count1++;
 	        		addPolicyInfo1(Data[i],orderstate);
 	            }
 	            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==60||Data[i].baseinfor.orderstate==70)&&50==Index&&count2<10){//已支付
 	            	orderCount = orderCount2;
 	            	var orderstate="";
	 	    		if(Data[i].baseinfor.orderstate==50){
	 	    			orderstate="已支付";
	 	    		}else if(Data[i].baseinfor.orderstate==60){
	 	    			orderstate="已生效";
	 	    		}else{
	 	    			orderstate="已配送";
	 	    		}
	 	    		count2++;
	 	    		addPolicyInfo1(Data[i],orderstate);
 	            }
	 	        if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==20)&&10==Index&&count3<10){//暂存
	 	        	orderCount = orderCount3;
	 	        	if(Data[i].baseinfor.orderstate==10){
	 	    			orderstate="待定";
	 	    		}else{
	 	    			orderstate="已报价";
	 	    		}
	 	        	count3++;
	 	        	addPolicyInfo1(Data[i],orderstate);
	 	        }
	 	        if(data[i].baseinfor.orderstate==Index&&80==Index&&count4<10){//已关闭
	 	        	orderCount = orderCount4;
	 	        	count4++;
	 	        	orderstate="已撤销";
	 	        	addPolicyInfo1(Data[i],orderstate);
	 	        }
 	        }*/
        }   
        /*if(typeof(orderCount)==undefined || orderCount==null || orderCount==""){
        	Last_Page = 1;
        }else{
        	Last_Page = parseInt(orderCount/10)+parseInt(orderCount%10>0?1:0);
        }
        Pager(That_Page,Last_Page);*/
    }); 
    
    //触发检索框
    $('.search').click(function(){
    	Content=$('#search_content').val();
		if(Content==""){
			$(".single_product").remove();
			$('#Message').html("请输入内容您要搜索的关键字！");
			$('.errorhei').show();
			tabShowRule();
		}
		/*else{
			var data = Data;
	        var countdzf=0;
	        var countyzf=0;
			var countzt=0;
			var countygb=0;
			var That_Page = 1;
	        
	        for(var i=0;i<data.length;i++){
	          if(Content==data[i].vhlinfor.drvowner){	
	        	if(data[i].baseinfor.orderstate==30||data[i].baseinfor.orderstate==40){//待支付
	        		countdzf++;
	        	}
	        	if(data[i].baseinfor.orderstate==50||data[i].baseinfor.orderstate==60||data[i].baseinfor.orderstate==70){//已支付
	        		countyzf++;
	        	}
	        	if(data[i].baseinfor.orderstate==10||data[i].baseinfor.orderstate==20){//暂存
	        		countzt++;
	        	}
	        	if(data[i].baseinfor.orderstate==80){//已关闭
	        		countygb++;
	        	}
	          }	
	        }
	        orderCount1=countdzf;
	        orderCount2=countyzf;
	        orderCount3=countzt;
	        orderCount4=countygb;
            if(orderCount1==0&&orderCount2==0&&orderCount3==0&&orderCount4==0){ 
  	        	$('#Message').html("没有搜到<span style='color:red;font-weight: 600;'> "+Content+"</span> 请确认是否输入正确");
  				$('.errorhei').show();
  	        }
	        $(".wait_pay").html(countdzf);
	        $(".already_pay").html(countyzf);
	        $(".Stop_keep").html(countzt);
	        $(".On").html(countygb);
	        tabShowRule();
	        var orderSize;
	        var count1 = 0,count2 = 0,count3 = 0,count4 = 0;
	        for(var i=0;i<data.length;i++){
	        	if(Content==data[i].vhlinfor.drvowner){
		        	var orderno="'"+data[i].baseinfor.orderno+"'";
		       	 	var useId="'"+data[i].baseinfor.userinforid+"'";
		            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==40)&&Index==30 && count1<10){//待支付
		            	var orderstate="";
		        		if(Data[i].baseinfor.orderstate==30){
		        			orderstate="已下单";
		        		}else{
		        			orderstate="待支付";
		        		}
		        		orderSize = parseInt($(".wait_pay").html());
		        		count1++;
		        		addPolicyInfo1(Data[i],orderstate);
		            }
			        if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==60||Data[i].baseinfor.orderstate==70)&&50==Index&&count2<10){//已支付
		            	var orderstate="";
		        		if(Data[i].baseinfor.orderstate==50){
		        			orderstate="已支付";
		        		}else if(Data[i].baseinfor.orderstate==60){
		        			orderstate="已生效";
		        		}else{
		        			orderstate="已配送";
		        		}
		        		orderSize = parseInt($(".already_pay").html());
		        		count2++;
		        		addPolicyInfo1(Data[i],orderstate);
		            }
		            if((Data[i].baseinfor.orderstate==Index||Data[i].baseinfor.orderstate==20)&&10==Index&&count3<10){//暂存
		            	if(Data[i].baseinfor.orderstate==10){
		        			orderstate="待定";
		        		}else{
		        			orderstate="已报价";
		        		}
		            	orderSize = parseInt($(".Stop_keep").html());
		            	count3++;
		            	addPolicyInfo1(Data[i],orderstate);
		            }
		            if(data[i].baseinfor.orderstate==Index&&80==Index&&count4<10){//已关闭
		            	orderSize = parseInt($(".On").html());
		            	count4++;
		            	orderstate="已撤销";
		            	addPolicyInfo1(Data[i],orderstate);
		            }
		            
	        	}   
	        }
	        var Last_Page;
	        if(typeof(orderSize)==undefined || orderSize==null || orderSize==""){
	        	Last_Page = 1;
	        }else{
	        	Last_Page = parseInt(orderSize/10)+parseInt(orderSize%10>0?1:0);
	        }
	        Pager(That_Page,Last_Page);
	 }*/ 
   }); 
    
});
$("#MyOrderAll").click(function(){
	  $.getJSON(getUrl()+'/orderNo/allorder.do');
	
})
	
//继续支付
function continuePay(orderno,userinforid){//继续支付
	$.ajax({
	   url : getUrl()+"/orderNo/continuePayment.do",
       async: false ,
	   data: {orderno:orderno,userinforid:userinforid},
	   success : function(result){
		   if(result==null||result==""){
			   $('#Message').html("支付异常！");
				$('.errorhei').show();
		   }else{
			   window.location.href=result;
		   }
	   }
	});
}

//取消订单
function cancelOrder(orderno,userid){//取消订单
//	   var vhldata={lcnno:lcnNo,vinno:vinNo,registerdate:registerdate,baseinforOrdeo:orderNo};
   if(window.confirm('你确定要取消订单?')){
	    var canceldata={orderno:orderno,userinforid:userid};
		$.ajax({
		   type : "POST",
		   url : getUrl()+"/orderNo/cancelOrderNo.do",
	       async: false ,
//			   data: {orderno:orderno,userinforid:userid},
	       data:JSON.stringify(canceldata),
		   contentType : 'application/json;charset=utf-8', //设置请求头信息  
		   success : function(result){
				if(result==""){
					$('#Message').html("取消成功！");
					$('.errorhei').show();
					$('.errortan3').click(function(){
					    $('.errorhei').hide();
					    window.location.href=getUrl()+"/views/quicksure/jsp/personOrderCenter.jsp";
					});
				}else{
					$('#Message').html("取消失败！");
					$('.errorhei').show();
				}
		   }
		});
        return true;
    }
}
   
//展示订单详情
function showOrderDetail(orderno,userinforid){
	$.ajax({
	   url : getUrl()+"/orderNo/getOrderDetail.do",
       async: false ,
	   data: {orderno:orderno,userinforid:userinforid},
	   success : function(result){
		   		window.location.href=getUrl()+"/views/quicksure/jsp/orderDetails.jsp";
	   }
	});
}

//继续投保
function continueInsure(orderno,userinforid){
   $.ajax({
	   url : getUrl()+"/orderNo/continueInsure.do",
       async: false ,
	   data: {orderno:orderno,userinforid:userinforid},
	   success : function(result){
		   		window.location.href=getUrl()+result;
	   }
   });
}

//点击分页页码，请求后台返回对应页码的数据
function getdata(fewPage,flag,drvowner){
   $.getJSON(getUrl()+'/orderNo/pageOrder.do?curPage='+fewPage+'&flag='+flag+'&drvowner'+drvowner, function (Data) {
	   var That_Page = fewPage;
	   var orderCount;
	   $('#Order_information').html("");
	   for(var i=0;i<Data.length;i++){
		   var orderno="'"+Data[i].baseinfor.orderno+"'";
	  	   var useId="'"+Data[i].baseinfor.userinforid+"'";
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
	       	   }else{
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
   var This=$(this),fewPage,flag;
   if(This.hasClass("active")){
       return true;
   }else if(This.children().length!=0){
	   if(This.hasClass("None_1")||This.hasClass("None")){
		   if(This.hasClass("prev")){
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
       flag=$('.Order_list li').children('.act').index();
       getdata(fewPage,flag,Content);
   }else{
       This.addClass("active").siblings('.active').removeClass('active');
       fewPage=parseFloat(This.text());
       flag=$('.Order_list li').children('.act').index();
       getdata(fewPage,flag,Content);
   }
});
  
//分页栏
function Pager(That_Page,Last_Page){
	 /*console.log(Last_Page);
	 console.log(typeof That_Page);*/
	 var pager=$('#pager');
	 pager.html("");
	 /*console.log(That_Page);*/
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
	mm_div.append('<a href="#"><img src="'+urlpath+'/views/quicksure/images/'+ insuranceDetailsVO.vhlinfor.codeName+'.jpg" style="width:110px;height:98px;"/>');
	var sp_ul = $('<ul class="Car_mation"/>').append('<li class="main_search">车牌号: <span class="Car_number">'+insuranceDetailsVO.vhlinfor.lcnno+'</span>&nbsp;&nbsp;车主:<span class="user_Name">'+insuranceDetailsVO.vhlinfor.drvowner+'</span></li>');
	if(insuranceDetailsVO.baseinfor.sypolicystartdate != null){
		sp_ul.append($('<li>商业险起期:<span class="begin_date">'+insuranceDetailsVO.baseinfor.sypolicystartdate.slice(0,11)+'</span></li>'));
	}
	if(insuranceDetailsVO.baseinfor.sypolicyenddate != null){
		sp_ul.append($('<li>商业险止期:<span class="end_date">'+insuranceDetailsVO.baseinfor.sypolicyenddate.slice(0,11)+'</span></li>'));
	}
	if(insuranceDetailsVO.baseinfor.jqpolicystartdate != null){
		sp_ul.append($('<li>交强险起期:<span class="begin_date">'+insuranceDetailsVO.baseinfor.jqpolicystartdate.slice(0,11)+'</span></li>'));
	}
	if(insuranceDetailsVO.baseinfor.jqpolicyenddate != null){
		sp_ul.append($('<li>交强险止期:<span class="end_date">'+insuranceDetailsVO.baseinfor.jqpolicyenddate.slice(0,11)+'</span></li>'));
	}
	mm_div.append(sp_ul).append('<b>〉</b>');
	sp_div.append(mm_div);
	var p = $('<p/>');
	if(insuranceDetailsVO.baseinfor.orderstate==30||insuranceDetailsVO.baseinfor.orderstate==40){
		p.append('<a href="#" onclick="continuePay('+orderno+','+useId+');">继续支付</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelOrder('+orderno+','+useId+');">取消订单</a>&nbsp;&nbsp;&nbsp;&nbsp;');
	}
	if(insuranceDetailsVO.baseinfor.orderstate==10||insuranceDetailsVO.baseinfor.orderstate==20){
		p.append('<a href="#" onclick="continueInsure('+orderno+','+useId+');">继续投保</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelOrder('+orderno+','+useId+');">取消订单</a>&nbsp;&nbsp;&nbsp;&nbsp;');
	}
	p.append('金额: <span class="money">'+insuranceDetailsVO.baseinfor.totalPremium+'</span>');
	sp_div.append(p);
	order_info.append(sp_div);
}
 //检索框有值时订单详情动态加载
function addPolicyInfo1(insuranceDetailsVO,orderstate){
	var orderno="'"+insuranceDetailsVO.baseinfor.orderno+"'";
	var useId="'"+insuranceDetailsVO.baseinfor.userinforid+"'";
	var order_info = $('#Order_information');
	var sp_div = $('<div class="single_product"/>');
	sp_div.append($('<ul class="Top_mation"/>').append('<li class="left">订单编号: <span class="indent_number">'+insuranceDetailsVO.baseinfor.orderno+'</span></li class="right"><li class="right">状态: <span class="mode">'+orderstate+'</span></li>'));
	var mm_div = $('<div class="Middle_mation" onclick="showOrderDetail('+orderno+','+useId+');"/>');
	mm_div.append('<a href="#"><img src="'+urlpath+'/views/quicksure/images/'+ insuranceDetailsVO.vhlinfor.codeName+'.jpg" style="width:110px;height:98px;"/>');
	var sp_ul=$('<ul class="Car_mation"/>').append('<li class="main_search">车牌号: <span class="Car_number">'+insuranceDetailsVO.vhlinfor.lcnno+'</span>&nbsp;&nbsp;车主:<span class="user_Name">'+insuranceDetailsVO.vhlinfor.drvowner+'</span></li>');
	if(insuranceDetailsVO.baseinfor.sypolicystartdate != null){
		sp_ul.append($('<li>商业险起期:<span class="begin_date">'+insuranceDetailsVO.baseinfor.sypolicystartdate.slice(0,11)+'</span></li>'));
	}
	if(insuranceDetailsVO.baseinfor.sypolicyenddate != null){
		sp_ul.append($('<li>商业险止期:<span class="end_date">'+insuranceDetailsVO.baseinfor.sypolicyenddate.slice(0,11)+'</span></li>'));
	}
	if(insuranceDetailsVO.baseinfor.jqpolicystartdate != null){
		sp_ul.append($('<li>交强险起期:<span class="begin_date">'+insuranceDetailsVO.baseinfor.jqpolicystartdate.slice(0,11)+'</span></li>'));
	}
	if(insuranceDetailsVO.baseinfor.jqpolicyenddate != null){
		sp_ul.append($('<li>交强险止期:<span class="end_date">'+insuranceDetailsVO.baseinfor.jqpolicyenddate.slice(0,11)+'</span></li>'));
	}
	mm_div.append(sp_ul).append('<b>〉</b>');
	sp_div.append(mm_div);
	var p = $('<p/>');
	if(insuranceDetailsVO.baseinfor.orderstate==30||insuranceDetailsVO.baseinfor.orderstate==40){
		p.append('<a href="#" onclick="continuePay('+orderno+','+useId+');">继续支付</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelOrder('+orderno+','+useId+');">取消订单</a>&nbsp;&nbsp;&nbsp;&nbsp;');
	}
	if(insuranceDetailsVO.baseinfor.orderstate==10||insuranceDetailsVO.baseinfor.orderstate==20){
		p.append('<a href="#" onclick="continueInsure('+orderno+','+useId+');">继续投保</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelOrder('+orderno+','+useId+');">取消订单</a>&nbsp;&nbsp;&nbsp;&nbsp;');
	}
	p.append('金额: <span class="money">'+insuranceDetailsVO.baseinfor.totalPremium+'</span>');
	sp_div.append(p);
	order_info.append(sp_div);
}
$('.errortan3').click(function(){
    $('.errorhei').hide();

})

//我的订单tab展示规则
function tabShowRule(){
	var This = $('.Order_list li').children('.act');
	var index = This.index();
	console.log("这个tab的index为："+index);
	var num = This.children().text();
	console.log("num为："+num);
	if(num==0){
		if($('.wait_pay').text()!=0 && index!=0){
			$('.Order_list li').children().eq(0).click();
		}else if($('.already_pay').text()!=0 && index!=1){
			$('.Order_list li').children().eq(1).click();
		}else if($('.Stop_keep').text()!=0 && index!=2){
			$('.Order_list li').children().eq(2).click();
		}else if($('.On').text()!=0 && index!=3){
			$('.Order_list li').children().eq(3).click();
		}
	}else{
		if(index==0){
			$('.Order_list li').children().eq(0).click();
		}else if(index==1){
			$('.Order_list li').children().eq(1).click();
		}else if(index==2){
			$('.Order_list li').children().eq(2).click();
		}else if(index==3){
			$('.Order_list li').children().eq(3).click();
		}
	}
}
