package com.ligouzi.service.impl;

import com.ligouzi.common.CheckEnum;
import com.ligouzi.common.ResponseCode;
import com.ligouzi.dao.CartMapper;
import com.ligouzi.pojo.Cart;
import com.ligouzi.pojo.Product;
import com.ligouzi.service.ICartService;
import com.ligouzi.service.IProductService;
import com.ligouzi.utils.BigDecimalUtil;
import com.ligouzi.utils.ServerResponse;
import com.ligouzi.vo.CartProductVO;
import com.ligouzi.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartServiceImpl implements ICartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    IProductService productService;

    @Override
    public ServerResponse add(Integer productId, Integer count, Integer userId) {
        if(productId==null||count==null){
            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMTER_NOT_EXIST.getCode(),ResponseCode.PARAMTER_NOT_EXIST.getMsg());
        }
        //查询购物车中是否有该商品
        Cart cart=cartMapper.findCartByProductId(productId);
        int result;
        if(cart!=null){
            //更新数量
            cart.setQuantity(cart.getQuantity()+count);
            result=cartMapper.updateByProductId(cart.getProductId(),cart.getQuantity());
            if(result==0){
                return ServerResponse.createServerResponseByFail(ResponseCode.CART_UPDATE_FAIL.getCode(),ResponseCode.CART_UPDATE_FAIL.getMsg());
            }
        }else{
            //添加
            Cart insertCart=new Cart();
            insertCart.setProductId(productId);
            insertCart.setQuantity(count);
            insertCart.setChecked(CheckEnum.CART_PRODUCT_CHECKED.getCheck());
            insertCart.setUserId(userId);
            result=cartMapper.insert(insertCart);
            if(result<=0){
                //添加失败
                return ServerResponse.createServerResponseByFail(ResponseCode.CART_ADD_FAIL.getCode(),ResponseCode.CART_ADD_FAIL.getMsg());
            }
        }

        //CartVO
        CartVO cartVO=getCartVO(userId);

        return ServerResponse.createServerResponseBySuccess(cartVO);
    }

    @Override
    public ServerResponse list(Integer userId) {
        return ServerResponse.createServerResponseBySuccess(getCartVO(userId));
    }

    @Override
    public ServerResponse update(Integer productId, Integer count, Integer userId) {
        //商品信息已存在，在原有基础上更新数据
        if(productId==null||count==null){
            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMTER_NOT_EXIST.getCode(),ResponseCode.PARAMTER_NOT_EXIST.getMsg());
        }
        //查询购物车中是否有该商品
        Cart cart=cartMapper.findCartByProductId(productId);
        int result;
        if(cart!=null){
            //更新数量
            cart.setQuantity(cart.getQuantity()+count);
            result=cartMapper.updateByProductId(cart.getProductId(),cart.getQuantity());
            if(result==0){
                return ServerResponse.createServerResponseByFail(ResponseCode.CART_UPDATE_FAIL.getCode(),ResponseCode.CART_UPDATE_FAIL.getMsg());
            }
        }else{
            //返回商品不存在信息
            return ServerResponse.createServerResponseByFail(ResponseCode.PRODUCT_NOT_EXISTS.getCode(),ResponseCode.PRODUCT_NOT_EXISTS.getMsg());
        }

        //CartVO
        CartVO cartVO=getCartVO(userId);

        return ServerResponse.createServerResponseBySuccess(cartVO);
    }

    public CartVO getCartVO(Integer userId){
        CartVO cartVO=new CartVO();
        //step1:查询购物车信息
        List<Cart> cartList=cartMapper.selectAllByUserId(userId);
        if(cartList==null||cartList.size()==0){
            return cartVO;
        }
        List<CartProductVO> cartProductVOList=new ArrayList<>();
        BigDecimal cartTotalPrice=new BigDecimal("0");
        //step2:便利购物车，根据商品id查询商品信息
        for(Cart cart:cartList){
            CartProductVO cartProductVO=new CartProductVO();
            cartProductVO.setId(cart.getId());
            cartProductVO.setQuantity(cart.getQuantity());
            cartProductVO.setProductChecked(cart.getProductId());
            cartProductVO.setUserId(cart.getUserId());

            //根据商品id查询商品信息
            ServerResponse serverResponse=productService.findProductById(cart.getProductId());
            if(!serverResponse.isSuccess()){
                //将不存在的商品移除
                cartMapper.deleteByPrimaryKey(cart.getId());
            }
            Product product=(Product)serverResponse.getData();
            cartProductVO.setProductId(cart.getProductId());
            cartProductVO.setProductMainImage(product.getMainImage());
            cartProductVO.setProductName(product.getName());
            cartProductVO.setProductPrice(product.getPrice());
            cartProductVO.setProductStatus(product.getStatus());
            int stock=product.getStock();
            cartProductVO.setProductStock(stock);
            cartProductVO.setProductSubtitle(product.getSubtitle());
            if(stock>cart.getQuantity()){
                cartProductVO.setLimitQuantity("LIMIT_NUM_SUCCESS");
            }else {
                cartProductVO.setLimitQuantity("LIMIT_NUM_FAIL");
                //更新数量
                cartMapper.updateByProductId(product.getId(),product.getStock());
            }
            //quantity*price
            //计算总的价格
            cartProductVO.setProductTotalPrice(BigDecimalUtil.multi(String.valueOf(product.getPrice().doubleValue()),String.valueOf(cart.getQuantity())));
            if (cart.getChecked()==CheckEnum.CART_PRODUCT_CHECKED.getCheck()){
                //被选中
                cartTotalPrice=BigDecimalUtil.add(String.valueOf(cartTotalPrice.doubleValue()),String.valueOf(cartProductVO.getProductTotalPrice().doubleValue()));
            }
            cartProductVOList.add(cartProductVO);
        }

        cartVO.setCartProductVOList(cartProductVOList);
        //判断用户是否全选
        int unCheckedCount=cartMapper.unCheckedCount();
        if(unCheckedCount==0){
            cartVO.setAllChecked(true);
        }else{
            cartVO.setAllChecked(false);
        }
        //计算总价格
        cartVO.setCartTotalPrice(cartTotalPrice);

        return cartVO;
    }

}
