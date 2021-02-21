package com.wangtf.demo.datasource.service;

import com.wangtf.demo.datasource.db.ManagementCenter;
import com.wangtf.demo.datasource.service.impl.TServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TServiceImplTest {

    @Resource
    ManagementCenter managementCenter;

    @Resource
    TService tService;

    @Test
    @Transactional
    public void testInsertAndQuery() {
        String sql = "insert into t1(id) values(3);";
        tService.insert(managementCenter.getDefaultDataSource(), sql);

        sql = "select * from t1 limit 3;";
        // 使用从库 slave1
        List<Map<String, Object>> entities = tService.query(managementCenter.getSlaveDataSource(), sql);
        for (Map item: entities) {
            System.out.println(item.toString());
        }
    }
}
