//***扩展对象点赞插件、点赞特效***//
//***Zynblog**//
//***2016-5-11**//
//***用法：jQuery('.praisebtn').praise(options);***//
; (function ($) {
    $.fn.praise = function (options) {
        var defaults = {
            obj: null, //jq对象，针对哪个对象使用这个tipsBox函数
            str: "+1", //字符串，要显示的内容;也可以传一段html
            startSize: "10px", //动画开始的文字大小
            endSize: "30px",  //动画结束的文字大小
            interval: 600, //文字动画时间间隔
            color: "red",  //文字颜色
            callback: function () { }  //回调函数
        };
        var opt = $.extend(defaults, options);  
        $("body").append("<span class='num'>" + opt.str + "</span>");
        var box = $(".num");
        var left = opt.obj.offset().left + opt.obj.width()/2; 
        var top = opt.obj.offset().top - opt.obj.height();
        box.css({
            "position": "absolute",
            "left": left + "px",
            "top": top + "px",
            "z-index": 9999,
            "font-size": opt.startSize,
            "line-height": opt.endSize,
            "color": opt.color
        });
        box.animate({
            "font-size": opt.endSize,
            "opacity": "0",
            "top": top - parseInt(opt.endSize) + "px"
        }, opt.interval, function () {
            box.remove();
            opt.callback();
        });
    }
})(jQuery);

//点赞图标恢复原样
function niceIn(prop) {
    prop.find('.praisenum').addClass('niceIn').css("color", "red");
    setTimeout(function () {
        prop.find('.praisenum').css("color", "#45BCF9").removeClass('niceIn');
    }, 1000);
};







