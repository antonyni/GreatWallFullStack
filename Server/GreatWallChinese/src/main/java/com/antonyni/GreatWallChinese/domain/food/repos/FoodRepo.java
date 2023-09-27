package com.antonyni.GreatWallChinese.domain.food.repos;

import com.antonyni.GreatWallChinese.domain.food.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FoodRepo extends JpaRepository<Food, UUID> {

    Optional<Food> findFoodByName(String name);

}
