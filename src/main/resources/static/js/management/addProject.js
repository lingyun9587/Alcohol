$(function(){
	var selectAllSortUrl = "/shopadmin/getallsort";	
	
	var categoryThreeId = $(".category03").val();

	selectAllSort(categoryThreeId);

	var i = 0;
	
	$(".hide").change(function(){
		if(i>2){
			alert("No more than 3");
			return;
		}
		i++;
		var file =document.getElementById("pic"+i);
		var imgUrl =window.URL.createObjectURL(file.files[0]);
		$(".tupian"+i).attr("src",imgUrl	);
		$(".tupian"+i).show();
	});

	$(".labelBtn2").click(function(){
		$(this).hide();
		$(this).next().show();
	});

	$("input[name=color]").click(function(){
		var neirong = $(this).val();
		var xiangqing = "<tr class='neirong'><td>"+neirong+"</td><td></td><td></td><td></td><td>"
					+"<input type='file' name='' ></td></tr>";
		if($("input[name=color]").prop("checked")){
		
			$(".biaoge").append(xiangqing);
		}else{
			$(".neirong").remove();
		}
			
		
	});

	$("input[name=size]").click(function(){
		
		var neirong
		$("input[name=color]:checked").each(function(){
			neirong = $(this).val();
		});
		
		if(neirong == undefined){
			return ;
		}
	});

	function selectAllSort(id){

		$.getJSON(selectAllSortUrl,id,function(data){

			var allsort="";
			data.sortList.map(function(item,index){
				allsort +='<dl><dt><span>'+item.name+'</span></dt><dd><div class="fbox">'
						+'<select class="textStyle" style="width:210px;">';
				
				allsort +='</select></div></dd></dl>';
			});
			$(".shuxing").html(allsort);
			/*data.sortconList.map(function(it,in){
					if(it.sortId == item.id){
						allsort +='<option value='+it.id+'>'+it.name+'</option>';
					}
				});*/
		});	
	}
});