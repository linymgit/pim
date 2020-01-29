function renderForm($1, formObject) {
    $1.append($(buildForm(formObject)))
}

let gFormObject;
let gI;

function buildForm(formObject) {
    gFormObject = formObject;
    let formText = '<form class="form-horizontal" id="yimi-form-v1">';
    let i = 1;

    for (let object of formObject.fields) {
        let iT = "text";
        if (strNonNull(object.inputType)) {
            iT = object.inputType;
        }
        //按钮的处理
        if (iT === "button") {
            formText += '<button type="button" onclick=\'goFun(' + object.formHandler + ',' + object.success + ',' + object.fail + ')\' class="' + object.class + '" style="' + object.style + '">' + object.name + '</button>';
            continue;
        }
        //超链接处理
        if (iT === "a") {
            formText += '<a href="' + object.href + '"';
            if (strNonNull(object.class)) {
                formText += ' class="' + object.class + '"';
            }
            if (strNonNull(object.style)) {
                formText += ' style="' + object.style + '"';
            }
            formText += ' >' + object.name + '</a>';
            continue;
        }
        formText += '<div class="form-group has-feedback" id="form-has' + i + '">';
        formText += '<label class="control-label col-sm-3" for=form-id' + i + '>' + object.label + '</label>';
        formText += '<div class="col-sm-9">';
        let n = strNonNull(object.icon);
        if (n) {
            formText += '<div class="input-group">';
            formText += '<span class="input-group-addon"><span class="glyphicon ' + object.icon + ' "></span></span>';
        }

        if (iT === "text" || iT === "password") {
            formText += '<input type="' + iT + '" name="' + object.name + '" class="form-control" id="form-id' + i + '"';
        }

        //验证码特殊处理
        if (iT === "captcha") {
            formText += '<input type="hidden" id="captchaId" name="captchaId">'
            formText += '<input type="text" name="captchaCode" class="form-control" id="form-id' + i + '"';
        }

        //单选框处理
        if (iT === "radio") {
            formText += '<div class="checkbox-inline">'

            for (let op of object.ops) {
                formText += op.name + '<input type="radio" name="' + object.name + '" value="' + op.value + '" checked/>';
                formText += '&nbsp;&nbsp;&nbsp;'
            }

            formText += "</div>"
        }

        if (object.check !== undefined && object.check != null) {
            formText += 'onblur="doCheck(' + i + ')" >';
        }
        if (iT === "captcha") {
            formText += '<img id="captcha" style="position: absolute;top: 2px;right: 2px;z-index: 1000" onclick="renderCaptcha()"></img>'
        }

        formText += '<span class="glyphicon form-control-feedback" id="span-ok' + i + '"></span>'

        if (n) {
            formText += '</div>';
        }
        formText += '<span style="color: #a94442" id="span-tips' + i + '"></span>'
        formText += '</div></div>';

        i++;
    }

    formText += "</form>";
    gI = i;
    return formText;
}

function strIsNull(str) {
    return str == null || str === "";
}

function strNonNull(str) {
    return !strIsNull(str)
}

function doCheck(n) {
    let j = 1;
    if (n > 0) {
        j = n;
    }
    for (j; j < gFormObject.fields.length; j++) {
        if (j >= gI || (n > 0 && n !== j)) {
            return true;
        }
        let $1 = $('#form-has' + j);
        let $2 = $('#span-ok' + j);
        let $3 = $('#span-tips' + j);

        if (gFormObject.fields[j - 1].check === undefined || gFormObject.fields[j - 1].check === null) {
            continue;
        }

        for (let c of gFormObject.fields[j - 1].check) {
            let val = $('#form-id' + j).val();
            let ok = true;
            if (c.type === 'nonnull') {
                ok = strIsNull(val)
            }
            if (c.type === 'phone') {
                ok = phoneFmtError(val)
            }
            if (c.type === 'email') {
                ok = emailFmtError(val)
            }
            if (ok) {
                $1.attr("class", "form-group has-feedback has-error");
                $2.attr("class", "glyphicon form-control-feedback glyphicon-remove");
                $3.text(c.tips);
                return false;
            }
            $1.attr("class", "form-group has-feedback has-success");
            $2.attr("class", "glyphicon form-control-feedback glyphicon-ok");
            $3.text("");
        }
    }
}


let emailReg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;

function emailFmtError(val) {
    return !emailReg.test(val);
}


let phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/;

function phoneFmtError(val) {
    return !phoneReg.test(val);
}

function reset() {
    for (let i = 1; i < gI; i++) {
        $('#form-id' + i).val("");
        $('#form-has' + i).attr("class", "form-group has-feedback");
        $('#span-ok' + i).attr("class", "glyphicon form-control-feedback");
        $('#span-tips' + i).text("");
    }
}

function goFun(formHandler, success, fail) {
    if (formHandler !== undefined && formHandler !== null) {
        //重置表单内容
        if (formHandler.name === "reset") {
            reset()
            return;
        }
        if (formHandler.name === "") {
            formHandler()
        }
    }
    if (!doCheck(0)) {
        return;
    }
    let serializeJson = $("#yimi-form-v1").serializeJson();
    $.ajax({
        //请求方式
        type: gFormObject["method"],
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url: gFormObject["url"],
        //数据，json字符串
        data: JSON.stringify(serializeJson),
        //请求成功
        headers: {
            'captchaId': $("#captchaId").val(),
            'captchaCode': serializeJson["captchaCode"],
        },
        success: function (result) {
            if (result.code >= 0) {
                if (success !== undefined && success !== null) {
                    success(result)
                }
            } else {
                if (fail !== undefined && fail !== null) {
                    fail(result)
                }
            }
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

$.fn.serializeJson = function (otherString) {
    let serializeObj = {},
        array = this.serializeArray();
    $(array).each(function () {
        if (serializeObj[this.name]) {
            serializeObj[this.name] += ';' + this.value;
        } else {
            serializeObj[this.name] = this.value;
        }
    });
    if (otherString !== undefined) {
        let otherArray = otherString.split(';');
        $(otherArray).each(function () {
            let otherSplitArray = this.split(':');
            serializeObj[otherSplitArray[0]] = otherSplitArray[1];
        });
    }
    return serializeObj;
};

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

function withTokenGet(uri) {
    $.ajax({
        //请求方式
        type: "GET",
        //请求地址
        url: uri,
        //请求成功
        headers: {
            'x-token': localStorage.getItem("x-token"),
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}