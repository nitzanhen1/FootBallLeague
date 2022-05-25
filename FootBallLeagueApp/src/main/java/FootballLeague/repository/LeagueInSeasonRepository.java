package FootballLeague.repository;

import FootballLeague.entity.LeagueInSeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueInSeasonRepository extends JpaRepository<LeagueInSeasonEntity, String> {
    LeagueInSeasonEntity getOneById(String lis);
}