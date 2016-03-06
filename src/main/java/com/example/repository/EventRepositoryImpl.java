package com.example.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.example.model.Event;
import com.example.model.EventReport;

//@Repository("eventRepository")       //SpringData support
public class EventRepositoryImpl {
//implements EventRepository {    //suppress it for SpringData enabling*/

    @PersistenceContext
    private EntityManager em;
    
    
    public Event save(Event event) {
          
        if(event.getId()==null){
            em.persist(event);      //save object
            
            em.flush(); 
        } else {
            event=em.merge(event);  //update object No need for flush()
        }        
        
        return event;
    }    
    
    public List<Event> findAll() {
        
        //Query query = em.createNamedQuery(Event.FIND_ALL_EVENTS);         //same story but have to add suppress warning
        TypedQuery<Event> query = em.createNamedQuery(Event.FIND_ALL_EVENTS, Event.class);
       
        return query.getResultList();
    }

    
    public List<EventReport> findAllReports() {
        
        /*Query query = em.createQuery("Select new com.example.model.EventReport(e.name, a.name, a.phone, a.emailAddress) "
                + "from Event e, Attendee a where e.id=a.event.id" );*/
        
        TypedQuery<EventReport> query = em.createNamedQuery(Event.FIND_EVENT_REPORTS, EventReport.class);  
        
        return query.getResultList();
    }

}
