package com.hqinjun.myboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

//Jpa docs: https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/#jpa.java-config
/**
 * 注意：spring-data-jpa2.x版本需要spring版本为5.x
 * 否则会报Initialization of bean failed; nested exception is java.lang.AbstractMethodError错误
 * 参考：https://stackoverflow.com/questions/47558017/error-starting-a-spring-application-initialization-of-bean-failed-nested-excep
 * 搭配方案：spring4+spring-data-jpa1.x或spring5+spring-data-jpa2.x
 */
@Configuration
// 借助spring data实现自动化的jpa repository，只需编写接口无需编写实现类
// 相当于xml配置的<jpa:repositories base-package="com.example.repository" />
// repositoryImplementationPostfix默认就是Impl
// entityManagerFactoryRef默认就是entityManagerFactory
// transactionManagerRef默认就是transactionManager
@EnableJpaRepositories(basePackages = {"com.hqinjun.myboot.repository"})
//        repositoryImplementationPostfix = "Impl",
//        entityManagerFactoryRef = "entityManagerFactory",
//        transactionManagerRef = "transactionManager"
//        )
@EnableTransactionManagement    // 启用事务管理器

public class SpringDateJpaConfig {


    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driverclassname}")
    private String driverClassName;


    @Bean(name = "Mysql")
    @Primary
    public DataSource prodDataSource(){

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;

    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        // 设置数据库类型（可使用org.springframework.orm.jpa.vendor包下的Database枚举类）
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        // 设置打印sql语句
        jpaVendorAdapter.setShowSql(true);
        // 设置不生成ddl语句
        jpaVendorAdapter.setGenerateDdl(false);
        // 设置hibernate方言
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return jpaVendorAdapter;
    }

    // 配置实体管理器工厂
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        // 注入数据源
        emfb.setDataSource(dataSource);
        // 注入jpa厂商适配器
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        // 设置扫描基本包
        emfb.setPackagesToScan("com.hqinjun.myboot.domain");
        return emfb;
    }

    // 配置jpa事务管理器
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        // 配置实体管理器工厂
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(JpaVendorAdapter jpaVendorAdapter,DataSource prodDataSource){
        return entityManagerFactory(prodDataSource,jpaVendorAdapter).getObject().createEntityManager();
    }


//
//
//    @Bean("devsql")
//    public DataSource devDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public JdbcOperations prodJdbcOperations(@Qualifier("Mysql") DataSource prodDataSource) {
//        return new JdbcTemplate(prodDataSource);
//    }
//
//    @Bean
//    public JdbcOperations devJdbcOperations(@Qualifier("devsql") DataSource devDataSource) {
//        return new JdbcTemplate(devDataSource);
//    }
//
//    @Bean
//    public JpaProperties getJpaProperties(){
//       return new JpaProperties();
//    }


//    @Bean(name = "entityManageFactoryPrimary")
//    public LocalContainerEntityManagerFactoryBean entityManageFactory(EntityManagerFactoryBuilder builder, @Qualifier("Mysql") DataSource prodDataSource){
//
//
//        LocalContainerEntityManagerFactoryBean entityManagerFactory =  builder.dataSource(prodDataSource)
//                .packages("com.hqinjun.myboot.domain").build();
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        jpaProperties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
//        jpaProperties.put("hibernate.connection.charSet", "utf-8");
//        jpaProperties.put("hibernate.show_sql", "false");
//        entityManagerFactory.setJpaProperties(jpaProperties);
//
//        return entityManagerFactory;
//    }


//    @Bean
//    public PlatformTransactionManager prodTransactionManager(@Qualifier("Mysql") DataSource prodDataSource) {
//        return new DataSourceTransactionManager(prodDataSource);
//    }

//    @Bean
//    public PlatformTransactionManager devTransactionManager(@Qualifier("devsql") DataSource devDataSource) {
//        return new DataSourceTransactionManager(devDataSource);
//    }


}
