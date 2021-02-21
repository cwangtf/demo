package com.wangtf.demo.datasource.service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public interface TService {

    void insert(DataSource dataSource, String sql);

    List<Map<String, Object>> query(DataSource dataSource, String sql);
}
