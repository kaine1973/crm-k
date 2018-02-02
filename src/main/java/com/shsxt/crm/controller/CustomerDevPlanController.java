package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.dao.SaleChanceDao;
import com.shsxt.crm.models.MessageModel;
import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.service.CustomerDevPlanService;
import com.shsxt.crm.vo.CusDevPlan;
import com.shsxt.crm.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("cus_dev_plan")
public class CustomerDevPlanController extends BaseController {

    @Resource
    private CustomerDevPlanService customerDevPlanService;

    @Resource
    private SaleChanceDao saleChanceDao;

    @RequestMapping("index")
    public String index(String id, Model model) {
        SaleChance saleChance = saleChanceDao.querySaleChancesById(id);
        model.addAttribute("saleChance", saleChance);
        return "cus_dev_plan_detail";
    }

    @RequestMapping("queryDevPlan")
    @ResponseBody
    public Map<String, Object> queryDevPlan(CusDevPlanQuery cusDevPlanQuery) {
        return customerDevPlanService.queryDevPlan(cusDevPlanQuery);
    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(CusDevPlan cusDevPlan) {
        customerDevPlanService.insert(cusDevPlan);
        return success("添加开发计划成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(CusDevPlan cusDevPlan) {
        customerDevPlanService.update(cusDevPlan);
        return success("修改开发计划成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer id) {
        customerDevPlanService.delete(id);
        return success("删除开发计划成功");
    }

}
