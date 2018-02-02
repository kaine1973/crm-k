function openCustomerAddDialog() {
    $("#fm").form("clear");
    $("#dlg").dialog("open");
}

function openCustomerModifyDialog() {
    var sels = $("#dg").datagrid("getSelections");
    if (sels.length < 1) {
        $.messager.alert("提示", "请至少选择一项进行操作", "info")
        return;
    }
    if (sels.length > 1) {
        $.messager.alert("提示", "一次只能操作一条数据", "info")
        return;
    }
    $("#fm").form("load", sels[0]);
    $("#dlg").dialog("open");

}

function closeCustomerDialog() {
    $.messager.confirm("提示", "未保存的更改将丢失，确定？", function (f) {
        if (f) {
            $("#dlg").dialog("close");
        }
    })
}

function saveOrUpdateCustomer() {
    var id = $("#id").val();
    var url = ctx + "/customer/update"
    if (id == null || id == '') {
        url = ctx + "/customer/insert"
    }
    $("#fm").form("submit", {
        url: url,
        onsubmit: function () {
            return $("#fm").form("validate");
        },
        success: function (data) {
            data = JSON.parse(data);
            $.messager.alert("提示", data.msg, "info")
            if (data.code == 200) {
                $("#dlg").dialog("close")
                $("#dg").dialog("load")
            }
        }
    })
}

function openCustomerOtherInfo(title, id) {
     var url = ctx + "/customer/openCustomerOtherInfo/"+id;
    var sels = $("#dg").datagrid("getSelections");
    if (sels.length < 1) {
        $.messager.alert("提示", "请至少选择一项进行操作", "info")
        return;
    }
    if (sels.length > 1) {
        $.messager.alert("提示", "一次只能操作一条数据", "info")
        return;
    }
    var cusId = sels[0].id;
    var name = sels[0].name;
    window.parent.openTab(title + '-' + name, url + "?cid=" + cusId);
}

function deleteCustomer() {
    var sels = $("#dg").datagrid("getSelections");
    if (sels.length < 1) {
        $.messager.alert("提示", "请至少选择一项进行操作", "info");
        return;
    }
    var ids = "ids="
    for(var i = 0 ;i<sels.length;i++){
        ids+=sels[i].id+"&ids=";
    }
    ids = ids.substring(0,ids.length-5);
    $.messager.confirm("警告", "确定删除所选项目？", function (t) {
        if (t) {
            $.ajax({
                url: ctx + "/customer/delete",
                type: 'post',
                dataType: 'json',
                data: ids,
                success: function (data) {
                    $.messager.alert("提示", data.msg, "info")
                    if (data.code == 200) {
                        $("#dg").datagrid("load")
                    }
                }
            })
        }
    });

}