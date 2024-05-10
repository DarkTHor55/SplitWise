package com.SplitWise.SplitWise.Service.impl;

import com.SplitWise.SplitWise.Model.Events;
import com.SplitWise.SplitWise.Model.Group;
import com.SplitWise.SplitWise.Model.User;
import com.SplitWise.SplitWise.Repository.GroupRepository;
import com.SplitWise.SplitWise.Repository.UserRepository;
import com.SplitWise.SplitWise.Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group createGroup(Group group) {
        if (!isGroupExist(group.getGroupUserName())) {
            group.setGroupUserName((group.getGroupName() + group.getGroupId()).toString());
            groupRepository.save(group);
        }
        return null;
    }

    @Override
    public List<Group> allGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroup(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow();
    }

    @Override
    public List<User> allGroupMember(Long groupId) {
        List<User> users = new ArrayList<>();
        Group group = groupRepository.findById(groupId).orElseThrow();
        for (User user : group.getMembers()) {
            users.add(user);
        }
        return users;
    }


    @Override
    public Group updateGroup(Group group, Long groupId) {
        String username = usernameById(groupId);
        if (isGroupExist(username)) {
            group.setGroupUserName(username);
            group.setGroupId(groupId);
            groupRepository.deleteById(groupId);
            groupRepository.save(group);
        }
        return null;
    }

    @Override
    public boolean removeGroup(Long groupId) {
        String username = usernameById(groupId);
        if (!username.equals("null")) {

            if (isGroupExist(username)) {
                groupRepository.deleteById(groupId);
                return true;
            }
        }


        return false;
    }

    @Override
    public List<User> allUserByUserId(Long userId) {
        User user=userRepository.findById(userId).orElseThrow();
        Group group=groupRepository.findById(user.getGroup().getGroupId()).orElseThrow();
        List<User>groupMember=new ArrayList<>();
        for (User u:group.getMembers()){
            groupMember.add(u);
        }
        return groupMember;
    }


    @Override
    public Group addGroupsByUserId(List<Long>userIds,Long groupId){
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        for (Long userId : userIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

            user.setGroup(group);
            group.getMembers().add(user);
        }

        return groupRepository.save(group);
    }



    //private


    private boolean isUserExist(Long userId) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }

    //    private boolean isUserExistInGroup(Long userId){
//        List<Group>groups=groupRepository.findAll();
//        for (Group group : groups){
//            if(group.getMembers())
//        }
//        return false;
//    }
    private boolean isGroupExist(String username) {
        List<Group> groups = groupRepository.findAll();
        for (Group group : groups) {
            if (group.getGroupUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private String usernameById(Long groupId) {
        for (Group group : groupRepository.findAll()) {
            if (group.getGroupId() == groupId) {
                return group.getGroupUserName();
            }
        }
        return "null";
    }
}
