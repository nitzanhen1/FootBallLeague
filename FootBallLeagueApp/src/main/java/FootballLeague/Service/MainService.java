package FootballLeague.Service;

import FootballLeague.domain.AssignRefereeDomain;
import FootballLeague.domain.LoginDomain;
import FootballLeague.domain.ScheduleMatchDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainService {

    @Autowired
    AssignRefereeDomain assignRefereeDomain;

    @Autowired
    LoginDomain loginDomain;

    @Autowired
    ScheduleMatchDomain scheduleMatchDomain;

    public boolean login(String userName,String password){
        return loginDomain.login(userName,password);
    }

    public boolean assignRefereeToLeague(String refereeId, String leagueId, String seasonId){
        return assignRefereeDomain.assignReferee(refereeId, leagueId, seasonId);
    }

    public boolean scheduleMatches(String leagueId, String seasonId){
        return scheduleMatchDomain.scheduleMatches(leagueId, seasonId);
    }



}
