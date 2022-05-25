package FootballLeague.entity;

import javax.persistence.*;

@Entity
@Table(name="coach")
public class CoachEntity extends RoleEntity{

    private String qualification;
    private String roleInTeam;
    private String personalPage; //TODO: entity? class? String?
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_name", referencedColumnName = "teamName")
    private TeamEntity team;


    public CoachEntity(String roleId, String name, SubscriberEntity subscriber, String qualification, String roleInTeam, String personalPage, TeamEntity team) {
        super(roleId, name, subscriber);
        this.qualification = qualification;
        this.roleInTeam = roleInTeam;
        this.personalPage = personalPage;
        this.team = team;
    }

    public CoachEntity(String roleId, String name, SubscriberEntity subscriber) {
        super(roleId, name, subscriber);
    }

    public CoachEntity() {
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setRoleInTeam(String roleInTeam) {
        this.roleInTeam = roleInTeam;
    }

    public void setPersonalPage(String personalPage) {
        this.personalPage = personalPage;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public String getQualification() {
        return qualification;
    }

    public String getRoleInTeam() {
        return roleInTeam;
    }

    public String getPersonalPage() {
        return personalPage;
    }

    public TeamEntity getTeam() {
        return team;
    }
}
