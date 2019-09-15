package com.lin.shiro.core.config;

import com.lin.shiro.core.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * InitConfig  功能描述
 *
 * @Author Lin
 * @Description //TODO $ 系统初始化配置
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Component
public class InitConfig implements InitializingBean {

    //初始化user_id计数器
    public static AtomicInteger userIdCount = new AtomicInteger();

    @Autowired
    UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {

        String user_id = userService.getMaxUserId();
        int userId = Integer.valueOf(user_id) + 1;
        userIdCount.getAndSet(userId);
       // System.out.println("初始化完成INITConfig");

    }
}
