package com.antonyni.GreatWallChinese.domain.foodOptions.controllers;

import com.antonyni.GreatWallChinese.domain.order.models.Order;
import com.antonyni.GreatWallChinese.domain.user.controllers.UserController;
import com.antonyni.GreatWallChinese.domain.user.exceptions.UserCreationException;
import com.antonyni.GreatWallChinese.domain.user.exceptions.UserNotFoundException;
import com.antonyni.GreatWallChinese.domain.user.models.User;
import com.antonyni.GreatWallChinese.domain.user.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.antonyni.GreatWallChinese.domain.BaseControllerTest.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
public class FoodOptionsControllerTests {

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    private User mockUserInput;

    private User mockUserResponse;

    private List<Order> orderList;


    private UUID randomId;

    @BeforeEach
    public void setUp(){

        orderList = new ArrayList<>();

        randomId = UUID.randomUUID();
        mockUserInput = new User("bob","bob@gmail.com",orderList);
        mockUserInput.setId(randomId);
        mockUserResponse = new User("bob","bob@gmail.com",orderList);
        mockUserResponse.setId(randomId);

    }

    @Test
    @DisplayName("User Post: /api/v1/UserRequests - success")
    public void createUserRequestSuccess() throws Exception{
        BDDMockito.doReturn(mockUserResponse).when(userService).create(mockUserInput);

        mockMvc.perform(post("/api/v1/UserRequests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockUserInput)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(randomId.toString())));

    }

    @Test
    @DisplayName("User Post: /api/v1/UserRequests - user exists")
    public void createUserRequestFail() throws Exception {
        BDDMockito.doThrow(new UserCreationException("User already exists")).when(userService).create(mockUserInput);

        mockMvc.perform(post("/api/v1/UserRequests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockUserInput)))

                .andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("User Get: /api/v1/UserRequests - users exist")
    public void getAllUsers() throws Exception {
        List<User> users =new ArrayList<>();
        users.add(mockUserInput);
        BDDMockito.doReturn(users).when(userService).getAll();

        mockMvc.perform(get("/api/v1/UserRequests"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(randomId.toString())))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("User Get: /api/v1/UserRequests/{id} - success")
    public void getUserRequestByIdSuccess() throws Exception {
        BDDMockito.doReturn(mockUserResponse).when(userService).getById(randomId);

        mockMvc.perform(get("/api/v1/UserRequests/{id}",randomId))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(randomId.toString())));
    }

    @Test
    @DisplayName("GET /api/v1/UserRequests/{id} - Not Found")
    public void getUserByIdTestFail() throws Exception {
        BDDMockito.doThrow(new UserNotFoundException("Not Found")).when(userService).getById(randomId);
        mockMvc.perform(get("/api/v1/GameRequests/{id}", randomId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /api/v1/UserRequests/1 - Success")
    public void updateUserTestSuccess() throws Exception{


        BDDMockito.doReturn(mockUserResponse).when(userService).update(randomId, mockUserInput);

        mockMvc.perform(put("/api/v1/UserRequests/{id}", randomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockUserInput)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(randomId.toString())));
    }

    @Test
    @DisplayName("DELETE /api/v1/UserRequests/{id} - Success")
    public void deleteUserTestSuccess() throws Exception{
        BDDMockito.doNothing().when(userService).delete(randomId);
        mockMvc.perform(delete("/api/v1/UserRequests/{id}", randomId))
                .andExpect(status().isNoContent());
    }

}
