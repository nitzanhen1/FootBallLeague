package FootballLeague.repository;

import FootballLeague.entity.SeasonEntity;
import FootballLeague.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, String> {
}
