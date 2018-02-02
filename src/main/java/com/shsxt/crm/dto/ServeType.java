package com.shsxt.crm.dto;

import com.shsxt.crm.base.BaseVo;

public class ServeType extends BaseVo {

    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setLevel(String type) {
        this.type = type;
    }
}
