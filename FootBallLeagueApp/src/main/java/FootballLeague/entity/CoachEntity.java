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
}
