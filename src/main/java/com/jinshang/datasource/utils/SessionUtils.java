package com.jinshang.datasource.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 16:26
 */
public class SessionUtils {
    public static final String USER_DATASOURCE_BIND = "com.jinshang.user.bind.datasource";

    public static String getCurrentDataSource() {
        String name = (String) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(USER_DATASOURCE_BIND);
        return name;
    }
}
