package com.eface.comm.base.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public abstract class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> {
    public PageInfo listPage(Integer pageNo, Integer pageSize) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        PageHelper.startPage(pageNo, pageSize);
        List list = list(queryWrapper);
        return new PageInfo(list, pageSize);
    }

}
