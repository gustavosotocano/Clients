package com.example.clienttenant.tenant;

import com.mongodb.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Document("tenants")
public class Tenant{
        @Id
        String id;
        String name;
        @Indexed
        String tenantId;  // ID del tenant
        Instant validTo;
        String postgresDbName; // Nombre de la base de datos PostgreSQL
        String apiKey;
        @Nullable
        String agentPublicKey;
        List<String> envNames;


}
