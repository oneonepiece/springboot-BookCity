package com.lin.shiro.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Book  功能描述
 *
 * @Author Lin
 * @Description //TODO $ book表
 * `b_id` int (11),
 * 	`b_name` varchar (600),
 * 	`author` varchar (150),
 * 	`price` double ,
 * 	`curr_price` double ,
 * 	`discount` double ,
 * 	`press` varchar (300),
 * 	`publish_time` datetime ,
 * 	`edition` int (11),
 * 	`page_num` int (11),
 * 	`word_num` int (11),
 * 	`print_time` datetime ,
 * 	`book_size` int (11),
 * 	`paper` varchar (150),
 * 	`image_uri` varchar (300),
 * 	`order_by` int (11),
 * 	`classify_id` int (11),
 * 	`isbn` varchar (300)
 * 	book_num varchar(64)
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public class Book implements Serializable {

    public int b_id;
    public String book_num; // 书籍编号|商品编号
    public String b_name; //书名
    public String author; //作者
    public double price; //价格
    public double curr_price; //当前价格
    public double discount; //折扣
    public String press; //出版社?
    public Date publish_time; //发布时间
    public int edition; //版号?版次?
    public int page_num; //页数
    public int word_num; //字数
    public Date print_time; //印刷时间?
    public int book_size; //书籍大小? 开本16开
    public String paper; //纸? 书籍纸张类型
    public String image_uri; //图片uri
    public int order_by; //订货人??
    public int classify_id; //分类ID  ? category_id
    public String isbn;
    public int status; //书籍状态,上架 0 下架1


    public Book(){ }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCurr_price() {
        return curr_price;
    }

    public void setCurr_price(double curr_price) {
        this.curr_price = curr_price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getWord_num() {
        return word_num;
    }

    public void setWord_num(int word_num) {
        this.word_num = word_num;
    }

    public Date getPrint_time() {
        return print_time;
    }

    public void setPrint_time(Date print_time) {
        this.print_time = print_time;
    }

    public int getBook_size() {
        return book_size;
    }

    public void setBook_size(int book_size) {
        this.book_size = book_size;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(String image_uri) {
        this.image_uri = image_uri;
    }

    public int getOrder_by() {
        return order_by;
    }

    public void setOrder_by(int order_by) {
        this.order_by = order_by;
    }

    public int getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(int classify_id) {
        this.classify_id = classify_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBook_num() {
        return book_num;
    }

    public void setBook_num(String book_num) {
        this.book_num = book_num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
