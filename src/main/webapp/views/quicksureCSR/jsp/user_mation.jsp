<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1">
    <meta http-equiv="X-UA-Compatible"  content="IE=edge">
    <meta name="screen-orientation" content="portrait">
    <meta name="x5-orientation" content="portrait">
    <meta name="full-screen" content="yes">
    <meta name="x5-fullscreen" content="true">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <meta name="x5-page-mode" content="no-title">
    <meta name="blink-page-mode" content="no-title">
    <title>详细信息</title>
    <link rel="stylesheet" href="<%=path%>/views/quicksureCSR/css/user_mation.css">
    <link rel="stylesheet" href="<%=path%>/views/quicksureCSR/css/datePicker.css" media="all"></head>
    <link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/optiscroll.css">
    
     <script src="<%=path%>/views/quicksure/scripts/optiscroll.js"></script>
  <body>
    <div class="main_mation">
      <div id="content_left">
        <form  id="from" action="" method="post">
          <div class="form_content lf">
            <div id="Product_mation">
              <h2>产品信息</h2>
              <table>
              	<!-- 用于订单详情地址带出来 -->
              	<input type="hidden" id="deptcode1" value="${insuranceDetailsVO.baseinfor.deptno}">
                <tr>
                  <td>
                    <input type="checkbox" class="checkbox_All" <c:if test="${insuranceDetailsVO.baseinfor.sypolicystartdate!=null && insuranceDetailsVO.baseinfor.sypolicystartdate!=''}">checked</c:if> >商业险
                    <span>起保时间</span>
                    <input type="text" value="${insuranceDetailsVO.baseinfor.sypolicystartdate }" id="SY_DATE" style='width:140px' class="date_picker" placeholder="点击选择日期" class="Must_fill">
                  	<input type="hidden" id="syStartDate"><input type="hidden" id="syEndDate"></td>
                  <td>
                    <input type="checkbox"  id="CTPLCode" <c:if test="${insuranceDetailsVO.baseinfor.jqpolicystartdate!=null && insuranceDetailsVO.baseinfor.jqpolicystartdate!=''}">checked</c:if>>交强险
                    <span>起保时间</span>
                    <input type="text" value="${insuranceDetailsVO.baseinfor.jqpolicystartdate }" id="JQ_DATE" style='width:140px' class="date_picker" placeholder="点击选择日期" >
                    <input type="hidden" id="jqStartDate"><input type="hidden" id="jqEndDate"></td>
                </tr>
                <tr>
                  <td>承保城市
		                <select name="province" id="Travel_Province" onchange="provinceChange(this.id,this.value)"  value="" class="selectAll">
		                </select>
		                <select name="city" id="Travel_City" value="" onchange="cityChange(this.id,this.value)" class="selectAll">
		                </select>
		                <select name="composed" id="Travel_County"value="" onchange="townChange(this.id,this.value)" class="selectAll">
		                </select>
                  </td>
                  <td>代理人
                    <input type="text"></td></tr>
                    <tr><td style="text-align: left">行政代码<input type="text" id="code"></td>
                        <td>订单号<input type="text" id="csrPremiumOrderNo" value="${insuranceDetailsVO.baseinfor.orderno }"></td>
                    </tr>
              </table>
            </div>
            <div id="User_mation">
              <p>
                <input type="checkbox" class="toggle_action">人员信息</p>
              <div class="car_user_content">
                <h2 class="cae_user_content_p">车主信息</h2>
                <table>
                  <tr>
                    <td>车主姓名
                      <input type="text" value="${insuranceDetailsVO.vhlinfor.drvowner}" maxlength="4" name="ownersname" id="carownerinfor" onblur="validateName('Owner')" placeholder="请输入车主姓名" class="Must_fill" ></td>
                    <td>手机号
                      <input type="text" value="${insuranceDetailsVO.vhlinfor.phoneno}" name="ownersphoneno" maxlength="11" id="carownerphone" onblur="mobileNumber('Owner')" class="Must_fill"></td>
                    <td>身份证
                      <input type="text" value="${insuranceDetailsVO.vhlinfor.certificateno}" name="ownerscerticode" maxlength="18" id="carownercertcode" placeholder="请输入车主身份证件号码" onblur="identityCodeValid('Owner');" class="Must_fill" style="width:150px;"></td></tr>
                  <tr>
                    <td>地址
                      <select name="OwnerProvinceName" id="Owner_Province" onchange="provinceChange(this.id,this.value)"  value="" class="selectAll">
		                </select>
		                <select name="OwnerCityName" id="Owner_City" value="" onchange="cityChange(this.id,this.value)" class="selectAll">
		                </select>
		                <select name="OwnerComposedName" id="Owner_County"value="" onchange="townChange(this.id,this.value)" class="selectAll">
		                </select>
                   </td>
                    <td>邮政编码
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.ownerszipcode }" id="Owner_ZipCode" name="ownerzipcode"></td></tr>
                    <tr>
                      <td>详细地址<input type="text" value="${insuranceDetailsVO.insuranceperinfor.ownersaddress }" id="ownersdetailaddress" maxlength="30" name="ownersdetailaddress" class="Must_fill" style="width:270px;"></td>
                      <td></td>
                    </tr>  
                </table>
              </div>
              <div class="Policy_holder">
                <h2 class="Policy_holder_p">投保人信息
                  <input type="checkbox" class="Copy"></h2>
                <table>
                  <tr>
                    <td>投保人姓名
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationname }" maxlength="4" placeholder="请输入投保人姓名" name="applicationname" id="appName" onblur="validateName('Application')"></td>
                    <td>手机号
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationphoneno }" maxlength="11" placeholder="请输入投保人手机号码" name="applicationphoneno" id="appphone" onblur="mobileNumber('Application')" ></td>
                    <td>身份证
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationcerticode }" maxlength="18" name="applicationcerticode" id="appcertcode" onblur="identityCodeValid('Application');" style="width:150px;"></td></tr>
                  <tr>
                    <td class="Area">地址
                      <select name="applicationProvinceName" id="Application_Province" onchange="provinceChange(this.id,this.value)"  value="" class="selectAll">
		                </select>
		                <select name="applicationCityName" id="Application_City" value="" onchange="cityChange(this.id,this.value)" class="selectAll">
		                </select>
		                <select name="applicationCountyName" id="Application_County"value="" onchange="townChange(this.id,this.value)" class="selectAll">
		                </select>
                   </td>
                    <td>邮政编码
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationzipcode }" id="Application_ZipCode" name="applicationzipcode"></td></tr>
                    <tr>
                      <td>详细地址<input type="text" maxlength="30" value="${insuranceDetailsVO.insuranceperinfor.applicationaddress }" id="applicationdetailaddress" name="applicationdetailaddress" style="width:270px;"></td>
                      <td></td>
                    </tr> 
                </table>
              </div>
              <div class="Insured_content">
                <h2 class="Insured_content_p">被保人信息
                  <input type="checkbox" class="Copy"></h2>
                <table>
                  <tr>
                    <td>被保人姓名
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.insurename }" maxlength="4" name="insurename" id="inName" onblur="validateName('Insured')"></td>
                    <td>手机号
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.insurephoneno }" maxlength="11" name="insurephoneno" id="inphone" onblur="mobileNumber('Insured')"></td>
                    <td>身份证
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.insurecerticode }" maxlength="18" name="insurecerticode" id="incertcode" onblur="identityCodeValid('Insured')" style="width:150px;"></td></tr>
                  <tr>
                    <td class="Area">地址
                      <select name="insuredProvinceName" id="Insured_Province" onchange="provinceChange(this.id,this.value)"  value="" class="selectAll">
		                </select>
		                <select name="insuredCityName" id="Insured_City" value="" onchange="cityChange(this.id,this.value)" class="selectAll">
		                </select>
		                <select name="insuredCountyName" id="Insured_County"value="" onchange="townChange(this.id,this.value)" class="selectAll">
		                </select>
                   </td>
                    <td>邮政编码
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.insurezipcode }" id="Insured_ZipCode" name="insuredzipcode"></td></tr>
                    <tr>
                      <td>详细地址<input type="text" maxlength="30" value="${insuranceDetailsVO.insuranceperinfor.insureaddress }" id="insuredetailaddress" name="insuredetailaddress" style="width:270px;"></td>
                      <td></td>
                    </tr> 
                </table>
              </div>
              <div class="Delivery_content">
                <h2 class="Delivery_content_p">配送信息
                  <input type="checkbox" class="Copy"></h2>
                <table>
                  <tr>
                    <td>保单收件人
                      <input type="text" value="${insuranceDetailsVO.deliveryinfor.deliveryname}" maxlength="4" id="deliveryName" name="deliveryname" onblur="validateName('Delivery')"></td>
                    <td>手机号
                      <input type="text" value="${insuranceDetailsVO.deliveryinfor.deliveryphone}"  maxlength="11" id="deliveryPhone" name="deliveryphone" onblur="mobileNumber('Delivery')"></td>
                    <td><input type="text" style="display:none"></td></tr>
                 <tr>
                    <td class="Area">地址
                      <select name="deliveryProvinceName" id="Delivery_Province" onchange="provinceChange(this.id,this.value)"  value="" class="selectAll">
		                </select>
		                <select name="deliveryCityName" id="Delivery_City" value="" onchange="cityChange(this.id,this.value)" class="selectAll">
		                </select>
		                <select name="deliveryCountyName" id="Delivery_County"value="" onchange="townChange(this.id,this.value)" class="selectAll">
		                </select>
                   </td>
                    <td style="text-align: left;">邮政编码
                      <input type="text" value="${insuranceDetailsVO.deliveryinfor.deliveryaddress}" id="Delivery_ZipCode" name="deliveryzipcode"></td>
                  </tr>
                    <tr>
                      <td>详细地址<input type="text" value="${insuranceDetailsVO.deliveryinfor.deliveryaddress}" maxlength="30" id="deliverydetailaddress" name="deliverydetailaddress" style="width:270px;"></td>
                      <td></td>
                    </tr> 
                </table>
              </div>
            </div>
            <div id="Car_user_mation">
              <h2 class="Car_user_mation_p">车辆信息</h2>
              <table>
                <tr>
                  <td style="width: 200px;">车架
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.vinno}" id="vinno" maxlength="17" class="Must_fill" style="width: 155px;"></td>
                  <td style="padding-left: 22px;" class="model">品牌型号
                    <input type="text" value="" id="modelName" class="Must_fill" style="width:150px">
                    <a onclick="modelSelect()"  class="btn"><span style="margin-left: 0px;font-weight:600;">搜索车型</span></a></td>
                  <td>车型代码
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.brandcode}" id="brandcode" class="Must_fill"></td></tr>
                <tr>
                  <td>车牌
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.lcnno}" id="lcnno" class="Must_fill" maxlength="7"></td>
                  <td style="padding-left: 26px;">&nbsp;&nbsp;&nbsp;发动机
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.engno}" id="engno" class="Must_fill" maxlength="12"></td>
                  <td>初登日期
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.registerdate}" id="registerdate" style='width:120px' class="date_picker" placeholder="点击选择日期" class="Must_fill"></td></tr>
                <tr>
                  <td>排量
                    <input type="text" id="displacement" value="${insuranceDetailsVO.vhlinfor.displacement}" class="Must_fill"></td>
                  <td>&nbsp;&nbsp;&nbsp;新车购置价
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.vhlval}" id="vhlval" class="Must_fill"></td>
                  <td>座位数
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.setno}" id="setno" class="Must_fill"></td></tr>
                <tr>
                  <td>上市时间<input type="text" id="marketyear" class="Must_fill" value="${insuranceDetailsVO.vhlinfor.marketyear}"></td>
                  <td>品牌名称<input type="text" id="brandName" name="brandName" value="${insuranceDetailsVO.vhlinfor.brandName}" class="Must_fill"></td>
                  <td></td>
                </tr>
                <tr>
                 <td class="chgownerflag">是否过户车
                    <input type="radio"  name='chgownerflag' id="chgownerflag1" value="1" <c:if test="${insuranceDetailsVO.vhlinfor.transferdate!=null && insuranceDetailsVO.vhlinfor.transferdate!=''}">checked</c:if>>是
                    <input type="radio"  name='chgownerflag' id="chgownerflag2" value="0" <c:if test="${insuranceDetailsVO.vhlinfor.transferdate==null || insuranceDetailsVO.vhlinfor.transferdate==''}">checked</c:if>>否
                 </td>
                  <td class="transferdate" style="display:none;">过户日期
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.transferdate}" id="transferdate" style='width:120px' class="date_picker" placeholder="点击选择日期">
                  </td>
                  <td></td>
                 </tr>
                               </table>
            </div>
            <div id="Insurance_mation">
              <h2 class="Insurance_mation_p">险种信息</h2>
              <table>
                <tr class="All_checkbox">
                  <th>
                    <input type="checkbox" class="check_All">全选</th>
                  <th>险种</th>
                  <th>保额</th>
                  <th>是否不计免赔</th>
                  <th>保费</th>
                  <th>不计免赔保费</th></tr>
                <tr>
                  <td>
                    <input type="checkbox" class="check" id="modCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.suminsured!=null&&s.suminsured!=''}">checked</c:if></c:forEach>></td>
                  <td>车辆损失险</td>
                  <td>
                    <input type="text" id="modQuota" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.suminsured!=null&&s.suminsured!=''}">value="${s.suminsured}"</c:if></c:forEach>></td>
                  <td>
                    <input type="checkbox" id="modAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.nyl12!=null&&s.nyl12!=''}">checked</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="modQuotaPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.premium!=null&&s.premium!=''}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="modductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.nyl12!=null&&s.nyl12!=''}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" class="check" id="vtplCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured!=''&&s.suminsured!=null}">checked</c:if></c:forEach>></td>
                  <td>第三者责任险</td>
                  <td>
                    <select name="money"  id="vtplQuota">
                    <option value="306006004" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='50000'}">selected="selected"</c:if></c:forEach>>50000</option>
				    <option value="306006005" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='100000'}">selected="selected"</c:if></c:forEach>>100000</option>
				    <option value="306006006" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='200000'}">selected="selected"</c:if></c:forEach>>200000</option>
				    <option value="306006007" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='300000'}">selected="selected"</c:if></c:forEach>>300000</option>
				    <option value="306006009" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='500000'}">selected="selected"</c:if></c:forEach>>500000</option>
				    <option value="306006014" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='1000000'}">selected="selected"</c:if></c:forEach>>1000000</option>
                  </td>
                  <td>
                    <input type="checkbox" value="" id="vtplAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.nyl12!=null&&s.nyl12!=''}">checked</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="vtplPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.premium!=null&&s.premium!=''}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="vtplductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.nyl12!=null&&s.nyl12!=''}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" id="theftCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.suminsured!=null&&s.suminsured!=''}">checked</c:if></c:forEach>></td>
                  <td>全车盗抢险</td>
                  <td>
                    <input type="text" id="theftQuota" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.suminsured!=null&&s.suminsured!=''}">value="${s.suminsured}"</c:if></c:forEach>></td>
                  <td>
                    <input type="checkbox" value="" id="theftAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.nyl12!=null&&s.nyl12!=''}">checked</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="theftPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.premium!=null&&s.premium!=''}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="theftductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.nyl12!=null&&s.nyl12!=''}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" class="check" id="DriversCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured!=''&&s.suminsured!=null}">checked</c:if></c:forEach>></td>
                  <td>司机座位责任险</td>
                  <td>
                    <select name="money" id="DriversQuota">
                      <option value="10000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='10000'}">selected="selected"</c:if></c:forEach>>10000</option>
		               <option value="20000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='20000'}">selected="selected"</c:if></c:forEach>>20000</option>
		               <option value="30000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='30000'}">selected="selected"</c:if></c:forEach>>30000</option>
		               <option value="50000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='50000'}">selected="selected"</c:if></c:forEach>>50000</option>
		               <option value="60000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='60000'}">selected="selected"</c:if></c:forEach>>60000</option>
		               <option value="70000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='70000'}">selected="selected"</c:if></c:forEach>>70000</option>
		               <option value="80000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='80000'}">selected="selected"</c:if></c:forEach>>80000</option>
		               <option value="90000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='90000'}">selected="selected"</c:if></c:forEach>>90000</option>
		               <option value="100000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='100000'}">selected="selected"</c:if></c:forEach>>100000</option>
                      </select>
                  </td>
                  <td>
                    <input type="checkbox" id="DriversAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.nyl12!=null&&s.nyl12!=''}">checked</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="DriversPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.premium!=null&&s.premium!=''}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="DriversductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.nyl12!=null&&s.nyl12!=''}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" class="check" id="PassengerCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured!=''&&s.suminsured!=null}">checked</c:if></c:forEach>></td>
                  <td>乘客座位责任险</td>
                  <td>
                    <select name="money" id="PassengerQuota">
                      <option value="10000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='10000'}">selected="selected"</c:if></c:forEach>>10000</option>
		               <option value="20000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='20000'}">selected="selected"</c:if></c:forEach>>20000</option>
		               <option value="30000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='30000'}">selected="selected"</c:if></c:forEach>>30000</option>
		               <option value="50000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='50000'}">selected="selected"</c:if></c:forEach>>50000</option>
		               <option value="60000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='60000'}">selected="selected"</c:if></c:forEach>>60000</option>
		               <option value="70000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='70000'}">selected="selected"</c:if></c:forEach>>70000</option>
		               <option value="80000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='80000'}">selected="selected"</c:if></c:forEach>>80000</option>
		               <option value="90000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='90000'}">selected="selected"</c:if></c:forEach>>90000</option>
		               <option value="100000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='100000'}">selected="selected"</c:if></c:forEach>>100000</option>
                      
                      </select>
                  </td>
                  <td>
                    <input type="checkbox" id="PassengerAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.nyl12!=null&&s.nyl12!=''}">checked</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="PassengerPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.premium!=null&&s.premium!=''}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="PassengerductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.nyl12!=null&&s.nyl12!=''}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" class="check" id="CombustionCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'&&s.suminsured!=null&&s.suminsured!=''}">checked</c:if></c:forEach>></td>
                  <td>自然险损失险</td>
                  <td>
                    <input type="text" id="CombustionQuota" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'&&s.suminsured!=null&&s.suminsured!=''}">value="${s.suminsured}"</c:if></c:forEach>></td>
                  <td>
                    <input type="checkbox" id="CombustionAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'&&s.nyl12!=null&&s.nyl12!=''}">checked</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="CombustionPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'&&s.premium!=null&&s.premium!=''}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="CombustionductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'&&s.nyl12!=null&&s.nyl12!=''}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" id="GlassCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'&&s.premium!=null&&s.premium!=''}">checked</c:if></c:forEach>></td>
                  <td>玻璃破碎险</td>
                  <td>
                    <select name="Glass" id="GlassQuota" value="" >
                      <option value="303011001" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'&&s.suminsured=='303011001'}">selected="selected"</c:if></c:forEach>>国产玻璃</option>
                  	  <option value="303011002" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'&&s.suminsured=='303011002'}">selected="selected"</c:if></c:forEach>>进口玻璃</option>
                    </select>
                  </td>
                  <td></td>
                  <td>
                    <input type="text" id="GlassQuotaPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'&&s.premium!=null&&s.premium!=''}">value="${s.premium}"</c:if></c:forEach>></td>
                </tr>
                <!-- <tr>
                  <td>
                    <input type="checkbox"></td>
                  <td>车损找不到第三方</td>
                  <td></td>
                  <td></td>
                  <td>
                    <input type="text" value=""></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox"></td>
                  <td>制定修理厂</td>
                  <td></td>
                  <td></td>
                  <td>
                    <input type="text" value=""></td>
                </tr> -->
              </table>
            </div>
            <div id="Parameter_mation">
              <h2 class="Parameter_mation_p">系数和车船税</h2>
              <table>
                <tr>
                  <td>车船税保费
                    <input type="text" value="" id ="currenttax"></td>
                  <td>滞纳金
                    <input type="text" value="" id="latefee"></td>
                  <td>当年应缴
                    <input type="text" value="" id="currentTax2"></td></tr>
                <tr>
                  <td>总折扣
                    <input type="text" value="" id="applyTotalAdj"></td>
                  <td>上年理赔情况
                    <input type="text" value="" id="claimAdjustRsn"></td>
                  <td></td></tr>
              </table>
            </div>
          </div>
          <div id="aside_Service">
            <div>
              <p>交强险信息</p>
              <ul>
                <li>保额
                  <span>0.00</span>元</li>
                <li>保费
                  <span id ="jqpremium">  </span>元</li>
                <li>车船税
                  <span id ="currenttax1"></span>元</li></ul>
            </div>
            <div>
              <p>商业险信息</p>
              <ul>
                <li>保额
                  <span>0.00</span>元</li>
                <li>保费
                  <span id ="sypremium" > </span>元</li></ul>
            </div>
            <div>
              <p>总保费</p>
              <ul>
                <li>应缴保费
                  <span id ="totalPremium"></span>元</li></ul>
            </div>
            <div class="btn_list" id="btn_list">
              <a onclick="getQuote()" id="quote" style="display:bloclk"><span>保费计算</span></a>
              <a onclick="submitform()" id="submit" style="display:none"><span>提交核保</span></a>
              <a onclick="paymentinfor()" id="payment" style="display:none"><span>获取支付链接</span></a>
              </div>
          </div>
        </form>
        <span style="display:none;"><input type="text" id ="quotation" class="inputP1"></span>
        
        
        <div class="conModel1" id="vehicleModel" style="display:none">
			<h3 class="conModel2">请选择车牌型号<b style="height: 26px;margin-right: 11px;">X</b></h3>
			<div class="m-wrapper optiscroll" id="m-wrapper" style="position: static;">
				<div class="conModel3" id="">
				<ul class="conModel4">
				
			    </ul>
			</div>
			</div>
		</div>
        
        
        
        <div class="pf_left">
          <a class="cae_user_content">车主信息</a>
          <a class="Policy_holder">投保人信息</a>
          <a class="Insured_content">被保人信息</a>
          <a class="Car_user_mation">车辆信息</a>
          <a class="Insurance_mation">险种信息</a>
          <a class="Delivery_content">配送信息</a>
          <a class="Parameter_mation">系数</a>
          <a class="">头部信息</a>
          <a class="">特约</a></div>
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
 <!--  <div class="errorhei" style="display:none">
	<div class="errortan">
		<p class="errortan1">信息提示</p>
		<p class="errortan2"><div id="chackMess"></div></p>
		<a class="errortan3" href="javascript:void(0);">确 定</a>
	</div>
    </div> -->
    
    <div class="errorhei" style="display:none">
		<div style="   position: fixed;top: 35%; width:100%;height:auto;text-align:center;">
			<div style="width:85%;max-width:300px;margin:0 auto;background:#fff;max-width:300px;color: #333; border-radius:5px;padding: 20px 0px 3px 0px;box-shadow: 0 0 20px 2px #333;">
					<h2 style='font-size: 17px;color: #222;font-weight: normal;padding:10px 0px;background:#fff;'>提示信息</h2>
					<p style='margin-bottom: 10px;font-size: 12px;padding: 15px 20px;text-align: center; color:#444;background:#fff;'id="chackMess"></p>
					<div style='border-top:1px solid #ddd;font-size:17px;color:#333;padding: 8px 0px;'><span style='display:block;width:100%;font-size:15px' id="ensure">确定</span></div>
			</div>
	 </div> 
	</div>

  <script src="<%=path%>/views/quicksureCSR/js/jquery.1.7.2.min.js"></script>
  <script src="<%=path%>/views/quicksure/scripts/layer.js"></script>
  <script src="<%=path%>/views/quicksureCSR/js/jquery.date_input.pack.js"></script>
   <script src="<%=path%>/views/quicksureCSR/js/user_mation.js"></script>
   	<script type="text/javascript">
			$('#ensure').click(function(){
    $('.errorhei').hide();
    }) ;
	</script>
  <script>
	$(".conModel2 b").click(function(){$(".conModel1").hide();});
	var wr = new Optiscroll(document.getElementById('m-wrapper'), { forceScrollbars: true });
  </script>
  <script>
     $('.check').click(function() {
      var right_iput = $(this).parent().siblings().eq(2).children(':checkbox');
      if ($(this).attr('checked') == null) {
        if (right_iput.attr('checked') !== null) {
          right_iput.removeAttr('checked');
        }
      } else if (right_iput.attr('checked') == null) {
        right_iput.attr('checked', 'checked');
      }
    });
    //选择了全险
    $('.check_All').click(function() {
      var All_iput = $(this).parent().parent().siblings().children().children(':checkbox');
      if ($(this).attr('checked') == null) {
        All_iput.removeAttr('checked');
      } else {
        All_iput.attr('checked', 'checked');
      }
    });
    //选择了商业险触发全勾
    $('.checkbox_All').click(function() {
      var All_iput =$(".All_checkbox").siblings().children().children(':checkbox');
      if ($(".checkbox_All").attr('checked') == null) {
        All_iput.removeAttr('checked');
      } else {
        All_iput.attr('checked', 'checked');
      }
    });
    $(function() {
      $('#datePicker').date_input();
      $('#registerdate').date_input();
      $('#transferdate').date_input();
      $('#SY_DATE').date_input();
      $('#JQ_DATE').date_input();
/*       var arr = []; //点击复制class="Copy"
      $('.Copy').on('click',
      function() {
      copyArea();
        if ($(this).attr('checked') != null) {
          $('.car_user_content td input:text').each(function(i) {
            arr[i] = $(this).val();
          });
          var Class_input = "." + $(this).parent().parent().attr("class") + " input:text";
          $(Class_input).each(function(i) {
            $(this).val(arr[i]);
          })
        }
      }); */
      $('#Compute').click(function() {
        var elem_Input = $('.Must_fill');
        for (var i = 0; i < elem_Input.length; i++) {
          if (elem_Input.eq(i).val() == "") {
            elem_Input.eq(i).addClass('Missing');
            var moved = 0,
            move = 0,
            step = 0,
            interval = 10,
            elem_selTop = 0,
            timer = null;
            var scroll = $(document).scrollTop(),
            screenHeight = $(window).innerHeight();
            function scrollTo() {
              elem_selTop = elem_Input.eq(i).offset().top;
              move = (scroll + screenHeight / 2) - elem_selTop;
              step = move / 100;
              timer = setInterval(function timer() {
                scrollStep()
              },
              1);
              function scrollStep() {
                scroll = scroll - (step);
                $(document).scrollTop(scroll);
                moved++;
                if (moved == 100) {
                  clearInterval(timer);
                  timer = null;
                  moved = 0;
                }
              }
            }
            scrollTo();
            break;
          }
        }
      });
      $('.toggle_action').click(function() {
        if ($(this).attr('checked') != null) {
          $('#User_mation>div').addClass('None');
          $('#User_mation p').addClass('style');
        } else($('#User_mation>div').removeClass('None'))
      })
    });
    $('.pf_left a').click(function() {
      var Position = "." + $(this).attr('class') + "_p";
      var moved = 0,
      move = 0,
      step = 0,
      interval = 10,
      elem_selTop = 0,
      timer = null;
      var scroll = $(document).scrollTop(),
      screenHeight = $(window).innerHeight();
      function scrollTo() {
        elem_selTop = $(Position).offset().top;
        move = (scroll + screenHeight / 2) - elem_selTop;
        step = move / 100;
        timer = setInterval(function timer() {
          scrollStep()
        },
        10);
        function scrollStep() {
          scroll = scroll - (step);
          $(document).scrollTop(scroll);
          moved++;
          if (moved == 100) {
            clearInterval(timer);
            timer = null;
            moved = 0;
          }
        }
      }
      scrollTo();
    })
  </script>
</html> 
