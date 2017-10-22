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

<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>开始答题</title>


    <link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">

    <script src="${ctx}/js/jquery.min.js"></script>
    <script src="${ctx}/js/jquery.knob.js"></script>
    <script src="${ctx}/js/jquery.throttle.js"></script>
    <script src="${ctx}/js/jquery.classycountdown.js"></script>

    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.classycountdown.css" />

    <style>
        .ClassyCountdownDemo { margin:0 auto 30px auto; max-width:800px; width:calc(100%); padding:30px; display:block }
        #countdown2 { background:#FFF }
    </style>
    <!--[if IE]>
    <!--<script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>-->
    <%--<![endif]-->--%>
</head>
<body>
    <div id="countdown2" class="ClassyCountdownDemo"></div>
    <div style="margin: 50px">
        <%--<div id="allQues">--%>
            <%--<ul id="dataList" class="easyui-datalist" title="Basic DataList" lines="true" style="width:400px;height:250px">--%>
                <%----%>
            <%--</ul>--%>
        <%--</div>--%>

            <c:choose>
                <c:when test="${questionList == null}">
                    <div style="margin-left: 45%; margin-top: 20%">
                        <h1>题库中题目小于100，请先录入足够多的题目</h1>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${questionList}" var="n" >
                        <div id="${n.quesNo}">
                            <input id="quesNo" type="hidden" value="${n.quesNo}"/>
                            <input id="uuid" type="hidden" value="${n.uuid}"/>
                            <input id="questionText" type="hidden" value="${n.questionText}"/>
                            <label  style="color:black; font-weight:bold; font-size: larger">${n.quesNo}. ${n.questionText}</label><span style="margin-bottom: 20px"></span><br>
                            <input id="radioA" type="radio" name="rightAnswer${n.quesNo}" value="A"/><label id="choiceA">A: ${n.choiceA}</label><br>
                            <input id="radioB" type="radio" name="rightAnswer${n.quesNo}" value="B"/><label id="choiceB">B: ${n.choiceB}</label><br>
                            <input id="radioC" type="radio" name="rightAnswer${n.quesNo}" value="C"/><label id="choiceC">C: ${n.choiceC}</label><br>
                            <input id="radioD" type="radio" name="rightAnswer${n.quesNo}" value="D"/><label id="choiceD">D: ${n.choiceD}</label>
                            <label id="result${n.quesNo}"></label>
                            <label id="right${n.quesNo}"></label>
                                <%--<c:out value="${n}"/>--%>
                        </div>
                        <div style="margin: 20px"></div>
                    </c:forEach>
                </c:otherwise>

            </c:choose>


        <div style="margin:10px 0;"></div>
        <div id="btnDiv" style="padding:5px 0;">
            <a href="#" style="position: relative; margin-left: 50%" class="easyui-linkbutton" onclick="submit()" data-options="iconCls:'icon-ok', size: 'large'">提交</a>
            <%--<span style="margin-right: 20px"></span>--%>
            <%--<a href="#" class="easyui-linkbutton" onclick="loadNextQuestion($('#currNo').val())" data-options="iconCls:'icon-redo'">下一题</a>--%>
        </div>

    </div>


    <div id="hiddenDiv">
        <input type="hidden" id="totalTime" value="${totalTime}"/>
        <%--当前题号， 默认为1--%>
        <input type="hidden" id="currNo" value="1">
        <input type="hidden" id="maxNo" value="1">
        <%--<input type="hidden" id="questionList" value="${questionList}">--%>

    </div>
