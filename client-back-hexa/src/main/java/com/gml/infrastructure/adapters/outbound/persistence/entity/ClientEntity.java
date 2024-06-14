package com.gml.infrastructure.adapters.outbound.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import jakarta.persistence.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
@Builder
public class ClientEntity {
    @Id
    private String sharedKey;
    private String businessId;
    private String email;
    private String phone;
    private Date added;
    private Date started;
    private Date ended;
    private Date updated;
}
