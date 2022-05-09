package FootballLeague.controller;

import FootballLeague.entity.UserEntity;
import FootballLeague.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    DemoService demoService;

    @RequestMapping("/test/{id}")
    public String getId(@PathVariable String id){
        return demoService.printUser(id).getId();
    }

    @RequestMapping("/test")
    public String hi(){
        return "hi";
        //return demoService.printUser(id);
    }


}
