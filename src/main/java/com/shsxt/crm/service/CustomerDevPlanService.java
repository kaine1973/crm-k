package com.shsxt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.dao.CustomerDevPlanDao;
import com.shsxt.crm.dao.SaleChanceDao;
import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.vo.CusDevPlan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CustomerDevPlanService {

    @Resource
    private SaleChanceDao saleChanceDao;

    @Resource
    private CustomerDevPlanDao customerDevPlanDao;

    public Map<String, Object> queryDevPlan(CusDevPlanQuery cusDevPlanQuery) {

        AssertUtil.isTrue(saleChanceDao.querySaleChancesById(cusDevPlanQuery.getSaleChanceId()) == null, "营销机会不存在");
        List<CusDevPlan> cusDevPlans = customerDevPlanDao.queryDevPlan(cusDevPlanQuery);
        PageHelper.startPage(cusDevPlanQuery.getPage(), cusDevPlanQuery.getRows());
        PageInfo pi = new PageInfo(cusDevPlans);
        Map<String, Object> map = new HashMap();
        map.put("rows", pi.getList());
        map.put("total", pi.getTotal());
        return map;
    }

    public void insert(CusDevPlan cusDevPlan) {
        AssertUtil.isTrue(saleChanceDao.querySaleChancesById(cusDevPlan.getSaleChanceId() + "") == null, "营销机会不存在");

        cusDevPlan.setCreateDate(new Date());
        cusDevPlan.setUpdateDate(new Date());
        cusDevPlan.setIsValid(1);
        AssertUtil.isTrue(customerDevPlanDao.insert(cusDevPlan) < 1, "插入开发计划失败");
        AssertUtil.isTrue(saleChanceDao.updateDevResult(1, cusDevPlan.getSaleChanceId()) < 1, "修改开发结果失败");

    }

    public void delete(Integer id) {
        AssertUtil.isTrue(customerDevPlanDao.delete(id) < 1, "删除开发计划失败");
    }

    public void update(CusDevPlan cusDevPlan) {
        AssertUtil.isTrue(saleChanceDao.querySaleChancesById(cusDevPlan.getSaleChanceId() + "") == null, "营销机会不存在");

        cusDevPlan.setUpdateDate(new Date());

        AssertUtil.isTrue(customerDevPlanDao.update(cusDevPlan) < 0, "更新开发计划失败");
    }
}
