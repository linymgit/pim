<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
</head>
<body>
<%--<p align="center">--%>
<%--    <button id="open">开启摄像头</button>--%>
<%--    <button id="close">关闭摄像头</button>--%>
<%--    <button id="CatchCode">拍照</button>--%>
<%--</p>--%>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-4 col-md-offset-4">
            <button style="width: 100%;margin-top: 2rem;" type="button" class="btn btn-default" id="open">
                打开摄像头
            </button>
            <button style="width: 100%;margin-top: 0.5rem" type="button" class="btn btn-default" id="close">
                关闭摄像头
            </button>
            <button style="width: 100%;margin-top: 0.5rem" type="button" class="btn btn-default" id="faceLogin">
                点击登录
            </button>
            <div style="width:100%;height:50vh;border-radius: 0.2rem;overflow-x: hidden" id="video-wrap">
                <video id="video" autoplay></video>
                <canvas hidden="hidden" id="canvas" width="800" height="600"></canvas>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var video;//视频流对象
    var context;//绘制对象
    var canvas;//画布对象
    $(function() {
        let flag = false;
        //开启摄像头
        $("#open").click(function() {
            //判断摄像头是否打开
            if (!flag) {
                //调用摄像头初始化
                open();
                flag = true;
            }
        });
        //关闭摄像头
        $("#close").click(function() {
            //判断摄像头是否打开
            if (flag) {
                video.srcObject.getTracks()[0].stop();
                flag = false;
            }
        });
        //人脸登录
        $("#faceLogin").click(function() {
            if (flag) {
                context.drawImage(video, 0, 0, 330, 250);
                CatchCode();
            } else
                alert("请先开启摄像头!");
        });
    });
    //将当前图像传输到后台
    function CatchCode() {
        //获取图像
        let img = getBase64();
        alert(img)
        //Ajax将Base64字符串传输到后台处理
        // $.ajax({
        //     type : "POST",
        //     async : false,
        //     url : "/user/face/register",
        //     data : {
        //         img : img
        //     },
        //     dataType : "JSON",
        //     success : function(data) {
        //         //返回的结果
        //         alert(JSON.stringify(data))
        //     }
        // });
    };
    //开启摄像头
    function open() {
        //获取摄像头对象
        canvas = document.getElementById("canvas");
        context = canvas.getContext("2d");
        //获取视频流
        video = document.getElementById("video");
        let videoObj = {
            "video" : true
        }, errBack = function(error) {
            console.log("Video capture error: ", error.code);
        };

        // let $video = $('#video-wrap');
        // let offset = $video.offset();
        context.drawImage(video, 0,0);
        // context.drawImage(video, 0, 0, 330, 250);
        //初始化摄像头参数
        if (navigator.getUserMedia || navigator.webkitGetUserMedia
            || navigator.mozGetUserMedia) {
            navigator.getUserMedia = navigator.getUserMedia
                || navigator.webkitGetUserMedia
                || navigator.mozGetUserMedia;
            navigator.getUserMedia(videoObj, function(stream) {
                video.srcObject = stream;
                video.play();
            }, errBack);
        }
    }
    //将摄像头拍取的图片转换为Base64格式字符串
    function getBase64() {
        //获取当前图像并转换为Base64的字符串
        let imgSrc = canvas.toDataURL("image/png");
        //截取字符串
        return imgSrc.substring(22);
    };
</script>
</html>