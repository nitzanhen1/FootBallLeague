package FootballLeague.Service;

import FootballLeague.entity.LeagueEntity;
import FootballLeague.entity.LeagueInSeasonEntity;
import FootballLeague.entity.MatchEntity;
import FootballLeague.entity.RefereeEntity;
import FootballLeague.entity.SeasonEntity;
import FootballLeague.entity.TeamEntity;
import FootballLeague.repository.LeagueInSeasonRepository;
import FootballLeague.repository.MatchRepository;
import FootballLeague.repository.RefereeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatCode;
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
    private List<TeamEntity> teamsInMatches;

    @BeforeEach
    public void init() {
        leagueInSeasonEntity = setUpLeagueInSeason(leagueId,seasonId);
        teamsInMatches = setUpTeamsInDB();
        matchesInSeasonInLeagues = setUpMatchesInDB(leagueInSeasonEntity, teamsInMatches);
        refereeInSeasonInLeague = setUpReferees(leagueInSeasonEntity,4);

        leagueInSeasonRepository.save(leagueInSeasonEntity);
        //TODO save to team repo? if yes then create team repo
        matchRepository.saveAll(matchesInSeasonInLeagues);
        refereeRepository.saveAll(refereeInSeasonInLeague);
    }

    @Test
    public void scheduleMatches() {
        //successful schedule games
        assertTrue(mainService.scheduleMatches(leagueId,seasonId));

        //invalid params
        assertThatCode(() -> mainService.scheduleMatches(null,seasonId)).isInstanceOf(NullPointerException.class);

        //seasonInLeague doesnt exist in the system
        assertThatCode(() -> mainService.scheduleMatches("Belgian",seasonId)).isInstanceOf(NoSuchElementException.class);

        //no matches are connected to this leagueInSeason
        LeagueInSeasonEntity anotherLeagueInSeason = setUpLeagueInSeason("Belgian",seasonId);
        leagueInSeasonRepository.save(anotherLeagueInSeason);
        assertThatCode(() -> mainService.scheduleMatches("Belgian",seasonId)).isInstanceOf(UnsupportedOperationException.class);

        //TODO delete belgian league

        //not enough referees to schedule the matches
        refereeInSeasonInLeague = setUpReferees(leagueInSeasonEntity,3);
        assertThatCode(() -> mainService.scheduleMatches(leagueId,seasonId)).isInstanceOf(UnsupportedOperationException.class);

        //one match doesn't have two teams- cannot schedule matches
        //TODO test if this makes the chnge in the database
        MatchEntity matchEntity = matchesInSeasonInLeagues.get(0);
        matchEntity.setAwayTeam(null);
        matchRepository.save(matchEntity);
        assertThatCode(() -> mainService.scheduleMatches(leagueId,seasonId)).isInstanceOf(UnsupportedOperationException.class);
    }

    @AfterEach
    public void end() {
        //TODO delete
    }



    private List<RefereeEntity> setUpReferees(LeagueInSeasonEntity leagueInSeasonEntity, int quantity) {
        RefereeEntity referee1;
        List<RefereeEntity> refereesInLeagueInSeason = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            referee1 = new RefereeEntity();
            referee1.setRoleId("referee1"+i);
            referee1.getLeagueInSeason().add(leagueInSeasonEntity);
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
}
