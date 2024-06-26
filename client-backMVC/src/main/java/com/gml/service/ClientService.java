package com.gml.service;

import com.gml.dto.ClienteDto;
import com.gml.entity.Client;
import com.gml.exception.RequestException;
import com.gml.exception.ResourceNotFoundException;
import com.gml.repository.ClientJpaRepository;
import com.gml.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientService implements ClientServiceI {


    private final ClientJpaRepository clientJpaRepository;


    public ClientService(ClientJpaRepository clientJpaRepository) {
        this.clientJpaRepository = clientJpaRepository;
    }

    @Override
    public List<ClienteDto> findAll() {


        return clientJpaRepository.findAll().stream()
                .map(Utils::toClientDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDto findByEmail(String email) {


        var clients = clientJpaRepository.findByEmail(email);
        if (Objects.isNull(clients)) {
            throw new ResourceNotFoundException("001", "Client Not Found");
        }
        var personLst = clientJpaRepository.findByEmail(email);
        return Utils.toClientDto(personLst);
                /*.stream()
                .map(this::toClientDto)
                .collect(Collectors.toList());*/
    }

    @Override
    public ClienteDto findBySharedKey(String sharedKey) {


        var clients = clientJpaRepository.findById(sharedKey);
        if (clients.isEmpty()) {
            throw new ResourceNotFoundException("V-102", "Cliente No Encontrado");
        }

        return Utils.toClientDto(clients.get());

    }

    @Override
    public Client save(ClienteDto clienteDto) {

        var clients = clientJpaRepository
                .findByEmail(clienteDto.getEmail());
        if (Objects.nonNull(clients)) {
            throw new RequestException("004", "Client is already created");
        }
        String[] names = clienteDto.getBussinessId().split(" ");
        String lastName = "";
        if (names.length == 1) {
            lastName = names[0];
        } else if (names.length == 2) {
            lastName = names[1];
        } else if (names.length > 2) {
            lastName = names[2];
        }
        clienteDto.setAdded(new Date());
        String initial = names[0].substring(0, 1);
        clienteDto.setSharedKey((initial + lastName).toLowerCase(Locale.ROOT));

        return clientJpaRepository.save(Utils.toClient(clienteDto));
    }

    @Override
    public Client update(ClienteDto clienteDto) {

        var client = clientJpaRepository
                .findById(clienteDto.getSharedKey());

        if (client.isEmpty()) {

            throw new RequestException("005", "Client Does not exist");
        }
        var clientEmail = clientJpaRepository
                .findByEmail(clienteDto.getEmail());

        if (Objects.nonNull(clientEmail) && (!client.get().getSharedKey().equals(clientEmail.getSharedKey()))) {
            throw new RequestException("006", "Email belongs to another client");
        }


        return clientJpaRepository.save(Utils.toClientUpdate(clienteDto, client.get()));
    }

}
