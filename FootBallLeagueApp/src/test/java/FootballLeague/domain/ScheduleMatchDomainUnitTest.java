package FootballLeague.domain;
import FootballLeague.entity.LeagueEntity;
import FootballLeague.entity.LeagueInSeasonEntity;
import FootballLeague.entity.MatchEntity;
import FootballLeague.entity.RefereeEntity;
import FootballLeague.entity.SeasonEntity;
import FootballLeague.entity.TeamEntity;
import FootballLeague.repository.LeagueInSeasonRepository;
import FootballLeague.repository.MatchRepository;
import FootballLeague.repository.RefereeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ScheduleMatchDomainUnitTest {

    @InjectMocks
    private ScheduleMatchDomain scheduleMatchDomain;

    @Mock
    MatchRepository matchRepository;

    @Mock
    RefereeRepository refereeRepository;

    @Mock
    LeagueInSeasonRepository leagueInSeasonRepository;

    @Test
    public void scheduleMatchesSuccessfulTest() {
        //mocks
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        List<MatchEntity> matchesInSeasonInLeagues = getMockedMatchesInLeagueInSeason(mockedLeagueInSeasonEntity);
        List<RefereeEntity> refereeInSeasonInLeague = getMockedRefereesInLeagueInSeason(mockedLeagueInSeasonEntity,4);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(mockedLeagueInSeasonEntity);
        when(matchRepository.findAllByLeagueInSeason(mockedLeagueInSeasonEntity)).thenReturn(matchesInSeasonInLeagues);
        when(refereeRepository.findAllByLeagueInSeason(mockedLeagueInSeasonEntity)).thenReturn(refereeInSeasonInLeague);

        //tests
        assertTrue(scheduleMatchDomain.scheduleMatches(leagueId,seasonId));
        matchesInSeasonInLeagues.forEach(matchEntity -> verify(matchRepository).save(matchEntity));
    }

    @Test
    public void scheduleMatchesNoExceptionsTest() {
        //mocks
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        List<MatchEntity> matchesInSeasonInLeagues = getMockedMatchesInLeagueInSeason(mockedLeagueInSeasonEntity);
        List<RefereeEntity> refereeInSeasonInLeague = getMockedRefereesInLeagueInSeason(mockedLeagueInSeasonEntity,4);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(mockedLeagueInSeasonEntity);
        when(matchRepository.findAllByLeagueInSeason(mockedLeagueInSeasonEntity)).thenReturn(matchesInSeasonInLeagues);
        when(refereeRepository.findAllByLeagueInSeason(mockedLeagueInSeasonEntity)).thenReturn(refereeInSeasonInLeague);

        //tests
        assertThatCode(() -> scheduleMatchDomain.scheduleMatches(leagueId,seasonId)).doesNotThrowAnyException();
        matchesInSeasonInLeagues.forEach(matchEntity -> verify(matchRepository).save(matchEntity));
    }

    @Test
    public void scheduleMatchesNullPointerExceptionTest() {
        //mocks
        String leagueId = "German";
        String seasonId = null;
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        List<MatchEntity> matchesInSeasonInLeagues = getMockedMatchesInLeagueInSeason(mockedLeagueInSeasonEntity);

        //tests
        assertThatCode(() -> scheduleMatchDomain.scheduleMatches(leagueId,seasonId)).isInstanceOf(NullPointerException.class);
        matchesInSeasonInLeagues.forEach(matchEntity -> verify(matchRepository, never()).save(matchEntity));
    }

    @Test
    public void scheduleMatchesNoLeagueInSeasonInDBTest() {
        //mocks
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        List<MatchEntity> matchesInSeasonInLeagues = getMockedMatchesInLeagueInSeason(mockedLeagueInSeasonEntity);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(null);

        //tests
        assertThatCode(() -> scheduleMatchDomain.scheduleMatches(leagueId,seasonId)).isInstanceOf(NoSuchElementException.class);
        matchesInSeasonInLeagues.forEach(matchEntity -> verify(matchRepository, never()).save(matchEntity));
    }

    @Test
    public void scheduleMatchesNoGamesToScheduleTest() {
        //mocks
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        List<MatchEntity> matchesInSeasonInLeagues = getMockedMatchesInLeagueInSeason(mockedLeagueInSeasonEntity);
        List<RefereeEntity> refereeInSeasonInLeague = getMockedRefereesInLeagueInSeason(mockedLeagueInSeasonEntity,4);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(mockedLeagueInSeasonEntity);
        when(matchRepository.findAllByLeagueInSeason(mockedLeagueInSeasonEntity)).thenReturn(new ArrayList<>());
        when(refereeRepository.findAllByLeagueInSeason(mockedLeagueInSeasonEntity)).thenReturn(refereeInSeasonInLeague);

        //tests
        assertThatCode(() -> scheduleMatchDomain.scheduleMatches(leagueId,seasonId)).isInstanceOf(UnsupportedOperationException.class);
        matchesInSeasonInLeagues.forEach(matchEntity -> verify(matchRepository, never()).save(matchEntity));
    }

    @Test
    public void scheduleMatchesNotEnoughRefereesTest() {
        //mocks
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        List<MatchEntity> matchesInSeasonInLeagues = getMockedMatchesInLeagueInSeason(mockedLeagueInSeasonEntity);
        List<RefereeEntity> refereeInSeasonInLeague = getMockedRefereesInLeagueInSeason(mockedLeagueInSeasonEntity, 3);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(mockedLeagueInSeasonEntity);
        when(matchRepository.findAllByLeagueInSeason(mockedLeagueInSeasonEntity)).thenReturn(matchesInSeasonInLeagues);
        when(refereeRepository.findAllByLeagueInSeason(mockedLeagueInSeasonEntity)).thenReturn(refereeInSeasonInLeague);

        //tests
        assertThatCode(() -> scheduleMatchDomain.scheduleMatches(leagueId,seasonId)).isInstanceOf(UnsupportedOperationException.class);
        matchesInSeasonInLeagues.forEach(matchEntity -> verify(matchRepository, never()).save(matchEntity));
    }

    @Test
    public void scheduleMatchesNoTwoTeamsPerMatchTest() {
        //mocks
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        List<MatchEntity> matchesInSeasonInLeagues = getMockedMatchesInLeagueInSeason(mockedLeagueInSeasonEntity);
        matchesInSeasonInLeagues.get(0).setAwayTeam(null);
        List<RefereeEntity> refereeInSeasonInLeague = getMockedRefereesInLeagueInSeason(mockedLeagueInSeasonEntity,4);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(mockedLeagueInSeasonEntity);
        when(matchRepository.findAllByLeagueInSeason(mockedLeagueInSeasonEntity)).thenReturn(matchesInSeasonInLeagues);
        when(refereeRepository.findAllByLeagueInSeason(mockedLeagueInSeasonEntity)).thenReturn(refereeInSeasonInLeague);

        //tests
        assertThatCode(() -> scheduleMatchDomain.scheduleMatches(leagueId,seasonId)).isInstanceOf(UnsupportedOperationException.class);
        matchesInSeasonInLeagues.forEach(matchEntity -> verify(matchRepository, never()).save(matchEntity));
    }

    private List<RefereeEntity> getMockedRefereesInLeagueInSeason(LeagueInSeasonEntity leagueInSeasonEntity, int quantity) {
        RefereeEntity referee1;
        List<RefereeEntity> refereesInLeagueInSeason = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            referee1 = new RefereeEntity();
            referee1.setRoleId("referee"+i);
            referee1.getLeagueInSeason().add(leagueInSeasonEntity);
            leagueInSeasonEntity.getReferees().add(referee1);
            refereesInLeagueInSeason.add(referee1);
        }
        return refereesInLeagueInSeason;
    }

    private List<MatchEntity> getMockedMatchesInLeagueInSeason(LeagueInSeasonEntity leagueInSeasonEntity) {
        TeamEntity team1 = new TeamEntity();
        team1.setTeamName("team11");
        team1.setHomeStadium("stadium11");
        TeamEntity team2 = new TeamEntity();
        team2.setTeamName("team12");
        team2.setHomeStadium("stadium12");
        TeamEntity team3 = new TeamEntity();
        team3.setTeamName("team13");
        team3.setHomeStadium("stadium13");

        MatchEntity matchEntity1 = new MatchEntity();
        matchEntity1.setMatchId("match1");
        matchEntity1.setHomeTeam(team1);
        matchEntity1.setAwayTeam(team2);
        matchEntity1.setLeagueInSeason(leagueInSeasonEntity);

        MatchEntity matchEntity2 = new MatchEntity();
        matchEntity2.setMatchId("match2");
        matchEntity2.setHomeTeam(team2);
        matchEntity2.setAwayTeam(team1);
        matchEntity2.setLeagueInSeason(leagueInSeasonEntity);

        MatchEntity matchEntity3 = new MatchEntity();
        matchEntity3.setMatchId("match3");
        matchEntity3.setHomeTeam(team1);
        matchEntity3.setAwayTeam(team3);
        matchEntity3.setLeagueInSeason(leagueInSeasonEntity);

        MatchEntity matchEntity4 = new MatchEntity();
        matchEntity4.setMatchId("match4");
        matchEntity4.setHomeTeam(team3);
        matchEntity4.setAwayTeam(team1);
        matchEntity4.setLeagueInSeason(leagueInSeasonEntity);

        MatchEntity matchEntity5 = new MatchEntity();
        matchEntity5.setMatchId("match5");
        matchEntity5.setHomeTeam(team2);
        matchEntity5.setAwayTeam(team3);
        matchEntity5.setLeagueInSeason(leagueInSeasonEntity);

        MatchEntity matchEntity6 = new MatchEntity();
        matchEntity6.setMatchId("match6");
        matchEntity6.setHomeTeam(team3);
        matchEntity6.setAwayTeam(team2);
        matchEntity6.setLeagueInSeason(leagueInSeasonEntity);

        return Arrays.asList(matchEntity1,matchEntity2,matchEntity3,matchEntity4,matchEntity5,matchEntity6);
    }

    private LeagueInSeasonEntity getMockedLeagueInSeasonEntity(String leagueId, String seasonId) {
        LeagueEntity leagueEntity = new LeagueEntity();
        leagueEntity.setName(leagueId);
        SeasonEntity seasonEntity = new SeasonEntity();
        seasonEntity.setYear(seasonId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = new LeagueInSeasonEntity();
        mockedLeagueInSeasonEntity.setLeague(leagueEntity);
        mockedLeagueInSeasonEntity.setSeason(seasonEntity);
        return mockedLeagueInSeasonEntity;
    }
}