package FootballLeague.Service;

import FootballLeague.entity.LeagueInSeasonEntity;
import FootballLeague.entity.MatchEntity;
import FootballLeague.entity.RefereeEntity;
import FootballLeague.entity.SubscriberEntity;
import FootballLeague.repository.LeagueInSeasonRepository;
import FootballLeague.repository.MatchRepository;
import FootballLeague.repository.RefereeRepository;
import FootballLeague.repository.SubscriberRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;


@RunWith( SpringRunner.class )
@SpringBootTest
public class MainServiceScheduleMatchTest {
    //this class is for system end-to-end tests
//system tests for login logic
    @Autowired
    MainService mainService;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    RefereeRepository refereeRepository;

    @Autowired
    LeagueInSeasonRepository leagueInSeasonRepository;

    private final String leagueId = "German";
    private  final String seasonId = "2023";
    private LeagueInSeasonEntity leagueInSeasonEntity;
    private List<MatchEntity> matchesInSeasonInLeagues;
    private List<RefereeEntity> refereeInSeasonInLeague;

    @Before
    public void init() {

    }

    @Test
    public void scheduleMatches() {
    }


    @After
    public void end() {
    }
}
