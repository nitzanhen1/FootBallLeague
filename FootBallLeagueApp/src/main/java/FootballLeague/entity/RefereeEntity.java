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
}
