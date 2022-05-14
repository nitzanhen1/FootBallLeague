package FootballLeague.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table(name="subscriber")
public class SubscriberEntity {
    @Id
    private String userName;
    private String password;
    private boolean loggedIn;

    @JsonIgnore
    @OneToMany(mappedBy = "subscriber")
    private Set<RoleEntity> roles;
}