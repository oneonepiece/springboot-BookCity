package com.lin.shiro.core.controller;

import com.lin.shiro.core.config.InitConfig;
import com.lin.shiro.core.entity.shiro.User;
import com.lin.shiro.core.service.IndexService;
import com.lin.shiro.core.service.UserService;
import com.lin.shiro.core.util.PageResult;
import com.lin.shiro.core.util.Result;
import com.lin.shiro.core.util.ResultGenerator;
import com.lin.shiro.core.util.Validator;
import com.lin.shiro.core.util.shiro.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Date;


/**
 * IndexController  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private IndexService indexService;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping({"/", "/index", "index.html"})
    public String index(HttpServletRequest request) {

        return  goPageURI(1,request);
    }



    @RequestMapping({"/page/{id}"})
    public String goPageURI (@PathVariable("id") int id  , HttpServletRequest request ){

        Subject subject = SecurityUtils.getSubject();
        //开发避免每次登陆,在访问index的时候就给其登陆先
        UsernamePasswordToken token = new UsernamePasswordToken("user1","123");
        //认证登录
        subject.login(token);
        User user = userService.findByUsername("user1");
        subject.getSession().setAttribute("userid" , user.getId());
        //end


        PageResult bookPageResult = indexService.getBooks(null,id,-1 , 15);
        if (bookPageResult == null){
            return "/error/error404";
        }
        request.setAttribute("BookPageResult" , bookPageResult );
        request.setAttribute("categorys", indexService.getCategory());
        request.setAttribute("indexclass", "list-group-item active");
        request.setAttribute("pageUrl" , "page"); //待做

//        Subject subject = SecurityUtils.getSubject();
        //未登录
        if(!subject.isAuthenticated()){
            request.setAttribute("user" , null);
        }else {
            String username = (String)subject.getPrincipal();
            request.setAttribute("user" , username);
        }

        return  "index";
    }



    /**
     * Login
     *
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        //如果已经登录
        if(subject.isAuthenticated()){

            return "/index";
        }
        return   "/anyone/login";
    }

    //test:
//    User user1 = new User();
//        user1.setUser_id("3");
//        user1.setUsername("user1");
//        user1.setPassword("123");
//        12b6e20ad450e28f00cbbad5b5cb1e8a  b4a8cc4e5d8541c846d7d3cf583dc718
//
//    User user2 = new User();
//        user1.setUser_id("4");
//        user1.setUsername("user2");
//        user1.setPassword("123");
//        8efecea996366148a49202bc73219c24  656e10ede4e12ae2407ce04a91b522a3
    //密码一般是123456 或者123
    @PostMapping("/login")
    @ResponseBody
    public Result login(User loginUser ,
                        ServletRequest request ) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(),loginUser.getPassword());
        //登录判断
        if(!subject.isAuthenticated()){
            try {
                //注意!!!:不能在登录方法中设置cookie,会造成cookie无效问题:
                // 原因大概是shiro会覆盖掉cookie的设置
                // 不然会引起登录无效问题!!!
                //解决思路:1单独做一个api来设置cookie,只在登录成功后设置,如下面的mycookie api
                //         2在login.html中登录成功后请求一次cookie,达到设置效果.
//                Cookie cookie = new Cookie("JSESSIONID" , sessionId );
//                cookie.setMaxAge(600);  //让浏览器保存的cookie时间,单位秒
//                cookie.setPath("/");
//                response.addCookie(cookie);

                //认证登录
                subject.login(token);
                User user = userService.findByUsername(loginUser.getUsername());
                subject.getSession().setAttribute("userid" , user.getId());

            }catch (Exception e){
                System.out.println("fail");
                return ResultGenerator.genFailResult("登录失败");
            }
        }

        //获取上一次请求路径
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String url = "";
        if(savedRequest != null){
            //测试发现有时候会跑到图标界面下
            String oldurl = savedRequest.getRequestUrl();
            if (oldurl.equals("/favicon.ico") ||
                    oldurl.equals("/register")  ){
                return ResultGenerator.genSuccessResult((Object)"/index");
            }
            url = savedRequest.getRequestUrl();
        }else{
//            url = "/page/main.html";
            url = "/index";
        }

        return  ResultGenerator.genSuccessResult((Object) url);

    }

    //注册
    @GetMapping("/register")
    public String register( ){
        Subject subject = SecurityUtils.getSubject();
        //如果已经登录,先让用户退出
        if (subject.isAuthenticated()){
            return "/anyone/notRole";
        }
        return "/anyone/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public Result add( User user ){
        Subject subject = SecurityUtils.getSubject();
        //如果已经登录,先让用户退出
        if (subject.isAuthenticated()){
            return ResultGenerator.genFailResult("请先退出登录");
        }

        //username(账号不能够重复)
        if (userService.findByUsername(user.getUsername()) != null){
            return ResultGenerator.genFailResult("用户名已存在");
        }
        //另外还有email的情况是不是也不能重复?

        user.setPhone("13700000000");

        if (! Validator.isUserName(user.getUsername()))  return ResultGenerator.genFailResult("用户名不符合要求");
        if (! Validator.isPassword(user.getPassword()))  return ResultGenerator.genFailResult("密码不符合要求");
        if (! Validator.isEmail(user.getEmail()))  return ResultGenerator.genFailResult("邮箱不正确");
        if (! Validator.isMobile(user.getPhone())) return ResultGenerator.genFailResult("电话不符合要求");

        //生成user_id
        String user_id = String.valueOf(InitConfig.userIdCount.getAndIncrement());
        user.setUser_id(user_id);
        user.setCreate_time(new Date());

        User u = userService.createUser(user);
        if (u == null){
            //406：请求的资源并不符合要求。
            return ResultGenerator.genErrorResult(406,"不符合要求");
        }
        return ResultGenerator.genSuccessResult(u);
    }



    //由于在shiroConfig中已经定义了退出url,所以不会走到这里,shiro已经帮我做了退出处理
    @RequestMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultGenerator.genSuccessResult("已注销");
    }


    /**
     * 未授权
     *
     * @return
     */
    @GetMapping("/notRole")
    public String notRole(HttpServletRequest request) {
        return   "/anyone/notRole";
    }



    //在使用subject维护的session中,已经不需要这个api ,存在的意义是和下面对比加深印象.  2019 - 0828
    @GetMapping("/mycookie")
    @ResponseBody
    public Result getCookie(HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated() ){
            Result result = new Result();
            result.setResultCode(302);
            result.setMessage("用户未登录");
            result.setData("/login");
            return result;
        }

        Session session = subject.getSession();
        Serializable serializable = session.getId();
//        System.out.println(serializable.toString());
        session.getAttributeKeys();
        response.addCookie(new Cookie("username" , (String)subject.getPrincipal()));

        return ResultGenerator.genSuccessResult((Object) serializable.toString() );
    }



//    //在登录逻辑中设置响应的cookie ,在登录成功后由客户端触发  使用Servlet容器的session时的配置 2019 0828
//    @GetMapping("/mycookie")
//    @ResponseBody
//    public Result getCookie( @CookieValue(value="JSESSIONID") String sessionId  ,HttpServletResponse response){
//        Subject subject = SecurityUtils.getSubject();
//        if (!subject.isAuthenticated() ){
//            Result result = new Result();
//            result.setResultCode(302);
//            result.setMessage("用户未登录");
//            result.setData("/login");
//            return result;
//        }
//
////       // 让浏览器保存的cookie时间
//        Cookie cookie = new Cookie("JSESSIONID" , sessionId );
//        cookie.setMaxAge(1800); //单位秒
//        cookie.setPath("/");
//        response.addCookie(cookie);
//
//        return ResultGenerator.genSuccessResult( cookie );
//    }


}
