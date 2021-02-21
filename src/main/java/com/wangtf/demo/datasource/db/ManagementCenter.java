package com.wangtf.demo.datasource.db;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Random;

@Component
public class ManagementCenter {

    @Resource(name = "master")
    DataSource masterDataSource;

    @Resource(name = "slave1")
    DataSource slave1DataSource;

    @Resource(name = "slave2")
    DataSource slave2DataSource;

    public DataSource getDefaultDataSource() {
        return masterDataSource;
    }

    /**
     * 简单负载均衡
     * @return
     */
    public DataSource getSlaveDataSource() {
        Random random = new Random();
        int salveIndex = random.nextInt(2);
        if (salveIndex > 0) {
            return slave2DataSource;
        }
        return slave1DataSource;
    }

}
