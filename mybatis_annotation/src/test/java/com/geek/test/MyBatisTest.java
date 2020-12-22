package com.geek.test;

import com.geek.dao.IUserDao;
import com.geek.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
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
        /*
        创建工厂，MyBatis 使用了 `构建者模式`。把创建对象的细节隐藏，使用者直接调用方法就可以拿到对象。
        现实生活中建工厂，请包工队。我们只提需求，给钱。
        sqlSessionFactoryBuilder 就是包工队，
        sqlSessionFactoryBuilder.build(is) 拿钱（is）去构建（build）工厂。
         */
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        // 使用工厂生产 SqlSession 对象。
        /*
        工厂模式。解耦（降低类之间的依赖关系）。
        假如现在 new 了 new 1实现();
        现在想要 2实现();
        就要修改源码。重新部署。
         */
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 使用 SqlSession 创建 Dao 接口的代理对象。
        /*
        代理模式。不修改源码的基础上对源码增强。
         */
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 使用代理对象执行方法。
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        // 释放资源。
        sqlSession.close();
        is.close();
    }

}
