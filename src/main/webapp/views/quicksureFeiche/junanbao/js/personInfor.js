 var wr = new Optiscroll(document.getElementById('page_box'), { forceScrollbars: true });
$(document).ready(function(){
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
        })
    });
    $(document).ready(function(){
        $('.readNotice span').click(function(){
            var text=$(this).text(),Class=".page"+$(this).attr("data-a");console.log(Class);
            $("#page_content").html($(Class).html());
            $('.page_title').html(text);
            $('.shade_box').show();
            $('#m-wrapper').removeClass('anima');
        });
        $('.close_icon').click(function(){
            $('.shade_box').hide();
            $('#m-wrapper').addClass('anima');
        });
        $('.footer i').click(function(){
            if($(this).hasClass('Check')){
                $(this).removeClass('Check')
            }else{ $(this).addClass('Check')}
        })
    });
    //是否本人
    $(document).ready(function(){
    	var now = new Date();
    	var now1 = new Date();
        var date = now.setTime(now.getTime()+1*24*60*60*1000);
        var date1=now.toISOString();
        $("#ijn").val(date1.substr(0,10));
        var date2 = now1.setTime(now1.getTime()+365*24*60*60*1000);
        var date3 = now1.toISOString();
        $("#endDate").val(date3.substr(0,10));
        $("#ijn").change(function(){
        	var datestart =  $("#ijn").val();
        	datestart = datestart.replace(/-/g,'/');
        	var datetime = new Date(datestart);
        	var time = datetime.setTime(datetime.getTime()+365*24*60*60*1000);
        	var StringDate = datetime.toISOString();
        	$("#endDate").val(StringDate.substr(0,10));
        })
        
        $('#select_icon').click(function(){
            if($(this).hasClass('select_NO')){
                $(this).removeClass('select_NO')
                 
            }else{
            	$(this).addClass('select_NO')
            }
            $('.item-inner').slideToggle();
        });
    });
    $(function () {
        var currYear = (new Date()).getFullYear();
        var opt={};
        opt.date = {preset : 'date'};
        opt.datetime = {preset : 'datetime'};
        opt.time = {preset : 'time'};
        opt.default = {
            theme: 'android-ics light',
            display: 'modal',
            mode: 'scroller',
            dateFormat: 'yyyy-mm-dd',
            lang: 'zh',
            showNow: true,
            nowText: "今天",
            startYear: currYear - 50,
            endYear: currYear + 50
        };
        var t=new Date();
        var iToDay=t.getDate();
        var iToMon=t.getMonth();
        var iToYear=t.getFullYear();
        var newDay = new Date(iToYear,iToMon,(iToDay+1));
        var min = { preset : 'date', minDate: new Date(newDay) };
        $("#ijn").mobiscroll($.extend(min, opt['default']));

    });
	   var wr = new Optiscroll(document.getElementById('page_box'), { forceScrollbars: true });
	   //关闭错误提示
	   $(document).ready(function(){
		   $("#ensure").click(function(){
			   $('.errorhei').hide();
		   })
	   });

//保费显示
$(document).ready(function(){
   	var sumpremium=$('#sumpremium').val();
   	$('.price_left').html('&yen'+sumpremium);
   	if(userName!=null&&userName!=""){
  	  if (/^1[34578]\d{9}$/.test(userName)){
  			$('#appPhone').val(userName);
  	  }
   	}
})

