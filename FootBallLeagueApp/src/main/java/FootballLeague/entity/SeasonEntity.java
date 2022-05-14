package FootballLeague.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="season")
public class SeasonEntity {

    @Id
    private int year;
    @JsonIgnore
    @OneToMany(mappedBy = "season")
    private Set<LeagueInSeasonEntity> leagueInSeason;

    public int getYear() {
        return year;
    }
}
