package com.example.springbootAll.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author: sea
 * @Description: 第二数据源配置
 * @Date: 14:01 2017/8/12
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerSec",//实体管理引用
        transactionManagerRef = "secTransactionManager",//事务管理引用
        basePackages = {"com.example.springbootAll.repository.secRepository"}
)
public class SecondaryDataSourceConfig {

    //注入主数据源
    @Autowired
    @Qualifier("secDataSource")
    private DataSource secDataSource;

    //配置EntityManager工厂实体
    @Bean(name = "entityManagerSec")
    public LocalContainerEntityManagerFactoryBean entityManagerSec(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secDataSource)
                .properties(getVendorProperties(secDataSource))
                .packages(new String[]{"com.example.springbootAll.repository.secRepository.entity"}) //设置应用creditDataSource的基础包名
                .persistenceUnit("secPersistenceUnit")
                .build();
    }


    //注入Jpa配置实体
    @Autowired
    private JpaProperties jpaProperties;

    //获取jpa配置信息
    private Map<String,String> getVendorProperties(DataSource datasource) {
        return jpaProperties.getHibernateProperties(datasource);
    }


    //配置TransactionManager 根据EntityManagerFactory创建事务Manager，让事务应用到实体工厂内
    @Bean(name = "secTransactionManager")
    public PlatformTransactionManager secTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerSec(builder).getObject());
    }

}
