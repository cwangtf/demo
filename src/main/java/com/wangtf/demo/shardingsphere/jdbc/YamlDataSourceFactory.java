package com.wangtf.demo.shardingsphere.jdbc;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class YamlDataSourceFactory {

    public static DataSource newInstance() throws SQLException, IOException {
        return YamlShardingSphereDataSourceFactory.createDataSource(getFile("/application.yaml"));

    }

    private static File getFile(final String fileName) {
        return new File(YamlDataSourceFactory.class.getResource(fileName).getFile());
    }
}
