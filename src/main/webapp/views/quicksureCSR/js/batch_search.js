var Last_Page;
var provinceNo=440000;
var cityNo =440300;
var countyNo=440303;
var province;

$(document).ready(function(){
	
	$("#cancel").hide();
	if(agentFlag==2){		
		$("#cancel").show();
		$("#requestInterface").show();
	}else{
		$("#cancel").hide();
		$("#requestInterface").hide();
	}
});
//点击撤销保单号时候显示弹框
$("#cancel").click(function(){
	$("#syapplicationno").val('');//商业险投保单
	$("#jqapplicationno").val('');
	$('.errorhei').show();
})

//点击调用接口显示文本框
$("#requestInterface").click(function(){
	$("#requestxmlT").val("");
	$("#responsexml").val("");
	$("#selectInter").val("0");
	$("#url").val("");	
	$(".requeInterface").show();
})

//点击取消按钮的时候隐藏弹框
$("#ensure1").click(function(){
	$('.errorhei').hide();
	
})
//点击关闭隐藏调用接口框
$("#close").click(function(){
	$('.requeInterface').hide();
	$("#textareTwo").hide();
	
})

//选择调用接口，url地址的改变
$("#selectInter").click(function(){
	if($("#selectInter").val()==1){
		$("#url").val("http://agenttest.sinosafe.com.cn/carservice")
	}else if($("#selectInter").val()==2){
		$("#url").val("http://139.219.229.31:376/DpoService.svc?singleWsdl")
	}else{
		$("#url").val("")
	}
})
//选择改变时候，清空数据
$("#selectInter").change(function(){
	$("#requestxmlT").val("");
	$("#responsexml").val("");
	$("#textareTwo").hide();
})

//点击调用接口
function getresponsexml(){
	$("#prompt").html("正在获取数据，请稍等...........");
	$("#pop").show();
	var selectval=$("#selectInter").val();
	var requestxml=$("#requestxmlT").val();
	$("#responsexml").val("");	
	setTimeout(function(){
		$.ajax({
			type: "post",
			url : getUrl()+"/VelicheBatchCheck/requestxml.do",
			async: false ,
			data: {interfacevalue:selectval,requestxml:requestxml},
			success:function(result){		       
				   $("#pop").hide();
					if(result!=null){					
						$("#responsexml").val(result.interfaceslogsWithBLOBs.responsexml);
						$("#textareTwo").show();
					}
			},
			 error: function(result){
			    	 $("#pop").hide();
			    	 $("#responsexml").val("接口调用失败");
			     	  //Hide();
			    }  
		});
	},5000)
	
}

function load(outtimes){
	$("#pop").show();
	setTimeout(function(){$('#pop').hide()
	},outtimes);
}

//点击确认撤销的时候撤销投保单 
function cancelInsurancePolicy(){
	
	var syapplicationno=$("#syapplicationno").val();//商业险投保单
	var jqapplicationno=$("#jqapplicationno").val();//较强险投保单
   if((syapplicationno!=null&&syapplicationno!=undefined&&syapplicationno!="")||(jqapplicationno!=null&&jqapplicationno!=undefined&&jqapplicationno!="")){
		$('.errorhei').hide();
	   var vhldata={syapplicationno:syapplicationno,jqapplicationno:jqapplicationno};
	   $.ajax({
		    type: "POST", 
			url: getUrl()+'/orderHandle/cancelInsurancePolicy.do',
			data: JSON.stringify(vhldata),//将对象序列化成JSON字符串  
			dataType:"json",  
			contentType : 'application/json;charset=utf-8',		
			success : function(result){				  
				      var listMap = result.listInfo;
				      var html ="";
				      var error1="";
		    		  var error2="";
				      if(listMap.length>2){
				    	  for(var i=0;i<listMap.length;i++){
								if(listMap[0]=="0" || listMap[2] =="0"){
									$('#Message').html("订单取消成功");
									$('.errorhei1').show();
									
								}else if(listMap[0]=="1" || listMap[2] =="1"){
									if(listMap[0]=='0'){
										error1='删单成功';
									}else if(listMap[0]=='1'){
										error1='删单失败';
									}
									if(listMap[2]=='0'){
										error2='删单成功';
									}else if(listMap[2]=='1'){
										error2='删单失败';
									}
									$('#Message').html("<p>"+error1+","+listMap[1]+",<br>"+error2+","+listMap[3]+"</p>");
									$('.errorhei1').show();	
								}
							  }
				      }else{
				    	  for(var i=0;i<listMap.length;i++){
				    		  if(listMap[0]=='0'){
									error1='删单成功';
								}else if(listMap[0]=='1'){
									error1='删单失败';
								}
				    	    $('#Message').html("<p>"+error1+" "+listMap[1]+"</p>");
							$('.errorhei1').show();
				    	  }
				      }
				    
			   }
		});
	}else{		
		$('.errorhei').hide();
		  $('#Message').html("请输入交强险投保单 或者商业险投保单");
			$('.errorhei1').show();
	}
	
}



