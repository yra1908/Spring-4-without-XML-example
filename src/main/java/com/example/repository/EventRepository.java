package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Event;
import com.example.model.EventReport;

@Repository("eventRepository")
public interface EventRepository extends JpaRepository<Event, Long>{
    
    //Event save (Event event);         //SpringData  

    //List<Event> findAll();      //SpringData 
    
    @Query("Select new com.example.model.EventReport(e.name, a.name, a.phone, a.emailAddress) "
            + "from Event e, Attendee a where e.id=a.event.id")
    List<EventReport> findAllReports();

}
