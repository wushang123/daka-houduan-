package com.ligouzi.service.impl;

//登录注册时用户名密码不能为空或长度为0或有空格、tab制表符
//注册时邮箱电话密保也不能

import com.ligouzi.common.Const;
import com.ligouzi.common.ResponseCode;
import com.ligouzi.dao.UserInfoMapper;
import com.ligouzi.pojo.UserInfo;
import com.ligouzi.service.IUserService;
import com.ligouzi.utils.DateUtil;
import com.ligouzi.utils.MD5Utils;
import com.ligouzi.utils.ServerResponse;
import com.ligouzi.vo.UserInfoVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class UserService implements IUserService {

    private UserInfoMapper userInfoMapper;

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper){
        this.userInfoMapper=userInfoMapper;
    }

    @Override
    public ServerResponse loginLogic(String username, String password){

        //StringUtils.isEmpty("");判断字符串是否为空或长度为0
        //StringUtils.isBlank("")；判断字符串是否为空或长度为0或是否有空格、tab制表符
        //step1:用户名和密码的非空判断
        if(StringUtils.isBlank(username)){
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EMPTY.getCode(),ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(password)){
            return ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(),ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }
        //step2:查看用户名是否存在
        Integer count=userInfoMapper.findByUsername(username);
        if(count==0){
            //用户名不存在
            return  ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EXIST.getCode(),ResponseCode.USERNAME_NOT_EXIST.getMsg());
        }
        //step3:根据用户名和密码查询
        UserInfo userInfo=userInfoMapper.findByUsernameAndPassword(username, MD5Utils.getMD5Code(password));
        if(userInfo==null){
            return ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_ERROR.getCode(),ResponseCode.PASSWORD_ERROR.getMsg());
        }
        //step4:返回结果
        return ServerResponse.createServerResponseBySuccess(convert(userInfo));
    }

    private UserInfoVO convert(UserInfo userInfo){

        UserInfoVO userInfoVO=new UserInfoVO();
        userInfoVO.setId(userInfo.getId());
        userInfoVO.setUsername(userInfo.getUsername());
        userInfoVO.setEmail(userInfo.getEmail());
        userInfoVO.setPhone(userInfo.getPhone());
        userInfoVO.setCreateTime(DateUtil.date2String(userInfo.getCreateTime()));
        userInfoVO.setUpdateTime(DateUtil.date2String(userInfo.getUpdateTime()));
        return userInfoVO;

    }

    @Override
    public ServerResponse registerLogic(UserInfo userInfo) {

        if(userInfo==null){
            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMTER_NOT_EXIST.getCode(),ResponseCode.PARAMTER_NOT_EXIST.getMsg());
        }

        String username=userInfo.getUsername();
        String password=userInfo.getPassword();
        String email=userInfo.getEmail();
        String question=userInfo.getQuestion();
        String answer=userInfo.getAnswer();
        String phone=userInfo.getPhone();

        //step1:判断用户信息是否为空
        if(StringUtils.isBlank(username)){
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EMPTY.getCode(),ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(password)){
            return ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(),ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(email)){
            return ServerResponse.createServerResponseByFail(ResponseCode.EMAIL_NOT_EMPTY.getCode(),ResponseCode.EMAIL_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(question)){
            return ServerResponse.createServerResponseByFail(ResponseCode.QUESTION_NOT_EMPTY.getCode(),ResponseCode.QUESTION_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(answer)){
            return ServerResponse.createServerResponseByFail(ResponseCode.ANSWER_NOT_EMPTY.getCode(),ResponseCode.ANSWER_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(phone)){
            return ServerResponse.createServerResponseByFail(ResponseCode.PHONE_NOT_EMPTY.getCode(),ResponseCode.PHONE_NOT_EMPTY.getMsg());
        }

        //step2:判断用户名是否存在，若存在则注册失败
        Integer count=userInfoMapper.findByUsername(username);
        if(count>0){
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_EXIST.getCode(),ResponseCode.USERNAME_EXIST.getMsg());
        }

        //step3:判断邮箱是否存在，若存在则注册失败
        Integer email_count=userInfoMapper.findByEmail(email);
        if(email_count>0){
            return ServerResponse.createServerResponseByFail(ResponseCode.EMAIL_EXIST.getCode(),ResponseCode.EMAIL_EXIST.getMsg());
        }

        //step4:注册
        userInfo.setPassword(MD5Utils.getMD5Code(userInfo.getPassword()));
        userInfo.setRole(Const.NORMAL_USER);//设置用户的角色
        Integer result=userInfoMapper.insert(userInfo);
        if(result==0){
            //注册失败
            return ServerResponse.createServerResponseByFail(ResponseCode.REGISTER_FAIL.getCode(),ResponseCode.REGISTER_FAIL.getMsg());
        }
        return ServerResponse.createServerResponseBySuccess();
    }

    @Override
    public ServerResponse updateUserLogic(UserInfo userInfo){

        int count=userInfoMapper.updateByPrimaryKey(userInfo);
        if(count==0){
            //修改数据库失败
            return ServerResponse.createServerResponseByFail(ResponseCode.USERINFO_UPDATE_FALL.getCode(),ResponseCode.USERINFO_UPDATE_FALL.getMsg());
        }

        //根据id查询
        UserInfo newuserInfo=userInfoMapper.selectByPrimaryKey(userInfo.getId());
        UserInfoVO userInfoVO=convert(newuserInfo);

        return ServerResponse.createServerResponseBySuccess(userInfoVO);

    }

}
