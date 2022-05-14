package FootballLeague.entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="teamManager")
public class TeamManagerEntity extends RoleEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_name", referencedColumnName = "teamName")
    private TeamEntity team;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assigner_team_owner_id", referencedColumnName = "roleId")
    private TeamOwnerEntity assignerTeamOwner;
}
