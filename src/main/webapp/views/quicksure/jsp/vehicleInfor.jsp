<%@ page language="java" import="java.util.*,com.quicksure.mobile.utility.*,com.quicksure.mobile.entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
 Userinfor userinfor=null;
if(session.getAttribute("loginUser")!=null){
  userinfor=(Userinfor)session.getAttribute("loginUser");
}
int agentFlag=userinfor!=null?userinfor.getAgentFlag():0;
request.setAttribute("agentFlag",agentFlag); 
String nowDate=DateFormatUtils.getSystemDateByYYYYMMDDHHMMSSSSS();
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" />
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="format-detection" content="telephone=no"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<title>基本信息</title>
<link type="text/css" rel="stylesheet" href="<%=path%>/views/quicksure/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/optiscroll.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/normalize.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/htmleaf-demo.css">
<link href="<%=path%>/views/quicksure/css/mobiscroll_date.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/Baidustatistics.js"></script>
<script type="text/javascript" src=" http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/quicksure/swiper-3.4.0.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/views/quicksure/scripts/quicksure/js.js"></script>
 <script src="<%=path%>/views/quicksure/scripts/optiscroll.js"></script>
<%-- <script src="<%=path%>/views/quicksure/scripts/quicksure/jquery.min.js"></script>  --%>
<script  src="<%=path%>/views/quicksure/scripts/quicksure/mobiscroll_date.js" charset="UTF-8"></script> 
<script src="<%=path%>/views/quicksure/scripts/quicksure/mobiscroll.js"></script> 
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script src="<%=path%>/views/quicksure/scripts/date.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="<%=path%>/views/quicksure/scripts/wx_linkshare.js"></script>
<script type="text/javascript">
	var shareId=0;
</script>

<style type="text/css">
	.errorpop{display:none;font-size: 1rem;
		padding-left: 4%;
		margin-top: 5px;
	}
	.errorpop1{
		display:none;
		font-size: 0.7rem;
		margin-top: 1px;
		padding-left: 27%;
  }
</style>
</head>

