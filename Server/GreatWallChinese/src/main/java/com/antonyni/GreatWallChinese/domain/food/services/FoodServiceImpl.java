package com.antonyni.GreatWallChinese.domain.food.services;

import com.antonyni.GreatWallChinese.domain.food.exceptions.FoodCreationException;
import com.antonyni.GreatWallChinese.domain.food.exceptions.FoodNotFoundException;
import com.antonyni.GreatWallChinese.domain.food.models.Food;
import com.antonyni.GreatWallChinese.domain.food.repos.FoodRepo;
import com.antonyni.GreatWallChinese.domain.foodOptions.models.FoodOptions;
import com.antonyni.GreatWallChinese.domain.foodOptions.repos.FoodOptionsRepo;
import com.antonyni.GreatWallChinese.domain.foodOptions.services.FoodOptionsService;
import com.antonyni.GreatWallChinese.domain.foodOptions.services.FoodOptionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoodServiceImpl implements FoodService {

    FoodRepo foodRepo;

    FoodOptionsService foodOptionsService;


    @Autowired
    public FoodServiceImpl(FoodRepo foodRepo, FoodOptionsService foodOptionsService){
        this.foodRepo = foodRepo;
        this.foodOptionsService = foodOptionsService;
    }

    @Override
    public Food create(Food food) throws FoodCreationException {
        if(foodRepo.findFoodByName(food.getName()).isPresent())
            throw new FoodCreationException("Food called " + food.getName() +" already exists");
        return foodRepo.save(food);
    }

    @Override
    public Food getById(UUID id) throws FoodNotFoundException {
        Optional<Food> foodOptional = foodRepo.findById(id);
        if(foodOptional.isEmpty())
            throw new FoodNotFoundException("Food with id: " + id + " not found");
        return foodOptional.get();
    }

    @Override
    public List<Food> getAll() {

        List<FoodOptions> foodOptionsList = foodOptionsService.getAll();
        populateFoodOptionsList(foodOptionsList);
        return foodRepo.findAll();

    }

    @Override
    public Food update(UUID id, Food foodDetails) throws FoodNotFoundException {
        Food food = getById(id);
        food.setFoodOptionsList(foodDetails.getFoodOptionsList());
        food.setSection(foodDetails.getSection());
        food.setHasSmall(foodDetails.getHasSmall());
        food.setSmallPrice(foodDetails.getSmallPrice());
        food.setLargePrice(foodDetails.getLargePrice());
        food.setName(foodDetails.getName());
        return foodRepo.save(food);
    }

    @Override
    public void delete(UUID id) throws FoodNotFoundException {
        Food food = getById(id);
        foodRepo.delete(food);
    }

    public void populateFoodOptionsList(List<FoodOptions> allFoodOptions) {
        List<Food> foods = foodRepo.findAll();

        for(Food food : foods){
        for (FoodOptions foodOption : allFoodOptions) {
            if (foodOption.getSections().contains(food.getSection())) {
                food.getFoodOptionsList().add(foodOption);
            }
        }
        }
    }
}
