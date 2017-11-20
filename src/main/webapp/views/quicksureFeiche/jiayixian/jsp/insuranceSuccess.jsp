<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-store, must-revalidate">
    <meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
    <meta http-equiv="expires" content="0">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>支付详情页面</title>
</head>
<style>
    @charset "utf-8";
    * {
        font-family: microsoft yahei, 微软雅黑, Arial;
        font-weight:normal;
    }
    body {margin:0px; padding:0px; font-size:12px; font-family: microsoft yahei, 微软雅黑, Arial; background:#FFF;font-weight:normal;-webkit-tap-highlight-color:rgba(0,0,0,0);}
    ol,ul,li,dl,dt,dd,form,p,h1,h2,h3,h4,h5,img{ margin:0px; padding:0px;}
    ol,ul,li{ list-style:none; padding:0; margin:0;}
    img{ border:none;}
    a{text-decoration:none; border:none; margin:0; padding:0;}
    a:active{
        -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    }
    a:hover{
        text-decoration:none;
        outline:none;outline:0;
         -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    }

    a:focus{
        text-decoration:none;
        outline:none;outline:0;
         -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    }
    table{border:1px solid #ebebeb ;border-spacing:0px;width:100%;margin-top: 20px;text-align: left;}
    a:visited{
        text-decoration:none;
        outline:none;outline:0;
         -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    }
    .top_tr1{width:32%;padding-left: 4%}
    .top_tr1 img{max-width: 77%;
        margin-top: 1px;
    }
    .content_box{
        max-width: 640px;
        min-width: 320px;
        min-height: 320px;
        margin: 0 auto;
        position: relative;
        border: 1px solid #eee;
        padding-bottom:20px;
    }
    .content_box>img{width:100%;}
    .content {
        width: 96%;
        margin: 0 auto;
        margin-top: 30px;
        text-align: center;
    }

    .logo {
        margin: 3% 0;
    }
    .con8_p1 {
        font-size: 16px;
        color: #333333;
        line-height: 2em;
    }
    .tr_1>td{border-bottom:1px solid #ddd;}
    .success_cont{width:100%;}
    .success_cont td{padding:8px 0 8px 8px;}
    .btn{width:100%;margin-top:30px;}
    .btn>a{display:inline-block;margin:0 auto;font-size: 16px;width:92%;height:42px;line-height:42px;text-align:center;color:#333;letter-spacing: 2px;background-color: #f6f6f6;}
    .btn>a:hover{background:#f63c30;color:#fff;}
    .success-box{color:rgb(220,108,32);font-weight:500;font-size:18px;letter-spacing: 2px;}
    .success-box img{vertical-align: middle;}
</style>
<body>
<div class="content_box">
    <div class="content">
        <div class="logo"><img src="<%=path %>/views/quicksureFeiche/jiayixian/images/index8_2.png" alt=""></div>
        <p class="con8_p1">您的爱车已成功投保，电话客服将会在一个工作日内与您联系，感谢您选择慧英保险</p>
        <div class="success-box"><img src="<%=path%>/views/quicksureFeiche/jiayixian/images/success-icon.png" alt="">您已支付成功</div>
        <div class="btn"><a href="">返回首页</a></div>
    </div>
</div>
</body>
</html>
