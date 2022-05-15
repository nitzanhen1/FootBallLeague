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


    public RoleEntity(String roleId, SubscriberEntity subscriber) {
        this.roleId = roleId;
        this.subscriber = subscriber;
    }

    public RoleEntity() {
    }

    public String getRoleId() {
        return roleId;
    }

    public SubscriberEntity getSubscriber() {
        return subscriber;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setSubscriber(SubscriberEntity subscriber) {
        this.subscriber = subscriber;
    }
}