package FootballLeague.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
@Entity

public abstract class RoleEntity {
    //protected String name;
    @Id
    protected String roleId;
    @ManyToOne
    protected SubscriberEntity subscriber;
}
