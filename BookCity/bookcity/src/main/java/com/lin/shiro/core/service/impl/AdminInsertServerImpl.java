package com.lin.shiro.core.service.impl;

import com.lin.shiro.core.dao.BookMapper;
import com.lin.shiro.core.entity.Book;
import com.lin.shiro.core.service.AdminInsertServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * AdmininsertServerImpl  功能描述
 *
 * @Author Lin
 * @Description //TODO $管理员插入数据到数据库实现类
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Service
public class AdminInsertServerImpl implements AdminInsertServer {

    @Autowired
    BookMapper bookMapper;

    @Override
    public int insertBook (Book book){
        return bookMapper.insertBook(book);
    }


    @Override
    public int updataBook(HashMap hashMap){

        return bookMapper.updataBook(hashMap);
    }

}
