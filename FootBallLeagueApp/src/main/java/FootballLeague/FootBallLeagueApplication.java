package FootballLeague;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class FootBallLeagueApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootBallLeagueApplication.class, args);
	}

}
