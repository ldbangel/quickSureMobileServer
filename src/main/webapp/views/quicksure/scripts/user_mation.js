
	$('.check').click(function(){
        var right_iput=$(this).parent().siblings().eq(2).children(':checkbox');
        if($(this).attr('checked')==null){

            if(right_iput.attr('checked')!==null){

                right_iput.removeAttr('checked');
            }
        }else if(right_iput.attr('checked')==null){

            right_iput.attr('checked','checked');
        }

    });

    $('.check_All').click(function(){
        var All_iput=$(this).parent().parent().siblings().children().children(':checkbox');
        if($(this).attr('checked')==null){
            All_iput.removeAttr('checked');
        }else{
            All_iput.attr('checked','checked');
        }
    });

    $(function(){
        $('#transferdate').date_input();
        $('#registerdate').date_input();
        $('#certificatedate').date_input();
        $('#sypolicystartdate').date_input();
        $('#jqpolicystartdate').date_input();
        var arr=[];//点击复制class="Copy"
        $('.Copy').on('click',function(){
            if($(this).attr('checked')!=null){
               $('.car_user_content td input:text').each(function(i){
                   arr[i]=$(this).val();
               });

                var Class_input="."+$(this).parent().parent().attr("class")+" input:text";
                $(Class_input).each(function(i){
                    $(this).val(arr[i]);
                });
            }


        });
        $('#Compute').click(function(){

            var elem_Input=$('.Must_fill');

            for(var i=0;i<elem_Input.length;i++){
                if(elem_Input.eq(i).val()==""){
                    elem_Input.eq(i).addClass('Missing');

                    var moved= 0,move= 0,step= 0, interval=10,elem_selTop= 0,timer=null;
                    var scroll=$(document).scrollTop(), screenHeight=$(window).innerHeight();
                    function scrollTo(){
                        elem_selTop=elem_Input.eq(i).offset().top;
                        move=(scroll+screenHeight/2)-elem_selTop;
                        step=move/100;
                        timer=setInterval(function timer(){scrollStep()},1);
                        function scrollStep(){
                            scroll=scroll-(step);
                            $(document).scrollTop(scroll);
                            moved++;
                            if(moved==100){
                                clearInterval(timer);
                                timer=null;
                                moved=0;
                            }
                        }
                    }
                    scrollTo();
                    break;
                }
            }
        });

        $('.toggle_action').click(function(){//隐藏
            if($(this).attr('checked')!=null){
                $('#User_mation>div').addClass('None');
            }else($('#User_mation>div').removeClass('None'));
        });
    });

    //定位
     $('.pf_left span').click(function(){
         var Position="."+$(this).attr('class')+"_p";
         var moved= 0,move= 0,step= 0, interval=10,elem_selTop= 0,timer=null;
         var scroll=$(document).scrollTop(), screenHeight=$(window).innerHeight();
         function scrollTo(){
             elem_selTop=$(Position).offset().top;
             move=(scroll+screenHeight/2)-elem_selTop;
             step=move/100;
             timer=setInterval(function timer(){scrollStep()},10);
             function scrollStep(){
                 scroll=scroll-(step);
              $(document).scrollTop(scroll);
                 moved++;
                 if(moved==100){
                     clearInterval(timer);
                    timer=null;
                    moved=0;
                 }
             }
         }
         scrollTo();
     });
     
