package com.shsxt.crm.dao;


import com.shsxt.crm.vo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {

    @Select("SELECT id, user_name AS userName, user_pwd AS userPwd, true_name AS trueName " +
            ", phone, email, is_valid AS isValid, create_date AS createDate " +
            ", update_date AS updateDate" +
            " FROM T_USER WHERE USER_NAME = #{userName} and is_valid = '1' ")
    public User selectByUserName(String userName);


    public User selectUserById(@Param("userId") String id);

    public Integer updateUserPwd(@Param("userId") String userId, @Param("newPwd") String newPwd);

    @Select("SELECT U.ID , U.TRUE_NAME AS trueName\n" +
            "FROM T_USER U \n" +
            "LEFT JOIN T_USER_ROLE R ON U.ID = R.USER_ID \n" +
            "LEFT JOIN T_ROLE E ON R.ROLE_ID = E.ID\n" +
            "WHERE U.IS_VALID = '1' AND E.ID = '1'")
    public List<User> queryAllCustomerManager();
}
