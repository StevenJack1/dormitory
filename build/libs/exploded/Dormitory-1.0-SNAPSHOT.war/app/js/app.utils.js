// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function (fmt) {
    "use strict";
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

/**
 * 从datepicker格式化年月日
 * @param id
 */
var dateFormat = function (id) {
    "use strict";
    var date = $(('#' + id)).datepicker('getDate');
    if (isNullOrEmpty(date)) {
        return "";
    } else {
        return date.format("yyyy-MM-dd");
    }
};

/**
 * 是空或null或undefined嘛
 * @param value
 * @returns {boolean}
 */
var isNullOrEmpty = function (value) {
    "use strict";
    value = $.trim(value);

    if (!value && typeof value === "object") {
        return true;
    }

    if (value == "") {
        return true;
    }

    if (value == " ") {
        return true;
    }

    if (typeof(value) === "undefined") {
        return true;
    }
};

/**
 * 如果为Null或空就返回空字符串
 * @param data
 * @returns {*}
 */
var ifIsNullReturnEmptyString = function ifIsNullReturnEmptyString(data) {
    "use strict";
    if (isNullOrEmpty(data)) {
        return "";
    } else {
        return data;
    }
};

/**
 * 是不是数字？
 * @param value
 * @returns {*}
 */
var checkNaN = function (value) {
    "use strict";
    value = $.trim(value);

    if (isNaN(value) || isNullOrEmpty(value)) {
        return 0;
    } else {
        return value;
    }
};

/**
 * 分页
 * @param url url，不带 pageNumber和pageSize
 * @param tableId
 * @param updateTable
 * @param pageNumber
 * @param pageSize
 * @constructor
 */
var Paging = function Paging(url, tableId, updateTable, pageNumber, pageSize) {
    // "use strict";
    $(document).ajaxStart(function () {
        Pace.restart();
    });
    $.ajax({
        type: "Get",
        url: url + "/pageNumber/" + pageNumber + "/pageSize/" + pageSize,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            var pagination = $("#pagination");
            pagination.empty();
            var previousPage = data["previousPage"];
            var currentPage = data["currentPage"];
            var pageCount = data["pageCount"];
            var nextPage = data["nextPage"];
            nowPage=currentPage;
            //拼接上一页
            if (previousPage < 1 || previousPage >= currentPage) {//上一页不可达
                pagination.append("<li class='disabled paging'><a href='#' pageNumber='1'>&laquo;</a></li>");
            } else {
                pagination.append("<li class='paging'><a href='#' pageNumber=" + previousPage + ">&laquo;</a></li>");
            }
            //拼接页码
            for (var page = 1; page <= pageCount; page++) {
                if (page == currentPage) {
                    pagination.append("<li class='active paging'><a href='#' pageNumber=" + page + ">" + page + "</a></li>")
                } else {
                    pagination.append("<li class='paging'><a href='#' pageNumber=" + page + ">" + page + "</a></li>")
                }
            }

            //拼接下一页
            if (nextPage < 1 || nextPage <= currentPage) {//下一页不可达
                pagination.append("<li class='disabled paging'><a href='#' pageNumber=" + nextPage + ">&raquo;</a></li>");
            } else {
                pagination.append("<li class='paging'><a href='#' pageNumber=" + nextPage + ">&raquo;</a></li>");
            }

            $(".paging").click(function () {
                var selectedPageNumber = this.getElementsByTagName("a")[0].getAttribute("pageNumber");
                Paging(url, tableId, updateTable, selectedPageNumber, pageSize);
            });
            //清空table
            $(('#' + tableId)).empty();
            //更新table
            updateTable(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status!=200){
            swal({
                title: "出错了！",
                text: "错误信息" + XMLHttpRequest.status,
                type: "error",
                confirmButtonText: "知道了"
            });
            }

        }
    });
};


var Get = function (url,success) {
    "use strict";
    $.ajax({
        url:url,
        type:"GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success:function (data) {
            success(data);
        },
        error:function (XMLHttpRequest) {
        }
    });
};

/**
 * 通过GET添加
 * @param url
 * @param success
 * @constructor
 */
