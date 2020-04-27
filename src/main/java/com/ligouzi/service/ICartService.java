package com.ligouzi.service;

import com.ligouzi.utils.ServerResponse;

public interface ICartService {

    public ServerResponse add(Integer productId,Integer count, Integer userId);

    public ServerResponse list(Integer userId);

    public ServerResponse update(Integer productId,Integer count, Integer userId);

}
