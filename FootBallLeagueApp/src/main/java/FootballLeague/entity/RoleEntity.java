package FootballLeague.entity;

import javax.persistence.*;
import java.util.ArrayList;

//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class RoleEntity {
    @Id
    protected String roleId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId", referencedColumnName = "userName")
    protected SubscriberEntity subscriber;
}