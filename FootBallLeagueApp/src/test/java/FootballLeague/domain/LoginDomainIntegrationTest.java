package FootballLeague.domain;

import FootballLeague.Service.MainService;
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
public class LoginDomainIntegrationTest {
//this class is for system integration tests between the Domain level and data access level (without mocks)

    @Autowired
    LoginDomain loginDomain;

    @Autowired
    SubscriberRepository subscriberRepository;

    @Before
    public void init() {
        SubscriberEntity subscriberEntity= new SubscriberEntity("user10","password10",false);
        subscriberRepository.save(subscriberEntity);
    }

    @Test
    public void loginTests() {

        assertTrue(loginDomain.login("user10","password10"));

        assertThrows(NullPointerException.class,() -> {
            loginDomain.login("user10",null);
        });

        assertThrows(InputMismatchException.class,() -> {
            loginDomain.login("user10","wordpass10");
        });

        assertThrows(NoSuchElementException.class,() -> {
            loginDomain.login("user11","password10");
        });

        assertFalse(loginDomain.login("user10","password10"));   //subscriber already logged in
    }

    @After
    public void end() {
        subscriberRepository.deleteById("user10");
    }

}
