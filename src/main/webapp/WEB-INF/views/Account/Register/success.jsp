<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2017/7/28
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <title>注册成功</title>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="/assets/img/logo.jpg">
    <link type="text/css" href="/assets/css/register.css" rel="stylesheet">
    <script src="/assets/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="/assets/js/jquery.form.js" type="text/javascript"></script>
</head>
<style>
    .input_div span{ background:#FFF;}
    .register_successed
    {
        width:1080px;
        height:200px;
        border:2px solid #e1e1e1;
        border-top:2px solid #0089e1;
        position:absolute; top:190px;
        left:60px;
    }
</style>
<body>
<div id="header">
    <div class="header">
        <h1 class="png_bg"></h1>
        <a class="png_bg" href="/Account/Login">返回主页</a>
    </div>
</div>

<div class="register_content">

    <ul class="step_ul step2 clear">
        <li class="li1">01、填写资料</li>
        <li class="li2">02、完成注册</li>
    </ul>

    <div class="register_successed">
        <div align="center" style="padding: 5px 5px 5px 5px">
            <img src="/assets/img/success.png">
        </div>
        <h1 style="text-align: center;top: 50px;">您已成功注册华理论坛之家！</h1>
        <a class="btn2" href="/Account/Login" style="left: 450px;top: 130px">去登录</a>
    </div>


</div>
</body>

</html>
