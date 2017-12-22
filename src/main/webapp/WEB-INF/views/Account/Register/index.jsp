<%--
  Created by IntelliJ IDEA.
  User: Yzf
  Date: 2017/7/19/019
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <title>注册</title>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="/assets/img/logo.jpg">
    <link type="text/css" href="/assets/css/register.css" rel="stylesheet">
    <script src="/assets/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="/assets/js/jquery.form.js" type="text/javascript"></script>
</head>
<style>
    .input_div span{ background:#FFF;}
</style>
<body>
<div id="header">
    <div class="header">
        <h1 class="png_bg"></h1>
        <a class="png_bg" href="/Account/Login">返回主页</a>
    </div>
</div>

<div class="register_content">

    <ul class="step_ul step1 clear">
        <li class="li1">01、填写资料</li>
        <li class="li2">02、完成注册</li>
    </ul>
    <form name="registerForm" action="/Account/Register" method="post" style="padding:60px 40px 88px 40px;font-family:Microsoft Yahei">
        <div class="div_form clear ">
            <label>用户名：</label>
            <div class="input_div input_div1">
                <input id="userName" name="userName" type="text" placeholder="格式6-24位数字字母组合" maxlength="24">
                <span></span>
            </div>
        </div>
        <div class="div_form clear ">
            <label>手机号：</label>
            <div class="input_div input_div5">
                <input id="phoneNumber" name="phoneNumber" type="number" placeholder="请输入手机号码" maxlength="11">
                <span></span>
            </div>
        </div>
        <div class="div_form clear ">
            <label>性别：</label>
            <div style="size: 25px" id="sex">
                <input type="radio" name="sex" value="1" checked="checked" style="height: 18px;width: 20px;margin: 10px" onclick="getValue()"/><span style="font-size: 18px">男</span>
                <input type="radio" name="sex" value="0" style="height: 18px;width: 20px;margin: 10px" onclick="getValue()"/><span style="font-size: 18px">女</span>
            </div>
        </div>
        <div class="div_form clear ">
            <label>请创建一个密码：</label>
            <div class="input_div input_div3">
                <input id="password1" name="password1" type="password" placeholder="最少 6 个字符，区分大小写" maxlength="32">
                <span></span>
            </div>
        </div>
        <div class="div_form clear ">
            <label>重新输入密码：</label>
            <div class="input_div input_div4">
                <input id="password2" name="password2" type="password" placeholder="再次输入密码" maxlength="32">
                <span></span>
            </div>
        </div>

        <div class="div_form clear ">
            <label></label>
            <div class="input_div check2 input_div6" data="0" id="agreement">
                我已阅读并接受《华理论坛用户服务协议》
                <span></span>
            </div>
        </div>

        <div class="div_form clear ">
            <label></label>
            <div class="input_div">
                <button id="btn" class="btn">注册</button>
            </div>
        </div>
    </form>

    <div class="reg_login">
        <p>已有帐号？</p>
        <a class="btn2" href="/Account/Login">登录</a>
    </div>

</div>
</body>
<script type="text/javascript">
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
        $(".btn").click(function(){
            var agreenMent=$("#agreement").attr("data");
            var userName= $("#userName").val();
            var password1= $("#password1").val();
            var password2=$("#password2").val();
            var phoneNumber=$("#phoneNumber").val();

            password1=$.trim(password1);
            password2=$.trim(password2);

            if(!isRegisterUserName(userName)){
                $(".input_div1 span").html('<img src="/assets/img/text_error.png"><span style="color: red">账户名格式不正确!</span>');
                $("#username").focus();
                $(".btn").val('注册').removeAttr('disabled');
                return false;
            } else if(phoneNumber.equals(null)){
                $(".input_div5 span").html('<img src="/assets/img/text_error.png"><span style="color: red">手机号不正确!</span>');
                $("#password1").focus();
                $(".btn").val('注册').removeAttr('disabled');
                return false;
            }else if(password1.length < 6){
                $(".input_div3 span").html('<img src="/assets/img/text_error.png"><span style="color: red">密码格式不正确!</span>');
                $("#password1").focus();
                $(".btn").val('注册').removeAttr('disabled');
                return false;
            }else if(password1.equals(password2)){
                $(".input_div4 span").html('<img src="/assets/img/text_error.png"><span style="color: red">两次输入的密码不一致!</span>');
                $("#password2").focus();
                $(".btn").val('注册').removeAttr('disabled');
                return false;
            } else if(agreenMent != '1'){
                $(".input_div6 span").html('<img src="/assets/img/text_error.png"><span style="color: red">请先同意用户条款!</span>');
                $(".btn").val('注册').removeAttr('disabled');
                return false;
            }
        });
        $('.check2').click(function(){
            var rel = $('#agreement').attr("data");
            if(rel =='1'){
                $('#agreement').attr("data","0");
            }else{
                $('#agreement').attr("data","1");
            }
            $('.check2').toggleClass("check1");
        });
        function isRegisterUserName(s){
            var patrn=/^[a-zA-Z0-9]{1}([a-zA-Z0-9]|[._]){5,19}$/;
            if (!patrn.exec(s)) return false
            return true
        }
</script>

</html>

















