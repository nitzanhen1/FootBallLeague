package FootballLeague.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="referee")
public class RefereeEntity extends RoleEntity{

    private String qualification;
    @ManyToOne
    private UnionRepresentativeEntity unionRepresentativeAssigner;
    @OneToMany
    private Set<MatchEntity> matchesAsMainReferee;
    @ManyToMany
    private Set<MatchEntity> matchesAsAssistantReferee;
    @OneToMany
    private Set<EventEntity> events;
    @ManyToMany
    private Set<LeagueInSeasonEntity> leagueInSeason;
}
