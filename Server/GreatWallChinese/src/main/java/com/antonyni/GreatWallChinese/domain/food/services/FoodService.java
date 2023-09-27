package com.antonyni.GreatWallChinese.domain.food.services;

import com.antonyni.GreatWallChinese.domain.food.exceptions.FoodCreationException;
import com.antonyni.GreatWallChinese.domain.food.exceptions.FoodNotFoundException;
import com.antonyni.GreatWallChinese.domain.food.models.Food;
import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderCreationException;
import com.antonyni.GreatWallChinese.domain.order.exceptions.OrderNotFoundException;
import com.antonyni.GreatWallChinese.domain.order.models.Order;

import java.util.List;
import java.util.UUID;

public interface FoodService {
    Food create(Food food) throws FoodCreationException;

    Food getById(UUID id) throws FoodNotFoundException;

    List<Food> getAll();

    Food update(UUID id, Food foodDetails) throws FoodNotFoundException;

    void delete(UUID id) throws FoodNotFoundException;
}
