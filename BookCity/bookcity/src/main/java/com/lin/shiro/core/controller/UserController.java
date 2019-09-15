package com.lin.shiro.core.controller;


import com.lin.shiro.core.entity.CartItem;
import com.lin.shiro.core.entity.Order;
import com.lin.shiro.core.service.IndexService;
import com.lin.shiro.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * UserController  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private static String authen = "/authen";

    @Autowired
    private UserService userService;

    @Autowired
    private IndexService indexService;


    @GetMapping({"/cart"})
    public String userCart( HttpServletRequest request){

        Subject subject = SecurityUtils.getSubject();
//        String username = (String)subject.getPrincipal();
        Integer u_id = (Integer)subject.getSession().getAttribute("userid");

        HashMap<String,Object> CartMap =  userService.getCart(u_id);
        if ( CartMap != null ){
            request.setAttribute("cartItems" , CartMap.get("cartItems"));
            request.setAttribute("bookList" , CartMap.get("bookList"));
        }
        request.setAttribute("user" , (String)subject.getPrincipal());

        return  authen +"/usercart";
    }


    //订单详细页
    //    String uri = "/user/order/oid?order=1" ;  @RequestParam("userName") String userName,
    @GetMapping({"/order/oid"})
    public String userOrderId(HttpServletRequest request , @RequestParam(value = "order") Integer o_id ){

        Order order =  userService.getOrder(o_id);
        if (order == null){
            return authen +"/order";
        }
        Subject subject = SecurityUtils.getSubject();
//        String username = (String)subject.getPrincipal();
//        Integer u_id = (Integer)subject.getSession().getAttribute("userid");

        HashMap<String,Object> orderItemMap =  userService.getOrderItem(o_id);
        if ( orderItemMap != null ){
            request.setAttribute("orderItem" , orderItemMap.get("orderItem"));
            request.setAttribute("bookList" , orderItemMap.get("bookList"));
            request.setAttribute("total", order.getTotal()); //此处可以用orderItemMap的值也行
            request.setAttribute("address" , order.getAddress());
            request.setAttribute("order" , order);
        }
        request.setAttribute("user" , (String)subject.getPrincipal());

        return  authen +"/order";
    }


    //订单页
    @GetMapping({"/order"})
    public String userOrderList(HttpServletRequest request ){

        Subject subject = SecurityUtils.getSubject();
//        String username = (String)subject.getPrincipal();
        Integer u_id = (Integer)subject.getSession().getAttribute("userid");

        List<Order> order =  userService.getOrderByUserId(u_id);
        if (order.size() == 0){
            return authen +"/order";
        }

        request.setAttribute("orderList" , order);
        request.setAttribute("user" , (String)subject.getPrincipal());

//        HashMap<String,Object> orderItemMap =  userService.getOrderItem(o_id);
//        if ( orderItemMap != null ){
//            request.setAttribute("orderItem" , orderItemMap.get("orderItem"));
//            request.setAttribute("bookList" , orderItemMap.get("bookList"));
//            request.setAttribute("total", order.getTotal()); //此处可以用orderItemMap的值也行
//            request.setAttribute("address" , order.getAddress());
//        }
//        request.setAttribute("user" , (String)subject.getPrincipal());

        return  authen +"/orderlist";
    }


}