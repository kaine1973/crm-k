function openPasswordModifyDialog() {
    $("#dlg").dialog("open")
}

function closePasswordModifyDialog() {
    $("#dlg").dialog("close")
}

function openTab(text, url, iconCls) {
    if ($("#tabs").tabs("exists", text)) {
        $("#tabs").tabs("select", text);
    } else {
        var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"
            + url + "'></iframe>";
        $("#tabs").tabs("add", {
            title: text,
            iconCls: iconCls,
            closable: true,
            content: content
        })
    }
}

function modifyPassword() {
    var oldPassword = $("#oldPassword").val();
    var newPassword = $("#newPassword").val();
    var newPassword2 = $("#newPassword2").val();

    if (isEmpty(oldPassword) || isEmpty(newPassword) || isEmpty(newPassword2)) {
        $.messager.alert("错误", "当前密码、新密码、重复密码不能为空", "info");
        return;
    }
    if (newPassword != newPassword2) {
        $.messager.alert("错误", "新密码与重复密码不一致", "error")
        return;
    }

    $("#fm").form("submit", {
        url: ctx + "/user/newPwd",
        onSubmit: function () {
            return $("#fm").form("validate");
        },
        success: function (data) {
            data = JSON.parse(data);
            console.log(data);
            if (data.code == '200') {
                $.messager.alert("请重新登陆");
                return;
            }
            var msg = data;
            alert(msg);
        }
    })

}