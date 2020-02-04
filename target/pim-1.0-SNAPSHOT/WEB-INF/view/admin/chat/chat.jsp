<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/2/2
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息管理</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <style>
        .unread-tip {
            background-color: red;
            border-radius: 50%;
            width: 1.5rem;
            height: 1.5rem;
            position: absolute;
            right: 1rem;
            top: 1rem;
            font-size: 1.5rem;
            text-align: center;
            line-height: 1.5rem;
        }

        .msg-bg {
            margin: 0.5rem;
            padding: 2rem;
            background-color: #252626;
            border-radius: 0.5rem;
            color: white;
        }

        .avatar-bg {
            width: 4rem;
            height: 4rem;
            border-radius: 50%
        }

        .msg-r {
            margin: 1rem;
            background-color: #1ea0fa;
            padding: 1rem;
            border-radius: 1rem
        }

        .msg-s {
            margin: 1rem;
            background-color: #BF5E6F;
            padding: 1rem;
            border-radius: 1rem
        }


        .msg-b {
            margin: 1rem;
            background-color: #494a4a;
            padding: 1rem;
            border-radius: 1rem
        }

        div::-webkit-scrollbar {
            display: none;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 col-xs-12 hidden-sc">
            <h4>信息管理</h4>
            <div style="height: 10vh">
            </div>
            <div id="userList" style="height:62vh;overflow-y:scroll;scrollbar-width: none;">
            </div>

            <div style="height:20vh">
            </div>
        </div>
    </div>
</div>
</body>
<script>
    let selectId = 0;

    let param = {
        pageNum: 1,
        pageSize: 4
    };

    let tempPage = 1;

    let maxPage = 2;

    let temp = 0;

    let showNomore = true;

    $(function () {
        reload(param);

        // 滑动加载更多
        $("#userList").scroll(function () {
            let scrollTop = $(this).scrollTop();
            if (scrollTop - temp >= 0) {

                tempPage += 1;
                if (tempPage <= maxPage) {
                    param["pageNum"] = tempPage;
                    reload(param);
                }
                if (tempPage - 10 > maxPage && showNomore) {
                    $("#userList").append("<div style='text-align: center'>我是有底线的~~~</div>");
                    showNomore = false;
                }

            }
            temp = scrollTop;
        });
    });

    function reload(p) {
        $.ajax({
            type: 'post',
            url: '/admin2020/user/list',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(p), //数据
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (data, textStatus, jqXHR) {
                let h = '';
                let l = data.data.list;
                if (data.data.hasNextPage) {
                    maxPage = maxPage + 1;
                } else {
                    maxPage = data.data.pages;
                }
                for (let i in l) {
                    h += '<div class="msg-bg">\n' +
                        '                    <div onclick="showForm(\'' + l[i].id + '\')">\n' +
                        '                        <img src="' + l[i].avatar + '" class="avatar-bg">\n' +
                        '                        <span style="margin-left: 1rem;">' + handlerNameUndefine(l[i].name) + '</span>\n' +
                        '                    </div>\n' +
                        '<div id="msg' + l[i].id + '"></div>' +
                        '                </div>';
                }
                $("#userList").append(h);
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    function showForm(id) {
        if (selectId > 0) {
            if (id !== selectId) {
                $("#msg" + selectId).text("");
            } else {
                $("#msg" + id).text("");
                selectId = 0;
                return;
            }
        }

        let s = ' <div style="margin-top: 2rem">\n';

        $.ajax({
            type: 'post',
            url: '/admin2020/chat/list?userId=' + id,
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (data, textStatus, jqXHR) {
                let l = data.data;
                for (let i in l) {
                    let tempName = handlerNameUndefine(l[i].name);
                    tempName += "-"+l[i].fromUserId;
                    if (l[i].toUserId == id) {
                        s += '                        <div class="msg-s">\n' +
                            '                            <span style="float: right">' + l[i].msg + ' :我</span>\n' +
                            '                            <span>' + l[i].createTime + '</span>\n' +
                            '                        </div>\n';
                    } else {
                        s += '                        <div class="msg-r">\n' +
                            '                            <span>' + tempName + '：' + l[i].msg + '</span>\n' +
                            '                            <span style="float: right">' + l[i].createTime + '</span>\n' +
                            '                        </div>\n';
                    }

                }
                s += '                        <div class="msg-b">\n' +
                    '                            <div class="form-group">\n' +
                    '                                <textarea class="form-control" id="msgText"></textarea>\n' +
                    '                            </div>\n' +
                    '                            <button id="sendMsgBtn" type="submit" class="btn btn-default" onclick="sendMsg(' + id + ')">发送</button>\n' +
                    '                        </div>\n' +
                    '                    </div>';
                $("#msg" + id).append(s);
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });

        selectId = id;
    }

    function sendMsg(id) {
        let val = $("#msgText").val();
        if (val === "") {
            return;
        }
        $("#sendMsgBtn").attr("disabled","true");
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url: "/admin2020/chat/add",
            //数据，json字符串
            data: JSON.stringify({
                toUserId: id,
                msg: val,
            }),
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (result) {
                if (result.code >= 0) {
                    $("#msg" + id).text("");
                    //登录成功
                    showForm(id);
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

    function handlerUndefine(s) {
        if (s === undefined || s === "") {
            return "-";
        }
        return s;
    }

    function handlerNameUndefine(s) {
        if (s === undefined || s === "") {
            return "未命名";
        }
        return s;
    }
</script>
</html>
