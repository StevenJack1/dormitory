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
                    <h5>缴费管理</h5>
                    <div class="input-group col-sm-3" style="float: right">
                        <input type="text" placeholder="请输入关键词" class="input-sm form-control" id="content"> <span
                            class="input-group-btn">
                        <button type="button" class="btn btn-sm btn-primary" id="searchPackage"> 搜索</button> </span>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class=" m-b-xs col-lg-6">
                            <select class="input-sm   " title="请选择来源" id="college" onclick="loadPage(1)">
                                <option value="信息工程学院">信息工程学院</option>
                                <option value="化学工程学院">化学工程学院</option>
                                <option value="外国语学院">外国语学院</option>
                                <option value="理学院">理学院</option>
                                <option value="管理学院">管理学院</option>
                            </select>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped" >
                            <thead id="tableTop">
                            <tr>
                                <th>宿舍楼</th>
                                <th>宿舍号</th>
                                <th>宿舍类型</th>
                                <th>缴费人姓名</th>
                                <th>缴费金额</th>
                                <th>是否已缴费</th>
                                <th>缴费时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="packageList">
                            </tbody>
                        </table>
                    </div>
                    <ul class="pagination" id="pagination"></ul>
                </div>
            </div>
        </div>

    </div>
</div>

<script>

    var loadPage = function (pageNumber) {
        var college = $("#college").val();
        var uploadTable = function (data) {
            $("#packageList").empty();
            var result = data["results"];
            result.forEach(function (e) {
                $("#packageList").append(`
                    <tr>
                    <td >`+e.dormitoryInfo.buildNumber+`</td>
                    <td>`+e.dormitoryInfo.dormitoryNumber+`</td>
                    <td>`+e.dormitoryInfo.dormitoryType+`</td>
                    <td>`+e.name+`</td>
                    <td>`+e.dormitoryInfo.paymentMoney+`</td>
                    `+(
                        (e.payment == false)
                        ?
                            `<td>否</td>`
                        :
                            `<td>是</td>`
                    )+`
                    <td>`+getLocalTime(e.paymentTime)+`</td>
                     `+(
                        (e.payment == true)
                        ?
                            ``
                        :
                            `<td><a class="md-payment" studentId = '`+e.userName+`'>缴费</a></td>`
                    )+`
                    </tr>
                    `);
            });
            $(".md-payment").click(function () {
                const studentId = $(this).attr("studentId");
                AjaxPost("/PaymentManagement/isPayment/studentId/" + studentId );
                loadThis();
            });
        };
        Paging("/PaymentManagement/getInfo/college/" + college, "dormitoryInfoTable", uploadTable, pageNumber, 10);
    };

    $(document).ready(function () {
        loadPage(1);
    });
</script>
