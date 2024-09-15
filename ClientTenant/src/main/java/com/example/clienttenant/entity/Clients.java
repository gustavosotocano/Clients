package com.example.clienttenant.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import jdk.jfr.Name;
import lombok.*;

import jakarta.persistence.Id;


@Setter
@Getter
@Table(name = "clients")
@Entity
public class Clients {
    @Id
    @Column(name = "shared_key")
    private String sharedKey;
    private String email;
    @Column(name = "business_id")
    private String businessId;

}
