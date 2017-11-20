

$(document).ready(function(){
  $("#con_ultab li").click(function(){
     $(this).addClass('on').siblings().removeClass('on');
     var index=$(this).index();
     $('.con_ul_tab>div:eq('+index+')').show().siblings().hide();
  });

});


$(document).ready(function(){
  $(".con7_ul li").click(function(){
	  var This_Input=$(this).children('input');
	 
		  This_Input.attr('checked','checked');
		  $(this).addClass('on').siblings().removeClass('on');
		  $(this).siblings().children('input').removeAttr('checked');
	 
    
  });

});

$(document).ready(function(){
  $(".tang5_ul li").click(function(){
    $(this).addClass('on').siblings().removeClass('on');
    var text=$(this).find('span').text();
	$('#model').val(text);
  });

});


$(document).ready(function(){
	   $(".con_p8 a").click(function(e){
		  var text=$(this).text();
		  $(".nation_title").html(text);
	      e.preventDefault();
	      var Class="#"+$(this).attr('class');
	      $('.add_content').html(" ");
	      $('.beijing').show();
	      $('.con4_tang1').show();
	      $('.add_content').append($(Class).html());
	      $("body").css('overflow-y','hidden');
	   });
});
$(document).ready(function(){
	$(".con_p2 span").click(function(){
		var depAddress;
		var li=$(this).children('on');
		if(ispc){
			depAddress=$("#city").val();
		}else{
			depAddress=$("#demo1").val();
		}
		
		if($(this).hasClass('on'))
		{
		$('#lcno').removeAttr('readonly');
		 $(this).removeClass('on');
		 if(depAddress!=null&&"undefined"!==typeof(depAddress)){			 
			 setDeptNoandLcnNo(depAddress,true);			 
		 }else{
			 $('#lcno').val(' ').removeAttr('readonly');	
		 }
		
		// $('#lcno').val(' ').removeAttr('readonly');	
		}else{
			$(this).addClass('on');
			$('#lcno').val('*-*').attr('readonly',true);
	    	$('.errorpop').css("display","none")
			}
		
	});
});
$(document).ready(function(){
	$(".con_ul span").click(function(){
		var li=$(this).children('on');
		var Input=$(this).children('input');
		if($(this).hasClass('on')){
			Input.removeAttr('checked');
		    $(this).removeClass('on');
		}else{
			$(this).addClass('on');
			Input.attr('checked','checked');
			}
	});
});
//交强险的选择()
$(document).ready(function(){
	$(".con3_p2 input").change(function(){
		var li=$(".con3_span").children('on');
		var Input=$(".con3_span").children('input');
		var jqdate = $("#JQ_DATE").val();//交强险
		var sydate=$("#SY_DATE").val();//商业险
		if(jqdate==null||jqdate==""){
			Input.removeAttr('checked');
	  		 $(".con3_span").removeClass('on');
        }else{
        	$(".con3_span").addClass('on');
			 Input.attr('checked','checked');
        }
		if(sydate==null ||sydate==""){
			 $("#SY_DATE").val(jqdate); 
		}else{
			  
		}
          
		
	});
});
//交强险的选择()
$(document).ready(function(){
	$(".con3_p3 span").click(function(){
		var li=$(this).children('on');
		var Input=$(this).children('input');
		var syDate = $("#SY_DATE").val();//商业险
		if($(this).hasClass('on')){
			Input.removeAttr('checked');
		  $(this).removeClass('on');
		  $("#JQ_DATE").val("");
		}else{
			$("#JQ_DATE").val(syDate);
			$(this).addClass('on');
			Input.attr('checked','checked');
			}
		
	});
});
//选择了全选后全部勾选上
$(document).ready(function(){
	$("#selectAll").click(function(){
		var right_span1=$(".td1 span").children('input');
/*		var right_span=$(".td1 span").parent().siblings().eq(0).children('span').children('input');*/
		var right_span=$(".td2 span").children('input');
		if($(this).hasClass('on')){
		    $(this).removeClass('on');
		    $(".td1 span").removeClass('on');
		    $(".td2 span").removeClass('on');
		    right_span1.removeAttr('checked');
		    right_span.removeAttr('checked');
		}else{
			$(this).addClass('on');
			$(".td1 span").addClass('on');
		    $(".td2 span").addClass('on');
			right_span1.attr('checked','checked');
			 right_span.attr('checked','checked');
			}
	});
});
//选择了选种后自动勾上不计免赔
$(document).ready(function(){
	$(".td1 span").on("click",function(){
		var li=$(this).children('on');
		var Input=$(this).children('input');
		var right_class=$(this).parent().siblings().eq(0).children('span');
		var right_span=$(this).parent().siblings().eq(0).children('span').children('input');
		if($(this).hasClass('on')){$('.td01>span').removeClass('on');
			Input.removeAttr('checked');
		    $(this).removeClass('on');
		    right_span.removeAttr('checked');
		    right_class.removeClass('on');
		}else{
			$(this).addClass('on');
			right_class.addClass('on');
			Input.attr('checked','checked');
			 right_span.attr('checked','checked');
			}
	});
});
//选择了不计免赔后自动选择对应的选择，可以取消不计免赔
$(document).ready(function(){
	$(".td2 span").click(function(){
		var li=$(this).children('on');
		var Input=$(this).children('input');
		var right_class=$(this).parent().siblings().eq(0).children('span');
		var right_span=$(this).parent().siblings().eq(0).children('span').children('input');
		if($(this).hasClass('on')){$('.td01>span').removeClass('on');
			Input.removeAttr('checked');
		    $(this).removeClass('on');
		}else{
			$(this).addClass('on');
			right_class.addClass('on');
			Input.attr('checked','checked');
			 right_span.attr('checked','checked');
			}
	});
});


