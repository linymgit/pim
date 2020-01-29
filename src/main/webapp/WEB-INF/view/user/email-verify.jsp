<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/1/26
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证邮箱</title>
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 col-xs-12">
            <h4>邮箱认证</h4>
            <div class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${user.email}</p>
                        <input type="hidden" name="email" value="${user.email}">
                    </div>
                </div>
                <c:choose>
                    <c:when test="${user.emailVertify==1}">
                        <button type="button" class="btn btn-primary" style="width: 100%" disabled>已发送验证邮件，请登录邮箱，完成激活。</button>
                    </c:when>
                    <c:when test="${user.emailVertify==2}">
                        <button type="button" class="btn btn-primary" style="width: 100%" disabled>已验证</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-primary" style="width: 100%" onclick="submit()">点击验证</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function submit(){
        $.ajax({
            //请求方式
            type: "POST",
            //请求地址
            url: "/user/email/verify",
            //请求成功
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (result) {
                alert(result.msg);
                window.location.reload();
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }
</script>
</html>
