package com.gml;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "channels")
public class Channel {
    @Id
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

}