package com.wangtf.demo.datasource.aspect;

import com.wangtf.demo.datasource.db.ManagementCenter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ReadAspect {

    @Resource
    ManagementCenter managementCenter;

    @Pointcut("@annotation(com.wangtf.demo.datasource.annotation.ReadOnly)")
    public void read(){};

    @Around("read()")
    public List<Map<String, Object>> setDatabaseSource(ProceedingJoinPoint point) throws Throwable {
        System.out.println("data source change......");
        Object[] argv = point.getArgs();
        argv[0] = managementCenter.getSlaveDataSource();
        return (List<Map<String, Object>>) point.proceed(argv);
    }

}
