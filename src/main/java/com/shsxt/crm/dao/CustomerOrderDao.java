package com.shsxt.crm.dao;

import com.shsxt.crm.dto.OrderDetail;
import com.shsxt.crm.vo.CustomerOrder;
import com.shsxt.crm.vo.OrderDetails;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerOrderDao {


    public List<CustomerOrder> queryOrderByCid(Integer cid);

    @Select("SELECT co.order_no as orderNo,co.order_date as orderDate,co.address,co.state,sum(od.sum) as sum FROM t_customer_order co left join t_order_details od on co.id = od.order_id where order_id = #{id} and co.is_valid = 1 and od.is_valid = 1")
    public OrderDetail queryOrderDetailById(Integer id);

    @Select("SELECT id,goods_name as goodsName,goods_num as goodsNum,unit,price,sum " +
            " FROM T_ORDER_DETAILS " +
            " WHERE order_id = #{id} and is_valid = 1")
    public List<OrderDetails> queryOrderDetailsById(Integer id);
}
