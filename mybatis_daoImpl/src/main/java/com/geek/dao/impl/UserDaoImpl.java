package com.geek.dao.impl;

import com.geek.dao.IUserDao;
import com.geek.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * dao 实现类。
 * 实际开发中一般不这样使用。
 *
 * @author geek
 */
public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<User> findAll() {
        // 使用工厂创建 sqlSession 对象。
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 使用 sqlSession 执行查询所有方法。
//        List<User> userList = sqlSession.selectList("select * from user");
        List<User> userList = sqlSession.selectList("com.geek.dao.IUserDao.findAll");
        sqlSession.close();
        // 返回查询结果。
        return userList;
    }

}
