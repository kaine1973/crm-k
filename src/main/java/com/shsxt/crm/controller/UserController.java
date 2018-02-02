package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.models.MessageModel;
import com.shsxt.crm.models.UserModel;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.CookieUtil;
import com.shsxt.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @RequestMapping("userLogin")
    @ResponseBody
    public MessageModel userLogin(String userName, String userPwd) {
        MessageModel mm = new MessageModel();

        UserModel um = userService.userLogin(userName, userPwd);
        mm.setResult(um);
        mm.setCode(200);
        mm.setMsg("登陆成功");
        return mm;
    }

    @RequestMapping("newPwd")
    @ResponseBody
    public MessageModel newPwd(HttpServletRequest request, String oldPassword, String newPassword, String confirmPassword) {
        String userId = CookieUtil.getCookieValue(request, "userId");
        userService.newPwd(userId, oldPassword, newPassword, confirmPassword);
        MessageModel mm = new MessageModel();
        mm.setCode(200);
        mm.setMsg("密码修改成功");
        return mm;
    }


    @RequestMapping("queryAllCustomerManager")
    @ResponseBody
    public List<User> queryAllCustomerManager() {
        return userService.queryAllCustomerManager();
    }
}
