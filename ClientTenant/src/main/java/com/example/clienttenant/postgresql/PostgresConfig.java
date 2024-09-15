package com.example.clienttenant.postgresql;

import com.example.clienttenant.tenant.TenantService;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class PostgresConfig {


    @Value("${app.multitenant}")
    private boolean isMultitenant;

    private final TenantService tenantService;

    public PostgresConfig(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @Bean
    public DataSource dataSource() {
        if (isMultitenant) {
            return multitenantDataSource();
        } else {
            return singleTenantDataSource();
        }

    }
    private DataSource multitenantDataSource() {
        TenantAwareRoutingDataSource tenantRoutingDataSource = new TenantAwareRoutingDataSource();

        Map<Object, Object> dataSources = new HashMap<>();

        // Agregamos los data sources de los tenants de forma dinámica
        tenantService.getAllTenants().forEach(tenant -> {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/" + tenant.getPostgresDbName());
            dataSource.setUsername("postgres");
            dataSource.setPassword("secure_pass_here");

            dataSources.put(tenant.getTenantId(), dataSource);
        });

        tenantRoutingDataSource.setTargetDataSources(dataSources);

        return tenantRoutingDataSource;

    }

    private DataSource singleTenantDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/exampledb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("secure_pass_here");
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[]{"com.example.clienttenant.entity"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    //    properties.setProperty("hibernate.hbm2ddl.auto", "update");

        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
