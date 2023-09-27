package com.antonyni.GreatWallChinese.domain.user.services;

import com.antonyni.GreatWallChinese.domain.order.models.Order;
import com.antonyni.GreatWallChinese.domain.user.exceptions.UserCreationException;
import com.antonyni.GreatWallChinese.domain.user.exceptions.UserNotFoundException;
import com.antonyni.GreatWallChinese.domain.user.models.User;
import com.antonyni.GreatWallChinese.domain.user.repos.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {
    @MockBean
    UserRepo userRepo;
    @Autowired
    UserService userService;
    private User mockUserInput;

    private User mockUserResponse;

    private List<Order> orderList;

    private UUID randomId;

    @BeforeEach
    public void setUp(){
        randomId = UUID.randomUUID();
        orderList = new ArrayList<>();
        mockUserInput = new User("bob","bob@gmail.com",orderList);
        mockUserInput.setId(randomId);
        mockUserResponse = new User("bob","bob@gmail.com",orderList);
        mockUserResponse.setId(randomId);

    }

    @Test
    @DisplayName("User Service: Create User - Success")
    public void createUserTestSuccess() throws UserCreationException {
        BDDMockito.doReturn(Optional.empty()).when(userRepo).findUserByUsername(mockUserInput.getUsername());
        BDDMockito.doReturn(Optional.empty()).when(userRepo).findUserByEmail(mockUserInput.getEmail());


        BDDMockito.doReturn(mockUserResponse).when(userRepo).save(mockUserInput);

        User user = userService.create(mockUserInput);

        Assertions.assertNotNull(user, "User created should not be null");
        Assertions.assertEquals(randomId, user.getId());
    }

    @Test
    @DisplayName("User Service: Create User - Fail (username exists)")
    public void createUserTestFail()  {
        BDDMockito.doReturn(Optional.of(mockUserInput)).when(userRepo).findUserByUsername(mockUserInput.getUsername());

        Assertions.assertThrows(UserCreationException.class, () -> {
            userService.create(mockUserInput);
        });
    }

    @Test
    @DisplayName("User Service: Get User by Id - Fail (Id doesn't exist)")
    public void getUserByIdFailTest(){
        BDDMockito.doReturn(Optional.empty()).when(userRepo).findUserById(randomId);

        Assertions.assertThrows(UserNotFoundException.class, ()->{
            User user = userService.getById(randomId);
        });
    }

    @Test
    @DisplayName("User Service: Update a User - Success")
    public void updateUserTest() throws UserNotFoundException {
        BDDMockito.doReturn(Optional.of(mockUserInput)).when(userRepo).findUserById(randomId);
        BDDMockito.doReturn(mockUserResponse).when(userRepo).save(mockUserResponse);

        User updatedUser = userService.update(randomId, mockUserResponse);

        Assertions.assertEquals(mockUserInput.toString(), updatedUser.toString());
    }

    @Test
    @DisplayName("User Service: Update a User - Fail (User doesn't exist)")
    public void updateUserTestFail()   {
        BDDMockito.doReturn(Optional.empty()).when(userRepo).findUserById(randomId);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.update(randomId, mockUserResponse);
        });
    }

    @Test
    @DisplayName("User Service: Delete a User - Success")
    public void deleteUserTest() throws UserNotFoundException {
        BDDMockito.doReturn(Optional.of(mockUserInput)).when(userRepo).findUserById(randomId);
        userService.delete(randomId);
    }

    @Test
    @DisplayName("User Service: Delete a User - Fail (No User of that ID)")
    public void deleteUserTestFail() throws UserNotFoundException {
        BDDMockito.doReturn(Optional.empty()).when(userRepo).findUserById(randomId);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.delete(randomId);
        });
    }









}
