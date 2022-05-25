package FootballLeague.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="fan")
public class FanEntity extends RoleEntity{

    public FanEntity() {
    }

    public FanEntity(String roleId, String name, SubscriberEntity subscriber) {
        super(roleId, name, subscriber);
    }
}