<body>
<div class="div640">
    <div class="div600 index7">
	    <div class="ul_bei" style='margin-bottom:15px;'>
	     <img src="<%=path%>/views/quicksure/images/vehicle.png">
	    	<%-- <img src="<%=path%>/views/quicksure/images/vehicle.png"> --%>
		    <ul class="ul_a">
		    
		      <li class="on"><a href="javascript:void(0);">基本信息</a></li>
		      <li><a href="javascript:void(0);">报价信息</a></li>
		      <li><a href="javascript:void(0);">确认信息</a></li>
		      <li><a href="javascript:void(0);">支付</a></li>
		     
		    </ul>
		   
		     <div class="clear"></div>
	    </div>
     	
      <p class="index7_p1" style="display:none">请准备好行驶证，对照补充车辆和车主信息，以便为您精准报价</p>
	    <div class="content7 boder1">
	       <p class="con7_p1"><img src="<%=path%>/views/quicksure/images/index_2.png">车辆信息<a class="fr" id="tanga" href="javascript:void(0);">找到车辆信息>></a></p>
	       <form id="uploadForm">
	        <div id="photos">
		       	<div class="con7_p2">点相机图标对行驶证拍照，可自动识别车辆信息
		       	<p style="display:inline-block;position: relative;    width: 30px;height: 30px; float:right;">
		       		<input type="file" id="picFile" name="picFile" accept="image.jpg" capture="camera" 
							onchange="readFile(this)" style="display:none;" />
		       		 <span class="fr">
		       			<img src="<%=path%>/views/quicksure/images/camera.gif" onclick="Prompt();">
		       		</span>
		       	</p>
		       	</div>
	       	</div>
	       </form>
	       <%-- <p class="con7_p2">信息太多懒得填写？对行驶证拍照后自动识别车辆信息<span class="fr"><img src="<%=path%>/views/quicksure/images/index_3.png"></span></p> --%>
	       <form id="form1" action="<%=path%>/vehicleInfor/vehicleSumbit.do" method="post">
	       	 <input type="hidden" id="orderNo" name="orderNo" value="${insuranceDetailsVO.baseinfor.orderno}" readonly="readonly"/>
		     <input style="display:none" type="text" id="brandcode" name="brandcode" value="${insuranceDetailsVO.vhlinfor.brandcode}" /> 
			 <input style="display:none" type="text" id="vhlval" name="vhlval" value="${insuranceDetailsVO.vhlinfor.vhlval}" />
			 <input style="display:none" type="text" id="setno" name="setno" value="${insuranceDetailsVO.vhlinfor.setno}" /> 
			 <input style="display:none" type="text" id="displacement"name="displacement" value="${insuranceDetailsVO.vhlinfor.displacement}" />
			 <input style="display:none" type="text" id="marketyear" name="marketyear" value="${insuranceDetailsVO.vhlinfor.marketyear}" />
			 <input style="display:none" type="text" id="brandName" name="brandName" value="${insuranceDetailsVO.vhlinfor.brandName}" />
			 <input style="display:none" type="text" id="citycode" name="citycode" value="${insuranceDetailsVO.baseinfor.deptno}" /> 
			 <input style="display:none" type="text" id="familyKind" name="familyKind" value="${insuranceDetailsVO.vhlinfor.familyKind}" /> 
			 <input style="display:none" type="text" id="newenergyflag" name="newenergyflag" value="${insuranceDetailsVO.vhlinfor.newenergyflag}" /> 
			 <input style="display:none" type="text" id="absflag" name="absflag" value="${insuranceDetailsVO.vhlinfor.absflag}" /> 
			 <input style="display:none" type="text" id="alarmflag" name="alarmflag" value="${insuranceDetailsVO.vhlinfor.alarmflag}" /> 
			 <input style="display:none" type="text" id="airbagfalg" name="airbagfalg" value="${insuranceDetailsVO.vhlinfor.airbagfalg}" /> 
			 <input style="display:none" type="text" id="gearboxtype" name="gearboxtype" value="${insuranceDetailsVO.vhlinfor.gearboxtype}" />
			 <input style="display:none" type="text" id="fullweight" name="fullweight" value="${insuranceDetailsVO.vhlinfor.fullweight}" />    
	       <div class="con7_div">
	         <div class="con7_diva">
	            <p class="con_p3"><font>*</font>车牌号码</p>
	            <input class="text1" type="text" id="lcno" name="lcnno" value="${insuranceDetailsVO.vhlinfor.lcnno}"
	            	required="required" maxlength="7" oninput="if(value.length>7)value=value.slice(0,7)" required ='required'/>
	           <!--  <div id ="errorMessageforLcnNo" class='errorpop'><lable style="color:red;font-size: 13px;">车牌号输入有误！<lable></div> -->
	            <div class="clear"></div>
	         </div>
	         <div class="con7_diva">
	            <p class="con_p3"><font>*</font>注册日期</p>
	             <input type="text" class="input text1 mnbv" id="registerdate" name="registerdate" value="${insuranceDetailsVO.vhlinfor.registerdate}"
	             	readonly="readonly" placeholder="请输入车辆注册日期"/>
	            <div class="clear"></div>
	         </div>
	         <div class="con7_diva">
	            <p class="con_p3"><font>*</font>车架号VIN</p>
	            <div style='diaplay:inline-block;position:relative;'> 
	            <input class="text1" style="text-transform: uppercase;" type="text" id="vinno" name="vinno" value="${insuranceDetailsVO.vhlinfor.vinno}" placeholder="请输入车架号" 
	            	required ="required" onblur="Trim(this);setUpperCase(this);" pattern="^[A-Z]{1}[0-9]{16}$" maxlength="17" onkeyup="if(value!=value.replace(/[\W]/g,''))value=value.replace(/[\W]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
	            <!-- <div id="vincode1" class="errorpop"><lable style="color:red;font-size: 13px;">车架号必须是17位<lable></div>
	            <div id="vincode2" class="errorpop"><lable style="color:red;font-size: 13px;">车架号不能有O,Q,I,*字符<lable></div>
	            <div id="vincode3" class="errorpop"><lable style="color:red;font-size: 13px;">车架号只能是英文和数字组合<lable></div>	 -->		
	             <img src="<%=path%>/views/quicksure/images/xx.png"  class='Clear_Val' style='display:none;position:relative;top:4px;right:25px;'>
	            </div>
	            <div class="clear"></div>
	         </div>
	         <div class="con7_diva">
	            <p class="con_p3"><font>*</font>品牌型号</p>
	              <div style='diaplay:inline-block;position:relative;'> 
			            <input class="text1" type="text" id="model" name="model" value="${insuranceDetailsVO.vhlinfor.model}" placeholder="请输入品牌型号 "
			            	required ="required" onblur="Trim(this);setUpperCase(this);"/>
			            <%-- <p class="con7_p4" id="tang2"><img src="<%=path%>/views/quicksure/images/index_5.png">搜不到型号</p>--%>
			            <img src="<%=path%>/views/quicksure/images/xx.png"  class='Clear_Val' style='display:none;position:relative;top:4px;right:25px;'>
		            </div>
	            <div class="clear"></div>
	         	<!-- <div id ="errorMessageforModel" class='errorpop1'><lable style="color:red;">品牌型号不能为空！<lable></div> -->
	         </div>
	         <div class="con7_diva">
	            <p class="con_p3"><font></font>配置型号</p>
	            <div class="textarea1" id="model1" name="model1">
	              <input type='text' value=''  class='Inpput_I' readonly/>
	              <input type='text' value='' placeholder="点击右侧按钮获取配置型号" class='Inpput_II' readonly/>
	              <input type='text' value='' class='Inpput_III' readonly/>
	           </div> 
	             <%-- <input class="textarea1" style="overflow:hidden;" id="model1" name="model1" value="${insuranceDetailsVO.vhlinfor.model}" required ="required" placeholder="请选择配置型号"></input> --%>
	            <%--<input class="text1" type="text" id="model1" name="model1" value="${insuranceDetailsVO.vhlinfor.model}" required ="required" readonly/>--%>
	            <span id="modelSerach" name="modelSerach" class='modelSerach'>选择车型</span>
	            <div class="clear"></div>
	         </div>
	         <div class="con7_diva">
	            <p class="con_p3"><font>*</font>发动机号</p>
	            <input class="text1" style="text-transform: uppercase;" type="text" id="engno" name="engno" value="${insuranceDetailsVO.vhlinfor.engno}" placeholder="请输入发动机号" 
	            	required ="required" onblur="setUpperCase(this);" maxlength="20" onkeyup="if(value!=value.replace(/[\W]/g,''))value=value.replace(/[\W]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>
	            <div class="clear"></div>
	         </div>
	       
	         <div class="con7_diva">
	            <p class="con_p3 con_p5"><font></font>一年内是否曾过户</p>
	            <ul class="con7_ul">
	             <li><i></i><input type="radio" style="display:none" value="1" id="chgownerflag1" onclick ="getchange()" name="chgownerflag" <c:if test="${insuranceDetailsVO.vhlinfor.chgownerflag=='1'}">checked="checked"</c:if> />是</li>
	             <li class="on"><i></i><input type="radio" style="display:none" value="0" id="chgownerflag2" onclick ="getchange()" name="chgownerflag" <c:if test="${insuranceDetailsVO.vhlinfor.chgownerflag=='0' || insuranceDetailsVO.vhlinfor.chgownerflag==null}">checked="checked"</c:if> />不是</li>
	             <div class="clear"></div>
	            </ul>
	            <p class="con7_p4" id="tang3"><img src="<%=path%>/views/quicksure/images/index_5.png">什么是过户</p>
	            <div class="clear"></div>
	         </div>
	         <div class="con7_diva" id="gouhudate" style="display:none">
	          <p class="con_p3"><font>*</font>过户日期</p>
	          <input type="text" id="transferdate" name="transferdate" class="input text1 mnbv" value="${insuranceDetailsVO.vhlinfor.transferdate}" placeholder="请输入过户日期" 
	          	readonly="readonly"/>
	          <div class="clear"></div>
	         </div>
	       </div>
	  <!-- </div>
     
	  <div class="content7 content7a boder1"> -->
	 
	      <p class="con7_p1" style="border-top:3px solid #ff0000;"><img src="<%=path%>/views/quicksure/images/index_4.png">车主信息</p>
	      <div class="con7_div con7_div2">
	         <div class="con7_diva">
	            <p class="con_p3"><font>*</font>车主姓名</p>
	            <input class="text1" type="text" id="drvowner" name="drvowner" value="${insuranceDetailsVO.vhlinfor.drvowner}" placeholder="请输入车主姓名"
	            	required ="required" maxlength="4"/>
		        <!-- <div id="checkOwnerName" class="errorpop"><lable style="color:red;font-size: 13px;">名字输入有误！<lable></div>
		        <div id="checkOwnerName1" class="errorpop"><lable style="color:red;font-size: 13px;">名字不能为空！<lable></div> -->
	            <div class="clear"></div>	                 
	         </div>
	         <div class="con7_diva">
	            <p class="con_p3"><font>*</font>车主身份证</p>
	           <div style='diaplay:inline-block;position:relative;'> 
	            <input class="text1" type="text" id="certificateno" style="text-transform: uppercase;" name="certificateno" value="${insuranceDetailsVO.vhlinfor.certificateno}" placeholder="请输入车主身份证件号码"
	            	required ="required" maxlength="18" onblur="setUpperCase(this);identityCodeValid(this.value);"onkeyup="if(value!=value.replace(/[\W]/g,''))value=value.replace(/[\W]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
	            	<!-- <div id="checkIdCard" class="errorpop"><lable style="color:red;font-size: 13px;">身份证有误<lable></div> -->	            	
	             <img src="<%=path%>/views/quicksure/images/xx.png"  class='Clear_Val' style='display:none;position:relative;top:4px;right:25px;'>
	            </div>
	            <div class="clear"></div>             
	         </div>
	       <c:if test="${!empty insuranceDetailsVO.userinfor.couponCode}" var="condition" scope="request">  
	       	<div class="con7_diva">
	            <p class="con_p3"><font>*</font>优惠码</p>
	            <input class="text1" type="text" id="agentCode" name="agentCode" value="${insuranceDetailsVO.userinfor.couponCode}" placeholder="请输入优惠码" maxlength="9"
	            	required ="required" readonly/>
	            <div class="clear"></div>	                 
	         </div>
	        </c:if>
	        <c:if test="${requestScope.agentFlag!=0 && empty insuranceDetailsVO.userinfor.couponCode}" var="condition" scope="request">  
	       	 <div class="con7_diva">
	            <p class="con_p3"><font>*</font>优惠码</p>
	            <input class="text1" type="text" id="agentCode" name="agentCode" value="${insuranceDetailsVO.baseinfor.agentCode}" placeholder="请输入优惠码" maxlength="9"
	            	required ="required"/>
	            <div class="clear"></div>	                 
	         </div>
	        </c:if>
	         <div class="con7_divb">
	          <!-- <a class="con7_a1" href="javascript:goBack()" >上一步</a> -->
	          <a class="con7_a2 red"  href="javascript:submitForm()">下一步</a>
	         </div>
	         <p class="con7_p6">慧英保险采用符合业界标准的加密技术对您的个人信息进行保密</p>
	       </div>
	  </div>
  	  </form>
  	  
	</div>
    
    
	<div class="beijing">
		<div class="con4_tang2">
			<p class="close"></p>
			<p class="con4_tang3_p1">国产车或合资车请按行驶证填写，纯进口车请使用“品牌+车系”，例如，"宝马720i”</p>
		</div>
	
		<div class="con4_tang3">
			<p class="close"></p>
			<p class="con4_tang3_p1">什么是车辆过户？怎么判断一年内过户？</p>
			<p class="p2">过户是指您的车辆所有人名称进行过变更。如果您的车是二手车，且属以下情况之一的，请点选“是”:</p>
			<p class="p2">1.过户时间在上年买保险到今天之间</p>
			<p class="p2">2.您的行驶证发证日期在一年之内</p>
			<p class="p2"></p>
		</div>
	
		<div class="con4_tang5 " id="vehicleModel">
			<p class="con4_tan_p1" style='font-style:16px;'>请选择车牌型号 <b style='display:inline-block;border-bottom:1px solid #ddd;position:absolute;bottom:0px;width:100%;left:0px;'></b></p>
			<div class="m-wrapper" id="m-wrapper">
			<ul class="tang5_ul">
				
			</ul>
			
			</div>
			<p class="con3_p7 con9_p4 con9_que"><a href="javascript:void(0);" class="que">取消</a></p>
		</div>
	
		<div class="con4_tang6">
			<p class="close"></p>
			<p class="con4_tang6_p1">选择日期</p>
			<div class="con4_tang6_div">
			  <div class="con4_tang6_a">
			      <ul class="con4_tang6_ul2">
			       <li>2016</li><li>2015</li><li class="li">2014</li><li>2013</li><li>2012</li><li>2011</li>
			       <li>2010</li><li>2009</li><li>2008</li><li>2007</li><li>2006</li><li>2005</li>
			      </ul>
			  </div>
			  <div class="con4_tang6_a">
			      <ul class="con4_tang6_ul2">
			       <li>12</li><li>11</li><li>10</li><li>09</li><li>08</li><li>07</li>
			       <li>06</li><li>05</li><li>04</li><li>03</li><li>02</li><li>01</li>
			      </ul>
			  </div>
			  <div class="con4_tang6_a">
			      <ul class="con4_tang6_ul2">
			       <li>30</li><li>29</li><li>28</li><li>27</li><li>26</li><li>25</li>
			       <li>24</li><li>23</li><li>22</li><li>21</li><li>20</li><li>19</li>
			       <li>18</li><li>17</li><li>16</li><li>15</li><li>14</li><li>13</li>
			       <li>12</li><li>11</li><li>10</li><li>09</li><li>08</li><li>07</li>
			       <li>06</li><li>05</li><li>04</li><li>03</li><li>02</li><li>01</li>
			      </ul>
			  </div>
			  <div class="clear"></div>
			</div>
		
			<ul class="con4_tang6_ul">
				<li class="first tang6"><a href="javascript:void(0);">确认</a></li>
				<li class="tang6"><a href="javascript:void(0);">取消</a></li>
				<div class="clear"></div>
			</ul>
		</div>
	
	
		<div class="con4_tang8">
		  <div class="con4_tang8_div">
		  	<p class="close"></p>
		    <p class="p1">中华人民共和国机动车行驶证</p>
		    <p class="p2">Vehicle license of People’s Republic China</p>
		    
		    <div class="con4_tang8_div2">
		      <div class="div1">
		        <p class="p3">号牌号码：</p>
		        <p class="p4">plate no.</p>
		      </div>
		      
		      <input class="texta text8" type="text" placeholder=""/>
		    
		      <div class="div1">
		        <p class="p3">车牌类型：</p>
		        <p class="p4">Vehicle Type</p>
		      </div>
		      <input class="texta text8" type="text"/>
		      <div class="clear"></div>
		    </div>
		    <div class="clear"></div>
		    
		    <div class="con4_tang8_div2">
		      <div class="div1">
		        <p class="p3">所有人：</p>
		        <p class="p4">Owner</p>
		      </div>
		      <input class="texta text9" type="text"/>
		      <div class="clear"></div>
		    </div>
		    
		    <div class="con4_tang8_div2">
		      <div class="div1">
		        <p class="p3">住   址：</p>
		        <p class="p4">Address</p>
		      </div>
		      <input class="texta text9" type="text"/>
		      <div class="clear"></div>
		    </div>
		    
		    <div class="con4_tang8_div2 con4_tang8_diva">
		      <div class="div2">
		        <p class="p3">使用性质：</p>
		        <p class="p4">Use character</p>
		      </div>
		      <input class="texta text10" type="text"/>
		      <div class="clear"></div>
		    </div>
		    
		    <div class="con4_tang8_div2 con4_tang8_diva con4_tang8_divb">
		      <div class="div2">
		        <p class="p3">品牌型号：</p>
		        <p class="p4">Model</p>
		      </div>
		      <input class="texta text10" type="text"/>
		      <p class="tang8_p1 tang8_p10"><img src="<%=path%>/views/quicksure/images/tang8_1.png"/>品牌型号</p>
		      <div class="clear"></div>
		    </div>
		    
		    
		    <div class="con4_tang8_div4">
		      <div class="con4_tang8_div2">
		        <div class="div3">
		          <p class="p3">发动机号码：</p>
		          <p class="p4">Engine No.</p>
		        </div>
		        <input class="texta text11" type="text"/>
		        <p class="tang8_p1 tang8_p11"><img src="<%=path%>/views/quicksure/images/tang8_1.png"/>发动机号</p>
		        <div class="clear"></div>
		      </div>
		     
		      <div class="con4_tang8_div2">
		        <div class="div3">
		          <p class="p3">车辆识别代号：</p>
		          <p class="p4">VIN</p>
		        </div>
		        <input class="texta text11" type="text"/>
		        <p class="tang8_p1 tang8_p11"><img src="<%=path%>/views/quicksure/images/tang8_1.png"/>车架号</p>
		        <div class="clear"></div>
		      </div>
		     
		      <div class="con4_tang8_div2 con4_tang8_diva">
			       <div class="div2">
			        <p class="p3">注册日期：</p>
			        <p class="p4">Register date</p>
			       </div>
			       <input class="texta text10" type="text"/>
			       <p class="tang8_p1 tang8_p7"><img src="<%=path%>/views/quicksure/images/tang8_1.png"/>注册日期</p>
			       <div class="clear"></div>
		      </div>
		      
		      <div class="con4_tang8_div2 con4_tang8_diva con4_tang8_divb">
			      <div class="div2">
			        <p class="p3">发证日期：</p>
			        <p class="p4">Issue Date</p>
			      </div>
		      	  <input class="texta text10" type="text"/>
		      	  <div class="clear"></div>
		      </div>
		      <div class="clear"></div>
		    </div>
		    
		    <div class="con4_tang8_div3">
		      <img src="<%=path%>/views/quicksure/images/tang8_2.png"/>
		    </div>
		    <div class="clear"></div>
		    
		  </div>
		</div>
	
	</div>
  
