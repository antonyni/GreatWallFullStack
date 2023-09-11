package com.antonyni.GreatWallChinese.domain.user.controllers;

import com.antonyni.GreatWallChinese.domain.user.exceptions.UserCreationException;
import com.antonyni.GreatWallChinese.domain.user.exceptions.UserNotFoundException;
import com.antonyni.GreatWallChinese.domain.user.models.User;
import com.antonyni.GreatWallChinese.domain.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/UserRequests")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<String> createUserRequest(@RequestBody User user)  {
        try {
            User savedUser = userService.create(user);
            ResponseEntity response = new ResponseEntity(savedUser, HttpStatus.CREATED);
            return response;
        } catch (UserCreationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());

        }
    }

    @GetMapping("")
    public ResponseEntity<User> getAllUsersRequest( )  {

        List<User> users = userService.getAll();
        ResponseEntity response = new ResponseEntity(users, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserByIdRequest(@PathVariable String id)  {

        try{
            UUID uuid = UUID.fromString(id);
            User user = userService.getById(uuid);
            ResponseEntity response = new ResponseEntity(user, HttpStatus.OK);
            return response;
        } catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserRequest(@PathVariable String id, @RequestBody User userDetail)  {

        try{
            UUID uuid = UUID.fromString(id);
            User user = userService.update(uuid, userDetail);
            ResponseEntity response = new ResponseEntity(user, HttpStatus.OK);
            return response;
        } catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserByIdRequest(@PathVariable String id)  {
        UUID uuid = UUID.fromString(id);

        try{
            userService.delete(uuid);
            ResponseEntity response = ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
            return response;
        } catch (UserNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }







}
