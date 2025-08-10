package com.getgoods.userservice.service.ServiceImple;

import com.getgoods.userservice.Entity.User;
import com.getgoods.userservice.repository.UserRepository;
import com.getgoods.userservice.service.UserService;
import com.getgoods.userservice.util.PasswordWrongException;
import com.getgoods.userservice.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImple implements UserService {

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User>user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        else{
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User>user = userRepository.findById(id);
        if (user.isPresent()) {
            User newUser = user.get();
            newUser.setName(updatedUser.getName());
            newUser.setEmail(updatedUser.getEmail());
            newUser.setPassword(updatedUser.getPassword());
            return userRepository.save(newUser);
        }
        else{
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User>user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public Boolean authenticate(String email, String password) {
        Optional<User>user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get().getPassword().equals(password);
        }
        else{
            throw new UserNotFoundException("User not found with email: " + email);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User>user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        else{
            throw new UserNotFoundException("User not found with email: " + email);
        }
    }

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImple(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public User changePassword(String oldPassword, String newPassword,String mail) {
        Optional<User>user=userRepository.findByEmail(mail);
        if(user.isPresent()){
            if(user.get().getPassword().equals(oldPassword)){
                user.get().setPassword(newPassword);
                return userRepository.save(user.get());
            }
            else{
                throw new PasswordWrongException("Incorrect Old Password");
            }
        }
        else{
            throw new UserNotFoundException("No user with email: "+mail);
        }
    }

}
