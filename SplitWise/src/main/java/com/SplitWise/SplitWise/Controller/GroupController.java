package com.SplitWise.SplitWise.Controller;

import com.SplitWise.SplitWise.Model.Group;
import com.SplitWise.SplitWise.Model.User;
import com.SplitWise.SplitWise.Service.impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("groups")
public class GroupController {
    @Autowired
    private GroupServiceImpl groupService;

    @GetMapping("/all")
    public ResponseEntity<List<Group>> getAllGroups(){
        List<Group> groups = groupService.allGroups();
        if(groups==null) return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(groups,HttpStatus.OK);
    }
    @GetMapping("/id/{groupId}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long groupId){
        Group group=groupService.getGroup(groupId);
        if(group==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(group,HttpStatus.OK);
    }
    @GetMapping("/members/userId/{groupId}")
    public ResponseEntity<List<User>> allGroupMemberByUserId(@PathVariable Long groupId){
            List<User> members=groupService.allGroupMember(groupId);
            return new ResponseEntity<>(members,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody Group group){
       Group g = groupService.createGroup(group);
//        if(g==null) return new ResponseEntity<>("Not Created",HttpStatus.OK);
        return new ResponseEntity<String>("Group Created Successfully",HttpStatus.OK);
    }
    @PutMapping("/update/{groupId}")
    public ResponseEntity<String> updateGroup(@RequestBody Group group,@PathVariable Long groupId){

        Group group1 = groupService.updateGroup(group,groupId);
        return new ResponseEntity<>("updated",HttpStatus.OK);
    }
    @DeleteMapping("/delete/{groupId}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long groupId){
        boolean isDeleted = groupService.removeGroup(groupId);
        if(!isDeleted) return  new ResponseEntity<>("Group Not Found",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>("Group Deleted Successfully",HttpStatus.OK);
    }
    @GetMapping("/all/user/{userId}")
    public ResponseEntity<List<User>> allUserByUserId(@PathVariable Long userId){
        List<User> users = groupService.allUserByUserId(userId);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @PostMapping("/add/user/{groupId}")
    public ResponseEntity<String> addGroupsByUserId(@RequestBody List<Long>userIds,@PathVariable Long groupId){
        Group group = groupService.addGroupsByUserId(userIds,groupId);
        return new ResponseEntity<String>("Group Added Successfully",HttpStatus.OK);
    }


}
