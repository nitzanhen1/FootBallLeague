package FootballLeague.domain;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest
public class demoDomainTest {

    @Autowired
    DemoDomain DemoDomain;

    @Test
    public void printUser() {

        assertEquals(DemoDomain.findUser("1").getUserId(),"1");
        assertNotEquals(DemoDomain.findUser("1").getUserId(),"2");
    }
}