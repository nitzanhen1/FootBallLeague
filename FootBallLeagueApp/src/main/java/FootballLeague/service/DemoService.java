package FootballLeague.service;

import FootballLeague.entity.UserEntity;
import FootballLeague.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Autowired
    private UserRepository userRepository;

    public DemoService() {
    }

    public UserEntity printUser(String id){
        return userRepository.findOneById(id);
    }
}
