package com.ken.util;

import com.ken.bean.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

/**
 * Created by david on 2017/4/21.
 */
public class PasswordHelper {
    // private RandomNumberGenerator randomNumberGenerator = new
    // SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 1;

    public void encryptPassword(User user) {
        // String salt=randomNumberGenerator.nextBytes().toHex();
        String newPassword = new SimpleHash(algorithmName, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), hashIterations).toHex();
        // String newPassword = new SimpleHash(algorithmName,
        // user.getPassword()).toHex();
        user.setPassword(newPassword);
    }

    public static void main(String[] args) {
        String username = "admin";
        String password = "123456";
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println("salt:  " + salt);
        PasswordHelper passwordHelper = new PasswordHelper();
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setSalt(salt);
        passwordHelper.encryptPassword(user);
        System.out.println(user);
        System.out.println("password: " + user.getPassword());
    }
}
