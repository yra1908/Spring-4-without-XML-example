package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Event;
import com.example.model.EventReport;
import com.example.service.EventService;

@Controller
@SessionAttributes("event")     //add event object as session attribute
public class EventController {
    
    @Autowired
    private EventService eventService; 
    
    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String displayEventPage(Model model, HttpSession session){
        
        Event event =(Event) session.getAttribute("event");
        
        if(event == null){
             event= new Event();
             event.setName("Java User Group");
        }        
        
        model.addAttribute("event", event);
        
        return "event";
    }
    
    
    //method save new event or update existing one from session
    @RequestMapping(value = "/event", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#event, 'createEvent')") //adding permission to create goal
    public String processEvent(@ModelAttribute("event") Event event){ 
        System.out.println(event.getName());
        
        eventService.save(event);
        
        return "redirect:index.html";
    }
    
    @RequestMapping(value = "/getEvents", method = RequestMethod.GET)
    public String getEvents(Model model){
        
        List<Event> events = eventService.findAll();
        
        model.addAttribute("events", events);
        
        return "getEvents";
    }
    
    @RequestMapping(value = "/getEventReports", method = RequestMethod.GET)
    public String getEventReports(Model model){
        
        List<EventReport> eventReports = eventService.findAllReports();
        
        model.addAttribute("eventReports", eventReports);
        
        return "getEventsReports";
    }
    

}
