<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String checkState = session.getAttribute("checkOrderState")==null?"":session.getAttribute("checkOrderState").toString();
String chexianId = request.getParameter("chexianId")==null?"":request.getParameter("chexianId"); 
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
     <script type="text/javascript">
    var chexianId = "<%=chexianId %>";
    if(chexianId=="null"){
		chexianId="";
	}   
    </script>
</head>
<body>
<div id="My_order_center">
	<input type="hidden" id="tabFlag" value=""/>
	<input type="hidden" id="chexianId" value=""/>
	<input type="hidden" id="showId" value=""/>
    <div class="Header_me">
        <ul>
           <c:if test="${loginUser.usertype!=3}">
           <li style=""><img src="<%=path%>/views/quicksure/images/indexa_2_2.png" style='margin: 0 6px 0 15px; vertical-align:middle;'/>账号:<span class="user_Name"><a href="<%=path%>/views/quicksure/jsp/quicksurehome.jsp" style="text-decoration:none;">&nbsp;&nbsp;${loginUser.username}</a></span>
           </li>
           </c:if>
            <li style="float: right;"><button id="feicheID" style="margin-right: 4%;margin-top: 8%;width: 80px;float:right;background-color: rgb(250, 14, 43);height: 30px;border-radius: 5px;color: white;border: none;display:none;">非车订单</button> </li>
        </ul>
    </div>
    <div class="Check_myOrder">
        <ul>
            <li class='left'><h3 style='text-align:left;font-size:15px;color: #333;' id="MyOrder_box"><img src="<%=path%>/views/quicksure/images/indexa_4.png" style='vertical-align:middle; width: 18px;
    margin: 0 8px 0 15px; '/>我的订单</h3></li>
            <li class='right'>
             <div class='seach_box'>
			   <input type="text" value='' placeholder="车主名或车牌号" id="search_content" onkeyup="Trim(this);"/>
			   <span class='search'></span>
			 </div>
			</li>
        </ul>
    </div>
    <ul>
         <li style='float:left;width:100%;padding: 10px 0;'>
           <div class="Order_list">
             <a href="#"data-mode='30' data-stop='40'>待支付(<span class="wait_pay"></span>)<b></b></a>
             <a href="#"data-mode='50' data-stop='60' data-type='70'>已支付(<span class="already_pay"></span>)<b></b></a>
             <a href="#"data-mode='10' data-stop='20'>暂存(<span class="Stop_keep"></span>)<b></b></a>
             <a href="#"data-mode='80'>已关闭(<span class="On"></span>)</a>
          </div>
         </li>
      </ul> 
    <div id="Order_information">
       
    </div>
    <ul id="pager"style='display:block;'>
    </ul>
    <!-- 非车和车险订单按钮选择 -->
		<div class="chooseMyAccount" style="display:none">
				<div style="   position: fixed;top: 0px; max-width:640px;height:100%;text-align:center;width:100%;">
					<div style="margin-top: 300px;width:85%;max-width:64%;margin:0 auto;background:#fff;position: absolute;left: 12%;top: 20%;color: #333; border-radius:5px;padding: 33px 30px 15px 30px;box-shadow: 0 0 20px 2px #333;">						
							<span id="jqapplicationno"  style="height: 17px;width:100%;text-align:center;font-size:20px;font-weight: 600;color: red;padding-bottom: 7px;" >请选择非车险或车险订单</span>	 							
							<div style='font-size:17px;color:#333;padding: 12px 0px;text-align:center;'><button class="ensure" id="chexian">车险订单</button>&nbsp;&nbsp;<button class="ensure" id="feiche">非车订单</button></div>  		
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

</body>
<script>
	var urlpath = "<%=path%>";
</script>

<script src="<%=path%>/views/quicksure/scripts/jquery.1.7.2.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script src="<%=path%>/views/quicksure/scripts/myAccount.js"></script>
<script src="<%=path%>/views/quicksure/scripts/layer.js"></script> 
<script>
	<%if(checkState.equals("10")||checkState.equals("20")){%>
			$(".Order_list a").eq(2).addClass('act');
			$(".Order_list a").eq(0).removeClass('act');
	<%}%>;	
</script>
</html>