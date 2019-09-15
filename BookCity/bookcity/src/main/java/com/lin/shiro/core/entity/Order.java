package com.lin.shiro.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Order  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public class Order implements Serializable {

//    `o_id` int (11),
//	`order_time` datetime ,
//            `total` double ,
//            `status` int (11),
//	`address` varchar (600),
//	`u_id` int (11)

    public Integer o_id;
    public Date order_time; //订单时间
    public double total; //总价
    public int status; //状态 0 已支付     1未支付
    public String address; //收获地址
    public int u_id; //用户
    public String o_number;

    public List<OrderItem> orderItemList;


    public Order(){}


    public Integer getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getO_number() {
        return o_number;
    }

    public void setO_number(String o_number) {
        this.o_number = o_number;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
