package FootballLeague.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="season")
public class SeasonEntity {

    @Id
    private String year;
    @JsonIgnore
    @OneToMany(mappedBy = "season")
    private Set<LeagueInSeasonEntity> leagueInSeason;

    public String getYear() {
        return year;
    }

    public SeasonEntity(String year, Set<LeagueInSeasonEntity> leagueInSeason) {
        this.year = year;
        this.leagueInSeason = leagueInSeason;
    }

    public SeasonEntity() {
        leagueInSeason = new HashSet<>();
    }

    public Set<LeagueInSeasonEntity> getLeagueInSeason() {
        return leagueInSeason;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setLeagueInSeason(Set<LeagueInSeasonEntity> leagueInSeason) {
        this.leagueInSeason = leagueInSeason;
    }
}
