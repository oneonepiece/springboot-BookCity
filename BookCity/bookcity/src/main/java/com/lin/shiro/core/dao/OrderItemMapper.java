package com.lin.shiro.core.dao;

import com.lin.shiro.core.entity.CartItem;
import com.lin.shiro.core.entity.Order;
import com.lin.shiro.core.entity.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * OrderItemMapper  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Component
public interface OrderItemMapper {

    List<OrderItem> getOrderItem(int o_id);

    int insertOrderItem( @Param("orderItemList") List<OrderItem> orderItemList );

}
