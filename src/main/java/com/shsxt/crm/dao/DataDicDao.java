package com.shsxt.crm.dao;


import com.shsxt.crm.dto.CusLevel;
import com.shsxt.crm.dto.ServeType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DataDicDao {

    @Select("select id,data_dic_value as level,is_valid as isValid,create_date as createDate, update_date as updateDate from t_datadic where is_valid = 1 and data_dic_name = '客户等级'")
    public List<CusLevel> queryAllLevel();

    @Select("select id,data_dic_value as level,is_valid as isValid,create_date as createDate, update_date as updateDate from t_datadic where is_valid = 1 and data_dic_name = '服务类型'")
    public List<ServeType> queryAllServe();

}
