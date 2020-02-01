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
    <h4>联系人</h4>
    <div class="row">
        <div class="col-md-3 col-xs-12" style="padding: 5px;">
            <button type="button" class="btn btn-default glyphicon glyphicon-plus" data-toggle="modal" data-target="#myModal" onclick="addIncome()">添加
            </button>
            <button type="button" class="btn btn-default glyphicon glyphicon-minus" id="delScheduleBtn"
                    onclick="removeIncome()">删除
            </button>
            <button type="button" class="btn btn-default glyphicon glyphicon-edit" id="editScheduleBtn" data-toggle="modal" data-target="#myModal" onclick="updateIncome()">编辑</button>
        </div>
        <div class="col-md-2 col-xs-6" style="padding: 5px">
            <input type="text" class="form-control" id="keyWord" placeholder="联系人姓名">
        </div>
        <div class="col-md-2 col-xs-12" style="padding: 5px">
            <button type="button" class="btn btn-default glyphicon glyphicon-search" onclick="search()">查找</button>
        </div>
    </div>

    <div class="row">
        <table class="table table-bordered" style="margin:0.5rem;" id="yimi-table">
            <tr class="success">
                <td>ID</td>
                <td>联系人姓名</td>
                <td>联系人电话号码</td>
                <td>联系人邮箱地址</td>
                <td>添加时间</td>
                <td>好友</td>
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
                                <label class="control-label">联系人姓名</label>
                                <input type="text" class="form-control" id="name">
                            </div>
                            <div class="form-group has-success has-feedback" id="valueWrap">
                                <label class="control-label">联系人电话号码</label>
                                <input type="text" class="form-control" id="phone">
                            </div>
                            <div class="form-group has-success has-feedback" id="incomeTimeWrap">
                                <label class="control-label">联系人邮箱地址</label>
                                <input type="text" class="form-control" id="email">
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

        tStr = $("#yimi-table").html();
        pStr = $("#page-bar").html();

        reload(param);
    });

    function reload(p) {
        $("#yimi-table").html(tStr);
        $("#page-bar").html(pStr);
        $.ajax({
            type: 'post',
            url: '/user/relation/list',
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
                    h += '<td style="width: 16.6%">' + l[i].id + '</td>';
                    h += '<td style="width: 16.6%">' + l[i].name + '</td>';
                    h += '<td style="width: 16.6%">' + l[i].pone + '</td>';
                    h += '<td style="width: 16.6%">' + l[i].email + '</td>';
                    h += '<td style="width: 16.6%">' + l[i].createTime + '</td>';
                    if (l[i].relationStatus === 1){
                        h += '<td style="width: 16.6%"><a onclick="addFriend(\''+l[i].friendid+'\')" style="cursor: pointer ">添加</a></td>';
                    }else if(l[i].relationStatus === 2){
                        h += '<td style="width: 16.6%">已添加,<a style="cursor: pointer" onclick="toMsg()">发信息</a></td>';
                    }else{
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
    }

    function removeIncome() {
        $.ajax({
            type: 'post',
            url: '/user/relation/remove?id=' + gId,
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (data, textStatus, jqXHR) {
                reload(param)
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    function search() {
        param["keyWord"] = $("#keyWord").val();
        reload(param);
    }

    function changePage(n) {
        param["pageNum"] = n;
        reload(param);
    }

    function submit() {
        let $name = $("#name");
        let $phone = $("#phone");
        let $email = $("#email");

        if ($name.val()==="" && $phone.val()==="" && $email.val()==="") {
            alert("没有录入联系人的信息！！！")
            return;
        }

        $.ajax({
            type: 'post',
            url: addOrUpdateUrl,
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                id: gId,
                name:$name.val(),
                pone:$phone.val(),
                email:$email.val(),
            }),
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (data, textStatus, jqXHR) {
                if (data.code>=0) {
                    window.location.href = "/user/relation/list";
                }else{
                    return;
                }
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }

    function addIncome() {
        // $("#incomeTime").removeAttr("disabled");
        let $name = $("#name");
        let $phone = $("#phone");
        let $email = $("#email");

        $name.val("");
        $phone.val("");
        $email.val("");

        addOrUpdateUrl = "/user/relation/add";
    }

    function updateIncome() {
        let $name = $("#name");
        let $phone = $("#phone");
        let $email = $("#email");

        // $incomeTime.attr("disabled", "true");

        addOrUpdateUrl = "/user/relation/update";

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
</script>
</html>
