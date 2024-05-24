package com.gml.service;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClientServiceTest {
    /*
    @Mock
    ClientJpaRepository clientJpaRepository;
    ClientServiceI clientServiceI;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        clientServiceI = new ClientService(clientJpaRepository);
    }

    @Test
    public void testFindAll(){
        Mockito.when( clientJpaRepository.findAll()).thenReturn(getClientDtos());
        var lstPerson= clientServiceI.findAll();

        Assertions.assertAll("test",
                () -> assertEquals(lstPerson.size(), 1, "Cantidad debe ser 1"),
                () -> assertEquals(lstPerson.get(0).bussinessId(), "segundoNombre", "Segundo Nombre no es igual"));
    }
    @Test
    public void testFindByEmail(){
        Mockito.when( clientJpaRepository.findByEmail("jdoe@gmail.com"))
                .thenReturn(Optional.of(getClient()));
        var lstPerson=clientServiceI.findByEmail("jdoe@gmail.com");

        Assertions.assertAll("test",
                () -> assertEquals(lstPerson.sharedKey(), "jdoe", "Shared key It's not equals"),
                () -> assertEquals(lstPerson.bussinessId(), "jhon doe", "Business ID It's not equals")
                );

    }

    @Test
    public void testFindByEmailThrow(){
        Mockito.when( clientJpaRepository.findByEmail("jdoes@gmail.com"))
.thenReturn(null);
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            clientServiceI.findByEmail("jdoes@gmail.com");
        });


        String expectedMessage = "Client Not Found";
        String actualMessage = exception.getMessage();
        Assertions.assertAll("test",
                () -> assertEquals (exception.getMessage(), "Client Not Found", "Exception error")


        );
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void testAddPerson(){
        ArgumentCaptor<Client> client = ArgumentCaptor.forClass(Client.class);
        Mockito.when( clientJpaRepository.save(client.capture()))
                .thenReturn(getClientReturn());


        var lstClient= clientServiceI.save(getClientDtoAdd());

        Assertions.assertAll("test",
                () -> assertEquals (String.valueOf(lstClient.getSharedKey()), "jdoe", "Shared key must be jdoe"),
                () -> assertEquals(lstClient.getBussinessId(), "jhon doe", "Business ID It's not equals")

        );
    }
    private Client getClientDtoAdd(){
        return Client.builder()

                .bussinessId("jhon doe")
                .email("gustavo@domain.com")
                .phone("11234567890")
                .added(new Date())
                .ended(Util.stringToDate("Jul-07-2013"))
                .started(Util.stringToDate("Jun-07-2013"))
                .build();

    }
    private Client getClientDto(){
        return Client.builder()
                .sharedKey("jdoe")
                .bussinessId("jhon doe")
                .email("gustavo@domain.com")
                .phone("11234567890")
                .added(Util.stringToDate("Jun-07-2013"))
                .ended(Util.stringToDate("Jul-07-2013"))
                .build();

    }


    private Client getClient(){
        return Client.builder()
                .sharedKey("jdoe")
                .bussinessId("jhon doe")
                .email("gustavo@domain.com")
                .phone("11234567890")
                .added(new Date())
                .ended(Util.stringToDate("Jul-07-2013"))
                .started(Util.stringToDate("Jun-07-2013"))
                .build();

    }

    private Client getClientReturn(){
        return Client.builder()
                .sharedKey("jdoe")
                .bussinessId("jhon doe")
                .email("gustavo@domain.com")
                .phone("11234567890")
                .added(Util.stringToDate("Jun-07-2013"))
                .ended(Util.stringToDate("Jul-07-2013"))
                .build();

    }
    private List<Client> getClientDtos() {
        List<Client> clients = new ArrayList<>();
        clients.add(Client.builder()
                .bussinessId("segundoNombre")
                .phone ("C")
                .ended(new Date())
                .started(new Date())
                .added(new Date())

                .build());
        return clients;
    }
    */

}
