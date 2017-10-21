<%--&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: zhengxin--%>
  <%--Date: 2017/10/15--%>
  <%--Time: 21:20--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@include file="common.jsp"%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Title</title>--%>
<%--</head>--%>
<%--<script type="text/javascript">--%>
<%--//    $(document).ready(function(){--%>
        <%--var tFlag = 0;--%>
        <%--var tPass = 0;--%>
        <%--function timer(id) {--%>
            <%--if (tFlag != 0) {--%>
                <%--var tNew = new Date().getTime();--%>
                <%--tPass = tPass + (tNew - tFlag);--%>
                <%--tFlag = tNew;--%>

            <%--} else {--%>
                <%--tFlag = new Date().getTime();--%>
            <%--}--%>
            <%--setTimeout("timer('" + id + "')", 100);--%>
            <%--var ml = tPass % 1000;--%>
            <%--var sc = Math.floor((tPass / 1000) % 60);--%>
            <%--var mi = Math.floor((tPass / 1000 / 60) % 60);--%>
            <%--var hr = Math.floor((tPass / 1000 / 60 / 60) % 24);--%>
            <%--var dy = Math.floor(tPass / 1000 / 60 / 60 / 24);--%>
            <%--var info = dy + "天" + hr + "时" + mi + "分" + sc + "秒" + ml + "毫秒";--%>
            <%--document.getElementById(id).innerHTML = info;--%>
        <%--}--%>
        <%--$(document).ready(function(){--%>

        <%--})--%>
<%--//    });--%>
<%--</script>--%>
<%--<body>--%>
<%--<button type="button" onclick="timer('ptime')">--%>
    <%--开始计时--%>
<%--</button>--%>
<%--<p id="ptime"></p>--%>


<%--</body>--%>
<%--</html>--%>

<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>canvas圆形倒计时插件</title>


    <link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">

    <script src="${ctx}/js/jquery.min.js"></script>
    <script src="${ctx}/js/jquery.knob.js"></script>
    <script src="${ctx}/js/jquery.throttle.js"></script>
    <script src="${ctx}/js/jquery.classycountdown.js"></script>

    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.classycountdown.css" />

    <style>
        .ClassyCountdownDemo { margin:0 auto 30px auto; max-width:800px; width:calc(100%); padding:30px; display:block }
        #countdown2 { background:#FFF }
        #countdown3 { background:rgb(52, 73, 94) }
        #countdown4 { background:#222 }
        #countdown5 { background:#222 }
        #countdown6 { background:#222 }
        #countdown7 { background:#222 }
        #countdown8 { background:#222 }
        #countdown9 { background:#FFF }
        #countdown10 { background:#3498db }
    </style>
    <!--[if IE]>
    <script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>

<div id="countdown2" class="ClassyCountdownDemo"></div>

<div id="questionDiv">

</div>
<script type="text/javascript">
    var allQustion = [];
    var answers = [];
    $(document).ready(function() {
        $.ajax({
            url : "/question/getExamPaper.action", //
            contentType : "application/json;charset=utf-8",
            success : function(result) {
//                totalTime = result.data;
            }
        });

        var totalTime = 0;
        $.ajax({
            url : "/question/getTimmer.action", //
            type : "GET",
            contentType : "application/json;charset=utf-8",
            success : function(result) {
                totalTime = result.data;
            }

        });

        $('#countdown2').ClassyCountdown({
            end: $.now() + 10,
            style: {
                element: "",
                textResponsive: .5,
//                days: {
//                    gauge: {
//                        thickness: .01,
//                        bgColor: "rgba(0,0,0,0.05)",
//                        fgColor: "#1abc9c"
//                    },
//                    textCSS: 'font-family:\'Open Sans\'; font-size:25px; font-weight:300; color:#34495e;'
//                },
                hours: {
                    gauge: {
                        thickness: .01,
                        bgColor: "rgba(0,0,0,0.05)",
                        fgColor: "#2980b9"
                    },
                    textCSS: 'font-family:\'Open Sans\'; font-size:25px; font-weight:300; color:#34495e;'
                },
                minutes: {
                    gauge: {
                        thickness: .01,
                        bgColor: "rgba(0,0,0,0.05)",
                        fgColor: "#8e44ad"
                    },
                    textCSS: 'font-family:\'Open Sans\'; font-size:25px; font-weight:300; color:#34495e;'
                },
                seconds: {
                    gauge: {
                        thickness: .01,
                        bgColor: "rgba(0,0,0,0.05)",
                        fgColor: "#f39c12"
                    },
                    textCSS: 'font-family:\'Open Sans\'; font-size:25px; font-weight:300; color:#34495e;'
                }

            },
            onEndCallback: function() {
                console.log("Time out!");
            }
        })
    });

</script>


</body>
</html>
