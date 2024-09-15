package com.example.clienttenant.tenant.repository.mongo;



import com.example.clienttenant.tenant.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TenantRepository extends MongoRepository<Tenant, String> {
    Optional<Tenant> findByTenantId(String tenantId);
}