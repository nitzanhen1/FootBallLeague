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

}
