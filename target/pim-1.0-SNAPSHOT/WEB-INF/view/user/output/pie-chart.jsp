<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/1/30
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>饼状图</title>
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>
<script type="text/javascript">
    let dom = document.getElementById("container");
    let myChart = echarts.init(dom);
    let app = {};
    option = null;

    $(function () {
        $.ajax({
            type: 'post',
            url: '/user/assets/output/pieData',
            contentType: "application/json;charset=UTF-8",
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (result, textStatus, jqXHR) {
                option = {
                    title: {
                        text: '支出饼状图',
                        subtext: '根据支出类型统计支出金额',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        // data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
                        data: result.data.types,
                    },
                    series: [
                        {
                            name: '资金额度',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            // data: [
                            //     {value: 335, name: '直接访问'},
                            //     {value: 310, name: '邮件营销'},
                            //     {value: 234, name: '联盟广告'},
                            //     {value: 135, name: '视频广告'},
                            //     {value: 1548, name: '搜索引擎'}
                            // ],
                            data: result.data.values,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }

            },
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    });

</script>
</body>
</html>
