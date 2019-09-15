package com.lin.shiro.core.dao;

import com.lin.shiro.core.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * CategoryMapper  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Component
public interface CategoryMapper {

    List<Category> getCategoryAll();

}
