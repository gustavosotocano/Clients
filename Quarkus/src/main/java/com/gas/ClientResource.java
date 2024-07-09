package com.gas;



import com.gas.dominio.Client;
import com.gas.service.ClientService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientResource {

    @Inject
    ClientService clientService;

    @GET
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GET
    @Path("/{id}")
    public Client getClientById(@PathParam("id") String id) {
        return clientService.getClientById(id);
    }

    @POST
    public Response addClient(@Valid Client client) {
        Client client1=clientService.addClient(client);
        //return Response.status(Response.Status.CREATED).build();

        return Response.status(Response.Status.CREATED).entity(client1).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateClient(@PathParam("id") String id, Client client) {
        client.setBusinessId(id);
        clientService.updateClient(client);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClient(@PathParam("id") Long id) {
        clientService.deleteClient(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/businessId")
    public Client getClientByBusinessId(@QueryParam("businessId") String businessId) {
        return clientService.getClientByBusinessId(businessId);
    }
}
