package com.example.clienttenant.tenant.repository.jpa;

import com.example.clienttenant.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ClientRepository extends JpaRepository<Clients, String> {
}
