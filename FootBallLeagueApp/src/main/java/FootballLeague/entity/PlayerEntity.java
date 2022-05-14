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

}
