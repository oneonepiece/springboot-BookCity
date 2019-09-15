package com.lin.shiro.core.controller.index;

import com.lin.shiro.core.dao.BookMapper;
import com.lin.shiro.core.entity.Book;
import com.lin.shiro.core.entity.shiro.User;
import com.lin.shiro.core.service.IndexService;
import com.lin.shiro.core.util.PageResult;
import com.lin.shiro.core.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * IndexController  功能描述
 *
 * @Author Lin
 * @Description //TODO $ 前端不需要登录就可以获取的数据
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Controller
public class FreeDataController {
    @Autowired
    IndexService indexService;

    @Autowired
    BookMapper bookMapper;


    @RequestMapping({"/classify/{classifyId}"})
    public String getClassify (@PathVariable("classifyId") int classifyId  , HttpServletRequest request ){

        return getClassify(classifyId,1 ,request );
    }

    @RequestMapping({"/classify/{classifyId}/{pageNum}"})
    public String getClassify (@PathVariable("classifyId") int classifyId  ,
                               @PathVariable("pageNum") int pageNum ,
                               HttpServletRequest request ){

        PageResult bookPageResult = indexService.getBooks(null,pageNum,classifyId , 8);
        if (bookPageResult == null){
            return "/error/error404";
        }
        request.setAttribute("BookPageResult" , bookPageResult );
        request.setAttribute("categorys", indexService.getCategory());
        request.setAttribute("indexclass", "list-group-item ");
        request.setAttribute("pageUrl" , "classify/" + String.valueOf(classifyId));
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            request.setAttribute("user" , null);
        }else {
            String username = (String)subject.getPrincipal();
            request.setAttribute("user" , username);
        }

        return "/anyone/classify";
    }


    @RequestMapping({"/book/{bookid}"})
    public String getBookDetail (@PathVariable("bookid") int bookId ,
                               HttpServletRequest request ){

        Book book = indexService.getOneBook(bookId);
        if (book == null){
            return "/error/error404";
        }
        request.setAttribute("book" , book );
//        request.setAttribute("categorys", indexService.getCategory());
//        request.setAttribute("indexclass", "list-group-item ");
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            request.setAttribute("user" , null);
        }else {
            String username = (String)subject.getPrincipal();
            request.setAttribute("user" , username);
        }

        return "/anyone/detail";
    }




//http://127.0.0.1:8082/search?keyword=%E6%96%97%E7%BD%97&page=1
    @RequestMapping({"/search"})
    public String getSearch (HttpServletRequest request,
                             @RequestParam(value = "keyword") String keyword  ,
                             @RequestParam(value = "page") int pageNum
                                ){

//        String key =null;
//        try {
//            key = new String(keyword.toString().getBytes("ISO-8859-1"),"utf-8");
//        }catch (Exception e){
//            return "/error/error404";
//        }

        PageResult bookPageResult = indexService.getBooks(keyword,pageNum,-1 , 8);
        if (bookPageResult == null){
            return "/error/error404";
        }
        request.setAttribute("BookPageResult" , bookPageResult );
//        request.setAttribute("categorys", indexService.getCategory());
//        request.setAttribute("indexclass", "list-group-item ");
        request.setAttribute("pageUrl" , "search?keyword=" + keyword + "&page=");
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            request.setAttribute("user" , null);
        }else {
            String username = (String)subject.getPrincipal();
            request.setAttribute("user" , username);
        }

        return "/anyone/search";
    }


//    Book book1 = new Book();
//        book1.setAuthor("Alin");
//        book1.setB_name("斗罗大陆");
//        book1.setBook_num("100100100");
//        book1.setPrice(55.5);
//        book1.setCurr_price(60.5);
//        book1.setDiscount(9.5);
//        book1.setPress("中国邮电出版社");
//        book1.setPublish_time(new Date());
//        book1.setEdition(1);
//        book1.setPage_num(325);
//        book1.setWord_num(100000);
//        book1.setPrint_time(new Date());
//        book1.setBook_size(16);
//        book1.setPaper("辣鸡纸");
//        book1.setImage_uri("/demo/img/1.png");
//        book1.setClassify_id(7);
//        book1.setIsbn("123654");
//        book1.setStatus(0); //上架0 下架1
//        boos.add(book1);




}
