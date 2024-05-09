package com.SplitWise.SplitWise.Service;

import com.SplitWise.SplitWise.Model.Group;
import com.SplitWise.SplitWise.Model.User;

import java.util.List;

public interface GroupService {
    public Group createGroup(Group group);
    public List<Group>allGroups();
    public Group getGroup(Long groupId);
    public List<User> allGroupMember(Long groupId);
    public Group updateGroup(Group group,Long groupId);
    public boolean removeGroup(Long groupId);
    public List<User> allUserByUserId(Long userId);
    public Group addGroupsByUserId(List<Long>userIds,Long groupId);




}
