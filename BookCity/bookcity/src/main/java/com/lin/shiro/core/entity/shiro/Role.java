package com.lin.shiro.core.entity.shiro;

import java.util.Date;

/**
 * Role  功能描述
 *
 * @Author Lin
 * @Description //TODO $  role--user  role--permission
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public class Role {

    private int id;
    private String role_id;
    private String name;
    private String description;  //描述
    private  int status;  //状态
    private Date create_time;
    private Date update_time;

    public void role(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
