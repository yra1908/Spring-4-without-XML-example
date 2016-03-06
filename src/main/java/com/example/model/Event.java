package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="events")//will create table events for event entity
@NamedQueries({
    @NamedQuery(name=Event.FIND_EVENT_REPORTS, query="Select new com.example.model.EventReport"
            + "(e.name, a.name, a.phone, a.emailAddress) "
            + "from Event e, Attendee a where e.id=a.event.id"),
    @NamedQuery(name=Event.FIND_ALL_EVENTS, query="Select e from Event e")
})
public class Event {
    
    public final static String FIND_EVENT_REPORTS="findEventReports";
    public final static String FIND_ALL_EVENTS="findAllEvents";
    
    @Id
    @GeneratedValue
    @Column(name="EVENT_ID")
    private Long id; 
    
    @Column(name="EVENT_NAME")
    private String name;
    
    @OneToMany(mappedBy="event", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Attendee> attendee = new ArrayList<>();
    
    public List<Attendee> getAttendee() {
        return attendee;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAttendee(List<Attendee> attendee) {
        this.attendee = attendee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    } 

}
