package FootballLeague.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="referee")
public class RefereeEntity extends RoleEntity{

    private String qualification;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assigner_id", referencedColumnName = "roleId")
    private UnionRepresentativeEntity unionRepresentativeAssigner;
    @JsonIgnore
    @OneToMany(mappedBy = "mainReferee")
    private Set<MatchEntity> matchesAsMainReferee;
    @JsonIgnore
    @ManyToMany(mappedBy = "assistantReferees")
    private Set<MatchEntity> matchesAsAssistantReferee;
    @JsonIgnore
    @OneToMany(mappedBy = "refereeCreator")
    private Set<EventEntity> events;
    @JsonIgnore
    @ManyToMany(mappedBy = "referees")
    private Set<LeagueInSeasonEntity> leagueInSeason;

    public RefereeEntity(String roleId, SubscriberEntity subscriber, String qualification, UnionRepresentativeEntity unionRepresentativeAssigner, Set<MatchEntity> matchesAsMainReferee, Set<MatchEntity> matchesAsAssistantReferee, Set<EventEntity> events, Set<LeagueInSeasonEntity> leagueInSeason) {
        super(roleId, subscriber);
        this.qualification = qualification;
        this.unionRepresentativeAssigner = unionRepresentativeAssigner;
        this.matchesAsMainReferee = matchesAsMainReferee;
        this.matchesAsAssistantReferee = matchesAsAssistantReferee;
        this.events = events;
        this.leagueInSeason = leagueInSeason;
    }

    public RefereeEntity() {
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setUnionRepresentativeAssigner(UnionRepresentativeEntity unionRepresentativeAssigner) {
        this.unionRepresentativeAssigner = unionRepresentativeAssigner;
    }

    public void setMatchesAsMainReferee(Set<MatchEntity> matchesAsMainReferee) {
        this.matchesAsMainReferee = matchesAsMainReferee;
    }

    public void setMatchesAsAssistantReferee(Set<MatchEntity> matchesAsAssistantReferee) {
        this.matchesAsAssistantReferee = matchesAsAssistantReferee;
    }

    public void setEvents(Set<EventEntity> events) {
        this.events = events;
    }

    public void setLeagueInSeason(Set<LeagueInSeasonEntity> leagueInSeason) {
        this.leagueInSeason = leagueInSeason;
    }

    public String getQualification() {
        return qualification;
    }

    public UnionRepresentativeEntity getUnionRepresentativeAssigner() {
        return unionRepresentativeAssigner;
    }

    public Set<MatchEntity> getMatchesAsMainReferee() {
        return matchesAsMainReferee;
    }

    public Set<MatchEntity> getMatchesAsAssistantReferee() {
        return matchesAsAssistantReferee;
    }

    public Set<EventEntity> getEvents() {
        return events;
    }

    public Set<LeagueInSeasonEntity> getLeagueInSeason() {
        return leagueInSeason;
    }
}
