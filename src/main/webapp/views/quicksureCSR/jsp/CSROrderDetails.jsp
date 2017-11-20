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
                <tr>
                  <td>
                    <!-- <input type="checkbox"> -->商业险
                    <span>起保时间</span>
                    <input type="text" value="${insuranceDetailsVO.baseinfor.sypolicystartdate }" id="syStartTime" style='width:140px' class="date_picker" class="Must_fill" disabled>
                  	<input type="hidden" id="syStartDate"><input type="hidden" id="syEndDate"></td>
                  <td>
                    <!-- <input type="checkbox"  id="CTPLCode"> -->交强险
                    <span>起保时间</span>
                    <input type="text" value="${insuranceDetailsVO.baseinfor.jqpolicystartdate }" id="jqStartTime" style='width:140px' class="date_picker" disabled>
                    <input type="hidden" id="jqStartDate"><input type="hidden" id="jqEndDate"></td>
                </tr>
                <tr>
                  <td>承保城市
		                <input type="text" value="${insuranceDetailsVO.baseinfor.deptAddress }" style='width:140px' class="date_picker" disabled>
                  </td>
                  <td>代理人
                    <input type="text" disabled></td></tr>
                    <tr><td style="text-align: left">行政代码<input type="text" id="code" disabled></td>
                        <td>订单号<input type="text" style='width:140px' id="csrPremiumOrderNo" value="${insuranceDetailsVO.baseinfor.orderno }" disabled></td>
                    </tr>
              </table>
            </div>
            <div id="User_mation">
              <p>
              <input type="checkbox" class="toggle_action" disabled>人员信息</p>
              <div class="car_user_content">
                <h2 class="cae_user_content_p">车主信息</h2>
                <table>
                  <tr>
                    <td>车主姓名
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.ownersname}" name="ownersname" id="carownerinfor" class="Must_fill" disabled></td>
                    <td>手机号
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.ownersphoneno}" name="ownersphoneno" id="carownerphone" class="Must_fill" disabled></td>
                    <td>身份证
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.ownerscerticode}" name="ownerscerticode" id="ownerscerticode" class="Must_fill" style='width:145px' disabled></td></tr>
                  <tr>
                    <td>地址
                      <input type="text" value="${insuranceDetailsVO.baseinfor.deptAddress}" style='width:140px' class="date_picker" disabled>
                   </td>
                    <td>邮政编码
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.ownerszipcode}" id="ownerszipcode" name="ownerzipcode" disabled></td></tr>
                    <tr>
                      <td>详细地址<input type="text" value="" id="ownersdetailaddress" name="ownersdetailaddress" class="Must_fill" disabled></td>
                      <td></td>
                    </tr>  
                </table>
              </div>
              <div class="Policy_holder">
                <h2 class="Policy_holder_p">投保人信息
                  <!-- <input type="checkbox" class="Copy"> --></h2>
                <table>
                  <tr>
                    <td>投保人姓名
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationname}" name="applicationname" id="appName" disabled></td>
                    <td>手机号
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationphoneno}" name="applicationphoneno" id="appphone" disabled></td>
                    <td>身份证
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationcerticode}" id="appcertcode" disabled></td></tr>
                  <tr>
                    <td class="Area">地址
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationaddress}" style='width:140px' class="date_picker" disabled>
                    </td>
                    <td>邮政编码
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationzipcode}" id="applicationzipcode" name="applicationzipcode" disabled></td></tr>
                    <tr>
                      <td>详细地址<input type="text" value="${insuranceDetailsVO.insuranceperinfor.applicationaddress}" id="applicationdetailaddress" name="applicationdetailaddress" disabled></td>
                      <td></td>
                    </tr> 
                </table>
              </div>
              <div class="Insured_content">
                <h2 class="Insured_content_p">被保人信息
                  <!-- <input type="checkbox" class="Copy"> --></h2>
                <table>
                  <tr>
                    <td>被保人姓名
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.insurename}" name="insurename" id="insurename" disabled></td>
                    <td>手机号
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.insurephoneno}" name="insurephoneno" id="insurephoneno" disabled></td>
                    <td>身份证
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.insurecerticode}" name="insurecerticode" id="insurecerticode" disabled></td></tr>
                  <tr>
                    <td class="Area">地址
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.insureaddress}"  disabled>
                    </td>	
                    <td>邮政编码
                      <input type="text" value="${insuranceDetailsVO.insuranceperinfor.insurezipcode}" id="insuredzipcode" name="insuredzipcode" disabled></td></tr>
                    <tr>
                      <td>详细地址<input type="text" value="${insuranceDetailsVO.insuranceperinfor.insureaddress}" id="insuredetailaddress" name="insuredetailaddress" disabled></td>
                      <td></td>
                    </tr> 
                </table>
              </div>
              <div class="Delivery_content">
                <h2 class="Delivery_content_p">配送信息
                  <!-- <input type="checkbox" class="Copy" disabled> --></h2>
                <table>
                  <tr>
                    <td>保单收件人
                      <input type="text" value="${insuranceDetailsVO.deliveryinfor.deliveryname}" id="deliveryName" name="deliveryname" disabled></td>
                    <td>手机号
                      <input type="text" value="${insuranceDetailsVO.deliveryinfor.deliveryphone}" id="deliveryPhone" name="deliveryphone" disabled></td>
                  </tr>
                  <tr>
                    <td class="Area">地址
                      <input type="text" value="${insuranceDetailsVO.deliveryinfor.deliveryaddress}"  disabled>
                    </td>
                    <td>邮政编码
                      <input type="text" <%-- value="${insuranceDetailsVO.deliveryinfor.deliveryaddress}" --%> id="Delivery_ZipCode" name="deliveryzipcode" disabled></td>
                  </tr>
                    <tr>
                      <td>详细地址<input type="text" value="" id="deliverydetailaddress" name="deliverydetailaddress" disabled></td>
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
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.vinno}" id="vinno" class="Must_fill" style="width: 155px;"></td>
                  <td style="padding-left: 22px;" class="model">品牌型号
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.model}" id="modelName" class="Must_fill" style="width:150px">
                  </td>
                  <td>车型代码
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.brandcode}" id="brandcode" class="Must_fill"></td></tr>
                <tr>
                  <td>车牌
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.lcnno}" id="lcnno" class="Must_fill"></td>
                  <td style="padding-left: 26px;">&nbsp;&nbsp;&nbsp;发动机
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.engno}" id="engno" class="Must_fill"></td>
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
                  <td>品牌型号<input type="text" id="brandName" name="brandName" value="${insuranceDetailsVO.vhlinfor.brandName}" class="Must_fill"></td>
                  <td></td>
                </tr>
                <tr>
                 <td>是否过户车
                    <input type="radio" value="2" name='chgownerflag' id="chgownerflag1">是
                    <input type="radio" value="1" name='chgownerflag' checked="checked" id="chgownerflag2">否
                 </td>
                  <td>过户日期
                    <input type="text" value="${insuranceDetailsVO.vhlinfor.transferdate}" id="transferdate" style='width:120px' class="date_picker" placeholder="点击选择日期">
                  </td>
                  <td></td>
                 </tr>
                               </table>
            </div>
            <div id="Insurance_mation">
              <h2 class="Insurance_mation_p">险种信息</h2>
              <table>
                <tr>
                  <th>
                    <input type="checkbox" class="check_All">全选</th>
                  <th>险种</th>
                  <th>保额</th>
                  <th>是否不计免赔</th>
                  <th>保费</th>
                  <th>不计免赔保费</th></tr>
                <tr>
                  <td>
                    <input type="checkbox" class="check" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='0357'}">checked="checked"</c:if></c:forEach>></td>
                  <td>交强险</td>
                  <td>
                    <input type="text" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='0357'}">value="${s.suminsured}"</c:if></c:forEach>></td>
                  <td></td>
                  <td>
                    <input type="text" id="CTPLPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='0357'}">value="${s.premium}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" class="check" id="modCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'}">checked="checked"</c:if></c:forEach>></td>
                  <td>车辆损失险</td>
                  <td>
                    <input type="text" id="modQuota" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'}">value="${s.suminsured}"</c:if></c:forEach> ></td>
                  <td>
                    <input type="checkbox" id="modAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.deductibleflag=='1'}">checked="checked"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="modQuotaPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'&&s.deductibleflag=='1'}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text"  id="modductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030101'}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" class="check" id="vtplCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'}">checked="checked"</c:if></c:forEach>></td>
                  <td>第三者责任险</td>
                  <td>
                    <select name="money"  id="vtplQuota">
                    <option value="306006004" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='50000.00'}">selected="selected"</c:if></c:forEach>>50000</option>
				    <option value="306006005" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='100000.00'}">selected="selected"</c:if></c:forEach>>100000</option>
				    <option value="306006006" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='200000.00'}">selected="selected"</c:if></c:forEach>>200000</option>
				    <option value="306006007" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='300000.00'}">selected="selected"</c:if></c:forEach>>300000</option>
				    <option value="306006009" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='500000.00'}">selected="selected"</c:if></c:forEach>>500000</option>
				    <option value="306006014" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.suminsured=='1000000.00'}">selected="selected"</c:if></c:forEach>>1000000</option>
				    </select>
                  </td>
                  <td>
                    <input type="checkbox" value="" id="vtplAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.deductibleflag=='1'}">checked="checked"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="vtplPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="vtplductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030102'&&s.deductibleflag=='1'}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" id="theftCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'}">checked="checked"</c:if></c:forEach>></td>
                  <td>全车盗抢险</td>
                  <td>
                    <input type="text" id="theftQuota" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'}">value="${s.suminsured}"</c:if></c:forEach>></td>
                  <td>
                    <input type="checkbox" value="" id="theftAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.deductibleflag=='1'}">checked="checked"</c:if></c:forEach>></td>
                  <td>
                    <input type="text"  id="theftPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="theftductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030103'&&s.deductibleflag=='1'}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" class="check" id="DriversCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'}">checked="checked"</c:if></c:forEach>></td>
                  <td>司机座位责任险</td>
                  <td>
                    <select name="money" id="DriversQuota">
                      <option value="10000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='10000.00'}">selected="selected"</c:if></c:forEach>>10000</option>
		               <option value="20000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='20000.00'}">selected="selected"</c:if></c:forEach>>20000</option>
		               <option value="30000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='30000.00'}">selected="selected"</c:if></c:forEach>>30000</option>
		               <option value="50000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='50000.00'}">selected="selected"</c:if></c:forEach>>50000</option>
		               <option value="60000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='60000.00'}">selected="selected"</c:if></c:forEach>>60000</option>
		               <option value="70000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='70000.00'}">selected="selected"</c:if></c:forEach>>70000</option>
		               <option value="80000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='80000.00'}">selected="selected"</c:if></c:forEach>>80000</option>
		               <option value="90000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='90000.00'}">selected="selected"</c:if></c:forEach>>90000</option>
		               <option value="100000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.suminsured=='100000.00'}">selected="selected"</c:if></c:forEach>>100000</option>
                      </select>
                  </td>
                  <td>
                    <input type="checkbox" id="DriversAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.deductibleflag=='1'}">checked="checked"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="DriversPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="DriversductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030104'&&s.deductibleflag=='1'}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" class="check" id="PassengerCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'}">checked="checked"</c:if></c:forEach>></td>
                  <td>乘客座位责任险</td>
                  <td>
                    <select name="money" id="PassengerQuota">
                      <option value="10000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='10000.00'}">selected="selected"</c:if></c:forEach>>10000</option>
		               <option value="20000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='20000.00'}">selected="selected"</c:if></c:forEach>>20000</option>
		               <option value="30000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='30000.00'}">selected="selected"</c:if></c:forEach>>30000</option>
		               <option value="50000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='50000.00'}">selected="selected"</c:if></c:forEach>>50000</option>
		               <option value="60000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='60000.00'}">selected="selected"</c:if></c:forEach>>60000</option>
		               <option value="70000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='70000.00'}">selected="selected"</c:if></c:forEach>>70000</option>
		               <option value="80000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='80000.00'}">selected="selected"</c:if></c:forEach>>80000</option>
		               <option value="90000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='90000.00'}">selected="selected"</c:if></c:forEach>>90000</option>
		               <option value="100000" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.suminsured=='100000.00'}">selected="selected"</c:if></c:forEach>>100000</option>
                      
                      </select>
                  </td>
                  <td>
                    <input type="checkbox" id="PassengerAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.deductibleflag=='1'}">checked="checked"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="PassengerPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="PassengerductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030105'&&s.deductibleflag=='1'}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" class="check" id="CombustionCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'}">checked="checked"</c:if></c:forEach>></td>
                  <td>自然险损失险</td>
                  <td>
                    <input type="text" id="CombustionQuota" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'}">value="${s.suminsured}"</c:if></c:forEach>></td>
                  <td>
                    <input type="checkbox" id="CombustionAbatement" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'&&s.deductibleflag=='1'}">checked="checked"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="CombustionPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'}">value="${s.premium}"</c:if></c:forEach>></td>
                  <td>
                    <input type="text" id="CombustionductiblePremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030108'}">value="${s.nyl12}"</c:if></c:forEach>></td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" id="GlassCode" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'}">checked="checked"</c:if></c:forEach>></td>
                  <td>玻璃破碎险</td>
                  <td>
                    <select name="Glass" id="GlassQuota" value="">
                      <option value="303011001" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'&&s.suminsured=='303011001'}">selected="selected"</c:if></c:forEach>>国产玻璃</option>
                  	  <option value="303011002" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'&&s.suminsured=='303011002'}">selected="selected"</c:if></c:forEach>>进口玻璃</option>
                    </select>
                  </td>
                  <td></td>
                  <td>
                    <input type="text"  id="GlassQuotaPremium" <c:forEach items="${insuranceDetailsVO.coverageinfors}" var="s"><c:if test="${s.insrnccode=='030107'}"> value="${s.premium}"</c:if></c:forEach>></td>
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
              <h2 class="Parameter_mation_p">系数</h2>
              <table>
                <tr>
                  <td>车船税保费
                    <input type="text" value="${insuranceDetailsVO.baseinfor.taxpremium}" id ="currenttax"></td>
                  <td>滞纳金
                    <input type="text" value="${insuranceDetailsVO.taxinfor.latefee}" id="latefee"></td>
                  <td>当年应缴
                    <input type="text" value="${insuranceDetailsVO.taxinfor.currenttax}" id="currentTax2"></td>
                </tr>
                <tr>
                  <td>总折扣
                    <input type="text" value="${insuranceDetailsVO.baseinfor.applyTotalAdj}" id="applyTotalAdj"></td>
                  <td>上年理赔情况
                    <input type="text" value="${insuranceDetailsVO.baseinfor.claimAdjustRsn}" id="claimAdjustRsn"></td>
                  <td></td>
                </tr>
              </table>
            </div>
          </div>
          <div id="aside_Service">
            <div>
              <p>交强险信息</p>
              <ul>
                <!-- <li>保额
                  <span>0.00</span>元</li> -->
                <li>保费
                  <span id ="jqpremium">${insuranceDetailsVO.baseinfor.jqpremium}</span>元</li>
                <li>车船税
                  <span id ="currenttax1">${insuranceDetailsVO.baseinfor.taxpremium}</span>元</li></ul>
            </div>
            <div>
              <p>商业险信息</p>
              <ul>
                <!-- <li>保额
                  <span>0.00</span>元</li> -->
                <li>保费
                  <span id ="sypremium" >${insuranceDetailsVO.baseinfor.sypremium}</span>元</li></ul>
            </div>
            <div>
              <p>总保费</p>
              <ul>
                <li>应缴保费
                  <span id ="totalPremium">${insuranceDetailsVO.baseinfor.totalPremium}</span>元</li></ul>
            </div>
            <div class="btn_list" id="btn_list">
              <a onclick="getQuote()" id="quote" style="display:bloclk"><span>保费计算</span></a>
              <a onclick="submitform()" id="submit" style="display:none"><span>提交核保</span></a>
              <a onclick="paymentinfor()" id="payment" style="display:none"><span>立即支付</span></a>
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
  </body>
  <div class="errorhei" style="display:none">
	<div class="errortan">
		<p class="errortan1">信息提示</p>
		<p class="errortan2"><div id="chackMess"></div></p>
		<a class="errortan3" href="javascript:void(0);">确 定</a>
	</div>
    </div>
  <script src="<%=path%>/views/quicksureCSR/js/jquery.1.7.2.min.js"></script>
  <script src="<%=path%>/views/quicksure/scripts/layer.js"></script>
  <script src="<%=path%>/views/quicksureCSR/js/jquery.date_input.pack.js"></script>
   <script src="<%=path%>/views/quicksureCSR/js/user_mation.js"></script>
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
    $('.check_All').click(function() {
      var All_iput = $(this).parent().parent().siblings().children().children(':checkbox');
      if ($(this).attr('checked') == null) {
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
