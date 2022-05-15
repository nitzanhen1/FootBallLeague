package FootballLeague.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name="user")
public abstract class UserEntity {
    @Id
    private String id;

    public UserEntity(String id) {
        this.id = id;
    }

    public UserEntity() {
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
}
