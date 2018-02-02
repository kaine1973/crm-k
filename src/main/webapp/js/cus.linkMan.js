function searchCustomer() {
    $("#dg").datagrid("load", {
        linkName: $("#link_name").val(),
        officePhone: $("#office_phone").val(),
        phone: $("#phone").val()
    })
}

function openLinkManModifyDialog() {
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
    $("#dlg").dialog("open").dialog("setTitle", "修改联系人")

}

function openLinkManAddDialog() {
    cleanForm()
    $("#dlg").dialog("open").dialog("setTitle", "添加联系人")
}

function delLinkMan() {
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
                url: ctx + "/linkMan/delete",
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

function saveOrUpdateLinkMan() {
    var id = $("#id").val()
    var url = ctx + "/linkMan/insert?cusId=" + cusId;
    if (!isEmpty(id)) {
        url = ctx + "/linkMan/update?cusId=" + cusId;
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
                $("#dlg").dialog('close')
                $("#dg").datagrid("reload")
            }
        }
    })
}

function genderformat(sex) {
    if (sex == 1) {
        return '男';
    } else if (sex == 0) {
        return '女';
    }
}

function closeLinkManDialog() {
    $.messager.confirm("提示", "未保存的更改将丢失，确定？", function (f) {
        if (f) {
            $("#dlg").dialog("close");
        }
    })
}

function cleanForm() {
    $("#id").val("");
    $("#linkName").val("");
    $("#zhiwei").val("");
    $("#officePhone").val("");
    $("#phone").val("");
    $("#sex").prop("selected", true);
}