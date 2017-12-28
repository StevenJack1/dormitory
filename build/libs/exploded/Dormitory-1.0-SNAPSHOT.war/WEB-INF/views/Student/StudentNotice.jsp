<%--
  Created by IntelliJ IDEA.
  User: Yzf
  Date: 2017/12/28/028
  Time: 17:16
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
    var success = function (e) {
        $("#noticeTable").empty();

        $("#noticeTable").append(`
             <li>
                <div>
                    <small>所在宿舍楼</small>

                    <h4 style="font-size: 20px">亲爱的<b>`+e.name+`</b>,您好,您在<b>`+e.dormitoryInfo.buildNumber+`</b>号楼<b>`+e.dormitoryInfo.dormitoryNumber+`</b>宿舍</h4>
                    <a href="pin_board.html#"><i class="fa fa-trash-o "></i></a>
                </div>
            </li>
        `);

    };
    Get("/StudentNotice/getInfo",success);
</script>

