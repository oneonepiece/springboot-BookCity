package com.lin.shiro.core.service;

import com.lin.shiro.core.entity.Book;
import com.lin.shiro.core.entity.Category;
import com.lin.shiro.core.util.PageResult;

import java.util.List;

/**
 * BookService  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public interface IndexService {


    List<Category> getCategory();

    PageResult getBooks(String keyword , int page ,int classify_id , int limit);

    Book getOneBook(int b_id);
}
