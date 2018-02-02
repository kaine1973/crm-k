function closeDialog() {
    $("#dlg").dialog("close")
}

function formatterState(state) {

    if (state == "0") {
        return "未分配";
    } else if (state == "1") {
        return "已分配";
    }
}

function saveAccount() {
    var id = $("#id").val()
    var url = ctx + "/sale_chance/save";
    if (isEmpty(id)) {
        url = ctx + "/sale_chance/edit";
    }


    $("#fm").form("submit", {
        url: url,
        onsubmit: function () {
            return $("#fm").form("validate")
        },
        success: function (data) {
            data = JSON.parse(data);
            $.messager.alert("crm", data.msg, "info");
            if (data.code == 200) {
                closeDialog()
                $("#dg").datagrid("reload")
            }
        }
    })
}

function deleteAccount() {
    var sels = $("#dg").datagrid("getSelections");
    if (sels.length < 1) {
        $.messager.alert("提示", "至少选择一项", "info");
        return;
    }
    var ids = "id=";
    for (var i = 0; i < sels.length; i++) {
        if (i == 0) {
            ids = ids + sels[i].id;
        } else {
            ids = ids + "&id=" + sels[i].id;
        }
    }
    $.messager.confirm("警告", "确定删除？", function (r) {
        if (r) {
            $.ajax({
                url: ctx + "/sale_chance/delete",
                data: ids,
                dataType: "json",
                type: "post",
                success: function (data) {
                    $.messager.alert("提示", data.msg, "info")
                    if (data.code == "200") {
                        $("#dg").datagrid("reload")
                    }
                }
            })
        }
    });

}

function openAddAccountDialog() {
    $("#fm").form("clear");
    $("#dlg").dialog("open").dialog("setTitle", "添加营销机会信息")
}

function searchSaleChances() {
    $("#dg").datagrid("load", {
        createMan: $("#createMan").val(),
        customerName: $("#customerName").val(),
        createDate: $("#createDate").datebox('getValue'),
        state: $("#state").combobox('getValue')
    })
}

/*function$(
    function defaultClosed() {
        $("#dlg").dialog("close")
    }
)*/

function openModifyAccountDialog() {
    var abc = $("#dg").datagrid("getSelections");

    if (abc.length == 0) {
        $.messager.alert("提示", "请先选择1条数据", "info")
        return;
    }

    if (abc.length > 1) {
        $.messager.alert("提示", "一次只能操作条数据", "info")
        return;
    }
    $("#fm").form("load", abc[0]);
    $("#dlg").dialog("open").dialog("setTitle", "修改营销机会信息");
}