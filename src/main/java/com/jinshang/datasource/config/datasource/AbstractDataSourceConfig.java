package com.jinshang.datasource.config.datasource;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 10:38
 */
public abstract class AbstractDataSourceConfig {


    protected DataSource getDataSource(Environment env, String prefix, String dataSourceName) {
        Properties prop = build(env, prefix);
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName(dataSourceName);
        ds.setXaProperties(prop);
        return ds;
    }

    /**
     * 主要针对druid数据库链接池
     *
     * @param env
     * @param prefix
     * @return
     */
    protected Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
        return prop;
    }
}
