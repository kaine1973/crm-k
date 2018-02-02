package com.shsxt.crm.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

    public static String getCookieValue(HttpServletRequest request, String key) {

        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (key.equals(name)) {
                String value = cookie.getValue();
                return value;
            }
        }

        return null;
    }

}
