package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.dto.OrderDetail;
import com.shsxt.crm.query.CusOrderQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.service.CustomerOrderService;
import com.shsxt.crm.service.CustomerService;
import com.shsxt.crm.vo.Customer;
import com.shsxt.crm.vo.CustomerOrder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customer_order")
public class CustomerOrderController extends BaseController{

    @Resource
    private CustomerOrderService customerOrderService;

    @RequestMapping("queryOrderByCid")
    @ResponseBody
    public Map<String, Object> queryOrdersByCid(CusOrderQuery cusOrderQuery){
        return customerOrderService.queryOrdersByCid(cusOrderQuery);
    }

    @RequestMapping("queryOrderDetailById")
    @ResponseBody
    public OrderDetail queryOrderDetailByid(Integer id){
        return customerOrderService.queryOrderDetailById(id);
    }

    @RequestMapping("queryOrderDetailsByOrderId")
    @ResponseBody
    public Map<String,Object> queryOrderDetailsByid(CusOrderQuery cusOrderQuery){
        return customerOrderService.queryOrderDetailsById(cusOrderQuery);
    }



}
