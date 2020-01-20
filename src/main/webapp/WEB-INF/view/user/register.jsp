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
    <div class="row" style="margin-top: 15vh">
        <div class="col-xs-12 col-sm-12 col-md-4 col-md-offset-4" id="aaaa">
        </div>
    </div>
</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
    $(function () {
        let formText = buildForm();
        let formElement = $(formText)
        $("#aaaa").append(formElement)
    });

    let formObject = [
        {
            "label": "用户名",
            "name": "userName",
            "icon": "glyphicon-user",
            "check": [
                {
                    "type": "nonnull",
                    "tips": "用户名不能为空"
                }
            ]
        },
        {
            "label": "真实姓名",
            "icon": "glyphicon-credit-card",
            "name": "realName",
            "check": [
                {
                    "type": "nonnull",
                    "tips": "真实姓名不能为空"
                }
            ]
        },
        {
            "label": "密码",
            "name": "passworld",
            "icon": "glyphicon-eye-close",
            "check": [
                {
                    "type": "nonnull",
                    "tips": "密码不能为空"
                }
            ],
            "inputType":"password"
        }
    ]

    function buildForm() {
        let formText = '<form class="form-horizontal">';
        let i = 1;

        for (let object of formObject) {

            formText += '<div class="form-group has-feedback" id="form-has' + i + '">';
            formText += '<label class="control-label col-sm-3" for=fEId' + i + '>' + object.label + '</label>';
            formText += '<div class="col-sm-9">';
            let n = strNonNull(object.icon);
            if (n) {
                formText += '<div class="input-group">';
                formText += '<span class="input-group-addon"><span class="glyphicon ' + object.icon + ' "></span></span>';
            }
            let iT = "text";
            if (strNonNull(object.inputType)) {
                iT = object.inputType;
            }
            formText += '<input type="'+ iT +'" name="' + object.name + '" class="form-control" id="form-id' + i + '"';

            if (object.check !== undefined && object.check != null) {
                formText += 'onblur="doCheck(' + i + ')"';
            }
            formText += '>';
            formText += '<span class="glyphicon form-control-feedback" id="span-ok' + i + '"></span>'

            if (n) {
                formText += '</div>';
            }
            formText += '<span style="color: #a94442" id="span-tips' + i + '"></span>'
            formText += '</div></div>';

            i++;
        }

        formText += "</form>";
        return formText;
    }

    function strIsNull(str) {
        return str == null || str === "";
    }

    function strNonNull(str) {
        return !strIsNull(str)
    }

    function doCheck(n) {
        let $1 = $('#form-id' + n);
        let $2 = $('#form-has' + n);
        let $3 = $('#span-ok' + n);
        let $4 = $('#span-tips' + n);
        let val = $1.val();

        let check = formObject[n - 1].check;
        let tips = '';
        let ok = true;

        for (let c of check) {
            if (c.type === 'nonnull') {
                if (strIsNull(val)) {
                    tips += c.tips;
                    ok = false;
                }
            }
        }

        if (ok) {
            $2.removeClass("has-error");
            $2.addClass("has-success");
            $3.removeClass("glyphicon-remove");
            $3.addClass("glyphicon-ok");
            $4.text(tips);
        } else {
            $2.removeClass("has-success");
            $2.addClass("has-error");
            $3.remove("glyphicon-ok");
            $3.addClass("glyphicon-remove");
            $4.text(tips)
        }

    }

</script>
</body>
</html>
