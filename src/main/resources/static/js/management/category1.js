$(function () {
    var categoryUrl = "/CateOne/getcategory1list";
    function getCategoryListOne(){

        $.getJSON(categoryUrl,function(data){

            var oneCategory="";
            data.oneList.map(function(item,index){
                oneCategory +='<li class="one" data-id="'+item.id
                    +'">'+item.categoryName+'</li>';
            });
            $(".categoryOne").html(oneCategory);
        });
    }
})