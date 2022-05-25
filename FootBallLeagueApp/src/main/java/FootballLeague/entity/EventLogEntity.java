package FootballLeague.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="eventLog")
public class EventLogEntity {

    @Id
    private String eventLogId;
    @JsonIgnore
    @OneToOne(mappedBy = "eventLog")
    private MatchEntity match;

    @JsonIgnore
    @OneToMany(mappedBy = "eventLog")
    private Set<EventEntity> events;


    public EventLogEntity(String eventLogId, MatchEntity match, Set<EventEntity> events) {
        this.eventLogId = eventLogId;
        this.match = match;
        this.events = events;
    }

    public EventLogEntity() {
        events = new HashSet<>();
    }

    public String getEventLogId() {
        return eventLogId;
    }

    public MatchEntity getMatch() {
        return match;
    }

    public Set<EventEntity> getEvents() {
        return events;
    }

    public void setEventLogId(String eventLogId) {
        this.eventLogId = eventLogId;
    }

    public void setMatch(MatchEntity match) {
        this.match = match;
    }

    public void setEvents(Set<EventEntity> events) {
        this.events = events;
    }
}
