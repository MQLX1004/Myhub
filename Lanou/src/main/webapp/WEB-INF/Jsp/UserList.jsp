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
    <title>用户订单</title>
    <link rel="stylesheet" type="text/css" href="/ui/themes/ui-pepper-grinder/easyui.css">
    <link rel="stylesheet" type="text/css" href="/ui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/ui/demo/demo.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/lanou.js"></script>

</head>
<body>
<div class="demo-info">
    <div class="demo-tip icon-tip">&nbsp;</div>
</div>

<div style="margin:10px 0">
    <table>
        <tr>
    <td><a href="#" class="easyui-linkbutton" onclick="insert()">添加</a></td>
    <td>&nbsp;&nbsp;</td>
    <td><input class="easyui-validatebox" id="textid" placeholder="请输入ID" type="text" required="true"></td>
    <td><a href="#" class="easyui-linkbutton" onclick="searchid()">查询</a></td>
        </tr>
    </table>
</div>
<div id="dd" class="easyui-dialog" style="padding:5px;width:500px;height:400px;"
     title="My Dialog" iconCls="icon-ok" closed="true"
      buttons="#dlg-buttons">
    <form id="ff" method="post" style="display:block; width:100px;">
        <div>
            <label>姓名:</label>
            <input class="easyui-validatebox" type="text" name="name" required="true"></input>
        </div>
        <div>
            <label>电话:</label>
            <input class="easyui-validatebox" type="text" name="phone" required="true" ></input>
        </div>
        <div>
            <label>地址:</label>
            <input class="easyui-validatebox" type="text" name="address" required="true"/>
        </div>
        <div>
            <label>类型:</label>
            <select class="easyui-combobox" name="type" readonly="true" required="true">
                <option value="metal">金属</option>
                <option value="glass">玻璃制品</option>
                <option value="plastic">塑料制品</option>
                <option value="domastic">废旧家电</option>
            </select>
        </div>
    </form>
</div>
<div id="showdd" class="easyui-dialog" style="padding:5px;width:300px;height:200px;"
     title="My Dialog" iconCls="icon-ok" closed="true"
     buttons="#showbuttons">
    <table>
        <tr>
            <td>姓名:</td>
            <td><div id="showname"></div></td>
        </tr>
        <tr>
            <td>电话:</td>
            <td><div id="showphone"></div></td>
        </tr>
        <tr>
            <td>地址:</td>
            <td><div id="showaddress"></div></td>
        </tr>
        <tr>
            <td>类型:</td>
            <td><div id="showtype"></div></td>
        </tr>
    </table>
</div>
<div id="finishdd" class="easyui-dialog" style="padding:5px;width:300px;height:200px;"
     title="My Dialog" iconCls="icon-ok" closed="true"
     buttons="#finishbuttons">
    <form id="finishff" method="post" style="display:block; width:100px;">
        <div>
            <label>回收员ID:</label>
            <input class="easyui-combobox" name="huishouid" required="true"
            data-options="
            editable:false,
            valueField: 'id',
            textField: 'id',
            url: '${pageContext.request.contextPath}/HsidList'"/>
        </div>
        <div>
            <label>回收时间:</label>
            <input class="easyui-datebox" type="text" name="finishdate" required="true" data-options="editable:false"></input>
        </div>
    </form>
</div>
<div id="finishbuttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="finishok()">确认</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="finishcancel()">取消</a>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">确认</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()">取消</a>
</div>
<div id="showbuttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="closeshow()">确认</a>
</div>
<table id="tt" fitColumns="true" singleSelect="true" pagination="true"
       data-options="rownumbers:true,
            url:'/UserList',
            method:'get',
            pageSize:15,
            pageList:[5,10,15,20,25]"></table>

</body>
</html>
