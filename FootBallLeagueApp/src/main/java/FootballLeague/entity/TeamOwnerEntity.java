package FootballLeague.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="teamOwner")
public class TeamOwnerEntity extends RoleEntity{

    private String assets; //TODO: need to be list but can't if not entity

    @ManyToMany
    private Set<TeamEntity> teams;
    @ManyToOne
    private TeamOwnerEntity assignerTeamOwner;
    @OneToMany
    private Set<TeamOwnerEntity> assignedTeamOwners;
    @OneToMany
    private Set<TeamManagerEntity> assignedTeamManager;

}
