package com.shsxt.crm.query;

public class CusOrderQuery extends PageVO {

    private Integer cid;

    private Integer orderId;
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
