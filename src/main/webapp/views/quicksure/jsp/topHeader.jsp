<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.quicksure.mobile.entity.Userinfor;"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
HttpSession ssion = request.getSession();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<style type="text/css">
  		.topHeader{border:none;
		    position: absolute;
		    left: 80%;
		    top: 20%;
		    width: 400px;
		    margin-left: -50px;
		    margin-top: -90px;
		   }
  	</style>
  </head> 
  <body style="font-size:100%;">
  <div class="topHeader">
		 <%
		 Userinfor user = (Userinfor) ssion.getAttribute("loginUser");
		 if(user==null){
		 %>
		 	<font style="color: green;"><a href="<%=path%>/views/quicksure/jsp/LoginUser.jsp" style="text-decoration: none;">登录</a>&nbsp;&nbsp;<a href="<%=path%>/views/quicksure/jsp/LoginUser.jsp" style="text-decoration: none;">我的订单</a></font>
		 <%
		 }else{
		 %>
		 	<font style="color: green;">欢迎你:<%=user.getUsername()%>&nbsp;&nbsp;<a href="<%=path%>/views/quicksure/jsp/personOrderCenter.jsp" style="text-decoration: none;" >我的订单</a></font>
		 <%
		 }			
	     %>
	</div>
</body>
</html>
