package com.antonyni.GreatWallChinese.domain.foodOptions.controllers;

import com.antonyni.GreatWallChinese.domain.foodOptions.exceptions.FoodOptionsCreationException;
import com.antonyni.GreatWallChinese.domain.foodOptions.exceptions.FoodOptionsNotFoundException;
import com.antonyni.GreatWallChinese.domain.foodOptions.models.FoodOptions;
import com.antonyni.GreatWallChinese.domain.foodOptions.services.FoodOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/FoodOptionsRequests")
public class FoodOptionController {

    private FoodOptionsService foodOptionsService;

    @Autowired
    public FoodOptionController(FoodOptionsService foodOptionsService){this.foodOptionsService=foodOptionsService;}

    @PostMapping("")
    public ResponseEntity<String> createFoodOptionsRequest(@RequestBody FoodOptions foodOptions)  {
        try {
            FoodOptions savedFoodOption = foodOptionsService.create(foodOptions);
            ResponseEntity response = new ResponseEntity(savedFoodOption, HttpStatus.CREATED);
            return response;
        } catch (FoodOptionsCreationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<String> getAllFoodOptionsRequest( )  {

        List<FoodOptions> foodOptions = foodOptionsService.getAll();
        ResponseEntity response = new ResponseEntity(foodOptions, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getFoodOptionsByIdRequest(@PathVariable String id)  {

        try{
            UUID uuid = UUID.fromString(id);
            FoodOptions foodOptions = foodOptionsService.getById(uuid);
            ResponseEntity response = new ResponseEntity(foodOptions, HttpStatus.OK);
            return response;
        } catch (FoodOptionsNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFoodOptionsRequest(@PathVariable String id, @RequestBody FoodOptions foodOptionsDetail)  {

        try{
            UUID uuid = UUID.fromString(id);
            FoodOptions foodOptions = foodOptionsService.update(uuid, foodOptionsDetail);
            ResponseEntity response = new ResponseEntity(foodOptions, HttpStatus.OK);
            return response;
        } catch (FoodOptionsNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodOptionsByIdRequest(@PathVariable String id)  {
        UUID uuid = UUID.fromString(id);

        try{
            foodOptionsService.delete(uuid);
            ResponseEntity response = ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
            return response;
        } catch (FoodOptionsNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}
