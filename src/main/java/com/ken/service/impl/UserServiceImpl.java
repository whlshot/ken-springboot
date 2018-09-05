package com.ken.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ken.bean.User;
import com.ken.mapper.UserMapper;
import com.ken.model.PageParam;
import com.ken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> page(User user, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageIndex(), pageParam.getPageSize());
        PageInfo<User> pageInfo = new PageInfo<>();
        return null;
    }

    @Override
    public User saveUser(User user) {
        userMapper.insert(user);
        return user;
    }

}
