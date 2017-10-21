<%--
  Created by IntelliJ IDEA.
  User: zhengxin
  Date: 2017/10/21
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@include file="common.jsp"%>
<html>
<head>
    <title>题目录入</title>
</head>
<script type="text/javascript">
    $.extend($.fn.validatebox.defaults.rules, {
        equals: {
            validator: function(value,param){
                return value == $(param[0]).val();
            },
            message: 'Field do not matchddd.'
        }
    });

</script>
<body>
    <div style="position: relative;margin-top:100px; margin-left: 100px">
        <h2>请输入题目信息</h2>
        <div style="margin:20px 0;"></div>
        <div class="easyui-panel" title="题目信息" style="width:100%;max-width:1000px;padding:30px 60px;">
            <form id="importForm" class="easyui-form" method="post" data-options="novalidate:true">

                <div style="margin-bottom:20px">
                    <input id="questionText" class="easyui-textbox" name="questionText" style="width:100%;height:60px"
                           data-options="label:'题目:',multiline:true,required:true,prompt:'请输入题目.'">
                </div>
                <h3>请输入选项</h3>
                <div style="margin:10px 0;"></div>
                <div style="margin-bottom:20px">
                    <input id="choiceA" class="easyui-textbox" name="choiceA" style="width:100%"
                           data-options="label:'选项A:',required:true,prompt:'请输入选项A.'">
                </div>
                <div style="margin-bottom:20px">
                    <input id="choiceB" class="easyui-textbox" name="choiceB" style="width:100%"
                           data-options="label:'选项B:',required:true,prompt:'请输入选项B.'">
                </div>
                <div style="margin-bottom:20px">
                    <input id="choiceC" class="easyui-textbox" name="choiceC" style="width:100%"
                           data-options="label:'选项C:',required:true,prompt:'请输入选项C.'">
                </div>
                <div style="margin-bottom:20px">
                    <input id="choiceD" class="easyui-textbox" name="choiceD" style="width:100%" data-options="label:'选项D:',required:true,prompt:'请输入选项D.'">
                </div>
                <h3>请输入正确答案</h3>
                <div style="margin:10px 0;"></div>
                <div>
                    <label>A:</label><input name="rightAnswer" type="radio" id="answerA" value="A"/><span style="margin-right: 20px"></span>
                    <label>B:</label><input name="rightAnswer" type="radio" id="answerB" value="B"/><span style="margin-right: 20px"></span>
                    <label>C:</label><input name="rightAnswer" type="radio" id="answerC" value="C"/><span style="margin-right: 20px"></span>
                    <label>D:</label><input name="rightAnswer" type="radio" id="answerD" value="D"/>
                </div>
                <div style="text-align:center;padding:5px 0; margin-top: 20px">
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">完成</a><span style="margin-right: 20px"></span>
                    <%--<a href="javascript:void(0)" class="easyui-linkbutton" onclick="continueForm()" style="width:80px">保存并继续</a>--%>
                </div>
            </form>
        </div>

    </div>
    <script>
        function submitForm(){
            if (!checkForm()) {
                return;
            }
            var obj = new Object();
            obj.questionText = $("#questionText").val();
            obj.choiceA = $("#choiceA").val();
            obj.choiceB = $("#choiceB").val();
            obj.choiceC = $("#choiceC").val();
            obj.choiceD = $("#choiceD").val();
            obj.rightAnswer = $('input:radio[name="rightAnswer"]:checked').val();
            $.ajax({
                url : '/question/add.action',
                type : 'POST',
                contentType : "application/json;charset=utf-8",
                data : JSON.stringify(obj),
                dataType : "json",
                success : function (data) {
                    if (data.success == true) {
                        $.messager.confirm('提示', '添加成功,是否继续？', function (data) {
                            if(data == true) {
                                window.location.reload();
                            } else {
                                window.location.href="/question/all.action"
                            }

                        });
                    } else {
                        $.messager.alert('提示', '添加失败', 'warning',function () {
                            window.location.reload();
                        });
                    }

                }

            })
        }

        function checkForm() {
            if ($("#questionText").val() == null || $("#questionText").val() == "") {
                $.messager.alert('提示', '题目描述不能为空', 'warning');
                return false;
            }
            if ($("#choiceA").val() == null || $("#choiceA").val() == "") {
                $.messager.alert('提示', '选项A不能为空', 'warning');
                return false;
            }
            if ($("#choiceB").val() == null || $("#choiceB").val() == "") {
                $.messager.alert('提示', '选项B不能为空', 'warning');
                return false;
            }
            if ($("#choiceC").val() == null || $("#choiceC").val() == "") {
                $.messager.alert('提示', '选项C不能为空', 'warning');
                return false;
            }
            if ($("#choiceD").val() == null || $("#choiceD").val() == "") {
                $.messager.alert('提示', '选项B不能为空', 'warning');
                return false;
            }
            if ($('input:radio[name="rightAnswer"]:checked').val() == null
                || $('input:radio[name="rightAnswer"]:checked').val() == "") {
                $.messager.alert('提示', '', 'warning');
                return false;
            }
        }
    </script>
</body>
</html>
