package FootballLeague.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="teamOwner")
public class TeamOwnerEntity extends RoleEntity{
    private String assets; //TODO: need to be list but can't if not entity
    @JsonIgnore
    @ManyToMany(mappedBy = "teamOwners")
    private Set<TeamEntity> teams;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assigner_team_owner_id", referencedColumnName = "roleId")
    private TeamOwnerEntity assignerTeamOwner;
    @JsonIgnore
    @OneToMany(mappedBy = "assignerTeamOwner")
    private Set<TeamOwnerEntity> assignedTeamOwners;
    @JsonIgnore
    @OneToMany(mappedBy = "assignerTeamOwner")
    private Set<TeamManagerEntity> assignedTeamManager;
}
