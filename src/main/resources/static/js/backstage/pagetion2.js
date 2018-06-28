// JavaScript Document
$(function() {
	var dataGrid = function(ele, opt) {
		this.defaults = {
			//id
			id: "",
			//请求url
			url: null,
			//扩展参数
			data: null,
			//表头格式
			columns: null,
			//是否分页
			pagination: false,
			//总页数
			totalpage: null,
			//总记录数
			totalcount: null

		}
		this.settings = $.extend({}, this.defaults, opt);
	}

	dataGrid.prototype = {
		_id: null,
		_op: null,
		init: function() {
			this._id = this.settings.id;
			_op = this;
			this.create();
			this.bindEvent();
		},
		create: function() {
			//初始化元素
			this.InitializeElement();
			//初始化表头
			this.createTableHead();
			//初始化动态行
			this.createTableBody(3);
			//选择是否分页
			if(this.settings.pagination) this.createTableFoot();
		},
		bindEvent: function() {

			//添加表格行单击事件
			this.resisterTrOnclick();
		},
		//初始化元素
		InitializeElement: function() {
			//var id = this.settings.id;
			$("#" + this._id).empty().append("<thead><tr></tr></thead><tbody></tbody>");
		},
		//循环添加表头
		createTableHead: function() {
			var headcols = this.settings.columns;
			for(var i = 0; i < headcols.length; i++) {
				if(headcols[i].field == 'ck') $("table[id='" + this._id + "'] thead tr").append("<th width='30px'></th>");
				else $("table[id='" + this._id + "'] thead tr").append("<th width=" + headcols[i].width + " align=" + headcols[i].align + ">" + headcols[i].title + "</th>");
			}
		},
		//循环添加行
		createTableBody: function(pn) {
			var columns = _op.settings.columns;
			var operations = _op.settings.operations;
			var pagecount = _op.settings.pagecount;
			var json = this.getAjaxDate(_op.settings.url, _op.settings.data);

			//总记录数
			_op.settings.totalcount = json.total;
			//总页数=向上取整(总数/每页数)
			_op.settings.totalpage = Math.ceil((json.total) / _op.settings.data.pagesize);

			var rowsdata = "";
			for(var rowindex = 0; rowindex < json.rows.length; rowindex++) {
				if(rowindex == json.rows.length) break;
				rowsdata += "<tr>";
				for(var colindex = 0; colindex < columns.length; colindex++) {
					var rowId; //编号
					if(columns[colindex].field == 'ID') {
						rowId = json.rows[rowindex][columns[colindex].field];

					}
					if(columns[colindex].field == 'ck') {
						rowsdata += '<td width="30" align="center"><input name="chk" type="checkbox"></td>'
					} else {
						if(columns[colindex].field != 'Operation') {

							rowsdata += '<td width=' + columns[colindex].width + ' align=' + columns[colindex].align + '>' + json.rows[rowindex][columns[colindex].field] + '</td>';
						} else {
							rowsdata += '<td>'
							for(var r = 0; r < operations.length; r++) {

								rowsdata += '<span onclick="' + operations[r].fun + '(' + rowId + ');">' + operations[r].btn + '</span>';
							}
							rowsdata += '</td>'
						}
					}

				}

				rowsdata += "</tr>";
			}
			$("table[id='" + this._id + "'] tbody").empty().append(rowsdata);
			$("#currentpageIndex").html(pn);

		},
		//初始化分页
		createTableFoot: function() {
			var index = _op.settings.data.pageindex;
			var pageCount = _op.settings.totalpage;
			var totalCount = _op.settings.totalcount;
			$(".pageStyle").empty();
			var pageInfo = "";
			pageInfo += "<div class='pagecentent'>" +
				"<span class='txtLeft'>共计：<span class='pagefont'>" + totalCount + "</span>条记录，共<span class='pagefont'>" + pageCount + "</span>页</span>" +
				"</div>" +
				"<div class='searchPage'>" +
				"<div class='txt'>G0&nbsp;</div>" +
				"<input type='text' name='pageNo'/>" +
				"<img src='images/fdj.png' class='imgSearch' />" +
				"</div>" +
				"<div class='page' id='page'>&nbsp;</div>";
			$(".pageStyle").append(pageInfo);
			//

			$("#page").empty();

			$("#page").append("<span><a href=' javascript:onclick=show(1)'>首页</a></span>");
			var iqishi = 1;
			if(pageCount >= 8) {
				if((index + 4) >= pageCount) {
					iqishi = pageCount - 8;
				} else if(index >= 5) {
					iqishi = index - 4;
				}
			}
			if(totalCount != 0) {
				if(index != 1) {
					$("#page").append("<span><a href=' javascript:onclick=show(" + (index - 1) + ")'>&lt;&lt;</a></span>");
				}
				for(var i = iqishi; i <= iqishi + 8; i++) {
					if(i == index) {
						$("#page").append("<span id='active'><a href=' javascript:onclick=show(" + i + ")' >" + i + "</a></span>");
					} else {
						$("#page").append("<span><a href=' javascript:onclick=show(" + i + ")'>" + i + "</a></span>");

					}
					if(i > pageCount - 1) {
						break;
					}
				}
				if(index != pageCount) {
					$("#page").append("<span><a href=' javascript:onchange=show(" + (index + 1) + ")'>&gt;&gt;</a></span>");
				}
			}
			$("#page").append("<span class='endPage'><a href=' javascript:onclick=show(" + pageCount + ")'>末页</a></span>");

		},
		//添加表格行单击事件
		resisterTrOnclick: function() {

			/*给未来元素绑定单击事件，单击表格选中一行开始*/
			$("#" + _op.settings.id).on("click", "tr", function() {

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
		},
		//添加异步ajax事件
		getAjaxDate: function(url, param) {

			//定义一个全局变量来接受$post的返回值
			var result;
			//用ajax的同步方式
			$.ajax({
				url: url,
				async: false,
				//改为同步方式
				data: param,
				success: function(data) {
					result = data;
				}
			});
			return result;
		}

	}

	$.fn.grid = function(options) {
		var grid = new dataGrid(this, options);
		return this.each(function() {
			grid.init();
		});
	}
})

/*
 * 分页选显示条数，以及跳页
 */
$(function() {
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