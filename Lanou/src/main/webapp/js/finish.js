var types = [
    {typename:'metal',name:'金属'},
    {typename:'plastic',name:'塑料制品'},
    {typename:'glass',name:'玻璃制品'},
    {typename:'domastic',name:'废旧家电'}

];
$(function(){
    $('#fstt').datagrid({
        title:'已完成订单列表',
        iconCls:'icon-edit',
        width:1120,
        height:400,
        singleSelect:true,
        idField:'itemid',
        // url:'/finishlist',
        columns:[[
            {field:'id',title:'订单ID',width:100},
            {field:'name',title:'姓名',width:100,editor:'text'},
            {field:'phone',title:'电话',width:120,editor:'text'},
            {field:'address',title:'地址',width:140,editor:'text'},
            {field:'type',title:'类型',width:100,align:'center',
                formatter:function(value){
                    for(var i=0; i<types.length; i++){
                        if (types[i].typename == value) return types[i].name;
                    }
                    return value;
                },
                editor:{
                    type:'combobox',
                    options:{
                        valueField:'typename',
                        textField:'name',
                        data:types,
                        required:true,
                        editable:false
                    }
                }
            },
            {field:'finishdate',title:'完成时间',width:100,editor:'text'},
            {field:'huishouid',title:'回收员ID',width:100},
            {field:'huishouname',title:'回收员姓名',width:100,editor:'text'},
            {field:'huishouphone',title:'回收员电话',width:120,editor:'text'},
            {field:'action',title:'Action',width:140,align:'center',
                formatter:function(value,row,index){
                    if (row.editing){
                        var s = '<a href="#" onclick="saverow(this)">保存</a> ';
                        var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
                        return s+c;
                    } else {
                        var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
                        return e;
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
            $.ajax({
                type:"POST",
                url:"finishUpdate",
                data:{
                    "id":row.id,
                    "name":row.name,
                    "phone":row.phone,
                    "address":row.address,
                    "type":row.type,
                    "huishouid":row.huishouid,
                    "finishdate":row.finishdate,
                    "huishouname":row.huishouname,
                    "huishouphone":row.huishouphone,
                },
                success: function (data) {
                    $("#fstt").datagrid("reload");
                }
            })
        },
        onCancelEdit:function(index,row){
            row.editing = false;
            updateActions(index);
        }
    });
});
function updateActions(index){
    $('#fstt').datagrid('updateRow',{
        index: index,
        row:{}
    });
}
function getRowIndex(target){
    var tr = $(target).closest('tr.datagrid-row');
    return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target){
    var row = $('#fstt').datagrid('getSelected',getRowIndex(target));
    row.editing = true;
    $('#fstt').datagrid('beginEdit', getRowIndex(target));
}
function deleterow(target){
    $('#fstt').datagrid('selectRow',getRowIndex(target));
    var row = $('#fstt').datagrid('getSelected',getRowIndex(target));
    if(row){
        $.messager.confirm('Confirm','Are you sure?',function(r){
            if (r){
                $('#fstt').datagrid('deleteRow', getRowIndex(target));
                $.ajax({
                    type: "POST",
                    url: "HsDelete",
                    data:{"id":row.id},
                    success: function (data) {
                        alert(data);
                        $("#fstt").datagrid("reload")
                    }
                })
            }
        });
    }
}
function saverow(target){
    $('#fstt').datagrid('endEdit', getRowIndex(target));
}
function cancelrow(target){
    $('#fstt').datagrid('cancelEdit', getRowIndex(target));
}
function hscancel() {
    $("#showfsdd").dialog("close");
}
function finishsearch() {
    var text = $('#finishid').val();
    var rows = $('#fstt').datagrid("getRows");
    var flag=0;
    $.each(rows,function(i,row){
        if(row.id==text){
            flag=1;
            $("#fsid").text(row.id);
            $("#fsname").text(row.name);
            $("#fsphone").text(row.phone);
            $("#fsaddress").text(row.address);
            $("#fstype").text(row.type);
            $("#fsdate").text(row.finishdate);
            $("#fshuishouid").text(row.huishouid);
            $("#fshuishouname").text(row.huishouname);
            $("#fshuishouphone").text(row.huishouphone);
            $('#showfsdd').dialog({closed:false});
            $('#fstt').datagrid('selectRow',i);
        }

    });
    if(flag==0)
    {
        alert("对不起，无此订单！！");
    }
}
function fsclose() {
    $('#showfsdd').dialog({closed:true});
}
