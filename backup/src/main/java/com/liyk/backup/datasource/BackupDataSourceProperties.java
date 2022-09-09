package com.liyk.backup.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liyongkui
 * @version 1.0
 * @description: 数据源属性
 * 添加一个数据源属性配置类，配置@ConfigurationProperties(prefix = "backup.datasource")注解，
 * 这样就可以通过注入BackupDataSourceProperties读取数据源属性了。
 * @date 2022/8/24 09:07
 */
@Component
@ConfigurationProperties(prefix = "backup.datasource")
public class BackupDataSourceProperties {
    private String host;
    private String userName;
    private String password;
    private String database;
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }
}