//状态下拉框值改变时，触发查询
$('#orderstate').change(function(){
	submitForm();
});

//选中一个订单，让其选中,并且管控四个操作按钮
$('#tb_content').on("click","#tb_content>tr",function(){
	var This=$(this);
	if(This.hasClass('green')){
		This.removeClass('green');
		$('.foot_btn').children().eq(2).attr('disabled',true);
		$('.foot_btn').children().eq(3).attr('disabled',true);
		$('.foot_btn').children().eq(4).attr('disabled',true);
		$('.foot_btn').children().eq(5).attr('disabled',true);
	}else{
		This.siblings('.green').removeClass('green');
		This.addClass('green');
	}
	
	var orderstate = This.children().eq(8).text();
	if(orderstate=="暂存"){
		$('.foot_btn').children().eq(2).removeClass('ban');
		$('.foot_btn').children().eq(3).addClass('ban');
		$('.foot_btn').children().eq(4).removeClass('ban');
		$('.foot_btn').children().eq(5).removeClass('ban');
		$('.foot_btn').children().eq(2).attr('disabled',false);
		$('.foot_btn').children().eq(3).attr('disabled',true);
		$('.foot_btn').children().eq(4).attr('disabled',false);
		$('.foot_btn').children().eq(5).attr('disabled',false);
	}else if(orderstate=="待支付"){
		$('.foot_btn').children().eq(2).removeClass('ban');
		$('.foot_btn').children().eq(3).removeClass('ban');
		$('.foot_btn').children().eq(4).addClass('ban');
		$('.foot_btn').children().eq(5).removeClass('ban');
		$('.foot_btn').children().eq(2).attr('disabled',false);
		$('.foot_btn').children().eq(3).attr('disabled',false);
		$('.foot_btn').children().eq(4).attr('disabled',true);
		$('.foot_btn').children().eq(5).attr('disabled',false);
	}else if(orderstate=="已支付"){
		$('.foot_btn').children().eq(2).removeClass('ban');
		$('.foot_btn').children().eq(3).addClass('ban');
		$('.foot_btn').children().eq(4).addClass('ban');
		$('.foot_btn').children().eq(5).addClass('ban');
		$('.foot_btn').children().eq(2).attr('disabled',false);
		$('.foot_btn').children().eq(3).attr('disabled',true);
		$('.foot_btn').children().eq(4).attr('disabled',true);
		$('.foot_btn').children().eq(5).attr('disabled',true);
	}else{
		$('.foot_btn').children().eq(2).addClass('ban');
		$('.foot_btn').children().eq(3).addClass('ban');
		$('.foot_btn').children().eq(4).addClass('ban');
		$('.foot_btn').children().eq(5).addClass('ban');
		$('.foot_btn').children().eq(2).attr('disabled',true);
		$('.foot_btn').children().eq(3).attr('disabled',true);
		$('.foot_btn').children().eq(4).attr('disabled',true);
		$('.foot_btn').children().eq(5).attr('disabled',true);
	}
});

//立即投保
function immediateInsure(){
	window.location.href=getUrl()+"/views/quicksureCSR/jsp/user_mation.jsp";
}

//订单详情
function orderDetails(){
	var orderno = $('.green>td').eq(1).text();
	window.location.href=getUrl()+"/orderHandle/orderDetails.do?orderno="+orderno;
}

//继续支付
function continuePayment(){
	var orderno = $('.green>td').eq(1).text();
	$.ajax({
		url: getUrl()+"/myAccount/continuePay.do?orderno="+orderno,  
		async: false ,
		success: function(path){
			if(path!=null && path!=""){
				window.location.href=path;
			}
		}
	});
}

//继续投保
function continueInsure(){
	var orderno = $('.green>td').eq(1).text();
	window.location.href=getUrl()+"/orderHandle/CSRContinueInsure.do?orderno="+orderno;
}

