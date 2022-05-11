package FootballLeague.Service;

import FootballLeague.domain.DemoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoService {

    @Autowired
    DemoDomain demoDomain;

    @RequestMapping("/test/{id}")
    public String getId(@PathVariable String id){
        return demoDomain.findUser(id).getUserId();
    }

    @RequestMapping("/test")
    public String hi(){
        return "hi";
    }


}