package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.models.MessageModel;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.service.CustomerService;
import com.shsxt.crm.vo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("customer")
public class CustomerController extends BaseController {


    @Resource
    private CustomerService customerService;

    @RequestMapping("queryAllCustomers")
    @ResponseBody
    public List<Customer> queryAllCustomers() {
        return customerService.queryAllCustomers();
    }

    @RequestMapping("index")
    public String index() {
        return "customer";
    }

    @RequestMapping("openCustomerOtherInfo/{id}")
    public String openCustomerOtherInfo(@PathVariable("id")Integer id, Integer cid, Model model){
        Customer customer = customerService.queryCusById(cid);
        model.addAttribute("customer",customer);
        switch (id){
            case 1:return "customer_linkMan";
            case 2:return "customer_concat";
            case 3:return "customer_order";
            default:return "error";
        }

    }

    @RequestMapping("linkMan")
    public String linkMan() {
        return "customer_linkMan";
    }

    @RequestMapping("queryCustomersByParams")
    @ResponseBody
    public Map<String, Object> queryCustomersByParams(CustomerQuery customerQuery) {
        return customerService.queryCustomersByParams(customerQuery);
    }

    @RequestMapping("insert")
    @ResponseBody
    public MessageModel insert(Customer customer) {
        customerService.insert(customer);
        return success("客户信息保存成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public MessageModel update(Customer customer) {
        customerService.update(customer);
        return success("客户信息更新成功");
    }
    @RequestMapping("delete")
    @ResponseBody
    public MessageModel delete(Integer[] ids) {
        customerService.delete(ids);
        return success("客户信息删除成功");
    }


}
