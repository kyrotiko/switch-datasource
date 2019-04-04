package com.jinshang.datasource.mapper;

import com.jinshang.datasource.domain.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 13:10
 */
public interface AccountMapper {

    void insert(Account account);


    List<Account> select();

    void createDatabase(@Param("dbName") String dbName);
}
