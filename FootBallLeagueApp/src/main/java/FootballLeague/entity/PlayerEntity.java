package FootballLeague.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="player")
public class PlayerEntity extends RoleEntity{

    private LocalDate dateOfBirth;
    private String position;
    private String personalPage;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_name", referencedColumnName = "teamName")
    private TeamEntity team;

    public PlayerEntity(String roleId, String name, SubscriberEntity subscriber, LocalDate dateOfBirth, String position, String personalPage, TeamEntity team) {
        super(roleId, name, subscriber);
        this.dateOfBirth = dateOfBirth;
        position = position;
        this.personalPage = personalPage;
        this.team = team;
    }

    public PlayerEntity(String roleId, String name, SubscriberEntity subscriber) {
        super(roleId, name, subscriber);
    }

    public PlayerEntity() {
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPosition() {
        return position;
    }

    public String getPersonalPage() {
        return personalPage;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPosition(String position) {
        position = position;
    }

    public void setPersonalPage(String personalPage) {
        this.personalPage = personalPage;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }
}
