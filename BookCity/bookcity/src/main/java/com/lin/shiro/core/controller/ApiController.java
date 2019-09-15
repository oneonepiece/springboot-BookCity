package com.lin.shiro.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.lin.shiro.core.entity.Book;
import com.lin.shiro.core.entity.CartItem;
import com.lin.shiro.core.entity.Order;
import com.lin.shiro.core.entity.OrderItem;
import com.lin.shiro.core.service.UserService;
import com.lin.shiro.core.util.Result;
import com.lin.shiro.core.util.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ApiController  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    UserService userService;

//删除
//    /cart/remove    method = RequestMethod.POST,  ,produces = "application/json;charset=UTF-8"  @RequestBody JSONObject param
    @RequestMapping(value = "/cart/remove" , method = RequestMethod.POST )
    public Result cartRemove(@RequestBody  JSONObject param ){

        Subject subject = SecurityUtils.getSubject();
//        String username = (String)subject.getPrincipal();
        Integer u_id = (Integer)subject.getSession().getAttribute("userid");

        String reId = (String)param.get("remove");
        String[] id = reId.split(",");
        HashSet<Integer> idList = new HashSet<>();
        for (int i=0 ; i<id.length;i++){
            idList.add(Integer.valueOf(id[i]));
        }

        int re = userService.removeCartItem( u_id , idList );
        if (re < 0 ){
            return ResultGenerator.genFailResult("删除错误");
        }


//        String[] ids = (String[])JSONObject.toJavaObject( param , String[].class);

//        JSONObject id_json = param.getJSONObject("remove");
//        Integer[] ids = (Integer[])JSONObject.toJavaObject( id_json ,Integer[].class);
//        for (int i:ids){
//
//           int a = i;
//        }

        return ResultGenerator.genSuccessResult("成功去除");
    }


    //生成订单 ,返回订单uri
    @RequestMapping(value = "/cart/order" , method = RequestMethod.POST )
    public Result addOrder(@RequestBody  JSONObject param ){

        Subject subject = SecurityUtils.getSubject();
        Integer u_id = (Integer)subject.getSession().getAttribute("userid");
        HashSet<Integer> bookId = new HashSet<>();
        List<CartItem> cartItemList ;
        try {
            String reId = (String)param.get("order");
            String[] id = reId.split(",");

            for (int i=0 ; i<id.length;i++){
                bookId.add(Integer.valueOf(id[i]));
            }
            cartItemList = userService.getCartItem(u_id , bookId);
            if (cartItemList == null) throw new Exception();

        }catch (Exception e){
            return ResultGenerator.genFailResult("参数类型错误");
        }

        List<OrderItem> orderItemList = new ArrayList<>();
        Order order = new Order();
        double total = 0.0;
        for (CartItem c : cartItemList){
            Book book = userService.getBookById(c.getB_id());
            int quantity = c.getQuantity();
            double currPrice = book.getCurr_price();
            double subtotal = quantity * currPrice;
            total += subtotal;

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(quantity);
            orderItem.setSubtotal(subtotal);
            orderItem.setB_id(c.getB_id());
            orderItemList.add(orderItem);

        }

        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String strDate = time.format(new Date());
        String number ="-";

        for ( int i=0; i<5; i++ ){
            Integer ran2 = (int) (Math.random()*10);
            number += ran2.toString();

        }

        order.setTotal(total);
        order.setOrder_time(new Date());
        order.setStatus(1);
        order.setU_id(u_id);
        order.setO_number( strDate + number );

        int st = userService.insertOrder(order);
        if (st < 0 || order.getO_id() == null ){
            return ResultGenerator.genFailResult("无法预知的错误");
        }
        for (OrderItem oI : orderItemList ){
            oI.setO_id(order.getO_id());
        }
        int orderItemStatus = userService.insertOrderItem(orderItemList);
        if (orderItemStatus < 0 ){
            return ResultGenerator.genFailResult("无法预知的错误");
        }

        userService.removeCartItem( u_id , bookId );

        JSONObject json = new JSONObject();
        json.put("uri" , "/user/order/oid?order=" + order.getO_id().toString());
        json.put("order_num" , order.getO_number());
        json.put("order_address" , order.getAddress());
        json.put("total" , order.getTotal());


//        String uri = "/user/order/oid?" + "order=1" ;
//        String json = "{ 'uri':'/user/order/oid?order=1' , 'order_num' : '123' , 'order_address' : '广西新西兰' }";
        return ResultGenerator.genSuccessResult( json );
    }


//    JSON.stringify({cart_id:id ,num : num})
    @RequestMapping(value = "/cart/cutoradd" , method = RequestMethod.POST )
    public Result cartCut(@RequestBody  JSONObject param ){

        String cart_id = (String)param.get("cart_id");
        Integer id = Integer.valueOf(cart_id);

        String num = (String)param.get("num");
        Integer quantity = Integer.valueOf(num);

        return ResultGenerator.genSuccessResult((Object)quantity);
    }


    @RequestMapping(value = "/addbook/tocart" , method = RequestMethod.POST)
    public Result addBookToCart ( @RequestBody  JSONObject param ){

        try {

            Integer bookId = (Integer)param.get("bookId");
//            Integer b_id = Integer.valueOf(bookId);

            Integer num = (Integer)param.get("quantity");
//            Integer quantity= Integer.valueOf(num);

        }catch (Exception e){
            return  ResultGenerator.genFailResult("不成功");
        }

        return  ResultGenerator.genSuccessResult("已添加到购物车");
    }

//    @RequestMapping(value = "/cart/add" , method = RequestMethod.POST )
//    public Result cartAdd(@RequestBody  JSONObject param ){
//
//        String cart_id = (String)param.get("cart_id");
//        Integer id = Integer.valueOf(cart_id);
//
//        String num = (String)param.get("num");
//        Integer quantity = Integer.valueOf(num);
//
//
//        return ResultGenerator.genSuccessResult((Object)quantity);
//    }



}
