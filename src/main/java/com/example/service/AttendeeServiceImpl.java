package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Attendee;
import com.example.repository.AttendeeRepository;

@Service("attendeeService")
public class AttendeeServiceImpl implements AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;
    
    @Override
    @Transactional
    public Attendee save(Attendee attendee) {
        
        attendee = attendeeRepository.save(attendee);
        
        return attendee;
    }

}
