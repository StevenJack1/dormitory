<%--
  Created by IntelliJ IDEA.
  User: Yzf
  Date: 2017/12/28/028
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="shortcut icon" href="assets/img/logo.png">
    <title>华理宿舍管理系统</title>
    <%@include file="admin_css.jsp" %>
</head>
<body class="pace-done">
<div class="pace  pace-inactive"><div class="pace-progress" data-progress-text="100%" data-progress="99" style="width: 100%;">
    <div class="pace-progress-inner"></div>
</div>
    <div class="pace-activity"></div></div>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">

                    <div class="dropdown profile-element" style="text-align: center"> <span>
                                <img alt="image" class="img-circle" src="/assets/img/profile_small.jpg"/>
                                 </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                    <span class="clear">
                                        <span class="block m-t-xs">
                                        <strong class="font-bold" id="userName"></strong>
                                        </span>
                                        <span class="text-muted text-xs block">学生<b class="caret"></b></span>
                                    </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="#" data-toggle="modal"
                                   data-target="#myModal1" id="change">修改个人资料</a>
                            </li>
                            <li><a href="#" data-toggle="modal"
                                   data-target="#myModal2" id="changePassWord">修改密码</a>
                            </li>
                            <li><a href="/Account/LogOut">安全退出</a>
                            </li>
                        </ul>
                    </div>
                    <div class="logo-element">
                        <strong>宿舍</strong>
                    </div>
                </li>
                <li class="li">
                    <a href="#" url="/StudentNotice/" class="redirect"><i class="fa fa-home" aria-hidden="true"></i><span class="nav-label">通知公告</span> </a>
                </li>
                </li>
            </ul>
        </div>
    </nav>
    <%--弹窗修改个人资料--%>
    <div class="modal inmodal fade in" id="myModal1" tabindex="-1" role="dialog" aria-hidden="true"
         style="display: none ; padding-right: 17px;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                            class="sr-only">Close</span></button>
                    <h4 class="modal-title">修改个人资料</h4>
                </div>
                <small class="font-bold">
                    <div class="modal-body" align="center">

                        <form class="form-horizontal" name="modify">
                                <div class="form-group">
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
                                        <select class="input-sm   " title="请选择学院" id="college" style="width: 100%">
                                            <option value="信息工程学院">信息工程学院</option>
                                            <option value="化学工程学院">化学工程学院</option>
                                            <option value="外国语学院">外国语学院</option>
                                            <option value="理学院">理学院</option>
                                            <option value="管理学院">管理学院</option>
                                        </select>
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
                                    </div>>
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
    <%--修改个人密码--%>
    <div class="modal inmodal fade in" id="myModal2" tabindex="-1" role="dialog" aria-hidden="true"
         style="display: none ; padding-right: 17px;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                            class="sr-only">Close</span></button>
                    <h4 class="modal-title">修改密码</h4>
                </div>
                <small class="font-bold">
                    <div class="modal-body" align="center">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-4 control-label" style="font-size: medium">原密码：</label>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control" placeholder="请输入原密码"
                                           id="origin">
                                </div>
                            </div><br/>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" style="font-size: medium">密码：</label>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control" placeholder="请输入您的新密码"
                                           id="passWord1">
                                </div>
                            </div><br/>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" style="font-size: medium">确认密码：</label>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control" placeholder="请再次输入新密码"
                                           id="passWord2">
                                </div>
                            </div><br/>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-white" data-dismiss="modal" id="Cancel">取消</button>
                        <button type="button" class="btn btn-primary" id="Create">确认</button>
                    </div>
                </small>
            </div>
            <small class="font-bold">
            </small>
        </div>
        <small class="font-bold">
        </small>
    </div>

    <div id="page-wrapper" class="gray-bg dashoard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i
                            class="fa fa-bars"></i> </a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <span class="m-r-sm text-muted welcome-message"><a href="#" title="返回首页"><i
                                class="fa fa-home"></i></a>欢迎使用华理宿舍管理系统</span>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                            <i class="fa fa-envelope"></i> <span class="label label-warning">16</span>
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li>
                                <div class="dropdown-messages-box">
                                    <div class="media-body">
                                        <small class="pull-right">46小时前</small>
                                        <strong>小四</strong> 项目已处理完结
                                        <br>
                                        <small class="text-muted">3天前 2014.11.8</small>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="dropdown-messages-box">
                                    <div class="media-body ">
                                        <small class="pull-right text-navy">25小时前</small>
                                        <strong>国民岳父</strong> 这是一条测试信息
                                        <br>
                                        <small class="text-muted">昨天</small>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a href="">
                                        <i class="fa fa-envelope"></i> <strong> 查看所有消息</strong>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="">
                            <i class="fa fa-bell"></i> <span class="label label-primary">8</span>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="">
                                    <div>
                                        <i class="fa fa-envelope fa-fw"></i> 您有16条未读消息
                                        <span class="pull-right text-muted small">4分钟前</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="">
                                    <div>
                                        <i class="fa fa-qq fa-fw"></i> 3条新回复
                                        <span class="pull-right text-muted small">12分钟钱</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a href="">
                                        <strong>查看所有 </strong>
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>

            </nav>
        </div>
        <div id="mainPage">
        </div>
    </div>
