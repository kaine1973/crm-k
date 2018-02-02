package com.shsxt.crm.dao;

import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.vo.SaleChance;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SaleChanceDao {


    public List<SaleChance> querySaleChancesByParams(SaleChanceQuery saleChanceQuery);

    public SaleChance querySaleChancesById(String id);

    public Integer saveSaleChance(SaleChance saleChance);

    public Integer editSaleChance(SaleChance saleChance);

    public Integer delete(Integer[] ids);

    @Update("update t_sale_chance set dev_result = #{devResult } where id = #{id }")
    public Integer updateDevResult(@Param("devResult") Integer devResult, @Param("id") Integer id);
}
