package com.antonyni.GreatWallChinese.domain.order.services;

import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderCreationException;
import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderNotFoundException;
import com.antonyni.GreatWallChinese.domain.order.models.Order;
import com.antonyni.GreatWallChinese.domain.order.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepo orderRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo){this.orderRepo = orderRepo;}
    @Override
    public Order create(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Order getById(UUID id) throws OrderNotFoundException {
        Optional<Order> orderOptional = orderRepo.findById(id);
        if(orderOptional.isEmpty())
            throw new OrderNotFoundException("Order with id: " + id + " not found");
        return orderOptional.get();
    }

    @Override
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    @Override
    public Order update(UUID id, Order orderDetails) throws OrderNotFoundException {
        Order order = getById(id);
        order.setItems(orderDetails.getItems());
        return orderRepo.save(order);
    }

    @Override
    public void delete(UUID id) throws OrderNotFoundException {
        Order order = getById(id);
        orderRepo.delete(order);
    }
}
