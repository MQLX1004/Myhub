<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2018/3/16
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="jquery,ui,easy,easyui,web">
    <meta name="description" content="easyui help you build your web page easily!">
    <title>回收管理系统</title>
    <link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/themes/icon.css">
    <style type="text/css">
        #ff label{
            display:block;
            width:100px;
        }
    </style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/lanou.js"></script>
</head>
<body>
<div region="center" style="padding: 5px;border-left: 0px;border-right: 0px;border-top: 0px;"><h3 align="center">回收管理系统</h3></div>
    <form id="loginff" method="post" action="/login">
        <div class="fitem" style="width: 30%;margin: 20px auto;">
            <label>用户名:</label>
            <input style="width: 100%;height: 32px" class="easyui-validatebox" type="text" name="name" required="true"></input>
        </div>
        <div class="fitem" style="width: 30%;margin: 20px auto;">
            <label>密码:</label>
            <input style="width: 100%;height: 32px" class="easyui-validatebox" type="password" name="password" required="true"></input>
        </div>
        <div style="margin: 20px"></div>
        <div style="width:15%;margin: 20px auto;">
            <input iconCls="icon-ok" style="width: 100%;height: 32px;" type="submit" value="登录">
        </div>
    </form>
</div>
</body>
</html>
