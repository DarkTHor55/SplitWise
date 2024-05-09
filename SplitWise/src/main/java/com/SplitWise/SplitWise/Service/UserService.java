package com.SplitWise.SplitWise.Service;

import com.SplitWise.SplitWise.Model.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);
    public User updateUser(User user,Long userId);
    public List<User> getAllUsers();
    public User getUserById(Long userId);
    public boolean deleteUserById(Long userId);

}
