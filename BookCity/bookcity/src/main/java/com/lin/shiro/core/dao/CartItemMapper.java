package com.lin.shiro.core.dao;

import com.lin.shiro.core.entity.CartItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * CartItemMapper  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Component
public interface CartItemMapper {

    List<CartItem> getCart(int u_id);

    List<CartItem> getCartItem(@Param("userId") Integer userId , @Param("bookId") HashSet<Integer> bookId);

    int removeCartItem(@Param("userId") Integer userId , @Param("bookId") HashSet<Integer> bookId);

}
