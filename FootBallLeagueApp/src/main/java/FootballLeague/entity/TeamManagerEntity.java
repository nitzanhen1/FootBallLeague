package FootballLeague.entity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="teamManager")
public class TeamManagerEntity extends RoleEntity{

    @ManyToOne
    private TeamEntity team;
    @ManyToOne
    private TeamOwnerEntity assignerTeamOwner;
}
