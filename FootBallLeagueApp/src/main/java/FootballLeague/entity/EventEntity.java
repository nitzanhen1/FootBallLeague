package FootballLeague.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="event")
public class EventEntity {

    @Id
    private String eventId;
    private Date date;
    private Time time;
    private int matchMinuteOfEvent;
    @Enumerated(EnumType.STRING)
    private GameEventEnum matchEvent;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "referee_id", referencedColumnName = "roleId")
    private RefereeEntity refereeCreator;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_log_id", referencedColumnName = "eventLogId")
    private EventLogEntity eventLog;

    public EventEntity() {
    }

    public EventEntity(String eventId, Date date, Time time, int matchMinuteOfEvent, GameEventEnum matchEvent, String description, RefereeEntity refereeCreator, EventLogEntity eventLog) {
        this.eventId = eventId;
        this.date = date;
        this.time = time;
        this.matchMinuteOfEvent = matchMinuteOfEvent;
        this.matchEvent = matchEvent;
        this.description = description;
        this.refereeCreator = refereeCreator;
        this.eventLog = eventLog;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setMatchMinuteOfEvent(int matchMinuteOfEvent) {
        this.matchMinuteOfEvent = matchMinuteOfEvent;
    }

    public void setMatchEvent(GameEventEnum matchEvent) {
        this.matchEvent = matchEvent;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRefereeCreator(RefereeEntity refereeCreator) {
        this.refereeCreator = refereeCreator;
    }

    public void setEventLog(EventLogEntity eventLog) {
        this.eventLog = eventLog;
    }

    public String getEventId() {
        return eventId;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getMatchMinuteOfEvent() {
        return matchMinuteOfEvent;
    }

    public GameEventEnum getMatchEvent() {
        return matchEvent;
    }

    public String getDescription() {
        return description;
    }

    public RefereeEntity getRefereeCreator() {
        return refereeCreator;
    }

    public EventLogEntity getEventLog() {
        return eventLog;
    }
}
