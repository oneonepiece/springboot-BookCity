//package com.lin.shiro.core.controller.blog;
//
//
//import org.apache.catalina.Session;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.bind.annotation.RequestMapping;
//import javax.servlet.http.HttpServletResponse;
//
//
//
///**
// * UpdateController  功能描述
// *
// * @Author Lin
// * @Description //TODO $
// * @Date $ $
// * @Param $
// * @return $
// * @Version 1.0
// */
//
//@Controller
//public class UpdateController {
//
//
//    @RequestMapping("/user/update")
//    public String userUpdate( ){
//
//
////        //HttpServerletResponse  装相应信息的类
////        Cookie cookie = new Cookie("login","true");
////        response.addCookie(cookie);
//
//        Subject subject = SecurityUtils.getSubject();
//
//
////        String username = (String)subject.getPrincipal();
////        //noruser 普通用户
////        if (subject.hasRole("noruser")){
////            //有权限
////            System.out.println("我是普通用户," + username);
////        }
////        //管理员
////        if (subject.hasRole("manage")){
////
////            System.out.println("我是管理员" + username);
////        }
////        //管理员
////        if (!subject.hasRole("abcdefg")){
////            System.out.println("是的,我不是abcdefg用户" + username);
////        }
////        if(subject.isPermitted("delete")){
////            System.out.println("是的,我有delete权限" + username);
////        }
//
//        return "/anyone/update";
//    }
//
//
//
//
//}
