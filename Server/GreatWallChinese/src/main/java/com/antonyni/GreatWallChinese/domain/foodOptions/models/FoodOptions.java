package com.antonyni.GreatWallChinese.domain.foodOptions.models;

import com.antonyni.GreatWallChinese.domain.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Slf4j
@Entity
public class FoodOptions extends BaseEntity {

    @NonNull
    String name;

    @NonNull
    float smallPrice;

    @NonNull
    float largePrice;

    @NonNull
    String description;

    @NonNull
    Set<String> sections;
}
