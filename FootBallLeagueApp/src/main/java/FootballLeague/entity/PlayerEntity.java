package FootballLeague.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="player")
public class PlayerEntity extends RoleEntity{

    private Date dateOfBirth;
    private String Position;
    private String personalPgae; //TODO: entity? class? String?
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_name", referencedColumnName = "teamName")
    private TeamEntity team;

    public PlayerEntity(String roleId, SubscriberEntity subscriber, Date dateOfBirth, String position, String personalPgae, TeamEntity team) {
        super(roleId, subscriber);
        this.dateOfBirth = dateOfBirth;
        Position = position;
        this.personalPgae = personalPgae;
        this.team = team;
    }

    public PlayerEntity() {
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPosition() {
        return Position;
    }

    public String getPersonalPgae() {
        return personalPgae;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public void setPersonalPgae(String personalPgae) {
        this.personalPgae = personalPgae;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }
}
