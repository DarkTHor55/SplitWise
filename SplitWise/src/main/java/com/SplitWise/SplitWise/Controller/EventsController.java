package com.SplitWise.SplitWise.Controller;

import com.SplitWise.SplitWise.Model.Events;
import com.SplitWise.SplitWise.Service.impl.EventsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventsController {
    @Autowired
    private EventsServiceImpl eventsService;
    @PostMapping("/create/{groupId}")
    public ResponseEntity<String> createEvent(@PathVariable Long groupId, @RequestBody Events events) {
        Events event = eventsService.createNewEvent(groupId,events);
        return new ResponseEntity<String>("Event Created Successfully", HttpStatus.OK);
    }

    @GetMapping("/id/{eventId}")
    public ResponseEntity<Events> getEventById(@PathVariable Long eventId) {
        Events event = eventsService.getEvent(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping("/update/{eventId}")
    public ResponseEntity<String> updateEvent(@PathVariable Long eventId, @RequestBody Events events) {
        Events event = eventsService.updateEvent(events,eventId);
        if(event==null) return  new ResponseEntity<>("Event Not Found",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>("Event Updated Successfully", HttpStatus.OK);
    }
    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) {
        boolean isDeleted = eventsService.deleteEvent(eventId);
        if(!isDeleted) return  new ResponseEntity<>("Event Not Found",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>("Event Deleted Successfully", HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Events>> getAllEvents() {
        List<Events> events = eventsService.getAllEvetns();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    @GetMapping("/groupId/{groupId}")
    public ResponseEntity<List<Events>> allEventsByGroupId(@PathVariable Long groupId) {
        List<Events> events = eventsService.allEventsByGroupId(groupId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
