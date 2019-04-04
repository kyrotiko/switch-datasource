package com.jinshang.datasource.mapper;

import com.jinshang.datasource.domain.User;

import java.util.List;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 9:20
 */
public interface UserMapper {

    List<User> select();

    void insert(User user);
}
