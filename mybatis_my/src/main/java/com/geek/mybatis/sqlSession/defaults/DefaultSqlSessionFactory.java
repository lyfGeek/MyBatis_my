package com.geek.mybatis.sqlSession.defaults;

import com.geek.mybatis.cfg.Configuration;
import com.geek.mybatis.sqlSession.ISqlSession;
import com.geek.mybatis.sqlSession.ISqlSessionFactory;

/**
 * ISqlSessionFactory 接口的实现类。
 *
 * @author geek
 */
public class DefaultSqlSessionFactory implements ISqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 用于创建一个新的操作数据库对象。
     *
     * @return
     */
    @Override
    public ISqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

}
