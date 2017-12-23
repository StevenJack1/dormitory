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
                    <h5>宿舍管理</h5>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <button type="button" class="btn btn-w-m btn-primary" data-toggle="modal"
                                data-target="#myModal1">添加宿舍
                        </button>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>宿舍楼名称</th>
                                <th>宿舍名称</th>
                                <th>宿舍类型</th>
                                <th>床铺剩余数量</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="dormitoryInfoTable">
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
                <h4 class="modal-title">添加宿舍</h4>
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
                            <label class="col-sm-4 control-label" style="font-size: medium">宿舍号</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入宿舍号" name="dormitoryName"
                                       id="dormitoryName">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">宿舍类型</label>
                            <div class="col-sm-1">
                                <select class="input-sm   " title="请选择宿舍楼类型" id="dormitoryType">
                                    <option value="公寓类">公寓类</option>
                                    <option value="普通类">普通类</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-white" data-dismiss="modal" id="dormitoryCancelButton">取消</button>
                    <button type="button" class="btn btn-primary" id="dormitoryCreateButton">确认</button>
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
                <h4 class="modal-title">修改</h4>
            </div>
            <small class="font-bold">
                <div class="modal-body" align="center">
                    <form class="form-horizontal" role="form">

                        <div class="form-group">
                            <label class="col-sm-4 control-label" style="font-size: medium">宿舍楼</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入宿舍楼名称" name="modifyBuildName"
                                       id="modifyBuildName">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">宿舍号</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入宿舍号" name="modifyDormitoryName"
                                       id="modifyDormitoryName">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">宿舍类型</label>
                            <div class="col-sm-1">
                                <select class="input-sm   " title="请选择宿舍楼类型" id="modifyDormitoryType">
                                    <option value="公寓类">公寓类</option>
                                    <option value="普通类">普通类</option>
                                </select>
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
            $("#dormitoryInfoTable").empty();
            var result = data["results"];
            result.forEach(function (e) {
                $("#dormitoryInfoTable").append(`
                    <tr>
                    <td >`+e.buildNumber+`</td>
                    <td>`+e.dormitoryNumber+`</td>
                    <td>`+e.dormitoryType+`</td>
                    <td>`+e.bedNumber+`</td>
                    <td>
                        <a class="md-modify" data-toggle="modal" data-target="#myModal2" dormitoryInfoId = '`+e.id+`'>修改</a>
                        <a class="md-delete" dormitoryInfoId = '`+e.id+`'>删除</a>
                    </td>
                    </tr>
                    `);
            });
            $(".md-delete").click(function () {
                const dormitoryInfoId = $(this).attr("dormitoryInfoId");
                AjaxDeleteRequest("/DormitoryManagement/delete/dormitoryInfoId/" + dormitoryInfoId );
                loadThis();
            });
            $(".md-modify").click(function () {
                const dormitoryInfoId = $(this).attr("dormitoryInfoId");
                var success = function (data) {
                    $("#modifyBuildName").val(data.buildNumber);
                    $("#modifyDormitoryName").val(data.dormitoryNumber);
                    $("#modifyDormitoryType").val(data.dormitoryType);
                };
                Get("/DormitoryManagement/getTheDormitory/dormitoryInfoId/" + dormitoryInfoId,success);
                $("#modifyCreateButton").click(function () {
                    var buildName = $("#modifyBuildName").val();
                    var dormitoryName = $("#modifyDormitoryName").val();
                    var dormitoryType = $("#modifyDormitoryType").val();
                    if (isNullOrEmpty(buildName) || isNullOrEmpty(dormitoryName) || isNullOrEmpty(dormitoryType)) {
                        swal({
                            title: "错误",
                            text: "不可为空",
                            type: "error",
                            confirmButtonText: "知道了"
                        });
                    } else {
                        AjaxPost("/DormitoryManagement/modify/dormitoryInfoId/"+ dormitoryInfoId + "/buildName/" + buildName + "/dormitoryName/" + dormitoryName + "/dormitoryType/" + dormitoryType);
                        $("#modifyCancelButton").click();
                        loadThis();
                    }
                });
            });
        };
        Paging("/DormitoryManagement/getDormitory", "dormitoryInfoTable", uploadTable, pageNumber, 10);
    };

    //新增宿舍
    $("#dormitoryCreateButton").click(function () {
        var buildName = $("#buildName").val();
        var dormitoryName = $("#dormitoryName").val();
        var dormitoryType = $("#dormitoryType").val();
        if (isNullOrEmpty(buildName) || isNullOrEmpty(dormitoryName) || isNullOrEmpty(dormitoryType)) {
            swal({
                title: "错误",
                text: "不可为空",
                type: "error",
                confirmButtonText: "知道了"
            });
        } else {
            AjaxPostRequest("/DormitoryManagement/createDormitory/buildName/" + buildName + "/dormitoryName/" + dormitoryName + "/dormitoryType/" + dormitoryType);
            $("#dormitoryCancelButton").click();
            loadThis();
        }
    });
    $(document).ready(function () {
        loadPage(1);
    });
</script>
