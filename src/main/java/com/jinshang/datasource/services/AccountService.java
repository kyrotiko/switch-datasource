package com.jinshang.datasource.services;

import com.jinshang.datasource.domain.Account;

import java.util.List;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 13:12
 */
public interface AccountService {


    void insert(Account account);

    void createNewDataSource(String dbName) throws Exception;

    List<Account> queryAll();
}
