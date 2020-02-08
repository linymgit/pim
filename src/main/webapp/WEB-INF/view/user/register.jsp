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
    <title>注册</title>

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
        <div class="col-xs-8 col-sm-6 col-md-4 col-md-offset-1" style="padding-top: 2rem;">
            logo xxx系统
        </div>
    </div>
    <div id="web_bg"
         style="background-image: url('../../static/img/bg.jpg');"></div>
    <div class="alert alert-success alert-dismissable" role="alert" style="text-align: center; display: none"
         id="do-result">
        <button class="close" type="button" data-dismiss="alert" onclick="closeAlert()">&times;</button>
    </div>
    <h4 style="text-align: center;padding-bottom: 2vh">注册会员</h4>
    <div class="row">
        <div class="col-md-4 col-md-offset-4 col-xs-12 col-sm-12">
            <ul class="nav nav-tabs" style="background-color:#494a4a;cursor: pointer">
                <li role="presentation"><a onclick="show(1)">普通注册</a></li>
                <li role="presentation"><a onclick="show(2)">人脸注册</a></li>
            </ul>
        </div>
        <div id="tab1">
            <form id="register-form" class="form-horizontal">
                <div class="col-xs-12 col-sm-12 col-md-4 col-md-offset-4">
                    <div class="form-group" id="name-wrap" style="padding-left: 15px;padding-right:15px">
                        <label for="name">用户名:</label>
                        <input type="text" class="form-control" id="name" name="name" onblur="vertifyName()">
                        <span style="color: #a94442; display: none" id="name-tips">用户名不能为空</span>
                    </div>
                    <div class="form-group" style="padding-left: 15px;padding-right:15px">
                        <label for="relname">真实姓名:</label>
                        <input type="text" class="form-control" id="relname" name="relname">
                    </div>
                    <div class="form-group" style="padding-left: 15px;padding-right:15px">
                        <label for="relname">性别:</label>
                        <label>
                            <input type="radio" name="sex" value="1" checked/>
                        </label>男
                        <label>
                            <input type="radio" name="sex" value="0"/>
                        </label>女
                    </div>
                    <div class="form-group" id="password-wrap" style="padding-left: 15px;padding-right:15px">
                        <label for="password">密码:</label>
                        <input type="password" class="form-control" id="password" name="password"
                               onblur="vertifyPasswd()">
                        <span style="color: #a94442; display: none" id="password-tips">密码不能为空</span>
                    </div>
                    <div class="form-group" style="padding-left: 15px;padding-right:15px">
                        <label for="avatar">头像:</label>
                        <img id="avatar-img" src="" alt="avatar"
                             style="width: 4rem;height: 4rem;border-radius: 50%;margin-bottom: 0.5rem">
                        <input type="file" id="avatar" name="avatar" onchange="changeAvatar()">
                        <p class="help-block">上传头像照片</p>
                    </div>
                    <div class="form-group" id="phone-wrap" style="padding-left: 15px;padding-right:15px">
                        <label for="phone">手机号:</label>
                        <input type="text" class="form-control" id="phone" name="phone" onblur="vertifyPhone()">
                        <span style="color: #a94442; display: none" id="phone-tips">手机号不合法</span>
                    </div>
                    <div class="form-group" id="email-wrap" style="padding-left: 15px;padding-right:15px">
                        <label for="email">邮箱:</label>
                        <input type="text" class="form-control" id="email" name="email" onblur="vertifyEmail()">
                        <span style="color: #a94442; display: none" id="email-tips">邮箱不合法</span>
                    </div>
                    <div class="form-group" style="padding-left: 15px;padding-right:15px">
                        <label for="job">职业:</label>
                        <input type="text" class="form-control" id="job" name="job">
                    </div>
                    <div class="form-group" style="padding-left: 15px;padding-right:15px">
                        <label for="address">地址:</label>
                        <input type="text" class="form-control" id="address" name="address">
                    </div>
                    <div class="form-group" id="captchaCode-wrap"
                         style="padding-left: 15px;padding-right:15px;position: relative">
                        <label class="control-label">验证码</label>
                        <input type="hidden" id="captchaId" name="captchaId">
                        <input type="text" class="form-control" id="captchaCode" name="captchaCode"
                               onblur="vertifyCaptcha()">
                        <img id="captcha" style="position: absolute;bottom: 2px;right: 17px;z-index: 1000"
                             onclick="renderCaptcha()">
                        <span style="color: #a94442; display: none" id="captchaCode-tips">验证码不能为空</span>
                    </div>
                    <button type="button" class="btn btn-default" onclick="register()">确定注册</button>
                    <a style="float: right;margin-top: 1rem;"
                       href="${pageContext.request.contextPath}/user/login">去登陆</a>
                </div>
            </form>
        </div>
        <div id="tab2" class="col-sm-12 col-xs-12 col-md-4 col-md-offset-4" style="display: none">
            <button style="width: 100%;margin-top: 2rem;" type="button" class="btn btn-default"
                    onclick="openCamera()">
                打开摄像头
            </button>
            <button style="width: 100%;margin-top: 0.5rem" type="button" class="btn btn-default"
                    onclick="closeCamera()">
                关闭摄像头
            </button>
            <button style="width: 100%;margin-top: 0.5rem" type="button" class="btn btn-default"
                    onclick="faceRegister()">
                点击人脸注册
            </button>
            <div style="width:100%;height:50vh;border-radius: 0.2rem;overflow-x: hidden" id="video-wrap">
                <video id="video" autoplay></video>
                <canvas hidden="hidden" id="canvas" width="800" height="600"></canvas>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
    let emailReg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;

    let phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/;

    let video;//视频流对象
    let context;//绘制对象
    let canvas;//画布对象

    $(function () {
        renderCaptcha();

        fixCanvasPixel();
    });

    function fixCanvasPixel() {
        $("#video").attr("width", $("#tab2").width);
        $("#video").attr("height", $("#video-wrap").height());
    }

    function register() {
        if (!vertifyEmail() || !vertifyPhone() || !vertifyName() || !vertifyPasswd() || !vertifyCaptcha()) {
            return;
        }
        let fs = $("#register-form").serializeArray();
        let formData = new FormData();
        $.each(fs, function (key, value) {
            formData.append(value.name, value.value);
        });
        formData.append("avatar", $("#avatar")[0].files[0]);
        $.ajax({
            type: 'post',
            url: "/user/do/register",
            cache: false,
            contentType: false,
            processData: false, //默认为true，默认情况下，发送的数据将被转换为对象，设为false不希望进行转换
            data: formData, //数据
            headers: {
                'captchaId': $("#captchaId").val(),
                'captchaCode': $("#captchaCode").val(),
            },
            success: function (result) {
                if (result.code >= 0) {
                    alert("注册成功！");
                    window.location.href = "/user/login";
                } else {
                    alert(result.msg);
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    function renderCaptcha() {
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/captcha/arithm",
            success: function (result) {
                $("#captcha").attr("src", result.pic);
                $("#captchaId").val(result.captchaId);
            }
        });
    }

    function vertifyPhone() {
        let val = $("#phone").val();
        if (val !== undefined && val !== "") {
            if (!phoneReg.test(val)) {
                $("#phone-wrap").addClass("has-error has-feedback");
                $("#phone-tips").css("display", "block");
                return false;
            } else {
                $("#phone-wrap").removeClass("has-error has-feedback");
                $("#phone-tips").css("display", "none");
                return true;
            }
        } else {
            $("#phone-wrap").removeClass("has-error has-feedback");
            $("#phone-tips").css("display", "none");
            return true;
        }
    }

    function vertifyEmail() {
        let val = $("#email").val();
        if (val !== undefined && val !== "") {
            if (!emailReg.test(val)) {
                $("#email-wrap").addClass("has-error has-feedback");
                $("#email-tips").css("display", "block");
                return false;
            } else {
                $("#email-wrap").removeClass("has-error has-feedback");
                $("#email-tips").css("display", "none");
                return true;
            }
        } else {
            $("#email-wrap").removeClass("has-error has-feedback");
            $("#email-tips").css("display", "none");
            return true;
        }
    }

    function vertifyName() {
        let val = $("#name").val();
        if (val !== undefined && val !== "") {
            $("#name-wrap").removeClass("has-error has-feedback");
            $("#name-tips").css("display", "none");

            return true;
        } else {
            $("#name-wrap").addClass("has-error has-feedback");
            $("#name-tips").css("display", "block");
            return false;
        }
    }

    function vertifyPasswd() {
        let val = $("#password").val();
        if (val !== undefined && val !== "") {
            $("#password-wrap").removeClass("has-error has-feedback");
            $("#password-tips").css("display", "none");

            return true;
        } else {
            $("#password-wrap").addClass("has-error has-feedback");
            $("#password-tips").css("display", "block");
            return false;
        }
    }

    function vertifyCaptcha() {
        let val = $("#captchaCode").val();
        if (val !== undefined && val !== "") {
            $("#captchaCode-wrap").removeClass("has-error has-feedback");
            $("#captchaCode-tips").css("display", "none");

            return true;
        } else {
            $("#captchaCode-wrap").addClass("has-error has-feedback");
            $("#captchaCode-tips").css("display", "block");
            return false;
        }
    }

    function changeAvatar() {
        let file = $("#avatar")[0].files[0];
        let src = window.URL.createObjectURL(file);
        $("#avatar-img").attr("src", src);
    }

    let tabId = [1, 2]

    function show(id) {
        for (let x of tabId) {
            if (id == x) {
                $("#tab" + x).css("display", "block");
            } else {
                $("#tab" + x).css("display", "none");
            }
        }
    }


    //开启摄像头
    function openCamera() {
        //获取摄像头对象
        canvas = document.getElementById("canvas");
        context = canvas.getContext("2d");
        //获取视频流
        video = document.getElementById("video");
        let videoObj = {
            "video": true
        }, errBack = function (error) {
            console.log("Video capture error: ", error.code);
        };
        context.drawImage(video, 0, 0);
        //初始化摄像头参数
        if (navigator.getUserMedia || navigator.webkitGetUserMedia
            || navigator.mozGetUserMedia) {
            navigator.getUserMedia = navigator.getUserMedia
                || navigator.webkitGetUserMedia
                || navigator.mozGetUserMedia;
            navigator.getUserMedia(videoObj, function (stream) {
                video.srcObject = stream;
                video.play();
            }, errBack);
        }
    }

    function closeCamera() {
        if (video === undefined) {
            return;
        }
        let srcObject = video.srcObject;
        if (srcObject === undefined) {
            return
        }
        srcObject.getTracks()[0].stop();
    }

    //将当前图像传输到后台
    function faceRegister() {
        context.drawImage(video, 0, 0);
        //获取图像
        let img = getBase64();
        //Ajax将Base64字符串传输到后台处理
        $.ajax({
            type: "POST",
            async: false,
            url: "/user/face/register",
            data: {
                img: img
            },
            dataType: "JSON",
            success: function (data) {
                //返回的结果
                if (data.code >= 0) {
                    alert("注册成功！");
                    window.location.href = "/user/login"
                } else {
                    alert(data.msg);
                }
            }
        });
    }

    //将摄像头拍取的图片转换为Base64格式字符串
    function getBase64() {
        //获取当前图像并转换为Base64的字符串
        let imgSrc = canvas.toDataURL("image/png");
        //截取字符串
        return imgSrc.substring(22);
    }
</script>
</body>
</html>
