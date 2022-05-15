package FootballLeague.repository;

import FootballLeague.entity.RefereeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefereeRepository extends JpaRepository<RefereeEntity, String> {
    RefereeEntity getOneByRoleId(String refereeId);
}
