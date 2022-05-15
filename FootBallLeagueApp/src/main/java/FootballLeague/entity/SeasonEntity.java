package FootballLeague.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="season")
public class SeasonEntity {

    @Id
    private int year;
    @JsonIgnore
    @OneToMany(mappedBy = "season")
    private Set<LeagueInSeasonEntity> leagueInSeason;

    public int getYear() {
        return year;
    }

    public SeasonEntity(int year, Set<LeagueInSeasonEntity> leagueInSeason) {
        this.year = year;
        this.leagueInSeason = leagueInSeason;
    }

    public SeasonEntity() {
    }

    public Set<LeagueInSeasonEntity> getLeagueInSeason() {
        return leagueInSeason;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setLeagueInSeason(Set<LeagueInSeasonEntity> leagueInSeason) {
        this.leagueInSeason = leagueInSeason;
    }
}
