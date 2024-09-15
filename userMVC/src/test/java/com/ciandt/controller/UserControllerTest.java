package com.ciandt.controller;

import com.ciandt.dto.UserDto;
import com.ciandt.entity.User;
import com.ciandt.service.UserService;
import com.ciandt.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.LockModeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    void updateUserShouldReturn200WhenUserIsUpdated(String name) throws Exception {
        UserDto userToUpdate = new UserDto();
        userToUpdate.setFirstName(name);
        Long id = 1L;
        given(userService.updateUser(any(Long.class), any(UserDto.class))).willReturn(userToUpdate);

        String userJson = JsonUtil.serializeJson(userToUpdate);
        mockMvc.perform(MockMvcRequestBuilders.put("/my-project/user/" + id + "/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(mvcResult -> {
                    String responseBody = mvcResult.getResponse().getContentAsString();
                    Map<String, Object> responseMap = JsonUtil.deserializeJson(responseBody, HashMap.class);
                    Assertions.assertTrue((Boolean) responseMap.get("succeeded"));
                });
    }


    void updateUserShouldReturn400WhenUserServiceReturnsNull() throws Exception {
        UserDto userToUpdate = new UserDto();
        userToUpdate.setFirstName("Test User");
        Long id = 1L;
        given(userService.updateUser(any(Long.class), any(UserDto.class))).willReturn(null);

        String userJson = JsonUtil.serializeJson(userToUpdate);
        mockMvc.perform(MockMvcRequestBuilders.put("/my-project/user/" + id + "/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(mvcResult -> {
                    String responseBody = mvcResult.getResponse().getContentAsString();
                    Map<String, Object> responseMap = JsonUtil.deserializeJson(responseBody, HashMap.class);
                    Assertions.assertFalse((Boolean) responseMap.get("succeeded"));
                });
    }
    @Test
    public void concurrentTest() throws  InterruptedException {

        Runnable transaction1 = () -> {
            try {
                updateUserShouldReturn200WhenUserIsUpdated("1");

                // Simular un retraso en la primera transacción

                    Thread.sleep(2000); // Simula un retraso de 2 segundos



            } catch (Exception e) {
                System.out.println("No se pudo adquirir el bloqueo: " + e.getMessage());
            }
        };
        Runnable transaction2 = () -> {
            try {
                updateUserShouldReturn200WhenUserIsUpdated("2");
            } catch (Exception e) {
                System.out.println("No se pudo adquirir el bloqueo: " + e.getMessage());
            }
        };


        Thread thread1 = new Thread(transaction1);
        Thread thread2 = new Thread(transaction2);

        thread1.start();
        Thread.sleep(100); // Asegurarse de que la primera transacción adquiera el bloqueo antes que la segunda
        thread2.start();

        thread1.join();
        thread2.join();
    }
}