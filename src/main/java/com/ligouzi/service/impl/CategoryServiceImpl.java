package com.ligouzi.service.impl;

import com.ligouzi.common.ResponseCode;
import com.ligouzi.dao.CategoryMapper;
import com.ligouzi.pojo.Category;
import com.ligouzi.service.ICategoryService;
import com.ligouzi.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public ServerResponse findSubCategoryLogic(Integer categoryId) {
        if(categoryId==null){
            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMTER_NOT_EXIST.getCode(),ResponseCode.PARAMTER_NOT_EXIST.getMsg());
        }
        List<Category> categoryList=categoryMapper.findSubCategoryById(categoryId);
        return ServerResponse.createServerResponseBySuccess(categoryList);
    }

    @Override
    public ServerResponse findAllChildCategoryLogic(Integer categoryId) {
        if(categoryId==null){
            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMTER_NOT_EXIST.getCode(),ResponseCode.PARAMTER_NOT_EXIST.getMsg());
        }
        Set<Category> set=new HashSet<>();
        Set<Category> categorySet=findAllChild(set,categoryId);
        return ServerResponse.createServerResponseBySuccess(categorySet);
    }

    private Set<Category> findAllChild(Set<Category> categorySet,Integer categoryId){

        //step1:根据categoryId查询
        Category category=categoryMapper.selectByPrimaryKey(categoryId);
        if(category!=null){
            categorySet.add(category);
        }
        //step2:查询category下的所有一级子类别
        List<Category> categoryList=categoryMapper.findSubCategoryById(categoryId);
        for(Category category1:categoryList){
            findAllChild(categorySet,category1.getId());
        }
        return categorySet;

    }

}
