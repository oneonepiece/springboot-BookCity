package com.lin.shiro.core.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Category  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public class Category implements Serializable {

//    `category_id` int (11),
//	`category_name` varchar (150),
//	`category_desc` varchar (600),
//	`order_by` int (11)


    public int category_id;
    public String category_name;
    public String category_desc;
    public int order_by;

    public ArrayList<Classify> classifies;

    public Category(){}

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_desc() {
        return category_desc;
    }

    public void setCategory_desc(String category_desc) {
        this.category_desc = category_desc;
    }

    public int getOrder_by() {
        return order_by;
    }

    public void setOrder_by(int order_by) {
        this.order_by = order_by;
    }

    public ArrayList<Classify> getClassifies() {
        return classifies;
    }

    public void setClassifies(ArrayList<Classify> classifies) {
        this.classifies = classifies;
    }
}