//撤销
function cancelOrder(){
	var orderno = $('.green>td').eq(1).text();
	$.ajax({
		url: getUrl()+"/orderHandle/cancelOrder.do?orderno="+orderno,  
		async: false ,
		success: function(data){
			if(data=="success"){
				submitForm();
			}
		}
	});
}

//导出excel
function exportExcel(){
	var startTime = $('#datePicker1').val();
	var endTime = $('#datePicker2').val();
	var orderstate = $('#orderstate').val();
	var lcnno = $('#lcnno').val();
	var orderno = $('#orderno').val();
	var drvowner = $('#drvowner').val();
	var deptcode = $('#deptCode').val();
	window.location.href=getUrl()+'/orderHandle/exportExcel.do?startTime='+startTime+'&endTime='+endTime
		+'&orderstate='+orderstate+'&lcnno='+lcnno+'&orderno='+orderno+'&drvowner='+drvowner+'&deptcode='+deptcode;
	/*$.getJSON(getUrl()+'/orderHandle/exportExcel.do',
			{startTime:startTime,endTime:endTime,orderstate:orderstate,lcnno:lcnno,orderno:orderno,drvowner:drvowner,deptcode:deptcode}, 
			function (result) {
		if(result=="success"){
			return true;
		}
	});*/
}

//清除表单
function clearForm(){
	$('#datePicker1').val('');
	$('#datePicker2').val('');
	$('#orderstate').children().eq(0).attr('selected','selected');
	$('#orderno').val('');
	$('#lcnno').val('');
	$('#drvowner').val('');
}

//提交表单,进行查询订单
function submitForm() {		
	$('#xinxi').remove();
	var orderstate = $('#orderstate').val();
	if(orderstate==3||orderstate==0){
		$('#tab1').append('<td id="xinxi"><span>信息提示</span></td>');
	}
	$('#tb_content').html('');
	var startTime = $('#datePicker1').val();
	var endTime = $('#datePicker2').val();
	var orderstate = $('#orderstate').val();
	var lcnno = $('#lcnno').val();
	var orderno = $('#orderno').val();
	var drvowner = $('#drvowner').val();
	var deptcode = $('#deptCode').val();
	$.getJSON(getUrl()+'/VelicheBatchCheck/batchQuery.do',
			{startTime:startTime,endTime:endTime,orderstate:orderstate,lcnno:lcnno,orderno:orderno,drvowner:drvowner,deptcode:deptcode}, 
			function (data) {
		var policyCount = data.policyCount;
		Last_Page = parseInt(policyCount/15)+parseInt(policyCount%15>0?1:0);
		var list = data.InsuranceDetailsVOs;
		/*console.log(list);*/
		for(var i=0;i<list.length;i++){
			addPolicyInfor(list[i],i%2);
		}
		Pager(1);
	});
};

