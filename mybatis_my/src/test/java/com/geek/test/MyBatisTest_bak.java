//package com.geek.test;
//
//import com.geek.dao.IUserDao;
//import com.geek.domain.User;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.ISqlSession;
//import org.apache.ibatis.session.ISqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//public class MyBatisTest_bak {
//
//    public static void main(String[] args) throws IOException {
//
//        // 读取配置文件。
//        InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
//        // 创建 ISqlSessionFactory 工厂。
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//        ISqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
//        // 使用工厂生产 ISqlSession 对象。
//        ISqlSession sqlSession = sqlSessionFactory.openSession();
//        // 使用 ISqlSession 创建 Dao 接口的代理对象。
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//        // 使用代理对象执行方法。
//        List<User> userList = userDao.findAll();
//        for (User user : userList) {
//            System.out.println(user);
//        }
//        // 释放资源。
//        sqlSession.close();
//        is.close();
//    }
//
//}
