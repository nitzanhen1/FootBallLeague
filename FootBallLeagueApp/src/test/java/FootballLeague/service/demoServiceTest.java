package FootballLeague.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class demoServiceTest {

    @Autowired
    DemoService DemoService;

    @Test
    void printUser() {

        assertEquals(DemoService.printUser("1").getId(),"1");
        assertNotEquals(DemoService.printUser("1").getId(),"2");
    }
}