package com.antonyni.GreatWallChinese.domain.user.services;



import com.antonyni.GreatWallChinese.domain.user.exceptions.UserCreationException;
import com.antonyni.GreatWallChinese.domain.user.exceptions.UserNotFoundException;
import com.antonyni.GreatWallChinese.domain.user.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User create(User user) throws UserCreationException;

    User getById(UUID id) throws UserNotFoundException;

    User getByEmail(String email) throws UserNotFoundException;

    List<User> getAll();

    User update(UUID id, User user) throws UserNotFoundException;

    void delete(UUID id) throws UserNotFoundException;
}
