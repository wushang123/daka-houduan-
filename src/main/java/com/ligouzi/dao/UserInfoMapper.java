package com.ligouzi.dao;

import com.ligouzi.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table daka_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table daka_user
     *
     * @mbg.generated
     */
    int insert(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table daka_user
     *
     * @mbg.generated
     */
    UserInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table daka_user
     *
     * @mbg.generated
     */
    List<UserInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table daka_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(@Param("userInfo") UserInfo record);

    /**
     * 判断用户名是否存在
     */
    Integer findByUsername(@Param("username") String username);

    /**
     * 根据用户名和密码查询
     */
    UserInfo findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    /**
     * 判断邮箱是否存在
     */
    Integer findByEmail(@Param("email") String email);
}