function getUrl(){
	 var protocol = window.location.protocol;
	 var host = window.location.host;
	 var pathname = window.location.pathname.split('/');
	 var url = protocol+"//"+host+"/"+pathname[1];
	 return url;
}
function submitinfor() {
  var applicationname = $("#applicationname").val();//投保人姓名
  var appId = $("#appId").val(); //投保人身份证
  var appPhone = $("#appPhone").val(); //投保人手机
  var appEmail = $("#appEmail").val(); //投保人邮箱
  var appAddr = $("#appAddr").val();//投保人城市
  var insuranceStartTime = $("#ijn").val()//保险起期
  var insruanceEndTime = $("#endDate").val();//保险止期
  var ralationship; //被保人和投保人关系
  var insurername; //被保人姓名
  var insId; //被保人身份证
  var insPhone; //被保人手机
  var insEmail;//被保人邮箱
  var insAddr;//被保人地址
  if($("#select_icon").hasClass('select_NO')){
	  ralationship = $("#select_list1").val(); //被保人和投保人关系
	  insurername = $("#insurername").val(); //被保人姓名
	  insId = $("#insId").val(); //被保人身份证
	  insPhone = $("#insPhone").val(); //被保人手机
	  insEmail = $("#insEmail").val();
	  insAddr = $("#insAddr").val();
  }else{
	  ralationship ="601005"; //被保人和投保人关系
	  insurername = $("#applicationname").val(); //被保人姓名
	  insId = $("#appId").val(); //被保人身份证
	  insPhone = $("#appPhone").val(); //被保人手机
	  insEmail = $("#appEmail").val();
	  insAddr = $("#appAddr").val();
  }
  var checkboxflag=$(".footer>i").hasClass('Check');//判断是否勾选复选框
  /*if(applicationname==null || applicationname ==""){
	   $('#Message').html("投保人姓名不能为空");
	  	$('.beijing').hide();
	  	$('.errorhei').show();
  }*/
  //投保人校验
  if(!checkName(applicationname,"app")){		  
  		 return false;
  }
  if(!checkId(appId,"app")){
  		 return false;
  }
  if(!checkPhone(appPhone,"app")){
  		 return false;
  }	 
  if(!checkEmail(appEmail,"app")){
  		 return false;
  }
  //被保人校验
  if(!checkName(insurername,"insure")){
  		 return false;
  	}
  if(!checkId(insId,"insure")){
  		 return false;
  }
  if(!checkPhone(insPhone,"insure")){
  		 return false;
  }	 
  if(!checkEmail(insEmail,"insure")){
  		 return false;
  }	  
  if(!checkboxflag){
	  $("#Message").html("请阅读并同意《投保须知》《保险条款》《理赔指南》《偿付能力告知书》");
  	  $("#errorhei").show();
      return false;
  }
  else{
	  var orderno = $("#orderNo").val();
	  var fcBaseinfor = {
			  applicationname:applicationname,
			  appId:appId,
			  appPhone:appPhone,
			  appEmail:appEmail,
			  appAddr:appAddr,
			  ralationship:ralationship,
			  insurername:insurername,
			  insId:insId,
			  insEmail:insEmail,
			  insAddr:insAddr,
			  insPhone:insPhone,
			  orderno:orderno,
			  prodno:'0615',
			  insuranceStartTime:insuranceStartTime,
			  insruanceEndTime:insruanceEndTime
	  }
	  $("#prompt").html("数据提核中，请稍等...");
	  load(60000);
	  $.ajax({
	      type: "POST",
	      url: getUrl() + "/PersonalAccidentInsurance/submitinfor.do",
	      data: JSON.stringify(fcBaseinfor),
	      //将对象序列化成JSON字符串  
	      dataType: "json",
	      async: true,
	      contentType: 'application/json;charset=utf-8',
	      success: function(data) {
	    	$("#pop").hide();
			var payUrl = data.ludifcBaseinfor.payUrl;
			var errorMassage = data.interfaceslogsWithBLOBs.responsemessage;
            if(payUrl!=null){
        	  window.location.href=payUrl;
            }else{
            	$("#Message").html(errorMassage);
     			$("#errorhei").show();
            }
	      },
	      error: function(res) {           
	      
	      }
	  });
   }
}


/**
 * 校验手机号
 */
function checkPhone(value,type){
	if(value==null||"undefined"==value||""==value){
		if(type==="insure"){
			$("#Message").html("被保人手机号不能为空");
			$("#errorhei").show();
	   		return false;
		}else if(type=="app"){
			$("#Message").html("投保人手机号不能为空");
			$("#errorhei").show();
	   		return false;
		}
	} else if (!(/^1[34578]\d{9}$/.test(value))) {
	 	 if(type==="insure"){
	 		$("#Message").html("被保人手机号输入有误");
	 		$("#errorhei").show();
	    		return false;
	 	}else if(type=="app"){
	 		$("#Message").html("投保人手机号输入有误");
	 		$("#errorhei").show();
	    		return false;
	 	}
	}else{
	 	return true;
	}	
}

