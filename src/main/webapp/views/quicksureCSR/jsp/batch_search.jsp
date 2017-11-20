<%@ page language="java" import="java.util.*,com.quicksure.mobile.entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

 Userinfor userinfor=null;
if(session.getAttribute("loginUser")!=null){
  userinfor=(Userinfor)session.getAttribute("loginUser"); 
}
int agentFlag=userinfor!=null?userinfor.getAgentFlag():0;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>车辆批量查询</title>
    <link rel="stylesheet" href="<%=path%>/views/quicksureCSR/css/batch_search.css">
    <link rel="stylesheet" href="<%=path%>/views/quicksureCSR/css/optiscroll.css">
    <link rel="stylesheet" href="<%=path%>/views/quicksureCSR/css/datePicker.css" type="text/css" media="all">
    <script type="text/javascript">
    	var agentFlag = "<%=agentFlag %>";
    </script>
</head>
<body>
<div style="width:1300px;margin:0 auto;">
    <form action="" method="" id="form">
        <div class="search_content">
            <table>
                <tr>
                    <td>录入日期<input type="text" name="begin_date" value="" id="datePicker1" placeholder="--请输入录入日期起期--" style='width:200px' class="date_picker">
                    		 <input id="datePicker2" style='width:200px' class="date_picker" placeholder="--请输入录入日期止期--" type="text" name='end_date' value=""> 
                    </td>
                    <td>行驶区域
                    	<select name="province" id="Travel_Province" value="" onchange="provinceChange(this.id,this.value)" >
		                </select>
		                <select name="city" id="Travel_City" value="" onchange="cityChange(this.id,this.value)" >
		                </select>
		                <select name="composed" id="Travel_County" value="" onchange="townChange(this.id,this.value)" >
		                </select>
                    </td>
                    <input type="hidden" id="deptCode" value="">
                </tr>
                <tr>
                	<td>&nbsp;&nbsp;&nbsp;订单号<input id="orderno" type="text" value="" placeholder="请输入订单号"></td>
                	<td>车主姓名<input id="drvowner" type="text" value="" placeholder="请输入车主姓名"></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;车牌号<input id="lcnno" type="text" value="" placeholder="请输入车牌号"></td>
                   
                    <td>订单状态
                        <select id="orderstate" name="orderstate" style='width:216px'>
                         <option value="0">-请选择-----</option>
                         <option value="1">待支付</option>
                         <option value="2">已支付</option>
                         <option value="3">暂存</option>
                         <option value="4">已撤销</option>
                        </select>
                    </td>
                </tr>
            </table>
            <div class="btn">
        <span><a href="javascript:submitForm();" class="Search_btn"><img src="<%=path%>/views/quicksureCSR/images/search.png">搜索</a><i></i>
        <a href="javascript:clearForm();" class="Clear_btn"><img src="<%=path%>/views/quicksureCSR/images/clean.png">清除</a></span>
            </div>
        </div>
         
                 <%-- 用户名：<input id="username" type="text" value="${userinfo.username}" style="display：none"/> --%>
    </form>
    <input type="hidden" id="policyCount" value="${policyCount}"/>
    <div class="product_content">
        <div id="Table_list" style='overflow:hidden;'>
            <div class="m-wrapper" id="m-wrapper">
		            <table id="table1" width="100%" border="0" cellspacing="0" cellpadding="0">
		                <tr id="tab1" class="titlebg">
		                    <td><span><pre>车主姓名</pre></span></td>
		                    <td><span><pre>订单号</pre></span></td>
		                    <td><span><pre>车牌</pre></span></td>
		                    <td><span><pre>交强投保单号</pre></span></td>
		                    <td><span><pre>交强保单号</pre></span></td>
		                    <td><span><pre>商业投保单号</pre></span></td>
		                    <td><span><pre>商业保单号</pre></span></td>
		                    <td><span><pre>总保费</pre></span></td>
		                    <td><span><pre>状态</pre></span></td>
		                    <td><span><pre>最后操作时间</pre></span></td>
		                    <!-- <td><span>信息提示</span></td> -->
		                </tr>
		                <tbody id="tb_content">
		                	
		                </tbody>
		            </table>
		      </div>
        </div>
        <div>
      		<ul id="pager">
      		
			</ul>
        </div>
        <div class="foot_btn">
            <button type="button" class="allow" id="requestInterface" style="display:none" >调用接口</button>
        	<button type="button" class="allow" id="cancel" style="display:none" >撤销投保单</button>
            <button type="button" class="allow ban" disabled=true onclick="orderDetails();">订单详情</button>
            <button type="button" class="allow ban" disabled=true onclick="continuePayment();">继续支付</button>
            <button type="button" class="allow ban" disabled=true onclick="continueInsure();">继续投保</button>
            <button type="button" class="allow ban" disabled=true onclick="cancelOrder();">撤销</button>
        	<button type="button" class="allow" onclick="immediateInsure();">立即投保</button>
        	<button type="button" class="allow" onclick="exportExcel();">导出Excel</button>
        </div>
    </div>
