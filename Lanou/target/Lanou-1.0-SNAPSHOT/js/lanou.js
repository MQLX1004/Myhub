var types = [
    {typename:'metal',name:'金属'},
    {typename:'plastic',name:'塑料制品'},
    {typename:'glass',name:'玻璃制品'},
    {typename:'domastic',name:'废旧家电'}

];
$(function(){
    $('#tt').datagrid({
        title:'订单列表',
        iconCls:'icon-edit',
        width:660,
        height:400,
        singleSelect:true,
        idField:'itemid',
        //url:'/UserList',
        columns:[[
            {field:'id',title:'ID',width:100},
            {field:'name',title:'姓名',width:100,editor:'text'},
            {field:'phone',title:'电话',width:120,align:'right',editor:'text'},
            {field:'address',title:'地址',width:120,align:'right',editor:'text'},
            {field:'type',title:'类型',width:80,align:'center',
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
            {field:'action',title:'Action',width:140,align:'center',
                formatter:function(value,row,index){
                    if (row.editing){
                        var s = '<a href="#" onclick="saverow(this)">保存</a> ';
                        var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
                        return s+c;
                    } else {
                        var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
                        var d = '<a href="#" onclick="deleterow(this)">确认完成</a>';
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
            $.ajax({
                type:"POST",
                url:"UserUpdate",
                data:{
                    "id":row.id,
                    "name":row.name,
                    "phone":row.phone,
                    "address":row.address,
                    "type":row.type},
                success: function (data) {
                    $("#tt").datagrid("reload");
                }
            })
        },
        onCancelEdit:function(index,row){
            row.editing = false;
            updateActions(index);
        }
    });
});
$(function(){
    var myChart = echarts.init(document.getElementById('main'));
    myChart.setOption({
        title: {
            text: '订单处理折线图'
        },
        tooltip: {},
        legend: {
            data:['销量']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '订单量',
            type: 'line',
            data: []
        }]
    });

// 异步加载数据
    $.ajax({
        type:"POST",
        url:"getfinishdate",
        success:function (data) {
            var yi=0,er=0,san=0,si=0;
            var date = new Date();
            var year = date.getFullYear();
            for(var i=0;i<data.length;i++){
                var da = data[i];
                var bir = new Date(da);
                if(bir.getFullYear()==year){
                    if(1<=bir.getMonth()&&bir.getMonth()<=3){
                        yi++;
                    }
                    else if(4<=bir.getMonth()&&bir.getMonth()<=6){
                        er++;
                    }
                    else if(7<=bir.getMonth()&&bir.getMonth()<=9){
                        san++;
                    }
                    else {si++;}

                }
            }
            // 填入数据
            myChart.setOption({
                xAxis: {
                    data: ["第一季度","第二季度","第三季度","第四季度"]
                },
                series: [{
                    // 根据名字对应到相应的系列
                    name: '订单量',
                    data: [yi,er,san,si]
                }]
            });
        }
    });
});
$(function() {
    var my = echarts.init(document.getElementById('pies'));
    var brower=[];
    $.ajax({
        type:"post",
        url:"countbyhs",
        dataType:"json",
        success:function(data){
            for(var i=0;i<data.length;i++){
                brower.push({
                    name:data[i].name,
                    value:data[i].value
                })
            }
            my.setOption({
                title:{
                    show:true,
                    text:"回收员完成订单总量"
                },
                series: {
                    title:"回收员订单量",
                    radius:'55%',
                    itemStyle: {
                        normal: {
                            label:{
                            show:true,
                            formatter:'{b} : {c}'
                        },
                            labelLine :{show:true}
                        }},
                    type: 'pie',
                    data:brower
                },
            });
        }
    });

    });
function updateActions(index){
    $('#tt').datagrid('updateRow',{
        index: index,
        row:{}
    });
}
function getRowIndex(target){
    var tr = $(target).closest('tr.datagrid-row');
    return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target){
    $('#tt').datagrid('beginEdit', getRowIndex(target));
}
function deleterow(target){
    $("#finishff").form("clear");
    $('#tt').datagrid('selectRow',getRowIndex(target));
    $('#finishdd').dialog({closed:false});

}
function saverow(target){
        $('#tt').datagrid('endEdit', getRowIndex(target));
}
function cancelrow(target){
    $('#tt').datagrid('cancelEdit', getRowIndex(target));
}
function insert(){
    $("#ff").form("clear");
    $('.type').combobox('loadData',types);
    $('.type').combobox('setValue',types[0].name);
    $('#dd').dialog({closed:false});
    $('#tt').datagrid("reload");
}
function save() {
    $.messager.confirm("Confirm","确定提交？",function (r) {
        if(r){
            $('#ff').form('submit',{
                url:"/mainsubmit",
                success: function (data) {
                    $("#dd").dialog("close");
                    $("#tt").datagrid("reload");
                }
            })
        }
    })
}
function cancel() {
    $("#dd").dialog("close");
}
function searchid() {
    var text = $('#textid').val();
    var rows = $('#tt').datagrid("getRows");
    var flag=0;
    $.each(rows,function(i,row){
        if(row.id==text){
            flag=1;
            $("#showname").text(row.name);
            $("#showphone").text(row.phone);
            $("#showaddress").text(row.address);
            $("#showtype").text(row.type);
            $('#showdd').dialog({closed:false});
            $('#tt').datagrid('selectRow',i);
        }

    });
    if(flag==0)
    {
        alert("对不起，无此订单！！");
    }

}
function closeshow() {
    $('#showdd').dialog({closed:true});
}
function finishcancel() {
    $('#finishdd').dialog({closed:true});
}
function finishok() {
    var row = $('#tt').datagrid('getSelected');
    if(row){
        $.messager.confirm('Confirm','Are you sure?',function(r) {
            if (r) {
                $('#finishff').form('submit',{
                    url:"/finishinsert?id="+row.id+"&name="+row.name+"&address="+row.address+"&phone="+row.phone+"&type="+row.type,
                    success: function (data) {
                        alert("success");
                        $.ajax({
                            type: "POST",
                            url: "UserDelete",
                            data: {"id": row.id},
                            success: function (data) {
                                $('#tt').datagrid('deleteRow',$('#tt').datagrid('getRowIndex',row));
                                $("#tt").datagrid("reload");
                            }
                        })
                        $("#finishdd").dialog("close");
                    }
                })

            }
        })
    }
}