<script type="text/javascript">

    function submit() {
        var allAnswer = [];
        for (var i = 1; i < 101; i ++) {
            var obj = new Object();
            obj.quesNo = $("#" + i + " #quesNo").val();
            obj.uuid = $("#" + i + " #uuid").val();
            obj.selectChoice = $("input:radio[name=rightAnswer"+ i +"]:checked").val();
            allAnswer.push(obj);
        }
        $.ajax({
            url : "/question/submitPaper.action",
            type : "POST",
            contentType : "application/json;charset=utf-8",
            data : JSON.stringify(allAnswer),

            success : function (data) {
                debugger;
                if (data.success == true) {
//                    $.messager.show({
//                        title:'My Title',
//                        msg:'Message will be closed after 5 seconds.',
//                        timeout:5000,
//                        showType:'slide'
//                    });
//                    $.messager.alert('My Title','Here is a info message!','info');
                    $.messager.alert("测试结果", "您的总分是" + data.mark, "info");
                    var list = data.data;

                } else {
                    $.messager.alert("测试结果", "内部错误", "info");
                }
            }
        })

    }

    var allQustion = [];
    var answers = [];

    $.ajax({
        url : "/question/getExamPaper.action", //
        contentType : "application/json;charset=utf-8",
        success : function(result) {
            $.each(result.data, function(i, obj) {
                if (obj.quesNo == 1) {
//                    document.getElementById("paperDiv").innerHTML =
//                        '<input id="quesNo" type="hidden" value="'+ obj.quesNo +'"/>'+
//                        '<input id="uuid" type="hidden" value="'+ obj.uuid +'"/>' +
//                        '<input id="questionText" type="hidden" value="'+ obj.questionText +'"/>' +
//                        '<label>'  + "1. " + obj.questionText +'</label><br>'
//                        + '<input id="radioA" type="radio" name="rightAnswer" value="' + obj.choiceA + '"/>' + '<label id="choiceA">A: '+ obj.choiceA+'</label><br>'
//                        + '<input id="radioB" type="radio" name="rightAnswer" value="' + obj.choiceB + '"/>' + '<label id="choiceB">B: '+ obj.choiceB+'</label><br>'
//                        + '<input id="radioC" type="radio" name="rightAnswer" value="' + obj.choiceC + '"/>' + '<label id="choiceC">C: '+ obj.choiceC+'</label><br>'
//                        + '<input id="radioD" type="radio" name="rightAnswer" value="' + obj.choiceD + '"/>' + '<label id="choiceD">D: '+ obj.choiceD+'</label>';
                }
                allQustion.push(obj);
//                document.getElementById("dataList").innerHTML =
//                    '<li value="' + obj.quesNo + '">第' + obj.quesNo +'题</li>';
            });
        }
    });

    /**
     * 点击下一题，从allQuestion中取得题目
     * @param currNo
     */
    function loadNextQuestion(currNo) {
        var maxNo = $("#maxNo").val();
        if (currNo == 100) {
            $.messager.alert('已经最后一题，答题结束');
            //TODO
            return
        } else {
            if (maxNo == currNo) {
                var obj = new Object();
                obj.quesNo = $("#quesNo").val();
                obj.uuid = $("#uuid").val();
                obj.questionText = $("#questionText").val();
                obj.choiceA = $("#radioA").val();
                obj.choiceB = $("#radioB").val();
                obj.choiceC = $("#radioC").val();
                obj.choiceD = $("#radioD").val();
                obj.rightAnswer = $('input:radio[name="rightAnswer"]:checked').val();
                answers.push(obj);

                $("#paperDiv").empty();
                var currQues = allQustion[currNo];
                currNo ++;
                document.getElementById("paperDiv").innerHTML =
                    '<input id="quesNo" type="hidden" value="'+ currQues.quesNo +'"/>'+
                    '<input id="uuid" type="hidden" value="'+ currQues.uuid +'"/>'+
                    '<input id="questionText" type="hidden" value="'+ currQues.questionText +'"/>' +
                    '<label>' + currNo + ". " + currQues.questionText +'</label><br>'
                    + '<input id="radioA" type="radio" name="rightAnswer" value="' + currQues.choiceA + '"/>' + '<label>A: '+ currQues.choiceA+'</label><br>'
                    + '<input id="radioB" type="radio" name="rightAnswer" value="' + currQues.choiceB + '"/>' + '<label>B: '+ currQues.choiceB+'</label><br>'
                    + '<input id="radioC" type="radio" name="rightAnswer" value="' + currQues.choiceC + '"/>' + '<label>C: '+ currQues.choiceC+'</label><br>'
                    + '<input id="radioD" type="radio" name="rightAnswer" value="' + currQues.choiceD + '"/>' + '<label>D: '+ currQues.choiceD+'</label>';

                $("#currNo").val(currNo);
            } else {

            }

        }
    }

    /**
     * 点击上一题，从answers中取得题目
     * @param currNo
     */
    function loadPreQuestion(currNo) {
        if (currNo == 1) {
            $.messager.alert('已经是第一题');
            //TODO
            return;
        } else {
            $("#paperDiv").empty();
            currNo --;
            var currQues = answers[currNo - 1];
//            document.getElementById("paperDiv").innerHTML =
//                '<input id="quesNo" type="hidden" value="'+ currQues.quesNo +'"/>'+
//                '<input id="uuid" type="hidden" value="'+ currQues.uuid +'"/>'+
//                '<label>' + currNo + ". " + currQues.questionText +'</label><br>'
//                + '<input type="radio" name="rightAnswer" value="' + currQues.choiceA + '"/>' + '<label>A: '+ currQues.choiceA+'</label><br>'
//                + '<input type="radio" name="rightAnswer" value="' + currQues.choiceB + '"/>' + '<label>B: '+ currQues.choiceB+'</label><br>'
//                + '<input type="radio" name="rightAnswer" value="' + currQues.choiceC + '"/>' + '<label>C: '+ currQues.choiceC+'</label><br>'
//                + '<input type="radio" name="rightAnswer" value="' + currQues.choiceD + '"/>' + '<label>D: '+ currQues.choiceD+'</label>';
            $("input[type=radio]").attr("checked",currQues.rightAnswer);
            $("#currNo").val(currNo);
        }
    }

    $(document).ready(setTimeout(function() {

        var end = $("#totalTime").val();
        $('#countdown2').ClassyCountdown({
            end: $.now() + end * 1,
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
                $.messager.alert('提示','答题结束!','info', function (r) {
                    if (r) {

                    }
                });
            }
        })
    }), 2000);

</script>


</body>
</html>
