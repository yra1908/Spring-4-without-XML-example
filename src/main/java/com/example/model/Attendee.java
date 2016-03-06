package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.example.view.Phone;

@Entity
public class Attendee {
    
    @Id
    @GeneratedValue
    private Long id;
       
    
    @Size(min=2, max=30)
    private String name;
    
    @NotEmpty @Email
    private String emailAddress;
    
    @NotEmpty @Phone //custom validation annotation
    private String phone; 
    
    @ManyToOne
    private Event event;
    
    public String getEmailAddress() {
        return emailAddress;
    }
    public Event getEvent() {
        return event;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    

}
