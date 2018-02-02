package com.shsxt.crm.base.exceptions;

import com.shsxt.crm.base.CrmConstant;

public class ParamsException extends RuntimeException {

    public Integer code = CrmConstant.DEFAULT_ERROR_CODE;
    public String msg = CrmConstant.DEFAULT_ERROR_MSG;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ParamsException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
