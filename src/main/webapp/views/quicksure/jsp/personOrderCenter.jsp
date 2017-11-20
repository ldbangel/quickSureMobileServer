<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String checkState = session.getAttribute("checkOrderState")==null?"":session.getAttribute("checkOrderState").toString();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
   	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="screen-orientation" content="portrait">
    <title>订单管理</title>
    <link rel="stylesheet" href="<%=path%>/views/quicksure/css/My_order_center.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/views/quicksure/css/style.css">
    <script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
</head>
<body>
<div id="My_order_center">
    <div class="Header_me">
        <ul>
           <c:if test="${loginUser.usertype!=3}"> <li>账号:<span class="user_Name"><a href="<%=path%>/views/quicksure/jsp/quicksurehome.jsp" style="text-decoration:none;">&nbsp;&nbsp;${loginUser.username}</a></span></li></c:if>
            <li><a href="#"><b></b></a></li>
        </ul>
    </div>
    <div class="Check_myOrder">
        <ul>
            <li class='left'><h3><button id="MyOrder_box">我的订单</button></h3></li>
            <li class='right'>
             <div class='seach_box'>
			   <input type="text" value='' placeholder="请输入车主姓名搜索" id="search_content"/>
			   <span class='search'></span>
			 </div>
			</li>
        </ul>
    </div>
    <div class="Order_list">
        <ul>
            <li><a href="#" class="act"data-mode='30' data-stop='40'>待支付(<span class="wait_pay"></span>)</a>
            <a href="#"data-mode='50' data-stop='60' data-type='70'>已支付(<span class="already_pay"></span>)</a>
            <a href="#"data-mode='10' data-stop='20'>暂存(<span class="Stop_keep"></span>)</a>
           <a href="#"data-mode='80'>已关闭(<span class="On"></span>)</a></li>
        </ul>
    </div>
    <div id="Order_information">
       
    </div>
    <ul id="pager">

    </ul>
</div>
<div class="errorhei" style="display:none">
	<div class="errortan">
		<p class="errortan1">信息提示</p>
		<p class="errortan2"><div id="Message" style="font-size: 17px;"></div></p>
		<a class="errortan3" href="javascript:void(0);">确 定</a>
	</div>
</div>
</body>
<script>
	var urlpath = "<%=path%>";
</script>

<script src="<%=path%>/views/quicksure/scripts/jquery.1.7.2.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script src="<%=path%>/views/quicksure/scripts/personOrderCenter.js"></script>
<script src="<%=path%>/views/quicksure/scripts/layer.js"></script> 
<script>
	<%if(checkState.equals("10")||checkState.equals("20")){%>
			$(".Order_list a").eq(2).addClass('act');
			$(".Order_list a").eq(0).removeClass('act');
		/*  $(document).ready(function(){
			$(".Order_list a").eq(2).addClass('act');
			$(".Order_list a").eq(0).removeClass('act');
		}); */
	<%}%>;	
</script>
</html>