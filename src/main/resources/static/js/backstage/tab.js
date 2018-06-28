// JavaScript Document
$(function(){
	$(".tabs span").click(function(){
			$(this).addClass("spanHover");
			$(this).siblings().removeClass("spanHover");
			$(this).siblings().addClass("spanOut");
			$(".divTable").hide();
			$(".divTable").eq($(this).index()).show();
	});
	
});