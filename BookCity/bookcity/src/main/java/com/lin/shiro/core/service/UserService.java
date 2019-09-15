package com.lin.shiro.core.service;


import com.lin.shiro.core.entity.Book;
import com.lin.shiro.core.entity.CartItem;
import com.lin.shiro.core.entity.Order;
import com.lin.shiro.core.entity.OrderItem;
import com.lin.shiro.core.entity.shiro.User;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * UserService  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public interface UserService {

    Set<String> findRoles(String username);// 根据用户名查找其角色

    Set<String> findPermissions(String username); //根据用户名查找其权限

    public Set<String> findPermissionsByRole(String role_id); // 根据角色id查找权限

    User findByUsername(String username);// 根据用户名查找用户

//
    User createUser(User user); //创建账户

    String getMaxUserId(); //获取最大用户id

    //购物车
    HashMap<String, Object> getCart(int u_id);

    //订单Item 数据
    HashMap<String, Object> getOrderItem(int o_id);

    Order getOrder(int o_id); //订单

    List<Order> getOrderByUserId(int u_id); //订单

    List<CartItem> getCartItem( Integer userId ,  HashSet<Integer> bookId);

    List<Book> getBookById( List<Integer> bookids);

    Book getBookById( Integer b_id);

    int insertOrder(Order order); //新增订单

    int insertOrderItem(List<OrderItem> orderItemList ); //

    int removeCartItem( Integer userId ,  HashSet<Integer> bookId);


//    public void changePassword(String user_id, String newPassword);//修改密码
//    public void correlationRoles(String user_id, String... role_ids); //添加用户-角色关系
//    public void uncorrelationRoles(String user_id, String... role_ids);// 移除用户-角色关系





}
