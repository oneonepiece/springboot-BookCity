package com.lin.shiro.core.dao;


import com.lin.shiro.core.entity.shiro.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * UserMapper  功能描述
 *
 * @Author Lin
 * @Description //TODO $  用户
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Component
public interface UserMapper {

//    List<Student> selectStudentByKey(@Param("start") Integer start, @Param("limit") Integer limit);
//
//    Student selectStudentBYstuid(int stuid);
//
//    List<FreeTime> selectFreeTimeBYstuid(int stuid);


    User findByUsername(@Param("username")String username);// 根据用户名查找用户

    int createUser(User user); //创建用户

    String getMaxUserId();

//    int createUser(User user);

}
