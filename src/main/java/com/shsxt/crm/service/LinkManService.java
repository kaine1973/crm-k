package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.shsxt.crm.dao.LinkManDao;
import com.shsxt.crm.query.LinkManQuery;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.vo.LinkMan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LinkManService {

    @Resource
    private LinkManDao linkManDao;

    public Map<String, Object> query(LinkManQuery linkManQuery) {
        List<LinkMan> list = linkManDao.query(linkManQuery);
        PageHelper.startPage(linkManQuery.getPage(), linkManQuery.getRows());
        PageInfo pi = new PageInfo(list);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", pi.getList());
        map.put("total", pi.getTotal());
        return map;
    }

    public void insert(LinkMan linkMan) {
        checkParams(linkMan.getLinkName(), linkMan.getZhiwei(), linkMan.getPhone());
        linkMan.setIsValid(1);
        linkMan.setCreateDate(new Date());
        linkMan.setUpdateDate(new Date());
        AssertUtil.isTrue(linkManDao.insert(linkMan) < 1, "添加联系人失败～");

    }

    public void checkParams(String linkName, String zhiwei, String phone) {
        AssertUtil.isTrue(StringUtil.isEmpty(linkName), "联系人不能为空");
        AssertUtil.isTrue(StringUtil.isEmpty(zhiwei), "职位不能为空");
        AssertUtil.isTrue(StringUtil.isEmpty(phone), "手机号不能为空");
    }

    public void update(LinkMan linkMan) {
        AssertUtil.isTrue(linkManDao.update(linkMan) < 1, "修改联系人失败");
    }


    public void delete(Integer[] ids) {
        AssertUtil.isTrue(linkManDao.delete(ids)<ids.length,"删除联系人失败");
    }
}
