package com.ken.service;

import com.github.pagehelper.PageInfo;
import com.ken.bean.User;
import com.ken.model.PageParam;

public interface UserService extends IService<User> {

    PageInfo<User> page(User user, PageParam pageParam);
}
