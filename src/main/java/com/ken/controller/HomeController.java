package com.ken.controller;

import com.ken.exception.ResultException;
import com.ken.service.UserService;
import com.ken.util.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(String userName, String password) {

        //检查用户状态
        Integer userState = userService.getUserState(userName);
        if (Constants.ENABLE_STATE != userState.intValue()) {
            throw new ResultException("该用户已锁定");
        }

        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) { // 没有指定的账户
                uae.printStackTrace();
            } catch (IncorrectCredentialsException ice) {// 账户存在，密码不匹配
                ice.printStackTrace();
            } catch (LockedAccountException lae) {//用户被锁定
                lae.printStackTrace();
            } catch (AuthenticationException ae) {//所有认证时异常的父类
                ae.printStackTrace();
            }
        }
        return "login success";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/notLogin")
    public String notLogin() {
        return "该账号未登录";
    }
}
