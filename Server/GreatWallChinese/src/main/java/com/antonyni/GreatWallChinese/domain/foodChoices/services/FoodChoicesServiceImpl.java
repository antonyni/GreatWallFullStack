package com.antonyni.GreatWallChinese.domain.foodChoices.services;

import com.antonyni.GreatWallChinese.domain.foodChoices.exceptions.FoodChoicesNotFoundException;
import com.antonyni.GreatWallChinese.domain.foodChoices.models.FoodChoices;
import com.antonyni.GreatWallChinese.domain.foodChoices.repos.FoodChoicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoodChoicesServiceImpl implements FoodChoicesService{

    FoodChoicesRepo foodChoicesRepo;

    @Autowired
    public FoodChoicesServiceImpl(FoodChoicesRepo foodChoicesRepo){this.foodChoicesRepo = foodChoicesRepo;}

    @Override
    public FoodChoices create(FoodChoices foodChoices) {
        return foodChoicesRepo.save(foodChoices);
    }

    @Override
    public FoodChoices getById(UUID id) throws FoodChoicesNotFoundException {
        Optional<FoodChoices> foodChoicesOptional = foodChoicesRepo.findById(id);
        if(foodChoicesOptional.isEmpty())
            throw new FoodChoicesNotFoundException("Food choice not found");
        return foodChoicesOptional.get();
    }

    @Override
    public List<FoodChoices> getAll() {
        return foodChoicesRepo.findAll();
    }

    @Override
    public FoodChoices update(UUID id, FoodChoices foodChoicesDetails) throws FoodChoicesNotFoundException {

        FoodChoices foodChoices = getById(id);
        foodChoices.setOrderId(foodChoicesDetails.getOrderId());
        foodChoices.setPrice(foodChoicesDetails.getPrice());
        foodChoices.setQuantity(foodChoicesDetails.getQuantity());
        foodChoices.setOrderInformation(foodChoicesDetails.getOrderInformation());
        return foodChoicesRepo.save(foodChoices);
    }

    @Override
    public void delete(UUID id) throws FoodChoicesNotFoundException {

        FoodChoices foodChoices = getById(id);
        foodChoicesRepo.delete(foodChoices);

    }
}
