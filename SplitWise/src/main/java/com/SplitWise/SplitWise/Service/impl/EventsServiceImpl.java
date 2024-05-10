package com.SplitWise.SplitWise.Service.impl;

import com.SplitWise.SplitWise.Model.Events;
import com.SplitWise.SplitWise.Model.Group;
import com.SplitWise.SplitWise.Model.User;
import com.SplitWise.SplitWise.Repository.EventsRepository;
import com.SplitWise.SplitWise.Service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsServiceImpl  implements EventsService {
    @Autowired
    private GroupServiceImpl groupService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EventsRepository eventsRepository;

    @Override
    public Events createNewEvent(Long groupId,Events event) {
        Group group=groupService.getGroup(groupId);

            List<User>members=groupService.allGroupMember(groupId);

            double totalExpenses=event.getTotalExpense();
            int totalMember=group.getMembers().size();
            double ExpensePerMember=(totalExpenses/totalMember);
            for (User user : members) {
               double pay=user.getPayable()+ExpensePerMember;
                user.setPayable(pay);
                userService.updateUser(user,user.getUserId());
            }

            event.setAttendees(members);
        event.setGroup(group);
        System.out.println(event.getEventDescription()+"................................................................");
        return eventsRepository.save(event);

    }

    @Override
    public Events getEvent(Long eventId) {
        return eventsRepository.findById(eventId).orElseThrow(null);
    }

    @Override
    public Events updateEvent(Events event, Long eventId) {
        Events events=eventsRepository.findById(eventId).orElseThrow(null);
        if(events!=null){
            events.setEventName(event.getEventName());
            events.setTotalExpense(event.getTotalExpense());
            events.setEventDate(event.getEventDate());
            events.setEventDescription(event.getEventDescription());
            events.setAttendees(event.getAttendees());
            eventsRepository.deleteById(eventId);
            return eventsRepository.save(events);
        }

        return null;
    }

    @Override
    public Events addNewMember(Long userId) {

        return null;
    }

    @Override
    public boolean deleteEvent(Long eventId) {
        Events events=eventsRepository.findById(eventId).orElseThrow(null);
        if(events!=null){
            eventsRepository.deleteById(eventId);
            return true;
        }
        return false;
    }

    @Override
    public List<Events> getAllEvetns() {
        return eventsRepository.findAll();
    }

    @Override
    public List<Events> allEventsByGroupId(Long groupId) {
        Group group=groupService.getGroup(groupId);
        List<Events> events=eventsRepository.findAllByGroup(group);
        return events;
    }
}
