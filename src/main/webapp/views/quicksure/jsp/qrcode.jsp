<%@ page language="java" import="java.util.*,com.quicksure.mobile.utility.*,com.quicksure.mobile.entity.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	Userinfor userinfor=null;
	if(session.getAttribute("loginUser")!=null){
	  userinfor=(Userinfor)session.getAttribute("loginUser");
	}
	int userId = userinfor!=null?userinfor.getUserid():0;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible"; content="IE=edge">
    <meta name="screen-orientation" content="portrait">
    <meta name="x5-orientation" content="portrait">
    <meta name="full-screen" content="yes">
    <meta name="x5-fullscreen" content="true">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">
    <link type="text/css" rel="stylesheet" href="<%=path%>/views/quicksure/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/css.css">
    <link rel="stylesheet" href="<%=path%>/views/quicksure/css/LArea.css">
    <title>分享二维码！</title>
    <script type="text/javascript">
    var userId ="<%=userId%>";
    function getUrl() {
		return "<%=path%>";
  	}
    if(userId==null){
       userId=0;
    }
    </script>
  </head> 
 <body style="font-size:100%;">
 <form action="">
 <div class="top">
	      <div class="div600">
		      <table style='width:100%;background: rgba(255,255,255,0.5);'>
		         <tr>
		           <td class='top_tr1'><img src="<%=path%>/views/quicksure/images/index_logo.png" style='max-width:100%;'></td>
		         </tr>
		      </table>
	     </div>
	   </div>
  <div style="padding-top: 27%;">
    <div style="margin-left:27%;margin-bottom:40px">长按此处分享二维码<img style="margin-left:12px;width:40px;" src="<%=path%>/views/quicksure/images/gt002.gif"></div>
	<center><img style="width:200px;height:200px;margin-bottom:20px"id="qrcode" src="data:image/jpeg;base64,${result}"/></center>
	<div style="margin-left:41%;">
	    <a style="text-decoration:none;" href="" id="path"><b style="color: red;">返回首页</b></a>
	</div>
</div>
<input id="url" value="${strUrl}" style="display:none;"/>
<input id="isagentshare" value="${isagentshare}" style="display:none;"/>
</form>
</body>
<script src="<%=path%>/views/quicksure/scripts/quicksure/jquery-1.9.1.min.js"></script>
<!-- <script type="text/javascript">
    $(document).ready(function(){
	      //var strUrl="http://172.16.55.108:8088/ludiquickSureMobileServer/";
	      //var strUrl="http://test.quicksure.cn:8000/ludiquickSureMobileServer/"; //测试环境地址
	      var strUrl="http://m.quicksure.com/ludiquickSureMobileServer/";//生产环境地址
		  $.ajax({
			url: getUrl()+"/loginUser/parseQRCode.do?userId="+userId+"&strUrl="+strUrl,
			type:'post',
			success : function(data) {
			    $('#qrcode').attr('src',"data:image/jpeg;base64,"+data);
			    //注意：img 的src前面需要拼接data:image/jpg;base64, 在加上后台生成的二进制才能正确解析二进制
			}
		 })
    });
</script> -->
<script type="text/javascript">
//初始化赋值返回首页的链接
$(document).ready(function(){
	var url=$('#url').val();
	var isagentshare=$('#isagentshare').val();
    document.getElementById("path").href=url+"?isagentshare="+isagentshare;
})
</script>
</html>
