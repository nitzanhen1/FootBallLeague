package FootballLeague.entity;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name="subscriber")
public class SubscriberEntity extends UserEntity{

    private String userName;
    private String password;
    private boolean loggedIn;
    @OneToMany
    private Set<RoleEntity> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
