package com.gas.client.infrastructure.company;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter

public class ClientEntityDTO extends BaseEntity {
    @Column("shared_key")
    private String sharedKey;
    private String email;
    private String phone;
    private LocalDateTime added;
    private LocalDate started;
    private LocalDate ended;
    @Column( "business_id")
    private String businessId;
    private LocalDateTime updated;

    @Builder
    public ClientEntityDTO(String sharedKey, String businessId, String email, String phone, LocalDateTime added, LocalDate started,
                           LocalDate ended
                        ) {
        super.setId(sharedKey );
        this.businessId = businessId;
        this.email = email;
        this.phone = phone;
        this.added = added;
        this.ended = ended;
        this.started = started;
    }
}
