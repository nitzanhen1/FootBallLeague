package FootballLeague.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="unionRepresentative")
public class UnionRepresentativeEntity extends RoleEntity{

    @OneToMany
    private Set<RefereeEntity> refereesAssigned;

    @OneToMany
    private Set<LeagueInSeasonEntity> leagueInSeason;
}
