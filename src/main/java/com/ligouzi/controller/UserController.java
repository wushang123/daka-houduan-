package com.ligouzi.controller;

import com.ligouzi.common.Const;
import com.ligouzi.common.ResponseCode;
import com.ligouzi.pojo.UserInfo;
import com.ligouzi.service.IUserService;
import com.ligouzi.utils.ServerResponse;
import com.ligouzi.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal/")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "user/login.do")
    public ServerResponse login(String username, String password, HttpSession session){

        ServerResponse serverResponse=userService.loginLogic(username,password);
        if(serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        }
        return serverResponse;

    }

    @RequestMapping("user/register.do")
    public ServerResponse register(UserInfo userInfo){

        return userService.registerLogic(userInfo);

    }

    @RequestMapping("user/update.do")
    public ServerResponse UpdateUser(UserInfo userInfo, HttpSession session){

        //判断用户是否登录
        UserInfoVO userInfoVO=(UserInfoVO) session.getAttribute(Const.CURRENT_USER);
//        if (userInfoVO==null){
//            return ServerResponse.createServerResponseByFail(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
//        }
//        if(userInfo==null){
//            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMTER_NOT_EXIST.getCode(),ResponseCode.PARAMTER_NOT_EXIST.getMsg());
//        }
        userInfo.setId(userInfoVO.getId());
        ServerResponse serverResponse= userService.updateUserLogic(userInfo);
        if(serverResponse.isSuccess()){//更新session中的用户信息
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        }
        return serverResponse;
    }

    @RequestMapping("user/logout.do")
    public ServerResponse logout(HttpSession session){
        ServerResponse serverResponse=userService.logoutLogic(session);
        return serverResponse;
    }

}
