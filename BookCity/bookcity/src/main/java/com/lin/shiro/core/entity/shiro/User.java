package com.lin.shiro.core.entity.shiro;

import java.io.Serializable;
import java.util.Date;

/**
 * User  功能描述
 *
 * @Author Lin
 * @Description //TODO $  user--role
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
public class User implements Serializable {

    private int id;
    private String user_id; //用户id
//    @NotBlank(message = "name 不允许为空")
//    @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间")
    private String username; //用户名
    private String password; //密码
    private String salt; //盐
    private String email; //邮箱
    private String phone; //手机号
    private int sex = 2; //性别 2表示未知 ,1男 0女
    private int age = 0; //年龄 默认是零
    private int status  = 1; //用户状态(锁定? 1未锁定)
    private Date create_time; //创建时间
    private Date update_time; //更新时间
    private Date last_login_time; //最后登录时间


    public void user(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }
    public String getSaltAndUsername(){
        return username + salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }
}