</div>
</body>
<%@include file="admin_script.jsp" %>
<%@include file="admin_footer.jsp" %>
<script type="text/javascript">
    function getValue() {
        $("#sex").val("");
        $("input[type='radio'][name='sex']:checked").attr("checked",true);
        var sex= $("input[type='radio'][name='sex']:checked").val();
        $("#sex").val(sex);
    }

    $(document).ready(function () {
        load();
    });
    function load(){
        bindRedirect();
        function fillUser(data) {
            $("#name").val(data.name);
            $("#age").val(data.age);
            $("#college").val(data.college);
            $("#profession").val(data.profession);
            $("#classNumber").val(data.classNumber);
            $("#phoneNumber").val(data.phoneNumber);
            $("#nativePlace").val(data.nativePlace);
            $("#studentOrDormitoryNumber").val(data.studentOrDormitoryNumber);
        }
        $(".li").click(function () {
            $(".active").removeClass("active");
            $(this).addClass("active");
        });

        // 修改
        $("#CreateButton").click(function () {
            if ($("#sex").val() == "男"){
                var sex = true;
            } else {
                var sex = false;
            }
            var data = {
                name:$("#name").val(),
                age:$("#age").val(),
                college:$("#college").val(),
                profession:$("#profession").val(),
                classNumber:$("#classNumber").val(),
                nativePlace:$("#nativePlace").val(),
                phoneNumber:$("#phoneNumber").val(),
                studentOrDormitoryNumber:$("#studentOrDormitoryNumber").val(),
                sex: sex
            };
            Post("/StudentAccount/modifyStudent",data);
            $("#CancelButton").click();
        });

//        修改密码
        $("#changePassWord").click(function () {
            $("#passWord2").val(null);
            $("#passWord1").val(null);
            $("#origin").val(null);
            $("#Create").click(function () {
                var passWord1=$("#passWord1").val();
                var passWord2=$("#passWord2").val();
                if(passWord1==passWord2){
                    var origin=$("#origin").val();
                    var passWord=passWord1;
                    $.ajax({
                        url: "/AdminAccount/changePassWord/origin/"+origin+"/passWord/"+passWord,
                        type: "PUT",
                        success: function () {
                            swal({
                                title: "成功",
                                text: "修改成功",
                                type: "success",
                                confirmButtonText: "知道了"
                            });
                            $("#Cancel").click();
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            swal({
                                title: "出错了！",
                                text: "密码错误",
                                type: "error",
                                confirmButtonText: "知道了"
                            });
                        }
                    });
                }else {
                    swal({
                        title: "错误",
                        text: "两次输入的密码不一致",
                        type: "error",
                        confirmButtonText: "知道了"
                    });
                }
            })
        });
        AjaxGetRequest("/AdminAccount/getInfo", fillUser);
    }
</script>


</html>
