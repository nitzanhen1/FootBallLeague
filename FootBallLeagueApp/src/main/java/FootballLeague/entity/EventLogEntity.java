package FootballLeague.entity;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="eventLog")
public class EventLogEntity {

    @Id
    @OneToOne
    private MatchEntity match;
    @OneToMany
    private Set<EventEntity> events;


}
