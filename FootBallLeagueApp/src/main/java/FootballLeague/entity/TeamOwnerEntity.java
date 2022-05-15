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

    public TeamOwnerEntity(String roleId, SubscriberEntity subscriber, String assets, Set<TeamEntity> teams, TeamOwnerEntity assignerTeamOwner, Set<TeamOwnerEntity> assignedTeamOwners, Set<TeamManagerEntity> assignedTeamManager) {
        super(roleId, subscriber);
        this.assets = assets;
        this.teams = teams;
        this.assignerTeamOwner = assignerTeamOwner;
        this.assignedTeamOwners = assignedTeamOwners;
        this.assignedTeamManager = assignedTeamManager;
    }

    public TeamOwnerEntity() {
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public void setTeams(Set<TeamEntity> teams) {
        this.teams = teams;
    }

    public void setAssignerTeamOwner(TeamOwnerEntity assignerTeamOwner) {
        this.assignerTeamOwner = assignerTeamOwner;
    }

    public void setAssignedTeamOwners(Set<TeamOwnerEntity> assignedTeamOwners) {
        this.assignedTeamOwners = assignedTeamOwners;
    }

    public void setAssignedTeamManager(Set<TeamManagerEntity> assignedTeamManager) {
        this.assignedTeamManager = assignedTeamManager;
    }

    public String getAssets() {
        return assets;
    }

    public Set<TeamEntity> getTeams() {
        return teams;
    }

    public TeamOwnerEntity getAssignerTeamOwner() {
        return assignerTeamOwner;
    }

    public Set<TeamOwnerEntity> getAssignedTeamOwners() {
        return assignedTeamOwners;
    }

    public Set<TeamManagerEntity> getAssignedTeamManager() {
        return assignedTeamManager;
    }
}
