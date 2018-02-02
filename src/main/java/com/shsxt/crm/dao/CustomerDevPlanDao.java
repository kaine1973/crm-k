package com.shsxt.crm.dao;

import com.shsxt.crm.query.CusDevPlanQuery;
import com.shsxt.crm.vo.CusDevPlan;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CustomerDevPlanDao {

    public List<CusDevPlan> queryDevPlan(CusDevPlanQuery cusDevPlanQuery);

    public Integer insert(CusDevPlan cusDevPlan);

    @Update("update t_cus_dev_plan set is_valid = 0 , update_date = sysdate() where id = #{id }")
    public Integer delete(Integer id);


    public Integer update(CusDevPlan cusDevPlan);
}
