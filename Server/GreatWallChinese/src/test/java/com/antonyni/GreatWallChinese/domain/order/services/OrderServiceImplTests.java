package com.antonyni.GreatWallChinese.domain.order.services;

import com.antonyni.GreatWallChinese.domain.foodChoices.models.FoodChoices;
import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderCreationException;
import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderNotFoundException;
import com.antonyni.GreatWallChinese.domain.order.models.Order;
import com.antonyni.GreatWallChinese.domain.order.repos.OrderRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class OrderServiceImplTests {

    @MockBean
    OrderRepo orderRepo;

    @Autowired
    OrderService orderService;

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
    @DisplayName("Order Service: Create Order - Success")
    public void createOrderTestSuccess() throws OrderCreationException {
        BDDMockito.doReturn(Optional.empty()).when(orderRepo).findById(mockOrderInput.getId());
        BDDMockito.doReturn(mockOrderResponse).when(orderRepo).save(mockOrderInput);
        Order order = orderService.create(mockOrderInput);
        Assertions.assertNotNull(order, "Order created should not be null");
        Assertions.assertEquals(randomId, order.getId());
    }


    @Test
    @DisplayName("Order Service: Get Order by Id - Fail (Id doesn't exist)")
    public void getOrderByIdFailTest() {
        BDDMockito.doReturn(Optional.empty()).when(orderRepo).findById(randomId);

        Assertions.assertThrows(OrderNotFoundException.class, () -> {
            Order order = orderService.getById(randomId);
        });
    }

    @Test
    @DisplayName("Order Service: Update an Order - Success")
    public void updateOrderTest() throws OrderNotFoundException {
        BDDMockito.doReturn(Optional.of(mockOrderInput)).when(orderRepo).findById(randomId);
        BDDMockito.doReturn(mockOrderResponse).when(orderRepo).save(mockOrderResponse);

        Order updatedOrder = orderService.update(randomId, mockOrderResponse);

        Assertions.assertEquals(mockOrderInput.toString(), updatedOrder.toString());
    }

    @Test
    @DisplayName("Order Service: Update an Order - Fail (Order doesn't exist)")
    public void updateOrderTestFail() {
        BDDMockito.doReturn(Optional.empty()).when(orderRepo).findById(randomId);

        Assertions.assertThrows(OrderNotFoundException.class, () -> {
            orderService.update(randomId, mockOrderResponse);
        });
    }

    @Test
    @DisplayName("Order Service: Delete an Order - Success")
    public void deleteOrderTest() throws OrderNotFoundException {
        BDDMockito.doReturn(Optional.of(mockOrderInput)).when(orderRepo).findById(randomId);
        orderService.delete(randomId);
    }

    @Test
    @DisplayName("Order Service: Delete an Order - Fail (No Order of that ID)")
    public void deleteOrderTestFail() throws OrderNotFoundException {
        BDDMockito.doReturn(Optional.empty()).when(orderRepo).findById(randomId);

        Assertions.assertThrows(OrderNotFoundException.class, () -> {
            orderService.delete(randomId);
        });
    }





}
