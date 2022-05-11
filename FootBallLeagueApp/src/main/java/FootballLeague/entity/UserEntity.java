package FootballLeague.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public abstract class UserEntity {
    @Id
    protected String userId;

    public String getUserId(){
        return userId;
    }

    public void setUserId(String id){
        this.userId = id;
    }
}
