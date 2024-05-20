package com.gml.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@WebMvcTest
public class ClientControllerTest {
    /*

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientJpaRepository clientJpaRepository;

    @MockBean
    private ClientService clientService;

    @Test
    public void findAllTest() throws Exception {

        List<Client> personas = getClientDtos();
        Mockito.when(clientService.findAll()).thenReturn(personas);


        //  RequestBuilder request= MockMvcRequestBuilders
        var result = mockMvc.perform(get("/v1/client"))
                .andExpect(status().isOk())
                .andReturn();
        List<Client> objeto = stringToObject(result);
        Assertions.assertAll("test",
                () -> assertEquals(objeto.size(), 1, "Cantidad debe ser 1"),
                () -> assertEquals(objeto.get(0).bussinessId() , "John Doe", "Business Id no es igual"));


    }

    private List<Client> stringToObject(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
    }

    private List<Client> getClientDtos() {
        List<Client> clients = new ArrayList<>();
        clients.add(
                new Client( "jdoe","John Doe" ,"jdoe@gmail.com",
        "1111111111",  Util.stringToDate( "Jun-07-2013"), null,Util.stringToDate("Jul-07-2013"))
        );

return clients;
 }



    @Test
    public void findAllNotFoundTest() throws Exception {
        Mockito.when(clientService.findAll())
                .thenReturn(Collections.emptyList());
        //  RequestBuilder request= MockMvcRequestBuilders
        var result = mockMvc.perform(get("/v1/client"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Assertions.assertAll("test",
                () -> assertEquals(HttpStatus.OK.value(),
                        result.getResponse().getStatus(), "Esperamos una lista vacia"));
    }

    @Test
    public void findbyEmailNfotFoundTest() throws Exception {

        /*
        Mockito.when(personService.findByEmail("C")).thenReturn(getPersonaDtos());
        //  RequestBuilder request= MockMvcRequestBuilders
        var result = mockMvc.perform(get("/persona/C/dcto/234"))

                .andExpect(status().isOk())
                .andReturn();
        List<ClienteDto> objeto = stringToObject(result);
        Assertions.assertAll("test",
                () -> assertEquals(objeto.get(0).getName(), "segundoNombre", "No tiene personas Registradas"));


    }

    @Test
    public void findbyEmailNotFoundTest() throws Exception {


        Mockito.when(clientService.findByEmail("jdsoe@gmail.com"))
                .thenThrow(new ResourceNotFoundException("001", "Client Not Found"));
        //  RequestBuilder request= MockMvcRequestBuilders
        var result = mockMvc.perform(get("/v1/client/email/jdsoe@gmail.com"))

                .andExpect(status().isNotFound())
                .andReturn();

        Assertions.assertAll("test",
                () -> assertEquals(((ResourceNotFoundException) Objects.requireNonNull(result.getResolvedException())).getMessage()
                        , "Client Not Found", "Client Not Found"));


    }

    @Test
    public void addClientTest() throws Exception {
        String json= """
                {
                "bussinessId":"jhon doe",
                "email":"gustavo@domain.com",
                "phone":"adolfo",
                "started":"2024-05-25",
                "ended":"2024-05-25"
                }
                """;
         mockMvc.perform(post("/v1/client").contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(status().isCreated())
                .andReturn();

    }
    @Test
    public void addClientTrowTest() throws Exception {
        String json= """
                {
                "email":"gustavo@domain.com",
                "phone":"adolfo",
                "added":"2024-05-01",
                "started":"2024-05-25",
                "ended":"2024-05-25",
                "name":"cra 1a113 #72-84"
                }
                """;
       mockMvc.perform(post("/v1/client").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();

    }

 */
}
