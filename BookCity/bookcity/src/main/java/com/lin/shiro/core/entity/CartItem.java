package com.lin.shiro.core.entity;

/**
 * CartItem  功能描述
 *
 * @Author Lin
 * @Description //TODO $ 对应表cart_item
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public class CartItem {


//    	`cart_item_id` int (11),
//	`quantity` int (11),
//	`b_id` int (11),
//	`u_id` int (11),
//	`order_by` int (11)

    private int cart_item_id;
    private int quantity; //数量
    private int b_id; //商品
    private int u_id; //用户id
    private int order_by;
    private Book book;


    public CartItem(){ }


    public int getCart_item_id() {
        return cart_item_id;
    }

    public void setCart_item_id(int cart_item_id) {
        this.cart_item_id = cart_item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getOrder_by() {
        return order_by;
    }

    public void setOrder_by(int order_by) {
        this.order_by = order_by;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
