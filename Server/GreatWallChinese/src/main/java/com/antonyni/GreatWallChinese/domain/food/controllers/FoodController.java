package com.antonyni.GreatWallChinese.domain.food.controllers;

import com.antonyni.GreatWallChinese.domain.food.exceptions.FoodCreationException;
import com.antonyni.GreatWallChinese.domain.food.exceptions.FoodNotFoundException;
import com.antonyni.GreatWallChinese.domain.food.models.Food;
import com.antonyni.GreatWallChinese.domain.food.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/FoodRequests")
public class FoodController {

    private FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService){this.foodService = foodService;}

    @PostMapping("")
    public ResponseEntity<String> createFoodRequest(@RequestBody Food food)  {
        try {
            Food savedFood = foodService.create(food);
            ResponseEntity response = new ResponseEntity(savedFood, HttpStatus.CREATED);
            return response;
        } catch (FoodCreationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<String> getAllFoodsRequest( )  {

        List<Food> foods = foodService.getAll();
        ResponseEntity response = new ResponseEntity(foods, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getFoodByIdRequest(@PathVariable String id)  {

        try{
            UUID uuid = UUID.fromString(id);
            Food food = foodService.getById(uuid);
            ResponseEntity response = new ResponseEntity(food, HttpStatus.OK);
            return response;
        } catch (FoodNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFoodRequest(@PathVariable String id, @RequestBody Food foodDetail)  {

        try{
            UUID uuid = UUID.fromString(id);
            Food food = foodService.update(uuid, foodDetail);
            ResponseEntity response = new ResponseEntity(food, HttpStatus.OK);
            return response;
        } catch (FoodNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodByIdRequest(@PathVariable String id)  {
        UUID uuid = UUID.fromString(id);

        try{
            foodService.delete(uuid);
            ResponseEntity response = ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
            return response;
        } catch (FoodNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
