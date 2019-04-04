package com.jinshang.datasource.services.impl;

import com.jinshang.datasource.domain.User;
import com.jinshang.datasource.mapper.UserMapper;
import com.jinshang.datasource.services.UserService;
import com.jinshang.datasource.config.datasource.utils.DataSourceContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 9:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> select() {
        return userMapper.select();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUserTestException(String name) throws Exception {
        User user = new User();
        user.setName(name);
        DataSourceContextHolder.setDatasourceType("test1");
        userMapper.insert(user);
        DataSourceContextHolder.setDatasourceType("test2");
        userMapper.insert(user);
        //模拟异常
        Assert.notNull(null, "123456");
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUserTestNoException(String name) throws Exception {
        User user = new User();
        user.setName(name);
        DataSourceContextHolder.setDatasourceType("test1");
        userMapper.insert(user);
        DataSourceContextHolder.setDatasourceType("test2");
        userMapper.insert(user);
    }
}
