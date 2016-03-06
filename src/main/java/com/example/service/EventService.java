package com.example.service;

import java.util.List;

import com.example.model.Event;
import com.example.model.EventReport;

public interface EventService {
    
    Event save(Event event);

    List<Event> findAll();

    List<EventReport> findAllReports();

}
