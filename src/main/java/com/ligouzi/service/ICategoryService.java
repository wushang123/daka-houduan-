package com.ligouzi.service;

import com.ligouzi.utils.ServerResponse;

public interface ICategoryService {

    public ServerResponse findSubCategoryLogic(Integer categoryId);

    /**
     * 查询categoryId下的所有子类
     */
    public ServerResponse findAllChildCategoryLogic(Integer categoryId);

}
