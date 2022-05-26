package FootballLeague.repository;

import FootballLeague.entity.LeagueEntity;
import FootballLeague.entity.LeagueInSeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<LeagueEntity, String> {
}
