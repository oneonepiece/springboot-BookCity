package com.lin.shiro.core.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lin.shiro.core.util.Result;
import com.lin.shiro.core.util.ResultGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

/**
 * ApiRoleController  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@RestController
@RequestMapping("/role")
public class ApiRoleController {


    @RequestMapping(value = "/api/addca" , method = RequestMethod.POST )
    public Result addCategory(@RequestBody JSONObject param ){

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
            return ResultGenerator.genFailResult("无权限");
        }
        String category = (String)param.get("category");
        return ResultGenerator.genSuccessResult("添加一级分类成功");
    }


    @RequestMapping(value = "/api/updca" , method = RequestMethod.POST )
    public Result updCategory(@RequestBody JSONObject param ){

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
            return ResultGenerator.genFailResult("无权限");
        }

        String category = (String)param.get("category");
        Integer id = (Integer)param.get("id");

        return ResultGenerator.genSuccessResult("一级分类已修改");
    }


@RequestMapping(value = "/api/remca" , method = RequestMethod.POST )
public Result remCategory(@RequestBody JSONObject param ){

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
        return ResultGenerator.genFailResult("无权限");
    }

        Integer id = (Integer)param.get("id");

        return ResultGenerator.genSuccessResult("删除了一个一级分类");
        }


    @RequestMapping(value = "/api/addcl" , method = RequestMethod.POST )
    public Result addClassify(@RequestBody JSONObject param ){

            Subject subject = SecurityUtils.getSubject();
            if ( !subject.hasRole("manage") ){
                return ResultGenerator.genFailResult("无权限");
            }

            String category = (String)param.get("classify");

            return ResultGenerator.genSuccessResult("添加二级分类成功");
            }


    @RequestMapping(value = "/api/updcl" , method = RequestMethod.POST )
    public Result updClassify(@RequestBody JSONObject param ){

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
            return ResultGenerator.genFailResult("无权限");
        }

        String category = (String)param.get("classify");
        Integer id = (Integer)param.get("id");

        return ResultGenerator.genSuccessResult("二级分类已修改");
    }


    @RequestMapping(value = "/api/remcl" , method = RequestMethod.POST )
    public Result remClassify(@RequestBody JSONObject param ){

        Subject subject = SecurityUtils.getSubject();
        if ( !subject.hasRole("manage") ){
            return ResultGenerator.genFailResult("无权限");
        }

        Integer id = (Integer)param.get("id");

        return ResultGenerator.genSuccessResult("删除了一个二级分类");
    }



}
