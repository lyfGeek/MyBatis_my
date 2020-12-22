package com.geek.mybatis.sqlSession;

/**
 * 自定义 MyBatis 中和数据库交互的核心类。
 * 创建 dao 接口的代理对象。
 *
 * @author geek
 */
public interface ISqlSession {

    /**
     * 根据参数创建一个代理对象。
     *
     * @param daoInterfaceClass dao 的接口字节码。
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);
    // 泛形——> 先声明，再使用。<T>。

    /**
     * 释放资源。
     */
    void close();

}
