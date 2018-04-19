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
    <title>回收员管理</title>
    <link rel="stylesheet" type="text/css" href="/ui/themes/ui-pepper-grinder/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ui/demo/demo.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/huishou.js"></script>

</head>
<body>
<div class="demo-info">
    <div class="demo-tip icon-tip">&nbsp;</div>
</div>

<div style="margin:10px 0">
    <table>
        <tr>
            <td><a href="#" class="easyui-linkbutton" onclick="hsinsert()">添加</a></td>
            <td>&nbsp;&nbsp;</td>
            <td><input class="easyui-validatebox" id="hsid" placeholder="请输入ID" type="text" required="true"></td>
            <td><a href="#" class="easyui-linkbutton" onclick="hssearch()">查询</a></td>
        </tr>
    </table>
</div>
<div id="hsdd" class="easyui-dialog" style="padding:5px;width:500px;height:400px;"
     title="My Dialog" iconCls="icon-ok" closed="true"
     buttons="#dlg-buttons">
    <form id="hsff" method="post" style="display:block; width:100px;">
        <div>
            <label>姓名:</label>
            <input class="easyui-validatebox" type="text" name="name" required="true"></input>
        </div>
        <div>
            <label>电话:</label>
            <input class="easyui-validatebox" type="text" name="phone" required="true" ></input>
        </div>
    </form>
</div>
<div id="hslzdd" class="easyui-dialog" style="padding:5px;width:500px;height:400px;"
     title="My Dialog" iconCls="icon-ok" closed="true"
     buttons="#lzbuttons">
    <form id="hslzff" method="post" style="display:block; width:100px;">
        <div>
            <label>请输入离职时间:</label>
            <input class="easyui-datebox" type="text" name="lzdate" required="true" data-options="editable:false"></input>
        </div>
    </form>
</div>
<div id="showhsdd" class="easyui-dialog" style="padding:5px;width:300px;height:200px;"
     title="My Dialog" iconCls="icon-ok" closed="true"
     buttons="#showhsbuttons">
    <table>
        <tr>
            <td>姓名:</td>
            <td><div id="hsname"></div></td>
        </tr>
        <tr>
            <td>电话:</td>
            <td><div id="hsphone"></div></td>
        </tr>
        <tr>
            <td>是否在职:</td>
            <td><div id="hsstatus"></div></td>
        </tr>
        <tr>
            <td>离职时间:</td>
            <td><div id="hslzdate"></div></td>
        </tr>
    </table>
</div>
<div id="hsfinishdd" class="easyui-dialog" style="padding:5px;width:1000px;height:400px;"
     title="订单信息" iconCls="icon-ok" closed="true"
     buttons="#hsfinishbuttons">
    <table id="fsdg" title="已处理订单" class="easyui-datagrid" style="width: 950px;height:auto"
           fitColumns="true" singleSelect="true"
           data-options="rownumbers:true">
        <thead>
        <tr>
            <th field="id" width="100">订单ID</th>
            <th field="name" width="200">姓名</th>
            <th field="phone" width="200">电话</th>
            <th field="address" width="200">地址</th>
            <th field="type" width="100">类型</th>
            <th field="finishdate" width="200">完成时间</th>
        </tr>
        </thead>
    </table>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="hssave()">确认</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="hscancel()">取消</a>
</div>
<div id="lzbuttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="lzsave()">确认</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="lzcancel()">取消</a>
</div>
<div id="showhsbuttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="hsclose()">确认</a>
</div>
<div id="hsfinishbuttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="hsfinishclose()">确认</a>
</div>
<table id="hstt" fitColumns="true" singleSelect="true" pagination="true"
       data-options="rownumbers:true,
            url:'/HsList',
            method:'get',
            pageSize:15,
            pageList:[5,10,15,20,25]"></table>

</body>
</html>
