package com.gas.client.infrastructure.company;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Objects;



@Getter
@Setter
public abstract class BaseEntity  implements Persistable<String> {

    @Id
    @Column("shared_key")
    private String id;

    @Override
    public boolean isNew() {
        return Objects.nonNull(id);
    }
}
