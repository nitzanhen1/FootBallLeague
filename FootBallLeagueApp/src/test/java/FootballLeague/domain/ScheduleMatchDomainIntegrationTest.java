package FootballLeague.domain;

import FootballLeague.entity.LeagueEntity;
import FootballLeague.entity.LeagueInSeasonEntity;
import FootballLeague.entity.MatchEntity;
import FootballLeague.entity.RefereeEntity;
import FootballLeague.entity.SeasonEntity;
import FootballLeague.entity.TeamEntity;
import FootballLeague.repository.LeagueInSeasonRepository;
import FootballLeague.repository.LeagueRepository;
import FootballLeague.repository.MatchRepository;
import FootballLeague.repository.RefereeRepository;
import FootballLeague.repository.SeasonRepository;
import FootballLeague.repository.TeamRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith( SpringRunner.class )
@SpringBootTest
public class ScheduleMatchDomainIntegrationTest {
    //this class is for system end-to-end tests
//system tests for login logic
    @Autowired
    ScheduleMatchDomain scheduleMatchDomain;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    RefereeRepository refereeRepository;

    @Autowired
    LeagueInSeasonRepository leagueInSeasonRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    SeasonRepository seasonRepository;


    private final String leagueId = "French";
    private  final String seasonId = "2024";
    private LeagueInSeasonEntity leagueInSeasonEntity;
    private List<MatchEntity> matchesInSeasonInLeagues;
    private List<RefereeEntity> refereeInSeasonInLeagues;
    private List<TeamEntity> teamsInMatches;

    @Before
    public void init() {
        leagueInSeasonEntity = setUpLeagueInSeason(leagueId,seasonId);
        teamsInMatches = setUpTeamsInDB();
        matchesInSeasonInLeagues = setUpMatchesInDB(leagueInSeasonEntity, teamsInMatches);
        refereeInSeasonInLeagues = new ArrayList<>();
        refereeInSeasonInLeagues.addAll(addReferees(leagueInSeasonEntity,3, 1));

        leagueInSeasonRepository.save(leagueInSeasonEntity);
        matchRepository.saveAll(matchesInSeasonInLeagues);
        refereeRepository.saveAll(refereeInSeasonInLeagues);
    }

    @Test
    public void scheduleMatches() {
        //not enough referees to schedule the matches
        assertThatCode(() -> scheduleMatchDomain.scheduleMatches(leagueId,seasonId)).isInstanceOf(UnsupportedOperationException.class);

        refereeInSeasonInLeagues.addAll(addReferees(leagueInSeasonEntity,1,4));
        //successful schedule games
        assertTrue(scheduleMatchDomain.scheduleMatches(leagueId,seasonId));

        //invalid params
        assertThatCode(() -> scheduleMatchDomain.scheduleMatches(null,seasonId)).isInstanceOf(NullPointerException.class);

        //seasonInLeague doesnt exist in the system
        assertThatCode(() -> scheduleMatchDomain.scheduleMatches("Israeli",seasonId)).isInstanceOf(NoSuchElementException.class);

        //no matches are connected to this leagueInSeason
        LeagueInSeasonEntity anotherLeagueInSeason = setUpLeagueInSeason("Israeli",seasonId);
        leagueInSeasonRepository.save(anotherLeagueInSeason);
        assertThatCode(() -> scheduleMatchDomain.scheduleMatches("Israeli",seasonId)).isInstanceOf(UnsupportedOperationException.class);
        anotherLeagueInSeason.setSeason(null);
        anotherLeagueInSeason.setLeague(null);
        leagueInSeasonRepository.delete(anotherLeagueInSeason);
        leagueRepository.deleteById("Israeli");
    }

    private List<RefereeEntity> addReferees(LeagueInSeasonEntity leagueInSeasonEntity, int quantity, int startId) {
        RefereeEntity referee1;
        List<RefereeEntity> refereesInLeagueInSeason = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            referee1 = new RefereeEntity();
            referee1.setRoleId("referee1"+startId++);
            referee1.getLeagueInSeason().add(leagueInSeasonEntity);
            leagueInSeasonEntity.getReferees().add(referee1);
            refereeRepository.save(referee1);
            leagueInSeasonRepository.save(leagueInSeasonEntity);
            refereesInLeagueInSeason.add(referee1);
        }
        return refereesInLeagueInSeason;
    }

    private List<MatchEntity> setUpMatchesInDB(LeagueInSeasonEntity leagueInSeasonEntity, List<TeamEntity> teamsInMatches) {
        List<MatchEntity> matchEntities = new ArrayList<>();
        MatchEntity matchEntity;
        int teamNum = teamsInMatches.size();
        for (int i = 0; i < teamNum; i++) {
            for (int j = 0; j < teamNum; j++) {
                if(i !=j){
                    matchEntity = new MatchEntity();
                    matchEntity.setMatchId("match"+i+j);
                    matchEntity.setHomeTeam(teamsInMatches.get(i));
                    matchEntity.setAwayTeam(teamsInMatches.get(j));
                    matchEntity.setLeagueInSeason(leagueInSeasonEntity);
                    leagueInSeasonEntity.getMatches().add(matchEntity);
                    matchRepository.save(matchEntity);
                    leagueInSeasonRepository.save(leagueInSeasonEntity);
                    matchEntities.add(matchEntity);
                }
            }
        }
        return matchEntities;
    }

    private List<TeamEntity> setUpTeamsInDB() {
        TeamEntity team1 = new TeamEntity();
        team1.setTeamName("team11");
        team1.setHomeStadium("stadium11");
        TeamEntity team2 = new TeamEntity();
        team2.setTeamName("team12");
        team2.setHomeStadium("stadium12");
        TeamEntity team3 = new TeamEntity();
        team3.setTeamName("team13");
        team3.setHomeStadium("stadium13");
        return Arrays.asList(team1,team2,team3);
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
    public void end() {
        leagueInSeasonEntity.setSeason(null);
        leagueInSeasonEntity.setLeague(null);
        leagueInSeasonEntity.getReferees().removeAll(leagueInSeasonEntity.getReferees());

        leagueInSeasonEntity.getMatches().removeAll(leagueInSeasonEntity.getMatches());

        for(RefereeEntity refereeEntity: refereeInSeasonInLeagues){
            //many to many
            refereeEntity.getLeagueInSeason().removeAll(refereeEntity.getLeagueInSeason());

            refereeEntity.getMatchesAsAssistantReferee().removeAll(refereeEntity.getMatchesAsAssistantReferee());
            refereeEntity.getMatchesAsMainReferee().removeAll(refereeEntity.getMatchesAsMainReferee());

        }
        for(MatchEntity matchEntity: matchesInSeasonInLeagues){
            //many to one
            matchEntity.setLeagueInSeason(null);
            matchEntity.setMainReferee(null);
            matchEntity.getAssistantReferees().removeAll(matchEntity.getAssistantReferees());
            matchEntity.setHomeTeam(null);
            matchEntity.setAwayTeam(null);
        }

        matchRepository.deleteAll(matchesInSeasonInLeagues);
        leagueInSeasonRepository.delete(leagueInSeasonEntity);
        refereeRepository.deleteAll(refereeInSeasonInLeagues);

        teamRepository.deleteAll(teamsInMatches);
        seasonRepository.deleteById(seasonId);
        leagueRepository.deleteById(leagueId);
    }
}
