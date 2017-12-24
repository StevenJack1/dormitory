<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2017/12/23
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    tbody tr td:hover {
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
                                    <th id="monday"></th>
                                    <th id="tuesday"></th>
                                    <th id="wednesday"></th>
                                    <th id="thursday"></th>
                                    <th id="friday"></th>
                                    <th id="saturday"></th>
                                    <th id="sunday"></th>
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

<div class="modal inmodal fade in" id="myModal1" tabindex="-1" role="dialog" aria-hidden="true"
     style="display: none ; padding-right: 17px;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">排班</h4>
            </div>
            <small class="font-bold">
                <div class="modal-body" align="center">
                    <form class="form-horizontal" role="form">

                        <div class="form-group">

                            <label class="col-sm-4 control-label" style="font-size: medium">排班事项</label>
                            <div class="col-sm-6">
                                <select class="input-sm   " title="请选择排班类型" id="scheduleStatus">
                                    <option value="早班">早班</option>
                                    <option value="晚班">晚班</option>
                                    <option value="病假">病假</option>
                                    <option value="事假">事假</option>
                                    <option value="调休">调休</option>

                                </select>
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

<script>

    var loadPage = function () {
        var buildName = $("#buildName").val();

        var monday = $("#monday").text().split("(")[0];
        var tuesday = $("#tuesday").text().split("(")[0];
        var wednesday = $("#wednesday").text().split("(")[0];
        var thursday = $("#thursday").text().split("(")[0];
        var friday = $("#friday").text().split("(")[0];
        var saturday = $("#saturday").text().split("(")[0];
        var sunday = $("#sunday").text().split("(")[0];


        var uploadTable = function (data) {
            $("#scheduleInfoTable").empty();
            for (var i = 0; i < data.length; i++){
                var userName = data[i].userName;
                var suuccess = function (e) {
                    var monday_child = "";
                    var tuesday_child = "";
                    var wednesday_child = "";
                    var thursday_child = "";
                    var friday_child = "";
                    var saturday_child = "";
                    var sunday_child = "";
                    for (var item of e){
                        if (item.workTime == monday){
                            monday_child = item.scheduleStatus;
                        } else if (item.workTime == tuesday_child){
                            tuesday_child = item.scheduleStatus;
                        } else if (item.workTime == wednesday){
                            wednesday_child = item.scheduleStatus;
                        } else if (item.workTime == wednesday){
                            wednesday_child = item.scheduleStatus;
                        } else if (item.workTime == thursday){
                            thursday_child = item.scheduleStatus;
                        } else if (item.workTime == friday){
                            friday_child = item.scheduleStatus;
                        } else if (item.workTime == saturday){
                            saturday_child = item.scheduleStatus;
                        } else if (item.workTime == sunday){
                            sunday_child = item.scheduleStatus;
                        }
                    }
                    $("#scheduleInfoTable").append(`
                        <tr>
                            <td>`+ data[i].name +` </td>
                            <td class="md-modify-1" data-toggle="modal" data-target="#myModal1" weekDay = '`+monday+`' userName = '`+data[i].userName+`'>`+ monday_child +`</td>
                            <td class="md-modify-2" data-toggle="modal" data-target="#myModal1" weekDay = '`+tuesday+`' userName = '`+data[i].userName+`'>`+ tuesday_child +`</td>
                            <td class="md-modify-3" data-toggle="modal" data-target="#myModal1" weekDay = '`+wednesday+`' userName = '`+data[i].userName+`'>`+ wednesday_child +`</td>
                            <td class="md-modify-4" data-toggle="modal" data-target="#myModal1" weekDay = '`+thursday+`' userName = '`+data[i].userName+`'>`+ thursday_child +`</td>
                            <td class="md-modify-5" data-toggle="modal" data-target="#myModal1" weekDay = '`+friday+`' userName = '`+data[i].userName+`'>`+ friday_child +`</td>
                            <td class="md-modify-6" data-toggle="modal" data-target="#myModal1" weekDay = '`+saturday+`' userName = '`+data[i].userName+`'>`+ saturday_child +`</td>
                            <td class="md-modify-7" data-toggle="modal" data-target="#myModal1" weekDay = '`+sunday+`' userName = '`+data[i].userName+`'>`+ sunday_child +`</td>
                        </tr>
                    `);


                };
                AjaxGetRequest("/ScheduleManagement/getSchedule/userName/" + userName + "/monday/" + monday + "/tuesday/" + tuesday + "/wednesday/" + wednesday + "/thursday/" + thursday + "/friday/" + friday + "/saturday/" + saturday + "/sunday/" + sunday,suuccess);
            }
            $(".md-modify-1").click(function () {
                const weekDay = $(this).attr("weekDay");
                const userName = $(this).attr("userName");
                modifyScheduleInfo(weekDay,userName);
            });
            $(".md-modify-2").click(function () {
                const weekDay = $(this).attr("weekDay");
                const userName = $(this).attr("userName");
                modifyScheduleInfo(weekDay,userName)
            });
            $(".md-modify-3").click(function () {
                const weekDay = $(this).attr("weekDay");
                const userName = $(this).attr("userName");
                modifyScheduleInfo(weekDay,userName)
            });
            $(".md-modify-4").click(function () {
                const weekDay = $(this).attr("weekDay");
                const userName = $(this).attr("userName");
                modifyScheduleInfo(weekDay,userName)
            });
            $(".md-modify-5").click(function () {
                const weekDay = $(this).attr("weekDay");
                const userName = $(this).attr("userName");
                modifyScheduleInfo(weekDay,userName)
            });
            $(".md-modify-6").click(function () {
                const weekDay = $(this).attr("weekDay");
                const userName = $(this).attr("userName");
                modifyScheduleInfo(weekDay,userName)
            });
            $(".md-modify-7").click(function () {
                const weekDay = $(this).attr("weekDay");
                const userName = $(this).attr("userName");
                modifyScheduleInfo(weekDay,userName)
            });
        };
        AjaxGetRequest("/ScheduleManagement/getInfo/buildName/" + buildName,uploadTable);
    };


</script>

<script>
    $(document).ready(function () {
        var cells = document.getElementById('tableTop').getElementsByTagName('th');
        var clen = cells.length;
        var currentFirstDate;
        var formatDate = function(date){
            var year = date.getFullYear()+'年';
            var month = (date.getMonth()+1)+'月';
            var day = date.getDate();
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
               $("#tableTop").find("th").eq(i).text(formatDate(i==1 ? date : addDate(date,1)));
//                cells[i].innerHTML = formatDate(i==1 ? date : addDate(date,1));
            }
        };
        $("#last-week").click(function () {
            setDate(addDate(currentFirstDate,-7));
            loadPage()
        });
        $("#next-week").click(function () {
            setDate(addDate(currentFirstDate,7));
            loadPage()
        });
        $("#this-week").click(function () {
            setDate(new Date());
            loadPage()
        });
        setDate(new Date());
        loadPage()
    });

</script>
