<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2017/12/25
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-9">
        <h2>通知公告</h2>
        <ol class="breadcrumb">
            <li>
                <a href="#">主页</a>
            </li>
            <li>
                附加页面
            </li>
            <li>
                <strong>标签墙</strong>
            </li>
        </ol>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="wrapper wrapper-content animated fadeInUp">
            <ul class="notes" id="noticeTable">

            </ul>
        </div>
    </div>
</div>

<script>
    var success = function (data) {
        $("#noticeTable").empty();
        data.forEach(function (e) {
            $("#noticeTable").append(`
                 <li>
                    <div>
                        <small>排班情况安排表</small>
                        <h4>`+e.workTime+`</h4>
                        <p style="font-size: 20px">亲爱的<b>`+e.user.name+`</b>,您好,您在<b>`+e.workTime+`</b>这天是<b>`+e.scheduleStatus+`</b></p>
                        <a href="pin_board.html#"><i class="fa fa-trash-o "></i></a>
                    </div>
                </li>
            `);
        });
    };
    Get("/DormitoryNotice/getNoticeList",success);
</script>
