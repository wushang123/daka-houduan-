package com.ligouzi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ligouzi.common.ResponseCode;
import com.ligouzi.dao.ProductMapper;
import com.ligouzi.pojo.Product;
import com.ligouzi.service.IProductService;
import com.ligouzi.utils.ServerResponse;
import com.ligouzi.vo.ProductListVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;
    @Override
    public ServerResponse list(Integer categoryId, String keyword, Integer pageNum, Integer pageSize, String orderby) {

        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(orderby)) {
            String[] orders = orderby.split("_");
            if(orders.length!=2){
                return ServerResponse.createServerResponseByFail(ResponseCode.ILLEGAL_PARAM.getCode(),ResponseCode.ILLEGAL_PARAM.getMsg());
            }
            PageHelper.orderBy(orders[0] + " " + orders[1]);
        }
        if(!StringUtils.isBlank(keyword)){
            keyword="%"+keyword+"%";
        }
        List<Product> productList=productMapper.list(categoryId,keyword);
        List<ProductListVO> productListVOList=new ArrayList<>();
        for (Product product:productList){
            ProductListVO productListVO=product2ProductListVO(product);
            productListVOList.add(productListVO);
        }
        PageInfo pageInfo=new PageInfo(productList);
        pageInfo.setList(productListVOList);
        return ServerResponse.createServerResponseBySuccess(pageInfo);
    }

    @Override
    public ServerResponse findProductById(Integer productId) {
        if(productId==null||productId==0){
            return ServerResponse.createServerResponseByFail(ResponseCode.ILLEGAL_PARAM.getCode(),ResponseCode.ILLEGAL_PARAM.getMsg());
        }
        Product product=productMapper.selectByPrimaryKey(productId);
        if(product==null){
            return ServerResponse.createServerResponseByFail(ResponseCode.PRODUCT_NOT_EXISTS.getCode(),ResponseCode.PRODUCT_NOT_EXISTS.getMsg());
        }
        return ServerResponse.createServerResponseBySuccess(product);
    }

    private ProductListVO product2ProductListVO(Product product){

        ProductListVO productListVO=new ProductListVO();
        productListVO.setId(product.getId());
        productListVO.setName(product.getName());
        productListVO.setSubtitle(product.getSubtitle());
        productListVO.setCategoryId(product.getCategoryId());
        productListVO.setMainImage(product.getMainImage());
        productListVO.setPrice(product.getPrice());
        productListVO.setStatus(product.getStatus());
        return productListVO;

    }

}