//动态添加数据
function addPolicyInfor(insuranceDetailsVO,flag){
	var tbody = $('#tb_content');
	var trbg;
	var orderstate;
	var status = insuranceDetailsVO.baseinfor.orderstate;
	
	if(status==10 || status==20){
		orderstate = '暂存';
	}else if(status==30 || status==40){
		orderstate = '待支付';
	}else if(status==50 || status==60 || status==70){
		orderstate = '已支付';
	}else if(status==80){
		orderstate = '已撤销';
	}
	if(flag==1){
		trbg = $('<tr class="trbg1"/>');
	}else{
		trbg = $('<tr class="trbg2"/>');
	}
	if(insuranceDetailsVO.vhlinfor.drvowner!=null && insuranceDetailsVO.vhlinfor.drvowner!=""){
		trbg.append($('<td/>').append('<span>'+insuranceDetailsVO.vhlinfor.drvowner+'</span>'));
	}else{
		trbg.append($('<td/>').append('<span></span>'));
	}
	trbg.append($('<td/>').append('<span>'+insuranceDetailsVO.baseinfor.orderno+'</span>'));
	trbg.append($('<td/>').append('<span>'+insuranceDetailsVO.vhlinfor.lcnno+'</span>'));
	if(insuranceDetailsVO.baseinfor.jqapplicationno!=null && insuranceDetailsVO.baseinfor.jqapplicationno!=""){
		trbg.append($('<td/>').append('<span>'+insuranceDetailsVO.baseinfor.jqapplicationno+'</span>'));
	}else{
		trbg.append($('<td/>').append('<span></span>'));
	}
	if(insuranceDetailsVO.baseinfor.jqpolicyno!=null && insuranceDetailsVO.baseinfor.jqpolicyno!=""){
		trbg.append($('<td/>').append('<span>'+insuranceDetailsVO.baseinfor.jqpolicyno+'</span>'));
	}else{
		trbg.append($('<td/>').append('<span></span>'));
	}
	if(insuranceDetailsVO.baseinfor.syapplicationno!=null && insuranceDetailsVO.baseinfor.syapplicationno!=""){
		trbg.append($('<td/>').append('<span>'+insuranceDetailsVO.baseinfor.syapplicationno+'</span>'));
	}else{
		trbg.append($('<td/>').append('<span></span>'));
	}
	if(insuranceDetailsVO.baseinfor.sypolicyno!=null && insuranceDetailsVO.baseinfor.sypolicyno!=""){
		trbg.append($('<td/>').append('<span>'+insuranceDetailsVO.baseinfor.sypolicyno+'</span>'));
	}else{
		trbg.append($('<td/>').append('<span></span>'));
	}
	if(insuranceDetailsVO.baseinfor.totalPremium!=null && insuranceDetailsVO.baseinfor.totalPremium!=""){
		trbg.append($('<td/>').append('&yen;<span>'+insuranceDetailsVO.baseinfor.totalPremium+'</span>'));
	}else{
		trbg.append($('<td/>').append('<span></span>'));
	}
	trbg.append($('<td/>').append('<span>'+orderstate+'</span>'));
	if(insuranceDetailsVO.interfaceslog.resposnetime!=null && insuranceDetailsVO.interfaceslog.resposnetime!=""){
		if(insuranceDetailsVO.interfaceslog.resposnetime.length>19){
			var responsetime=insuranceDetailsVO.interfaceslog.resposnetime.substr(0,19); 
			trbg.append($('<td/>').append('<span>'+responsetime+'</span>'));
		}else{
			trbg.append($('<td/>').append('<span>'+insuranceDetailsVO.interfaceslog.resposnetime+'</span>'));
		}	
	}else{
		trbg.append($('<td/>').append('<span></span>'));
	}
	if(orderstate=="暂存"){
		if(insuranceDetailsVO.interfaceslog.responsemessage!=null && insuranceDetailsVO.interfaceslog.responsemessage!=""){
			trbg.append($('<td/>').append('<span>'+insuranceDetailsVO.interfaceslog.responsemessage+'</span>'));
		}else{
			trbg.append($('<td/>').append('<span></span>'));
		}
	}else if(orderstate=="待支付"||orderstate=="已支付"||orderstate=="已撤销"){
		trbg.append($('<td/>').append('<span></span>'));
	}
	tbody.append(trbg);
	//每次查询展示新的数据就将四个操作按钮禁止
	$('.foot_btn').children().eq(2).addClass('ban');
	$('.foot_btn').children().eq(3).addClass('ban');
	$('.foot_btn').children().eq(4).addClass('ban');
	$('.foot_btn').children().eq(5).addClass('ban');
	$('.foot_btn').children().eq(2).attr('disabled',true);
	$('.foot_btn').children().eq(3).attr('disabled',true);
	$('.foot_btn').children().eq(4).attr('disabled',true);
	$('.foot_btn').children().eq(5).attr('disabled',true);
}

//获取对应页码的数据
function getdata(That_Page){
	$('#tb_content').html('');
	var startTime = $('#datePicker1').val();
	var endTime = $('#datePicker2').val();
	var lcnno = $('#lcnno').val();
	var orderstate = $('#orderstate').val();
	var orderno = $('#orderno').val();
	var drvowner = $('#drvowner').val();
	$.getJSON(getUrl()+'/VelicheBatchCheck/CSRPaging.do',
			{thatPage:That_Page,lastPage:Last_Page,startTime:startTime,endTime:endTime,
				lcnno:lcnno,orderstate:orderstate,orderno:orderno,drvowner:drvowner}, 
			function (data) {
		for(var i=0;i<data.length;i++){
			addPolicyInfor(data[i],i%2);
		}
		Pager(That_Page);
	});
}

//点击分页页码
$('#pager').on('click','#pager>li',function(event){
   var This=$(this),fewPage;
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
       getdata(fewPage);
   }else{
       This.addClass("active").siblings('.active').removeClass('active');
       fewPage=parseFloat(This.text());
       getdata(fewPage);
   }
});
  
