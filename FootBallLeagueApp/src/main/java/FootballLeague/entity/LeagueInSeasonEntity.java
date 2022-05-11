package FootballLeague.entity;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="leagueInSeason")
public class LeagueInSeasonEntity {

//    @EmbeddedId
//    private String id;
//
//    @ManyToOne
//    @MapsId("name")
//    private LeagueEntity league;
//
//    @ManyToOne
//    @MapsId("year")
//    private SeasonEntity season;

    @Id
    @ManyToOne
    private LeagueEntity league;
    @Id
    @ManyToOne
    private SeasonEntity season;
    @ManyToMany
    private Set<RefereeEntity> referees;
    @OneToMany
    private Set<MatchEntity> matches;
    @ManyToMany
    private Set<TeamEntity> teams;

}
