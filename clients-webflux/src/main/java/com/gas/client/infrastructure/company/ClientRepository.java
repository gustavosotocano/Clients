package com.gas.client.infrastructure.company;


import org.springframework.data.r2dbc.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface ClientRepository extends ReactiveCrudRepository<ClientEntity, String> {


     @Query("""
            SELECT *
            FROM CLIENT tp
            WHERE EMAIL = :email
            """)
     Mono<ClientEntityDTO> findByEmail(@Param("email") String email);
}


