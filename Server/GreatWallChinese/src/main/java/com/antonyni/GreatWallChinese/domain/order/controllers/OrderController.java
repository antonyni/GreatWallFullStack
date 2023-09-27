package com.antonyni.GreatWallChinese.domain.order.controllers;

import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderCreationException;
import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderNotFoundException;
import com.antonyni.GreatWallChinese.domain.order.models.Order;
import com.antonyni.GreatWallChinese.domain.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/OrderRequests")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){this.orderService = orderService;}

    @PostMapping("")
    public ResponseEntity<String> createOrderRequest(@RequestBody Order order)  {
        try {
            Order savedOrder = orderService.create(order);
            ResponseEntity response = new ResponseEntity(savedOrder, HttpStatus.CREATED);
            return response;
        } catch (OrderCreationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<String> getAllOrdersRequest( )  {

        List<Order> orders = orderService.getAll();
        ResponseEntity response = new ResponseEntity(orders, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getOrderByIdRequest(@PathVariable String id)  {

        try{
            UUID uuid = UUID.fromString(id);
            Order order = orderService.getById(uuid);
            ResponseEntity response = new ResponseEntity(order, HttpStatus.OK);
            return response;
        } catch (OrderNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrderRequest(@PathVariable String id, @RequestBody Order orderDetail)  {

        try{
            UUID uuid = UUID.fromString(id);
            Order order = orderService.update(uuid, orderDetail);
            ResponseEntity response = new ResponseEntity(order, HttpStatus.OK);
            return response;
        } catch (OrderNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderByIdRequest(@PathVariable String id)  {
        UUID uuid = UUID.fromString(id);

        try{
            orderService.delete(uuid);
            ResponseEntity response = ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
            return response;
        } catch (OrderNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }





}
