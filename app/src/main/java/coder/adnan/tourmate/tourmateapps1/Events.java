package coder.adnan.tourmate.tourmateapps1;

public class Events {
    private String eventId;
   private String eventName;
   private String eventLocation;
   private String eventDestination;
   private String eventSDate;
    private String eventEDate;
   private double eventBudget;

    //public Events(String keyId, String eventName, String eventlocation, String eventdestination, String evetnDate, Double aDouble) {
    //}

    public Events() {
    }

    public Events(String eventId, String eventName, String eventLocation, String eventDestination, String eventSDate, double eventBudget) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDestination = eventDestination;
        this.eventSDate = eventSDate;
        this.eventBudget = eventBudget;
    }

    /*
        public Events(String eventId, String eventName, String eventSDate, String eventEDate) {
            this.eventId = eventId;
            this.eventName = eventName;
            this.eventSDate = eventSDate;
            this.eventEDate = eventEDate;
        }

        public Events(String eventName, String eventLocation, String eventDestination, String eventDate, double eventBudget) {
            this.eventName = eventName;
            this.eventLocation = eventLocation;
            this.eventDestination = eventDestination;
            this.eventSDate = eventDate;
            this.eventBudget = eventBudget;
        }

    */

    /*

    public Events(String eventId, String eventName, String eventLocation, String eventDestination, String eventSDate, String eventEDate, double eventBudget) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDestination = eventDestination;
        this.eventSDate = eventSDate;
        this.eventEDate = eventEDate;
        this.eventBudget = eventBudget;
    }
    */

    // get -sett next time if need


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDestination() {
        return eventDestination;
    }

    public void setEventDestination(String eventDestination) {
        this.eventDestination = eventDestination;
    }

    public String getEventSDate() {
        return eventSDate;
    }

    public void setEventSDate(String eventSDate) {
        this.eventSDate = eventSDate;
    }

    public String getEventEDate() {
        return eventEDate;
    }

    public void setEventEDate(String eventEDate) {
        this.eventEDate = eventEDate;
    }

    public double getEventBudget() {
        return eventBudget;
    }

    public void setEventBudget(double eventBudget) {
        this.eventBudget = eventBudget;
    }
}
