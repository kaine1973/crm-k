package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.utils.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class IndexController extends BaseController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest request) throws UnsupportedEncodingException {
        String userName = CookieUtil.getCookieValue(request, "userName");
        String trueName = CookieUtil.getCookieValue(request, "trueName");
        request.setAttribute("trueName", URLDecoder.decode(trueName, "utf-8"));
        request.setAttribute("userName", URLDecoder.decode(userName, "utf-8"));


        return "main";
    }


}
