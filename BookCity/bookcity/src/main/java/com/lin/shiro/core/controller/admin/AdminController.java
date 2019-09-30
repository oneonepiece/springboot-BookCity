package com.lin.shiro.core.controller.admin;

import com.lin.shiro.core.entity.shiro.User;
import com.lin.shiro.core.service.IndexService;
import com.lin.shiro.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * AdminController  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    IndexService indexService;


    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
            return "error/error404";
        }
//        //开发避免每次登陆,在访问index的时候就给其登陆先
//        UsernamePasswordToken token = new UsernamePasswordToken("user1","123");
//        //认证登录
//        subject.login(token);
//        User user = userService.findByUsername("user1");
//        subject.getSession().setAttribute("userid" , user.getId());
//        //end


        request.setAttribute("loginUser" , subject.getPrincipal() );
        request.setAttribute("path", "index");

        return "admin/index";
    }


    //分类
    @GetMapping({"/categories"})
    public String categories(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
            return "error/error404";
        }


        request.setAttribute("categorys", indexService.getCategory());
        request.setAttribute("loginUser" , subject.getPrincipal() );
        request.setAttribute("path", "categories");

        return "admin/categories";
    }


    //订单
    @GetMapping({"/orders"})
    public String orders(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
            return "error/error404";
        }


        request.setAttribute("loginUser" , subject.getPrincipal() );
        request.setAttribute("path", "orders");

        return "admin/orders";
    }


    //商品管理
    @GetMapping({"/merchandise"})
    public String merchandise(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
            return "error/error404";
        }


        request.setAttribute("loginUser" , subject.getPrincipal() );
        request.setAttribute("path", "merchandise");

        return "admin/merchandise";
    }













}