$(document).ready(function(){
	$("#shuomingshu").click(function(){
		$('.beijing').show();
		$('.con4_tang1').show();
		$("body").css('overflow-y','hidden');
	});
	$(".close").click(function(){
		$('.beijing').hide();
		$('.con4_tang1').hide();
		$("body").css('overflow-y','auto');
	});
});

$(document).ready(function(){
	$("#tang2").click(function(){
		$('.beijing').show();
		$('.con4_tang2').show();
		//弹出框后面的页面还能滑动，要求不滑动
		/*if($('.beijing').css('display')=='block'){
			$(document).on("touchmove", function (event) {  return false; });
		}*/
		
		$("body").css('overflow-y','hidden');
	});
	$(".close").click(function(){
		$('.beijing').hide();
		$('.con4_tang2').hide();
		$("body").css('overflow-y','auto');
	});
});

$(document).ready(function(){
	$("#tang3").click(function(){
		$('.beijing').show();
		$('.con4_tang3').show();
		$("body").css('overflow-y','hidden');
	});
	$(".close").click(function(){
		$('.beijing').hide();
		$('.con4_tang3').hide();
		$("body").css('overflow-y','auto');
	});
});


$(document).ready(function(){
	$("#tang4").click(function(){
		if($('#registerdate[type=text]').val().length==0)
		{
		$('.beijing').show();
		$('.con4_tang4').show();
		$("body").css('overflow-y','hidden');
		}
	});
	$(".close4").click(function(){
		$('.beijing').hide();
		$('.con4_tang4').hide();
		$("body").css('overflow-y','auto');
	});
});

$(document).ready(function(){
	/*$("#model").click(function(){
		$('.beijing').show();
		$('.con4_tang5').show();
		$("body").css('overflow-y','hidden');
	});*/
	$(".que").click(function(){
		$('.beijing').hide();
		$('.con4_tang5').hide();
		$("body").css('overflow-y','auto');
	});
});

$(document).ready(function(){
	$("#tang6").click(function(){
		$('.beijing').show();
		$('.con4_tang6').show();
		$("body").css('overflow-y','hidden');
		
		var ulWidth=$('.con4_tang6_a').outerWidth()+20;
	    $('.con4_tang6_ul2').width(ulWidth);
	    $('.con4_tang6_ul2 li').width(ulWidth);
	});
	$(".close").click(function(){
		$('.beijing').hide();
		$('.con4_tang6').hide();
		$("body").css('overflow-y','auto');
	});
});


$(document).ready(function(){
	$("#tang7").click(function(){
		$('.beijing').show();
		$('.con4_tang7').show();
		$("body").css('overflow-y','hidden');
		
		var ulWidth=$('.con4_tang6_a').outerWidth()+20;
	    $('.con4_tang6_ul2').width(ulWidth);
	    $('.con4_tang6_ul2 li').width(ulWidth);
	});
	$(".tang6").click(function(){
		$('.beijing').hide();
		$('.con4_tang7').hide();
		$("body").css('overflow-y','auto');
	});
});

$(document).ready(function(){
	$("#tanga").click(function(){
		$('.beijing').show();
		$('.con4_tang8').show();
		$("body").css('overflow-y','hidden');
	});
	$(".close").click(function(){
		$('.beijing').hide();
		$('.con4_tang8').hide();
		$("body").css('overflow-y','auto');
	});
	$('#con_d2>ul').click(function(){
		var depAddress;
		if(ispc){
			depAddress=$("#city").val();
		}else{
			depAddress=$("#demo1").val();
		}
		var This=$(this).children();
		if(This.hasClass('action')){
			$('#lcno').removeAttr('readonly');
			 if(depAddress!=null&&"undefined"!==typeof(depAddress)){			 
				 setDeptNoandLcnNo(depAddress,true);			 
			 }else{
				 $('#lcno').val(' ').removeAttr('readonly');	
			 }
			This.removeClass('action');
		}else{
			$('#lcno').val('*-*').attr('readonly',true);
			This.addClass('action')}
	})
});




window.onload=function(){
	 heigh();
	 width_h();
	 img_width();
	}
	
$(document).ready(function(e) {
    $(window).resize(function(e) {
		heigh();
		img_width();
	});
});


/*gundong*/
function width_h(){
	 
     $('.con4_tang6_ul2').width(ulWidth);
	 $('.con4_tang6_ul2 li').width(ulWidth);
};




function heigh(){
	var hh=$('.ul_bei').height();
	$('.ul_a li').css('line-height',hh+'px');
	$('.ul_a,ul_a li').css('height',hh+'px');
	
}


function width_h(){
    $('.texta').focus(function(){
		
		$(this).next('.tang8_p1').hide();
	});
	$('.texta').blur(function(){
		if($(this).val().length==0) 
		{
			$(this).next('.tang8_p1').show();
			}
		
	});
	
}

function img_width(){
	var hr=$(window).height();
	var hrr=$('.bei').height();
	if(hr>hrr)
	{
	 $('.bei').height(hr);
	}
    
};




