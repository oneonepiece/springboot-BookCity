package com.lin.shiro.core.service;

import com.lin.shiro.core.entity.Book;

import java.util.HashMap;

/**
 * AdmininsertServer  功能描述
 *
 * @Author Lin
 * @Description //TODO $  管理员插入数据到数据库接口
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public interface AdminInsertServer {

    int insertBook (Book book);
    int updataBook(HashMap hashMap);
}
