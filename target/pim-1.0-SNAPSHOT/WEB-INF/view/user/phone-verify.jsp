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
            <h4>手机号码验证</h4>
            <div class="form-horizontal" style="margin-top: 2rem">
                <div class="form-group">
                    <label class="col-sm-3 control-label" style="text-align: center">手机号码</label>
                    <div class="col-sm-9">
                        <c:choose>
                            <c:when test="${user.phone!=null}">
                                <input type="text" name="phone" id="phone" class="form-control" placeholder="请输入手机号码">
                            </c:when>
                            <c:otherwise>
                                <p class="form-control-static">${user.phone}</p>
                                <input type="hidden" name="email" value="${user.phone}">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${user.phoneVertify!=2}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label" style="text-align: center">验证码</label>
                            <div class="col-sm-9">
                                <input type="text" name="smscode" id="smsCode" class="form-control"
                                       placeholder="输入手机验证码">
                            </div>
                        </div>

                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${user.phoneVertify==1}">
                        <button type="button" class="btn btn-primary" style="width: 100%" onclick="submit()">点击验证
                        </button>
                    </c:when>
                    <c:when test="${user.phoneVertify==2}">
                        <button type="button" class="btn btn-primary" style="width: 100%" disabled>已验证</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-primary " style="width: 100%" onclick="sendCode()">发送手机验证码
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {

    });

    function submit() {
        let $phone = $("#phone").val();
        let $smsCode = $("#smsCode").val();

        if ($phone === "") {
            alert("手机号不可以为空");
            return;
        }

        if ($smsCode === "") {
            alert("短信验证码不可以为空");
            return;
        }

        $.ajax({
            //请求方式
            type: "POST",
            //请求地址
            url: "/user/phone/verify",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify({
                to: $phone,
                code: $smsCode
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

    function sendCode() {
        $.ajax({
            //请求方式
            type: "POST",
            //请求地址
            url: "/user/phone/code2verify",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify({
                to: $("#phone").val()
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
