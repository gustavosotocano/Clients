package com.example.clienttenant.mongo;

import com.example.clienttenant.tenant.TenantContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClientFactory mongoClientFactory() {
        return new MongoClientFactory();
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClientFactory().getMongoClient(), getDatabaseName());
    }

    private String getDatabaseName() {
        String tenantId = TenantContext.getTenantId();
        return tenantId != null ? "db_" + tenantId : "spring";
    }
}
