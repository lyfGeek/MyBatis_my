package com.geek.mybatis.sqlSession;

/**
 * 用于打开一个新的 ISqlSession 对象。
 *
 * @author geek
 */
public interface ISqlSessionFactory {

    /**
     * 用于打开一新的 ISqlSession 对象。
     *
     * @return
     */
    ISqlSession openSession();

}
