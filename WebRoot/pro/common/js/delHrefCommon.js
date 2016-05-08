var URLS;
var url_regex = new RegExp("http://*");
var href_header = "http://dgxl.link";
var href_footer = ".html";

function forbidSeeRealLinks() {
	var olinks = document.links;
	URLS = new Array(olinks.length);
	for (var i = 0; i < olinks.length; i++) {
		var link = olinks[i];
		URLS[i] = link.href;
		link.href = href_header  +i+ href_footer;
		link.onclick = function() {
			return gotolink(this);
			//console.log('地址是：'+this);
		};
	}
}

function gotolink(A) {
	var link = A.href;
	var i = link.replace(href_header, "").replace(href_footer, "").replace("/",
			"");
	window.location.replace(URLS[i]);
	return false;
}

forbidSeeRealLinks();