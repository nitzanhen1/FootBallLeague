package FootballLeague.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="fan")
public class FanEntity extends RoleEntity{

    public FanEntity() {
    }

    public FanEntity(String roleId, SubscriberEntity subscriber) {
        super(roleId, subscriber);
    }


}
