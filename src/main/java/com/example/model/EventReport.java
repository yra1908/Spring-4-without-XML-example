package com.example.model;

public class EventReport {
    
    private String eventName; 
    private String attendeeName;
    private String attendeePhone;
    private String attendeeEmail;
    
    public EventReport(String eventName, String attendeeName, 
            String attendeePhone, String attendeeEmail){
        
        this.eventName=eventName;
        this.attendeeName=attendeeName;
        this.attendeePhone=attendeePhone;
        this.attendeeEmail=attendeeEmail;
        
    }
    
    public String getAttendeeEmail() {
        return attendeeEmail;
    }
    public String getAttendeeName() {
        return attendeeName;
    }
    public String getAttendeePhone() {
        return attendeePhone;
    }
    public String getEventName() {
        return eventName;
    }
    public void setAttendeeEmail(String attendeeEmail) {
        this.attendeeEmail = attendeeEmail;
    }
    public void setAttendeeName(String attendeeName) {
        this.attendeeName = attendeeName;
    }
    public void setAttendeePhone(String attendeePhone) {
        this.attendeePhone = attendeePhone;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    

}
