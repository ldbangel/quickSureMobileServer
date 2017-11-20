<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible"; content="IE=edge">
    <meta name="screen-orientation" content="portrait">
    <meta name="x5-orientation" content="portrait">
    <meta name="full-screen" content="yes">
    <meta name="x5-fullscreen" content="true">
    <meta name="browsermode" content="application">
    <meta name="x5-page-mode" content="app">

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">

<meta name="x5-page-mode" content="no-title">
<meta name="blink-page-mode" content="no-title">
    <title>详细信息</title>
    <link rel="stylesheet" href="<%=path%>/views/quicksure/css/user_mation.css">
    <link href="<%=path%>/views/quicksure/css/datePicker.css" rel="stylesheet" type="text/css" media="all" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/views/quicksure/css/optiscroll.css">
    
     <script src="<%=path%>/views/quicksure/scripts/optiscroll.js"></script>
</head>
<body>
<div class="main_mation">
    <div id="content_left">
        <form action=""method="post">
            <div class="form_content lf">
                <div id="Product_mation">
                    <h2>产品信息</h2>
                    <table>
                        <tr>
                            <td><input type="checkbox">商业险<span>起保时间</span>
                                <input value=""name="act_start_time" type="text" class="text-box" style="cursor:pointer;width:132px" id="sypolicystartdate"  class="date_picker"></td>
                            <td><input type="checkbox">交强险<span>起保时间</span><input type="text" id="jqpolicystartdate"  class="date_picker" style="cursor:pointer;width:132px"></td>
                        </tr>
                        <tr>
                            <td>承保城市
                                <select name="province_main">
                                    <option value="-1">--请选择省份--</option>
                                    <option value="1">广东</option>
                                </select>
                                <select name="city_main" >
                                    <option value="-1">--请选择城市--</option>
                                    <option value="1">深圳</option>
                                </select>
                            </td>
                            <td>代理人<input type="text"></td>
                        </tr>
                    </table>

                </div>
                <div id="User_mation">
                    <p><input type="checkbox" class="toggle_action">人员信息</p>
                    <div class="car_user_content">
                        <p class="cae_user_content_p">车主信息</p>
                        <table>
                            <tr>
                                <td>车主姓名<input type="text" id="ownersname" class="Must_fill"></td>
                                <td>手机号<input type="text" id="ownersphoneno" class="Must_fill"></td>
                                <td>身份证<input type="text" id="ownerscerticode" class="Must_fill"></td>
                            </tr>

                            <tr>
                                <td>车主地址<select id="provinceName">
                                    <option value="-1">--请选择省份--</option>
                                </select>
                                </td>
                                <td><select id="cityName">
                                    <option value="-1">--请选择城市--</option>
                                </select>
                                    <select id="countyName">
                                        <option value="-1">--请选择县区--</option>
                                    </select>
                                </td>
                                <td>详细<input id="ownersaddress"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="Policy_holder">
                        <p class="Policy_holder_p">投保人信息 <input type="checkbox" class="Copy"></p>
                        <table>
                            <tr>
                                <td>姓名<input type="text" id="applicationname"></td>
                                <td>手机号<input type="text" id="applicationphoneno"></td>
                                <td>身份证<input type="text" id="applicationcerticode"></td>
                            </tr>

                            <tr>
                                <td>地址<select id="applicationProvinceName">
                                    <option value="-1">--请选择省份--</option>
                                </select>
                                </td>
                                <td><select id="applicationCityName">
                                    <option value="-1">--请选择城市--</option>
                                </select>
                                    <select id="applicationCountyName">
                                        <option value="-1">--请选择县区--</option>
                                    </select>
                                </td>
                                <td>详细<input id="applicationaddress"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="Insured_content">
                        <p class="Insured_content_p">被保人信息<input type="checkbox"  class="Copy"></p>
                        <table>
                            <tr>
                                <td>姓名<input type="text" id="insurename"></td>
                                <td>手机号<input type="text" id="insurephoneno"></td>
                                <td>身份证<input type="text" id="insurecerticode"></td>
                            </tr>

                            <tr>
                                <td>地址<select id="insuredProvinceName">
                                    <option value="-1">--请选择省份--</option>
                                </select>
                                </td>
                                <td><select id="insuredCityName">
                                    <option value="-1">--请选择城市--</option>
                                </select>
                                    <select id="insuredCountyName">
                                        <option value="-1">--请选择县区--</option>
                                    </select>
                                </td>
                                <td>详细<input type="text" id="insureaddress"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="Delivery_content">
                        <p class="Delivery_content_p">配送信息<input type="checkbox"  class="Copy"></p>
                        <table>
                            <tr>
                                <td>姓名<input type="text" id="deliveryname"></td>
                                <td>手机号<input type="text" id="deliveryphone"></td>
                                <td>身份证<input type="text" id="deliverycerticode"></td>
                            </tr>

                            <tr>
                                <td>地址<select name="">
                                    <option value="-1">--请选择省份--</option>
                                </select>
                                </td>
                                <td><select name="">
                                    <option value="-1">--请选择城市--</option>
                                </select>
                                    <select name="">
                                        <option value="-1">--请选择县区--</option>
                                    </select>
                                </td>
                                <td>详细<input type="text" id="deliveryaddress"></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div id="Car_user_mation">
                    <h2 class="Car_user_mation_p">车辆信息</h2>
                    <table>
                        <tr>
                            <td>车架<input type="text" id="vinno" class="Must_fill"></td>
                            <td>品牌型号<input type="text" id="modelName" class="Must_fill"><span><input type="button" value="搜索车型"  onclick="modelSelect()"></span></td>
                            <td>车型代码<input type="text" id="brandcode" class="Must_fill"></td>
                        </tr>

                        <tr>
                            <td>车牌<input type="text" id="lcnno" class="Must_fill"></td>
                            <td>初登日期<input type="text"  id="registerdate" style='width:110px'class="date_picker" placeholder="点击选择日期"></td>
                            <td>发动机<input type="text" id="engno" class="Must_fill"></td>
                            
                        </tr>

                        <tr>
                            <td>排量<input type="text" id="displacement" class="Must_fill"></td>
                            <td>座位数<input type="text" id="setno" class="Must_fill"></td>
                            <td>新车购置价<input type="text"  id="vhlval"  class="Must_fill" ></td>     
                        </tr>

                        <tr>
                            <td>生产时间<input type="text" id="marketyear" class="Must_fill"></td>
                            <td>是否过户车<input type="radio" value="1" name="chgownerflag">是<input type="radio" value="2" name="chgownerflag" checked="checked">否</td>

                            <td>过户日期<input type="text" id="transferdate" style='width:110px' class="date_picker" placeholder="点击选择日期"></td>
                            
                        </tr>
                    </table>
                </div>
                <div id="Insurance_mation">
                    <h2 class="Insurance_mation_p">险种信息</h2>
                    <table>
                        <tr>
                            <th><input type="checkbox" class="check_All">全选</th>
                            <th>险种</th>
                            <th>保额</th>
                            <th>是否不计免赔</th>
                            <th>保费</th>
                            <th>不计免赔保费</th>

                        </tr>
                        <tr>
                            <td><input type="checkbox" class="check" id="CTPLCode"></td>
                            <td>交强险</td>
                            <td><input type="text" id="CTPLQuota"></td>
                            <td></td>
                            <td><input type="text" id="CTPLPremium"></td>

                        </tr>
                        <tr>
                            <td><input type="checkbox" class="check" id="modCode"></td>
                            <td>车辆损失险</td>
                            <td><input type="text" id="modQuota"></td>
                            <td><input type="checkbox" id="modAbatement"></td>
                            <td><input type="text" id="modQuotaPremium"></td>
                            <td><input type="text" id="modductiblePremium"></td>

                        </tr>

                        <tr>
                            <td><input type="checkbox" class="check" id="vtplCode"></td>
                            <td>第三者责任险</td>
                            <td><select  id="vtplQuota">
					                <option value="306006004">50000</option>
								    <option value="306006005">100000</option>
								    <option value="306006006">200000</option>
								    <option value="306006007">300000</option>
								    <option value="306006009">500000</option>
								    <option value="306006014">1000000</option>
					             </select>
                            </td>
                            <td><input type="checkbox" id="vtplAbatement"></td>
                            <td><input type="text" id="vtplPremium"></td>
                            <td><input type="text" id="vtplductiblePremium"></td>

                        </tr>
                        <tr>
                            <td><input type="checkbox" id="theftCode"></td>
                            <td>全车盗抢险</td>
                            <td><input type="text" id="theftQuota"></td>
                            <td><input type="checkbox" id="theftAbatement"></td>
                            <td><input type="text" id="theftPremium"></td>
                            <td><input type="text" id="theftductiblePremium"></td>

                        </tr>
                        <tr>
                            <td><input type="checkbox" class="check" id="DriversCode"></td>
                            <td>司机座位责任险</td>
                            <td><select name="money" >
								    <option value="306006005">10000</option>
								    <option value="306006006">20000</option>
								    <option value="306006007">30000</option>
								    <option value="306006009">50000</option>
					             </select>
                            </td>
                            <td><input type="checkbox" id="DriversAbatement"></td>
                            <td><input type="text" id="DriversPremium"></td>
                            <td><input type="text" id="DriversductiblePremium"></td>

                        </tr>
                        <tr>
                            <td><input type="checkbox"class="check" id="PassengerCode"></td>
                            <td>乘客座位责任险</td>
                            <td><select name="money" id="PassengerQuota">
                                	<option value="306006005">10000</option>
								    <option value="306006006">20000</option>
								    <option value="306006007">30000</option>
								    <option value="306006009">50000</option>
                            </select></td>
                            <td><input type="checkbox" id="PassengerAbatement"></td>
                            <td><input type="text" id="PassengerPremium"></td>
                            <td><input type="text" id="PassengerductiblePremium"></td>

                        </tr>
                        <tr>
                            <td><input type="checkbox" class="check" id="CombustionCode"></td>
                            <td>自然险损失险</td>
                            <td><input type="text" id="CombustionQuota"></td>
                            <td><input type="checkbox" id="CombustionAbatement"></td>
                            <td><input type="text" id="CombustionPremium"></td>
                            <td><input type="text" id="CombustionductiblePremium"></td>

                        </tr>
                        <tr>
                            <td><input type="checkbox" id="GlassCode"></td>
                            <td>玻璃破碎险</td>
                            <td><select name="Glass" id="GlassQuota">
                                <option value="303011001">国产玻璃</option>
                                <option value="303011002">进口玻璃</option>
                            </select></td>
                            <td></td>
                            <td><input type="text" id="GlassQuotaPremium"></td>

                        </tr>
                        <tr>
                            <td><input type="checkbox" id="ThirdPartyCode"></td>
                            <td>车损找不到第三方</td>
                            <td><input type="text" id="ThirdPartyQuota" /></td>
                            <td></td>
                            <td><input type="text" id="ThirdPartyPremium"></td>

                        </tr>
                        <tr>
                            <td><input type="checkbox" id="CarCode"></td>
                            <td>制定修理厂</td>
                            <td><input type="text" id="CarQuota" /></td>
                            <td></td>
                            <td><input type="text" id="CarQuotaPremium" /></td>

                        </tr>
                    </table>
                </div>
                <div id="Parameter_mation">
                    <h2 class="Parameter_mation_p">系数</h2>
                    <table>
                        <tr>
                            <td>车船税保费 <input type="text" id="taxvalfee"></td>
                            <td>滞纳金 <input type="text" id="latefee"></td>
                            <td>当年应缴 <input type="text" id="currenttax"></td>
                        </tr>
                        <tr>
                            <td>总折扣 <input type="text" id="totalsale"></td>
                            <td>上年出险次数 <input type="text" id="formercountinsure"></td>
                            <td>交通违法系数 <input type="text" id="trafficviolate"></td>
                        </tr>
                        <tr>
                            <td>核保系数 <input type="text" id="underwritefactor"></td>
                            <td>渠道系数 <input type="text" id="channelratio"></td>
                            <td></td>
                        </tr>
                    </table>
                </div>

            </div>
            <div id="aside_Service">
                <div>
                    <p>较强险信息</p >
                    <ul>
                        <li>保额 <span> </span>元</li>
                        <li>保费 <span> </span>元</li>
                        <li>车船税 <span> </span>元</li>
                    </ul>
                </div>
                <div>
                    <p>商业险信息</p >
                    <ul>
                        <li>保额 <span> </span>元</li>
                        <li>保费 <span> </span>元</li>
                    </ul>
                </div>
                <div>
                    <p>总保费</p >
                    <ul>
                        <li>应缴保费 <span> </span>元</li>
                    </ul>
                </div>
                <div class="btn_list">
                    <span id="Compute" onclick="getQuote()">保费计算</span>
                    <span>保费</span>
                    <span>提交核保</span>
                    <span>立即支付</span>
                </div>
            </div>
        </form>
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
            <span class="cae_user_content">车主信息</span>
            <span class="Policy_holder">投保人信息</span>
            <span class="Insured_content">被保人信息</span>
            <span class="Car_user_mation">车辆信息</span>
            <span class="Insurance_mation">险种信息</span>
            <span class="Delivery_content">配送信息</span>
            <span class="Parameter_mation">系数</span>
            <span class="">头部信息</span>
            <span class="">特约</span>

        </div>
    </div>

</div>
</body>

<script src="<%=path%>/views/quicksure/scripts/jquery.1.7.2.min.js"></script>
<script>
$(".conModel2 b").click(function(){$(".conModel1").hide();});
var wr = new Optiscroll(document.getElementById('m-wrapper'), { forceScrollbars: true });
</script>
<script src="<%=path%>/views/quicksure/scripts/layer.js"></script>
<script type="text/javascript" src="<%=path%>/views/quicksure/scripts/jquery.date_input.pack.js"></script>
<script src="<%=path%>/views/quicksure/scripts/common.js"></script>
<script src="<%=path%>/views/quicksure/scripts/user_mation.js"></script>
</html>