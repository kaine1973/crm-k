package com.shsxt.crm.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.dao.CustomerDao;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.vo.Customer;
import com.shsxt.crm.vo.CustomerOrder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    @Resource
    private CustomerDao customerDao;

    public List<Customer> queryAllCustomers() {
        return customerDao.queryAllCustomers();
    }

    public Map<String, Object> queryCustomersByParams(CustomerQuery customerQuery) {
        PageHelper.startPage(customerQuery.getPage(), customerQuery.getRows());
        List<Customer> customers = customerDao.queryCustomersByParams(customerQuery);
        PageInfo pi = new PageInfo(customers);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", pi.getList());
        map.put("total", pi.getTotal());
        return map;
    }

    public void insert(Customer customer) {
        checkParams(customer.getName(), customer.getFr(), customer.getPhone());
        customer.setState(0);
        customer.setIsValid(1);
        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());
        AssertUtil.isTrue(customerDao.insert(customer) < 1, "客户信息添加失败");
    }


    public void checkParams(String customerName, String fr, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName), "客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(fr), "法人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "联系电话不能为空");
    }

    public void update(Customer customer) {
        checkParams(customer.getName(), customer.getFr(), customer.getPhone());
        customer.setState(0);
        customer.setIsValid(1);
        customer.setUpdateDate(new Date());
        AssertUtil.isTrue(customerDao.update(customer) < 1, "客户信息更新失败");
    }

    public void delete(Integer[] ids) {
        AssertUtil.isTrue(customerDao.delete(ids) < ids.length,"客户信息删除失败");
    }

    public Customer queryCusById(Integer cid) {
        Customer customer = customerDao.queryCusById(cid);
        AssertUtil.isTrue(customer==null,"找不到该客户");
        return customer;
    }

}
