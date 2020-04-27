package com.ligouzi.service;

import com.ligouzi.pojo.Product;
import com.ligouzi.utils.ServerResponse;

public interface IProductService {

    public ServerResponse list(Integer categoryId,String keyword,Integer pageNum,Integer pageSize,String orderby);

    public ServerResponse findProductById(Integer productId);

}
