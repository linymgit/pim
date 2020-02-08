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
    <title>管理后台登录</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    <style>
        #web_bg {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            min-width: 1000px;
            z-index: -10;
            zoom: 1;
            background-color: #fff;
            background-repeat: no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            -o-background-size: cover;
            background-position: center 0;
        }

        .poi {
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
    <div id="web_bg"
         style="background-image: url('../../static/img/bg.jpg');"></div>
    <div class="row" style="margin-top: 6rem">
        <div class="col-md-4"></div>
        <div class="col-md-3 col-md-offset-3">
            <div style="padding: 3rem;background-color:#F9F9F9;border-radius: 1rem">
                <div id="dia1">
                    <h4>密码登录</h4>
                    <div class="form-group has-success has-feedback">
                        <label class="control-label">用户名</label>
                        <input type="text" class="form-control" id="name">
                    </div>
                    <div class="form-group has-warning has-feedback">
                        <label class="control-label">密码</label>
                        <input type="password" class="form-control" id="password">
                    </div>
                    <div class="form-group has-warning has-feedback">
                        <label class="control-label">验证码</label>
                        <input type="hidden" id="captchaId" name="captchaId">
                        <input type="text" class="form-control" id="captchaCode" name="captchaCode">
                        <img id="captcha" style="position: absolute;top: 2.6rem;right: 2px;z-index: 1000"
                             onclick="renderCaptcha()">
                    </div>
                    <p>
                        <button type="button" class="btn btn-primary" style="width: 100%" onclick="pwLogin()">登录
                        </button>
                    </p>
                </div>
                <div id="dia2" style="display: none">
                    <h4>手机短信验证码登录</h4>
                    <div class="form-group has-success has-feedback">
                        <label class="control-label">手机号</label>
                        <input type="text" class="form-control" id="phone" aria-describedby="inputSuccess2Status">
                    </div>

                    <div class="form-group has-warning has-feedback">
                        <label class="control-label" id="phoneLabel">图片验证码</label>
                        <input type="text" class="form-control" id="smsCode">
                        <input type="hidden" id="captchaId2Phone">
                        <img id="captcha2Phone" style="position: absolute;top: 2.6rem;right: 2px;z-index: 1000"
                             onclick="renderCaptcha2Phone()">
                    </div>
                    <div class="form-group has-warning has-feedback">
                        <button type="button" class="btn" style="width: 100%" id="smsBtn" onclick="sendPhoneCode()">
                            <span id="smsSSS">点击发送手机验证码</span><span id="smsS" style="display: none">已发送(<span id="smsSS"></span>s)</span></button>
                    </div>
                    <p>
                        <button type="button" class="btn btn-primary" style="width: 100%" onclick="phoneLogin()">登录</button>
                    </p>
                </div>
                <div id="dia3" style="display: none">
                    <h4>邮箱验证码登录</h4>
                    <div class="form-group has-success has-feedback">
                        <label class="control-label">邮箱地址</label>
                        <input type="text" class="form-control" id="email" aria-describedby="inputSuccess2Status">
                    </div>
                    <div class="form-group has-warning has-feedback">
                        <label class="control-label" id="emailLabel">图片验证码</label>
                        <input type="text" class="form-control" id="emailCode">
                        <input type="hidden" id="captchaId2Email">
                        <img id="captcha2Email" style="position: absolute;top: 2.6rem;right: 2px;z-index: 1000"
                             onclick="renderCaptcha2Email()">
                    </div>
                    <div class="form-group has-warning has-feedback">
                        <button type="button" class="btn" style="width: 100%" id="emailBtn" onclick="sendEmailCode()">
                            <span id="emailSSS">点击发送邮箱验证码</span><span id="emailS" style="display: none">已发送邮箱验证码(<span id="emailSS"></span>s)</span>
                        </button>
                    </div>
                    <p>
                        <button type="button" class="btn btn-primary" style="width: 100%" onclick="emailLogin()">登录
                        </button>
                    </p>
                </div>
                <p>
                    <span class="poi" id="tab1" onclick="changeTab(1)" style="display: none">密码登录</span>
                    <span class="poi" id="tab2" onclick="changeTab(2)">手机登录</span>
                    <span class="poi" id="tab3" onclick="changeTab(3)">邮箱登录</span>
                </p>
                <div>
                    <span><a href="${pageContext.request.contextPath}/user/register" style="float:right;">免费注册</a></span>
                    <span><a id="forget-pw" href="${pageContext.request.contextPath}/user/pw/reset" style="float:right;margin-right: 0.5rem;">忘记密码?</a></span>
                </div>

            </div>
        </div>
    </div>


</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script src="../../static/js/yimi-form.js"></script>
<script>
    $(function () {
        renderCaptcha();

        changeTab(1);
    });

    //视图
    tabIds = [1, 2, 3]

    function changeTab(id) {
        // 1.刷新图片验证码
        if (id === 2){
            renderCaptcha2Phone();
        }
        if (id === 3){
            renderCaptcha2Email();
        }
        for (let i in tabIds) {
            if (id === 1) {
                $("#forget-pw").css("display", "inline-block")
            }else{
                $("#forget-pw").css("display", "none")
            }
            if (tabIds[i] === id) {
                $("#tab" + tabIds[i]).css("display", "none")
                $("#dia" + tabIds[i]).css("display", "block")
            } else {
                $("#tab" + tabIds[i]).css("display", "inline-block")
                $("#dia" + tabIds[i]).css("display", "none")
            }
        }
    }

    //发送手机短信验证码
    function sendPhoneCode() {
        let $1 = $("#phone").val();
        if ($1 === "") {
            alert("手机号码不能为空");
            return;
        }
        let $smsCode = $("#smsCode").val();
        if ($smsCode === "") {
            alert("图片验证码不能为空");
            return;
        }
        let $smsBtn = $("#smsBtn");
        $smsBtn.attr("disabled", true);
        let $smsSSS = $("#smsSSS");
        $smsSSS.text("发送中...");
        $smsSSS.css("display", "inline-block");
        $.ajax({
            type: "POST",
            contentType: "application/json;charset=UTF-8",
            headers: {
                'captchaId': $("#captchaId2Phone").val(),
                'captchaCode': $smsCode,
            },
            url: "/code/phone?phone=" + $1,
            success: function (result) {
                if (result.code >= 0) {
                    let $smsS = $("#smsS");
                    let $smsSS = $("#smsSS");
                    $smsSSS.css("display", "none");
                    $smsSS.text(120);
                    $smsS.css("display", "inline-block");
                    let t1 = window.setInterval(function () {
                        if ($smsSS.text() <= 0) {
                            window.clearInterval(t1);
                            $smsS.css("display", "none");
                            $smsBtn.removeAttr("disabled")
                            return;
                        }
                        $smsSS.text($smsSS.text() - 1)
                    }, 1000);
                    $("#phoneLabel").text("手机验证码");
                    $("#captchaId2Phone").css("display", "none");
                } else {
                    alert("发送失败，因为" + result.msg);
                    renderCaptcha2Phone();
                    $smsBtn.removeAttr("disabled");
                    $smsSSS.text("点击发送手机验证码");
                }
                $("#smsCode").val("");
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    //发送邮箱验证码
    function sendEmailCode() {
        let $1 = $("#email").val()
        if ($1 === "") {
            alert("邮箱地址不能为空")
            return;
        }
        let $2 = $("#emailCode").val();
        if ($2 === "") {
            alert("图片验证码不能为空")
            return;
        }
        let $emailS = $("#emailS")
        let $emailSS = $("#emailSS");
        let $emailSSS = $("#emailSSS");
        let $emailBtn = $("#emailBtn");
        $emailSSS.text("正在发送邮件验证码...");
        $emailSSS.css("display", "inline-block");
        $emailBtn.attr("disabled", true);
        $.ajax({
            type: "POST",
            contentType: "application/json;charset=UTF-8",
            headers: {
                'captchaId': $("#captchaId2Email").val(),
                'captchaCode': $2,
            },
            url: "/code/email?email=" + $1,
            success: function (result) {
                if (result.code >= 0) {
                    $emailSS.text(120);
                    $emailSSS.css("display", "none");
                    $emailS.css("display", "inline-block");
                    let t1 = window.setInterval(function () {
                        if ($emailSS.text() <= 0) {
                            window.clearInterval(t1);
                            $emailS.css("display", "none");
                            $emailBtn.removeAttr("disabled");
                            $("#emailLabel").text("图片验证码");
                            $("#captcha2Email").css("display", "block");
                            $emailSSS.text("点击发送邮箱验证码");
                            $emailSSS.css("display", "inline-block");
                            return;
                        }
                        $emailSS.text($emailSS.text() - 1);
                    }, 1000);
                    $("#emailLabel").text("邮箱验证码");
                    $("#captcha2Email").css("display", "none");
                } else {
                    alert(result.msg)
                    renderCaptcha2Email();
                    $emailS.css("display", "none");
                    $emailBtn.removeAttr("disabled");
                    $("#emailLabel").text("图片验证码");
                    $("#captcha2Email").css("display", "block");
                    $emailSSS.text("点击发送邮箱验证码");
                    $emailSSS.css("display", "inline-block");
                }
                $("#emailCode").val("");
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    function pwLogin() {
        let $1 = $("#name").val();
        let $2 = $("#password").val();
        let $3 = $("#captchaCode").val();
        if ($1 === "") {
            alert("用户名不能为空！");
            return;
        }
        if ($2 === "") {
            alert("密码不能为空！");
            return;
        }
        if ($3 === "") {
            alert("验证码不能为空！");
            return;
        }
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url: "/admin2020/pw/login",
            //数据，json字符串
            data: JSON.stringify({
                name: $1,
                password: $2,
            }),
            //请求成功
            headers: {
                'captchaId': $("#captchaId").val(),
                'captchaCode': $3,
            },
            success: function (result) {
                if (result.code >= 0) {
                    //登录成功
                    localStorage.setItem('x-token', result.data);
                    window.location.href = "/admin2020/index";
                } else {
                    alert(result.msg);
                    renderCaptcha();
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });

    }

    function emailLogin() {
        let $1 = $("#email").val();
        let $2 = $("#emailCode").val();
        if ($1 === "") {
            alert("邮箱地址不能为空！");
            return;
        }
        if ($2 === "") {
            alert("邮箱验证码不能为空！");
            return;
        }
        $("#emailBtn").css("disabled", true);
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url: "/admin2020/email/login",
            //数据，json字符串
            data: JSON.stringify({
                to: $1,
                code: $2,
            }),
            success: function (result) {
                if (result.code >= 0) {
                    //登录成功
                    localStorage.setItem('x-token', result.data);
                    window.location.href = "/admin2020/index"
                } else {
                    alert(result.msg)
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    function phoneLogin() {
        let $1 = $("#phone").val();
        let $2 = $("#smsCode").val();
        if ($1 === "") {
            alert("手机号不能为空！");
            return;
        }
        if ($2 === "") {
            alert("手机验证码不能为空！");
            return;
        }

        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url: "/admin2020/phone/login",
            //数据，json字符串
            data: JSON.stringify({
                to: $1,
                code: $2,
            }),
            success: function (result) {
                if (result.code >= 0) {
                    //登录成功
                    localStorage.setItem('x-token', result.data);
                    window.location.href = "/admin2020/index"
                } else {
                    alert(result.msg)
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }


    function renderCaptcha2Phone() {
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/captcha/arithm",
            success: function (result) {
                $("#captcha2Phone").attr("src", result.pic);
                $("#captchaId2Phone").val(result.captchaId);
            }
        });
    }

    function renderCaptcha2Email(){
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/captcha/arithm",
            success: function (result) {
                $("#captcha2Email").attr("src", result.pic);
                $("#captchaId2Email").val(result.captchaId);
            }
        });
    }
</script>
</body>
</html>
