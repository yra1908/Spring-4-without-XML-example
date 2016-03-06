package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Event;

@RestController
public class EventsReportController {
    
    @RequestMapping("/events") // http://localhost:8080/MVC4Example/events.json
    public List<Event> getEvents(){
        
        List<Event> events = new ArrayList<>();
        
        Event e1 = new Event();
        e1.setName("Java User Group");
        Event e2 = new Event();
        e2.setName("Angular User Group");
        Event e3 = new Event();
        e3.setName("C# User Group");
        
        events.add(e1);
        events.add(e2);
        events.add(e3);
        
        return events;
        
    }

}
