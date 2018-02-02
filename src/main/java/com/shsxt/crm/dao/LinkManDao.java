package com.shsxt.crm.dao;

import com.shsxt.crm.query.LinkManQuery;
import com.shsxt.crm.vo.LinkMan;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface LinkManDao {


    public List<LinkMan> query(LinkManQuery linkManQuery);

    public Integer insert(LinkMan linkMan);

    public Integer update(LinkMan linkMan);

    public Integer delete(Integer[] ids);
}
