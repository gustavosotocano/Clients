package com.example.clienttenant.tenant;

import com.example.clienttenant.tenant.repository.mongo.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public Optional<Tenant> getTenantByTenantId(String tenantId) {
        return tenantRepository.findByTenantId(tenantId);
    }
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }
}
