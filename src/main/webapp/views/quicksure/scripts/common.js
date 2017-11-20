//取绝对路径地址
function getUrl(){
	 var protocol = window.location.protocol;
	 var host = window.location.host;
	 var pathname = window.location.pathname.split('/');
	 var url = protocol+"//"+host+"/"+pathname[1];
	 return url;
}

//小写转大写
function setUpperCase(obj){ 
    var a=obj.value;
    var b=a.toLocaleUpperCase();
    obj.value=b;
}

//身份证校验
function identityCodeValid(code) {
	/*var code=document.getElementById('certificateno').value;*/
  var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
  var tip = "";
  var pass= true;

  // to fix XXB-441. Carey corrected regular express, remove the year starting with 18, correct the month [12] to [0-2]
  if(!code || !/^\d{6}(19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
      tip = "The format of Id no is wrong!";
      pass = false;
  }

  else if(!city[code.substr(0,2)]){
      tip = "Area code is wrong ";
      pass = false;
  }
  else{
      //18位身份证需要验证最后一位校验位
      if(code.length == 18){
          code = code.split('');
          //∑(ai×Wi)(mod 11)
          //加权因子
          var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
          //校验位
          var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
          var sum = 0;
          var ai = 0;
          var wi = 0;
          for (var i = 0; i < 17; i++)
          {
              ai = code[i];
              wi = factor[i];
              sum += ai * wi;
          }
          var last = parity[sum % 11];
          if(parity[sum % 11] != code[17]){
              tip = "The last character is wrong!";
              pass =false;
          }
      }
  }
  if(!pass){
  	 checkIdFlag=false;
//  	 $("#checkIdCard").show();
  	 idno = 1;
  }
  if(pass){
     checkIdFlag=true;
// 	 $("#checkIdCard").hide();
 	 idno=0;   
  }
  
//  return pass;
}

$('.con_a>a').click(function(e){
	e.preventDefault();

	if($(this).hasClass('con_right')){
		$('#Confirm').hide();
	}else{
		$('#Confirm').hide();
	}
})
