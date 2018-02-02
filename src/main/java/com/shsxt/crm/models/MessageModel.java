package com.shsxt.crm.models;

public class MessageModel {

    public Integer code;
    public String msg;
    public Object result;

    public MessageModel() {
    }

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public MessageModel(Integer code, String msg, Object result) {

        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
