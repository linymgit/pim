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
    <h4>管理员信息</h4>
<%--    <div class="row">--%>
<%--        <div class="col-md-2 col-xs-6" style="padding: 5px">--%>
<%--            <input type="text" class="form-control" id="keyWord" placeholder="管理员姓名">--%>
<%--        </div>--%>
<%--        <div class="col-md-2 col-xs-12" style="padding: 5px">--%>
<%--            <button type="button" class="btn btn-default glyphicon glyphicon-search" onclick="search()">查找</button>--%>
<%--        </div>--%>
<%--    </div>--%>

    <div class="row">
        <table class="table table-bordered" style="margin:0.5rem;" id="yimi-table">
            <tr class="success">
                <td>ID</td>
                <td>管理员姓名</td>
                <td>管理员电话号码</td>
                <td>管理员邮箱地址</td>
                <td>联系</td>
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


        tStr = $("#yimi-table").html();
        pStr = $("#page-bar").html();

        reload(param);
    });

    function reload(p) {
        $("#yimi-table").html(tStr);
        $("#page-bar").html(pStr);
        $.ajax({
            type: 'post',
            url: '/user/relation/listAdmin',
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
                    h += '<td style="width: 20%">' + l[i].id + '</td>';
                    h += '<td style="width: 20%">' + l[i].name + '</td>';
                    h += '<td style="width: 20%">' + l[i].phone + '</td>';
                    h += '<td style="width: 20%">' + l[i].email + '</td>';
                    h += '<td style="width: 20%"><a style="cursor: pointer" onclick="toMsg()">发信息</a></td>';

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
        $("#page-row").text(i + sr);
    }


    function changePage(n) {
        param["pageNum"] = n;
        reload(param);
    }

    function toMsg() {
        window.location.href = "/user/relation/msg"
    }

</script>
</html>