function getCoverage(){
  var coverageList = new Array();
  
  //车损
  if($("#modCode").prop("checked")){
	  var modsuminsured=1;//车损保额
	  var moddeductibleflag;
  if($("#modAbatement").prop("checked")){
	  moddeductibleflag=1;
  }else{
	  moddeductibleflag=0;
  }
	  coverageList.push({insrnccode:"101",suminsured:modsuminsured,deductibleflag:moddeductibleflag});
  }else{
    $("#modQuota").val("");
	$("#modQuotaPremium").val("");
	$("#modductiblePremium").val("");
  }
  //三者
  if($("#vtplCode").prop("checked")){
	 var vtpldeductibleflag;//不计免赔
	 var vtplsuminsured=$("#vtplQuota").val();;//保额
  
  if($("#vtplAbatement").prop("checked")){
	 vtpldeductibleflag=1;
  }else{
	 vtpldeductibleflag=0;
  }
	  coverageList.push({insrnccode:"102",suminsured:vtplsuminsured,deductibleflag:vtpldeductibleflag});
  }else{
    $("#vtplPremium").val("");
    $("#vtplductiblePremium").val("");
  }
  //盗抢
  if($("#theftCode").prop("checked")){
	 var TheftDeductibleflag;
	 var TheftSuminsured=1;//保额
 if($("#theftAbatement").prop("checked")){
	 TheftDeductibleflag=1;
  }else{
	  TheftDeductibleflag=0;
  }
 	coverageList.push({insrnccode:"103",suminsured:TheftSuminsured,deductibleflag:TheftDeductibleflag});
  }else{
    $("#theftQuota").val("");
	$("#theftPremium").val("");
	$("#theftductiblePremium").val("");
  }
  //司机责任
 if($("#DriversCode").prop("checked")){
	  var Driversdeductibleflag;//不计免赔
	  var Driverssuminsured=$("#DriversQuota").val();;//保额
  if($("#DriversAbatement").prop("checked")){
	  Driversdeductibleflag=1;
  }else{
	  Driversdeductibleflag=0;
  }
     coverageList.push({insrnccode:"104",suminsured:Driverssuminsured,deductibleflag:Driversdeductibleflag});
  }else{
    $("#DriversPremium").val("");
    $("#DriversductiblePremium").val("");
  } 
  //乘客责任
 if($("#PassengerCode").prop("checked")){
	 var Passengerdeductibleflag;//不计免赔
	 var Passengersuminsured=$("#PassengerQuota").val();;//保额
  if($("#PassengerAbatement").prop("checked")){
	 Passengerdeductibleflag=1;
  }else{
	 Passengerdeductibleflag=0;
  }
  	 coverageList.push({insrnccode:"105",suminsured:Passengersuminsured,deductibleflag:Passengerdeductibleflag});
  }else{
     $("#PassengerPremium").val("");
     $("#PassengerductiblePremium").val("");
  }
  //自燃
  if($("#CombustionCode").prop("checked")){
	 var CombustionDeductibleflag;
	 var CombustionSuminsured=1;//保额
  if($("#CombustionAbatement").prop("checked")){
	 CombustionDeductibleflag=1;
  }else{
	 CombustionDeductibleflag=0;
  }
 	coverageList.push({insrnccode:"108",suminsured:CombustionSuminsured,deductibleflag:CombustionDeductibleflag});
  }else{
    $("#CombustionQuota").val("");
	$("#CombustionPremium").val("");
	$("#CombustionductiblePremium").val("");
  }
  //玻璃
  if($("#GlassCode").prop("checked")){
	 var GlassDeductibleflag;
	 var Glasssuminsured=$("#GlassQuota").val();;//保额
	 coverageList.push({insrnccode:"107",suminsured:Glasssuminsured,deductibleflag:GlassDeductibleflag});
  }else{
     $("#GlassQuotaPremium").val("");
  }
  //第三方
  if($("#ThirdPartyCode").prop("checked")){
	 var ThirdPartyDeductibleflag=null;
	 var ThirdPartySuminsured=1;//保额
	 coverageList.push({insrnccode:"115",suminsured:ThirdPartySuminsured,deductibleflag:ThirdPartyDeductibleflag});
  }else{
	 $("#ThirdPartyQuota").val("");
	 $("#ThirdPartyPremium").val("");
  }
  //专车
  if($("#CarCode").prop("checked")){
	 var CarDeductibleflag=null;
	 var CarSuminsured=1;//保额
	 coverageList.push({insrnccode:"116",suminsured:CarSuminsured,deductibleflag:CarDeductibleflag});
  }else{
    $("#CarQuota").val("");
    $("#CarPartyPremium").val("");
  }
  //交强
  if($("#CTPLCode").prop("checked")){
	 var TCPLdeductibleflag=null;
	 var TCPLsuminsured=1;//保额
	 coverageList.push({insrnccode:"357",suminsured:TCPLsuminsured,deductibleflag:TCPLdeductibleflag});
  }else{
    $("#CTPLQuota").val("");
	$("#CTPLPremium").val("");
	$("#currenttax").val("");
	$("#appDate_1").val("");
  }
   //起保时间
   var syStartDate=$("#sypolicystartdate").val();
   var jqStartDate=$("#jqpolicystartdate").val();
   //订单号
//   var orderNo = document.getElementById("orderNo").value;
   if(syStartDate!=null || jqStartDate!=null ){
     coverageList.push({sypolicystartdate:syStartDate,jqpolicystartdate:jqStartDate});
   }  
  
  return coverageList;
  };  
  
  function getVhlinfor(){
	  var vinno=$("#vinno").val();
	  var brandName=$("#brandName").val();
	  var brandcode=$("#brandcode").val();
	  var lcnno=$("#lcnno").val();
	  var engno=$("#engno").val();
	  var certificatedate=$("#certificatedate").val();
	  var displacement=$("#displacement").val();
	  var setno=$("#setno").val();
	  var registerdate=$("#registerdate").val();
	  
	  var chgownerflag= $('input:radio[name="chgownerflag"]:checked').val();
	  if(chgownerflag=="2"){
		  $("#transferdate").attr("readOnly",false);//不可编辑，可以传值
		  $("#transferdate").attr("disabled",false);//不可编辑，不可以传值
	  }
	  var vhlinfor=
	  {
		  "vinno":vinno,
	  	  "brandName":brandName,
	  	  "brandcode":brandcode,
	  	  "lcnno":lcnno,
	  	  "engno":engno,
	  	  "certificatedate":certificatedate,
	  	  "displacement":displacement,
	  	  "brandcode":brandcode,
	  	  "setno":setno,
	  	  "registerdate":registerdate,
	  	  "chgownerflag":chgownerflag
	  };
	  return vhlinfor;
  }
  
  function getInsuranceperinfor(){
	  var ownersname=$("#ownersname").val();
	  var ownersphoneno=$("#ownersphoneno").val();
	  var ownerscerticode=$("#ownerscerticode").val();
	  var ownersaddress=$("#ownersaddress").val();
	  
	  var applicationname=$("#applicationname").val();
	  var applicationphoneno=$("#applicationphoneno").val();
	  var applicationcerticode=$("#applicationcerticode").val();
	  var applicationaddress=$("#applicationaddress").val();
	  
	  var insurename=$("#insurename").val();
	  var insurephoneno=$("#insurephoneno").val();
	  var insurecerticode=$("#insurecerticode").val();
	  var insureaddress=$("#insureaddress").val();
	  
	  var deliveryname=$("#deliveryname").val();
	  var deliveryphone=$("#deliveryphone").val();
	  var deliverycerticode=$("#deliverycerticode").val();
	  var deliveryaddress=$("#deliveryaddress").val();
	  
	  var insuranceperinfor=
	  {
			  "ownersname":ownersname,
		  	  "ownersphoneno":ownersphoneno,
		  	  "brandcode":ownerscerticode,
		  	  "ownersaddress":ownersaddress,
		  	  "applicationname":applicationname,
		  	  "applicationphoneno":applicationphoneno,
		  	  "applicationcerticode":applicationcerticode,
		  	  "applicationaddress":applicationaddress,
		  	  "insurename":insurename,
		  	  "insurephoneno":insurephoneno,
		  	  "insurecerticode":insurecerticode,
		  	  "insureaddress":insureaddress,
		  	  "deliveryname":deliveryname,
		  	  "deliveryphone":deliveryphone,
		  	  "deliverycerticode":deliverycerticode,
		  	  "deliveryaddress":deliveryaddress
	  };
	  return insuranceperinfor;
  }
  
  function getQuote(){
	//险种规则搭配
	//不能单车损和选择了附加险不选择车损
	if($("#CombustionCode").prop("checked")||$("#GlassCode").prop("checked")||$("#CarCode").prop("checked")||$("#ThirdPartyCode").prop("checked")){
		if($("#modCode").prop("checked")){
			if($("#vtplCode").prop("checked")||$("#theftCode").prop("checked")||$("#DriversCode").prop("checked")||$("#PassengerCode").prop("checked")){
					
			}else{
				alert("不能单承保车损和附加险");
				return false;
			}
		}else if($("#PassengerCode").prop("checked")){
			if($("#DriversCode").prop("checked")){
				
			}else{
				alert("承保车损了才能承保附加险+承保司机责任险才能承保乘客责任险");
				return false;
			}
			
		}else{
			alert("承保车损了才能承保附加险");
			return false;
		}		
	}
	//不能单车损
	if($("#modCode").prop("checked")){
		if($("#vtplCode").prop("checked")||$("#theftCode").prop("checked")||$("#DriversCode").prop("checked")||$("#PassengerCode").prop("checked")){
				
		}else{
			alert("不能单独投保车损险");
			return false;
		}
	}
	//必须选择了司机责任才能选择乘客
	if($("#PassengerCode").prop("checked")){
		if($("#DriversCode").prop("checked")){
			
		}else{
			alert("承保司机责任险才能承保乘客责任险");
			return false;
		}
	}
	//目前不能承保无法找到第三方损失险和专车修理厂险
	if($("#ThirdPartyCode").prop("checked")||$("#CarCode").prop("checked")){
		alert("很抱歉！目前不能投保'无法找到第三方损失险'和'专车修理厂险'险别");
		return false;
	}
  
  var vhlinfor=getVhlinfor(); 	
  var coveragelist=getCoverage();
  var insuranceperinfor=getInsuranceperinfor();
  
  var result = new Array();
  result.push({vhlinfor:vhlinfor});
  result.push({coverageinfors:coveragelist});
  result.push({insuranceperinfor:insuranceperinfor});
  $.ajax({  
    type: "POST",  
    url: getUrl()+"/userMation/userPreCount.do",  
    data:JSON.stringify(result),
    dataType:"json",  
    contentType : 'application/json;charset=utf-8', //设置请求头信息  
    beforeSend: function () {
    	this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
    },
    success: function(data){
    var sypremium;
    var jqpremium;
    var currenttax;
    var totalPremium;
    var coverageinfors=data.coverageinfors;
    layer.close(this.layerIndex);
   // 如果返回错误给予友好提示框
   var errorcode ="E00000030";
   var errorcode2 = "C99999999";
   if(data.userinfor.errorCode==errorcode || data.userinfor.errorCode==errorcode2){
/*        alert(data.userinfor.errorMessage); */
       var errorMessage =data.userinfor.errorMessage;
       $("#Message").html(errorMessage);
       $('.max').show();
   }else{
	   $('.max').hide();
   }
   
   
   if(data.baseinfor!=null){
     if(data.baseinfor.sypremium!=null){
      sypremium=data.baseinfor.sypremium;
      $("#sypremium").val(sypremium);
     }else{
       $("#sypremium").val(0.00);
     }
     
      if(data.baseinfor.jqpremium!=null){
      jqpremium=data.baseinfor.jqpremium;
      $("#jqpremium").val(jqpremium);
     }else{
       $("#jqpremium").val(0.00);
     }
     if(data.baseinfor.totalPremium!=null){
      totalPremium = data.baseinfor.totalPremium;
      $("#totalPremium").val(totalPremium);
     }else{
        $("#totalPremium").val(0.00);
     }
     
     if(data.baseinfor.jqpolicystartdate!=null){
       $("#appDate_1").val(data.baseinfor.jqpolicystartdate); 
     }
     if(data.baseinfor.sypolicystartdate!=null){
       $("#appDate").val(data.baseinfor.sypolicystartdate); 
     }
   }
   if(data.taxinfor!=null){
	    if(data.taxinfor.sumuptax!=null){
	     currenttax=data.taxinfor.sumuptax;
	     $("#currenttax").val(data.taxinfor.sumuptax);  
	   }else{	
	     $("#currenttax").val(0.00); 
	   }
   }
   if(coverageinfors!=null){
   for(var i=0;i<coverageinfors.length;i++){
    var coveragr=coverageinfors[i];
    if(coveragr.insrnccode==="0357"){
     if(coveragr.premium!=null){
      $("#CTPLQuota").val(coveragr.suminsured);
      $("#CTPLPremium").val(coveragr.premium);
      }else{
        $("#CTPLPremium").val(0);
      }
    }
     if(coveragr.insrnccode==="030101"){
      $("#modQuota").val(coveragr.suminsured);
      $("#modQuotaPremium").val(coveragr.premium);
      $("#modductiblePremium").val(coveragr.nyl12);
    }
     if(coveragr.insrnccode==="030102"){
      $("#vtplPremium").val(coveragr.premium);
      $("#vtplductiblePremium").val(coveragr.nyl12);
    }
     if(coveragr.insrnccode==="030103"){
       $("#theftQuota").val(coveragr.suminsured);
       $("#theftPremium").val(coveragr.premium);
       $("#theftductiblePremium").val(coveragr.nyl12);
    }
     if(coveragr.insrnccode==="030104"){
       $("#DriversPremium").val(coveragr.premium);
       $("#DriversductiblePremium").val(coveragr.nyl12);
    }
     if(coveragr.insrnccode==="030105"){
       $("#PassengerPremium").val(coveragr.premium);
      $("#PassengerductiblePremium").val(coveragr.nyl12);
    }
     if(coveragr.insrnccode==="030108"){
      $("#CombustionQuota").val(coveragr.suminsured);
       $("#CombustionPremium").val(coveragr.premium);
       $("#CombustionductiblePremium").val(coveragr.nyl12);
    }
     if(coveragr.insrnccode==="030107"){
       $("#GlassQuotaPremium").val(coveragr.premium);   
    };       
   };
   }
     if(sypremium==null || sypremium==undefined){
       sypremium=0.00;
    }else if(jqpremium==null || jqpremium==undefined){
      jqpremium=0.00;
    }
     if(currenttax==null || currenttax==undefined){
      currenttax=0.00;
    }
    totalPremium=parseFloat(sypremium)+parseFloat(jqpremium)+parseFloat(currenttax);
    $("#totalPremium").val(totalPremium.toFixed(2)); 
    
    },
    error: function(res){  
 
    } 
});  
}