/**
 * 校验身份证
 */
function checkId(value,type){
	if(value==null||"undefined"==value||""==value){
		if(type==="insure"){
			$("#Message").html("被保人身份证不能为空");
			$("#errorhei").show();
	   		return false;
		}else if(type=="app"){
			$("#Message").html("投保人身份证不能为空");
			$("#errorhei").show();
	   		return false;
		}
	}else if(value.length!==15&&value.length!==18){
 		if(type==="insure"){
 			$("#Message").html("被保人身份证长度不符,请检查！");
 			$("#errorhei").show();
 	   		return false;
 		}else if(type=="app"){
 			$("#Message").html("投保人身份证长度不符,请检查！");
 			$("#errorhei").show();
 	   		return false;
 		}
	}else{
	var code=value;
	 var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
	    var tip = "";
	    var pass= true;
	    if(!code || !/^\d{6}(19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
	        tip = "The format of Id no is wrong!";
	        pass = false;
	    }

	    else if(!city[code.substr(0,2)]){
	        tip = "Area code is wrong ";
	        pass = false;
	    }
	    else{
	        // 18位身份证需要验证最后一位校验位
	        if(code.length == 18){
	            code = code.split('');
	            // ∑(ai×Wi)(mod 11)
	            // 加权因子
	            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
	            // 校验位
	            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
	            var sum = 0;
	            var ai = 0;
	            var wi = 0;
	            for (var i = 0; i < 17; i++)
	            {
	                ai = code[i];
	                wi = factor[i];
	                sum += ai * wi;
	            }
	            var last = parity[sum % 11];
	            if(parity[sum % 11] != code[17]){
	                tip = "The last character is wrong!";
	                pass =false;
	            }
	        }
	    }
	    if(!pass){
	    	if(type==="insure"){
				$("#Message").html("被保人身份证输入有误，请检查");
				$("#errorhei").show();
		   		return false;
			}else if(type=="app"){
				$("#Message").html("投保人身份证输入有误，请检查");
				$("#errorhei").show();
		   		return false;
			}		
	    }else{
	    	return true;
	    }
	}
}

/**
 * 校验姓名
 */
function checkName(value,type){
	if(value==null||"undefined"==value||""==value){
		if(type==="insure"){
			$("#Message").html("被保人姓名不能为空");
			$("#errorhei").show();
	   		return false;
		}else if(type=="app"){
			$("#Message").html("投保人姓名不能为空");
			$("#errorhei").show();
	   		return false;
		}
	}else if (!(/^[\u4e00-\u9fa5]{2,4}$/i.test(value))) {
		if(type==="insure"){
			$("#Message").html("被保人姓名输入有误，请检查");
			$("#errorhei").show();
	   		return false;
		}else if(type=="app"){
			$("#Message").html("投保人姓名输入有误，请检查");
			$("#errorhei").show();
	   		return false;
		}
	} else{
		return true;
	}	
}
    	  
/**
 * 常用邮箱
 */
function checkEmail(value,type){	
	if(value==null||"undefined"==value||""==value){
		if(type==="insure"){
			$("#Message").html("被保人邮箱不能为空");
			$("#errorhei").show();
	   		return false;
		}else if(type=="app"){
			$("#Message").html("投保人邮箱不能为空");
			$("#errorhei").show();
	   		return false;
		}
	}else if (!(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value))) {
		if(type==="insure"){
			$("#Message").html("被保人邮箱有误，请检查");
			$("#errorhei").show();
	   		return false;
		}else if(type=="app"){
			$("#Message").html("投保人邮箱有误，请检查");
			$("#errorhei").show();
	   		return false;
		}	
	} else{
		return true;
	}	
}

//小写转大写
function setUpperCase(obj){ 
    var a=obj.value;
    var b=a.toLocaleUpperCase();
    obj.value=b;
}

//加载延迟时间
function load(outtimes){
		$("#pop").show();
		setTimeout(function(){$('#pop').hide()
		},outtimes);
	}
