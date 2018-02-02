package com.shsxt.crm.base;

import com.shsxt.crm.models.MessageModel;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @ModelAttribute
    public void preMethod(HttpServletRequest request) {
        request.setAttribute("ctx", request.getContextPath());

    }

    public static MessageModel success(String msg) {
        MessageModel mm = new MessageModel();
        mm.setCode(200);
        mm.setMsg(msg);
        return mm;
    }

}
