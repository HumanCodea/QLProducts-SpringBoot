package com.storeTechnology.demo.cofig;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDatabase {

    @Bean
    public DataSource dataSource(){ // day la ham de ket noi voi sql
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create(); // day la ham de xu li dang nhap sql
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionUrl ="jdbc:sqlserver://localhost:1433;" + "databaseName=QLProducts;integratedSecurity=true;" + "encrypt=true;trustServerCertificate=true";
        dataSourceBuilder.url(connectionUrl);
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("123456789");
        return dataSourceBuilder.build();
    }
}
