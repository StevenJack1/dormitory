<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2017/12/25
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>访客登记管理</h5>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <button type="button" class="btn btn-w-m btn-primary" data-toggle="modal"
                                data-target="#myModal1">添加来访人员
                        </button>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>宿舍楼</th>
                                <th>访客姓名</th>
                                <th>访问对象</th>
                                <th>来访事由</th>
                                <th>来访时间</th>
                                <th>计划离开时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="vistorInfoTable">
                            </tbody>
                        </table>
                    </div>
                    <ul class="pagination" id="pagination"></ul>
                </div>

            </div>
        </div>
    </div>
</div>
<%--弹窗添加宿舍楼--%>
<div class="modal inmodal fade in" id="myModal1" tabindex="-1" role="dialog" aria-hidden="true"
     style="display: none ; padding-right: 17px;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">添加来访人员</h4>
            </div>
            <small class="font-bold">
                <div class="modal-body" align="center">
                    <form class="form-horizontal" role="form">

                        <div class="form-group">
                            <label class="col-sm-4 control-label" style="font-size: medium">宿舍楼</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入宿舍楼名称" name="buildName"
                                       id="buildName">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">访客姓名</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入访客姓名" name="visitorName"
                                       id="visitorName">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">访问对象</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入访问对象" name="otherName"
                                       id="otherName">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">来访事由</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入来访事由" name="cause"
                                       id="cause">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">计划离开时间</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入计划离开时间" name="end"
                                       id="end">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal" id="CancelButton">取消</button>
                    <button type="button" class="btn btn-primary" id="CreateButton">确认</button>
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
                <h4 class="modal-title">添加来访人员</h4>
            </div>
            <small class="font-bold">
                <div class="modal-body" align="center">
                    <form class="form-horizontal" role="form">

                        <div class="form-group">
                            <label class="col-sm-4 control-label" style="font-size: medium">宿舍楼</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入宿舍楼名称" name="modifybuildName"
                                       id="modifybuildName">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">访客姓名</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入访客姓名" name="modifyvisitorName"
                                       id="modifyvisitorName">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">访问对象</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入访问对象" name="modifyotherName"
                                       id="modifyotherName">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">来访事由</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入来访事由" name="modifycause"
                                       id="modifycause">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">计划离开时间</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入计划离开时间" name="modifyend"
                                       id="modifyend">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal" id="modifyCancelButton">取消</button>
                    <button type="button" class="btn btn-primary" id="modifyCreateButton">确认</button>
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
    //分页加载页面
    var loadPage = function (pageNumber) {
        var uploadTable = function (data) {
            $("#vistorInfoTable").empty();
            var result = data["results"];
            result.forEach(function (e) {
                $("#vistorInfoTable").append(`
                    <tr>
                    <td >`+e.buildName+`</td>
                    <td>`+e.visitorName+`</td>
                    <td>`+e.otherName+`</td>
                    <td>`+e.cause+`</td>
                    <td>`+getLocalTime(e.begin)+`</td>
                    <td>`+getLocalTime(e.end)+`</td>
                    <td>
                        <a class="md-modify" data-toggle="modal" data-target="#myModal2" visitorInfoId = '`+e.id+`'>修改</a>
                        <a class="md-delete" visitorInfoId = '`+e.id+`'>删除</a>
                    </td>
                    </tr>
                    `);
            });
            $(".md-delete").click(function () {
                const visitorInfoId = $(this).attr("visitorInfoId");
                AjaxDeleteRequest("/Visitor/delete/visitorInfoId/" + visitorInfoId );
                loadThis();
            });
            $(".md-modify").click(function () {
                const visitorInfoId = $(this).attr("visitorInfoId");
                var success = function (data) {
                    $("#modifybuildName").val(data.buildName);
                    $("#modifyvisitorName").val(data.visitorName);
                    $("#modifyotherName").val(data.otherName);
                    $("#modifycause").val(data.cause);
                    $("#modifyend").val(data.end);
                };
                Get("/Visitor/getTheVisitor/visitorInfoId/" + visitorInfoId,success);
                $("#modifyCreateButton").click(function () {
                    var modifyBuildName = $("#modifybuildName").val();
                    var modifyVisitorName = $("#modifyvisitorName").val();
                    var modifyOtherName = $("#modifyotherName").val();
                    var modifyCause = $("#modifycause").val();
                    var modifyEnd = $("#modifyend").val();
                    if (isNullOrEmpty(modifyBuildName) || isNullOrEmpty(modifyVisitorName) || isNullOrEmpty(modifyOtherName) || isNullOrEmpty(modifyCause) || isNullOrEmpty(modifyEnd)) {
                        swal({
                            title: "错误",
                            text: "不可为空",
                            type: "error",
                            confirmButtonText: "知道了"
                        });
                    } else {
                        AjaxPost("/Visitor/modify/visitorInfoId/"+ visitorInfoId + "/modifyBuildName/" + modifyBuildName + "/modifyVisitorName/" + modifyVisitorName + "/modifyOtherName/" + modifyOtherName + "/modifyCause/" + modifyCause + "/modifyEnd/" + modifyEnd);
                        $("#modifyCancelButton").click();
                        loadThis();
                    }
                });
            });
        };
        Paging("/Visitor/getVisitor","vistorInfoTable", uploadTable, pageNumber, 10);
    };

    //新增宿舍
    $("#CreateButton").click(function () {

        var buildName = $("#buildName").val();
        var visitorName = $("#visitorName").val();
        var otherName = $("#otherName").val();
        var cause = $("#cause").val();
        var end = $("#end").val();

        if (isNullOrEmpty(buildName) || isNullOrEmpty(visitorName) || isNullOrEmpty(otherName) || isNullOrEmpty(cause) || isNullOrEmpty(end)) {
            swal({
                title: "错误",
                text: "不可为空",
                type: "error",
                confirmButtonText: "知道了"
            });
        } else {
            AjaxPostRequest("/Visitor/create/buildName/" + buildName + "/visitorName/" + visitorName + "/otherName/" + otherName + "/cause/" + cause + "/end/" + end);
            $("#CancelButton").click();
            $("#buildName").val("");
            $("#visitorName").val("");
            $("#otherName").val("");
            $("#cause").val("");
            $("#end").val("");
            loadThis();
        }
    });
    $(document).ready(function () {
        loadPage(1);
    });
</script>
