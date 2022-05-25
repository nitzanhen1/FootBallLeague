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

    public TeamManagerEntity(String roleId, String name, SubscriberEntity subscriber, TeamEntity team, TeamOwnerEntity assignerTeamOwner) {
        super(roleId, name, subscriber);
        this.team = team;
        this.assignerTeamOwner = assignerTeamOwner;
    }

    public TeamManagerEntity(String roleId, String name, SubscriberEntity subscriber) {
        super(roleId, name, subscriber);
    }

    public TeamManagerEntity() {
    }

    public TeamEntity getTeam() {
        return team;
    }

    public TeamOwnerEntity getAssignerTeamOwner() {
        return assignerTeamOwner;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public void setAssignerTeamOwner(TeamOwnerEntity assignerTeamOwner) {
        this.assignerTeamOwner = assignerTeamOwner;
    }
}
