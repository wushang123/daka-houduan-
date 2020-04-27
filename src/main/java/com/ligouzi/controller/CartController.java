package com.ligouzi.controller;

import com.ligouzi.common.Const;
import com.ligouzi.service.ICartService;
import com.ligouzi.utils.ServerResponse;
import com.ligouzi.vo.UserInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal/cart/")
public class CartController {

    @Autowired
    ICartService cartService;

    /**
     * 商品添加到购物车
     */
    @RequestMapping("{productId}/{count}")
    public ServerResponse add(@PathVariable("productId") Integer productId, @PathVariable("count") Integer count, HttpSession session){
        UserInfoVO userInfoVO=(UserInfoVO) session.getAttribute(Const.CURRENT_USER);
        return cartService.add(productId,count, userInfoVO.getId());
    }

    /**
     * 获取购物车列表
     */
    @RequestMapping("list.do")
    public ServerResponse list(HttpSession session){
        UserInfoVO userInfoVO=(UserInfoVO) session.getAttribute(Const.CURRENT_USER);
        return cartService.list(userInfoVO.getId());
    }

    /**
     * 更新购物车某个产品数量
     */
    @RequestMapping("update.do")
    public ServerResponse update(Integer productId, Integer count, HttpSession session){
        UserInfoVO userInfoVO=(UserInfoVO) session.getAttribute(Const.CURRENT_USER);
        return cartService.update(productId,count, userInfoVO.getId());
    }

}
