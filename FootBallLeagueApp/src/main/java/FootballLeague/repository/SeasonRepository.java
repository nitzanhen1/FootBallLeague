package FootballLeague.repository;

import FootballLeague.entity.LeagueInSeasonEntity;
import FootballLeague.entity.SeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<SeasonEntity, String> {
}
