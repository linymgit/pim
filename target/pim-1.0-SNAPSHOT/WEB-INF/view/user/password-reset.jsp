<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/2/1
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置密码</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 col-xs-12">
            <h4>重置密码</h4>
            <div class="form-horizontal">
                <div class="form-group">
                    <c:choose>
                        <c:when test="${user.emailVertify==2}">
                            <p class="form-control-static">请输入新密码</p>
                            <input type="password" id="password" class="form-control" placeholder="密码">
                            <button type="button" class="btn btn-primary" style="width: 100%;margin-top: 0.5rem" onclick="submit()" id="resetBtn">重置密码
                            </button>
                        </c:when>
                        <c:otherwise>
                            <p class="form-control-static">未验证邮箱，无法重置密码！！！</p>
                            <button type="button" class="btn btn-primary" style="width: 100%" disabled>重置密码
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function submit() {
        $("#resetBtn").attr("disabled", true);

        let val = $("#password").val();

        if (val==="") {
            alert("密码不可以为空");
            return;
        }

        $.ajax({
            //请求方式
            type: "POST",
            //请求地址
            url: "/user/pw/reset",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify({
                password:val
            }),
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
