<%--
  Created by IntelliJ IDEA.
  User: zhengxin
  Date: 2017/10/21
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@include file="common.jsp"%>
<html>
<head>
    <title>试题管理</title>
</head>
<body>
<div style="margin:20px 0;"></div>
<table id="allTable" class="easyui-datagrid" title="所有试题" style="width:100%;height:100%"
       data-options="rownumbers:true, singleSelect:false,  url:'/question/getAllQuestions.action', method:'get',toolbar:toolbar">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <%--<th data-options="field:'id',width:60">题目序号</th>--%>
        <th data-options="field:'uuid',width:60">题目编号</th>
        <th data-options="field:'questionText',width:800">题目</th>
        <th data-options="field:'choiceA',width:200,align:'right'">选项A</th>
        <th data-options="field:'choiceB',width:200,align:'right'">选项B</th>
        <th data-options="field:'choiceC',width:200,align:'right'">选项C</th>
        <th data-options="field:'choiceD',width:200,align:'right'">选项D</th>
        <th data-options="field:'rightAnswer',width:80,align:'right'">正确选项</th>

    </tr>
    </thead>
</table>
<script type="text/javascript">
    var toolbar = [{
        text:'添加',
        iconCls:'icon-edit_add',
        handler:function(){
            window.location.href="/question/import.action"
        }
    },'-',{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
//            $('#allTable').datagrid({selectOnCheck:$(this).is(':checked')});
            var ids = [];
            var rows = $('#allTable').datagrid('getSelections');
            $.each(rows, function(i, n) {
                ids.push(n.uuid);
            });
            $.ajax({
                url : "/question/delete.action",
                type : "POST",
                contentType : "application/json;charset=utf-8",
                data : JSON.stringify(ids),
                dataType : "json",
                success : function(data) {
                    if (data.success == true) {
                        $.messager.alert('提示', '删除成功', 'info', function () {
                            window.location.reload();
                        });
                    } else {
                        $.messager.alert('提示', '删除失败', 'warning', function () {
                            window.location.reload();
                        });
                    }
                }

            });
        }
    }];
</script>
</body>
</html>
