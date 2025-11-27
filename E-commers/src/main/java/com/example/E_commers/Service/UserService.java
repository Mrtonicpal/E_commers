package com.example.E_commers.Service;

import com.example.E_commers.Repository.UserRepository;
import com.example.E_commers.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.config.types.Password;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Get user by username

    public User getUser(String username){

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("user not found"));

    }

    //Update user profile

    public User updateUser(String username, User updatedUser){
        User user = getUser(username);
        user.setUsername(updatedUser.getUsername());
        user.setRole(updatedUser.getRole());
        return userRepository.save(user);
    }

    //Chang password

    public String changePassword(String username , String oldPass, String newPass){
        User user = getUser(username);
        if(!passwordEncoder.matches(oldPass, user.getPassword())){
            throw new RuntimeException("Old Password incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPass));
        userRepository.save(user);

        return "Password changed successfully";

    }

    //Delete account

    public String deleteUser(String username){
        User user = getUser(username);
        userRepository.delete(user);

        return "User deleted successfully";

    }

    //Admin  - list all user

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
