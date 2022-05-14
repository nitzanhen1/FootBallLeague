package FootballLeague.entity;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="matches")
public class MatchEntity {

    @Id
    private String matchId;
    private Date date;
    private Time time;
    private int homeTeamScore;
    private int awayTeamScore;
    private String stadium;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_log_id", referencedColumnName = "eventLogId")
    private EventLogEntity eventLog;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_referee_id", referencedColumnName = "roleId")
    private RefereeEntity mainReferee;
    @ManyToMany
    @JoinTable(
            name="assistant_referees_in_match",
            joinColumns = @JoinColumn(name = "matchId"),
            inverseJoinColumns = @JoinColumn(name = "refereeId")
    )
    private Set<RefereeEntity> assistantReferees;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_team", referencedColumnName = "teamName")
    private TeamEntity homeTeam;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_team", referencedColumnName = "teamName")
    private TeamEntity awayTeam;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "league_in_season", referencedColumnName = "id")
    private LeagueInSeasonEntity leagueInSeason;

}
