package com.SplitWise.SplitWise.Service;

import com.SplitWise.SplitWise.Model.Events;
import com.SplitWise.SplitWise.Model.Group;

import java.util.List;

public interface EventsService {
    public Events createNewEvent(Long groupId,Events events);
    public Events getEvent(Long eventId);
    public Events updateEvent(Events event,Long eventId);
    public Events addNewMember(Long userId);
    public boolean deleteEvent(Long eventId);
    public List<Events> getAllEvetns();
    public List<Events> allEventsByGroupId(Long groupId);

}
