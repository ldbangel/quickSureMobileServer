<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String showfcId = request.getParameter("showfcId")==null?"":request.getParameter("showfcId"); 
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="screen-orientation" content="portrait">
    <title>非车订单管理</title>
    <link rel="stylesheet" href="<%=path%>/views/quicksureFeiche/jiayixian/css/myAccount.css">
    <script type="text/javascript">
    var showfcId = "<%=showfcId %>";
    if(showfcId=="null"){
		showfcId="";
	}   
    </script>
</head>
<body>
<div class="My_order_box">
	<input type="hidden" id="tabFlag" value=""/>
	<input type="hidden" id="feicheshowId" value=""/>
	<input type="hidden" id="showfcId" value=""/>
    <div class="Header_title"><!-- <img src="../images/indexa_2_2.png" alt=""> -->
		<ul>
	       <c:if test="${loginUser.usertype!=3}">
	        <li style="float: left;"><img src="../images/indexa_2_2.png" style='margin: 0 6px 0 15px; vertical-align:middle;'/>账号:<span class="user_Name"><a href="" style="text-decoration:none;">&nbsp;&nbsp;${loginUser.username}</a></span>
	       </li>
	       </c:if> 
	       <li style="float: right;"><button id="chexianChoose" style="display:none;font-weight: 500;margin-right: 4%;margin-top: 6px;float: right;width: 80px;height: 30px;color: white;border: none;border-radius: 5px;background-color: rgb(237, 20, 72);">车险订单</button></li>
	    </ul>
    </div>
    <div class="Check_myOrder">
	    <table>
	        <tr>
	            <td><img src="<%=path %>/views/quicksure/images/indexa_4.png" alt="">我的订单</td>
	            <td><div id="search_box"class="search_box"><input type="text"><img src="../images/search.png" alt=""></div></td>
	        </tr>
	    </table>
    </div>
    <div class="product_title_box">
        <div>
            <ul class="title_list">
                <li>待支付(<span class="wait_pay"></span>)<i></i></li>
                <li>已支付(<span class="already_pay"></span>)<i></i></li>
                <li>暂存(<span class="stop_keep"></span>)<i></i></li>
                <li>已关闭(<span class="closed"></span>)</li>
            </ul>
        </div>
    </div>
    <!-- 展示信息 -->
    <div id="Order_information"></div>
    <!-- 分页栏 -->
    <ul id="pager" class="pager" style="display:block;"></ul>
    <!-- 非车和车险订单按钮选择 -->
    <div class="chooseMyAccount" style="display:none">
		<div style="   position: fixed;top: 0px; max-width:640px;height:100%;text-align:center;width:100%;">
			<div style="margin-top: 300px;width:85%;max-width:64%;margin:0 auto;background:#fff;position: absolute;left: 12%;top: 20%;color: #333; border-radius:5px;padding: 33px 30px 15px 30px;box-shadow: 0 0 20px 2px #333;">						
					<span id="jqapplicationno"  style="height: 24px;width:100%;text-align:center;font-size:20px;font-weight: 600;color: red;padding-bottom: 3px;">请选择非车险或车险订单</span>		 							
					<div style='font-size:17px;color:#333;padding: 12px 0px;text-align:center;'><button class="ensure" id="feiche">非车订单</button>&nbsp;&nbsp;<button class="ensure" id="chexian">车险订单</button></div>  		
			</div>
		</div> 
    </div>
</div>
<div class="errorhei" style="display:none">
	<div class="errortan">
		<p class="errortan1">信息提示<a class="errortan3" href="javascript:void(0);"><img src="<%=path%>/views/quicksure/images/Clear.png"/></a></p>
		<p class="errortan2"><div id="Message" style="font-size: 17px; margin-top: 55px;"></div></p>
	</div>
</div>

<div style="position:fixed;top:0px;left:0px;width:100%;height:100%;z-index: 100;display:none;"id="pop">
	<div style="position: fixed;top:35%; width:100%;height:auto;text-align:center;">
	    <span style='display:inline-block;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 10px;letter-spacing: 2px; padding: 20px;'>
		    <img  src="<%=path%>/views/quicksure/images/mu_loading.gif" style='width: 36px; margin-bottom: 5px;'>
		    <p id="prompt"></p>
		</span>
	</div>
</div>
<!-- 非车和车险订单按钮选择 -->

</body>
<script type="text/javascript">
	var path = "<%=path%>";
</script>
<script src="<%=path%>/views/quicksureFeiche/jiayixian/js/jquery.1.7.2.min.js"></script>
<script src="<%=path%>/views/quicksureFeiche/jiayixian/js/myAccount.js"></script>
</html>
