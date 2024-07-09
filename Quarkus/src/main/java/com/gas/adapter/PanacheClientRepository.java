package com.gas.adapter;

import com.gas.dominio.Client;
import com.gas.port.ClientRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class PanacheClientRepository implements ClientRepository, PanacheRepository<ClientEntity> {

    @Override
    public List<Client> getAllClients() {
        return   ClientEntity.listAll().stream()
                .filter(Objects::nonNull)
                .map(entity -> ((ClientEntity) entity).toDomain())
                .collect(Collectors.toList());
    }

    @Override
    public Client getClientById(String id) {
        ClientEntity entity = ClientEntity.findById(id);

        return entity != null ? entity.toDomain() : null;
    }

    @Override
    public Client getClientByBusinessId(String businessId) {
        ClientEntity entity = ClientEntity.find("businessId", businessId).firstResult();

        return entity != null ? entity.toDomain() : null;
    }

    @Override
    public Client getClientByField(String fieldName,String fieldValue) {
        ClientEntity entity = ClientEntity.find(fieldName, fieldValue).firstResult();

        return entity != null ? entity.toDomain() : null;
    }


    @Override
    public Client addClient( Client client) {
        ClientEntity clientEntity=ClientEntity.fromDomain(client);

        ClientEntity.persist(clientEntity);
        return getClientByBusinessId(clientEntity.businessId);
    }

    @Override
    public void updateClient(Client client) {
        ClientEntity entity = ClientEntity.findById(client.getBusinessId());
        if (entity != null) {
            entity.setBusinessId(client.getBusinessId());
            entity.setEmail(client.getEmail());
            entity.persist();
        }
    }

    @Override
    public void deleteClient(Long id) {
        ClientEntity.deleteById(id);
    }

}
