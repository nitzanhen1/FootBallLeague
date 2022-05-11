package FootballLeague.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="league")
public class LeagueEntity {

    @Id
    private String name;
    @OneToMany
    private Set<LeagueInSeasonEntity> leagueInSeason;
}
