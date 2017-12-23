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
                            <select class="input-sm   " title="请选择来源" id="theWay">
                                <option value="信息工程学院">信息工程学院</option>
                                <option value="化学工程学院">化学工程学院</option>
                                <option value="外国语学院">外国语学院</option>
                                <option value="理学院">理学院</option>
                                <option value="管理学院">管理学院</option>
                            </select>
                            <%--&nbsp;--%>
                            <%--<select class="input-sm   " title="请选择订单状态" id="packageStatus">--%>
                                <%--<option value="待领取">待领取</option>--%>
                                <%--<option value="已撤销">已撤销</option>--%>
                                <%--<option value="待送达">待送达</option>--%>
                                <%--<option value="待签收">待签收</option>--%>
                                <%--<option value="已完成">已完成</option>--%>
                            <%--</select>--%>
                            <%--&nbsp;--%>
                            <%--<select id="school" class="input-sm input-s-sm inline" title="请选择学校">--%>
                            <%--</select>--%>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped" >
                            <thead id="tableTop">
                            <tr>

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
    var loadPage=function (pageNumber) {
        var updateTable=function (data) {
            $("#tableTop").empty();
            var resultList = data["results"];
            //根据订单种类加载
            if($("#theWay").val()=="wechat"){
                switch($("#packageStatus").val()){
                    case "待领取": {
                        $("#tableTop").append(`
                <tr>
                    <th>发布人</th>
                    <th>快递类型</th>
                    <th>快递大小</th>
                    <th>发布时间</th>
                <tr>
`);
                        for (var i = 0; i < data["totalCount"]; i++) {
                            var result = resultList[i];
                            $("#packageList").append(
                                '<tr>' +
                                '<td >' + result.delegation.name +
                                '</td>' +
                                '<td>' + result.courierCompany.companyName +
                                '</td>'+
                                '<td>' + result.standard.description +
                                '</td>'+
                                '<td>' + getLocalTime(result.publishTime) +
                                '</tr>'
                            )
                        }
                        break;
                    }
                    case "已撤销": {
                        $("#tableTop").append(`
                <tr>
                    <th>发布人</th>
                    <th>快递类型</th>
                    <th>快递大小</th>
                    <th>发布时间</th>
                    <th>结束时间</th>
                    <th>订单结果</th>
                <tr>
`);
                        for (var i = 0; i < data["totalCount"]; i++) {
                            var result = resultList[i];
                            $("#packageList").append(
                                '<tr>' +
                                '<td >' + result.delegation.name +
                                '</td>' +
                                '<td>' + result.courierCompany.companyName +
                                '</td>'+
                                '<td>' + result.standard.description +
                                '</td>'+
                                '<td>' + getLocalTime(result.publishTime) +
                                '</td>'+
                                '<td>' + getLocalTime(result.endTime) +
                                '</td>'+
                                '<td>' + result.orderResult +
                                '</tr>'
                            )
                        }
                        break;
                    }
                    case "待送达": {
                        $("#tableTop").append(`
                <tr>
                    <th>发布人</th>
                    <th>接受人</th>
                    <th>快递类型</th>
                    <th>快递大小</th>
                    <th>发布时间</th>
                    <th>领取时间</th>
                <tr>
`);
                        for (var i = 0; i < data["totalCount"]; i++) {
                            var result = resultList[i];
                            $("#packageList").append(
                                '<tr>' +
                                '<td >' + result.delegation.name +
                                '</td>' +
                                '<td>' + result.agency.name+
                                '</td>' +
                                '<td>' + result.courierCompany.companyName +
                                '</td>'+
                                '<td>' + result.standard.description +
                                '</td>'+
                                '<td>' + getLocalTime(result.publishTime) +
                                '</td>'+
                                '<td>' + getLocalTime(result.receiveTime) +
                                '</td>'+
                                '</tr>'
                            )
                        }
                        break;
                    }
                    case "待签收": {
                        $("#tableTop").append(`
                <tr>
                    <th>发布人</th>
                    <th>接受人</th>
                    <th>快递类型</th>
                    <th>快递大小</th>
                    <th>发布时间</th>
                    <th>领取时间</th>
                    <th>送达时间</th>
                <tr>
`);
                        for (var i = 0; i < data["totalCount"]; i++) {
                            var result = resultList[i];
                            $("#packageList").append(
                                '<tr>' +
                                '<td >' + result.delegation.name +
                                '</td>' +
                                '<td>' + result.agency.name+
                                '</td>' +
                                '<td>' + result.courierCompany.companyName +
                                '</td>'+
                                '<td>' + result.standard.description +
                                '</td>'+
                                '<td>' + getLocalTime(result.publishTime) +
                                '</td>'+
                                '<td>' + getLocalTime(result.receiveTime) +
                                '</td>'+
                                '<td>' + getLocalTime(result.deliveryTime) +
                                '</td>'+
                                '</tr>'
                            )
                        }
                        break;
                    }
                    case "已完成": {
                        $("#tableTop").append(`
                <tr>
                   <th>发布人</th>
                                <th>接受人</th>
                                <th>快递类型</th>
                                <th>快递大小</th>
                                <th>发布时间</th>
                                <th>领取时间</th>
                                <th>送达时间</th>
                                <th>结束时间</th>
                                <th>订单结果</th>
                <tr>
`);
                        for (var i = 0; i < data["totalCount"]; i++) {
                            var result = resultList[i];
                            $("#packageList").append(
                                '<tr>' +
                                '<td >' + result.delegation.name +
                                '</td>' +
                                '<td>' + result.agency.name +
                                '</td>' +
                                '<td>' + result.courierCompany.companyName +
                                '</td>'+
                                '<td>' + result.standard.description +
                                '</td>'+
                                '<td>' + getLocalTime(result.publishTime) +
                                '</td>'+
                                '<td>' + getLocalTime(result.receiveTime) +
                                '</td>'+
                                '<td>' + getLocalTime(result.deliveryTime) +
                                '</td>'+
                                '<td>' + getLocalTime(result.endTime) +
                                '</td>'+
                                '<td>' + result.orderResult +
                                '</td>'+
                                '</tr>'
                            )
                        }
                        break;
                    }
                }
            }else {
                switch ($("#packageStatus").val()){
                    case "待领取":{
                        $("#tableTop").append(`
                <tr>
                    <th>发布人</th>
                    <th>快递类型</th>
                    <th>发布时间</th>
                <tr>
`);
                        for (var i = 0; i < data["totalCount"]; i++) {
                            var result = resultList[i];
                            $("#packageList").append(
                                '<tr>' +
                                '<td >' + result.name +
                                '</td>' +
                                '<td>' + result.courierCompany.companyName +
                                '</td>'+
                                '<td>' + getLocalTime(result.createDate) +
                                '</td>'+
                                '</tr>'
                            )
                        }
                        break;
                    }
                    case "待送达":{
                        $("#tableTop").append(`
                <tr>
                    <th>发布人</th>
                    <th>快递类型</th>
                    <th>发布时间</th>
                    <th>领取时间</th>
                <tr>
`);
                        for (var i = 0; i < data["totalCount"]; i++) {
                            var result = resultList[i];
                            $("#packageList").append(
                                '<tr>' +
                                '<td >' + result.name +
                                '</td>' +
                                '<td>' + result.courierCompany.companyName +
                                '</td>'+
                                '<td>' + getLocalTime(result.createDate) +
                                '</td>'+
                                '<td>' + getLocalTime(result.receiveDate) +
                                '</td>'+
                                '</tr>'
                            )
                        }
                        break;
                    }
                    case "已完成":{
                        $("#tableTop").append(`
                <tr>
                    <th>发布人</th>
                    <th>快递类型</th>
                    <th>发布时间</th>
                    <th>领取时间</th>
                    <th>领取时间</th>
                <tr>
`);
                        for (var i = 0; i < data["totalCount"]; i++) {
                            var result = resultList[i];
                            $("#packageList").append(
                                '<tr>' +
                                '<td >' + result.name +
                                '</td>' +
                                '<td>' + result.courierCompany.companyName +
                                '</td>'+
                                '<td>' + getLocalTime(result.createDate) +
                                '</td>'+
                                '<td>' + getLocalTime(result.receiveDate) +
                                '</td>'+
                                '<td>' + getLocalTime(result.endDate) +
                                '</td>'+
                                '</tr>'
                            )
                        }
                        break;
                    }
                }

            }
        };
        if($("#theWay").val()=="wechat"){
            Paging("/PackageListManagement/PackageList/packageStatus/" + $("#packageStatus").val()+"/schoolId/"+$("#school").val(),"packageList",updateTable,pageNumber,10);
        }else {
            Paging("/PackageListManagement/UMLPackageList/packageStatus/" + $("#packageStatus").val()+"/schoolId/"+$("#school").val(),"packageList",updateTable,pageNumber,10);
        }
    };

    $(document).ready(
        function () {
            loadSchool("school");
            loadPage(1);
            $("#theWay").change(function () {
                $("#packageStatus").html("");
                if($("#theWay").val()=="wechat"){
                    $("#packageStatus").append(`
                    <option value="待领取">待领取</option>
                    <option value="已撤销">已撤销</option>
                    <option value="待送达">待送达</option>
                    <option value="待签收">待签收</option>
                    <option value="已完成">已完成</option>
            `);
                }else {
                    $("#packageStatus").append(`
                    <option value="待领取">待领取</option>
                    <option value="待送达">待送达</option>
                    <option value="已完成">已完成</option>
            `)
                }
                loadPage(1);
            });
            $("#packageStatus").change(function () {
                loadPage(1);
            });
            $("#school").change(function () {
                loadPage(1);
            })
        });



</script>
