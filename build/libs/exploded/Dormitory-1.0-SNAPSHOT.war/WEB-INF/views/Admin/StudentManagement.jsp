<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2017/12/23
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>学生管理</h5>
                </div>
                <div class="ibox-content">
                    <div class="row">

                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal1">新建
                        </button>
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#myModal2"
                                id="editButton" disabled="disabled">修改
                        </button>
                        <button type="button" class="btn btn-danger" id="deleteButton" disabled="disabled">删除</button>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>选择</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>年龄</th>
                                <th>籍贯</th>
                                <th>联系方式</th>
                                <th>学院</th>
                                <th>专业</th>
                                <th>班级</th>
                                <th>住宿楼号</th>
                                <th>宿舍号</th>
                            </tr>
                            </thead>
                            <tbody id="CourierTable">
                            </tbody>
                        </table>
                    </div>
                </div>
                <ul class="pagination" id="pagination"></ul>
            </div>
        </div>
    </div>
</div>
<%--弹窗新增--%>
<div class="modal inmodal fade in" id="myModal1" tabindex="-1" role="dialog" aria-hidden="true"
     style="display: none ; padding-right: 17px;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">新建快递类型</h4>
            </div>
            <small class="font-bold">
                <div class="modal-body" align="center">
                    <input type="text" placeholder="请输入快递类型名称" class="form-control" name="min" id="content" style="
                width: 8cm;">
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal" id="createCancelButton">关闭</button>
                    <button type="button" class="btn btn-primary" id="createButton">保存</button>
                </div>
            </small>
        </div>
        <small class="font-bold">
        </small>
    </div>
    <small class="font-bold">
    </small>
</div>
<%--弹窗修改--%>
<div class="modal inmodal fade in" id="myModal2" tabindex="-1" role="dialog" aria-hidden="true"
     style="display: none ; padding-right: 17px;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">新建快递类型</h4>
            </div>
            <small class="font-bold">
                <div class="modal-body" align="center">
                    <input type="text" placeholder="请输入快递类型名称" class="form-control" name="min" id="updateDescription" style="
                width: 8cm;">
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal" id="updateCancelButton">取消</button>
                    <button type="button" class="btn btn-primary" id="updateSubmitButton">确认</button>
                </div>
            </small>
        </div>
        <small class="font-bold">
        </small>
    </div>
    <small class="font-bold">
    </small>
</div>

<script type="text/javascript">
    function updateButton() {
        if ($("input[class='checkMe']:checked").length == 1) {
            $('#editButton').removeAttr("disabled");
        } else {
            $('#editButton').attr('disabled', "true");
        }
    }
    function deleteButton() {
        if ($("input[class='checkMe']:checked").length) {
            $('#deleteButton').removeAttr("disabled");
        } else {
            $('#deleteButton').attr('disabled', "true");
        }
    }

    //让这两个button不可用
    function setUnAvailable() {
        $('#editButton').attr('disabled', "true");
        $('#deleteButton').attr('disabled', "true");
    }

    //为checkMe绑定点击事件 重新加载列表后需要重新绑定点击事件
    function CheckMe() {
        setUnAvailable();
        $(".checkMe").click(function () {
            updateButton();
            deleteButton();
        });
    }

    //分页加载页面
    var loadPage = function (pageNumber) {
        var uploadTable = function (data) {
            $("#CourierTable").empty();
            var resultList = data["results"];
            resultList.forEach(function (e) {
                $("#CourierTable").append(`
                    <tr>
                        <td>
                            <div class='icheckbox_square-green checked'>
                                <input type='checkbox' class='checkMe' id='`+e.id+`' value='option1'/>
                            </div>
                        </td>
                        <td>`+ e.companyName +`</td>
                    </tr>
                `);
            });
            CheckMe();
        };
        Paging("/CourierCompanyManagement/getCourierCompanyList", "CourierTable", uploadTable, pageNumber, 10);

    };

    //新增
    $("#createButton").click(function () {
        var content = $("#content").val();
        if (isNullOrEmpty(content)) {
            swal({
                title: "错误",
                text: "不可为空",
                type: "error",

                confirmButtonText: "知道了"
            });
        } else {
            AjaxPostRequest("/CourierCompanyManagement/createCourier/companyName/" + content);
            loadThis();
            $("#createCancelButton").click();
            $("#content").val("");
        }
    });

    //修改时给input设置值
    $("#editButton").click(function () {
        var id = $("input[class='checkMe']:checked").attr("id");
        var thisElement = $("#" + id).parent().parent().next();
        $("#updateDescription").val(thisElement.text());
    });

    //修改
    $("#updateSubmitButton").click(function () {
        var updateDescriptionVal = $("#updateDescription").val();
        var id = $("input[class='checkMe']:checked").attr("id");
        if (isNullOrEmpty(updateDescriptionVal)) {
            swal({
                title: "错误",
                text: "必填项不可为空",
                type: "error",
                confirmButtonText: "知道了"
            });
        } else {
            AjaxPutRequest("/CourierCompanyManagement/updateCourierCompany/id/" + id + "/updateDescription/" + updateDescriptionVal);
            $("#updateCancelButton").click();
            loadThis();
        }
        setUnAvailable();
    });

    //删除
    $('#deleteButton').click(function () {
        swal({
                title: "确定？",
                text: "你确定删除吗？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "删除",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    var checkBoxes = $("input[class='checkMe']:checked");
                    for (var i = 0; i < checkBoxes.length; i++) {
                        AjaxDeleteRequest("/CourierCompanyManagement/deleteCourierCompany/id/" + checkBoxes[i].id);
                    }
                    loadThis();
                } else {
                    swal("已取消", "未作任何操作", "info");
                    setUnAvailable()
                }
            });
    });

    $(document).ready(function () {
        loadPage(1);
    });
</script>
