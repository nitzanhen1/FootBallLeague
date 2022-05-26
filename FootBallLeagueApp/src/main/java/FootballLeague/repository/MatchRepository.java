package FootballLeague.repository;

import FootballLeague.entity.LeagueInSeasonEntity;
import FootballLeague.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, String> {
    List<MatchEntity> findAllByLeagueInSeason(LeagueInSeasonEntity lis);
}
