package com.lin.shiro.core.service;

import com.lin.shiro.core.entity.Book;
import com.lin.shiro.core.entity.Category;
import com.lin.shiro.core.entity.Order;
import com.lin.shiro.core.util.PageResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AdminServer  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public interface AdminServer {


    List<Category> getCategory();

    PageResult getBooks(String keyword , int page ,int classify_id , int limit);

    Book getOneBook(int b_id); //通过bookId得到一行book数据

    List<Order> findOrder(int page , int status , int limit , String desc);

    List<Order> findOrder(Map<String, Object> params);


}
