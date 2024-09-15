package com.example.clienttenant.rest;

import com.example.clienttenant.entity.Clients;
import com.example.clienttenant.tenant.repository.jpa.ClientRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<Clients> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Clients getClientById(@PathVariable String id) {
        return clientRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Clients createClient(@RequestBody Clients client) {
        return clientRepository.save(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable String id) {
        clientRepository.deleteById(id);
    }
}

