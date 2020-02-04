<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/1/25
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>xxx个人信息管理系统</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<style type="text/css">

    .lym-nav {
        margin-top: 1rem;
        font-size: 1.8rem;
        color: #F9F9F9;
        cursor: pointer;
        min-height: 5vh;
        background-color: #252626;
        text-align: center;
        padding: 2rem;
    }

    .lym-sub-nav {
        padding: 1rem;
        font-size: 1.5rem;
        color: #F9F9F9;
        cursor: pointer;
        min-height: 5vh;
        background-color: #252626;
        text-align: center;
        text-decoration: underline;
        display: none;
        border-radius: 0.5rem;
    }

    .lym-nav:hover {
        background-color: #000000;
    }

    .lym-sub-nav:hover {
        background-color: #131314;
    }

    div::-webkit-scrollbar {
        width: 0;
    }

    .hide-icon {
        margin-top: 2vh;
    }

    .avatar {
        width: 6vh;
        height: 6vh;
        float: right;
        margin-top: 1vh;
        border-radius: 50%
    }

    .yimi-nav-wrap {
        background-color: #131314;
        max-height: 92vh;
        min-height: 92vh;
        padding: 0.5rem;
        overflow-y: scroll
    }

    .close-icon, .refresh-icon {
        color: #252626;
        position: absolute;
        top: 2rem;
    }

    .close-icon:active, .refresh-icon:active {
        color: red;
    }


    @media screen and (max-width: 600px) {
        .hide-icon {
            display: none;
        }

        .yimi-nav-wrap {
            max-height: 75vh;
            min-height: 75vh;
        }
    }


</style>
<body>
<div class="container-fluid">
    <div class="row" style="background-color: #281A33;min-height: 8vh;line-height: 8vh">
        <div class="col-md-2 col-xs-6" id="logo" style="color: #00710F">
            xxx个人信息管理系统
        </div>
        <div class="col-md-8 col-xs-6" id="logo-hide">
            <span class="glyphicon glyphicon-menu-hamburger hide-icon" style="color: #00710F"
                  onclick="hideNav()"></span>
            <img id="avatar" class="avatar img-responsive" alt="avatar">
        </div>
        <div class="col-md-2" style="color: #00710F;float: right">
            <span id="name"></span>
            <span style="cursor: pointer" onclick="exit()">，退出</span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 yimi-nav-wrap" id="nav" style="scrollbar-width: none;">
            <div class="lym-nav" id="n1" onclick="exportMenu(1)">个人信息管理
                <div class="lym-sub-nav" id="sn1">
                    <div><a onclick="to('/user/mail/verify')">验证邮箱</a></div>
                    <div><a onclick="to('/user/phone/verify')">验证手机号</a></div>
                    <div><a onclick="to('/user/info')">修改个人信息</a></div>
                    <div><a onclick="to('/user/pw/reset')">重置密码</a></div>
                </div>
            </div>
            <div class="lym-nav" id="n2" onclick="exportMenu(2)">日程管理
                <div class="lym-sub-nav" id="sn2">
                    <div><a onclick="to('/user/schedule/list')">日程管理</a></div>
                </div>
            </div>
            <div class="lym-nav" id="n3" onclick="exportMenu(3)">个人财产/收支数据统计
                <div class="lym-sub-nav" id="sn3">
                    <div><a onclick="to('/user/assets/income')">个人财产管理</a></div>
                    <div><a onclick="to('/user/assets/output')">个人收支管理</a></div>
                    <div><a onclick="to('/')">数据分析</a></div>
                </div>
            </div>
            <div class="lym-nav" id="n4" onclick="exportMenu(4)">个人通讯录
                <div class="lym-sub-nav" id="sn4">
                    <div><a onclick="to('/user/relation/list')">联系人信息</a></div>
                    <div><a onclick="to('/user/relation/listAdmin')">管理员信息</a></div>
                    <div><a onclick="to('/user/chat/list')">好友信息</a></div>
                    <div><a onclick="to('/user/chat/admins')">管理员信息</a></div>
                    <%--<div><a onclick="to('/user/info')">发邮件</a></div>--%>
                </div>
            </div>
            <div class="lym-nav" id="n5" onclick="exportMenu(5)">文件中心
                <div class="lym-sub-nav" id="sn5">
                    <div><a onclick="to('/user/file/upload')">文件上传</a></div>
                    <div><a onclick="to('/user/file/list')">上传记录</a></div>
                </div>
            </div>
            <div class="lym-nav" id="n6" onclick="exportMenu(6)">系统设置
                <div class="lym-sub-nav" id="sn6">
                    <div><a onclick="to('/user/pw/reset')">修改密码</a></div>
                    <div><a onclick="to('/user/schedule/list')">日程提醒设置</a></div>
