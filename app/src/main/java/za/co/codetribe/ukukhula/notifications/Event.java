package za.co.codetribe.ukukhula.notifications;

import static android.R.attr.data;

/**
 * Created by Codetribe on 2017/09/01.
 */

public class Event {


        private String eventName;
        private String eventDiscription;
        private String date;

    public Event()
    {

    }

    public Event(String eventName, String eventDiscription, String date) {
        this.eventName = eventName;
        this.eventDiscription = eventDiscription;
        this.date = date;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDiscription(String eventDiscription) {
        this.eventDiscription = eventDiscription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDiscription() {
        return eventDiscription;
    }
}

