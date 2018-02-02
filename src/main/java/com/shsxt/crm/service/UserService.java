package com.shsxt.crm.service;

import com.github.pagehelper.StringUtil;
import com.shsxt.crm.base.CrmConstant;
import com.shsxt.crm.dao.UserDao;
import com.shsxt.crm.models.UserModel;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.EncyptUtil;
import com.shsxt.crm.vo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public UserModel userLogin(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtil.isEmpty(userName), CrmConstant.LOGIN_FAIL_CODE, "用户名不能为空");
        AssertUtil.isTrue(StringUtil.isEmpty(userPwd), CrmConstant.LOGIN_FAIL_CODE, "密码不能为空");
        User user = userDao.selectByUserName(userName);
        String encypt = EncyptUtil.encypt(userPwd);
        AssertUtil.isTrue(user == null, CrmConstant.LOGIN_FAIL_CODE, "该用户名未注册");
        AssertUtil.isTrue(!user.getUserPwd().equals(encypt), CrmConstant.LOGIN_FAIL_CODE, "密码输入有误");
        return newUserModel(user);
    }


    private UserModel newUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        userModel.setUserId(EncyptUtil.idEncode(user.getId()));
        return userModel;
    }

    public void newPwd(String userId, String oldPassword, String newPassword, String confirmPassword) {
        AssertUtil.isTrue(StringUtil.isEmpty(userId), "用户未登录");
        AssertUtil.isTrue(StringUtil.isEmpty(newPassword) || StringUtil.isEmpty(oldPassword) || StringUtil.isEmpty(confirmPassword), "密码不能为空");

        AssertUtil.isTrue(!(newPassword.trim()).equals(confirmPassword.trim()), "新密码二次输入不一致");
        User user = userDao.selectUserById(EncyptUtil.idDecode(userId));
        AssertUtil.isTrue(user == null, "非法用户");
        AssertUtil.isTrue(!user.getUserPwd().equals(EncyptUtil.encypt(oldPassword)), "原密码错误");
        String newPwd = EncyptUtil.encypt(newPassword);
        AssertUtil.isTrue(userDao.updateUserPwd(EncyptUtil.idDecode(userId), newPwd) < 1, "修改失败");
    }

    public List<User> queryAllCustomerManager() {
        return userDao.queryAllCustomerManager();
    }
}
