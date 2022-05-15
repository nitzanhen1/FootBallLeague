package FootballLeague.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="unionRepresentative")
public class UnionRepresentativeEntity extends RoleEntity{

    @JsonIgnore
    @OneToMany(mappedBy = "unionRepresentativeAssigner")
    private Set<RefereeEntity> refereesAssigned;

    @JsonIgnore
    @OneToMany(mappedBy = "unionRepresentativeCreator")
    private Set<LeagueInSeasonEntity> leagueInSeason;

    public UnionRepresentativeEntity(String roleId, SubscriberEntity subscriber, Set<RefereeEntity> refereesAssigned, Set<LeagueInSeasonEntity> leagueInSeason) {
        super(roleId, subscriber);
        this.refereesAssigned = refereesAssigned;
        this.leagueInSeason = leagueInSeason;
    }

    public UnionRepresentativeEntity() {
    }

    public void setRefereesAssigned(Set<RefereeEntity> refereesAssigned) {
        this.refereesAssigned = refereesAssigned;
    }

    public void setLeagueInSeason(Set<LeagueInSeasonEntity> leagueInSeason) {
        this.leagueInSeason = leagueInSeason;
    }

    public Set<RefereeEntity> getRefereesAssigned() {
        return refereesAssigned;
    }

    public Set<LeagueInSeasonEntity> getLeagueInSeason() {
        return leagueInSeason;
    }
}
