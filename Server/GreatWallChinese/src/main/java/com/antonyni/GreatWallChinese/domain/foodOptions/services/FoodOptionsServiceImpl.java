package com.antonyni.GreatWallChinese.domain.foodOptions.services;

import com.antonyni.GreatWallChinese.domain.foodOptions.exceptions.FoodOptionsCreationException;
import com.antonyni.GreatWallChinese.domain.foodOptions.exceptions.FoodOptionsNotFoundException;
import com.antonyni.GreatWallChinese.domain.foodOptions.models.FoodOptions;
import com.antonyni.GreatWallChinese.domain.foodOptions.repos.FoodOptionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoodOptionsServiceImpl implements FoodOptionsService{

    FoodOptionsRepo foodOptionsRepo;

    @Autowired
    public FoodOptionsServiceImpl(FoodOptionsRepo foodOptionsRepo){this.foodOptionsRepo = foodOptionsRepo;}

    @Override
    public FoodOptions create(FoodOptions foodOptions) throws FoodOptionsCreationException {
        if(foodOptionsRepo.findFoodOptionsByName(foodOptions.getName()).isPresent())
            throw new FoodOptionsCreationException("Food option: " + foodOptions.getName() + " exists");
        return foodOptionsRepo.save(foodOptions);
    }

    @Override
    public FoodOptions getById(UUID id) throws FoodOptionsNotFoundException {
        Optional<FoodOptions> foodOptionsOptional = foodOptionsRepo.findById(id);
        if(foodOptionsOptional.isEmpty())
            throw new FoodOptionsNotFoundException("Food option with id: " + id + " not found");
        return foodOptionsOptional.get();
    }

    @Override
    public List<FoodOptions> getAll() {
        return foodOptionsRepo.findAll();
    }

    @Override
    public FoodOptions update(UUID id, FoodOptions foodOptionsDetail) throws FoodOptionsNotFoundException {
        FoodOptions foodOptions = getById(id);
        foodOptions.setName(foodOptionsDetail.getName());
        foodOptions.setDescription(foodOptionsDetail.getDescription());
        foodOptions.setLargePrice(foodOptionsDetail.getLargePrice());
        foodOptions.setSmallPrice(foodOptionsDetail.getSmallPrice());
        foodOptions.setSections(foodOptionsDetail.getSections());
        return foodOptionsRepo.save(foodOptions);
    }

    @Override
    public void delete(UUID id) throws FoodOptionsNotFoundException {
        FoodOptions foodOptions = getById(id);
        foodOptionsRepo.delete(foodOptions);
    }
}
