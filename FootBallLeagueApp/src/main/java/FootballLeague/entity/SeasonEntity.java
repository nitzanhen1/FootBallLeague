package FootballLeague.entity;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="season")
public class SeasonEntity {

    @Id
    private int year;
    @OneToMany
    private Set<LeagueInSeasonEntity> leagueInSeason;
}
