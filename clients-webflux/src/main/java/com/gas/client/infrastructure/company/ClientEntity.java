package com.gas.client.infrastructure.company;


import lombok.*;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Getter
@Setter
@Table(name = "Client")
public class ClientEntity  extends BaseEntity {

    private String email;
    private String phone;
    private LocalDateTime added;
    private LocalDate started;
    private LocalDate ended;
    @Column( "business_id")
    private String businessId;
    private LocalDateTime updated;

    @Builder
    public ClientEntity(String id, String businessId,String email, String phone, LocalDateTime added, LocalDate started,
                        LocalDate ended
                        ) {
        super.setId(id);
        this.businessId = businessId;
        this.email = email;
        this.phone = phone;
        this.added = added;
        this.ended = ended;
        this.started = started;
    }
    @Override
    @Column("shared_key")
    public String getId() {
        return super.getId();
    }
}
