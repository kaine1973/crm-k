package com.shsxt.crm.service;

import com.shsxt.crm.dao.CustomerOrderDao;
import com.shsxt.crm.dto.OrderDetail;
import com.shsxt.crm.query.CusOrderQuery;
import com.shsxt.crm.utils.AssertUtil;
import com.shsxt.crm.utils.PageUtil;
import com.shsxt.crm.vo.CustomerOrder;
import com.shsxt.crm.vo.OrderDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CustomerOrderService {


    @Resource
    private CustomerOrderDao customerOrderDao;

    public Map<String,Object> queryOrdersByCid(CusOrderQuery cusOrderQuery) {
        List<CustomerOrder> list = customerOrderDao.queryOrderByCid(cusOrderQuery.getCid());
        Map pagedMap = PageUtil.getPagedMap(cusOrderQuery, list);
        return pagedMap;
    }

    public OrderDetail queryOrderDetailById(Integer id) {
        OrderDetail orderDetail = customerOrderDao.queryOrderDetailById(id);
        AssertUtil.isTrue(orderDetail==null,"找不到该订单信息");
        return orderDetail;
    }

    public Map<String,Object> queryOrderDetailsById(CusOrderQuery cusOrderQuery) {
        List<OrderDetails> list = customerOrderDao.queryOrderDetailsById(cusOrderQuery.getOrderId());
        Map pagedMap = PageUtil.getPagedMap(cusOrderQuery,list);
        return pagedMap;
    }
}
