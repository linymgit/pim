<%--
  Created by IntelliJ IDEA.
  User: lym
  Date: 2020/1/30
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支出条形图</title>
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
    <%--let dataAxis=${dataAxis};--%>
    <%--let data=${data};--%>
    <%--let yMax = ${yMax};    --%>
    let dataAxis=[];
    let data=[];
    let yMax = 0;

    let dataShadow = [];

    $(function () {
        $.ajax({
            type: 'post',
            url: '/user/assets/output/barData',
            contentType: "application/json;charset=UTF-8",
            headers: {
                'x-token': localStorage.getItem("x-token"),
            },
            success: function (result, textStatus, jqXHR) {
                dataAxis = result.data.dataAxis;
                data = result.data.data;
                yMax = result.data.yMax;

                for (let i = 0; i < data.length; i++) {
                    dataShadow.push(yMax);
                }

                option = {
                    title: {
                        text: '个人支出条形图',
                        subtext: 'x轴按时间降序排列y轴表示支出金额'
                    },
                    xAxis: {
                        data: dataAxis,
                        axisLabel: {
                            inside: true,
                            textStyle: {
                                color: '#000'
                            }
                        },
                        axisTick: {
                            show: false
                        },
                        axisLine: {
                            show: false
                        },
                        z: 10
                    },
                    yAxis: {
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                color: '#999'
                            }
                        }
                    },
                    dataZoom: [
                        {
                            type: 'inside'
                        }
                    ],
                    series: [
                        { // For shadow
                            type: 'bar',
                            itemStyle: {
                                color: 'rgba(0,0,0,0.05)'
                            },
                            barGap: '-100%',
                            barCategoryGap: '40%',
                            data: dataShadow,
                            animation: false
                        },
                        {
                            type: 'bar',
                            itemStyle: {
                                color: new echarts.graphic.LinearGradient(
                                    0, 0, 0, 1,
                                    [
                                        {offset: 0, color: '#83bff6'},
                                        {offset: 0.5, color: '#188df0'},
                                        {offset: 1, color: '#188df0'}
                                    ]
                                )
                            },
                            emphasis: {
                                itemStyle: {
                                    color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            {offset: 0, color: '#2378f7'},
                                            {offset: 0.7, color: '#2378f7'},
                                            {offset: 1, color: '#83bff6'}
                                        ]
                                    )
                                }
                            },
                            data: data
                        }
                    ]
                };

                // Enable data zoom when user click bar.
                var zoomSize = 6;
                myChart.on('click', function (params) {
                    console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
                    myChart.dispatchAction({
                        type: 'dataZoom',
                        startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
                        endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
                    });
                });
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
