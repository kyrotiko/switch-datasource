package com.jinshang.datasource.aspect;

import com.jinshang.datasource.config.datasource.utils.DataSourceContextHolder;
import com.jinshang.datasource.utils.SessionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author yuanyang(417168602@qq.com)
 * @date 2019/4/3 16:33
 */
@Aspect
@Component
public class DataSourceBindAspect {


    @Pointcut("execution(* com.jinshang.datasource.controller.*.*(..))")
    public void bindDataSource() {
    }

    @Before("bindDataSource()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        DataSourceContextHolder.setDatasourceType(SessionUtils.getCurrentDataSource());
    }

}
