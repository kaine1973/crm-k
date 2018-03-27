package com.shsxt.crm.interceptors;

import com.shsxt.crm.base.CrmConstant;
import com.shsxt.crm.dao.UserDao;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.CookieUtil;
import com.shsxt.crm.utils.EncyptUtil;
import com.shsxt.crm.vo.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserService userService;

    @Resource
    private UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = CookieUtil.getCookieValue(request, "userId");

        if("/".equals(request.getRequestURI())){
            return true;
        }
        
        AssertUtil.isTrue(userId == null, CrmConstant.DEFAULT_ERROR_CODE, "用户未登录");
        userId = EncyptUtil.idDecode(userId);
        User user = userDao.selectUserById(userId);
        AssertUtil.isTrue(user == null, CrmConstant.DEFAULT_ERROR_CODE, "用户不存在");

        return true;
    }
}
