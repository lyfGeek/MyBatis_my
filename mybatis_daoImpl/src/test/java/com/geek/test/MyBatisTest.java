package com.geek.test;

import com.geek.dao.impl.UserDaoImpl;
import com.geek.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    public static void main(String[] args) throws IOException {

        // 读取配置文件。
        InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 创建 SqlSessionFactory 工厂。
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

        // 使用工厂生产 SqlSession 对象。
//        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 使用工厂创建 dao 对象。
        UserDaoImpl userDao = new UserDaoImpl(sqlSessionFactory);

        // 使用 SqlSession 创建 Dao 接口的代理对象。
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 使用代理对象执行方法。
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        // 释放资源。
        is.close();
    }

}
