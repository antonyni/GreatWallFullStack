package com.antonyni.GreatWallChinese.domain.order.repos;

import com.antonyni.GreatWallChinese.domain.order.models.Order;
import com.antonyni.GreatWallChinese.domain.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order,UUID> {
    Optional<Order> findOrderByUserUUID(UUID userUUID);
}
