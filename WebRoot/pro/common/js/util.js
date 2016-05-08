/**
 *  ClassName : 判断浏览顺
 *  method: 正则表达式分析浏览器的userAgent属性
 *  param: 
 */
var Sys = {};

var ua = navigator.userAgent.toLowerCase();

var s;
(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :

(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :

(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :

(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :

(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

/**********判断空***************/
function isEmpty(v) {
    return (v == null || v == undefined || v == '')?true:false;
}