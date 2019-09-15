package com.lin.shiro.core.dao;


import com.lin.shiro.core.entity.shiro.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * RoleMapper  功能描述
 *
 * @Author Lin
 * @Description //TODO $  角色
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Component
public interface RoleMapper {

    List<String> findRolesByUserIds(@Param("user_id") String user_id); // 根据用户id查找其角色id

    //SELECT * FROM tb_blog WHERE blog_id = 1||80||100;
    List<Role> findRoleByRids(@Param("role_ids") List<String> role_ids); // 根据角色id 集合得到角色

    int setRole(@Param( "user_id") String user_id , @Param( "role_id")String role_id);

//    Role createRole(Role role);
//    void deleteRole(Long roleId);
//    //添加角色-权限之间关系
//    void correlationPermissions(Long roleId, Long... permissionIds);
//    //移除角色-权限之间关系
//    void uncorrelationPermissions(Long roleId, Long... permissionIds);//


}
