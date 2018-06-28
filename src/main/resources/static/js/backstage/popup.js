// JavaScript Document

/*确定保存弹出框的位置*/
var saveAlertWidth = $(window).width();
var saveAlertHeight = $(window).height();
/*保存*/
function showWebAlert(title) {
	var confirmHtml = "<div class='saveAlert'></div>";
	$(".mbStyle").after(confirmHtml);
	$(".saveAlert").html('');
	$(".saveAlert").fadeIn(1000);
	$(".saveAlert").append(title);
	setTimeout("hideWebAlert()", 1000);
	$(".saveAlert").css("left", saveAlertWidth / 2 - 135);
	$(".saveAlert").css("top", saveAlertHeight / 2 - 40);

}

function hideWebAlert() {

	$(".saveAlert").fadeOut(1000);

}
/*保存结束*/

/*删除开始*/
/*describe值可有可无*/
/*describe用于提示重要信息*/
var divName;
function deleteShowAlert(title, info, ok, cancel,divName,describe) {
	this.divName=divName;
	
	$(".mbStyle").fadeIn(300);
	var confirmHtml = "";
	confirmHtml += "<div class='deleteAlert'>" +
		"<div class='d_title'></div>" +
		"<div class='d_msg'></div>" +
		"<div>" +
		"<div class='d_ms'></div>"+
		"<div class='d_bt_ok' onclick=clickOK()></div>" +
		"<div class='d_bt_cancel' onclick=clickCancle()></div>" +
		"</div>" +
		"</div>";
	$(".mbStyle").after(confirmHtml);

	$(".deleteAlert").fadeIn(1000);
    
    if(describe!=undefined&&describe!=null&&describe!=""){
    	$(".d_ms").html("");
    	$(".d_ms").append(describe)
    	$(".d_ms").css({"color":"#999","font-size":"10px","margin-top":"-35px"});
    	$(".deleteAlert").css("height", "160px");
    }

	$(".d_title").html("");
	$(".d_msg").html("");
	
	$(".d_bt_ok").html("");
	$(".d_bt_cancel").html("");

	$(".d_title").append(title);
	$(".d_msg").append(info);
	
	$(".d_bt_ok").append(ok);
	$(".d_bt_cancel").append(cancel);
    
	$(".deleteAlert").css("left", saveAlertWidth / 2 - 130);
	$(".deleteAlert").css("top", saveAlertHeight / 2 - 40);
	$(".deleteAlert").css("z-index", "99998");
	
  
}

function clickOK() {

	$('.mbStyle').html('');
	window.parent.hideAlert();
	$(".mbStyle").fadeOut(300);
	$(".deleteAlert").hide();
	result(divName,true);

}

function clickCancle() {
	$('.mbStyle').html('');
	window.parent.hideAlert();
	$(".mbStyle").fadeOut(300);
	$(".deleteAlert").hide();
    result(divName,false);

}


/* mwidth    蒙板宽度
 * mheight   蒙板高度
 * isTop     是否顶部垂直居中
 * idName    弹出层DIV的名称
 * isReset   DIV中如果有表单元素，是否重置  (可缺省，如果缺省则为false)
 * 修改日期：2017-12-20 13:05
 * */
var popupArr = new Array();
var i = 0;

function mask(mwidth, mheight, isTop, idName, isReset) {
	i++;
	popupArr.push(i);
	$("#mb_" + idName).css({
		"background-color": "#000",
		"filter": "alpha(opacity=60)",
		"-moz-opacity": "0.6",
		"opacity": "0.6",
		"width": "100%",
		"height": "100%",
		"position": "absolute",
		"left": "0px",
		"top": "0px",
		"display": "none",
		"z-index": "9999"
	});
	/*如果为真，则重置表单元素，否则保留*/
	if(isReset) {
		$("#" + idName).find("form")[0].reset();
	}

	/*window.parent.showAlert();*/

	/*isTop为true为顶部居中*/
	if(mwidth >= 1200) {
		mwidth = 1200;
	}
	if(mheight >= 560) {
		mheight = 560;
	}

	var swidth = (mwidth) / 2 + 30;

	var sheight = (mheight) / 2;
	/*初始化遮罩*/
	/*确定保存弹出框的位置*/
	var mb_Height = $(document).height();
	var wWidth = $(window).width();
	var wHeight = $(window).height();

	$("#mb_" + idName).css("height", mb_Height);
	$("#" + idName+"  .pInfo").css("height", mheight-35-40-25);
    
	$("#" + idName).css("left", (wWidth / 2) - swidth);
	if(!isTop) {
		$("#" + idName).css("top", 10);
	} else {
		$("#" + idName).css("top", (wHeight / 2) - sheight);
	}

	$("#" + idName).css({
		"width": mwidth,
		"height": mheight
	});
	$("#" + idName).children("h1").css("width", mwidth);
	$(".popupInfo").css("max-height", mheight - 60);
	$("#mb_" + idName).fadeIn(300);
	$("#" + idName).slideDown(200);

}

//关闭弹窗，蒙板
function closeMask(idName) {
	$("#mb_" + idName).fadeOut(300);
	$("#" + idName).slideUp(200);
	for(var i = popupArr.length - 1; i >= 0; i--) {
		if(popupArr.length == 1) {

			window.parent.hideAlert();
		}
		popupArr.splice(i, 1);
		return;
	}
	i = 0;
}

/*提醒框开始*/
function showRemind(title,url, reminTime) {
	$(".reText").html("");
	$(".reText").append(title+"&nbsp;<a href='"+url+"'>立即查看</a>");
	$(".remind").fadeIn(1500);
	setTimeout("hideRemind()", reminTime);
}

function hideRemind() {
	$(".remind").fadeOut(300);
}
/*提醒框结束*/