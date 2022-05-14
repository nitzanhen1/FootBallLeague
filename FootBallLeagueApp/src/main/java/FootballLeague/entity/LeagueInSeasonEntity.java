package FootballLeague.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="leagueInSeason")
//@IdClass(LeagueInSeasonEntity.CompositeKey.class)
public class LeagueInSeasonEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "league_name", referencedColumnName = "name")
    private LeagueEntity league;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "season_year", referencedColumnName = "year")
    private SeasonEntity season;
    @Id
    private String id = league.getName().concat(season.getYear()+""); //TODO: check insert
    @ManyToMany
    @JoinTable(
            name="refereesToLeagueInSeoson",
            joinColumns = @JoinColumn(name = "leagueInSeoson"),
            inverseJoinColumns = @JoinColumn(name = "refereeId")
    )
    private Set<RefereeEntity> referees;
    @JsonIgnore
    @OneToMany(mappedBy = "leagueInSeason")
    private Set<MatchEntity> matches;
    @ManyToMany
    @JoinTable(
            name="teamsToLeagueInSeoson",
            joinColumns = @JoinColumn(name = "leagueInSeoson"),
            inverseJoinColumns = @JoinColumn(name = "teamName")
    )
    private Set<TeamEntity> teams;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id", referencedColumnName = "roleId")
    private UnionRepresentativeEntity unionRepresentativeCreator;



//    public class CompositeKey implements Serializable {
//        private LeagueEntity league;
//        private SeasonEntity season;
//
//    }
}


