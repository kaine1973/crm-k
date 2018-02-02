$(
    function () {
        var res = $("#devResult").val();
        var saleChanceId = $("#saleChanceId").val();
        if (res == '2' || res == '3') {
            $("#toolbar").remove();
        }
        $("#dg").edatagrid({
            url: ctx + "/cus_dev_plan/queryDevPlan?saleChanceId=" + saleChanceId,
            saveUrl: ctx + "/cus_dev_plan/insert?saleChanceId=" + saleChanceId,
            updateUrl: ctx + "/cus_dev_plan/update?saleChanceId=" + saleChanceId
        })
    }
)

function saveCusDevPlan() {
    $("#dg").edatagrid("saveRow");
    $("#dg").edatagrid("load");
}

function updateCusDevPlan() {
    $("#dg").edatagrid("saveRow");
    $("#dg").edatagrid("load");
}

function delCusDevPlan() {
    var sels = $("#dg").edatagrid("getSelections");
    if (sels.length < 1) {
        $.messager.alert("提示", "请选择一项进行操作", "info");
        return;
    }
    $.messager.confirm("警告", "确定删除所选项目？", function (t) {
        if (t) {
            $.ajax({
                url: ctx + "/cus_dev_plan/delete",
                type: 'post',
                dataType: 'json',
                data: "id=" + sels[0].id,
                success: function (data) {
                    $.messager.alert("提示", data.msg, "info")
                    if (data.code == 200) {
                        $.edatagrid('load')
                    }
                }
            })
        }
    });
}

function updateSaleChanceDevResult(state) {
    var saleChanceId = $("#saleChanceId").val();
    var msg = "";
    if (state == '2') {
        msg = "确定完成开发？"
    } else {
        msg = "确定终止开发？";
    }
    $.messager.confirm("警告", msg, function (t) {
        if (t) {
            $.ajax({
                type: 'post',
                url: ctx + "/sale_chance/changeDevResult",
                dataType: 'json',
                data: {
                    devResult: state,
                    saleChanceId: saleChanceId
                },
                success: function (data) {
                    $.messager.alert("提示", data.msg, "info")
                    if (data.code == '200') {
                        $("#toolbar").remove();
                    }
                }
            })
        }
    })
}