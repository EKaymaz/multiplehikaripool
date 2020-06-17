package com.example.dbtestpool.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(value = "com.example.dbtestpool.repository.write",
        entityManagerFactoryRef = "writingEntityManagerFactory",
        transactionManagerRef = "writingTransactionManager")
@EnableTransactionManagement
public class SpringDataJpaWritingConfiguration {

    @Bean(name = "writingEntityManagerFactory")
    public EntityManagerFactory writingEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceUnitName("persistence.writing");
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.example");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.afterPropertiesSet();
        return em.getObject();
    }

    @Bean(name = "writingExceptionTranslator")
    public HibernateExceptionTranslator writingHibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean(name = "writingTransactionManager")
    public PlatformTransactionManager readingTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(writingEntityManagerFactory());
        return transactionManager;
    }

    @Bean(name = "hikariWRITE")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.h2.Driver");
        hikariConfig.setJdbcUrl("jdbc:h2:mem:testdb");
        hikariConfig.setUsername("sa");
        hikariConfig.setPassword("password");
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("writeHikariCP");

        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        return new HikariDataSource(hikariConfig);

    }

}