</div>
	<div class="errorhei" style="display:none">
		<div style="   position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
			<div style="width:85%;max-width:300px;margin:0 auto;background:#fff;max-width:300px;color: #333; border-radius:5px;padding: 20px 0px 3px 0px;box-shadow: 0 0 20px 2px #333;">
					<h2 style='font-size: 17px;color: #222;font-weight: normal;'>提示信息</h2>
					<p style='margin-bottom: 10px;font-size: 12px;padding: 15px 20px;text-align: center; color:#444;'id="Message"></p>
					<div style='border-top:1px solid #ddd;font-size:17px;color:#333;padding: 8px 0px;'><span style='display:block;width:100%;font-size:15px' id="ensure">确定</span></div>
			</div>
	 </div> 
	</div>
</div>
<%-- <div style="position:fixed;top:0px;left:0px;width:100%;height:100%;z-index: 100;display:none;"id="pop">
<div  style='width: auto;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 10px;letter-spacing: 2px; padding: 20px;text-align: center; position: fixed;top: 35%; left: 34%;' >
 <img  src="<%=path%>/views/quicksure/images/mu_loading.gif" style='width:50px'>
	   <p id="prompt">.</p >
	</div>
</div> --%>
<div style="position:fixed;top:0px;left:0px;width:100%;height:100%;z-index: 100;display:none;"id="pop">
<div style="position: fixed;top:35%; width:100%;height:auto;text-align:center;">
     <span style='display:inline-block;height: auto;border-radius: 5px;background: rgba(0,0,0,0.6);color: #fff;font-size: 10px;letter-spacing: 2px; padding: 20px;'>
        <img  src="<%=path%>/views/quicksure/images/mu_loading.gif" style='width: 36px; margin-bottom: 5px;'>
        <p id="prompt"></p>
	</span>
	 
</div>
</div>
<script src="<%=path%>/views/quicksure/scripts/layer.js"></script>
<script src="<%=path%>/views/quicksure/scripts/vehicleinfor.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=faeb78fb58db7f3acb8314910e5e3bd7&version=<%=nowDate %>"></script>
<script type="text/javascript">
 	var wr = new Optiscroll(document.getElementById('m-wrapper'), { forceScrollbars: true }); 

	function goBack(){
		window.location.href = "<%=path%>/vehicleInfor/goBackToFirstPage.do?orderNo=${insuranceDetailsVO.baseinfor.orderno}";	
	}
	
	$('.con4_tan_p1>b').click(function(){
	   $('.beijing').hide();
	});
	$('#ensure').click(function(){
    $('.errorhei').hide();
    }) ;
</script>

</body>

</html>
