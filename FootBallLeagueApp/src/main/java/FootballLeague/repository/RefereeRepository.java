package FootballLeague.repository;

import FootballLeague.entity.LeagueInSeasonEntity;
import FootballLeague.entity.RefereeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefereeRepository extends JpaRepository<RefereeEntity, String> {
    RefereeEntity getOneByRoleId(String refereeId);
    List<RefereeEntity> findAllByLeagueInSeason(LeagueInSeasonEntity lis);
}
