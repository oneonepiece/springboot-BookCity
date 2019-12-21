package com.lin.shiro.core.controller.admin;


import com.alibaba.fastjson.JSONObject;
import com.lin.shiro.core.dao.BookMapper;
import com.lin.shiro.core.entity.Book;
import com.lin.shiro.core.entity.Order;
import com.lin.shiro.core.service.AdminInsertServer;
import com.lin.shiro.core.service.AdminServer;
import com.lin.shiro.core.service.IndexService;
import com.lin.shiro.core.service.UserService;
import com.lin.shiro.core.util.PageResult;
import com.lin.shiro.core.util.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * BookListAdminController  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/admin")
public class BookListAdminController {


    @Autowired
    private UserService userService;

    @Autowired
    AdminServer adminServer;

    //其实不应该用adminInsertServer 来做updata逻辑,因为名字都对应不上
    @Autowired
    private AdminInsertServer adminInsertServer;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping({"/booklist", "booklist.html"})
    public String index(HttpServletRequest request) {

        return  goPageURI(1,request);
    }


    @RequestMapping({"/page/{id}"})
    public String goPageURI (@PathVariable("id") int id  , HttpServletRequest request ){

        Subject subject = SecurityUtils.getSubject();

        PageResult bookPageResult = adminServer.getBooks(null,id,-1 , 15);
        if (bookPageResult == null){
            return "/error/error404";
        }
        request.setAttribute("BookPageResult" , bookPageResult );
        request.setAttribute("categorys", adminServer.getCategory());
        request.setAttribute("indexclass", "list-group-item active");
        request.setAttribute("pageUrl" , "admin/page"); //待做

//        Subject subject = SecurityUtils.getSubject();
        //未登录
        if(!subject.isAuthenticated()){
            request.setAttribute("user" , null);
        }else {
            String username = (String)subject.getPrincipal();
            request.setAttribute("user" , username);
        }

        return  "/admin/booklist";
    }



    @RequestMapping({"/classify/{classifyId}"})
    public String getClassify (@PathVariable("classifyId") int classifyId  , HttpServletRequest request ){

        return getClassify(classifyId,1 ,request );
    }

    @RequestMapping({"/classify/{classifyId}/{pageNum}"})
    public String getClassify (@PathVariable("classifyId") int classifyId  ,
                               @PathVariable("pageNum") int pageNum ,
                               HttpServletRequest request ){

        PageResult bookPageResult = adminServer.getBooks(null,pageNum,classifyId , 8);
        if (bookPageResult == null){
            return "/error/error404";
        }
        request.setAttribute("BookPageResult" , bookPageResult );
        request.setAttribute("categorys", adminServer.getCategory());
        request.setAttribute("indexclass", "list-group-item ");
        request.setAttribute("pageUrl" , "admin/classify/" + String.valueOf(classifyId));
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            request.setAttribute("user" , null);
        }else {
            String username = (String)subject.getPrincipal();
            request.setAttribute("user" , username);
        }

