package com.SplitWise.SplitWise.Controller;

import com.SplitWise.SplitWise.Model.User;
import com.SplitWise.SplitWise.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if(user==null) return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if(users==null) return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String>createNewUser(@RequestBody User user){
        User newUser = userService.createUser(user);
        if(newUser==null) return  new ResponseEntity<>("Email Already Exists",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>("User Added Successfully",HttpStatus.OK);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        boolean isDeleted = userService.deleteUserById(userId);
        if(!isDeleted) return  new ResponseEntity<>("User Not Found",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>("User Deleted Successfully",HttpStatus.OK);
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@RequestBody User user,@PathVariable Long userId){
        User updatedUser = userService.updateUser(user,userId);
        if(updatedUser==null) return  new ResponseEntity<>("User Not Found",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>("User Updated Successfully",HttpStatus.OK);
    }

}
