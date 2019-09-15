package com.lin.shiro.core.util.shiro;

import com.lin.shiro.core.entity.shiro.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * PasswordHelper  功能描述
 *
 * @Author Lin
 * @Description //TODO $   对密码加密, 加密算法
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    //这些要与Realm中的一致
    private static String algorithmName = "md5";
    private final static int hashIterations = 2;  //哈希迭代

    static public  void encryptPassword(User user) {
        //加盐
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
//                ByteSource.Util.bytes(user.getSaltAndUsername()),// ?? user.getCredentialsSalt() 辅助方法返回 username+salt。
                user.getSaltAndUsername(),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }

//    String algorithmName = "md5";
//    String username = "liu";
//    String password = "123";
//    String salt1 = username;
//    String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
//    int hashIterations = 2;
//    SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
//    String encodedPassword = hash.toHex();&nbsp;






}
