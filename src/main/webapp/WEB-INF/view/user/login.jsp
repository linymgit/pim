<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/16
  Time: 0:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登录</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    <style>
        #web_bg{
            position:fixed;
            top: 0;
            left: 0;
            width:100%;
            height:100%;
            min-width: 1000px;
            z-index:-10;
            zoom: 1;
            background-color: #fff;
            background-repeat: no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            -o-background-size: cover;
            background-position: center 0;
        }
        .poi{
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-8 col-sm-6 col-md-4 col-md-offset-1" style="padding-top: 2rem">
            logo xxx系统
        </div>
    </div>
    <div id="web_bg" style="background-image: url('https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1579197132065&di=d77563a2e327982a1bfe06147ca71a52&imgtype=0&src=http%3A%2F%2Fimg.pptjia.com%2Fimage%2F20190223%2F69a70f4ad56095d768033c59d2def273.jpeg');"></div>
    <div class="row" style="margin-top: 20vh">
        <div class="col-md-4"></div>
        <div class="col-md-3 col-md-offset-3">
            <div style="padding: 3rem;background-color: whitesmoke;border-radius: 1rem">
                <h4>密码登录</h4>
                <!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
                    <div class="form-group has-success has-feedback">
                    <label class="control-label">用户名</label>
                    <input type="text" class="form-control" id="inputSuccess2" aria-describedby="inputSuccess2Status">
                    <%--                <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>--%>
                    <%--                <span id="inputSuccess2Status" class="sr-only">(success)</span>--%>
                </div>
                <div class="form-group has-warning has-feedback">
                    <label class="control-label" for="inputWarning2">密码</label>
                    <input type="text" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status">
                    <%--                <span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>--%>
                    <%--                <span id="inputWarning2Status" class="sr-only">(warning)</span>--%>
                </div>
                <p> <button type="button" class="btn btn-primary" style="width: 100%">登录</button></p>
                <p><span class="poi">邮箱登录</span>&ensp;<span class="poi">手机登录</span></p>
                <p class="poi" style="text-align: right">免费注册</p>

            </div>
        </div>
    </div>


</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>
