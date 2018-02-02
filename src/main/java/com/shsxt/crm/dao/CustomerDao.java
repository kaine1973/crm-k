package com.shsxt.crm.dao;

import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.vo.Customer;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerDao {

    public List<Customer> queryAllCustomers();

    public List<Customer> queryCustomersByParams(CustomerQuery customerQuery);

    public Integer insert(Customer customer);

    public Integer update(Customer customer);

    public Integer delete(Integer[] ids);

    public Customer queryCusById(Integer cid);
}
