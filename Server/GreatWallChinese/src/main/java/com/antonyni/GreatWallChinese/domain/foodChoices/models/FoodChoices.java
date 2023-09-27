package com.antonyni.GreatWallChinese.domain.foodChoices.models;

import com.antonyni.GreatWallChinese.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Slf4j
@Entity
public class FoodChoices extends BaseEntity {

    @NonNull
    @Column(name = "order_id")
    private UUID orderId;

    @NonNull
    private Integer quantity;

    @NonNull
    private String orderInformation;

    @NonNull
    private float price;


}
