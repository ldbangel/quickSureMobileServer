var dataSuccess = 0; //保费试算成功标志
$('html').ready(function() {
	$("#pop").hide();
    if ($('#modCode').attr('checked') != null) {
        $('#modCode').parent().addClass('on');
        
        var modpremium = $("#modQuotaPremium").val();
        if(modpremium==null ||modpremium=="" || modpremium==undefined){
        	$("#modQuota").val("");
        }else{
        	$("#modTotalPremium").val((parseFloat($("#modQuotaPremium").val())+parseFloat($("#modductiblePremium").val())).toFixed(2));
        	$("#trl1").show();
        }
        
    }
    if ($('#modAbatement').attr('checked') != null) {
        $('#modAbatement').parent().addClass('on');
    }
    if ($('#vtplCode').attr('checked') != null) {
        $('#vtplCode').parent().addClass('on');
        var vtplpremium = $("#vtplPremium").val();
        if(vtplpremium==null ||vtplpremium=="" || vtplpremium==undefined){
        	
        }else{
        	$("#vtplTotalPremium").val((parseFloat($("#vtplPremium").val())+parseFloat($("#vtplductiblePremium").val())).toFixed(2));
        	$("#trl2").show();
        }
    }
    if ($('#vtplAbatement').attr('checked') != null) {
        $('#vtplAbatement').parent().addClass('on');
    }
    if ($('#theftCode').attr('checked') != null) {
        $('#theftCode').parent().addClass('on');
        var theftpremium = $("#theftPremium").val();
        if(theftpremium==null ||theftpremium=="" || theftpremium==undefined){
        	$("#theftQuota").val("");
        }else{
            $("#theftTotalPremium").val((parseFloat($("#theftPremium").val())+parseFloat($("#theftductiblePremium").val())).toFixed(2));	
        	$("#trl3").show(); 
        }
        
    }
    if ($('#theftAbatement').attr('checked') != null) {
        $('#theftAbatement').parent().addClass('on');
    }
    if ($('#DriversCode').attr('checked') != null) {
        $('#DriversCode').parent().addClass('on');
        var Driverspremium = $("#DriversPremium").val();
        if(Driverspremium==null ||Driverspremium=="" || Driverspremium==undefined){
        	
        }else{       	
            $("#DriversTotalPremium").val((parseFloat($("#DriversPremium").val())+parseFloat($("#DriversductiblePremium").val())).toFixed(2));
            $("#trl4").show();
        }
        
    }
    if ($('#DriversAbatement').attr('checked') != null) {
        $('#DriversAbatement').parent().addClass('on');
    }
    if ($('#PassengerCode').attr('checked') != null) {
        $('#PassengerCode').parent().addClass('on');
        var Passengerpremium = $("#PassengerPremium").val();
        if(Passengerpremium==null ||Passengerpremium=="" || Passengerpremium==undefined){
        	
        }else{
        	$("#PassengerTotalPremium").val((parseFloat($("#PassengerPremium").val())+parseFloat($("#PassengerductiblePremium").val())).toFixed(2));
        	$("#trl5").show();
        }
        
        
    }
    if ($('#PassengerAbatement').attr('checked') != null) {
        $('#PassengerAbatement').parent().addClass('on');
    }
    if ($('#CombustionCode').attr('checked') != null) {
        $('#CombustionCode').parent().addClass('on');
        var Combustionpremium = $("#CombustionPremium").val();
        if(Combustionpremium==null ||Combustionpremium=="" || Combustionpremium==undefined){
        	$("#CombustionQuota").val("");
        }else{
        	$("#CombustionTotalPremium").val((parseFloat($("#CombustionPremium").val())+parseFloat($("#CombustionductiblePremium").val())).toFixed(2));
        	$("#trl6").show();
        }
        
        
    }
    if ($('#CombustionAbatement').attr('checked') != null) {
        $('#CombustionAbatement').parent().addClass('on');
    }
    if ($('#GlassCode').attr('checked') != null) {
        $('#GlassCode').parent().addClass('on');
        var GlassQuotapremium = $("#GlassQuotaPremium").val();
        if(GlassQuotapremium==null ||GlassQuotapremium=="" || GlassQuotapremium==undefined){
        	
        }else{
        	 $("#GlassTotalPremium").val($("#GlassQuotaPremium").val());
        	 $("#trl7").show();
        }
       
       
    }
    if ($('#CTPLCode').attr('checked') != null) {
        $('#CTPLCode').parent().addClass('on');
    }
    var jqprem = $("#CTPLPremium").val();
    var curtax = $("#currenttax1").val();
    if ($("#CTPLCode").prop("checked")) {
        if (jqprem !== null && jqprem !== 0 && jqprem !== '') {
            $("#b2").show();
            $("#b3").show();
            $("#jqpremium").val(jqprem);
        }
        var jqdate = $('#JQ_DATE').val();
        jqdate = jqdate.substring( - 1, 10);
        $('#JQ_DATE').val(jqdate);
    } else {
        $("#jqpremium").val("");
        $("#currenttax1").val("");
        $("#currenttax").val("");
        $('#JQ_DATE').val("");
    }
    var syprem = $("#sypremium").val();
    if ($('#modCode').prop('checked') || $('#vtplCode').prop('checked') || $('#theftCode').prop('checked') || $('#DriversCode').prop('checked') || $('#PassengerCode').prop('checked')) {
        if (syprem !== null && syprem !== 0 && syprem !== '') {
            $("#b1").show();
        }
        if (syprem !== null && syprem !== 0 && syprem !== '' || jqprem !== null && jqprem !== 0 && jqprem !== '') {
            if (syprem !== null && syprem !== 0 && syprem !== '' && jqprem == null || jqprem == 0 || jqprem == '') {
                $("#totalPremium").val(syprem);
            }
            if (jqprem !== '' && curtax == '') {
                var totalPrem = parseFloat(syprem) + parseFloat(jqprem);
                $("#totalPremium").val(totalPrem.toFixed(2));
            }
            if (syprem !== null && syprem !== 0 && syprem !== '' && jqprem !== null && jqprem !== 0 && jqprem !== '' && curtax !== null && curtax !== '' && curtax !== 0) {
                var totalPrem = parseFloat(syprem) + parseFloat(jqprem) + parseFloat(curtax);
                $("#totalPremium").val(totalPrem.toFixed(2));
            }
        } else {
            $("#totalPremium").val("");
        }
        var sydate = $('#SY_DATE').val();
        sydate = sydate.substring( - 1, 10);
        $('#SY_DATE').val(sydate);
    } else {
        $("#sypremium").val("");
        $("#totalPremium").val("");
        $('#SY_DATE').val("");
    }
    
        $('.td1 span').click(function() {
            if (dataSuccess == 1) {
                dataSuccess = 0;
                $('#chackMess').html("做出任何改变后请重新点击保费计算！");
                $('.errorhei').show();
                $('#next').hide();
               // Hide();
            }

        });
        $('.td2 span').click(function() {
            if (dataSuccess == 1) {
                dataSuccess = 0;
                $('#chackMess').html("做出任何改变后请重新点击保费计算！");
                $('.errorhei').show();
                $('#next').hide();
               // Hide();
            }

        });
        $('.con3_span').click(function() {
            if (dataSuccess == 1) {
                dataSuccess = 0;
                $('#chackMess').html("做出任何改变后请重新点击保费计算！");
                $('.errorhei').show();
                $('#next').hide();
               // Hide();
            }

        });
        $('.select').change(function() {
            if (dataSuccess == 1) {
                dataSuccess = 0;
                $('#chackMess').html("做出任何改变后请重新点击保费计算！");
                $('.errorhei').show();
                $('#next').hide();
              //  Hide();
            }

        });
        $('.con3_p2 input').change(function() {
            if (dataSuccess == 1) {
                dataSuccess = 0;
                $('#chackMess').html("做出任何改变后请重新点击保费计算！");
                $('.errorhei').show();
                $('#next').hide();
              //  Hide();
            }

        });
        $('#selectAll').click(function() {
            if (dataSuccess == 1) {
                dataSuccess = 0;
                $('#chackMess').html("做出任何改变后请重新点击保费计算！");
                $('.errorhei').show();
                $('#next').hide();
              //  Hide();
            }

        });
});

