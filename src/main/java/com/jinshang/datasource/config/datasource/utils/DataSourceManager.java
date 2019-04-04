package com.jinshang.datasource.config.datasource.utils;

import com.jinshang.datasource.config.datasource.MybatisConfig;
import com.jinshang.datasource.domain.Account;
import com.jinshang.datasource.services.AccountService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 15:01
 */
@Component
public class DataSourceManager {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomSqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private MybatisConfig mybatisConfig;

    /**
     * 初始或者重载入数据源
     *
     * @throws Exception
     */
    @PostConstruct
    public void loadDataSource() throws Exception {
        List<Account> accountList = accountService.queryAll();
        System.out.println(accountList);
        Map<Object, SqlSessionFactory> newSqlSessionFactoryMap = new HashMap<>(16);
        Map<Object, SqlSessionFactory> sqlSessionFactoryMap = sqlSessionTemplate.getTargetSqlSessionFactorys();
        for (Account account : accountList) {
            SqlSessionFactory sqlSessionFactory = mybatisConfig.createSqlSessionFactory(mybatisConfig.getDataSource(account.getDbName()));
            newSqlSessionFactoryMap.put(account.getDbName(), sqlSessionFactory);
        }
        newSqlSessionFactoryMap.putAll(sqlSessionFactoryMap);
        this.sqlSessionTemplate.setTargetSqlSessionFactorys(newSqlSessionFactoryMap);
    }

}