$('.btn').click(function(){
    $('.max').hide();

});

//车型查询（车架号查询）
function modelSelect(){
	   var modleName=$("#modelName").val();
	   var vinNo=$("#vinno").val();
	   var orderNo='LD20161205002245';
	   var vhldata={vehiclename:modleName,baseinforOrdeo:orderNo};
	   var vhldata1={vinno:vinNo,baseinforOrdeo:orderNo};
	   if(modleName!=='' && modleName!==null && vinno=='' || vinno==null){
		   $.ajax({  
			type: "POST",  
			url: getUrl()+"/vehicleInfor/modelSerachByName.do",  
			data: JSON.stringify(vhldata),//将对象序列化成JSON字符串  
			dataType:"json",  
			contentType : 'application/json;charset=utf-8', //设置请求头信息  
			beforeSend: function () {
		    	this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
		    },
			success: function(data){ 
				layer.close(this.layerIndex);
			    if(data.length==0){
			    	    $('.conModel4').html("");
			            $('.conModel4').append('<p  style="color:red;font-size: 16px;margin-left: 230px;">没搜索到！</p>');
			        }else{
			            $('.conModel4').html("");
			            for(var i= 0;i<data.length;i++){
			            	$('.conModel4').append('<li><i></i><div class="conModel5"><span class="right">'+data[i].VehicleName+'</span><span class="right">'+data[i].Remark+'</span><span class="right">'+data[i].MarketDate+'</span><span class="right">'+data[i].Seat+'</span>座<span class="right">'+data[i].Displacement+'</span>&nbsp;&nbsp;&nbsp;参考价格:<span class="right">'+data[i].PurchasePrice+'</span><span class="right" style="display:none">'+data[i].VehicleCode+'</span><span class="right" style="display:none">'+data[i].BrandName+'</span></div></li>');
			            };
			        }
			    $('.conModel1').show();
			    },  
		    error: function(res){  
		    }  
		   });
	   }else if(vinno!=='' && vinno!==null && modleName=='' || modleName==null){
		   $.ajax({  
			    type: "POST",  
			    url: getUrl()+"/vehicleInfor/modelSerachByVin.do",  
			    data: JSON.stringify(vhldata1),//将对象序列化成JSON字符串  
			    dataType:"json",  
			    contentType : 'application/json;charset=utf-8', //设置请求头信息  
			    beforeSend: function () {
			    	this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
			    },
			    success: function(data){  
			    	layer.close(this.layerIndex);
			    	if(data.length==0){
			    		$('.conModel4').html("");
			            $('.conModel4').append('<p  style="color:red;font-size: 16px;margin-left: 230px;">没搜索到！请使用车型来车型</p>');
			        }else{
				        	$('.conModel4').html("");
				            for(var i= 0;i<data.length;i++){
				            	$('.conModel4').append('<li><i></i><div class="conModel5"><span class="right">'+data[i].VehicleName+'</span><span class="right">'+data[i].Remark+'</span><span class="right">'+data[i].MarketDate+'</span><span class="right">'+data[i].Seat+'</span>座<span class="right">'+data[i].Displacement+'</span>&nbsp;&nbsp;&nbsp;参考价格:<span class="right">'+data[i].PurchasePrice+'</span><span class="right" style="display:none">'+data[i].VehicleCode+'</span><span class="right" style="display:none">'+data[i].BrandName+'</span></div></li>');
				            };
			            }
			    	$('.conModel1').show();
			    },  
			    	error: function(res){  
			    }  
			   });	
	   }else if(vinno!=='' && vinno!==null && modleName!=='' || modleName!==null){
		   var layerIndex;
		   $.ajax({  
			    type: "POST",  
			    url: getUrl()+"/vehicleInfor/modelSerachByVin.do",  
			    data: JSON.stringify(vhldata1),//将对象序列化成JSON字符串  
			    dataType:"json",  
			    contentType : 'application/json;charset=utf-8', //设置请求头信息  
			    beforeSend: function () {
			    	layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
			    },
			    success: function(data){ 
			    	layer.close(layerIndex);
			    	if(data.length==0){
			 		   $.ajax({  
			 				type: "POST",  
			 				url: getUrl()+"/vehicleInfor/modelSerachByName.do",  
			 				data: JSON.stringify(vhldata),//将对象序列化成JSON字符串  
			 				dataType:"json",  
			 				contentType : 'application/json;charset=utf-8', //设置请求头信息  
			 				success: function(data){ 
			 					layer.close(layerIndex);
			 				    if(data.length==0){
			 				    	    $('.conModel4').html("");
			 				            $('.conModel4').append('<p  style="color:red;font-size: 16px;margin-left: 230px;">没搜索到！</p>');
			 				            $('.conModel1').show();
			 				        }else{
			 				            $('.conModel4').html("");
			 				            for(var i= 0;i<data.length;i++){
			 				            	$('.conModel4').append('<li><i></i><div class="conModel5"><span class="right">'+data[i].VehicleName+'</span><span class="right">'+data[i].Remark+'</span><span class="right">'+data[i].MarketDate+'</span><span class="right">'+data[i].Seat+'</span>座<span class="right">'+data[i].Displacement+'</span>&nbsp;&nbsp;&nbsp;参考价格:<span class="right">'+data[i].PurchasePrice+'</span><span class="right" style="display:none">'+data[i].VehicleCode+'</span><span class="right" style="display:none">'+data[i].BrandName+'</span></div></li>');
			 				            };
			 				           $('.conModel1').show();
			 				        }
			 				    },  
			 			    error: function(res){  
			 			    }  
			 			   });
			        }else{
				        	$('.conModel4').html("");
				            for(var i= 0;i<data.length;i++){
				            	$('.conModel4').append('<li><i></i><div class="conModel5"><span class="right">'+data[i].VehicleName+'</span><span class="right">'+data[i].Remark+'</span><span class="right">'+data[i].MarketDate+'</span><span class="right">'+data[i].Seat+'</span>座<span class="right">'+data[i].Displacement+'</span>&nbsp;&nbsp;&nbsp;参考价格:<span class="right">'+data[i].PurchasePrice+'</span><span class="right" style="display:none">'+data[i].VehicleCode+'</span><span class="right" style="display:none">'+data[i].BrandName+'</span></div></li>');
				            };
				            $('.conModel1').show();
			            }
			    },  
			    	error: function(res){  
			    }  
			   });	
	   }  
}