var AjaxGetRequest = function AjaxGetRequest(url, success) {
    "use strict";
    $.ajax({
        url: url,
        type: "GET",
        success: function (data) {
            success(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            swal({
                title: "出错了！",
                text: "错误信息" + XMLHttpRequest.status,
                type: "error",
                confirmButtonText: "知道了"
            });
        }
    });
};

/**
 * POST 请求
 * @param url
 * @param success
 * @constructor
 */
var AjaxPost = function AjaxPost(url, success) {
    "use strict";
    $.ajax({
        url: url,
        type: "POST",
        success: function () {
            success();
            swal({
                title: "修改成功",
                text: "修改成功",
                type: "success",
                confirmButtonText: "知道了"
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            swal({
                title: "出错了！",
                text: "错误信息" + XMLHttpRequest.status,
                type: "error",
                confirmButtonText: "知道了"
            });
        }
    });
};

/**
 * 通过POST添加
 * @param url
 * @constructor
 */
var AjaxPostRequest = function AjaxPostRequest(url) {
    "use strict";
    $.ajax({
        url: url,
        type: "POST",
        success: function () {
            swal({
                title: "发布成功",
                text: "发布成功",
                type: "success",
                confirmButtonText: "知道了"
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            swal({
                title: "出错了！",
                text: "错误信息" + XMLHttpRequest.status,
                type: "error",
                confirmButtonText: "知道了"
            });
        }
    });
};

/**
 * Post方法
 * @param url
 * @param data
 * @param success
 * @constructor
 */
var Post = function (url,data) {
    "use strict";

    $.ajax({
        url:url,
        type:"POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function () {
            swal({
                title: "添加成功",
                text: "添加成功",
                type: "success",
                confirmButtonText: "知道了"
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            swal({
                title: "出错了！",
                text: "错误信息" + XMLHttpRequest.status,
                type: "error",
                confirmButtonText: "知道了"
            });
        }
    });
};

/**
 * Put方法
 * @param url
 * @param data
 * @constructor
 */
var Put = function (url) {
    "use strict";

    $.ajax({
        url:url,
        type:"PUT",
        contentType: "application/json",
        success: function () {
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        }
    });
};
/**
 * 通过PUT修改
 * @param url
 * @constructor
 */
var AjaxPutRequest = function AjaxPutRequest(url) {
    "use strict";
    $.ajax({
        url: url,
        type: "PUT",
        success: function () {
            swal({
                title: "成功",
                text: "修改成功",
                type: "success",
                confirmButtonText: "知道了"
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            swal({
                title: "出错了！",
                text: "错误信息" + XMLHttpRequest.status,
                type: "error",
                confirmButtonText: "知道了"
            });
        }
    });
};

/**
 * 通过DELETE修改
 * @param url
 * @constructor
 */
var AjaxDeleteRequest = function AjaxDeleteRequest(url) {
    "use strict";
    $.ajax({
        url: url,
        type: "DELETE",
        success: function () {
            swal({
                title: "成功",
                text: "删除成功",
                type: "success",
                confirmButtonText: "知道了"
            });
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            swal({
                title: "出错了！",
                text: "错误信息" + XMLHttpRequest.status,
                type: "error",
                confirmButtonText: "知道了"
            });
        }
    });
};

/**
 * 异步加载页面
 * @param href
 * @constructor
 */
var AsynchronousLoading = function AsynchronousLoading(href) {
    "use strict";
    $(document).ajaxStart(function () {
        Pace.restart();
    });
    $.ajax({
        type: 'GET',
        url: href,
        success: function (msg) {
            $.ajaxSetup({
                async: false,
                cache: true
            });
            $("#mainPage").empty().append(msg);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            swal({
                title: "出错了！",
                text: "错误信息" + XMLHttpRequest.status,
                type: "error",
                confirmButtonText: "知道了"
            });
        }
    });
};


var AsynchronousLoading_childPage = function AsynchronousLoading_childPage(href) {
    "use strict";
    $(document).ajaxStart(function () {
        Pace.restart();
    });
    $.ajax({
        type: 'GET',
        url: href,
        success: function (msg) {
            $.ajaxSetup({
                async: false,
                cache: true
            });
            $("#freshChildPage").empty().append(msg);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            swal({
                title: "出错了！",
                text: "错误信息" + XMLHttpRequest.status,
                type: "error",
                confirmButtonText: "知道了"
            });
        }
    });
};


/**
 * 手动绑定跳转事件
 */
var bindRedirect = function bindRedirect() {
    "use strict";
    $(document).ajaxStart(function () {
        Pace.restart();
    });
    $(".redirect").click(function () {
        var attribute = this.getAttribute("url");
        AsynchronousLoading(attribute);
    });
};

/**
 * 手动绑定checkAll事件
 */
var bindCheckAll = function bindCheckAll() {
    "use strict";
    var checkAllBox = $("#checkAll");
    checkAllBox.bind("click", function () {
        var isChecked = checkAllBox.attr("checked");
        var checkMe = $(".checkMe");
        if (typeof isChecked === 'undefined') {
            checkAllBox.attr("checked", "checked");
            checkMe.each(function () {
                $(this).prop("checked", "checked");
            })
        } else {
            checkAllBox.removeAttr("checked");
            checkMe.each(function () {
                $(this).removeAttr("checked");
            })
        }
    });
};

/**
 * 为id为toAppend的div拼接提示通知
 * @param toAppend
 * @param title
 * @param content
 * @param buttonText
 * @param Url
 * @param type
 */
var appendNotify = function appendNotify(toAppend, title, content, buttonText, Url, type) {
    "use strict";
    //noinspection HtmlUnknownAttribute
    $("#" + toAppend).append(
        '<div class="alert alert-' + type + ' alert-dismissible fade in">' +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' +
        '<h4>' + title + '</h4>' +
        '<p>' + content + "&nbsp;&nbsp;&nbsp;" +
        '<button url="' + Url + '" type="button" class="btn btn-' + type + ' waves-effect waves-light redirect">' + buttonText + '</button>' +
        '</p>' +
        '</div>'
    );
};

/**
 * 加载学校
 */
var loadSchool = function (id) {
    var success = function (data) {
        $("#"+id).empty();
        for (var i = 0; i < data.length; i++) {
            var item = data[i];
            $("#"+id).append(
                '<option value="' + item.id +
                '"> ' + item.schoolName +
                '</option>'
            )
        }
    };
    AjaxGetRequest("/UserManagement/getSchoolList", success);
};


function loadThis() {
        loadPage(nowPage);
    }


var getLocalTime = function(nS) {
    if(nS!=null){
    var time = new Date(nS);
    var y = time.getFullYear();
    var m = time.getMonth() + 1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y+"-"+m+"-"+d+" "+h+":"+mm+":"+s;
    }else {
        return "";
    }
};
