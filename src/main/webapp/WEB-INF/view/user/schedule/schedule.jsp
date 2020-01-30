<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/1/27
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <style>
        .yimi-cp {
            cursor: pointer;
        }
        div::-webkit-scrollbar {
            width: 0;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <h4>日程管理</h4>
    <div class="row">
        <div class="col-md-3 col-xs-12" style="padding: 5px;">
            <button type="button" class="btn btn-default glyphicon glyphicon-plus" data-toggle="modal" data-target="#myModal" onclick="addSchedule()">添加
            </button>
            <button type="button" class="btn btn-default glyphicon glyphicon-minus" id="delScheduleBtn"
                    onclick="delSchedule()">删除
            </button>
            <button type="button" class="btn btn-default glyphicon glyphicon-edit" id="editScheduleBtn" data-toggle="modal" data-target="#myModa2"
                    onclick="updateSchedule()">编辑
            </button>
        </div>
        <div class="col-md-2 col-xs-6" style="padding: 5px">
                <input type="text" class="form-control" id="plan" placeholder="日程内容">
        </div>
        <div class="col-md-2 col-xs-6" style="padding: 5px">
            <input type="date" class="form-control" id="date" placeholder="选择日期">
        </div>
        <div class="col-md-2 col-xs-12" style="padding: 5px">
            <button type="button" class="btn btn-default glyphicon glyphicon-search" onclick="search()">查找</button>
        </div>
    </div>

    <div class="row">
        <table class="table table-bordered" style="margin:0.5rem;" id="yimi-table">
            <tr class="success">
                <td>日程ID</td>
                <td>日程内容</td>
                <td>提醒次数</td>
                <td>提醒间隔</td>
                <td>已提醒次数</td>
                <td>日程时间</td>
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
                <h4 class="modal-title" id="myModalLabel">添加日程</h4>
            </div>
            <div class="modal-body">
                <iframe
                        id="frame"
                        src=""
                        width="100%"
                        height="100%"
                        frameBorder="0">
                </iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="myModa2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel2">编辑日程</h4>
            </div>
            <div class="modal-body">
                <iframe
                        id="frame2"
                        src=""
                        width="100%"
                        height="100%"
                        frameBorder="0">
                </iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
            url: '/user/schedule/list',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(p), //数据
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (data, textStatus, jqXHR) {
                let h = '';
                let l = data.data.list;
                for (let i in l) {
                    h += '<tr onclick="chooseId(\'' + l[i].id + '\',' + i + ')" id="item' + l[i].id + '" class="yimi-cp">';
                    h += '<td style="width: 16.6%">' + l[i].id + '</td>';
                    h += '<td style="width: 16.6%">' + l[i].plan + '</td>';
                    h += '<td style="width: 16.6%">' + l[i].remindSum + '次</td>';
                    h += '<td style="width: 16.6%">' + l[i].remindPeriod + 'min/次</td>';
                    h += '<td style="width: 16.6%">' + l[i].remindCount + '次</td>';
                    h += '<td style="width: 16.6%">' + l[i].startTime + '</td>';
                    // h += '<td style="width: 14%">' + handlerDate(l[i].endTime) + '</td>';
                    h += '</tr>'
                }
                $("#yimi-table").append(h);

                let pageBarButtonStr = "";
                for (let i = 1; i <= data.data.pages; i++) {
                    pageBarButtonStr += '<button type="button" class="btn btn-default" onclick="changePage(' + i + ')">' + i + '</button>'
                }
                $("#page-bar").append(pageBarButtonStr);

                $("#page-row").text(data.data.startRow);
                $("#page-rows").text(data.data.size);

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

    function addSchedule() {
        $("#frame").attr("src","/user/schedule/add");
    }

    function delSchedule() {
        $.ajax({
            type: 'post',
            url: '/user/schedule/del/' + gId,
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
        param["keyWord"] = $("#plan").val();
        param["date"] = $("#date").val();
        reload(param);
    }

    function updateSchedule() {
        $("#frame2").attr("src","/user/schedule/edit/"+gId);
    }

    function handlerDate(date) {
        let d = new Date(date);
        return d.getFullYear() + "年" + (d.getMonth() + 1) + "月" + d.getDate() + "日" + " " + d.getHours() + ":" + d.getMinutes()
    }

    function changePage(n) {
        param["pageNum"] = n;
        reload(param);
    }

    $('#myModal').on('hide.bs.modal', function () {
       reload(param);
    });

    $('#myModa2').on('hide.bs.modal', function () {
        reload(param);
    });
</script>
</html>
