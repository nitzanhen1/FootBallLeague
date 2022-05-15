package FootballLeague.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="team")
public class TeamEntity {
    @Id
    private String teamName;
    private boolean active;
    private String homeStadium;
    private String personalPage; //TODO: entity? class? String?
    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private Set<PlayerEntity> players;

    //@JsonIgnore
    @ManyToMany//(mappedBy = "team")
    @JoinTable(
            name="owner_of_team",
            joinColumns = @JoinColumn(name = "teamName"),
            inverseJoinColumns = @JoinColumn(name = "ownerId")
    )
    private Set<TeamOwnerEntity> teamOwners;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private Set<CoachEntity> coaches;
    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private Set<TeamManagerEntity> teamManagers;
    @JsonIgnore
    @OneToMany(mappedBy = "homeTeam")
    private Set<MatchEntity> homeMatches;
    @JsonIgnore
    @OneToMany(mappedBy = "awayTeam")
    private Set<MatchEntity> awayMatches;
    @JsonIgnore
    @ManyToMany(mappedBy = "teams")
    private Set<LeagueInSeasonEntity> leagueInSeason;


    public TeamEntity(String teamName, boolean active, String homeStadium, String personalPage, Set<PlayerEntity> players, Set<TeamOwnerEntity> teamOwners, Set<CoachEntity> coaches, Set<TeamManagerEntity> teamManagers, Set<MatchEntity> homeMatches, Set<MatchEntity> awayMatches, Set<LeagueInSeasonEntity> leagueInSeason) {
        this.teamName = teamName;
        this.active = active;
        this.homeStadium = homeStadium;
        this.personalPage = personalPage;
        this.players = players;
        this.teamOwners = teamOwners;
        this.coaches = coaches;
        this.teamManagers = teamManagers;
        this.homeMatches = homeMatches;
        this.awayMatches = awayMatches;
        this.leagueInSeason = leagueInSeason;
    }

    public TeamEntity() {
    }

    public String getTeamName() {
        return teamName;
    }

    public boolean isActive() {
        return active;
    }

    public String getHomeStadium() {
        return homeStadium;
    }

    public String getPersonalPage() {
        return personalPage;
    }

    public Set<PlayerEntity> getPlayers() {
        return players;
    }

    public Set<TeamOwnerEntity> getTeamOwners() {
        return teamOwners;
    }

    public Set<CoachEntity> getCoaches() {
        return coaches;
    }

    public Set<TeamManagerEntity> getTeamManagers() {
        return teamManagers;
    }

    public Set<MatchEntity> getHomeMatches() {
        return homeMatches;
    }

    public Set<MatchEntity> getAwayMatches() {
        return awayMatches;
    }

    public Set<LeagueInSeasonEntity> getLeagueInSeason() {
        return leagueInSeason;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setHomeStadium(String homeStadium) {
        this.homeStadium = homeStadium;
    }

    public void setPersonalPage(String personalPage) {
        this.personalPage = personalPage;
    }

    public void setPlayers(Set<PlayerEntity> players) {
        this.players = players;
    }

    public void setTeamOwners(Set<TeamOwnerEntity> teamOwners) {
        this.teamOwners = teamOwners;
    }

    public void setCoaches(Set<CoachEntity> coaches) {
        this.coaches = coaches;
    }

    public void setTeamManagers(Set<TeamManagerEntity> teamManagers) {
        this.teamManagers = teamManagers;
    }

    public void setHomeMatches(Set<MatchEntity> homeMatches) {
        this.homeMatches = homeMatches;
    }

    public void setAwayMatches(Set<MatchEntity> awayMatches) {
        this.awayMatches = awayMatches;
    }

    public void setLeagueInSeason(Set<LeagueInSeasonEntity> leagueInSeason) {
        this.leagueInSeason = leagueInSeason;
    }
}