//分页栏
function Pager(That_Page){
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
   
     /*console.log(That_Page+1);console.log(typeof(That_Page+1));*/
     if((That_Page+1)<=Last_Page){
    	 pager.append(" <li class='nextPage None_1 next att'><span>下一页</span></li>");
    	 pager.append(" <li class='nextPage None_1 last att' data-lastpage="+Last_Page+"><span>尾页</span></li>");
     }else{
    	 pager.append(" <li class='nextPage'><span>下一页</span></li>");
    	 pager.append(" <li class='nextPage'><span>尾页</span></li>");
     }
     pager.append(" <li class='AllPage'><span>共"+Last_Page+"页</span></li>");
}


//省级改变   
function provinceChange(id, value) {
	provinceNo = value;
    var idArray = id.split("_");
    id = idArray[0];
    $("#" + id + "_City").html("");
    $("#" + id + "_Address").val("");
    if(value==1){
    	$("#" + id + "_City").append("<option value='1' name='请选择' data-name='请选择' selected>--请选择--</option>");
    }else{
    	for (var i = 0; i < province.length; i++) { //市级
    		if (province[i].parentid == value) {
    			m = {
    					n: province[i].pinyin,
    					Value: province[i].deptinforid,
    					name: province[i].name
    			};
    			$("#" + id + "_City").append('<option value=' + m.Value + ' name=' + m.n + '>' + province[i].name + '</option>');
    		}
    	}
    }
    var cityFirstValue = $("#" + id + "_City").children('option:first').val();
    cityChange(id + "_City", cityFirstValue);

}

//市级改变
function cityChange(id, value) {
	cityNo = value;
    var idArray = id.split("_");
    id = idArray[0];
    $("#" + id + "_County").html("");
    $("#" + id + "_Address").val("");
    if(value==1){
    	$("#" + id + "_County").append("<option value='1' name='请选择' data-name='请选择' selected>--请选择--</option>");
    	$("#deptCode").val(1);
    }else{
    	for (var i = 0; i < province.length; i++) { //市级
    		if (province[i].parentid == value) {
    			
    			m = {
    					n: province[i].pinyin,
    					Value: province[i].zipcode,
    					name: province[i].name+"("+province[i].deptinforid+")"
    			};
    			$("#" + id + "_County").append("<option value=" + m.Value + ' name=' + m.n + " data-name=" + province[i].name + ">" + m.name + '</option>');
    			
    		}
    	}
    	var address = $("#" + id + "_Province").children('option:selected').text() + $("#" + id + "_City").children('option:selected').text() + $("#" + id + "_County").children('option:selected').text();
    	var zipcode = $("#" + id + "_County").val();
    	$("#" + id + "_Address").val(address);
    	$("#" + id + "_ZipCode").val(zipcode);
    	if(id=="Travel"){
    		for(var i=0;i<province.length; i++){
    			if (province[i].zipcode == zipcode) {
    				m = {
    						n: province[i].deptinforid,
    				};
    				$("#deptCode").val(m.n);
    			}
    		}
    	}
    }
    

}

//镇级改变
function townChange(id, value) {
	countyNo=value;
    var idArray = id.split("_");
    var zipcode = null;
    id = idArray[0];
    $("#" + id + "_Address").val("");
    for (var i = 0; i < province.length; i++) { //市级
        if (province[i].deptinforid == value) {
           zipcode = province[i].zipcode;
           break;
        }
    }
    var address = $("#" + id + "_Province").children('option:selected').text() + $("#" + id + "_City").children('option:selected').text() + $("#" + id + "_County").children('option:selected').text();
    var zipcode;
    if($("#" + id + "_County").children('option:selected').attr("zipcode")!==undefined){
      zipcode = $("#" + id + "_County").children('option:selected').attr("zipcode");
    }else{
      zipcode = $("#" + id + "_County").val();
    }
     
    $("#" + id + "_Address").val(address);
    $("#" + id + "_ZipCode").val(zipcode);
    if(id=="Travel"){
    	for(var i=0;i<province.length; i++){
        	if (province[i].zipcode == zipcode) {
        		m = {
                        n: province[i].deptinforid,
                    };
        		$("#deptCode").val(m.n);
        	}
        }
    }
}

