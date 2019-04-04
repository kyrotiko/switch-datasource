package com.jinshang.datasource.services.impl;

import com.jinshang.datasource.config.datasource.utils.DataSourceManager;
import com.jinshang.datasource.domain.Account;
import com.jinshang.datasource.mapper.AccountMapper;
import com.jinshang.datasource.services.AccountService;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 13:12
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private DataSourceManager dataSourceManager;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Value("${spring.datasource.druid.system.url}")
    private String url;
    @Value("${spring.datasource.druid.system.username}")
    private String username;
    @Value("${spring.datasource.druid.system.password}")
    private String password;
    @Value("${spring.datasource.druid.system.driver-class-name}")
    private String driverClassName;


    @Override
    public void insert(Account account) {
        accountMapper.insert(account);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createNewDataSource(String dbName) throws Exception {
        Account account = new Account();
        account.setDbName(dbName);
        createDataBase(dbName);
        accountMapper.insert(account);
        dataSourceManager.loadDataSource();
    }


    private void createDataBase(String dbName) throws Exception {
//        accountMapper.createDatabase(dbName);
        Connection connection = null;
        Statement st = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            st = connection.createStatement();
            st.execute("create database " + dbName);
            st.execute("use " + dbName);
            st.execute("SET NAMES utf8mb4;\n" +
                    "SET FOREIGN_KEY_CHECKS = 0;\n" +
                    "\n" +
                    "DROP TABLE IF EXISTS `user`;\n" +
                    "CREATE TABLE `user`  (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`) USING BTREE\n" +
                    ") ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;\n" +
                    "\n" +
                    "SET FOREIGN_KEY_CHECKS = 1;\n");
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Account> queryAll() {
        return accountMapper.select();
    }
}
