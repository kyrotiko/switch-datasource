package com.jinshang.datasource.services;

import com.jinshang.datasource.domain.User;

import java.util.List;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 9:20
 */
public interface UserService {

    void insertUser(User user);

    List<User> select();

    void insertUserTestException(String name) throws Exception;

    void insertUserTestNoException(String name) throws Exception;
}
