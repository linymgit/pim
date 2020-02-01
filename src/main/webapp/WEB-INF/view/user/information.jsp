<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/1/27
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人基本信息</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-md-6 col-md-offset-3">
            <h4 style="text-align: center">个人基本信息</h4>
            <form id="info-form" style="margin-top: 2rem">
                <div class="form-group">
                    <span>
                         <label>Id:</label>
                    </span>
                    <span class="form-control-static">${user.id}</span>
                </div>
                <div class="form-group">
                    <label for="relname">用户名:</label>
                    <input type="text" class="form-control" id="name" name="name" value="${user.name}">
                </div>
                <div class="form-group">
                    <label for="relname">真实姓名:</label>
                    <input type="text" class="form-control" id="relname" name="relname" value="${user.relname}">
                </div>
                <div class="form-group">
                    <label>性别:</label>
                    <label class="checkbox-inline">
                        <c:if test="${user.sex==1}">
                            <input type="radio" name="sex" value="1" checked> 男
                        </c:if>
                        <c:if test="${user.sex!=1}">
                            <input type="radio" name="sex" value="1"> 男
                        </c:if>
                    </label>
                    <label class="checkbox-inline">
                        <c:if test="${user.sex==0}">
                            <input type="radio" name="sex" value="0" checked> 女
                        </c:if>
                        <c:if test="${user.sex!=0}">
                            <input type="radio" name="sex" value="0"> 女
                        </c:if>
                    </label>
                </div>
                <div class="form-group">
                    <label for="avatar">头像:</label>
                    <img id="avatar-img" src="${user.avatar}"
                         style="width: 4rem;height: 4rem;border-radius: 50%;margin-bottom: 0.5rem">
                    <input type="file" id="avatar" name="avatar" onchange="changeAvatar()">
                    <p class="help-block">上传头像照片</p>
                </div>
                <div class="form-group">
                    <label for="job">职业:</label>
                    <input type="text" class="form-control" id="job" name="job" value="${user.job}">
                </div>
                <div class="form-group">
                    <label for="address">地址:</label>
                    <input type="text" class="form-control" id="address" name="address" value="${user.address}">
                </div>
                <button type="button" class="btn btn-default" onclick="modify()">确定修改</button>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    function modify() {
        let fs = $("#info-form").serializeArray();
        let formData = new FormData();
        $.each(fs, function (key, value) {
            formData.append(value.name, value.value);
        });
        formData.append("avatar", $("#avatar")[0].files[0]);
        $.ajax({
            type: 'post',
            url: '/user/info/modify',
            cache: false,
            contentType: false,
            processData: false, //默认为true，默认情况下，发送的数据将被转换为对象，设为false不希望进行转换
            data: formData, //数据
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (data, textStatus, jqXHR) {
                if (data.code>=0) {
                    alert("修改成功！")
                    window.location.reload();
                }
            }
        });
    }

    function changeAvatar() {
        let file = $("#avatar")[0].files[0];
        let src = window.URL.createObjectURL(file);
        $("#avatar-img").attr("src", src);
    }
</script>
</html>
