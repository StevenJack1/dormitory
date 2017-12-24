<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2017/12/23
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    tbody tr:hover {
        background: linear-gradient(#fff,#aaa);
        font-size: 17px;
    }
    thead tr{
        background: #ccc;
        color: #fff;
    }
    tbody tr{
        height: 80px;
    }



</style>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>排班管理</h5>
                    <div class="input-group col-sm-3" style="float: right">
                        <input type="text" placeholder="请输入关键词" class="input-sm form-control" id="content"> <span
                            class="input-group-btn">
                        <button type="button" class="btn btn-sm btn-primary" id="searchPackage"> 搜索</button> </span>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class=" m-b-xs col-lg-6">
                            <select class="input-sm   " title="请选择来源" id="buildName" onclick="loadPage(1)">
                                <option value="梅园一号楼">梅园一号楼</option>
                                <option value="梅园二号楼">梅园二号楼</option>
                                <option value="梅园三号楼">梅园三号楼</option>
                                <option value="梅园四号楼">梅园四号楼</option>
                                <option value="梅园五号楼">梅园五号楼</option>
                                <option value="梅园六号楼">梅园六号楼</option>
                                <option value="梅园七号楼">梅园七号楼</option>
                            </select>

                        </div>
                        <div class=" m-b-xs col-lg-6">
                            <div style="float: right">
                                <button id="last-week">上一周</button>
                                <button id="next-week">下一周</button>
                                <button id="this-week">本周</button>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped" border="1" cellpadding="10" width="100%">
                            <thead id="tableTop">
                                <tr>
                                    <th>姓名</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id="scheduleInfoTable">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<script>

    var loadPage = function (pageNumber) {
        var buildName = $("#buildName").val();
        var uploadTable = function (data) {
//            $("#scheduleInfoTable").empty();
//            var result = data["results"];
//            result.forEach(function (e) {
//                $("#scheduleInfoTable").append(`
//                    <tr>
//                    <td >`+e.dormitoryInfo.buildNumber+`</td>
//                    <td>`+e.dormitoryInfo.dormitoryNumber+`</td>
//                    <td>`+e.dormitoryInfo.dormitoryType+`</td>
//                    <td>`+e.name+`</td>
//                    <td>`+e.dormitoryInfo.paymentMoney+`</td>
//                    <td><a class="md-payment" studentId = '`+e.userName+`'>缴费</a></td>
//                    </tr>
//                    `);
//            });
//            $(".md-payment").click(function () {
//                const studentId = $(this).attr("studentId");
//                AjaxPost("/PaymentManagement/isPayment/studentId/" + studentId );
//                loadThis();
//            });
        };
        AjaxGetRequest("/ScheduleManagement/getInfo/buildName/" + buildName,uploadTable);
    };

    $(document).ready(function () {
        loadPage(1);
    });
</script>

<script>
    $(document).ready(function () {
        var cells = document.getElementById('tableTop').getElementsByTagName('th');
        var clen = cells.length;
        var currentFirstDate;
        var formatDate = function(date){
            var year = date.getFullYear()+'/';
            var month = (date.getMonth()+1)+'/';
            var day = date.getDate() + '<br>';
            var week = '('+['星期天','星期一','星期二','星期三','星期四','星期五','星期六'][date.getDay()]+')';
            var str = year+month+day;
            str += week;
            return str;
        };
        var addDate= function(date,n){
            date.setDate(date.getDate()+n);
            return date;
        };
        var setDate = function(date){
            var week = date.getDay()-1;
            date = addDate(date,week*-1);
            currentFirstDate = new Date(date);

            for(var i = 1;i<clen;i++){
                cells[i].innerHTML = formatDate(i==1 ? date : addDate(date,1));
            }
        };
        $("#last-week").click(function () {
            setDate(addDate(currentFirstDate,-7));
        });
        $("#next-week").click(function () {
            setDate(addDate(currentFirstDate,7));
        });
        $("#this-week").click(function () {
            setDate(new Date());
        });
        setDate(new Date());
    });

</script>
