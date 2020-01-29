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
         style="background-image: url('https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1579197132065&di=d77563a2e327982a1bfe06147ca71a52&imgtype=0&src=http%3A%2F%2Fimg.pptjia.com%2Fimage%2F20190223%2F69a70f4ad56095d768033c59d2def273.jpeg');"></div>
    <div class="alert alert-success alert-dismissable" role="alert" style="text-align: center; display: none" id="do-result">
        <button class="close" type="button" data-dismiss="alert" onclick="closeAlert()">&times;</button>
    </div>
    <h4 style="text-align: center;padding-bottom: 5vh">注册会员</h4>
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-4 col-md-offset-4" id="form-wrap">
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
        renderForm($("#form-wrap"), ff)
        renderCaptcha()
    });

    let f = [
        {
            "label": "用户名",
            "name": "name",
            // "icon": "glyphicon-user",
            "check": [
                {
                    "type": "nonnull",
                    "tips": "用户名不能为空"
                }
            ]
        },
        {
            "label": "密码",
            "name": "password",
            // "icon": "glyphicon-eye-close",
            "check": [
                {
                    "type": "nonnull",
                    "tips": "密码不能为空"
                }
            ],
            "inputType": "password"
        },
        {
            "label": "真实姓名",
            // "icon": "glyphicon-credit-card",
            "name": "relname",
            "check": [
                {
                    "type": "nonnull",
                    "tips": "真实姓名不能为空"
                }
            ]
        },
        {
            "label": "性别",
            "inputType": "radio",
            "name": "sex",
            "ops": [
                {
                    "name": "男",
                    "value": 1,
                },
                {
                    "name": "女",
                    "value": 0,
                }
            ]
        },
        {
            "label": "手机号码",
            // "icon": "glyphicon-earphone",
            "name": "phone",
            "check": [
                {
                    "type": "phone",
                    "tips": "手机号码格式不正确"
                }
            ]
        },
        {
            "label": "邮箱",
            // "icon": "glyphicon-share-alt",
            "name": "email",
            "check": [
                {
                    "type": "email",
                    "tips": "邮箱格式不正确"
                }
            ]
        },
        {
            "label": "地址",
            "name": "address",
            // "icon": "glyphicon-home",
            // "check": [
            //     {
            //         "type": "nonnull",
            //         "tips": "地址不能为空"
            //     }
            // ]
        },
        {
            "label": "工作",
            "name": "job",
            // "icon": "glyphicon-tasks",
            // "check": [
            //     {
            //         "type": "nonnull",
            //         "tips": "工作不能为空"
            //     }
            // ]
        },
        {
            "label": "验证码",
            "inputType": "captcha",
            "icon": "glyphicon-picture",
            "check": [
                {
                    "type": "nonnull",
                    "tips": "验证码必填"
                }
            ]
        },
        {
            "name": "提交",
            "inputType": "button",
            "class": "col-md-3 col-sm-12 col-xs-12 btn",
            "style": "float:right;margin:0.2rem;",
            "success": successCallBack,
            "fail": failCallBack,
            "formHandler": function () {
                ff["url"] = "/user/do/register";
                ff["method"] = "POST";
            },
        },
        {
            "name": "重置",
            "inputType": "button",
            "class": "col-md-3 col-sm-12 col-xs-12 btn",
            "style": "float:right;margin:0.2rem;",
            "formHandler": "reset"
        },
        {
            "name": "去登录",
            "inputType": "button",
            "class": "col-md-3 col-sm-12 col-xs-12 btn",
            "style": "float:right;margin:0.2rem;",
            "formHandler": function () {
                window.location.href="/user/login"
            }
        }
    ]

    let ff = {
        "fields": f,
    }

    function successCallBack(s) {
        let $1 = $("#do-result")
        $1.append("注册成功！已为你跳转到登录页");
        $1.css("display", "block");
        setTimeout(function (){
            window.location.href = "/user/login"
        }, 1500);
    }

    function failCallBack(s) {
        let $1 = $("#do-result")
        $1.attr("class", "alert alert-danger alert-dismissable")
        $1.append("注册失败！因为"+s.msg);
        $1.css("display", "block");
    }

    function closeAlert() {
        window.location.reload();
    }

</script>
</body>
</html>
