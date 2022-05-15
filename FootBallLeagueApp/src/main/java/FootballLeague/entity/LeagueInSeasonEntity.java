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
            name="refereesToLeagueInSeason",
            joinColumns = @JoinColumn(name = "leagueInSeason"),
            inverseJoinColumns = @JoinColumn(name = "refereeId")
    )
    private Set<RefereeEntity> referees;
    @JsonIgnore
    @OneToMany(mappedBy = "leagueInSeason")
    private Set<MatchEntity> matches;
    @ManyToMany
    @JoinTable(
            name="teamsToLeagueInSeason",
            joinColumns = @JoinColumn(name = "leagueInSeason"),
            inverseJoinColumns = @JoinColumn(name = "teamName")
    )
    private Set<TeamEntity> teams;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id", referencedColumnName = "roleId")
    private UnionRepresentativeEntity unionRepresentativeCreator;

    public LeagueInSeasonEntity(LeagueEntity league, SeasonEntity season, String id, Set<RefereeEntity> referees, Set<MatchEntity> matches, Set<TeamEntity> teams, UnionRepresentativeEntity unionRepresentativeCreator) {
        this.league = league;
        this.season = season;
        this.id = id;
        this.referees = referees;
        this.matches = matches;
        this.teams = teams;
        this.unionRepresentativeCreator = unionRepresentativeCreator;
    }

    public LeagueInSeasonEntity() {
    }

    public void setLeague(LeagueEntity league) {
        this.league = league;
    }

    public void setSeason(SeasonEntity season) {
        this.season = season;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setReferees(Set<RefereeEntity> referees) {
        this.referees = referees;
    }

    public void setMatches(Set<MatchEntity> matches) {
        this.matches = matches;
    }

    public void setTeams(Set<TeamEntity> teams) {
        this.teams = teams;
    }

    public void setUnionRepresentativeCreator(UnionRepresentativeEntity unionRepresentativeCreator) {
        this.unionRepresentativeCreator = unionRepresentativeCreator;
    }

    public LeagueEntity getLeague() {
        return league;
    }

    public SeasonEntity getSeason() {
        return season;
    }

    public String getId() {
        return id;
    }

    public Set<RefereeEntity> getReferees() {
        return referees;
    }

    public Set<MatchEntity> getMatches() {
        return matches;
    }

    public Set<TeamEntity> getTeams() {
        return teams;
    }

    public UnionRepresentativeEntity getUnionRepresentativeCreator() {
        return unionRepresentativeCreator;
    }
}


