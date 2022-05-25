package FootballLeague.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="matches")
public class MatchEntity {

    @Id
    private String matchId;
    private LocalDate date;
    private LocalTime time;
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

    public MatchEntity(String matchId, LocalDate date, LocalTime time, int homeTeamScore, int awayTeamScore, String stadium, EventLogEntity eventLog, RefereeEntity mainReferee, Set<RefereeEntity> assistantReferees, TeamEntity homeTeam, TeamEntity awayTeam, LeagueInSeasonEntity leagueInSeason) {
        this.matchId = matchId;
        this.date = date;
        this.time = time;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.stadium = stadium;
        this.eventLog = eventLog;
        this.mainReferee = mainReferee;
        this.assistantReferees = assistantReferees;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.leagueInSeason = leagueInSeason;
    }

    public MatchEntity() {
        assistantReferees = new HashSet<>();
    }

    public MatchEntity(TeamEntity homeTeam, TeamEntity awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public String getMatchId() {
        return matchId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public String getStadium() {
        return stadium;
    }

    public EventLogEntity getEventLog() {
        return eventLog;
    }

    public RefereeEntity getMainReferee() {
        return mainReferee;
    }

    public Set<RefereeEntity> getAssistantReferees() {
        return assistantReferees;
    }

    public TeamEntity getHomeTeam() {
        return homeTeam;
    }

    public TeamEntity getAwayTeam() {
        return awayTeam;
    }

    public LeagueInSeasonEntity getLeagueInSeason() {
        return leagueInSeason;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public void setEventLog(EventLogEntity eventLog) {
        this.eventLog = eventLog;
    }

    public void setMainReferee(RefereeEntity mainReferee) {
        this.mainReferee = mainReferee;
    }

    public void setAssistantReferees(Set<RefereeEntity> assistantReferees) {
        this.assistantReferees = assistantReferees;
    }

    public void setHomeTeam(TeamEntity homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(TeamEntity awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setLeagueInSeason(LeagueInSeasonEntity leagueInSeason) {
        this.leagueInSeason = leagueInSeason;
    }
}
