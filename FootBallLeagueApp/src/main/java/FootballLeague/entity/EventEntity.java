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
    @ManyToOne
    private RefereeEntity refereeCreator;
    @ManyToOne
    private EventLogEntity eventLog;




}
