function userLogin() {
    var userName = $("#userName").val();
    var userPwd = $("#userPwd").val();
    if (isEmpty(userName) || isEmpty(userPwd)) {
        alert("用户名或密码不能为空");
        return;
    }
    var params = {};
    params.userName = userName;
    params.userPwd = userPwd;
    $.ajax({
        type: "post",
        url: ctx + "/user/userLogin",
        data: params,
        dataType: "json",
        success: function (data) {
            if (data.code == 200) {
                alert(data.msg);
                var user = data.result;
                $.cookie("userName", user.userName);
                $.cookie("trueName", user.trueName);
                $.cookie("userId", user.userId);
                window.location.href = "main";
            } else {
                alert(data.msg);
            }
        }
    })
}