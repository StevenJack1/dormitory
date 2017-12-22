<%--
  Created by IntelliJ IDEA.
  User: arthurme
  Date: 2017/3/21
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="shortcut icon" href="assets/img/logo.png">
    <title>校园快递后台管理系统</title>
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
                                        <span class="text-muted text-xs block">管理员<b class="caret"></b></span>
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
                        <strong>镖</strong>
                    </div>
                </li>
                <li class="li">
                    <a href="#" url="/UserManagement/" class="redirect"><i class="fa fa-users"></i><span class="nav-label">用户管理</span> </a>
                </li>
                <li class="li">
                    <a href="#" url="/VerifyManagement/" class="redirect"><i class="fa fa-check-circle-o"></i> <span class="nav-label">学生认证</span> </a>
                </li>
                <li class="li">
                <a href="#" url="/AddressManagement/" class="redirect"><i class="fa fa-map-marker"></i> <span class="nav-label">区域管理</span> </a>
                </li>
                <li class="li">
                    <a href="#" url="/PackageListManagement/" class="redirect"><i class="fa fa-th-list"></i> <span class="nav-label">任务列表管理</span></a>
                </li>
                <li class="li">
                    <a href="#" url="/CourierCompanyManagement/" class="redirect"><i class="fa fa-truck"></i> <span class="nav-label">快递类型管理</span> </a>
                </li>
                <li class="li">
                    <a href="#" url="/StandardManagement/" class="redirect"><i class="fa fa-street-view"></i> <span class="nav-label">包裹标准管理</span> </a>
                </li>
                <li class="li">
                    <a href="#" url="/FeedBackManagement/" class="redirect"><i class="fa fa-thumbs-up"></i> <span class="nav-label">用户反馈</span> </a>
                </li>
                <li class="li">
                    <a href="#" url="/UMITeam/admin" class="redirect"><i class="fa fa-tasks"></i> <span class="nav-label">团队接单</span> </a>
                </li>
                <li class="li">
                    <a href="#" url="/SearchPackage/" class="redirect"><i class="fa fa-search-plus"></i> <span class="nav-label">订单查询</span> </a>
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
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-4 control-label" style="font-size: medium">姓名：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" placeholder="请输入姓名"
                                           id="name">
                                </div>
                            </div><br/>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" style="font-size: medium">昵称：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" placeholder="请输入昵称"
                                           id="nickName">
                                </div>
                            </div><br/>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" style="font-size: medium">联系电话：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" placeholder="请输入联系电话"
                                           id="phoneNumber">
                                </div>
                            </div><br/>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" style="font-size: medium">邮箱：</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" placeholder="请输入邮箱"
                                           id="email">
                                </div>
                            </div><br/>
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
                                class="fa fa-home"></i></a>欢迎使用智慧镖局后台管理系统</span>
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
    $(document).ready(
        function () {
            load();
        });
    function load(){
        bindRedirect();
        function fillUser(data) {
            $("#userName").text(data.name);
            userName=data.userName;
            $("#name").val(data.name);
            $("#nickName").val(data.nickName);
            $("#phoneNumber").val(data.phoneNumber);
            $("#email").val(data.studentId);
        }
        $(".li").click(function () {
            $(".active").removeClass("active");
            $(this).addClass("active");
        });
//        修改个人资料
        $("#change").click(function () {
            $("#CreateButton").click(function () {
                var name=$("#name").val();
                var  nickName=$("#nickName").val();
                var  phoneNumber=$("#phoneNumber").val();
                var  email=$("#email").val();
                AjaxPutRequest("/AdminAccount/changeInfo/name/"+name+"/nickName/"+nickName+"/phoneNumber/"+phoneNumber+"/email/"+email);
                $("#CancelButton").click();
            })
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
        AjaxGetRequest("/UserManagement/UserInfo", fillUser);
    }



    
</script>

</html>
