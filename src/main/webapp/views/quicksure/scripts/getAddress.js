var lnglatXY;
var map, geolocation;
var provincebyLocation,citybyLocation;
var layerIndex;
var ispc=false;
/**
 * 页面初始化，获取行政区域等信息
 * 
 */
$(document).ready(function() {
	  
      $(function (){         
          var system={
          win:false,
          mac:false,
          x11:false
          };
				var p = navigator.platform;
				system.win = p.indexOf("Win") == 0;
				system.mac = p.indexOf("Mac") == 0;
				system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
				//跳转语句
				if(system.win||system.mac||system.xll){
				 ispc=true;
				$("#city").css('display','block');
				$('#demo1').remove();
				
				$("#city").click(function (e) {
					SelCity(this,e);
				});
				$('#cityJson').attr('src',getUrl()+"/views/quicksure/scripts/quicksure/cityJson.js");
				$('#citySet').attr('src',getUrl()+"/views/quicksure/scripts/quicksure/citySet.js");
				$('#Popt').attr('src',getUrl()+"/views/quicksure/scripts/quicksure/Popt.js");
				
				}else{
				 ispc=false;
				$('#city').remove();
				/* $('#LArea').attr('src',urlpath+"/views/quicksure/scripts/quicksure/LArea.js"); */
				var area1 = new LArea();
				area1.init({
					'trigger': '#demo1', 
					'valueTo': '#value1', 
					'keys': {
						id: 'id',
						name: 'name'
					}, 
					'type': 1, 
					'data': LAreaData 
				});
				area1.value=[1,13,3];
			
				}
           });     
	$.ajaxSettings.async = false;
	$.getJSON(getUrl()+'/views/quicksure/scripts/json/department.json', function (data) {
		provincedata=data;
	});
if(""===$("#demo1").val() && (deptAddress=="" || deptAddress==null)&&!ispc){
 //layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 10*1000});
  //加载地图，调用浏览器定位服务
	map = new AMap.Map('container', {
		resizeEnable : true
	});

	map.plugin('AMap.Geolocation', function() {
		geolocation = new AMap.Geolocation({
			enableHighAccuracy : true,//是否使用高精度定位，默认:true
			timeout : 10000, //超过10秒后停止定位，默认：无穷大
			buttonOffset : new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
			zoomToAccuracy : true, //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
			buttonPosition : 'RB'
		});
		map.addControl(geolocation);
		geolocation.getCurrentPosition();
		AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
		AMap.event.addListener(geolocation, 'error', onError); //返回定位出错信息
	});

	//解析定位结果
	function onComplete(data) {
		lnglatXY = [];
		lnglatXY = [ data.position.getLng(), data.position.getLat() ];
		getAddressFormJW();
	}
	//解析定位错误信息
	function onError(data) {
		if(document.getElementById('tip')!=null){
			document.getElementById('tip').innerHTML = '定位失败';
		}	
	}

	AMap.service('AMap.Geocoder', function() {//回调函数
		//实例化Geocoder
		geocoder = new AMap.Geocoder({
			city : "010"//城市，默认：“全国”
		});
		//TODO: 使用geocoder 对象完成相关功能
	});
	function getAddressFormJW() {
		geocoder.getAddress(lnglatXY, function(status, result) {
			if (status === 'complete' && result.info === 'OK') {
//				$("#pop").hide();
				provincebyLocation=result.regeocode.addressComponent.province;
				citybyLocation=result.regeocode.addressComponent.city;	
				var district=result.regeocode.addressComponent.district;
				var deptAddress=provincebyLocation+","+citybyLocation+","+district;	
				if(""===$("#demo1").val()&&"undefined"!==typeof(deptAddress)&&""!==deptAddress&&deptAddress!=null){					
					$("#demo1").val(deptAddress);
				    setDeptNoandLcnNo(deptAddress,true);					    
				}
			} else {
            alert("定位失败");
			}
		});
	}

}
});

function load(outtimes){
	$("#pop").show();
	setTimeout(function(){$('#pop').hide();
	},outtimes);
}
	
