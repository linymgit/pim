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
</head>
<body>
<div class="container-fluid">
    <!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
    <div class="form-group has-success has-feedback">
        <label class="control-label" for="inputSuccess2">Input with success</label>
        <input type="text" class="form-control" id="inputSuccess2" aria-describedby="inputSuccess2Status">
        <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
        <span id="inputSuccess2Status" class="sr-only">(success)</span>
    </div>
    <div class="form-group has-warning has-feedback">
        <label class="control-label" for="inputWarning2">Input with warning</label>
        <input type="text" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status">
        <span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>
        <span id="inputWarning2Status" class="sr-only">(warning)</span>
    </div>
    <div class="form-group has-error has-feedback">
        <label class="control-label" for="inputError2">Input with error</label>
        <input type="text" class="form-control" id="inputError2" aria-describedby="inputError2Status">
        <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
        <span id="inputError2Status" class="sr-only">(error)</span>
    </div>
    <div class="form-group has-success has-feedback">
        <label class="control-label" for="inputGroupSuccess1">Input group with success</label>
        <div class="input-group">
            <span class="input-group-addon">@</span>
            <input type="text" class="form-control" id="inputGroupSuccess1" aria-describedby="inputGroupSuccess1Status">
        </div>
        <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
        <span id="inputGroupSuccess1Status" class="sr-only">(success)</span>
    </div>
</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>
