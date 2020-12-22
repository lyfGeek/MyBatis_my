package com.geek.mybatis.sqlSession.proxy;

import com.geek.mybatis.cfg.Mapper;
import com.geek.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @author geek
 */
public class MapperProxy implements InvocationHandler {

    // map 的 key 是全限定类名 + 方法名。
    private Map<String, Mapper> mappers;

    private Connection connection;

    public MapperProxy(Map<String, Mapper> mappers, Connection connection) {
        this.mappers = mappers;
        this.connection = connection;
    }

    /**
     * 用于对方法进行增强。
     * 我们的增强其实就是调用 selectList(); 方法。
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取方法名。
        String methodName = method.getName();
        // 获取方法所在的类名。
        String className = method.getDeclaringClass().getName();
        // 组合 key。
        String key = className + "." + methodName;
        // 获取 mappers 中的 Mapper 对象。
        Mapper mapper = mappers.get(key);
        // 判断是否有 mapper。
        if (mapper == null) {
            throw new IllegalArgumentException("传入的参数有误。");
        }

        // 调用工具类，执行查询所有。
        return new Executor().selectList(mapper, connection);
    }

}
