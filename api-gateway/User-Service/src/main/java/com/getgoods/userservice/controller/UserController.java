package com.getgoods.userservice.controller;

import com.getgoods.userservice.Entity.User;
import com.getgoods.userservice.service.ServiceImple.UserServiceImple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImple userServiceImple;

    public UserController(UserServiceImple userServiceImple) {
        this.userServiceImple = userServiceImple;
    }
    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody User user){
        return new ResponseEntity<>(userServiceImple.createUser(user),HttpStatus.CREATED);
    }

    @PutMapping("/change/password")
    public ResponseEntity<User> changePassword(@RequestParam String oldPassword,
                                               @RequestParam String newPassword,
                                               @RequestParam String mail
    ){
        return new ResponseEntity<>(userServiceImple.changePassword(oldPassword,newPassword,mail),HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userServiceImple.getUserById(id),HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userServiceImple.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity<User> getUserById(@PathVariable String email){
        return new ResponseEntity<>(userServiceImple.getUserByEmail(email),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user){
        return new  ResponseEntity<>(userServiceImple.updateUser(id,user),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>("User with id: "+id+" deleted",HttpStatus.OK);
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Boolean> authenticate(@RequestParam String email, @RequestParam String password){
        return new ResponseEntity<>(userServiceImple.authenticate(email, password),HttpStatus.OK);
    }

}
