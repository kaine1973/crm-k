package com.shsxt.crm.base.exceptions;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.base.CrmConstant;
import com.shsxt.crm.models.MessageModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class GlobalException implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
       
        ModelAndView mv = createDefaultModelAndView(request);

        if (handler instanceof HandlerMethod) {
             HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
            if (e instanceof ParamsException) {
                ParamsException e1 = (ParamsException) e;
                if (e1.code.equals(CrmConstant.DEFAULT_ERROR_CODE)) {
                    mv.addObject("msg", e1.getMsg());
                    mv.addObject("code", CrmConstant.DEFAULT_ERROR_CODE);
                    return mv;
                }
            }
        ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
            if (responseBody != null) {
                MessageModel mm = new MessageModel();
                mm.setMsg(CrmConstant.DEFAULT_ERROR_MSG);
                mm.setCode(CrmConstant.DEFAULT_ERROR_CODE);

                if (e instanceof ParamsException) {
                    ParamsException e1 = (ParamsException) e;
                    mm.setCode(e1.getCode());
                    mm.setMsg(e1.getMsg());
                }
                response.setContentType("application/json;charset=UTF-8");
                response.setCharacterEncoding("utf-8");
                PrintWriter pw = null;
                try {
                    pw = response.getWriter();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    if (pw != null) {
                        pw.write(JSON.toJSONString(mm));
                        pw.flush();
                        pw.close();
                    }
                }
            } else {
                if (e instanceof ParamsException) {
                    ParamsException e1 = (ParamsException) e;
                    mv.addObject("msg", e1.getCode());
                    mv.addObject("code", e1.getCode());
                }
            }

        }
        return mv;

    }


    private ModelAndView createDefaultModelAndView(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("code", CrmConstant.DEFAULT_ERROR_CODE);
        mv.addObject("msg", CrmConstant.DEFAULT_ERROR_MSG);
        mv.addObject("uri", request.getRequestURI());
        mv.addObject("ctx", request.getContextPath());
        return mv;
    }


}
