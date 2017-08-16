package com.example.springbootAll.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author: sea
 * @Description: 主数据源配置 详细实现了DataSourceConfigurer类中声明的bookDataSource
 * @Date: 13:45 2017/8/12
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerPri",//实体管理引用
        transactionManagerRef = "priTransactionManager",//事务管理引用
        basePackages = {"com.example.springbootAll.repository.priRepository"}
)
public class PrimaryDataSourceConfig {

    //注入主数据源
    @Autowired
    @Qualifier("priDataSource")
    private DataSource priDataSource;

    //配置EntityManager工厂实体
    @Primary
    @Bean(name = "entityManagerPri")
    public LocalContainerEntityManagerFactoryBean entityManagerPri(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(priDataSource)
                .properties(getVendorProperties(priDataSource))
                .packages(new String[]{"com.example.springbootAll.entity.priEntity"}) //设置应用creditDataSource的基础包名
                .persistenceUnit("priPersistenceUnit")
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
    @Primary
    @Bean(name = "priTransactionManager")
    public PlatformTransactionManager priTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerPri(builder).getObject());
    }

}
