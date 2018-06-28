$(function(){

	var categoryOne = "/shopadmin/getcategorylistone";
	var categoryTwo = "/shopadmin/getcategorylisttwo";
	var categoryThree = "/shopadmin/getcategorylistthree";

	getCategoryListOne();

	$(".categoryOne").on("click",".one",function(){
		$(this).css("background-color","red");
		$(this).siblings.css("background-color","white");
		var context = $(this).html();
		var id = $(this).attr("data-id");
		$(".msg").html(context);
		$(".msg").attr("oneId",id);
		getCategoryListTwo(id);
	});

	$(".categoryTwo").on("click",".two",function(){
		$(this).css("background-color","red");
		$(this).siblings.css("background-color","white");
		var context = "-->"+$(this).html();
		var id = $(this).attr("data-id");
		$(".msg").append(context);
		$(".msg").attr("twoId",id);
		getCategoryListThree(id);
	});

	$(".categoryThree").on("click",".three",function(){
		$(this).css("background-color","red");
		$(this).siblings.css("background-color","white");
		var context = "=>"+$(this).html();
		var id = $(this).attr("data-id");
		$(".msg").attr("threeId",id);
		$(".msg").append(context);
	});

	$(".tiao").click(function(){
		var context = $(".msg").html();
		if(context==null || context ==""){
			return false;
		}
		var oneId=$(".msg").attr("oneId");
		var twoId=$(".msg").attr("twoId");
		var threeId=$(".msg").attr("threeId");
		location.href="goaddproject?context="+context+"&oneId="
			+oneId+"&twoId="+twoId+"&threeId="+threeId;
	});

	function getCategoryListOne(){
		
		$.getJSON(categoryOne,function(data){

				var oneCategory="";
				data.oneList.map(function(item,index){
					oneCategory +='<li class="one" data-id="'+item.id
						+'">'+item.categoryName+'</li>';
				});
				$(".categoryOne").html(oneCategory);
		});	
	}

	function getCategoryListTwo(id){
		
		$.getJSON(categoryTwo,id,function(data){

				var twoCategory="";
				data.twoList.map(function(item,index){
					twoCategory +='<li class="two" data-id="'+item.id
						+'">'+item.categoryName+'</li>';
				});
				$(".categoryTwo").html(twoCategory);
		});	
	}

	function getCategoryListThree(id){
		
		$.getJSON(categoryThree,id,function(data){

				var threeCategory="";
				data.threeList.map(function(item,index){
					threeCategory +='<li class="three" data-id="'+item.id
						+'">'+item.categoryName+'</li>';
				});
				$(".categoryThree").html(threeCategory);
		});	
	}
});