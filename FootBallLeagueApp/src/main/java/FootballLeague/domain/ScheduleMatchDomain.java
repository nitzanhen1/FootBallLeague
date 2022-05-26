package FootballLeague.domain;

import FootballLeague.entity.LeagueInSeasonEntity;
import FootballLeague.entity.MatchEntity;
import FootballLeague.entity.RefereeEntity;
import FootballLeague.repository.LeagueInSeasonRepository;
import FootballLeague.repository.MatchRepository;
import FootballLeague.repository.RefereeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

@Service
public class ScheduleMatchDomain {

    @Autowired
    LeagueInSeasonRepository leagueInSeasonRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    RefereeRepository refereeRepository;

    public boolean scheduleMatches(String leagueId, String seasonId){
        if(leagueId == null || seasonId == null)
            //invalid params
            throw new NullPointerException("invalid params");

        String lis = leagueId.concat(seasonId);
        LeagueInSeasonEntity leagueInSeasonEntity = leagueInSeasonRepository.getOneById(lis);
        if(leagueInSeasonEntity==null)
            //leagueInSeason doesn't exist in the system
            throw new NoSuchElementException("league and season are not connected");
        List<MatchEntity> matchesInSeasonInLeagues = matchRepository.findAllByLeagueInSeason(leagueInSeasonEntity);
        if(matchesInSeasonInLeagues.size()==0)
            throw new UnsupportedOperationException("no matches to schedule");
        List<RefereeEntity> refereeInSeasonInLeague = refereeRepository.findAllByLeagueInSeason(leagueInSeasonEntity);
        if(refereeInSeasonInLeague.size()<4)
            throw new UnsupportedOperationException("minimum 4 referees required to schedule match");
        int day =1;
        int year = Integer.parseInt(seasonId);
        Random random = new Random();
        Set<Integer> indexes;
        RefereeEntity refereeEntity;
        int counter = 0, index;
        for (MatchEntity matchEntity:matchesInSeasonInLeagues){
            matchEntity.setStadium(matchEntity.getHomeTeam().getHomeStadium());
            matchEntity.setDate(LocalDate.of(year,8,day++));
            matchEntity.setTime(LocalTime.of(20,00));
            //setReferees(matchEntity, refereeInSeasonInLeague);

            indexes = new HashSet<>();
            counter = 0;
            while(counter<4){
                index = random.nextInt(refereeInSeasonInLeague.size());
                if(indexes.contains(index))
                    continue;
                refereeEntity = refereeInSeasonInLeague.get(index);
                if(indexes.isEmpty()){// assign main referee
                    matchEntity.setMainReferee(refereeEntity);
                    refereeEntity.getMatchesAsMainReferee().add(matchEntity);
                }
                else{//assign 3 assistant referees
                    matchEntity.getAssistantReferees().add(refereeEntity);
                    refereeEntity.getMatchesAsAssistantReferee().add(matchEntity);
                }
                indexes.add(index);
                counter++;
            }
        }

        //save changes to the DB
        for (MatchEntity matchEntity:matchesInSeasonInLeagues) {
            matchRepository.save(matchEntity);
            refereeRepository.save(matchEntity.getMainReferee());
            refereeRepository.saveAll(matchEntity.getAssistantReferees());
        }
        return true;
    }
}
