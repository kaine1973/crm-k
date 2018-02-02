package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.models.MessageModel;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.vo.SaleChance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {

    @RequestMapping("index/{type}")
    public String index(@PathVariable("type") String type) {
        switch (Integer.valueOf(type)) {
            case 1:
                return "sale_chance";
            case 2:
                return "cus_dev_plan";
            default:
                return "error";
        }
    }


    @Resource
    private SaleChanceService saleChanceService;

    @RequestMapping("querySaleChancesByParams")
    @ResponseBody
    public Map<String, Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery) {
        return saleChanceService.querySaleChancesByParams(saleChanceQuery);
    }

    @RequestMapping("save")
    @ResponseBody
    public MessageModel saveSaleChance(HttpServletRequest request, SaleChance saleChance) throws UnsupportedEncodingException {
        MessageModel mm = new MessageModel();
        saleChanceService.saveSaleChance(request, saleChance);
        mm.setCode(200);
        mm.setMsg("保存营销机会成功");
        return mm;
    }

    @RequestMapping("edit")
    @ResponseBody
    public MessageModel edit(HttpServletRequest request, SaleChance saleChance) {
        MessageModel mm = new MessageModel();
        saleChanceService.edit(request, saleChance);
        mm.setCode(200);
        mm.setMsg("保存营销机会成功");
        return mm;
    }

    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer[] id) {
        MessageModel mm = new MessageModel();
        saleChanceService.delete(id);
        mm.setCode(200);
        mm.setMsg("删除营销机会成功");
        return mm;
    }

    @RequestMapping("changeDevResult")
    @ResponseBody
    public MessageModel changeDevResult(Integer devResult, Integer saleChanceId) {
        MessageModel mm = new MessageModel();
        saleChanceService.changeDevResult(devResult, saleChanceId);
        mm.setCode(200);
        mm.setMsg("操作成功");
        return mm;
    }
}
