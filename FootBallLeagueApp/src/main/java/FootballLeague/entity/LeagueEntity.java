package FootballLeague.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="league")
public class LeagueEntity {

    @Id
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "league")
    private Set<LeagueInSeasonEntity> leagueInSeason;

    public String getName() {
        return name;
    }

    public LeagueEntity(String name, Set<LeagueInSeasonEntity> leagueInSeason) {
        this.name = name;
        this.leagueInSeason = leagueInSeason;
    }

    public LeagueEntity() {
        leagueInSeason = new HashSet<>();
    }

    public Set<LeagueInSeasonEntity> getLeagueInSeason() {
        return leagueInSeason;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeagueInSeason(Set<LeagueInSeasonEntity> leagueInSeason) {
        this.leagueInSeason = leagueInSeason;
    }
}
