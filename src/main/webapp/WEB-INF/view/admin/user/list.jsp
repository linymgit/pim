<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/1/30
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>联系人</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <h4>用户列表</h4>
    <div class="row">
        <div class="col-md-3 col-xs-12" style="padding: 5px;">
            <%--            <button type="button" class="btn btn-default glyphicon glyphicon-plus" data-toggle="modal" data-target="#myModal" onclick="addIncome()">添加--%>
            <%--            </button>--%>
            <button type="button" class="btn btn-default glyphicon glyphicon-export" id="delScheduleBtn"
                    data-toggle="modal" data-target="#myModal2"
                    onclick="banUser()">封号
            </button>
            <button type="button" class="btn btn-default glyphicon glyphicon-import" id="unbanBtn"
                    onclick="unBanUser()">解封
            </button>
            <button type="button" class="btn btn-default glyphicon glyphicon-edit" id="editScheduleBtn"
                    data-toggle="modal" data-target="#myModal" onclick="updatePW()">修改密码
            </button>
        </div>
        <div class="col-md-2 col-xs-6" style="padding: 5px">
            <input type="text" class="form-control" id="keyWord" placeholder="用户名">
        </div>
        <div class="col-md-2 col-xs-6" style="padding: 5px">
            <input type="text" class="form-control" id="id" placeholder="用户ID">
        </div>
        <div class="col-md-2 col-xs-12" style="padding: 5px">
            <button type="button" class="btn btn-default glyphicon glyphicon-search" onclick="search()">查找</button>
        </div>
    </div>

    <div class="row">
        <table class="table table-bordered" style="margin:0.5rem;" id="yimi-table">
            <tr class="success">
                <td>ID</td>
                <td>用户名</td>
                <td>真实姓名</td>
                <td>性别</td>
                <td>头像</td>
                <td>手机号</td>
                <td>邮箱地址</td>
                <td>工作</td>
                <td>地址</td>
                <td>封禁时间</td>
            </tr>
        </table>
    </div>
    <div class="row">
        <div class="btn-group" role="group" aria-label="..." style="float: right;margin-right: 0.5rem" id="page-bar">
            <button type="button" class="btn btn-default" disabled>10条/页</button>
            <button type="button" class="btn btn-default" disabled>
                第<span id="page-row"></span>条，共<span id="page-rows"></span>条
            </button>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加资产</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 col-md-3 col-md-offset-2">
                            <div class="form-group has-success has-feedback" id="typeWrap">
                                <label class="control-label">用户名</label>
                                <input type="text" class="form-control" id="name" disabled>
                            </div>
                            <div class="form-group has-success has-feedback" id="passwordWrap">
                                <label class="control-label">密码</label>
                                <input type="password" class="form-control" id="password">
                            </div>
                            <div class="form-group has-success has-feedback" id="re-passwordWrap">
                                <label class="control-label">确认密码</label>
                                <input type="password" class="form-control" id="re-password">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-default" onclick="submit()">提交</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel2">封禁用户</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 col-md-3 col-md-offset-2">
                            <div class="form-group has-success has-feedback" id="userIdWrap">
                                <label class="control-label">用户ID</label>
                                <input type="text" class="form-control" id="userId" disabled>
                            </div>
                            <div class="form-group has-success has-feedback" id="name2Wrap">
                                <label class="control-label">用户名</label>
                                <input type="text" class="form-control" id="name2" disabled>
                            </div>
                            <div class="form-group has-success has-feedback" id="banDateWrap">
                                <label class="control-label">封禁日期</label>
                                <input type="date" class="form-control" id="banDate">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-default" onclick="ban()">提交</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    let param = {
        pageNum: 1,
        pageSize: 10
    };
    let gId = 0;
    let sr = 0;
    let tStr = '';
    let pStr = '';

    let gList;

    let addOrUpdateUrl = '';

    $(function () {

        $("#delScheduleBtn").attr('disabled', true);
        $("#editScheduleBtn").attr('disabled', true);
        $("#unbanBtn").attr('disabled', true);

        tStr = $("#yimi-table").html();
        pStr = $("#page-bar").html();

        reload(param);
    });

    function reload(p) {
        $("#yimi-table").html(tStr);
        $("#page-bar").html(pStr);
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
                gList = data.data.list;
                let l = data.data.list;
                for (let i in l) {
                    h += '<tr onclick="chooseId(\'' + l[i].id + '\',' + i + ')" id="item' + l[i].id + '" class="yimi-cp">';
                    h += '<td style="width: 10%">' + l[i].id + '</td>';
                    h += '<td style="width: 10%">' + handlerUndefine(l[i].name) + '</td>';
                    h += '<td style="width: 10%">' + handlerUndefine(l[i].relname) + '</td>';
                    h += '<td style="width: 10%">' + convertSex(l[i].sex) + '</td>';
                    h += '<td style="width: 10%"><img src=\'' + l[i].avatar + '\' style="width: 3.5rem;height: 3.5rem;border-radius: 50%"></td>';
                    h += '<td style="width: 10%">' + handlerUndefine(l[i].phone) + '</td>';
                    h += '<td style="width: 10%">' + handlerUndefine(l[i].email) + '</td>';
                    h += '<td style="width: 10%">' + handlerUndefine(l[i].job) + '</td>';
                    h += '<td style="width: 10%">' + handlerUndefine(l[i].address) + '</td>';
                    h += '<td style="width: 10%">' + handlerUndefine(l[i].banTime) + '</td>';
                    if (l[i].relationStatus === 1) {
                        h += '<td style="width: 16.6%"><a onclick="addFriend(\'' + l[i].friendid + '\')" style="cursor: pointer ">添加</a></td>';
                    } else if (l[i].relationStatus === 2) {
                        h += '<td style="width: 16.6%">已添加,<a style="cursor: pointer" onclick="toMsg()">发信息</a></td>';
                    } else {
                        h += '<td style="width: 16.6%"></td>';
                    }

                    h += '</tr>'
                }
                $("#yimi-table").append(h);

                let pageBarButtonStr = "";
                for (let i = 1; i <= data.data.pages; i++) {
                    pageBarButtonStr += '<button type="button" class="btn btn-default" onclick="changePage(' + i + ')">' + i + '</button>'
                }
                $("#page-bar").append(pageBarButtonStr);

                $("#page-row").text(data.data.startRow);
                $("#page-rows").text(data.data.total);

                sr = data.data.startRow;
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }


    function chooseId(id, i) {
        if (gId > 0) {
            $("#item" + gId).removeClass("warning");
        }
        $("#item" + id).addClass("warning");
        gId = id;
        $("#delScheduleBtn").removeAttr("disabled");
        $("#editScheduleBtn").removeAttr("disabled");

        $("#page-row").text(i + sr);

        for (let x in gList) {
            if (gList[x].id == gId) {
                if (gList[x].banTime !== undefined) {
                    $("#unbanBtn").removeAttr("disabled");
                } else {
                    $("#unbanBtn").attr('disabled', true);
                }
                return;
            }
        }
    }

    function banUser() {
        let $userid = $("#userId");
        let $name2 = $("#name2");

        for (let x in gList) {
            if (gList[x].id == gId) {
                $userid.val(gList[x].id);
                $name2.val(gList[x].name);
                return;
            }
        }
    }

    function search() {
        param["keyWord"] = $("#keyWord").val();
        param["id"] = $("#id").val();
        reload(param);
    }

    function changePage(n) {
        param["pageNum"] = n;
        reload(param);
    }

    function submit() {
        let $name = $("#name");
        let $pwd = $("#password");
        let $rpwd = $("#re-password");

        if ($pwd.val() === "" && $pwd.val() === "") {
            alert("密码不可以为空！！！")
            return;
        }

        if ($pwd.val() !== $rpwd.val()) {
            alert("两次输入的密码不一致！！！")
            return;
        }
        $.ajax({
            type: 'post',
            url: "/admin2020/user/resetpw",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify({
                id: gId,
                name: $name.val(),
                password: $pwd.val(),
            }),
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (data, textStatus, jqXHR) {
                if (data.code >= 0) {
                    alert("修改成功！");
                    window.location.href = "/admin2020/user/list";
                } else {
                    alert(data.msg);
                }
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }


    function updatePW() {
        let $name = $("#name");
        let $phone = $("#phone");
        let $email = $("#email");

        for (let x in gList) {
            if (gList[x].id == gId) {
                $name.val(gList[x].name);
                $phone.val(gList[x].pone);
                $email.val(gList[x].email);
                return;
            }
        }
    }

    function addFriend(id) {
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url: "/user/relation/friend/add",
            //数据，json字符串
            data: JSON.stringify({
                friendid: id,
            }),
            //请求成功
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (result) {
                if (result.code >= 0) {
                    window.location.reload();
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

    function toMsg() {
        window.location.href = "/user/relation/msg"
    }

    function convertSex(i) {
        return i === 1 ? "男" : "女";
    }

    function handlerUndefine(s) {
        if (s === undefined || s === "") {
            return "-";
        }
        return s;
    }

    function unBanUser() {
        if (gId > 0) {
            let b = confirm("确定要解封用户(" + gId + ")吗？");
            if (b) {
                $.ajax({
                    //请求方式
                    type: "POST",
                    //请求地址
                    url: "/admin2020/user/unban",
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify({
                        id: gId,
                    }),
                    headers: {
                        'x-token': localStorage.getItem("x-token"),
                    },
                    success: function (result) {
                        if (result.code >= 0) {
                            alert("解封成功！");
                        }
                        window.location.href = "/admin2020/user/index"
                    },
                    //请求失败，包含具体的错误信息
                    error: function (e) {
                        console.log(e.status);
                        console.log(e.responseText);
                    }
                });

            }
        }
    }

    function ban() {
        let bt = $("#banDate").val();
        if (bt === undefined) {
            alert("未设置封禁时间");
            return;
        }
        $.ajax({
            //请求方式
            type: "POST",
            //请求地址
            url: "/admin2020/user/ban",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify({
                id: $("#userId").val(),
                name: $("#name2").val(),
                banTime: bt
            }),
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (result) {
                if (result.code >= 0) {
                    alert("封禁成功！");
                }
                window.location.href = "/admin2020/user/list"
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
