package com.ligouzi.service;

import com.ligouzi.pojo.UserInfo;
import com.ligouzi.utils.ServerResponse;

import javax.servlet.http.HttpSession;

public interface IUserService {

    /**
     * 登录
     */
    public ServerResponse loginLogic(String username, String password);

    /**
     * 注册
     */
    public ServerResponse registerLogic(UserInfo userInfo);

    /**
     * 修改用户信息
     */
    public ServerResponse updateUserLogic(UserInfo userInfo);

    /**
     * 退出登录
     */
    public ServerResponse logoutLogic(HttpSession session);

}
