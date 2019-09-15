package com.lin.shiro.core.realm;

import com.lin.shiro.core.entity.shiro.User;
import com.lin.shiro.core.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomSecurityRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    public CustomSecurityRealm() {
        this(new AllowAllCredentialsMatcher());
    }

    public CustomSecurityRealm(final CredentialsMatcher matcher) {
        super(matcher);
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //在数据库中查询用户拥有的角色/权限
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }


    /**
     * 验证(比如登录验证),这里的验证会交给ShiroConfig里面定义的bean: HashedCredentialsMatcher 处理
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken token) throws AuthenticationException {
        //用户的token
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if(user == null){
            throw new UnknownAccountException(); //没找到账号
        }
        //1真0假 ,1有效 ,0 无效
        if(Boolean.TRUE.equals(user.getStatus() == 0)){
            throw new LockedAccountException(); //账号被锁定
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSaltAndUsername()), // 盐 salt pp   = username+salt
                getName()); //realm name
        return authenticationInfo;
    }

//    生成的相应数据组装为 SimpleAuthenticationInfo，
//    通过 SimpleAuthenticationInfo 的 credentialsSalt 设置盐，
//    HashedCredentialsMatcher 会自动识别这个盐。

}