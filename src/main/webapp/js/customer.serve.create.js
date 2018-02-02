function saveCustomerService(state) {
    var url = ctx+"/customer_serve/insert";
    if(state == 1){
        url = ctx+"/customer_serve/insert";
    }
    $("#fm").form("submit",{
        url:url,
        onSubmit:function () {
            return $("#fm").form("validate")
        },
        success: function (data) {
            data = JSON.parse(data);
            $.messager.alert("crm", data.msg, "info");
            if (data.code == 200) {
                $("#dlg").dialog('close')
                $("#dg").datagrid("reload")
            }
        }
    })
}
