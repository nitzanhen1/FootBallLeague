package FootballLeague.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
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


}
