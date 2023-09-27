package com.antonyni.GreatWallChinese.domain.foodChoices.repos;

import com.antonyni.GreatWallChinese.domain.foodChoices.models.FoodChoices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FoodChoicesRepo extends JpaRepository<FoodChoices, UUID> {

}
