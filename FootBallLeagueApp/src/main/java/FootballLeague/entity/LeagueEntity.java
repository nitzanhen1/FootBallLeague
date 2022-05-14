package FootballLeague.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="league")
public class LeagueEntity {

    @Id
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "league")
    private Set<LeagueInSeasonEntity> leagueInSeason;

    public String getName() {
        return name;
    }
}
