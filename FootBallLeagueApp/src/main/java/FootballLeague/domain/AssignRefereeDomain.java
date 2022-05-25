package FootballLeague.domain;


import FootballLeague.entity.LeagueInSeasonEntity;
import FootballLeague.entity.RefereeEntity;
import FootballLeague.repository.LeagueInSeasonRepository;
import FootballLeague.repository.RefereeRepository;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class AssignRefereeDomain {

    @Autowired
    RefereeRepository refereeRepository;

    @Autowired
    LeagueInSeasonRepository leagueInSeasonRepository;

    //params- league, season, referees id
    //league-season repo - find by params
    //referee repo - find by param
    //update league-season - add referee
    //update referee - add league-season
    //save table changes

    //test - (1) if league-season exists (2) if referee exists
    // (3) see if referee already assigned to this league
    // (4) see if referee already assigned to another league

    public boolean assignReferee(String refereeId, String leagueId, String seasonId){
        if(refereeId == null || leagueId == null || seasonId == null)
            //invalid params
            throw new NullPointerException("invalid params");

        RefereeEntity refereeEntity = refereeRepository.getOneByRoleId(refereeId);
        if(refereeEntity==null)
            //referee id doen't exist in the DB
            throw new NoSuchElementException("referee doesn't exist in the DB");

        String lis = leagueId.concat(seasonId);
        LeagueInSeasonEntity leagueInSeasonEntity = leagueInSeasonRepository.getOneById(lis);
        if(leagueInSeasonEntity==null)
            //leagueInSeason doesn't exist in the system
            throw new NoSuchElementException("league and season are not connected");

        Set<LeagueInSeasonEntity> refereeLeagueInSeasons = refereeEntity.getLeagueInSeason();
        if(!refereeLeagueInSeasons.isEmpty()) {
            for (LeagueInSeasonEntity refereeLeagueInSeason : refereeLeagueInSeasons) {
                if (seasonId.equals(refereeLeagueInSeason.getSeason().getYear())) {
                    if (leagueId.equals(refereeLeagueInSeason.getLeague().getName()))
                        //referee already assigned to this League and Season
                        return false;
                    else
                        //referee already assigned to this another League this season;
                        throw new UnsupportedOperationException("referee alreay assigned to another league in this season");
                }
            }
        }
        return save(refereeEntity,leagueInSeasonEntity);
        //referee successfully assigned to this League and Season
    }

    private boolean save(RefereeEntity refereeEntity, LeagueInSeasonEntity leagueInSeasonEntity){
        refereeEntity.getLeagueInSeason().add(leagueInSeasonEntity);
        leagueInSeasonEntity.getReferees().add(refereeEntity);
        refereeRepository.save(refereeEntity);
        leagueInSeasonRepository.save(leagueInSeasonEntity);
        return true;
        //TODO see if save twice is unnecessary
    }
}
