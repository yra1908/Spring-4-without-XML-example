package com.example.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Attendee;
import com.example.model.Event;
import com.example.service.AttendeeService;

@Controller
public class AttendeeController { 
    
    @Autowired
    private AttendeeService attendeeService; 

    @RequestMapping(value = "/attendee", method = RequestMethod.GET)
    public String displayAttendeePage(Model model){
        
        Attendee attendee = new Attendee();
        model.addAttribute("attendee", attendee);
        
        return "attendee";
    }
    
    @RequestMapping(value = "/attendee", method = RequestMethod.POST)
    public String processAttendee(@Valid Attendee attendee, BindingResult result, Model m, HttpSession session){
        
        System.out.println(attendee);
        
        if(result.hasErrors()){
            return "attendee";
        } else {
            Event event = (Event)session.getAttribute("event");
            
            attendee.setEvent(event);
            attendeeService.save(attendee);
        }
        
        return "redirect:index.html";
    }
}
