//获取属性值
function getAlreadySetSkuVals2(){
		var b = true;
		var skuTypeArr =  [];//存放SKU类型的数组
		//获取元素类型
		$(".SKU_TYPE").each(function(){
			//SKU类型节点
			var skuTypeNode = $(this).children("li");
			var skuTypeObj = {};//sku类型对象
			//SKU属性类型标题
			skuTypeObj.skuTypeTitle = $(skuTypeNode).attr("sku-type-name");
			//SKU属性类型主键
			var propid = $(skuTypeNode).attr("propid");
			skuTypeObj.skuTypeKey = propid;
			skuValueArr = [];//存放SKU值得数组
			//SKU相对应的节点
			var skuValNode = $(this).next();
			//获取SKU值
			var skuValCheckBoxs = $(skuValNode).find("input[type='checkbox'][class*='sku_value']");
			$(skuValCheckBoxs).each(function(){
					var skuValObj = {};//SKU值对象
					skuValObj.skuValueTitle = $(this).val();//SKU值名称
					skuValObj.skuValueId = $(this).attr("propvalid");//SKU值主键
					skuValObj.skuPropId = $(this).attr("propid");//SKU类型主键
					skuValueArr.push(skuValObj);
				
			});
			if(skuValueArr && skuValueArr.length > 0){
				skuTypeObj.skuValues = skuValueArr;//sku值数组
				skuTypeObj.skuValueLen = skuValueArr.length;//sku值长度
				skuTypeArr.push(skuTypeObj);//保存进数组中
			}
		});
alert("商品属性"+JSON.stringify(skuTypeArr));
}




function gettype(){
	var b = true;
	var skuTypeArr =  [];//存放SKU类型的数组
	$(".SKU_TYPEproduct").each(function(){
			//SKU类型节点
			var skuTypeNode = $(this).children("li");
			var skuTypeObj = {};//sku类型对象
			//SKU属性类型标题
			skuTypeObj.skuTypeTitle = $(skuTypeNode).attr("sku-type-name");
			//SKU属性类型主键
			var propid = $(skuTypeNode).attr("propid");
			skuTypeObj.skuTypeKey = propid;
			//是否是必选SKU 0：不是；1：是；
			var is_required = $(skuTypeNode).attr("is_required");
			skuValueArr = [];//存放SKU值得数组
			//SKU相对应的节点
			var skuValNode = $(this).next();
			//获取SKU值
			var skuValCheckBoxs = $(skuValNode).find("input[type='checkbox'][class*='sku_value1']");
			var checkedNodeLen = 0 ;//选中的SKU节点的个数
			$(skuValCheckBoxs).each(function(){
				if($(this).is(":checked")){ //判断是否选中
					var skuValObj = {};//SKU值对象
					skuValObj.skuValueTitle = $(this).val();//SKU值名称
					skuValObj.skuValueId = $(this).attr("propvalid");//SKU值主键
					skuValObj.skuPropId = $(this).attr("propid");//SKU类型主键
					skuValueArr.push(skuValObj);
					checkedNodeLen ++ ;
				}
			});
			if(is_required && "1" == is_required){//必选sku
				if(checkedNodeLen <= 0){//有必选的SKU仍然没有选中
					b = false;
					return false;//直接返回
				}
			}
			if(skuValueArr && skuValueArr.length > 0){
				skuTypeObj.skuValues = skuValueArr;//sku值数组
				skuTypeObj.skuValueLen = skuValueArr.length;//sku值长度
				skuTypeArr.push(skuTypeObj);//保存进数组中
			}
		});
alert("分类属性"+JSON.stringify(skuTypeArr));
}

/**
 * 获取已经设置的SKU值 
 */
function getAlreadySetSkuVals1(){
	var shopId = $("#shopId").val();
	var alreadySetSkuVals1 = [];//已经设置的SKU值数据
	alreadySetSkuVals = {};
	//获取设置的SKU属性值
	$("tr[class*='sku_table_tr']").each(function(){
		var skuPrice = $(this).find("input[type='text'][class*='setting_sku_price']").val();//SKU价格
		var skuPrice1 = $(this).find("input[type='text'][class*='setting_sku_price1']").val();//SKU价格
		var skuStock = $(this).find("input[type='text'][class*='setting_sku_stock']").val();//SKU库存
		if(skuPrice || skuStock){//已经设置了全部或部分值
			var propvalids = $(this).attr("propvalids");//SKU值主键集合
			alreadySetSkuVals = {
				"skuId":propvalids,
				"skuPrice" : skuPrice,
				"skuPrice1":skuPrice1,
				"skuStock" : skuStock
			}
			alreadySetSkuVals1.push(alreadySetSkuVals);
		}
	});
	
	alert(JSON.stringify(alreadySetSkuVals1));

}

$(function(){
	$("#submit").click(function(){
	    gettype();
		getAlreadySetSkuVals2();
		/*getAlreadySetSkuVals1();*/
		getAlreadySetSkuVals1();
});
})