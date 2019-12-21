package com.lin.shiro.core.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lin.shiro.core.entity.Book;
import com.lin.shiro.core.entity.shiro.User;
import com.lin.shiro.core.service.AdminInsertServer;
import com.lin.shiro.core.service.IndexService;
import com.lin.shiro.core.service.UserService;
import com.lin.shiro.core.util.PageResult;
import com.lin.shiro.core.util.Result;
import com.lin.shiro.core.util.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * AdminController  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    IndexService indexService;

    @Autowired
    AdminInsertServer adminInsertServer;


    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
            return "error/error404";
        }


        //开发避免每次登陆,在访问index的时候就给其登陆先
        UsernamePasswordToken token = new UsernamePasswordToken("user1","123");
        //认证登录
        subject.login(token);
        User user = userService.findByUsername("user1");
        subject.getSession().setAttribute("userid" , user.getId());
        //end


        request.setAttribute("loginUser" , subject.getPrincipal() );
        request.setAttribute("path", "index");

        return "admin/index";
    }


    //分类
    @GetMapping({"/categories"})
    public String categories(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
            return "error/error404";
        }


        request.setAttribute("categorys", indexService.getCategory());
        request.setAttribute("loginUser" , subject.getPrincipal() );
        request.setAttribute("path", "categories");

        return "admin/categories";
    }


    //订单
    @GetMapping({"/orders"})
    public String orders(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
            return "error/error404";
        }
        request.setAttribute("loginUser" , subject.getPrincipal() );
        request.setAttribute("path", "orders");
        return "admin/orders";
    }


    //商品管理
    @GetMapping({"/merchandise"})
    public String merchandise(HttpServletRequest request) {

//        Subject subject = SecurityUtils.getSubject();
//        if ( !subject.hasRole("manage") ){
//            return "error/error404";
//        }


//        request.setAttribute("loginUser" , subject.getPrincipal() );
        request.setAttribute("path", "merchandise");

        return "admin/merchandise";
    }



    //文件上传 + 书的信息
    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String UPLOAD_PATH_PREFIX = "static/demo/img/";
    //商品管理
    @PostMapping({"/merchandise/upload"})
    @ResponseBody
    public JSONObject imgUpload( HttpServletRequest request ) {

        JSONObject json = new JSONObject();
        json.put("error","");
//        Subject subject = SecurityUtils.getSubject();
//        if ( !subject.hasRole("manage")  ){
//            json.put("error","error");
//            return json;
//        }

        //获取请求的参数
        //文件上传的请求
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        //前端框架上传的img的key为test
        MultipartFile uploadFile = fileMap.get("test");
        if (uploadFile == null){
            json.put("error","没有上传图片");
            return json;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd/");
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        String format = sdf.format(new Date());
        //存放上传文件的文件夹
        File file = new File(realPath + format);
        if(!file.isDirectory()){
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
        String imgUrl = "";
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            uploadFile.transferTo(newFile);
            //格式： http://127.0.0.1:8080/demo/img/20191120/45f9bcd1-d9d4-437b-8753-025d3ba7a787.jpg
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo/img/" + format + newName;
            imgUrl = "/demo/img/" + format + newName;

        } catch (Exception e) {
            e.printStackTrace();
            json.put("error","封面保存失败");
        }

        //取得book的信息
        Map<String , String[]> requestMap = request.getParameterMap();
        String bookJson = requestMap.get("BookJsonData")[0];
        JSONObject param = JSONObject.parseObject(bookJson);

        String bookName = (String)param.get("bookName");
        Double bookPrice = Double.valueOf(param.get("bookPrice").toString());
        Double bookDiscount = Double.valueOf((String)param.get("bookDiscount"));;
        Double bookCurrPrice = Double.valueOf((String)param.get("bookCurrPrice"));
        String bookAuthor = (String)param.get("bookAuthor");
        String bookPress = (String)param.get("bookPress");
        String bookPublishTime = (String)param.get("bookPublishTime");
        String bookPrintTime = (String)param.get("bookPrintTime");

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date PublishTimeDate = null;
        Date PrintTimeDate = null;
        try{
            PublishTimeDate = sdf1.parse(bookPublishTime);
            PrintTimeDate = sdf1.parse(bookPrintTime);
        }catch (ParseException e){
            e.printStackTrace();
            json.put("error","输入的数值错误,无非通过校验");
        }
        int bookEdition = Integer.valueOf((String)param.get("bookEdition"));
        int bookSize = Integer.valueOf((String)param.get("bookSize"));
        String bookPaper = (String)param.get("bookPaper");
        int bookPageNum = Integer.valueOf((String)param.get("bookPageNum"));
        int bookWordNum = Integer.valueOf((String)param.get("bookWordNum"));

        Book oneBook = new Book(bookName,bookPrice,bookDiscount,bookCurrPrice,bookAuthor,bookPress,PublishTimeDate,PrintTimeDate,bookEdition,bookSize,
                bookPaper,bookPageNum,bookWordNum);

        oneBook.setBook_num("YMD-2019-12-04");
        oneBook.setIsbn("123456");
        oneBook.setStatus(0);
        oneBook.setClassify_id(7);
        oneBook.setOrder_by(0);
        oneBook.setImage_uri(imgUrl);

        //插入一本书
//        int ad = adminInsertServer.insertBook(oneBook);
//        if (ad < 0){
//            json.put("error","添加失败,未知错误!");
//        }
        //
        System.out.println(oneBook.getImage_uri());

//        json.put("resultCode",200);
//        json.put("data",imgUrl);

        return json;
    }


}
