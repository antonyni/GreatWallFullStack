package com.antonyni.GreatWallChinese.domain.foodChoices.services;

import com.antonyni.GreatWallChinese.domain.foodChoices.exceptions.FoodChoicesNotFoundException;
import com.antonyni.GreatWallChinese.domain.foodChoices.models.FoodChoices;

import java.util.List;
import java.util.UUID;

public interface FoodChoicesService {
    FoodChoices create(FoodChoices foodChoices) ;

    FoodChoices getById(UUID id) throws FoodChoicesNotFoundException;

    List<FoodChoices> getAll();

    FoodChoices update(UUID id, FoodChoices foodChoicesDetails) throws FoodChoicesNotFoundException;

    void delete(UUID id) throws FoodChoicesNotFoundException;
}
