package com.example.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.model.Attendee;

//@Repository("attendeeRepository")        // SpringData support
public class AttendeeRepositoryImpl {
//implements AttendeeRepository {  //suppress for SpringData support

    @PersistenceContext
    private EntityManager em;
    
//    public Attendee save(Attendee attendee) {
//        
//        em.persist(attendee);
//
//        em.flush();
//        
//        return attendee;
//    }

}
