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
<table class="easyui-datagrid" title="所有试题" style="width:1200px;height:700px"
       data-options="rownumbers:true, singleSelect:true,  url:'datagrid_data1.json', method:'get',toolbar:toolbar">
    <thead>
    <tr>
        <th data-options="field:'itemid',width:50">题目编号</th>
        <th data-options="field:'productid',width:100">题目</th>
        <th data-options="field:'listprice',width:80,align:'right'">选项A</th>
        <th data-options="field:'listprice',width:80,align:'right'">选项B</th>
        <th data-options="field:'listprice',width:80,align:'right'">选项C</th>
        <th data-options="field:'listprice',width:80,align:'right'">选项D</th>
        <th data-options="field:'unitcost',width:80,align:'right'">正确选项</th>
        <th data-options="field:'attr1',width:240">分值</th>
    </tr>
    </thead>
</table>
<script type="text/javascript">
    var toolbar = [{
        text:'添加',
        iconCls:'icon-add',
        handler:function(){
            window.location.href="/question/import.action"
        }
    },'-',{
        text:'删除',
        iconCls:'icon-del',
        handler:function(){
            alert('del')
        }
    }];
</script>
</body>
</html>
