package FootballLeague.Service;

import FootballLeague.entity.SubscriberEntity;
import FootballLeague.repository.SubscriberRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;


@RunWith( SpringRunner.class )
@SpringBootTest
public class MainServiceLoginTest {
//this class is for system end-to-end tests
//system tests for login logic
    @Autowired
    MainService mainService;

    @Autowired
    SubscriberRepository subscriberRepository;

    @Before
    public void init() {
        SubscriberEntity subscriberEntity = new SubscriberEntity("user10", "password10", false);
        subscriberRepository.save(subscriberEntity);
    }

    @Test
    public void login() {

        //successful login
        assertTrue(mainService.login("user10", "password10"));

        //invalid params
        assertThrows(NullPointerException.class, () -> {
            mainService.login("user10", null);
        });

        //wrong password
        assertThrows(InputMismatchException.class, () -> {
            mainService.login("user10", "wordpass10");
        });

        //subscriber doesn't exist in the DB
        assertThrows(NoSuchElementException.class, () -> {
            mainService.login("user11", "password10");
        });

        //subscriber already logged in
        assertFalse(mainService.login("user10", "password10"));
    }

    @After
    public void end() {
        subscriberRepository.deleteById("user10");
    }
}