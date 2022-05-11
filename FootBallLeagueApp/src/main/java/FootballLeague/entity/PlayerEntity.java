package FootballLeague.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="player")
public class PlayerEntity extends RoleEntity{

    private Date dateOfBirth;
    private String Position;
    private String personalPgae; //TODO: entity? class? String?
    @ManyToOne
    private TeamEntity team;



}