<%--                    <div><a onclick="to('/user/file/list')">样式主题切换</a></div>--%>
                </div>
            </div>
        </div>
        <div class="col-md-10" id="center" style="background-color: #252626">
            <div style="min-height: 85vh;max-height: 85vh;overflow-y: scroll;scrollbar-width: none;">
                <iframe
                        style="padding-top: 1.5rem;"
                        id="frame"
                        ref="iframe"
                        src=""
                        width="100%"
                        height="85%"
                        frameBorder="0">
                </iframe>
                <span class="glyphicon glyphicon-remove-circle close-icon" style="right: 3rem" title="关闭"
                      onclick="closeFrame()"></span>
                <span class="glyphicon glyphicon glyphicon-refresh refresh-icon"
                      style="right: 5rem" title="刷新"
                      onclick="refreshFrame()"></span>
            </div>
            <div class="row"
                 style="text-align: center;background-color: #281A33;color:#00710F;min-height: 7vh;line-height: 7vh">
                @2020 xxx个人信息管理系统
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    let ws = new WebSocket("ws://localhost:8080/ws?x-token=" + localStorage.getItem("x-token"));
    $(function () {
        $.ajax({
            //请求方式
            type: "POST",
            //请求地址
            url: "/user/index",
            //请求成功
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (result) {
                if (result.code >= 0) {
                    //登录成功
                    $("#avatar").attr("src", result.data.user.avatar);

                    let name = "无设置用户名";
                    if (result.data.user.name !== undefined) {
                        name = result.data.user.name;
                    }
                    $("#name").text(name);

                    if (result.data.schedules.length <= 0) {
                        setTimeout(function(){
                            alert("暂无日程信息");
                        },800);
                    } else {
                        let msg = "你有" + result.data.schedules.length + "条日程信息！\r\n"
                        for (let i in result.data.schedules) {
                            msg += i + "、时间：" + result.data.schedules[i].startTime + "，\r\n日程内容：" + result.data.schedules[i].plan + "。\r\n"
                        }
                        alert(msg);
                    }
                } else {
                    window.location.href = "/user/login"
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });

        to("/user/info");
    });

    ws.onopen = function (evt) {
        console.log("已连接websocket")
    };

    ws.onmessage = function (evt) {
        let o = JSON.parse(evt.data);
        sel = confirm("你有一个日程提醒!\r\n时间：" + o.startTime + "，\r\n日程内容：" + o.plan + "。\r\n\r\n是否取消提醒？"); //在页面上弹出对话框
        if (sel === true) {
            $.ajax({
                //请求方式
                type: "POST",
                //请求地址
                url: "/user/schedule/remind/cancel?id=" + o.id,
                //请求成功
                headers: {
                    'x-token': localStorage.getItem("x-token"),
                },
                success: function (result) {
                    console.log(JSON.stringify(result));
                },
                //请求失败，包含具体的错误信息
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        }
    };

    let hideFlag = true;

    function hideNav() {
        if (hideFlag) {
            $("#nav").css("display", "none");
            $("#logo").css("display", "none");
            $("#center").attr("class", "col-md-12")
            $("#logo-hide").attr("class", "col-md-10 col-xs-6")
        } else {
            $("#nav").css("display", "block");
            $("#logo").css("display", "block");
            $("#center").attr("class", "col-md-10")
            $("#logo-hide").attr("class", "col-md-8 col-xs-6")
        }
        hideFlag = !hideFlag;
    }

    function exit() {
        localStorage.removeItem("x-token");
        window.location.href = "/user/login"
    }

    let showNavFlag = [false, false, false, false, false, false]

    function exportMenu(id) {
        if (!showNavFlag[id]) {
            $("#sn" + id).css("display", "block");
            showNavFlag[id] = true;
        } else {
            $("#sn" + id).css("display", "none");
            showNavFlag[id] = false;
        }
    }

    function to(url) {
        let token = localStorage.getItem("x-token");
        if (token === undefined || token === "") {
            window.location.href = "/user/login"
            return;
        }

        // $("#frame").attr("src", url + "?x-token=" + token);
        $("#frame").attr("src", url + "?x-token=" + token);
        if (document.body.clientWidth < 600) {
            $("#nav").css("display", "none");
        }
    }

    function closeFrame() {
        $("#frame").attr("src", "")
        if (document.body.clientWidth < 600) {
            $("#nav").css("display", "block");
        }
    }

    function refreshFrame() {
        let $frame = $("#frame");
        $frame.attr("src", $frame.attr("src"))
    }
</script>
</html>
