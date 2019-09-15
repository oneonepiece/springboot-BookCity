package com.lin.shiro.core.service.impl;

import com.lin.shiro.core.dao.BookMapper;
import com.lin.shiro.core.dao.CategoryMapper;
import com.lin.shiro.core.dao.ClassifyMapper;
import com.lin.shiro.core.entity.Book;
import com.lin.shiro.core.entity.Category;
import com.lin.shiro.core.entity.Classify;
import com.lin.shiro.core.service.IndexService;
import com.lin.shiro.core.util.PageQueryUtil;
import com.lin.shiro.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IndexServiceImpl  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ClassifyMapper classifyMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Category> getCategory() {
        List<Category> categoryList = categoryMapper.getCategoryAll();
        for (int i=0; i < categoryList.size(); i++){
            Category category =  categoryList.get(i);
            Classify[] classifies =  classifyMapper.getClassify(category.category_id);
            if (classifies.length > 0){
                category.setClassifies(classifies);
            }
        }

        return categoryList;
    }

    //classify_id大于0 触发
    @Override
    public PageResult getBooks(String keyword , int page , int classify_id , int limit) {

        Map param = new HashMap();
        param.put("page", page);
        //每页8条
        param.put("keyword", keyword);
        param.put("classify_id" , classify_id);
        param.put("limit", limit);
        PageQueryUtil pageUtil = new PageQueryUtil(param);
        List<Book> bookList = bookMapper.findBookList(pageUtil);
        if (bookList.size() == 0 )return null;

        int totalCount = bookMapper.getTotalBook(param);
        PageResult pageResult = new PageResult(bookList,totalCount,pageUtil.getLimit(),pageUtil.getPage());

        return pageResult;
    }

    @Override
    public Book getOneBook(int b_id) {
        try {
            Book book = bookMapper.getOneBook(b_id);
            return  book;
        }catch (Exception e){
            return null;
        }
    }


}