</div>

<div class="errorhei" style="display:none">
		<div style="   position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
			<div style="width:85%;max-width:300px;margin:0 auto;background:#fff;max-width:300px;color: #333; border-radius:5px;padding: 33px 30px 15px 30px;box-shadow: 0 0 20px 2px #333;">
					<input id="syapplicationno" name="syapplicationno" style="margin-bottom: 5px;height: 17px;" value="" placeholder="请输入商业险投保单"/>
					<input id="jqapplicationno" name="jqapplicationno" style="height: 17px;" value="" placeholder="请输入交强险投保单"/>					
					
					<div style='font-size:17px;color:#333;padding: 12px 0px;text-align:center;'><button class="ensure" id="ensure2" onclick="cancelInsurancePolicy();">确定撤销</button>&nbsp;&nbsp;<button class="ensure" id="ensure1">取消</button></div>  
		
			</div>
	 </div> 
	</div>
</div>
<div class="requeInterface" style="display:none">
		<div style="   position: fixed;top: 5%; width:100%;height:auto;text-align:center;">
		    
			<div style="width:85%;margin:0 auto;background:#fff;color: #333; border-radius:5px;padding: 33px 30px 15px 30px;box-shadow: 0 0 20px 2px #333;height:400px;overflow-y:scroll;">
			 <div style='height:50px;'><button class="ensure" id="close" style="float:right;width:75px;font-weight: 800;font-size: 12px;">关闭</button></div>
						  <div>url:<input type="text" name="txtURL" size="99" style="width:400px;"value="" id="url">  
						     <select style="float: right;" id="selectInter" name="interfacevalue">
						          <option value="0">==请选择==</option>
						          <option value="1">调用华安</option>
						          <option value="2">调用雷达</option>
						     </select>
						  </div>   
							<textarea name="requestxml" cols="100" rows="15" id="requestxmlT"></textarea>		
							<div style='font-size:17px;color:#333;padding: 12px 0px;text-align:center;'><button class="ensure" style="margin-left: -113px;width: 16%;font-size: larger;height: 38px;font-weight: 600;" id="ensureInterface" onclick="getresponsexml();">点击确认</button>&nbsp;&nbsp;</div> 
							<div style="display:none;position: relative;margin-right: 117px;" id="textareTwo"><textarea id="responsexml" cols="100" rows="15"></textarea></div> 		        
		 </div>
	 </div> 
</div>


<div class="errorhei1" style="display:none">
		<div style="   position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
			<div style="width:85%;margin:0 auto;background:#fff;color: #333;max-width:300px; border-radius:5px;padding: 20px 0px 3px 0px;box-shadow: 0 0 20px 2px #333;">
					<h2 style='font-size: 17px;color: #222;font-weight: normal;'>提示信息</h2>
					<p style='margin-bottom: 10px;font-size: 12px;padding: 15px 20px;text-align: center; color:#444;'id="Message"></p>
					<!-- <div style='border-top:1px solid #ddd;font-size:17px;color:#333;padding: 8px 0px;'><span style='display:block;width:100%;font-size:15px' id="ensure">确定</span></div> -->
					<div style='border-top:1px solid #ddd;font-size:17px;color:#333;padding: 8px 0px;'><button style='display:block;width:30%;font-size:15px;margin-left: 32%;height: 32px;background-color: antiquewhite;' id="ensure" >确定</button></div>
			</div>
	 </div> 
	<!-- </div> -->
</div>
<!-- 加载框 -->
<div style="position:fixed;top:0px;left:0px;width:100%;height:100%;z-index: 100;display:none;"id="pop">
<div style="position: fixed;top:35%; width:100%;height:auto;text-align:center;">
     <span style='display:inline-block;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 10px;letter-spacing: 2px; padding: 20px;'>
        <img  src="<%=path%>/views/quicksureCSR/images/mu_loading.gif" style='width: 36px; margin-bottom: 5px;'>
        <p id="prompt"></p>
	</span>
	 
</div>
</div>

</body>
<script src="<%=path%>/views/quicksureCSR/js/jquery.1.7.2.min.js"></script>
<script src="<%=path%>/views/quicksureCSR/js/jquery.date_input.pack.js"></script>
<script src="<%=path%>/views/quicksureCSR/js/common.js"></script>
<script src="<%=path%>/views/quicksureCSR/js/batch_search.js"></script>
<script src="<%=path%>/views/quicksureCSR/js/optiscroll.js"></script>
<script>
var wr = new Optiscroll(document.getElementById('m-wrapper'), { forceScrollbars: true }); 
    $('#datePicker1').date_input();
    $('#datePicker2').date_input();
    $('.Clear_btn').click(function(){
    	$('table input').val(' ');
    });
    $('#ensure').click(function(){
    $('.errorhei1').hide();
    if($("#syapplicationno").val()==""&&$("#jqapplicationno").val()==""){
    	$(".errorhei").show();
    }
    }) ;
</script>
</html>