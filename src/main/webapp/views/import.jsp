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
                    <input class="easyui-textbox" name="message" style="width:100%;height:60px"
                           data-options="label:'题目:',multiline:true,required:true,prompt:'请输入题目.'">
                </div>
                <h3>请输入选项</h3>
                <div style="margin:10px 0;"></div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="subject" style="width:100%" data-options="label:'选项A:',required:true,prompt:'请输入选项A.'">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="subject" style="width:100%" data-options="label:'选项B:',required:true,prompt:'请输入选项B.'">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="subject" style="width:100%" data-options="label:'选项C:',required:true,prompt:'请输入选项C.'">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" name="subject" style="width:100%" data-options="label:'选项D:',required:true,prompt:'请输入选项D.'">
                </div>
                <h3>请输入正确答案</h3>
                <div style="margin:10px 0;"></div>
                <div>
                    <label>A:</label><input name="answer" type="radio" id="answerA"/><span style="margin-right: 20px"></span>
                    <label>B:</label><input name="answer" type="radio" id="answerB"/><span style="margin-right: 20px"></span>
                    <label>C:</label><input name="answer" type="radio" id="answerC"/><span style="margin-right: 20px"></span>
                    <label>D:</label><input name="answer" type="radio" id="answerD"/>
                </div>
                <div style="text-align:center;padding:5px 0; margin-top: 20px">
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">完成</a><span style="margin-right: 20px"></span>
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="continueForm()" style="width:80px">保存并继续</a>
                </div>
            </form>
        </div>

    </div>
    <script>
        function submitForm(){
            $('#importForm').form('submit',{
                onSubmit:function(){
                    return $(this).form('enableValidation').form('validate');
                }
            });
        }
        function continueForm(){
//            $('#importForm').form('clear');
        }
    </script>
</body>
</html>
