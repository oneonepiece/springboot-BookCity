package com.lin.shiro.core.entity;

import java.io.Serializable;

/**
 * Classify  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public class Classify implements Serializable {

//    `classify_id` int (11),
//	`classify_name` varchar (150),
//	`classify_desc` varchar (600),
//	`order_by` int (11),
//	`category_id` int (11)

    public int classify_id;
    public String classify_name;
    public String classify_desc;
    public int order_by;
    public int category_id;


    public Classify(){}

    public int getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(int classify_id) {
        this.classify_id = classify_id;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public String getClassify_desc() {
        return classify_desc;
    }

    public void setClassify_desc(String classify_desc) {
        this.classify_desc = classify_desc;
    }

    public int getOrder_by() {
        return order_by;
    }

    public void setOrder_by(int order_by) {
        this.order_by = order_by;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
