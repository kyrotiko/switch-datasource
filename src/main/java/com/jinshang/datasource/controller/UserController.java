package com.jinshang.datasource.controller;

import com.jinshang.datasource.config.datasource.utils.DataSourceContextHolder;
import com.jinshang.datasource.domain.User;
import com.jinshang.datasource.services.AccountService;
import com.jinshang.datasource.services.UserService;
import com.jinshang.datasource.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 9:20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;


    @RequestMapping("/save")
    public String save(User user) {
        userService.insertUser(user);
        return "ok";
    }

    @RequestMapping("/list")
    public List<User> select() {
        return userService.select();
    }

    @RequestMapping("/saveTwoException")
    public String saveEx(String name) throws Exception {
        userService.insertUserTestException(name);
        return "ok";
    }

    @RequestMapping("/saveTwoNoException")
    public String saveNoEx(String name) throws Exception {
        userService.insertUserTestNoException(name);
        return "ok";
    }


    @RequestMapping("/createDataSource")
    public String createDb(String name) throws Exception {
        accountService.createNewDataSource(name);
        return "success";
    }


    @RequestMapping("/bind")
    public String bindDataSource(String dataSourceName, HttpSession session) {
        session.setAttribute(SessionUtils.USER_DATASOURCE_BIND, dataSourceName);
        return "ok";
    }
}
