<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2018/3/11
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>已完成订单</title>
    <link rel="stylesheet" type="text/css" href="/ui/themes/ui-pepper-grinder/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ui/demo/demo.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/finish.js"></script>

</head>
<body>
<div class="demo-info">
    <div class="demo-tip icon-tip">&nbsp;</div>
</div>

<div style="margin:10px 0">
    <table>
        <tr>
            <td><input class="easyui-validatebox" id="finishid" placeholder="请输入ID" type="text" required="true"></td>
            <td><a href="#" class="easyui-linkbutton" onclick="finishsearch()">查询</a></td>
        </tr>
    </table>
</div>
<div id="showfsdd" class="easyui-dialog" style="padding:5px;width:300px;height:400px;"
     title="订单信息" iconCls="icon-ok" closed="true"
     buttons="#showfsbuttons">
    <table>
        <tr>
            <td>订单ID:</td>
            <td><div id="fsid"></div></td>
        </tr>
        <tr>
            <td>姓名:</td>
            <td><div id="fsname"></div></td>
        </tr>
        <tr>
            <td>电话:</td>
            <td><div id="fsphone"></div></td>
        </tr>
        <tr>
            <td>地址:</td>
            <td><div id="fsaddress"></div></td>
        </tr>
        <tr>
            <td>类型:</td>
            <td><div id="fstype"></div></td>
        </tr>
        <tr>
            <td>回收时间:</td>
            <td><div id="fsdate"></div></td>
        </tr>
        <tr>
            <td>回收员ID:</td>
            <td><div id="fshuishouid"></div></td>
        </tr>
        <tr>
            <td>回收员姓名:</td>
            <td><div id="fshuishouname"></div></td>
        </tr>
        <tr>
            <td>回收员电话:</td>
            <td><div id="fshuishouphone"></div></td>
        </tr>
    </table>
</div>
<div id="showfsbuttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="fsclose()">确认</a>
</div>
<table id="fstt" fitColumns="true" singleSelect="true" pagination="true"
       data-options="rownumbers:true,
            url:'/finishlist',
            method:'get',
            pageSize:15,
            pageList:[5,10,15,20,25]"></table>

</body>
</html>
