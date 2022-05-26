package FootballLeague.Service;

import FootballLeague.entity.LeagueEntity;
import FootballLeague.entity.LeagueInSeasonEntity;
import FootballLeague.entity.RefereeEntity;
import FootballLeague.entity.SeasonEntity;
import FootballLeague.repository.LeagueInSeasonRepository;
import FootballLeague.repository.LeagueRepository;
import FootballLeague.repository.RefereeRepository;
import FootballLeague.repository.SeasonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;


@RunWith( SpringRunner.class )
@SpringBootTest
public class MainServiceAssignRefereeTest {
//this class is for system end-to-end tests
//system tests for assign referee logic
    @Autowired
    MainService mainService;

    @Autowired
    private RefereeRepository refereeRepository;

    @Autowired
    private LeagueInSeasonRepository leagueInSeasonRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired SeasonRepository seasonRepository;

    private RefereeEntity refereeEntity;
    private LeagueInSeasonEntity leagueInSeasonEntity;
    private final String refereeId ="referee6";
    private final String leagueId= "German";
    private final String seasonId= "2023";


    @Before
    public void init() {
        setUpRefereeInDB(refereeId);
        leagueInSeasonEntity = setUpLeagueInSeason(leagueId,seasonId);
        refereeRepository.save(refereeEntity);
        leagueInSeasonRepository.save(leagueInSeasonEntity);
    }

    @Test
    public void assignRefereeToLeague() {
        //successful assign referee
        assertTrue(mainService.assignRefereeToLeague(refereeId,leagueId,seasonId));

        //invalid params
        assertThatCode(() -> mainService.assignRefereeToLeague(null,leagueId,seasonId)).isInstanceOf(NullPointerException.class);

        //referee doesnt exist in the system
        assertThatCode(() -> mainService.assignRefereeToLeague("referee7",leagueId,seasonId)).isInstanceOf(NoSuchElementException.class);

        //seasonInLeague doesnt exist in the system
        assertThatCode(() -> mainService.assignRefereeToLeague(refereeId,"Belgian",seasonId)).isInstanceOf(NoSuchElementException.class);

        //referee already connected to another league in this season
        LeagueInSeasonEntity anotherLeagueInSeason = setUpLeagueInSeason("Belgian",seasonId);
        leagueInSeasonRepository.save(anotherLeagueInSeason);
        assertThatCode(() -> mainService.assignRefereeToLeague(refereeId,"Belgian",seasonId)).isInstanceOf(UnsupportedOperationException.class);
        anotherLeagueInSeason.setSeason(null);
        anotherLeagueInSeason.setLeague(null);
        leagueInSeasonRepository.delete(anotherLeagueInSeason);
        leagueRepository.deleteById("Belgian");

        //referee already assigned to this leagueInSeason
        assertFalse(mainService.assignRefereeToLeague(refereeId,leagueId,seasonId));
    }

    private void setUpRefereeInDB(String refereeId) {
        refereeEntity = new RefereeEntity();
        refereeEntity.setRoleId(refereeId);
    }

    private LeagueInSeasonEntity setUpLeagueInSeason(String leagueId, String seasonId) {
        LeagueEntity leagueEntity = new LeagueEntity();
        leagueEntity.setName(leagueId);
        SeasonEntity seasonEntity = new SeasonEntity();
        seasonEntity.setYear(seasonId);
        LeagueInSeasonEntity leagueInSeasonEntity1 = new LeagueInSeasonEntity();
        leagueInSeasonEntity1.setId(leagueId.concat(seasonId));
        leagueInSeasonEntity1.setLeague(leagueEntity);
        leagueInSeasonEntity1.setSeason(seasonEntity);
        return leagueInSeasonEntity1;
    }

    @After
    public void end(){
        leagueInSeasonEntity.setSeason(null);
        leagueInSeasonEntity.setLeague(null);
        leagueInSeasonEntity.getReferees().removeAll(leagueInSeasonEntity.getReferees());
        refereeEntity.getLeagueInSeason().removeAll(refereeEntity.getLeagueInSeason());
        leagueInSeasonRepository.delete(leagueInSeasonEntity);
        refereeRepository.delete(refereeEntity);
        seasonRepository.deleteById(seasonId);
        leagueRepository.deleteById(leagueId);
    }
}