function getCoverage() {
    var coverageList = new Array();

    //车损
    if ($("#modCode").prop("checked")) {
        var modsuminsured = 1; //车损保额
        var moddeductibleflag;
        if ($("#modAbatement").prop("checked")) {
            moddeductibleflag = 1;
        } else {
            moddeductibleflag = 0;
        }
        coverageList.push({
            insrnccode: "101",
            suminsured: modsuminsured,
            deductibleflag: moddeductibleflag
        });
    } else {
        $("#modQuota").val("");
        $("#modQuotaPremium").val("");
        $("#modductiblePremium").val("");
        $("#modTotalPremium").val("");
        $("#trl1").hide();
    }
    //三者
    if ($("#vtplCode").prop("checked")) {
        var vtpldeductibleflag; //不计免赔
        var vtplsuminsured = $("#vtplQuota").val();; //保额
        if ($("#vtplAbatement").prop("checked")) {
            vtpldeductibleflag = 1;
        } else {
            vtpldeductibleflag = 0;
        }
        coverageList.push({
            insrnccode: "102",
            suminsured: vtplsuminsured,
            deductibleflag: vtpldeductibleflag
        });
    } else {
        $("#vtplPremium").val("");
        $("#vtplductiblePremium").val("");
        $("#vtplTotalPremium").val("");
        $("#trl2").hide();
    }
    //盗抢
    if ($("#theftCode").prop("checked")) {
        var TheftDeductibleflag;
        var TheftSuminsured = 1; //保额
        if ($("#theftAbatement").prop("checked")) {
            TheftDeductibleflag = 1;
        } else {
            TheftDeductibleflag = 0;
        }
        coverageList.push({
            insrnccode: "103",
            suminsured: TheftSuminsured,
            deductibleflag: TheftDeductibleflag
        });
    } else {
        $("#theftQuota").val("");
        $("#theftPremium").val("");
        $("#theftductiblePremium").val("");
        $("#theftTotalPremium").val("");
        $("#trl3").hide();
    }
    //司机责任
    if ($("#DriversCode").prop("checked")) {
        var Driversdeductibleflag; //不计免赔
        var Driverssuminsured = $("#DriversQuota").val();; //保额
        if ($("#DriversAbatement").prop("checked")) {
            Driversdeductibleflag = 1;
        } else {
            Driversdeductibleflag = 0;
        }
        coverageList.push({
            insrnccode: "104",
            suminsured: Driverssuminsured,
            deductibleflag: Driversdeductibleflag
        });
    } else {
        $("#DriversPremium").val("");
        $("#DriversductiblePremium").val("");
        $("#DriversTotalPremium").val("");
        $("#trl4").hide();
    }
    //乘客责任
    if ($("#PassengerCode").prop("checked")) {
        var Passengerdeductibleflag; //不计免赔
        var Passengersuminsured = $("#PassengerQuota").val();; //保额
        if ($("#PassengerAbatement").prop("checked")) {
            Passengerdeductibleflag = 1;
        } else {
            Passengerdeductibleflag = 0;
        }
        coverageList.push({
            insrnccode: "105",
            suminsured: Passengersuminsured,
            deductibleflag: Passengerdeductibleflag
        });
    } else {
        $("#PassengerPremium").val("");
        $("#PassengerductiblePremium").val("");
        $("#PassengerTotalPremium").val("");
        $("#trl5").hide();
    }
    //自燃
    if ($("#CombustionCode").prop("checked")) {
        var CombustionDeductibleflag;
        var CombustionSuminsured = 1; //保额
        if ($("#CombustionAbatement").prop("checked")) {
            CombustionDeductibleflag = 1;
        } else {
            CombustionDeductibleflag = 0;
        }
        coverageList.push({
            insrnccode: "108",
            suminsured: CombustionSuminsured,
            deductibleflag: CombustionDeductibleflag
        });
    } else {
        $("#CombustionQuota").val("");
        $("#CombustionPremium").val("");
        $("#CombustionductiblePremium").val("");
        $("#CombustionTotalPremium").val("");
        $("#trl6").hide();
    }
    //玻璃
    if ($("#GlassCode").prop("checked")) {
        var GlassDeductibleflag;
        var Glasssuminsured = $("#GlassQuota").val();; //保额
        coverageList.push({
            insrnccode: "107",
            suminsured: Glasssuminsured,
            deductibleflag: GlassDeductibleflag
        });
    } else {
        $("#GlassQuotaPremium").val("");
        $("#GlassTotalPremium").val("");
        $("#trl7").hide();
    }
    //第三方
    if ($("#ThirdPartyCode").prop("checked")) {
        var ThirdPartyDeductibleflag = null;
        var ThirdPartySuminsured = 1; //保额
        coverageList.push({
            insrnccode: "115",
            suminsured: ThirdPartySuminsured,
            deductibleflag: ThirdPartyDeductibleflag
        });
    } else {
        $("#ThirdPartyQuota").val("");
        $("#ThirdPartyPremium").val("");
    }
    //专车
    if ($("#CarCode").prop("checked")) {
        var CarDeductibleflag = null;
        var CarSuminsured = 1; //保额
        coverageList.push({
            insrnccode: "116",
            suminsured: CarSuminsured,
            deductibleflag: CarDeductibleflag
        });
    } else {
        $("#CarQuota").val("");
        $("#CarPartyPremium").val("");
    }
    //交强
    if ($("#CTPLCode").prop("checked")) {
        var TCPLdeductibleflag = null;
        var TCPLsuminsured = 1; //保额
        coverageList.push({
            insrnccode: "357",
            suminsured: TCPLsuminsured,
            deductibleflag: TCPLdeductibleflag
        });
    } else {
        $("#CTPLQuota").val("");
        $("#CTPLPremium").val("");
        $("#currenttax").val("");
        $("#appDate_1").val("");
    }
    //起保时间
    var syStartDate = document.getElementById("SY_DATE").value;
    var jqStartDate = document.getElementById("JQ_DATE").value;
    //订单号
    var orderNo = document.getElementById("orderNo").value;
    if (syStartDate != null || jqStartDate != null || orderNo != null) {
        coverageList.push({
            sypolicystartdate: syStartDate,
            jqpolicystartdate: jqStartDate,
            baseinfororderno: orderNo
        });
    }

    return coverageList;
}
function getQuote() {
    dataSuccess = 0;
    //险种规则搭配
    if ($("#modCode").prop("checked") || $("#vtplCode").prop("checked") || $("#theftCode").prop("checked") || $("#DriversCode").prop("checked") || $("#PassengerCode").prop("checked")) {
    	//车辆损失险 //第三者责任险//全车盗抢险//司机座位责任险//乘客座位责任险
    } else {
	   $("#prompt").hide();
        $('#chackMess').html("必须选择一个主险投保");
        $('.errorhei').show();
       // Hide();
        $('#next').hide(); 
        return false;
    }
    //不能单车损和选择了附加险不选择车损//自燃损失险//玻璃单独破碎险
    if ($("#CombustionCode").prop("checked") || $("#GlassCode").prop("checked") || $("#CarCode").prop("checked") || $("#ThirdPartyCode").prop("checked")) {
        if ($("#modCode").prop("checked")) {//车辆损失险 
            if ($("#vtplCode").prop("checked") || $("#theftCode").prop("checked") || $("#DriversCode").prop("checked") || $("#PassengerCode").prop("checked")) {

            } else {
	            $("#prompt").hide();
                $('#chackMess').html("不能单承保车损和附加险");
                $('.errorhei').show();
                //Hide();
                $('#next').hide();
                return false;
            }
        } else if ($("#PassengerCode").prop("checked")) {
            if ($("#DriversCode").prop("checked")) {
            	$("#prompt").hide();
                $('#chackMess').html("承保车损了才能承保附加险");
                $('.errorhei').show();
               // Hide();
                $('#next').hide();
                return false;
            } else {
            	$("#prompt").hide();
                $('#chackMess').html("承保车损了才能承保附加险+承保司机责任险才能承保乘客责任险");
                $('.errorhei').show();
               // Hide();
                $('#next').hide();
                return false;
            }

        } else {
        	$("#prompt").hide();
            $('#chackMess').html("承保车损了才能承保附加险");
            $('.errorhei').show();
            //Hide();
            $('#next').hide();
            return false;
        }
    }
    //不能单车损
    if ($("#modCode").prop("checked")) {
        if ($("#vtplCode").prop("checked") || $("#theftCode").prop("checked") || $("#DriversCode").prop("checked") || $("#PassengerCode").prop("checked")) {

        } else {
	        $("#prompt").hide();
            $('#chackMess').html("不能单独投保车损险");
            $('.errorhei').show();
           // Hide();
            $('#next').hide();
            return false;
        }
    }
    //必须选择了司机责任才能选择乘客
    if ($("#PassengerCode").prop("checked")) {
        if ($("#DriversCode").prop("checked")) {

        } else {
	        $("#prompt").hide();
            $('#chackMess').html("承保司机责任险才能承保乘客责任险");
            $('.errorhei').show();
           // Hide();
            $('#next').hide();
            return false;
        }
    }
    //目前不能承保无法找到第三方损失险和专车修理厂险
    if ($("#ThirdPartyCode").prop("checked") || $("#CarCode").prop("checked")) {
    	$("#prompt").hide();
        $('#chackMess').html("很抱歉！目前不能投保'无法找到第三方损失险'和'专车修理厂险'险别");
        $('.errorhei').show();
       // Hide();
        $('#next').hide();
        return false;
    }
    var syDate = document.getElementById("SY_DATE").value;
    var jqDate = document.getElementById("JQ_DATE").value;
    if (syDate == "" || syDate == null || syDate == undefined) {
    	$("#prompt").hide();
        $('#chackMess').html("商业险起保时间不能为空");
        $('.errorhei').show();
       // Hide();
        $('#next').hide();
        return false;
    }
    if ($("#CTPLCode").prop("checked")) {
        if (jqDate == "" || jqDate == null || jqDate == undefined) {
        	$("#prompt").hide();
            $('#chackMess').html("交强险起保时间不能为空");
            $('.errorhei').show();
           // Hide();
            $('#next').hide();
            return false;
        }

    }
    var coveragelist = getCoverage();
    $("#prompt").html("保费计算中，请稍等...");
    load(60000);
    $.ajax({
        type: "POST",
        url: getUrl() + "/PremiumCount/premiumCount.do",
        data: JSON.stringify(coveragelist),
        //将对象序列化成JSON字符串  
        dataType: "json",
        async: true,
        contentType: 'application/json;charset=utf-8',
        success: function(data) {
            var sypremium;
            var jqpremium;
            var currenttax;
            var currenttax1;
            var totalPremium;
            var coverageinfors = data.coverageinfors;
            //layer.close(this.layerIndex);
            $("#pop").hide();
            // 如果返回错误给予友好提示框
            var errorcode = "E00000030";
            var errorcode2 = "C99999999";
            if (data.userinfor.errorCode == null && data.baseinfor.sypremium == null) {
                dataSuccess = 0;
                $('#chackMess').html("请求失败或者超时,请稍后重试！");
                $('.errorhei').show();
                //Hide();
            } else if (data.userinfor.errorCode == errorcode || data.userinfor.errorCode == errorcode2|| data.userinfor.errorCode !== null ) {
                if (data.userinfor.agentFlag == 1 || data.userinfor.agentFlag==2) {
                    var errorMessage = data.userinfor.errorMessage;
                    $("#chackMessage").html(errorMessage);
                    $('#errorService').show();
                    $('#next').hide();
                    $("#trl1").hide();
                    $("#trl2").hide();
                    $("#trl3").hide();
                    $("#trl4").hide();
                    $("#trl5").hide();
                    $("#trl6").hide();
                    $("#trl7").hide();
                } else {
                    $('#chackMessage').html("保费计算失败，请拨打客服热线10100111-6咨询！");
                    $('#errorService').show();
                    $('#next').hide();
                    $('#next').hide();
                    $("#trl1").hide();
                    $("#trl2").hide();
                    $("#trl3").hide();
                    $("#trl4").hide();
                    $("#trl5").hide();
                    $("#trl6").hide();
                    $("#trl7").hide();
                }
            } else {
                $('#errorService').hide();
                $('#next').show();
                var inputSYdate = $("#SY_DATE").val();//录入的时间
                var inputJQdate = $("#JQ_DATE").val();//
                var returnSYdate = data.baseinfor.sypolicystartdate;//平台返回的时间
                var returnJQdate = data.baseinfor.jqpolicystartdate;
                
                if(inputJQdate==""||inputJQdate==null){
                    returnSYdate=returnSYdate.substring(-1,10);
                 	  if(inputSYdate!==returnSYdate){
                 		 $('.errorhei').show();
                 		 $("#SY_DATE").val(returnSYdate);
                 		 $('#chackMess').html("填写的商业起保时间为:"+inputSYdate+" 实际为:"+returnSYdate+" 已经回填正确的时间！");
                 		 Hide1(); 
                 	  }
                }else{
                	returnSYdate=returnSYdate.substring(-1,10);
                	returnJQdate=returnJQdate.substring(-1,10);
                	 if(inputSYdate!== returnSYdate && inputJQdate!==returnJQdate){
                		 $('.errorhei').show();
                		 $("#SY_DATE").val(returnSYdate);
                		 $("#JQ_DATE").val(returnJQdate);
                		 $('#chackMess').html("填写的商业起保时间为:"+inputSYdate+" 实际为:"+returnSYdate+" 填写的交强起保时间为:"+inputJQdate+" 实际为:"+returnJQdate+" 已经回填正确的时间！");
                		 Hide1();
                	 }else if(inputSYdate!==returnSYdate){
                		 $('.errorhei').show();
                		 $("#SY_DATE").val(returnSYdate);
                		 $('#chackMess').html("填写的商业起保时间为:"+inputSYdate+" 实际为:"+returnSYdate+" 已经回填正确的时间！");
                		 Hide1();
                	 }else if(inputJQdate!==returnJQdate){
                		 $('.errorhei').show();
                		 $("#JQ_DATE").val(returnJQdate);
                		 $('#chackMess').html("填写的交强起保时间为:"+inputSYdate+" 实际为:"+returnSYdate+" 已经回填正确的时间！"); 
                		 Hide1();
                	 }
                }
                	
                
                
            }

            if (data.baseinfor != null) {
                if (data.baseinfor.sypremium !== null) {
                    sypremium = data.baseinfor.sypremium;
                    $("#sypremium").val(sypremium);
                    $("#b1").show();
                }else{
                	$("#sypremium").val("");    	
                }

                if (data.baseinfor.jqpremium !== null) {
                    jqpremium = data.baseinfor.jqpremium;
                    $("#jqpremium").val(jqpremium);
                    $("#b2").show();
                } else {
                    $("#jqpremium").val("");
                    $("#CTPLPremium").val("");
                    $("#b2").hide();
                }
                if (data.baseinfor.totalPremium !== null) {
                    totalPremium = data.baseinfor.totalPremium;
                    $("#totalPremium").val(totalPremium);
                } else {
                    $("#totalPremium").val("");
                }

                if (data.baseinfor.jqpolicystartdate !== null) {
                    $("#appDate_1").val(data.baseinfor.jqpolicystartdate);
                }
                if (data.baseinfor.sypolicystartdate !== null) {
                    $("#appDate").val(data.baseinfor.sypolicystartdate);
                }
            }
            if (data.taxinfor !== null) {
                if (data.taxinfor.sumuptax !== null) {
                    currenttax = data.taxinfor.sumuptax;
                    $("#currenttax").val(data.taxinfor.sumuptax);
                    $("#currenttax1").val(currenttax);
                    $("#b3").show();
                }else{
                	$("#currenttax").val("");
                    $("#currenttax1").val("");
                    $("#b3").hide();
                }
            } else {
                $("#currenttax1").val("");
                $("#currenttax").val("");
                $("#b3").hide();
            }
            if (coverageinfors !== null && data.baseinfor.sypremium !== null) {
                dataSuccess = 1;
                for (var i = 0; i < coverageinfors.length; i++) {
                    var coveragr = coverageinfors[i];
                    if (coveragr.insrnccode === "0357") {
                        if (coveragr.premium != null) {
                            $("#CTPLQuota").val(coveragr.suminsured);
                            $("#CTPLPremium").val(coveragr.premium);
                        }
                    }
                    if (coveragr.insrnccode === "030101") {
                        $("#modQuota").val(coveragr.suminsured);
                        $("#modQuotaPremium").val(coveragr.premium);
                        $("#modductiblePremium").val(coveragr.nyl12);
                        $("#modTotalPremium").val((parseFloat(coveragr.premium)+parseFloat(coveragr.nyl12)).toFixed(2));
                        $("#trl1").show();
                    }
                    if (coveragr.insrnccode === "030102") {
                        $("#vtplPremium").val(coveragr.premium);
                        $("#vtplductiblePremium").val(coveragr.nyl12);
                        $("#vtplTotalPremium").val((parseFloat(coveragr.premium)+parseFloat(coveragr.nyl12)).toFixed(2));
                        $("#trl2").show();
                    }
                    if (coveragr.insrnccode === "030103") {
                        $("#theftQuota").val(coveragr.suminsured);
                        $("#theftPremium").val(coveragr.premium);
                        $("#theftductiblePremium").val(coveragr.nyl12);
                        $("#theftTotalPremium").val((parseFloat(coveragr.premium)+parseFloat(coveragr.nyl12)).toFixed(2));
                        $("#trl3").show();
                    }
                    if (coveragr.insrnccode === "030104") {
                        $("#DriversPremium").val(coveragr.premium);
                        $("#DriversductiblePremium").val(coveragr.nyl12);
                        $("#DriversTotalPremium").val((parseFloat(coveragr.premium)+parseFloat(coveragr.nyl12)).toFixed(2));
                        $("#trl4").show();
                    }
                    if (coveragr.insrnccode === "030105") {
                        $("#PassengerPremium").val(coveragr.premium);
                        $("#PassengerductiblePremium").val(coveragr.nyl12);
                        $("#PassengerTotalPremium").val((parseFloat(coveragr.premium)+parseFloat(coveragr.nyl12)).toFixed(2));
                        $("#trl5").show();
                    }
                    if (coveragr.insrnccode === "030108") {
                        $("#CombustionQuota").val(coveragr.suminsured);
                        $("#CombustionPremium").val(coveragr.premium);
                        $("#CombustionductiblePremium").val(coveragr.nyl12);
                        $("#CombustionTotalPremium").val((parseFloat(coveragr.premium)+parseFloat(coveragr.nyl12)).toFixed(2));
                        $("#trl6").show();
                    }
                    if (coveragr.insrnccode === "030107") {
                        $("#GlassQuotaPremium").val(coveragr.premium);
                        $("#GlassTotalPremium").val(coveragr.premium);
                        $("#trl7").show();
                    }
                }
            }else{
            	$("#sypremium").val("");
            }
            if (sypremium == null || sypremium == undefined) {
                sypremium = 0.00;
            }
            if (jqpremium == null || jqpremium == undefined) {
                jqpremium = 0.00;
            }
            if (currenttax == null || currenttax == undefined) {
                currenttax = 0.00;
                currenttax1 = 0.00;
            }
            totalPremium = parseFloat(sypremium) + parseFloat(jqpremium) + parseFloat(currenttax);
            if (totalPremium == 0 || totalPremium == null || totalPremium == undefined) {
            	$("#totalPremium").val("");
            	$("#b1").hide();
            } else {
                $("#totalPremium").val(totalPremium.toFixed(2));
            }

        },
        error: function(res) {           
            	$("#pop").hide();
                $('#chackMess').html("保费计算失败");
                $('.errorhei').show();
                //Hide();
}
    });
     return false;
}

function submitFrom() {
    $("#prompt").html("数据加载中，请稍等...");
    load(15000);
    $("#form").submit();
}
function load(outtimes) {
    $("#pop").show();
    setTimeout(function() {
        $('#pop').hide()
    },
    outtimes);
}
/*function Hide() {
    setTimeout(function() {
        $('.errorhei').hide()
    },
    2000);
}*/
function Hide1() {
    setTimeout(function() {
        $('.errorhei').hide()
    },
    5000);
}
/*var wr = new Optiscroll(document.getElementById('m-wrapper'), { forceScrollbars: true });*/
