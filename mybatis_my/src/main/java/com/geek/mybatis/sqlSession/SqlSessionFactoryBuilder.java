package com.geek.mybatis.sqlSession;

import com.geek.mybatis.cfg.Configuration;
import com.geek.mybatis.sqlSession.defaults.DefaultSqlSessionFactory;
import com.geek.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建一个 SqlSessionFactoryBuilder 对象。
 *
 * @author geek
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流来构建一个 SqlSessionFactory 工厂。
     *
     * @param config
     * @return
     */
    public ISqlSessionFactory build(InputStream config) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }

}
