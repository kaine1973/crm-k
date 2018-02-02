package com.shsxt.crm.utils;

import com.shsxt.crm.base.CrmConstant;
import com.shsxt.crm.base.exceptions.ParamsException;

public class AssertUtil {

    public static void isTrue(boolean flag, String msg) {
        if (flag) {
            throw new ParamsException(CrmConstant.DEFAULT_ERROR_CODE, msg);
        }
    }

    public static void isTrue(boolean flag, Integer code, String msg) {
        if (flag) {
            throw new ParamsException(code, msg);
        }
    }


}
