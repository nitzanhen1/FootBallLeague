package FootballLeague.domain;
import FootballLeague.entity.LeagueEntity;
import FootballLeague.entity.LeagueInSeasonEntity;
import FootballLeague.entity.RefereeEntity;
import FootballLeague.entity.SeasonEntity;
import FootballLeague.repository.LeagueInSeasonRepository;
import FootballLeague.repository.RefereeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AssignRefereeDomainUnitTest {

    @InjectMocks
    private AssignRefereeDomain assignRefereeDomain;

    @Mock
    private RefereeRepository refereeRepository;

    @Mock
    private LeagueInSeasonRepository leagueInSeasonRepository;

    @Test
    public void SuccessfulAssignRefereeNoExceptionsTest() {
        //mocks
        String refereeId = "referee6";
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        RefereeEntity mockedRefereeEntity = getMockedRefereeEntity(refereeId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        when(refereeRepository.getOneByRoleId(refereeId)).thenReturn(mockedRefereeEntity);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(mockedLeagueInSeasonEntity);

        //tests
        assertThatCode(() -> assignRefereeDomain.assignReferee(refereeId,leagueId,seasonId)).doesNotThrowAnyException();
        verify(refereeRepository).save(mockedRefereeEntity);
        verify(leagueInSeasonRepository).save(mockedLeagueInSeasonEntity);
    }

    @Test
    public void SuccessfulAssignRefereeAssertTrueTest() {
        //mocks
        String refereeId = "referee6";
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        RefereeEntity mockedRefereeEntity = getMockedRefereeEntity(refereeId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        when(refereeRepository.getOneByRoleId(refereeId)).thenReturn(mockedRefereeEntity);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(mockedLeagueInSeasonEntity);

        //tests
        assertTrue(assignRefereeDomain.assignReferee(refereeId,leagueId,seasonId));
        verify(refereeRepository).save(mockedRefereeEntity);
        verify(leagueInSeasonRepository).save(mockedLeagueInSeasonEntity);
    }

    @Test
    public void assignRefereeNullPointerExceptionTest() {
        //mocks
        String refereeId = null;
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        RefereeEntity mockedRefereeEntity = getMockedRefereeEntity(refereeId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        when(refereeRepository.getOneByRoleId(refereeId)).thenReturn(mockedRefereeEntity);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(mockedLeagueInSeasonEntity);

        //tests
        assertThatCode(() -> assignRefereeDomain.assignReferee(refereeId,leagueId,seasonId)).isInstanceOf(NullPointerException.class);
        verify(refereeRepository, never()).save(mockedRefereeEntity);
        verify(leagueInSeasonRepository, never()).save(mockedLeagueInSeasonEntity);
    }

    @Test
    public void assignRefereeNoRefereeInDBTest() {
        //mocks
        String refereeId = "referee6";
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        RefereeEntity mockedRefereeEntity = getMockedRefereeEntity(refereeId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        when(refereeRepository.getOneByRoleId(refereeId)).thenReturn(null);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(mockedLeagueInSeasonEntity);

        //tests
        assertThatCode(() -> assignRefereeDomain.assignReferee(refereeId,leagueId,seasonId)).isInstanceOf(NoSuchElementException.class);
        verify(refereeRepository, never()).save(mockedRefereeEntity);
        verify(leagueInSeasonRepository, never()).save(mockedLeagueInSeasonEntity);
    }

    @Test
    public void assignRefereeNoLeagueInSeasonInDBTest() {
        //mocks
        String refereeId = "referee6";
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        RefereeEntity mockedRefereeEntity = getMockedRefereeEntity(refereeId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        when(refereeRepository.getOneByRoleId(refereeId)).thenReturn(mockedRefereeEntity);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(null);

        //tests
        assertThatCode(() -> assignRefereeDomain.assignReferee(refereeId,leagueId,seasonId)).isInstanceOf(NoSuchElementException.class);
        verify(refereeRepository, never()).save(mockedRefereeEntity);
        verify(leagueInSeasonRepository, never()).save(mockedLeagueInSeasonEntity);
    }

    @Test
    public void refereeAlreadyAssignedToThisLeagueInSeasonTest() {
        //mocks
        String refereeId = "referee6";
        String leagueId = "German";
        String seasonId = "2023";
        String lis = leagueId.concat(seasonId);
        RefereeEntity mockedRefereeEntity = getMockedRefereeEntity(refereeId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntity = getMockedLeagueInSeasonEntity(leagueId,seasonId);
        //connect refere to wanted league in season before the service is triggered
        mockedRefereeEntity.getLeagueInSeason().add(mockedLeagueInSeasonEntity);
        when(refereeRepository.getOneByRoleId(refereeId)).thenReturn(mockedRefereeEntity);
        when(leagueInSeasonRepository.getOneById(lis)).thenReturn(mockedLeagueInSeasonEntity);

        //tests
        assertFalse(assignRefereeDomain.assignReferee(refereeId,leagueId,seasonId));
        verify(refereeRepository, never()).save(mockedRefereeEntity);
        verify(leagueInSeasonRepository, never()).save(mockedLeagueInSeasonEntity);
    }

    //TODO add system test for referee
    //TODO logic for schedule and all of tests
    //TODO integration tests for all logic

    @Test
    public void refereeAlreadyAssignedToAnotherLeagueInThisSeasonTest() {
        //mocks
        String refereeId = "referee6";
        String leagueIdWanted = "German";
        String leagueIdAssignedTo = "Belgian";
        String seasonId = "2023";
        String lisWanted = leagueIdWanted.concat(seasonId);
        RefereeEntity mockedRefereeEntity = getMockedRefereeEntity(refereeId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntityWanted = getMockedLeagueInSeasonEntity(leagueIdWanted,seasonId);
        LeagueInSeasonEntity mockedLeagueInSeasonEntityAssignedTo = getMockedLeagueInSeasonEntity(leagueIdAssignedTo,seasonId);
        //connect refere to another league in the same season before the service is triggered
        mockedRefereeEntity.getLeagueInSeason().add(mockedLeagueInSeasonEntityAssignedTo);
        when(refereeRepository.getOneByRoleId(refereeId)).thenReturn(mockedRefereeEntity);
        when(leagueInSeasonRepository.getOneById(lisWanted)).thenReturn(mockedLeagueInSeasonEntityWanted);

        //tests
        assertThatCode(() -> assignRefereeDomain.assignReferee(refereeId,leagueIdWanted,seasonId)).isInstanceOf(UnsupportedOperationException.class);
        verify(refereeRepository, never()).save(mockedRefereeEntity);
        verify(leagueInSeasonRepository, never()).save(mockedLeagueInSeasonEntityWanted);
    }

    private RefereeEntity getMockedRefereeEntity(String refereeId) {
        RefereeEntity mockedRefereeEntity = new RefereeEntity();
        mockedRefereeEntity.setRoleId(refereeId);
        return mockedRefereeEntity;
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