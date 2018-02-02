package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.dao.SaleChanceDao;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.CookieUtil;
import com.shsxt.crm.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.ServiceMode;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleChanceService {

    @Resource
    private SaleChanceDao saleChanceDao;

    public Map<String, Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery) {
        PageHelper.startPage(saleChanceQuery.getPage(), saleChanceQuery.getRows());
        List<SaleChance> list = saleChanceDao.querySaleChancesByParams(saleChanceQuery);
        PageInfo<SaleChance> pi = new PageInfo(list);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", pi.getList());
        map.put("total", pi.getTotal());
        return map;
    }

    public void saveSaleChance(HttpServletRequest request, SaleChance saleChance) throws UnsupportedEncodingException {
        checkParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());
        saleChance.setCreateMan(URLDecoder.decode(CookieUtil.getCookieValue(request, "trueName"), "utf-8"));
        if (saleChance.getAssignMan() != null) {
            saleChance.setState(1);
        }
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());
        saleChance.setIsValid(1);
        saleChance.setState(0);
        saleChance.setDevResult(0);
        AssertUtil.isTrue(saleChanceDao.saveSaleChance(saleChance) < 1, "保存营销机会失败");
    }

    public void checkParams(String customerName, String linkMan, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName), "客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan), "联系人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "联系电话不能为空");
    }

    public void edit(HttpServletRequest request, SaleChance saleChance) {
        checkParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());
        if (saleChance.getAssignMan() != null) {
            saleChance.setState(1);
        }
        saleChance.setUpdateDate(new Date());
        AssertUtil.isTrue(saleChanceDao.editSaleChance(saleChance) < 1, "保存营销机会失败");
    }

    public void delete(Integer[] ids) {
        AssertUtil.isTrue(saleChanceDao.delete(ids) < 1, "删除营销机会失败");
    }

    public void changeDevResult(Integer devResult, Integer saleChanceId) {
        AssertUtil.isTrue(saleChanceDao.updateDevResult(devResult, saleChanceId) < 1, "修改开发结果失败");
    }
}
