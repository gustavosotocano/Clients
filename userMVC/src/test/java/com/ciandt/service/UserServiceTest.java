package com.ciandt.service;

import com.ciandt.dto.UserDto;
import com.ciandt.entity.User;
import com.ciandt.exception.ResourceNotFoundException;
import com.ciandt.repository.UserJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {
    @Mock
    UserJpaRepository userJpaRepository;
    UserServiceI userServiceI;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        userServiceI = new UserService(userJpaRepository);
    }

    @Test
    public void testFindAll(){
        Mockito.when(userJpaRepository.findAll()).thenReturn(getClientDtos());
        var lstPerson= userServiceI.findAll();

        assertAll("test",
                () -> assertEquals(lstPerson.size(), 1, "Cantidad debe ser 1"),
                () -> assertEquals(lstPerson.get(0).getFirstName(), "jhon", "Segundo Nombre no es igual"));
    }
    @Test
    public void testFindByEmail(){
        Mockito.when(userJpaRepository.findByEmailAddress("jdoe@gmail.com"))
                .thenReturn(Optional.ofNullable(getUser()));
        var lstPerson= userServiceI.findByEmailAddress("jdoe@gmail.com");

        assertAll("test",
                () -> assertEquals(lstPerson.getFirstName(), "jhon", "Shared key It's not equals"),
                () -> assertEquals(lstPerson.getLastName(), "doe", "Business ID It's not equals")
                );

    }

    @Test
    public void testFindByEmailThrow(){
        Mockito.when(userJpaRepository.findByEmailAddress("jdoes@gmail.com"))
.thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            userServiceI.findByEmailAddress("jdoes@gmail.com");
        });


        String expectedMessage = "User Not Found";
        String actualMessage = exception.getMessage();
        assertAll("test",
                () -> assertEquals (exception.getMessage(), "User Not Found", "Exception error")


        );
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void testAddPerson(){
        ArgumentCaptor<User> client = ArgumentCaptor.forClass(User.class);
        Mockito.when(userJpaRepository.save(client.capture()))
                .thenReturn(getClientReturn());

        var lstClient= userServiceI.createUser(getUserDtoAdd());

        assertAll("test",
                () -> assertEquals (String.valueOf(lstClient.getFirstName()), "jhon", "first name must be jhon"),
                () -> assertEquals(lstClient.getLastName(), "doe", "last name ID It's not equals")

        );
    }
    private UserDto getUserDtoAdd(){
        return UserDto.builder()
                .id(12345L)
                .firstName("jhon")
                .lastName("doe")
                .emailAddress("gustavo@domain.com")
                .build();

    }
    private UserDto getUserDto(){
        return UserDto.builder()
                .firstName("jhon")
                .lastName("doe")
                .emailAddress("gustavo@domain.com")

                .build();

    }


    private User getUser(){
        return User.builder()
                .id(12345L)
                .firstName("jhon")
                .lastName("doe")
                .emailAddress("gustavo@domain.com")
                .build();
    }

    private UserDto getUserDtoUpdate() {
        return UserDto.builder()
                .firstName("NewFirstName")
                .lastName("NewLastName")
                .emailAddress("newmail@domain.com")
                .build();
    }

    private User getUpdatedUser() {
        return User.builder()
                .id(12345L)
                .firstName("NewFirstName")
                .lastName("NewLastName")
                .emailAddress("newmail@domain.com")
                .build();
    }



private User getClientReturn(){
        return User.builder()
                .id(12345L)
                .firstName("jhon")
                .lastName("doe")
                .emailAddress("gustavo@domain.com")
                .build();

    }
    private List<User> getClientDtos() {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                          .id(12345L)
                          .firstName("jhon")
                          .lastName("doe")
                          .emailAddress("gustavo@domain.com")

                .build());
        return users;
    }


    // unit tests for updateUser method.
    @Test
    public void testUpdateUser_Success() throws InterruptedException {
        UserDto newDetails = UserDto.builder()
                .firstName("NewFirstName")
                .lastName("NewLastName")
                .emailAddress("newmail@domain.com")
                .build();
        User updatedUser = User.builder()
                .id(12345L)
                .firstName("NewFirstName")
                .lastName("NewLastName")
                .emailAddress("newmail@domain.com")
                .build();
        Mockito.when(userJpaRepository.findUserForUpdate(12345L)).thenReturn(Optional.of(getUser()));
        Mockito.when(userJpaRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);

        Runnable transaction1 = () -> {

            UserDto lockedUser = userServiceI.updateUser(12345L, newDetails);
            // User lockedUser = userJpaRepository.findUserForUpdate(getUser().getId()).orElseThrow();
            lockedUser.setFirstName("John Updatedzx");
//            userJpaRepository.save(lockedUser);

            // Simular un retraso en la primera transacción
            try {
                Thread.sleep(2000); // Simula un retraso de 2 segundos
                assertAll("update test",
                          () -> assertEquals(lockedUser.getFirstName(), "NewFirstName", "First name is not updated"),
                          () -> assertEquals(lockedUser.getLastName(), "NewLastName", "Last name is not updated"),
                          () -> assertEquals(lockedUser.getEmailAddress(), "newmail@domain.com", "Email is not updated"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            userJpaRepository.flush(); // Forzar el guardado de los cambios y la liberación del bloqueo // Forzar el guardado de los cambios y la liberación del bloqueo
        };

        AtomicReference<UserDto> userDto = null;
        Runnable transaction2 = () -> {

            userDto.set(userServiceI.updateUser(12345L, newDetails));
           // User lockedUser = userJpaRepository.findUserForUpdate(getUser().getId()).orElseThrow();
            userDto.get().setFirstName("John Updatedz");
//            userJpaRepository.save(lockedUser);

            // Simular un retraso en la primera transacción
            try {
                Thread.sleep(2000); // Simula un retraso de 2 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            userJpaRepository.flush(); // Forzar el guardado de los cambios y la liberación del bloqueo
        };


        Thread thread1 = new Thread(transaction1);
        Thread thread2 = new Thread(transaction2);

        thread1.start();
        Thread.sleep(100); // Asegurarse de que la primera transacción adquiera el bloqueo antes que la segunda
        thread2.start();

        thread1.join();
        thread2.join();

    }

@Test
public void testUpdateUser_UserNotFound() {
    UserDto newDetails = UserDto.builder()
            .firstName("NewFirstName")
            .lastName("NewLastName")
            .emailAddress("newmail@domain.com")
            .build();


    Mockito.when(userJpaRepository.findUserForUpdate(1L)).thenReturn(Optional.empty());
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
        userServiceI.updateUser(1L, newDetails);
    });
    String expectedMessage = "User Not Found";
    String actualMessage = exception.getMessage();
    assertAll("update test",
                         () -> assertEquals(exception.getMessage(), expectedMessage, "Exception error"));
}
}
