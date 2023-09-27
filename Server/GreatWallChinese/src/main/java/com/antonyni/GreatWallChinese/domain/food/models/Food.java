package com.antonyni.GreatWallChinese.domain.food.models;

import com.antonyni.GreatWallChinese.domain.common.BaseEntity;
import com.antonyni.GreatWallChinese.domain.foodOptions.models.FoodOptions;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Slf4j
@Entity
public class Food extends BaseEntity {
    @NonNull
    String name;

    @NonNull
    String specialRequests;

    @NonNull
    Float smallPrice;

    @NonNull
    Float largePrice;

    @NonNull
    Boolean hasSmall;

    @NonNull
    String section;

    @NonNull
    @ManyToMany
    List<FoodOptions> foodOptionsList;

}
