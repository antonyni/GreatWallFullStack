package com.antonyni.GreatWallChinese.domain.order.models;

import com.antonyni.GreatWallChinese.domain.common.BaseEntity;
import com.antonyni.GreatWallChinese.domain.food.models.Food;
import com.antonyni.GreatWallChinese.domain.foodChoices.models.FoodChoices;
import com.antonyni.GreatWallChinese.domain.user.models.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Slf4j
@Table(name="Orders")
public class Order extends BaseEntity {

    @NonNull
    @Column(name = "user_id")
    private UUID userUUID;


    @NonNull
    private Float total;

    @NonNull
    private String status;

    @NonNull
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<FoodChoices> items;
}
