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
}
