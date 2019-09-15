package com.lin.shiro.core.dao;

import com.lin.shiro.core.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * OrderMapper  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Component
public interface OrderMapper {

    Order getOrder(int o_id);

    List<Order> getOrderByUserId(int u_id);

    int insertOrder(Order order);

}
