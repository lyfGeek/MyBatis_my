package com.geek.mybatis.cfg;

import lombok.Data;

/**
 * 用于封装执行 SQL 语句和结果类型的全限定类名。
 *
 * @author geek
 */
@Data
public class Mapper {

    /**
     * SQL。
     */
    private String queryString;
    /**
     * 实体类的全限定类名。
     */
    private String resultType;

}
