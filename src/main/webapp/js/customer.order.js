function formatterState(state) {
    if(state == '0'){
        return "未支付";
    }else if(state == '1'){
        return "已支付";
    }
}
function formatterOp(state) {
    var href = "javascript:openOrderDetail()"
    return "<a href='"+href+"'>查看详情</a>"
}

function openOrderDetail() {
    var sels = $("#dg").datagrid("getSelections");

    var id =  sels[0].id;
    $.ajax({
        type:'post',
        url:ctx+"/customer_order/queryOrderDetailById",
        data:'id='+id,
        dataType:'json',
        success:function (data) {
            $("#fm").form('load',data);
        }
    })
    $("#dg2").datagrid("load",{
        orderId:id
    });

    $("#dlg").dialog("open");
}

function reloadgrid(id) {
    var url = ctx+"/customer_order/queryOrderDetailsByOrderId?orderId="+id;
    $("#dg2").datagrid('options').url = url
    $("#dg2").datagrid('reload')
}

function closeOrderDetailDialog() {
    $("#dlg").dialog("close")
}