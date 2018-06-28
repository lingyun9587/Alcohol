/*
 * JavaScript code For Login
 * 
 * @author : VIPArcher
 * @Email : VIPArcher@sina.com
 * @date : 20170527
 *
 *  Copyright 2017 VIPArcher
 */
function msgtemp(msg,className) {
	return `<div class="alert ${className} alert-dismissible fade in" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span></button>${msg}</div>`
};
(function($){
	$.fn.extend({
		/* 重置验证码发送按钮 */
		rewire: function (time){
			var $this = $(this);
			var time = time || 60;
			time -= 1;
			if (time == 0) {
				$this.removeAttr("disabled");
				$this.html("获取验证码");
			} else {
				$this.prop("disabled", true);
				$this.html("重新发送（{0}）".format(time));
				setTimeout(function() { $this.rewire(time) }, 1000);
			}
		},
		/*
		 * 验证手机号码
		 * 
		 * @return 0,1,2,3
		 *		0:验证成功; 1:内容为空;  2长度不为11位; 3:格式不对。
		 */
		validatemobile: function (){
			var num = $(this).val();
			if (num.length == 0) {
				$(this)[0].focus();
				return 1;
			/*} else if (num.length != 11) {
				$(this)[0].focus();
				return 2;*/
			} else {
				//手机号验证
				var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
				//邮箱验证
				var mailbox =/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;

				if(!reg.test(num) && !mailbox.test(num)) {
					$(this)[0].focus();
					return 3;
				} else {
					return 0;
				}
			}
		},
		/*
		 * 验证密码 $phone 被验证的输入框jQ对象
		 *
		 * 字母+数字，字母+特殊字符，数字+特殊字符，至少6位
		 * @return 0,1,2,3
		 *		0:验证成功; 1:内容为空; 2:长度过短; 3:格式不对。
		 */
		validatepwd: function (){
			var num = $(this).val();
			if (num.length == 0) {
				$(this)[0].focus();
				return 1
			} else if (num.length < 6) {
				$(this)[0].focus();
				return 2
			} else {
				var  reg = /^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/;
				if(!reg.test(num)) {
					$(this)[0].focus();
					return 3;return 3;
				} else {
					return 0;
				}
			}
		},
        /**
		 * 随机验证码  验证
		 * IsBy 获取随机验证码
		 * sms  输入的验证码
		 * 0:验证成功; 1:内容为空; 2:密码不正确
         * @returns {number}
         */
        login_sms: function (){
            var IsBy = $.idcode.validateCode();
            var sms= $("#login_sms").val();
			if(sms.length==0){
                return 1;
			}
            if(!IsBy){
                $("#login_sms").val("");
                return 2;
            }
        },
	});
})(jQuery);




$(document).ready(function() {
	// 隐藏/显示密码切换
	$('.pwd-toggle').on('click',function() {
		var icon = $(this).find('.glyphicon');
		if (icon.hasClass('glyphicon-eye-open')) {
			$(this).attr("title", "隐藏密码").siblings('input').prop('type', 'text');
			icon.removeClass('glyphicon-eye-open').addClass('glyphicon-eye-close');
		} else if (icon.hasClass('glyphicon-eye-close')) {
			$(this).attr("title", "显示密码").siblings('input').prop('type', 'password');
			icon.removeClass('glyphicon-eye-close').addClass('glyphicon-eye-open');
		}
	})
	$('#register').click(function() {$('.login').fadeOut(150,function() {$('.register').fadeIn(150)})});
	$('#resetpwd').click(function() {$('.login').fadeOut(150,function() {$('.resetpwd').fadeIn(150)})});
	$('#reglogin').click(function() {$('.register').fadeOut(150,function() {$('.login').fadeIn(150)})});
	$('#pwdlogin').click(function() {$('.resetpwd').fadeOut(150,function() {$('.login').fadeIn(150)})});
});
