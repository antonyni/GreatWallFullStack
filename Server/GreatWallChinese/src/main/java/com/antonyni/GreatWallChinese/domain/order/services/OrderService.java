package com.antonyni.GreatWallChinese.domain.order.services;

import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderCreationException;
import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderNotFoundException;
import com.antonyni.GreatWallChinese.domain.order.models.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order create(Order order) throws OrderCreationException;

    Order getById(UUID id) throws OrderNotFoundException;

    List<Order> getAll();

    Order update(UUID id, Order orderDetails) throws OrderNotFoundException;

    void delete(UUID id) throws OrderNotFoundException;
}
