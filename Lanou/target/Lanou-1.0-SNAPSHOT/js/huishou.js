$(function(){
    $('#hstt').datagrid({
        title:'回收员列表',
        iconCls:'icon-edit',
        width:650,
        height:400,
        singleSelect:true,
        idField:'itemid',
        //url:'/HsList',
        columns:[[
            {field:'id',title:'ID',width:100},
            {field:'name',title:'姓名',width:100,editor:'text'},
            {field:'phone',title:'电话',width:120,align:'right',editor:'text'},
            {field:'status',title:'是否在职',width:70,align:'center',
                editor:{
                    type:'checkbox',
                    options:{
                        on: '是',
                        off:'否'
                    }
                }
            },
            {field:'lzdate',title:'离职时间',width:120},
            {field:'action',title:'Action',width:140,align:'center',
                formatter:function(value,row,index){
                    if (row.editing){
                        var s = '<a href="#" onclick="saverow(this)">保存</a> ';
                        var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
                        return s+c;
                    } else {
                        var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
                        var d = '<a href="#" onclick="shidrow(this)">信息</a>';
                        return e+d;
                    }
                }
            }
        ]],
        onBeforeEdit:function(index,row){
            row.editing = true;
            updateActions(index);
        },
        onAfterEdit:function(index,row){
            row.editing = false;
            updateActions(index);
            $('#hstt').datagrid('selectRow',index);
            if(row.status=="否"){
                $('#hslzdd').dialog({closed:false});
                $("#hslzff").form("clear");
            }
            else {
                $('#hstt').datagrid('updateRow',{
                    index: index,
                    row:{"lzdate":null}
                });
                $.ajax({
                    type:"POST",
                    url:"HsUpdate?status=是",
                    data:{
                        "id":row.id,
                        "name":row.name,
                        "phone":row.phone},
                    success: function (data) {
                        $("#hstt").datagrid("reload");
                    }
                })
            }

        },
        onCancelEdit:function(index,row){
            row.editing = false;
            updateActions(index);
        }
    });
});
function updateActions(index){
    $('#hstt').datagrid('updateRow',{
        index: index,
        row:{}
    });
}
function getRowIndex(target){
    var tr = $(target).closest('tr.datagrid-row');
    return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target){
    $('#hstt').datagrid('beginEdit', getRowIndex(target));
}
function shidrow(target) {
    $('#hstt').datagrid('selectRow',getRowIndex(target));
    var row = $('#hstt').datagrid('getSelected',getRowIndex(target));
    $.ajax({
        type: "POST",
        url: "listbyhsid",
        data:{"huishouid":row.id},
        success: function (data) {
            $("#fsdg").datagrid("reload");
            $("#fsdg").datagrid("loadData",data);
        }
    })
    $('#hsfinishdd').dialog({closed:false});

}
function deleterow(target){
    $('#hstt').datagrid('selectRow',getRowIndex(target));
    var row = $('#hstt').datagrid('getSelected',getRowIndex(target));
    if(row){
        $.messager.confirm('Confirm','Are you sure?',function(r){
            if (r){
                $('#hstt').datagrid('deleteRow', getRowIndex(target));
                $.ajax({
                    type: "POST",
                    url: "HsDelete",
                    data:{"id":row.id},
                    success: function (data) {
                        alert(data);
                        $("#hstt").datagrid("reload")
                    }
                })
            }
        });
    }
}
function saverow(target){
    $('#hstt').datagrid('endEdit', getRowIndex(target));
}
function cancelrow(target){
    $('#hstt').datagrid('cancelEdit', getRowIndex(target));
}
function hsinsert(){
    $("#hsff").form("clear");
    $('#hsdd').dialog({closed:false});
    $('#hstt').datagrid("reload");
}
function hssave() {
    $.messager.confirm("Confirm","确定提交？",function (r) {
        if(r){
            $('#hsff').form('submit',{
                url:"/hssubmit?status=是",
                success: function (data) {
                    alert(data);
                    $("#hsdd").dialog("close");
                    $("#hstt").datagrid("reload");
                }
            })
        }
    })
}
function hscancel() {
    $("#hsdd").dialog("close");
}
function hssearch() {
    var text = $('#hsid').val();
    var rows = $('#hstt').datagrid("getRows");
    var flag=0;
    $.each(rows,function(i,row){
        if(row.id==text){
            flag=1;
            $("#hsname").text(row.name);
            $("#hsphone").text(row.phone);
            $("#hsstatus").text(row.status);
            $("#hslzdate").text(row.lzdate);
            $('#showhsdd').dialog({closed:false});
            $('#hstt').datagrid('selectRow',i);
        }

    });
    if(flag==0)
    {
        alert("对不起，无此回收员！！");
    }
}
function hsclose() {
    $('#showhsdd').dialog({closed:true});
}
function lzsave() {
    var row = $('#hstt').datagrid('getSelected');
    $('#hslzff').form('submit',{
        url:"/HsUpdate?status=否&id="+row.id+"&name="+row.name+"&phone="+row.phone,
        success: function (data) {
            $("#hslzdd").dialog("close");
            $("#hstt").datagrid("reload");
        }
    })
}
function lzcancel() {
    $("#hslzdd").dialog("close");
}
function hsfinishclose() {
    $("#hsfinishdd").dialog("close");
}