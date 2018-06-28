// JavaScript Document
$(function() {
	var conWidth = $(window).width();
	$(".content").css("width", conWidth);
	var leftHeight = $(window).height() - 46;
	$(".left").css("height", leftHeight);
	var rightWidth = conWidth - 200;
	$(".right").css("width", rightWidth);
	$(".right").css("height", leftHeight);

	$(".right").css("width", rightWidth);
	$(".right").css("height", leftHeight);
	$(".iframe").css("width", rightWidth);
	$(".iframe").css("height", leftHeight);

});
$(function() {

	$(".nav_title").click(function() {

		$(this).addClass("nav_title_bg");
		$(this).parents().siblings().children("li div").removeClass("nav_title_bg");
		$('.second').hide(300);
		$(this).siblings('ul').toggle();
		
		

	});

	$(".rightBtn").hide();

});

function hideLeft() {
	var conWidth = $(window).width();
	$(".rightBtn").show();
	$(".leftBtn").hide(100);
	$(".left").hide(100);
	$(".right").css("width", conWidth);

	$(".iframe").css("width", conWidth);

}

function showLeft() {
	var conWidth = $(window).width();
	$(".rightBtn").hide();
	$(".leftBtn").show(100);
	$(".left").show(100);
	$(".right").css("width", conWidth - 200);
	$(".iframe").css("width", conWidth - 200);
}

$(function() {
	$(".hav li").click(function() {
		$(this).removeClass("hav_li");
		$(this).addClass("line");
		$(this).siblings().removeClass("line");
		$(this).siblings().addClass("hav_li");
        $(".nav_title").removeClass("nav_title_bg");
		var num = $(this).index() + 1;
		if(num != null && num > 0) {
			$("#nav_" + num).siblings().css("display", "none");
			$("#nav_" + num).css("display", "block");

		}
	});

	$("#admin").mouseover(function() {
		if(!this.contains(event.fromElement)) {
			$(".person").slideDown(1);
		}
	});
	$("#admin").mouseout(function() {
		if(!this.contains(event.toElement)) {
			$(".person").slideUp(1);
		}
	});
	$(".person").click(function() {
		$(this).slideUp(300);
	});
});

/*弹出框开始*/
function showAlert() {

	$(".iframe").css("z-index", "300");
	$(".left").css("z-index", "260");
	

}

function hideAlert() {

	$(".iframe").css("z-index", "240");
	$(".left").css("z-index", "260");


}
/*弹出框结束*/

