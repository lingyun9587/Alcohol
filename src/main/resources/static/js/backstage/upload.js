function up_load(isImg) {
	$("#uploadfile").html('');
	var uptext = "";

	uptext += "<div>";
	uptext += "<input type='text' class='textStyle' id='imgUrl' style='width:360px;' />";
	uptext += "</div>";
	uptext += "<div class='changefile'>";
	uptext += "<a href='#' class='fileStyle' title='请选择要上传的文件'>";
	uptext += "<input type='file' id='pic' name='pic' onchange='showPic()' class='file' size=1 />";
	uptext += "</a>";
	if(isImg) uptext += "<img width='80' height='80' class='img_yl'/>";
	uptext += "</div>";
	uptext += "<div>";
	uptext += "<span id='parent'>";
	uptext += "<span id='son'></span>";
	uptext += "</span>";
	uptext += "</div>";
	$("#uploadfile").append(uptext);
}

function showPic() {
	document.getElementById('imgUrl').value = document.getElementById('pic').value;
	var pic = $("#pic").get(0).files[0];
	$(".img_yl").prop("src", window.URL.createObjectURL(pic));

}

function uploadFile() {
	var pic = $("#pic").get(0).files[0];
	var formData = new FormData();
	formData.append("file", pic);
	/** 
	 * 必须false才会避开jQuery对 formdata 的默认处理 
	 * XMLHttpRequest会对 formdata 进行正确的处理 
	 */
	$.ajax({
		type: "POST",
		url: "upload",
		data: formData,
		processData: false,
		//必须false才会自动加上正确的Content-Type 
		contentType: false,
		xhr: function() {
			var xhr = $.ajaxSettings.xhr();
			if(onprogress && xhr.upload) {
				xhr.upload.addEventListener("progress", onprogress, false);
				return xhr;
			}
		}
	});
	$("#uptxt").text("文件上传成功！")
	//$("#uptxt").text("文件上传失败！")

}
/**
 * 侦查附件上传情况 ,这个方法大概0.05-0.1秒执行一次
 */
function onprogress(evt) {
	var loaded = evt.loaded; //已经上传大小情况 
	var tot = evt.total; //附件总大小 
	var per = Math.floor(100 * loaded / tot); //已经上传的百分比 
	$("#son").html(per + "%");
	$("#son").css("width", per + "%");
}