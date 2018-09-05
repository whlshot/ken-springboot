package com.ken.controller;

import com.github.pagehelper.PageInfo;
import com.ken.bean.User;
import com.ken.model.PageParam;
import com.ken.model.ResultInfo;
import com.ken.service.UserService;
import com.ken.util.CommonUtil;
import com.ken.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable int id) {
        return null;
    }

    @PostMapping("/page/{pageIndex}/{pageSize}")
    public ResultInfo<User> pageUser(@RequestBody User user, @PathVariable int pageIndex, @PathVariable int pageSize) {
        PageParam pageParam = CommonUtil.pageParam(pageIndex, pageSize);
        PageInfo<User> page = userService.page(user, pageParam);
        return ResultUtil.success(null);
    }

}
