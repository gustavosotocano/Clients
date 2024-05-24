package com.gml.infrastructure.adapters.outbound.persistence.repository;


import com.gml.infrastructure.adapters.outbound.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

   Optional <ClientEntity> findByEmail(String email);


}
