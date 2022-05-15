package FootballLeague.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

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


    public SubscriberEntity(String userName, String password, boolean loggedIn) {
        this.userName = userName;
        this.password = password;
        this.loggedIn = loggedIn;
    }

    public SubscriberEntity() {
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}