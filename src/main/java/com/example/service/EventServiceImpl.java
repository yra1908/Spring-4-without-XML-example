package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Event;
import com.example.model.EventReport;
import com.example.repository.EventRepository;

@Service("eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    
    @Override
    @Transactional
    public Event save(Event event) {         
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public List<Event> findAll() {
        
        return eventRepository.findAll();
    }

    @Override
    public List<EventReport> findAllReports() {
        
        return eventRepository.findAllReports();
    }

}
