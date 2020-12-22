package com.geek.mybatis.cfg;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义 MyBatis 的配置类。
 *
 * @author geek
 */
@Data
public class Configuration {

    private String driver;
    private String url;
    private String username;
    private String password;

    private Map<String, Mapper> mappers = new HashMap<String, Mapper>();

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
//        this.mappers = mappers;
        // 此处使用追加的方式。如果使用赋值方式，只能有一个<mapper>标签。前面的会被覆盖。
        this.mappers.putAll(mappers);
    }

}