//选中选择的车型
$('.conModel4').on('click',"li",function(){
	var text1=$(this).children('.conModel5').children().eq(0).html();
	var text2=$(this).children('.conModel5').children().eq(1).html();
	var text3=$(this).children('.conModel5').children().eq(2).html();
	var text4=$(this).children('.conModel5').children().eq(3).html();
	var text5=$(this).children('.conModel5').children().eq(4).html();
	var text6=$(this).children('.conModel5').children().eq(5).html();
	var text7=$(this).children('.conModel5').children().eq(6).html();
	var text8=$(this).children('.conModel5').children().eq(7).html();
    $("#marketyear").val(text3);
    $("#setno").val(text4);
    $("#displacement").val(text5);
    $("#vhlval").val(text6);
    $("#brandcode").val(text7);
    $("#brandName").val(text8);
    $('#modelName').val(text1);
    /*$('#model1').html(text2+"&nbsp;"+text3+"&nbsp;"+text4+"&nbsp;"+text5+"&nbsp;"+text6);*/
    $('#model1').append('<span class="right">'+text2+'&nbsp;</span><span class="right">'+text3+'&nbsp;</span><span class="right">'+text4+'座&nbsp;</span><span class="right">'+text5+'&nbsp;</span><span class="right">参考价格:'+text6+'</span>');
    if( $('#model').val()!==""){
    	$('#vehicleModel').hide();
    }
});
