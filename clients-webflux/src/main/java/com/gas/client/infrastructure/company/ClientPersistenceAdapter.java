package com.gas.client.infrastructure.company;

import com.gas.client.domain.client.ClientDto;
import com.gas.client.domain.client.ClientPersistencePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ClientPersistenceAdapter implements ClientPersistencePort {


    private final ClientRepository clientRepository;

    public ClientPersistenceAdapter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Flux<ClientDto> getAllClients() {
        return clientRepository.findAll()
                .map(this::mapToClientDto);
    }

    @Override
    public Mono<ClientDto> saveClient(ClientDto clientDto) {


   return clientRepository.save(ClientEntity.builder()
                                        .id(clientDto.getSharedKey())
                                        .businessId(clientDto.getBusinessId())
                                        .started(clientDto.getStarted())
                                        .ended(clientDto.getEnded())
                                        .phone(clientDto.getPhone())
                                        .email(clientDto.getEmail())
                                        .added(clientDto.getAdded())
                                        .build())
                  .map(this::mapToClientDto);
    }

    @Override
    public Mono<ClientDto> getClientByEmail(String email){
      return clientRepository.findByEmail(email)
              .map(this::mapToClientDto);
    }

    @Override
    public Mono<ClientDto> getClientById(String sharedKey) {
        return clientRepository.findById(sharedKey)
        .map(this::mapToClientDto);
    }

    protected ClientDto mapToClientDto(ClientEntityDTO clientEntity) {
        return ClientDto.builder()
                .sharedKey(clientEntity.getId())
                .businessId(clientEntity.getBusinessId())
                .added(clientEntity.getAdded())
                .ended(clientEntity.getEnded())
                .started(clientEntity.getStarted())
                .phone(clientEntity.getPhone())
                .email(clientEntity.getEmail())

                .build();
    }
    protected ClientDto mapToClientDto(ClientEntity  clientEntity) {
        return ClientDto.builder()
                .sharedKey(clientEntity.getId())
                .businessId(clientEntity.getBusinessId())
                .added(clientEntity.getAdded())
                .ended(clientEntity.getEnded())
                .started(clientEntity.getStarted())
                .phone(clientEntity.getPhone())
                .email(clientEntity.getEmail())

                .build();
    }

}
