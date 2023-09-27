package com.antonyni.GreatWallChinese.domain.foodChoices.controllers;

import com.antonyni.GreatWallChinese.domain.food.models.Food;
import com.antonyni.GreatWallChinese.domain.foodChoices.exceptions.FoodChoicesNotFoundException;
import com.antonyni.GreatWallChinese.domain.foodChoices.models.FoodChoices;
import com.antonyni.GreatWallChinese.domain.foodChoices.services.FoodChoicesService;
import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderCreationException;
import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderNotFoundException;
import com.antonyni.GreatWallChinese.domain.order.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/FoodChoicesRequests")
public class FoodChoicesController {
    private FoodChoicesService foodChoicesService;

    @Autowired
    public FoodChoicesController(FoodChoicesService foodChoicesService){this.foodChoicesService = foodChoicesService;}

    @PostMapping("")
    public ResponseEntity<String> createOrderRequest(@RequestBody FoodChoices foodChoices)  {

            FoodChoices savedFoodChoices = foodChoicesService.create(foodChoices);
            ResponseEntity response = new ResponseEntity(savedFoodChoices, HttpStatus.CREATED);
            return response;

    }

    @GetMapping("")
    public ResponseEntity<String> getAllFoodChoicesRequest( )  {

        List<FoodChoices> foodChoices = foodChoicesService.getAll();
        ResponseEntity response = new ResponseEntity(foodChoices, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getFoodChoicesByIdRequest(@PathVariable String id)  {

        try{
            UUID uuid = UUID.fromString(id);
            FoodChoices foodChoices = foodChoicesService.getById(uuid);
            ResponseEntity response = new ResponseEntity(foodChoices, HttpStatus.OK);
            return response;
        } catch (FoodChoicesNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFoodChoicesRequest(@PathVariable String id, @RequestBody FoodChoices foodChoicesDetail)  {

        try{
            UUID uuid = UUID.fromString(id);
            FoodChoices foodChoices = foodChoicesService.update(uuid, foodChoicesDetail);
            ResponseEntity response = new ResponseEntity(foodChoices, HttpStatus.OK);
            return response;
        } catch (FoodChoicesNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodChociesByIdRequest(@PathVariable String id)  {
        UUID uuid = UUID.fromString(id);

        try{
            foodChoicesService.delete(uuid);
            ResponseEntity response = ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
            return response;
        } catch (FoodChoicesNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
