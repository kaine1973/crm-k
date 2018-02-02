function formatterDevResult(dev_result) {
    if (dev_result == '0') {
        return '未开发';
    } else if (dev_result == '1') {
        return '开发中';
    } else if (dev_result == '2') {
        return '开发成功';
    } else if (dev_result == '3') {
        return '开发失败';
    } else {
        return "未定义";
    }

}

function formatterOp(operation, rowdata) {

    if (rowdata.devResult == '0' || rowdata.devResult == '1') {
        return "<a href=javascript:openCusDevPlanDetailTab(\"客户开发计划管理—" + rowdata.id + "\",\"" + rowdata.id + "\")>开发</a>";
    } else if (rowdata.devResult == '2' || rowdata.devResult == '3') {
        return "<a href=javascript:openCusDevPlanDetailTab(\"客户开发计划详情—" + rowdata.id + "\",\"" + rowdata.id + "\")>查看详情</a>";
    }

}

function openCusDevPlanDetailTab(title, id) {
    window.parent.openTab(title, ctx + "/cus_dev_plan/index?id=" + id);
}

$(
    function () {
        $("#dg").datagrid({
            rowStyler: function (index, rowData) {
                if (rowData.devResult == '3')
                    return "background-color:red";
                if (rowData.devResult == '2')
                    return "background-color:green";
                if (rowData.devResult == '1' || rowData.devResult == '0')
                    return "background-color:yellow";
            }
        })
    }
)