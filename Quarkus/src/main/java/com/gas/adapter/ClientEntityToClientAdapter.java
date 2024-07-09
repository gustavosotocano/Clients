package com.gas.adapter;

public class ClientEntityToClientAdapter {
    public static com.gas.dominio.Client adapt(ClientEntity clientEntity) {
        com.gas.dominio.Client client = new com.gas.dominio.Client();
        client.setBusinessId(clientEntity.getSharedKey());

        client.setEmail(clientEntity.email);

        return client;
    }
}