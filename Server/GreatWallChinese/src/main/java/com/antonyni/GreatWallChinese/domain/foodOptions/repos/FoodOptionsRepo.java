package com.antonyni.GreatWallChinese.domain.foodOptions.repos;

import com.antonyni.GreatWallChinese.domain.foodOptions.models.FoodOptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FoodOptionsRepo extends JpaRepository<FoodOptions, UUID> {

    Optional<FoodOptions> findFoodOptionsByName(String name);

}
