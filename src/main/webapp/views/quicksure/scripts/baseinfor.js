if(JQ!==null && JQ!==''){
 $('.call1').show();
}else{
 $('.call1').hide();
}
    
if(SY!==null && SY!==''){
 $('.call2').show();
}else{
 $('.call2').hide();
}
  //弹出一个loading层
var ii;
$("#submit").on("click", function(){
	ii = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
});
	   
var error = "C99999999";
var error2="E00000030";
if(error==errorCode || error2== errorCode){
   $("#Message").html(errorMessage);
   $('.max').show();
}else{
   layer.close(ii);
   $('.max').hide();
}

$('.btn').click(function(){
   $('.max').hide();
});

$('.tips_content button').click(function(){$('.tips_box').hide();});
function check(){
    var Cheko=$('.Clause>input');
    console.log(Cheko);
    if(Cheko.attr('checked')==null){$('.tips_box').show();return false;}else{return true;}
}
	
$('html').ready(function() {
	$(".lf_num").val(parseFloat(sypremium));
	$(".mid_num").val(parseFloat(jqpremium));	
	$(".rg_num").val(parseFloat(taxpremium));
	$("#total_num").val(parseFloat(taxpremium)+parseFloat(jqpremium)+parseFloat(taxpremium));
});
	
function goback(){
	window.location.href = getUrl()+"/PremiumCount/backToPersonInfor.do?orderNo="+orderNo+"";
}
