$(function(){	
	var addressLeng = $(".ui-switchable-panel").length;
	
	var ds_prov = document.getElementById('ds_prov');
	var ds_city = document.getElementById('ds_city');
	if(addressLeng >= 3){
		var ds_area = document.getElementById('ds_area');
	}
	if(addressLeng == 4){
		var ds_town = document.getElementById('ds_town');
	}
	
	var ds_cities = null;
	var ds_areas = null;
	var ds_townList = null;
	
	for(var i=0; i<ds_citys.length; i++){
		ds_prov.innerHTML += "<li main-data='"+ds_citys[i]["value"]+"'><a href='javascript:void(0)'>"+ds_citys[i]["text"]+"</a></li>"; 
	}
	
//	鼠标进入指定区域显示地址选项框
	var ds_flag = false;//控制选项区域的显示隐藏
	$(".ds_area").on("mouseover",function(){
		ds_flag = true;
		$(this).attr("class","ds_area text-wrap-hover");
	})
	
	$(".ds_area").on("mouseout",function(){
		if(ds_flag){
			$(this).attr("class","ds_area");
			$(".ui-area-text-wrap").attr("class","ui-area-text-wrap");
		}else{
			$(".ui-area-text-wrap").attr("class","ui-area-text-wrap text-wrap-hover");
		}
	})
	
	
	//	点击省份里面具体li标签
	$("#ds_prov").on("click","li",function(){
		ds_flag = false;
		ds_cities = null;
		ds_areas = null;
		ds_townList = null;
		//		所点击的li所在的ui-switchable-panel
		var thePanel = $(this).parents(".ui-switchable-panel");

		//		该li对应的tab
		var theTab = $(".ui-area-tab a").eq(thePanel.index());
		
		//		切换当前及下一个页签的状态
		changeTab(theTab,$(this));
		//		修改省份后清空市,区,街道
		ds_city.innerHTML = "";
		if(addressLeng >= 3){
			ds_area.innerHTML = "";
		}
		if(addressLeng == 4){
			ds_town.innerHTML = "";
		}
		
		
		
		//		字体变红
		$(this).attr("class","ui-area-current").siblings().removeClass("ui-area-current");
		
		
		//		通过循环将所有省份的国标码与所选省份的国标码做对比
		for(var i=0; i<ds_citys.length; i++){
			if($("#ds_prov .ui-area-current").attr("main-data") == ds_citys[i]["value"]){
				//	找到所选省份后,根据i值确定需要加载的市
				ds_cities = ds_citys[i]["children"];
			}
		}
		
		//	将获取到的市添加到市的ul中
		for(var j=0; j<ds_cities.length; j++){
			ds_city.innerHTML += "<li main-data='"+ds_cities[j]["value"]+"'><a href='javascript:void(0)'>"+ds_cities[j]["text"]+"</a></li>";
		}
		
		changePanel(thePanel);
	})
	
//	点击市里面具体的li标签
	$("#ds_city").on("click","li",function(){
		ds_flag = false;
		ds_areas = null;
		ds_townList = null;
		
		//		所点击的li所在的ui-switchable-panel
		var thePanel = $(this).parents(".ui-switchable-panel");

		//		该li对应的tab
		var theTab = $(".ui-area-tab a").eq(thePanel.index());
		
		
		//		修改市后清空区,街道
		if(addressLeng >= 3){
			ds_area.innerHTML = "";
		}
		if(addressLeng == 4){
			ds_town.innerHTML = "";
		}
		
		//		字体变红
		$(this).attr("class","ui-area-current").siblings().removeClass("ui-area-current");
		
		//		通过循环将所有市的国标码与所选市的国标码做对比
		for(var i=0; i<ds_cities.length;i++){
			if($("#ds_city .ui-area-current").attr("main-data") == ds_cities[i]["value"]){
				//	找到所选市后,根据i值确定需要加载的区
				ds_areas = ds_cities[i]["children"];
				
			}
		}
		
		//		判断是否没有 区 信息，若没有直接到此终止
		if(!ds_areas || addressLeng < 3){
			theEnd($(this),2);
			return;
		}

		//		切换当前及下一个页签的状态
		changeTab(theTab,$(this));
		//		将获取到的市添加到市的ul中
		for(var j=0; j<ds_areas.length; j++){
			ds_area.innerHTML += "<li main-data='"+ds_areas[j]["value"]+"'><a href='javascript:void(0)'>"+ds_areas[j]["text"]+"</a></li>";
		}
		
		changePanel(thePanel);
		
	})
	
//	点击区里面具体的li标签
	$("#ds_area").on("click","li",function(){
		ds_flag = false;
		ds_townList = null;
		
		//		所点击的li所在的ui-switchable-panel
		var thePanel = $(this).parents(".ui-switchable-panel");

		//		该li对应的tab
		var theTab = $(".ui-area-tab a").eq(thePanel.index());
		
		//		修改区后清空街道
		if(addressLeng == 4){
			ds_town.innerHTML = "";
		}
		
		//		字体变红
		$(this).attr("class","ui-area-current").siblings().removeClass("ui-area-current");
		
		//		通过循环将所有区的国标码与所选区的国标码做对比
		for(var i=0; i<ds_areas.length;i++){
			if($("#ds_area .ui-area-current").attr("main-data") == ds_areas[i]["value"]){
				//				找到所选区后,根据i值确定需要加载的街道
				ds_townList = ds_areas[i]["children"];
				
			}
		}
		//		判断是否没有街道信息，若没有直接到此终止
		if(!ds_townList || addressLeng < 4){
			theEnd($(this),3);
			return;
		}

		//		切换当前及下一个页签的状态
		changeTab(theTab,$(this));
		//		将获取到的街道添加到街道的ul中
		for(var j=0; j<ds_townList.length; j++){
			ds_town.innerHTML += "<li main-data='"+ds_townList[j]["value"]+"'><a href='javascript:void(0)'>"+ds_townList[j]["text"]+"</a></li>";
		}
		
		$(".ui-area-tab a:last").show();
		
		changePanel(thePanel);
		
	})
	
//	点击街道里面具体的li标签
	$("#ds_town").on("click","li",function(){
		theEnd($(this),4);
	})
	
//	点击tab页签
	$(".ui-area-tab").on("click","a",function(){
		$(this).attr("class","ui-switchable-item ui-area-current").siblings().attr("class","ui-switchable-item");
//		点击页签,切换对应div
		$(".ui-area-content div").eq($(this).index()).attr("class","ui-switchable-panel ui-switchable-panel-selected").siblings().attr("class","ui-switchable-panel");
	});
//	点击叉号隐藏地址选择区域
	$(".ui-area-close").on("click",function(){
		$(".ds_area").removeClass("text-wrap-hover");
	})
	
	
	
/******以下为封装的函数**********/	
//		点击li后自动切换到下一个对应的ui-switchable-panel
	function changePanel(thePanel){
		thePanel.next().attr("class","ui-switchable-panel ui-switchable-panel-selected").siblings().attr("class","ui-switchable-panel");
	}
//	切换当前及下一个页签的状态
	function changeTab(theTab,objThis){
		var _this = objThis;
		
		//		给tab页签重新赋值
		theTab.find("em").html(_this.find("a").html());
		theTab.find('em').attr("main-data",_this.attr("main-data"));
		
		//		重置下一个页签,以及隐藏后边页签
		theTab.nextAll().find("em").removeAttr("main-data").html("请选择");
		theTab.next().show().nextAll().hide();
		
		
		theTab.css("display","block");
		theTab.attr("class","ui-switchable-item").next().attr("class","ui-switchable-item ui-area-current");
	}	
	
//	最终选项
	function theEnd(objThis,arearLength){
		ds_flag = true;
		var _this = objThis;
		//		所点击的li所在的ui-switchable-panel
		var thePanel = _this.parents(".ui-switchable-panel");

		//		该li对应的tab
		var theTab = $(".ui-area-tab a").eq(thePanel.index());
		
		//		给tab页签重新赋值
		theTab.find("em").html(_this.find("a").html());
		theTab.find('em').attr("main-data",_this.attr("main-data"));
		//		字体变红
		_this.attr("class","ui-area-current").siblings().removeClass("ui-area-current");
		
//		将所选结果写入指定位置
		var ds_arear_str = "";
		var ds_code_str = "";
		$(".ui-area-tab em[main-data]").each(function(i){
			if(i < arearLength - 1){
				ds_arear_str += $(this).html()+"/";
				ds_code_str += $(this).attr("main-data")+"/";
			}else{
				ds_arear_str += $(this).html();
				ds_code_str += $(this).attr("main-data");
			}
		})
		$(".ui-area-text-wrap span").attr("ds_code_str",ds_code_str).html(ds_arear_str);
		
		//		隐藏地址选项区
		$(".ds_area").removeClass("text-wrap-hover");
	}
	
})

