package FootballLeague.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="coach")
public class CoachEntity extends RoleEntity{

    private String qualification;
    private String roleInTeam;
    private String personalPage; //TODO: entity? class? String?
    @ManyToOne
    private TeamEntity team;
}
