package FootballLeague.entity;

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
    @OneToMany
    private Set<PlayerEntity> players;
    @OneToMany
    private Set<CoachEntity> coaches;
    @ManyToMany
    private Set<TeamOwnerEntity> teamOwners;
    @OneToMany
    private Set<TeamManagerEntity> teamManagers;

    @OneToMany
    private Set<MatchEntity> homeMatches;
    @OneToMany
    private Set<MatchEntity> awayMatches;
    @ManyToMany
    private Set<LeagueInSeasonEntity> leagueInSeason;
}