//初始化时区域显示
$(document).ready(function() {
    $.getJSON(getUrl()+'/views/quicksure/scripts/json/department.json',
    function(data) {
        province = data;
    	var idList = new Array("Travel","Owner","Application","Insured","Delivery"); //地址初始化的影响范围：车主信息、投保人信息、被保人信息、配送信息
    	var provinceList = new Array(5); //初始化信息Province的影响范围：车主省份、投保人省份、被保人省份、配送省份
    	var cityList = new Array(5); //初始化信息City的影响范围：车主城市、投保人城市、被保人城市、配送城市
    	var countyList = new Array(5); //初始化信息County的影响范围：车主County、投保人County、被保人County、配送County
        for(var y = 0; y < idList.length; y++){
        	var id = idList[y];
        	for(var o=0; o < provinceList.length; o++){
	        	if(provinceList[o]=="" || provinceList[o] == null || "undefined"==typeof(provinceList[o])){
//	        		provinceList[o] = 440000;
	        		provinceList[o] = 1;
	        	}
	        	if(cityList[o]=="" || cityList[o]==null || "undefined"==typeof(cityList[o])){
//	        		cityList[o] = 440300;
	        		cityList[o] = 1;
	        	}
	        	if(countyList[o]=="" || countyList[o]==null || "undefined"==typeof(countyList[o])){
//	        		countyList[o] = 440303;
	        		countyList[o] = 1;
	        		$("#deptCode").val(countyList[o]);
	        	}
	        }
        	$("#" + id + "_Province").append("<option value='1' name='请选择' data-name='请选择' selected>--请选择--</option>");
        	$("#" + id + "_City").append("<option value='1' name='请选择' data-name='请选择' selected>--请选择--</option>");
        	$("#" + id + "_County").append("<option value='1' name='请选择' data-name='请选择' selected>--请选择--</option>");
	        for (var i = 0; i < data.length; i++) {
	        	var a,b,c;
	            if (data[i].parentid == '100000') { //省级                
	                a = {
	                    n: data[i].pinyin,
	                    Value: data[i].deptinforid,
	                    name: data[i].name
	                };
	                if(provinceList[y] == data[i].deptinforid) {
	                    $("#" + id + "_Province").append("<option value=" + a.Value + " name=" + a.n + " data-name=" + data[i].name + " selected>" + data[i].name + "</option>");
	                }else{
	                    $("#" + id + "_Province").append("<option value=" + a.Value + ' name=' + a.n + " data-name=" + data[i].name + ">" + data[i].name + '</option>');
	                }
	            } else if (data[i].parentid == provinceList[y]) { //市级
	                b = {
	                    n: data[i].pinyin,
	                    Value: data[i].deptinforid,
	                    name: data[i].name
	                };
	                if (cityList[y] == data[i].deptinforid ) {
	                    $("#" + id + "_City").append("<option value=" + b.Value + ' name=' + b.n + " data-name=" + data[i].name + " selected>" + data[i].name + '</option>');
	                }else{
		                $("#" + id + "_City").append("<option value=" + b.Value + ' name=' + b.n + " data-name=" + data[i].name + ">" + data[i].name + '</option>');
	                }
	            } else if (data[i].parentid == cityList[y]) { //镇级
	                c = {
	                    n: data[i].pinyin,
	                    Value: data[i].deptinforid,
	                    name: data[i].name+"("+data[i].deptinforid+")",
	                    zip: data[i].zipcode
	                };
	                if(countyList[y] == data[i].detinforid){
		                $("#" + id + "_County").append("<option value=" + c.Value +" name=" + c.n + " data-name=" + data[i].name + " zipCode=" + data[i].zipcode + "selected>" + c.name + '</option>');
	                }else{
	                	$("#" + id + "_County").append("<option value=" + c.Value +" name=" + c.n + " data-name=" + data[i].name + " zipCode=" + data[i].zipcode + ">" + c.name + '</option>');
	                }
	            }
	        }
	        var data_name_1 = $("#" + id + "_Province").children(':selected').attr('data-name');
	        var data_name_2 = $("#" + id + "_City").children(':selected').attr('data-name');
	        var data_name_3 = $("#" + id + "_County").children(':selected').attr('data-name');
	        var text_4 = $("#" + id + "_County").children(':first').attr('zipCode');
	        $("#" + id + "_Address").val(data_name_1 + ' ' + data_name_2 + ' ' + data_name_3);
	       	$("#" + id + "_ZipCode").val(text_4);
        }   	
        
    });
});









