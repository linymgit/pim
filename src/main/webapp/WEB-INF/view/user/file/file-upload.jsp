<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/1/30
  Time: 8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row" style="margin-top: 4rem">
        <div class="col-md-6 col-md-offset-3 col-xs-12">
            <h4 style="margin-bottom: 5rem">文件管理</h4>
            <div class="form-group">
                <input type="file" id="file" name="file" accept="image/jpeg,image/gif,application/msword,image/png,.txt,.xls">
                <p class="help-block" id="tips">上传文件</p>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-3 col-xs-12">
            <div class="form-group">
                <button type="button" class="btn btn-default" onclick="submit()">确认提交</button>
                <button type="button" class="btn btn-default" onclick="goON()" id="goOn" style="display: none">继续上传</button>
                <button type="button" class="btn btn-default" onclick="toList()">查看上传记录</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function submit() {
        let $tips = $("#tips");
        $tips.text("上传中...");

        let formData = new FormData();
        if ($("#file")[0].files[0] === undefined) {
            alert("文件不能为空")
            $tips.text("请选择文件");
            return;
        }
        formData.append("file", $("#file")[0].files[0]);
        $.ajax({
            type: 'post',
            url: '/user/file/upload',
            cache: false,
            contentType: false,
            processData: false, //默认为true，默认情况下，发送的数据将被转换为对象，设为false不希望进行转换
            data: formData, //数据
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (data, textStatus, jqXHR) {
                if (data.code>=0) {
                    $tips.text("上传成功");
                    $("#goOn").css("display", "inline-block");
                }else{
                    $tips.text(data.msg);
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    function goON() {
        let $tips = $("#tips");
        $tips.text("请选择文件");
        $("#file").val(undefined)
    }

    function toList() {
        window.location.href = "/user/file/list";
    }
</script>
</html>
