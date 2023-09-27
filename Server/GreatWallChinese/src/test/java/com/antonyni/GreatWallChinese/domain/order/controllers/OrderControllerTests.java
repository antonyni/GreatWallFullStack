package com.antonyni.GreatWallChinese.domain.order.controllers;

import com.antonyni.GreatWallChinese.domain.foodChoices.models.FoodChoices;
import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderCreationException;
import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderNotFoundException;
import com.antonyni.GreatWallChinese.domain.order.models.Order;
import com.antonyni.GreatWallChinese.domain.order.services.OrderService;

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

@WebMvcTest(OrderController.class)
@ExtendWith(SpringExtension.class)
public class OrderControllerTests {
    @MockBean
    private OrderService orderService;

    @Autowired
    MockMvc mockMvc;

    private Order mockOrderInput;

    private Order mockOrderResponse;

    private List<FoodChoices> foodChoicesList;


    private UUID randomId;

    @BeforeEach
    public void setUp(){

        foodChoicesList = new ArrayList<>();

        UUID id1 = UUID.randomUUID();

        randomId = UUID.randomUUID();
        mockOrderInput = new Order(id1,10.20f,foodChoicesList);
        mockOrderInput.setId(randomId);
        mockOrderResponse = new Order(id1,10.20f,foodChoicesList);
        mockOrderResponse.setId(randomId);

    }

    @Test
    @DisplayName("User Post: /api/v1/OrderRequests - success")
    public void createOrderRequestSuccess() throws Exception{
        BDDMockito.doReturn(mockOrderResponse).when(orderService).create(mockOrderInput);

        mockMvc.perform(post("/api/v1/OrderRequests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockOrderInput)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(randomId.toString())));

    }

    @Test
    @DisplayName("User Post: /api/v1/OrderRequests - user exists")
    public void createUserRequestFail() throws Exception {
        BDDMockito.doThrow(new OrderCreationException("Order already exists")).when(orderService).create(mockOrderInput);

        mockMvc.perform(post("/api/v1/OrderRequests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockOrderInput)))

                .andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("User Get: /api/v1/OrderRequests - orders exist")
    public void getAllOrders() throws Exception {
        List<Order> orders =new ArrayList<>();
        orders.add(mockOrderInput);
        BDDMockito.doReturn(orders).when(orderService).getAll();

        mockMvc.perform(get("/api/v1/OrderRequests"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(randomId.toString())))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("User Get: /api/v1/OrderRequests/{id} - success")
    public void getOrderRequestByIdSuccess() throws Exception {
        BDDMockito.doReturn(mockOrderInput).when(orderService).getById(randomId);

        mockMvc.perform(get("/api/v1/OrderRequests/{id}",randomId))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(randomId.toString())));
    }

    @Test
    @DisplayName("GET /api/v1/OrderRequests/{id} - Not Found")
    public void getOrderByIdTestFail() throws Exception {
        BDDMockito.doThrow(new OrderNotFoundException("Not Found")).when(orderService).getById(randomId);
        mockMvc.perform(get("/api/v1/OrderRequests/{id}", randomId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /api/v1/OrderRequests/1 - Success")
    public void updateOrderTestSuccess() throws Exception{


        BDDMockito.doReturn(mockOrderResponse).when(orderService).update(randomId, mockOrderInput);

        mockMvc.perform(put("/api/v1/OrderRequests/{id}", randomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockOrderInput)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(randomId.toString())));
    }

    @Test
    @DisplayName("DELETE /api/v1/OrderRequests/{id} - Success")
    public void deleteOrderTestSuccess() throws Exception{
        BDDMockito.doNothing().when(orderService).delete(randomId);
        mockMvc.perform(delete("/api/v1/OrderRequests/{id}", randomId))
                .andExpect(status().isNoContent());
    }
}
