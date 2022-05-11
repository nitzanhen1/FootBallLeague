package FootballLeague.entity;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="match")
public class MatchEntity {

    @Id
    private String matchId;
    private Date date;
    private Time time;
    private int homeTeamScore;
    private int awayTeamScore;
    private String stadium;
    @OneToOne
    private EventLogEntity eventLog;
    @ManyToOne
    private RefereeEntity mainReferee;
    @ManyToMany
    private Set<RefereeEntity> assistantReferee;
    @ManyToOne
    private TeamEntity homeTeam;
    @ManyToOne
    private TeamEntity awayTeam;
    @ManyToOne
    private LeagueInSeasonEntity leagueInSeason;

}
