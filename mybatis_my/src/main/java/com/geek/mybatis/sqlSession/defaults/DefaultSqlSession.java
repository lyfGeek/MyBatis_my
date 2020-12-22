package com.geek.mybatis.sqlSession.defaults;

import com.geek.mybatis.cfg.Configuration;
import com.geek.mybatis.sqlSession.ISqlSession;
import com.geek.mybatis.sqlSession.proxy.MapperProxy;
import com.geek.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ISqlSession 接口的实现类。
 *
 * @author geek
 */
public class DefaultSqlSession implements ISqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        this.connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 根据参数创建一个代理对象。
     *
     * @param daoInterfaceClass dao 的接口字节码。
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {

        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass}, new MapperProxy(cfg.getMappers(), connection));

    }

    /**
     * 释放资源。
     */
    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
