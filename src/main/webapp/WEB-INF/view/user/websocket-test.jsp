<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/1/28
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.name}您好！欢迎进入群聊大厅！</title>
</head>
<body>
<input id="message" type="text">
<button id="btn">发送消息</button>
<div id="show"></div>
</body>
<script type="text/javascript">
    let btn = document.getElementById("btn");
    let message = document.getElementById("message");
    let show = document.getElementById("show");
    let ws = new WebSocket("ws://localhost:8080/ws?x-token="+localStorage.getItem("x-token"));
    ws.onopen = function(evt){
      alert(evt);
    };
    ws.onmessage = function (evt) {
        let node = document.createElement("div");
        node.innerHTML = "<h5>" + evt.data + "</h5>";
        show.appendChild(node);
    };
    btn.addEventListener("click", function () {
        let data = message.value;
        console.log(data);
        if (data) {
            ws.send(encodeURI(data));
        } else {
            alert("请输入消息后发送");
        }
        message.value = "";
    });
    // 关闭页面时候关闭 ws
    window.addEventListener("beforeunload", function(event) {
        alert("close")
        ws.close();
    });
</script>
</html>
