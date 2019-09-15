package com.lin.shiro.core.dao;

import com.lin.shiro.core.entity.shiro.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * PermissionMapper  功能描述
 *
 * @Author Lin
 * @Description //TODO $ 权限
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Component
public interface PermissionMapper {

    HashSet<String> findPermissions(String role_id);  // 1 根据角色id查找其权限id 集合 ,表role--permission
    HashSet<String> findPeNamesByPeIds(@Param("pe_ids") Set<String> pe_ids); // 2 根据权限id集合找到权限行 表Permission


//    public Permission createPermission(Permission permission); //
//    public void deletePermission(Long permissionId);

}
