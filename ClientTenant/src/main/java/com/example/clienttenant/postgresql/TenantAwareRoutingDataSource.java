package com.example.clienttenant.postgresql;

import com.example.clienttenant.tenant.TenantContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantAwareRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // Obtenemos el tenantId desde el contexto
        String tenantId = TenantContext.getTenantId();
        if (tenantId == null) {
            tenantId="company-a-id";
        }

        return tenantId;
    }
}
