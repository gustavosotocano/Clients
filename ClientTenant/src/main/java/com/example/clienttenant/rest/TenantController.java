package com.example.clienttenant.rest;

import com.example.clienttenant.tenant.Tenant;
import com.example.clienttenant.tenant.TenantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    /*
    @GetMapping("/{id}")
    public Tenant getTenantById(@PathVariable UUID id) {
        return tenantService.getTenantById(id);
    }

    @PostMapping
    public Tenant createTenant(@RequestBody Tenant tenant) {
        return tenantService.saveTenant(tenant);
    }

    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable UUID id) {
        tenantService.deleteTenant(id);
    }

 */
}
