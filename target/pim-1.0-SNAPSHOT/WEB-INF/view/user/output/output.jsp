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
    <title>收支管理</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <h4>支出管理</h4>
    <div class="row">
        <div class="col-md-6 col-xs-12" style="padding: 5px;">
            <button type="button" class="btn btn-default glyphicon glyphicon-plus" data-toggle="modal" data-target="#myModal" onclick="addoutput()">添加
            </button>
            <button type="button" class="btn btn-default glyphicon glyphicon-minus" id="delScheduleBtn"
                    onclick="removeoutput()">删除
            </button>
            <button type="button" class="btn btn-default glyphicon glyphicon-edit" id="editScheduleBtn" data-toggle="modal" data-target="#myModal" onclick="updateoutput()">编辑</button>
            <button type="button" class="btn btn-default glyphicon glyphicon-stats" id="bar-chart"
                    onclick="genBarChart()">生成条形图
            </button>
            <button type="button" class="btn btn-default glyphicon glyphicon-adjust" id="pie-chart"
                    onclick="genPieChart()">生成饼状图
            </button>
        </div>
        <div class="col-md-2 col-xs-6" style="padding: 5px">
            <input type="date" class="form-control" id="date" placeholder="日期">
        </div>
        <div class="col-md-2 col-xs-6" style="padding: 5px">
            <button type="button" class="btn btn-default glyphicon glyphicon-search" onclick="search()">查找</button>
        </div>
    </div>

    <div class="row">
        <table class="table table-bordered" style="margin:0.5rem;" id="yimi-table">
            <tr class="success">
                <td>ID</td>
                <td>支出类型</td>
                <td>支出金额</td>
                <td>时间</td>
                <td>备注</td>
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
                <h4 class="modal-title" id="myModalLabel">支出管理</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 col-md-3 col-md-offset-2">
                            <div class="form-group has-success has-feedback" id="typeWrap">
                                <label class="control-label">支出类型</label>
                                <input type="text" class="form-control" id="type">
                                <p style='color: #BF5E6F;display: none' id="typeErr">支出类型不可为空</p>
                            </div>
                            <div class="form-group has-success has-feedback" id="valueWrap">
                                <label class="control-label">支出金额</label>
                                <input type="text" class="form-control" id="value">
                                <p style='color: #BF5E6F;display: none' id="valueErr">支出金额不可为空</p>
                            </div>
                            <div class="form-group has-success has-feedback" id="outputTimeWrap">
                                <label class="control-label">支出时间</label>
                                <input type="date" class="form-control" id="outputTime">
                                <p style='color: #BF5E6F;display: none' id="outputTimeErr">支出时间不可为空</p>
                            </div>
                            <div class="form-group has-success has-feedback">
                                <label class="control-label">备注</label>
                                <input type="text" class="form-control" id="desc">
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
            url: '/user/assets/output',
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
                    h += '<td style="width: 20%">' + l[i].type + '</td>';
                    h += '<td style="width: 20%">' + l[i].cost + '</td>';
                    h += '<td style="width: 20%">' + l[i].outputTime + '</td>';
                    h += '<td style="width: 20%">' + l[i].memo + '</td>';
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

    function removeoutput() {
        $.ajax({
            type: 'post',
            url: '/user/assets/output/remove?id=' + gId,
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
        param["date"] = $("#date").val();
        reload(param);
    }

    function changePage(n) {
        param["pageNum"] = n;
        reload(param);
    }

    function submit() {
        let $type = $("#type");
        let $value = $("#value");
        let $outputTime = $("#outputTime");
        let $desc = $("#desc");

        $("#typeErr").css("display","none");
        $("#valueErr").css("display","none");
        $("#outputTimeErr").css("display","none");
        $("#typeWrap").attr("class", "form-group has-success has-feedback");
        $("#valueWrap").attr("class", "form-group has-success has-feedback");
        $("#outputTimeWrap").attr("class", "form-group has-success has-feedback");

        if ($type.val() === "") {
            $("#typeWrap").attr("class", "form-group has-error has-feedback");
            $("#typeErr").css("display","block");
            return;
        }
        if ($value.val() === "") {
            $("#valueWrap").attr("class", "form-group has-error has-feedback");
            $("#valueErr").css("display","block");
            return;
        }
        if ($outputTime.val() === "") {
            $("#outputTimeWrap").attr("class", "form-group has-error has-feedback");
            $("#outputTimeErr").css("display","block");
            return;
        }

        $.ajax({
            type: 'post',
            url: addOrUpdateUrl,
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                id: gId,
                type:$type.val(),
                cost:$value.val(),
                outputTime:$outputTime.val(),
                memo:$desc.val(),
            }),
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (data, textStatus, jqXHR) {
                if (data.code>=0) {
                    window.location.href = "/user/assets/output";
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

    function addoutput() {
        // $("#outputTime").removeAttr("disabled");
        let $type = $("#type");
        let $value = $("#value");
        let $outputTime = $("#outputTime");
        let $desc = $("#desc");

        $type.val("");
        $value.val("");
        $outputTime.val("");
        $desc.val("");

        addOrUpdateUrl = "/user/assets/output/add";
    }

    function updateoutput() {
        let $type = $("#type");
        let $value = $("#value");
        let $outputTime = $("#outputTime");
        let $desc = $("#desc");

        // $outputTime.attr("disabled", "true");

        addOrUpdateUrl = "/user/assets/output/update";

        for (let x in gList) {
            if (gList[x].id == gId) {
                $type.val(gList[x].type);
                $value.val(gList[x].cost);
                $outputTime.val(gList[x].outputTime);
                $desc.val(gList[x].memo);
                return;
            }
        }
    }

    let height = window.screen.height;
    let width = window.screen.width;

    function genBarChart() {
        window.open ("/user/assets/output/barchart", "条形图", "height="+height*0.8+", width="+width*0.8+",top="+height*0.05+",left="+width*0.1+",toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")

    }

    function genPieChart() {
        window.open ("/user/assets/output/pieChart", "饼状图", "height="+height*0.8+", width="+width*0.8+",top="+height*0.05+",left="+width*0.1+",toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
    }
</script>
</html>
