package com.lin.shiro.core.dao;

import com.lin.shiro.core.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BookMapper  功能描述
 *
 * @Author Lin
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 * @Version 1.0
 */

@Component
public interface BookMapper {

    int insertBook(Book book);

    List<Book> findBookList(Map<String, Object> map);

    int getTotalBook(Map<String, Object> map);

    //获取book
    List<Book> getBookById(@Param("bookids") List<Integer> bookids);

    Book getOneBook(int b_id);

//    1、MyBatis 通过包含的jdbcType类型
//
//    BIT、FLOAT、CHAR 、TIMESTAMP 、 OTHER 、UNDEFINEDTINYINT 、
// REAL 、VARCHAR 、BINARY 、BLOB NVARCHAR、SMALLINT 、DOUBLE 、
// LONGVARCHAR 、VARBINARY 、CLOB、NCHAR、INTEGER、 NUMERIC、DATE 、
// LONGVARBINARY 、BOOLEAN 、NCLOB、BIGINT 、DECIMAL 、TIME 、NULL、CURSOR
}
