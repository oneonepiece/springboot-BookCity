package com.lin.shiro.core.service.impl;


import com.lin.shiro.core.dao.*;
import com.lin.shiro.core.entity.Book;
import com.lin.shiro.core.entity.CartItem;
import com.lin.shiro.core.entity.Order;
import com.lin.shiro.core.entity.OrderItem;
import com.lin.shiro.core.entity.shiro.Role;
import com.lin.shiro.core.entity.shiro.User;
import com.lin.shiro.core.service.UserService;
import com.lin.shiro.core.util.shiro.PasswordHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * UserServiceImpl  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private OrderMapper orderMapper;




    //根据用户名返回角色名,流程:查到user,根据userid查到管理的roleid,根据roleid查到role名
    @Override
    public Set<String> findRoles(String username) {

        User user = userMapper.findByUsername(username);
        if (user == null){
            return null;
        }
        Set<String> role_name = new HashSet<String>();
        //找到该用户的角色id 集合
        List<String> roles_ids = roleMapper.findRolesByUserIds(user.getUser_id());
        //找不到该角色,表示这个用户还没有分配角色
        if (roles_ids.size() == 0 ){
          //  role_name.add("notRole");
            return null;
        }
        List<Role> roles =  roleMapper.findRoleByRids(roles_ids);


        for (int i=0 ; i<roles.size(); i++){
            role_name.add(roles.get(i).getName());
        }
       return role_name;
    }

    //根据用户名返回该用户权限名 , 流程:根据用户名找到用户,根据用户id找到关联的roleid ,
    // 关联的roleid找到管理的permissionid,根据id找到permission的name
    @Override
    public Set<String> findPermissions(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null){
            return null;
        }
        //找到该用户的角色id 集合
        List<String>  roles_ids = roleMapper.findRolesByUserIds(user.getUser_id());

        Set<String> permi = new HashSet<>();
        for (String s : roles_ids){
            Set<String> ps = findPermissionsByRole(s);
            permi.addAll(ps);
        }
        return permi;

    }

    //根据角色id ,返回角色的权限 name 的集合
    @Override
    public Set<String> findPermissionsByRole(String role_id) {

      //  List<Role>  roles = roleMapper.findRoles(user.getUser_id());
        //得到权限id 集合
        Set<String> permi_id = permissionMapper.findPermissions(role_id);
        Set<String> permi_name = permissionMapper.findPeNamesByPeIds(permi_id);
        return permi_name;
    }

    //根据用户名返回用户
    @Override
    public User findByUsername(String username) {

        User user = userMapper.findByUsername(username);
        if (user == null){
            return null;
        }
        return user;
    }

    @Override
    public User createUser(User user) {

        //加密
        PasswordHelper.encryptPassword(user);
        int u = 0;
        try {
            u = userMapper.createUser(user);
            //授予用户初级权限
          roleMapper.setRole(user.getUser_id() , "1");

        }catch (Exception e){ e.printStackTrace();}

        return u>0? user : null ;
    }

    @Override
    public String getMaxUserId() {
        String user_id = null;
        try {
            user_id = userMapper.getMaxUserId();
        }catch (Exception e){
        }
        return user_id;
    }

    //购物车
    @Override
    public HashMap<String, Object> getCart(int u_id) {

        List<CartItem> cartItems = cartItemMapper.getCart(u_id);
        if (cartItems.size()==0) return null;
        ArrayList<Integer> bookids = new ArrayList<>(cartItems.size()+2);
        for (CartItem c: cartItems){
            bookids.add(c.getB_id());
        }
        List<Book> bookList = getBookById(bookids);

        HashMap<String,Object> CartMap = new HashMap<>(5);
        CartMap.put("cartItems" , cartItems );
        CartMap.put("bookList" , bookList );

        return CartMap;
    }

    //订单页数据 ,提供order 的o_id
    @Override
    public HashMap<String, Object> getOrderItem(int o_id) {

        List<OrderItem> orderItem = orderItemMapper.getOrderItem(o_id);
        if (orderItem.size()==0) return null;
        ArrayList<Integer> bookids = new ArrayList<>(orderItem.size()+2);
        Double total = 0.0;
        for (OrderItem o: orderItem){
            bookids.add(o.getB_id());
            total += o.subtotal;
        }
        List<Book> bookList = getBookById(bookids);

        HashMap<String,Object> orderMap = new HashMap<>(5);
        orderMap.put("orderItem" , orderItem );
        orderMap.put("bookList" , bookList );
//        orderMap.put("total", total);

        return orderMap;

    }

    @Override
    public Order getOrder(int o_id) {

        try {
            return orderMapper.getOrder(o_id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Order> getOrderByUserId(int u_id) {

        return orderMapper.getOrderByUserId(u_id);
    }

    @Override
    public List<CartItem> getCartItem(Integer userId ,  HashSet<Integer> bookId) {

//        HashSet<Integer> bookId = new HashSet<>();
//        bookId.add(3);
//        bookId.add(5);

        List<CartItem> cartItemList = cartItemMapper.getCartItem(userId , bookId);
        return cartItemList.size() > 0 ? cartItemList : null;
    }

    @Override
    public List<Book> getBookById(List<Integer> bookids) {
        return bookMapper.getBookById(bookids);
    }

    @Override
    public Book getBookById(Integer b_id) {
        return bookMapper.getOneBook(b_id);
    }

    @Override
    public int insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public int insertOrderItem(List<OrderItem> orderItemList) {
        return orderItemMapper.insertOrderItem(orderItemList);
    }

    @Override
    public int removeCartItem(Integer userId, HashSet<Integer> bookId) {
        return cartItemMapper.removeCartItem(userId , bookId);
    }


//    //创建用户
//    @Override
//    public User createUser(User user) {
//        PasswordHelper.encryptPassword(user);
//        int i =1;
//        i = userMapper.createUser(user);
//        return i<0 ? null : user;
//    }


}
