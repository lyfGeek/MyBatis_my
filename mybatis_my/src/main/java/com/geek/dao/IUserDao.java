package com.geek.dao;

import com.geek.domain.User;
import com.geek.mybatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口。
 *
 * @author geek
 */
public interface IUserDao {

    /**
     * 查询所有操作。
     *
     * @return
     */
    @Select("select * from users")
    List<User> findAll();

}
