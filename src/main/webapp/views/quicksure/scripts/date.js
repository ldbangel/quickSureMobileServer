$(function () {
    var currYear = (new Date()).getFullYear();
    var opt={};
    opt.date = {preset : 'date'};
    opt.datetime = {preset : 'datetime'};
    opt.time = {preset : 'time'};
    opt.default = {
        theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式
        mode: 'scroller', //日期选择模式
        dateFormat: 'yyyy-mm-dd',
        lang: 'zh',
        showNow: true,
        nowText: "今天",
//            startYear: currYear - 10, //开始年份
        endYear: currYear + 10 //结束年份
    };

      var Date_l=new Date();
      //给当前时间+一天
      var Date_2=new Date((Date_l/1000+86400)*1000);
      
    var now = Date_l.toString("yyyy-MM-dd");
    var now1=Date_2.toString("yyyy-MM-dd");
    var min = { preset : 'date', minDate: new Date(now1) };

    //起保日期
    $("#SY_DATE").mobiscroll($.extend(min, opt['default']));
    $("#JQ_DATE").mobiscroll($.extend(min, opt['default']));

    var max = { preset : 'date', maxDate: new Date(now) };
    //初登日期
    $("#registerdate").mobiscroll($.extend(max, opt['default']));
    //过户日期
    $("#transferdate").mobiscroll($.extend(max, opt['default']));
    //购置日期
    $("#certificateDate").mobiscroll($.extend(max, opt['default']));
    });