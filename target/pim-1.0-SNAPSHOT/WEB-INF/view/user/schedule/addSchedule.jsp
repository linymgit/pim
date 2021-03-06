<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/1/29
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加日程</title>
    <link rel="stylesheet" href="../../../static/css/dateTime.css" charset="UTF-8">
    <style>
        .wrap{
            margin: 1rem;
        }
        .wrap-s{
            margin-left: 20rem;
        }

        .sel{
            margin-bottom: 0.5rem;
        }

        .error{
            color: red;
        }

        @media screen and (max-width: 600px) {
            .wrap-s{
                margin-left: 0;
            }
        }

        .btn{
            width: 10rem;
            height: 2rem;
            background-color: #e2e2e2;
            border-radius: 1rem;
        }
    </style>
</head>
<body>
<div class="mycontainer wrap-s" id="form">
    <div class="wrap">
        <div>日程时间：</div>
        <input type="text" id="datetime">
        <span class="error" id="datetimeErr"></span>
    </div>
    <div class="wrap">
        <div>日程内容：</div>
        <textarea rows="4" cols="40" style="border:1px solid #ccc;" id="plan" onblur="checkPlan()"></textarea>
        <span class="error" id="planErr"></span>
    </div>
    <div class="wrap">
        <div class="sel">提醒次数（最多3次）：</div>
        <label>
            <select id="remindSum">
                <option value="0">不设置提醒</option>
                <option value="1">1次</option>
                <option value="2">2次</option>
                <option value="3">3次</option>
            </select>
        </label>
    </div>
    <div class="wrap">
        <div class="sel">提醒时间间隔（单位/min）：</div>
        <label>
            <select id="remindPeriod">
                <option value="0">无设置提醒</option>
                <option value="1">1min</option>
                <option value="5">5min</option>
                <option value="10">10min</option>
            </select>
        </label>
        <span class="error" id="remindPeriodErr"></span>
    </div>
    <div class="wrap">
        <button class="btn" onclick="submit()">提交</button>
    </div>
</div>
<div id="result" style="display: none">
</div>
</body>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script type="text/javascript" src="../../../static/js/dateTime.min.js"></script>
<script type="text/javascript">
    let dtOk = false;
    let planOk = false;

   $(function () {
       $("#datetime").datetime({
           type:"datetime",
           value:[2020,1,1,12,30]
       })

       $("#ensure").click(function(){
          checkDateTime()
       });
       $("#close").click(function(){
          checkDateTime()
       });
   });


   function checkDateTime() {
       let $datetimeErr = $("#datetimeErr");
       let $d = $("#datetime").val();
       let $now = new Date();
       $datetimeErr.text("");
       if ($d === "") {
           $datetimeErr.text("日程时间不能为空！！！");
           return false;
       }else if(Date.parse($d)<Date.parse($now)){
           $datetimeErr.text("日程时间不能早于当前时间！！！");
           return false;
       }
       $.ajax({
           type: 'post',
           url: '/user/schedule/isRepeat?dateTime='+$d,
           headers: {
               'x-token': localStorage.getItem("x-token"),
           },
           success: function (data, textStatus, jqXHR) {
             if (data.data) {
                 $datetimeErr.text("已存在改时间的日程，不可以重复添加！！！");
             }else{
                 dtOk = true;
             }
           },
           error: function (e) {
               console.log(e.status);
               console.log(e.responseText);
           }
       });
       return true;
   }

   function checkPlan() {
       let $planErr = $("#planErr");
       $planErr.text("");
       let $p = $("#plan").val();
       if ($p === "") {
           $planErr.text("日程内容不可以为空！！！");
           planOk = false;
           return false;
       }
       planOk = true;
       return true;
   }

    function submit() {
        if (!dtOk || !planOk) {
            return;
        }
        let $remindPeriodErr = $("#remindPeriodErr")
        $remindPeriodErr.text("");
        let $remindSum = $("#remindSum").val();
        let $remindPeriod = $("#remindPeriod").val();

        if($remindSum>1 && $remindPeriod <= 0){
            $remindPeriodErr.text("请选择提醒时间间隔,再尝试提交一次。");
            return;
        }

        let param = {
            startTime: $("#datetime").val(),
            plan: $("#plan").val(),
            remindSum: $remindSum,
            remindPeriod: $remindPeriod,
        };

        $.ajax({
            type: 'post',
            url: '/user/schedule/add',
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify(param),
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (data, textStatus, jqXHR) {
                $("#form").css("display", "none");
                let $result = $("#result")
                $result.css("display", "block");
                if (data.code>=0) {
                    $result.text("添加成功！！！");
                }else{
                    $result.text(data.msg);
                }
            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });

    }

</script>
</html>
