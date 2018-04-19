<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="jquery,ui,easy,easyui,web">
    <meta name="description" content="easyui help you build your web page easily!">
    <title>回收管理系统</title>
    <link rel="stylesheet" type="text/css" href="ui/themes/ui-pepper-grinder/easyui.css">
    <link rel="stylesheet" type="text/css" href="ui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="ui/demo/demo.css">
    <script type="text/javascript" src="ui/jquery.min.js"></script>
    <script type="text/javascript" src="ui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/lanou.js"></script>
    <script type="text/javascript" src="js/echarts.min.js"></script>
    <script type="text/javascript">
        function addTab(title, url){
            if ($('#uu').tabs('exists', title)){
                $('#uu').tabs('select', title);
            } else {
                var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
                $('#uu').tabs('add',{
                    title:title,
                    content:content,
                    closable:true
                });
            }
        }
    </script>
</head>
<body>
<div>
    <table align="center">
        <tr>
            <td width="70%" align="center">管理员已登录</td>
            <td width="30%"><a class="easyui-linkbutton" href="/tologin">退出登录</a></td>
        </tr>
    </table>
</div>
<div class="easyui-layout" style="width:100%;height:100%;">
    <div region="west" split="true" title="功能区" style="width:150px;">
        <div class="easyui-accordion" style="width:auto;height: 150px;border: 0px;">
            <div title="订单管理" iconCls="icon-cog" style="overflow: auto;padding: 10px;">
        <div class="menu-last">
            <a href="#"class="easyui-linkbutton" onclick="addTab('未处理订单','/toUserList')">未处理订单</a><br/>
        </div>
        <div>&nbsp;</div>
        <div class="menu-last">
                <a href="#" class="easyui-linkbutton" onclick="addTab('已完成订单','/tofinishlist')">已完成订单</a><br/>
        </div>
            </div>
            <div title="回收员管理" iconCls="icon-cog" style="overflow: auto;padding: 10px;">
        <div class="menu-last">
            <a href="#" class="easyui-linkbutton" onclick="addTab('回收员管理','/tohsList')">回收员管理</a><br/>
        </div>
            </div>
    </div>
    </div>
    <div id="content" region="center" title="工作区" style="padding:5px;">
        <div id="uu" class="easyui-tabs" style="width:100%;height:100%;">
            <div title="Home">
                <table>
                    <tr>
                        <td>
                <div id="main" style="width: 600px;height:400px;display:block;float:left;">

                </div>
                        </td>
                        <td>
              <div id="pies" style="width: 500px;height:400px;display:block;float:right;">

                </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>