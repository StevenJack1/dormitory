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
                                <th>学号</th>
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
                <h4 class="modal-title">创建学生</h4>
            </div>
            <small class="font-bold">
                <div class="modal-body" align="center">

                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-4 control-label" style="font-size: medium">用户名</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入用户名" name="username"
                                       id="username">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">姓名</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入姓名" name="name"
                                       id="name">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">年龄</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入年龄" name="age"
                                       id="age">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">学院</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入学院" name="college"
                                       id="college">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">专业</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入专业" name="profession"
                                       id="profession">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">班级</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入班级" name="classNumber"
                                       id="classNumber">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">籍贯</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入籍贯" name="nativePlace"
                                       id="nativePlace">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">密码</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入密码" name="passWord"
                                       id="passWord">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">手机号</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入手机号" name="phoneNumber"
                                       id="phoneNumber">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">学号</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入学号" name="studentOrDormitoryNumber"
                                       id="studentOrDormitoryNumber">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">宿舍楼</label>
                            <div class="col-sm-6">
                                <select class="input-sm   " title="请选择宿舍楼" id="buildName" style="width: 100%">
                                    <option value="梅一">梅一</option>
                                    <option value="梅二">梅二</option>
                                    <option value="梅三">梅三</option>
                                    <option value="梅四">梅四</option>
                                    <option value="梅五">梅五</option>
                                    <option value="梅六">梅六</option>
                                    <option value="梅七">梅七</option>
                                </select>
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">宿舍号</label>
                            <div class="col-sm-6">
                                <select class="input-sm   " title="请选择宿舍号" id="dormitoryName" style="width: 100%">
                                    <option value="327">327</option>
                                    <option value="328">328</option>
                                    <option value="329">329</option>
                                </select>
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">性别</label>
                            <div style="size: 25px" id="sex" class="col-sm-3">
                                <input type="radio" name="sex" value="男" checked="checked" style="height: 18px;width: 20px;margin: 10px" onclick="getValue()"/><span style="font-size: 18px">男</span>
                                <input type="radio" name="sex" value="女" style="height: 18px;width: 20px;margin: 10px" onclick="getValue()"/><span style="font-size: 18px">女</span>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-white" data-dismiss="modal" id="CancelButton">取消</button>
                            <button type="button" class="btn btn-primary" id="CreateButton">确认</button>
                        </div>

                    </form>
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
                <h4 class="modal-title">创建学生</h4>
            </div>
            <small class="font-bold">
                <div class="modal-body" align="center">

                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-4 control-label" style="font-size: medium">姓名</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入姓名" name="modifyname"
                                       id="modifyname">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">年龄</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入年龄" name="modifyage"
                                       id="modifyage">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">学院</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入学院" name="modifycollege"
                                       id="modifycollege">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">专业</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入专业" name="modifyprofession"
                                       id="modifyprofession">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">班级</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入班级" name="modifyclassNumber"
                                       id="modifyclassNumber">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">籍贯</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入籍贯" name="modifynativePlace"
                                       id="modifynativePlace">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">手机号</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入手机号" name="modifyphoneNumber"
                                       id="modifyphoneNumber">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">学号</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="请输入学号" name="modifystudentOrDormitoryNumber"
                                       id="modifystudentOrDormitoryNumber">
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">宿舍楼</label>
                            <div class="col-sm-6">
                                <select class="input-sm   " title="请选择宿舍楼" id="modifybuildName" style="width: 100%">
                                    <option value="梅一">梅一</option>
                                    <option value="梅二">梅二</option>
                                    <option value="梅三">梅三</option>
                                    <option value="梅四">梅四</option>
                                    <option value="梅五">梅五</option>
                                    <option value="梅六">梅六</option>
                                    <option value="梅七">梅七</option>
                                </select>
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">宿舍号</label>
                            <div class="col-sm-6">
                                <select class="input-sm   " title="请选择宿舍号" id="modifydormitoryName" style="width: 100%">
                                    <option value="327">327</option>
                                    <option value="328">328</option>
                                    <option value="329">329</option>
                                </select>
                            </div>
                            <label class="col-sm-4 control-label" style="font-size: medium">性别</label>
                            <div style="size: 25px" id="modifysex" class="col-sm-3">
                                <input type="radio" name="sex" value="男" checked="checked" style="height: 18px;width: 20px;margin: 10px" onclick="getValue()"/><span style="font-size: 18px">男</span>
                                <input type="radio" name="sex" value="女" style="height: 18px;width: 20px;margin: 10px" onclick="getValue()"/><span style="font-size: 18px">女</span>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-white" data-dismiss="modal" id="modifyCancelButton">取消</button>
                            <button type="button" class="btn btn-primary" id="modifyCreateButton">确认</button>
                        </div>

                    </form>
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

    $(document).ready(function () {
        var sex= $("input[type='radio'][name='sex']:checked").val();
        $("#sex").val(sex);
    });
    function getValue() {
        $("#sex").val("");
        $("input[type='radio'][name='sex']:checked").attr("checked",true);
        var sex= $("input[type='radio'][name='sex']:checked").val();
        $("#sex").val(sex);
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
                                <input type='checkbox' class='checkMe' id='`+e.userName+`' value='option1'/>
                            </div>
                        </td>
                        <td>`+ e.name +`</td>
                        `+(
                            (e.sex == true)
                                ?
                                `<td>男</td>`
                                :
                                `<td>女</td>`
                        )+`
                        <td>`+ e.age +`</td>
                        <td>`+ e.studentOrDormitoryNumber +`</td>
                        <td>`+ e.nativePlace +`</td>
                        <td>`+ e.phoneNumber +`</td>
                        <td>`+ e.college +`</td>
                        <td>`+ e.profession +`</td>
                        <td>`+ e.classNumber +`</td>
                        <td>`+ e.dormitoryInfo.buildNumber +`</td>
                        <td>`+ e.dormitoryInfo.dormitoryNumber +`</td>
                    </tr>
                `);
            });
            CheckMe();
        };
        Paging("/StudentManagement/getStudentList", "CourierTable", uploadTable, pageNumber, 10);

    };

    //新增
    $("#CreateButton").click(function () {
        if ($("#sex").val() == "男"){
            var sex = true;
        } else {
            var sex = false;
        }
        var data = {
            userName:$("#username").val(),
            name:$("#name").val(),
            age:$("#age").val(),
            college:$("#college").val(),
            profession:$("#profession").val(),
            classNumber:$("#classNumber").val(),
            nativePlace:$("#nativePlace").val(),
            passWord:$("#passWord").val(),
            phoneNumber:$("#phoneNumber").val(),
            studentOrDormitoryNumber:$("#studentOrDormitoryNumber").val(),
            dormitoryInfo:{
                buildNumber:$("#buildName").val(),
                dormitoryNumber:$("#dormitoryName").val()
            },
            sex: sex
        };
        if (isNullOrEmpty(data)) {
            swal({
                title: "错误",
                text: "不可为空",
                type: "error",
                confirmButtonText: "知道了"
            });
        } else {
            Post("/StudentManagement/createStudent",data);
            $("#CancelButton").click();
            $("#username").val("");
            $("#name").val("");
            $("#age").val("");
            $("#college").val("");
            $("#profession").val("");
            $("#classNumber").val("");
            $("#nativePlace").val("");
            $("#passWord").val("");
            $("#phoneNumber").val("");
            $("#studentOrDormitoryNumber").val("");
            loadThis();
        }
    });

    $("#editButton").click(function () {
        var id = $("input[class='checkMe']:checked").attr("id");
        var success = function (data) {
            $("#modifyuserName").val(data.userName);
            $("#modifyname").val(data.name)
            $("#modifyage").val(data.age);
            $("#modifycollege").val(data.college);
            $("#modifyprofession").val(data.profession);
            $("#modifyclassNumber").val(data.classNumber);
            $("#modifynativePlace").val(data.nativePlace);
            $("#modifyphoneNumber").val(data.phoneNumber);
            $("#modifystudentOrDormitoryNumber").val(data.studentOrDormitoryNumber);
            $("#modifybuildName").val(data.dormitoryInfo.buildNumber);
            $("#modifydormitoryName").val(data.dormitoryInfo.dormitoryNumber);
        };
        Get("/StudentManagement/getInfo/id/" + id , success);
    });

    //修改
    $("#modifyCreateButton").click(function () {
        var id = $("input[class='checkMe']:checked").attr("id");
        if (isNullOrEmpty(id)) {
            swal({
                title: "错误",
                text: "必填项不可为空",
                type: "error",
                confirmButtonText: "知道了"
            });
        } else {
            if ($("#sex").val() == "男"){
                var sex = true;
            } else {
                var sex = false;
            }
            var data = {
                userName:id,
                name:$("#modifyname").val(),
                age:$("#modifyage").val(),
                college:$("#modifycollege").val(),
                profession:$("#modifyprofession").val(),
                classNumber:$("#modifyclassNumber").val(),
                nativePlace:$("#modifynativePlace").val(),
                passWord:$("#modifypassWord").val(),
                phoneNumber:$("#modifyphoneNumber").val(),
                studentOrDormitoryNumber:$("#modifystudentOrDormitoryNumber").val(),
                dormitoryInfo:{
                    buildNumber:$("#modifybuildName").val(),
                    dormitoryNumber:$("#modifydormitoryName").val()
                },
                sex: sex
            };
            $.ajax({
                url:"/StudentManagement/modifyStudent",
                type:"POST",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function () {
                    swal({
                        title: "修改成功",
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
            $("#modifyCancelButton").click();
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
                        AjaxDeleteRequest("/StudentManagement/deleteStudent/id/" + checkBoxes[i].id);
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
