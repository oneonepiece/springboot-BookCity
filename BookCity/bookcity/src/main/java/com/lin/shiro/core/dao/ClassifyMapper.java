package com.lin.shiro.core.dao;

import com.lin.shiro.core.entity.Classify;
import org.springframework.stereotype.Component;

/**
 * ClassifyMapper  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */
@Component
public interface ClassifyMapper {


    Classify[] getClassify(int category_id);
}