        return "/admin/classify";
    }


    @RequestMapping({"/book/{bookid}"})
    public String getBookDetail (@PathVariable("bookid") int bookId ,
                                 HttpServletRequest request ){

        Book book = adminServer.getOneBook(bookId);
        if (book == null){
            return "/error/error404";
        }
        String strBookStatus = "下架";
        if (book.status != 0){
            strBookStatus = "上架";
        }
        request.setAttribute("book" , book );
        request.setAttribute("status" , strBookStatus );
//        request.setAttribute("categorys", adminServer.getCategory());
//        request.setAttribute("indexclass", "list-group-item ");
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            request.setAttribute("user" , null);
        }else {
            String username = (String)subject.getPrincipal();
            request.setAttribute("user" , username);
        }

        return "/admin/detail";
    }


    //http://127.0.0.1:8082/search?keyword=%E6%96%97%E7%BD%97&page=1
    @RequestMapping({"/search"})
    public String getSearch (HttpServletRequest request,
                             @RequestParam(value = "keyword") String keyword  ,
                             @RequestParam(value = "page") int pageNum
    ){

        PageResult bookPageResult = adminServer.getBooks(keyword,pageNum,-1 , 8);
        if (bookPageResult == null){
            return "/error/error404";
        }
        request.setAttribute("BookPageResult" , bookPageResult );
//        request.setAttribute("categorys", adminServer.getCategory());
//        request.setAttribute("indexclass", "list-group-item ");
        request.setAttribute("pageUrl" , "admin/search?keyword=" + keyword + "&page=");
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            request.setAttribute("user" , null);
        }else {
            String username = (String)subject.getPrincipal();
            request.setAttribute("user" , username);
        }

        return "/admin/search";
    }


    @ResponseBody
    @RequestMapping(value = "/book/code" , method = RequestMethod.POST)
    public JSONObject bookCode ( @RequestBody JSONObject param ){

        JSONObject json = new JSONObject();
        Integer bookId = null;
        Integer bookStatus = null;
        try {
            bookId = (Integer)param.get("bookId");
            bookStatus = (Integer) param.get("status");
        }catch (Exception e){
            e.printStackTrace();
            json.put("resultCode",404);
            json.put("message", "传入的参数不对");
            return json;
        }

        //等于-1为删除Book
        if (bookStatus == -1){
            json.put("resultCode",201);
            return  json;
        }
        HashMap<String , Object> map = new HashMap<>();
        map.put("id",bookId);
        map.put("status",bookStatus);
        adminInsertServer.updataBook(map);

        json.put("resultCode",200);
        json.put("data","data");
        json.put("message", "Good");

        return json;
    }


    //订单详情页
    //    String uri = "/user/order/oid?order=1" ;  @RequestParam("userName") String userName,
    @GetMapping({"/user/order/oid"})
    public String userOrderId(HttpServletRequest request , @RequestParam(value = "order") Integer o_id ){

        Order order =  userService.getOrder(o_id);
        if (order == null){
            return  "/admin/user_order";
        }
        Subject subject = SecurityUtils.getSubject();
//        String username = (String)subject.getPrincipal();
//        Integer u_id = (Integer)subject.getSession().getAttribute("userid");

        HashMap<String,Object> orderItemMap =  userService.getOrderItem(o_id);
        if ( orderItemMap != null ){
            request.setAttribute("orderItem" , orderItemMap.get("orderItem"));
            request.setAttribute("bookList" , orderItemMap.get("bookList"));
            request.setAttribute("total", order.getTotal()); //此处可以用orderItemMap的值也行
            request.setAttribute("address" , order.getAddress());
            request.setAttribute("order" , order);
        }
        request.setAttribute("user" , (String)subject.getPrincipal());

        return  "/admin/user_order";
    }


    //订单页 ,/user/order?page=1&limit=8&st=0
    @GetMapping({"/user/order"})
    public String userOrderList(HttpServletRequest request ,@RequestParam Map<String, Object> params){

//        int status = -1 , page = 1 , limit = 9 ;
//        String desc = null;
//        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
//            return "/error/error404";
//        }
//        if (!StringUtils.isEmpty(params.get("st"))){
//            try {
//                status = Integer.valueOf((String)params.get("st"));
//            }catch (Exception e){
//                e.printStackTrace();
//                return "/error/error404";
//            }
//        }
//        if (!StringUtils.isEmpty(params.get("desc"))){
//            desc = "desc";
//        }
//        try {
//            page = Integer.valueOf((String)params.get("page"));
//            limit = Integer.valueOf((String)params.get("limit"));
//        }catch (Exception e){
//            e.printStackTrace();
//            return "/error/error404";
//        }

        List<Order> order = null;
        try {
            order = adminServer.findOrder(params);
        }catch (Exception e){
            e.printStackTrace();
            return "/error/error404";
        }

//        List<Order> order = adminServer.findOrder(page, status , limit , desc);

        if (order.size() == 0){
            return "/admin/user_order";
        }

        Subject subject = SecurityUtils.getSubject();
        request.setAttribute("orderList" , order);
        request.setAttribute("user" , (String)subject.getPrincipal());

        return  "/admin/orderlist";
    }


}
