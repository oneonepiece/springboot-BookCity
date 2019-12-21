package com.lin.shiro.core.service.impl;

import com.lin.shiro.core.dao.BookMapper;
import com.lin.shiro.core.dao.CategoryMapper;
import com.lin.shiro.core.dao.ClassifyMapper;
import com.lin.shiro.core.dao.OrderMapper;
import com.lin.shiro.core.entity.Book;
import com.lin.shiro.core.entity.Category;
import com.lin.shiro.core.entity.Classify;
import com.lin.shiro.core.entity.Order;
import com.lin.shiro.core.service.AdminServer;
import com.lin.shiro.core.util.PageQueryUtil;
import com.lin.shiro.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AdminServerImpl  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Service
public class AdminServerImpl implements AdminServer {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ClassifyMapper classifyMapper;

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public PageResult getBooks(String keyword , int page , int classify_id , int limit) {

        Map param = new HashMap();
        param.put("page", page);
        //每页8条
        param.put("keyword", keyword);
        param.put("classify_id" , classify_id);
        param.put("limit", limit);
        param.put("status",1);
        PageQueryUtil pageUtil = new PageQueryUtil(param);
        List<Book> bookList = bookMapper.findBookList(pageUtil);
        if (bookList.size() == 0 )return null;

        int totalCount = bookMapper.getTotalBook(param);
        PageResult pageResult = new PageResult(bookList,totalCount,pageUtil.getLimit(),pageUtil.getPage());

        return pageResult;
    }


    @Override
    public List<Category> getCategory() {
        List<Category> categoryList = categoryMapper.getCategoryAll();
        if (categoryList.size() == 0) return null;
        for (int i=0; i < categoryList.size(); i++){
            Category category =  categoryList.get(i);
            ArrayList<Classify> classifies =  classifyMapper.getClassify(category.category_id);
            if (classifies.size() < 1){
                category.setClassifies(null);
            }
            category.setClassifies(classifies);
        }

        return categoryList;
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



    @Override
    public List<Order> findOrder(int page , int status , int limit , String desc) {

        Map param = new HashMap();
        param.put("page",page);
        if (limit < 0 ){
            param.put("limit",9);
        }else {
            param.put("limit",limit);
        }
        if (status >= 0 )
            param.put( "status" , status );
        if (desc != null)
            param.put("desc","desc");

        PageQueryUtil pageUtil = new PageQueryUtil(param);
        List<Order> orderList = orderMapper.findOrder(pageUtil);

        return orderList;
    }

    @Override
    public List<Order> findOrder(Map<String, Object> params) {

        int status = -1 , page = 1 , limit = 9 ;
        String desc = null;
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
           throw new NullPointerException("数组实体不存在");
        }
        if (!StringUtils.isEmpty(params.get("st"))){
            try {
                status = Integer.valueOf((String)params.get("st"));
            }catch (Exception e){
                throw new NullPointerException("数组实体不存在");
            }
        }
        if (!StringUtils.isEmpty(params.get("desc"))){
            desc = "desc";
        }
        try {
            page = Integer.valueOf((String)params.get("page"));
            limit = Integer.valueOf((String)params.get("limit"));
        }catch (Exception e){
            throw new NullPointerException("数组实体不存在");
        }
        return findOrder(page, status , limit , desc);
    }


}
