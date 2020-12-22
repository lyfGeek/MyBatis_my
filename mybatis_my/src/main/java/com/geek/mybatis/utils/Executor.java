package com.geek.mybatis.utils;

import com.geek.mybatis.cfg.Mapper;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 负责执行 Sql 语句，并且封装结果。
 */
public class Executor {

    /**
     * 执行查询。
     *
     * @param mapper
     * @param connection
     * @param <E>
     * @return
     */
    public <E> List<E> selectList(Mapper mapper, Connection connection) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // 取出 mapper 中的数据。
        String queryString = mapper.getQueryString();// select * from user;
        String resultType = mapper.getResultType();// com.geek.domain.User。

        Class<?> aClass = null;
        try {
            aClass = Class.forName(resultType);

            // 获取 PreparedStatement 对象。
            preparedStatement = connection.prepareStatement(queryString);
            // 执行 SQL 语句，获取结果集。
            resultSet = preparedStatement.executeQuery();
            // 封装结果集。
            List<E> list = new ArrayList<E>();// 定义返回值。
            while (resultSet.next()) {
                // 实例化要封装的实体类对象。
                E o = (E) aClass.newInstance();

                // 取出结果集的元信息，ResultSetMetaData。
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                // 取出总列数。
                int columnCount = resultSetMetaData.getColumnCount();
                // 遍历总列数。
                for (int i = 1; i <= columnCount; i++) {
                    // 获取列名。从 1 开始。
                    String columnName = resultSetMetaData.getColumnName(i);
                    // 根据得到的列名，获取每列的值。
                    Object columnValue = resultSet.getObject(columnName);
                    // 给 Object 赋值，使用 java 内省机制（借助 PropertyDescription 实现属性的封装）。
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, aClass);// 要求：实体类
                    //  获取 ta 的写入方法。
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    // 把获取的列的值赋值给对象。
                    writeMethod.invoke(o, columnValue);
                }
                // 把赋好值的对象加入到集合中。
                list.add(o);
            }
            return list;
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | IntrospectionException | SQLException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            release(preparedStatement, resultSet);
        }
    }

    /**
     * 释放资源。
     *
     * @param preparedStatement
     * @param resultSet
     */
    private void release(PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
