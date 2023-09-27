package com.antonyni.GreatWallChinese.domain.foodOptions.services;

import com.antonyni.GreatWallChinese.domain.foodOptions.exceptions.FoodOptionsCreationException;
import com.antonyni.GreatWallChinese.domain.foodOptions.exceptions.FoodOptionsNotFoundException;
import com.antonyni.GreatWallChinese.domain.foodOptions.models.FoodOptions;

import java.util.List;
import java.util.UUID;

public interface FoodOptionsService {

    FoodOptions create(FoodOptions foodOptions) throws FoodOptionsCreationException;

    FoodOptions getById(UUID id) throws FoodOptionsNotFoundException;

    List<FoodOptions> getAll();

    FoodOptions update(UUID id, FoodOptions foodOptionsDetail) throws FoodOptionsNotFoundException;

    void delete(UUID id) throws FoodOptionsNotFoundException;
}

