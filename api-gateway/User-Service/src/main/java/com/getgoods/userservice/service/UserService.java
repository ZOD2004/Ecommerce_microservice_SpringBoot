package com.getgoods.userservice.service;

import com.getgoods.userservice.Entity.User;

import java.util.List;

public interface UserService {

        User createUser(User user);

        User getUserById(Long id);

        List<User> getAllUsers();

        User updateUser(Long id, User updatedUser);

        void deleteUser(Long id);

        Boolean authenticate(String email, String password);

        User getUserByEmail(String email);

        User changePassword(String oldPassword, String newPassword,String mail);
}
