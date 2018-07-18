$(function() {

    /*给未来元素绑定单击事件，单击表格选中一行开始*/
    $("#tableData").on("click", "tr", function() {

        // 找到checkbox对象
        var chks = $("input[type='checkbox']", this);
        var tag = $(this).attr("tag");

        chks.prop("checked", true);
        $(this).siblings().children("td").children("input[type='checkbox']").prop("checked", false);

        if(tag == "selected") {
            // 之前已选中，设置为未选中
            $(this).siblings().children("td").removeClass("tr_active");
            chks.prop("checked", false);

        } else {
            // 之前未选中，设置为选中
            $(this).siblings().children("td").removeClass("tr_active");
            $(this).children("td").addClass("tr_active");
            chks.prop("checked", true);

        }
    });

    /*给未来元素绑定单击事件，单击表格选中一行结束*/

    // 跳页按钮被点击时
    $(document).on("click", ".imgSearch:visible", function() {
        // 获取表单值
        var ival = $(this).prev().val();
        if(isNaN(ival) || ival == "") {
            alert('请填写正确数字');
            $(this).prev().val("");
            return false;
        }
        $(this).prev().val(parseInt(ival));

        // 将小数转为整数
        show(parseInt(ival));
    });

});
/*分页*/
function   showPage(pageNum, pages, total) {
    $(".pageStyle").empty();
    var pageInfo = "";
    pageInfo += "<div class='pagecentent'>" +
        "<span class='txtLeft'>共计：<span class='pagefont'>" + total + "</span>条记录，共<span class='pagefont'>" + pages + "</span>页</span>" +
        "</div>" +
        "<div class='searchPage'>" +
        "<div class='txt'>G0&nbsp;</div>" +
        "<input type='text' name='pageNo'/>" +
        "<img src='/images/backstage/fdj.png' class='imgSearch' />" +
        "</div>" +
        "<div class='page' id='page'>&nbsp;</div>";
    $(".pageStyle").append(pageInfo);
    //

    $("#page").empty();

    $("#page").append("<span><a href=' javascript:onclick=show(1)'>首页</a></span>");
    var iqishi = 1;
    if(pages >= 8) {
        if((pageNum + 4) >= pages) {
            iqishi = pages - 8;
        } else if(pageNum >= 5) {
            iqishi = pageNum - 4;
        }
    }
    if(total != 0) {
        if(pageNum != 1) {
            $("#page").append("<span><a href=' javascript:onclick=show(" + (pageNum - 1) + ")'>&lt;&lt;</a></span>");
        }
        for(var i = iqishi; i <= iqishi + 8; i++) {
            if(i == pageNum) {
                $("#page").append("<span id='active'><a href=' javascript:onclick=show(" + i + ")' >" + i + "</a></span>");
            } else {
                $("#page").append("<span><a href=' javascript:onclick=show(" + i + ")'>" + i + "</a></span>");

            }
            if(i > pages - 1) {
                break;
            }
        }
        if(pageNum != pages) {
            $("#page").append("<span><a href=' javascript:onchange=show(" + (pageNum + 1) + ")'>&gt;&gt;</a></span>");
        }
    }
    $("#page").append("<span class='endPage'><a href=' javascript:onclick=show(" + pages + ")'>末页</a></span>");
}