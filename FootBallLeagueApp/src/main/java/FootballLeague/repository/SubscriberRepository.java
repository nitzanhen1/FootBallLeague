package FootballLeague.repository;

import FootballLeague.entity.SubscriberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<SubscriberEntity, String> {

    SubscriberEntity getOneByUserName(String userName);
}
