package com.SplitWise.SplitWise.Service.impl;

import com.SplitWise.SplitWise.Model.User;
import com.SplitWise.SplitWise.Repository.UserRepository;
import com.SplitWise.SplitWise.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean checkUserByEmail(String email){
        List<User>users=userRepository.findAll();

        for (User user : users){
            System.out.println(user.getEmail());
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    @Override
    public User createUser(User user) {
        if(checkUserByEmail(user.getEmail())){
            return null;

        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPayable(0.0);

        return userRepository.save(user);

    }
    private boolean isUserExist(Long userId){
        List<User>users=userRepository.findAll();
        for (User user : users){
            if (user.getUserId()==userId){
                return true;
            }
        }
        return false;
    }

    @Override
    public User updateUser(User user, Long userId) {

        if(isUserExist(userId)){
            user.setUserId(userId);
            userRepository.deleteById(userId);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setPayable(0.0);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public boolean deleteUserById(Long userId) {
        if(isUserExist(userId)){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
